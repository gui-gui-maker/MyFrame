<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>demo-list</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>

<script type="text/javascript">
	var qmUserConfig = {

			sp_fields:[
							        ],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}
],
listeners: {
	rowClick: function(rowData, rowIndex) {
	},
	rowDblClick: function(rowData, rowIndex) {
		detail();
	},	
	
}

};
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/finance/fybxd_detail.jsp?id=' + id + '&pageStatus=detail'
			//content: 'url:lsts/finance/doPrintDetail.do?id='+id+'&viewURL=app/finance/fybxd_detail_print'
		});
	}
	
	
</script>
</head>
<body>

	<qm:qm pageid="TJY2_FCP_FYBXD"  script="false" singleSelect="true">
	<qm:param name="employee_id" compare="=" value="${param.id}" />
	</qm:qm>
</body>
</html>