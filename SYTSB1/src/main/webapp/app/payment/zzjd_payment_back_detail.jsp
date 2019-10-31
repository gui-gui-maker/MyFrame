<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.text.SimpleDateFormat" pageEncoding="utf-8" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String pageStatus = request.getParameter("status");
%>
<head pageStatus="${param.status}">
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/payment/zzjd_payment_list.js"></script>
<script type="text/javascript">
	var pageStatus = "${param.status}";		
	// 设备类别
	var deviceType = <u:dict code="device_classify"/>
	// 收费状态
	var feeStatus = <u:dict code="PAYMENT_STATUS"/>
	// 收费类型
	var advanceType = <u:dict code="ADVANCE_TYPE"/>
	// 实收总金额
	var receivable;
	
	$(function() {
		createInspectionInfoGrid2();
		$("#form1").initForm({
			success : function(responseText) {//处理成功
				if (responseText.success) {
					top.$.notice("保存成功！");
					api.data.window.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败' + responseText);
				}
			},
			getSuccess : function(resp) {
				inspectionInfoGrid.loadData({
					Rows : resp.data["inspectionZZJDPayInfoDTOList"]
				});
				receivable = resp.receivable;
			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#form1").validate().form()) {
				    		var cash_back = $("#cash_back").val();
				    		if(parseFloat(cash_back) == 0){
				    			$.ligerDialog.alert("请输入退款金额！");
				    			$("#cash_back").focus();
				    		}else if(parseFloat(cash_back) < 0){
				    			$.ligerDialog.error("退款金额不能小于或等于0，请检查！", "错误");
				    			$("#cash_back").focus();
				    		}else{
				    			if(parseFloat(cash_back)>parseFloat(receivable)){
				    				$.ligerDialog.error("退款金额不能大于实收金额，请检查！", "错误");
				    				$("#cash_back").focus();
				    			}else{
				    				if(confirm("确定保存？保存后不能修改！")){
						    			//表单提交
						    			$("#form1").submit();
							    	}	
				    			}
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
	
	function initInfo(){
		$.getJSON("payment/payBack/getZZJDDetail.do?id=${param.id}&info_id=${param.info_id}", function(resp){
			if(resp.success){
				inspectionInfoGrid.loadData({
					Rows : resp.data["inspectionZZJDPayInfoDTOList"]
				});
				receivable = resp.receivable;
			}
		})
	}
</script>
</head>
<body <%if("add".equals(pageStatus)){%>onload="initInfo();"<%}%> >
<div class="navtab">
	<div title="退款情况" tabId="payInfoTab" style="height: 400px">
	<form name="form1" id="form1" action="payment/payBack/savePayBack.do?status=${param.status}"
		getAction="payment/payBack/getZZJDDetail.do?id=${param.id}&info_id=${param.info_id}">
		<input id="id" name="id" type="hidden" />
		<input id="fk_inspection_info_id" name="fk_inspection_info_id" type="hidden" value="${param.info_id}" />
		<input id="com_name" name="com_name" type="hidden" value="${param.com_name}" />
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>退款单</div>
			</legend>
			<table border="0" cellpadding="3" cellspacing="0" class="l-detail-table">
			<!-- 
			<c:if test="${'detail' eq param.status}">
				<tr>
					<td class="l-t-td-left">业务流水号：</td>
					<td class="l-t-td-right">
						<input id="sn" name="sn" type="text" ltype='text'/>
				    </td>
					<td class="l-t-td-left">单位名称：</td>
					<td class="l-t-td-right">
						<input name="com_name" id="com_name" type="text" ltype='text'/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">设备类型：</td>
					<td class="l-t-td-right">
						<input name="device_sort_code" id="device_sort_code" type="text" ltype='text'/>
				    </td>
					<td class="l-t-td-left">设备名称：</td>
					<td class="l-t-td-right">
						<input name="device_name" id="device_name" type="text" ltype='text'/>
					</td>
				</tr>
			</c:if>
			 -->
				<tr>
					<td class="l-t-td-left">退款金额：</td>
					<td class="l-t-td-right">
						<input id="cash_back" name="cash_back" type="text" ltype='float' validate="{required:true,maxlength:20}" />
				    </td>
					<td class="l-t-td-left">退款时间：</td>
					<td class="l-t-td-right">
						<input name="back_date" id="back_date" type="text" ltype='date' validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">接受退款人：</td>
					<td class="l-t-td-right">
						<input id="receive_man" name="receive_man" type="text" ltype='text' validate="{required:true,maxlength:20}" />
				    </td>
					<td class="l-t-td-left">&nbsp;</td>
					<td class="l-t-td-right">&nbsp;</td>
				</tr>
				<tr>
						<td class="l-t-td-left">备注:</td>
						<td class="l-t-td-right" colspan="3">
							<textarea name="remark" id="remark" cols="60" rows="4" class="l-textarea"></textarea>
						</td>
					</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>业务信息</div>
			</legend>
			<div id="inspectionInfoList"></div>
		</fieldset>
	</form>
	</div>
	<%
		if(!"add".equals(pageStatus)){
			%>
			<div title="业务信息" tabId="inspectionInfoTab">
				<form id="inspectionInfoForm" name="inspectionInfoForm" >
			  	<!-- <input type="hidden" name="id" id="inspection_info_id"/> -->
				<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
					<tr>
				       <td class="l-t-td-left">业务流水号：</td>
						<td class="l-t-td-right">
							<input name="sn" id="sn" type="text" ltype='text'/>
						</td>
						<td class="l-t-td-left">报告书编号：</td>
						<td class="l-t-td-right"><input name="report_sn" id="report_sn" type="text" ltype='text'/></td>
				    </tr>
				    <tr>
				    	<td class="l-t-td-left">制造单位：</td>
				       	<td class="l-t-td-right" colspan="3"><input name="made_unit_name" id="made_unit_name" type="text" ltype='text' /></td>
				    </tr>
				    <tr>
				    	<td class="l-t-td-left">设备类别：</td>
				       	<td class="l-t-td-right"><u:combo name="device_type_code" code="device_classify" tree="true" attribute="treeLeafOnly:true"/></td>
				    	<td class="l-t-td-left">产品名称：</td>
				       	<td class="l-t-td-right"><input name="device_name" id="device_name" type="text" ltype='text' /></td>
				    </tr>
				    <tr>
				       	<td class="l-t-td-left">产品编号：</td>
				       	<td class="l-t-td-right"><input name="device_no" id="device_no" type="text" ltype='text' /></td>
				       	<td class="l-t-td-left">设备代码：</td>
				       	<td class="l-t-td-right"><input name="device_code" id="device_code" type="text" ltype='text' /></td>
				    </tr>
				    <tr>
				       	<td class="l-t-td-left">应收金额：</td>
				       	<td class="l-t-td-right"><input name="advance_fees" id="advance_fees" type="text" ltype='float' /></td>
				       	<td class="l-t-td-left">实收金额：</td>
				       	<td class="l-t-td-right"><input name="receivable" id="receivable" type="text" ltype='float' /></td>
				    </tr>
				    <tr>
				    	<td class="l-t-td-left">收费状态：</td>
				       	<td class="l-t-td-right"><u:combo name="fee_status" code="PAYMENT_STATUS"/></td>
				       	<td class="l-t-td-left">收费类型：</td>
				       	<td class="l-t-td-right"><u:combo name="advance_type" code="ADVANCE_TYPE"/></td>
				    </tr>
				    <tr>
				       	<td class="l-t-td-left">检验部门：</td>
				       	<td class="l-t-td-right"><input name="inspection_unit_name" id="inspection_unit_name" type="text" ltype='text' /></td>
				       	<td class="l-t-td-left">参检人员：</td>
				       	<td class="l-t-td-right"><input name="check_op_name" id="check_op_name" type="text" ltype='text' /></td>
				    </tr>
				    <tr>
				    	<td class="l-t-td-left">检验日期：</td>
				       	<td class="l-t-td-right"><input name="advance_time" id="advance_time" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"/></td>
						<td class="l-t-td-left">备注：</td>
				       	<td class="l-t-td-right"><input name="remark" id="remark" type="text" ltype='text' /></td>
				    </tr>
				    <tr>
				       	<td class="l-t-td-left">录入人员：</td>
				       	<td class="l-t-td-right"><input name="enter_op_name" id="enter_op_name" type="text" ltype='text' /></td>
				       	<td class="l-t-td-left">录入时间：</td>
				       	<td class="l-t-td-right"><input name="enter_time" id="enter_time" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"/></td>
				    </tr>
				</table> 
			  	<script type="text/javascript">
			    $("#inspectionInfoForm").initFormList({
			    	root:'datalist',
			        getAction:"payment/payBack/getInspectionZZJDInfoList.do?id=${param.info_id}",
			        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
			        //actionParam:{"baseEquipmentApply.id" : $("#formObj>[name='id']")},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
			        //delAction:'equipment/apply/relation/delete.do',	//删除数据的action
			        //delActionParam:{ids:"id"},	//默认为选择行的id 
			        columns:[
			            //此部分配置同grid
			            //{ display: 'id', name: 'id',  hide:true},
				        { display: '业务流水号', name: 'sn', width: '10%'},
				        { display: '报告书编号', name: 'report_sn', width: '10%'},
				        { display: '制造单位', width: '20%', name: 'made_unit_name', type: 'text'},
				        { display: '设备类别', width: '15%', name: 'device_type_code', type: 'text', 
				            render: function (item) {
				                return render(item["device_type_code"],deviceType);
				            }
				        },
				        { display: '产品名称', width: '12%', name: 'device_name', type: 'text'},
				        { display: '产品编号', width: '12%', name: 'device_no', type: 'text'},
				        { display: '设备代码', width: '12%', name: 'device_code', type: 'text'},
				        { display: '应收金额', width: '10%', name: 'advance_fees', type: 'float'},
				        { display: '实收金额', width: '10%', name: 'receivable', type: 'float'},
				        { display: '收费状态', width: '8%', name: 'fee_status', type: 'text',
				            render: function (item) {
				                return render(item["fee_status"],feeStatus);
				            }
				        },
				      	{ display: '收费类型', width: '8%', name: 'advance_type', type: 'text',
				            render: function (item) {
				                return render(item["advance_type"],advanceType);
				            }
				        },
				      	{ display: '检验日期', width: '10%', name: 'advance_time', type: 'date'},
				      	{ display: '参检人员', width: '15%', name: 'check_op_name', type: 'text'},
				      	{ display: '录入人员', width: '8%', name: 'enter_op_name', type: 'text'},
				      	{ display: '录入时间', width: '10%', name: 'enter_time', type: 'date'},
				        { display: '备注', name: 'remark', width: '30%', editor: { type: 'text'}, align: 'left', maxlength:200}
			        ]
			    });
				</script>
				</form>
			</div>
			<%
		}
	%>
</body>
</html>
