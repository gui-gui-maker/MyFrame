<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<script type="text/javascript" src="/pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="/app/finance/cw_bill_return_detail_para.js"></script>
<%@include file="/k/kui-base-form.jsp"%>
<%
CurrentSessionUser user=SecurityUtil.getSecurityUser();
String orgId=user.getDepartment().getId();
String orgName=user.getDepartment().getOrgName();
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
String date = df.format(new Date());
String year =date.substring(0,4);
String userId = user.getId();
%>
<script type="text/javascript">		
		var pageStatus = "${param.pageStatus}";
		var orgName="<%=orgName %>";
		var userName="<%=user.getName()%>";
		var userId="<%=user.getId()%>";
		var date="<%=date %>";
		var year="<%=year %>";
		var step = "${param.step}";
			$(function() {
				createbillGrid();
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
						if(res.billList==null||res.billList==""){
							if(step=="editApply"){
								$.ligerDialog.alert('提示：选项数据还未填写申请信息');
							}else{
								$.ligerDialog.alert('提示：选项数据发票编号已全部使用，没有可退换发票号');
							}						
						}else{
							billGrid.loadData({
								Rows : res.billList
							});
						}
						if(res.data.return_received_time==null){
							$("#return_received_time").val(date);
							$("#return_op_opinion").ligerGetRadioGroupManager().setValue("1");
						}
					}
				});
			
			
			})
		
		function close(url){	
			api.close();
		}
		function save(){
			var url = "cwBillAction/saveReceivedDetail.do";
			//验证表单数据
			if($('#form1').validate().form()){
				var return_op_opinion=$("#return_op_opinion").ligerGetRadioGroupManager().getValue();
				var return_received_time=$("#return_received_time").val();
				if(return_op_opinion=="1"&&return_received_time==""){
					$.ligerDialog.error('提示：请填写接受时间');
					return false;
				}
				var formData = $("#form1").getValues();
		        var data = {};
		        data = formData;
		        data["billPara"] = billGrid.getData();
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
		function onChooseBillBack(){}
		function setValue(valuex,name){
			if(valuex==""){
				return;
			}
			if(name=='return_op'){
				$('#return_op').val(valuex)
			}
		}
	</script>
</head>
<body>
	<form id="form1" getAction="cwBillAction/getReturnDetail.do?id=${param.id }&step=${param.step }">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>申请详情</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
				<tr>
					<input id="id" name="id" type="hidden" />					
					<input id="create_op" name="create_op" type="hidden" />
					<input id="create_op_id" name="create_op_id" type="hidden"/>
					<input id="create_date" name="create_date" type="hidden"/>
					<input id="data_status" name="data_status" type="hidden" value="0" />
					<input id="audit_opinion" name="audit_opinion" type="hidden"/>
					<input id="audit_op" name="audit_op" type="hidden"/>
					<input id="audit_op_id" name="audit_op_id" type="hidden"/>
					<input id="audit_time" name="audit_time" type="hidden"/>
					<input id="audit_remark" name="audit_remark" type="hidden"/>
					<td class="l-t-td-left">申请人:</td>
					<td class="l-t-td-right" >
					<input id="apply_op_id" name="apply_op_id" type="hidden" value="<%=user.getId()%>" />
					<input name="apply_op" id="apply_op" validate="{required:true}" ltype='text' readonly="readonly" 
						 value="<%=user.getName()%>"
						ligerui="{disabled:true,iconItems:[
							{img:'k/kui/images/icons/16/user.png',click:function(val,e,srcObj){selectUnitOrUser(1,0,'apply_op_id','apply_op')}}
							]}" /></td>
							<td class="l-t-td-left">申请部门:</td>
					<td class="l-t-td-right"><input id="apply_org_id" name="apply_org_id" type="hidden" value="<%=orgId %>" />
					<input name="apply_org" id="apply_org" validate="{required:true}" ltype='text' readonly="readonly" 
						ligerui="{disabled:true,iconItems:[
							{img:'k/kui/images/icons/16/user.png',click:function(val,e,srcObj){selectUnitOrUser(0,0,'apply_org_id','apply_org')}}
							]}" /></td>					
				</tr>
				<tr>	
					<td class="l-t-td-left">申请时间:</td>
					<td class="l-t-td-right" ><input name="apply_time" id="apply_time" type="text" ltype="date" validate="{required:true}"
							ligerui="{disabled:true,initValue:'<%=date %>',format:'yyyy-MM-dd'}"/></td>			
					<td class="l-t-td-left">份数:</td>
					<td class="l-t-td-right">
						<input name="num" id="num" validate="{required:true,number:true,maxlength:20}" ltype='text' readonly="readonly"/>
					</td>					
				</tr>
				<tr>	
					<td class="l-t-td-left">申请取得票段号:</td>
					<td class="l-t-td-right" >
					<input name="bill_num" id="bill_num"  ltype='text' readonly="readonly"/>
					</td>						
				</tr>
				<tr>
					<td class="l-t-td-left">备注:</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="remark" id="remark" rows="3" cols="3" readonly="readonly"></textarea>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>退回详情</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
				<tr id="bill">
					<td class="l-t-td-left">退回票段：</td>
					<td class="l-t-td-right" colspan="3">
					 	<input name="return_bill_num" id=return_bill_num ltype='text' type="text"  readonly="readonly" />
					</td>
				</tr>
				<tr>				
					<td class="l-t-td-left">退回接受人:</td>
					<td class="l-t-td-right">		
						<input id="return_op" name="return_op" type="hidden" />
						<input type="text"  name="return_op_id" id="return_op_id"  ltype="select" validate="{required:true}"
							ligerui="{disabled:true,
							data:<u:dict sql="select t.id code,t.name text from SYS_USER t ,sys_role r,sys_user_role ur where t.id = ur.user_id and r.id = ur.role_id and r.name like '%票据申请退回接受人%'"/>
							}"/>
					</td>					
					<td class="l-t-td-left">退回时间:</td>
					<td class="l-t-td-right" ><input name="return_time" id="return_time" type="text" ltype="date" validate="{required:true}"
							ligerui="{disabled:true,format:'yyyy-MM-dd'}"/></td>															
				</tr>
				<tr>
					<td class="l-t-td-left">退回备注:</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="return_remark" id="return_remark" rows="3" cols="3" readonly="readonly"></textarea>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>接受人意见</div>
			</legend>	
				<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
				<tr>
					<td class="l-t-td-left">接受意见：</td>
					<td class="l-t-td-right">
					<input type="radio"  name="return_op_opinion" id="return_op_opinion"  ltype="radioGroup" validate="{required:true,}"
					 ligerui="{onChange:function(value){
							if(value=='1'){
								$('#data_status').val('7');
								$('#r_date ').show();
							}else{
								$('#data_status').val('8');
								$('#return_received_time').val(null);
								$('#r_date').hide();
							}
						},data:[{ text:'接收', id:'1' }, { text:'不接收', id:'2' } ]}"/>	
					</td>
				
				</tr>
				<tr  id="r_date">
					<td class="l-t-td-left">接受时间:</td>
					<td class="l-t-td-right" ><input name="return_received_time" id="return_received_time" type="text" ltype="date" 
							ligerui="{format:'yyyy-MM-dd'}"/></td>	
				</tr>
				<tr>
					<td class="l-t-td-left">接受人备注:</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="return_op_remark" id="return_op_remark" rows="3" cols="3"></textarea>
					</td>
				</tr>			
				</table>			
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>退回发票情况</div>
			</legend>
			<div id="degree"></div>
		</fieldset>
	</form>
</body>
</html>
