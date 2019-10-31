<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>受检单位查询列表</title>
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
					api.data.parentWindow.callBackReport(Qm.getValueByName("id"),Qm.getValueByName("com_name"));
					api.close();
				//}
			}

			function close(){
				api.close();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="company_list" script="false" singleSelect="true">
		</qm:qm>
	</body>
</html>