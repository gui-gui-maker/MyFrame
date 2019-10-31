<%@page import="com.scts.weixin.com.WeiXinUtil"%>
<%@page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>1111</title>
    <script src="http://rescdn.qqmail.com/node/ww/wwopenmng/js/sso/wwLogin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
<%@include file="/k/kui-base.jsp"%>
<%
//WeiXinUtil.httpPostWithJson();

%>
<script type="text/javascript">

</script>
</head>
<body >
    <div id="code" ></div>

    <script>
    var auth_code = "${param.code}";
    $(function(){
    	if(auth_code==""){
      	  window.WwLogin({
                "id" : "code",  //显示二维码的容器id
                "appid" : "ww60c5a15a6e259d36",
                "agentid" : "1000002",  //企业微信的cropID，在 企业微信管理端->我的企业 中查看
                "redirect_uri" :"http%3A%2F%2F192.168.0.110:8000",   //重定向地址，需要进行UrlEncode
                "state" : "3828293919281",   //用于保持请求和回调的状态，授权请求后原样带回给企业。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议企业带上该参数
                "href" : "",    //自定义样式链接，企业可根据实际需求覆盖默认样式。详见文档底部FAQ

        	});
  	  }else{
  		getUserInfo(auth_code);
  	  }
    })
    
     function getUserInfo(auth_code){
    	 $.post("weixinLoginAction/getUserAccont.do", {
				code:auth_code
			}, function(data) {
				if (data["success"]) {
					var userName = data.userName;
					$.post("j_spring_security_check", {
						j_password : $.md5("**"),
						j_username : userName
					}, function(data) {
						if (data["success"]) {
							positionLogin(data);
						} else {
							alert("用户名或密码错误！");
							$("#userCodePanel .k-login-inputbtn").html(html1);
							$("#userButtonPanel").html(html2);
						}
					})
				} else {
					alert("用户名或密码错误！");
					$("#userCodePanel .k-login-inputbtn").html(html1);
					$("#userButtonPanel").html(html2); 
				}
			})
    }
        
    </script>

</body>
</html>