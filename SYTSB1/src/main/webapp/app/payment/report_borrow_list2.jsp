<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>外借历史记录</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
		sp_fields:[
			{name:"report_sn", compare:"like"},
			{name:"device_registration_code", compare:"like"},
			{name:"report_com_name", compare:"like"},
			{name:"check_unit_id", id:"check_unit_id", compare:"="},
			{name:"check_op_name", compare:"like"},
			{name : "fee_status", compare : "="},
			{group:[
						{name:"borrow_date", compare:">=", value:""},
						{label:"到", name:"borrow_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
			]},
			{name : "invoice_no", compare : "like"},
			{name:"sign_leader_id", id:"sign_leader_id", compare:"="}
	    ],
		tbar:[
			{ text : '详情', id : 'detail', icon : 'detail', click : detail},
			 '-', 
			<sec:authorize access="hasRole('charge')">	
				{
					text : '收费B',
					id : 'payB',
					click : doPaymentB,
					icon : 'add'
				}, '-', {
					text : '收费P',
					id : 'payP',
					click : doPaymentP,
					icon : 'add'
				}, '-', {
					text : '修改外借登记',
					id : 'mdyBorrow',
					click : mdyBorrow,
					icon : 'role'
				}, '-', {
					text : '取消外借',
					id : 'delBorrow',
					click : delBorrow,
					icon : 'delete'
				}, '-', {
					text : '打印借条',
					id : 'printBorrow',
					click : printBorrow,
					icon : 'print'
				},'-', {
					text : '修改金额',
					id : 'changeMoney',
					click : changeMoney,
					icon : 'modify'
				}, '-', {
					id:"borrow_export",// 必须有菜单id才能调用
					menus: [{ text: '导出',icon:"excel-export",click:function(){exportPayInfo2()}, 
						menu: { width: 120, items:
								[
									{ text: '借报告', icon:"excel-export", click: function(){ exportPayInfo2() } },
									{ text: '借发票', icon:"excel-export", click:function(){ exportPayInfo3() } },
									{ text: '借报告和发票', icon:"excel-export", click: function(){ exportPayInfo4() } }
								]
						}
					}]
				},'-', {
					text : '应收金额合计',
					id : 'pay_receive',
					click : doTotal1,
					icon : 'help'
				},
			</sec:authorize>
        	{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory},
        	'-', 
        	{ text:'消息发送', id:'sendMsg',icon:'outbox', click: sendMsg},
        	'-', 
 			{text : '清空', id : 'empty', icon : 'modify', click : empty}     
        ],
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({detail:count==1,printBorrow:count==1,turnHistory:count==1,mdyBorrow:count==1,delBorrow:count==1,payB:count>0,payP:count==1,pay_receive:count>0,changeMoney:count>0,sendMsg:count==1});
			},
			rowDblClick : function(rowData, rowIndex) {
				//Qm.getQmgrid().selectRange("id", [rowData.id]);
			},
	        pageSizeOptions:[10,20,30,50,100,200]
		}
	};	
	
	function empty(){
		$("#qm-search-p input").each(function(){
			$(this).val("");
		})
		//$("input[name='fee_status-txt']").ligerComboBox().selectValue('');	// 收费状态
	}
	
	
	
	// 查看详情
	function detail() {
		var selectedIds = Qm.getValuesByName("inspection_info_id").toString();
		top.$.dialog({
			width : 1000,
			height : 550,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},
			content : 'url:app/payment/report_borrow_detail.jsp?status=detail&id='+ selectedIds	
		});
	}
	
	// 收费（外借发票、外借报告和发票）
	function doPaymentP() {
		//var company_ids = "";
		var ids = Qm.getValuesByName("inspection_info_id").toString();	// 报检业务ID
		var com_ids = Qm.getValuesByName("fk_unit_id");	// 报检单位ID
		var comArr = Qm.getValuesByName("fk_company_info_use_id");	// 设备使用单位ID
		//var unit_id = Qm.getValuesByName("check_unit_id");	// 单位ID
		
		var first_com = comArr[0];
		var diff_com = false;
		
		for(var i=1;i<comArr.length;i++){
			if(comArr[i] != first_com){
				//$.ligerDialog.error("明明：使用单位不一致，看清楚莫弄错了，皮卡丘，切！");
				//return;
				diff_com = true;
			}
		}
		/*
		company_ids += com_ids;
		var install_com_ids = Qm.getValuesByName("fk_company_info_install_id");	// 安装单位ID
		var make_com_ids = Qm.getValuesByName("fk_company_info_make_id");	// 制造单位ID
		var maintain_com_ids = Qm.getValuesByName("fk_maintain_unit_id");		// 维保单位ID
		for(var i=0;i<install_com_ids.length;i++){
			if(install_com_ids[i].length>0){
				if(company_ids.length>0){
					company_ids += ",";				
				}
				company_ids += install_com_ids[i];
			}
		}
		for(var i=0;i<make_com_ids.length;i++){
			if(make_com_ids[i].length>0){
				if(company_ids.length>0){
					company_ids += ",";				
				}
				company_ids += make_com_ids[i];
			}
			
		}
		for(var i=0;i<maintain_com_ids.length;i++){
			if(maintain_com_ids[i].length>0){
				if(company_ids.length>0){
					company_ids += ",";				
				}
				company_ids += maintain_com_ids[i];
			}
		}*/
		//if(unit_id.indexOf("100036")!=-1){
		//}
		
		
		if(diff_com){
			$.ligerDialog.confirm("亲，使用单位不一致哦，确定收费？", function(yes) {
					if (yes) {
						top.$.dialog({
							width : 1000,
							height : 620,
							lock : true,
							title : "收费",
							data : {
								"window" : window,
								"id":ids,
								"com_ids":com_ids
							},
							content : 'url:app/payment/payment_detail3.jsp?status=add&borrow_id='+Qm.getValueByName("borrow_id").toString()
							//content : 'url:app/payment/payment_detail.jsp?status=add&id='+ids+'&com_ids='+com_ids+'&company_ids='+company_ids
						});
					}
				});
		}else{
			top.$.dialog({
							width : 1000,
							height : 620,
							lock : true,
							title : "收费",
							data : {
								"window" : window,
								"id":ids,
								"com_ids":com_ids
							},
							content : 'url:app/payment/payment_detail3.jsp?status=add&borrow_id='+Qm.getValueByName("borrow_id").toString()
							//content : 'url:app/payment/payment_detail.jsp?status=add&id='+ids+'&com_ids='+com_ids+'&company_ids='+company_ids
						});
		}
	}
	
	// 收费（外借报告）
	function doPaymentB() {
		var ids = Qm.getValuesByName("inspection_info_id").toString();	// 报检业务ID
		var com_ids = Qm.getValuesByName("fk_unit_id");	// 报检单位ID
		var comArr = Qm.getValuesByName("fk_company_info_use_id");	// 设备使用单位ID
		
		var first_com = comArr[0];
		var diff_com = false;
		
		for(var i=1;i<comArr.length;i++){
			if(comArr[i] != first_com){
				diff_com = true;
			}
		}
		if(diff_com){
			$.ligerDialog.confirm("亲，使用单位不一致哦，确定收费？", function(yes) {
					if (yes) {
						top.$.dialog({
							width : 1000,
							height : 620,
							lock : true,
							title : "收费",
							data : {
								"window" : window,
								"id":ids,
								"com_ids":com_ids
							},
							content : 'url:app/payment/payment_detail4.jsp?status=add&borrow_id='+Qm.getValuesByName("borrow_id").toString()
						});
					}
				});
		}else{
			top.$.dialog({
							width : 1000,
							height : 620,
							lock : true,
							title : "收费",
							data : {
								"window" : window,
								"id":ids,
								"com_ids":com_ids
							},
							content : 'url:app/payment/payment_detail4.jsp?status=add&borrow_id='+Qm.getValuesByName("borrow_id").toString()
						});
		}
	}
	
	// 外借登记
	function doBorrow() {
		var selectedIds = Qm.getValuesByName("inspection_info_id").toString();	// 报检业务ID
		//var com_ids = Qm.getValuesByName("fk_unit_id");	// 报检单位ID
		var comArr = Qm.getValuesByName("fk_company_info_use_id");	// 设备使用单位ID
		var first_com = comArr[0];
		var diff_com = false;
		for(var i=1;i<comArr.length;i++){
			if(comArr[i] != first_com){
				//$.ligerDialog.error("设备信息使用单位不一致，不能同时外借，请核实！");
				//return;
				diff_com = true;
			}
		}
		if(diff_com){
			$.ligerDialog.confirm("明明：使用单位不一致，看清楚莫弄错了，皮卡丘，切！", function(yes) {
					if (yes) {
						top.$.dialog({
							width : 1000,
							height : 620,
							lock : true,
							title : "外借登记",
							data : {
								"window" : window
							},
							content : 'url:app/payment/report_borrow_detail.jsp?status=add&id='+ selectedIds+'&com_id='+first_com
						});
					}
				});
		}else{
			top.$.dialog({
				width : 1000,
				height : 620,
				lock : true,
				title : "外借登记",
				data : {
					"window" : window
				},
				content : 'url:app/payment/report_borrow_detail.jsp?status=add&id='+ selectedIds+'&com_id='+first_com
			});
		}
	}
	
	// 修改外借登记
	function mdyBorrow() {
		var selectedIds = Qm.getValuesByName("inspection_info_id").toString();	// 报检业务ID
		//var com_ids = Qm.getValuesByName("fk_unit_id");	// 报检单位ID
		var comArr = Qm.getValuesByName("fk_company_info_use_id");	// 设备使用单位ID
		var first_com = comArr[0];
		for(var i=1;i<comArr.length;i++){
			if(comArr[i] != first_com){
				$.ligerDialog.error("设备信息使用单位不一致，不能同时外借，请核实！");
				return;
			}
		}
		top.$.dialog({
			width : 1000,
			height : 620,
			lock : true,
			title : "修改外借登记",
			data : {
				"window" : window
			},
			content : 'url:app/payment/report_borrow_detail.jsp?status=modify&id='+ selectedIds+'&com_id='+first_com
		});
	}
	
	// 取消外借登记
	function delBorrow() {
		var info_id = Qm.getValueByName("inspection_info_id").toString();	// 报检业务ID
		var com_id = Qm.getValueByName("fk_unit_id");	// 报检单位ID
		$.del("您确定要取消外借吗？取消后如需再次外借或进行收费，请到“待收费”进行处理！", "report/borrow/delBorrow.do", {
			"info_id" : info_id
		});
	}
	
	// 打印借条
	function printBorrow(){
		var selectedIds = Qm.getValuesByName("inspection_info_id").toString();
		var feeStatusArr = Qm.getValuesByName("fee_status");
		for(var i=0;i<feeStatusArr.length;i++){
			if("已收费" == feeStatusArr[i]){
				$.ligerDialog.error("已收费的报告，不能打印借条！");
				return;
			}else if("初始" == feeStatusArr[i] || "待收费" == feeStatusArr[i]){
				$.ligerDialog.error("未进行外借登记，不能打印借条！");
				return;
			}
		}
		$.getJSON("report/borrow/getDetail.do?status=detail&id="+selectedIds, function(resp){
			if(resp.success){
				if(resp.data["id"] != null){
					top.$.dialog({
						width : $(top).width(),
						height :  $(top).height()-40,
						lock : true,
						title : "打印借条",
						parent: api,
						content : 'url:app/payment/docEditor.jsp?status=modify&type=2&isPrint=1',	// type 1：缴费单 2：借条
						data: {pwindow: window, bean: resp.data}
					}).max();
				}else{
					$.ligerDialog.alert("请先进行外借登记，再打印借条！");
				}
			}
		})
	}
	
	// 修改金额
	function changeMoney() {
		var info_ids = Qm.getValuesByName("inspection_info_id").toString();	// 报告业务ID		
		top.$.dialog({
			width : 1000,
			height : 420,
			lock : true,
			title : "修改金额",
			data : {
				"window" : window,
				"info_ids":info_ids
			},
			content : 'url:app/payment/change_money_detail.jsp?status=add'
		});
	}
	
	// 导出借报告
	function exportPayInfo2(){
		var org_id = $("input[name='check_unit_id-txt']").ligerGetComboBoxManager().getValue();
		if("" == org_id){
			//$.ligerDialog.alert("请先选择检验部门，再进行导出！");
			//return;
		}
		var sign_leader_id = $("input[name='sign_leader_id-txt']").ligerGetComboBoxManager().getValue();
		if("" == sign_leader_id){
			//$.ligerDialog.alert("请先选择签字主任，再进行导出！");
			//return;
		}
		if("" == org_id && "" == sign_leader_id){
			$.ligerDialog.alert("请先选择检验部门或签字主任，再进行导出！");
			return;
		}
		$("#org_id").val(org_id);
		$("#leader_id").val(sign_leader_id);

		$("#borrow_start_date").val($("#borrow_date").val());
		$("#borrow_end_date").val($("#borrow_date1").val());
		
		$("body").mask("正在导出数据,请等待...");
    	$("#form1").attr("action","payment/payInfo/export2.do");
    	$("#form1").submit();
    	$("body").unmask();
	}
	
	// 导出借发票
	function exportPayInfo3(){
		var org_id = $("input[name='check_unit_id-txt']").ligerGetComboBoxManager().getValue();
		if("" == org_id){
			//$.ligerDialog.alert("请先选择检验部门，再进行导出！");
			//return;
		}
		var sign_leader_id = $("input[name='sign_leader_id-txt']").ligerGetComboBoxManager().getValue();
		if("" == sign_leader_id){
			//$.ligerDialog.alert("请先选择签字主任，再进行导出！");
			//return;
		}
		if("" == org_id && "" == sign_leader_id){
			$.ligerDialog.alert("请先选择检验部门或签字主任，再进行导出！");
			return;
		}
		$("#org_id").val(org_id);
		$("#leader_id").val(sign_leader_id);

		$("#borrow_start_date").val($("#borrow_date").val());
		$("#borrow_end_date").val($("#borrow_date1").val());
		
		$("body").mask("正在导出数据,请等待...");
    	$("#form1").attr("action","payment/payInfo/export3.do");
    	$("#form1").submit();
    	$("body").unmask();
	}
	
	// 导出借报告和发票
	function exportPayInfo4(){
		var org_id = $("input[name='check_unit_id-txt']").ligerGetComboBoxManager().getValue();
		if("" == org_id){
			//$.ligerDialog.alert("请先选择检验部门，再进行导出！");
			//return;
		}
		var sign_leader_id = $("input[name='sign_leader_id-txt']").ligerGetComboBoxManager().getValue();
		if("" == sign_leader_id){
			//$.ligerDialog.alert("请先选择签字主任，再进行导出！");
			//return;
		}
		if("" == org_id && "" == sign_leader_id){
			$.ligerDialog.alert("请先选择检验部门或签字主任，再进行导出！");
			return;
		}
		$("#org_id").val(org_id);
		$("#leader_id").val(sign_leader_id);

		$("#borrow_start_date").val($("#borrow_date").val());
		$("#borrow_end_date").val($("#borrow_date1").val());
		
		$("body").mask("正在导出数据,请等待...");
    	$("#form1").attr("action","payment/payInfo/export4.do");
    	$("#form1").submit();
    	$("body").unmask();
	}
	
	// 流转过程
	function turnHistory(){	
		top.$.dialog({
   			width : 400, 
   			height : 700, 
   			lock : true, 
   			title: "流程卡",
   			content: 'url:department/basic/getFlowStep.do?ins_info_id='+Qm.getValueByName("inspection_info_id"),
   			data : {"window" : window}
   		});
	}
	
	// 应收金额合计
	function doTotal1() {
		var ids = Qm.getValuesByName("advance_fees").toString();
		doTotal(ids,"应收金额");
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
				/* total = total + parseFloat(str[i]); */
				total = accAdd(total,parseFloat(str[i]));
				/*total = Number((total+parseFloat(str[i])).toFixed(2));*/
			}
			$.ligerDialog.alert(title+'合计：' + total + "元。");
		}
	}
	
	function accAdd(arg1,arg2){ 
		var r1,r2,m; 
		try{
			r1=arg1.toString().split(".")[1].length;
		}catch(e){
			r1=0;
		} 
		try{
			r2=arg2.toString().split(".")[1].length;
		}catch(e){
			r2=0;
		} 
		m=Math.pow(10,Math.max(r1,r2));
		return (arg1*m+arg2*m)/m;
	}
	
	function parseIs_borrow(thisValue){
		var str = "";
		if("2" == thisValue){
	    	str = "是";
		}else{
			str = "否";
		}
		return str;
	}
	
	// 刷新Grid
	function refreshGrid() {
   		Qm.refreshGrid();
	}
	//消息发送
	function sendMsg(){
		$.ligerDialog.confirm('是否发送消息？', function (yes){
			if(!yes){return false;}
			$.ajax({
		    	url: "report/borrow/sendMsg.do?id="+Qm.getValueByName("inspection_info_id").toString(),
		        type: "POST",
		        datatype: "json",
		        contentType: "application/json; charset=utf-8",
		        success: function (resp) {
		        	if(resp.success){
		        		$.ligerDialog.alert("消息发送成功！");
		        	}else{
		        		$.ligerDialog.alert("消息发送失败！");
		        	}
		        },
		        error: function (resp) {
		        	$.ligerDialog.alert("出错了，请重试！");
		        }
		    });
		});
	  }
</script>
</head>
<body>
	<form name="form1" id="form1" method="post" action="" getAction="" target="_blank">
		<input type="hidden" name="org_id" id="org_id" value=""/>
		<input type="hidden" name="sign_leader_id" id="leader_id" value=""/>
		<input type="hidden" name="borrow_start_date" id="borrow_start_date" value=""/>
		<input type="hidden" name="borrow_end_date" id="borrow_end_date" value=""/>
	</form>	
	<qm:qm pageid="report_borrow_list2" script="false">
	</qm:qm>
	<script type="text/javascript">
//    根据 sql或码表名称获得Json格式数据
<%--var aa=<u:dict code="community_type"></u:dict>;--%>
//Qm.config.columnsInfo.area_id.binddata=<u:dict sql='select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE'></u:dict>;
Qm.config.columnsInfo.check_unit_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
Qm.config.columnsInfo.sign_leader_id.binddata=<u:dict sql="select t.id,t.pid,t.code, t.text from (select o.id as id, o.id as code, replace(o.org_code,'j','a')  as tcode, o.ORG_NAME as text, o.PARENT_ID as pid from sys_org o union select t1.id as id,t1.id as code, replace(e.code,'j','a') as tcode, t1.NAME as text, t1.ORG_ID as pid from employee e,sys_user t1 where e.id=t1.employee_id and t1.id in (select s.user_id from sys_user_role s ,Sys_Role r where r.id=s.role_id and r.name='部门领导') union select t2.id as id, t2.id as code, replace(e1.code, 'j', 'a') as tcode, t2.NAME as text, p2.ORG_ID as pid from employee e1, sys_user t2, SYS_EMPLOYEE_POSITION p1, SYS_POSITION p2 where e1.id = t2.employee_id and t2.id in (select s.user_id from sys_user_role s, Sys_Role r where r.id = s.role_id and r.name = '部门领导') and p1.position_id = p2.id and p1.employee_id = e1.id and (p2.org_id = '100023')) t start with t.id in ('100020','100021','100022','100023','100024','100063') connect by t.pid = prior t.id ORDER BY T.TCODE asc"/>;
Qm.config.columnsInfo.fee_status.binddata = [
	{id: '0', text: '外借类型', children: [
		{id: '3', text: '外借报告'},
		{id: '4', text: '外借发票'},
		{id: '5', text: '外借报告和发票'}
	]}
];
</script>
</body>
</html>
