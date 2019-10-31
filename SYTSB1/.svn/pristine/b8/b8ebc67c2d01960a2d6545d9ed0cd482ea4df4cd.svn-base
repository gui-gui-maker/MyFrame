<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title></title>

<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

<script type="text/javascript" src="k/kui/frame/core.js"></script>

<script type="text/javascript" src="k/kui/frame/main.js"></script>


<script src="desk/js/jquery.KinSlideshow-1.2.1.min.js" type="text/javascript"></script>
	
	

		
	<script type="text/javascript">
		
		
			

 
	
		$(function(){
			showPic();
			showAffiches();
			showInfo();
			
			
			
		})
		
	
		 
	

		

	
		function  showAffiches()
		{
			
			
			var result="";
			
			
			
			
			$.getJSON("affiche/getAffiches.do", function(resp){
				if(resp.success){
				
						
						result="<marquee direction='left' height='60'  scrollamount='3' scrolldelay='1'><b><font color='red' size='8'> "+resp.content+"</font></b></marquee>";
						
						alert(result);
						$('#show').html(result);
					
			  	}else{
			  		$.ligerDialog.error(resp.msg);
			  	}
			})
			
				
		
		
		}
		
		function  showInfo()
		{
			
			
			
			$.getJSON("department/basic/getShowInfo.do", function(resp){
				if(resp.success){
						
						
						var item;  
	                    $.each(resp.flowData,function(i,result){  
	                    	
	                        item = "<tr><td align='center' width='50%'>"+result[0]+"</td><td align='center' width='12%'>"+result[1]+"</td><td align='center' width='10%'>"+result[2]+"</td><td align='center' width='10%'>"+result[3]+"</td><td align='center' width='10%'>报告领取</tr>";  
	                       
	                        $('#talking').append(item);  
	                    }); 
						//alert(item);
					
						
						
					
			  	}else{
			  		$.ligerDialog.error(resp.msg);
			 	}
			})
			
				
		
		}
		function  showPic()
		{

			
			$.getJSON("picture/showPic.do", function(resp){
				if(resp.success){
				
						var item;  
	                    $.each(resp.flowData,function(i,result){  
	                    	
	                    	 
	                        item = "<a href='desk/over.jsp'  target='_blank' ><img src='"+kui["base"]+"fileupload/downloadByFilePath.do?path="+result[1]+"&fileName="+result[2]+"' alt='"+result[0]+"'  width='930' height='530' /></a>";  
	                       
	                       
	                        $('#KinSlideshow').append(item);  
	                    }); 
					
			  	}else{
			  		$.ligerDialog.error(resp.msg);
			 	}
				$("#KinSlideshow").KinSlideshow();
			})
			
			
		}
		function change(){

			window.location.reload(true);
		}
		
		
	</script>
	
	<style>






	
		.dst-allbg {background:#193F64;overflow: hidden;}
		.desktopwrap {padding:5px;}
		.otherinfor {overflow:auto; _zoom:1; *overflow-x:hidden;}
		.oa_wel_boxTop {overflow:hidden;  border:#60c2e9 solid 1px; margin:0 5px 10px 5px; _margin:0 5px 10px 5px; background:#ECECFB; min-height:60px!important; height:60px;}
		.oa_wel_box1 {overflow:hidden; border:#60c2e9 solid 1px; margin:0 5px 10px 5px; _margin:0 5px 10px 5px; background:#ECECFB; min-height:450px!important; height:450px;}
		.oa_wel_boxBom {overflow:hidden;  border:#60c2e9 solid 1px; margin:0 5px 10px 5px; _margin:0 5px 10px 5px; background:#ECECFB; min-height:530px!important; height:530px;}
	</style>

 	</head>
	<body class="dst-allbg"  scroll="no"  >
	<div class="desktopwrap"> 
		
		<!--待处理信息、预警信息-->
		<div class="otherinfor">
			<table border="0" cellspacing="0" cellpadding="0" width="100%">
					<tr>
							
							<td valign="top" width="50%" height="10%" colspan="2">
							<div class="oa_wel_boxTop" onclick="change();return false"   >
									
									
									
									<span id='show' ></span>
								
									
							</div>
							</td>
						</tr>
						
						<tr>
							
							<td valign="top" width="50%"  colspan="2">
								<div class="oa_wel_box1">
									
									<table id='report_head' style="font-size:25px; width:100%; height:30px; ">
										    <tr bgcolor="#eeeeee">
										    <td align=center width='50%'>单位名称</td> 
										    <td align=center width='12%'>报告书编号</td>
										    <td align=center width='10%'>检验性质</td>
										  
										    <td align=center width='10%'>检验日期</td>
										    <td align=center width='10%'>检验状态</td>
										    </tr>
										    </table>
	<!--<marquee direction="up" scrolldelay="50" scrollamount="1"     style="font-size:25px; width:100%; height:490px; ">
  

		<table style="font-size:22px; width:100%; height:20px;border-bottom: dotted 1PX #999" id='talking'>
		
		</table>
</marquee>
	-->
	
<DIV id="scrollobj" style="white-space:nowrap;overflow:hidden;height:100%"  >
<table style="font-size:22px; width:100%; height:20px;border-bottom: dotted 1PX #999" id='talking'>
</table>
</DIV>
<script language="javascript" type="text/javascript">
<!--
	function scroll(obj) {
		var tmp = (obj.scrollTop)++;
		//当滚动条到达右边顶端时
		if (obj.scrollTop==tmp) obj.innerHTML += obj.innerHTML;
		//当滚动条滚动了初始内容的宽度时滚动条回到最左端
		if (obj.scrollTop>=obj.firstChild.offsetWidth) obj.scrollTop=0;
	}
	var a =	setInterval("scroll(document.getElementById('scrollobj'))",20);
	
//-->
</script>



					</div>
							</td>
						</tr>
									
						<tr>
							
							<td valign="top" width="50%" height="35%">
								<div class="oa_wel_boxBom">






<!--<iframe src="http://s.wasu.cn/portal/player/20141230/WsPlayer.swf?vid=1938174&mode=4&live=2&ap=2" height="430" width="780" name="mainFrame" scrolling="no" frameborder="no" marginwidth="0" marginheight="0" >

<iframe src="http://pub.pptv.com/player/iframe/index.html#w=800&h=600&id=300177" height="430" width="780" name="mainFrame" scrolling="no" frameborder="no" marginwidth="0" marginheight="0" >-->




<!--http://live.imgo.tv/live/NetTvPlayerP2P_new.html?mt=%E6%B9%96%E5%8D%97%E5%A8%B1%E4%B9%90&p2p=2&p2purl=http://pczhibo.imgo.tv/111s41fm&h=undefined
<iframe src="http://www.ysjhz.com/tv/?pd=13" height="430" width="800" name="mainFrame" scrolling="no" frameborder="no" marginwidth="0" marginheight="0" >

<video src="http://192.168.3.4/123.mp4" controls="controls"></video>
<object width="780" height="430"  classid="clsid:02BF25D5-8C17-4B23-BC80-D3488ABDDC6B" codebase="http://www.apple.com/qtactivex/qtplugin.cab">
   <param name="src" value="http://192.168.3.4/123.mp4">
   <param name="autoplay" value="false">
   <param name="controller" value="true">
   <embed src="http://192.168.3.4/123.mp4" width="460" height="444" autostart="false" controller="true" hidden="false" "></embed>
</object>
 
<embed width="780" height="430" align="middle"   pluginspage="http://www.macromedia.com/go/getflashplayer" src="desk/flvplayer.swf" type="application/x-shockwave-flash" quality="high" flashvars="file=http://192.168.3.4/123.mp4&autostart=true&repeat=true&showdigits=false&backcolor=0x000000&ShowBar=false">
<video  width="950" height="530" controls="controls"  autoplay="autoplay">>
<source src="http://192.168.3.4/123.mp4" type="video/mp4">
</video>-->

<embed  src="http://192.168.3.4/123.mp4" width="950" height="530" loop="true" repeat="true" autostart="true" uimode="full"   controls="stopbutton" hidden=no   >

<!--<embed width="409" height="230" align="middle"   pluginspage="http://www.macromedia.com/go/getflashplayer" src="desk/flvplayer.swf" type="application/x-shockwave-flash" quality="high" flashvars="file=http://www.scsei.org.cn/video1/60.flv&autostart=true&repeat=true&showdigits=false&backcolor=0x000000&ShowBar=false">					
http://www.74177.com/tv/cctv13.html
</embed>
 
<OBJECT ID=video1 CLASSID="clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA" HEIGHT=288 WIDTH=352> 
<param name="_ExtentX" value="9313"> 
<param name="_ExtentY" value="7620"> 
<param name="AUTOSTART" value="0"> 
<param name="SHUFFLE" value="0"> 
<param name="PREFETCH" value="0"> 
<param name="NOLABELS" value="0"> 
<param name="SRC" value="http://192.168.3.4/123.mp4"> 
<param name="CONTROLS" value="ImageWindow"> 
<param name="CONSOLE" value="Clip1"> 
<param name="LOOP" value="0"> 
<param name="NUMLOOP" value="0"> 
<param name="CENTER" value="0"> 
<param name="MAINTAINASPECT" value="0"> 
<param name="BACKGROUNDCOLOR" value="#000000"><embed SRC type="audio/x-pn-realaudio-plugin" CONSOLE="Clip1" CONTROLS="ImageWindow" HEIGHT="288" WIDTH="352" AUTOSTART="false"> 


</OBJECT> -->



								</div>
								
							</td>
							<td valign="top" width="50%" height="30%">
								<div class="oa_wel_boxBom">
								
							
								 <div id="KinSlideshow" style="visibility:hidden;">
      
        
 		
       
        
							  </div>

							</div>
 
                   

 


							</td> 
						</tr>	
			</table>
		</div>
	</div>
	
</body>
</html>
