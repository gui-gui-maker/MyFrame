<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String com_ids = request.getParameter("com_ids");
%>
	<head>
		<title>在线交易记录（支付宝/微信）</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
					sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
					sp_fields:[
					{name:"com_name",compare:"like"},
					{name:"buyer_logon_id",compare:"like"},
					{name:"trade_no",compare:"like"},
					{name:"buyer_pay_amount",compare:"=",value:""}
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
				api.data.parentWindow.callBackOnlineTrade(Qm.getValueByName("id").toString(),Qm.getValueByName("pay_type").toString(),Qm.getValueByName("buyer_pay_amount").toString());
				api.close();
			}
			
			function close(){
				api.close();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="machine_trade_list" script="false" singleSelect="true">
			<qm:param name="pay_type" value="${param.pay_type}" compare="=" />
		</qm:qm>
	</body>
</html>