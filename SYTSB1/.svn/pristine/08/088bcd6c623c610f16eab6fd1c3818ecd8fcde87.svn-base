<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报检信息列表</title>

<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">

	var flag = "${param.flag}";
	
	var unitcode = "";
	
	var userId = "<sec:authentication property='principal.sysUser.employee.id' htmlEscape='false' />";
	
	
	var bar =[];
	
	var condition=[];
	//科室报检
	if(flag=="1"){
 	   bar =[{ text:'报检', id:'add',icon:'add', click: add},"-",
 	        { text:'修改', id:'modify',icon:'modify', click: modify},"-",
 	       { text:'删除', id:'del',icon:'delete', click: del},"-",
 	      { text:'作废', id:'cancel',icon:'cancel', click: cancel},"-",
 	     { text:'提交', id:'submit',icon:'submit', click: submit},"-",
 	   { text:'历史记录', id:'detail',icon:'detail', click: info}
 	   ]
 	 
 	   
 	 //任务分配
    }else if(flag=="2"){
       bar =[{ text:'分配', id:'plan',icon:'add', click: plan},{ text:'转送', id:'transfer',icon:'back', click: transfer}]
      <sec:authorize access="hasRole('py')">
      	 unitcode="100005";
      	 condition=[{name : "unit_code", compare : "=", value : unitcode}]
      </sec:authorize>
      <sec:authorize access="hasRole('luojuyong')">
      	 unitcode="100012";
     	 condition=[{name : "unit_code", compare : "=", value : unitcode}]
     </sec:authorize>
     <sec:authorize access="hasRole('rwfp')">
     	 unitcode = 	<sec:authentication property='principal.department.id' htmlEscape='false' />
    	// condition=[{name : "unit_code", compare : "=", value : unitcode}]
     </sec:authorize>
       
      //报检历史记录
    } else{
 	   bar =[{ text:'返回', id:'return',icon:'return', click: back}]  
 	   condition=[{name : "unit_code", compare : "=", value : unitcode},{name : "is_rec", compare : "=", value : flag}]
	}
	
	var qmUserConfig = {
			sp_fields:[
					{name:"check_type",compare:"=",value:""}
		            ],
			        tbar:bar,
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                   // Qm.setTbar({transfer:count==1,plan:count>0,add:count==1,modify:count==1,del:count>0});
	                },
	                afterQmReady:function(){
	                	
	                	
	                	Qm.setCondition(condition);
	                	
	                	Qm.searchData();
	                }
	            }
	};
	function	modify(){
		top.$.dialog({
			width : 1071, 
			height : 660, 
			lock : true, 
			title:"修改报检信息",
			content: 'url:app/inspection/serviceDepartment_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
function	del(){
	 $.del("确定删除?",
	    		"department/basic/del.do",
	    		{"ids":Qm.getValuesByName("id").toString()});
	}
	
function	submit(){
	$.getJSON("department/basic/subDep.do?ids="+Qm.getValuesByName("id"), function(resp){
		if(resp.success){
			top.$.notice("提交成功！");
			submitAction();
		}else{
			$.ligerDialog.alert(resp.message, "提示");
		}
	})
}

function	cancel(){
	 $.del("确定作废?",
	    		"department/basic/del.do",
	    		{"ids":Qm.getValuesByName("id").toString()});
}



	
function	transfer(){
	
	top.$.dialog({
		width : 800, 
		height : 300, 
		lock : true, 
		title:"转送",
		content: 'url:app/inspection/transfer_detail.jsp?status=modify&id='+Qm.getValuesByName("id"),
		data : {"window" : window}
	});
	
	
}
	
	
function plan2(){

	
var hall_ids =  Qm.getValuesByName("id").toString();

var temp = hall_ids.split(",")

var same=false;
var tmp ="";
for(var i in temp){
	

	if(tmp==""){
		tmp=temp[i];
	}else{
		
		if(temp[i]!=tmp){
			
			same=true;
			break;
		}
	}
}

if(!same){
	
	
		top.$.dialog({
			width : 900, 
			height : 600, 
			lock : true, 
			title:"任务分配",
			content: 'url:app/inspection/servicePlan_detail.jsp?status=modify&org_id='+unitcode+'&id='+Qm.getValuesByName("id"),
			data : {"window" : window}
		});
	
	}else{
	
		
		top.$.dialog({
			width : 800, 
			height : 600, 
			lock : true, 
			title:"任务分配",
			content: 'url:app/inspection/servicePlan_detail.jsp?status=batch&id='+Qm.getValuesByName("id")+'&org_id='+unitcode,
			data : {"window" : window}
		});
	}
	
}
	
	
function add(){
	
	top.$.dialog({
		width : 1071, 
		height : 660, 
		lock : true, 
		title : "业务报检信息", 
		data : {"window" : window},
	content : 'url:app/inspection/serviceDepartment_detail.jsp?status=add'
	});
}
function plan(){
	//alert();
	var device_type = Qm.getValueByName("device_sort_code").toString();
	var check_type = Qm.getValueByName("check_type_code").toString();
	var  ids=Qm.getValuesByName("id").toString();
	
	
	$.getJSON("flow/config/getFlow.do?device_type="+device_type+"&check_type="+check_type+"&ids="+ids, function(resp){
		
		if(resp.success){
		
			getFlow(resp.data);
		}else{
			$.ligerDialog.alert(resp.message, "提示");
		}
	})
}

function getFlow(flow_code){
	
	var unitId="<sec:authentication property='principal.department.id'/>";
	var device_type = Qm.getValueByName("device_sort_code").toString();
	var com_name = Qm.getValueByName("com_name").toString();
	
	getServiceFlowConfig(flow_code,unitId,function(result,flowData){
		
		if(result){
			top.$.dialog({
				width : 900, 
				height : 600, 
				lock : true, 
				title:"分配信息",
				content: 'url:app/inspection/servicePlan_detail.jsp?com_name='+encodeURIComponent(com_name)+'&status=modify&device_type='+device_type+'&unit_id=<sec:authentication property='principal.department.id'  />&check_type='+Qm.getValueByName("check_type")+'&id='+Qm.getValuesByName("id")+'&flowId='+flowData.id+'&ywid='+Qm.getValuesByName("ywid"),
				data : {"window" : window}
			});
		}else{
			$.ligerDialog.alert('没有找到相应流程！', "提示");
		}
	});	
}	

function back(){
	window.history.back();
}

function info(){
	/**
	top.$.dialog({
		width : 1100, 
		height : 600, 
		lock : true, 
		title:"报检信息",
		content: 'url:app/inspection/department_detail.jsp?status=add&unit_id=<sec:authentication property='principal.department.id'  />&check_type='+Qm.getValueByName("check_type")+'&acc_no='+Qm.getValueByName("hall_no"),
		data : {"window" : window}
	});
	*/
	window.location.href='department_list.jsp?flag=3';


}
	
	

	
	function submitAction(o) {
		Qm.refreshGrid();
	}

	

	
	
</script>
</head>
<body>

	<qm:qm pageid="Ywbj_list" script="false" singleSelect="false" seachOnload="false"  >
	</qm:qm>
</body>
</html>
