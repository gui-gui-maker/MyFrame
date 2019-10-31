<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="util.TS_Util"%>
<%@page import="com.lsts.webservice.cxf.server.QueryDataService"%>
<%@page import="util.ReportUtil"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.lsts.inspection.dao.InspectionDao"%>
<%@page import="java.util.List"%>
<%@ page import="com.lsts.inspection.bean.FlowInfoDTO"%>
<%@page import="com.khnt.utils.StringUtil" %>
<%@page import="com.lsts.employee.bean.EmployeeDTO"%>
<%@page import="com.lsts.employee.dao.EmployeeCertDao"%>
<%@page import="com.lsts.relevant.dao.RelevantPeopleCertDao"%>
<%@page import="com.lsts.relevant.bean.RelevantPeopleDTO"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@page import="com.scts.payment.dao.InspectionPayInfoDao"%>
<%@page import="com.scts.payment.bean.InspectionInfoDTO"%>
<%@page import="com.scts.maintenance.dao.MaintenanceInfoDao"%>
<%@page import="com.scts.maintenance.bean.MaintenanceInfoDTO"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <title></title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
<link href="app/css/common1.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="app/css/jquery.fullPage.css">
<title>内网首页</title>
<script src="app/js/jquery.min.js"></script>
<script type="text/javascript" src="app/js/desktop.js"></script>
<script src="app/js/jquery-powerSwitch.js"></script>
<script type="text/javascript" src="app/js/koala.min.1.5.js"></script>
<script type="text/javascript" src="app/js/popup.js"></script>

<script type="text/javascript" src="app/research/js/encode.js"></script>

<!--[if lte IE 8]>   

<style>

.sb_count { width:400px;height: 30px;position: absolute; left: 0; top: 0; right: 0; bottom: 0;margin: auto;margin-top:30px; }


</style>

<![endif]-->

    <%
    CurrentSessionUser sessionUser=(CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String user=sessionUser.getSysUser().getName();
    String userBm= sessionUser.getDepartment().getId();
    Map<String,String> roles=sessionUser.getRoles();
   //JSONArray inspData =  TS_Util.getInspNew();
    String noticeData = ReportUtil.getNotice("通知公告");
    String news = ReportUtil.getNotice("内网新闻");
    String newsImg = ReportUtil. getNoticeImg("内网新闻");
    String inspCount = TS_Util.getInspDeviceCount();
    String inspCountN = TS_Util.getInspDeviceCountDay();
    String taskCount = TS_Util.getTaskCount();
    %>
    <script type="text/javascript">
    try{
    	parent.mPanelDispay({panel:'left',display:false,close:true});//隐藏父层菜单
    }catch (e){}
    document.onkeydown=function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];           
         if(e && e.keyCode==13){ // enter 键
        	 indexSerachClick();
        }
    };	

        $(function(){//jQuery页面载入事件
        	  //获取新闻公告数据
            var noticeData = <%=noticeData%>;

           if(noticeData!=null){
        	   
	            var noticeUl = $('#wordSlide');
	            var l = noticeData.length;
	         	if(l>4){
	         		l=4;
	         	}
	            for (var i = 0; i < l; i++) {
	            	if(i==0){
	            		//noticeUl.append($('<li id="wordList'+(i+1)+'" style="display: list-item;" data-rel="wordList'+(i+1)+'"><a href="javascript:openDialog(\''+noticeData[i].title+'\',\''+noticeData[i].url+'\')">'+noticeData[i].title+'</a></li>'));	
	            		noticeUl.append($('<li id="wordList'+(i+1)+'" style="display: list-item;" data-rel="wordList'+(i+1)+'"><a  target="_blank"  href="'+noticeData[i].url+'">'+noticeData[i].title+'</a><div class="gg_time">'+noticeData[i].date+'</div></li>'));	
	            	}else{
	            		 noticeUl.append($('<li id="wordList'+(i+1)+'" data-rel="wordList'+(i+1)+'"><a  target="_blank"  href="'+noticeData[i].url+'">'+noticeData[i].title+'</a><div class="gg_time">'+noticeData[i].date+'</div></li>'));
	            	}
	            	
				}
            
           }
            
         	 //获取新闻
         	 var news = <%=news%>;
         	 if(news!=null){
         		$('#news').html("");
	         	 var newContainer = $('#news');
	         	// var newest = 0;//最新新闻时间
	         	var l = news.length;
	         	/* if(l>10){
	         		l=10;
	         	} */
	             for (var i = 0; i < l; i++) {
	            	 /* if(i<4){
	            		var ss =  '<div class="fcon" style="display: none;">'+
							'<a target="_blank" href="'+news[i].url+'"><img src="'+news[i].image+'" style="opacity: 1; "></a>'+
							'<span class="shadow"><a target="_blank" href="'+news[i].url+'">'+news[i].title+'</a></span>'+
							''+
						'</div>';
						$("#D1pic1").append(ss)
	            	 } */
	            	// alert(news[i].title+"----"+news[i].date)
	            	 newContainer.append($('<li><div class="n_l_box_list_bt"><a  target="_blank"  href="'+news[i].url+'" >'+news[i].title+'</a></div><div class="n_l_box_time">'+news[i].date+'</div></li>'));
	 			 }
         	 }
         	 //最近检验数据（滚动）
         	<%-- var inspData = <%=inspData%>; 
         	if(inspData!=null){
         		var l = inspData.length;
	         	if(l>10){
	         		l=10;
	         	}
	         	for (var i = 0; i < l; i++) {
	         		var insp = inspData[i];
					var ss = '<tr style="">'+
					'<td align="center" width="30%">'+insp.report_com_name+'</td>'+
					'<td align="center" width="22%">'+insp.report_sn+'</td>'+
					'<td align="center" width="10%">'+insp.check_type+'</td>'+
					'<td align="center" width="14%">'+insp.advance_time.substring(0,10)+'</td>'+
					'<td align="center" width="10%">'+insp.flow_note_name+'</td>'+
					'</tr>';
					$("#inspData").append(ss)
				}
         	} --%>
         	//当年检验数量
         	var inspCounts = "<%=inspCount%>";
         	var inspCount = inspCounts.split(",")[0];
         	var years = inspCounts.split(",")[1];
         	$("#year").html(years);
         	var l = 7-inspCount.length;
         	$("#inspCount").html("");
         	$("#inspCount").append('<div class="text">台</div>')
         	for (var i = 0; i < l; i++) {
         		$("#inspCount").append('<li>0</li>')
			}
         	for (var i = 0; i < inspCount.length; i++) {
         		$("#inspCount").append('<li>'+inspCount.substring(i,i+1)+'</li>')
			}
         	
         	//今天检验数量
         	var inspCountsN = "<%=inspCountN%>";
         	var l = 4-inspCountsN.length;
         	$("#inspCountN").html("");
         	$("#inspCountN").append('<div class="text">台</div>')
         	for (var i = 0; i < l; i++) {
         		$("#inspCountN").append('<li>0</li>')
			}
         	for (var i = 0; i < inspCountsN.length; i++) {
         		$("#inspCountN").append('<li>'+inspCountsN.substring(i,i+1)+'</li>')
			}
         	
         	//今天任务数量
         	var taskCount = "<%=taskCount%>";
         	var l = 3-taskCount.length;
         	$("#taskCount").html("");
         	$("#taskCount").append('<div class="text">条</div>')
         	for (var i = 0; i < l; i++) {
         		$("#taskCount").append('<li>0</li>')
			}
         	for (var i = 0; i < taskCount.length; i++) {
         		$("#taskCount").append('<li>'+taskCount.substring(i,i+1)+'</li>')
			}
         	
         	
        	//7
            var myDate = new Date(); 
            var str = myDate.toLocaleDateString();  
            var Week = ['日','一','二','三','四','五','六'];  
            str += ' 星期' + Week[myDate.getDay()];
            $("#nowDay").html(str);
            $("#date1").html(str);
            setTime();
        });
		
        function indexSerachClick(){
     		if($("#search").val()=="输入职工姓名、维保单位"){
     			alert("请输入查询条件！")
     			return;
     		}
     		var name=$("#search").val();
     		 $("#yuns").html("");
     		$("#yuns").append('<div class="ser_tit">电子资料云</div>'+
     				'<div class="k-login-submit-msg" id="k-login-submit-msg"><img src="k/kui/images/indicator.gif"></img><div>正在搜索，请稍后……</div></div>');
     		/* $("#search").val("正在搜索，请稍后……");
     		$("#dosearch").css("background-color","gray");
     		$("#dosearch").attr("disabled","disabled"); */
     		/* top.$.dialog({
				width : $(top).width() * 0.8,
				height : $(top).height() * 0.8,
				lock : true,
				title : "搜索",
				data : {
					"window" : window
				},
				content : 'url:enterSearchAction/searchAll.do?name='+$("#search").val()
			}).max(); */
     		window.location.href= $("base").attr("href")+"enterSearchAction/searchAll.do?name="+EncodeUtf8(name);
     		
     	}
     	  
     	  
        
    	function openDialog(title,url){
			top.$.dialog({
				width : $(top).width() * 0.8,
				height : $(top).height() * 0.8,
				lock : true,
				title : title,
				data : {
					"window" : window
				},
				content : 'url:' + url
			}).max();
		}
    	
    	function openNews(name){
    		var name1 = encodeURI(name);
    		top.$.dialog({
    			width : 1024,
    			height :800,
    			lock : true,
    			title : name,
    			content : 'url:app/research/main_news_list.jsp?status=add&name='+name1,
    			data : {
    				"window" : window
    			}
    		});
    	}
    	
    </script>
  <style type="text/css">

.slide_bg2{ background:#fff url(app/images1/bg.png) no-repeat top center; background-size:cover; width: 100%; height: 100%; }
#imgLoading {background:url('k/kui/images/loading-s5.gif') no-repeat transparent center center;width:10%;height:10%;z-index:8;position:absolute;}
	#imgLoading .close {position:absolute;top:5px;right:5px;width:16px;height:16px;background:url("k/kui/skins/default/images/basic/close2.png") repeat-x top left;}
	/* #imgLoading .close a {display:block;width:16px;height:16px;} 
	#imgLoading .close:hover {background-position:0px -16px;}
	#imgLoading .close span {display:inline;}*/

</style> 
    </head>
<body style="overflow: auto;">

<div id="dowebok">
    <div class="section">
        <div class="slide">
		     <div>
<div class="s_n_bg"></div>
<div class="cont_news">
	<div class="ser_war">
		<div class="n_ser" id="yuns">
		
		<div class="ser_tit">电子资料云</div>
			<input id="search" name="" type="text" value="输入职工姓名、维保单位" style="color:#9C9A9C;" onfocus="if(this.value=='输入职工姓名、维保单位'){this.value='';this.style.color = '#000'};" onblur="if(this.value==''){this.value='输入职工姓名、维保单位';this.style.color='#9C9A9C'};" />
			<input name="" class="ser_bnt" type="button" value="搜索" id="dosearch" onclick="indexSerachClick()"/>
			
		</div>
		<div class="n_date" id="date1">2016年11月3日 星期四     多云间晴 12～21℃</div>
	
	
	</div>
		

   <div class="n_mid">
        
		<div class="n_m_left">
		
		
		
		<div class="gg_tz"> 
		
		    <div class="gg_tit">通知公告</div>
			<ul id="wordSlide" class="word_slide">
				<!-- <li id="wordList1" style="display: list-item;" data-rel="wordList1"><a href="#">· 关于征集2016全国特种设备安全与节能学术会议参会论文的通知 </a> 2016-08-09 16:30 </li>
				<li id="wordList2" data-rel="wordList2"><a href="#">· 全会纲领性文件如何出台</a> <a href="##">图解各届全会研究内容</a>2016-08-09 16:30</li>
				<li id="wordList3" data-rel="wordList3"><a href="#">· 国台办:“台独”图谋搞乱香港绝不可能得逞</a> 2016-08-09 16:30</li>
				<li id="wordList4" data-rel="wordList4"><a href="#">· 中央将遴选100名具冲击诺贝尔奖潜力人才</a> 2016-08-09 16:30</li>
				<li id="wordList5" data-rel="wordList5"><a href="#">· 国信办主任鲁炜：网聚正能量 共筑中国梦</a> 2016-08-09 16:30</li>
				<li id="wordList6" data-rel="wordList6"><a href="#">· 要求主流新闻网站要做到八个带头</a> 2016-08-09 16:30</li> -->
			</ul>
		</div>
		<div class="swf_new">
						
						<div class="fl">
						
							<div id="fsD1" class="focus">  
							<div id="D1pic1" class="fPic">  
								   <div class="fcon" style="display: none;">
									<a target="_blank" href="#"><img src="images/img01.jpg" style="opacity: 1; "></a>
									<span class="shadow"><a target="_blank" href="#"></a></span>
								</div>
								
								<div class="fcon" style="display: none;">
									<a target="_blank" href="#"><img src="images/img02.jpg" style="opacity: 1; "></a>
									<span class="shadow"><a target="_blank" href="#"></a></span>
								</div>
								
								<div class="fcon" style="display: none;">
									<a target="_blank" href="#"><img src="images/img03.jpg" style="opacity: 1; "></a>
									<span class="shadow"><a target="_blank" href="#"></a></span>
								</div>
								
								<div class="fcon" style="display: none;">
									 <a target="_blank" href="#"><img src="images/img04.jpg" style="opacity: 1; "></a>
									<span class="shadow"><a target="_blank" href="#"></a></span>
								</div>    
							</div>
							<div class="fbg">  
							<div class="D1fBt" id="D1fBt">  
								<a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>1</i></a>  
								<a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>2</i></a>  
								<a href="javascript:void(0)" hidefocus="true" target="_self" class="current"><i>3</i></a>  
								<a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>4</i></a>  
							</div>  
							</div>  
							<span class="prev"></span>   
							<span class="next"></span>    
						</div>
							
						<script type="text/javascript">
						 var news1 = <%=newsImg%>;
			         	 if(news1!=null){
			         		 var l = news1.length;
				         	if(l>4){
				         		l=4;
				         	}
			         		 if(l>0){
			         			$("#D1pic1").html("");
			         		 }
				             for (var i = 0; i < l; i++) {
				            	var ss =  '<div class="fcon" style="display: none;">'+
										'<a target="_blank" href="'+news1[i].url+'"><img src="'+news1[i].image+'" style="opacity: 1; "></a>'+
										'<span class="shadow"><a target="_blank" href="'+news1[i].url+'">'+news1[i].title+'</a></span>'+
										''+
									'</div>';
									$("#D1pic1").append(ss)
				            }
				             
			         	 }
							Qfast.add('widgets', { path: "app/js/terminator2.2.min.js", type: "js", requires: ['fx'] });  
							Qfast(false, 'widgets', function () {
								K.tabs({
									id: 'fsD1',   //焦点图包裹id  
									conId: "D1pic1",  //** 大图域包裹id  
									tabId:"D1fBt",  
									tabTn:"a",
									conCn: '.fcon', //** 大图域配置class       
									auto: 1,   //自动播放 1或0
									effect: 'fade',   //效果配置
									eType: 'click', //** 鼠标事件
									pageBt:true,//是否有按钮切换页码
									bns: ['.prev', '.next'],//** 前后按钮配置class                          
									interval: 3000  //** 停顿时间  
								}) 
							})  
						</script>
						
							<div style="width:520px;"><img src="app/images1/swf_bg.png" /></div>
						
						</div>
						
			
			
						
			
						
						
			
			<div class="list_1 fr">
        <div class="list_1_top">
          <div class="list_1_bt_tab">
            <ul>
              <li class="i-tab1-over">
                <div class="one">内网新闻</div>
              </li>

            </ul>
          </div>
          <div class="n_l_top_more">
            <a href="http://www.scsei.org.cn/innerindex.php?action=category&categoryid=8&page="  target="_blank"  title="点击查看更多新闻">更多</a>
          </div>
        </div>

        <div class="show" id="tabF0" style="display: block;">

          <div class="n_l_box_list" >

           <!--  <dl>
              <dt>
                <a href="mb_newshow.html" >关于征集2016全国特种设备安全与节能学术会议参会论文的通知</a>
              </dt>
              <dd>
                2016全国特种设备安全与节能学术会议暨科技成果展将于2016年11月22-25日期间在福建省厦门市举办,现向全院征集参会论文（具体范围和格...
              </dd>
            </dl> -->
            <ul id="news">
              <li>
                <div class="n_l_box_list_bt">
                  <a href="#" >没有新闻！</a>
                </div>
                <div class="n_l_box_time"></div>
              </li>

            </ul>
          </div>
        </div>

      </div>
		
		
		
		</div>
		
		
	  </div>	
		
	  <div class="n_m_right">
	     
		 	<div class="nav_list">
				<div class="n_l_t">常用目录</div>

				<ul class="mulu">
				<!-- http://www.scsei.org.cn/innerindex.php?action=entity&entityid=1784 -->
				<li><a target="_blank" href="http://www.scsei.org.cn/innerindex.php?action=category&categoryid=58&page="><img src="app/images1/icon01.png" />组织结构</a></li>
				<li><a target="_blank" href="http://y.scsei.org.cn/app/cloud_platform/down/down_file_list.jsp"><img src="app/images1/icon02.png" />下载中心</a></li>
				<li><a href="javascript:openNews('党建工作')"><img src="app/images1/icon03.png" />党建工作</a></li>
				<li><a href="javascript:openNews('廉政行风')"><img src="app/images1/icon04.png" />廉政行风</a></li>
				<li><a href="javascript:openNews('质量管理')"><img src="app/images1/icon05.png" />质量管理</a></li>
				
				<li><a href="javascript:openNews('设备管理')"><img src="app/images1/icon06.png" />设备管理</a></li>
				<!-- <li><a target="_blank" href="http://www.scsei.org.cn/innerindex.php?action=category&categoryid=55&page="><img src="app/images1/icon06.png" />设备管理</a></li> -->
				<li><a target="_blank" href="http://www.scsei.org.cn/innerindex.php?action=category&categoryid=53&page="><img src="app/images1/icon07.png" />规范标准</a></li>
				<li><a target="_blank" href="http://www.scsei.org.cn/innerindex.php?action=category&categoryid=22&page="><img src="app/images1/icon08.png" />检验(事故)案例</a></li>
				<li><a href="javascript:openNews('业务培训')"><img src="app/images1/icon09.png" />业务培训</a></li>
				<li><a href="javascript:openNews('科研工作')"><img src="app/images1/icon11.png" />科研工作</a></li>
				<li><a href="javascript:openNews('院大事记')"><img src="app/images1/icon12.png" />院大事记</a></li>
				<li><a href="javascript:openNews('文化建设')"><img src="app/images1/icon13.png" />文化建设</a></li>
				</ul>
			
			
			</div>
	  
	  
	  </div>
	  
	  
	  
	  
    
	</div> 
	 
	<div class="m_end">
		<a target="_blank" href="http://www.scsei.org.cn/innerindex.php?action=category&categoryid=19&page="><div class="yuan_btn kl_box4"><img src="app/images1/in_box_01.png" /><span style="color:#fff; line-height:78px;"> 院文件</span></div></a>
		<a href="javascript:openNews('交流园地')"><div class="msg_btn kl_box4">  <img src="app/images1/in_box_02.png" /> <span style="color:#fff; line-height:78px;"> 交流园地</span></div></a>
   		<a href="app/maintenance/maintenance_confirm_list.jsp"><div class="xttz_btn kl_box4"><img src="app/images1/in_box_03.png" /> <span style="color:#fff; line-height:78px;">平台运维日志</span></div></a>
   		<a target="_blank" href="http://www.scsei.org.cn/innerindex.php?action=topic&topicid=29"><div class="zlkh_btn kl_box4"> <img src="app/images1/in_box_04.png" /> <span style="color:#fff; line-height:78px;">质量考核通报</span></div></a>
        <a target="_blank" href="http://www.scsei.org.cn/innerindex.php?action=category&categoryid=70&page="><div class="txwj_btn kl_box4"> <img src="app/images1/in_box_06.png" /> <span style="color:#fff; line-height:78px;"> 体系文件</span></div></a>
		<a href="resourceSpace/goUserSpace.do?main=true"><div class="yun_btn kl_box4"> <img src="app/images1/in_box_05.png" /> <span style="color:#fff; line-height:78px;"> 电子资料云平台</span></div></a>
	</div> 
    
     
        

     

     
     </div>
      </div> 
  </div>
     

<div class="slide slide_bg2">
		
	<div>
	<div class="wrapper">
      <%if(!userBm.equals("100032") && !"100038".equals(userBm) && !"100039".equals(userBm)){ %>
     <div class="col1 clearfix">

        <div class="big rwtz">
          <a  href="app/office/ywhbsgz_fb_list2.jsp">任务台帐</a>
          <span class="numb"></span>
        </div>
         <div class="big ztrw">
          <a  href="app/office/office_rv_fb_list2.jsp">重大任务</a>
          <span class="numb"></span>
        </div>
        <div class="big dcl">
          <a  href="department/basic/getFlowInfo.do">待处理业务</a>
          <span class="numb"></span>
        </div>
        <div class="big pcl">
          <a href="inspection/zzjd/getFlowInfo.do">批量待处理业务</a>
          <span class="numb"></span>
        </div>  
     </div>
   
     <div class="col2 clearfix">

        <div class="small fybx">
          <a href="app/finance/fybxd_list.jsp?validPwd=1">费用报销申请</a>
          <span class="numb"></span>
        </div>     
        
        <div class="small clf">
          <a href="app/finance/clfbxd_list.jsp?validPwd=1">差旅费用申请</a>
          <span class="numb"></span>
        </div> 
        
        <div class="small hk">
          <a href="app/finance/finance_bills.jsp?validPwd=1">还款单填报</a>
          <span class="numb"></span>
        </div> 
        
        <div class="small px">
          <a href="app/finance/pxfbxd_list.jsp?validPwd=1">培训费用申请</a>
          <span class="numb"></span>
        </div>  
        
        <div class="small lkd">
          <a href="app/finance/recipients_bill.jsp?validPwd=1">领款单填报</a>
          <span class="numb"></span>
        </div>  
        
       <div class="small jk">
          <a href="app/finance/finance_borrow.jsp?validPwd=1">借款单填报</a>
          <span class="numb"></span>
        </div>     
        
        <div class="big ly"> 	

          <a href="app/finance/messageChecky_detail.jsp">个人工资查询</a>
          <p>休假申请</p>
          <span class="numb"></span>
        </div>

     </div>
       <%} %>
     <div class="col3 clearfix">
      <%if(!userBm.equals("100032") && !"100038".equals(userBm) && !"100039".equals(userBm)){ %>
        <div class="small sbxx">
          <a  href="app/device/device_index.jsp?type=1">设备信息</a>
          <p>设备信息</p>
        </div>     
      <%} %> 
       <%  if((roles.get("2c90758150a1f8ad0150a29f15530003")!=null||roles.get("EA94849D4083D0A8E040007F020052CE")!=null)||userBm.equals("100032")||userBm.equals("100038")||userBm.equals("100039")){%>
    	    <div class="small sbyj">
        <a  href="app/device/device_index.jsp?type=2">设备预警</a>
          <p>设备预警</p>
          </div>
      <%  }%>
       
        <div class="small bgcx">
         <a  href="app/query/report_query_list.jsp">报告查询</a>
        </div>
    
	</div>
	  <div class="sbtj_box">
				 
				 	<div class="">
					
							<div class="sbtj_num">
							
							<p><span id="year">2016</span>年已完成检验设备</p>
							<div class="sb_count"> 
								<ul class="num_list1" id="inspCount">
									<div class="text">台</div>
									<li>1</li>
									<li>8</li>
									<li>0</li>
									<li>2</li>
									<li>3</li>
									<li>5</li>
									<li>0</li>
								</ul>
							</div>
							
							
							</div>
							
							
							
							
							
						<div class="sbtj_num" style="left:33%;">
						
						<p>今天完成检验设备</p>
						<div class="sb_count"> 
							<ul class="num_list2" id="inspCountN">
								<div class="text">台</div>
								<li>4</li>
								<li>2</li>
								<li>3</li>
								<li>0</li>
							</ul>
						</div>
						
						
						</div>
						



						<div class="sbtj_num" style="left:66%;">
						
						<p>今天发布任务书</p>
						<div class="sb_count"> 
							<ul class="num_list3" id="taskCount">
								<div class="text">条</div>
								<li>3</li>
								<li>5</li>
								<li>0</li>
							</ul>
						</div>
						
						
						</div>
							
				</div>
						
					

						

					
					
				 	<div class="sb_bg_l"></div>
					
				 
				 </div>
				 
				</div>
		   
		   </div>
		
		</div>
    </div>
</div>

<script src="app/js/jquery-1.8.3.min.js"></script>
<script src="app/js/jquery.fullPage.js"></script>
<script>
$(function(){
    $('#dowebok').fullpage({
        sectionsColor : [''],
        loopBottom: true
    });

});
</script>
</body>
</html>

