<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>设备信息</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="app/common/js/render.js"></script>
		<script type="text/javascript">
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
            	{name:"eq_name", compare:"like"},
				{name:"eq_no", compare:"like"},
					{group:[
						{name:"eq_instock_date", compare:">=", value:""},
						{label:"到", name:"eq_instock_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]}
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
	        		},				
	                rowAttrRender : function(rowData, rowid) {
	            		var fontColor="red";            		
	            		//显示红色
	            		return "style='color:"+fontColor+"'";
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
	
        
        function detail(){
				id = Qm.getValueByName("id").toString();
			top.$.dialog({
				width:700,
				height:590,
				lock:true,
				title:"查看详情",
				content: 'url:app/equipment/base/equipment_basic_detail.jsp?status=detail&id=' + id,
				data:{window:window}
			});
		}
        
        //新增
        function add(){
			top.$.dialog({
				width: 700,
				height: 650,
				lock:true,
				title:"新增",
				data: {window:window},
				content: 'url:app/equipment/base/equipment_basic_detail.jsp?status=add'
			});
        }
        
        //修改
        function modify(){
			top.$.dialog({
				width: 700,
				height: 650,
				lock:true,
				title:"修改",
				data: {window:window},
				content: 'url:app/equipment/base/equipment_basic_detail.jsp?status=modify&id='+Qm.getValueByName("id")
			});
        } 
        
        //删除
        function del(){
            $.del("确定要删除？删除后无法恢复！","equipment2Action/delete.do",{"ids":Qm.getValuesByName("id").toString()});
        }      
        
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
	</head>
	
	<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="red">“红色”</font>维修保养中和已维修返件的设备，检验通过后取消翻红。			
			</div>
		</div>
	</div>
	<body>	
		<qm:qm  pageid="TJY2_EQ_WXYJ_LIST" singleSelect="true"></qm:qm>
	</body>
</html>
