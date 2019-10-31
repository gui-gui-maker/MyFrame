<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全施工告知书</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {			
			sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义	
			sp_fields:[					
				{name:"device_sort_code", compare:"="},
				{name:"created_date", compare:"<"},
				{name:"created_date", compare:">"}
		            ],
	            tbar:[
					{ text:'详情', id:'detail',icon:'detail', click: detail},	
					{ text:'添加', id:'add',icon:'add', click: add},	
					{ text:'修改', id:'modify',icon:'modify', click: modify},	
					{ text:'删除', id:'del',icon:'del', click: del}
					//{ text:'两个月前历史记录', id:'history',icon:'detail', click: history}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
                		selectionChange();
                	},
	        		rowDblClick :function(rowData,rowIndex){
	        			detail(rowData.id);
	        		},				
	                rowAttrRender : function(rowData, rowid) {
	           		    var green = 60;	// 2个月内到期
	           		 	var blue = 30;	// 1个月内到期
	           			var red = 0;	// 已过期	           		    
	            		var fontColor="black";            		
	            		//到期60天至30天之间 显示绿色
	            		if (rowData.warning <= green && rowData.warning > blue){
	            			fontColor="green";
	            		}
	                    //到期30天之内 显示蓝色
	            		else if(rowData.warning <= blue && rowData.warning > red ) {
	            			fontColor="blue";
	            		}
	            		//到期 显示红色
	            		else if(rowData.warning <= red) {
	            			fontColor="red";
	            		}
	            		return "style='color:"+fontColor+"'";
	            	}
	
	
	
	            }
	};
	
	// 行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount(); // 行选择个数
		if(count == 1){
	   		Qm.setTbar({detail: true,add:true,modify:true,del:true,history:true});            	
		}else if(count > 1){
	  		Qm.setTbar({detail: false,add:true,modify:false,del:true,history:true});            	
	  	}else{
	  		Qm.setTbar({detail: false,add:true,modify:false,del:false,history:true});            	
		}
	}
	function add(){
		
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true, 
			title:"添加安全告知书",
			content: 'url:app/device/device_notify_detail.jsp?status=add',
			data : {"window" : window}
		});
	}
	function modify(){
		
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true, 
			title:"修改安全告知书",
			content: 'url:app/device/device_notify_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	function del(){
		 url = "device/tzsbapp/delete.do";
		$.del("确定删除?", url, {
			"appIds" : Qm.getValuesByName("id").toString()
		});
	}
	function detail(){
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true, 
			title:"安全告知书详细",
			content: 'url:app/device/device_notify_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
		}
	function history(){
		Qm.setCondition([ {
			name : "created_date",
			compare : "<=",
			value : '2014-1-9',
			dataType:'date'
		} ]);
		Qm.searchData();
		}

		// 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
</script>
</head>
<body>
	<qm:qm pageid="tzsb_application" script="false" singleSelect="false">
	</qm:qm>
</body>
</html>