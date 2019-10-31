<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String org_id = request.getParameter("org_id");
%>
	<head>
		<title>缴费单位查询列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
					sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
					sp_fields:[
					{name:"pact_name",compare:"like",value:""}
					
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
				//var id = Qm.getValueByName("id");	
				//if(id != "${param.com_id}"){
				//	alert("您所选的缴费单位与报检单位不一致，请重新选择！");
				//	return;
				//}else{
					api.data.parentWindow.callBackPact(Qm.getValueByName("id"),Qm.getValueByName("pact_name"));
					api.close();
				//}
			}
			
			
			
			function close(){
				api.close();
			}
			//列表刷新
			function refreshGrid() {
			    Qm.refreshGrid();
			}
			function submitAction() {
			    Qm.refreshGrid();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="pactInfo" script="false" singleSelect="true">
		
			<qm:param name="pact_unit_id" value="<%=org_id%>" compare="=" />
						
				
		</qm:qm>
	</body>
</html>