<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/eseal/bc/seal.js"></script>
<script type="text/javascript">
	$(function() {
		
			$.post("pub/signseal/getListByIds.do",{Ids:api.data.ids},function(sign){
		         if(sign.success){
		             for(var i in sign.data){
		                 showSingleSeal("body",sign.data[i],i * 180 + 10,"10",0);
		             }
		             if(!sign.data[0])
		                showSing("body",api.data.ids);	 
		         }
		    },"json");
				  	
	});
</script>
</head>
<body id="body"></body>
</html>