<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head >
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">
 	$(function () {
         tbar=[{ text: '提交', id: 'del', icon: 'submit', click: submit},
              { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    });
    function submit(){
     	var obj=$("#form1").validate().form();
     	var cancelReason=encodeURI(encodeURI($("#cancelReason").val()));
     	if(cancelReason==""){
     		return;
     	};
    	$.ligerDialog.confirm('是否提交作废申请？', function (yes){
            if(!yes){return false;}
    	    id="${param.id}";
            top.$.ajax({
                url: "Tyfabh/a/cancelsub.do?id="+id+"&cancelReason="+cancelReason,
                type: "GET",
                dataType:'json',
                async: false,
                success:function (success) {
                    if (success) {
                    	top.$.notice("提交成功！");
						api.data.window.Qm.refreshGrid();
						api.close();
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
	<form id="form1" action="">
		<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" id="">
					<tr>
						<td class="l-t-td-left">作废原因</td>
						<td class="l-t-td-right"><textarea  name="cancelReason" id="cancelReason" rows="5" cols="25" class="l-textarea"   validate="{minlength:1,maxlength:1000,required:true}"></textarea></td>
					</tr>
					</table>
	</form>
</body>
</html>