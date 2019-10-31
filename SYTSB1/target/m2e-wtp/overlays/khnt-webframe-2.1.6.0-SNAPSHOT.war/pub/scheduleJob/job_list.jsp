<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>系统用户登录日志</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
		var qmUserConfig = {
            sp_defaults:{columnWidth:0.4,labelAlign:'right',labelSeparator:''},// 可以自己定义
            sp_fields:[
				{name:"job_name",compare:"like",value:""},
				{columnWidth:0.5,group:[{label:"创建时间",name:"createtime",compare:">=",value:"",width:"100"},{label:"至",name:"createtime",compare:"<=",value:"",labelAlign:"center",labelWidth:20,width:"100"}]},
            ],
            tbar:[
            	{id:'detail',text:'详情',icon:'detail',click:detail},"-",
            	{id:'add',text:'新增',icon:'add',click:add},"-",
            	{id:'edit',text:'修改',icon:'modify',click:modify},"-",
            	{id:'del',text:'删除',icon:'delete',click:del}
            ],
            listeners: {
                rowClick :function(rowData,rowIndex){},
                rowDblClick :function(rowData,rowIndex){detail();},
                selectionChange :function(rowData,rowIndex){selectionChange()}
            }
        };
        //行选择改变事件
        function selectionChange(){
			var count = Qm.getSelectedCount();//行选择个数
			Qm.setTbar({del : count > 0,add:true,edit:count==1,detail:count==1});
        }
		
		function detail(){
			var windows=top.$.dialog({
				width:600,
				height:400,
				lock:true,
				title:"详情",
				content: 'url:pub/scheduleJob/job_detail.jsp?status=detail&id='+Qm.getValueByName("id")
				,data:{"window":window}
			});
		};
		function add(){
			var windows=top.$.dialog({
				width:600,
				height:400,
				lock:true,
				title:"新增定时任务",
				content: 'url:pub/scheduleJob/job_detail.jsp?status=add'
				,data:{"window":window}
			});
		};
		function modify(){
			var windows=top.$.dialog({
				width:600,
				height:400,
				lock:true,
				title:"修改定时任务",
				content: 'url:pub/scheduleJob/job_detail.jsp?status=modify&id='+Qm.getValueByName("id")
				,data:{"window":window}
			});
		};
  
       //删除任务节点功能
       function del(){
          $.del(kFrameConfig.DEL_MSG,"pub/scheduleJob/delete.do",{"ids":Qm.getValuesByName("id").toString()});
       }    
    </script>
	</head>
	<body>
		<qm:qm pageid="scheduleJob" script="false" singleSelect="false">
		</qm:qm>
	</body>
</html>
