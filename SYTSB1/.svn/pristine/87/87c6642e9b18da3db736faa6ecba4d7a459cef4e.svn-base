<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告领取</title>
<%@ include file="/k/kui-base-form.jsp"%>
</head>
<script type="text/javascript">
	var pageStatus = "${param.status}";		
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		
			}, 
			getSuccess: function (response){
			
			}, 
			toolbar: [{
						text: "关闭", icon: "cancel", click: function(){
							api.data.window.refreshGrid();
							api.close();
						}
					}], 
			toolbarPosition: "bottom"
		});	
	    $.post("report/draw/savePreparedQrcode.do",
	    		{
	    			"ids":api.data.ids,
	    			"report_sns":api.data.report_sns
	    		},
	    		function(res){
	    			if(res.success){
	    				var qrcode = res.data;
	    				$("img").attr("src","report/draw/savePreparedQrcodeImg.do?qrcode="+qrcode);
	    			}else{
	    				$("#xp").html("获取二维码失败！");
	    			}
	    			
	    		}
   		);
	    
	});
</script>
<body>
<form action="">
<h2 id="xp">扫描下方二维码，跳转到报告领取签字页面。</h2>
	<div style="height:300px;width:300px;margin:0 auto;padding:70px 70px;">
		<img src="" alt="" />
	</div>
</form>
</body>
</html>