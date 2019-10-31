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
					{name:"com_name",compare:"like",value:""},
					{name:"com_code",compare:"=",value:""}
	            ],
	            tbar:[
			        { text:'选择', id:'select',icon:'check',click:selectValue},
			        "-",
			        { text:'新增', id:'add',icon:'add',click:add},
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
				api.data.parentWindow.callBack(Qm.getValueByName("id"),Qm.getValueByName("com_name"),Qm.getValueByName("com_address"),Qm.getValueByName("com_code"),Qm.getValueByName("com_legal_rep"),Qm.getValueByName("com_tel"));
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
					content: 'url:app/sinspection/sinspection_unit_detail.jsp?com_type=4',
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
		<qm:qm pageid="orgInfo" script="false" singleSelect="true"   >
			<%
				if(StringUtil.isNotEmpty(com_ids)){
					String[] ids = com_ids.split(",");
					for(int i=0;i<ids.length;i++){
						if(i==0){
							%>
							<qm:param name="id" value="<%=ids[i]%>" compare="=" />
							<%
						}else{
							%>
							<qm:param name="id" value="<%=ids[i]%>" compare="=" logic="or"/>
							<%
						}
					}
				}
			%>
		</qm:qm>
	</body>
</html>