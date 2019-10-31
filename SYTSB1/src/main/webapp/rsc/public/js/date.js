/*
产生时间选择通用函数
Create:2006年02月17日 14:14:03
LastEdit:2009年01月06日 星期二 13:52:04
Dev:Liu YueBo

调用说明：


应用例子：
<input type="text" name="textfield" onfocus="SelectDate.show(this,true,this.value);"> 
第一个参数表示Input 的对象
第二个参数表示： true 表示时间与时间   false  表示只有日期
第三个参数表示要检查的值

*/

//alert("装载了选择时间的js文件。")

function atCalendarControl(){
	var calendar=this;
	this.calendarPad=null;
	this.prevMonth=null;
	this.nextMonth=null;
	this.prevYear=null;
	this.nextYear=null;
	this.goToday=null;
	this.calendarClose=null;
	this.calendarAbout=null;
	this.head=null;
	this.body=null;
	this.today=[];
	this.currentDate=[];
	this.sltDate;
	this.target;
	this.source;
	this.count=0;
	this.setDateOther=null;

	/************** 加入日历底板及阴影 *********************/
	this.addCalendarPad=function(){
		document.write("<div id='divCalendarpad' style='position:absolute;top:0px;left:0px;width:0px;height:0px;display:none;z-index:8848;border:0px solid #99CC00;/*filter:progid:DXImageTransform.Microsoft.shadow(direction=135,color=#8E8E8E,strength=3);*/'>");
		document.write("<iframe frameborder='0' height='184' width='252'></iframe>");
		document.write("<div style='position:absolute;top:1px;left:1px;width:252px;height:184px;background-color:#0D5BB2;border:0px outset #FFFF00;'></div>");
		document.write("</div>");
		calendar.calendarPad=document.all.divCalendarpad;
	}
  /************** 加入日历面板 *********************/
	this.addCalendarBoard=function(){
		var BOARD=this;
		var divBoard=document.createElement("div");
		calendar.calendarPad.insertAdjacentElement("beforeEnd",divBoard);
		divBoard.style.cssText="position:absolute;top:2px;left:2px;width:250px;height:182px;border:0px outset #CC33FF;background-color:#99CCFF;";

		var tbBoard=document.createElement("table");
		divBoard.insertAdjacentElement("beforeEnd",tbBoard);
		tbBoard.style.cssText="position:absolute;top:1px;left:1px;width:248px;height:179px;font-size:12px;border:0px solid #FF0000;";
		tbBoard.cellPadding=0;
		tbBoard.cellSpacing=0;

		/************** 设置各功能按钮的功能 *********************/
		/*********** Calendar About Button ***************/
		trRow = tbBoard.insertRow(0);
		calendar.calendarAbout=calendar.insertTbCell(trRow,0,"","center");
		calendar.calendarAbout.title="帮助 快捷键:H";
		calendar.calendarAbout.onclick=function(){calendar.about();}
		/*********** Calendar Head 日期 2009年01月06日 星期二 10:28:02 lybykw ***************/
		tbCell=trRow.insertCell(1);
		tbCell.colSpan=4;
		tbCell.bgColor="";
		tbCell.align="center";
		tbCell.style.cssText = "cursor:default;text-align:center;";
		calendar.head=tbCell;
		/*********** Calendar Close Button ***************/
		tbCell=trRow.insertCell(2);
		calendar.calendarClose = calendar.insertTbCell(trRow,2,"关闭","center");
		calendar.calendarClose.title="关闭 快捷键:ESC或X";
		calendar.calendarClose.onclick=function(){calendar.hide();}

		//tbCell=trRow.insertCell(0);
		calendar.calendartts = calendar.insertTbCell(trRow,2,"清空","center");
		calendar.calendartts.title="清空";
		calendar.calendartts.onclick=function(){calendar.target.value="";calendar.hide();}

		/*********** Calendar PrevYear Button ***************/
		trRow = tbBoard.insertRow(1);
		calendar.prevYear = calendar.insertTbCell(trRow,0,"&lt;&lt;","center");
		calendar.prevYear.title="上一年 快捷键:↑";
		calendar.prevYear.onmousedown=function(){
			calendar.currentDate[0]--;
			calendar.shows(calendar.target,calendar.returnTime,calendar.SendDefaultDate(),calendar.source,calendar.setDateOther);
		}
		/*********** Calendar PrevMonth Button ***************/
		calendar.prevMonth = calendar.insertTbCell(trRow,1,"&lt;","center");
		calendar.prevMonth.title="上一月 快捷键:←";
		calendar.prevMonth.onmousedown=function(){
			calendar.currentDate[1]--;
			if(calendar.currentDate[1]==0){
				calendar.currentDate[1]=12;
				calendar.currentDate[0]--;
			}
			calendar.shows(calendar.target,calendar.returnTime,calendar.SendDefaultDate(),calendar.source,calendar.setDateOther);
		}
		/*********** Calendar Today Button ***************/
		calendar.goToday = calendar.insertTbCell(trRow,2,"今天","center",3);
		calendar.goToday.title="选择今天 快捷键:T";
		calendar.goToday.onclick=function(){
			if(calendar.returnTime) {
				calendar.sltDate=calendar.today[0]+"-"+
				calendar.formatTime(calendar.today[1])+"-"+
				calendar.formatTime(calendar.today[2])+" "+
				calendar.formatTime(calendar.today[3])+":"+
				calendar.formatTime(calendar.today[4])+":"+
				calendar.formatTime(calendar.today[5])
			}
			else {
				calendar.sltDate=calendar.today[0]+"-"+
				calendar.formatTime(calendar.today[1])+"-"+
				calendar.formatTime(calendar.today[2]);
			}
			//开始附值=============================
			//2006年07月14日 星期五 10:40:17 lybykw
			var k=calendar.Exp(0,calendar.today,calendar.target.ext_maxLength);
			if(k[0])	calendar.sltDate=k[1];
			calendar.target.value=calendar.sltDate;
			//激发别的函数
			eval(calendar.setDateOther);
			calendar.hide();
			//calendar.shows(calendar.target,calendar.today[0]+"-"+calendar.today[1]+"-"+calendar.today[2],calendar.source);
		}
		/*********** Calendar NextMonth Button ***************/
		calendar.nextMonth = calendar.insertTbCell(trRow,3,"&gt;","center");
		calendar.nextMonth.title="下一月 快捷键:→";
		calendar.nextMonth.onmousedown=function(){
			calendar.currentDate[1]++;
			if(calendar.currentDate[1]==13){
				calendar.currentDate[1]=1;
				calendar.currentDate[0]++;
			}
			calendar.shows(calendar.target,calendar.returnTime,calendar.SendDefaultDate(),calendar.source,calendar.setDateOther);
		}
		/*********** Calendar NextYear Button ***************/
		calendar.nextYear = calendar.insertTbCell(trRow,4,"&gt;&gt;","center");
		calendar.nextYear.title="下一年 快捷键:↓";
		calendar.nextYear.onmousedown=function(){
			calendar.currentDate[0]++;
			calendar.shows(calendar.target,calendar.returnTime,calendar.SendDefaultDate(),calendar.source,calendar.setDateOther);

		}

		trRow = tbBoard.insertRow(2);
		var cnDateName = new Array("日","一","二","三","四","五","六");
		for (var i = 0; i < 7; i++) {
			tbCell=trRow.insertCell(i)
			tbCell.innerText=cnDateName[i];
			tbCell.align="center";
			tbCell.width=35;tbCell.height=18;
			tbCell.style.cssText="cursor:default;border:1px solid #4A9AF2;background-color:#BEDAFA;text-align:center;";
		}

		/*********** Calendar Body ***************/
		trRow = tbBoard.insertRow(3);
		tbCell=trRow.insertCell(0);
		tbCell.colSpan=7;
		tbCell.height=97;
		tbCell.vAlign="top";
		tbCell.bgColor="#F0F0F0";

		var tbBody=document.createElement("table");
		tbCell.insertAdjacentElement("beforeEnd",tbBody);
		tbBody.style.cssText="position:relative;top:0px;left:0px;width:245px;height:103px;"
		tbBody.cellPadding=0;
		tbBody.cellSpacing=1;
		calendar.body=tbBody;
	
		/*********** Time Body ***************/
		trRow = tbBoard.insertRow(4);
		tbCell=trRow.insertCell(0);
		calendar.prevHours = calendar.insertTbCell(trRow,0,"-","center");
		calendar.prevHours.title="小时调整 快捷键:Home";
		calendar.prevHours.onmousedown=function(){
			calendar.currentDate[3]--;
			if(calendar.currentDate[3]==-1) calendar.currentDate[3]=23;
			calendar.bottom.innerText=calendar.formatTime(calendar.currentDate[3])+":"+calendar.formatTime(calendar.currentDate[4])+":"+calendar.formatTime(calendar.currentDate[5]);
		}
		tbCell=trRow.insertCell(1);
		calendar.nextHours = calendar.insertTbCell(trRow,1,"+","center");
		calendar.nextHours.title="小时调整 快捷键:End";
		calendar.nextHours.onmousedown=function(){
			calendar.currentDate[3]++;
			if(calendar.currentDate[3]==24) calendar.currentDate[3]=0;
			calendar.bottom.innerText=calendar.formatTime(calendar.currentDate[3])+":"+calendar.formatTime(calendar.currentDate[4])+":"+calendar.formatTime(calendar.currentDate[5]);
		}
		tbCell=trRow.insertCell(2);
		tbCell.colSpan=3;
		tbCell.bgColor="";
		tbCell.align="center";
		tbCell.style.cssText = "cursor:default;text-align:center;";
		calendar.bottom=tbCell;
		tbCell=trRow.insertCell(3);
		calendar.prevMinutes = calendar.insertTbCell(trRow,3,"-","center");
		calendar.prevMinutes.title="分钟调整 快捷键:PageUp";
		calendar.prevMinutes.onmousedown=function(){
			calendar.currentDate[4]--;
			if(calendar.currentDate[4]==-1) calendar.currentDate[4]=59;
			calendar.bottom.innerText=calendar.formatTime(calendar.currentDate[3])+":"+calendar.formatTime(calendar.currentDate[4])+":"+calendar.formatTime(calendar.currentDate[5]);
		}
		tbCell=trRow.insertCell(4);
		calendar.nextMinutes = calendar.insertTbCell(trRow,4,"+","center");
		calendar.nextMinutes.title="分钟调整 快捷键:PageDown";
		calendar.nextMinutes.onmousedown=function(){
			calendar.currentDate[4]++;
			if(calendar.currentDate[4]==60) calendar.currentDate[4]=0;
			calendar.bottom.innerText=calendar.formatTime(calendar.currentDate[3])+":"+calendar.formatTime(calendar.currentDate[4])+":"+calendar.formatTime(calendar.currentDate[5]);
		}
	}

	//上一年、上一月、下一月、下一年，把时间值传到shows函数。
	//2007年09月21日 星期五 14:33:23 lybykw
	this.SendDefaultDate=function()
	{
		return calendar.currentDate[0]+"-"
			+calendar.formatTime(calendar.currentDate[1])+"-"
			+calendar.formatTime(calendar.currentDate[2])+" "
			+calendar.currentDate[3]+":"
			+calendar.currentDate[4]+":"
			+calendar.currentDate[5];
	}
  
	/************** 加入功能按钮公共样式 *********************/
	this.insertTbCell=function(trRow,cellIndex,TXT,trAlign,tbColSpan){
		var tbCell=trRow.insertCell(cellIndex);
		if(tbColSpan!=undefined) tbCell.colSpan=tbColSpan;

		var btnCell=document.createElement("button");
		tbCell.insertAdjacentElement("beforeEnd",btnCell);
		btnCell.value=TXT;
		btnCell.style.cssText="font:11px tahoma;width:100%;border:1px solid #4A9AF2;background-color:#73B1F5;";
		btnCell.onmouseover=function(){
			btnCell.style.cssText="font:11px tahoma;width:100%;border:1px solid #ffffff;background-color:#A6BFFE;";
		}
		btnCell.onmouseout=function(){
			btnCell.style.cssText="font:11px tahoma;width:100%;border:1px solid #4A9AF2;background-color:#73B1F5;";
		}
		// btnCell.onmousedown=function(){
		//  btnCell.style.cssText="width:100%;border:1 inset;background-color:#F0F0F0;";
		// }
		btnCell.onmouseup=function(){
			btnCell.style.cssText="font:11px tahoma;width:100%;border:1px solid #ffffff;background-color:#73B1F5;";
		}
		btnCell.onclick=function(){
			btnCell.blur();
		}
		return btnCell;
	}
  
	this.setDefaultDate=function(){
		var dftDate=new Date();
		calendar.today[0]=dftDate.getYear();
		calendar.today[1]=dftDate.getMonth()+1;
		calendar.today[2]=dftDate.getDate();
		calendar.today[3]=dftDate.getHours();
		calendar.today[4]=dftDate.getMinutes();
		calendar.today[5]=dftDate.getSeconds();
		calendar.today[6]=dftDate.getMilliseconds();
	}

	/****************** Show Calendar 1 *********************/
	this.show=function(targetObject,returnTime,defaultDate,sourceObject){
		//calendar.shows(targetObject,returnTime,defaultDate,sourceObject);
	}

	/****************** Show Calendar 2 *********************/
	this.shows=function(targetObject,returnTime,defaultDate,sourceObject,fun){
		if(targetObject==undefined) {
			alert("未设置目标对象. \n方法: ATCALENDAR.shows(obj 目标对象,boolean 是否返回时间,string 默认日期,obj 点击对象);\n\n目标对象:接受日期返回值的对象.\n默认日期:格式为\"yyyy-mm-dd\",缺省为当前日期.\n点击对象:点击这个对象弹出calendar,默认为目标对象.\n");
			return false;
		}
		else calendar.target=targetObject;

		if(sourceObject==undefined) calendar.source=calendar.target;
		else calendar.source=sourceObject;

		if(returnTime) calendar.returnTime=true;
		else calendar.returnTime=false;

		//激发别的函数
		//2006年07月25日 星期二 10:46:33 lybykw
		if(fun)	calendar.setDateOther=fun;
		else		calendar.setDateOther=null;

		var firstDay;
		var Cells=new Array();
		if((defaultDate==undefined) || (defaultDate=="")){
			calendar.setDefaultDate();
			var theDate=new Array();
			//设置表头时间
			calendar.head.innerText = calendar.today[0]+"-"+calendar.formatTime(calendar.today[1])+"-"+calendar.formatTime(calendar.today[2]);
			calendar.bottom.innerText = calendar.formatTime(calendar.today[3])+":"+calendar.formatTime(calendar.today[4])+":"+calendar.formatTime(calendar.today[5]);

			theDate[0]=calendar.today[0]; theDate[1]=calendar.today[1]; theDate[2]=calendar.today[2];
			theDate[3]=calendar.today[3]; theDate[4]=calendar.today[4]; theDate[5]=calendar.today[5];
		}
		else{

			//对时间进行验证
			//2006年07月19日 星期三 17:45:10 lybykw
			var s=calendar.CheckDate(calendar.target,defaultDate);
			if(s[0]) return;

			var theDate=new Array(5);
			theDate[0]=s[1][0];
			theDate[1]=s[1][1];
			theDate[2]=s[1][2];
			theDate[3]=s[1][3];
			theDate[4]=s[1][4];
			theDate[5]=s[1][5];
			calendar.head.innerText = theDate[0]+"-"+calendar.formatTime(theDate[1])+"-"+calendar.formatTime(theDate[2]);
			calendar.bottom.innerText = calendar.formatTime(theDate[3])+":"+calendar.formatTime(theDate[4])+":"+calendar.formatTime(theDate[5]);
		}

		calendar.currentDate[0]=theDate[0];
		calendar.currentDate[1]=theDate[1];
		calendar.currentDate[2]=theDate[2];
		calendar.currentDate[3]=theDate[3];
		calendar.currentDate[4]=theDate[4];
		calendar.currentDate[5]=theDate[5];
	   
		theFirstDay=calendar.getFirstDay(theDate[0],theDate[1]);
		theMonthLen=theFirstDay+calendar.getMonthLen(theDate[0],theDate[1]);
		//calendar.setEventKey();

		calendar.calendarPad.style.display="";
		var theRows = Math.ceil((theMonthLen)/7);
		//清除旧的日历;
		while (calendar.body.rows.length > 0) {
			calendar.body.deleteRow(0)
		}

		//建立新的日历;
		var n=0;day=0;
		for(i=0;i<theRows;i++){
			theRow=calendar.body.insertRow(i);
			for(j=0;j<7;j++){
				n++;
				if(n>theFirstDay && n<=theMonthLen){
					day=n-theFirstDay;
					calendar.insertBodyCell(theRow,j,day);
				}
				else{
					var theCell=theRow.insertCell(j);
					theCell.style.cssText="background-color:#F0F0F0;cursor:default;";
				}
			}
		}

		//****************调整日历位置**************//
		var offsetPos=calendar.getAbsolutePos(calendar.source);//计算对象的位置;
		//计算出来的当前INPUT框与下边的距离<当前日期选择框的高度
		//2007年09月21日 星期五 16:10:23 lybykw
		if((document.body.offsetHeight-(offsetPos.y+calendar.source.offsetHeight-document.body.scrollTop))<calendar.calendarPad.offsetHeight){
			//alert("在上显示");
			var calTop=offsetPos.y-calendar.source.offsetHeight-calendar.calendarPad.offsetHeight+12;
		}
		else{
			//alert("在下显示");
			var calTop=offsetPos.y+calendar.source.offsetHeight;
		}

		//日期显示层超过了top
		//2007年10月12日 星期五 11:47:03 lybykw
		if (calTop<0) {
			calTop=5;
		}
		
		//计算出来的当前INPUT框与右边的距离<当前日期选择框的宽度
		//2007年09月21日 星期五 16:31:03 lybykw
		if((document.body.offsetWidth-(offsetPos.x-document.body.scrollLeft))<calendar.calendarPad.offsetWidth){
			var calLeft=(document.body.offsetWidth-calendar.calendarPad.offsetWidth+offsetPos.x)-offsetPos.x-45;
		}
		else{
			var calLeft=offsetPos.x;
		}
		//alert(offsetPos.x);
		calendar.calendarPad.style.pixelLeft=calLeft;
		calendar.calendarPad.style.pixelTop=calTop;
	}

	/****************** 计算对象的位置 *************************/
	this.getAbsolutePos = function(el) {
		var r = { x: el.offsetLeft, y: el.offsetTop };
		if (el.offsetParent) {
			var tmp = calendar.getAbsolutePos(el.offsetParent);
			r.x += tmp.x;
			r.y += tmp.y;
		}
		return r;
	};

	//************* 插入日期单元格 **************/
	this.insertBodyCell=function(theRow,j,day,targetObject){
		var theCell=theRow.insertCell(j);
		if(j==0) var theBgColor="#A0CAF8";
		else var theBgColor="#FFFFFF";
		if(day==calendar.currentDate[2]) var theBgColor="#FCD5AE";
		if(day==calendar.today[2]) var theBgColor="#FF9F29";
		theCell.bgColor=theBgColor;
		theCell.innerText=day;
		theCell.align="center";
		theCell.width=35;
		theCell.style.cssText="font:11px tahoma;border:1px solid #4A9AF2;cursor:hand;text-align:center;";
		theCell.onmouseover=function(){ 
			theCell.bgColor="#349DF8"; 
			theCell.style.cssText="font:11px tahoma;border:1px outset;cursor:hand;text-align:center;";
		}
		theCell.onmouseout=function(){ 
			theCell.bgColor=theBgColor; 
			theCell.style.cssText="font:11px tahoma;border:1px solid #4A9AF2;cursor:hand;text-align:center;";
		}
		theCell.onmousedown=function(){ 
			theCell.bgColor="#5E9FD3"; 
			theCell.style.cssText="font:11px tahoma;border:1px inset;cursor:hand;text-align:center;";
		}
		theCell.onclick=function(){
			if(calendar.returnTime)  
			calendar.sltDate=calendar.currentDate[0]+"-"+calendar.formatTime(calendar.currentDate[1])+"-"+calendar.formatTime(day)+" "+calendar.formatTime(calendar.currentDate[3])+":"+calendar.formatTime(calendar.currentDate[4])+":"+calendar.formatTime(calendar.currentDate[5]);
			else
			calendar.sltDate=calendar.currentDate[0]+"-"+calendar.formatTime(calendar.currentDate[1])+"-"+calendar.formatTime(day);
			//开始附值=============================
			//2006年07月14日 星期五 10:40:17 lybykw 2006年11月24日 星期五 10:16:41
			//ChangeDetailExitOff();
			var k=calendar.Exp(0,[calendar.currentDate[0],calendar.formatTime(calendar.currentDate[1]),calendar.formatTime(day),calendar.formatTime(calendar.currentDate[3]),calendar.formatTime(calendar.currentDate[4]),calendar.formatTime(calendar.currentDate[5])],calendar.target.ext_maxLength);
			if(k[0])	calendar.sltDate=k[1];
			if(calendar.target.value!=calendar.sltDate){
				calendar.target.value=calendar.sltDate;
				//激发的别的函数
				eval(calendar.setDateOther);
			}
			calendar.hide();
		}
	}
	/************** 取得月份的第一天为星期几 *********************/
	this.getFirstDay=function(theYear, theMonth){
		var firstDate = new Date(theYear,theMonth-1,1);
		return firstDate.getDay();
	}
	/************** 取得月份共有几天 *********************/
	this.getMonthLen=function(theYear, theMonth) {
		theMonth--;
		var oneDay = 1000 * 60 * 60 * 24;
		var thisMonth = new Date(theYear, theMonth, 1);
		var nextMonth = new Date(theYear, theMonth + 1, 1);
		var len = Math.ceil((nextMonth.getTime() - thisMonth.getTime())/oneDay);
		return len;
	}
	/************** 隐藏日历 *********************/
	this.hide=function(){
		//calendar.clearEventKey();
		calendar.calendarPad.style.display="none";
	}
	/************** 从这里开始 *********************/
	this.setup=function(defaultDate){
		calendar.addCalendarPad();
		calendar.addCalendarBoard();
		calendar.setDefaultDate();
	}
	/************** 格式化时间 *********************/
	this.formatTime = function(str) {
		str = ("00"+str);
		return str.substr(str.length-2);
	}

	/************** 关于AgetimeCalendar *********************/
	this.about=function(){
		var strAbout = "\日历选择输入控件操作说明:\n\n";
		strAbout+="-\t: 关于\n";
		strAbout+="x\t: 隐藏\n";
		strAbout+="<<\t: 上一年\n";
		strAbout+="<\t: 上一月\n";

		strAbout+="今日\t: 返回当天日期\n";
		strAbout+=">\t: 下一月\n";
		strAbout+="<<\t: 下一年\n";
		strAbout+="\n\n链盘对应：\n←:向前翻月\t\t↑:向前翻年 \n→:向后翻月\t\t↓:向前翻年 \nT:选择当天\t\tX:关闭选择日期对话框 \nH:帮助\t\t\tHome链:向前翻小时 \nEnd链:向后翻小时\t\tPageUp链:向前翻分钟 \nPageDown链:向后翻分钟";
		alert(strAbout);
	}



	this.showOther=function(Obj,dateFormat,fun)
	{
		if (Obj.tagName=="IMG") {
			var Obj=Obj.parentElement.children(0);
			calendar.shows(Obj,dateFormat,Obj.value,undefined,fun);
		}
		else {
			calendar.shows(Obj,dateFormat,Obj.value,undefined,fun);
		}
	}

	//在时间选择框后加入图片
	//dateFormat，表示对时间进行格式化，值为：false/true；ObjStr，表示要对哪个对象进行时间赋值；fun，表示要激发的别的函数。以上参数全为字符型。
	//2006年07月25日 星期二 11:23:29 lybykw
	this.SelectTimeImg=function(dateFormat,ObjStr,fun)
	{
		var d=1;
		//给每个选择时间的图片加上ID编号，以便找到
		calendar.count++;
		var imgId="SelectImg_9984_"+calendar.count;
		try{
			if (PageStatus=="detail") {
				d=0;
				var str='<div id="'+imgId+'" exp_type="time"></div>';
			}
		}catch (e){}
		if (d==1) {
			var str='';
			str+=' <img style="cursor:hand;" src="'+SITEROOT+'public/images/other/select_date.gif" border="0" alt="请点击选择时间" onclick="SelectDate.showOther('
			if (ObjStr) {
				str+=ObjStr;
			}
			else {
				str+="this";
			}
			if (!dateFormat) {
				dateFormat="false";
			}
			str+=','+dateFormat+'';
			if(fun)	str+=',\''+fun+'\'';
			str+=');" id="'+imgId+'">';
		}
		document.write(str);//alert(str)
		var Obj=document.getElementById(imgId).parentNode.children(0);
		//找到图片前的INPUT框，并对值时行格式化。
		//2006年07月19日 星期三 14:22:36 lybykw 2006年12月15日 星期五 14:59:44
		Obj.value=calendar.PageSetDateFormat(Obj.value,Obj.ext_maxLength)
		//document.write('<textarea name="" rows="10" cols="40" style="overflow:auto;" needs="">'+str+'</textarea>');
	}

	//时间格式化
	//2006年12月15日 星期五 14:58:45 lybykw
	this.PageSetDateFormat=function(ObjValue,ObjMxaxLength)
	{
		var v="",t=ObjValue;
		if (t!="") {
			t=t.replace(/\.0$/,"");
			t1=t.replace(/\-/ig,"/");
			var nt=calendar.datePack(t1);
			var a=[];
			a[0]=nt.getFullYear();
			a[1]=nt.getMonth()+1;
			a[2]=nt.getDate();
			a[3]=nt.getHours();
			a[4]=nt.getMinutes();
			a[5]=nt.getSeconds();
			a[6]=nt.getMilliseconds();
			var k=calendar.Exp(0,a,ObjMxaxLength);
			k[0] ? calendar.sltDate=k[1] : calendar.sltDate=t;
			v=calendar.sltDate;
		}
		return v;
	}

	//时间扩展属性
	//2006年07月14日 星期五 10:30:44 //2006年07月14日 星期五 10:30:51
	//key，0表示取得时间，开始返回值。1表示对时间进行验证
	this.Exp=function(key,timeObj,extFomart)
	{
		if(extFomart=="") return [false];
		//如果 input 框的属性中有 extFomart ，且字符长度大于5以上，那么就说明指定了时间的格式。
		//if (key==0 && extFomart.indexOf("yyyy")>-1) {
		//alert(extFomart.length)
		//如果没有 input 框没有 ext_maxLength 属性，就返回假
		//2007年03月16日 星期五 13:53:52
		if(extFomart==undefined){
			return [false];
		}
		if (key==0 && extFomart.length>5) {
			var dateFormatStr=extFomart;
			dateFormatStr=dateFormatStr.replace("yyyy",timeObj[0]);
			dateFormatStr=dateFormatStr.replace("MM",calendar.formatTime(timeObj[1]));
			dateFormatStr=dateFormatStr.replace("dd",calendar.formatTime(timeObj[2]));
			dateFormatStr=dateFormatStr.replace("hh",calendar.formatTime(timeObj[3]));
			dateFormatStr=dateFormatStr.replace("HH",calendar.formatTime(timeObj[3]));
			dateFormatStr=dateFormatStr.replace("mm",calendar.formatTime(timeObj[4]));
			dateFormatStr=dateFormatStr.replace("ss",calendar.formatTime(timeObj[5]));
			return [true,dateFormatStr];
		}
		else {
			return [false];
		}
	}

	//对日期进行检查
	//2006年07月19日 星期三 13:34:20 lybykw //2007年04月30日 星期一 16:55:09
	this.CheckDate=function(Obj,s)
	{
		var o=Obj.value;
		var t="";
		var str="";
		if (o!="") {
			str=o.replace(/\.0$/,"");
			str=str.replace(/\-/ig,"/");

			//对时间中的，年、月、日、时、分、秒，进行大小的判断
			//2007年04月28日 星期六 14:44:38 lybykw
			//============================================
			var ckDate=str.replace(/\s/,"/");
			ckDate=ckDate.replace(/\:/ig,"/");
			ckDate=ckDate.split("/");
			for (var i = 0; i < ckDate.length; i++) {
				//判断年只能是4位数。
				var dateNumber
				if (i==0) {
					if (ckDate[i].length>4) {
						SystemMsg("年不能是5位！");
						try{Obj.focus();}catch (e){}
						return [true];
					}
					dateNumber=parseInt(ckDate[i]);
				}
				//判断只能是两位数。
				else {
					if (ckDate[i].length>3) {
						SystemMsg("时间严重不正确！");
						try{Obj.focus();}catch (e){}
						return [true];
					}
					dateNumber=parseInt(ckDate[i].replace("0",""));
				}
				if (isNaN(dateNumber)) {
					SystemMsg("时间格式有错误！");
					try{Obj.focus();}catch (e){}
					return [true];
				}
				switch (i) {
					case 0 :
						if (dateNumber>3000 || dateNumber<1900) {
							SystemMsg("年份不正确！");
							try{Obj.focus();}catch (e){}
							return [true];
						}
						break;
					case 1 :
						if (dateNumber>12 || dateNumber<1) {
							SystemMsg("月不正确！");
							try{Obj.focus();}catch (e){}
							return [true];
						}
						break;
					case 2 :
						if (dateNumber>31 || dateNumber<1) {
							SystemMsg("日不正确！");
							try{Obj.focus();}catch (e){}
							return [true];
						}
						break;
					case 3 :
						if (dateNumber>23 || dateNumber<0) {
							SystemMsg("时不正确！");
							try{Obj.focus();}catch (e){}
							return [true];
						}
						break;
					case 4 :
						if (dateNumber>59 || dateNumber<0) {
							SystemMsg("分不正确！");
							try{Obj.focus();}catch (e){}
							return [true];
						}
						break;
					case 5 :
						if (dateNumber>59 || dateNumber<0) {
							SystemMsg("秒不正确！");
							try{Obj.focus();}catch (e){}
							return [true];
						}
						break;
					default :
						//alert(i+"=nfs7.com");
				}
			}
			//============================================

			t=calendar.datePack(str);
			if (isNaN(t)) {
				SystemMsg("默认日期(时间)的格式不正确！\t\n\n默认可接受格式为:\n1、yyyy-mm-dd \n2、yyyy-mm-dd hh:mm:ss \n3、yyyy-mm-dd hh:mm \n4、(空)");
				try{Obj.focus();}catch (e){}
				calendar.setDefaultDate();
				return [true];
			}
		}
		var a=new Array(6);
		if (s) {
			var sstr=s.replace(/\-/ig,"/");
			s=calendar.datePack(sstr);
			a[0]=s.getFullYear();
			a[1]=calendar.formatTime(s.getMonth()+1);
			a[2]=calendar.formatTime(s.getDate());
			if(t!=undefined) {
				a[3]=calendar.formatTime(s.getHours());
				a[4]=calendar.formatTime(s.getMinutes());
				a[5]=calendar.formatTime(s.getSeconds());
				a[6]=calendar.formatTime(s.getMilliseconds());
			}
			else {
				a[3]=calendar.formatTime(calendar.today[3]);
				a[4]=calendar.formatTime(calendar.today[4]);
				a[5]=calendar.formatTime(calendar.today[5]);
				a[6]=calendar.formatTime(calendar.today[6]);
			}
		}
		else {
			a[0]=t.getFullYear();
			a[1]=calendar.formatTime(t.getMonth()+1);
			a[2]=calendar.formatTime(t.getDate());
			a[3]=calendar.formatTime(t.getHours());
			a[4]=calendar.formatTime(t.getMinutes());
			a[5]=calendar.formatTime(t.getSeconds());
			a[6]=calendar.formatTime(t.getMilliseconds());
		}
		return [false,a];
	}

	//对时间进行格式补充化
	//2007年03月20日 星期二 13:47:23 lybykw
	this.datePack=function(str)
	{
		var s=new Date(str);
		if (isNaN(s)) {
			s=new Date(str+"-01");
		}
		return s;
	}

	document.onkeydown=function(){
		if(calendar.calendarPad.style.display=="none"){
			window.event.returnValue= true;
			return true ;
		}
		switch(window.event.keyCode){
			case 27 : calendar.hide(); break; //ESC
			case 37 : calendar.prevMonth.onmousedown(); break;//←
			case 38 : calendar.prevYear.onmousedown();break; //↑
			case 39 : calendar.nextMonth.onmousedown(); break;//→
			case 40 : calendar.nextYear.onmousedown(); break;//↓
			case 84 : calendar.goToday.onclick(); break;//T
			case 88 : calendar.hide(); break;   //X
			case 72 : calendar.about(); break;   //H	
			case 36 : calendar.prevHours.onmousedown(); break;//Home
			case 35 : calendar.nextHours.onmousedown(); break;//End
			case 33 : calendar.prevMinutes.onmousedown();break; //PageUp
			case 34 : calendar.nextMinutes.onmousedown(); break;//PageDown
		} 
		window.event.keyCode = 0;
		//window.event.returnValue= false;
	}

	calendar.setup();
}

var SelectDate = new atCalendarControl();

