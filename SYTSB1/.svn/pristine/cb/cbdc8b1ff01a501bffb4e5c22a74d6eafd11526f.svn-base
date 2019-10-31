<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus = "${param.pageStatus}">
<title>工龄及可休假天数设置</title>
<%@include file="/k/kui-base-form.jsp"%>
<link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript">
$(function () {
	tbar=[{text: "保存", icon: "save", click: function(){
    	if ($("#formObj").validate().form()) {
    		$("#formObj").submit();
    	}else{
    		$.ligerDialog.error('提示：' + '请填写完整后保存！');
    	}}},
		{text: "关闭", icon: "cancel", click: function(){api.close();}}];
	$("#formObj").initForm({
		success: function (response) {//处理成功
    		if (response.success) {
            	top.$.dialog.notice({
             		content: "保存成功！"
            	});
            	api.close();
    		} else {
           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
      		}
		}, getSuccess: function (response){
		},
		showToolbar: true,
        toolbarPosition: "bottom",
        toolbar: tbar
	});
})
function resChange(val){
	var extraDays=$("#extraDays").val();
	var workAge;
	var date = new Date(val.replace(/-/g,"/"));
	var year=date.getFullYear();//计算工龄时间年份
	var moth=date.getMonth()+1;//计算工龄时间月份
	var nowDate = new Date();
	var nowYear=nowDate.getFullYear();//当前时间年份
	var nowMoth=nowDate.getMonth()+1;//当前时间月份
	if(year==nowYear&&moth<=nowMoth){
		workAge="0";
	}else if(year<nowYear){
		if(moth<=nowMoth){
			workAge=nowYear-year;
		}else{
			workAge=nowYear-year-1;
		}
	}else{
		$.ligerDialog.error("日期选择不正确，请核对！");
		return false;
	}
	if(workAge>=0&&workAge<1){
		$("#leaveDays").val("0");
	}else if(workAge>=1&&workAge<10){
		$("#leaveDays").val("5");
	}else if(workAge>=10&&workAge<20){
		$("#leaveDays").val("10");
	}else if(workAge>=20){
		$("#leaveDays").val("15");
	}
	if(extraDays!=null&&extraDays!=""&&extraDays!="undefined"){
		var leaveDays=$("#leaveDays").val();
		$("#totalDays").val(parseInt(extraDays)+parseInt(leaveDays));
	}
 }
function extraChange(val){
	var leaveDays=$("#leaveDays").val();
	if(val!=null&&val!=""&&val!="undefined"){
		if(leaveDays!=null&&leaveDays!=""&&leaveDays!="undefined"){
			$("#totalDays").val(parseInt(val)+parseInt(leaveDays));
		}else{
			$.ligerDialog.error('年假天数为空，请确认数据是否填写完整！');
		}
	}else{
		$.ligerDialog.error('额外天数不能为空！');
	}
 }
</script>
</head>
<body>
	<form name="formObj" id="formObj" action="employeeBaseAction/saveDateAndDays.do"
	getAction="employeeBaseAction/detail.do?id=${param.id}">
		<input type="hidden" id="id" name="id" value="${param.id}"/>
		<table class="l-detail-table">
			<tr>
				<!-- 计算工龄时间 -->
				<td class="l-t-td-left">计算工龄时间</td>
				<td class="l-t-td-right">
				<input type="text" name="seniorityDate" id="seniorityDate" ltype="date" validate="{required:true}" ligerui="onChange:resChange"/>
				</td>
			</tr>
	  		<tr>
	            <td class="l-t-td-left">年假天数</td>
	           	<td class="l-t-td-right">
	           	<input name="leaveDays" id="leaveDays" type="text" ltype='text' validate="{required:true}" />
	        	</td>
	      	</tr>
	      	<tr>
	            <td class="l-t-td-left">额外天数</td>
	           	<td class="l-t-td-right">
	           	<input name="extraDays" id="extraDays" type="text" ltype='text' validate="{required:true}" value="0" ligerui="onChange:extraChange"/>
	        	</td>
	      	</tr>
	      	<tr>
	            <td class="l-t-td-left">可休年假天数</td>
	           	<td class="l-t-td-right">
	           	<input name="totalDays" id="totalDays" type="text" ltype='text' validate="{required:true}" />
	        	</td>
	      	</tr>
	</table>
  </form>
</body>
</html>