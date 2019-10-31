<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page language="java" import="java.text.SimpleDateFormat" pageEncoding="utf-8" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>制造监督检验报告收费</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/payment/zzjd_payment_list.js"></script>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String pageStatus = request.getParameter("status");
%>
<script type="text/javascript">		
	var pageStatus = "${param.status}";		
	// 设备类别
	var deviceType = <u:dict code="device_classify"/>
	// 收费状态
	var feeStatus = <u:dict code="PAYMENT_STATUS"/>
	// 收费类型
	var advanceType = <u:dict code="ADVANCE_TYPE"/>
	
	$(function() {
		createInspectionInfoGrid();
		$("#form1").initForm({ //参数设置示例
			toolbar : [ {
				text : '保存',
				//id : 'save',
				icon : 'save',
				click : save
			},{
				text : '关闭',
				//id : 'close',
				icon : 'cancel',
				click : close
			}], 
			toolbarPosition: "bottom",
			getSuccess : function(resp) {
				inspectionInfoGrid.loadData({
					Rows : resp.data["inspectionZZJDPayInfoDTOList"]
				});
				$("#company_name").val(resp.com_name);	// 容器制造检验收费时，此处需手动设置制造单位名称为缴费单位（重要）
			}
		});
	})
		
	function close(){	
		api.close();
	}
			
	function save(){
		var url = "payment/payInfo/saveZZJDPayInfo.do";
		//验证表单数据
		if($('#form1').validate().form()){
			if(checkBeforeSave()){
				if(confirm("确定保存？保存后无法修改！")){
					var formData = $("#form1").getValues();
					var datas = {};
					datas = formData;
					//验证grid
					if(!validateGrid(inspectionInfoGrid)){
						return false;
					}
					
					datas["inspectionZZJDPayInfoDTOList"] = inspectionInfoGrid.getData();
					$("body").mask("正在保存数据，请稍后！");
					$.ajax({
						url: url,
					 	type: "POST",
					 	datatype: "json",
					  	contentType: "application/json; charset=utf-8",
					 	data: $.ligerui.toJSON(datas),
					   	success: function (resp) {
					    	$("body").unmask();
					      	if (resp["success"]) {
					         	$.ligerDialog.confirm('保存成功，现在打印缴费单？', function (yes)
								{
			                  		if (yes){
			                  			printPayInfo();
				           			}else{
				           				api.data.window.refreshGrid();
			               				api.close(); 
				           			}
			                	});
					      	}else{
					        	$.ligerDialog.error('提示：' + resp.msg);
					    	}
					    },
						error: function (resp) {
					    	$("body").unmask();
					    	$.ligerDialog.error('提示：' + resp.msg);
						}
					});
				}
			}
		}
	}
	
	// 打印缴费单
	function printPayInfo(){
		$.getJSON("payment/payInfo/getZZJDDetail.do?status=detail&id=${param.id}", function(resp){
			if(resp.success){
				top.$.dialog({
					width : $(top).width(),
					height :  $(top).height()-40,
					lock : true,
					title : "打印缴费单",
					parent: api,
					content : 'url:app/payment/report_zzjd_docEditor.jsp?status=modify&type=1&isPrint=2',	// type 1：缴费单 2：借条
					data: {pwindow: window, bean: resp.data}
				}).max();
			}
		})
	}
	
	// 保存前验证数据
	function checkBeforeSave(){
		var pay_type = $("input[id='pay_type-txt']").ligerComboBox().getValue();	// 收费方式 1 现金缴费 2 银行转账 3 免收费 4 现金及转账 5 POS机刷卡
		var pay_receive = $("#pay_receive").val();	// 应收总金额
		var pay_received = $("#pay_received").val();	// 实收总金额
		if("3" != pay_type){
			//var pay_man_name = $("#pay_man_name").val();	// 缴费人姓名
			var invoice_no = $("#invoice_no").val();	// 发票号
			var remark = $("#remark").val();			// 备注（银行转账/POS机刷卡金额备注）
			if(pay_received == 0){
				$.ligerDialog.alert("请输入实收总金额！");
				return false;
			}else{
				if(parseFloat(pay_received) < parseFloat(pay_receive)){
					//$.ligerDialog.alert("实收总金额不能小于应收总金额，请检查！");
					//return false;
				}else if(parseFloat(pay_received) > parseFloat(pay_receive)){
					//if("" == remark){
					//	$.ligerDialog.alert("实收总金额大于应收总金额，请输入备注！");
					//	return false;
					//}
				}
				//if("" == pay_man_name){
				//	$.ligerDialog.alert("请输入缴费人姓名！");
				//	return false;
				//}
				if("1" == pay_type){	// 现金
					$("#cash_pay").val(parseFloat(pay_received));	
				}else if("2" == pay_type){	// 转账
					if(parseFloat(remark) <= 0){	
						$.ligerDialog.alert("收费方式为‘银行转账’时，请在‘转账’里填写转账金额说明！");
						return false;
					}
				}else if("4" == pay_type){	// 现金及转账
					var cash_pay = $("#cash_pay").val();	
					if(parseFloat(cash_pay) <= 0){
						$.ligerDialog.alert("请输入现金金额！");
						return false;
					}
					if(parseFloat(remark) <= 0){
						$.ligerDialog.alert("请输入转账金额！");
						return false;
					}
					var tTotal = parseFloat(cash_pay)+parseFloat(remark);
					if(tTotal != pay_receive){
						$.ligerDialog.alert("现金金额和转账金额之和必须等于应收总金额，请检查！");
						return false;
					}
				}else if("5" == pay_type){	// pos机刷卡
					if(parseFloat(pay_received) <= 0){
						$.ligerDialog.alert("请输入实收总金额！");
						return false;
					}
				}else if("6" == pay_type){
					var cash_pay = $("#cash_pay").val();	// 现金及POS机刷卡
					if(parseFloat(cash_pay) <= 0){
						$.ligerDialog.alert("请输入现金金额！");
						return false;
					}
					if(parseFloat(remark) <= 0){
						$.ligerDialog.alert("请输入POS机刷卡金额！");
						return false;
					}
					var posTotal = parseFloat(cash_pay)+parseFloat(remark);
					if(posTotal != pay_receive){
						$.ligerDialog.alert("现金金额和POS机刷卡金额之和必须等于应收总金额，请检查！");
						return false;
					}
				}
				if("" == invoice_no){
					$.ligerDialog.alert("请输入发票号！");
					return false;
				}
			}
			
		}else{
			$("#pay_receive").val("0");	
			$("#pay_received").val("0");	
		}
		return true;
	}
	
	// 收费方式为“免收费”时，改变实收金额为0
	function change(pay_type){
		if("3" == pay_type){
			if(confirm("“免收费”操作将修改所有业务的金额为0，是否进行修改？")){
				$("#pay_receive").val("0");		// 应收总金额
				$("#pay_received").val("0");	// 实收总金额
				$("#cash_pay").val("0");		// 现金
				$("#remark").val("0");			// 转账
				/*var data = inspectionInfoGrid.getData();
				for(var i in data){
					inspectionInfoGrid.updateRow(i,{
			        	receivable: 0
			    	});	// 此方法等同于inspectionInfoGrid.updateCell("receivable",0,i);
				}*/
			}
		}else{
			var pay_receive = $("#pay_receive").val();	// 应收总金额
			if(pay_receive == 0){	
				initInfo(pay_type);
			}
			
			if("1" == pay_type){
				$("#cash_pay").val(parseFloat($("#pay_received").val()));// 收费方式为“现金”时，默认“现金”为实收总金额
				$("#remark").val("0");									// 收费方式为“现金”时，默认“转账”为0
			}else if("2" == pay_type){
				$("#cash_pay").val("0");								// 收费方式为“银行转账”时，默认“现金”为0
				$("#remark").val(parseFloat($("#pay_received").val()));	// 收费方式为“银行转账”时，默认“转账”为实收总金额
			}else if("4" == pay_type || "5" == pay_type || "6" == pay_type){	// 收费方式为“现金及转账”、“POS机刷卡”、“现金及POS机刷卡”
				$("#cash_pay").val("0");	// 默认“现金”为0，用户手动填写金额
				$("#remark").val("0");		// 默认“转账/POS机刷卡”为0，用户手动填写金额
			}
		}
	}
	
	function initInfo(pay_type){
		$.getJSON("payment/payInfo/getZZJDDetail.do?status=${param.status}&id=${param.id}", function(resp){
			if(resp.success){
				inspectionInfoGrid.loadData({
					Rows : resp.data["inspectionZZJDPayInfoDTOList"]
				});
				$("#id").val(resp.data["id"]);	// 存在收费记录时，此处需手动设置ID值（重要）
				$("#company_name").val(resp.com_name);	// 容器制造检验收费时，此处需手动设置制造单位名称为缴费单位（重要）
				$("#pay_receive").val(parseFloat(resp.data["pay_receive"]));	// 应收总金额
				$("#pay_received").val(parseFloat(resp.data["pay_receive"]));	// 实收总金额（默认值为“应收总金额”）
				if("1" == pay_type){
					$("#cash_pay").val(parseFloat(resp.data["pay_receive"]));// 收费方式为“现金”时，默认“现金”为实收总金额
					$("#remark").val("0");									// 收费方式为“现金”时，默认“转账”为0
				}else if("2" == pay_type){
					$("#cash_pay").val("0");								// 收费方式为“银行转账”时，默认“现金”为0
					$("#remark").val(parseFloat(resp.data["pay_receive"]));	// 收费方式为“银行转账”时，默认“转账”为实收总金额
				}else if("4" == pay_type || "5" == pay_type || "6" == pay_type){	// 收费方式为“现金及转账”、“POS机刷卡”、“现金及POS机刷卡”
					$("#cash_pay").val("0");	// 默认“现金”为0，用户手动填写金额
					$("#remark").val("0");		// 默认“转账/POS机刷卡”为0，用户手动填写金额
				}
				//$('#fk_company_id').val(resp.data["inspectionZZJDPayInfoDTOList"][0].com_id);	// 缴费单位ID
				//$('#company_name').val(resp.data["inspectionZZJDPayInfoDTOList"][0].com_name);	// 缴费单位名称	
			}
		})
	}
	</script>
</head>
<body <%if("add".equals(pageStatus)){%>onload="initInfo('0');"<%}%> >
<div class="navtab">
	<div title="缴费情况" tabId="payInfoTab" style="height: 400px">
	<form id="form1" getAction="payment/payInfo/getZZJDDetail.do?status=${param.status}&id=${param.id}">
		<input type="hidden" name="id" id="id"/>
		<input type="hidden" name="fk_inspection_info_id" value="${param.id}"/>
		<input type="hidden" name="pay_no" id="pay_no"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>缴费单</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">缴费单位：</td>
					<td class="l-t-td-right">
						<input name="fk_company_id" id="fk_company_id" type="hidden" />
						<input name="company_name" id="company_name" type="text" ltype='text' validate="{required:true,maxlength:200}"/>
					</td>
					<td class="l-t-td-left">收费方式：</td>
					<td class="l-t-td-right"><u:combo name="pay_type" code="PAY_TYPE" validate="required:true" attribute="onSelected:change"/></td>
					
				</tr>
				<tr>
					<td class="l-t-td-left">应收总金额：</td>
					<td class="l-t-td-right">
						<input name="pay_receive" id="pay_receive" type="text" ltype='float' ligerui="{readonly:true}"/>
					</td>
					<td class="l-t-td-left">实收总金额：</td>
					<td class="l-t-td-right">
						<input name="pay_received" id="pay_received" type="text" ltype='float' validate="{required:true,maxlength:18}"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">现金：</td>
					<td class="l-t-td-right">
						<input name="cash_pay" id="cash_pay" type="text" ltype='float' ligerui="{maxlength:18}"/>
					</td>
					<td class="l-t-td-left">转账/POS机刷卡：</td>
					<td class="l-t-td-right">
						<input name="remark" id="remark" type="text" ltype='float' ligerui="{maxlength:18}"/>
					</td>
				</tr>
				<tr>
					<!-- 
					<td class="l-t-td-left">缴费人姓名：</td>
					<td class="l-t-td-right">
						<input name="pay_man_name" id="pay_man_name" type="text" ltype='text' validate="{maxlength:20}" />
					</td>
					 -->
					<td class="l-t-td-left">缴费日期：</td>
					<td class="l-t-td-right">
						<input name="pay_date" id="pay_date" type="text" ltype='date' validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" />
					</td>
					<td class="l-t-td-left">发票号：</td>
					<td class="l-t-td-right">
						<input name="invoice_no" id="invoice_no" type="text" ltype='text' validate="maxlength:32}" />
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
			        getAction:"payment/payInfo/getInspectionZZJDInfoList.do?id=${param.id}",
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
				        { display: '安装单位', width: '20%', name: 'install_unit_name', type: 'text'},
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
</div>
</body>
</html>
