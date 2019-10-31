<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%
CurrentSessionUser user=SecurityUtil.getSecurityUser();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报检</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">

	var flag = "${param.flag}";
	
	var unitcode = "";
	
	var userId = "<%=user.getId()%>";
    var loginName = "<%=user.getName()%>";
	var	bar =[{ text:'接收任务', id:'sub',icon:'check', click: sub}
			,{ text:'退回任务分配', id:'back',icon:'back', click: back}
			];
	var	condition=[//{name : "inc_op_id", compare : "=", value : userId},
		           //{name : "check_op_ids", compare : "like", value : userId ,logic: "or"},
		           {name : "check_op_ids", compare : "like", value : userId },
		           {name : "is_rec", compare : "in", value : "(1,3)",logic: ") and( "},{name : "is_plan", compare : "=", value : 2 ,logic: ") and( "}];
	var	field = [{name:"is_plan",compare:"=",value:""},
		         {name:"com_name",compare:"like",value:""},
		         {name:"inspection_type",compare:"=",value:""}];
 	   
	var qmUserConfig = {
			sp_fields:field,
		    tbar:bar,
		    listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                	Qm.setTbar({add:count==1,sub:count>0});
	                	
	                },
	                afterQmReady:function(){
	                	Qm.setCondition(condition);
	                	Qm.searchData();
	                },
		            rowAttrRender : function(rowData, rowid) {
		            	var is_plan = rowData.is_plan;
		            	//var fontColor="#8B008B";
		            	var is_rec = rowData.is_rec;
		            	var fontColor="#000000";
		            	if(is_plan=="已分配"){
	            			fontColor="blue";
	            		}
		            	if(is_rec=="3"){
		            		fontColor="green";
		            	}
	            		return "style='color:"+fontColor+"'";
	            	}
	            }
	};
	function sub(){
		var hall_ids = Qm.getValuesByName("id").toString();
		/*
		var op_name=Qm.getValuesByName("op_name");
		for(var i in op_name){
			if(op_name[i]!=loginName){
				$.ligerDialog.alert('不能提交非负责人数据！');
				return;
			}
		}*/
		$.ligerDialog.confirm('是否接受检验任务？', function (yes) { if(yes){
			$.getJSON("reportHallParaAction/subInspection.do?ids="+Qm.getValuesByName("id"), function(resp){
				if(resp.success){
					top.$.notice("提交成功！");
					submitAction();
				}else{
					$.ligerDialog.alert(resp.message, "提示");
				}
			})
			
		}})
	}	
		
	function add(){
	   var plan = Qm.getValuesByName("is_plan");
		
		if(plan=='未分配'){
			
			top.$.notice("任务已撤销，还未分配，请重新选择！");
			
			return;
			
		}
		var device_type = Qm.getValueByName("devicetype").toString();
		var check_type = Qm.getValueByName("check_type").toString();
		$.getJSON("baseFlowConfigAction/getFlow.do?device_type="+device_type+"&check_type="+check_type, function(resp){
			
			if(resp.success){
			
				getFlow(resp.data);
			}else{
				$.ligerDialog.alert(resp.message, "提示");
			}
		})
	}
	function getFlow(flow_code){
		var unitId="<sec:authentication property='principal.department.id'/>";
		var device_type = Qm.getValueByName("devicetype").toString();
	
		var para_id = Qm.getValueByName("id").toString();
		var com_name = Qm.getValueByName("com_name").toString();
		
		getServiceFlowConfig(flow_code,unitId,function(result,flowData){
			if(result){
				top.$.dialog({
					width : 1150, 
					height : 680, 
					lock : true, 
					title:"报检信息",
					content: 'url:app/tzsb/inspection/flow/insing/insper/report_insper.jsp?status=add&device_type='
							+device_type+'&unit_id='+unitId+'&check_type='
							+Qm.getValueByName("check_type")+'&acc_no='+Qm.getValueByName("hall_no")+'&flowId='+flowData.id+"&para_id="+para_id,
					data : {"window" : window,"com_name":com_name}
				});
			}else{
				$.ligerDialog.alert('没有找到相应流程！', "提示");
			}
		});	
	}	
	
	
	function back(){
		$.getJSON("reportHallParaAction/getInspectCount.do?hall_para_id="+Qm.getValueByName("id"), function(resp){
			if(resp.success){
				if(resp.data > 0){
					$.ligerDialog.confirm('该任务已经进行过报检，确定退回任务分配?', function (yes) { 
						if(yes){
							returnAssign();
						}
					});
				} else {
					$.ligerDialog.confirm('确定退回任务分配?', function (yes) { 
						if(yes){
							returnAssign();
						}
					});
				}
			}else{
				$.ligerDialog.alert(resp.message, "提示");
			}
		})
	}
	
	function returnAssign(){
		$.getJSON("reportHallParaAction/returnAssign.do?hall_para_id="+Qm.getValueByName("id"), function(resp){
			if(resp.success){
				top.$.notice("退回任务分配成功！");
				submitAction();
			}else{
				$.ligerDialog.alert(resp.message, "提示");
			}
		})
	}
	function info(){
		top.$.dialog({
			width : 1100, 
			height : 600, 
			lock : true, 
			title:"报检历史记录",
			content: 'url:app/tzsb/inspection/flow/insing/insper/report_old_list.jsp?status=add&unit_id=<sec:authentication property='principal.department.id'  />&check_type='+Qm.getValueByName("check_type")+'&acc_no='+Qm.getValueByName("hall_no"),
			data : {"window" : window}
		});
	}
	function submitAction(o) {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="blue">“蓝色”</font>代表报检信息已经分配。
				<font color="green">“绿色”</font>代表报检信息已经接受。
			</div>
		</div>
	</div>
	<qm:qm pageid="tzsb_hall_task_list" script="false" singleSelect="false" seachOnload="false"  >
	</qm:qm>
</body>
</html>
