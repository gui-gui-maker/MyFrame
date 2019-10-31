<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>任务考核管理</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
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
	                { text:'考核', id:'check',icon:'accept', click: checkTask},
		            "-",
	                { text:'评论', id:'discuss',icon:'discuss', click: discussTask}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count = Qm.getSelectedCount();
						var status = Qm.getValueByName("status").toString();
	                	Qm.setTbar({
	                    	detail: count==1,
	                    	discuss: count==1,
	                    	check: count==1 && status==2
	                    });
	                },
            		rowDblClick :function(rowData,rowIndex){
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
					lock:true,
					title:"查看任务",
					content: 'url:app/task/task_detail.jsp?status=detail&id=' + id
				});
			}
        	
			function checkTask(){
				var tid = Qm.getValueByName("id").toString();
				top.winOpen({
					title: "任务考核",
					width: 500,
					height: 300,
					id: "chkDlg",
					content:"url:app/task/check_task.jsp?id=" + tid
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
        </script>
	</head>
	<body>
		<qm:qm pageid="oa_task_check" script="false" singleSelect="true" />
	</body>
</html>