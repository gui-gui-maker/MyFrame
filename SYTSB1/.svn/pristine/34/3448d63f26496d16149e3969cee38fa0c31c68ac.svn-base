<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%
	String com_ids = request.getParameter("com_ids");
%>
	<head>
		<title>机构查询列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
					sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},		
					sp_fields:[
					{name:"com_code",compare:"=",value:""},
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
	                    Qm.setTbar({select:count==1});
	                },
	                rowDblClick :function(rowData,rowIndex){
	                api.data.parentWindow.callBack(rowData.id,rowData.module_code,rowData.module_name,rowData.param_01);
					api.close();
	                }
	            }
	        };
        	
			function selectValue(){
				api.data.parentWindow.callBack(Qm.getValueByName("id"),Qm.getValueByName("module_code"),Qm.getValueByName("module_name"),Qm.getValueByName("param_01"));
				api.close();
			}
			
			function add(){
				top.$.dialog({
					width : 800, 
					height : 700, 
					lock : true, 
					title:"新增",
					parent:api,
					content: 'url:app/tzsb/data/company/enter_detail.jsp?status=add',
					data : {"window" : window}
				});
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
		<qm:qm pageid="tjy2_msg_content_m" script="false" singleSelect="true"   >
			
		</qm:qm>
	</body>
</html>