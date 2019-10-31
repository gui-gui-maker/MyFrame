<%@page import="com.khnt.rbac.bean.Position"%>
<%@page import="com.khnt.security.CurrentSS3SecurityUser"%>
<%@page import="org.apache.velocity.runtime.directive.Foreach"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageKeys="sysMain">
	<%@include file="/k/kui-base.jsp"%>
	<title>Loading...</title>
	<script type="text/javascript">
		loadCoreLibrary("main");
	</script>

	<script type="text/javascript">
		<%
		String _orgName = "";
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String orgName = user.getUnit().getOrgName();
		if(!user.getUnit().getId().equals(user.getDepartment().getId()))
			orgName += ("-" + user.getDepartment().getOrgName());
		%>
		var _current_user_account = "<%=user.getUsername()%>";
		var userName = "<%=user.getName()%>";
		if(kFrameConfig.user.nickName){
			userName = kFrameConfig.user.nickName;
		}
		var loginUserName={org:"<%=orgName%>",name:userName};
		if(kui["SYS_POSITION_SET"]){
			<%
				Position position = user.getPosition();
				String posName = "";
				if(position!=null){
					posName = position.getPosName();
				}
				_orgName = "".equals(posName)?orgName:orgName+"-【"+posName+"】";
			%>
			loginUserName = {org:"<%=_orgName%>",name:userName};
		}
		var systemUserName="${pageContext.request.scheme}_${pageContext.request.serverName}_${pageContext.request.serverPort}${pageContext.request.contextPath}_"+(loginUserName["name"]);
		function sysMainPageComplate() {
			//menuRelocation("m001","org_manage_division");
			//mBigData();//载入大数据显示效果 2016年10月25日 09:52:21 lybide
		};
	</script>

	<%--<script type="text/javascript" src="k/kui/skins/extflat/js/frame-main.js"></script>--%>
	<%--<link rel="stylesheet" href="k/kui/skins/extflat/css/frame-main.css"/>--%>

</head>
<body>
<div id="sysMain">
	<div id="mTop" position="top" class="m-top">
		<div id="mTopDiv" class="m-top-div">
			<div id="systemTitle" class="m-top-logo">
				<div id="systemTitleText"></div>
			</div>
			<div id="mTopLeft" class="m-top-left"></div>
			<div id="mTopRight" class="m-top-right"></div>
		</div>
	</div>
	<div id="mSystemItem" class="m-system-item">
		<ul>
		</ul>
	</div>
	<div id="mUserInfo" class="m-user-info">
		<div id="mUserSetMenu" class="user-set-menu"></div>
		<div id="mUserInfoName" class="m-user-info-name">
			<div>loading</div>
		</div>
	</div>
	<div id="mFoldLeft" class="m-fold-left" title="收缩菜单工作区"><a><span>收缩/打开左菜单工作区</span></a></div>
	<div id="mFoldTop" class="m-fold-top" title="收缩顶部工作区"><a><span>收缩/打开主工作区</span></a></div>
	<div id="mFoldRight" class="m-fold-right" title="收缩右工作区"><a><span>收缩/打开右工作区</span></a></div>
	<div id="mFoldButtom" class="m-fold-buttom" title="收缩底工作区"><a><span>收缩/打开底工作区</span></a></div>
	<div id="mMenu1More" class="m-menu1-more"></div>
	<div id="mMenu1" class="m-menu1">
		<div class="m-menu1-div" id="mMenu1Div">
			<ul>
			</ul>
		</div>
	</div>
	<div id="mMenu2" position="left" class="m-menu2">
		<div class="m-menu2-1-div" id="mMenu2Div">

		</div>
	</div>
	<div id="framecenter" position="center" class="m-center"></div>
	<div id="mFoot" position="bottom" class="m-foot"></div>
</div>


</body>
</html>

