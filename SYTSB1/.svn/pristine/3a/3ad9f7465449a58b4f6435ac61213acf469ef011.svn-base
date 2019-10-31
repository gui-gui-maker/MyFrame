<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>设备出库</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
				{name:'loan_type',compare:'like'},
				{name:'loan_name',compare:'like'},
				{group:[
						{name:"register_time", compare:">=", value:""},
						{label:"到", name:"register_time", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]}
            ],
            tbar:[
            	{ text:'详情', id:'detail',icon:'detail', click: detail},
                "-",
                { text:'设备出库', id:'outStock',icon:'forward', click: outStock}
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
	            Qm.setTbar({outStock: true, detail: true});            	
	 		}else{
	       		Qm.setTbar({outStock: false, detail: false});
	    	}
		}
        //设备详情
        function detail(id){
			if($.type(id)!="string"){
				id = Qm.getValueByName("id").toString();
			}
			top.$.dialog({
				width:900,
				height:630,
				lock:true,
				title:"详情",
				content: 'url:app/equipment/apply/equipment_loan_detail.jsp?pageStatus=detail&id=' + id,
				data:{window:window},
				cancel:true
			});
		}
        //设备出库
        function outStock(){
        	var id = Qm.getValuesByName("id").toString();
        	var type = Qm.getValuesByName("loan_type").toString();
        	if(type=="借用"){
        		top.$.dialog({
            		width: 900,
        			height: 630,
        			lock:true,
        			title:"设备出库",
        			data: {window:window},
        			content: 'url:app/equipment/base/equipment_outstock_detail.jsp?status=outStock&id=' +id+"&type=loan"
        		});
        	}else if(type=="领用"){
        		top.$.dialog({
            		width: 900,
        			height: 630,
        			lock:true,
        			title:"设备出库",
        			data: {window:window},
        			content: 'url:app/equipment/base/equipment_outstock_detail.jsp?status=outStock&id=' +id
        		});
        	}
        }
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
	</head>
	<body>	
		<qm:qm pageid="TJY2_LOAN_ADOPTION" singleSelect="true">
		</qm:qm>
	</body>
</html>
