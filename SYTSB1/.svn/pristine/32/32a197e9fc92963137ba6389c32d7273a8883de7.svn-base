<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
    $(function() {
    	$("#mform").initForm({
    		toolbar: [{
    			text: "确定",
    			icon: "save",
    			click: setData
    		},{
    			text:"关闭",
    			icon:"cancel",
    			click:function(){
    				api.close()
    			}
    		}],
    		afterParse: function(){
    			$("#content").val(api.data.msg);
    		}
    	});
    });
    function setData(){
    	if(api.data.callback){
    		api.data.callback($("#content").val())
    	}
    	api.close();
    }
</script>
<style type="text/css">
*{font-size:14px!important;}
</style>
<body>
	<form id="mform">
		<textarea style="width:350px;height:350px;line-height:1.5em;" id="content"></textarea>
	</form>
</body>
</html>