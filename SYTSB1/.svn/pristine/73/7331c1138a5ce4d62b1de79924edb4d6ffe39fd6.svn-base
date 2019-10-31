<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>大厅报检作废记录</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
			sp_fields:[
					{name:"hall_no",compare:"like",value:""},
					{name:"com_name",compare:"like",value:""},
					{name:"inspection_type",compare:"=",value:""}		
		            ],
	            tbar:[
					{ text:'查看', id:'detail',icon:'detail', click: detail}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({detail:count==1});
	                }
	            }
	};
	
	function detail(){
		
		top.$.dialog({
			width : 800, 
			height : 600, 
			lock : true, 
			title:"大厅报检信息",
			content: 'url:app/insing/report_hall_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
</script>
</head>
<body>
	<qm:qm pageid="tzsb_hall_list" script="false" singleSelect="false" >
		<qm:param name="flow_status" value="2" compare="=" />
	</qm:qm>
</body>
</html>
