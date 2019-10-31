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
<link href="app/weixin/css/style_favoriteico.css" rel="stylesheet" type="text/css">
<script src="app/weixin/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<%
String userId = (String)session.getAttribute("Userid");
String accessToken = (String)session.getAttribute("AccessToken");

%>
<style>
 .bigboxh{ font-size:0.875em; color:#fff; text-align:center; line-height:2em;}
 .wzgs{
	height: 70px;
	
}
</style>
<script type="text/javascript">
var baseurl = $("base").attr("href") ;
//打开页面的时候先判断一下是否已经投票，如果已经投票，直接跳转到投票结果页面
$.ajax({
	url : "voteFavoriteIcoAction/checkVotedFinal.do",
	type : "post",
	async : false,
	success : function(data) {
		if (data.success) {
			//location.href = baseurl+"votePlayAction/toResultPageFinal.do";
		} else {
			location.href = baseurl+"voteFavoriteIcoAction/toResultPageFinal.do";
		}
	}
});


function changeName(val){
	if(val!=""){
		$.ajax({
			url : "voteFavoriteIcoAction/checkUserName.do",
			type : "post",
			async : false,
			data:{"userName":val},
			success : function(res) {
				if (res.success) {
					if (res.flag) {
						$.ajax({
							url : "voteFavoriteIcoAction/checkVotedFinalU.do",
							type : "post",
							async : false,
							data:{"Userid":val},
							success : function(data) {
								if (data.success) {
								} else {
									if(confirm( '该用户已经投票，是否跳转投票结果页面？')){
										location.href = baseurl+"voteFavoriteIcoAction/toResultPageFinal.do";
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
var voteArray = new Array("0","0","0");
//每个候选院标名字
var nameArray = new Array(3);
//已经投票数
var voteNum = 0 ;



</script>
<title>请选择你中意的院标</title>
	</head>
	<body>

<div class="pneal">

<img src="app/weixin/images_favoriteico/top_01.jpg" />

</div>

<div class="lm_title">投票规则：总共3个，每人选1个后提交。</div>
<div class="lm2"><img src="app/weixin/images_favoriteico/lm_02.jpg" /></div>
<div class="pneanin dome1">
	

	<div class="listopen">
		<div class="listopenin">
			<div class="bigbox">
				<div class="bigboxin" >
				
					<div class="boxb"><img src="app/weixin/images_favoriteico/tp01.png" /></div>
					<div class="">
						<div class="bigboxh">标志一</div>
					</div>
					<div class="boxbtn"><input type="button" value="投票"></div>
					<div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div>
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_favoriteico/tp02.png" /></div>
                <div class="">
						<div class="bigboxh">标志二</div>
				</div>
					 <div class="boxbtn"><input type="button" value="投票"></div>
					 <div class="alertbox">
						<div class="box-text ">已选择</div>
					</div>
				</div> 
				<div class="bigboxin ">
                <div class="boxb"><img src="app/weixin/images_favoriteico/tp03.png" /></div>
                <div class="">
						<div class="bigboxh">标志三</div>
				</div>
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


		 if($("div.thua").length==1){
				$('#alertContent').text("提交投票信息？");
				$('#okButton').unbind("click");
				$('#okButton').click(function(){submitVote();});
		       	$('#cancelButton').click(function(){reSetVote();});
		       	$('.cd-popup').addClass('is-visible');
			 	//submitVote();
		    }else if($("div.thua").length>1){
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
	if (voteNum >= 1 ){
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
		url : "voteFavoriteIcoAction/checkVotedFinal.do",
		type : "post",
		async : false,
		success : function(data) {
			if (data.success) {
				var url = "voteFavoriteIcoAction/saveRoundOne.do?result=" + voteArray.toString();
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
							location.href = baseurl+"voteFavoriteIcoAction/toResultPageFinal.do";
						} else {
							if(data.msg!=""){
								$('#alertContent').text(data.msg);
								//如果msg有信息 则说明当前轮次投票过期，需要重新刷新页面，进入下一轮投票
								location.href = baseurl+"voteFavoriteIcoAction/wUserInfoFinal.do";
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
				location.href = baseurl+"voteFavoriteIcoAction/toResultPageFinal.do";
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
	var url = "voteFavoriteIcoAction/reSetRoundOne.do";
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
	location.href = baseurl+"voteFavoriteIcoAction/wUserInfoFinal.do";
	
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