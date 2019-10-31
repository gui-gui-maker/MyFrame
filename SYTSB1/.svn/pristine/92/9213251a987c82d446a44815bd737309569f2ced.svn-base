<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>制造监督检验报告领取记录</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/common/js/windowprint.js"></script>
<script type="text/javascript">   
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.5,labelAlign:'right',labelSeparator:'',labelWidth:80, width:"180"},	// 默认值，可自定义
		sp_fields:[
			{name:"sn",compare:"like"},
			{name:"job_unit",compare:"like"},
			{name:"pulldown_op",compare:"like"},
			{group:[
				{name:"pulldown_time", id:"pulldown_time1", compare:">="},
				{label:"到", name:"pulldown_time", id:"pulldown_time2", compare:"<=", labelAlign:"center", labelWidth:20}
			]}
	    ],
		tbar:[
			{text : '修改', id : 'modify', icon : 'modify', click : modify }, 
			"-",
            { text:'打印', id:'print',icon:'print', click: printReportDraw}
        ],
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({print:count>0,modify:count==1});
			},
			rowDblClick : function(rowData, rowIndex) {
				//Qm.getQmgrid().selectRange("id", [rowData.id]);
				//detailEmployee();
			}
		}
	};
	
	// 修改报告领取记录
	function modify(){
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width : 600,
			height : 180,
			lock : true,
			title : "修改报告领取记录",
			content : 'url:app/flow/report_zzjd_draw_modify.jsp?status=modify&id=' + id,
			data : {
				"window" : window
			}
		});	
	}

	//打印报告领取记录
	function printReportDraw() {
		var id = Qm.getValuesByName("id");
		var get_user_name = Qm.getValueByName("pulldown_op");
		var userArr = Qm.getValuesByName("pulldown_op");
		var first_user = userArr[0];
		for(var i=1;i<userArr.length;i++){
			if(userArr[i] != first_user){
				$.ligerDialog.error("亲，批量打印领取记录时，只能选择相同领取人的报告哦！");
				return;
			}
		}
		$.getJSON("report/draw/getZZJDPrintContent.do?id="+id, function(resp){
			if(resp.success){
				top.$.dialog({
								width : $(top).width(),
								height :  $(top).height()-40,
								lock : true,
								title : "打印领取记录",
								parent: api,
								content : 'url:app/flow/report_zzjd_docEditor.jsp?status=modify&isPrint=1',	
								data: {window: window, bean: resp.printContent, op_user_name: resp.op_user_name}
							}).max();
			}else{
				$.ligerDialog.error(resp.message);
			}
		})
	}
	
	// 刷新Grid
	function refreshGrid() {
   		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="zzjd_draw_history" script="false" singleSelect="false">
	</qm:qm>	
</body>
</html>
