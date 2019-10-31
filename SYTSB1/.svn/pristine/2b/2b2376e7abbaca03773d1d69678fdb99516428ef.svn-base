<%@page import="java.util.Date"%>
<%
	 /********************************************
	 * 文件名称：car_history_excel.jsp
	 * 功能描述：统计导出EXCEL页面
	 * 创建日期：2016-12-19
	 * @author 周定萍
	 * @version 1.0
	 *********************************************/
%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.khnt.utils.DateUtil" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String file = "attachment; filename=proportion_statistic("+DateUtil.getDateTime("yyyyMMddhhmmss", new Date())+").xls";
	//String file = "attachment; filename=car_use_his.xls";
	response.setHeader("Content-disposition",file);

%>
<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" >
<HEAD>
<META http-equiv="Content-Style-Type" content="text/css">
<Meta http-equiv="Content-Type" Content="text/html; Charset=UTF-8">
<Meta http-equiv="Content-Language" Content="zh-CN">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.3.2 for Windows">
<TITLE></TITLE>
<%@ include file="/k/kui-base-list.jsp"%>
</HEAD>

<style>
<!--table
  {mso-displayed-decimal-separator:"\.";
  mso-displayed-thousand-separator:"\,";}
@page
  {margin:1.0in .75in 1.0in .75in;
  mso-header-margin:.5in;
  mso-footer-margin:.5in;}
.font0
  {color:windowtext;
  font-size:12.0pt;
  font-weight:400;
  font-style:normal;
  text-decoration:none;
  font-family:宋体;
  mso-generic-font-family:auto;
  mso-font-charset:134;}
.font6
  {color:windowtext;
  font-size:12.0pt;
  font-weight:400;
  font-style:normal;
  text-decoration:none;
  font-family:"Times New Roman", serif;
  mso-font-charset:0;}
tr
  {mso-height-source:auto;
  mso-ruby-visibility:none;}
col
  {mso-width-source:auto;
  mso-ruby-visibility:none;}
br
  {mso-data-placement:same-cell;}
.style0
  {mso-number-format:General;
  text-align:general;
  vertical-align:bottom;
  white-space:;
  mso-rotate:0;
  mso-background-source:auto;
  mso-pattern:auto;
  color:windowtext;
  font-size:12.0pt;
  font-weight:400;
  font-style:normal;
  text-decoration:none;
  font-family:宋体;
  mso-generic-font-family:auto;
  mso-font-charset:134;
  border:none;
  mso-protection:locked visible;
  mso-style-name:常规;
  mso-style-id:0;}
td
  {mso-style-parent:style0;
  padding-top:5px;
  padding-right:5px;
  padding-left:5px;
  mso-ignore:padding;
  color:windowtext;
  font-size:12.0pt;
  font-weight:400;
  font-style:normal;
  text-decoration:none;
  font-family:宋体;
  mso-generic-font-family:auto;
  mso-font-charset:134;
  mso-number-format:General;
  text-align:general;
  vertical-align:bottom;
  border-top:.5pt solid windowtext;
  border-right:.5pt solid windowtext;
  border-bottom:.5pt solid windowtext;
  border-left:.5pt solid windowtext;
  mso-background-source:auto;
  mso-pattern:auto;
  mso-protection:locked visible;
  white-space:;
  mso-rotate:0;}
.left
  {mso-style-parent:style0;
  text-align:left;
  border:.5pt solid windowtext;}
.left_red
  {mso-style-parent:style0;
  text-align:left;
  border:.5pt solid windowtext;
  color:red;}
.center
  {mso-style-parent:style0;
  text-align:center;
  border:.5pt solid windowtext;}
.right
  {mso-style-parent:style0;
  font-family:"Times New Roman", serif;
  text-align:right;
  mso-font-charset:0;
  mso-number-format:"\@";}
.right_red
  {mso-style-parent:style0;
  font-family:"Times New Roman", serif;
  text-align:right;
  mso-font-charset:0;
  mso-number-format:"\@";
  color:red;}
ruby
  {ruby-align:left;}
rt
  {color:windowtext;
  font-size:9.0pt;
  font-weight:400;
  font-style:normal;
  text-decoration:none;
  font-family:宋体;
  mso-generic-font-family:auto;
  mso-font-charset:134;
  mso-char-type:none;
  display:none;}
-->
</style>
</head>
<body>
	<form id="form1">
	<div id="f">
	<h1 align="center">合同额（月份）本期占比</h1>
	<br />
	<table width="90%" border="1" align="center" cellpadding="3" cellspacing="3"
	 style="text-align: center; font-size: small;">
		<tr class="text_double">
		<td>年份</td><td>总金额（万元）</td><td>1月（%）</td><td>2月（%）</td><td>3月（%）</td><td>4月（%）</td>
		<td>5月（%）</td><td>6月（%）</td><td>7月（%）</td><td>8月（%）</td><td>9月（%）</td><td>10月（%）</td><td>11月（%）</td><td>12月（%）</td>
		</tr>
		<c:forEach items="${list}" var="data">
		
			<tr>
				<td>${data.year }</td><td>${data.all }</td><td>${data.m_01 }</td><td>${data.m_02 }</td><td>${data.m_03 }</td><td>${data.m_04 }</td>
				<td>${data.m_05 }</td><td>${data.m_06 }</td><td>${data.m_07 }</td><td>${data.m_08 }</td><td>${data.m_09 }</td><td>${data.m_10 }</td>
				<td>${data.m_11 }</td><td>${data.m_12 }</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	
	</form>
</body>

</html>
