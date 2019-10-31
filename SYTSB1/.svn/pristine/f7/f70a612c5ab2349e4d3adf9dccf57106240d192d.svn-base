<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>任务管理</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
		<script type="text/javascript">
			var c_user_id = "<sec:authentication property="principal.id" />";
			var qmUserConfig = {
				sp_fields: [
					{name:"title",compare:"like"},
					{name:"p_title",compare:"like"},
					{name:"types",compare:"="},
					{name:"levels",compare:"="},
					{name:"status_txt",compare:"="},
					{name:"stakeholder",compare:"like"},
					{name:"assigner",compare:"like"},
					{group:[
						{name:"begins",compare:">="},
						{name:"begins",compare:"<=",label:"到",labelWidth:20,labelAlign:"center"}
					]},
					{group:[
						{name:"ends",compare:">="},
						{name:"ends",compare:"<=",label:"到",labelWidth:20,labelAlign:"center"}
					]}
				],
	            tbar:[
		            { text:'详情', id:'detail',icon:'detail',click: detail},
		            "-",
	                { text:'新增', id:'add',icon:'add', click: function(){newTask(false);}},
	                { text:'修改', id:'modify',icon:'modify', click: modifyTask},
	                { text:'删除', id:'del',icon:'del', click: deleteTask},
	                "-",
	                { text:'终止', id:'terminal',icon:'forbid', click: terminalTask},
		            "-",
	                { text:'转派', id:'transfer',icon:'userMove', click: transferTask},
		            "-",
	                { text:'分解', id:'resolve',icon:'same', click: function(){
	                	var pid = Qm.getValueByName("id").toString();
	                	var ptitle = Qm.getValueByName("title").toString();
	                	var eid = Qm.getValueByName("stakeholder_id").toString();
	                	newTask(pid,ptitle);
	                }},
		            "-",
	                { text:'反馈', id:'feedback',icon:'feedback', click: feedbackTask},
		            "-",
	                { text:'完成', id:'finish',icon:'right', click: finishTask},
		            "-",
	                { text:'评论', id:'discuss',icon:'discuss', click: discussTask},
		            "->",
	                "-",
	                { text:'我分配的', id:'my_ass_task',icon:'search2', click: listMyAssTask},
		            "-",
	                { text:'我执行的', id:'my_exec_task',icon:'search2', click: listMyExecTask},
		            "-",
	                { text:'我创建的', id:'my_create_task',icon:'search2', click: listMyCreateTask},
		            "-",
	                { text:'所有', id:'all_task',icon:'search2', click: listAllTask}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count = Qm.getSelectedCount();
						var status = Qm.getValueByName("status").toString();
						var eid = Qm.getValueByName("stakeholder_id").toString();
						var aid = Qm.getValueByName("assigner_id").toString();
						var cid = Qm.getValueByName("creater_id").toString();
	                	Qm.setTbar({
	                    	detail: count==1,
	                    	del: (count == 1 && status=="1" && (aid==c_user_id||cid==c_user_id)),
	                    	modify: (count==1 && status=="1" && (aid==c_user_id||cid==c_user_id)),
	                    	terminal: (count==1 && status == "1"),
	                    	resolve: (count==1),
	                    	finish: (count == 1 && status=="1" ),
	                    	/* accept: (count == 1 && eid==c_user_id), */
	                    	transfer: (count == 1 && status=="1"),
	                    	feedback: (count == 1 && status=="1"),
	                    	discuss: count==1
	                    });
	                },
            		rowDblClick : function(rowData,rowIndex){
            			detail(rowData.id);
            		},
            		rowAttrRender: function(rowData){
            			if(rowData.score==0)rowData.score = "-";
            			rowData.progress = rowData.progress + "%";
            			
            			if(rowData.status=='1')return "style='color:red;'";
            			else if(rowData.status=='2')return "style='color:green;'";
            			else if(rowData.status=='3')return "style='color:gray;'";
            		}
	            }
	        };
			
			function detail(id){
				if($.type(id)!="string")
					id = Qm.getValueByName("id").toString();
				top.$.dialog({
					width: 1000,
					height: $(top).height()-100,
					lock: true,
					data: {Qm : Qm},
					title: "查看任务",
					content: 'url:app/task/task_detail.jsp?status=detail&id=' + id
				});
			}
        	
			// 完成任务
			function finishTask(){
				var id = Qm.getValueByName("id").toString();
				top.$.dialog({
					width: 500,
					height: 300,
					title: "完成任务",
					lock: true,
					data: {callback:function(){
						Qm.refreshGrid();
					}},
					content: 'url:app/task/finish_task.jsp?id=' + id
				});
			}
			
			function acceptTask(){
				var id = Qm.getValueByName("id").toString();
				$.getJSON("oa/task/accept.do?taskId=" + id,function(resp){
					if(resp.success){
						top.$.notice("操作成功！",3);
						Qm.refreshGrid();
					}else{
						$.ligerDialog.error("操作失败！<br/>" + resp.msg);
					}
				});
			}
			
			//新增、分解任务
        	function newTask(pid,title){
				top.$.dialog({
					width: 900,
					height: $(top).height()>550?550:$(top).height(),
					lock: true,
					title: pid ? "分解任务" : "新增任务",
					content: 'url:app/task/edit_task.jsp?status=add' + (pid ? "&pid=" + pid : ""),
					data: {Qm : Qm, ptitle : title}
				});
			}
			
			//修改数据
			function modifyTask(){
				top.$.dialog({
					width:900,
					height: $(top).height()>550?550:$(top).height(),
					lock:true,
					title:"修改任务",
					content: 'url:app/task/edit_task.jsp?status=modify&id=' + Qm.getValueByName("id"),
					data:{Qm:Qm}
				});
			}
			
			//删除数据
			function deleteTask(){
			    $.del(kui.DEL_MSG,"oa/task/delete.do",{"id":Qm.getValuesByName("id").toString()});
		    }
			
			//终止任务
			function terminalTask(){
				var tid = Qm.getValueByName("id").toString();
				top.winOpen({
					title: "终止任务",
					width: 500,
					height: 300,
					id: "tmnDlg",
					data: {callback:function(){
						Qm.refreshGrid();
					}},
					content: "url:app/task/terminal_task.jsp?id=" + tid
				});
			}
			function feedbackTask(){
				top.winOpen({
					title: "任务反馈",
					width: 500,
					height: 300,
					lock: true,
					data: {callback:function(){
						Qm.refreshGrid();
					}},
					content:"url:app/task/feedback_task.jsp?id=" + Qm.getValueByName("id") 
							+ "&progress=" + Qm.getValueByName("pg")
				});
			}
			
			//转派任务执行人
			function transferTask(){
				var tid = Qm.getValueByName("id").toString();
				top.winOpen({
					title: "任务转派",
					width: 450,
					height: 250,
					data: {callback:function(){
						Qm.refreshGrid();
					}},
					lock: true,
					content:"url:app/task/transfer_task.jsp?id=" + tid
				});
			}
			
			//添加评论
			function discussTask(){
				var tid = Qm.getValueByName("id").toString();
				top.winOpen({
					title: "任务考核",
					width: 500,
					height: 300,
					id: "chkDlg",
					data: {callback:function(){}},
					content:"url:app/task/discuss_task.jsp?id=" + tid
				});
			}
			
			function listMyAssTask(){
				Qm.setCondition([
					{name: "stakeholder_id", compare: "=", value: ""},
					{name: "assigner_id", compare: "=", value: c_user_id}
				]);
				Qm.searchData();
			}
			
			function listMyExecTask(){
				Qm.setCondition([
					{name: "stakeholder_id", compare: "=", value: c_user_id},
					{name: "assigner_id", compare: "=", value: ""}
				]);
				Qm.searchData();
			}
			function listMyCreateTask(){
				Qm.setCondition([
					{name: "creater_id", compare: "=", value: c_user_id},
					{name: "stakeholder_id", compare: "=", value: ""},
					{name: "assigner_id", compare: "=", value: ""}
				]);
				Qm.searchData();
			}
			function listAllTask(){
				Qm.setCondition([
					{name: "creater_id", compare: "=", value: ""},
					{name: "stakeholder_id", compare: "=", value: ""},
					{name: "assigner_id", compare: "=", value: ""}
				]);
				Qm.searchData();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="oa_task_mine" script="false" singleSelect="true" />
	</body>
</html>