<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="edit">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type=text/javascript src="ueditor/ueditor.config.js"></SCRIPT>  
	<script type=text/javascript src="ueditor/ueditor.all.min.js"></SCRIPT>
    <script type="text/javascript">
        $(function () {
//                        如果不设置额外参数，不用调用此方法，初始化时会自动调用
            $("#formObj").initForm({    //参数设置示例
                toolbarPosition:"top",
                success : function(responseText) {//处理成功
                    if (responseText.success) {
                        top.$.notice("保存成功！");
                    } else {

                            $.ligerDialog.error('保存失败' + responseText);
                    }
                }
            });
	        var editor1 = UE.getEditor("unitdesc",{initialFrameWidth:$("body").width()-180});
		    editor1.ready(function(){
				window.setTimeout(function(){
					var con = document.getElementById("unitdesc").value;
		    		editor1.setContent(con);
				},100);
		    });
			var editor = UE.getEditor("unitfdesc",{initialFrameWidth:$("body").width()-180});
			editor.ready(function(){
			    window.setTimeout(function(){
			    var con = document.getElementById("unitfdesc").value;
			    editor.setContent(con);
			   },100);
			});
            
        });
    </script>
</head>
<body>
<form id="formObj" action="app/zp/website/saveunit.do" getAction="app/zp/website/detail.do?id=sys001">
    <input type="hidden" id="id" name="id" value="sys001">
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>首页单位简介</div>
        </legend>
	    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
	    	<tr>
				<td class="l-t-td-left">内容：</td>
				<td class="l-t-td-right" colspan="3">
					<textarea name="unitfdesc" id="unitfdesc" class="l-textarea" ltype="textarea"></textarea> 
				</td>
			</tr>
	    </table>
    </fieldset>
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>单位简介</div>
        </legend>
	    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
	    	<tr>
				<td class="l-t-td-left">内容：</td>
				<td class="l-t-td-right" colspan="3">
					<textarea name="unitdesc" id="unitdesc" class="l-textarea" ltype="textarea"></textarea> 
				</td>
			</tr>
	    </table>
    </fieldset>
</form>
</body>
</html>
