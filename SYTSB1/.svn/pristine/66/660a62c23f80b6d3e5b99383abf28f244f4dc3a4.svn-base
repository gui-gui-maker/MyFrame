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
					{name:"com_name",compare:"like",value:""},
					{name:"com_code",compare:"=",value:""}
	            ],
	            tbar:[
			        { text:'选择', id:'select',icon:'check',click:selectValue},
			        "-",
			        { text:'新增', id:'add',icon:'add',click:add},
			        "-",
					{ text:'修改', id:'modify',icon:'modify', click: modify},
					"-",
			        { text:'关闭', id:'close',icon:'close',click:close}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({select:count==1,close:count>0,modify:count==1});
	                }
	            }
	        };
        	
			function selectValue(){
				api.data.parentWindow.callBack(Qm.getValueByName("id"),Qm.getValueByName("com_name"));
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
			
			function modify(){
				top.$.dialog({
					width : 800, 
					height : 700, 
					lock : true, 
					title:"修改",
					content: 'url:app/fwxm/contract/contract_custom_detail.jsp?status=modify&id='+Qm.getValueByName("id")+"&flag=2&device_type="+Qm.getValueByName("big_class"),
					data : {"window" : window}
				});
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="contract_custom_list" script="false" singleSelect="true"   >
			
		</qm:qm>
	</body>
</html>