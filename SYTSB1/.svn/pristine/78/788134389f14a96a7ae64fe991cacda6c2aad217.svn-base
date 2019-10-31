<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>单位信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
						{name:"type",compare:"like",value:""}
		            ],
	            tbar:[
					{ text:'选择', id:'check',icon:'check', click:selectOk},
					"-",
					{ text:'新增', id:'add',icon:'add', click: add},
					"-",
				
					{ text:'修改', id:'modify',icon:'modify', click: modify},
					"-",
				
					{ text:'删除', id:'del',icon:'delete', click: del}
					
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({modify:count==1,detail:count==1,del:count>0,device:count==1,check:count==1});
	                }
	            }
	};
	
	var org_id = null;
	function add(){
		
			top.$.dialog({
				width : 500, 
				height : 400, 
				lock : true, 
				title:"新增",
				content: 'url:app/fwxm/contract/custom/custom_type_detail.jsp?status=add',
				data : {"window" : window}
			});
		
	}
	
	function selectOk (){
		var id = Qm.getValuesByName("id");
		var type = Qm.getValuesByName("type");
		api.data.window.selectTypeBack(id,type);
		api.close();
	}
	
	function modify(){
		top.$.dialog({
			width : 500, 
			height : 400, 
			lock : true, 
			title:"修改",
			content: 'url:app/fwxm/contract/custom/custom_type_detail.jsp?status=modify&id='+Qm.getValueByName("id")+"&flag=2&device_type="+Qm.getValueByName("big_class"),
			data : {"window" : window}
		});
	}
	function del(){
		  $.del("确定删除?",
		    		"contractCustomTypeAction/delete.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
    }
	
	function detail(){
		
		top.$.dialog({
			width : 500, 
			height : 400, 
			lock : true, 
			title:"详情",
			content: 'url:app/fwxm/contract/custom/custom_type_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	
	
	
	function loadGridData(nodeId,unitId,url){
		
		/* org_id=${param.type};
		
		
			
				
				Qm.setCondition([{name:"enter_type",compare:"=",value:nodeId}]);
			
			  			
		
		Qm.searchData(); */
	}
	
	function submitAction(o) {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="contract_custom_type" script="false" singleSelect="false">
	</qm:qm>
</body>
</html>
