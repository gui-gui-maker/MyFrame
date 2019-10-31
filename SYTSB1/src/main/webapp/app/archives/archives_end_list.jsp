<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>档案归档</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var userId = "<sec:authentication property='principal.id' htmlEscape='false' />"
var	bar = [{ text:'报告归档', id:'check',icon:'table-save', click: reportEnd},
	       { text:'查看报告', id:'showReport',icon:'detail', click: showReport},
		   { text:'流转过程', id:'flow_note',icon:'follow', click: getFlow},
		   {text: '编辑附件', id: 'add', icon: 'add', click: add}
             ];
var	step_name="报告归档";
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{ name:"report_sn",compare:"like"},
			{name:"sn",compare:"like"},
			{name:"device_registration_code",compare:"like"},
			{name:"com_name",compare:"like"},
			{name:"flow_note_name",compare:"like"},
			{name:"report_name",compare:"like"}
	    ],
		tbar:bar,
        listeners: {
			selectionChange :function(rowData,rowIndex){
				var up_status = Qm.getValueByName("flow_note_name");
     			var count = Qm.getSelectedCount();//行选择个数
     			if(up_status=="报告归档"){
					Qm.setTbar({add:count>0,check:count>0,flow_note:count==1,showReport:count==1});
				}else{
               	Qm.setTbar({add:count>0,check:count<0,flow_note:count==1,showReport:count==1});
				}
			},
			afterQmReady : function() {
				Qm.setCondition([ 
				                  {name : "handler_id",compare : "=",value : userId}
								]);
				Qm.searchData();
			}
		}
	};
	function add() {
		var id = Qm.getValueByName("id");
		if(!id){
          top.$.notice('请先选择数据！',3);
          return;
      }
		top.$.dialog({
			width: 950,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "添加",
			content: 'url:app/archives/archives_upload.jsp?id=' + id + '&pageStatus=modify'
		});
	}
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
		var id = Qm.getValueByName("id");
		var report_id = Qm.getValueByName("report_type");	// 报告类型
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
	function reportEnd(){

				$.ligerDialog.confirm('确定归档该数据?', function (yes) { 
			if(yes){
				$("body").mask("提交中...");
				$.getJSON('department/basic/flow_reportEnd.do',{
					infoId:Qm.getValuesByName("id").toString(),
					flow_num: Qm.getValueByName("flow_num_id"),
					report_sn: Qm.getValueByName("report_sn"),
					process_id:Qm.getValuesByName("process_id").toString()},
						function(data){
							if(data){
								$("body").unmask();
								top.$.notice("归档成功！",4);
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
	<qm:qm pageid="TJY2_report54" script="false" singleSelect="true" seachOnload="false">
		
	</qm:qm>
</body>
</html>
