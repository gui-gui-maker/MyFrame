<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
    String userid = user.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>地图展示</title>
        <%@include file="/k/kui-base-list.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-Compatible" content="ie=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
		<style type="text/css">
			body, html,#container {width: 100%;height: 100%;overflow: hidden;margin:0;font-size:14px; font-family: "Microsoft Yahei", "黑体", "宋体";}
		</style>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FUDWFK8X3lc6vvCdUEpd8pZhK1D8tceB"></script>
		<script language="JavaScript">
			//初始化地图
			$(function(){
				var map=null;
				map = new BMap.Map("container");          // 创建地图实例
				map.centerAndZoom(new BMap.Point(104.06792346,30.65994285), 10); 
				
				//缩放控件
				var naveCtl = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT});
				naveCtl.setOffset(new BMap.Size(110, 10));
				map.addControl(naveCtl);
				map.enableScrollWheelZoom(true);//允许鼠标滚动缩放
				
				//比例尺控件
				var scaCtl = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});
				scaCtl.setOffset(new BMap.Size(110, 20));
				map.addControl(scaCtl);
				
				parent.closeLoading();//关闭加载等待图层
			});
		</script>
    </head>
    <body>
    	<div id="container"></div>
 	</body>
</html>