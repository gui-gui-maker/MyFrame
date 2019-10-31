<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-form.jsp"%>
<title>选择机构、人员</title>
<script type="text/javascript">
var orgTreeManager;
$(function(){
	var unitId,unitName;
	if("${param.type}"=="1" || "${param.type}"=="3"<sec:authorize access="hasRole('sys_administrate')"> || true</sec:authorize>){//全系统范围
		unitId = "";unitName="";
	} else if("${param.type}"=="33" || "${param.type}"=="11"){//本单位内
		unitId = "<sec:authentication property="principal.department.id" />";
		unitName = "<sec:authentication property="principal.department.orgName" htmlEscape="false" />";
	} else{//本部门内
		unitId = "<sec:authentication property="principal.unit.id" />";
		unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false" />";
	}
	$("#layout").ligerLayout({
		leftWidth: 250,
		rightWidth: 250
	});
	
	$.getJSON("rbac/org/getSubordinate.do?orgid=" + unitId,function(dataList){
		if(unitId=="") {
			createOrgTree(dataList);
			$("#person_frame").attr("src","app/fwxm/scientific/instruction/selectUser/user_list.jsp?orgId=&checkbox=${param.checkbox}");
		}
		else{
			alert(1);
			createOrgTree([{
				id : unitId,
				text : unitName,
				level : "0",
				children : dataList
			}]);
			$("#person_frame").attr("src","app/fwxm/scientific/instruction/selectUserr/user_list.jsp?orgId="+unitId+"&checkbox=${param.checkbox}");
		} 
	});
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
	},<c:if test="${param.chooseOrg==1}">
	check: {
		enable: true,
		chkStyle: "${param.checkbox=='0'?'radio':'checkbox'}",
		chkboxType:{"Y":"","N":""},
		radioType: "all"
	},</c:if>
	callback: {
		onClick: function(event, treeId, treeNode){
			var win = $("#person_frame").get(0).contentWindow.window;
			if(win.loadGridData) win.loadGridData(treeNode.id);
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
		}<c:if test="${param.chooseOrg==1}">,
		onCheck: function(event, treeId, treeNode){
			addOrRemoveOrg(treeNode);
		}</c:if>
	}
};

function createOrgTree(datas){
	var strData = JSON.stringify(datas);
	strData = strData.replace(/isexpand/g, "open");
	zNodes = eval(strData);
	ztree = $.fn.zTree.init($("#orgTree"), setting, zNodes);
}

//选择结果
function getSelectResult(){
	var result = {code:"",name:"",jsonObj:{person:[],org:[]}};
	var options = $("#person_list option");
	$.each(options,function(){
		var $this = $(this);
		result.code += ("," + $this.val());
		result.name += ("," + $this.attr("name"));
		if($this.attr("stype")=="user"){
			result.jsonObj.person.push({id:$this.val(),name:$this.attr("name"),deptId:$this.attr("deptid"),deptName:$this.attr("deptname")});
		}else{
			result.jsonObj.org.push({id:$this.val(),name:$this.attr("name")});
		}
	});
	result.code = result.code.replace(',',"");
	result.name = result.name.replace(',',"");
	
	return result;
}

function addOrRemoveOrg(orgNode){
	if(!orgNode.checked){
		$("#person_list option[value='" + orgNode.id + "']").remove();
	}else{
		if("${param.checkbox}" == "0"){
			$("#person_list").empty();
			removeUser();
		}
		$("#person_list").append("<option value='" + orgNode.id + "' name='" + orgNode.text +  "' stype='org'>机构-" + orgNode.text + "</option>");
	}
}

//从已选择角色中删除或者添加
function addOrRemoveUser(isAdd, user) {
	if(!isAdd){
		$("#person_list option[value='" + user.id + "']").remove();
	}else{
		if("${param.checkbox}" == "0"){
			$("#person_list").empty();
			var cns = ztree.getCheckedNodes(true);
			if(cns.length > 0) ztree.updateNode(cns[0],false);
		}
		$("#person_list").append("<option value='" + user.id +  "' deptid='" + user.org_id + "' name='" + user.name
				   + "' deptname='" + user.org_name + "' stype='user'><c:if test="${param.chooseOrg==1}">人员-</c:if>" + user.name + "</option>");
	}
}

function removeSelected(){
	$("#person_list option:selected").each(function(){
		if($(this).attr("stype")=="user") removeUser();
		else{
			var rnode = ztree.getNodeByParam("id",$(this).val());
			if("${param.checkbox}" == "0"){
				ztree.updateNode(rnode,false);
			}else{
				ztree.checkNode(rnode,false);
			}
			$(this).remove();
		}
	});
}

//双击select列表，移除被点击的选项
function removeUser() {
	$("#person_list option:selected").remove();
	var idRange = getSelectUserArr();
	person_frame.Qm.getQmgrid().selectRange("id", idRange);
}

//将已选择的人员转换为ID数组，供中间表格动态设置使用
function getSelectUserArr() {
    var idRange = [];
    $("#person_list option").each(function() {
        idRange.push(this.value);
    });
    return idRange;
}
</script>
<style type="text/css">
.l-tree .l-tree-icon-none img {
	height: 16px;
	margin: 3px;
	width: 16px;
}
</style>
</head>
<body class="p5">
    <div id="layout">
        <div position="left" title="组织机构" style="overflow: auto;">
            <ul id="orgTree" class="ztree"></ul>
        </div>
        <div position="center">
            <iframe style="height: 100%; width: 100%" frameborder="0" scrolling="no" id="person_frame"
                name="person_frame" src=""></iframe>
        </div>
        <div position="right" title="已选择">
            <select id="person_list" multiple="multiple" ondblclick="removeSelected(this)" title="双击移除"
                style="border: none; width: 100%; height: 100%; font-size: 14px; padding: 5px;"></select>
        </div>
    </div>
</body>
</html>
