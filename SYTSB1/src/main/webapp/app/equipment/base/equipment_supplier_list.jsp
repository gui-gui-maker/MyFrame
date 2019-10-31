<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>供应商管理</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
            	{name:"supplier_name", compare:"like"},
            	{group:[
						{name:"cooperation_begin", compare:">=", value:""},
						{label:"到", name:"cooperation_begin", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]}
            ],
            tbar:[
            	{ text:'详情', id:'detail',icon:'detail', click: detail},
                "-",
                { text:'增加', id:'add',icon:'add', click: add},
                { text:'修改', id:'modify',icon:'modify', click:modify},
                { text:'删除', id:'del',icon:'delete', click:del}
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
	            Qm.setTbar({add: true, modify: true, del: true, detail: true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({add: true, modify: false, del: true, detail: false});
	    	}else{
	    		Qm.setTbar({add: true, modify: false, del: false, detail: false});
	    	}
		}
        
        function detail(id){
			if($.type(id)!="string"){
				id = Qm.getValueByName("id").toString();
			}
			top.$.dialog({
				width:700,
				height:250,
				lock:true,
				title:"查看详情",
				content: 'url:app/equipment/base/equipment_supplier_detail.jsp?status=detail&id=' + id,
				data:{window:window},
				cancel:true
			});
		}
        
        //新增
        function add(){
			top.$.dialog({
				width: 700,
				height: 380,
				lock:true,
				title:"新增",
				data: {window:window},
				content: 'url:app/equipment/base/equipment_supplier_detail.jsp?status=add'
			});
        }
        
        //修改
        function modify(){
			top.$.dialog({
				width: 700,
				height: 380,
				lock:true,
				title:"修改",
				data: {window:window},
				content: 'url:app/equipment/base/equipment_supplier_detail.jsp?status=modify&id='+Qm.getValueByName("id")
			});
        } 
        
        //删除
        function del(){
            $.del("确定要删除?删除后无法恢复！","equipment2SupplierAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
        }      
        
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
	</head>
	<body>	
		<qm:qm pageid="TJY2_EQUIP_SUPPLIER" singleSelect="false">
		</qm:qm>
	</body>
</html>
