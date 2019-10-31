<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://khnt.com/tags/qm" prefix="q"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人员信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>

<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/rbac/js/area.js"></script>

<script type="text/javascript">
		
			 var qmUserConfig = { 
			 sp_defaults : {
					labelWidth : 60,
					columnWidth : 200,
					width : 130
				},
					sp_fields : [
					 {label : "姓名", name : "name", compare : "like", value : ""}
				],
				tbar:[
					
				],
				//提供以下4个事件
				listeners: {
					rowClick :function(rowData,rowIndex){},
					rowDblClick :function(rowData,rowIndex){detail();}
					,selectionChange :function(rowData,rowIndex){
					}
				}
			};
			function loadGridData(data){
				Qm.config.defaultSearchCondition[0].value=data;
				Qm.searchData();
			}
			
		</script>
</head>
<body>
	<q:qm pageid="sel_employee" singleSelect="true">
	   <qm:param name="level_code" compare="llike" value="${param.levelCode}" />
	   <qm:param name="polstatus" compare="=" value="0" />
	</q:qm>
</body>
</html>