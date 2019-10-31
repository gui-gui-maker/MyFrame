<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>选择机构</title>
<%@include file="/k/kui-base-form.jsp"%>
<link rel="stylesheet" type="text/css" href="pub/common/css/common.css">
<script type="text/javascript">
var org_types = <u:dict code="pub_org_type" />;
var ztree=null,treeNodes=null;
var ztreeSetting = {
    data: {
        key: {
            name: "text"
        }
    },
    async:{
    	enable: ${param.sync},
    	url: "rbac/org/choose/tree.do",
		type: "get",
		autoParam: ["id=pid"],
		otherParam: {
			props: "${param.props}",
			type: "${param.type}",
			range: "${param.range}",
			sync: "${param.sync}"
		}
    },
    callback: {
        onNodeCreated:function(event, treeId, treeNode){
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            if(treeNode.level=="0"){
                treeNode.icon = "k/kui/images/icons/16/home.png";
            }
            else if(treeNode["property"] == 'unit'){
                treeNode.icon = "k/kui/images/icons/16/org.png";
            }
            else if (treeNode["property"] == 'dep'){
				treeNode.icon = "k/kui/images/icons/16/group.png";
            }
            else{
            	treeNode.icon = "k/kui/images/icons/16/folders_explorer.png";
            }
            
            //初始化已选状态
            if($("#choosed-list-item-" + treeNode.id).length == 1)
            	treeNode.checked = true;
            
            treeObj.updateNode(treeNode);
        },
        beforeClick: function(){
        	return false;
        }
    },
    check : {
        enable: false
    },
    view: {
		addHoverDom: function(treeId, treeNode){
			if ($("#diy_btn_" + treeNode.id).length > 0) return;
			var nodeObj = $("#" + treeNode.tId + "_a");
			var btnHtml = $("<button type='button' class='ztree_add_btn' id='diy_btn_" + treeNode.id
				+ "' title='添加" + treeNode.text + "' onfocus='this.blur();'>+添加到已选</button>");
			btnHtml.click(function(){
				addChoosedItem(treeNode.id,treeNode.text);
			});
			btnHtml.appendTo(nodeObj);
		},
		removeHoverDom: function(treeId, treeNode){
			var btn = $("#diy_btn_" + treeNode.id);
			if (btn.length > 0){
				btn.remove();
			}
		}
	}
};

$(function(){
	$("#layout").ligerLayout({
		rightWidth: 350,
		space: 0
	});
	$("#centerArea").addClass("navtab").ligerTab({
		onAfterSelectTabItem: function(tabid){
			if(tabid=="treeview" && ztree == null){
				initOrgTree();
			}if(tabid=='org-groups' && $("#org-groups-ctr").children().length==0){
				loadOrgGroups();
			}
		}
	});
	var initTypes = "${param.type}";
	$.each(org_types,function(idx){
		var hasin = initTypes=="all" || initTypes.indexOf(this.id) >= 0;
		if(hasin){
			//$("#filters").append("<label for='org_type"+ idx + "'><input class='org-type-item' value='" + this.id + 
			//		"' type='checkbox' name='org_type' id='org_type"+ idx +"' onchange='initOrgTree()' />&nbsp;"+ this.text + "</label>");
			$("#orgtype").append("<option value='" + this.id + "'>" + this.text + "</option>");
		}
	});
	
	//初始化已选
	if(api.data.initIds && api.data.initNames){
		var idarr = api.data.initIds.split(",");
		var namearr = api.data.initNames.split(",");
		for(var i in idarr){
			if(idarr[i]){
				addChoosedItem(idarr[i],namearr[i]);
			}
		}
		refreshChoosedNum();
	}
	winResize();
	$(window).resize(winResize);
});

function winResize(){
	$("#search-result").height($(window).height()-90);
	//$("#org-groups-ctr").height($(window).height()-90);
	$("#choosed-list").height($(window).height()-46);
}

function initOrgTree(){
	$("body").mask("正在加载，请稍候！");
	var otype = "${param.type}";
	var chks = $("#filters .org-type-item:checked");
	if(chks.length > 0){
		otype = "";
		$("#filters input:checked").each(function(){
			otype +=  "," + this.value;
		});
	}
	
	$.getJSON("rbac/org/choose/tree.do",{
		props: "${param.props}",
		type: otype,
		self: "${param.self}",
		range: "${param.range}",
		sync: "${param.sync}"
	},function(dataList){
		if(ztree != null){
			ztree.destroy();
		}
		ztree = $.fn.zTree.init($("#orgTree"), ztreeSetting, dataList);
		treeNodes = ztree.getNodes();
		ztree.expandNode(treeNodes[0],true);
		if(!${param.sync} && ${param.expand}){
			ztree.expandAll(true);
		}
		$("body").unmask();
	});
}

/**
 * 获取选择结果
 */
function chooseResult(){
	var choosed = $("#choosed-list a");
	if(choosed.length == 0){
		$.ligerDialog.alert("您没有选择机构！");
		return null;
	}
	var result = {code:"",name:"",data:[]};
	choosed.each(function(i){
		var $this = $(this);
		result.code += (i==0?"":",") + $this.attr("data-value");
		result.name += (i==0?"":",") + $this.text();
		result.data.push({id: $this.attr("data-value"),name: $this.text()});
	});
	return result;
}

function addChoosedItem(id,name){
	if(!${param.multiple}){
		$("#choosed-list").empty();
		$("#search-result a.checked").removeClass("checked");
		$("#org-groups-ctr a.checked").removeClass("checked");
	}
	if($("#choosed-list-item-" + id).length == 0){
		$("<a class='choosed-list-item' id='choosed-list-item-" + id + "' data-value='" + id + "'>"+ name +"</a>").appendTo("#choosed-list").hover(function(){
			if(!$(this).hasClass("checked")) $(this).addClass("hover");
		},function(){
			$(this).removeClass("hover");
		}).click(function(){
			$(this).toggleClass("hover");
			$(this).toggleClass("checked");
		}).dblclick(function(){
			$("#search-item-" + id).removeClass("checked");
			removeChoosedItem(id);
		});
	}
	refreshChoosedNum();
	$("#search-item-" + id).addClass("checked");
	$("#group-item-" + id).addClass("checked");
	checkAllSearchResultIsChoosed();
}

function removeChoosedItem(id){
	$("#choosed-list-item-" + id).remove();
	refreshChoosedNum();
	if(id.indexOf("group_")==0){
		$(":checkbox","#g-" + id.replace("group_","")).attr("checked",false);
	}
	else{
		$("#search-item-" + id).removeClass("checked");
		$("#group-item-" + id).removeClass("checked");
	}
	checkAllSearchResultIsChoosed();
}

function refreshChoosedNum(){
	$("#choosed-num").text($("#choosed-list a").length);
}

function doKeypressSearch(event){
	 var e = event || window.event;         // Key event object
     var code = e.charCode || e.keyCode;    // What key was pressed
	 if(code==13 || code==108){
		 doSearch();
	 }
}

function doSearch(){
	var orgtype = $("#orgtype").val();
	var searchName = $("#searchName").val();
	if(orgtype=="" && searchName==""){
		top.$.notice("请输入搜索条件！",3,"k/kui/images/icons/dialog/32X32/hits.png");
		return;
	}
	$("body").mask("正在搜索，请稍候...");
	$.post("rbac/org/choose/find.do",{type:orgtype,name:searchName,props:"${param.props}",range:"${param.range}"},function(resp){
		if(resp.success){
			$("#search-result").empty();
			if(resp.data.length > 0){
				for(var i in resp.data){
					var checked = $("#choosed-list-item-" + resp.data[i].id).length > 0;
					$("#search-result").append("<a class='search-result-item" + (checked?" checked":"") + 
							"' id='search-item-" + resp.data[i].id + "' data-value='" + resp.data[i].id + "'>" + resp.data[i].name + "</a>");
				}
				$("#search-result a").click(function(){
					if($(this).hasClass("checked")){
						removeChoosedItem($(this).attr("data-value"));
						refreshChoosedNum();
					}else{
						addChoosedItem($(this).attr("data-value"),$(this).text());
					}
				});
				$("#chooseAllResult").show();
				checkAllSearchResultIsChoosed();
			}else{
				$("#chooseAllResult").hide();
			}
		}else{
			top.$.notice("搜索失败，请稍候重试！",4,"k/kui/images/icons/dialog/32X32/hits.png");
		}
		$("body").unmask();
	},"json");
}

function checkAllSearchResultIsChoosed(){
	if($("#search-result a.checked").length==0)
		$("#chooseAllResult").html('<span class="l-icon l-icon-check"></span>全选');
	else
		$("#chooseAllResult").html('<span class="l-icon l-icon-cancel"></span>取消');
}

function clearChoosed(isAll){
	var $els = isAll?$("#choosed-list ").children():$("#choosed-list .checked");
	$els.each(function(){
		removeChoosedItem($(this).attr("data-value"));
	});
}

function addGroup(){
	winOpen({
		title: '添加自建分组',
		lock: true,
		width: 500,
		height: 300,
		parent: api,
		content: "url:pub/common/org_group_detail.jsp?pageStatus=add",
		data:{
			callback: function(group){
				top.$.notice("添加分组成功！",3);
				showOrgGroup([group]);
			}
		}
	});
}

function editGroup(gid){
	winOpen({
		title: '修改自建分组',
		lock: true,
		width: 800,
		height: 450,
		parent: api,
		content: "url:pub/common/org_group_detail.jsp?pageStatus=edit&id=" + gid,
		data:{
			callback: function(group){
				top.$.notice("修改分组成功！",3);
				showOrgGroup([group]);
			}
		}
	});
}

function delGroup(gid){
	$.ligerDialog.confirm("删除后无法恢复，确定删除？",function(rst){
		if(!rst) return;
		$("body").mask("正在处理，请稍候...");
		$.post("rbac/org/group/delete.do",{ids: gid},function(resp){
			if(resp.success){
				top.$.notice("删除成功！",3);
				$("#g-" + gid).remove();
			}else{
				$.ligerDialog.error("删除失败！<br/>请稍后重试或者联系技术支持人员。");
			}
			$("body").unmask();
		},"json");
	});
}

function loadOrgGroups(){
	$("body").mask("正在加载分组信息...");
	$.getJSON("rbac/org/group/get_user_groups.do",function(resp){
		if(resp.success){
			showOrgGroup(resp.data);
		}else{
			$.ligerDialog.error("获取自建分组失败！");
		}
		$("body").unmask();
	});
}

//分组选择，分组选择的结果id中，添加了前缀“group_”，已表区别
function checkGroup(obj){
	var groupToOrg = "${param.groupToOrg}"=="true";
	var $group = $(obj).parent().parent();
	if(obj.checked){
		//参数指定分组选择时转换为下面所有机构
		if(groupToOrg){
			$(obj).parent().siblings().each(function(){
				addChoosedItem($(this).attr("data-value"),$(this).text());
			});
		}
		else{
			addChoosedItem("group_" + $group.attr("data-value"),$(obj).prev().text());
		}
	}
	else{
		if(groupToOrg){
			$(obj).parent().siblings().each(function(){
				removeChoosedItem($(this).attr("data-value"));
			});
		}
		else{
			removeChoosedItem("group_" + $group.attr("data-value"));
		}
	}
}

function closeOrExpandGrou(obj){
	var $g = $(obj).parent().parent();
	if($g.hasClass("expand")){
		$g.removeClass("expand").addClass("closed").find(".search-result-item").show();
	}else{
		$g.removeClass("closed").addClass("expand").find(".search-result-item").hide();
	}
}

function showOrgGroup(groups){
	$.each(groups,function(){
		var $group = $("<div class='org-group expand' id='g-" + this.id + "' data-value='" + this.id 
				+ "'><div class='org-group-head'><span class='group-name' onclick='closeOrExpandGrou(this)'><i></i>" + this.groupName
				+ "</span><input type='checkbox' name='org-grount-chkbox' onchange='checkGroup(this)'/>"
				+ "<a class='edit-btn l-button3 has-icon' onclick='editGroup(\"" + this.id + "\")'><span class='l-icon l-icon-add'></span>修改</a>" 
				+ "<a class='edit-btn l-button3 has-icon' onclick='delGroup(\"" + this.id + "\")'><span class='l-icon l-icon-del'></span>删除</a></div></div>");
		if(this.orgId && this.orgName){
			var idarr = this.orgId.split(',');
			var namearr = this.orgName.split('，');
			for(var i in idarr){
				var checked = $("#choosed-list-item-" + idarr[i]).length > 0;
				$group.append("<a class='search-result-item" + (checked?" checked":"") + 
						"' id='group-item-" + idarr[i] + "' data-value='" + idarr[i] + "'>" + namearr[i] + "</a>");
			}
		}
		if($("#g-" + this.id).length > 0){
			removeChoosedItem("group_" + this.id);
			$("#g-" + this.id).removeAttr("id").after($group).remove();
			$group.find(":checkbox").attr("checked",true);
			addChoosedItem("group_" + this.id,this.groupName);
		}else{
			if($("#choosed-list-item-group_" + this.id).length > 0)
				$group.find(":checkbox").attr("checked",true);
			$group.appendTo("#org-groups-ctr");
		}
		$(".search-result-item",$group).click(function(){
			$(this).toggleClass("checked");
			if($(this).hasClass("checked")){
				addChoosedItem($(this).attr("data-value"),$(this).text());
			}else{
				removeChoosedItem($(this).attr("data-value"));
				refreshChoosedNum();
			}
		});
	});
}

function chooseAllSearchResult(btn){
	var isAdd = $(btn).find(".l-icon-check").length>0;
	$("#search-result a.search-result-item").each(function(){
		if(isAdd){
			if(!$(this).hasClass("checked")){
				$(this).addClass("checked")
				addChoosedItem($(this).attr("data-value"),$(this).text());
			}
		}else{
			if($(this).hasClass("checked")){
				$(this).removeClass("checked")
				removeChoosedItem($(this).attr("data-value"));
			}
		}
	});
}
</script>
</head>
<body>
<div id="layout">
	<div id="centerArea" position="center">
		<div tabid="searchview" title="条件搜索">
			<div class="toolbar left">
				<select id="orgtype" class="org-type"><option value="">---机构类型---</option></select>
				<input type="text" id="searchName" class="search-name" onkeyup="doKeypressSearch()" placeHolder="请输入机构名称" />
				<a class="seaech-button l-button3 has-icon" onclick="doSearch()"><span class="l-icon l-icon-search"></span>搜索</a>
				<a class="seaech-button l-button3 has-icon choose-all" id="chooseAllResult" onclick="chooseAllSearchResult(this)"><span class="l-icon l-icon-check"></span>全选</a>
			</div>
			<div id="search-result" class="choose-item-div"></div>
		</div>
		<div tabid="treeview" title="机构树">
    		<ul id="orgTree" class="ztree"></ul>
		</div>
		<div tabid="org-groups" title="自建分组">
			<div id="org-groups-ctr" class="choose-item-div"></div>
			<div class="new-group-wrap">
				<a class="l-button3 has-icon" onclick="addGroup()"><span class="l-icon l-icon-add"></span>新分组</a>
			</div>
		</div>
	</div>
	<div position="right">
		<div class="choosed-list-header l-tab-links">
			<span class="title">已选择(<span id="choosed-num">0</span>)个</span>
			<span class="l-button" onclick="clearChoosed(true)">全部移除</span>
			<span class="l-button" onclick="clearChoosed(false)">移除</span>
		</div>
		<div id="choosed-list"></div>
	</div>
</div>
</body>
</html>