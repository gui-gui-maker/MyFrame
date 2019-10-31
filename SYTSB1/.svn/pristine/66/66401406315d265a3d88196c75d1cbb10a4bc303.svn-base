<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>为权限配置资源</title>
<%@include file="/k/kui-base-form.jsp"%>
 <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript" src="k/kui/frame/ligerTree.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var  status="${param.pageStatus}";
var  ids="${param.ids}";
$(function(){
	$("#formObj").initForm({
		toolbar : [ {
        	text : '发送',
        	id : 'save',
	        icon : 'save',
	        click : saveForm
          }, {
           	text : '关闭',
	        id : 'close',
	        icon : 'cancel',
	        click : close
          } ],
		success: function(resp) {
			if (resp.success) {
				top.$.dialog.notice('保存成功!',3);
				if(api.data.Qm)
					api.data.Qm.refreshGrid();
				api.close();
			} else {
				$.ligerDialog.error('保存失败!<br/>' + resp.msg);
			}
		},
		getSuccess: function(resp){
			var readers = [];
			$("#formObj").setValues(resp.message)
			if(resp.employee){
				 for(var i=0;i<resp.employee.length;i++){
					readers.push({
						types : "reader",
						name: resp.employee[i].empName,
						id: resp.employee[i].id
					});
				} 
				 if(status=="detail"){
					 addReaderDetail(readers,false);
				 }else{
					 addReader(readers,false);
				 }
				 
			}
		}
	});
	/* $("#formObj").validate({
		submitHandler : saveForm
	}); */
});
function close() {
		api.close();
	   }

//保存
function saveForm(){
   var message=$("#remark").val();
	$("body").mask("正在发送信息！");
	 $.ajax({
        url: "rlMessageAction/sendMessage.do",
        type: "POST",
        /* dataType: "json",
        contentType: "application/json;charset=utf-8", */
        data:{"message":message,"ids":ids},
        success: function (resp, stats) {
        	if(resp.success){
    			top.$.notice("操作成功！",3);
    			api.data.window.Qm.refreshGrid();
    			api.close();
    		}else{
    			$.ligerDialog.error("操作失败！<br/>" + resp.msg);
    		}
    		$("body").unmask();
        },
        error: function (data) {
            $.ligerDialog.error('操作失败！<br/>' + data.msg);
    		$("body").unmask();
        }
    }); 
}
</script>
</head>
<body>
		   <form name="formObj" id="formObj"  getAction="rlMessageAction/detailMessage.do?id=${param.id}">
		   <input type="hidden" id="id" name="id" />
				<table class="l-detail-table">
		           <%-- <tr>
						<td class="l-t-td-left">发送方式</td>
						<td class="l-t-td-right"><u:combo  name="sendManner" code="TJY2_RL_MESSAGETYPE" validate="required:true"></u:combo></td>
				  </tr> --%>
				  <tr>
						<td class="l-t-td-left">消息内容</td>
						<td ><textarea  name="message" id="remark" rows="4" cols="25" class="l-textarea" value=""></textarea></td>
				  </tr>
		
	        	</table>
	       </form>
</body>
</html>
