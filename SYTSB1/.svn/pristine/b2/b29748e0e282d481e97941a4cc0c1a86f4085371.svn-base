<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>设备信息</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
            	{name:"eq_name", compare:"like"},
            	{name:"eq_type", compare:"="},
            	{name:"eq_use_department", compare:"like"},
            	{name:"eq_status", compare:"="},
            	{name:"eq_use_status", compare:"="},
            	{name:"eq_buy_date", compare:"="}
            ],
            tbar:[
            	{ text:'详情', id:'detail',icon:'detail', click: detail}
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        			detail(rowData.id);
        		}
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	     	if(count == 1){
	            Qm.setTbar({detail: true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({detail: false});
	    	}else{
	    		Qm.setTbar({detail: false});
	    	}
		}
	
        
        function detail(id){
			if($.type(id)!="string"){
				id = Qm.getValueByName("id").toString();
			}
			var eqtype = Qm.getValueByName("eqtype").toString();
			top.$.dialog({
				width:800,
				height:500,
				lock:true,
				title:"查看详情",
				content: 'url:app/equipment/equipment_basic_detail.jsp?status=detail&eq_type='+eqtype+'&id=' + id,
				data:{window:window},
				cancel:true
			});
		}
      
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
	</head>
	<body>	
		<qm:qm pageid="equipment_list" singleSelect="false">
		</qm:qm>
	</body>
</html>
