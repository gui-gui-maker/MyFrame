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
<script type="text/javascript" src="/app/finance/cw_bill_audit_detail_para.js"></script>
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
						billGrid.loadData({
							Rows : res.data.billPara
						});
						if("${param.pageStatus}"!="detail"&&res.data.audit_time==null){
							$("#audit_time").val(date);
							$("#audit_opinion").ligerGetRadioGroupManager().setValue("1");
						}
					}
				});
			
			
			})
		
		function close(url){	
			api.close();
		}
		function save(){
			var url = "cwBillAction/saveBasic.do";
			//验证表单数据
			if($('#form1').validate().form()){
				var audit_opinion=$("#audit_opinion").ligerGetRadioGroupManager().getValue();
				var bill_num=$("#bill_num").val();
				var num=$("#num").val();
				if(audit_opinion=="1"&&billGrid.getData().length==0){
					$.ligerDialog.error('提示：至少添加一条发放发票号数据');
					return false;
				}
				if(num!=billGrid.getData().length){
					$.ligerDialog.error('提示：所选发票号段要和份数一致');
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
	</script>
</head>
<body>
	<form id="form1" getAction="cwBillAction/detail.do?id=${param.id }">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>基本情况</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
				<tr>
					<input id="id" name="id" type="hidden" />					
					<input id="create_op" name="create_op" type="hidden" />
					<input id="create_op_id" name="create_op_id" type="hidden"/>
					<input id="create_date" name="create_date" type="hidden"/>
					<input id="data_status" name="data_status" type="hidden" value="0" />
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
						onclick="selectUnitOrUser(0,0,'apply_org_id','apply_org')" value="<%=orgName %>"
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
					<td class="l-t-td-left">备注:</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="remark" id="remark" rows="3" cols="3" readonly="readonly"></textarea>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>审核情况</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
				<tr>
					<td class="l-t-td-left">审核意见：</td>
					<td class="l-t-td-right">
					<input type="radio"  name="audit_opinion" id="audit_opinion"  ltype="radioGroup" validate="{required:true,}"
					 ligerui="{onChange:function(value){
							if(value=='1'){
								$('#data_status').val('2');
								$('#bill').show();
							}else{
								$('#data_status').val('3');
								$('#bill_num').val('');
								$('#bill').hide();
							}
						},data:[{ text:'通过', id:'1' }, { text:'不通过', id:'2' } ]}"/>	
					</td>
				</tr>
				<tr id="bill">
					<td class="l-t-td-left">发放票段：</td>
					<td class="l-t-td-right" colspan="3">
					 	<input name="bill_num" id="bill_num" ltype='text' type="text"/>
					</td>
				</tr>
				<tr>				
					<td class="l-t-td-left">审核人:</td>
					<td class="l-t-td-right">		
						<input id="audit_op_id" name="audit_op_id" type="hidden" />
						<input name="audit_op" id="audit_op" validate="{required:true"} readonly="readonly" ltype='text'/>
					</td>					
					<td class="l-t-td-left">审核时间:</td>
					<td class="l-t-td-right" ><input name="audit_time" id="audit_time" type="text" ltype="date" validate="{required:true}"
							ligerui="{format:'yyyy-MM-dd'}"/></td>															
				</tr>
				<tr>
					<td class="l-t-td-left">审核备注:</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="audit_remark" id="audit_remark" rows="3" cols="3"></textarea>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>发放发票情况</div>
			</legend>
			<div id="degree"></div>
		</fieldset>
	</form>
</body>
</html>
