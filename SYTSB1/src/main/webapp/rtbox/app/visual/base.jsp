<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--表单、详情、添加页面，引导方式--%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

<link href="rtbox/public/jQuery/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<!-- <link href="rtbox/public/jQuery/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />  -->
<link href="rtbox/public/jQuery/ligerUI/skins/ligerui-icons.css" rel="stylesheet" />

<script type="text/javascript" src="rtbox/app/visual/js/jquery.js"></script>
<script src="rtbox/public/jQuery/ligerUI/js/core/base.js" type="text/javascript"></script> 
<script src="rtbox/public/jQuery/ligerUI/js/ligerui.min.js" type="text/javascript"></script> 

<script src="rtbox/public/js/utils.js" type="text/javascript"></script> 

<script type="text/javascript" src="k/kui/frame/jquery.form.js"></script>
<script src="rtbox/public/jQuery/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="rtbox/public/jQuery/jquery-validation/jquery.metadata.js" type="text/javascript"></script> 
<script src="rtbox/public/jQuery/jquery-validation/messages_cn.js" type="text/javascript"></script>  

<link rel="stylesheet" href="rtbox/public/jQuery/artDialog/css/ui-dialog.css">
<script src="rtbox/public/jQuery/artDialog/dist/dialog-min.js"></script>

<link rel="stylesheet" type="text/css" href="rtbox/app/visual/css/jquery-ui.min.css">
<script type="text/javascript" src="rtbox/app/visual/js/jquery-ui.min.js"></script>
<script type="text/javascript">
	var kFrameConfig=[];
	 kFrameConfig.base="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/";
</script>

<script type="text/javascript" src="rtbox/public/js/uploadAsst.js"></script>
<style  type="text/css">
<!--

-->
.error{
color: red;
position: absolute;
}
</style>
