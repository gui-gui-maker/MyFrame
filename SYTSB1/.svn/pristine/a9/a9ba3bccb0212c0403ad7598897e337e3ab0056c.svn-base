<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<%
	DateFormat ds = new SimpleDateFormat("yyyyMMddHHmmss");
	String nowNum = ds.format(new Date());
	String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String area_code = ((Org)user.getDepartment()).getAreaCode();
	String unit_code = ((Org)user.getDepartment()).getOrgCode();
%>
<script type="text/javascript">
	var p;
	var status = "${param.status}";
	$(function() {
		
	
		/* $("#baseForm").initForm({
				toolbar : bar,
				success : function(response) {//处理成功
					if (response.success) {
	
						//保存基本信息（主表）后，id未自动赋值，故此处手动赋值
						top.$.notice("操作成功！", 3);
						api.close();
					} else {
						$.ligerDialog.error('保存失败！');
					}
				},
				//取得图片
				getSuccess : function(res) {
					var fileList = res.fileList;
					for (var i = 0; i < fileList.length; i++) {
						showSignPicture(fileList[i]);
					}
					
				}
			}); */
	
	}) 
	
	
function save(){
		$("#baseForm").submit();
	}
	
function init(){
	 var step = "${param.step}";
		if(step=="1"){
			$("#step1").ligerGetComboBoxManager().setData(
					[{id:'0',text:'起草'}]);
		}else if(step=="2"){
			$("#step1").ligerGetComboBoxManager().setData(
					[{id:'0',text:'起草'},{id:'1',text:'审批中'}]);
		}else if(step=="3"){
			$("#step1").ligerGetRadioGroupManager().setData(
					[{id:'0',text:'起草'},{id:'1',text:'审批中'},{id:'2',text:'审批完成'}]);
		}
}
</script>
</head>
<body >
	<form name="baseForm" id="baseForm" method="post" action="contractInfoAction/backStep.do?ids=${param.ids}" >
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			 <tr>
				<td class="l-t-td-left">退回环节：</td>
				<td  class="l-t-td-right">
					<c:if test="${param.step=='1' }">
						 <input id="step1" name="step" ltype="radioGroup" type="radio"
							validate="{required:true}"
							ligerui="{
							readonly:true,
							data: [{id:'0',text:'起草'}] }" />
					</c:if>
					<c:if test="${param.step=='2' }">
						 <input id="step1" name="step" ltype="radioGroup" type="radio"
							validate="{required:true}"
							ligerui="{
							readonly:true,
							data: [{id:'0',text:'起草'},{id:'1',text:'审批中'}] }" />
					</c:if>
					<c:if test="${param.step=='3' }">
						 <input id="step1" name="step" ltype="radioGroup" type="radio"
							validate="{required:true}"
							ligerui="{
							readonly:true,
							data: [{id:'0',text:'起草'},{id:'1',text:'审批中'},{id:'2',text:'审批完成'}] }" />
					</c:if>
				</td>
			</tr> 
			
		</table>
	</form>
</body>
</html>