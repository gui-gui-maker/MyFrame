<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>图书续借</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript">
	$(function () {
		toolbar : [ {
			text : '保存',
			id : 'save',
			icon : 'save',
			click : save
		},
		{
			text : '关闭',
			id : 'close',
			icon : 'cancel',
			click : function close(){	
					 	api.close();
					}
		}],
		$("#formObj").initForm({    //参数设置示例
			success: function (response) {
				if(response.success){
					top.$.notice("保存成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}
				else{
					$.ligerDialog.error("操作失败！<br/>" + response.msg);
				}
			}
		});
	});
	//保存
	function save(){
		
		//验证表单数据
		if($('#formObj').validate().form()){
			
			var formData = $("#formObj").getValues();
			formData["ids"] = $("#ids").val();
			
	        $("body").mask("正在保存数据，请稍后！");
	        $.ajax({
	            url: "lib/borrow/borrowContinue.do",
	            type: "POST",
	            datatype: "json",
	           	data:formData,
	            success: function (data, stats) {
	            	$("body").unmask();
	                if (data["success"]) {
	                	if(api.data.window.Qm){
	                		api.data.window.Qm.refreshGrid();
	                	}
	                    top.$.dialog.notice({content:'保存成功'});
	                    api.close();
	                }else{
	                	$.ligerDialog.error('提示：' + data.msg);
	                	$("#save").removeAttr("disabled");
	                }
	            },
	            error: function (data,stats) {
	            	$("body").unmask();
	                $.ligerDialog.error('提示：' + data.msg);
	                $("#save").removeAttr("disabled");
	            }
	        });
		}
	}
</script>
</head>
<body>
<form id="formObj" action="lib/borrow/borrowContinue.do" >
	<input type="hidden" name="ids" id="ids" value="${param.ids}"/>
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">续借天数：</td>
            <td class="l-t-td-right">
            	<input name="timeLimitAdd" id="timeLimitAdd" type="text" 
            		ltype="number" validate="{required:true,digits:true,maxlength:3}" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
