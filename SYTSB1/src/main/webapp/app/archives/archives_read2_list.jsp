<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
    <script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
    <script type="text/javascript">
    $(function () {
    	$("#form1").initForm({
            toolbar: false,
            });
	});
   function submit(){
      	 var obj=$("#form1").validate().form();
      	 
    	var yzm1=$("#yzm").val();
    	if(yzm1=="" || yzm1==undefined || yzm1.length!=6) {
    	   $.ligerDialog.warn('请输入6位数字验证码!');
    	   return;
    	}
   	 if(obj){
   		$("body").mask("正在提交...");
		 $.ajax({
    		url : 'archives/yijina/savetime.do?yzm='+yzm1,
    		type : "POST",
    		dataType : "json",
    		contentType : "application/json; charset=utf-8",
    		//data: $.ligerui.toJSON(formData),/app/archives/
    		success : function(data) {
     			if(!data.success){
	    			$("body").unmask();
	    			top.$.dialog.notice('验证成功！');
	    			var pathName=window.document.location.pathname;  
  					var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
	    			window.location.href=projectName+"/archives/archives_read_list.jsp?yzm="+yzm1; 
    			}else if(data.msg=="验证码已过期！"){
	    			$("body").unmask();
    				$.ligerDialog.error(data.msg);
    			}else{
    				$("body").unmask();
    				$.ligerDialog.error('输入验证码错误！！');
    			}
     			close();
    		},
    		error : function(data) {
    			$("body").unmask();
    			$.ligerDialog.error('输入数据有误，保存失败！');
    		}
    	});
	}else{
		$("body").unmask();
		 var pathName=window.document.location.pathname;  
  		 var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
  
		return window.location.href=projectName+"/app/archives/archives_read2_list.jsp"; 
    }
   }
    	//生成验证码
        	
    </script>
</head>
<style>
.l-table td{border:1px solid #d2e0f1;}
.l-table{width:40%;margin-top:10px;cellspacing:2;}
form{border:0px;}
.l-t-td-left{height:45px;}
.l-t-td-right .l-text input{height:40px;line-height:40px}
.l-t-td-right .l-text-wrapper{height:40px;}
</style>
<body>
<form id="form1" action="mobileMessage/nk/saveBasic.do" getAction="mobileMessage/nk/detail.do?id=${param.id}">

    <input type="hidden" id="id" name="id"></input>
    <table align="center" style="margin-top:200px;">
        <tr>
            <td><span style="color:red;font-size:14px">*　查看报告信息需通过验证！</span>
            </td>
        </tr>
    </table>
    <table class="l-table" align="center">
    	<tr>
            <td  class="l-t-td-left">验证码</td>
            <td  class="l-t-td-right">
            	<input style="width: 200px" name="yzm" id="yzm" validate="{maxlength:6}"/>
            </td>
            <td colspan="2" align="center">
                <a class="l-button-warp l-button" href="javascript:submit();">
                	<span class="l-button-main l-button-hasicon">
	                	<span class="l-button-text">提　交</span>
	                	<span class="l-button-icon l-icon-check"></span>
	                </span>
	            </a>
                <input type="hidden" Style="display: none"  value="" id="fkMsg" name=fkMsg />
            </td>
        </tr>
	</table>
	
</form>
</body>
</html>
