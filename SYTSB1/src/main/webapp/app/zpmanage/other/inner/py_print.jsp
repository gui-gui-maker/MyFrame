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
				//var page2 = document.getElementById("idea").innerHTML;
				LODOP.PRINT_INIT("打印信息");
				//LODOP.NewPage();
			     // 报表内容打印
			    LODOP.ADD_PRINT_HTM("9mm","9mm","160mm","2000mm",strFormHtml);
			    //LODOP.NewPage();
			     // 报表内容打印
			   // LODOP.ADD_PRINT_HTM("9mm","9mm","160mm","2000mm",page2);
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
       	  <span style='font-size:16.0pt;font-family:宋体'><strong>四川烹饪高等专科学校聘用人员审批表</strong></span>
        </td>
      </tr>
      <tr>
        <td width="202" align="right" style="width:200px"><span style='font-size:14.0pt;font-family:宋体'><strong>拟聘部门</strong>:</span></td>
        <td width="15" ><div style="width:150px; text-align:left">${xqxx.depName }</div></td>
        <td width="129" align="right"><span style='font-size:14.0pt;font-family:宋体'><strong>拟聘岗位</strong>:</span> </td>
        <td width="184" style="width:180px">
       	  <div style="width:120px; text-align:left">${zpinner.gwname }</div>
        </td>
      </tr>
    </table>
  </div>
  <div style="width:670px;height:auto">
    <table width="670" border="1" cellspacing="0" cellpadding="0" bordercolor="#000000">
  <tr>
            <td width="20" rowspan="6" align="center"><div style="width:16px; text-align:center"><strong>拟聘人员基本信息</strong></div></td>
            <td width="84" height="30" align="center"><div style="width:80px">姓名</div></td>
            <td width="81" ><div style="width:80px;">${jl.name }</div></td>
            <td width="81" align="center"><div style="width:80px">性别</div></td>
            <td width="106" >${xb }</td>
            <td width="80" align="center"><div style="width:80px">出生日期</div></td>
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
            <td height="30" align="center"><div style="width:80px">籍贯/国籍</div></td>
            <td ><div style="width:80px"></div></td>
            <td align="center"><div style="width:80px">出生地</div></td>
            <td><div style="width:100px"></div></td>
            <td ><div style="width:80px; text-align:center">最高学历</div></td>
            <td ><div style="width:80px">${xueli }</div></td>
          </tr>
          <tr>
            <td height="30" align="center"><div style="width:80px">民族</div></td>
            <td ><div style="width:80px">${mz }</div></td>
            <td align="center"><div style="width:80px">最高职称</div></td>
            <td colspan="3" >${zc }</td>
          </tr>
          <tr>
            <td height="30" align="center"><div style="width:80px">身份证号</div></td>
            <td colspan="5">${jl.certificatesNum }</td>
          </tr>
          <tr>
            <td height="30" align="center"><div style="width:80x">何时加入何种党派</div></td>
            <td colspan="8" >${zzmm }</td>
          </tr>
          <tr>
            <td align="center"><div style="width:80px">联系方式</div></td>
            <td colspan="8" align="center">          
            	<table border="0" cellspacing="5" cellpadding="0">
                  <tr>
                    <td width="49" align="right">Tel：</td>
                    <td width="212"><div style="width:210px; text-align:left">${jl.telNum}</div></td>
                    <td width="78"><div style="width:80px">通讯地址：</div></td>
                    <td width="201"><div style="width:200px; text-align:left">${jl.nowaddress }</div></td>
                  </tr>
                  <tr>
                    <td align="right">Email：</td>
                    <td><div style="width:210px; text-align:left">${jl.email }</div></td>
                    <td>邮政编码：</td>
                    <td><div style="width:200px; text-align:left"> </div></td>
                  </tr>
                </table>            
           </td>
          </tr>
          	  	<tr>
	                <td rowspan="2" align="center"><div style="width:14px; text-align:center"><strong>学历信息</strong></div></td>
	                <td align="center"><div style="width:80px; text-align:center"><strong>获取学位</strong></div></td>
	                <td colspan="2" align="center"><strong>起始时间</strong></td>
	                <td colspan="2" align="center"><strong>截止时间</strong></td>
	                <td colspan="4" align="center"><strong>学校名称及专业方向</strong></td>
          	  	</tr>
          	  	<tr>
                <td height="60" align="center"><div style="width:80px;"></div></td>
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
	                <td height="60" align="center"><div style="width:80px;"></div></td>
	                <td colspan="2" align="center"></td>
	                <td colspan="2" align="center"></td>
	                <td colspan="4" align="center"><div style="width:200px; text-align:left"></div></td>
	              </tr>
	               <tr>
             <td colspan="13" align="center">
             <div style="width:670px;word-break:break-all; height:150px; text-align:left">
               <strong>用人部门意见：</strong>
               <br />
             </div></td>
           </tr>
          
           <tr>
            <td align="center"><div style="width:14px; text-align:center"><strong>人事处审核意见</strong></div></td>
            <td colspan="12">
                 <div style="width:600px;word-break:break-all; height:100px">             	 </div>
                  <div align="right"><span style="margin-right:100px;">负责人签字：</span><br/>
                  		<span style="margin-right:20px; text-align:right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日		&nbsp;&nbsp;&nbsp;</span>                  </div>            </td>
          </tr>
          <tr>
            <td align="center"><div style="width:14px; text-align:center"><strong>分管校领导意见</strong></div></td>
            <td colspan="12">
                 <div style="width:600px;word-break:break-all; height:100px">             	 </div>
                  <div align="right"><span style="margin-right:100px;">分管校领导签字：</span><br/>
                  		<span style="margin-right:20px; text-align:right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日		&nbsp;&nbsp;&nbsp;</span>                  </div>            </td>
          </tr>
            <tr>
            <td align="center"><div style="width:14px; text-align:center"><strong>学校审批意见</strong></div></td>
            <td colspan="12">
                 <div style="width:600px;word-break:break-all; height:100px">             	 </div>
                  <div align="right">
                  		<span style="margin-right:100px;">校长签字：</span>
                        <br/>
                  		<span style="margin-right:20px; text-align:right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日		&nbsp;&nbsp;&nbsp;</span>                  </div>            </td>
          </tr>
           </table>
  </div>
</div>
  </body>
</html>
