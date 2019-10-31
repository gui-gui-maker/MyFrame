<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>系统用户登录日志</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
		var qmUserConfig = {
            sp_fields:[
				{label:"用户",name:"user_name",compare:"like", columnWidth:0.28, labelWidth: 60},
				{label:"IP",name:"login_ip",compare:"like", columnWidth:0.28, labelWidth: 60},
				{group:[{
					label:"登录时间",name:"login_time",compare:">="
				}, {
					label:"至",name:"login_time",compare:"<=",labelAlign:"center",labelWidth:20
				}], columnWidth:0.44}
            ],
            
			tbar:[
              { text:'删除', id:'del',icon:'delete', click:del }
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
			Qm.setTbar({del : count > 0});
        }
		
		function detail(){};
  
       //删除任务节点功能
       function del(){
          $.del("确定删除?","rbac/userloginLog/delete.do",{"ids":Qm.getValuesByName("id").toString()});
       }    
    </script>
	</head>
	<body>
		<qm:qm pageid="sys_03" script="false" singleSelect="false"/>
		<script test="text/javascript">
		Qm.config.sortInfo= [{field:'login_time',direction:'desc'}];
		</script>
	</body>
</html>
