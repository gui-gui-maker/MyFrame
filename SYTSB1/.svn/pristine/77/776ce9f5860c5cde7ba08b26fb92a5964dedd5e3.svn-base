<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head pageStatus="${param.status}">
	</head>
	<%@ include file="/k/kui-base-form.jsp"%>
	<script type="text/javascript"
		src="app/report/report_transfer_info2.js"></script>
	<%
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String org_id = user.getDepartment().getId();
		String org_name = user.getDepartment().getOrgName();
	%>
	<script type="text/javascript">
	var pageStatus="${param.status}";
	$(function () {
		createReportTransferRecordGrid();
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'签收', id:'receive',icon:'submit', click:receiveInfo},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(resp){
	        	if(resp.success){
					reportTransferGrid.loadData({
						Rows : resp.reportTransferRecord
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
		if(confirm("亲，确认签收所选数据吗？签收后无法退回哦！")){
				$.getJSON("report/transfer/receive.do?id=${param.id}", function(resp){
					if(resp.success){
						api.data.window.refreshGrid();
		            	api.close();
						if(resp.info_id!=""){
							if(resp.bhg_report_sn!=""){
								top.$.dialog.notice({
						             content: "本次包含不合格的报告（报告编号："+resp.bhg_report_sn+"），不合格的报告不打印合格证！"
								});
							}else{
								top.$.dialog.notice({
						             content: "操作成功！正在跳转进行合格证打印..."
								});
							}
							
			            	var w=window.screen.availWidth;
							var h=(window.screen.availHeight);		
							top.$.dialog({ 
								width : w, 
								height :h, 
								lock : true, 
								title:"打印标签",
								content: 'url:app/query/report_print_index_tags.jsp?flag=yes&type=2',
								data : {
									"pwindow" : window,
									"id":resp.info_id
								}
							}).max();
						}else{
							top.$.dialog.notice({
					             content: "操作成功！"
							});
						}
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
			action="report/transfer/saveBasic.do"
			getAction="report/transfer/getDetail.do?id=${param.id}">
			<input id="id" name="id"  type="hidden"  />
			<input id="sn" name="sn"  type="hidden"  />
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>
						前后台报告交接明细表（注：单位、报告编号、份数必填）
					</div>
				</legend>
				<div id="reportTransferRecords"></div>
			</fieldset>		
		</form>
	</body>
</html>