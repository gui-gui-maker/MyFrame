<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head id="main_head">
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-list.jsp"%>
    <script type="text/javascript" src="k/kui/frame/lhgdialog.min.js"></script>
    <script type="text/javascript" src="k/kui/frame/ui.ext.js"></script>
    <script type="text/javascript">
		var tab = null;
		var accordion = null;
		var tree = null;
		$(function () {
			//布局
			$("#layout1").ligerLayout({ leftWidth:190,bottomHeight:30,allowBottomResize:false,space:2, height:'100%', onHeightChanged:f_heightChanged });

			var height = $(".l-layout-center").height();

			//Tab
			$("#framecenter").ligerTab({ height:height });

			//面板
			$("#accordion1").ligerAccordion({ height:height - 24, speed:null });

			$(".l-link").hover(function () {
				$(this).addClass("l-link-over");
			}, function () {
				$(this).removeClass("l-link-over");
			});
			$("#menu_tree1").ligerTree({
				checkbox:false,
				url:'rbac/resource/transformMenu.do?id=webmanager',
				attribute:["url"],
				onSelect:function(node){//2012-02-16 10:25:51 (4) lybide
					if (!node.data.url) return;
					var tabid = $(node.target).attr("tabid");
					if (!tabid) {
						tabid = new Date().getTime();
						$(node.target).attr("tabid", tabid)
					}
					/*if ($(">ul >li", tab.tab.links).length >= 4)
					 {
					 var currentTabid = $("li.l-selected", tab.tab.links).attr("tabid"); //当前选择的tabid
					 if (currentTabid == "home") return;
					 tab.overrideTabItem(currentTabid, { tabid: tabid, url: node.data.url, text: node.data.text });
					 return;
					 }*/
					 if(node.data.url!="null")
					f_addTab(tabid, node.data.text, node.data.url);
				}
			});
			
			//系统按钮事件绑定
			$(".m_sys_icn a").click(function(){
				topButton($(this));return false;
			})
			$(".m_username a").click(function(){
				topButton($(this));return false;
			})

			tab = $("#framecenter").ligerGetTabManager();
			accordion = $("#accordion1").ligerGetAccordionManager();
			tree = $("#tree1").ligerGetTreeManager();
			$("#pageloading").hide();
			//框架底部
			setUIFoot();
		});
		function f_heightChanged(options) {
			if (tab)
				tab.addHeight(options.diff);
			if (accordion && options.middleHeight - 24 > 0)
				accordion.setHeight(options.middleHeight - 24);
		}
		function f_addTab(tabid, text, url) {
			tab.addTabItem({tabid:tabid, text:text, url:url });
		}
	function topButton(aObj){
			var keys=aObj.attr("id");
			if(keys=="mSysmMain") {
				//alert("打开系统首页");
				window.location=$("base").attr("href")+"rbac/user/loginSuccess.do";
			} else if(keys=="mPasEdit") {
				alert("打开修改密码");
			} else if(keys=="mHelp") {
				alert("打开帮助中心");
			} else if(keys=="mExitSys") {
				//alert("打开安全退出");
				window.location=$("base").attr("href")+"";
			}
		};
	</script>
    <script type="text/javascript">
        $(function ()
        {
            $("tree1").ligerTree({url:'/demos/tree/tree.json' });
        });
    </script>
    </head>
    <body>
<div id="pageloading"></div>
<div id="layout1">
		<div position="top" class="m_top"> 
		<!-- 框架头部 start -->
		<div class="m_top_bg">
				<div class="m_caption" id="systemTitle">
				<div>网站内容管理系统</div>
			</div>
				<div class="m_sys_icn"><a href="javascript:void(0);" id="mSysmMain" title="系统首页"><span class="sin_hm"></span></a> <a href="javascript:void(0);" id="mPasEdit" title="修改密码"><span class="sin_pw"></span></a> <a href="javascript:void(0);" id="mHelp" title="帮助中心"><span class="sin_hp"></span></a> <a href="javascript:void(0);" id="" title="全屏" class="last"><span class="sin_screen"></span></a></div>
				<div class="m_username"><span class="user_ava"><img src="k/kui/skins/Aqua/images/kui/photo.png" alt="用户头像" /></span><span class="user_name">实名朋友</span><a href="javascript:void(0);" class="sin_ex" id="mExitSys">[退出]</a></div>
			</div>
		<!-- 框架头部 over --> 
	</div>
		<div position="left" title="民政信息管理系统" id="accordion1">
			<ul id="menu_tree1">
			</ul>
	</div>
		<div position="center" id="framecenter">
		<div tabid="home" title="我的主页" class="myhome">
				<iframe frameborder="0" name="home" id="home" src="app/webmanage/welcome.jsp"></iframe>
			</div>
	</div>
		<div position="bottom" id="foot" class="m_bottom"></div>
	</div>
</body>
</html>
<jsp:include page="/k/kui-core.jsp"/>