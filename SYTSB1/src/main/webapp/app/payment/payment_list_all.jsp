<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="com.khnt.rbac.impl.bean.Role"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>承压开票</title>
<%@include file="/k/kui-base-list.jsp"%>
<style type="">
.l-icon-exportExcel {
	background: url('k/kui/images/icons/16/excel-export.png') no-repeat center;
}
</style>
<script type="text/javascript">
	var check_deps = <u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'cy%' order by orders "></u:dict>;
	var condition = {};
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"company_name", id:"company_name", compare:"like"},
			{name:"check_dep_id", id:"check_dep_id", compare:"=", value:"", treeLeafOnly: false},//isMultiSelect:true
			{name:"receive_man_name", id:"receive_man_name", compare:"=",isMultiSelect:true},
			{group:[
				{label:"开票/销票日期", name:"start_date",id:"start_date", compare:">=", value:"",xtype:"date"},
				{label:"到", name:"end_date",id:"end_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20,xtype:"date"}
			]},
			{group:[
				{name:"invoice_no", id:"start_invoice_no", compare:">=", value:""},
				{label:"到", name:"invoice_no", id:"end_invoice_no", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
			]},
			{name : "invoice_no", id : "invoice_no", compare : "="},
			{name: "invoice_type", id:"invoice_type",compare:"=",isMultiSelect:true},
			{name : "pay_type", id:"pay_type",compare : "=",isMultiSelect:true},//isMultiSelect:true 多选设置
			{name : "pay_received", id:"pay_received",compare : "="}
		],
		tbar : [
		/*
		{
			text : '详情',
			id : 'detail',
			click : detail,
			icon : 'detail'
		}*/
		{
			text : '收费总金额',
			id : 'pay_received',
			click : doTotal1,
			icon : 'help'
		}, '-', {
			text : '现金合计',
			id : 'doCashTotal',
			click : doCashTotal,
			icon : 'help'
		}, '-', {
			text : '转账合计',
			id : 'doTransferTotal',
			click : doTransferTotal,
			icon : 'help'
		}, '-', {
			text : 'POS合计',
			id : 'doPosTotal',
			click : doPosTotal,
			icon : 'help'
		}, '-', {
			text : '上缴财政合计',
			id : 'doHandInTotal',
			click : doHandInTotal,
			icon : 'help'
		}, '-', {
			text : '承兑汇票合计',
			id : 'doDraftTotal',
			click : doDraftTotal,
			icon : 'help'
		}, '-', {
			text : '平板（支付宝）合计',
			id : 'doAlipayMobileTotal',
			click : doAlipayMobileTotal,
			icon : 'help'
		}, '-', {
			text : '平板（微信）合计',
			id : 'doWechatMobileTotal',
			click : doWechatMobileTotal,
			icon : 'help'
		}, '-', {
			text : '交账明细导出',
			id : 'exportPayInfo',
			click : exportPayInfo,
			icon : 'excel-export'
		}, '-', {
			text : '收费部门分配',
			id : 'payUnit',
			click : payUnit,
			icon : 'org'
		}
		<sec:authorize access="hasRole('charge')">	
		/*{
			text : '开票',
			id : 'doPayment',
			click : doPayment,
			icon : 'add'
		}
		, '-', {
			text : '取消开票',
			id : 'cancelPayment',
			click : cancelPayment,
			icon : 'del'
		}, '-', {
			text : '打印缴费单',
			id : 'printPayInfo',
			click : printPayInfo,
			icon : 'print'
		}*/
	</sec:authorize>//, '-', 
        //{ text:'开票日志', id:'turnHistory',icon:'follow', click: turnHistory}
		, '-', {
			text : '清空', 
			id : 'empty', 
			icon : 'modify', 
			click : empty
		} ],
		listeners : {
			rowClick : function(rowData, rowIndex) {
				setConditionValues(rowData);
			},
			rowDblClick : function(rowData, rowIndex) {
				//detail();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange()
			},
			rowAttrRender : function(rowData, rowid) {
				/*
	            var fontColor="black";
	            // 2：已开票
	            if (rowData.status == "2"){
	            	fontColor="black";
	            }
	            // 99：取消开票
	            if (rowData.status == "99"){
	            	fontColor="red";
	            }
	            return "style='color:"+fontColor+"'";
	            */
			},
	        pageSizeOptions:[10,20,30,50,100,200]
		}
	};

	// 行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		/* if (count == 0) {
			Qm.setTbar({
				doPayment : true,
				detail : false,
				pay_received : false,
				doCashTotal : false,
				doTransferTotal : false,
				doPosTotal : false,
				doHandInTotal : false,
				doDraftTotal : false,
				printPayInfo : false,
				cancelPayment : false,
				turnHistory : false,
				exportPayInfo : true,
				payUnit:false,
				doAlipayMobileTotal:false,
				doWechatMobileTotal:false
			});
		} else if (count == 1) {
			Qm.setTbar({
				doPayment : true,
				detail : true,
				pay_received : true,
				doCashTotal : true,
				doTransferTotal : true,
				doPosTotal : true,
				doHandInTotal : true,
				doDraftTotal : true,
				printPayInfo : true,
				cancelPayment : true,
				turnHistory : true,
				exportPayInfo : true,
				payUnit:true,
				doAlipayMobileTotal:true,
				doWechatMobileTotal:true

			});
		} else {
			Qm.setTbar({
				doPayment : true,
				detail : false,
				pay_received : true,
				doCashTotal : true,
				doTransferTotal : true,
				doPosTotal : true,
				doHandInTotal : true,
				doDraftTotal : true,
				printPayInfo : false,
				cancelPayment : true,
				turnHistory : false,
				exportPayInfo : true,
				payUnit:false,
				doAlipayMobileTotal:true,
				doWechatMobileTotal:true
			});
		} */
		Qm.setTbar({
			doPayment : count>0,
			detail : count==1,
			pay_received : count>0,
			doCashTotal : count>0,
			doTransferTotal : count>0,
			doPosTotal : count>0,
			doHandInTotal : count>0,
			doDraftTotal : count>0,
			printPayInfo : count==1,
			cancelPayment : count>0,
			turnHistory : count==1,
			exportPayInfo : count>0,
			payUnit:count==1,
			doAlipayMobileTotal:count>0,
			doWechatMobileTotal:count>0
		});
	}
	
	function setConditionValues(rowData){
		/* $("#qm-search-p input").each(function(){
			var name = $(this).attr("id");
			if(!$.kh.isNull(name)){
				$(this).val(rowData[name]);
			}
		}) */
		/* $("input[name='check_dep_id-txt']").ligerComboBox().selectValue(render(rowData["check_dep_id"],check_deps));	// 检验部门 */
		if(!$.kh.isNull(rowData["company_name"])){
			$("#company_name").val(rowData["company_name"]);
		}
		if(!$.kh.isNull(rowData["invoice_no"])){
			$("#invoice_no").val(rowData["invoice_no"]);
		}
		if(!$.kh.isNull(rowData["check_dep_id"])){
			$("#check_dep_id").val(rowData["check_dep_id"]);
		}
		if(!$.kh.isNull(rowData["check_dep_id"])){
			$("input[name='check_dep_id-txt']").ligerComboBox().selectValue(render(rowData["check_dep_id"],check_deps))
		}
	}
		
	
	
	function empty(){
		$("#qm-search-p input").each(function(){
			$(this).val("");
		})
	}
	
	
	
	// 查看详情
	function detail() {
		var id = Qm.getValueByName("id").toString();
		var url = 'url:app/payment/payment_detail_cy.jsp?status=detail&id='+ id;
		top.$.dialog({
			width : 1000,
			height : 550,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},
			content : url	
		});
	}
	
	
	// 开票
	function doPayment() {
		top.$.dialog({
			width : 900,
			height : 600,
			lock : true,
			title : "开票",
			data : {
				"window" : window
			},
			content : 'url:app/payment/payment_detail_cy.jsp?status=add'
		});
	}
	
	// 取消开票
	function cancelPayment() {
		$.ligerDialog.confirm("您确定要取消开票？", function(yes) {
			if (yes) {
				$.ajax({
					url : "payment/payInfo/cancelPayCY.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(resp) {
						if (resp.success) {
							top.$.notice("取消成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("取消失败！" + resp.msg);
						}
					}
				});
			}
		});
	}

	// 打印缴费单
	function printPayInfo(){
		top.$.dialog({
			width : 750, 
			height : 500, 
			lock : true, 
			title:"打印缴费结算单",
			content: 'url:payment/payInfo/doPrintCY.do?id='+Qm.getValueByName("id").toString()+'&type=2',
			data : {"window" : window}
		})
	}
	
	// 金额合计
	function doTotal1() {
		var ids = Qm.getValuesByName("pay_received").toString();
		doTotal(ids,"收费总金额");
	}
	
	// 现金合计
	function doCashTotal() {
		var ids = Qm.getValuesByName("cash_pay").toString();
		doTotal(ids,"现金");
	}
	
	// 转账合计
	function doTransferTotal() {
		var ids = Qm.getValuesByName("remark").toString();
		doTotal(ids,"转账");
	}
	
	// POS合计
	function doPosTotal() {
		var ids = Qm.getValuesByName("pos").toString();
		doTotal(ids,"POS");
	}
	
	// 上缴财政合计
	function doHandInTotal() {
		var ids = Qm.getValuesByName("hand_in").toString();
		doTotal(ids,"上缴财政");
	}
	
	// 承兑汇票合计
	function doDraftTotal() {
		var ids = Qm.getValuesByName("draft").toString();
		doTotal(ids,"承兑汇票");
	}
	
	// 平板（支付宝）合计
	function doAlipayMobileTotal() {
		var ids = Qm.getValuesByName("alipay_mobile").toString();
		doTotal(ids,"平板（支付宝）");
	}
	
	// 平板（微信）合计
	function doWechatMobileTotal() {
		var ids = Qm.getValuesByName("wechat_mobile").toString();
		doTotal(ids,"平板（微信）");
	}
	
	// 导出交账明细
	function exportPayInfo(){
		var org_id = $("input[name='check_dep_id-txt']").ligerGetComboBoxManager().getValue();
		$("#org_id").val(org_id);
		$("#pay_start_date").val($("#pay_date").val());
		$("#pay_end_date").val($("#pay_date1").val());
		$("body").mask("正在导出数据,请等待...");
    	$("#form1").attr("action","payment/payInfo/exportAll.do");
    	$("#form1").submit();
    	$("body").unmask();
	}
	
	// 开票日志
	function turnHistory(){	
		top.$.dialog({
	   		width : 400, 
	   		height : 500, 
	   		lock : true, 
	   		title: "开票日志",
	   		content: 'url:payment/payInfo/getFlowStep.do?id='+Qm.getValueByName("id"),
	   		data : {"window" : window}
	   	});
	}
	
	function doTotal(ids,title){
		var str = new Array();
		str = ids.split(",");
		var total = 0;
		if (str != null && str.length > 0) {
			for ( var i = 0; i < str.length; i++) {
				if(str[i]==''||str[i]==null){
						str[i]=0;
					}
				total = total + parseFloat(str[i]);
			}
			$.ligerDialog.alert(title+'合计：' + total + "元。");
		}
	}
	
	function render(value,data){
		    for (var i in data) {
		    	if (data[i]["text"] == value)
		        {
		    		
		        	return data[i]['id'];
		        }
				if(data[i].children)
				{
					for(var j in data[i].children)
					{
						if(data[i].children[j]["text"] ==value)
							return data[i].children[j]['id'];
						if(data[i].children[j].children)
						{
							for(var k in data[i].children[j].children)
								if(data[i].children[j].children[k]["text"]==value)
								{
									return data[i].children[j].children[k]["id"];
								}
						}
					}
				}
		    }
		    return value;
		}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
	
	//收费部门分配
	function payUnit(){
		var pay_received = Qm.getValueByName("pay_received");
		top.$.dialog({
	   		width :800, 
	   		height : 500, 
	   		lock : true, 
	   		title: "收费部门分配",
	   		content: 'url:app/payment/unit/payment_unit_cut.jsp?status=modify&id='+Qm.getValueByName("id")+"&pay_received="+pay_received,
	   		data : {"window" : window,
	   			"company_name":Qm.getValueByName("company_name")}
	   	});
	}
	
	function getData() {
        $("body").mask("加载中...");
        $.ajax({
            url: "payment/payInfo/getPaymentListAll.do",
            data: condition,
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            type: "post",
            async: true,
            success: function (data) {
              $("body").unmask(""); 
                if ( data ) {
                	$("#qmgrid").ligerGetGridManager().loadData({rows: data}); 
                	 selectionChange(); 
                } else {

                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
              $("body").unmask(""); 
            }
        });
    } 
	
	function doSearch() {
         condition = {
   		 company_name: $("#company_name").val(),
   		 check_dep_id: $("#check_dep_id").val(),
   		 receive_man_name: $("#receive_man_name").val(),
   		 start_date: $("#start_date").val(),
   		 end_date: $("#end_date").val(),
   		 start_invoice_no: $("#start_invoice_no").val(),
   		 end_invoice_no: $("#end_invoice_no").val(),
   		 invoice_no:$("#invoice_no").val(),
   		 invoice_type: $("#invoice_type").val(),
   		 pay_type: $("#pay_type").val(),
   		 pay_received: $("#pay_received").val(),
        };
        getData();
    }
	
</script>
</head>
<body>
	<form name="form1" id="form1" action="" getAction="" target="_blank">
		<input type="hidden" name="org_id" id="org_id" value=""/>
		<input type="hidden" name="pay_start_date" id="pay_start_date" value=""/>
		<input type="hidden" name="pay_end_date" id="pay_end_date" value=""/>
	</form>
	<!-- 
	<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表已开票，
				<font color="red">“红色”</font>代表取消开票。
			</div>
		</div>
	</div>
	 -->
	<%
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		Map<String, String> roles = user.getPermissions();
		String user_name = user.getName();
		String role_name = "";
		for (String role : roles.values()) {  
			//Role role = (Role)value;
		  	if(role_name.length()>0){
		  		role_name += ",";
		  	}
		  	role_name += role;
		} 
	%>
	<qm:qm pageid="payment_list_all" script="false" >
	<%
		if(StringUtil.isNotEmpty(role_name) && !role_name.contains("sys_administrate")){
			if("邓洋".equals(user_name)){
				%>
				<qm:param name="receive_man_name" value="<%=user.getName()  %>" compare="="/>
				<%
			}
		}
	%>
	</qm:qm>
	<script type="text/javascript">
	// 根据 sql或码表名称获得Json格式数据
	<%--var aa=<u:dict code="community_type"></u:dict>;--%>
	Qm.config.columnsInfo.check_dep_id.binddata=<u:dict sql="select id,parent_id pid,id code, ORG_NAME text from SYS_ORG where (ORG_CODE like 'jd%' or ORG_CODE like 'cy%') and ORG_CODE!='cy4_1' order by orders "></u:dict>;
	Qm.config.columnsInfo.pay_type.binddata=<u:dict code="PAY_TYPE"></u:dict>;
	Qm.searchData = function () {//重写qm的searchData方法
		doSearch();
    }
	</script>
</body>
</html>