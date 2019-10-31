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
<script language="javascript" src="app/lodop/CheckActivX.js"></script><object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0></object>
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
				var page2 = document.getElementById("content1").innerHTML;
				LODOP.PRINT_INIT("打印信息");
				LODOP.NewPage();
			     // 报表内容打印
			    LODOP.ADD_PRINT_HTM("9mm","9mm","160mm","2000mm",strFormHtml);
			   LODOP.NewPage();
			     // 报表内容打印
			    LODOP.ADD_PRINT_HTM("9mm","9mm","160mm","2000mm",page2);
			    LODOP.PREVIEW();	
			};
	</script>
</head>
  
  <body onload="init()">
   <div id="content" style="width:670px; height:auto;">
	<div id="title" style="width:670px; height:70px; text-align:center; vertical-align:middle">
   	  <table  border="0" align="center" style="width:670px">
      <tr>
        <td colspan="4" align="center">
       	  <span style='font-size:16.0pt;font-family:宋体'><strong>四川烹饪高等专科学校</br>名誉教授（客座教授、兼职教师）聘用申请审批表</strong></span>
        </td>
      </tr>
    </table>
  </div>
  <div style="width:670px;height:auto">
    <table width="670" border="1" cellspacing="0" cellpadding="0" bordercolor="#000000">
  <tr>
            <td width="20" rowspan="8" align="center"><div style="width:16px; text-align:center"><strong>拟聘人员基本信息</strong></div></td>
            <td width="84" height="30" align="center"><div style="width:80px">姓名</div></td>
            <td width="81" ><div style="width:80px;">${jl.name }</div></td>
            <td width="81" align="center"><div style="width:80px">性别</div></td>
            <td width="100" >${xb }</td>
            <td width="86" align="center"><div style="width:80px">出生日期</div></td>
            <td width="86" ><div style="width:80px"><fmt:formatDate	value="${jl.birthday}" pattern="yyyy-MM-dd" /></div></td>
            <td width="114" colspan="3" rowspan="4" align="center">          
            	<c:if test="${jl.photoAdd != null }">
					<img id="photo" src="${pageContext.request.contextPath}/fileUpload/downloadByObjId.do?id=${jl.photoAdd }" width="110px" height="120px"/>
					</c:if>
					<c:if test="${jl.photoAdd == null }">
						<img id="photo" src="${pageContext.request.contextPath}/app/teacher/images/nopic.gif" width="110px" height="120px"/>
					</c:if> 	  
            </td>
      </tr>
          <tr>
            <td height="30" align="center"><div style="width:80px">学历</div></td>
            <td ><div style="width:80px">${xueli }</div>            </td>
            <td align="center"><div style="width:80px">学位</div></td>
            <td><div style="width:100px">${xuewei }</div></td>
            <td ><div style="width:80px; text-align:center">政治面貌</div></td>
            <td ><div style="width:80px">${zzmm }</div></td>
          </tr>
          <tr>
            <td height="30" align="center"><div style="width:80px">职称</div></td>
            <td ><div style="width:80px">${zc}</div></td>
            <td align="center"><div style="width:80px">职务</div></td>
            <td colspan="3" >${jl.dutyname }</td>
      </tr>
          <tr>
            <td height="30" align="center"><div style="width:80px">身份证号</div></td>
            <td colspan="5">${jl.certificatesNum }</td>
          </tr>
          <tr>
            <td height="30" align="center"><div style="width:80x">现工作单位</div></td>
            <td colspan="8" >${jl.xgzdw }</td>
      </tr>
       <tr>
            <td height="34" align="center"><div style="width:80x">住址</div></td>
            <td colspan="3" ><span style="width:200px; text-align:left"><div style="width:200px">${jl.nowaddress }</div></span></td>
            <td ><div align="center">邮编</div></td>
            <td colspan="4" >&nbsp;</td>
      </tr>
          <tr>
            <td height="30" align="center">办公电话</td>
            <td colspan="3" align="center">&nbsp;</td>
            <td align="center">传真</td>
            <td colspan="4" align="center">&nbsp;</td>
          </tr>
          <tr>
            <td height="30" align="center">手机号</td>
            <td colspan="3" align="center"><div style="width:200px; text-align:left">${jl.telNum}</div></td>
            <td align="center">邮箱</td>
            <td colspan="4" align="center">${jl.email}</td>
          </tr>
          	  	<tr>
	                <td rowspan="2" align="center"><div style="width:14px; text-align:center"><strong>学历信息</strong></div></td>
	                <td align="center"><div style="width:80px; text-align:center"><strong>获取学位</strong></div></td>
	                <td colspan="2" align="center"><strong>起始时间</strong></td>
	                <td colspan="2" align="center"><strong>截止时间</strong></td>
	                <td colspan="4" align="center"><strong>学校名称及专业方向</strong></td>
          	  	</tr>
          	  	<tr>
                <td height="80" align="center"><div style="width:80px;"></div></td>
                <td colspan="2" align="center"></td>
                <td colspan="2" align="center"></td>
                <td colspan="4" align="center"><div style="width:200px; text-align:left"></div></td>
             	</tr>
          	  	<tr>
                    <td rowspan="2" align="center"><div style="width:14px; text-align:center"><strong>工作经历</strong></div></td>
                    <td align="center"><div style="width:80px; text-align:center"><strong>职位名称</strong></div></td>
                    <td colspan="2" align="center"><strong>起始时间</strong></td>
                    <td colspan="2" align="center"><strong>截止时间</strong></td>
                    <td colspan="4" align="center"><strong>任职单位</strong></td>
          		</tr>
	              <tr>
	                <td height="80" align="center"><div style="width:80px;"></div></td>
	                <td colspan="2" align="center"></td>
	                <td colspan="2" align="center"></td>
	                <td colspan="4" align="center"><div style="width:200px; text-align:left"></div></td>
	              </tr>
             <tr>
                    <td  align="center"><strong>代表成果或作品</strong></td>
                    <td colspan="9" align="center"><div align="left" style="height:150px">
                      <p>1：代表作品</p>
                      <p>&nbsp;</p>
                      <p>2：获奖情况</p>
                    </div></td>
                </tr>
	        
           <tr>
            <td align="center"><div style="width:14px; text-align:center"><strong>用人部门意见</strong></div></td>
            <td colspan="12">
                 <div style="width:600px;word-break:break-all; height:120px">             	 </div>
                  <div align="right"><span style="margin-right:100px;">负责人签字：</span><br/>
                  		<span style="margin-right:20px; text-align:right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日		&nbsp;&nbsp;&nbsp;</span>                  </div>            </td>
          </tr>
           <tr>
            <td align="center"><div style="width:14px"><strong>教务处意见</strong></div></td>
            <td colspan="3">
           	  <div style="word-break:break-all; height:120px">
              </div>
                  
            	<div align="left">
                  		<span style="width:200px" >负责人签字（盖章）：</span>
            <br/>
                  		<span style="margin-left:150px; text-align:right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日		&nbsp;&nbsp;&nbsp;</span>                 
              </div>
            </td>
            <td align="center"><div style="width:14px">
              <div align="center"><strong>人事处意见</strong></div>
            </div></td>
            <td colspan="22">
            	<div style="word-break:break-all; height:120px">
                </div>
                  
            	<div align="right">
                  		<span style="margin-right:100px;">负责人签字（盖章）：</span>
            <br/>
                  		<span style="margin-right:20px; text-align:right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日		&nbsp;&nbsp;&nbsp;</span>                 
              </div>
            </td>
          </tr>
          </table> 
             </div>
</div>
		<div id="content1">
           <table width="670" border="1" cellspacing="0" cellpadding="0" bordercolor="#000000">
          <tr>
            <td align="center"><div style="width:14px; text-align:center"><strong>分管领导意见</strong></div></td>
            <td colspan="12">
                 <div style="width:600px;word-break:break-all; height:90px">             	 </div>
                  <div align="right"><span style="margin-right:100px;">分管领导签字：</span><br/>
                  		<span style="margin-right:20px; text-align:right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日		&nbsp;&nbsp;&nbsp;</span>                  </div>            </td>
          </tr>
            <tr>
            <td align="center"><div style="width:14px; text-align:center"><strong>学校意见</strong></div></td>
            <td colspan="12">
                 <div style="width:600px;word-break:break-all; height:80px">             	 </div>
                  <div align="right">
                  		<span style="margin-right:100px;">校长签字：</span>
                        <br/>
                  		<span style="margin-right:20px; text-align:right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日		&nbsp;&nbsp;&nbsp;</span>                  </div>            </td>
          </tr>
           </table>
		</div>
  </body>
</html>
