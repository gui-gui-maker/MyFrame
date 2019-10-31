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
	         	sp_fields:[
					{name:"custom_com_name",compare:"like",value:""},
					{name:"contract_no",compare:"=",value:""}
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
				api.data.parentWindow.callBackContact(Qm.getValueByName("id"),Qm.getValueByName("contract_no"));
				//api.data.parentWindow.$("#fk_base_org_id").val(Qm.getValueByName("id"));
				api.close();

				//window.close();
			}
			
			function add(){
				top.$.dialog({
					width : 700, 
					height : 260, 
					lock : true, 
					title:"新增",
					parent:api,
					content: 'url:app/fwxm/contract/contract_custom_detail.jsp?status=add',
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
		<qm:qm pageid="contract_list" script="false" singleSelect="true"   >
			<qm:param name="id" value="${param.id}" compare="<>" />
		</qm:qm>
	</body>
</html>