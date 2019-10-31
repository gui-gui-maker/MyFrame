<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page language="java" import="java.text.SimpleDateFormat" pageEncoding="utf-8" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>通用借票收费</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/payment/cw_bank_list.js"></script>
<script type="text/javascript">		
	var pageStatus = "${param.status}";
	var formDatas = api.data.formDatas; //前一次填写的表单信息，作用于本次填写初始化内容
	$(function() {
		createCwBankInfoGrid();		// 初始化银行转账信息
		$("#form1").initForm({ 
			toolbar : [ {
				text : '保存',
				id : 'save',
				icon : 'save',
				click : save
			},{
                text : '保存并继续',
                icon : 'save',
                click : saveAndContinue
            }, {
				text : '关闭',
				id : 'close',
				icon : 'cancel',
				click : close
			}], 
			toolbarPosition: "bottom",
			getSuccess : function(resp) {
				$("#id").val("");
				$("#fk_company_id").val(resp.data["fk_company_id"]);
				$("#company_name").val(resp.data["com_name"]);
				$("#pay_received").val(resp.data["unpay_amount"]);	// 总金额
				$("#cash_pay").val("0");		// 现金
				$("#remark").val("0");			// 转账
				$("#pos").val("0");				// POS机刷卡
				$("#hand_in").val("0");
				$("#draft").val("0");
				$("#pay_date").val(resp.data["borrow_date"].substring(0,10));	// 开票日期
				debugger;
				if(formDatas!='' && typeof(formDatas)!='undefined'){
                    $('#report_com_id').val(formDatas["report_com_id"]);	// 受检单位ID
                    $('#report_com_name').val(formDatas["report_com_name"]);// 受检单位名称
					$("#report_count").val(formDatas["report_count"]);
				}
			}
		});
	})

	function saveAndContinue(){
		save(true);
	}
	function close(){	
		api.close();
	}
			
	function save(isContinue){
		var url = "payment/payInfo/savePayCY1.do?status=${param.status}&borrow_id=${param.id}&type=borrowPay";
		var invoice_no = $("#invoice_no").val();	// 发票号
		if("" == invoice_no){
			$.ligerDialog.alert("请输入发票号！");
			return;
		}else{
			invoice_no = invoice_no.trim();	// 去空格
			if(invoice_no.length == 10){
				invoice_no = invoice_no.substr(0,(invoice_no.length-1));	// 截最后一位
				$("#invoice_no").val(invoice_no);
			}
			if(checkBeforeSave()){
				//验证表单数据
				if($('#form1').validate().form()){
					if(confirm("确定保存？保存后无法修改！")){
						$("#save").attr("disabled","disabled");
						var formData = $("#form1").getValues();
						var datas = {};
						datas = formData;
						//验证grid
						if(!validateGrid(cwBankInfoGrid)){
							return false;
						}
						datas["cwBankDTOList"] = cwBankInfoGrid.getData();
						var pay_type = $("input[id='pay_type-txt']").ligerComboBox().getValue();
						if("2"==pay_type || "4"==pay_type || "7"==pay_type){
							if(cwBankInfoGrid.getData()=="" || cwBankInfoGrid.getData() == null){
								$("#save").removeAttr("disabled");
								$.ligerDialog.alert("亲，转账业务请点击“转账”按钮，选择银行转账记录！");
								return;
							}else{
								// 验证银行转账本次收费金额合计是否等于实收总金额
								var totalMoney = 0;		// 银行转账本次收费金额合计
								var remark = $("#remark").val();	// 转账总金额
								var data = cwBankInfoGrid.getData();
								for(var item in data){
									totalMoney += parseFloat(data[item].usedMoney);											
								}
								if(parseFloat(totalMoney) != parseFloat(remark)){
									$("#save").removeAttr("disabled");
									$.ligerDialog.alert("亲，银行转账中的“本次收费金额”不等于缴费单中的“转账”金额，请检查这两项是否填写正确！");
									return;
								}
							}
						}
						$("body").mask("正在保存数据，请稍后！");
						$.ajax({
							url: url,
						 	type: "POST",
						 	datatype: "json",
						  	contentType: "application/json; charset=utf-8",
						 	data: $.ligerui.toJSON(datas),
						   	success: function (resp) {
						    	$("body").unmask();
                                if ( resp["success"] ) {
                                    if(isContinue){
                                        api.data.window.doPaymentAgain(api.data.remaining,$.ligerui.toJSON(datas));
                                        api.close();
									}else{
                                        $.ligerDialog.confirm('保存成功，现在打印缴费单？', function (yes) {
                                            if ( yes ) {
                                                printPayInfo(resp["data"]);
                                            } else {
                                                api.data.window.refreshGrid();
                                                api.close();
                                            }
                                        });
									}
                                } else {
                                    $("#save").removeAttr("disabled");
                                    $.ligerDialog.error(resp.msg);
                                }
						    },
							error: function (resp) {
						    	$("body").unmask();
						    	$("#save").removeAttr("disabled");
						    	$.ligerDialog.error(resp.msg);
							}
						});
					}
				}
			}
		}
	}
	
	// 打印缴费单
	function printPayInfo(id){
		top.$.dialog({
			width : 750, 
			height : 750, 
			lock : true, 
			title:"打印缴费单",
			content: 'url:payment/payInfo/doBatchPrintCY.do?id='+id+'&type=1&type_1=borrow',	
			data : {"pwindow" : window}
		})
	}
	
	// 保存前验证数据
	function checkBeforeSave(){
		// 收费方式 1 现金缴费 2 银行转账 3 免收费 4 现金及转账 5 POS机刷卡 6 现金及POS机刷卡 7 转账及POS机刷卡
		var pay_type = $("input[id='pay_type-txt']").ligerComboBox().getValue();	
		var pay_receive = $("#pay_receive").val();	// 应收总金额
		var pay_received = $("#pay_received").val();	// 实收总金额
		if("3" != pay_type){
			//var pay_man_name = $("#pay_man_name").val();	// 缴费人姓名
			var remark = $("#remark").val();			// 备注（银行转账金额备注）
			if(pay_received == 0){
				$.ligerDialog.alert("请输入总金额！");
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
				if("1" == pay_type){
					$("#cash_pay").val(parseFloat(pay_received));	// 现金
				}else if("2" == pay_type){
					if(parseFloat(remark) <= 0){
						$.ligerDialog.alert("收费方式为‘银行转账’时，请在‘转账’里填写转账金额说明！");
						return false;
					}
				}else if("4" == pay_type){
					var cash_pay = $("#cash_pay").val();	// 现金
					if(parseFloat(cash_pay) <= 0){
						$.ligerDialog.alert("请输入现金金额！");
						return false;
					}
					if(parseFloat(remark) <= 0){
						$.ligerDialog.alert("请输入转账金额！");
						return false;
					}
					if(parseFloat(cash_pay)+parseFloat(remark)!=parseFloat(pay_received)){
						$.ligerDialog.alert("亲，现金、转账之和不等于总金额，请检查！");
						return false;
					}
				}else if("6" == pay_type){	// 现金及POS机刷卡
					var cash_pay = $("#cash_pay").val();	
					if(parseFloat(cash_pay) <= 0){
						$.ligerDialog.alert("请输入现金金额！");
						return false;
					}
					var pos = $("#pos").val();	
					if(parseFloat(pos) <= 0){
						$.ligerDialog.alert("请输入POS机刷卡金额！");
						return false;
					}
					if(parseFloat(cash_pay)+parseFloat(pos)!=parseFloat(pay_received)){
						$.ligerDialog.alert("亲，现金、POS之和不等于总金额，请检查！");
						return false;
					}
				}else if("7" == pay_type){	// 转账及POS机刷卡
					if(parseFloat(remark) <= 0){
						$.ligerDialog.alert("请输入转账金额！");
						return false;
					}
					var pos = $("#pos").val();	
					if(parseFloat(pos) <= 0){
						$.ligerDialog.alert("请输入POS机刷卡金额！");
						return false;
					}
					if(parseFloat(remark)+parseFloat(pos)!=parseFloat(pay_received)){
						$.ligerDialog.alert("亲，转账、POS之和不等于总金额，请检查！");
						return false;
					}
				}else if("8" == pay_type){
					$("#hand_in").val(parseFloat(pay_received));	// 上缴财政
				}else if("9" == pay_type){
					$("#draft").val(parseFloat(pay_received));		// 承兑汇票
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
				$("#pos").val("0");				// POS机刷卡
				$("#hand_in").val("0");			// 上缴财政
				$("#draft").val("0");			// 承兑汇票
				cwBankInfoGrid.loadData({
					Rows : []
				});
			}
		}else{
			var pay_receive = $("#pay_receive").val();	// 应收总金额
			if(pay_receive == 0){	
				initInfo(pay_type);
			}
			if("1" == pay_type){
				$("#cash_pay").val(parseFloat($("#pay_received").val()));	// 收费方式为“现金”时，默认“现金”为实收总金额
				$("#remark").val("0");										// 收费方式为“现金”时，默认“转账”为0
				$("#pos").val("0");			// 收费方式为“现金”时，默认“pos机刷卡”为0
				$("#hand_in").val("0");			// 上缴财政
				$("#draft").val("0");
				cwBankInfoGrid.loadData({
					Rows : []
				});
				//$("#cwBankInfoList").find("tbody").children().remove();
			}else if("2" == pay_type){
				$("#cash_pay").val("0");									// 收费方式为“银行转账”时，默认“现金”为0
				$("#remark").val(parseFloat($("#pay_received").val()));		// 收费方式为“银行转账”时，默认“转账”为实收总金额
				$("#pos").val("0");		// 收费方式为“银行转账”时，默认“pos机刷卡”为0
				$("#hand_in").val("0");			// 上缴财政
				$("#draft").val("0");
			}else if("4" == pay_type || "6" == pay_type || "7" == pay_type){	// 收费方式为“现金及转账”、“现金及POS机刷卡”、“转账及POS机刷卡”
				$("#cash_pay").val("0");// 默认“现金”为0，用户手动填写金额
				$("#remark").val("0");	// 默认“转账”为0，用户手动填写金额
				$("#pos").val("0");	
				$("#hand_in").val("0");			// 上缴财政
				$("#draft").val("0");
				if("5" == pay_type || "6" == pay_type){
					cwBankInfoGrid.loadData({
						Rows : []
					});
					//$("#cwBankInfoList").find("tbody").children().remove();
				}
			}else if("5" == pay_type){
				$("#cash_pay").val("0");
				$("#remark").val("0");	// 收费方式为“转账及pos机刷卡”时，默认“转账”为0
				$("#pos").val(parseFloat($("#pay_received").val()));		// 收费方式为“转账及pos机刷卡”时，默认“pos机刷卡”为0
				$("#hand_in").val("0");			// 上缴财政
				$("#draft").val("0");
			}else if("8" == pay_type){
				$("#cash_pay").val("0");	// 收费方式为“现金”时，默认“现金”为实收总金额
				$("#remark").val("0");		// 收费方式为“现金”时，默认“转账”为0
				$("#pos").val("0");	
				$("#hand_in").val(parseFloat($("#pay_received").val()));	// 上缴财政
				$("#draft").val("0");
				cwBankInfoGrid.loadData({
					Rows : []
				});
			}else if("9" == pay_type){
				$("#draft").val(parseFloat($("#pay_received").val()));	// 收费方式为“承兑汇票”时，默认“承兑汇票”为实收总金额
				$("#hand_in").val("0");	
				$("#cash_pay").val("0");		
				$("#remark").val("0");		
				$("#pos").val("0");			
				cwBankInfoGrid.loadData({
					Rows : []
				});
			}
		}
	}
	
	// 选择受检单位
	function selectReportCompany(){	
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择受检单位",
			content: 'url:app/payment/choose_report_com_list.jsp',
			data : {"parentWindow" : window}
		});
	}
	
	function callBackReport(id,name){
		$('#report_com_id').val(id);	// 受检单位ID
		$('#report_com_name').val(name);// 受检单位名称	
	}	
			
	// 选择开票单位
	function selectPayCompany(){	
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择开票单位",
			content: 'url:app/payment/choose_company_list.jsp',
			data : {"parentWindow" : window}
		});
	}
	
	function callBack(id,name){
		$('#fk_company_id').val(id);	// 开票单位ID
		$('#company_name').val(name);	// 开票单位名称	
	}		
	
	// 选择转账记录
	function selectTransfer(){	
		// 收费方式 1 现金缴费 2 银行转账 3 免收费 4 现金及转账 5 POS机刷卡 6 现金及POS机刷卡 7 转账及POS机刷卡
		var pay_type = $("input[id='pay_type-txt']").ligerComboBox().getValue();	
		if(pay_type==""){
			$.ligerDialog.alert("亲，请先选择“收费方式”！");
			return;
		}else{
			if("2"==pay_type || "4"==pay_type || "7"==pay_type){
				top.$.dialog({
					parent: api,
					width : 800, 
					height : 550, 
					lock : true, 
					title:"选择转账记录",
					content: 'url:app/payment/choose_transfer_list.jsp',
					data : {"parentWindow" : window}
				});
			}else{
				$.ligerDialog.alert("亲，您选择的“收费方式”未涉及到转账业务哦，请核实！");
				return;
			}
		}
	}
	
	function callBackTransfer(ids){
		// 验证所选银行转账记录是否重复
		var data = cwBankInfoGrid.getData();
		for(var item in data){
			if(ids.indexOf(data[item].id)!=-1){
				$.ligerDialog.alert("亲，不能选择重复的“银行转账”哦，请核实！");
				return;
			}
		}
		
		var useMoney = $("#remark").val();
		$.post("cw/bank/queryBankInfos.do?ids="+ids+"&useMoney="+useMoney, function(resp) {
			if (resp.success) {
				cwBankInfoGrid.addRows(resp.list);
				/*
				cwBankInfoGrid.loadData({
					Rows : data
				});
				*/
			}else{
				$.ligerDialog.error(resp.msg);
			}
		});
	}	
	
	function setTransfer(){
		var pay_received = $("#pay_received").val();	// 总金额
		$("#remark").val(pay_received);		
	}	
	
	function changeFlag(flag){
		if(flag=="1"){
			cwBankInfoGrid.loadData({
				Rows : []
			});
		}else if(flag=="0") {
			$.post("payment/payInfo/getBankInfos.do?id=${param.id}", function(resp) {
				if (resp.success) {
					cwBankInfoGrid.addRows(resp.data);
				}else{
					$.ligerDialog.error(resp.msg);
				}
			});
		}
	}
	
	String.prototype.trim = function() {
    	return this.replace(/(^\s*)|(\s*$)/g,'');
	}
	</script>
</head>
<body>
<div class="navtab">
	<div title="缴费情况" tabId="payInfoTab" style="height: 400px">
	<form id="form1" getAction="report/borrow/getCYDetail.do?status=${param.status}&id=${param.id}">
		<input type="hidden" name="id" id="id"/>
		<input type="hidden" name="fk_inspection_info_id" value=""/>
		<input type="hidden" name="payment_status" id="payment_status"/>
		<input type="hidden" name="created_by" id="created_by"/>
		<input type="hidden" name="created_date" id="created_date"/>
		<input type="hidden" name="device_type" id="device_type" value="CY"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>缴费单</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">受检单位：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="report_com_id" id="report_com_id" type="hidden" />
						<input name="report_com_name" id="report_com_name" type="text" ltype='text' validate="{required:true,maxlength:200}" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectReportCompany()}}]}"/><!--  onclick="selectPayCompany()" -->
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">开票单位：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="fk_company_id" id="fk_company_id" type="hidden" />
						<input name="company_name" id="company_name" type="text" ltype='text' validate="{required:true,maxlength:200}" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectPayCompany()}}]}"/><!--  onclick="selectPayCompany()" -->
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">合同号/编号：</td>
					<td class="l-t-td-right">
						<input name="pay_no" id="pay_no" type="text" ltype='text' validate="{maxlength:20}" />
					</td>
					<td class="l-t-td-left">检验部门：</td>
					<td class="l-t-td-right">
						<input type="text" name="check_dep_id" id="check_dep_id" ltype="select" validate="{required:true}" ligerui="{
							initValue:'',
							tree:{checkbox:false,data: <u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' or ORG_CODE = 'shxmb' order by orders"/>}
							}"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">收费方式：</td>
					<td class="l-t-td-right"><u:combo name="pay_type" code="PAY_TYPE" validate="required:true" attribute="onSelected:change"/></td>
					<td class="l-t-td-left">数量：</td>
					<td class="l-t-td-right">
						<input name="report_count" id="report_count" type="text" ltype='spinner' validate="{required:true,digits:true,maxlength:32}" />
					</td>
				</tr>
				<tr>	
					<td class="l-t-td-left">总金额：</td>
					<td class="l-t-td-right">
						<input name="pay_received" id="pay_received" type="text" ltype='float' ligerui="{value:'0',maxlength:18}" onkeyup = "setTransfer()"/>
					</td>
					<td class="l-t-td-left">现金：</td>
					<td class="l-t-td-right">
						<input name="cash_pay" id="cash_pay" type="text" ltype='float' ligerui="{value:'0',maxlength:18}"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">转账：</td>
					<td class="l-t-td-right">
						<input type="hidden" name="fk_cw_bank_id" id="fk_cw_bank_id"/>
						<input name="remark" id="remark" type="text" ltype='float' ligerui="{maxlength:18, value:'0',iconItems:[{icon:'add',click:function(){selectTransfer()}}]}"/><!-- onclick="selectTransfer()"  -->
					</td>
					<td class="l-t-td-left">POS机刷卡：</td>
					<td class="l-t-td-right">
						<input name="pos" id="pos" type="text" ltype='float' ligerui="{value:'0',maxlength:18}"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">上缴财政：</td>
					<td class="l-t-td-right">
						<input name="hand_in" id="hand_in" type="text" ltype='float' ligerui="{maxlength:18}" onchange="changeMoney('0')"/>
					</td>
					<td class="l-t-td-left">承兑汇票：</td>
					<td class="l-t-td-right">
						<input name="draft" id="draft" type="text" ltype='float' ligerui="{maxlength:18}" onchange="changeMoney('0')"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">发票号：</td>
					<td class="l-t-td-right">
						<input name="invoice_no" id="invoice_no" type="text" ltype='text' validate="{required:true,maxlength:10}" readonly="readonly"/> 
					</td>
					<td class="l-t-td-left">开票日期：</td>
					<td class="l-t-td-right">
						<input ligerui="{disabled:true}" readonly="readonly" name="pay_date" id="pay_date" type="text" ltype='date' validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}" ligerui="{disabled:true}"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">销票日期：</td>
					<td class="l-t-td-right">
						<input name="pay_date1" id="pay_date1" type="text" ltype='date' validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>"/>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>银行转账</div>
			</legend>
			<div id="cwBankInfoList"></div>
		</fieldset>
	</form>
	</div>
</div>
</body>
</html>
