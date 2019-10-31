<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<title>选择机构</title>
<script type="text/javascript">
var orgTree = null;
$(function(){
	var unitId = "<sec:authentication property="principal.unit.id" />";
	var unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false" />";
	orgTree = $("#orgTree").ligerTree({
		checkbox: "${param.checkbox}"=="1"?true:false,
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
		data : [],
		onSelect : function(node) {
			if(!this.hasChildren(node.data)&&node.data.level==1){
				this.loadData(node.data,"rbac/org/getSubordinate.do?orgid=" + node.data.id);
			}
		},
        onBeforeExpand: function(node){
            if (node.data.children && node.data.children.length == 0)
			    this.loadData(node.data,"rbac/org/getSubordinate.do?orgid=" + node.data.id);
        }
	});
	$.getJSON("rbac/org/getSubordinate.do?orgid=" + unitId,function(dataList){
		orgTree.append(unitId,[{
			id : unitId,
			text : unitName,
			level : "0",
			children : dataList
		}]);
	});
});
function getSelectResult(){
	if(orgTree == null){
		$.ligerDialog.error("机构树未生成!");
		return;
	}
	var checkBox = "${param.checkbox}";
	var nodes = (checkBox=="1"?orgTree.getChecked():orgTree.getSelected());
	if(!nodes){
		top.$.dialog.alert("您没有选择任何节点！");
		return null;
	}
	var result = {code:"",name:""};
	if(checkBox=="1"){
		$.each(nodes,function(i,el){
			result.code += (i==0?"":",") + el.data.id;
			result.name += (i==0?"":",") + el.data.text;
		});
	}
	else{
		result.code = nodes.data.id;
		result.name = nodes.data.text;
	}
	return result;
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
<body style="overflow:auto">
	<ul id="orgTree"></ul>
</body>
</html>
