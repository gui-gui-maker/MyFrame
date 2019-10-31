<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>设备入库记录</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
            	{name:"eq_name", compare:"like"},
            	{name:"eq_no", compare:"like"},
            	{name:"eq_model", compare:"like"},
            	{name:"instock_type", compare:"like"},
            	{name:"back_user_name", compare:"like"},
            	{group:[
						{name:"instock_date", compare:">=", value:""},
						{label:"到", name:"instock_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]}
            ],
            tbar:[
                /* { text:'记录详情', id:'detail',icon:'detail', click: detail},
                "-", */
                { text:'设备入库', id:'inStock',icon:'basket-put', click: inStock}
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        			//alert(rowData.eid);
        			detail(rowData.eid);
        		}
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	     	if(count == 1){
	            Qm.setTbar({inStock: true, detail: true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({inStock: true, detail: false});
	    	}else{
	    		Qm.setTbar({inStock: true, detail: false});
	    	}
		}
        //记录详情
        function detail(eid){
			if($.type(eid)!="string"){
				eid = Qm.getValueByName("eid").toString();
			}
			top.$.dialog({
				width:700,
				height:470,
				lock:true,
				title:"详情",
				content: 'url:app/equipment/base/equipment_basic_detail.jsp?status=detail&id=' + eid,
				data:{window:window}
			});
		}
        //设备入库
        function inStock(){
        	var id = Qm.getValuesByName("id").toString();
			top.$.dialog({
				width: 900,
				height: 630,
				lock:true,
				title:"设备入库",
				data: {window:window},
				content: 'url:app/equipment/base/equipment_instock_detail.jsp?status=inStock&id=' +id
			});
        }
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
	</head>
	<body>	
		<%-- <qm:qm pageid="TJY2_EQ_INSTOCK_LIST" singleSelect="false"> --%>
		<qm:qm pageid="TJY2_INSTOCK_RECORD" singleSelect="true">
		</qm:qm>
	</body>
</html>
