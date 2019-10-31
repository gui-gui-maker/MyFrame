<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>任务分配</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	<%
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String user_account = user.getSysUser().getAccount();
	%>
	var flag = "${param.flag}";
	var unitcode = "";
	var	bar =[{ text:'分配任务', id:'plan',icon:'add', click: planOper},"-",
	   	   	  //{ text:'按小组分配', id:'plan',icon:'add', click: planGroup},"-",
		      { text:'转送', id:'transfer',icon:'back', click: transfer},"-",
		      /* { text:'撤回', id:'cancel',icon:'nav-prev', click: cancel},"-", */
		      { text:'作废', id:'del',icon:'delete', click: del},"-", 
		      {text:'退回大厅',id:'backHall',icon:'back',click:backHall}];
	var	unitcode = 	<sec:authentication property='principal.department.id' htmlEscape='false' />
	var	condition=[{name : "unit_code", compare : "=", value : unitcode}/* ,{name : "is_plan", compare : "=", value : 1} */];
	var	field = [{name:"com_name",compare:"like",value:""},
		         {name:"inspection_type",compare:"=",value:""}];
       
	var qmUserConfig = {
			sp_fields:field,
		    tbar:bar,
		    listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                	Qm.setTbar({transfer:count==1,plan:count>0,backHall:count==1,cancel:count>0,del:count>0,planGroup:count==1});
	                	
	                },
	                afterQmReady:function(){
	                	<%
	                		if(!"admin".equals(user_account)){
	                			%>
	                			Qm.setCondition(condition);
	                			<%
	                		}
	                	%>
	                	
	                	Qm.searchData();
	                },
		            rowAttrRender : function(rowData, rowid) {
		            	var flag = rowData.is_plan;
		            	//var fontColor="#8B008B";
		            	var fontColor="#000000";
		            	if(flag=="已分配"){
	            			fontColor="blue";
	            		}
	            		return "style='color:"+fontColor+"'";
	            	}
	            }
	};

	
		function transfer(){
			top.$.dialog({
				width : 500, 
				height : 300, 
				lock : true, 
				title:"转送",
				content: 'url:app/insing/plan/report_transfer.jsp?status=modify&id='+Qm.getValuesByName("id"),
				data : {"window" : window}
			});
			
			
		}
			
		function planOper(){
		var hall_ids =  Qm.getValuesByName("hall_id").toString();
		var plan = Qm.getValuesByName("is_plan").toString();
		if(plan.indexOf('已分配')!=-1){
			top.$.notice("所选记录有已经分配任务的信息，请重新选择！");
			return;
		}
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
					width : 800, 
					height : 650, 
					lock : true, 
					title:"任务分配",
					content: 'url:app/insing/plan/report_planning.jsp?status=modify&org_id='+unitcode+'&id='+Qm.getValuesByName("id")+'&hall_id='+tmp,
					data : {"window" : window}
				});
			
			}else{
				top.$.dialog({
					width : 800, 
					height : 650, 
					lock : true, 
					title:"任务分配",
					content: 'url:app/insing/plan/report_planning.jsp?status=batch&id='+Qm.getValuesByName("id")+'&org_id='+unitcode,
					data : {"window" : window}
				});
			}
			
		}
		function planGroup(){
		var hall_ids =  Qm.getValuesByName("hall_id").toString();
		var plan = Qm.getValuesByName("is_plan").toString();
		if(plan.indexOf('已分配')!=-1){
			top.$.notice("所选记录有已经分配任务的信息，请重新选择！");
			return;
		}
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
					width : 800, 
					height : 600, 
					lock : true, 
					title:"任务分配",
					content: 'url:app/insing/plan/group/report_groupplan.jsp?status=modify&org_id='+unitcode+'&id='+Qm.getValuesByName("id")+'&hall_id='+tmp,
					data : {"window" : window}
				});
			
			}else{
				top.$.dialog({
					width : 800, 
					height : 600, 
					lock : true, 
					title:"任务分配",
					content: 'url:app/insing/plan/group/report_groupplan.jsp?status=batch&id='+Qm.getValuesByName("id")+'&org_id='+unitcode,
					data : {"window" : window}
				});
			}
			
		}
		function backHall(){
			var hall_id = Qm.getValuesByName("hall_id");
			var plan = Qm.getValuesByName("is_plan");
			if(plan=='已分配'){
				top.$.notice("已经分配任务，请重新选择！");
				return;
			}
			
			$.ligerDialog.prompt('确定退回该数据？退回请填写原因！',true, function (yes,value) {
				if(yes){
					$.getJSON("reportHallAction/backHall.do?hall_id="+hall_id+"&back_reason="+encodeURIComponent(value), function(resp){
						if(resp.success){
							top.$.notice("退回成功！");
							submitAction();
						}else{
							$.ligerDialog.alert(resp.message, "提示");
						}
					})
				}; 
			});
		}
		
		function back(){
			window.history.back();
		}
		
		function submitAction(o) {
			Qm.refreshGrid();
		}
		function cancel(){
		var hall_id = Qm.getValuesByName("id");
		var plan = Qm.getValuesByName("is_plan");
		//var nums = Qm.getValueByName("nums");
			
			if(plan=='未分配'){
				
				top.$.notice("未分配任务，不需要撤回！");
				
				return;
				
			}
			/* if(nums !=0){
		     alert("此任务已报检，不能撤回！");
				
				return;
			} */
			$.ligerDialog.confirm('确定撤回该数据?', function (yes) { 
				if(yes){
					$.getJSON("reportTaskPlanAction/cancelPlan.do?hall_id="+hall_id, function(resp){
						
						if(resp.success){
						
							top.$.notice("撤回成功！");
							submitAction();
						}else{
							$.ligerDialog.alert(resp.message, "提示");
						}
					})
				}
			});
			
		}
	
		function del(){
			  $.del("确定作废?",
			    		"reportHallAction/del.do",
			    		{"ids":Qm.getValuesByName("hall_id").toString()});
	    }
</script>
</head>
<body>
<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="blue">“蓝色”</font>代表报检信息已经分配。
			</div>
		</div>
	</div>
	<qm:qm pageid="tzsb_hall_task_list" script="false" singleSelect="false" seachOnload="false"  >
	</qm:qm>
</body>
</html>
