<%@page import="com.scts.discipline.PhoneSysUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
PhoneSysUtil.shutDown("600");
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>业务信息查询</title>
		<%@include file="/k/kui-base-form.jsp"%>

		<script type="text/javascript">
		
			
		
		function call(){
			//初始化、指定处理函数、发送请求的函数
			if (window.XMLHttpRequest) //Mozila
			{
				http_request = new XMLHttpRequest();
			} else if (window.ActiveXobject) //IE
			{
			   try
			   {
					 http_request = new ActiveXObject("Msxml2.XMLHTTP");
			   }
			   catch (e)
			   {
				   try{
						 http_request = new ActiveXObject("Microsoft.XMLHTTP");
					   }
					catch (e) { }
			   }
			}
			 
			if (!http_request)  // 异常，创建对象实例失败
			{
			   alert("不能创建XMLHttpRequest实例！！");
			   return false;
			}
		
			// 指定当服务器返回信息时客户端的处理方式
			http_request.onreadystatechange = processRequest;
			var url ="http://192.168.1.241:8100/call"; 
			//var url ="http://localhost:8081/gxBatchSealing/services/sign.jsp"; 
			//var url ="http://jx.scsei.org.cn:8081/gxBatchSealing/services/sign.jsp"; 
			var sendContent = {'callee':'913548199448','phone':'600'};
			
			http_request.open("POST",url,false);
			//http_request.send(sendContent);
			
		}
		
		//******************************************************************
	    function processRequest()
	    {
			//alert("判断对象状态-----------"+http_request.readyState);
	        if (http_request.readyState == 4) // 判断对象状态
	        {
	           if (http_request.status == 200)  // 请求结果已经成功返回
	           {
	        	   
	        	   alert(http_request.responseText);
	        	   
	        	   /* alert(1);
	        	   $.post("inspectionInfo/basic/expPdfFlag.do",{"date":day,"id":api.data.id},function(res){
	        		   if(res.success){
	        			   alert("盖章并上传成功！");  
	        		   }
	        	   }) */
	        	   
	             //alert(http_request.responseText);
				  //document.getElementById("getTxt").value=http_request.responseText;
	           }
	           else  //页面不正常
	           {
	              alert("你请求的页面不正常");
	           }
	        }
	    }
		
		</script>
		
	</head>
	<body>
		<div>
		<span>${param.phone }</span>电话呼叫
		</div>
		<input type="button" value="呼叫" onclick="call()"/>
	</body>
</html>
