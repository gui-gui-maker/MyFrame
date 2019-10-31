<%@page contentType="text/javascript;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
var KUI_TREND_CONFIG={
	user: {},
	temp: null,
	base : "${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/",
	menu : {
		dataAdd : function(data) {
			if (kui["menu"].addData) {
				data=kui["menu"].addData(data);
			}
			return data;//必须返回data
		},
		end : null
	}
};
var PAGE_KEYS=document.getElementsByTagName("head")[0].getAttribute("pageKeys");
if (PAGE_KEYS!="login" && PAGE_KEYS!="sessionTimeOut" && window==top) {
	//每个页面都需要执行，必须读取用户配置信息 2014年10月16日 15:49:48 星期四 lybide
	document.write(unescape('%3Cscript type="text/javascript" src="pub/sysPersonalize/getPersonalize.do"%3E%3C/script%3E'));
}