<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<title>选择人员</title>
<script type="text/javascript">
$(function(){
	var unitId = "<sec:authentication property="principal.unit.id" />";
	var unitName = "<sec:authentication property="principal.unit.orgName" />";
	$("#layout").ligerLayout({
		leftWidth : 300,
		rightWidth: 218
	});
	//setSelVal();
});

//添加选择人
function addUserItem(id,tel,name,type){
	if(type=="0") {
		$("#jld_list").empty();
	}
	if(type=="1"){
		if($("#"+id).size() > 0) return;
	}else{
		//左边树的id也是和已选人员id一样且都是li标签，故长度要大于1
		if($("#"+id).size() > 1) return;
	}
	//
	$("#jld_list").append("<option id='"+id+"' value='" + tel + "'>" +name +"</option");
}

//移除已选人员
function removeUserItem(code){
	$("#jld_list option").each(function(){
		if($(this).text()==code)
			$(this).remove();
	});
}

//选择结果
function getSelectResult(){
	var result = {id:"",tel:"",name:""};
	var options = $("#jld_list option");
	var i=0;
	$.each(options,function(){
		result.id+=(i==0?"":",")+$(this).attr("id");
		result.tel += (i==0?"":",") + $(this).val();
		result.name += (i==0?"":",")+ $(this).text();
		i=i+1;
	});
	return result;
}
//双击以选择项目移除
function removeItem(sel){
	$(sel).find("option:selected").remove();
}
/**
 * 将选择的信息设置在已选列表中
 */
function setSelVal(){
	var idVal = "${param.id}";
	var telVal= "${param.tel}";
	var nameVal="${param.name}";
	if(idVal!=""&&idVal!="undefined"){
		for(var i=0;i< idVal.split(',').length;i++){
			$("#jld_list").append("<option id='"+idVal.split(',')[i]+"' value='"+telVal.split(',')[i]+"'>"+nameVal.split(',')[i]+"</option");
		}
	}
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
<body>
	<div id="layout">
		<div position="center" title="待选择人员">
			<iframe style="height:100%;width:100%" frameborder="0" scrolling="no" id="person_frame" 
				src="app/oa/select/jld_list.jsp?orgId=004600" />&checkbox=${param.checkbox}"></iframe>
		</div>
		<div position="right" title="已选人员">
			<select id="jld_list" multiple="multiple" 
				style="border:none;width:218px;height:100%;font-size:14px;padding: 2px;"></select>
		</div>
	</div>
</body>
</html>
