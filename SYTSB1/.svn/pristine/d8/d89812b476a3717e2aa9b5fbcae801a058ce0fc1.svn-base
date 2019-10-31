<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="modify">
<base href="<%=basePath%>">
<title>简历信息打印预览</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/k/kui-base-form.jsp"%>
<script language="javascript" src="app/printActiveX/LodopFuncs.js"></script>
<object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0></object>
<script type="text/javascript">
var basepath="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"
	function init()
	{
		  
		  
		  var LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));
		  var strBodyStyle="<style>"+document.getElementById("style1").innerHTML+"</style>";
		  var strFormHtml=strBodyStyle+"<body>"+document.getElementById("content").innerHTML+"</body>";
		   api.close();
		   LODOP.PRINT_INIT("打印简历信息");
		     // 报表内容打印
		   LODOP.ADD_PRINT_HTM("5mm","2mm","190mm","2000mm",strFormHtml);
		  LODOP.PREVIEW();	
	} 
</script>
</head>
<body style="overflow:auto" onload="init()">
<style type="text/css" id="style1">
table {margin:10px auto;width:100%;text-align:center;border-collapse:collapse; font-family:"宋体";font-size: 12px;}
table td {border:#000 solid 1px; padding:7px 6px;width:12.5%;}
.tbl_fnl {
	width: 190mm;
	margin-left: auto;
	margin-right: auto;
	margin-top: 1em;
}

.l-grid-header {
	background: none;
}

.l-grid-header th {
	font-weight: bold;
}

.dataTd td,.l-grid-header th {
	border: 0px gray solid;
	font-size: 12px;
	padding: 5px;
	height:30px;
}
</style>
<style type="text/css" media="print">
.control {
	display: none;
}
.tbl_fnl td,.tbl_fnl th{
	font-size: 14px;
}
</style>
	<div id="content">
		<div align="center">
			<span style="text-align:center;" >
			<font size="5">简历信息</br>
		</span>
		</div>
		<table class="tbl_fnl">
          <tr class="dataTd">
            <td align="right" style="width:44mm">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
            <td>${zpJlxx.name }</td>
            <td align="right" style="width:44mm">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
            <td >${sex }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">身份号码：</td>
            <td >${zpJlxx.certificatesNum }</td>
            <td rowspan="4" align="right">照&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片：</td>
            <td rowspan="4"><c:if test="${zpJlxx.photoAdd != null }"> <img id="photo" src="${pageContext.request.contextPath}/fileUpload/downloadByObjId.do?id=${zpJlxx.photoAdd }" width="110px" height="120px"/> </c:if>
                <c:if test="${zpJlxx.photoAdd == null }"> <img id="photo" src="${pageContext.request.contextPath}/app/teacher/images/nopic.gif" width="110px" height="120px"/> </c:if>            </td>
          </tr>
          <tr class="dataTd">
            <td height="37" align="right">出生日期：</td>
            <td>${fn:substring(zpJlxx.birthday,0,10) }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族：</td>
            <td>${mz }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">政治面貌：</td>
            <td>${zzmm }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">现居住地址：</td>
            <td colspan="3">${zpJlxx.nowaddress }</td>
          </tr>
          <c:if test="${zpXqxx!=null }">
            <tr class="dataTd">
              <td align="right">申报职位：</td>
              <td>${zpXqxx.gwName }</td>
              <td align="right">职位编码：</td>
              <td>${zpXqxx.gwNum }</td>
            </tr>
          </c:if>
          <tr class="dataTd">
            <td align="right" style="height:18px">最高学历：</td>
            <td>${xl}</td>
            <td align="right">最高学位：</td>
            <td>${xw }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">毕业院校：</td>
            <td>${zpJlxx.college }</td>
            <td align="right">所学专业：</td>
            <td>${zpJlxx.major }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">毕业时间：</td>
            <td>${fn:substring(zpJlxx.educatedTime,0,10) }</td>
            <td align="right">手机号码：</td>
            <td>${zpJlxx.telNum }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">下一级学历：</td>
            <td>${xjxl}</td>
            <td align="right">下一级学历专业：</td>
            <td>${zpJlxx.xjMajor}</td>
          </tr>
          <tr class="dataTd">
            <td align="right">下一级学位：</td>
            <td>${xjxw }</td>
            <td align="right">下一级学历毕业院校：</td>
            <td>${zpJlxx.xjCollege }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">下一级学历毕业时间：</td>
            <td>${fn:substring(zpJlxx.xjEducatedTime,0,10) }</td>
            <td align="right">参加工作时间：</td>
            <td>${fn:substring(zpJlxx.cjTime,0,10) }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">现工作单位：</td>
            <td colspan="3">${zpJlxx.xgzdw }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">职称及所评专业：</td>
            <td>${zpJlxx.professional}</td>
            <td align="right">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：</td>
            <td>${zpJlxx.dutyname }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">健康状况：</td>
            <td>${jkzk }</td>
            <td align="right">婚姻状况：</td>
            <td>${hunyin }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">家庭通讯住址：</td>
            <td colspan="3">${zpJlxx.phoneadd }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">联系电话：</td>
            <td>${zpJlxx.phone }</td>
            <td align="right">邮箱地址：</td>
            <td>${zpJlxx.email }</td>
          </tr>
          <tr class="dataTd">
            <td align="right">申请加分原因：</td>
            <td colspan="3"><div style="width:500px;"> ${zpJlxx.addyy }</div></td>
          </tr>
          <tr class="dataTd">
            <td align="right">个人简历：</td>
            <td colspan="3"><div style="width:500px;"> ${zpJlxx.grjl }</div></td>
          </tr>
          <tr class="dataTd">
            <td align="right">所受惩罚情况：</td>
            <td colspan="3"><div style="width:500px;"> ${zpJlxx.ssjc}</div></td>
          </tr>
          <tr class="dataTd">
            <td align="right">专业证书、专长：</td>
            <td colspan="3"><div style="width:500px"> ${zpJlxx.zyzs }</div></td>
          </tr>
          <tr class="dataTd">
            <td align="right">考生诚信承诺：</td>
            <td colspan="3"><div style="width:500px;"> ${zpJlxx.kscn }</div></td>
          </tr>
        </table>
	</div>
	<div class="control" style="text-align: center;margin-top:1em">
		<input value=" 打 印 " onclick="print()" type="button" class="l-button l-btn2" />
	</div>
</body>
</html>