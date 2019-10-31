<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/k/kui-base-form.jsp"%>
<%@ taglib uri="http://khnt.com/tags/chart" prefix="chart" %>
<link rel="stylesheet" type="text/css" href="pub/chart/css/chart.css" />
<script type="text/javascript">
function getData(){
	var data={};
	data.prefix=$("#prefix").val();
	data.initValue=$("#initValue").val();
	data.suffix=$("#suffix").val();
	return data;
}
</script>
</head>
<body>
	<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<tr> 
       		<td class="l-t-td-left"> 前缀 :</td>
        	<td class="l-t-td-right"> 
        		<input name="prefix" id="prefix" type="text" ltype='text' validate="{required:false,maxlength:16}"/>
        	</td>
		</tr>
        <tr> 
        	<td class="l-t-td-left"> 起始序号 :</td>
        	<td class="l-t-td-right" colspan='3'> 
        		<input name="initValue" id="initValue"  type="text" ltype='int' value="1" validate="{required:true,number:true}"/>
        	</td>
       	</tr>
       	<tr> 
	         <td class="l-t-td-left"> 后缀 :</td>
	        <td class="l-t-td-right"> 
	        	<input name="suffix"  id="suffix" type="text" ltype='text' validate="{required:false,maxlength:16}"/>
	        </td>
       	</tr>
	</table>
</body>
	
</html>
