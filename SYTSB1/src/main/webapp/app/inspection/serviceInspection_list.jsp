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
	
	var userId = "<sec:authentication property='principal.name' htmlEscape='false' />";
	
	
	var bar =[];
	
	var condition=[];
	//科室报检
	if(flag=="1"){
 	   bar =[{ text:'报检', id:'add',icon:'add', click: add},"-",
 	        { text:'修改', id:'modify',icon:'modify', click: modify},"-",
 	       { text:'删除', id:'del',icon:'delete', click: del},"-",
 	      //{ text:'作废', id:'cancel',icon:'cancel', click: cancel},"-",
 	     { text:'提交', id:'submit',icon:'submit', click: submit}
 	   ]
 	  unitcode = 	<sec:authentication property='principal.department.id' htmlEscape='false' />
 	  condition=[{name : "enter_op", compare : "=", value : userId}]
 	   
 	 //任务分配
    }else if(flag=="2"){
       bar =[{ text:'分配', id:'plan',icon:'add', click: plan},{ text:'退回', id:'backINfo',icon:'back', click: backINfo}];
     
     <sec:authorize access="hasRole('rwfp')">
     	 unitcode = 	<sec:authentication property='principal.department.id' htmlEscape='false' />
    	condition=[{name : "check_unit_id", compare : "=", value : unitcode}]
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
	                    Qm.setTbar({transfer:count==1,plan:count>0,modify:count==1,del:count>0,submit:count==1});
	                },
	                afterQmReady:function(){
	                	
	                	
	                	Qm.setCondition(condition);
	                	
	                	Qm.searchData();
	                }
	            }
	};
	function	modify(){
		var checkT=Qm.getValueByName("check_type_code");
		var deviceT=Qm.getValueByName("device_sort_code");
		top.$.dialog({
			width : 1071, 
			height : 660, 
			lock : true, 
			title:"修改报检信息",
			content: 'url:app/inspection/serviceDepartment_detail.jsp?status=modify&org_id='+unitcode+'&checkT='+checkT+'&deviceT='+deviceT.substr(0,1)+'&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
function	del(){
	 $.del("确定删除?", "department/basic/del.do",{"ids":Qm.getValuesByName("id").toString()});

	}
	
function	submit(){
	if(confirm("确认提交此任务？")){
			$("body").mask("正在提交数据，请稍后！");
			$("#submit").attr("disabled","disabled");
			$.getJSON("department/basic/subDep.do?ids="+Qm.getValueByName("id")+"&checktypes="+Qm.getValuesByName("check_type_code"), function(resp){
				//top.$.notice("提交成功！");
			if(resp){
				$("body").unmask();
				$.ligerDialog.alert(resp.msg, "提示");
				submitAction();
			}
		})
	}
	
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
function backINfo(){
	var selectedId = Qm.getValuesByName("id");
	if (selectedId.length < 1) {
		$.ligerDialog.alert('请先选择要退回的事项！', "提示");
		return;
	}
	
	 
	 $.ligerDialog.confirm('确定回退到科室报检?', function (yes) { 

			if(yes){
				
				$.getJSON('department/basic/backINfo.do?infoId='+Qm.getValuesByName("ywid")+'&flow_num='+Qm.getValueByName("FLOW_NUM_ID")+'&acc_id='+Qm.getValuesByName("activity_id"),function(data){
					
					if(data){
						top.$.notice("退回成功！");
						submitAction();
					}
				
				});
			}
	});
}	
	
function plan(){
	var device_type = Qm.getValueByName("device_sort_code").toString().substr(0,1);
	var check_type = Qm.getValueByName("check_type_code").toString();
	var  ids=Qm.getValuesByName("id").toString();
	var selectedId = Qm.getValuesByName("id");
	if (selectedId.length < 1) {
		$.ligerDialog.alert('请先选择要分配的事项！', "提示");
		return;
	}
	
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

		top.$.dialog({
			width : 900, 
			height : 600, 
			
			lock : true, 
			title:"任务分配",
			content: 'url:app/inspection/servicePlan_detail.jsp?status=modify&org_id='+unitcode+'&id='+Qm.getValuesByName("id")+'&ywid='+Qm.getValuesByName("ywid")+'&acc_id='+Qm.getValuesByName("activity_id")+'&flow_num='+Qm.getValuesByName("FLOW_NUM_ID"),
			data : {"window" : window}
		});
	
}
	
	
function add(){
	
	
	top.$.dialog({
		width : 1071, 
		height : 660, 
		lock : true, 
		title : "业务报检信息", 
		data : {"window" : window},
	content : 'url:app/inspection/serviceDepartment_detail.jsp?status=add&org_id='+unitcode+'&checkT=1&deviceT=2'
	});
}


function plan3(){
	//alert();
	var device_type = Qm.getValueByName("device_sort_code").toString().substr(0,1);
	var check_type = Qm.getValueByName("check_type_code").toString();
	var  ids=Qm.getValuesByName("id").toString();
	var selectedId = Qm.getValuesByName("id");
	if (selectedId.length < 1) {
		$.ligerDialog.alert('请先选择要分配的事项！', "提示");
		return;
	}
	
	
	
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
		alert(flowData.id);
		if(result){
			top.$.dialog({
				width : 1200, 
				height : 500, 
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
//流转过程
function  getFlow(){
	var selectedId = Qm.getValuesByName("ywid");
	if (selectedId.length < 1) {
		$.ligerDialog.alert('请先选择要查看的事项！', "提示");
		return;
	}
	 top.$.dialog({
   			width : 400, 
   			height : 700, 
   			lock : true, 
   			title: "流程卡",
   			content: 'url:department/basic/getFlowStep.do?ins_info_id='+Qm.getValuesByName("ywid"),
   			data : {"window" : window}
   		});

	
}
	

	
	function submitAction(o) {
		Qm.refreshGrid();
	}

	

	
	
</script>
</head>
<body><%String userId=SecurityUtil.getSecurityUser().getId();%>
<% if(request.getParameter("flag").equals("2")){%>
	<qm:qm pageid="Rwfp_list" script="false" singleSelect="false" seachOnload="false"  >
	<qm:param name="handler_id" value="<%=userId%>" compare="=" />
	</qm:qm>
	<%}else{ %>
	<qm:qm pageid="Ywbj_list" script="false" singleSelect="false" seachOnload="false"  >
	
	</qm:qm>
	<%} %>
</body>
</html>
