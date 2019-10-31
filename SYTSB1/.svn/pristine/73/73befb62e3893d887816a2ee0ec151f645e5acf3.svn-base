<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报告信息查询列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
					sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
					sp_fields:[
	         		{name:"device_registration_code", id:"device_registration_code", compare:"like"},
	         		{name:"report_sn", id:"report_sn", compare:"like"},
	         		{name:"report_com_name",compare:"like"},
	         		{name:"check_unit_id", id:"check_unit_id", compare:"="},
					{name:"enter_op_name",compare:"like"},
					{group:[
						{name:"advance_time", id:"advance_time", compare:">="},
						{label:"到", name:"advance_time", id:"advance_time1", compare:"<=", labelAlign:"center", labelWidth:20}
					]},
					{name:"flow_note_name", id:"flow_note_name", compare:"="}
	            ],
	            tbar:[
			        { text:'复制', id:'select',icon:'check',click:selectValue},
			        "-",
			        { text:'关闭', id:'close',icon:'modify',click:close}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({select:count>0});
	                }
	            }
	        };
			
			function selectValue(){	
				api.data.window.callBack(Qm.getValuesByName("id").toString());
				api.close();
			}
			
			function close(){
				api.close();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="report_query_list5">
		<%
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			String org_id = user.getDepartment().getId(); 
			if(StringUtil.isNotEmpty(org_id)){
				if(!"100027".equals(org_id) && !"100082".equals(org_id) && !"100031".equals(org_id)){
					%>
					<qm:param name="check_unit_id" value="<%=org_id %>" compare="=" />
					<%
				}	
			}
		%>
		</qm:qm>
		<script type="text/javascript">
		// 根据 sql或码表名称获得Json格式数据
		//Qm.config.columnsInfo.report_id.binddata=<u:dict sql="select id,report_name from base_reports where report_name not like '%梯%'  "></u:dict>;
		Qm.config.columnsInfo.check_unit_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
		Qm.config.columnsInfo.flow_note_name.binddata = [
			{id: '打印报告', text: '封存未打印'},
			{id: '报告领取', text: '封存已打印'}
		];
		</script>
	</body>
</html>