<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script type="text/javascript" src="k/kui/frame/form.js"></script>
    <script type="text/javascript">
    	var toolbar;
    	var manager;
		$(function () {
			 toolbar=$("#toolbar").ligerToolBar(
             	{
                	items: [
                	"-",
                    {id: "detail", icon: "detail", text: "详情",click:detail},
                	"-",
                    {id: "add", icon: "add", text: "新增",click:add},
                    "-",
                    {id: "modify", icon: "edit", text: "修改",disabled:true,click:modify},
                    "-",
                    {id: "del", icon: "del", text: "删除",disabled:true,click:del},
                    "-",
                    {id: "jy", icon: "cancel", text: "禁用",disabled:true,click:jy},"-",
                    {id: "resume", img: "k/kui/images/icons/16/role2.png", text: "恢复",click:resumeJob,disabled:true},
                    "-",
                    {id: "pause", img: "k/kui/images/icons/16/forbid.png", text: "暂停",click:pause,disabled:true},
                    "-",
                    {id: "updatetime", icon: "time", text: "更新时间",click:updatetime,disabled:true},
                    "->",
                    "-",
                    {id:'runnow', icon: "refresh", text: "立即执行一次",click:runnow,disabled:true}
                	]
            	}  
            );
			manager = $("#plantask").ligerGrid({
				columns:[
					{name:'id',display:'id',width: '3%',hide:true},
					{name:'jobStatus',display:'状态',width: '10%'},
					{name:'jobName',display:'任务名',width: '15%',align:'left'},
					{name:'jobGroup',display:'任务组',width: '15%',align:'left'},
					{name:'isConcurrent',display:'任务类型',width: '10%',align:'center',render:function(row){
						if(row.isConcurrent=='0'){
							return '同步任务';
						}else{
							return '异步任务';
						}
					}},
					{name:'cronExpression',display:'时间表达式',width: '15%',align:'left'},
					{name:'createTime',display:'创建时间',width: '17%',type:'date',format:'yyyy-MM-dd hh:mm:ss'},
					{name:'lastRunTime',display:'上次执行时间',width: '17%',type:'date',format:'yyyy-MM-dd hh:mm:ss'},
					{name:'runTimes',display:'执行次数',width: '8%'},
					{name:'description',display:'备注',width: '30%',align:'left',render:function(row){
						return "<div title='"+row.description+"'>"+row.description+"</div>"
					}}
				],
		        enabledSort: false,
		        allowHideColumn: false, 
		        rownumbers: true,                         //是否显示行序号
		        frozenRownumbers: false,
		        usePager: false,
		       	url:'pub/scheduleJob/getAllJob.do',
		        height:$(window).height()-$(".item-tm").height()-3,
		       	root:'data',
		       	onSelectRow:function(row){
		       	   var status = row.jobStatus;
		       	   var id = row.id;
                   toolbar.setEnabled({detail:!$.kh.isNull(id),modify:!$.kh.isNull(id),jy:!$.kh.isNull(id),del:true,resume:(status=="PAUSED"),pause:(status!="PAUSED"),updatetime:!$.kh.isNull(id),runnow:true});
                },
                onUnSelectRow:function(row){
                    toolbar.setEnabled({detail:false,modify:false,del:false,jy:false,resume:false,pause:false,updatetime:false,runnow:false});
                }
			});
		});
		
		function jy(){
			 var row = manager.getSelectedRow();
			 $.ligerDialog.confirm("确认禁用选中任务？", function (yes) {
			 	if(yes){
			 		$.getJSON("pub/scheduleJob/jyJob.do",{jobName:row.jobName,jobGroup:row.jobGroup},function(res){
			 			if(res.success){
			 				manager.loadData();
			 			}
			 		})
			 	}
			 })
		}
		function add(){
			var windows=top.$.dialog({
				width:600,
				height:400,
				lock:true,
				title:"新增任务",
				content: 'url:pub/scheduleJob/job_detail.jsp?status=add'
				,data:{"window":window}
			});
		}
		function refreshGrid(){
			manager.loadData();
		}
		function detail(){
			var row = manager.getSelectedRow();
			var windows=top.$.dialog({
				width:600,
				height:400,
				lock:true,
				title:"任务详情",
				content: 'url:pub/scheduleJob/job_detail.jsp?status=detail&id='+row.id
				,data:{"window":window}
			});
		}
		function modify(){
			var row = manager.getSelectedRow();
			var windows=top.$.dialog({
				width:600,
				height:400,
				lock:true,
				title:"新增任务",
				content: 'url:pub/scheduleJob/job_detail.jsp?status=modify&id='+row.id
				,data:{"window":window}
			});
		}
		function del(){
			var row = manager.getSelectedRow();
			 $.ligerDialog.confirm(kFrameConfig.DEL_MSG, function (yes) {
			 	if(yes){
			 		$.getJSON("pub/scheduleJob/deleteJob.do",{jobName:row.jobName,jobGroup:row.jobGroup},function(res){
			 			if(res.success){
			 				manager.loadData();
			 			}
			 		})
			 	}
			 })
		}
		
		function runnow(){
			 var row = manager.getSelectedRow();
			 $.ligerDialog.confirm("您确定要立即执行一次该任务？", function (yes) {
			 	if(yes)
			 	$.getJSON("pub/scheduleJob/runAJobNow.do",{jobName:row.jobName,jobGroup:row.jobGroup},function(res){
			 		if(res.success){
			 			manager.loadData();
			 		}
			 	})
			 })
		}
		
		function updatetime(){
			var row = manager.getSelectedRow();
			var windows=top.$.dialog({
				width:400,
				height:100,
				lock:true,
				title:"任务时间表达式",
				content: 'url:pub/scheduleJob/job_time_detail.jsp?id='+row.id+"&jobName="+row.jobName+"&jobGroup="+row.jobGroup+"&cron="+row.cronExpression
				,data:{"window":window}
			});
		}
		function pause(){
		     var row = manager.getSelectedRow();
			 $.ligerDialog.confirm("您确定要暂停所选任务吗？", function (yes) {
			 	if(yes)
			 	$.getJSON("pub/scheduleJob/pauseJob.do",{jobName:row.jobName,jobGroup:row.jobGroup},function(res){
			 		if(res.success){
			 			manager.loadData();
			 		}
			 	})
			 })
		}
		function resumeJob(){
			var row = manager.getSelectedRow();
			 $.ligerDialog.confirm("您确定要恢复所选任务吗？", function (yes) {
			 	if(yes)
			 	$.getJSON("pub/scheduleJob/resumeJob.do",{jobName:row.jobName,jobGroup:row.jobGroup},function(res){
			 		if(res.success){
			 			manager.loadData();
			 		}
			 	})
			 })
		}
    </script>
</head>
<body>
<div class="item-tm">
    <div id="toolbar"></div>
</div>

<div class="scroll-tm"><div id="plantask"></div></div>
</body>
</html>