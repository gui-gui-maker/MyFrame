<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%
DateFormat ds = new SimpleDateFormat("yyyyMMdd");
String date = ds.format(new Date());
%>
    <title></title>
<%@ include file="/k/kui-base-form.jsp"%>
	 <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript" src="pub/fileupload1/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/archives/js/doc_order.js"></script>
<script type="text/javascript">
	var status="${param.pageStatus}";
	$(function(){
	    $("#form").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	            	api.close();
	
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (res){

			}, 
 			toolbarPosition: "bottom"
		});
	});
    function chooseReport(){
        top.$.dialog({
            width: 800,
            height: 450,
            lock: true,
            parent: api,
            title: "选择报告类型",
            content: 'url:app/approve/report_list_check.jsp',
            cancel: true,
            ok: function(){
                var p = this.iframe.contentWindow.getSelectedReport();
                if(!p){
                    top.$.notice("选择报告！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                    return false;
                }
                $("#fk_report_ids").val(p.ids);
                $("#report").val(p.names);
            }
        });
    }
    </script>
</head>

<body>
	<form id="form" method="post" action="certificateReportAction/saveCertReport.do">
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<input type="hidden" id="fk_cert_id" name="fk_cert_id" value="${param.id}"/>
		<input id="fk_report_ids" name="fk_report_ids" type="hidden"/>
			<tr>
				<td class="l-t-td-left">报告</td>
				<td class="l-t-td-right" colspan="3">
					<input id="report" name="report" type="text" ltype="text" ligerui="{iconItems:[{icon:'add',click:chooseReport}]}"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>