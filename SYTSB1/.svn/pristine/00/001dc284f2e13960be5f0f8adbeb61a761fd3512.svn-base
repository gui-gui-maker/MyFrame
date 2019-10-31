<%@page import="java.util.Date"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title></title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/equipment/js/order.js"></script>
<script test="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var pageStatus = "${param.status}";
	$(function() {
		$.getJSON("purchase/order/main/getOrderNum.do",function(data){
			if(data.success){
				$("input[name='orderNum']").val(data.data);
			}
		})
		createBuOrderListGrid();
		$("#formObj").initForm({
			success : function(response) {//处理成功
				if (response.success) {
					top.$.dialog.notice({
						content : "保存成功！"
					});
					api.data.window.submitAction();//执行列表页面函数
					api.close();
				} else {
					$.ligerDialog.error('保存失败！<br/>' + response.msg);
				}
			},
			getSuccess : function(res) {
				buOrderListGrid.loadData({
					Rows : res.data["buOrderLists"]
				});
				$("#formObj").setValues(res.data);
				$("#formObj").setValues({supplierId:res.data.supplier.id})
			},
			toolbar : [ {
				id:'save',
				text : "保存",
				icon : "save",
				click : save
			}, {
				text : '提交',
				id : 'submit',
				icon : 'submit',
				click : submit
			},{
				text : "关闭",
				icon : "cancel",
				click : closewindow
			} ],
			toolbarPosition : "bottom"
		});
	});

	function submit(){
		var url = "purchase/order/main/saveOrder.do?submit=1";
		$.ligerDialog.confirm('信息提交后，将不能修改，确认提交？', function (yes) 
		{ 
			if(yes)
			{
				save(url);
			}
		});
	}
	function save(url){
		if(url==undefined||url==null||url=="")
		{
			url = "purchase/order/main/saveOrder.do?submit=0";
		}
		//验证表单数据
		if($('#formObj').validate().form())
		{
	        var data = {};
	        //验证grid
	        if(!validateGrid(buOrderListGrid))
			{
				return false;
			}
	        var buorderLists = buOrderListGrid.getData();
	        if(buorderLists==null||buorderLists==undefined||buorderLists=="")
	        {
	        	$.ligerDialog.alert("请至少录入一条产品信息！","提示")
	        	return;
	        }
	        //设置金额
	        var orderMoney=0.00;
	        var orderAllMoney=0.00; 
	        var orderTaxMoney=0.00;
	        for(var i=0;i<buorderLists.length;i++){
	        	orderMoney+=parseFloat(buorderLists[i].goodsMoney);
	        	orderAllMoney+=parseFloat(buorderLists[i].goodsAllMoney);
	        	orderTaxMoney+=parseFloat(buorderLists[i].goodsTaxMoney);
	        }
	        $("#orderMoney").val(Math.round(orderMoney *100)/100);
	        $("#orderAllMoney").val(Math.round(orderAllMoney *100)/100);
	        $("#orderTaxMoney").val(Math.round(orderTaxMoney *100)/100);
	        var formData = $("#formObj").getValues();
	        data= formData;
	        data["buOrderLists"] = buorderLists;
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
	function getSupply(){
		var url="url:app/purchase/select_supplier_list.jsp"
		top.$.dialog({
			width : 800,
			height : 400,
			lock : true,
			parent : api,
			title : "选择进货渠道",
			content : url,
			cancel: true,
			ok : function() {
				var datas = this.iframe.contentWindow.getSelectResult();
				if(datas){
					$("#supplierId").val(datas.code);
					$("#company").val(datas.name);
					return true;
				}
				else return false;
			}
		});
	}
	function closewindow() {
		api.close();
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="purchase/order/main/saveOrder.do"
		getAction="purchase/order/main/detail.do?id=${param.id}">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					采购订单
				</div>
			</legend>
			<input type="hidden" name="id" id="id" />
			<input type="hidden" name="status" id="status" />
			<input type="hidden" name="orderMoney" id="orderMoney" />
			<input type="hidden" name="orderAllMoney" id="orderAllMoney" />
			<input type="hidden" name="orderTaxMoney" id="orderTaxMoney" />
			
			<table cellpadding="3" cellspacing="0" class="l-detail-table" id="purchasemain">
				<tr>
					<td class="l-t-td-left">单据编号：</td>
					<td class="l-t-td-right"><input name="orderNum" 
						type="text" ltype='text' validate="{maxlength:32,required:true}"  ligerui="{readonly:true}"/>
					</td>
					<td class="l-t-td-left">供货商：</td>
					<td class="l-t-td-right">
					<input type="text" id="company" name="supplier.company" ltype="text"  
						onclick="getSupply()" validate="{required:true}"
						ligerui="{readonly:true,value:'',iconItems:[{icon:'selectbox',click:function(){getSupply()}}]}"/>
						<input id="supplierId" name="supplierId"  type="hidden"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">经手人：</td>
					<td class="l-t-td-right">
					<input type="text" id="orderHandling" name="orderHandling" ltype="text"  
						onclick="selectUnitOrUser(1,0,'orderHandling','orderHandling')"
						ligerui="{readonly:true,value:'<sec:authentication property="principal.name" />',iconItems:[{icon:'selectbox',click:function(){selectUnitOrUser(1,0,'orderHandling','orderHandling')}}]}"/>
					</td>
					<td class="l-t-td-left">审核人：</td>
					<td class="l-t-td-right">
					<input type="text" id="orderCheck" name="orderCheck" ltype="text"  
						onclick="selectUnitOrUser(1,0,'orderCheck','orderCheck')"
						ligerui="{readonly:true,value:'<sec:authentication property="principal.name" />',iconItems:[{icon:'selectbox',click:function(){selectUnitOrUser(1,0,'orderCheck','orderCheck')}}]}"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">录单人：</td>
					<td class="l-t-td-right"><input name="createBy" 
						type="text" ltype='text'  value="<sec:authentication property="principal.name" />" validate="{maxlength:32}"  ligerui="{readonly:true}"/>
					</td>
					<td class="l-t-td-left">录单日期：</td>
					<td class="l-t-td-right"><input name="orderDate" 
						type="text" ltype='date'  value='<%=DateUtil.getDate(new Date()) %>'/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">说明：</td>
					<td class="l-t-td-right" colspan="3"><input name="remark" 
						type="text" ltype='text' validate="{maxlength:512}" />
					</td>
				</tr>
			</table>
			<div id="buOrderList"></div>
		</fieldset>
	</form>
</body>
</html>
