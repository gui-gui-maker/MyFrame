<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>

<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.khnt.security.util.SecurityUtil"%>

<head pageStatus="${param.status}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<%
SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
String nowTime=""; 
nowTime = dateFormat.format(new Date());
%>
<script type="text/javascript">
	var tbar="";
	$(function () {
		tbar=[
		      { text: '保存', id: 'up', icon: 'save', click: directChange},
	          { 
		    	  text: '关闭', 
		    	  id: 'close', 
		    	  icon:'cancel', 
		    	  click:function(){
	        	  	api.close();
	        	  }
		      }
		      ];
		$("#form1").initForm({
	        showToolbar: true,
	        toolbarPosition: "bottom",
	        toolbar: tbar,
	        getSuccess:function(res){
	        	console.log(res);
	        }
		});
	});
	function directChange(){ 
		var obj=$("#form1").validate().form();
		var start = $("#startNumber").val();
		var end = $("#endNumber").val();
		if(end - start <= 0){
			$.ligerDialog.error('提示：结束编号必须大于开始编号');
			return;
		}
		if(obj){
			var formData = $("#form1").getValues();
	        $("body").mask("正在保存......");
	        $.post("archives/box/saveManul.do",
	        		formData,
	        		function (data, stats) {
		                $("body").unmask();
		                if (data["success"]) {
		                    top.$.dialog.notice({content:'保存成功！'});
		                    if(api.data.window.getData){
			                    api.data.window.getData();
		                    }else{
		                    	api.data.window.Qm.refreshGrid();
		                    }
		                    api.close();
		                }else{
		                    $.ligerDialog.error('提示：' + data.msg);
		                }
	        });
		 }else{
             return;
		 }
	 }
</script>

</head>
<body>
	<form name="form1" id="form1" method="post" action="archives/box/saveManul.do"
		getAction="archives/box/detail.do?id=${param.id}">
		<input type="hidden" id="id" name="id" value="${param.id}"/>
	    <input type="hidden" name="registrant"/> <!-- 创建人 -->
	    <input type="hidden" name="registrantTime"/> <!-- 创建人时间 -->
	    <input type="hidden" name="registrantId"/> <!-- 创建人id -->
	    <input type="hidden" name="reportNumber"/> <!-- 档案编号 -->
	    <input type="hidden" name="reportNumberId"/> <!-- 档案id -->
	    <input type="hidden" name="reportNumber2"/> <!-- 缺少的档案编号 -->
	    <input type="hidden" name="reportNumberId2"/> <!-- 缺少的档案id -->
	    <input type="hidden" name="reportNum"/> <!-- 档案数量 -->
	    <input type="hidden" name="managerId"/> <!-- 管理人id -->
	    <input type="hidden" name="managerName"/> <!-- 管理人姓名 -->
	    <input type="hidden" name="status"/> <!-- 档案盒状态 -->
	    <input type="hidden" name="identifier"/> <!-- 档案盒档案开始编号 -->
	    <input type="hidden" name="identifier2"/> <!-- 档案盒档案结束编号 -->
		<table border="1" cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">档案盒编号</td>
				<td class="l-t-td-right">
					<input id="archivesBoxId" name="archivesBoxId" type="text" ltype="text" validate="{required:true}"  />
				</td>
				<td class="l-t-td-left">档案编号前缀</td>
				<td class="l-t-td-right">
					<input id="prefix" name="prefix" type="text" ltype="text" validate="{required:true}"  />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">年份</td>
				<td class="l-t-td-right">
					<input id="years" name="years" type="text" ltype="number" validate="{required:true,minlength:4,maxlength:4}"  />
				</td>
				<td class="l-t-td-left">开始编号</td>
				<td class="l-t-td-right">
					<input id="startNumber" name="startNumber" type="text" ltype='number' validate="{required:true,maxlength:5}"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">结束编号</td>
				<td class="l-t-td-right">
					<input id="endNumber" name="endNumber" type="text" ltype='number' validate="{required:true,maxlength:5}" />
				</td>
			</tr>
		</table>
	        
	</form>
</body>
</html>

