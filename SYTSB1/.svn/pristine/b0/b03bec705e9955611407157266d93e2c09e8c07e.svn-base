<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报检信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
						{name:"accept_no",compare:"like",value:""},
						{name:"com_name",compare:"like",value:""},
						{name:"check_type",compare:"=",value:""}
		            ],
	            tbar:[
	              
					{ text:'查看', id:'detail',icon:'detail', click: detail},
					"-",
					
					{ text:'修改', id:'modify',icon:'modify', click: modify},
					"-",
				
					{ text:'退回', id:'del',icon:'delete', click: del}
					
					
					//{ text:'返回', id:'return',icon:'return', click: back},
					
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({modify:count==1,detail:count==1,del:count>0});
	                },
					afterQmReady:function(){
	                	var unitcode = "<sec:authentication property='principal.department.id' htmlEscape='false' />";
	                	var userId = "<sec:authentication property='principal.sysUser.employee.id' htmlEscape='false' />";
	                	Qm.setCondition([{name : "enter_unit_id", compare : "=", value : unitcode},{name : "enter_op_id", compare : "=", value : userId}]);
	                	Qm.searchData();
	                }
	            }
	};
	
	
	
	function modify(){
		top.$.dialog({
			width : 1100, 
			height : 600, 
			lock : true, 
			title:"修改报检信息",
			content: 'url:app/inspection/department_detail.jsp?status=modify&unit_id=<sec:authentication property='principal.department.id'/>&check_type='+Qm.getValueByName("type")+'&id='+Qm.getValueByName("id")+'&acc_no='+Qm.getValueByName("accept_no"),
			data : {"window" : window}
		});
	}
	function del(){
		//判断是否已经录入报告，已经录入报告不能删除
		$.getJSON('department/basic/getRportIs.do?infoId='+Qm.getValueByName("info_id"),function(dataPara){
    		if(dataPara.success){
    			$.ligerDialog.alert("报告已录入，不能退回！");
    		}else{
    			 $.del("确定退回?",
    			    		"department/basic/del.do",
    			    		{"ids":Qm.getValuesByName("info_id").toString()});
    		}
    	});
		
		 
    }
	
	function detail(){
		
	
		top.$.dialog({
			width : 1100, 
			height : 600, 
			lock : true, 
			title:"报检信息",
			content: 'url:app/inspection/department_detail.jsp?status=detail&unit_id=<sec:authentication property='principal.department.id'/>&check_type='+Qm.getValueByName("type")+'&id='+Qm.getValueByName("id")+'&acc_no='+Qm.getValueByName("accept_no"),
			data : {"window" : window}
		});
	}
	
	
	function back(){
		window.history.back();
	}
	
	function submitAction(o) {
		Qm.refreshGrid();
	}

	

	
	
</script>
</head>
<body>
	<qm:qm pageid="inspectionInfo" script="false" singleSelect="false" seachOnload="false" >
	</qm:qm>
</body>
</html>
