<%@ page language="java" import="java.text.SimpleDateFormat" pageEncoding="utf-8" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>外借登记</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/payment/zzjd_payment_list.js"></script>
<%
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
			}
		});
	})
		
	function close(){	
		api.close();
	}
			
	function save(){
		var url = "report/borrow/saveZZJDBorrow.do";
		//验证表单数据
		if($('#form1').validate().form()){
			if(confirm("确定保存？保存后无法修改！")){
				var formData = $("#form1").getValues();
				var datas = {};
				datas = formData;
				//验证grid
				//if(!validateGrid(inspectionInfoGrid)){
				//	return false;
				//}
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
					    	$.ligerDialog.confirm('保存成功，现在打印借条？', function (yes)
							{
			                	if (yes){
			                  		printBorrow();
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
	
	// 打印借条
	function printBorrow(){
		$.getJSON("report/borrow/getZZJDDetail.do?status=detail&id=${param.id}", function(resp){
			if(resp.success){
				top.$.dialog({
					width : $(top).width(),
					height :  $(top).height()-40,
					lock : true,
					title : "打印借条",
					parent: api,
					content : 'url:app/payment/report_zzjd_docEditor.jsp?status=modify&type=2&isPrint=2',	// type 1：缴费单 2：借条
					data: {pwindow: window, bean: resp.data}
				}).max();
			}
		})
	}
	
	function initInfo(){
		$.getJSON("report/borrow/getZZJDDetail.do?status=${param.status}&id=${param.id}", function(resp){
			if(resp.success){
				inspectionInfoGrid.loadData({
					Rows : resp.data["inspectionZZJDPayInfoDTOList"]
				});
				$("#id").val(resp.data["id"]);	// 存在外借记录时，此处需手动设置ID值（重要）
				$("#unpay_amount").val(parseFloat(resp.data["unpay_amount"]));	// 应收总金额（欠款金额）
			}
		})
	}
	</script>
</head>
<body <%if("add".equals(pageStatus)){%>onload="initInfo();"<%}%> >
<div class="navtab">
	<div title="外借登记" tabId="borrowTab" style="height: 400px">
	<form id="form1" getAction="report/borrow/getZZJDDetail.do?status=${param.status}&id=${param.id}">
		<input type="hidden" name="id" id="id"/>
		<input id="fk_inspection_info_id" name="fk_inspection_info_id" type="hidden" value="${param.id}" />
		<input id="com_name" name="com_name" type="hidden" value="${param.com_name}" />
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>借条</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">外借类型：</td>
					<td class="l-t-td-right">
						<u:combo name="borrow_type" code="TZSB_BORROW_TYPE" validate="required:true" /><!-- attribute="onSelected:change" -->
					</td>
					<td class="l-t-td-left">欠款金额：</td>
					<td class="l-t-td-right">
						<input name="unpay_amount" id="unpay_amount" type="text" ltype='float' /><!-- ligerui="{readonly:true}" -->
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">欠款人姓名：</td>
					<td class="l-t-td-right">
						<input name="borrow_name" id="borrow_name" type="text" ltype='text' validate="{maxlength:20}" />
					</td>
					<td class="l-t-td-left">联系电话：</td>
					<td class="l-t-td-right">
						<input name="contack_number" id="contack_number" type="text" ltype='text' validate="{maxlength:11}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">发票号：</td>
					<td class="l-t-td-right">
						<input name="invoice_no" id="invoice_no" type="text" ltype='text' validate="maxlength:32}" />
					</td>
					<td class="l-t-td-left">外借日期：</td>
					<td class="l-t-td-right">
						<input name="borrow_date" id="borrow_date" type="text" ltype='date' validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" />
					</td>
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
			        getAction:"report/borrow/getInspectionZZJDInfoList.do?id=${param.id}",
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
</div>
</body>
</html>
