<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同查询列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
	         	sp_fields:[
					{name:"contract_no",compare:"like",value:""},
					{name:"project_name",compare:"like",value:""},
					{name:"custom_com_name",compare:"like",value:""}
	            ],
	            tbar:[
			        { text:'选择', id:'select',icon:'check',click:selectValue},
			        "-",
			        { text:'关闭', id:'close',icon:'modify',click:close}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({select:count==1,close:count>0});
	                }
	            }
	        };
        	
			function selectValue(){
				api.data.parentWindow.callBackContract(Qm.getValueByName("id"),Qm.getValueByName("contract_no"),Qm.getValueByName("project_name"),Qm.getValueByName("custom_com_name"),Qm.getValueByName("fk_customer_id"));
				api.close();
			}
			
			function close(){
				api.close();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="contract_list" script="false" singleSelect="true">		
		</qm:qm>
	</body>
</html>