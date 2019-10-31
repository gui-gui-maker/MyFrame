<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head pageStatus="${param.status}">
	</head>
	<%@ include file="/k/kui-base-form.jsp"%>
	<script type="text/javascript"
		src="app/report/report_print_info2.js"></script>
	<%
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String org_id = user.getDepartment().getId();
		String org_name = user.getDepartment().getOrgName();
	%>
	<script type="text/javascript">
	var pageStatus="${param.status}";
	$(function () {
		createReportPrintRecordGrid();
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'签收', id:'receive',icon:'submit', click:receiveInfo},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(resp){
	        	if(resp.success){
					reportPrintGrid.loadData({
						Rows : resp.reportPrintRecord
					});
					$("#formObj").setValues(resp.data);
					$("#org_id").val(resp.data.org_id);
					$("#org_name").val(resp.data.org_name);
				}
	        },
	        success: function (response) {//处理成功
	    		if (response.success) {
		      		top.$.dialog.notice({
			             content: "保存成功！"
					});
	            	api.data.window.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}
		});
	});	

	function receiveInfo(){
		if(confirm("亲，确认签收此次报送打印信息吗？签收后无法回退数据哦！")){
				$.getJSON("report/print/receive.do?id=${param.id}", function(resp){
					if(resp.success){
						top.$.dialog.notice({
				             content: "操作成功！"
						});
		            	api.data.window.refreshGrid();
		            	api.close();
					}else{
						$.ligerDialog.error("操作失败，未找到系统相关业务流程，请联系系统管理员！");
					}
			})
		}
	}
	    
	function close(){
		api.close();
	}	
</script>
	<body>
		<form name="formObj" id="formObj" method="post"
			action="report/print/saveBasic.do"
			getAction="report/print/getDetail.do?id=${param.id}">
			<input id="id" name="id"  type="hidden"  />
			<input id="sn" name="sn"  type="hidden"  />
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>
						基本信息
					</div>
				</legend>
				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					<tr>
						<td class="l-t-td-left">部门：</td>
						<td class="l-t-td-right">
							<input id="org_id" name="org_id"  type="hidden" value="" />
							<input id="org_name" name="org_name"  type="text" ltype="text" value="" disabled=disabled/>
						</td>	
						<td class="l-t-td-left"></td>
						<td class="l-t-td-right"></td>						
					</tr>
				</table>
			</fieldset>
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>
						报送打印明细表（注：报告编号必填，使用/制造/安装单位必填）
					</div>
				</legend>
				<div id="reportPrintRecords"></div>
			</fieldset>		
		</form>
	</body>
</html>