<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	var pageStatus="${param.pageStatus}";
	var w = window.screen.availWidth;
	var h = window.screen.availHeight;
	
	$(function () {
	    $("#formObj").initForm({ 
	       toolbar:[
	            { text:'打印', id:'save',icon:'save', click:save},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(resp){

	        },
	        success: function (response) {

			}
	    });	 
	});
	
	function save(){
    	//验证表单数据
		if($('#formObj').validate().form()){
			var print_type =$('#print_type').ligerGetRadioGroupManager().getValue();
			var print_remark = $('#remark').val();
			var print_count = $('#print_count').val();
			if(isNaN(print_count)){
				$.ligerDialog.error('打印份数必须是一个数字，不能包含字母、符号、空格等，请重新填写！');
				return;
			}else{
				if(print_count<1){
					$.ligerDialog.error('打印份数不能小于1，请重新填写！');
					return;
				}
			}
			
			if("4" == print_type){
				if(print_remark==null || print_remark==undefined || print_remark==""){
					$.ligerDialog.error('选择为其他打印时，请填写备注！');
					return;
				}
			}
			if(print_remark==null || print_remark==undefined){
				print_remark = "";
			}
			
			if("0" == "${param.print_types}"){
				top.$.dialog({ 	
					width : w, 
					height : h, 
					lock : true, 
					title:"打印报告",
					content: 'url:app/query/report_print_index.jsp?printType=1',
					data : {
						"window" : window,
						"id" : api.data.ids,
						"print_type":print_type,
						"print_count":print_count,
						"print_remark":print_remark
					}
				}).max();
			}else{
				var ids = api.data.ids;
				var report_sns = api.data.report_sns.split(",");
				var export_pdfs = api.data.export_pdfs.split(",");
				// 补打报告只打印一份
				openN(0,ids,report_sns,export_pdfs,api.data.l,print_count,3,print_type,print_remark);
			}
	    }
    }
	
	function openN(i,ids,report_sns,export_pdfs,l,printcopies,type,print_type,print_remark){
		top.$.dialog({
			width : 800, 
			height : 400, 
			lock : true, 
			title:"打印报告",
			content: 'url:app/flow/reportPdfPrint/report_doc.jsp?status=detail&print=true&id='+ids[i],
			data : {"window" : window,"report_sn":report_sns[i],"date":export_pdfs[i],"type":type,"printcopies":printcopies,"print_type":print_type,"print_remark":print_remark},
			close:function(){
				i++;
				if(i<l){
					if(report_sns[i]!=null && report_sns[i] != "" && report_sns[i] != "undefined"){
						openN(i,ids,report_sns,export_pdfs,l,printcopies,type,print_type,print_remark);
					}
				}else{
					
					//Qm.refreshGrid();
				}
			}
		}).max();	
	}
	
	function close(){
    	api.close();
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>打印报告</div>
			</legend>
			<table width="100%">
				<tr>
					<td class="l-t-td-left">打印类型：</td>
					<td class="l-t-td-right" colspan="3"><input type="radio"
						name="print_type" id="print_type" ltype="radioGroup"
						validate="{required:true}"
						ligerui="{value:'1',data: [{ text:'补办打印', id:'1' }, { text:'纠错打印', id:'2' }, { text:'换证打印', id:'3' }, { text:'其他打印', id:'4' } ] }" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">打印份数：</td>
					<td class="l-t-td-right" >
						<input name="print_count" id="print_count" type="text" ltype='spinner' value='1' validate="{required:true,digits:true,maxlength:32}" />
					</td>
					<td class="l-t-td-left">&nbsp;&nbsp;</td>
					<td class="l-t-td-right" >&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" colspan="3"><textarea
							name="remark" id="remark" rows="3" cols="25"
							class="l-textarea" validate="{required:false,maxlength:1000}"></textarea>
					</td>
				</tr>
				
			</table>
		</fieldset>
	</form>
</body>
</html>
