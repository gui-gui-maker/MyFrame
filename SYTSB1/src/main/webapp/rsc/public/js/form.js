
/*
Javascript Form 表单常用函数
Create:2006年02月06日 09:33:26
LastEdit:2009年01月12日 星期一 10:05:25
Dev:Liu YueBo
*/

//在提交 form 表单时检查哪些输入框是需要填写的。应用对象：input , select
function CheckForm(Obj)
{
	var Obj=Obj.elements;
	for (i=0;i<Obj.length;i++) {
		var ObjName=Obj[i].name ? true : false;
		if (ObjName) {//hidden元素，无元素名称元素，多表的元素不检查。2006年06月06日 星期二 14:17:55 lybykw
			if (Obj[i].name.indexOf("_1")<0) {
				if (CheckFormElement(Obj[i])) {
					return true;
				}
            }
		}
	}
	return false;
}

function CheckFormElement(Obj)
{
	/*var isNull=Obj.attributes.getNamedItem("isNull").nodeValue;
	var ext_type=Obj.attributes.getNamedItem("ext_type").nodeValue;
	var ext_name=Obj.attributes.getNamedItem("ext_name").nodeValue;
	var ext_float=Obj.attributes.getNamedItem("ext_float").nodeValue;
	var ext_maxLength=Obj.attributes.getNamedItem("ext_maxLength").nodeValue;*/
	var isNull=Obj.isNull;
	var ext_type=Obj.ext_type;
	var ext_name=Obj.ext_name;
	var ext_float=Obj.ext_float;
	var ext_maxLength=Obj.ext_maxLength;
	var ext_floatLength=0;//小数位数的个数。//2007年01月05日 星期五 15:32:51 lybykw
	if(isNull=="N"){
		if(Obj.value==""){
			SystemMsg(ext_name+"是必填项！");
			try{Obj.focus();}catch (e){}
			return true;
		}
	}
	if(ext_type) {
		if(ext_type=="float") {
			//判断是否为数字。
			//2006年07月06日 星期四 17:17:36 //2006年07月06日 星期四 17:17:41
			if(isNaN(Obj.value)) {
				SystemMsg(ext_name+"应为数字！");
				try{Obj.focus();}catch (e){}
				return true;
			}
			//小数位数的精度
			//2006年07月06日 星期四 17:17:47 //2006年07月06日 星期四 17:17:49
			if(ext_float) {
				if(Obj.value.indexOf(".")>-1) {
					var floatStr=Obj.value.substring(Obj.value.indexOf("."),Obj.value.length);
					ext_float=parseInt(ext_float);
					if(floatStr.length-1>ext_float) {
						SystemMsg(ext_name+"的小数位数只能有 "+ext_float+" 位！");
						try{Obj.focus();}catch (e){}
						return true;
					}
					ext_floatLength=ext_float+1;
				}
			}
		}
		if(ext_type=="time" && Obj.value!="")
		{
			var s=SelectDate.CheckDate(Obj);
			if(s[0]) return true;
			var mr=s[1][0]+"-"+s[1][1]+"-"+s[1][2]+" "+s[1][3]+":"+s[1][4]+":"+s[1][5];
			if (ext_maxLength) {
				if (ext_maxLength.indexOf("yyyy")>-1) {
					var dateValue=ext_maxLength;
					dateValue=dateValue.replace("yyyy",s[1][0]);
					dateValue=dateValue.replace("MM",s[1][1]);
					dateValue=dateValue.replace("dd",s[1][2]);
					dateValue=dateValue.replace("hh",s[1][3]);
					dateValue=dateValue.replace("mm",s[1][4]);
					dateValue=dateValue.replace("ss",s[1][5]);
					//对最特别的日期表现形式进行判断
					//2007年04月28日 星期六 13:56:51 lybykw
					if (ext_maxLength=="yyyy-MM") {
						Obj.value=Obj.value;
					}
					else {
						Obj.value=dateValue;
					}
				}
				else {
					Obj.value=mr;
				}
			}
			else {
				Obj.value=mr;
			}
		}
		if (ext_type=="postalCode" && Obj.value!="") {
			if (/^[0-9]{6,6}$/.test(Obj.value)==false) {
				SystemMsg("邮政编码只能六位数字");
				try{Obj.focus();}catch (e){}
				return true;
			}
		}
		if (ext_type=="email" && Obj.value!="") {
			var sReg = /[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/;
			if ( ! sReg.test(Obj.value) ) {
				SystemMsg("电子邮件地址不正确！请重新输入。");
				try{Obj.focus();}catch (e){}
				return true;
			}
		}
	}
	if(ext_maxLength && ext_type!="time")
	{
		//alert(Obj.value+"<<={"+maxInt+"}================={"+Obj.tagName+"}{"+Obj.name+"}=")
		var maxInt=parseInt(ext_maxLength)+ext_floatLength;
		var ovLen=GetBytesLength(Obj.value);
		if(!isNaN(maxInt) && ovLen>maxInt) {
			
			SystemMsg(ext_name+"的长度不能超过"+maxInt+"个字符，您现在"+ext_name+"的长度为："+ovLen+"个字符！");
			try{
				Obj.focus();
			}
			catch (e){}
			return true;
		}
	}
}

//在提交 form 表单时检查哪些选择框是需要填写的。应用对象：radio , checkbox
//参数 ：(form 中的 要验证的元素对象,必须选择的个数,对话框文字)
function CheckFormRadioAndCheckBox(Obj,CkedNum,Astr)
{
	var Num=0,Obj2;
	if(Obj.length){
		for(var i=0;i<Obj.length;i++){
			if(Obj[i].checked==true){
				Num++;
				Obj2=Obj[0];
			}
		}
	}
	else{
		if(Obj.checked==true){
			Num++;
			Obj2=Obj;
		}
	}
	try{
		if(Num==0){
			SystemMsg("请选择"+Astr);
			Obj2.focus();
			return true;
		}
		else if(Num<CkedNum){
			SystemMsg("选择的"+Astr+"数量不够！选择的数量必须大于"+CkedNum);
			Obj2.focus();
			return true;
		}
		else if(Num>CkedNum){
			return false;
		}
	}
	catch (e){
		//SystemMsg("失去焦点！"+e);
		return true;
	}
}

//检查长度，不允许超过 numberLength
function CheckInputLength(Obj,alertStr,numberLength){
	var length=GetBytesLength(Obj.value);
	if(length>numberLength){
		SystemMsg(alertStr+"的长度不能超过"+numberLength+"个字符，您现在"+alertStr+"的长度为："+length+"个字符！");
		Obj.focus()
		return true;
	}
	return false;
}

//获取真的字符串长度
function GetBytesLength(str)
{
    var re=/[\x00-\xff]/g;
    var len=str.length;
    var array=str.match(re);
    if (array==null) {
        array="";
    }
    return len*2 - array.length;
}

//在对数据进行提交时，相关按钮不能再按。
//例子：SubDisabled(form.sub,form.button)
function SubDisabled()
{
	for(var i=0;i<arguments.length;i++){
		if(arguments[i].length){
			for(var j=0;j<arguments[i].length;j++){
				arguments[i][j].disabled=true;
			}
		}
		else{
			arguments[i].disabled=true;
		}
	}
}

//在详细页面时，所要 onload 的函数
function ChangePageStatus()
{
	//d1=new Date();
	//alert(arguments.length)
	var nKey=GetPageType();
	if (arguments[0][0].tagName=="FORM") {
		for (var i=0;i<arguments[0].length;i++) {
			for (var j=0;j<arguments[0][i].elements.length;j++) {
				InputChange(arguments[0][i].elements[0],nKey);
			}
		}
	}
	else {
		var formEl=arguments[0].elements.length;
		for (var i=0;i<formEl;i++) {
			var Obj=arguments[0].elements;
			if(PageStatus=="detail") {
				InputChange(Obj[0],nKey);
			}
			else {
				InputChange(Obj[i],nKey);
			}
		}
	}
	//d2=new Date();alert(d2-d1)
	changeDivStatus();
}

//可对相关标签的 input 状态进行单独的改变状态。
//2006年06月22日 星期四 16:03:41 lybykw //2006年06月22日 星期四 16:03:45
function changeDivStatus()
{
	var Obj=document.body.getElementsByTagName("DIV");
	var l=Obj.length;
	var divs=[];
	for (var i = 0; i < l; i++) {
		el=Obj[i]
		cn=el.className;
		if ( cn == "" ) continue;
		
		if (Obj[i].PageStatus=="detail") {
			divs[divs.length]=Obj[i];
		}
	}
	for (var i = 0; i < divs.length; i++) {
		var all=divs[i].getElementsByTagName("*");
		var allLeng=all.length;//	alert(allLeng)
		for (var j = 0; j < allLeng; j++) {
			//新增第二个参数 0 ,当 nKey 变量
			//2006年12月18日 星期一 16:13:36 lybykw
			InputChange(all[j],0);
		}
	}
}

function InputChange(Obj,nKey)
{
	if(!Obj) return;

	//2006年11月24日 星期五 10:21:42 lybykw
	if(Obj.tagName=="INPUT" || Obj.tagName=="TEXTAREA" || Obj.tagName=="SELECT")
	{
		//改变所有 form 元素的函数
		//alert(Obj.tagName+"<<>>"+Obj.value)
		if(nKey==0 && Obj.tagName!="SELECT") {
			DoChangeObjStatus(Obj,Obj.value);
		}
		else if (nKey==1) {
			if ( typeof Obj.attachEvent != Object ) {
				Obj.attachEvent( "onchange", ChangeDetailExitOff );
			}
		}
	}
}

//在详细页面查看状态时，改变所有 form 元素的函数。
function DoChangeObjStatus(Obj,ObjValue)
{
	if(Obj=="cs")
	{
		ObjValue="<div class=view_detail_font>"+ObjValue+"</div>";
		document.write(OValue);
	}
	else
	{
		//2006年06月09日 星期五 14:48:59 最后修改
		if (Obj.id=="submitElement") {
			//控制所有 ID 为 submitElement 或者隐藏框都不显示出来。2006年06月09日 星期五 14:48:47
			//Obj.style.display="none";
			Obj.outerHTML="";
		}
		else {
			Obj.outerHTML=InputSetDetailValue(Obj,Obj.name,ObjValue);
		}
	}
}

//详细状态时，重建所有 input 框，把 input 框从内存中替换成 div
function InputSetDetailValue(Obj,ObjName,ObjValue)
{
	//解决了form.js中在详细页面时，对input框后有Main.Note()方法存在时，可自动判断后方的说明文字。
	//Liu Yue Bo 2006年04月20日 星期四 17:33:56
	var td=Obj.parentNode;
	if(ObjValue=="")	ObjValue="&nbsp;";
	ObjValue=ObjValue.replace(/\n/g,"<br>");
	var inputDiv="<div class=view_detail_font id='"+ObjName+"'";
	if(td.children.length>2)
	{
		//alert(td.innerHTML)
		if(td.children(2).exp_type!="time")
			inputDiv+=" style=float:left;width:70%;overflow:hidden;";
	}
	if(Obj.type=="hidden")
	{
		inputDiv+=" style=display:none;"
	}
	if(Obj.type=="password")
	{
		ObjValue="********";
	}
	ObjValue=inputDiv+">"+ObjValue+"</div>";
	return ObjValue;
}

// ==============================================================
// Select 对象通用函数,，开始=============================================
// ==============================================================
/*function CreateSelectInit()
{
	if (document.addEventListener) {
		//document.addEventListener("keyup", ComboBox_handleKey, false );
		document.addEventListener("mousedown", CreateSelectMouseDown, false );
	}
	else if (document.attachEvent) {
		//document.attachEvent("onkeyup", function () { ComboBox_handleKey(window.event); } );
		document.attachEvent("onmousedown", function () { CreateSelectMouseDown(window.event); } );
	}
}

function CreateSelectMouseDown(e)
{
	var eObj=e.target ? e.target : e.srcElement;
	while(eObj.nodeType!=1) el=eObj.parentNode;
	if(eObj.dpsk!="1"){
		document.getElementById("Select_Obj_alibaba").style.display="none";
	}
}

function CSSV(Obj,tts)
{

	var inputTrue=document.all(Obj);
	var inputTxt=document.all(Obj+"_txt");
	if(tts.value!=""){
		inputTrue.value=tts.value;
		inputTxt.value=tts.innerText;
	}
	else{
		inputTrue.value="";
		inputTxt.value="";
	}
	document.getElementById("Select_Obj_alibaba").style.display="none";
}*/

function CreateSelectInit()
{
	if (document.addEventListener) {
		//document.addEventListener("keyup", CreateSelectKeyUp, false );
		//document.addEventListener("mousedown", CreateSelectMouseDown, false );
	}
	else if (document.body.attachEvent) {
		//document.body.attachEvent("onkeyup", function () { CreateSelectKeyUp(window.event,"Select_Obj_alibaba"); } );
		document.body.attachEvent("onmousedown", function () { SelectDivMainShow(document.getElementById("Select_Obj_alibaba"),""); } );
	}
}

function CreateSelectKeyUp(e)
{
	//空
	switch ( e.keyCode ){
		case 38 : SelectKeyMove(-1) ;	break ;
		case 40 : SelectKeyMove(1) ;	break ;
	}
}

function SelectObjOnKeyUp()
{
	//event.cancelBubble=true;//2007年10月09日 星期二 17:30:45 lybykw 做到这里了。
	document.getElementById("Select_Obj_alibaba").blur();
}

function SelectKeyMove(n)
{
	//alert(n)
}

function CSSV(Obj,tts)
{
	//var eObj=window.event.srcElement;
	//while (eObj=eObj.offsetParent) {
		//alert(eObj.tagName)
	//}
	var values="";
	var inputTrue=document.all(Obj);
	var inputTxt=document.all(Obj+"_txt");
	if(tts.value!=""){
		values=tts.value;
	}
	//2006年06月14日 星期三 16:48:16 lybykw
	//不可以输入的 Select
	if (selectObj.arr[0][6]!="1") {
		selectObj.value=values;
		selectObj.text=tts.title;
		inputTrue.value=values;
		inputTxt.value=tts.title;
		//inputTxt.style.background="#FFFFFF";
		//inputTxt.style.color="#000000";
	}
	//可以输入的 Select
	else {
		selectObj.value=tts.title;
		selectObj.text=tts.title;
		inputTrue.value=tts.title;
		inputTxt.value=tts.title;
		//inputTrue.style.background="#FFFFFF";
		//inputTrue.style.color="#000000";
	}
	ChangeDetailExitOff();
	SelectDivMainShow(document.getElementById("Select_Obj_alibaba"),"");
}

//创建一个主层，用于选择对象
//2007年10月09日 星期二 16:06:30 lybykw 新增 event.cancelBubble=true。设置或获取当前事件是否要在事件句柄中向上冒泡。
document.write('<div id="Select_Obj_alibaba" style="position:absolute;display:none;background:#FFFFFF;border:1px solid #15537C;z-index:9988;" onselectstart="return false;" oncontextmenu="return false;" ondragstart="return false;" onmousedown="event.cancelBubble=true">创建一个主层，用于选择对象</div>');

//得到 selectObj
//2006年06月08日 星期四 15:49:49 lybykw
var selectObj={
	arr:null,value:"",text:"",count:0
}

function CreateSelect(arr, typeCode)
{
	selectObj.count++;
	var arrLength=arr.length;
	var dw=159;
	var dheight=0;
	var dh=arr[0][5];
	//2007年10月11日 星期四 00:25:20 lybykw
	if (dh!=undefined && dh!="") {
		if (dh.indexOf("||")>-1) {
			dh=dh.split("||");
			dw=parseInt(dh[0]);
			dheight=parseInt(dh[1]);
		}
		else {
			dw=dh;
		}
	}

	//找到默认值
	var selectDefaultValue=arr[0][2];
	var selectDetailValue="";
	var selectDetailText="";
	var selectIsHtml=arr[0][8];
	if (selectDefaultValue!="") {
		//找到默认值
		for (var i = 0; i < arrLength; i++) {
			if(selectDefaultValue==arr[i][0])
			{
				if (selectIsHtml=="html") {
					//如果没有第二项值，就取第一项的值
					if (arr[i][2]==undefined || arr[i][2]=="") {
						selectDetailText=arr[i][1];
					}
					else {
						selectDetailText=arr[i][2];
					}
				}
				else {
					selectDetailText=arr[i][1];
				}
				selectDetailValue=arr[i][0];
				break;
			}
		}
	}
	

	//2006年05月30日 星期二 10:16:03 lybykw,新增与修改
	var selectHiddenName=arr[0][1];
	var selectTextName=arr[0][1]+"_txt";
	var selectButtonName=arr[0][1]+"_bt";
	if (arr[0][6]!="1") {
		//不可以输入的 Select
		document.write('<input type="hidden" name="'+selectHiddenName+'" value="'+selectDetailValue+'"');
		if(arr[0][4]!="")	document.write(arr[0][4]);
		document.write('/>');
		document.write('<input type="text" name="'+selectTextName+'" value="'+selectDetailText+'" size="20" class="ipt_01_s" style="width:'+dw+'px;"');
		document.write(' readonly');
	}
	else {
		//可以输入的 Select
		selectDetailValue=arr[0][2];
		selectDetailText=arr[0][2];
		document.write('<input type="text" name="'+selectHiddenName+'" value="'+selectDetailText+'" size="20" class="ipt_01_s" style="width:'+dw+'px;"');
		if(arr[0][4]!="")	document.write(arr[0][4]);
		document.write('/>');
		document.write('<input type="hidden" name="'+selectTextName+'" value="'+selectDetailValue+'"');
	}
	document.write('/>');
	document.write('<input type="button" name="'+selectButtonName+'" value="" id="submitElement" class="select_Obj" onselectstart="return false;" oncontextmenu="return false;" ondragstart="return false;" style="font-family:webdings;padding:0px;"/>');

	if (arrLength<=1) {
		return false;
	}

	//对 Select 对象赋值。
	//2006年06月08日 星期四 10:16:10 lybykw
	if (arr[0][6]!="1") {//不可以输入的 Select
		var TrueIndexObj=document.all(selectHiddenName);
		var ReadObj=document.all(selectTextName);
	}
	else {//可以输入的 Select
		var TrueIndexObj=document.all(selectTextName);
		var ReadObj=document.all(selectHiddenName);
	}
	var Obj=document.all(selectButtonName);
	var divObj=document.all("Select_Obj_alibaba");
	//以下两属性主要用于一对多表时select对象
	TrueIndexObj.setAttribute("typeother","select");
	TrueIndexObj.setAttribute("ArrValue",arr);

	if (typeCode)
	{
		//弹出码表维护页面，并返回页面，重新计算 arr 。以便更快的对码表进行维护
		//2006年06月07日 星期三 15:58:05 lybykw
		document.write('<input type="button" value="+" id="submitElement" name="'+arr[0][1]+'_typeCode" class="select_Obj_mb" title="码表维护">')
		var typeCodeUrl="/cfdinfo?CONTROLLER=com.khnt.command.BlankSecuredCmd&NEXT_PAGE=PUB_SYSTEM_CODE_VALUE_DETAIL&type="+typeCode;
		var typeCodeObj=document.all(arr[0][1]+'_typeCode');
		typeCodeObj.onclick=function()
		{
			var Obj=WinOpen(1,typeCodeUrl,"",580,450);
			if (Obj!=undefined) {
				var newArr=[];
				newArr.push(arr[0]);
				for (var i = 0; i < Obj.length; i++) {
					if (i==0 && arr[1][0]=="") {
						newArr.push(["",""]);
					}
					//弹出码表页面有返回值，并且有被选择的值被提交时，且与返回值中的第 i 组数据的第一维值相等，就把原来 select 对象的值改变。
					//2006年09月06日 星期三 13:17:33 lybykw
					if (Obj[i][0]==Obj.selecteIndex) {
						//alert(Obj.a[i][1])
						TrueIndexObj.value=Obj.a[i][0];
						ReadObj.value=Obj[i][1];
					}
					newArr.push(Obj[i]);
				}
				arr=newArr;
			}
		}
	}

	ReadObj.onchange=function() {
		if(ReadObj.value==""){
			TrueIndexObj.value="";
		}
	}

	//都可以选择
	//2006年06月15日 星期四 16:28:02 lybykw
	ReadObj.onclick=function() {
		CreateSelectMain(ReadObj,arr,dw,divObj,ReadObj,dheight,TrueIndexObj);
	}
	Obj.onclick=function() {
		CreateSelectMain(Obj,arr,dw,divObj,ReadObj,dheight,TrueIndexObj);
	}
	/*//暂时关闭，因为上下选择功能不好实现
	ReadObj.onkeyup=function() {
		SelectObjOnKeyUp()
	}
	Obj.onkeyup=function() {
		SelectObjOnKeyUp()
	}*/

	if (selectObj.count==1) {
		CreateSelectInit();
	}

}

//显示 select 对象
//2007年09月27日 星期四 15:06:47 lybykw
function SelectDivMainShow(Obj,values)
{
	if (values=="") {
		Obj.style.display="none";
	}
	else if (values="show") {
		Obj.style.display="block";
	}
}

//创建 select 下拉对象
//2007年09月27日 星期四 15:07:08 lybykw
function CreateSelectMain(Obj,arr,dw,divObj,ReadObj,dheight,TrueIndexObj)
{
	var sdCreateSelectMain=new Date();

	var e=ReadObj;
	var topPx=e.offsetTop;
	var leftPx=e.offsetLeft;
	var widthPx=e.offsetWidth;
	var heightPx=e.offsetHeight;
	var divObjTopPx=0;

	while(e=e.offsetParent)
	{
		topPx+=e.offsetTop;
		leftPx+=e.offsetLeft;
	}

	var dwdith=parseInt(dw)+17;
	divObj.innerHTML="";
	//显示 select 对象最外层
	SelectDivMainShow(divObj,"show");
	//最外层 2007年09月21日 星期五 17:11:10 lybykw
	var divObjMain=document.createElement("div");
	divObjMain.style.fontSize="12px";
	divObjMain.style.padding="2px";

	//最外层有阴影
	divObj.style.filter="progid:DXImageTransform.Microsoft.shadow(direction=135,color=#8E8E8E,strength=3)";

	//重新计算下拉层的高度、宽度与顶部的距离
	//2006年07月07日 星期五 15:24:32 2007年09月27日 星期四 14:38:22
	//var optionShowCount=11;//超过多少条，有纵向滚动条出现。2007年09月26日 星期三 15:36:14 lybykw
	var arrLength=arr.length-1;//整个 select 对象的 option 长度。
	var scrollKey=false;//是否有滚动条出现
	var optionHeight=18;//每个 option 选择项的高度。2007年09月26日 星期三 15:34:33 lybykw
	var seleDivMainHeight=0;//select 对象的高度。
	var childDivWidth=0;//给 option 层定义宽度。
	var bodyObj=document.body;
	var ObjTop=topPx-bodyObj.scrollTop;//select 对象所在位置与顶部的距离 2007年09月27日 星期四 11:30:06 lybykw
	var ObjBottom=bodyObj.clientHeight-(topPx-bodyObj.scrollTop)-heightPx;//select 对象所在位置与底部的距离 2007年09月27日 星期四 11:30:14 lybykw
	var selectOptionHeight=arrLength*optionHeight;//select 对象 option 的总高度
	var scrollWidth=bodyObj.offsetWidth-bodyObj.scrollWidth;

	//alert(document.body.clientHeight+"===="+(topPx-document.body.scrollTop))
	//alert("select 对象的自身高度为："+ReadObj.offsetHeight+"，与顶部的距："+ObjTop+"，与底部的距离="+ObjBottom+"，整个当前body的高度="+bodyObj.clientHeight+"，option 的总高度："+selectOptionHeight);

	//select 对象与顶部的距离大于与select 对象底部的距离
	//2007年09月27日 星期四 11:32:19 lybykw 2007年09月27日 星期四 13:28:07
	if (ObjTop>ObjBottom && selectOptionHeight>ObjBottom) {
		//alert("下拉层显示在select 对象的上方");
		if (arrLength>parseInt(ObjTop/optionHeight)) {
			//alert("下拉层显示在select 对象的上方，有滚动条");
			seleDivMainHeight=ObjTop-14;
			divObjTopPx=document.body.scrollTop+5;
			childDivWidth=dwdith-scrollWidth;
			scrollKey=true;
		}
		else {
			//alert("下拉层显示在select 对象的上方，无滚动条");
			divObjTopPx=topPx-selectOptionHeight-10;
			childDivWidth=dwdith-4;
			scrollKey=false;
		}
	}
	else {
		//alert("下拉层显示在select 对象的下方");
		if (arrLength>parseInt(ObjBottom/optionHeight)) {
			//alert("下拉层显示在select 对象的下方，有滚动条");
			seleDivMainHeight=ObjBottom-8;
			divObjTopPx=topPx+heightPx;
			childDivWidth=dwdith-scrollWidth;
			scrollKey=true;
		}
		else {
			//alert("下拉层显示在select 对象的下方，无滚动条");
			divObjTopPx=topPx+heightPx;
			childDivWidth=dwdith-4;
			scrollKey=false;
		}
	}
	divObjMain.style.width=dwdith;
	if (scrollKey) {
		if (dheight==0) {
			divObjMain.style.height=seleDivMainHeight+3;
		}
		else {
			divObjMain.style.height=dheight+3;
		}
		divObjMain.style.overflowY="auto";
	}
	divObj.style.top=divObjTopPx;
	divObj.style.left=leftPx;

	//产生 select 对象显示第二大层， 有滚动条的这一层。
	divObj.insertAdjacentElement("beforeEnd",divObjMain);
	
	var x=0;selectedIndexScrollTop=null;
	
	//产生 option 选择层
	//2007年09月25日 星期二 17:00:08 lybykw
	arrLength=arrLength+1;
	for (var i = 1; i < arrLength; i++) {
		if(i!=2 || arr[i][0]!="") {	//数组中第2维数组为空时，不显示。 2006年06月14日 星期三 9:54:31 lybykw
			var divId=arr[0][1]+"Se"+i;
			//2007年10月11日 星期四 00:55:21 lybykw
			var divObjTitle="";//为点击后要取值的值。
			var divObjInnerHTML=arr[i][1];
			if (arr[0][8]=="html") {
				if (arr[i][2]==undefined || arr[i][2]=="") {
					divObjTitle=arr[i][1];
				}
				else {
					divObjTitle=arr[i][2];
				}
			}
			else {
				divObjTitle=arr[i][1];
			}

			var divObjChild=document.createElement("div");
			//取值
			//2007年10月11日 星期四 00:50:15 lybykw
			var divObjValue="";
			if (arr[i]!="") {
				divObjValue=arr[i][0];
			}
			else {
				divObjInnerHTML="================"
				divObjTitle="";
			}
			divObjChild.value=divObjValue;
			divObjChild.title=divObjTitle;
			divObjChild.className="sli";
			divObjChild.onclick=function(){
				CSSV(arr[0][1],this);
			}
			//产生 option 对象层。
			divObjMain.insertAdjacentElement("beforeEnd",divObjChild);

			var divObjChildA=document.createElement("a");
			divObjChildA.style.width=childDivWidth;			
			divObjChildA.href="#SelectObjOption";

			if (TrueIndexObj.value==arr[i][0]) {
				selectedIndexScrollTop=x;
				divObjChildA.style.background="#335EA8";
				divObjChildA.style.color="#FFFFFF";
			}
			//响应 onchange 事件 2006年06月08日 星期四 15:56:58 lybykw
			if (arr[0][7]) {
				divObjChildA.href="javascript:"+arr[0][7];
			}
			divObjChildA.innerHTML=divObjInnerHTML;//比 innerText 慢 0.050 秒 2007年09月26日 星期三 13:58:29 lybykw
			//divObjChildA.innerText=r;
			//alert(divObjChildA.innerHTML)
			//产生 A 标签层。
			divObjChild.insertAdjacentElement("beforeEnd",divObjChildA);
			x+=divObjChild.offsetHeight;
		}
	}
	selectObj.arr=arr;	

	//显示被选中值的位置
	//2007年09月25日 星期二 16:57:11 lybykw
	var divObjMainScrollHeight=parseInt(divObjMain.scrollHeight);
	var divObjMainHeight=parseInt(divObjMain.style.height);
	if (divObjMainScrollHeight>divObjMainHeight) {
		if (selectedIndexScrollTop>divObjMainHeight) {
			//alert(divObjMain.scrollHeight+"==="+x+"========="+divObjMain.style.height+"==="+selectedIndexScrollTop);
			divObjMain.scrollTop=selectedIndexScrollTop;
		}
	}
	//计算产生 select 对象一共花了多少时间
	//2007年09月26日 星期三 14:23:19 lybykw
	//var edCreateSelectMain=new Date();alert(((edCreateSelectMain-sdCreateSelectMain)/1000)+" 秒");
}

function showName() {
	window.status = event.srcElement.innerText;
	return true;
}

// ==============================================================
// Select 对象通用函数,，结束=============================================
// ==============================================================

//在页面上产生 select checkbox radio 等元素。
function CreateFormOther(Arr,typeCode)
{
	try{

		try{
			PageStatus
		}catch (e){PageStatus="add"}

		var str="";
		//列选框
		if(Arr[0][0]=="select" || Arr[0][0]=="selectMultiple" || Arr[0][0]=="selectwin"){
			/*var CreateFormOtherDataArr=[
				//2007年10月11日 星期四 00:38:35 lybykw
				//=======================================
				//select 元素（自定义）参数
				//"元素类型","元素名称","是否有默认值","默认值的分隔字符","其它属性","宽度||高度","是否可输入","点击函数，同等onchange事件","html，如果有这项值，说明option显示为html代码（注：option值就应该是["1","<div>我是中国人</div>","我是中国人"]），为空或没有，就以一般的显示"
				//["select","selectInputName","5",",","ext_type='string'","168||300","1","onChangeFunction()","html"],
				//=======================================
				//selectMultiple 元素与 selectwin 元素
				//"元素类型","元素名称","是否有默认值","默认值的分隔字符","其它属性"
				//["select","selectInputName","5",",","ext_type='string'"],
				//=======================================
				["select","selectInputName","5",",","ext_type='string'","168||300","1","onChangeFunction()"],
				//元素值,文字,其它属性
				["1","中国"],
				["2","英国"]
			];*/
			if(PageStatus=="detail"){
				str="<input type='text' name='"+Arr[0][1]+"' value='";
				if(Arr[0][0]=="select"){
					if(Arr[0][2]==""){
						//DoChangeObjStatus("cs","");
						str+="";
					}else{
						for(var i=1;i<Arr.length;i++){
							if(Arr[0][2]==Arr[i][0]){
								//DoChangeObjStatus("cs",Arr[i][1]);
								if (Arr[0][8]=="html") {
									if (Arr[i][2]==undefined || Arr[i][2]=="") {
										str+=Arr[i][1];
									}
									else {
										str+=Arr[i][2];
									}
								}
								else {
									str+=Arr[i][1];
								}
								break;
							}
						}
					}
				}
				if(Arr[0][0]=="selectMultiple" || Arr[0][0]=="selectwin"){
					if(Arr[0][2]==""){
						//DoChangeObjStatus("cs","");
						str+="";
					}else{
						var dstr=[];
						if(Arr[0][5]=="" || Arr[0][5]==undefined)	Arr[0][5]=",";
						Arr2A=Arr[0][2].split(Arr[0][5])
						if(Arr2A.length>1){
							for(var i=1;i<Arr2A.length;i++)
							{
								for(var j=1;j<Arr.length;j++){
									if(Arr2A[i]==Arr[j][0]){
										dstr.push(Arr[j][1]);
										break;
									}
								}
							}
							//DoChangeObjStatus("cs",dstr);
							str+=dstr;
						}else{
							for(var i=1;i<Arr.length;i++){
								if(Arr[0][2]==Arr[i][0]){
									//DoChangeObjStatus("cs",Arr[i][1]);
									str+=Arr[i][1];
									break;
								}
							}
						}
					}
				}
				str+="'>";
			}
			else{
				if (Arr[0][0]=="select") {
					CreateSelect(Arr, typeCode);
					return;
				}
				else if(Arr[0][0]=="selectMultiple" || Arr[0][0]=="selectwin") {
					str+='<select name="'+Arr[0][1]+'" '+Arr[0][4]+'>';
					for(var i=1;i<Arr.length;i++){
						if(Arr[i]==""){
							str+="<option value=''>==========</option>";
						}
						else{
							str+='<option value="'+Arr[i][0]+'"';
							//if(PageStatus=="edit" || PageStatus=="add"){
								if(Arr[0][0]=="select" || Arr[0][0]=="selectwin"){
									if(Arr[0][2]==Arr[i][0])	str+=" selected";
								}else if(Arr[0][0]=="selectMultiple"){
									if(Arr[0][5]=="" || Arr[0][5]==undefined)	Arr[0][5]=",";
									var SvArr=Arr[0][2].split(Arr[0][5])
									for(var j=0;j<SvArr.length;j++){
										if(Arr[i][0]==SvArr[j])	str+=" selected";
									}
								}
							//}
							str+='>'+Arr[i][1]+'</option>';
						}
					}
					str+="</select>";
				}
			}
		}
		//复选框，单选框
		else if(Arr[0][0]=="checkbox" || Arr[0][0]=="radio"){
			/*var CreateFormOtherDataArr=[
				//元素类型,元素名称,默认值,默认值的分隔字符
				["radio","toelsef","0","",""],
				//元素值,文字,其它属性,是否能选择,0为不能选择。
				["1","是","onclick='coles();'","0"],
				["0","否","onclick='coles();'"]
			];*/
			if(PageStatus=="detail"){
				str="<input type='text' name='"+Arr[0][1]+"' value='";
				if(Arr[0][2]==""){
					//DoChangeObjStatus("cs","");
					str+="";
				}else{
					var dstr=[];
					if(Arr[0][3]=="")	Arr[0][3]=",";
					var Arr2A=Arr[0][2].split(Arr[0][3])
					if(Arr2A.length>1){
						for(var i=1;i<Arr2A.length;i++)
						{
							for(var j=1;j<Arr.length;j++){
								if(Arr2A[i]==Arr[j][0]){
									dstr.push(Arr[j][1]);
									break;
								}
							}
						}
						//DoChangeObjStatus("cs",dstr);
						str+=dstr;
					}else{
						for(var i=1;i<Arr.length;i++){
							if(Arr[0][2]==Arr[i][0]){
								//DoChangeObjStatus("cs",Arr[i][1]);
								str+=Arr[i][1];
								break;
							}
						}
					}
				}
				str+="'>";
			}
			else{
				for(var i=1;i<Arr.length;i++){
					if(Arr[i]==""){
						str+="<br>";
					}
					else{
						str+='<input type="'+Arr[0][0]+'" name="'+Arr[0][1]+'" value="'+Arr[i][0]+'" '+Arr[i][2];
						if(Arr[i][3]=="0")	str+=" disabled";
						if(Arr[0][3]=="" || Arr[0][3]==undefined)	Arr[0][3]=",";
						var Arr2A=Arr[0][2].split(Arr[0][3]);
						for(var j=0;j<Arr2A.length;j++)
						{
							if(Arr[i][0]==Arr2A[j]){
								str+=" checked";
							}
						}
						//str+=' id="'+(Arr[0][1]+i)+'"><label for="'+(Arr[0][1]+i)+'">'+Arr[i][1]+'</label> ';
						str+=' alt="'+Arr[i][1]+'">'+Arr[i][1]+' ';
					}
					
				}
			}
			
		}
		document.write(str);
	}
	catch (e){if(DEBUG){alert("在页面上产生 select checkbox radio 等元素 E:8452 "+e);}}
}

//检查值是否为数字
//s表示input 框的值，bag 表示可以包含的字符。2007年03月22日 星期四 10:18:39 lybykw
function isCharsNumberNext (s, bag)
{
	for (var i = 0; i < s.length; i++)
	{
		var c = s.charAt(i);
		if (bag.indexOf(c) == -1) return false;
	}
	return true;
}