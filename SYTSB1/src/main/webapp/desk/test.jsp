
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<title>kxbdMarquee - 模拟Marquee无缝滚动</title>
	<meta name="generator" content="Aptana" />
	<meta name="author" content="Aken Li@www.kxbd.com" />
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<style type="text/css">
		* {margin:0;padding:0;}
		body { font-size:12px;}
		a {color:#333;}
		ul {list-style:none;}
		#marquee1 {position:absolute;top:80px;left:50px;width:310px;height:45px;overflow:hidden;background:#333;border:2px solid #333;}
		#marquee1 ul li {float:left; padding:0 1px;}
		#marquee1 ul li img {display:block;}
		
		#marquee2 {position:absolute;top:80px;left:400px;width:300px;height:25px;overflow:hidden; background:#EFEFEF;}
		#marquee2 ul li {float:left; padding:0 10px; line-height:25px;}
		
		#marquee3 {position:absolute;top:150px;left:50px;width:60px;height:235px;overflow:hidden;background:#333;border:2px solid #333;}
		#marquee3 ul li {float:left; padding:1px 0;}
		#marquee3 ul li img {display:block;}
		
		#marquee4 {position:absolute;top:150px;left:400px;width:200px;height:200px; overflow:hidden;background:#EFEFEF;}
		#marquee4 ul li {float:left; width:180px; padding:10px; line-height:20px;}
		
	</style>
	<script type="text/javascript" src="../scripts/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="kxbdMarquee.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#marquee1').kxbdMarquee({direction:'right'});
			$('#marquee2').kxbdMarquee({isEqual:false});
			$('#marquee3').kxbdMarquee({direction:'down'});
			$('#marquee4').kxbdMarquee({direction:'up',isEqual:false});
		});
	</script>
</head>

<body>
	
	
	
	
	<div id="marquee4">
		<ul>
			<li><a href="#">新闻公告一新闻公告一</a></li>
			<li><a href="#">新闻公告二新闻公告二新闻公告二新闻公告二新闻公告二新闻公告二新闻公告二新闻公告二新闻公告二新闻公告二新闻公告二新闻公告二新闻公告二新闻公告二新闻公告二新闻公告二</a></li>
			<li><a href="#">新闻公告三新闻公告三新闻公告三新闻公告三</a></li>
			<li><a href="#">新闻公告四新闻公告四新闻公告四新闻公告四新闻公告四新闻公告四</a></li>
			<li><a href="#">新闻公告五新闻公告五新闻公告五新闻公告五新闻公告五</a></li>
			<li><a href="#">新闻公告六新闻公告六新闻公告六新闻公告六新闻公告六新闻公告六</a></li>
		</ul>
	</div>
</body>
</html>

<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-2020457-1");
pageTracker._trackPageview();
} catch(err) {}</script>