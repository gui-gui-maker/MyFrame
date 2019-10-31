<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="detail">
<title>设备预警列表</title>

<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var qmUserConfig = {
            listeners: {
            	onSelectRow : function(){
                	$("#op_remark").text(Qm.getValuesByName("deal_remark"));
                	$("#op_status").text(Qm.getValuesByName("deal_status_name"));
                	$("#op_man").text(Qm.getValuesByName("deal_man"));
                	$("#op_date").text(Qm.getValuesByName("deal_time"));
                	$("#op_org").text(Qm.getValuesByName("deal_unit"));
                	$("#op_re_man").text(Qm.getValuesByName("deal_receive_man"));
                }

            }
};
$(function() {

});

	
</script>
<style>
#formObj .l-t-td-right{
border:none;border-bottom:solid 1px #9DB9E2;
}
</style>
</head>
<body>
<form name="formObj" id="formObj" method="post" action="">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>处理情况</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">操作人：</td>
					<td class="l-t-td-right"><span class="sp_info" id="op_man"></span></td>									
					<td class="l-t-td-left">操作时间：</td>
					<td class="l-t-td-right"><span class="sp_info" id="op_date"></span></td>	
				</tr>
				<tr>
					<td class="l-t-td-left">操作人所属机构：</td>
					<td class="l-t-td-right" ><span class="sp_info" id="op_org"></span></td>				
					<td class="l-t-td-left">接受对象：</td>
					<td class="l-t-td-right" ><span class="sp_info" id="op_re_man"></span></td>														
				</tr>
				<tr>
					<td class="l-t-td-left">处理状态：</td>
					<td class="l-t-td-right" colspan="3">
				         <span class="sp_info" id="op_status"></span>
					</td>	
				</tr>
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" colspan="3" > <span class="sp_info" id="op_remark"></span></td>									
					</td>	
				</tr>
			</table>
		</fieldset>
	</form>
		<qm:qm pageid="warning_record_list" script="false" singleSelect="true">
		<qm:param name="fk_base_device_document_id" value="${param.id }" compare="=" />
	</qm:qm>
</body>
</html>
