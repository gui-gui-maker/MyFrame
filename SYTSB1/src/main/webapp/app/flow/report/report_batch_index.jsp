<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title></title>
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
	<style type="text/css">
		#ow_big_table {width:100%;height:100%;}
		#ow_big_table .obt3 {text-align:right;height:48px;}
		#ow_big_table .obt3 input {margin-right:30px;margin-top:10px;}
		#tree_big_tb {width:100%;height:100%;/*border-top:1px solid #0066FF;border-left:1px solid #0066FF;*/}
		#tree_big_tb td {/*border-bottom:1px solid #0066FF;border-right:1px solid #0066FF;*/}
		#tree_big_tb_td_left {vertical-align:top;width:278px;height:100%;}
		#tree_big_tb_td_left iframe {width:100%;height:100%;}
		#tree_big_tb_td_center {width:4px;border:1px solid #276BA2;cursor:col-resize;}
		#tree_big_tb_td_right {vertical-align:top;height:100%;}
		#tree_big_tb_td_right iframe {width:100%;height:100%;}
		.ipt_02 {background:url("k/kui/images/button_1.gif");width:71px;height:23px;border:0px;padding:3px 0px 0px 5px;}
	</style>	
	<%
		String type = request.getParameter("type");
		String op_name = "04".equals(type)?"审核":"签发";
	%>
	<%@ include file="/k/kui-base-form.jsp"%>
	<script type="text/javascript">
		$(function() {
			$("#form1").initForm({ //参数设置示例
				toolbar : [ {
						text : '批量'+'<%=op_name%>',
						id : 'saveALLBtn',
						icon : 'save',
						click : function ()
		                {
		                    //disabledSaveAll();
							left.saveAll();						
		                }
				}, {
					text : '关闭',
					id : 'close',
					icon : 'cancel',
					click : close
				}],
				getSuccess : function(res) {
						
				}
			});	

			var height = $(window).height();
			$('#tree_big_tb_td_left').css({height:height});
			$('#tree_big_tb_td_right').css({height:height});
		})
		
		function save(data){
			$('#sssss').hide();
			if(data.ids=="null" || data.acc_id=="null"){
				alert('请先点击您想要单独<%=op_name%>的报告！ ');
				showBB();
				return;
			}
			
			top.$.dialog({
				width : 600, 
				height : 500, 
				lock : true, 
				title:data.op_type+'<%=op_name%>',
				parent:api,
				content: 'url:app/flow/report/report_batch_check.jsp?acc_id='+data.acc_id+'&flow_num='+data.flow_num+'&type='+data.type+'&device_type='+data.device_type+'&device_sort_code='+data.device_sort_code+'&check_flow='+data.check_flow+'&ids='+data.ids+'&isBatch='+data.isBatch+'&opid='+data.opid+'&advance_time='+data.advance_time+'&check_time='+data.check_time+'&enter_time='+data.enter_time+'&org_id='+data.org_id,
				data : {"window" : window}
			});
		}
		
		function showBB(){
			$("#sssss").show();
		}
		
		// 禁用批量操作按钮
		function disabledSaveAll(){
			$("#saveALLBtn").attr("disabled","disabled");
		}
		
		// 禁用单份操作按钮
		function disabledSave(){
			$("#saveBtn").attr("disabled","disabled");
		}
		
		// 启用批量操作按钮
		function showSaveAllBtn(){
			$('#saveALLBtn').removeAttr('disabled');     
		}
		
		// 启用单份操作按钮
		function showSaveBtn(){
			$('#saveBtn').removeAttr('disabled');     
		}
		
		function close()
		{	
			api.data.window.submitAction();
			api.close();
		}
	</script>	
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div  id="sssss">
<form id="form1" style="height:99%;">
	<table border="0" cellpadding="0" cellspacing="0" id="tree_big_tb" height="100%">
		<tr>
			<td id="tree_big_tb_td_left">
				<iframe name="left" src="report/batch/showReports.do?ids=${param.ids}&acc_id=${param.acc_id}&flow_num=${param.flow_num}&type=${param.type}&device_type=${param.device_type}&device_sort_code=${param.device_sort_code}&check_flow=${param.check_flow}&org_id=${param.org_id}" width="100%" height="100%" scrolling="auto" frameborder="0" border="0"></iframe>
			</td>
			<td id="tree_big_tb_td_right">
				<iframe name="right" src="" width="100%" height="100%" scrolling="no" frameborder="0" border="0"></iframe>				
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>