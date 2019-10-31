<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>

<script type="text/javascript">
	var qmUserConfig = {
	};
</script>
</head>
<body>

	<qm:qm pageid="TJY2_ZL_SUG" singleSelect="true">
		<qm:param name="business_id" value="${param.ids}" compare="=" />
	</qm:qm>
</body>
</html>