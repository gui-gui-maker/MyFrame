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
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/k/kui-base-form.jsp"%>
<script language="javascript" src="app/lodop/CheckActivX.js"></script>
<object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0></object>
    <script language="javascript">
		 function init()
		 { 
			  var LODOP=document.getElementById("LODOP");//这行语句是为了符合DTD规范
			  var path = "${pageContext.request.contextPath}";
			  if(CheckLodop(path))
			  {
				  api.close();
			      prn1_preview();
			  }
		 } 
		 function prn1_preview() {	
				var strFormHtml=document.getElementById("content").innerHTML;
				LODOP.PRINT_INIT("打印信息");
			     // 报表内容打印
			    LODOP.ADD_PRINT_HTM("9mm","9mm","160mm","2000mm",strFormHtml);
			    LODOP.PREVIEW();	
			};
	</script>
</head>
  
  <body onload="init()">
<div id="content" style="width:670px; height:auto;">
	<div id="title" style="width:670px; height:50px; text-align:center; vertical-align:middle">
    	<span style='font-size:16.0pt;font-family:宋体;'><strong>四川烹饪高等专科学校教师培训进修申请审批表</strong></span>
	</div>
    <div style="width:670px;height:auto">
    	<table width="670" border="1" cellspacing="0" cellpadding="0" bordercolor="#000000">
          <tr width="670px">
            <td width="42" height="32" align="center">
            <div style="width:40px">
            	姓名          
              </div></td>
            <td colspan="3" ><div style="width:70px">
              ${teacher.name }
            </div></td>
            <td width="49" align="center"><div style="width:40px">
              	性别
            </div></td>
            <td width="25"><div style="width:20px">
              ${xb }
            </div></td>
            <td width="102" align="center"><div style="width:70px">出生年月</div></td>
            <td width="81"><div style="width:80px"><fmt:formatDate	value="${teacher.birthday}" pattern="yyyy-MM-dd" /></div></td>
            <td width="117" align="center"><div style="width:80px">所在部门</div></td>
            <td colspan="16">${teacher.depName } </td>
          </tr>
          <tr>
            <td colspan="4" align="center" height="30"><div style="width:130px">最高学历(学位)</div></td>
            <td colspan="2">${xueli }</td>
            <td colspan="2" align="center"><div style="width:170px">毕业时间、学校、专业</div></td>
            <td colspan="17"><fmt:formatDate	value="${teacher.educatedTime}" pattern="yyyy-MM-dd" />,${teacher.college },${teacher.major }</td>
          </tr>
          <tr>
            <td height="30" colspan="4" align="center"><div style="width:130px">职称及评定时间</div></td>
            <td colspan="4"><div style="width:240px">${zc},<fmt:formatDate	value="${teacher.professionalTime}" pattern="yyyy-MM-dd" /></div></td>
            <td align="center"><div style="width:100px">现任行政职务</div></td>
            <td colspan="16">${dutyName}</td>
          </tr>
          <tr>
          	<td height="30" colspan="4" align="center"><div style="width:130px">目前所从事工作</div></td>
            <td colspan="4"><div style="width:240px"></div></td>
            <td align="center"><div style="width:80px">经费预计</div></td>
            <td colspan="16">${exTrain.money }</td>
          </tr>
          <c:if test="${size>0}">
          	<tr>
	            <td colspan="4" rowspan="${size+1 }" align="center"><div style="width:130px">进校后接受<br/>培训进修的简历</div></td>
	            <td height="30" colspan="3" align="center"><div style="width:80px">起止时间</div></td>
	            <td colspan="2" align="center"><div style="width:100px">培训进修单位</div></td>
	            <td colspan="16" align="center"><div style="width:100px">培训进修方向</div></td>
         	 </tr>
         	 <c:forEach items="${extrains }" var ="extrain">
         	 	<tr>
		            <td colspan="3"  align="center"><div style="width:180px"><fmt:formatDate	value="${extrain.startTime}" pattern="yyyy-MM-dd" />至<fmt:formatDate	value="${exTrain.endTime}" pattern="yyyy-MM-dd" /></div></td>
		            <td colspan="2"  align="center"><div style="width:180px">${extrain.tunitName }</div></td>
		            <td colspan="16"  align="center"><div style="width:160px">${extrain.direction }</div></td>
	          	</tr>
         	 </c:forEach>
          </c:if>
          <c:if test="${size==0}">
          	<tr>
	            <td colspan="4" rowspan="2" align="center"><div style="width:130px">进校后接受<br/>培训进修的简历</div></td>
	            <td height="30" colspan="3" align="center"><div style="width:80px">起止时间</div></td>
	            <td colspan="2" align="center"><div style="width:100px">培训进修单位</div></td>
	            <td colspan="16" align="center"><div style="width:100px">培训进修方向</div></td>
         	 </tr>
        	 <tr>
	            <td colspan="3"  align="center" height="60"><div style="width:180px"></div></td>
	            <td colspan="2"  align="center"><div style="width:180px"></div></td>
	            <td colspan="16"  align="center"><div style="width:160px"></div></td>
          	</tr>
          </c:if>
          <tr>
            <td height="30" colspan="4"><div style="width:130px">本次申请进修情况</div></td>
            <td colspan="3"  align="center"><div style="width:180px"><fmt:formatDate	value="${exTrain.startTime}" pattern="yyyy-MM-dd" />至<fmt:formatDate	value="${exTrain.endTime}" pattern="yyyy-MM-dd" /></div></td>
            <td colspan="2"  align="center"><div style="width:180px">${exTrain.tunitName }</div></td>
            <td colspan="16"  align="center"><div style="width:160px">${exTrain.direction }</div></td>
          </tr>
          <tr>
            <td align="center"><div style="width:14px">申请理由</div></td>
            <td colspan="24">
                 <div style="width:600px;word-break:break-all">
              	    本次培训进修的必要性及预期目标：             	 </div>
            	 <div style="width:600px;word-break:break-all; height:130px">
            	 	${exTrain.reason }
            	 	
              	 </div>
                  
                  <p></p>
                  <div align="right">
                  		<span style="margin-right:100px;">申请人签字：</span>
                        <br/>
                  		<span style="margin-right:20px; text-align:right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日		&nbsp;&nbsp;&nbsp;</span>                  </div>            </td>
          </tr>
           <tr>
            <td align="center"><div style="width:40px">部门意见</div></td>
            <td colspan="24">
                 <div style="width:600px;word-break:break-all; height:90px">             	 </div>
                  <div align="right">
                  		<span style="margin-right:100px;">部门负责人签字（盖章）：</span>
                        <br/>
                  		<span style="margin-right:20px; text-align:right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日		&nbsp;&nbsp;&nbsp;</span>                  </div>            </td>
          </tr>
           <tr>
            <td align="center"><div style="width:40px">人事处意见</div></td>
            <td colspan="24">
                 <div style="width:600px;word-break:break-all; height:90px">             	 </div>
                  <div align="right">
                  		<span style="margin-right:100px;">人事处处长（盖章）：</span>
                        <br/>
                  		<span style="margin-right:20px; text-align:right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日		&nbsp;&nbsp;&nbsp;</span>                  </div>            </td>
          </tr>
          <tr>
            <td align="center"><div style="width:40px">分管校领导意见</div></td>
            <td colspan="24">
                 <div style="width:600px;word-break:break-all; height:90px">             	 </div>
                  <div align="right">
                  		<span style="margin-right:100px;">分管校领导签字（盖章）：</span>
                        <br/>
                  		<span style="margin-right:20px; text-align:right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日		&nbsp;&nbsp;&nbsp;</span>                  </div>            </td>
          </tr>
            <tr>
            <td align="center"><div style="width:40px">学校意见</div></td>
            <td colspan="24">
                 <div style="width:600px;word-break:break-all; height:90px">             	 </div>
                  <div align="right"><span style="margin-right:20px; text-align:right">&nbsp;&nbsp;&nbsp;</span>                  </div>            </td>
          </tr>
        </table>

    </div>
</div>
  </body>
</html>
