<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<script type="text/javascript" src="/pub/selectUser/selectUnitOrUser.js"></script>
<%@include file="/k/kui-base-form.jsp"%>
<%
CurrentSessionUser user=SecurityUtil.getSecurityUser();
String orgId=user.getDepartment().getId();
String orgName=user.getDepartment().getOrgName();
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
String date = df.format(new Date());
String year =date.substring(0,4);
%>
<script type="text/javascript">		
		var pageStatus = "${param.status}";
		var orgName="<%=orgName %>";
		var userName="<%=user.getName()%>";
		var userId="<%=user.getId()%>";
		var date="<%=date %>";
		var year="<%=year %>";

			$(function() {
				$("#form1").initForm({ //参数设置示例
					toolbar : [ {
						text : '保存',
						//id : 'save',
						icon : 'save',
						click : save
					},{
						text : '关闭',
						icon : 'cancel',
						click : close
					} ],
					getSuccess : function(res) {
						
					}
				});
			
			
			})
		
		function close(url){	
			api.close();
		}
		function save(){
			var url = "personalTrainingAction/saveBasic.do";
			//验证表单数据
			if($('#form1').validate().form()){
				var formData = $("#form1").getValues();
		        var data = {};
		        data = formData;
		        $("body").mask("正在保存数据，请稍后！");
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
		                	$.ligerDialog.error('提示：' + data.msg);
		                }
		            },
		            error: function (data,stats) {
		            	$("body").unmask();
		                $.ligerDialog.error('提示：' + data.msg);
		            }
		        });
			}
		}			
	</script>
</head>
<body>

	<form id="form1" getAction="personalTrainingAction/initData.do">

		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>基本情况</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
				<tr>
					<input id="id" name="id" type="hidden" />
					<input id="org_audit_op" name="org_audit_op" type="hidden" />
					<input id="org_audit_op_id" name="org_audit_op_id" type="hidden" />
					<input id="audit_op" name="audit_op" type="hidden" />
					<input id="audit_op_id" name="audit_op_id" type="hidden" />
					<input id="sign_op" name="sign_op" type="hidden" />
					<input id="sign_op_id" name="sign_op_id" type="hidden" />
					<input id="create_op" name="create_op" type="hidden" />
					<input id="create_op_id" name="create_op_id" type="hidden" />
					<input id="create_date" name="create_date" type="hidden" />
					<input id="status" name="status" type="hidden" />
					<td class="l-t-td-left">申请人:</td>
					<td class="l-t-td-right" colspan="3">
					<input id="apply_op_id" name="apply_op_id" type="hidden" />
					<input name="apply_op" id="apply_op" validate="{required:true}" ltype='text' readonly="readonly" onclick="selectUnitOrUser(1,1,'apply_op_id','apply_op')"
						ligerui="{iconItems:[
							{icon:'user',click:function(val,e,srcObj){selectUnitOrUser(1,1,'apply_op_id','apply_op')}}
							]}" /></td>
					
				</tr>
				<tr>
					<td class="l-t-td-left">申请部门:</td>
					<td class="l-t-td-right" colspan="3">
					<input id="apply_org_id" name="apply_org_id" type="hidden" />
					<input name="apply_org" id="apply_org" readonly="readonly" validate="{required:false}" ltype='text' onclick="selectUnitOrUser(0,1,'apply_org_id','apply_org')"
							ligerui="{iconItems:[
							{icon:'org',lick:function(val,e,srcObj){selectUnitOrUser(0,1,'apply_org_id','apply_org')}}
							]}"/></td>
				</tr>
				<tr>
					
				<!-- 	<td class="l-t-td-left">编号:</td>
					<td class="l-t-td-right">
						<input name="sn" id="sn" validate="{required:true,maxlength:200}" ltype='text' readonly="readonly"/>
					</td> -->
					<td class="l-t-td-left">申请培训日期:</td>
					<td class="l-t-td-right" ><input name="apply_date" id="apply_date" type="text" ltype="date" validate="{required:true}"
							ligerui="{initValue:'',format:'yyyy-MM-dd'}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">拟申请培训的内容:</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="apply_content" id="apply_content" rows="3" cols="3"  validate="{required:true}"></textarea>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">申请理由:</td>
					<td class="l-t-td-right" colspan="3" >
						<textarea name="apply_reason" id="apply_reason" rows="3" cols="3" validate="{required:true}"></textarea>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">备注:</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="remark" id="remark" rows="3" cols="3"></textarea>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
