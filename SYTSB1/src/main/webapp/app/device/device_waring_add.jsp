<%@page import="com.khnt.utils.DateUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>添加处理记录</title>
<%@ include file="/k/kui-base-form.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	//String last_check_time = request.getParameter("last_check_time");
	String device_sort_code = request.getParameter("device_sort_code");
	String clause = " and t.value not in ( '1','2','3','4'" ;
	//if(last_check_time.indexOf("-")!= -1) {
		//clause += " ,'100','105' " ;
		clause += " ,'105' " ;
		clause += ",'108'" ;
		clause += " ) ";
	//} else  {
	//	clause += " ,'108') " ;
	//}
	String deal_status_sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'BASE_WARNING_DEAL_STATUS'  "+clause;
%>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
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
			}, getSuccess: function (response){
				
			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		var formData = $("#formObj").getValues();
					        var data = {};
					        data = formData;
					     
					        $("body").mask("正在保存数据，请稍后！");
					    	url = "device/deal/saveDealRecord.do";
					        $.ajax({
					            url: url,
					            type: "POST",
					            datatype: "json",
					            contentType: "application/json; charset=utf-8",
					           	data: $.ligerui.toJSON(data),
					            success: function (data, stats) {
					            	$("body").unmask();
					                if (data["success"]) {
					                	if(api.data.window.Qm)
					                	{
					                		api.data.window.Qm.refreshGrid();
					                	}
					                    top.$.dialog.notice({content:'保存成功'});
					                    api.close();
					                }else
					                {
					                	$.ligerDialog.error('提示：' + data);
					                }
					            },
					            error: function (data,stats) {
					            	$("body").unmask();
					                $.ligerDialog.error('提示：' + data.msg);
					            }
					        });    		
				    	}
	      			}
	      		},
				{
					text: "关闭", icon: "cancel", click: function(){
						api.close();
					}
				}
			], toolbarPosition: "bottom"
		});		
	});
	
	function selectImg()
	{  
		var formData=$("#formObj").getValues();
		var status=formData.deal_status;
		if(status==""){
			alert("请首先选择处理状态");
			return ;
		}
		top.$.dialog({
			width : 500,
			height :  300,
			lock : true,
			title : "备注选项",
			parent: api,
			content : "url:app/device/device_waring_add_remark.jsp?deal_status="+status+"&nextDate=",
			data: {pwindow: window}
		});
	}
	function closewindow(){
		api.close();
	}
	
	String.prototype.Trim = function() {
		var m = this.match(/^\s*(\S+(\s+\S+)*)\s*$/);
		return (m == null) ? "" : m[1];
	}
	String.prototype.isMobile = function() {
		return (/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/.test(this.Trim()));
	}
	String.prototype.isTel = function()
	{
		//"兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
	    //return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
	    return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
	}
	
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="">
		<input type="hidden" id="fk_base_device_document_id" name="fk_base_device_document_id" value="${param.ids}"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>处理信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">操作人：</td>
					<td class="l-t-td-right"><input  type="text" ltype='text' validate="{required:true,maxlength:32}"  value="<%=user.getUserName()%>" disabled="disabled"/></td>									
					<td class="l-t-td-left">操作时间：</td>
					<td class="l-t-td-right"><input name="deal_time" id="deal_time" type="text" ltype='text' validate="{maxlength:32}" value="<%=DateUtil.getDate(new Date()) %>"  disabled="disabled"/></td>	
				</tr>
				<tr>
					<td class="l-t-td-left">操作人所属机构：</td>
					<td class="l-t-td-right" colspan="3"><input name="deal_unit" id="deal_unit" type="text" ltype='text' validate="{required:true,maxlength:32}" value="<%=user.getUnit().getOrgName()%>" disabled="disabled"/></td>									
				</tr>
				<tr>
					<td class="l-t-td-left">处理状态：</td>
					<td class="l-t-td-right" colspan="3">
				<input type="text" id="deal_status" name="deal_status" ltype="select" validate="{required:true}"   ligerui="{
				readonly:true,
				data: <u:dict sql="<%=deal_status_sql %>"/>
				}"/>
					</td>	
				</tr>
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea id="deal_remark" validate="{required:false}"  name="deal_remark" rows="6" cols="50" class="l-textarea"></textarea>
					</td>										
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
