<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>测试</title>
    <%@include file="/k/mui/mobile-base.jsp" %>
    <style type="text/css">
    </style>
  	<script type="text/javascript">
  		var qm_title = ""; 
  		contentWebview = null;
  		function init(){
  			mui.plusReady(function() {
				var currentWebview =  plus.webview.currentWebview();
				currentWebview.append(plus.webview.create(currentWebview.QmUrl, currentWebview.QmUrl, {
					top: '48px', //内容页面顶部位置,需根据实际页面布局计算，若使用标准mui导航，顶部默认为48px；
					bottom: '0px'
				}));
			});
  		}
  		//查询监听
		function searchAction(){
			document.getElementById("searchForm").onsubmit = function(e){
				e.preventDefault();
				var searchDom = document.getElementById("searchKey");
				searchDom.blur();
				if(contentWebview==null){
					contentWebview = plus.webview.currentWebview().children()[0];
				}
				// 发出查找通知
				mui.fire(contentWebview, "searchEvent", {
					"searchKey": searchDom.value
				});
			};
		}
		//无title 设置查询条件
		function setSerachInfo(info,btn){
			var ch = parseDom(info)[0];
			document.getElementById("title").appendChild(ch);
			//绑定事件
			var ch1 = parseDom(btn)[0];
			document.getElementById("headInfo").appendChild(ch1);
			document.getElementById("btnSearch").addEventListener("tap",function(){
				var searchDom = document.getElementById("searchKey");
				searchDom.value = "";
				searchDom.blur();
				//刷新数据
				if(contentWebview==null){
					contentWebview = plus.webview.currentWebview().children()[0];
				}
				// 发出查找通知
				mui.fire(contentWebview, "searchEvent", {
					"searchKey": searchDom.value
				});
			})
			searchAction();
		}
		
		//设置title
		function setTitle(title){
			qm_title = title;
			var ch = parseDom(title)[0];
			document.getElementById("title").appendChild(ch);
		}
		//有title设置条件
		function setSearchListenWithTitle(obj,schinput){
			var ch = parseDom(obj)[0];
			document.getElementById("headInfo").appendChild(ch);
			document.getElementById("btnSearch").addEventListener("tap",function(){
				if(hasClass(this,'mui-icon-search')){
					removeClass(this,'mui-icon-search');
					addClass(this,'mui-icon-closeempty');
					setSearchInput(schinput);
				}else if(hasClass(this,'mui-icon-closeempty')){
					removeClass(this,'mui-icon-closeempty');
					addClass(this,'mui-icon-search');
					//显示title 并且刷刷新数据
					document.getElementById("title").innerHTML = '';
					setTitle(qm_title);
					//刷新数据
					if(contentWebview==null){
						contentWebview = plus.webview.currentWebview().children()[0];
					}
					// 发出查找通知
					mui.fire(contentWebview, "searchEvent", {
						"searchKey": ""
					});
				}
			})
		}
		
		function setSearchInput(schinput){
			//判断索索按钮的状态
			var ch = parseDom(schinput)[0];
			document.getElementById("title").innerHTML = '';
			document.getElementById("title").appendChild(ch);
			searchAction();
		}
		
		
		function setRightBar(obj,type){
			var ch = parseDom(obj)[0];
			document.getElementById("headInfo").appendChild(ch);
			setRightBarAction(type);
		}
		
		function setRightBarAction(type){
			document.getElementById("navRightBtn").addEventListener("tap",function(){
				if(contentWebview==null){
					contentWebview = plus.webview.currentWebview().children()[0];
				}
				// 发出查找通知
				mui.fire(contentWebview, "navRigthBtnEvent", {
					"type": type
				});
			});
		}
		
		function parseDom(arg){
			var objE = document.createElement("div"); 
			objE.innerHTML = arg; 
			return objE.childNodes; 
		}
		
		function addClass(obj, cls) {  
		    if (!this.hasClass(obj, cls)) obj.className += " " + cls;  
		}  
		  
		function removeClass(obj, cls) {  
		    if (hasClass(obj, cls)) {  
		        var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');  
		        obj.className = obj.className.replace(reg, ' ');  
		    }  
		}
		function hasClass(obj, cls) {  
		    return obj.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));  
		}
		
		window.onload = function(){
			init();
		}
	</script>
</head>
<body >
	<header class="mui-bar mui-bar-nav" style='position: relative;' id='headInfo'>
        <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        <h1 class="mui-title" id='title'>
        </h1>
    </header>
</body>
</html>