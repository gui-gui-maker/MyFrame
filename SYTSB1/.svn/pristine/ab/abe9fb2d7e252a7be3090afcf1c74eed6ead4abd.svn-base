<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<link rel='stylesheet' href='pub/calendar/lib/cupertino/jquery-ui.min.css' />
<link href='pub/calendar/css/fullcalendar.css' rel='stylesheet' />
<link href='pub/calendar/css/holiday-calendar.css' rel='stylesheet' />
<script src='pub/calendar/lib/moment.min.js'></script>
<script src='pub/calendar/lib/jquery.min.js'></script>
<script src='pub/calendar/js/fullcalendar.js'></script>
<script src='pub/calendar/lib/jquery-ui.custom.min.js'></script>
<script src='pub/calendar/js/lang-all.js'></script>
<script src='pub/calendar/js/holiday-edit.js'></script>
<script src='pub/calendar/js/holiday-calendar.js'></script>
<script>
	var _pre_cfg_holiday = <u:dict code="pub_holiday_legal" />;
	$(document).ready(function() {
		window.setTimeout(page_load_ready,100);
	});
	
	function page_load_ready(){
		// 节假日快捷栏折叠展开
		$("#external-events .accordion-btn").click(function(){
			if($(this).text()=="5"){
				$(this).text("6").attr("title","展开").parent().next().hide(200);
			}
			else{
				$(this).text("5").attr("title","折叠").parent().next().show(200);
			}
		});
		// 1、加载法定假日预设快捷方式
		initLegalHolidayShortcuts();
		
		// 3、计算页面尺寸
		var cdimension = calculateCalendarSize();
		
		// 4、加载日历
		initCalendar(cdimension);
		
		// 5、绑定各类假日日历显示隐藏开关事件
		$("#create-weekend-btn").click(autoCreateWeekendHoliday);
		$("#external-events .view-swicth").click(holidayViewSwitch);
		$("#weekend-view-swicth").click(function(){
			if($(this).hasClass("not-view")){
				$("#create-weekend-btn").hide();
			}else{
				if($("#weekend-holiday-events .fc-event").size()==0){
					$("#create-weekend-btn").show();
				}
			}
		});
	}
</script>
</head>
<body>
	<div id='wrap'>
		<div id='external-events'>
			<div class="external-events-title">法定假日
				<a class="accordion-btn" title="折叠">5</a>
				<a id="legal-view-swicth" class="view-swicth" target="legal" title="显示">N</a>
			</div>
			<div id="legal-holiday-events"></div>
			
			<div class="external-events-title">自定义
				<a class="accordion-btn" title="折叠">5</a>
				<a id="diy-view-swicth" class="view-swicth" target="diy" title="显示">N</a>
			</div>
			<div id="diy-holiday-events"></div>
			
			<div class="external-events-title">周末
				<a class="accordion-btn" title="折叠">5</a>
				<a id="weekend-view-swicth" class="view-swicth not-view" target="weekend" title="显示">N</a>
				<a id="create-weekend-btn" title="自动生成本年度的周末节假日">生成</a>
			</div>
			<div id="weekend-holiday-events"></div>
		</div>
		<div id='calendar'></div>
		<div style='clear:both'></div>
	</div>
</body>
</html>
