<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>压力容器定期检验报告2</title> [$SqlHead]
</head>

<body>
	 		<input type="hidden" id="fk_report_id" name="fk_report_id"  value="${param.fk_report_id}"  >
 		<input type="hidden" id="fk_inspection_info_id" name="fk_inspection_info_id"  value="${param.fk_inspection_info_id}"  >
 
	<input type="hidden" id="nextPage" name="nextPage" value="[$RtNextPage]"> <br /> 
	[$RtPageBody]

</body>
</html>
