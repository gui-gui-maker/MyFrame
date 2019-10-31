<%@page contentType="text/javascript;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%-- 框架统一管理，包含系统名称，工作标签，框架样式，窗口样式等功能 2013-6-25 下午5:29 lybide --%>
<%if ("1".equals(request.getParameter("get"))) {%><script type="text/javascript"><%}%>
var kFrameConfig=window['__systemParams'];

kFrameConfig=$.extend(true,{},kFrameConfig,{
	//用户配置
	user: {
		icon : "k/kui/images/head/1165.gif",//用户头像
		show : true//用户头像是否显示
	//是否显示头像
	},
	//loginOkUrl : "app/main.jsp",//登陆成功后跳转的页面
	base : "${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/",
	menu : {
		dataAdd : function(data) {//添加自定义菜单数组
			//return data;//必须返回data
			//使用原生数组方法添加自定义菜单，比如桌面、系统欢迎页面等。
			data[0]["children"]
					.unshift({
						"id" : "myIsIndexId",
						"text" : "系统首页",
						"url" : "javascript:(function(){mGetUrl('app/main-desktop.jsp');mPanelDispay({panel:'left',display:false,close:true});})();",
						"image" : "k/kui/images/icons/16/menu-default.png",
						"code" : "myIsIndex",
						"isexpand" : "true"//,
					//"html":'<div style="padding:5px;">'+kFrameConfig["name"]+'欢迎您，这里是自定义的html，当然，你可以干掉。</div>'
					}/*,{
					                "id": "sys_run_managess",
					                "text": "菜单测试数据",
					                "url": "null",
					                "image": "k/kui/images/icons/16/menu-default.png",
					                "code": "m002",
					                "width":"400",
					                "isexpand": "false",
					                "html": "<div style='padding:5px;'><img src='k/kui/images/icons/once/Clock.png' /><br><br>此菜单数据，位于app/demo/menu1.js，请自行修改app/public/k-frame-main.js</div>"
					            }*/);
			//2013-12-18 下午5:34 lybide 测试
			data[0]["children"].unshift({text:"演示系统",url:"",children:[
				{id:"demo1-0a",text:"列表1","url":"demo/demo_list.jsp"},
				{id:"demo1-0b",text:"列表2","url":"demo/demo_list2.jsp"},
				{id:"demo1-0c",text:"列表3","url":"demo/demo_list_iframe.jsp"},
				{id:"demo1-1",text:"统计图1","url":"demo/count_list.jsp"},
				{id:"demo1-2",text:"统计图2","url":"demo/count_list2.jsp"},
				{id:"demo1-dc",text:"我是第一层",children:[
					{id:"demo1-dc1",text:"我是第二层",children:[
						{id:"demo1-dc1a",text:"我是第三层"}
					]},
					{text:"我只是文字",isText:true},
					{text:"我只是文字我只是文字我只是文字我只是文字我只是文字我只是文字我只是文字",isText:true,image:"k/kui/images/icons/16/book.png"},
					{text:"我只是文字",isText:true,image:"k/kui/images/icons/16/exclamation.png"},
					{text:"我只是文字",isText:true,image:"k/kui/images/icons/16/time.png"},
					{text:"我只是文字",isText:true,image:"k/kui/images/icons/16/process1.png"},
					{text:"我是第三1层"},
					{text:"我是第三1层"},
					{text:"我是第三1层"}
				]},
				{text:"我是第一层",children:[
					{text:"我是第二1层",children:[
						{text:"我是第三1层"},
						{text:"我是第三2层"},
						{text:"我是第三3层"},
						{text:"我是第三4层"},
						{text:"我是第三5层"},
						{text:"我是第三6层"}
					]},
					{text:"我是第二2层",children:[
						{text:"我是第三21层",isText:true},
						{text:"我是第三22层",isText:true},
						{text:"我是第三23层",isText:true},
						{text:"我是第三24层"},
						{text:"我是第三25层"},
						{text:"我是第三26层"}
					]}
				]},
				{id:"demo1-qm",text:"通用查询","url":"k/qm/page.jsp"}
			]});
			return data;//必须返回data
		},
		end : null
	},
	temp:null
});

var kui = kFrameConfig;

<%if ("1".equals(request.getParameter("get"))) {%></script><%}%>