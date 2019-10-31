<%@page import="java.util.Date"%>
<%
	 /********************************************
	 * 文件名称：car_history_excel.jsp
	 * 功能描述：车辆使用记录导出EXCEL页面
	 * 创建日期：2008-12-09
	 * @author：廖增伟
	 * @version 1.0
	 *********************************************/
%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.khnt.utils.DateUtil" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String file = "attachment; filename="+DateUtil.getDateTime("yyyyMMddhhmmss", new Date())+".xls";
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



	<table x:str  width=835 align="center" cellSpacing=1 cellPadding=5 border=1 >
	<tr><td colspan="12" align="center" style="font-size: 25px;">
	车辆使用记录
	</td></tr>
	<tr id='queryhead'>
					<td class='querybodyhead'><b>车牌号</b></td>
					<td class='querybodyhead'><b>使用时间</b></td>
					<td class='querybodyhead'><b>使用部门</b></td>
					<td class='querybodyhead'><b>去往目的地</b></td>
					<td class='querybodyhead'><b>用途</b></td>
					<td class='querybodyhead'><b>出差审批单</b></td>
					<td class='querybodyhead'><b>驾驶人</b></td>
					<td class='querybodyhead'><b>派车人</b></td>
					<td class='querybodyhead'><b>派车里程记录（公里）</b></td>
					<td class='querybodyhead'><b>归还里程记录（公里）</b></td>
					<td class='querybodyhead'><b>归还时间</b></td>
					<td class='querybodyhead'><b>加油记录（卡、现金/数量）</b></td>
					<td class='querybodyhead'><b>记录人</b></td>
				</tr>
		<c:forEach items="${result}" var="record">
				<tr align='center'>
				<td title=''>${record.carNum}</td>
				<td title=''>${record.startTime}</td>
				<td title=''>${record.orgName}</td>
				<td title=''>${record.destination}</td>
				<td title=''>${record.usedCarReason}</td>
				<td title=''>${record.businessTravelApproval}</td>
				<td title=''>${record.driver}</td>
				<td title=''>${record.sendCarMan}</td>
				<td title=''>${record.startKm}</td>
				<td title=''>${record.endKm}</td>
				<td title=''>${record.endTime}</td>
				<td title=''>${record.refuelRecord}</td>
				<td title=''>${record.editor}</td>
				</tr>
		</c:forEach>		
</table>
</html>
