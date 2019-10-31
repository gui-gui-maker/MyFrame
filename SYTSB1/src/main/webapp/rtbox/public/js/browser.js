// 请勿修改，否则可能出错
	function uaMatch() {
		var ua = navigator.userAgent, rMsie = /(msie\s|trident.*rv:)([\w.]+)/, rFirefox = /(firefox)\/([\w.]+)/, rOpera = /(opera).+version\/([\w.]+)/, rChrome = /(chrome)\/([\w.]+)/, rSafari = /version\/([\w.]+).*(safari)/;
		ua = ua.toLowerCase();
		var match = rMsie.exec(ua);
		if (match != null) {
			return {
				browser : "IE",
				version : match[2] || "0"
			};
		}
		var match = rFirefox.exec(ua);
		if (match != null) {
			return {
				browser : match[1] || "",
				version : match[2] || "0"
			};
		}
		var match = rOpera.exec(ua);
		if (match != null) {
			return {
				browser : match[1] || "",
				version : match[2] || "0"
			};
		}
		var match = rChrome.exec(ua);
		if (match != null) {
			return {
				browser : match[1] || "",
				version : match[2] || "0"
			};
		}
		var match = rSafari.exec(ua);
		if (match != null) {
			return {
				browser : match[2] || "",
				version : match[1] || "0"
			};
		}
		if (match != null) {
			return {
				browser : "",
				version : "0"
			};
		}
	}
	
	function _ntko_get_browser(){
		var browser;
		var version;
		var browserMatch = uaMatch();
		if (browserMatch.browser) {
			browser = browserMatch.browser;
			version = browserMatch.version;
		}
		return browser;
	}
	var _browser = _ntko_get_browser();
    
	function  openPostWindow(url, data, name){
		 if (_browser == "IE") {
		     var tempForm = document.createElement("form");  
		       tempForm.id="tempForm1";  
		       tempForm.method="post";  
		       tempForm.action=url;  
		       tempForm.target=name;  
		       
		       var hideInput = document.createElement("input");  
		      hideInput.type="hidden";  
		      hideInput.name= "ids"
		      hideInput.value= data;
		      tempForm.appendChild(hideInput);  
	
		     //监听事件的方法        打开页面window.open(name);
		      tempForm.addEventListener("onsubmit",function(){  window.open(name); });
		      document.body.appendChild(tempForm);  
		      
		        tempForm.submit();
		      document.body.removeChild(tempForm);

		}else if (_browser == "firefox") {
				     var tempForm = document.createElement("form");  
				       tempForm.id="tempForm1";  
				       tempForm.method="post";  
				       tempForm.action=url;  
				       tempForm.target=name;  
				       
				       var hideInput = document.createElement("input");  
				      hideInput.type="hidden";  
				      hideInput.name= "ids"
				      hideInput.value= data;
				      tempForm.appendChild(hideInput);  
		
				//ie浏览器的绑定事件
				      tempForm.attachEvent ("onsubmit",function(){  window.open(name); });
				      document.body.appendChild(tempForm);  
				      //触发事件
				     tempForm.fireEvent("onsubmit");
				      tempForm.submit();
				      document.body.removeChild(tempForm);
		
				  
		}else{
			   var tempForm = document.createElement("form");  
			  tempForm.id="tempForm1";  
			  tempForm.method="post";  
			  tempForm.action=url;  
			  tempForm.target=name;  
			  
			  var hideInput = document.createElement("input");  
			 hideInput.type="hidden";  
			 hideInput.name= "ids"
			 hideInput.value= data;
			 tempForm.appendChild(hideInput);  
			
			//监听事件的方法        打开页面window.open(name);
			 tempForm.addEventListener("onsubmit",function(){  window.open(name); });
			 document.body.appendChild(tempForm);  
			 
			   tempForm.submit();
			 document.body.removeChild(tempForm);
			
			
		}
		 
	 }