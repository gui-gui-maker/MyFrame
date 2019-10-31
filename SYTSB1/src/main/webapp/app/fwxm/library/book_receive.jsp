<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>详情页面</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="/pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var r = new RegExp("^[A-Z]+-[A-Z]-[0-9]{3}-[0-9]{2}$");
	$(function () {
		$("#formObj").initForm({    //参数设置示例
			toolbar : [{
				text : '关闭',
				id : 'close',
				icon : 'cancel',
				click : function close(){	
						 	api.close();
						}
			}],
			afterParse : function(){
				$("#qrcode").bind("keyup",function(e){
					if(r.test(this.value)||e.keyCode==13){
						receive();
						$("#qrcode").val("");
					}
				});
			}
		});
	});
	//保存
	function receive(){
		//验证表单数据
		if($('#formObj').validate().form()){
			var formData = $("#formObj").getValues();
	        $("body").mask("正在保存数据，请稍后！");
	        $.post("lib/book/receive.do",formData,
        		function (data) {
	            	$("body").unmask();
	                if (data["success"]) {
	                	if(api.data.window.Qm){
	                		api.data.window.Qm.refreshGrid();
	                	}
	                    top.$.dialog.notice({content:'成功'});
	                }else{
	                	$.ligerDialog.error('提示：' + data.msg);
	                }
            });
		}
	}
	
	function selectUser(){
        /* top.$.dialog({
            width: 800,
            height: 450,
            lock: true,
            parent: api,
            title: "选择人员",
            content: 'url:app/common/user_choose.jsp',
            cancel: true,
            ok: function(){
                var p = this.iframe.contentWindow.getSelectedPerson();
                if(!p){
                    top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                    return false;
                }
                $("#receivedBy").val(p.userid);
                $("#receivedByName").val(p.name);
            }
        }); */
        selectUnitOrUser("4",0,"","",function(datas){
			if(!datas.code)return;
			$.post("lib/book/getUserByEmpid.do",{"empid":datas.code},function(res){
				if(res.success){
		            $("#receivedBy").val(res.data[0][0]);
	                $("#receivedByName").val(res.data[0][1]);
				}
			});
		 	
		});
    }
</script>
</head>
<body>
	<form id="formObj" action="">
		<table cellpadding="3" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">领取人：</td>
				<td class="l-t-td-right" colspan="3">
					<input name="receivedBy" id="receivedBy" type="hidden"/>
					<input name="receivedByName" id="receivedByName" 
						type="text" ltype="text" validate="{required:true}"
						ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectUser()}}]}" 
						onclick="selectUser()" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">图书二维码：</td>
				<td class="l-t-td-right" colspan="3">
					<input name="qrcode" id="qrcode" type="text" ltype="text" validate="{required:true}" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
