<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>单位信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>

<script type="text/javascript">
	var qmUserConfig = {
		
		
			
	           
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({modify:count==1,detail:count==1,del:count>0});
	                }
	            }
	};
	
	

	

	
	
</script>
</head>
<body>
	<qm:qm pageid="click_total" script="false" singleSelect="false">
		
	</qm:qm>
</body>
</html>
