<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%

CurrentSessionUser usee = SecurityUtil.getSecurityUser();
User uu = (User)usee.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
    <script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
     <%
SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
String nowTime=""; 
nowTime = dateFormat.format(new Date());
%>
        	
    <script type="text/javascript">
        $(function () {
        	$("#form1").initForm({
                toolbar: false,
                });
		});
        function send(){
	        //发送短信
	       	var formData = $("#form1").getValues();
	     	var obj=$("#form1").validate().form();
		  	 	if(obj ){
		  			$("body").mask("正在提交发送...");
				 	$.ajax({
		   				url : 'finance/messageCheckAction/saveyz.do',
				   		type : "POST",
				   		dataType : "json",
				   		contentType : "application/json; charset=utf-8",
				   		data: $.ligerui.toJSON(formData),
				   		success : function(data, stats) {	
		   					$("#fkMsg").val(data.yyy);
				   			if(data.a=="手机号码不是当前登录用户无法查询！！"){
				   				$.ligerDialog.error(data.a);
				   				$("body").unmask();
				   				api.data.window.Qm.refreshGrid();
				   			}else{
					   			top.$.dialog.notice('发送成功！');
					   			$("body").unmask();
					   		}
		   					//手机号保存后不能修改
		   		 			$("#account").attr("readonly","readonly");
				   			if(ps=="add"){
				    				api.data.window.Qm.refreshGrid();
				    		}
		    				close();
		   				},error : function(data) {
		   					$("body").unmask();
		   					$.ligerDialog.error('输入数据有误');
		   				}
		   			});
		   	 }else{	
		   		 return;
		   	 }
    	}
        
        
        function sendwx(){
            //发送微信
            var formData = $("#form1").getValues();
          	var obj=$("#form1").validate().form();
	      	 if(obj ){
	      		 $("body").mask("正在发送...");
	    		 $.ajax({
		       		url : 'finance/messageCheckAction/savewx.do',
		       		type : "POST",
		       		dataType : "json",
		       		contentType : "application/json; charset=utf-8",
		       		data: $.ligerui.toJSON(formData),
		       		success : function(data, stats) {
	       				$("#fkMsg").val(data.yyy);
	       				if(data.a=="手机号码不是当前登录用户无法查询！！"){
	       					$.ligerDialog.error(data.a);
	       					$("body").unmask();
	       					api.data.window.Qm.refreshGrid();
	       				}else{
	       					top.$.dialog.notice('发送成功！');
	       					$("body").unmask();
	       				}
	       					//手机号保存后不能修改
	       		 		$("#account").attr("readonly","readonly");
	       				if(ps=="add"){
	        				api.data.window.Qm.refreshGrid();
	        			}
	        			close();
	       			},error : function(data) {
	       				$("body").unmask();
	       				$.ligerDialog.error('输入数据有误');
	       			}
	       		});
	       	}else{	
	       		 return;
	       	}
      }
        
        function sendMsg(){//发送
        	var step = 59;
        	$("#sendwxBtn").val(" 重新发送  60  秒,请不要离开本页面");
            var _res = setInterval(function(){   
                $("#sendwxBtn").attr("disabled", true);//设置disabled属性
                $("#sendwxBtn").val(" 重新发送 "+step+" 秒,请不要离开本页面");
                step-=1;
                if(step <= 0){
	                $("#sendwxBtn").removeAttr("disabled"); //移除disabled属性
	                $("#sendwxBtn").val("  发送  ");
	                clearInterval(_res);//清除setInterval
                }
            },1000);
            var aa   =$('#send_msg_type').ligerGetRadioGroupManager().getValue();
            if(aa==1){
            	sendwx();//WeChat
            }else if(aa==2){
            	send();//Messages
            }else {
            	$.ligerDialog.error('亲！发送失败！');
            } 
        }
        
        
   
   function  aa(){
	 var formData = $("#form1").getValues();
   	 var obj=$("#form1").validate().form();
    	 //验证码
   	 var yzm=$("#fkMsg").val();
    	 //电话号码
     var dhhm=$("#account").val();
   	 var yzm1=$("#yzm").val();
   	 var  account=$("#account").val();
   	
   	if(yzm1=="" || yzm1==undefined || yzm1.length!=6) {
   	   $.ligerDialog.warn('请输入6位数字验证码!');
   	   return;
   	}
  	if( yzm==yzm1){
  		 $("body").mask("正在提交...");
  		 var formData = $("#form1").getValues();
    	 var obj=$("#form1").validate().form();
		 $.ajax({
   		      url : 'finance/messageCheckAction/savetime1.do',
   		      type : "POST",
   		      dataType : "json",
   		      contentType : "application/json; charset=utf-8",
   		      data: $.ligerui.toJSON(formData),
   		      success : function(data, stats) {
   			        if(data.sc123){
			   			$("body").unmask();
			   			top.$.dialog.notice('验证成功！');
			   			
			   			var pathName=window.document.location.pathname;  
			   			var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
			   			JavaScript:window.location.href=projectName+"/finance/importSalaryy_list.jsp"; 
   					}else{
		   				$("body").unmask();
		   				$.ligerDialog.error("验证超时，请重新发送验证码！！");
   					}
    				close();
	   		},error : function(data) {
	   			$("body").unmask();
	   			$.ligerDialog.error('输入数据有误，验证失败！');
	   		}
   		});
   	 }else{
   	 		//验证成功
   		 if(yzm1==null||yzm1==""||yzm1=="null"){
   			$.ligerDialog.error('1');
   			 return;
   		 }else{
   			$.ligerDialog.open({
       			width: 280,
       			type: 'warn',
       			content: '验证码失效，请重新发送！！',
       			buttons: [{ text: '确定', onclick: function() {
       				location.reload();
       			} }]
			});
   		    	//if(!yes)
   	   		    	//return window.location.href="/app/finance/messageChecky_detail.jsp"; 
   		 		//$("body").unmask();
   		 		//return window.location.href="messageChecky_detail.jsp"; 
   		 }
   	}
   }
    	//生成验证码
        	
    </script>
</head>
<style>
/* .l-table td{border:1px solid #d2e0f1;}
.l-table{width:40%;margin-top:10px;cellspacing:2;}
form{border:0px;}
.l-t-td-left{height:45px;}
.l-t-td-right .l-text input{height:40px;line-height:40px}
.l-t-td-right .l-text-wrapper{height:40px;} */
.l-t-td-left, .l-td-left {
text-align: center;
height: 20px;
 background: white; 
}
</style>
<body>
<form id="form1" action="mobileMessage/nk/saveBasic.do" getAction="mobileMessage/nk/detail.do?id=${param.id}">

    <input type="hidden" id="id" name="id"></input>
      <table align="center" style="margin-top:120px;">
        <tr>
            <td><span style="color:red;font-size:14px">*　查看工资信息需通过验证！</span>
            </td>
        </tr>
    </table>
	<fieldset Style="width: 35%;margin-left: 30%" class="l-fieldset">
			<legend class="l-legend">
				<div>工资查询验证</div>
			</legend>
	<table id="tab1" border="0" cellpadding="3" cellspacing="0" style="width: 60%"   align="center">
				<tr>
					<td class="l-t-td-left">手机号码：</td>
					<td class="l-t-td-right" colspan="3"><input name="account"
						id="account" type="text" readonly="readonly"
						value="<%=e.getMobileTel()%>" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">发送信息：</td>
					<td colspan="2" class="l-t-td-right"><input type="radio"
						name="send_msg_type" id="send_msg_type" ltype="radioGroup" validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'微信', id:'1' }, { text:'短信', id:'2' }] }" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">说明：</td>
					<td   class="l-t-td-right"><c:out
							value="点击【发送】按钮，系统会生成验证码发送至您的手机，请注意查收！"></c:out><br />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left"></td>
					<td   class="l-t-td-right">
					<input type="button" id="sendwxBtn"
						name="sendwxBtn" value="&nbsp;&nbsp;发送&nbsp;&nbsp;" onclick="javascript:sendMsg();" />
					</td>
					<td align="center" class="l-t-td-right"></td>
				</tr>
				
				<tr>
            <td  class="l-t-td-left">验证码：</td>
            <td style="width: 70%;"  ><input title="10分钟内有效" style="width: 100%;height: 25px" name="yzm" id="yzm" validate="{maxlength:6,required:false}"></input></td>
            <td style="width: 200px;" align="center"  colspan="2" align="center">
                <input type="hidden" style="display: none"  value="" id="fkMsg" name="fkMsg" />
            </td>
        </tr>
        <tr>
			<td class="l-t-td-left"></td>
			<td   class="l-t-td-right">
			  <input  type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" onclick="aa();" />
			</td>
			<td align="center" class="l-t-td-right"></td>
		</tr>
				
			</table>
			</fieldset>
</form>

</body>
</html>
