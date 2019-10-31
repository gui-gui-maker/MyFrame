<%@ page language="java" import="java.text.SimpleDateFormat" pageEncoding="utf-8" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>外借登记</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/payment/payment_list.js"></script>
<%
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
					Rows : resp.data["inspectionInfoDTOList"]
				});
			}
		});
	})
		
	function close(){	
		api.close();
	}
			
	function save(){
		//验证表单数据
		if($('#form1').validate().form()){
			var invoice_no = $("#invoice_no").val();	// 发票号
			if("" != invoice_no){
				invoice_no = invoice_no.trim();	// 去空格
				if(invoice_no.length == 10){
					invoice_no = invoice_no.substr(0,(invoice_no.length-1));	// 截最后一位
					$("#invoice_no").val(invoice_no);
				}
				//$.getJSON("payment/payInfo/validateInvoices.do?invoice_no="+invoice_no, function(resp){
				//	if(!resp.success){
						//$("#invoice_no").val("");
				//		if('${param.status}' == 'add'){
				//			$.ligerDialog.alert("亲，系统未能识别发票号（"+invoice_no+"），请核实是否已作废！确认未作废请联系财务人员导入！", "提示");
				//			return;
				//		}
				//	}else{
						doSave();
				//	}
				//})
			}else{
				doSave();
			}
		}
	}
	
	function doSave(){
		var if_del_invoice = '0';
		if('${param.status}' == 'modify'){
			if_del_invoice = $('#if_del_invoice').ligerGetRadioGroupManager().getValue();
		}
		var url = "report/borrow/saveBorrow.do?status=${param.status}&if_del_invoice="+if_del_invoice;
		if(confirm("确定保存？保存后无法修改！")){
				var formData = $("#form1").getValues();
				var datas = {};
				datas = formData;
				//验证grid
				//if(!validateGrid(inspectionInfoGrid)){
				//	return false;
				//}
				datas["inspectionInfoDTOList"] = inspectionInfoGrid.getData();
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
	
	// 打印借条
	function printBorrow(){
		$.getJSON("report/borrow/getDetail.do?status=detail&id=${param.id}", function(resp){
			if(resp.success){
				top.$.dialog({
					width : $(top).width(),
					height :  $(top).height()-40,
					lock : true,
					title : "打印借条",
					parent: api,
					content : 'url:app/payment/docEditor.jsp?status=modify&type=2&isPrint=2',	// type 1：缴费单 2：借条
					data: {pwindow: window, bean: resp.data}
				}).max();
			}
		})
	}
	
	function initInfo(){
		$.getJSON("report/borrow/getDetail.do?status=${param.status}&id=${param.id}", function(resp){
			if(resp.success){
				inspectionInfoGrid.loadData({
					Rows : resp.data["inspectionInfoDTOList"]
				});
				$("#id").val(resp.data["id"]);	// 存在外借记录时，此处需手动设置ID值（重要）
				$("#unpay_amount").val(parseFloat(resp.data["unpay_amount"]));	// 应收总金额（欠款金额）
			}
		})
	}
	
	function setValue(valuex,name){
		if(valuex==""){
			return;
		}
		if(name=='sign_leader_name'){
			$('#sign_leader_name').val(valuex);
		}
	}
	
	String.prototype.trim = function() {
    	return this.replace(/(^\s*)|(\s*$)/g,'');
	}
	</script>
</head>
<body <%if("add".equals(pageStatus)){%>onload="initInfo();"<%}%> >
<div class="navtab">
	<div title="外借登记" tabId="borrowTab" style="height: 400px">
	<form id="form1" getAction="report/borrow/getDetail.do?status=${param.status}&id=${param.id}">
		<input type="hidden" name="id" id="id"/>
		<input id="fk_inspection_info_id" name="fk_inspection_info_id" type="hidden" value="${param.id}" />
		<input id="fk_company_id" name="fk_company_id" type="hidden" value="${param.com_id}" />
		<input id="borrow_date" name="borrow_date" type="hidden" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" />
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
						<input name="unpay_amount" id="unpay_amount" type="text" ltype='float' ligerui="{readonly:true}" title="提示：此处不能修改金额，如需修改，请先提交金额修改申请！"/>
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
						<input name="invoice_no"
									id="invoice_no" type="text" ltype='text'
									validate="maxlength:32}" /> 
						<!-- 
							<input type="text" name="invoice_no" id="invoice_no" ltype="select" validate="{required:false}" ligerui="{
								tree:{checkbox:false,data: <u:dict sql="select f.INVOICE_CODE code,f.INVOICE_CODE text from TJY2_CW_INVOICE_F f where f.status='WSY' and rownum<6 ORDER BY f.INVOICE_CODE"/>}
									}"/>
							-->
					</td>
					<td class="l-t-td-left">签字主任：</td>
					<td class="l-t-td-right">
						<input name="sign_leader_name" id="sign_leader_name" type="hidden" /> 
						<input type="text" name="sign_leader_id" id="sign_leader_id" ltype="select"
								validate="{required:false}" onchange="setValue(this.value,'sign_leader_name')"
								ligerui="{tree:{checkbox:false,data:<u:dict sql="select t.id,t.pid,t.code, t.text from (select o.id as id, o.id as code, replace(o.org_code,'j','a')  as tcode, o.ORG_NAME as text, o.PARENT_ID as pid from sys_org o union select t1.id as id,t1.id as code, replace(e.code,'j','a') as tcode, t1.NAME as text, t1.ORG_ID as pid from employee e,sys_user t1 where e.id=t1.employee_id and t1.id in (select s.user_id from sys_user_role s ,Sys_Role r where r.id=s.role_id and r.name='部门领导')) t start with t.id in ('100020','100021','100022','100023','100024','100063') connect by t.pid = prior t.id ORDER BY T.TCODE asc"/>}}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">还款日期:</td>
					<td class="l-t-td-right" colspan="3">
						<input type="text" name="repay_date" id="repay_date" ltype="date"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">备注:</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="remark" id="remark" cols="60" rows="4" class="l-textarea"></textarea>
					</td>
				</tr>
				<c:choose>
					<c:when test="${param.status eq 'modify'}">
					<tr>
						<td class="l-t-td-left">原票号是否作废：</td>
						<td class="l-t-td-right">
							<input type="radio" name="if_del_invoice"  id="if_del_invoice" ltype="radioGroup" validate="{required:false}"
							ligerui="{value:'0',data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/> 
						</td>
						<td class="l-t-td-left">&nbsp;</td>
						<td class="l-t-td-right">&nbsp;</td>
					</tr>
					</c:when>
				</c:choose>
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
						<td class="l-t-td-left">检验类别：</td>
						<td class="l-t-td-right"><u:combo name="check_type" code="BASE_INSPECT_TYPE"/></td>
				    </tr>
				    <tr>
				    	<td class="l-t-td-left">受检单位：</td>
				       	<td class="l-t-td-right" colspan="3"><input name="com_name" id="com_name" type="text" ltype='text' /></td>
				       	
				    </tr>
				    <tr>
				    	<td class="l-t-td-left">设备类别：</td>
				       	<td class="l-t-td-right"><u:combo name="device_sort_code" code="device_classify" tree="true" attribute="treeLeafOnly:true"/></td>
				    	<td class="l-t-td-left">设备名称：</td>
				       	<td class="l-t-td-right"><input name="device_name" id="device_name" type="text" ltype='text' /></td>
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
				    	<td class="l-t-td-left">预检日期：</td>
				       	<td class="l-t-td-right"><input name="advance_time" id="advance_time" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"/></td>
				       	<td class="l-t-td-left">检验部门：</td>
				       	<td class="l-t-td-right"><input name="item_op_name" id="item_op_name" type="text" ltype='text' /></td>
				    </tr>
				    <tr>
				    	<td class="l-t-td-left">检验联系人：</td>
				       	<td class="l-t-td-right"><input name="check_op" id="check_op" type="text" ltype='text' /></td>
				       	<td class="l-t-td-left">联系人电话：</td>
				       	<td class="l-t-td-right"><input name="check_tel" id="check_tel" type="text" ltype='text' /></td>
				    </tr>
				    <tr>
				    	<td class="l-t-td-left">项目负责人：</td>
				       	<td class="l-t-td-right"><input name="item_op_name" id="item_op_name" type="text" ltype='text' /></td>
				       	<td class="l-t-td-left">参检人员：</td>
				       	<td class="l-t-td-right"><input name="check_op_name" id="check_op_name" type="text" ltype='text' /></td>
				    </tr>
				    <tr>
				       	<td class="l-t-td-left">录入人员：</td>
				       	<td class="l-t-td-right"><input name="enter_op_name" id="enter_op_name" type="text" ltype='text' /></td>
				       	<td class="l-t-td-left">录入时间：</td>
				       	<td class="l-t-td-right"><input name="enter_time" id="enter_time" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"/></td>
				    </tr>
				    <tr>
				    	<td class="l-t-td-left">备注：</td>
				       	<td class="l-t-td-right" colspan="3"><input name="receivable_remark" id="receivable_remark" type="text" ltype='text' /></td>
				    </tr>
				</table> 
			  	<script type="text/javascript">
			    $("#inspectionInfoForm").initFormList({
			    	root:'datalist',
			        getAction:"report/borrow/getInspectionInfoList.do?id=${param.id}",
			        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
			        //actionParam:{"baseEquipmentApply.id" : $("#formObj>[name='id']")},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
			        //delAction:'equipment/apply/relation/delete.do',	//删除数据的action
			        //delActionParam:{ids:"id"},	//默认为选择行的id 
			        columns:[
			            //此部分配置同grid
			            //{ display: 'id', name: 'id',  hide:true},
				        { display: '检验类别', width: '10%', name: 'check_type', type: 'text',
				            render: function (item) {
				                return render(item["check_type"],checkType);
				            }
				        },
				        { display: '业务流水号', name: 'sn', width: '10%'},
				        { display: '受检单位', width: '20%', name: 'com_name', type: 'text'},
				        { display: '设备类别', width: '15%', name: 'device_sort_code', type: 'text', 
				            render: function (item) {
				                return render(item["device_sort_code"],deviceType);
				            }
				        },
				        { display: '设备名称', width: '12%', name: 'device_name', type: 'text'},
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
				      	{ display: '预检日期', width: '10%', name: 'advance_time', type: 'date'},
				      	{ display: '检验联系人', width: '8%', name: 'check_op', type: 'text'},
				      	{ display: '联系人电话', width: '10%', name: 'check_tel', type: 'text'},
				      	{ display: '项目负责人', width: '8%', name: 'item_op_name', type: 'text'},
				      	{ display: '参检人员', width: '15%', name: 'check_op_name', type: 'text'},
				      	{ display: '录入人员', width: '8%', name: 'enter_op_name', type: 'text'},
				      	{ display: '录入时间', width: '10%', name: 'enter_time', type: 'date'},
				        { display: '备注', name: 'receivable_remark', width: '30%', editor: { type: 'text'}, align: 'left', maxlength:200}
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
