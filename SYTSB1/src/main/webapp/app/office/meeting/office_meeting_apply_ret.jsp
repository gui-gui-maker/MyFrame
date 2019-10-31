<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head >
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">


    
 	$(function () {
         tbar=[{ text: '确认撤销', id: 'del', icon: 'del', click: nosubmitSh},
              { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    });
 	
 	 
 	 
    function nosubmitSh(){
    	$("#auditResult").val("撤销");
     	var obj=$("#form1").validate().form();
     	var cxyy=$("#cxyy").val();
     	if(cxyy==""){
     		return;
     	};
    	$.ligerDialog.confirm('是否要撤销？', function (yes){
		if(!yes){return false;}
    	$("body").mask("正在处理，请稍后！");
    	var cxyy=encodeURIComponent(encodeURIComponent($("#cxyy").val()));
   	    id="${param.id}";
           top.$.ajax({
               url: "updates/yijina/ret.do?id="+id+"&cxyy="+cxyy+"&typeCode=TJY2_OFFICE_MEETING_FLOW",
               type: "GET",
               dataType:'json',
               async: false,
               success:function (data) {
                   if (data) {
                      $("body").unmask();
                			 $("#form1").submit();
                    	}else{
                		 	return;
                   }
               },
               error:function () {
             	  $.ligerDialog.alert("出错了!！请重试！");
                   $("body").unmask();
               }
           });
     });
    }
    </script>
</head>
<body>
	<form id="form1" action="updates/yijina/savesh.do">
		<input name="auditResult" id="auditResult" type="hidden" />
		<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" id="">
					<tr>
						<td class="l-t-td-left">撤销原因</td>
						<td class="l-t-td-right"><textarea  name="cxyy" id="cxyy" rows="5" cols="25" class="l-textarea"   validate="{minlength:1,maxlength:1000,required:true}"></textarea></td>
					</tr>
					</table>
	</form>
</body>
</html>