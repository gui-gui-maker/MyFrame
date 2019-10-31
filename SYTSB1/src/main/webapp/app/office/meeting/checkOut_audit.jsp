<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
		//如果不设置额外参数，不用调用此方法，初始化时会自动调用
		$("#formObj").initForm({
		toolbar : [ {
        	text : '发送',
        	id : 'send',
	        icon : 'save',
	        click : sendMessage
                  }],
	        });
        }); 
        function sendMessage() {
	    	var obj=$("#formObj").validate().form();
          if(obj){
    		var  reqId = "${param.reqId}";
    		var sendType=$("#audit").val();
    				$("body").mask("发送中...");
    			 	$.ajax({
    					url:"oa/meetingReq/info/audit.do?reqId="+reqId+"&sendType="+sendType,
    					type:"post",
    					async:false,
    					 success:function (data) {
                             if (data) {
                            	$("body").unmask();
                                top.$.notice('发送成功！！！',3);
                                   api.close();
                             }else{
                        	    $("body").unmask();
                                $.ligerDialog.alert("出错了！请重试！!");
                         
                             }
                         },
    					
    				}); 
}else{
	$.ligerDialog.alert("请选择发送消息的方式！");
	return;
}
    		
    		
    	}
    </script>
</head>
<body>
<form id="formObj" >
    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       <tr>
        <td class="l-t-td-left"> 发送方式：</td>
           <td class="l-t-td-right">
           	 <input type="checkbox" id="audit"  name="audit" ltype="checkboxGroup" validate="{required:true}" 
                             ligerui="{value:'0',data: [ { text:'短信发送', id:'0' }, { text:'微信发送', id:'1' }],onChange:function(value){
                   		 if(value==1){
                   			$('#audit').val('1');
                   		}else if(value==0){
                   		$('#audit').val('0');
                   		}else{
                   		$('#audit').val('0,1');
                   		}
                   }}"/>
           </td>
       </tr>
   </table>
</form>
</body>
</html>
