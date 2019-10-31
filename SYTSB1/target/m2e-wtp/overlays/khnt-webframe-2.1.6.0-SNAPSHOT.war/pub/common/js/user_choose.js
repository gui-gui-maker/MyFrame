/**
 * 获取选择结果
 */
function chooseResult(){
	var choosed = $("#choosed-list a");
	var result = {code:"",name:"",data:[]};
	if(choosed.length == 0){
		//$.ligerDialog.alert("您没有选择人员！");
		return null;
	}
	choosed.each(function(i){
		var $this = $(this);
		result.code += (i==0?"":",") + $this.attr("data-value");
		result.name += (i==0?"":",") + $this.text();
		result.data.push({id: $this.attr("data-value"),name: $this.text()});
	});
	return result;
}

function addChoosedItem(id,name){
	if(!_multiple){
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
			removeChoosedItem(id);
		});
	}
	refreshChoosedNum();
	//$("#search-item-" + id).addClass("checked");
	$("#group-item-" + id).addClass("checked");
}

function removeChoosedItem(id){
	$("#choosed-list-item-" + id).remove();
	refreshChoosedNum();
	//$("#search-item-" + id).removeClass("checked");
	$("#group-item-" + id).removeClass("checked");
	$("#group-item-" + id).siblings(".org-group-head").find(":checkbox").attr("checked",false);
	frames['person_frame'].initGridSelectRange();
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
	$.post("rbac/user/search.do",{name:searchName},function(resp){
		if(resp.success){
			$("#search-result").empty();
			for(var i in resp.data){
				var checked = $("#choosed-list-item-" + resp.data[i].id).length > 0;
				$("#search-result").append("<a class='search-result-item" + (checked?" checked":"") + 
						"' id='search-item-" + resp.data[i].id + "' data-value='" + resp.data[i].id + 
						"' title='" + resp.data[i].unit + "'>" + resp.data[i].name + "</a>");
			}
			$("#search-result a").click(function(){
				if($(this).hasClass("checked")){
					removeChoosedItem($(this).attr("data-value"));
					refreshChoosedNum();
				}else{
					addChoosedItem($(this).attr("data-value"),$(this).text());
				}
			});
		}else{
			top.$.notice("搜索失败，请稍候重试！",4,"k/kui/images/icons/dialog/32X32/hits.png");
		}
		$("body").unmask();
	},"json");
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
		content: "url:pub/common/user_group_detail.jsp?pageStatus=add&rangeType=" + (chooseRange=="1"||chooseRange=="all"?"":chooseRange),
		data:{
			callback: function(group){
				top.$.notice("添加分组成功！",3);
				showUserGroup([group]);
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
		content: "url:pub/common/user_group_detail.jsp?pageStatus=edit&id=" + gid,
		data:{
			callback: function(group){
				top.$.notice("修改分组成功！",3);
				var chxbox = $("#g-" + gid + " :checkbox");
				var checked = chxbox.attr("checked")=="checked";
				if(checked) chxbox.trigger("click");
				showUserGroup([group]);
				if(checked) chxbox.trigger("click");
			}
		}
	});
}

function delGroup(gid){
	var chxbox = $("#g-" + gid + " :checkbox");
	$.ligerDialog.confirm("删除后无法恢复，确定删除？",function(rst){
		if(!rst) return;
		$("body").mask("正在处理，请稍候...");
		$.post("rbac/user/group/delete.do",{ids: gid},function(resp){
			if(resp.success){
				top.$.notice("删除成功！",3);
				chxbox.trigger("click");
				$("#g-" + gid).remove();
			}else{
				$.ligerDialog.error("删除失败！<br/>请稍后重试或者联系技术支持人员。");
			}
			$("body").unmask();
		},"json");
	});
}

function loadUserGroups (range){
	$("body").mask("正在加载分组信息...");
	$.getJSON("rbac/user/group/get_user_groups.do?rangeType=" + range,function(resp){
		if(resp.success){
			showUserGroup(resp.data);
		}else{
			$.ligerDialog.error("获取自建分组失败！");
		}
		$("body").unmask();
	});
}

//分组选择，分组选择的结果id中，添加了前缀“group_”，已表区别
function checkGroup(checked,gid){
	$("#g-" + gid + " .search-result-item").each(function(){
		if(checked){
			addChoosedItem($(this).attr("data-value"),$(this).text());
		}
		else{
			removeChoosedItem($(this).attr("data-value"));
		}
	});
}

function closeOrExpandGroup(obj){
	var $g = $(obj).parent().parent();
	if($g.hasClass("expand")){
		$g.removeClass("expand").addClass("closed").find(".search-result-item").show();
	}else{
		$g.removeClass("closed").addClass("expand").find(".search-result-item").hide();
	}
}

function showUserGroup(groups){
	$.each(groups,function(){
		var $group = $("<div class='org-group expand' id='g-" + this.id + "' data-value='" + this.id 
				+ "'><div class='org-group-head'><span class='group-name' onclick='closeOrExpandGroup(this)'><i></i>" + this.groupName + "</span>"
				+ (_multiple?"<input type='checkbox' name='org-grount-chkbox' title='全选' onchange='checkGroup(this.checked,\"" + this.id + "\")'/>":"") 
				+ "<a class='edit-btn l-button3 has-icon' onclick='editGroup(\"" + this.id + "\")'><span class='l-icon l-icon-add'></span>修改</a>" 
				+ "<a class='edit-btn l-button3 has-icon' onclick='delGroup(\"" + this.id + "\")'><span class='l-icon l-icon-del'></span>删除</a></div></div>");
		if(this.userId && this.userName){
			var idarr = this.userId.split(',');
			var namearr = this.userName.split('，');
			for(var i in idarr){
				var checked = $("#choosed-list-item-" + idarr[i]).length > 0;
				$group.append("<a class='search-result-item" + (checked?" checked":"") + 
						"' id='group-item-" + idarr[i] + "' data-value='" + idarr[i] + "'>" + namearr[i] + "</a>");
			}
		}
		if($("#g-" + this.id).length > 0){
			$("#g-" + this.id).removeAttr("id").after($group).remove();
			//$group.find(":checkbox").attr("checked",true);
		}else{
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