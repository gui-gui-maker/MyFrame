<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<%
String areaCode=request.getParameter("areaCode");
String sortCode=request.getParameter("sortCode");
String sql="select t.*,t1.regional_name,t2.name sort_name  from BASE_DEVICE_DOCUMENT t,V_AREA_CODE  t1,PUB_CODE_TABLE_VALUES  t2 where t.device_area_code=t1.REGIONAL_CODE and t.device_sort_code=t2.value and t.inspect_next_date - sysdate < 0 and t.device_status !='99'  and t.device_sort_code in (select value from PUB_CODE_TABLE_VALUES  start with code_table_values_id = '"+sortCode+"' connect by prior id = code_table_values_id) and device_area_code='"+areaCode+"'";
%>
<script type="text/javascript">
	var qmUserConfig = {
		tbar : [
		{
			text : '查看',
			id : 'detail',
			icon : 'detail',
			click : detail
		}],
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					detail : count == 1
				});
			}
		}
	};

	function detail() {
		top.$.dialog({
			width : 800,
			height : 600,
			lock : true,
			title : "设备信息",
			content : 'url:app/device/device_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
			data : {
				"window" : window
			}
		});
	}


	function submitAction() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="count_device_area" sql="<%=sql%>" script="false" singleSelect="false"   />
</body>
</html>
