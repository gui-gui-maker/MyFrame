<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告领取记录</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/common/js/windowprint.js"></script>
<script type="text/javascript">   
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"invoice_no",compare:"like"},
			{name:"info_report_sn",compare:"like"},
			{name:"report_sn",compare:"like"},
			{name:"job_unit",compare:"like"},
			{name:"pulldown_op",compare:"like"},
			{group:[
				{name:"pulldown_time", id:"pulldown_time1", compare:">="},
				{label:"到", name:"pulldown_time", id:"pulldown_time2", compare:"<=", labelAlign:"center", labelWidth:20}
			]}
	    ],
		tbar:[
            { text:'详情', id:'detail',icon:'detail', click: reportDrawDetail},
            "-",
            {text : '修改', id : 'modify', icon : 'modify', click : modify }, 
			"-",
            { text:'打印', id:'print',icon:'print', click: printReportDraw},
            "-",
            { text:'新报告单打印', id:'printNew',icon:'print', click: printNew},
            "-",
			{ text:'删除', id:'del',icon:'delete', click:del}
        ],
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({
               		detail:count==1,
               		print:count>0,
               		printNew:count>0,
               		modify:count>0,
               		del:count>0
               	});
			},
			rowDblClick : function(rowData, rowIndex) {
				//Qm.getQmgrid().selectRange("id", [rowData.id]);
				//detailEmployee();
			}
		}
	};
	
	// 修改报告领取记录
	function modify(){
		var id = Qm.getValuesByName("id").toString();
		var invoice_no=Qm.getValuesByName("invoice_no");	// 发票编号
		var invoiceNo=""
		var report_sn=Qm.getValuesByName("report_sn").toString();
		for(var i=0;i<invoice_no.length;i++){
			if(i==0){
				invoiceNo=invoice_no[i];
			}else{
				if(invoiceNo!=invoice_no[i]){
					$.ligerDialog.alert("亲，你选择的发票编号不一样，请重新选择！");
					return;
				}
			}
		}
		var userArr = Qm.getValuesByName("pulldown_op");
		var first_user = userArr[0];
		for(var i=1;i<userArr.length;i++){
			if(userArr[i] != first_user){
				$.ligerDialog.error("修改多份报告领取记录时，只能选择同一个领取人的报告！");
				return;
			}
		}
		/* top.$.dialog({
			width : 600,
			height : 180,
			lock : true,
			title : "修改报告领取记录",
			content : 'url:app/flow/report_draw_modify.jsp?status=modify&id=' + id,
			data : {
				"window" : window
			}
		});	 */
		
		top.$.dialog({
			width : $(top).width()*0.5,
			height :$(top).height()*0.6,
			lock : true,
			title : "修改报告领取记录",
			content : 'url:app/flow/report_draw_modifyNew.jsp?status=modify&id=' + id+'&report_sn='+report_sn,
			data : {
				"window" : window
			}
		});	
	}
	
	//删除
    function del(){
		$.del("确定要删除？删除后无法恢复！","report/draw/del.do",{"ids":Qm.getValuesByName("id").toString()});
    }   

	//打印报告领取记录
	function printReportDraw() {
		var id = Qm.getValuesByName("id");
		var get_user_name = Qm.getValueByName("pulldown_op");
		var userArr = Qm.getValuesByName("pulldown_op");
		var first_user = userArr[0];
		for(var i=1;i<userArr.length;i++){
			if(userArr[i] != first_user){
				$.ligerDialog.error("打印多份报告领取记录时，只能选择同一个领取人的报告！");
				return;
			}
		}
		$.post("report/draw/reportDrawDetail1.do?id="+id,function(res){
			if(res.success){
				$.getJSON("report/draw/getPrintContent.do?id="+id, function(resp){
					if(resp.success){
						top.$.dialog({
										width : $(top).width(),
										height :  $(top).height()-40,
										lock : true,
										title : "打印领取记录",
										parent: api,
										content : 'url:app/flow/docEditor.jsp?status=modify&isPrint=1',	
										data: {window: window, bean: resp.printContent, op_user_name: resp.op_user_name,image:res.image}
									}).max();
					}else{
						$.ligerDialog.error(resp.message);
					}
				});
			}
		})
		
	}
	
	function printNew(){
		var id = Qm.getValuesByName("id");
		var get_user_name = Qm.getValueByName("pulldown_op");
		var userArr = Qm.getValuesByName("pulldown_op");
		var first_user = userArr[0];
		for(var i=1;i<userArr.length;i++){
			if(userArr[i] != first_user){
				$.ligerDialog.error("打印多份报告领取记录时，只能选择同一个领取人的报告！");
				return;
			}
		}
		
		$.ajax({
			type:'POST',
			url:'payment/payInfo/selectReportSn.do?reportSn='+Qm.getValuesByName("report_sn").toString(),
			data:{},
			dataType:'JSON',
			success:function(respost){
				$.post("report/draw/reportDrawDetail1.do?id="+id,function(res){
					if(res.success){
						$.getJSON("report/draw/getPrintContent.do?id="+id, function(resp){
							if(resp.success){
								top.$.dialog({
									width : $(top).width(),
									height :  $(top).height()-40,
									lock : true,
									title : "打印领取记录",
									parent: api,
									content : 'url:app/flow/newDocEditor.jsp?status=modify&isPrint=1',	
									data: {window: window, bean: resp.printContent, op_user_name: resp.op_user_name,image:res.image,report_sn1:respost.data}
								}).max();
							}else{
								$.ligerDialog.error(resp.message);
							}
						});
					}
				})
			}
		})
		
		
	}

	//打印报告领取记录
	function reportDrawDetail() {
		var row = Qm.getQmgrid().getSelected();
		top.$.dialog({
						width : 800,
						height : 600,
						lock : true,
						title : "领取详情",
						parent: api,
						content : 'url:app/flow/report_draw_detail2.jsp',	
						data: {window: window, row: row}
					}).max();
	}
	// 刷新Grid
	function submitAction() {
   		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="report_draw_history" script="false" singleSelect="false">
	</qm:qm>	
</body>
</html>
