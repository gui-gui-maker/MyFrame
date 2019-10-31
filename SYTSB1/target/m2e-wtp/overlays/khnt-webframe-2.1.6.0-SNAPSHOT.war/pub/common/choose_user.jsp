<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>选择机构、人员</title>
<%@include file="/k/kui-base-form.jsp"%>
<link rel="stylesheet" type="text/css" href="pub/common/css/common.css">
<script type="text/javascript" src="pub/common/js/user_choose.js"></script>
<script type="text/javascript">
var _multiple = ${param.multiple},chooseRange = "${param.range}";
$(function(){
	var unitId,unitName,levelCode;
	if(chooseRange=="1"||chooseRange=="all"<sec:authorize access="hasRole('sys_administrate')"> || true</sec:authorize>){//全系统范围
		unitId = "";unitName="";
	} else if(chooseRange=="11" || chooseRange=="dep"){//本部门内
		unitId = "<sec:authentication property="principal.department.id" />";
		unitName = "<sec:authentication property="principal.department.orgName" htmlEscape="false" />";
		levelCode = "<sec:authentication property="principal.department.levelCode" />"
	} else{//本单位内
		unitId = "<sec:authentication property="principal.unit.id" />";
		unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false" />";
		levelCode = "<sec:authentication property="principal.unit.levelCode" />"
	}
	$("#layout").ligerLayout({
		rightWidth: 225,
		space:0
	});
	$("#tabs").addClass("navtab").ligerTab({
		onAfterSelectTabItem: function(tabid){
			if(tabid=='group' && $("#org-groups-ctr").children().length==0){
				loadUserGroups(chooseRange=="1"||chooseRange=="all"?"":chooseRange);
			}
		}
	});
	
	$("#olayout").ligerLayout({
		leftWidth: 220,
		space:0
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
	
	$.getJSON("rbac/org/getSubordinate.do?orgid=" + unitId,function(dataList){
		if(unitId=="") {
			createOrgTree(dataList);
			$("#person_frame").attr("src","pub/common/choose_user_list.jsp?levelCode=&multiple=${param.multiple}");
		}
		else{
			createOrgTree([{
				id : unitId,
				text : unitName,
				level : "0",
				levelCode: levelCode,
				children : dataList
			}]);
			$("#person_frame").attr("src","pub/common/choose_user_list.jsp?levelCode="+levelCode+"&multiple=${param.multiple}");
		}
	});
	
	$("#olayout .l-layout-left").width($("#olayout .l-layout-left").width()-2);
	$("#olayout .l-layout-center").width($("#olayout .l-layout-center").width()-2);
	$("#choosed-list").height($("#layout").height()-40);
});

var ztree;
var zNodes;
var setting = {
	data: {
		key: {
			name: "text"
		}
	},
	async: {
		enable: true,
		url: "rbac/org/getSubordinate.do",
		autoParam: ["id=orgid"]
	},
	callback: {
		onClick: function(event, treeId, treeNode){
			var win = $("#person_frame").get(0).contentWindow.window;
			if(win.loadGridData) win.loadGridData(treeNode.levelCode);
		},
		onNodeCreated:function(event, treeId, treeNode){
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			if(treeNode["level"]==0)
				treeNode.icon = "k/kui/images/icons/16/home.png";
        	else if(treeNode["level"]==1)
        		treeNode.icon = "k/kui/images/icons/16/org.png";
        	else
        		treeNode.icon = "k/kui/images/icons/16/group.png";
			treeObj.updateNode(treeNode);
		}
	}
};

function createOrgTree(datas){
	var strData = JSON.stringify(datas);
	strData = strData.replace(/isexpand/g, "open");
	zNodes = eval(strData);
	zNodes[0].open=true;
	ztree = $.fn.zTree.init($("#orgTree"), setting, zNodes);
}
</script>
</head>
<body class="p5">
    <div id="layout">
    	<div id="tabs" position="center">
        	<div title="组织机构" id="olayout">
        		<div position="left" title="组织机构" style="overflow: auto;">
	            	<ul id="orgTree" class="ztree"></ul>
		        </div>
		        <div position="center">
		            <iframe style="height: 100%; width: 100%" frameborder="0" scrolling="no" id="person_frame" name="person_frame" src=""></iframe>
		        </div>
        	</div>
        	<div title="自建分组" tabid="group">
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
