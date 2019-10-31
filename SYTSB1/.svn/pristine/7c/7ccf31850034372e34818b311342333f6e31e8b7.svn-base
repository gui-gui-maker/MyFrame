<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告打印</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var userId = "<sec:authentication property='principal.id' htmlEscape='false' />"



var	bar = [{ text:'查看报告', id:'showReport',icon:'detail', click: showReport},"-",
          	{ text:'打印报告', id:'print',icon:'print', click: doPrintReport},"-",
          	{ text:'流转过程', id:'flow_note',icon:'follow', click: getFlow},"-",
          	{ text:'退回修改', id:'returnIput',icon:'return', click: backInput}];
var	step_name="报告打印";




	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"sn",compare:"="},
			{name:"device_registration_code",compare:"like"},
			{name:"com_name",compare:"like"}
	    ],
		tbar:bar,
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({print:count==1,check:count==1,flow_note:count==1,showReport:count==1,input:count==1});
			},
			
			afterQmReady : function() {

				Qm.setCondition([ {
					name : "activity_name",
					compare : "=",
					value : step_name
				},{
							name : "handler_id",
							compare : "=",
							value : userId
						}
				
				
				]);

				Qm.searchData();
			},
			
			
			
		}
	};


	//流转过程
	function  getFlow(){
		
		 top.$.dialog({
	   			width : 400, 
	   			height : 700, 
	   			lock : true, 
	   			title: "流程卡",
	   			content: 'url:department/basic/getFlowStep.do?ins_info_id='+Qm.getValuesByName("id"),
	   			data : {"window" : window}
	   		});
	
		
	}
	// 查看报告
	function showReport(){	
		var id = Qm.getValueByName("id").toString();
		var report_id = Qm.getValueByName("report_type").toString();	// 报告类型
		//var is_user_defined = Qm.getValueByName("is_user_defined").toString();	// 自定义报告
		//if(is_user_defined==""){
			var w=window.screen.availWidth;
			var h=(window.screen.availHeight);
			var url = "report/query/showReport.do?id="+id+"&report_id="+report_id;
			top.$.dialog({
				width : w, 
				height : h, 
				lock : true, 
				title:"报告信息",
				content: 'url:'+url,
				data : {"window" : window}
			}).max();
			//var fileValue = window.showModalDialog(url,[],"dialogwidth:"+w+";dialogheight:"+h+";help=no;status=no;center=yes;edge=sunken;resizable=yes");
	
	}
		
	// 打印报告
	function doPrintReport(){	
		var id = Qm.getValueByName("id").toString();
		var report_id = Qm.getValueByName("report_type").toString();	// 报告类型
		//var is_user_defined = Qm.getValueByName("is_user_defined").toString();	// 自定义报告
		//if(is_user_defined==""){
			
		
			var w=window.screen.availWidth;
			var h=(window.screen.availHeight);			
			top.$.dialog({
				width : w, 
				height :h, 
				lock : true, 
				title:"打印报告",

				content: 'url:app/query/report_print_index.jsp?id='+id+'&acc_id='+Qm.getValuesByName("activity_id")+'&flow_num='+Qm.getValueByName("flow_note_id"),


				data : {"window" : window}
			}).max();
			//url = "/cfdinfo?CONTROLLER=com.khnt.command.BlankSecuredCmd&NEXT_PAGE=TSJY_REPORT_PRINT_INDEX&id="+ids;
			//window.showModalDialog(url,[],"dialogwidth:"+w+";dialogheight:"+h+";help=no;status=no;center=yes;edge=sunken;resizable=yes");
		//} else {
		//	$.ligerDialog.alert("自定义报告请通过“查看报告”按钮下载报告文档进行打印");
		//}
	}
	function reportEnd(){
		
		
		$.ligerDialog.confirm('确定归档该数据?', function (yes) { 

			if(yes){
				
				$.getJSON('department/basic/flow_reportEnd.do',{infoId:Qm.getValueByName("id"),flow_num: Qm.getValueByName("flow_note_id"),process_id:Qm.getValueByName("process_id")},function(data){
					
					if(data){
						top.$.notice("归档成功！");
						submitAction();
					}
				
				});
				
				
			}
		
		
	});
	
		
	}
	
function backInput(){
		
		
		$.ligerDialog.confirm('确定回退修改?', function (yes) { 

			if(yes){
				
				$.getJSON('department/basic/flow_returnInput.do',{infoId:Qm.getValueByName("id"),flow_num: Qm.getValueByName("flow_note_id"),acc_id:Qm.getValueByName("activity_id")},function(data){
					
					if(data){
						top.$.notice("退回成功！");
						submitAction();
					}
				
				});
				
				
			}
		
		
	});
		
		
	}
	// 刷新Grid
	function submitAction() {
   		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="report_end_list" script="false" singleSelect="false" seachOnload="false">
		
	</qm:qm>
</body>
</html>
