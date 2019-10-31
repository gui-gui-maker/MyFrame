<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="util.TS_Util"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>报告金额</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var code = api.data.code;
var param = api.data.param;
var reportPath = api.data.reportPath;
var ysje = api.data.ysje;
var info_id = api.data.info_id;
$(function() {
	$("#ysje").val(ysje);
	$("#form1").initForm({ //参数设置示例
		toolbar : [ {
			text : '保存',
			icon : 'save',
			click : save
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
})
	function close(){	
		api.close();
	}
			
	function save(){
		$.ajax({
            url: "reportItemValueAction/saveYsje.do?info_id="+info_id+"&ysje="+$("#ysje").val(),
            type: "POST",
            dataType: "json",
            success: function (data, stats) {
            	$("body").unmask();
                if (data["success"]) {
                	api.close();
                	api.data.window.inputAfterSaveYsje();
                }else{
                	$.ligerDialog.error('提示：' + data.msg);
                }
            },
            error: function (data,stats) {
            	$("body").unmask();
                $.ligerDialog.error('提示：' + data.msg);
            }
        });
	}
</script>
</head>
<body>
	<form id="form1" >		
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>报告金额</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>	
					<td class="l-t-td-left">金额：</td>
					<td class="l-t-td-right"  colspan="2">
						<input type="text" name="ysje" id="ysje"/>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
