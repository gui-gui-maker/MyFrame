<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page language="java" import="java.text.SimpleDateFormat" pageEncoding="utf-8" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>收费</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/payment/payment_list.js"></script>
<script type="text/javascript" src="app/payment/cw_bank_list.js"></script>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	
	String pageStatus = request.getParameter("status");
%>
<script type="text/javascript">		
	var pageStatus = "${param.status}";		
	// 检验类别
	var checkType = <u:dict code="BASE_INSPECT_TYPE"/>
	// 设备类别
	var deviceType = <u:dict code="device_classify"/>
	// 收费状态
	var feeStatus = <u:dict code="PAYMENT_STATUS"/>
	// 收费类型
	var advanceType = <u:dict code="ADVANCE_TYPE"/>
	
	$(function() {
		createInspectionInfoGrid();	// 初始化检验业务信息
		createCwBankInfoGrid();		// 初始化银行转账信息
		$("#form1").initForm({ 
			toolbar : [ {
				text : '保存',
				id : 'save',
				icon : 'save',
				click : save
			},{
				text : '关闭',
				id : 'close',
				icon : 'cancel',
				click : close
			}], 
			toolbarPosition: "bottom",
			getSuccess : function(resp) {				
				inspectionInfoGrid.loadData({
					Rows : resp.data["inspectionInfoDTOList"]
				});
				cwBankInfoGrid.loadData({
					Rows : resp.data["cwBankDTOList"]
				});
				$("#payinfo_id").val(resp.data["id"]);	// 存在收费记录时，此处需手动设置ID值（重要）
				$("#pay_receive").val(parseFloat(resp.data["pay_receive"]));	// 应收总金额
				$("#pay_received").val(parseFloat(resp.data["pay_received"]));	// 实收总金额（默认值为“应收总金额”）
				var pay_type = resp.data["pay_type"]+"";
				if("1" == pay_type){
					$("#cash_pay").val(parseFloat(resp.data["pay_received"]));// 收费方式为“现金”时，默认“现金”为实收总金额
					$("#remark").val("0");		
					$("#pos").val("0");		
					$("#hand_in").val("0");		
					$("#draft").val("0");		
				}else if("2" == pay_type){
					$("#cash_pay").val("0");								// 收费方式为“银行转账”时，默认“现金”为0
					$("#remark").val(parseFloat(resp.data["pay_received"]));	// 收费方式为“银行转账”时，默认“转账”为实收总金额
					$("#pos").val("0");		
					$("#hand_in").val("0");		
					$("#draft").val("0");		
				}else if("4" == pay_type ){	// 收费方式为“现金及转账”
					$("#cash_pay").val(parseFloat(resp.data["cash_pay"]));
					$("#remark").val(parseFloat(resp.data["remark"]));		
					$("#pos").val("0");		
					$("#hand_in").val("0");	
					$("#draft").val("0");		
				}else if("5" == pay_type){	// 收费方式为“POS机刷卡”
					$("#pos").val(parseFloat(resp.data["pay_received"]));		
					$("#cash_pay").val("0");	
					$("#remark").val("0");	
					$("#hand_in").val("0");	
					$("#draft").val("0");		
				}else if("6" == pay_type){	// 收费方式为“现金及POS机刷卡”
					$("#cash_pay").val(parseFloat(resp.data["cash_pay"]));	
					$("#pos").val(parseFloat(resp.data["pos"]));	
					$("#remark").val("0");		
					$("#hand_in").val("0");	
					$("#draft").val("0");		
				}else if("7" == pay_type){
					$("#cash_pay").val("0");	
					$("#pos").val(parseFloat(resp.data["pos"]));		// 收费方式为“转账及pos机刷卡”时，默认“pos机刷卡”为0
					$("#remark").val(parseFloat(resp.data["remark"]));	// 收费方式为“转账及pos机刷卡”时，默认“转账”为0
					$("#hand_in").val("0");
					$("#draft").val("0");		
				}else if("8" == pay_type){
					$("#hand_in").val(parseFloat(resp.data["pay_received"]));
					$("#cash_pay").val("0");	
					$("#remark").val("0");		
					$("#pos").val("0");		
					$("#draft").val("0");		
				}else if("9" == pay_type){
					$("#draft").val(parseFloat(resp.data["pay_received"]));
					$("#cash_pay").val("0");	
					$("#remark").val("0");		
					$("#pos").val("0");		
					$("#hand_in").val("0");		
				}
			}
		});
	})
		
	function close(){	
		api.close();
	}
			
	function save(){
		var bank_change = '0';
		var if_del_invoice = '0';
		if('${param.status}' == 'modify'){
			bank_change = $('#bank_change').ligerGetRadioGroupManager().getValue();
			if_del_invoice = $('#if_del_invoice').ligerGetRadioGroupManager().getValue();
		}
		var pay_type = $("input[id='pay_type-txt']").ligerComboBox().getValue();	
		var url = "payment/payInfo/savePayInfo2.do?status=${param.status}&bank_change="+bank_change+"&if_del_invoice="+if_del_invoice;
		var invoice_no = $("#invoice_no").val();	// 发票号
		if(pay_type!="3"){
			if("" == invoice_no){
				$.ligerDialog.alert("请输入发票号！");
				return;
			}else{
				invoice_no = invoice_no.trim();	// 去空格
				if(invoice_no.length == 10){
					invoice_no = invoice_no.substr(0,(invoice_no.length-1));	// 截最后一位
					$("#invoice_no").val(invoice_no);
				}
				$.getJSON("payment/payInfo/validateInvoices.do?invoice_no="+invoice_no, function(resp){
					if(!resp.success){
						$.ligerDialog.alert("亲，系统未能识别发票号（"+invoice_no+"），请核实是否已作废！确认未作废请联系财务人员导入！", "提示");
						return;
					}else{
						if(checkBeforeSave()){
							//验证表单数据
							if($('#form1').validate().form()){
								if(confirm("确定保存？保存后无法修改！")){
									$("#save").attr("disabled","disabled");
									var formData = $("#form1").getValues();
									var datas = {};
									datas = formData;
									//验证grid
									if(!validateGrid(inspectionInfoGrid)){
										return false;
									}
									//验证grid
									if(!validateGrid(cwBankInfoGrid)){
										return false;
									}
									datas["inspectionInfoDTOList"] = inspectionInfoGrid.getData();
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
												$.ligerDialog.alert("亲，银行转账中“本次收费金额”不等于缴费单中的“转账”金额，请检查这两项是否填写正确！");
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
				})
			}
		}else{
			if(checkBeforeSave()){
				//验证表单数据
				if($('#form1').validate().form()){
					if(confirm("确定保存？保存后无法修改！")){
						$("#save").attr("disabled","disabled");
						var formData = $("#form1").getValues();
						var datas = {};
						datas = formData;
						//验证grid
						if(!validateGrid(inspectionInfoGrid)){
							return false;
						}
						//验证grid
						if(!validateGrid(cwBankInfoGrid)){
							return false;
						}
						datas["inspectionInfoDTOList"] = inspectionInfoGrid.getData();
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
									$.ligerDialog.alert("亲，银行转账中“本次收费金额”不等于缴费单中的“转账”金额，请检查这两项是否填写正确！");
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
	function printPayInfo(){
		/*
		$.getJSON("payment/payInfo/getDetail.do?status=detail&id=${param.id}", function(resp){
			if(resp.success){
				top.$.dialog({
					width : $(top).width(),
					height :  $(top).height()-40,
					lock : true,
					title : "打印缴费单",
					parent: api,
					content : 'url:app/payment/docEditor.jsp?status=modify&type=1&isPrint=2',	// type 1：缴费单 2：借条
					data: {pwindow: window, bean: resp.data}
				}).max();
			}
		})*/
		top.$.dialog({
			width : 750, 
			height : 750, 
			lock : true, 
			title:"打印缴费单",
			content: 'url:payment/payInfo/doPrint.do?id=${param.info_id}&type=1',	// 1 收费打印 2 已收费补打
			data : {"pwindow" : window}
		})
	}
	
	// 保存前验证数据
	function checkBeforeSave(){
		// 收费方式 1 现金缴费 2 银行转账 3 免收费 4 现金及转账 5 POS机刷卡 6 现金及POS机刷卡 7 转账及POS机刷卡 8 上缴财政 9 承兑汇票
		var pay_type = $("input[id='pay_type-txt']").ligerComboBox().getValue();	
		var pay_receive = $("#pay_receive").val();	// 应收总金额
		var pay_received = $("#pay_received").val();	// 实收总金额
		if("3" != pay_type){
			//var pay_man_name = $("#pay_man_name").val();	// 缴费人姓名
			var remark = $("#remark").val();			// 备注（银行转账金额备注）
			if(pay_received == 0){
				$.ligerDialog.alert("实收总金额为0，请核实！");
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
				/*var data = inspectionInfoGrid.getData();
				for(var i in data){
					inspectionInfoGrid.updateRow(i,{
			        	receivable: 0
			    	});	// 此方法等同于inspectionInfoGrid.updateCell("receivable",0,i);
				}*/
				cwBankInfoGrid.loadData({
					Rows : []
				});
				//$("#cwBankInfoList").find("tbody").children().remove();
			}
		}else{
			var pay_received = $("#pay_received").val();	// 实收总金额
			if(pay_received == 0){	
				initInfo(pay_type);
			}
			if("1" == pay_type){
				$("#cash_pay").val(parseFloat($("#pay_received").val()));	// 收费方式为“现金”时，默认“现金”为实收总金额
				$("#remark").val("0");		// 收费方式为“现金”时，默认“转账”为0
				$("#pos").val("0");			// 收费方式为“现金”时，默认“pos机刷卡”为0
				$("#hand_in").val("0");		// 上缴财政
				$("#draft").val("0");		// 承兑汇票
				cwBankInfoGrid.loadData({
					Rows : []
				});
				//$("#cwBankInfoList").find("tbody").children().remove();
			}else if("2" == pay_type){
				$("#cash_pay").val("0");	// 收费方式为“银行转账”时，默认“现金”为0
				$("#remark").val(parseFloat($("#pay_received").val()));		// 收费方式为“银行转账”时，默认“转账”为实收总金额
				$("#pos").val("0");			// 收费方式为“银行转账”时，默认“pos机刷卡”为0
				$("#hand_in").val("0");		// 上缴财政
				$("#draft").val("0");		// 承兑汇票
			}else if("4" == pay_type || "5" == pay_type || "6" == pay_type){	// 收费方式为“现金及转账”、“POS机刷卡”、“现金及POS机刷卡”
				$("#cash_pay").val("0");// 默认“现金”为0，用户手动填写金额
				$("#remark").val("0");	// 默认“转账”为0，用户手动填写金额
				$("#hand_in").val("0");		// 上缴财政
				$("#draft").val("0");		// 承兑汇票
				if("5" == pay_type || "6" == pay_type){
					cwBankInfoGrid.loadData({
						Rows : []
					});
					if("5" == pay_type){
						$("#pos").val(parseFloat($("#pay_received").val()));	// 收费方式为“POS机刷卡”时，默认“POS机刷卡”为实收总金额
					}else if("6" == pay_type){
						$("#remark").val("0");		// 收费方式为“现金及POS机刷卡”时，默认“转账”为0
					}
					//$("#cwBankInfoList").find("tbody").children().remove();
				}else if("4" == pay_type){
					$("#pos").val("0");		// 收费方式为“现金及转账”时，默认“POS机刷卡”为0
				}
			}else if("7" == pay_type){
				$("#cash_pay").val("0");// 收费方式为“转账及pos机刷卡”时，默认“现金”为0
				$("#pos").val("0");		// 收费方式为“转账及pos机刷卡”时，默认“pos机刷卡”为0
				$("#remark").val("0");	// 收费方式为“转账及pos机刷卡”时，默认“转账”为0
				$("#hand_in").val("0");		// 上缴财政
				$("#draft").val("0");		// 承兑汇票
			}else if("8" == pay_type){
				$("#hand_in").val(parseFloat($("#pay_received").val()));	// 收费方式为“上缴财政”时，默认“上缴财政”为实收总金额
				$("#remark").val("0");		// 收费方式为“现金”时，默认“转账”为0
				$("#pos").val("0");			// 收费方式为“现金”时，默认“pos机刷卡”为0
				$("#cash_pay").val("0");	// 上缴财政
				$("#draft").val("0");		// 承兑汇票
				cwBankInfoGrid.loadData({
					Rows : []
				});
			}else if("9" == pay_type){
				$("#draft").val(parseFloat($("#pay_received").val()));	// 收费方式为“承兑汇票”时，默认“承兑汇票”为实收总金额
				$("#remark").val("0");		// 收费方式为“现金”时，默认“转账”为0
				$("#pos").val("0");			// 收费方式为“现金”时，默认“pos机刷卡”为0
				$("#cash_pay").val("0");	// 现金
				$("#hand_in").val("0");		// 上缴财政
				cwBankInfoGrid.loadData({
					Rows : []
				});
			}
		}
	}
			
	// 选择缴费单位
	function selectPayCompany(){	
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择缴费单位",
			content: 'url:app/payment/choose_company_list.jsp?com_ids=${param.company_ids}',
			data : {"parentWindow" : window}
		});
	}
	
	function callBack(id,name){
		$('#fk_company_id').val(id);	// 缴费单位ID
		$('#company_name').val(name);	// 缴费单位名称	
	}	
	
	
	// 选择合同
	function selectPact(){	
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择缴费单位",
			content: 'url:app/pact/choose_pact_list.jsp?org_id=${param.org_id}',
			data : {"parentWindow" : window}
		});
	}
	
	function callBackPact(id,name){
		$('#pay_no').val(id);	// 缴费单位ID
		$('#pact_name').val(name);	// 缴费单位名称	
	}
	
	function initInfo(){
		$.getJSON("payment/payInfo/getDetail.do?status=${param.status}&id=${param.info_id}", function(resp){
			if(resp.success){
				$("#form1").setValues(resp.data);
				inspectionInfoGrid.loadData({
					Rows : resp.data["inspectionInfoDTOList"]
				});
				cwBankInfoGrid.loadData({
					Rows : resp.data["cwBankDTOList"]
				});
				$("#payinfo_id").val(resp.data["id"]);	// 存在收费记录时，此处需手动设置ID值（重要）
				$("#pay_receive").val(parseFloat(resp.data["pay_receive"]));	// 应收总金额
				$("#pay_received").val(parseFloat(resp.data["pay_received"]));	// 实收总金额
				var pay_type = resp.data["pay_type"];
				if("1" == pay_type){
					$("#cash_pay").val(parseFloat(resp.data["pay_received"]));// 收费方式为“现金”时，默认“现金”为实收总金额
					$("#remark").val("0");		
					$("#pos").val("0");		
					$("#hand_in").val("0");
					$("#draft").val("0");		// 承兑汇票
				}else if("2" == pay_type){
					$("#cash_pay").val("0");									// 收费方式为“银行转账”时，默认“现金”为0
					$("#remark").val(parseFloat(resp.data["pay_received"]));	// 收费方式为“银行转账”时，默认“转账”为实收总金额
					$("#pos").val("0");		
					$("#hand_in").val("0");
					$("#draft").val("0");		// 承兑汇票
				}else if("4" == pay_type ){		// 收费方式为“现金及转账”
					$("#cash_pay").val(parseFloat(resp.data["cash_pay"]));
					$("#remark").val(parseFloat(resp.data["remark"]));		
					$("#pos").val("0");		
					$("#hand_in").val("0");
					$("#draft").val("0");		// 承兑汇票
				}else if("5" == pay_type){		// 收费方式为“POS机刷卡”
					$("#pos").val(parseFloat(resp.data["pay_received"]));		
					$("#cash_pay").val("0");	
					$("#remark").val("0");	
					$("#hand_in").val("0");
					$("#draft").val("0");		// 承兑汇票
				}else if("6" == pay_type){		// 收费方式为“现金及POS机刷卡”
					$("#cash_pay").val(parseFloat(resp.data["cash_pay"]));	
					$("#pos").val(parseFloat(resp.data["pos"]));	
					$("#remark").val("0");	
					$("#hand_in").val("0");
					$("#draft").val("0");		// 承兑汇票
				}else if("7" == pay_type){
					$("#cash_pay").val("0");	
					$("#pos").val(parseFloat(resp.data["pos"]));		// 收费方式为“转账及pos机刷卡”时，默认“pos机刷卡”为0
					$("#remark").val(parseFloat(resp.data["remark"]));	// 收费方式为“转账及pos机刷卡”时，默认“转账”为0
					$("#hand_in").val("0");
					$("#draft").val("0");		// 承兑汇票
				}else if("8" == pay_type){
					$("#hand_in").val(parseFloat(resp.data["pay_received"]));
					$("#cash_pay").val("0");	
					$("#remark").val("0");		
					$("#pos").val("0");	
					$("#draft").val("0");		// 承兑汇票
				}else if("9" == pay_type){
					$("#draft").val(parseFloat(resp.data["pay_received"]));
					$("#cash_pay").val("0");	
					$("#remark").val("0");		
					$("#pos").val("0");	
					$("#hand_in").val("0");		
				}
			}
		})
	}
	
	// 选择转账记录
	function selectTransfer(){	
		// 收费方式 1 现金缴费 2 银行转账 3 免收费 4 现金及转账 5 POS机刷卡 6 现金及POS机刷卡 7 转账及POS机刷卡  8 上缴财政 9 承兑汇票
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
	
	function changeFlag(flag){
		if(flag=="1"){
			cwBankInfoGrid.loadData({
				Rows : []
			});
		}else if(flag=="0") {
			$.post("payment/payInfo/getBankInfos2.do?id=${param.info_id}", function(resp) {
				if (resp.success) {
					cwBankInfoGrid.addRows(resp.data);
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
	}
	
	function changeMoney(type){
		// 收费方式 1 现金缴费 2 银行转账 3 免收费 4 现金及转账 5 POS机刷卡 6 现金及POS机刷卡 7 转账及POS机刷卡
		var pay_type = $("input[id='pay_type-txt']").ligerComboBox().getValue();	
		var pay_received = $("#pay_received").val();	// 实收总金额
		if("4" == pay_type){
			//$("#cash_pay").val("0");
			//$("#remark").val("0");
			if("1"==type){
				var cash_pay = $("#cash_pay").val();// 现金金额
				$("#remark").val(parseFloat(pay_received)-parseFloat(cash_pay));
			}else if("2"==type){
				var remark = $("#remark").val();	// 转账金额
				$("#cash_pay").val(parseFloat(pay_received)-parseFloat(remark));
			}
		}else if("6" == pay_type){
			if("1"==type){
				var cash_pay = $("#cash_pay").val();// 现金金额
				$("#pos").val(parseFloat(pay_received)-parseFloat(cash_pay));
			}else if("3"==type){
				var pos = $("#pos").val();	// POS金额
				$("#cash_pay").val(parseFloat(pay_received)-parseFloat(pos));
			}
		}else if("7" == pay_type){
			if("2"==type){
				var remark = $("#remark").val();	// 转账金额
				$("#pos").val(parseFloat(pay_received)-parseFloat(remark));
			}else if("3"==type){
				var pos = $("#pos").val();	// POS金额
				$("#remark").val(parseFloat(pay_received)-parseFloat(pos));
			}
		}
	}
		
	
	String.prototype.trim = function() {
    	return this.replace(/(^\s*)|(\s*$)/g,'');
	}
	</script>
</head>
<body onload="initInfo();">
<div class="navtab">
	<div title="缴费情况" tabId="payInfoTab" style="height: 400px">
	<form id="form1" >
		<input type="hidden" name="id" id="payinfo_id"/>
		<input type="hidden" name="fk_inspection_info_id"/>
		<input type="hidden" name="pay_no" id="pay_no"/>
		<input type="hidden" name="payment_status" id="payment_status"/>
		<input type="hidden" name="created_by" id="created_by"/>
		<input type="hidden" name="created_date" id="created_date"/>
		<input type="hidden" name="receive_man_name" id="receive_man_name" <%if("add".equals(pageStatus)){%>value="<%=user.getName() %>"<%}%> />
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>缴费单</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
			<c:choose>
					<c:when test='${param.org_id=="100036"}'>
					
						<tr>
							<td class="l-t-td-left">缴费单位：</td>
							<td class="l-t-td-right" >
								<input name="fk_company_id" id="fk_company_id" type="hidden" />
								<input name="company_name" id="company_name" type="text" ltype='text' validate="{required:true,maxlength:200}" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectPayCompany()}}]}"/><!--  onclick="selectPayCompany()" -->
							</td>
							
							
							<td class="l-t-td-left">合同：</td>
							<td class="l-t-td-right" >
							
								<input name="pact_name" id="pact_name" type="text" ltype='text' validate="{required:false,maxlength:200}" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectPact()}}]}"/><!--  onclick="selectPayCompany()" -->
							</td>
					   </tr>
						
					</c:when>
					<c:otherwise>
						<tr>
							<td class="l-t-td-left">缴费单位：</td>
							<td class="l-t-td-right" colspan="3" >
								<input name="fk_company_id" id="fk_company_id" type="hidden" />
								<input name="company_name" id="company_name" type="text" ltype='text' validate="{required:true,maxlength:200}" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectPayCompany()}}]}"/><!--  onclick="selectPayCompany()" -->
							</td>
						</tr>
					
					</c:otherwise>
		</c:choose>
			
				
				<tr>
					<td class="l-t-td-left">收费方式：</td>
					<td class="l-t-td-right"><u:combo name="pay_type" code="PAY_TYPE" validate="required:true" attribute="onSelected:change"/></td>
					<td class="l-t-td-left">应收总金额：</td>
					<td class="l-t-td-right">
						<input name="pay_receive" id="pay_receive" type="text" ltype='float' ligerui="{readonly:true}" title="提示：此处不能修改金额，如需修改，请先提交金额修改申请！"/>
					</td>
				</tr>
				<tr>		
					<td class="l-t-td-left">实收总金额：</td>
					<td class="l-t-td-right">
						<input name="pay_received" id="pay_received" type="text" ltype='float' ligerui="{readonly:true}" title="提示：此处不能修改金额，如需修改，请先提交金额修改申请！"/>
					</td>
					<td class="l-t-td-left">现金：</td>
					<td class="l-t-td-right">
						<input name="cash_pay" id="cash_pay" type="text" ltype='float' ligerui="{maxlength:18}" onchange="changeMoney('1')"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">转账：</td>
					<td class="l-t-td-right">
						<input type="hidden" name="fk_cw_bank_id" id="fk_cw_bank_id"/>
						<input name="remark" id="remark" type="text" ltype='float' ligerui="{maxlength:18, value:'',iconItems:[{icon:'add',click:function(){selectTransfer()}}]}" onchange="changeMoney('2')"/><!-- onclick="selectTransfer()"  -->
					</td>
					<td class="l-t-td-left">POS机刷卡：</td>
					<td class="l-t-td-right">
						<input name="pos" id="pos" type="text" ltype='float' ligerui="{maxlength:18}" onchange="changeMoney('3')"/>
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
					<td class="l-t-td-left">缴费日期：</td>
					<td class="l-t-td-right">
						<input name="pay_date" id="pay_date" type="text" ltype='date' validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" />
					</td>
					<td class="l-t-td-left">发票号：</td>
					<td class="l-t-td-right">
						<input name="invoice_no" id="invoice_no" type="text" ltype='text' validate="{required:false,maxlength:10}" /> 
						<!-- 
							<input type="text" name="invoice_no" id="invoice_no" ltype="select" validate="{required:true}" ligerui="{
								tree:{checkbox:false,keySupport:true,data: <u:dict sql="select f.INVOICE_CODE code,f.INVOICE_CODE text from TJY2_CW_INVOICE_F f where f.status='WSY' and rownum<6 ORDER BY f.INVOICE_CODE"/>}
									}"/>
						-->
					</td>
					<!-- 
					<td class="l-t-td-left">缴费人姓名：</td>
					<td class="l-t-td-right">
						<input name="pay_man_name" id="pay_man_name" type="text" ltype='text' validate="{maxlength:20}" />
					</td>
					-->
				</tr>
				<c:if test="${param.status eq 'modify'}">
				<tr>
					<td class="l-t-td-left">是否重新冲账：</td>
					<td class="l-t-td-right">
						<input type="radio" name="bank_change"  id="bank_change" ltype="radioGroup" validate="{required:false}"
						ligerui="{onChange:changeFlag,value:'0',data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/> 
					</td>
					<td class="l-t-td-left">原票号是否作废：</td>
					<td class="l-t-td-right">
						<input type="radio" name="if_del_invoice"  id="if_del_invoice" ltype="radioGroup" validate="{required:false}"
						ligerui="{value:'0',data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/> 
					</td>
				</tr>
				</c:if>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>银行转账</div>
			</legend>
			<div id="cwBankInfoList"></div>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>业务信息</div>
			</legend>
			<div id="inspectionInfoList"></div>
		</fieldset>
	</form>
	</div>
</div>
</body>
</html>
