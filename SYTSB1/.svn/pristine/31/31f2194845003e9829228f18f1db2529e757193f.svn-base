<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>制造监督检验报告信息查询列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
					sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},		
					sp_fields:[
					{name:"made_unit_name",compare:"like"},
					{name:"device_name",compare:"like"}
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
				api.data.window.callBack(Qm.getValuesByName("zzjd_info_id").toString());
				api.close();
			}
			
			function close(){
				api.close();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="report_zzjd_query" script="false" singleSelect="false">
		<%
			String report_type = request.getParameter("report_type");
			if(StringUtil.isNotEmpty(report_type) ){
				%>
				<qm:param name="report_type" value="<%=report_type%>" compare="=" />
				<%
			}
		 %>
		</qm:qm>
	</body>
</html>