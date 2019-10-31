<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser useres = SecurityUtil.getSecurityUser();
User uu = (User)useres.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
<%
	String status=request.getParameter("status");
	String bxType=request.getParameter("bxType");
%>
<head pageStatus="${param.status}">
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var businessPart="100020,100021,100022,100023,100024,100063,100034,100035,100037,100033,100065,100036,100049,100066,100067,100068"
	$(function() {
		$("#form1").initForm({
			success : function(responseText) {//处理成功
			},
			getSuccess : function(responseText) {
			}, 
			toolbar: [
	      		{
	      			text: "确定", icon: "save", click: function(){
	      				if("<%=bxType%>"=="fybx"){
	      					var dpId=<%=useres.getDepartment().getId() %>
		      				var fybxType = $("input[name='bxType']:checked").val();
		      				if(fybxType=="0"){
		      					//默认url，没有分管领导
		      					var url="app/finance/fybxd_detail.jsp?pageStatus=add&fybxType="+fybxType;
		      					//如果所选单据的部门为业务部门（机电、承压），则修改url为有分管领导的页面
		      					if(businessPart.indexOf(dpId)>-1){
		      					   url="app/finance/fybxd_detail_bs.jsp?pageStatus=add&fybxType="+fybxType;
		      					}
		      				}else if(fybxType=="1"){
		      					//默认url，没有分管领导
		      					var url="app/finance/carfybxd_detail.jsp?pageStatus=add&fybxType="+fybxType;
		      					//如果所选单据的部门为业务部门（机电、承压），则修改url为有分管领导的页面
		      					if(businessPart.indexOf(dpId)>-1){
		      					   url="app/finance/carfybxd_detail_bs.jsp?pageStatus=add&fybxType="+fybxType;
		      					}
		      				}else if(fybxType=="2"){
		      					//默认url，没有分管领导
		      					var url="app/finance/ky_fybxd_detail.jsp?pageStatus=add&fybxType="+fybxType;
		      					//如果所选单据的部门为业务部门（机电、承压），则修改url为有分管领导的页面
		      					if(businessPart.indexOf(dpId)>-1){
		      					   url="app/finance/ky_fybxd_detail_bs.jsp?pageStatus=add&fybxType="+fybxType;
		      					}
		      				}
		      				top.$.dialog({
	  							width: 900,
	  							height: 500,
	  							lock: true,
	  							parent: api,
	  							data: {
	  								window: window
	  							},
	  							title: "新增",
	  							content: 'url:'+url
	  						});
	      				}else if("<%=bxType%>"=="clbx"){
	      					var clbxType = $("input[name='bxType']:checked").val();
	      					var url="app/finance/clfbxd_detail.jsp?pageStatus=add&clbxType="+clbxType;
	      					if(clbxType=="0"){
	      						url="app/finance/clfbxd_detail.jsp?pageStatus=add&clbxType="+clbxType;
		      				}else if(clbxType=="1"){
		      					url="app/finance/car_clfbxd_detail.jsp?pageStatus=add&clbxType="+clbxType;
		      				}else if(clbxType=="2"){
		      					url="app/finance/ky_clfbxd_detail.jsp?pageStatus=add&clbxType="+clbxType;
		      				}
	      					top.$.dialog({
	      						width: 900,
	      						height: 520,
	      						lock: true,
	      						parent: api,
	      						data: {
	      							window: window
	      						},
	      						title: "新增",
	      						content: 'url:'+url
	      					});
	      				}
	      			}
	      		},
				{
					text: "关闭", icon: "cancel", click: function(){
						api.close();
					}
				}
			], 
			toolbarPosition: "bottom"
		});
	});
</script>
</head>
<body>
	<form name="form1" id="form1" action="" getAction="">
		<table border="0" cellpadding="2" cellspacing="0" class="l-detail-table">
			<tr>
				<%
				if("fybx".equals(bxType)){
				%>
				<td class="l-t-td-left">费用报销类型：</td>
				<%
				}else if("clbx".equals(bxType)){
				%>
				<td class="l-t-td-left">差旅报销类型：</td>
				<%
				}
				%>
				<td class="l-t-td-right"><input type="radio" name="bxType" id="bxType" ltype="radioGroup" validate="{required:true}"
					ligerui="{value:'0',data: [ { text:'普通', id:'0' }, { text:'车辆费用', id:'1' }, { text:'科研项目', id:'2' } ] }" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
