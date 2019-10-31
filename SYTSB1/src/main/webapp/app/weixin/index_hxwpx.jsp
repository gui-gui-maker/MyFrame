<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width">
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<link href="app/weixin/css/style_hxwpx.css" rel="stylesheet" type="text/css">
<script src="app/weixin/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<%
String userId = (String)session.getAttribute("Userid");
String accessToken = (String)session.getAttribute("AccessToken");

%>
<style>
 .bigboxh{ font-size:0.875em; color:#fff; text-align:center; line-height:1.3em;}
 .wzgs{
	height: 70px;
	
}
#box1{
        padding-right: 100px;
}
</style>
<script type="text/javascript">
var baseurl = $("base").attr("href") ;
//打开页面的时候先判断一下是否已经投票，如果已经投票，直接跳转到投票结果页面

/*$.ajax({
	url : "voteGoodnewsAction/checkVotedFinal.do",
	type : "post",
	async : false,
	success : function(data) {
		if (data.success) {
			//location.href = baseurl+"votePlayAction/toResultPageFinal.do";
		} else {
			location.href = baseurl+"voteGoodnewsAction/toResultPageFinal.do";
		}
	}
});*/

//投票已结束
$(function() {
	/* $.dialog.tips("投票已结束！",5,icon,function(){
		location.href = baseurl+"voteGoodnewsAction/toResultPageFinal.do";
	}) */
	location.href = baseurl+"voteGoodnewsAction/toResultPageFinal.do";
})

function changeName(val){
	if(val!=""){
		$.ajax({
			url : "voteGoodnewsAction/checkUserName.do",
			type : "post",
			async : false,
			data:{"userName":val},
			success : function(res) {
				if (res.success) {
					if (res.flag) {
						$.ajax({
							url : "voteGoodnewsAction/checkVotedFinalU.do",
							type : "post",
							async : false,
							data:{"Userid":val},
							success : function(data) {
								if (data.success) {
								} else {
									if(confirm( '该用户已经投票，是否跳转投票结果页面？')){
										location.href = baseurl+"voteGoodnewsAction/toResultPageFinal.do";
									}else{
										$("#user").val("");
									}
									
								}
							}
						});
					}else{
						alert(res.msg);
						$("#user").val("");
					}
				}
			}
		})
	}
	
}

//再工具userId获取用户信息
<%-- $.ajax({
	url : "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=<%=accessToken%>&userid=<%=userId%>",
	type : "get",
	async : false,
	success : function(data) {
		/* if (data.success) {
		} else {
			location.href = baseurl+"votePlayAction/toResultPage.do";
		} */
	}
}); --%>



//投票结果数组
var voteArray = new Array("0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0");
//每个新闻名字
var nameArray = new Array(23);
//已经投票数
var voteNum = 0 ;



</script>
<title>2015年度“好新闻奖” 评选</title>
	</head>
	<body>

<div class="pneal">

<img src="app/weixin/images_hxwpx/top_01.jpg" />

</div>

<div class="lm_title">投票规则：总共23个，每人选13个后提交。</div>
<div class="lm2"><img src="app/weixin/images/lm_02.jpg" /></div>
<div class="pneanin dome1">
	

	<div class="listopen">
		<div class="listopenin">
			<div class="bigbox">
				<div class="bigboxin" >
				
					<div class="boxb"><img src="app/weixin/images_hxwpx/bgs.jpg" /></div>
					<div class="wzgs">
						<div class="bigboxh">蒋青一行赴金牛区市场监管局、驻金牛区工作站调研</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：办公室</div> -->
					</div>
					<div class="boxbtn"><input type="button" value="投票">
					</div>
					 <div class="boxbtn1">
					<input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=1833', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					</div>
					<div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/rlzyb.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">我院召开老干部光荣退休欢送座谈会</div>
                    	<!-- <div class="bigboxh">【杯子歌】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：人力资源部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=1502', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div> 
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/cwglb.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">秦长海副局长一行到四川省特检院调研财务预算工作</div>
                    	<!-- <div class="bigboxh">【脱口秀】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：财务管理部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=1651', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/zlb.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">我院召开2015年第四季度质量安全风险排查例会</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：质量监督管理部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=8037', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/kgb.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">四川省特检院2015年科技工作会议顺利召开</div>
                    	<!-- <div class="bigboxh">【相声】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：科研技术管理部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=7739', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/fzb.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">林涛一行赴中石化达州普光气田、川气东送管道分公司进行业务交流</div>
                    	<!-- <div class="bigboxh">【小品】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：业务发展部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=6830', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
                
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/bzb.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">我院新增多款劳保神器为检验一线提供安全防护保障</div>
                    	<!-- <div class="bigboxh">【视频+歌曲】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：保障部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=7949', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                
                
                
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/fwb.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">省特检院“娘子军”的服务之道</div>
                    	<!-- <div class="bigboxh">【相声】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：业务服务部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=7903', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     
                
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/dcs.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">督察室积极组织干部职工参加警示教育活动</div>
                    	<!-- <div class="bigboxh">【小合唱】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：督查室</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=6797', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     
                
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/xxxczx.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">我们都是特检人 不怕山高水又深——宜宾天然气管道检验纪实</div>
                    	<!-- <div class="bigboxh">【话剧】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：信息宣传中心</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=1705', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     


				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/jjb.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">蒋青院长一行视察“省特检平台”项目建设工地</div>
                    	<!-- <div class="bigboxh">【魔术、游戏】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：基建办</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=6508', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>     
                
                
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/jd1.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">黄坚副院长率队赴甘肃特检院开展检验技术交流和比对</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：机电一部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=7709', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/jd2.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">省特检院全力保障全国第九届残运会比赛场地电梯运行安全</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：机电二部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://www.scsei.org.cn/?action=entity&entityid=1542', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/jd3.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">双流国际机场T2航站楼定期检验顺利完成</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：机电三部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=6780', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/jd4.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">杜艳雄率队赴广东省特检院开展电梯检验比对活动</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：机电四部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=7643', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div> 
				
				 
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/jd5.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">起重机实作培训基地在金堂授牌</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：机电五部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=7346', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/cy1.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">省特检院完成达州普光气田压力容器检验项目</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：承压一部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=7786', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/cy2.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">承压二部与陕西省锅检所进行超高压水晶釜定期检验比对</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：承压二部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=7841', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/cy3.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">世界首台600MW超临界循环流化床锅炉内部检验项目顺利完成</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：承压三部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=7564', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/cy4.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">承压四部赴磨溪净化厂进行检验交流工作</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：承压四部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=7760', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/cy5.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">省特检院完成成都市燃气输送压力管道隐患整治试点检验工作</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：承压五部</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=6908', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/xh.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">中国特检协会副秘书长姜国锋出席四川省特检协会2015年培训考试工作总结暨研讨会</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：协会</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://www.scasei.org.cn/news_show.aspx?id=894', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx/sfjd.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">自纠自查重实效，查漏补缺保鉴定</div>
                    	<!-- <div class="bigboxh">【舞蹈】</div> -->
                    	<!-- <div class="bigboxh">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：司法鉴定中心</div> -->
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=8084', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				<div class="clear"></div> 
			</div> 
		</div>
	</div>
</div>

<div class="cd-popup" role="alert">
	<div class="cd-popup-container">
		<p id="alertContent">提交投票信息？</p>
		<ul class="cd-buttons">
			<li><a id="okButton">提交</a></li>
            <li><a href="#0" class="cd-popup-cancel" id="cancelButton">重新投票</a></li>
		</ul>
        <a href="#0" class="cd-popup-close img-replace">Close</a>
	</div> <!-- cd-popup-container -->
</div>


<script type="text/javascript">
function init(){
	
	var userId = "<%=userId%>";
	if("noUserId"==userId){
		$("#u").show();
	}else{
		$("#u").hide();
	}
	
}
/* $(".boxbtn").each(function(index) {
	$(".boxbtn").eq(index).find("input").click(function(){
		
		
		if(!$(".alertbox").eq(index).hasClass('thua')) {
			if(!checkIfCanVote()){//先判断是否可以再投票
				return ;
			}
			addVoteNum();
			voteArray[index]='1';
			$(".alertbox").eq(index).addClass("thua");
        } else {
            absVoteNum();
        	voteArray[index]='';
            $(".alertbox").eq(index).removeClass('thua');
        }


		 if($("div.thua").length==10){
				$('#alertContent').text("你已经选择10位候选人");
				$('#okButton').unbind("click");
				$('#okButton').click(function(){submitVote();});
		       $('.cd-popup').addClass('is-visible');
		    }else if($("div.thua").length>10){
		         $('.cd-popup').addClass('is-visible');
				  $(".alertbox").eq(index).removeClass('thua');
		    }

	});
}); */

$(".boxbtn").each(function(index) {
	$(".boxbtn").eq(index).click(function(){
		if(!$(".alertbox").eq(index).hasClass('thua')) {
			if(!checkIfCanVote()){//先判断是否可以再投票
				return ;
			}
			addVoteNum();
			voteArray[index]='1';
			$(".alertbox").eq(index).addClass("thua");
        } else {
            absVoteNum();
        	voteArray[index]='0';
            $(".alertbox").eq(index).removeClass('thua');
        }


		 if($("div.thua").length==13){
				$('#alertContent').text("提交投票信息？");
				$('#okButton').unbind("click");
				$('#okButton').click(function(){submitVote();});
		       	$('#cancelButton').click(function(){reSetVote();});
		       	$('.cd-popup').addClass('is-visible');
			 	//submitVote();
		    }else if($("div.thua").length>13){
		         $('.cd-popup').addClass('is-visible');
				  $(".alertbox").eq(index).removeClass('thua');
		    }

	});
});

jQuery(document).ready(function($){

	
	//close popup
	$('.cd-popup').on('click', function(event){
		if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup') || $(event.target).is('.cd-popup-cancel') ) {
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
	});
	//close popup when clicking the esc keyboard button
	$(document).keyup(function(event){
    	if(event.which=='27'){
    		$('.cd-popup').removeClass('is-visible');
	    }
    });
});



function checkIfCanVote(){
	if (voteNum >= 13 ){
		$('#alertContent').text("提交投票信息？");
		$('#okButton').unbind("click");
		$('#okButton').click(function(){submitVote();});
       	$('#cancelButton').click(function(){reSetVote();});
		/* if(voteNum==10){
			submitVote();
		} */
		$('.cd-popup').addClass('is-visible');
		return false ;
	}
	return true;
}
function addVoteNum(){
	voteNum++;
}
function absVoteNum(){
	voteNum--;
}
function submitVote(){
	//先判断是否已经投票
	$.ajax({
		url : "voteGoodnewsAction/checkVotedFinal.do",
		type : "post",
		async : false,
		success : function(data) {
			if (data.success) {
				var url = "voteGoodnewsAction/saveRoundOne.do?result=" + voteArray.toString();
				$.ajax({
					url : url,
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							/* $('#alertContent').text("投票成功，点击确定按钮查看投票结果。");
							$('.cd-popup').addClass('is-visible');
							$('#okButton').unbind("click");
							$('#okButton').click(function(){
								location.href = baseurl+"votePlayAction/toResultPage.do";
					    		$('.cd-popup').removeClass('is-visible');
					    	}); */
							location.href = baseurl+"voteGoodnewsAction/toResultPageFinal.do";
						} else {
							if(data.msg!=""){
								$('#alertContent').text(data.msg);
								//如果msg有信息 则说明当前轮次投票过期，需要重新刷新页面，进入下一轮投票
								location.href = baseurl+"voteGoodnewsAction/wUserInfoFinal.do";
							} else {
								$('#alertContent').text("提交失败");
							}
							$('.cd-popup').addClass('is-visible');
							$('#okButton').unbind("click");
							$('#okButton').click(function(){
					    		$('.cd-popup').removeClass('is-visible');
					    	});
						}
					}
				});
			} else {
				location.href = baseurl+"voteGoodnewsAction/toResultPageFinal.do";
				$('#alertContent').text(data.msg);
				$('.cd-popup').addClass('is-visible');
				$('#okButton').unbind("click");
				$('#okButton').click(function(){
		    		$('.cd-popup').removeClass('is-visible');
		    	});
			}
		}
	});
	
	
}

function reSetVote(){
	var url = "voteGoodnewsAction/reSetRoundOne.do";
	$.ajax({
		url : url,
		type : "post",
		async : false,
		success : function(data) {
			if (data.success) {
				$('#alertContent').text("重置投票成功，可以重新投票");
				$('.cd-popup').addClass('is-visible');
				$('#okButton').unbind("click");
				$('#okButton').click(function(){
		    		$('.cd-popup').removeClass('is-visible');
		    	});
			} else {
				$('#alertContent').text("重置投票成功失败");
				$('.cd-popup').addClass('is-visible');
				$('#okButton').unbind("click");
				$('#okButton').click(function(){
		    		$('.cd-popup').removeClass('is-visible');
		    	});
			}
		}
	});
	location.href = baseurl+"voteGoodnewsAction/wUserInfoFinal.do";
	
}
</script>

<!--    <div class="form_ctrl form_submit" id="8" title="重新投票">
    <label class="ctrl_title"></label>
    <input type="submit" name="submit" value="重置投票" onclick="reSetVote()" style="color: white;  background-color:#f1a30f;">
  </div>  -->
<div style="text-align:center; padding-right:.6em;margin-top:1em;margin-bottom:.5em; font-size:1.2em; color: white">
<p>技术支持：信息宣传中心</p>
</div>

	</body>
</html>