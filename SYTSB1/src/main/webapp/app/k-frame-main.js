
//=======================================================================================
//document.write('<script src="k/kui/frame/main-frame.js"><\/script>');//2014/11/10 15:37 lybide 必删除之
//document.write('<script src="k/kui/frame/main-frame.js.slj.js"><\/script>');
//=======================================================================================

//=======================================================================================
kFrameConfig=$.extend(true,{},kFrameConfig,{
	//框架页面最先载入事件
	mainBeginOnload:function(){//13-3-22 上午11:35 lybide
		//document.title = this.name;
		this.mCreatePageItem();//创建框架元素
	},
	//框架主页最后载入事件，主要是为了菜单异步完成后，再执行
	mainEndOnload:function(){
		//mPanelDispay({panel:"left",display:false});
		//mPanelDispay({panel:"top",display:true});
		//$(".m-user-info-name div").html(loginUserName);

		//右上角系统图标
		var str1='';
		//str1+='<li><a id="xgmm" keys="seUserPass" href="javascript:void(0);" style="background-position:-2040px 0px;" class="m-system-item-a" title="修改密码"><span>修改密码</span></a></li>';$("#mUserInfo").css("right","200px");
		str1+='<li><a id="topMIndex" keys="topMIndex" href="javascript:void(0);" style="background-position:0px 0px;" class="m-system-item-a" title="返回首页"><span>首页</span></a></li>';
		var userSkinSwitch=kui["userSkinSwitch"]!==false ? true : kui["userSkinSwitch"];
		if (userSkinSwitch) {
			str1+='<li><a id="topMSet" keys="setSoftSkin" href="javascript:void(0);" style="background-position:-72px 0px;" class="m-system-item-a"><span>设置</span></a>'+mMBTMenuStr('','<!--<a href="javascript:void(0);" keys="seUserPass">修改密码</a>--><a href="javascript:void(0);" keys="setSoftSkin">个性化管理</a><!--<a href="javascript:void(0);" keys="setUserHead">更改头像</a>-->')+'</li>';
		}
		str1+='<li><a id="topMOutSystem" keys="topMOutSystem" href="javascript:void(0);" style="background-position:-48px 0px;" class="m-system-item-a" title="退出系统"><span>退出系统</span></a></li>';
		$(".m-system-item ul").append(str1);

		//头像下拉菜单
		var temp = '<a href="javascript:void(0);" keys="seUserPass">修改密码</a>';
		if(kui["ENTRUST_OPEN"]){
			temp = temp +'<a href="javascript:void(0);" keys="setEntrust">委托设置</a>';
		}
		if(kui["SYS_POSITION_SET"]){
			temp = temp +'<a href="javascript:void(0);" keys="setPosition">岗位设置</a>'
		}
		str1=''+mMBTMenuStr('',temp)+'';
		$(".user-set-menu").append(str1);
		//$("#sysMain").append(str1);

		//头都右上角菜单事件绑定
		$(".m-system-item a,.m-user-info a,.m-m-children-div a").click(function(){
			var keys=$(this).attr("keys");//alert(keys);
			if (keys=="topMIndex") {
				welComeIndexShow();
			} else if (keys=="setUserIndividuation" || keys=="seUserPass") {
				top.$.dialog({
					width:450,
					height:300,
					lock:true,
					title:"修改密码",
					content:"url:pub/rbac/user_config.jsp"
				});
			} else if (keys=="setUserHead") {

			} else if(keys=="setEntrust"){
				top.$.dialog({
					width:450,
					height:250,
					lock:true,
					title:"委托设置",
					content:"url:pub/entrust/entrust_detail.jsp"
				});
			}else if(keys=="setPosition"){
				$.dialog({
					width:300,
					height:170,
					lock:true,
					max:false,
					min:false,
					title:"岗位设置",
					parent:api,
					content:"url:pub/rbac/user_position_detail.jsp"
				});
			}
			else if (keys=="setSoftSkin") {
//				top.$.dialog({
//					width:600,
//					height:500,
//					lock:true,
//					title:"系统界面设置",
//					content:"url:pub/rbac/user_skin.jsp?tabId=2"
//				});
                //add by alibaba 2014-06-12
                //if(!window["_userSetInit"]){
                //    $.getScript("k/kui/public/sys-set/userset.js", function(){
                //        window["_userSetInit"]=true;
                //        userSetInit();
                //    });
                //}else{
                    userSetInit();
                //}
			} else if (keys=="topMOutSystem") {
				//2014/12/9 11:04 lybide 新增外壳退出
				if (getkIsShell()) {
					var pwKeys=window.external.GetPopupWindowKeywords();
					pwKeys=pwKeys.split(";");
					for (var i = 0, l=pwKeys.length; i < l; i++) {
						window.external.ClosePopup(pwKeys[i]);
					}
					window.external.StopTrayFlicker();//取消闪烁
				}
				
				//清除SSL状态
				try{
				    if(window.document.execCommand){
				        document.execCommand('ClearAuthenticationCache');
				    }
				}catch(e){
				}
				
				window.location=kFrameConfig["base"]+"security/authentication/logout.do";
			}
			return false;
		});
		//$("#mMenu_myIsIndex").click();//框架载入完成后，自动进入欢迎页面。

		//打开欢迎页面
		setTimeout(function () {//采用此方法，解决ie9一直报错的问题 13-2-19 下午2:04 lybide
			//mGetUrl("pub/rbac/welcome.jsp");
			//mGetUrl("app/main-desktop.jsp");
		}, 10);
		//top.$.dialog.tips("系统打开");

		return false;
	},
	//菜单接口，根据传入的值，在工作区打开系统
	mGetUrl:function(url,menuId){
		setTimeout(function(){//13-3-21 下午4:19 lybide 增加延迟，解决每次点击时，锁定浏览器的问题。
			$("#home").attr("src",url);
		},1);
	},
	//创建框架html元素
	mCreatePageItem:function(){

	}
});
//主框架统一管理功能
//=======================================================================================


