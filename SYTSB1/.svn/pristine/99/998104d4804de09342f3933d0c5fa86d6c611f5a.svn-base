<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var rows=null;
	var qmUserConfig = {
		
		
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({modify:count==1,detail:count==1,del:count>0},{select:count==1,close:count>0});
	                },
	
	                afterQmReady:function(){
						var check_type = "${param.check_type}";
						
	                	var unitcode = "<sec:authentication property='principal.department.id' htmlEscape='false' />";
	                	
	                	Qm.setCondition([{name : "fk_unit_id", compare : "=", value : unitcode},{name : "check_type", compare : "=", value : check_type}]);
	                	
	                	Qm.searchData();
	                },
					onSelectRow:function (rowData){
						 
						rows=rowData;
					}
					
	            }
			
	};
	
	
	function submitAction(o) {
		Qm.refreshGrid();
	}

	function getSelectResult()
       { 
          
		
           return rows; 
       }


	
	
</script>
</head>
<body>
	<qm:qm pageid="report_type" script="false" singleSelect="false" >
	</qm:qm>
</body>
</html>
