<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>添加微信应用基础信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
				$("body").unmask();
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	         		api.data.window.refreshGrid();
	            	api.close();	            	      	
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess:function(resp){
	        	if(resp.success){
					$("#formObj").setValues(resp.data);
				}
	        }, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		if(confirm("确定保存？")){
				    			$("body").mask("正在保存数据，请稍后！");
				    			//表单提交
				    			$("#formObj").submit();
					    	}
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
	
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="weixin/app/info/saveBasic.do?status=${param.status}"
		getAction="weixin/app/info/getDetail.do?id=${param.id}">
		<input type="hidden" name="id" id="id" value="${param.id}"/>
		<c:if test="${param.status eq 'modify'}">
		<input type="hidden" name="create_user_id" id="create_user_id"/>
		<input type="hidden" name="create_user_name" id="create_user_name"/>
		<input type="hidden" name="create_date" id="create_date"/>
		<input type="hidden" name="data_status" id="data_status"/>
		</c:if>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>基本信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">	
				<tr>
					<td class="l-t-td-left">微信应用序号：</td>
					<td class="l-t-td-right"><input type="text" name="app_sn" id="app_sn" ltype="text" validate="{required:true,maxlength:20}"/></td>
					<td class="l-t-td-left">微信应用编号：</td>
					<td class="l-t-td-right"><input type="text" name="app_code" id="app_code" ltype="text" validate="{required:true,maxlength:20}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">微信应用名称：</td>
					<td class="l-t-td-right"><input type="text" name="app_name" id="app_name" ltype="text" validate="{required:true,maxlength:20}"/></td>
					<td class="l-t-td-left">功能模块名称：</td>
					<td class="l-t-td-right">
						<u:combo name="func_name" code="MAINTENANCE_FUNCTION" validate="required:true"  attribute="disabled:false" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">首页请求地址：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="app_index_req" id="app_index_req" rows="3" cols="25" class="l-textarea" validate="{required:false,maxlength:1000}"><c:out value="weixin/app/info/getUserInfo.do?app_code="></c:out></textarea>
					</td>				
				</tr>
				<tr>
					<td class="l-t-td-left">首页响应地址：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="app_index_url" id="app_index_url" rows="3" cols="25" class="l-textarea" validate="{required:true,maxlength:1000}"></textarea>
					</td>				
				</tr>
				<tr>
					<td class="l-t-td-left">业务处理地址：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="app_deal_url" id="app_deal_url" rows="3" cols="25" class="l-textarea" validate="{required:true,maxlength:1000}"></textarea>
					</td>				
				</tr>
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="remarks" id="remarks" rows="3" cols="25" class="l-textarea" validate="{required:false,maxlength:1000}"></textarea>
					</td>				
				</tr>
			</table>
		</fieldset>	
		<c:if test="${param.status eq 'detail'}">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>操作日志</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>					
					<td class="l-t-td-left">创建人：</td>
					<td class="l-t-td-right">
						<input type="text" id="create_user_name" name="create_user_name" ltype="text" />
					</td>
					<td class="l-t-td-left">创建日期：</td>
					<td class="l-t-td-right">
						<input name="create_date" id="create_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
				</tr>
				<tr>					
					<td class="l-t-td-left">修改人：</td>
					<td class="l-t-td-right">
						<input type="text" id="mdy_user_name" name="mdy_user_name" ltype="text" />
					</td>
					<td class="l-t-td-left">修改日期：</td>
					<td class="l-t-td-right">
						<input name="mdy_date" id="mdy_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
				</tr>
			</table>
		</fieldset>
		</c:if>
	</form>
</body>
</html>
