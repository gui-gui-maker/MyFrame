<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String com_ids = request.getParameter("com_ids");
%>
	<head>
		<title>合同查询列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
					sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
					sp_fields:[
					{name:"com_name",compare:"like",value:""}
	            ],
	            tbar:[
			        { text:'选择', id:'select',icon:'check',click:selectValue},
			        "-",
			        { text:'关闭', id:'close',icon:'modify',click:close}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({select:count==1});
	                }
	            }
	        };
        	
			function selectValue(){	
				api.data.parentWindow.callBackContract(Qm.getValueByName("id"),Qm.getValueByName("contract_no"));
				api.close();
			}
			
			
			function close(){
				api.close();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="contract_list" singleSelect="true">
			<qm:param name="data_status" value="0" compare="=" />
		</qm:qm>
	</body>
</html>