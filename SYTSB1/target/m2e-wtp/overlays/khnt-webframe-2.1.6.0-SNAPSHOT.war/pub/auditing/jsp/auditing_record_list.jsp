<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>审计日志、记录查看</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
				sp_fields: [
		            {name:"event_name",compare:"like"},
					{name:"operator",compare:"like"},
					{name:"department",compare:"like"},
					{name:"ip_address",compare:"like"},
                    {name:"login_user",compare:"like"},
					{group:[
					    {name:"operate_time",compare:">=",label:"操作时间",type:"date"},
					    {name:"operate_time",compare:"<=",label:"到",labelWidth:20,type:"date",labelAlign:"center"}
					]}
				],
				<sec:authorize ifNotGranted="super_administrate">
					<tbar:toolBar type="tbar" code="sys_auditing_record">
				</tbar:toolBar>,
				</sec:authorize>
				<sec:authorize access="hasRole('super_administrate')">
					tbar:[
              			{ text:'详情', id:'detail',icon:'detail',click:detail},
	                "-",
	                { text:'删除', id:'del',icon:'del',click:remove}
            		],
				</sec:authorize>
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();
	                    Qm.setTbar({detail:count==1,del:count>0});
	                },
            		rowDblClick: detail
	            }
	        };
        	
			function remove(){
			    $.del(kFrameConfig.DEL_MSG,
			    		"khnt/auditing/record/delete.do",
			    		{"ids":Qm.getValuesByName("id").toString()});
		    }
			
			function detail(id){
				top.$.dialog({
					width: 800,
					height: 550,
					lock: true,
					title: "审计记录详情",
					content: 'url:pub/auditing/jsp/auditing_record_detail.jsp?id=' + Qm.getValueByName("id")
				});
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="auditing_record" script="false" singleSelect="false"></qm:qm>
		<script test="text/javascript">
			Qm.config.sortInfo= [{field:'operate_time',direction:'desc'}];
		</script>
	</body>
</html>