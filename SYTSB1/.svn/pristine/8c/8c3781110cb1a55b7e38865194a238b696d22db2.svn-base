<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width">
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<title>投票</title>
<link href="app/weixin/css/style.css" rel="stylesheet" type="text/css">
<script src="app/weixin/js/jquery.js" type="text/javascript"></script>
</head>

<script type="text/javascript">
window.setInterval(showResult,3000);
jQuery(document).ready(function($){

	showResult();
	
});

function showResult(){
	$.ajax({
		url : "voteRoundOneAction/toResultPageScreen.do",
		type : "post",
		async : false,
		success : function(data) {
			if (data.success) {
				var total3 = data.data.total[2];
				var total2 = data.data.total[1];
				var total1 = data.data.total[0];
				//alert(total1[10]+"   "+total2[10]+"  "+total3[10]);
				for(var i = 0 ; i < total3.length ;i++){
					$('#i'+i+'0').css("height",Percentage(total3[i])+"px");
					$('#i'+i+'1').css("height",Percentage(total2[i])+"px");
					$('#i'+i+'2').css("height",Percentage(total1[i])+"px");
					$('#t'+i).html(total3[i]);
					//$('#e'+i+'0').html(total3[i]-total2[i]);
					//$('#e'+i+'1').html(total2[i]-total1[i]);
					//$('#e'+i+'2').html(total1[i]);
				}
				
				//$('#i00').css("height",Percentage(total3[0],total3[10])+"px");
				//$('#i01').css("height",Percentage(total2[0],total3[10])+"px");
				//$('#i02').css("height",Percentage(total1[0],total3[10])+"px");
			} else {
				for(var i = 0 ; i < 10 ;i++){
					$('#i'+i+'0').css("height","0px");
					$('#i'+i+'1').css("height","0px");
					$('#i'+i+'2').css("height","0px");
					$('#t'+i).html("0");
				}
			}
			getStep();
		}
	});
}
function Percentage(num) { 
	var total = 750; //写死总共要投票的人数
	//alert(180 * (Math.round(num / total * 100) / 100.00)+"    "+num+"  "+total)
    return 370 * (Math.round(num / total * 100) / 100.00);// 小数点后两位百分比
   
}


function getStep(){
	$.get("voteRoundOneAction/getStep.do",function(res){
		//alert(JSON.stringify(res));
		$("#top2").show();
		$("#top1").hide();
		$('#message').html("第"+step+"轮已投票"+res.stepVoted+"人");
		var step ="";
		if("1"==res.step){
			step ="一";
		} else if ("2"==res.step){
			step ="二";
		} else if ("3"==res.step){
			step ="三";
		} else {
			$('#message').html("已投票"+res.stepVoted+"人");
			$("#top1").show();
			$("#top2").hide();
		}
		$("#step").html(step);
	})
}
</script>
<body>

<div class="wrap">
   <div class="mess"> <div class="mess_wz" ><em id="message" ></em></div></div>
   <div class="tutu">
   <div style="font-size: 20px;background:url(../images/lm_bg.jpg) no-repeat;background-size:100%;
      line-height:2.1em;font-size:30px;text-align:center; color:#fff;" id="top1">停止投票</div>
<div style="font-size: 20px;background:url(../images/lm_bg.jpg) no-repeat;background-size:100%; 
	 line-height:2.1em;font-size:30px;text-align:center; color:#fff;" id="top2">现场投票：第<span id="step"></span>轮投票</div> 
              <div class="item">
              
                <span class="bar">
                
                    <i class="yellow" id="i00">  <em class="count" id="e00"></em></i>    
                    
                    <i class="orange" id="i01">  <em class="count" id="e01"></em></i>
    
                    <i class="red" id="i02">  <em class="count" id="e02"></em></i>

				    <div class="count3" id="t0"></div>
				    
                </span>
               
              
                <div class="name_i">
                    <i class="name"><img src="app/weixin/images/杨东01_x.png" border="0"></i>
                </div>
                
                <span class="txt_n">杨东</span>

              </div>
              
              <div class="item">
              
                <span class="bar">
                
                    <i class="yellow" id="i10">  <em class="count" id="e10"></em></i>    
                    
                    <i class="orange" id="i11">  <em class="count" id="e11"></em></i>
    
                    <i class="red" id="i12">  <em class="count" id="e12"></em></i>

				    <div class="count3" id="t1"></div>
				    
                </span>
               
              
                <div class="name_i">
                    <i class="name"><img src="app/weixin/images/任琼02_x.png" border="0"></i>
                </div>
                
                <span class="txt_n">任琼</span>

              </div>
              
              <div class="item">
              
                <span class="bar">
                
                    <i class="yellow" id="i20">  <em class="count" id="e20"></em></i>    
                    
                    <i class="orange" id="i21">  <em class="count" id="e21"></em></i>
    
                    <i class="red" id="i22">  <em class="count" id="e22"></em></i>

				    <div class="count3" id="t2"></div>
				    
                </span>
               
              
                <div class="name_i">
                    <i class="name"><img src="app/weixin/images/明子涵08_x.png" border="0"></i>
                </div>
                
                <span class="txt_n">明子涵</span>

              </div>
              

          <div class="item">
              
                <span class="bar">
                
                    <i class="yellow" id="i30">  <em class="count" id="e30"></em></i>    
                    
                    <i class="orange" id="i31">  <em class="count" id="e31"></em></i>
    
                    <i class="red" id="i32">  <em class="count" id="e32"></em></i>

				    <div class="count3" id="t3"></div>
				    
                </span>
               
              
                <div class="name_i">
                    <i class="name"><img src="app/weixin/images/易伟琪16_x.png" border="0"></i>
                </div>
                
                <span class="txt_n">易伟琪</span>

              </div>



          <div class="item">
              
                <span class="bar">
                
                    <i class="yellow" id="i40">  <em class="count" id="e40"></em></i>    
                    
                    <i class="orange" id="i41">  <em class="count" id="e41"></em></i>
    
                    <i class="red" id="i42">  <em class="count" id="e42"></em></i>

				    <div class="count3" id="t4"></div>
				    
                </span>
               
              
                <div class="name_i">
                    <i class="name"><img src="app/weixin/images/祝月华05_x.png" border="0"></i>
                </div>
                
                <span class="txt_n">祝月华</span>

              </div>

              <div class="item">
              
                <span class="bar">
                
                    <i class="yellow" id="i50">  <em class="count" id="e50"></em></i>    
                    
                    <i class="orange" id="i51">  <em class="count" id="e51"></em></i>
    
                    <i class="red" id="i52">  <em class="count" id="e52"></em></i>

				    <div class="count3" id="t5"></div>
				    
                </span>
               
              
                <div class="name_i">
                    <i class="name"><img src="app/weixin/images/王光明21_x.png" border="0"></i>
                </div>
                
                <span class="txt_n">王光明</span>

              </div>
              
              <div class="item">
              
                <span class="bar">
                
                    <i class="yellow" id="i60">  <em class="count" id="e60"></em></i>    
                    
                    <i class="orange" id="i61">  <em class="count" id="e61"></em></i>
    
                    <i class="red" id="i62">  <em class="count" id="e62"></em></i>

				    <div class="count3" id="t6"></div>
				    
                </span>
               
              
                <div class="name_i">
                    <i class="name"><img src="app/weixin/images/李学贵22_x.png" border="0"></i>
                </div>
                
                <span class="txt_n">李学贵</span>

              </div>              
              
              <div class="item">
              
                <span class="bar">
                
                    <i class="yellow" id="i70">  <em class="count" id="e70"></em></i>    
                    
                    <i class="orange" id="i71">  <em class="count" id="e71"></em></i>
    
                    <i class="red" id="i72">  <em class="count" id="e72"></em></i>

				    <div class="count3" id="t7"></div>
				    
                </span>
               
              
                <div class="name_i">
                    <i class="name"><img src="app/weixin/images/周青13_x.png" border="0"></i>
                </div>
                
                <span class="txt_n">周青</span>

              </div>        

              <div class="item">
              
                <span class="bar">
                
                    <i class="yellow" id="i80">  <em class="count" id="e80"></em></i>    
                    
                    <i class="orange" id="i81">  <em class="count" id="e81"></em></i>
    
                    <i class="red" id="i82">  <em class="count" id="e82"></em></i>

				    <div class="count3" id="t8"></div>
				    
                </span>
               
              
                <div class="name_i">
                    <i class="name"><img src="app/weixin/images/刘摇14_x.png" border="0"></i>
                </div>
                
                <span class="txt_n">刘摇</span>

              </div>   
              
              <div class="item">
              
                <span class="bar">
                
                    <i class="yellow" id="i90">  <em class="count" id="e90"></em></i>    
                    
                    <i class="orange" id="i91">  <em class="count" id="e91"></em></i>
    
                    <i class="red" id="i92">  <em class="count" id="e92"></em></i>

				    <div class="count3" id="t9"></div>
				    
                </span>
               
              
                <div class="name_i">
                    <i class="name"><img src="app/weixin/images/彭朝华15_x.png" border="0"></i>
                </div>
                
                <span class="txt_n">彭朝华</span>

              </div>                 
              
              
              

    </div>    
    
    
</div>

</body>
</html>
