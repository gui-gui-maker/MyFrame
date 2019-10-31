<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>流程信息列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript"
			src="pub/selectUser/selectUnitOrUser.js"></script>
		<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},		
			sp_fields:[
			{name:"report_name",compare:"like",value:""},
			{name:"flow_name",compare:"like",value:""}
		],
		tbar:[
			{ text:'查看', id:'detail',icon:'detail', click: detail},
			"-",
			{ text:'新增', id:'add',icon:'add', click: add},
			"-",
			{ text:'修改', id:'modify',icon:'modify', click: modify}
		],
		listeners: {
	   		selectionChange : function(rowData,rowIndex){
	        	var count=Qm.getSelectedCount();//行选择个数
	   			Qm.setTbar({modify:count==1,detail:count==1,del:count>0});
	   		},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detail();
			}
		}
	};

	function add(){
		top.$.dialog({
			width : 800, 
			height : 240, 
			lock : true, 
			title:"添加",
			content: 'url:app/inspection/flow_config_detail.jsp?status=add',
			data : {"window" : window}
		});
	}

	function modify(){
		top.$.dialog({
			width : 800, 
			height : 240, 
			lock : true, 
			title:"修改",
			content: 'url:app/inspection/flow_config_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}

	function detail(){
		top.$.dialog({
			width : 800, 
			height : 240, 
			lock : true, 
			title:"查看",
			content: 'url:app/inspection/flow_config_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}

	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
	</head>
	<body>
		<qm:qm pageid="flow_list" script="false" singleSelect="false">
		</qm:qm>
	</body>
</html>
