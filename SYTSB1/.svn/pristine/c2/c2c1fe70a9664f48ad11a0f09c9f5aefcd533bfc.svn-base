<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-form.jsp"%>
<title>选择车辆</title>
<script type="text/javascript">
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
			$("#carInfo_frame").attr("src","app/oa/car/car_type_list.jsp?orgId=&checkbox=${param.checkbox}&status=${param.status}&op_type=${param.op_type}");
		}
		else{
			createOrgTree([{
				id : unitId,
				text : unitName,
				level : "0",
				children : dataList
			}]);
			$("#carInfo_frame").attr("src","app/oa/car/car_type_list.jsp?orgId="+unitId+"&checkbox=${param.checkbox}&status=${param.status}&op_type=${param.op_type}");
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
			var win = $("#carInfo_frame").get(0).contentWindow.window;
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
	var result = {code:"",name:"",uname:"",jsonObj:{carInfo:[],org:[]}};
	var options = $("#carInfo_list option");
	$.each(options,function(){
		var $this = $(this);
		result.code += ("," + $this.val());
		result.name += ("," + $this.html());
		//result.uname +=("," + $this.attr("uname"));
		if($this.attr("stype")=="carInfo"){
			result.jsonObj.carInfo.push({id:$this.val(),car_num:$this.attr("car_num"),manager_room_name:$this.attr("manager_room_name"),car_brand:$this.attr("car_brand"),
				engine_no:$this.attr("engine_no"),frame_no:$this.attr("frame_no"),car_mileage:$this.attr("car_mileage"),load_number:$this.attr("load_number")});
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
		$("#carInfo_list option[value='" + orgNode.id + "']").remove();
	}else{
		if("${param.checkbox}" == "0"){
			$("#carInfo_list").empty();
			removeUser();
		}
		$("#carInfo_list").append("<option value='" + orgNode.id + "' name='" + orgNode.text +  "' stype='org'>机构-" + orgNode.text + "</option>");
	}
}
 
//从已选择角色中删除或者添加
function addOrRemoveUser(isAdd, carInfo) {
	if(!isAdd){
		$("#carInfo_list option[value='" + carInfo.id + "']").remove();
	}else{
		//右边单选
		$("#carInfo_list option").each(function (){
			$(this).remove();
		});
		
		var obj=$("#carInfo_list option[value='" + carInfo.id + "']");
		if(!(obj&&obj[0])){
			$("#carInfo_list").append("<option value='" + carInfo.id +  "' car_num='" + carInfo.car_num + "' manager_room_name='" + carInfo.manager_room_name + 
					"'car_brand='" + carInfo.car_brand + "' engine_no='" + carInfo.engine_no + "' frame_no='" + carInfo.frame_no + "' car_mileage='" + carInfo.car_mileage +
					"' load_number='" + carInfo.load_number +
					"'stype='carInfo'>" + carInfo.car_num + "</option>");
		}
		if("1" == "0"){
			$("#carInfo_list").empty();
			var cns = ztree.getCheckedNodes(true);
			if(cns.length > 0) ztree.updateNode(cns[0],false);
		}
		
	}
}
/* function winTip(classId){
	top.$.dialog({
		width:$(top).width(),
		height:$(top).height(),
		lock: true,
		parent: api,
		data: {
			window: window
		},
		title: "详情",
		content: 'url:pxzx/training/classInfo_detail.jsp?id=' + classId + '&pageStatus=detail'
	});
} */

function removeSelected(){
	$("#carInfo_list option:selected").each(function(){
		if($(this).attr("stype")=="carInfo") removeUser();
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
	$("#carInfo_list option:selected").remove();
	var idRange = getSelectUserArr();
	carInfo_frame.Qm.getQmgrid().selectRange("id", idRange);
}

//将已选择的人员转换为ID数组，供中间表格动态设置使用
function getSelectUserArr() {
    var idRange = [];
    $("#carInfo_list option").each(function() {
        idRange.push(this.value);
    });
    return idRange;
}
function getUserRoleStr() {
	var idRange = "";
	$("#carInfo_list option").each(function() {
		idRange += "," + this.value;
	});
	return idRange ? idRange.substring(1) : "";
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
        <div position="center">
            <iframe style="height: 100%; width: 100%" frameborder="0" scrolling="no" id="carInfo_frame"
                name="carInfo_frame" src=""></iframe>
        </div>
        <div position="right" title="已选择">
            <select id="carInfo_list" multiple="multiple" ondblclick="removeSelected(this)" title="双击移除"
                style="border: none; width: 100%; height: 100%; font-size: 14px; padding: 5px;"> 
			</select>
        </div>
    </div>
</body>
</html>
