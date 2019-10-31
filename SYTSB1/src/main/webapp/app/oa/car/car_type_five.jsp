<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String orgId = user.getUnit().getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},//可以自己定义 layout:column,float,
		sp_fields : [
 			{name : "car_brand", compare : "like"},
 			{name : "state", compare : "="},
 			{name : "repairType", compare : "="},
 			{name : "load_number", compare : ">="},
 			{name : "car_num", compare : "like"},
 			{group:[
 			           {name:"buy_date",compare:">=",value:""},
 			           {label:"到",name:"buy_date",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
 			           ]
 			}
 		],
 		tbar : [
			{text : '撤销维修', id : 'repairGood', icon : 'return', click : repairGood}
		],
		listeners: {
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					repairGood: count==1
				});
			}
            
		}
	};
	
	function repairGood(){
		$.ligerDialog.confirm('是否撤销维修', function (yes) {
  			if(yes){
 				$.post('oa/car/info/repairGood.do?ids='+Qm.getValuesByName("id").toString(),function(res){
 					if(res){
 						top.$.notice("操作成功！",3);
 						Qm.refreshGrid();
 					}else{
 						$.ligerDialog.error("操作失败");
 					}
 				})
  			}
  		})
	}

</script>
</head>
<body>
	<qm:qm pageid="oa_carinfo" script="false" singleSelect="true">
    	<qm:param name="repairType" value="1" compare="=" />
	</qm:qm>		
</body>
</html>