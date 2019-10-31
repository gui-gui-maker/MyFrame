<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<title>选择人员</title>
<script type="text/javascript">
$(function(){
	var unitId = "${param.type}"=="44"?"<sec:authentication property="principal.department.id" />":"<sec:authentication property="principal.unit.id" />";
	var unitName = "${param.type}"=="44"?"<sec:authentication property="principal.department.orgName" htmlEscape="false"/>":"<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
	<sec:authorize access="hasRole('sys_administrate')">
		var unitId = "<sec:authentication property="principal.unit.id" htmlEscape="false"/>";
		var unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
	</sec:authorize>
	$("#layout").ligerLayout({
		leftWidth : 300,
		rightWidth: 190
	});
	var orgTreeManager = $("#orgTree").ligerTree({
		checkbox : false,
		data : [],
		selectCancel : false,
        iconFieldName : "level",
        iconParse : function(data){
        	if(data["level"]==0)
           		return "k/kui/images/icons/16/home.png";
        	else if(data["level"]==1)
              	return "k/kui/images/icons/16/org.png";
        	else
            	return "k/kui/images/icons/16/group.png";
        },
		onSelect : function(node){
			var win = $("#person_frame").get(0).contentWindow.window;
			if(win.loadGridData) win.loadGridData(node.data.id);
		},
        onBeforeExpand: function(node){
            if (node.data.children && node.data.children.length == 0)
			    this.loadData(node.data,"rbac/org/getSubordinate.do?orgid=" + node.data.id);
        }
	});
	
	$.getJSON("rbac/org/getSubordinate.do?orgid=" + unitId,function(dataList){
		orgTreeManager.append(unitId,[{
			id : unitId,
			text : unitName,
			level : "0",
			children : dataList
		}]);
	});
	
	$("#person_frame").attr("src","pub/selectUser/position_list.jsp?orgId="+unitId+"&checkbox=${param.checkbox}&position=${param.position}");
});
//添加选择人
function addUserItem(code,name,type){
	if(type=="0") {
		$("#person_list").empty();
	}
	if($("#person_list option[value='"+code+"']").size() > 0) return;
	$("#person_list").append("<option value='" + code + "'>" +name +"</option");
}
//移除已选人员
function removeUserItem(code){
	$("#person_list option").each(function(){
		if($(this).val()==code)
			$(this).remove();
	});
}
//选择结果
function getSelectResult(){
	var result = {code:"",name:""};
	var options = $("#person_list option");
	$.each(options,function(){
		result.code += ("," + $(this).val());
		result.name += ("," + $(this).text());
	});
	result.code = result.code.replace(',',"");
	result.name = result.name.replace(',',"");
	return result;
}

//双击以选择项目移除
function removeItem(sel){
	$(sel).find("option:selected").remove();
}

//从已选择角色中删除或者添加
function addOrRemoveUser(isAdd, user) {
	if("${param.checkbox}"==true){
		if (isAdd && $("#person_list option[value='" + user.id + "']").size() == 0)
			$("#person_list").append("<option value='" + user.id +  "'>" + user.name + "</option>");
		else
			$("#person_list option[value='" + user.id + "']").remove();
	}
	else{
		$("#person_list").empty().append("<option value='" + user.id +  "'>" + user.name + "</option>");
	}
}


//从已选择岗位中删除或者添加
function addOrRemovePosition(isAdd, user) {
	if("1"==true){
		if (isAdd && $("#person_list option[value='" + user.id + "']").size() == 0)
			$("#person_list").append("<option value='" + user.id +  "'>" + user.position_name + "</option>");
		else
			$("#person_list option[value='" + user.id + "']").remove();
	}
	else{
		$("#person_list").empty().append("<option value='" + user.id +  "'>" + user.position_name + "</option>");
	}
}
//双击select列表，移除被点击的角色项
function removeAllUser() {
	$("#person_list option").each(function() {
		$(this).remove();
	});
}
//双击select列表，移除被点击的角色项
function removeUser() {
	var idRange = [];
	$("#person_list option").each(function() {
		if (this.selected == true)
			$(this).remove();
		else
			idRange.push(this.value);
	});
	person_frame.Qm.getQmgrid().selectRange("id", idRange);
}

//将已选择的角色转换为ID数组，供左边表格动态设置使用
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
		<div position="left" title="组织机构" style="overflow:auto;">
			<ul id="orgTree"></ul>
		</div>
		<div position="center">
			<iframe style="height:100%;width:100%" frameborder="0" scrolling="no" id="person_frame" name="person_frame" 
				src=""></iframe>
		</div>
		<c:if test="${param.position==1}"><div position="right" title="已选择岗位"></c:if>
		<c:if test="${param.position!=1}"><div position="right" title="已选择人员"></c:if>
			<select id="person_list" multiple="multiple" ondblclick="removeUser(this)" title="双击移除"
				style="border:none;width:100%;height:100%;font-size:14px;padding:5px;"></select>
		</div>
	</div>
</body>
</html>
