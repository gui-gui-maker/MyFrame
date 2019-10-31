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
						{name:"com_code",compare:"like",value:""},
						{name:"com_name",compare:"like",value:""}
		            ],
	            tbar:[
	              
					{ text:'查看', id:'detail',icon:'detail', click: detail},
					"-",
					
					{ text:'新增', id:'add',icon:'add', click: add},
					"-",
				
					{ text:'修改', id:'modify',icon:'modify', click: modify},
					"-",
				
					{ text:'删除', id:'del',icon:'delete', click: del}/* ,
					"-",
				
					{ text:'查看特种设备信息', id:'device',icon:'detail', click: device} */
					
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({modify:count==1,detail:count==1,del:count>0,device:count==1});
	                }
	            }
	};
	
	var org_id = null;
	function add(){
			org_type=org_id;
			//top.$.dialog({
			top.$.dialog({
				width : 800, 
				height : 700, 
				lock : true, 
				title:"新增",
				//skin:"win-flat",//窗口无边框。默认接口
			   // skin:"win-flat noborder close1",//窗口无边框。且有一根线条；关闭按钮在外部
			    //skin:"win-flat close2",//窗口无边框。关闭按钮在内部，大按钮
			    //skin:"win-flat close3",//窗口无边框。关闭按钮在内部；小按钮
			    //skin:"win-flat noborder close-hide",//窗口无边框。关闭按钮不存在
			    //skin:"win-flat noborder max-hide",//窗口无边框。最大化按钮不存在
			    //skin:"win-flat noborder min-hide",//窗口无边框。最小化按钮不存在
				content: 'url:app/fwxm/contract/contract_custom_detail.jsp?status=add',
				data : {"window" : window}
			});
	}
	
	function modify(){
		top.$.dialog({
			width : 800, 
			height : 700, 
			lock : true, 
			title:"修改",
			content: 'url:app/fwxm/contract/contract_custom_detail.jsp?status=modify&id='+Qm.getValueByName("id")+"&flag=2&device_type="+Qm.getValueByName("big_class"),
			data : {"window" : window}
		});
	}
	function del(){
		  $.del("确定删除?",
		    		"contractCustomAction/delete.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
    }
	
	function detail(){
		
		top.$.dialog({
			width : 800, 
			height : 600, 
			lock : true, 
			title:"详情",
			content: 'url:app/fwxm/contract/contract_custom_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
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

	
	function device(){
		top.$.dialog({
			width : 1024, 
			height : 800, 
			lock : true, 
			title:"查看特种设备信息",
			content: 'url:app/fwxm/contract/device_list.jsp?id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	
	
</script>
</head>
<body>
	<qm:qm pageid="contract_custom_list" script="false" singleSelect="false">
	</qm:qm>
</body>
</html>
