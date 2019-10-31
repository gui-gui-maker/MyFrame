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
<link href="app/weixin/css/style2.css" rel="stylesheet" type="text/css">
<script src="app/weixin/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<%
String userId = (String)session.getAttribute("Userid");
String accessToken = (String)session.getAttribute("AccessToken");
%>
<script type="text/javascript">
var baseurl = $("base").attr("href") ;
//打开页面的时候先判断一下是否已经投票，如果已经投票，直接跳转到投票结果页面
$.ajax({
	url : "voteRoundOneAction/checkVoted.do",
	type : "post",
	async : false,
	success : function(data) {
		if (data.success) {
		} else {
			location.href = baseurl+"voteRoundOneAction/toResultPage.do";
		}
	}
});


//再工具userId获取用户信息
<%-- $.ajax({
	url : "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=<%=accessToken%>&userid=<%=userId%>",
	type : "get",
	async : false,
	success : function(data) {
		/* if (data.success) {
		} else {
			location.href = baseurl+"voteRoundOneAction/toResultPage.do";
		} */
	}
}); --%>



//投票结果数组
var voteArray = new Array("0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0");
//每个候选人名字
var nameArray = new Array(22);
//已经投票数
var voteNum = 0 ;



</script>
<title>感动特检人物初选</title>
	</head>
	<body>

<div class="pneal">

<img src="app/weixin/images/top_01.jpg" />

</div>

<div class="lm_title">初选投票规则：当前候选人22位，每人选10位后提交。</div>
<div class="lm2"><img src="app/weixin/images/lm_02.jpg" /></div>
<div class="pneanin dome1">
	

	<div class="listopen">
		<div class="listopenin">
			<div class="bigbox">
				<div class="bigboxin">
				
					<div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1856"> --><img src="app/weixin/images/杨东01.png" /><!-- </a> --></div>
                    	<div class="bigboxh">杨东</div>
					<div class="boxbtn"><input type="button" value="投票"></div>
					<div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1850"> --><img src="app/weixin/images/任琼02.png" /><!-- </a> --></div>
					<div class="bigboxh">任琼</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1849"> --><img src="app/weixin/images/郭艳03.png" /><!-- </a> --></div>
					<div class="bigboxh">郭艳</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1859"> --><img src="app/weixin/images/王平杰04.png" /><!-- </a> --></div>
					<div class="bigboxh">王平杰</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1844"> --><img src="app/weixin/images/祝月华05.png" /><!-- </a> --></div>
					<div class="bigboxh">祝月华</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1865"> --><img src="app/weixin/images/秦云云06.png" /><!-- </a> --></div>
					<div class="bigboxh">秦云云</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1853"> --><img src="app/weixin/images/廖佳钰07.png" /><!-- </a> --></div>
					<div class="bigboxh">廖佳钰</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                
                
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1845"> --><img src="app/weixin/images/明子涵08.png" /><!-- </a> --></div>
					<div class="bigboxh">明子涵</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1846"> --><img src="app/weixin/images/吴洪成09.png" /><!-- </a> --></div>
					<div class="bigboxh">吴洪成</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1847"> --><img src="app/weixin/images/许雅娟10.png" /><!-- </a> --></div>
					<div class="bigboxh">许雅娟</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     


				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1855"> --><img src="app/weixin/images/陈德平11.png" /><!-- </a> --></div>
					<div class="bigboxh">陈德平</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>     
                
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1848"> --><img src="app/weixin/images/刘勇12.png" /><!-- </a> --></div>
					<div class="bigboxh">刘勇</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1854"> --><img src="app/weixin/images/周青13.png" /><!-- </a> --></div>
					<div class="bigboxh">周青</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1863"> --><img src="app/weixin/images/刘摇14.png" /><!-- </a> --></div>
					<div class="bigboxh">刘摇</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1864"> --><img src="app/weixin/images/彭朝华15.png" /><!-- </a> --></div>
					<div class="bigboxh">彭朝华</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>     
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1862"> --><img src="app/weixin/images/易伟琪16.png" /><!-- </a> --></div>
					<div class="bigboxh">易伟琪</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     
                
                                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1857"> --><img src="app/weixin/images/刘明旭17.png" /><!-- </a> --></div>
					<div class="bigboxh">刘明旭</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1861"> --><img src="app/weixin/images/王仁生18.png" /><!-- </a> --></div>
					<div class="bigboxh">王仁生</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>                     
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1860"> --><img src="app/weixin/images/周靖19.png" /><!-- </a> --></div>
					<div class="bigboxh">周靖</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>     
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1858"> --><img src="app/weixin/images/左坤20.png" /><!-- </a> --></div>
					<div class="bigboxh">左坤</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>     
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1852"> --><img src="app/weixin/images/王光明21.png" /><!-- </a> --></div>
					<div class="bigboxh">王光明</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>     
                
				<div class="bigboxin ">
                <div class="boxb"><!-- <a href="http://m.scsei.org.cn/index.php?action=entity&entityid=1851"> --><img src="app/weixin/images/李学贵22.png" /><!-- </a> --></div>
					<div class="bigboxh">李学贵</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
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

$(".bigboxin").each(function(index) {
	$(".bigboxin").eq(index).click(function(){
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


		 if(voteNum==10){
				$('#alertContent').text("提交投票信息？");
				$('#okButton').unbind("click");
				$('#okButton').click(function(){submitVote();});
		       	$('#cancelButton').click(function(){reSetVote();});
		       	$('.cd-popup').addClass('is-visible');
			 	//submitVote();
		    }else if($("div.thua").length>10){
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
	if (voteNum >= 10 ){
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
		url : "voteRoundOneAction/checkVoted.do",
		type : "post",
		async : false,
		success : function(data) {
			if (data.success) {
				var url = "voteRoundOneAction/saveRoundOne.do?result=" + voteArray.toString();
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
								location.href = baseurl+"voteRoundOneAction/toResultPage.do";
					    		$('.cd-popup').removeClass('is-visible');
					    	}); */
							
							location.href = baseurl+"voteRoundOneAction/toResultPage.do";
						} else {
							$('#alertContent').text("提交失败");
							$('.cd-popup').addClass('is-visible');
							$('#okButton').unbind("click");
							$('#okButton').click(function(){
					    		$('.cd-popup').removeClass('is-visible');
					    	});
						}
					}
				});
			} else {
				location.href = baseurl+"voteRoundOneAction/toResultPage.do";
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
	var url = "voteRoundOneAction/reSetRoundOne.do";
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
	location.href = baseurl+"voteRoundOneAction/wUserInfo.do";
	
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