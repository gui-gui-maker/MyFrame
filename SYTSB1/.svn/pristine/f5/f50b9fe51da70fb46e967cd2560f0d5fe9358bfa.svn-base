<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
    var tbar="";
        $(function () {
        		tbar=[{ text: '保存11', id: 'up', icon: 'save', click: baocun},
                      { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
			$("#form1").initForm({
	            toolbar: tbar,
	            getSuccess : function(res) {
					//alert(res.data.flow);
					
				}/*,
			toolbarPosition :"bottom",
			success : function(resp) {//处理成功
				if (resp.success) {
					top.$.notice("保存成功！");
					api.data.window.submitAction();//执行列表页面函数
					api.close();
					}
				}*/
	    	});
		});
        function baocun(){
        	var obj=$("#form1").validate().form();
        	/*var yy=$("#flow").val();
   //     	alert(yy);
        	if(yy=="QCDDY"){
        		$.ligerDialog.alert("此条已有！请勿重复添加！");
        	}*/
          	 if(obj){
          		 $("#form1").submit();
          	 }else{
          		 return;
          	}
        }
    </script>
</head>
<body>
<form id="form1" action="mobileMessage/nk/saveBasic.do" getAction="mobileMessage/nk/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
     	<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String nowTime=""; 
		nowTime = dateFormat.format(new Date());%>
    <table class="l-detail-table">
        <tr>
        	<td class="l-t-td-left">发送时间</td>
        	<td colspan="2" class="l-t-td-right"><input name="sendTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" value="<%=nowTime%>" /></td>
        </tr>
        <tr>
        	
        	<td class="l-t-td-left">姓名</td>
        	<td class="l-t-td-right">
				<input name="personName" type="text" ltype="text" validate="{required:true,maxlength:50}" />
			</td>
			<td class="l-t-td-left">号码</td>
        	<td class="l-t-td-right">
				<input name="account" type="text" ltype="text" validate="{required:true,maxlength:50}" />
			</td>
			<td class="l-t-td-left">信息内容</td>
        	<td class="l-t-td-right">
				<input name="fkMsg" type="text" ltype="text" validate="{required:true,maxlength:2000}" />
			</td>
        </tr>
		
    </table>
</form>
</body>
</html>
