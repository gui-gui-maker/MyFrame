<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base.jsp"%>
<script type="text/javascript">
	$(function() {
        $("#MediaPlayer1").height($(window).height()-5);
        $("#wmp").height($(window).height()-5);
	});
</script>
<style type="text/css">
object, embed {
	padding: 0;
	margin: 0;
}
</style>
</head>
<body style="padding: 0; margin: 0;">
	<object classid="clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95" id="MediaPlayer1" height="100%" width="100%">
		<param name="Filename" value="fileupload/download.do?id=${param.fid}" /> 
        <param name="AutoStart" value="1"/>
		<embed type="application/x-mplayer2"
			pluginspage="http://www.microsoft.com/Windows/Downloads/Contents/Products/MediaPlayer/"
			id="wmp" src="fileupload/download.do?id=${param.fid}" Width="100%" width="100%" AutoStart="1"></embed>
	</object>
</body>
</html>