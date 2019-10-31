<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
<title>投票</title>
<script type="text/javascript">
$(function () {
	getStep();
	$("#stop").click(function() {
		if(confirm( '确定停止该轮投票？')){
			$.get("voteRoundOneAction/chooseStep.do?step="+0,function(res){
				if(res.success){
					alert("停止投票成功！")
					if("1"==step){
						step ="一";
					}else if("2"==step){
						step ="二";
					}else if("3"==step){
						step ="三";
					}
					$("#top1").show();
					$("#top2").hide();
					$("#1").attr("checked",false);
					$("#2").attr("checked",false);
					$("#3").attr("checked",false);
				}
			})
		}
	});
	$('#sub').click(function() {
		var step = "1";
		$("[name='step']").each(
				function() {
					if ($(this).attr("checked")) {
						step = $(this).val();
						
				}
			})
		if(confirm( '确定开始该轮投票？')){
			$.get("voteRoundOneAction/chooseStep.do?step="+step,function(res){
				if(res.success){
					alert("现场投票轮数修改成功！")
					if("1"==step){
						step ="一";
					}else if("2"==step){
						step ="二";
					}else if("3"==step){
						step ="三";
					}
					$("#top2").show();
					$("#top1").hide();
					$("#step").html(step);
					$("#1").attr("checked",false);
					$("#2").attr("checked",false);
					$("#3").attr("checked",false);
				}
			})
		}
	});
});
function choose(step){
	if(step.value=="1"){
		$("#1").attr("checked",true);
		$("#2").attr("checked",false);
		$("#3").attr("checked",false);
	}else if(step.value=="2"){
		$("#2").attr("checked",true);
		$("#1").attr("checked",false);
		$("#3").attr("checked",false);
	}else{
		$("#3").attr("checked",true);
		$("#1").attr("checked",false);
		$("#2").attr("checked",false);
	}
}


function getStep(){
	$.get("voteRoundOneAction/getStep.do",function(res){
		//alert(JSON.stringify(res));
		$("#top2").show();
		$("#top1").hide();
		var step ="";
		if("1"==res.step){
			step ="一";
		}else if("2"==res.step){
			step ="二";
		}else if("3"==res.step){
			step ="三";
		}else{
			$("#top1").show();
			$("#top2").hide();
		}
		
		$("#step").html(step);
	})
}
</script>
</head>
<body>

<div class="pneal">
<img src="app/weixin/images/top_01.jpg" />
</div>
<div class="lm_title" style="font-size: 20px;" id="top1">停止投票</div>
<div class="lm_title" style="font-size: 20px;" id="top2">现场投票：第<span id="step"></span>轮投票 
		 <input type="button" id="stop" value="结束投票"  style="background-color:buttonface; margin-left:10px;
						width: 100px; height: 30px; font-size: 20px;"/></div>
  		 <table cellpadding="3" cellspacing="0" 
				style="border-color: blue;width: 100%;font-size: 40px; text-align: center;color: white;"
				id="zzjd">
				<tr>
					<td><input type="checkbox" name="step" id="1" style="width: 25px;height: 25px;"
							 value="1" onchange="choose(this)"><span> 第一轮</span>
					</td>
						
				</tr>
				<tr>
					<td><input type="checkbox" name="step" style="width: 25px;height: 25px;" 
							 id="2" value="2" onchange="choose(this)"><span> 第二轮</span>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="step" style="width: 25px;height: 25px;" 
							 id="3" value="3" onchange="choose(this)"><span> 第三轮</span>
					</td>
						
				</tr>
				<tr>
					<td align="center">
					<input type="button" id="sub" value="确定"  style="background-color:buttonface; 
						width: 200px; height:35px; font-size: 25px;"/>
					</td>
						
				</tr>
   			</table>
</body>
</html>
