<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/k/kui-base-form.jsp" %>
<title>报告打印页面</title>

<style type="text/css">
#ow_big_table {width:100%;height:100%;}
#ow_big_table .obt3 {text-align:right;height:48px;}
#ow_big_table .obt3 input {margin-right:30px;margin-top:10px;}
#tree_big_tb {width:100%;height:100%;/*border-top:1px solid #0066FF;border-left:1px solid #0066FF;*/}
#tree_big_tb td {/*border-bottom:1px solid #0066FF;border-right:1px solid #0066FF;*/}
#tree_big_tb_td_left {vertical-align:top;width:228px;height:100%;}
#tree_big_tb_td_left iframe {width:100%;height:100%;}
#tree_big_tb_td_center {width:4px;border:1px solid #276BA2;cursor:col-resize;}
#tree_big_tb_td_right {vertical-align:top;height:100%;}
#tree_big_tb_td_right iframe {width:100%;height:100%;}
.ipt_02 {background:url("k/kui/images/button_1.gif");width:71px;height:23px;border:0px;padding:3px 0px 0px 5px;}
.toolbar-tm-bottom {text-align:center;}<!-- 按钮居中 -->
</style>
<script type="text/javascript">
	//设左页面
	<% //String view = "app/tzsb/inspection/flow/print/report_print_left";%>
	$(function() {

		var height = $(window).height();
	/* 	$('#tree_big_tb_td_left').css({height:height});
		$('#tree_big_tb_td_right').css({height:height}); */
		
		//初始化
		$("#form1").initForm({ //参数设置示例
			toolbar : [  
			{
				text : '打印',
				icon : 'print',
				id:'save',
				click : print
			}
			, 
			{
				text : '关闭',
				icon : 'cancel',
				click : close
			} ],
			getSuccess : function(res) {
			}
		});
		
		//+api.data.id+'&acc_id='+api.data.acc_id+'&flow_num='+api.data.flow_num+'&printType=${param.printType}
		//&print_type='+api.data.print_type+'&print_count='+
		//api.data.print_count+'&print_remark='+encodeURI(encodeURI(api.data.print_remark))
		
		if('${param.re_print}'=='1'){
			var url = "report/query/showReportList.do?id="+api.data.infoIds+"&re_print=${param.re_print}&is_print=1&view=app/flow/rtcommon/report_print_left_doc"
					+'&acc_id='+api.data.activityIds+'&flow_num='+api.data.flow_num+'&record=${param.record}';
		} else {
			var url = "report/query/showReportList.do?id="+api.data.infoIds+"&re_print=${param.re_print}&is_print=1&view=app/flow/rtcommon/report_print_left_doc"
					+'&acc_id='+api.data.activityIds+'&flow_num='+api.data.flow_num+'&record=${param.record}';
		}//alert(url)
		$('#left').attr("src",url);
	})
	//关闭
	function close(url)
	{	
		api.data.window.Qm.refreshGrid();
		api.close();
	}
	//保存
	function print(url)
	{
		disableBtn();
		left.printFirset();//不调用printAll方法，主要是为了打印第一份报告不再重新查询
		//share.data("isBatch",true);
		//打开提交页面
		//right.doSub();
	}
	//禁用操作按钮
	function disableBtn(){
		$("#save").attr("disabled",true);
	}
	//启用操作按钮
	function ableBtn(){
		$("#save").attr("disabled",false);
	}
	
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div style="overflow:hidden">
	<form id="form1" style="height:99%;">
		<table border="0" cellpadding="0" cellspacing="0" id="tree_big_tb" height="100%">
			<tr>
				<td id="tree_big_tb_td_left">
				<iframe name="left" id="left" src="" width="100%" height="100%" scrolling="auto" frameborder="0" border="1"></iframe>
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