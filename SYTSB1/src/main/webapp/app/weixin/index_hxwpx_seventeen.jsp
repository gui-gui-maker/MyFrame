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
<link href="app/weixin/css/style_hxwpx_seventeen.css" rel="stylesheet" type="text/css">
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

$.ajax({
	url : "goodnewsSeventeenAction/checkVotedFinal.do",
	type : "post",
	async : false,
	success : function(data) {
		if (data.success) {
			//location.href = baseurl+"votePlayAction/toResultPageFinal.do";
		} else {
			location.href = baseurl+"goodnewsSeventeenAction/toResultPageFinal.do";
		}
	}
});

//投票已结束
//$(function() {
	/* $.dialog.tips("投票已结束！",5,icon,function(){
		location.href = baseurl+"voteGoodnewsAction/toResultPageFinal.do";
	}) */
//	location.href = baseurl+"voteGoodnewsAction/toResultPageFinal.do";
//})

function changeName(val){
	if(val!=""){
		$.ajax({
			url : "goodnewsSeventeenAction/checkUserName.do",
			type : "post",
			async : false,
			data:{"userName":val},
			success : function(res) {
				if (res.success) {
					if (res.flag) {
						$.ajax({
							url : "goodnewsSeventeenAction/checkVotedFinalU.do",
							type : "post",
							async : false,
							data:{"Userid":val},
							success : function(data) {
								if (data.success) {
								} else {
									if(confirm( '该用户已经投票，是否跳转投票结果页面？')){
										location.href = baseurl+"goodnewsSeventeenAction/toResultPageFinal.do";
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
var voteArray = new Array("0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0");
//每个新闻名字
var nameArray = new Array(27);
//已经投票数
var voteNum = 0 ;



</script>
<title>2016年度“好新闻奖” 投票</title>
	</head>
	<body>

<div class="pneal">

<img src="app/weixin/images_hxwpx_seventeen/top_2017.jpg" />

</div>

<div class="lm_title">投票规则：总共27个，每人选14个后提交。</div>
<div class="lm2"><img src="app/weixin/images/lm_02.jpg" /></div>
<div class="pneanin dome1">
	

	<div class="listopen">
		<div class="listopenin">
			<div class="bigbox">
				<div class="bigboxin" >
				
					<div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/bgs_1.jpg" /></div>
					<div class="wzgs">
						<div class="bigboxh">蒋青一行赴泸州开展“紧贴中心，服务大局”课题调研</div>
					</div>
					<div class="boxbtn"><input type="button" value="投票">
					</div>
					 <div class="boxbtn1">
					<input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=2007', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					</div>
					<div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/rlzyb_2.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">蒋青带队慰问省特检院退休老同志</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=2209', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div> 
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/cwglb_3.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">加班加点忙测算 一心一意为职工</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=9014', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/zlglb_4.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">我院召开2016年第三季度质量安全风险排查例会</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=9228', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/kyjsglb_5.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">熊荣带队参加“2016全国特种设备安全与节能学术会议暨科技成果展”</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=2258', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<!-- 修改的新闻 -->
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/ywfzb_6.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">业务发展部一行赴西南油气分公司输气处业务交流</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=10049', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
                
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/bzb_7.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">保障部组织密闭空间呼吸设备现场培训</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=8271', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                
                
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/ywfwb_8.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">人力资源管理部（党办）挂职交流心得</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=9281', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     
                
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/dcs_9.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">督查室积极配合省局阳光政务热线解决群众反映的问题</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=8841', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     
                
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/jjb_10.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">蒋青率队调研新基地规划</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=8664', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     

				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/kys_11.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">省特检院首次参加ASME企业换证联检</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=1997', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>     
                
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/jd1_12.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">机电一部集中力量开展环球中心电梯检验工作</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=8469', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/jd2_13.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">省特检院开展电梯安全知识进校园宣讲活动</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=2178', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/jd3_14.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">牢记使命筑精神</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=2221', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/jd4_15.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">蒋青看望省特检院驻锦江区工作站人员</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=2059', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div> 
				 
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/jd5_16.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">检查问题，排除隐患，确保电梯安全运行</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=8481', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/jd6_17.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">支援浙江省特检院游乐设施检验工作（记实二）</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=8628', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/rqjysyb_18.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">迎着朝阳披晚霞 四特精神闪光芒</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=2117', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/cpjysyb_19.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">产品监检部完成压力容器现场组焊监检任务</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=10050', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/gljysyb_20.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">二进西藏—世界最高垃圾发电厂安装监检纪实</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=9192', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/gcjysyb_21.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">贵州省特检院、四川省特检院安全附件“比对”活动圆满结束</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=9360', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/gdjysyb_22.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">四川特检人节日抢险 奋战一线 守护民生安全</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=2308', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/shzzjysyb_23.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">省特检院运用先进检测技术为某大型国企开展高温高压管线检测</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=2131', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/cqjjysyb_24.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">省特检院使用新一代高压储气井检测车提升检测能力</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=2107', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/wsjcsyb_25.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">石油钻井设备钢丝绳无损检测首次现场试验顺利完成</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=2232', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/sfjdzx_26.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">常压锅炉也要爆炸</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/?action=innerentity&entityid=10052', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
					 </div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_hxwpx_seventeen/stjxh_27.jpg" /></div>
                <div class="wzgs">
						<div class="bigboxh">弘扬工匠精神 彰显工匠风采</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票">
					 </div>
					 <div class="boxbtn1">
					 <input type="button" value="查看" onclick="javascript:window.open ('http://m.scsei.org.cn/index.php?action=entity&entityid=2225', 'newwindow', 'height=(window.screen.availHeight-30), width=(window.screen.availWidth-10), top=0, left=0,toolbar=no, menubar=no, scrollbars=no,  resizable=no, location=no, status=no') ">
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


		 if($("div.thua").length==14){
				$('#alertContent').text("提交投票信息？");
				$('#okButton').unbind("click");
				$('#okButton').click(function(){submitVote();});
		       	$('#cancelButton').click(function(){reSetVote();});
		       	$('.cd-popup').addClass('is-visible');
			 	//submitVote();
		    }else if($("div.thua").length>14){
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
	if (voteNum >= 14 ){
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
		url : "goodnewsSeventeenAction/checkVotedFinal.do",
		type : "post",
		async : false,
		success : function(data) {
			if (data.success) {
				var url = "goodnewsSeventeenAction/saveRoundOne.do?result=" + voteArray.toString();
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
							location.href = baseurl+"goodnewsSeventeenAction/toResultPageFinal.do";
						} else {
							if(data.msg!=""){
								$('#alertContent').text(data.msg);
								//如果msg有信息 则说明当前轮次投票过期，需要重新刷新页面，进入下一轮投票
								location.href = baseurl+"goodnewsSeventeenAction/wUserInfoFinal.do";
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
				location.href = baseurl+"goodnewsSeventeenAction/toResultPageFinal.do";
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
	var url = "goodnewsSeventeenAction/reSetRoundOne.do";
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
	location.href = baseurl+"goodnewsSeventeenAction/wUserInfoFinal.do";
	
}
</script>

<!--    <div class="form_ctrl form_submit" id="8" title="重新投票">
    <label class="ctrl_title"></label>
    <input type="submit" name="submit" value="重置投票" onclick="reSetVote()" style="color: white;  background-color:#f1a30f;">
  </div>  -->
<div style="text-align:center; padding-right:.6em;margin-top:1em;margin-bottom:.5em; font-size:1.1em; color: white">
<p>组织部门：文化宣传中心</p>
<p>技术支持：信息中心</p>
</div>

	</body>
</html>