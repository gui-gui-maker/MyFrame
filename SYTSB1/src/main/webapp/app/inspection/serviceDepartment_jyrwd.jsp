<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%
	String com_ids = request.getParameter("com_ids");
%><%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
%>
	<head>
		<title>机构查询列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
					sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
					sp_fields:[
					{name:"sn",compare:"like",value:""},
					{name:"com_name",compare:"like",value:""}
	            ],
	            tbar:[
			        { text:'选择', id:'select',icon:'check',click:selectValue},
			        "-",
			        { text:'关闭', id:'close',icon:'close',click:close}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({select:count==1,close:count>0});
	                }
	            }
	        };
        	
			function selectValue(){
				api.data.parentWindow.jyrwdBack(Qm.getValueByName("id"),Qm.getValueByName("sn"));
				api.close();
			}
			
			
			function close(){
				api.close();
			}
			//列表刷新
			function submitAction(o) {
			    Qm.refreshGrid();
			}
			
        </script>
	</head>
	<body>
	
	<% 
	//String check_type=request.getParameter("check_type");
	String sql="select t.* from CONTRACT_TASK_INFO t where  (org_id='"+user.getDepartment().getId()+"' or cjry_id like '%"+user.getDepartment().getId()+"%')   ORDER BY t.CREATE_DATE DESC ";
	System.err.println(sql);
	%>
	
		<qm:qm pageid="contract_task_bj"  script="false"  sql="<%=sql.toString() %>" >
<%-- 		<qm:param name="com_name" value="" compare="="/> --%>
		</qm:qm>
		<script type="text/javascript">
		 $(function(){
// 			 getData();
		 });
		 function getData() {
					Qm.config.defaultSearchCondition[0].value=decodeURI('${param.com_name }');
// 					Qm.searchData();
			 }
		</script>
	</body>
</html>