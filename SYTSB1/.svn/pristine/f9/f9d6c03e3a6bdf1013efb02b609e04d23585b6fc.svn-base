<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String com_ids = request.getParameter("com_ids");
%>
	<head>
		<title>银行转账记录列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
					sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
					sp_fields:[
					{name:"account_name",compare:"like"},
					{name:"money",compare:"=",value:""}
	            ],
	            tbar:[
			        { text:'选择', id:'select',icon:'check',click:selectValue},
			        "-",
			        { text:'关闭', id:'close',icon:'modify',click:close}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count = Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({select:count>0});
	                }
	            }
	        };
        	
			function selectValue(){				
				var moneys = Qm.getValuesByName("rest_money").toString();
				var money = moneys.split(",");
				for(var i=0;i<money.length;i++){
					if(parseFloat(money[i])==0){
						$.ligerDialog.alert("亲，您选择了剩余金额为0的转账记录哦，请重新选择！");
						return;
					}
				}
				api.data.parentWindow.callBackTransfer(Qm.getValuesByName("id").toString());
				api.close();
			}
			
			function close(){
				api.close();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="TJY2_CW_BANK_LIST" script="false" singleSelect="false">
		</qm:qm>
	</body>
</html>