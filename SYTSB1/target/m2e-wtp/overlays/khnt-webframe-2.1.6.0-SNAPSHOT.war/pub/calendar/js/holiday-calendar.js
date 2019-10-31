/**
 * 移出一个节假日
 * @param event
 */
function holiday_remove(event){
	$.getJSON("pub/calendar/holiday/delete.do?id=" + event.id,function(resp){
		if(resp.success){
			$('#calendar').fullCalendar("removeEvents",event.id);
			if(event.legal=="1"){
				$("#" + event.id).removeClass("fc-event-exist").data("event",{
					id: "drop_event_id_" + $("#" + event.id).siblings().size(),
					title: event.title,
					stick: true,
					legal: "1",
					weekend: "0",
					dayOff: "1"
				}).off("click",shortcut_click_show_calendar);
			}else{
				$("#" + event.id).remove();
			}
		}else{
			alert("删除失败！");
		}
	});
}

/**
 * 编辑节假日信息
 * 
 */
function holiday_edit(event){
	if(!event.id) event.id = "";
	var isNew = event.id == "";
	top.winOpen({
		width: 400,
		height: 250,
		lock: true,
		data: {
			event: event,
			callback: function(newEvent){
				holiday_save_callback(newEvent,isNew?null:event)
			}
		},
		title: "设置节假日",
		content: "url:pub/calendar/holiday_detail.jsp?legal=" + event.legal + "&status=" + (isNew?"add":"modify") + "&id=" + event.id
	});
}

/**
 * 自动创建周末节假日数据
 */
function autoCreateWeekendHoliday(){
	var currentDate = $('#calendar').fullCalendar('getDate');
	$.getJSON("pub/calendar/holiday/create_weekend_holiday.do?year=" + currentDate.year(),function(response){
		if(response.success){
			$("#create-weekend-btn").hide();
			$.each(response.data,function(){
				this.end = moment(this.end).add(1,"d");
				if(this.weekend=='1')
					this.title = this.title.substring(5);
			});
			
			// 加入到缓存
			var theYearEvents = getPageYearEvents(currentDate.year());
			if(theYearEvents){
				theYearEvents = theYearEvents.concat(response.data);
			}else{
				theYearEvents = response.data;
			}
			setPageYearEvents(currentDate.year(),theYearEvents);
			loadCurrentCalendarEvents(theYearEvents);
		}else{
			alert("生成失败！<br/>" + (response.msg||""));
		}
	});
}

/**
 * 节假日编辑保存回调
 * @param event
 */
function holiday_save_callback(newEvent,preEvent){
	if(preEvent==null){
		newEvent.end = moment(newEvent.end).add(1,"d");
		$('#calendar').fullCalendar('addEventSource', [newEvent]);
		//需检查年份是否与当前日历所在年份相同
		if(newEvent.start.substring(0,4) != $('#calendar').fullCalendar('getDate').year())return;
		if(newEvent.legal=="1"){
			$("#legal-holiday-events .fc-event").each(function(){
				if($(this).text()==newEvent.title){
					$(this).addClass("fc-event-exist").attr("id",newEvent.id).click(newEvent,shortcut_click_show_calendar);
				}
			});
		}
		if(newEvent.legal=="0"){
			//自定义假日
			_addHolidayEventShortcut(newEvent,"#diy-holiday-events");
		}
	}
	else{
		preEvent.title = newEvent.title;
		preEvent.start = newEvent.start;
		preEvent.end = newEvent.end;
		preEvent.legal = newEvent.legal;
		preEvent.weekend = newEvent.weekend;
		preEvent.dayOff = newEvent.dayOff;
		preEvent.color = newEvent.color;
		$('#calendar').fullCalendar('updateEvent', preEvent);
	}
}

/* -------------------------------------- 日历事件监听处理 ------------------------------------------ */

/**
 * 鼠标移出事件页面元素
 * @param event
 * @param jsEvent
 * @param view
 */
function calendar_event_mouseover(event, jsEvent, view){
	$(".calendar-popup").hide();
	if(event.id.indexOf("drop_event_id_")==0) return;
	var cs_menu = $("#cl_event_detail");
	if(cs_menu.length==0) 
		cs_menu = $("<div id='cl_event_detail' class='calendar-popup calendar-event-detail'></div>").appendTo("body");
	else if(cs_menu.css("display")!="none")return;
	cs_menu.html("<p class='title'>" + event.title + "</p><p class='detail'>" + event.start.format("YYYY月M年D日") + (event.end.isValid()?" - " + 
				moment(event.end).subtract(1,"d").format("YYYY月M年D日"):"") + (event.legal=="1"?"，国家法定假日":"") + (event.dayOff=="1"?"，休息":"") + "。</p>" + 
				(event.remark?"<p class='remark'>"+event.remark+"</p>":""));
	cs_menu.css(countPopupPosition(jsEvent,cs_menu.width(),cs_menu.height(),15,15));
	cs_menu.show();
}

/**
 * 鼠标移入事件页面元素
 * 
 * @param event
 * @param jsEvent
 * @param view
 */
function calendar_event_mouseout(event, jsEvent, view){
	$("#cl_event_detail").hide();
}

/**
 * 日历选择事件处理。
 * 这里将会向用户弹出一个菜单选项，包含：添加法定节假日、添加自定义节假日。
 * 
 * @param startDate
 * @param endDate
 * @param jsEvent
 * @param view
 */
function calendar_select_menu(startDate, endDate, jsEvent, view){
	$(".calendar-popup").hide();
	var cs_menu = $("#cl_select_menu");
	if(cs_menu.length==0) 
		cs_menu = $("<ul id='cl_select_menu' class='calendar-popup calendar-select-menu'><li legal='1'>添加法定节假日</li><li legal='0'>添加自定义节假日</li></ul>").appendTo("body");
	cs_menu.css(countPopupPosition(jsEvent,cs_menu.width(),cs_menu.height(),5,5));
	cs_menu.find("li").off("click").on("click",function(){
		$("#cl_select_menu").hide();
		holiday_edit({
			start: startDate,
			end: endDate,
			legal: $(this).attr("legal")
		});
	});
	cs_menu.show();
}

/**
 * 日历选择取消，隐藏选择菜单
 * 
 * @param view
 * @param jsEvent
 */
function calendar_unselect(view, jsEvent){
	$(".calendar-popup").hide();
}

/**
 * 日历事件对象点击效果
 * 
 * @param event
 * @param jsEvent
 * @param view
 */
function calendar_event_click_menu(event, jsEvent, view){
	// 周末不能修改
	var cs_menu = $("#cl_event_menu");
	if(cs_menu.length==0) 
		cs_menu = $("<ul id='cl_event_menu' class='calendar-popup calendar-event-menu'><li type='edit'>修改</li><li type='del'>删除</li></ul>").appendTo("body");
	if(event.weekend=="1")
		cs_menu.children().first().hide();
	else
		cs_menu.children().first().show();
	
	cs_menu.css(countPopupPosition(jsEvent,cs_menu.width(),cs_menu.height(),5,5));
	cs_menu.find("li").off("click").on("click",function(){
		$("#cl_event_menu").hide();
		if($(this).attr("type")=="edit"){
			holiday_edit(event);
		}else{
			holiday_remove(event);
		}
	});
	cs_menu.show();
}

/**
 * 日历事件对象拖拽或者日期长度变化后处理
 * 
 * @param event
 * @param delta
 * @param revertFunc
 * @param jsEvent
 * @param ui
 * @param view
 */
function calendar_event_drop_or_resize(event, delta, revertFunc, jsEvent, ui, view){
	//日历事件变化，及时更新保存
	var holidayData = {
		id: event.id,
		name: event.title,
		legal: event.legal,
		weekend: event.weekend,
		dayOff: event.dayOff,
		remark: event.remark
	};
	saveHoliday(holidayData,event.start.format("YYYY-MM-DD"),event.end.format("YYYY-MM-DD"),function(response){
		if(response.success){
			// 更新快捷方式对象绑定的event数据
			$("#"+event.id).data("event",event);
		}else{
			// 操作失败，恢复原样
			revertFunc();
		}
	});
}

/* ---------------------------------------- 拖放事件处理 --------------------------------- */

/**
 * 初始化预设节假日日历信息
 */
function initDragLegalHolidayEvent(holidays, events){
	$.each(holidays,function(idx){
		var legalDay = this;
		$.each(events,function(){
			if(this.title == legalDay.text) legalDay.event = this;
		});
		var isLegal = this.event && this.event.legal=="1";
		var item = $("<div class='fc-event" + (isLegal?" fc-event-exist":"") + 
				"' id='drop_event_id_" + idx + "' title='"+(isLegal?"已安排的节假日":"可安排的节假日") + 
				"'>" + this.text + "</div>").appendTo("#legal-holiday-events");
		if(this.event){
			item.data('event',this.event);
			
		}else{
			item.data('event',{
				id: "drop_event_id_" + idx,
				title: $.trim(legalDay.text),
				stick: true,
				legal: "1",
				weekend: "0",
				dayOff: "1"
			});
		}
	});
	
	return;
	/* initialize the external events
	-----------------------------------------------------------------*/
	$('#legal-holiday-events .fc-event').each(function() {
		// store data so the calendar knows to render an event upon drop
		if($(this).hasClass("fc-event-exist"))return;
		// make the event draggable using jQuery UI
		$(this).data("event").color = "red";
		$(this).draggable({
			zIndex: 999,
			revert: true,      // will cause the event to go back to its
			revertDuration: 0  //  original position after the drag
		});
	});
}


/**
 * 从左边法定节假日清单将未安排过的节假日拖入日历区以添加一个节假日。
 * 
 * @param date
 * @param jsEvent
 * @param ui
 * @param resourceId
 */
function calendar_on_drop_event(date, jsEvent, ui, resourceId){
	var event = $(this).data("event");
	event.start = date;
	event.end = moment(date).add(1,"d");
	event.isNew = true;
	holiday_edit(event);
	$(this).addClass("fc-event-adding")
}

/**
 * 日历时间渲染
 * 
 * @param event
 * @param element
 * @param view
 */
function calendar_event_render(event, element, view){
	
}

/**
 * 日历渲染
 * 
 * @param view
 * @param element
 */
function calendar_render(view, element){
	var currentDate = $('#calendar').fullCalendar('getDate');
	var year = $('#calendar').data("current_year");
	$('#calendar').data("current_year",currentDate.year());
	
	//年份没有变化，这一年的数据已经加载进来，无需多余处理
	if(year && year == currentDate.year()){
		return;
	}
	else{
		// 年份有变化，需要加载该年度事件
		getYearCalendarEvents(currentDate.year());
	}
}

/* ---------------------------------------- 应用初始化 --------------------------------- */

$(function(){
	$(window).data("calendar_events",{});
});

function setPageYearEvents(year,yevents){
	var allData = $(window).data("calendar_events");
	allData["y"+year] = yevents;
}

function getPageYearEvents(year){
	var allData = $(window).data("calendar_events");
	if(year){
		if(allData["y"+year]) 
			return allData["y"+year];
		else return null;
	}
	else{
		var allEvents = [];
		$.each(allData,function(k,v){
			allEvents = allEvents.concat(v);
		});
		return allEvents;
	}
}

/**
 * 获取一年的节日日历信息
 * 
 * @paran year
 */
function getYearCalendarEvents(year){
	var yearEvents = getPageYearEvents(year);// 从页面缓存获取这一年的数据
	if(yearEvents){// 该年数据已缓存
		loadCurrentCalendarEvents(yearEvents);
	}else{
		// 未缓存，则需从服务端获取
		getServerYearCalendarEvents("pub/calendar/holiday/holiday_as_events.do",year,function(events){
			$.each(events,function(){
				this.end = moment(this.end).add(1,"days");
				if(this.weekend=='1')
					this.title = this.title.substring(5);
			});
			// 1、缓存本年数据
			setPageYearEvents(year, events);
			loadCurrentCalendarEvents(events);
		});
	}
}

/**
 * 加载一年的节日日历信息
 * 
 * @param yearEvents
 */
function loadCurrentCalendarEvents(yearEvents){
	// 1、加载全年假日事件快捷方式
	var beShowEvents = createHolidayEventShortcuts(yearEvents);
	
	// 2、从日历中删除所有数据
	$('#calendar').fullCalendar('removeEvents');
	
	// 3、加载该年所有需要显示的事件
	$('#calendar').fullCalendar('addEventSource', beShowEvents);
}

/**
 * 获取全年节假日日历事件信息
 */
function getServerYearCalendarEvents(url,year,callback){
	$.getJSON(url,{year:year},function(response){
		if(response.success){
			callback(response.data);
		}else{
			alert("获取日历事件失败，请稍后重试！");
		}
	});	
}

/**
 * 初始化法定假日快捷方式
 */
function initLegalHolidayShortcuts(){
	$.each(_pre_cfg_holiday,function(idx){
		$("<div class='fc-event' id='drop_event_id_" + idx + "'>" + this.text + "</div>").appendTo("#legal-holiday-events");
	});
}

/*为快捷方式对象绑定节日日历事件模型对象,并绑定js单击事件，以定位日历位置*/
function _addLegalHolidayShortcutEvent(event){
	$("#legal-holiday-events .fc-event").addClass("legal").each(function(){
		if($(this).text()==event.title){
			$(this).addClass("fc-event-exist")
				   .attr("id",event.id)
				   .data("event",event)
				   .click(event,shortcut_click_show_calendar);
			return false;
		}
	});
}

/**
 * 快捷方式点击后，日历定位到该节假日的起始日期。
 * 
 * @param event
 */
function shortcut_click_show_calendar(jEvent){
	$('#calendar').fullCalendar("gotoDate",jEvent.data.start);
}

/**
 * 添加一个快捷方式
 * 
 * @param event
 * @param container
 */
function _addHolidayEventShortcut(event,container,className){
	$("<div class='fc-event " + className + "' id='" + event.id + "'>" + event.title+ "</div>")
		.appendTo(container).data("event",event).click(event,shortcut_click_show_calendar);
}

/**
 * 初始化一年的节假日快捷方式，放置到左边栏
 */
function createHolidayEventShortcuts(yearHolidayEvents){
	//删除先前初始化的其他年份数据
	$("#weekend-holiday-events .fc-event").remove();
	$("#diy-holiday-events .fc-event").remove();
	$("#legal-holiday-events .fc-event-exist").removeClass("fc-event-exist");
	
	var weekendNotView = $("#weekend-view-swicth").hasClass("not-view");
	var legalNotView = $("#legal-view-swicth").hasClass("not-view");
	var diyNotView = $("#diy-view-swicth").hasClass("not-view");
	var beShowEvents = [];
	$.each(yearHolidayEvents,function(){
		if(this.legal=="1"){
			if(!legalNotView){
				beShowEvents.push(this);
				_addLegalHolidayShortcutEvent(this);
			}
		}else if(this.weekend=="1"){
			if(!weekendNotView){
				console.log(this);
				beShowEvents.push(this);
				_addHolidayEventShortcut(this,"#weekend-holiday-events","weekend");
			}
		}else{
			if(!diyNotView){
				beShowEvents.push(this);
				_addHolidayEventShortcut(this,"#diy-holiday-events","diy");
			}
		}
	});
	
	if(!weekendNotView && $("#weekend-holiday-events .fc-event").size()==0){
		$("#create-weekend-btn").show();
	}else{
		$("#create-weekend-btn").hide();
	}
	return beShowEvents;
}

/**
 * 显示、隐藏某一类节假日事件
 * 
 * 隐藏时，需要将所有年份的数据从日历中删除
 * 显示时，日历中显示所有年份的该类别数据
 * 
 */
function holidayViewSwitch(){
	var $this = $(this);
	var target =  $this.attr("target");
	var isNotView = $this.hasClass("not-view");
	if(isNotView){
		$this.removeClass("not-view").attr("title","隐藏");
		var thisEvents = [];
		var moment = $('#calendar').fullCalendar('getDate');//当然日历日期,获得年份
		var yearEvents = getPageYearEvents(moment.year());
		if(target=="legal"){
			// 法定假日，有特别处理，需要将所有法定假日列出，再为已安排的法定节假日着色
			initLegalHolidayShortcuts();
		}
		
		$.each(yearEvents,function(){
			if((target=="weekend" && this.weekend=="1")||(target=="legal" && this.legal=="1")||(target=="diy" && this.legal!="1" && this.weekend!="1")){
				thisEvents.push(this);
				if(target=="legal")
					//法定节假日，只着色，无需再创建对象
					_addLegalHolidayShortcutEvent(this);
				else
					_addHolidayEventShortcut(this,"#"+ target + "-holiday-events", target);
			}
		});
		$('#calendar').fullCalendar("addEventSource",thisEvents);
	}
	else{
		$(this).addClass("not-view").attr("title","显示");
		
		$("#" + target + "-holiday-events .fc-event").remove();
		$('#calendar').fullCalendar("removeEvents",function(event){
			if((target=="weekend" && event.weekend=="1")||(target=="legal" && event.legal=="1")||(target=="diy" && event.legal!="1" && event.weekend!="1")) return true;
		});
	}
}

function weekendViewSwitchClick(){
	var weekendNotView = $("#weekend-view-swicth").hasClass("not-view");
	if(weekendNotView){
		$(this).removeClass("not-view").attr("title","隐藏周末");
		var weekendEvents = [];
		var moment = $('#calendar').fullCalendar('getDate');//当然日历日期,获得年份
		var yearEvents = getPageYearEvents(moment.year());
		$.each(yearEvents,function(){
			if(this.weekend=="1"){
				weekendEvents.push(this);
				_addHolidayEventShortcut(this,"#weekend-holiday-events","weekend");
			}
		});
		if(weekendEvents.length==0)
			$("#create-weekend-btn").show();
		$('#calendar').fullCalendar("addEventSource",weekendEvents);
	}
	else{
		$(this).addClass("not-view").attr("title","显示周末");;
		$("#create-weekend-btn").hide();
		$("#weekend-holiday-events .fc-event").remove();
		$('#calendar').fullCalendar("removeEvents",function(event){
			if(event.weekend=="1")return true;
		});
	}
}

/* initialize the calendar
 * 
 * 设计中，日历事件动态从页面本年度缓存中获取，如果非本年度日期，再动态从后台获取（异步添加到日历表）。
 * 
-----------------------------------------------------------------*/
function initCalendar(cdimension){
	$('#calendar').fullCalendar({
		theme: true,
		header: {
			left: 'title',
			center: '',
			right: 'today prevYear,prev,next,nextYear'
		},
		dayNames:['SunDay', 'MonDay', 'TueDay', 'WedDay', 'ThuDay', 'FriDay', 'SatDay'],
		dayNamesShort:['周日（Sun）', '周一（Mon）', '周二（Tue）', '周三（Wed）', '周四（Thu）', '周五（Fri）', '周六（Sat）'],
		fixedWeekCount: false,
		height: cdimension.height,
		width: cdimension.width,
		firstDay: "1",
		businessHours: true,
		lang: 'zh-cn',
		selectable: true,
		editable: true,
		select: calendar_select_menu,
		eventClick: calendar_event_click_menu,
		eventMouseout: calendar_event_mouseout,
		eventMouseover: calendar_event_mouseover,
		eventResize: calendar_event_drop_or_resize,
		eventDrop: calendar_event_drop_or_resize,
		eventRender: calendar_event_render,
		unselectCancel: "#cl_select_menu",
		unselect: calendar_unselect,
		eventDataTransform: calendarEventTransform,
		viewRender: calendar_render,
		windowResize: function(){
			var dimension = calculateCalendarSize();
			$('#calendar').fullCalendar('option', 'height', dimension.height);
			$('#calendar').fullCalendar('option', 'width', dimension.width);
		}
		//, 从左侧拖放
		//droppable: true,
		//drop: calendar_on_drop_event
	});
}

/* ------------------------------ 工具方法 -----------------------------------*/

/**
 * 计算点击弹出提示框显示位置
 * 
 * @param jsEvent js事件模型
 * @param width 弹框宽度
 * @param height 弹框高度
 * @param wc 宽度补差
 * @param hc 高度补差
 */
function countPopupPosition(jsEvent,width,height,wc,hc){
	return {
		"left" : jsEvent.clientX + width + wc > $("body").width() ? (jsEvent.clientX - width - wc) : (jsEvent.clientX + "px"),
		"top" : jsEvent.clientY + height + hc > $("body").height() ? (jsEvent.clientY - height - hc) : (jsEvent.clientY + "px")
	}
}

/**
 * 计算页面尺寸，在页面加载和窗口大小改变时需要重新计算并设置日历尺寸
 */
function calculateCalendarSize(){
	var cdimension = {
		width: $(window).width()-398,
		height: $(window).height()-10
	}
	$("#wrap").width($(window).width()-12);
	$("#calendar").width(cdimension.width);
	$("#external-events").height(cdimension.height-2);
	return cdimension;
}

/**
 * 日历事件数据转换器。
 * 
 * 该转换器的目的是根据事件类型决定在日历中的显示颜色。
 * 
 * @param eventData
 */
function calendarEventTransform(eventData){
	eventData.allDay = true;
	if(eventData.legal=="1")
		eventData.backgroundColor = "#FF9966";
	else if(eventData.weekend=="1"){
		eventData.backgroundColor = "#999999";
	}
	else
		eventData.backgroundColor = "#990033";
	return eventData;
}