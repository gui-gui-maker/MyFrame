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
<link href="app/weixin/css/style_hxwpx_seventeen.css" rel="stylesheet" type="text/css">
<script src="app/weixin/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<title>投票结果</title>
</head>
<%
	Map<String,Object> map = (HashMap<String,Object>)request.getAttribute("resultMap");
	List<Map.Entry<String, String>> mappingList = (List<Map.Entry<String, String>>)map.get("list");
	Map<String,String> voteMap = new HashMap<String,String>();
	Integer total = (Integer)map.get("total");
	int voteNum = total.intValue()/14;
%>
<body>
<style>
.txt_n{ text-align:left; color:#fff; display: inline-block; vertical-align:middle; width:25%; font-size:0.750em;}
.bar {
	background-color: #0e1e52;
	 height:15px;
	font-size: 0;
	line-height: 10px;
	display: inline-block;
	width: 45%;
	margin: 0 1%;
	vertical-align: middle;
}
</style>
<div class="pneal">
<img src="app/weixin/images_hxwpx_seventeen/top_2017.jpg" />
</div>
<div class="lm_title">投票结果</div>
<div class="lm2"><img src="app/weixin/images/lm_02.jpg" /></div>
<div class="pneanin dome1">
	<% for (int i = 0 ; i < mappingList.size() ; i++) { 
		Map.Entry<String, String> entry = mappingList.get(i);
		voteMap.put(entry.getKey(),entry.getValue());
	%>
		<%if(entry.getKey().equals("0")){ %>	
  			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/bgs_1.jpg" border="0"></i></div><span class="txt_n">蒋青一行赴泸州开展“紧贴中心，服务大局”课题调研</span><span class="bar"><i id="num_0" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("1")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/rlzyb_2.jpg" border="0"></i></div><span class="txt_n">蒋青带队慰问省特检院退休老同志</span><span class="bar"><i id="num_1"  ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("2")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/cwglb_3.jpg" border="0"></i></div><span class="txt_n">加班加点忙测算 一心一意为职工</span><span class="bar"><i id="num_2" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
  		<%} else if (entry.getKey().equals("3")){%>
  			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/zlglb_4.jpg" border="0"></i></div><span class="txt_n">我院召开2016年第三季度质量安全风险排查例会</span><span class="bar"><i id="num_3" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("4")){%>
			<div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/kyjsglb_5.jpg" border="0"></i></div><span class="txt_n">熊荣带队参加“2016全国特种设备安全与节能学术会议暨科技成果展”</span><span class="bar"><i id="num_4"  ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("5")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/ywfzb_6.jpg" border="0"></i></div><span class="txt_n">业务发展部一行赴西南油气分公司输气处业务交流</span><span class="bar"><i id="num_5" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("6")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/bzb_7.jpg" border="0"></i></div><span class="txt_n">保障部组织密闭空间呼吸设备现场培训</span><span class="bar"><i id="num_6" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("7")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/ywfwb_8.jpg" border="0"></i></div><span class="txt_n">人力资源管理部（党办）挂职交流心得</span><span class="bar"><i id="num_7" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("8")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/dcs_9.jpg" border="0"></i></div><span class="txt_n"> 督查室积极配合省局阳光政务热线解决群众反映的问题</span><span class="bar"><i id="num_8" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("9")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/jjb_10.jpg" border="0"></i></div><span class="txt_n">蒋青率队调研新基地规划</span><span class="bar"><i id="num_9" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("10")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/kys_11.jpg" border="0"></i></div><span class="txt_n">省特检院首次参加ASME企业换证联检</span><span class="bar"><i id="num_10" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%} else if (entry.getKey().equals("11")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/jd1_12.jpg" border="0"></i></div><span class="txt_n">机电一部集中力量开展环球中心电梯检验工作</span><span class="bar"><i id="num_11" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("12")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/jd2_13.jpg" border="0"></i></div><span class="txt_n">省特检院开展电梯安全知识进校园宣讲活动</span><span class="bar"><i id="num_12" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("13")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/jd3_14.jpg" border="0"></i></div><span class="txt_n">牢记使命筑精神</span><span class="bar"><i id="num_13" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("14")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/jd4_15.jpg" border="0"></i></div><span class="txt_n">蒋青看望省特检院驻锦江区工作站人员</span><span class="bar"><i id="num_14" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("15")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/jd5_16.jpg" border="0"></i></div><span class="txt_n">检查问题，排除隐患，确保电梯安全运行</span><span class="bar"><i id="num_15" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("16")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/jd6_17.jpg" border="0"></i></div><span class="txt_n">支援浙江省特检院游乐设施检验工作（记实二）</span><span class="bar"><i id="num_16" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("17")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/rqjysyb_18.jpg" border="0"></i></div><span class="txt_n">迎着朝阳披晚霞 四特精神闪光芒</span><span class="bar"><i id="num_17" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("18")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/cpjysyb_19.jpg" border="0"></i></div><span class="txt_n">产品监检部完成压力容器现场组焊监检任务</span><span class="bar"><i id="num_18" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("19")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/gljysyb_20.jpg" border="0"></i></div><span class="txt_n">二进西藏—世界最高垃圾发电厂安装监检纪实</span><span class="bar"><i id="num_19" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("20")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/gcjysyb_21.jpg" border="0"></i></div><span class="txt_n">贵州省特检院、四川省特检院安全附件“比对”活动圆满结束</span><span class="bar"><i id="num_20" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("21")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/gdjysyb_22.jpg" border="0"></i></div><span class="txt_n">四川特检人节日抢险 奋战一线 守护民生安全</span><span class="bar"><i id="num_21" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("22")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/shzzjysyb_23.jpg" border="0"></i></div><span class="txt_n">省特检院运用先进检测技术为某大型国企开展高温高压管线检测</span><span class="bar"><i id="num_22" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("23")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/cqjjysyb_24.jpg" border="0"></i></div><span class="txt_n">省特检院使用新一代高压储气井检测车提升检测能力</span><span class="bar"><i id="num_23" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("24")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/wsjcsyb_25.jpg" border="0"></i></div><span class="txt_n">石油钻井设备钢丝绳无损检测首次现场试验顺利完成</span><span class="bar"><i id="num_24" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("25")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/sfjdzx_26.jpg" border="0"></i></div><span class="txt_n">常压锅炉也要爆炸</span><span class="bar"><i id="num_25" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}else if (entry.getKey().equals("26")){%>
  <div class="item"><div class="name_i"><i class="name"><img src="app/weixin/images_hxwpx_seventeen/stjxh_27.jpg" border="0"></i></div><span class="txt_n">弘扬工匠精神 彰显工匠风采</span><span class="bar"><i id="num_26" ></i></span><em class="count"><%=entry.getValue()%>票</em></div>
		<%}%>
<%} %>


<div class="cd-popup" role="alert">
	<div class="cd-popup-container">
		<p id="alertContent"></p>
		<ul class="cd-buttons">
			<li><a id="okButton">确定</a></li>
            <li><a class="cd-popup-cancel" id="closeButton" >关闭</a></li>
		</ul>
        <a class="cd-popup-close img-replace">Close</a>
	</div> <!-- cd-popup-container -->
</div>

<script type="text/javascript">
var baseurl = $("base").attr("href") ;
jQuery(document).ready(function($){
	$('#num_0').css("width",Percentage("<%=voteMap.get("0")%>"));
	$('#num_1').css("width",Percentage("<%=voteMap.get("1")%>"));
	$('#num_2').css("width",Percentage("<%=voteMap.get("2")%>"));
	$('#num_3').css("width",Percentage("<%=voteMap.get("3")%>"));
	$('#num_4').css("width",Percentage("<%=voteMap.get("4")%>"));
	$('#num_5').css("width",Percentage("<%=voteMap.get("5")%>"));
	$('#num_6').css("width",Percentage("<%=voteMap.get("6")%>"));
	$('#num_7').css("width",Percentage("<%=voteMap.get("7")%>"));
	$('#num_8').css("width",Percentage("<%=voteMap.get("8")%>"));
	$('#num_9').css("width",Percentage("<%=voteMap.get("9")%>"));
	$('#num_10').css("width",Percentage("<%=voteMap.get("10")%>"));
	$('#num_11').css("width",Percentage("<%=voteMap.get("11")%>"));
	$('#num_12').css("width",Percentage("<%=voteMap.get("12")%>"));
	$('#num_13').css("width",Percentage("<%=voteMap.get("13")%>"));
	$('#num_14').css("width",Percentage("<%=voteMap.get("14")%>"));
	$('#num_15').css("width",Percentage("<%=voteMap.get("15")%>"));
	$('#num_16').css("width",Percentage("<%=voteMap.get("16")%>"));
	$('#num_17').css("width",Percentage("<%=voteMap.get("17")%>"));
	$('#num_18').css("width",Percentage("<%=voteMap.get("18")%>"));
	$('#num_19').css("width",Percentage("<%=voteMap.get("19")%>"));
	$('#num_20').css("width",Percentage("<%=voteMap.get("20")%>"));
	$('#num_21').css("width",Percentage("<%=voteMap.get("21")%>"));
	$('#num_22').css("width",Percentage("<%=voteMap.get("22")%>"));
	$('#num_23').css("width",Percentage("<%=voteMap.get("23")%>"));
	$('#num_24').css("width",Percentage("<%=voteMap.get("24")%>"));
	$('#num_25').css("width",Percentage("<%=voteMap.get("25")%>"));
	$('#num_26').css("width",Percentage("<%=voteMap.get("26")%>"));
});

function Percentage(num) {
    return (Math.round(num / "<%=map.get("total")%>" * 10000) / 100.00 + "%");// 小数点后两位百分比
   
}


function reSetVote(){
	var url = "goodnewsSeventeenAction/reSetRoundOne.do";
	$.ajax({
		url : url,
		type : "post",
		async : false,
		success : function(data) {
			if (data.success) {
				$('#alertContent').text("重置投票成功，点击确定按钮返回重新投票");
				$('.cd-popup').addClass('is-visible');
				$('#okButton').unbind("click");
				$('#okButton').click(function(){
					location.href = baseurl+"goodnewsSeventeenAction/wUserInfoFinal.do";
		    	});
				$('#closeButton').click(function(){
					$('.cd-popup').removeClass('is-visible');
		    	});
				
			} else {
				$('#alertContent').text("重置投票成功失败");
				$('.cd-popup').addClass('is-visible');
				$('#okButton').unbind("click");
				$('#okButton').click(function(){
		    		$('.cd-popup').removeClass('is-visible');
		    	});
				$('#closeButton').click(function(){
					$('.cd-popup').removeClass('is-visible');
		    	});
			}
		}
	});
}

</script>

 <div class="form_ctrl form_submit" id="8" title="投票人数">
    <label class="ctrl_title"></label>
    <input type="submit" name="submit" value="已投票人数： 【 <%=total%> 】 人"  style="color: white;  background-color:#f1a30f;">
  </div>
  
<div style="text-align:center; padding-right:.6em;margin-top:1em;margin-bottom:.5em; font-size:1.1em; color: white">
<p>组织部门：文化宣传中心</p>
<p>技术支持：信息中心</p>
</div>
</body>
</html>
