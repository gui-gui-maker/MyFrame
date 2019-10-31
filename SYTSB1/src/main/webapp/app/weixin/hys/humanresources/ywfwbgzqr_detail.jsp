<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page import="java.util.*" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<title>工资表信息</title>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<link rel="stylesheet" href="k/km/lib/kh-mobile.css"/>
<script src="k/km/lib/jquery.min.js"></script>

<script src="k/km/lib/kh-mobile.js"></script>
<script src="k/km/lib/kh-mobile-list.js"></script>
<script type="text/javascript">
<%
String id = (String)session.getAttribute("Name");
String phone = (String)session.getAttribute("Phone");
String accessToken = (String)session.getAttribute("AccessToken");
%>
var ids ='<%=id%>';
var phone ='<%=phone%>';
var id='';

 
 $(function(){
		$.ajax({
			url : "${pageContext.request.contextPath}/tjy2YwfwbgzqrbAction/getBusinessId.do?phone="+'<%=phone%>',
			type : "POST",
			dataType:'json',
			async : false,
			success : function(data) {	
			id = data.idss;
				 $("#form1").setValues("${pageContext.request.contextPath}/tjy2YwfwbgzqrbAction/detail.do?id="+id); 
				 
			} 
		 });
		
		  getcodetabl("lpryzc","professional"); 
		  getcodetabl("lpryxl","education"); 
		  getcodetabl("gwgz","jobs"); 
	      $("#form1").initForm({
				 getSuccess: function (res){
						if(res.data==null||res.success=="false"){
							alert("加载失败！");
						}
						
				}
	 	});		
	      
	      $("#next1").click(function() {
  		$.ajax({
  			url : "${pageContext.request.contextPath}/tjy2YwfwbgzqrbAction/queren.do",
  			type : "POST",
			async : false,
			data : "Id="+id,
  	      success : function (data) {
  	      	       	
  	      		
  					$("#form1").transform("detail");   
  					$("#next1").hide();
  			       $("#form1").setValues("${pageContext.request.contextPath}/tjy2YwfwbgzqrbAction/detail1.do?id="+id,function(res){
  			      });     
  	      	
  	      }
  		});  		

  	});
 });	

	      function getcodetabl(code,id)   
	      {  
	       $.ajax({
	      	url : "${pageContext.request.contextPath}/employeeBaseAction/getcodetabl.do?tablname=" + code,
	      	type : "POST",
	      	async : false,
	      	success : function(json) {		
	      		var	s="";
	      		if(json.success){
	      		for(var i in json.data) {	

	      		s+="<option value='"+json.data[i].value+"'>"+json.data[i].name+"</option>";				    				  				
	      		}
	      		
	      		$("#"+id).html(s);	
	      		
	      		}
	      	} 
	       });
	      }
	        
	    	 
</script>

</head>
<body>



        <div class="container">		
		<div class="page-header" align="center">
			<h1></h1>
		</div>
			<div class="page-panel" id="m-page-panel">
			<form id="form1" name="form1" method="post" action="" onsubmit="return false;" pageStatus="detail">
         <input name="id" type="hidden" />
         
        <input name="departmentId" id="departmentId" type="hidden" />
         <input  name="nameId" id="nameId" type="hidden" />
      <table border="0" cellpadding="0" cellspacing="0" width="100%" height="" align="">
		  		          <tr class="tr1"> 
        
        <td width="100"  class="tdtext1"> 姓名:</td>
        <td class="l-t-td-right"> 
        <input  ltype='text' class="form-control" readonly="readonly"  id="name" name="name" type="text" id="Reviewers"  onclick="choosePerson();" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/>
        </td>
        </tr>
       <tr class="tr1"> 
        <td class="tdtext1"> 部门:</td>
        <td class="l-t-td-right"> 
<input readonly="readonly" class="form-control"   validate="{maxlength:50,required:true}" ltype="text"  name="department" id="department"  type="text" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"/>
        </td>
       </tr>
       <tr class="tr1"> 
        <td class="tdtext1"> 学历:</td>
        <td class="l-t-td-right"> 
          <select class="form-control" id="education"   name="education"  ></select>
        </td>
        </tr>
       <tr class="tr1"> 
        <td class="tdtext1"> 岗位:</td>
        <td class="l-t-td-right"> 
            <select class="form-control" id="jobs"   name="jobs"  ></select>
        </td>
       </tr >
       <tr class="tr1"> 
        <td class="tdtext1"> 职称:</td>
        <td class="l-t-td-right"> 
         <select class="form-control" id="professional"   name="professional"  ></select>
        </td>
        </tr>
        </tr >
       <tr class="tr1"> 
        <td class="tdtext1"> 是否确认:</td>
        <td class="l-t-td-right"> 
        <input  class="form-control" id="yesNo" name="yesNo" type="text" ltype="text" />
        </td>
        </tr>
      </table>
    </form> 
		</div>
		<div class="bt1" >
				<div class="text-center row">
					<div class=""><a id="next1" class="button button-block button-rounded button-primary">确认</a></div>
				</div>
			</div>
		</div>
</body>
</html>
		