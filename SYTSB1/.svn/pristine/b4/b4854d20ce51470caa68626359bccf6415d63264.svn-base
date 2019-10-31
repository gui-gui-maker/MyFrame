
//alert(navigator.userAgent)

/*var offimgArray=new Array();
offimgArray[0]=new Image();
offimgArray[0].src="/public/skin/default/images/navigation/n_01_01.gif";
offimgArray[1]=new Image();
offimgArray[1].src="/public/skin/default/images/navigation/n_01_02.gif";
offimgArray[2]=new Image();
offimgArray[2].src="/public/skin/default/images/navigation/n_01_03.gif";
offimgArray[3]=new Image();
offimgArray[3].src="/public/skin/default/images/navigation/n_01_04.gif";
offimgArray[4]=new Image();
offimgArray[4].src="/public/skin/default/images/navigation/n_01_05.gif";
offimgArray[5]=new Image();
offimgArray[5].src="/public/skin/default/images/navigation/n_01_06.gif";
offimgArray[6]=new Image();
offimgArray[6].src="/public/skin/default/images/navigation/n_01_07.gif";
offimgArray[7]=new Image();
offimgArray[7].src="/public/skin/default/images/navigation/n_01_08.gif";

var onimgArray=new Array();
onimgArray[0]=new Image();
onimgArray[0].src="/public/skin/default/images/navigation/n_02_01.gif";
onimgArray[1]=new Image();
onimgArray[1].src="/public/skin/default/images/navigation/n_02_02.gif";
onimgArray[2]=new Image();
onimgArray[2].src="/public/skin/default/images/navigation/n_02_03.gif";
onimgArray[3]=new Image();
onimgArray[3].src="/public/skin/default/images/navigation/n_02_04.gif";
onimgArray[4]=new Image();
onimgArray[4].src="/public/skin/default/images/navigation/n_02_05.gif";
onimgArray[5]=new Image();
onimgArray[5].src="/public/skin/default/images/navigation/n_02_06.gif";
onimgArray[6]=new Image();
onimgArray[6].src="/public/skin/default/images/navigation/n_02_07.gif";
onimgArray[7]=new Image();
onimgArray[7].src="/public/skin/default/images/navigation/n_02_08.gif";
*/

//png 透明执行
document.write('<!--[if IE 6]><script src="/public/js/DD_belatedPNG.js"></script><![endif]-->');
$(function(){//jQuery页面载入事件
	if ($.browser.msie && parseFloat($.browser.version) <=6) {//浏览器检测 2012年06月19日 星期二 14:04:54 lybide
		try{document.execCommand("BackgroundImageCache",false,true);}catch(e){}//ie6不缓存背景图片
		DD_belatedPNG.fix("*");
	}
});

//---------------应用函数---------------

function offimg(i,Obj)
{
	Obj.src=offimgArray[i].src;
}

function onimg(i,Obj)
{
	Obj.src=onimgArray[i].src;
}

/*======================================*/


function buttonDisabled(Obj)
{
	if(!Obj) return;
	if(Obj.length)
	{
		for(var i=0;i<Obj.length;i++)
		{
			Obj[i].disabled=true;
		}
	}
	else
	{
		Obj.disabled=true;
		//alert("只有一个提交框");
	}
}

//锁定提交按钮
//2006年11月30日 星期四 9:14:46 lybykw
function SubDisabled(Obj)
{
	buttonDisabled(Obj)
}

function doSubmitCKFS(ObjectElement)
{
	for(i=0;i<ObjectElement.elements.length;i++)
	{
		var eoNeeds=ObjectElement.elements[i].getAttribute("needs");
		if(eoNeeds)
		{
			if(ObjectElement.elements[i].value=="")
			{
				alert("请输入"+eoNeeds+"！");
				ObjectElement.elements[i].focus();
				return false;
			}
		}
	}
	return true;
}

//检查Email地址
function CheckEmail(Obj){
	var sReg = /[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/;
	if ( ! sReg.test(Obj.value) )
	{
		alert("电子邮件地址不正确！请重新输入。");
		Obj.focus();
		return false;
	}
	return true;
}

//检查电话号码：允许仅包括数字、减号和小括号，且长度小于等于15位
function CheckMobile(Obj){
	if ( Obj.value.length <= 15 && Obj.value.length >= 7 )
	{
		var sReg = /[^\d-\(\)]{1}/;
		var sReg1 = /^13[0-9]{1}\d{8}$/;
		var sReg2 = /[-\(\)]{1}/;
		var HasError = false;
		if (sReg.test(Obj.value))
		{
			HasError = true;
			alert("电话号码不能包含除数字，小括号，减号以外字符！");
			Obj.focus();
			return false;
		}
		else
		{
			if ( Obj.value.length == 11 && !sReg1.test(Obj.value) && !sReg2.test(Obj.value)  )
			{
				HasError = true;
				alert("您输入的手机号码有误！\n如果是非手机号码，请在区号和号码间加短横线“-”。");
				Obj.focus();
				return false;
			}
			if ( (Obj.value.length == 12 || Obj.value.length == 10) && !sReg2.test(Obj.value) )
			{
				HasError = true;
				alert("您输入的如果是手机号码，则位数有误，必须为11位！\n如果是非手机号码，请在区号和号码间加短横线“-”。");
				Obj.focus();
				return false;
			}
		}
	}
	else
	{
		HasError = true;
		alert("电话号码必须是小于等于15位且大于等于8位！");
		Obj.focus();
		return false;
	}
	if ( HasError )
	{
		return false;
	}
	return true;
}

//检查姓名，不允许包括空格及数字
function CheckTrueName(Obj){
	//姓名中不允许数字或空格
	var sReg = /\d|\s/;
	if ( sReg.test(Obj.value) )
	{
		alert("真实姓名不允许包含空格或数字！");
		Obj.focus();
		return false;
	}
	//姓名中不允许英文字母
	var sReg = /[a-zA-Z]/;
	if ( sReg.test(Obj.value) )
	{
		alert("真实姓名不允许包含英文字母！\n如果您身份证上姓名不是中文，请电话和我们联系。");
		Obj.focus();
		return false;
	}
	if ( Obj.value.length > 4 )
	{
		alert("姓名太长！");
		Obj.focus();
		return false;
	}
	return true;
}

//检查长度，不允许超过 numberLength
function CheckInputLength(Obj,alertStr,numberLength){
	if(Obj.value.length>numberLength){
		alert(alertStr+"的长度不能超过"+numberLength+"个字符，您现在"+alertStr+"的长度为："+Obj.value.length+"个字符！");
		Obj.focus()
		return false;
	}
	return true;
}

//检查邮政编码
function CheckTruePostalcode(Obj)
{
	if(/^[0-9]{6,6}$/.test(Obj.value)==false)
	{
		alert("邮政编码只能六位数字");
		Obj.focus();
		return false;
	}
	return true;
}

//检查数字，不允许包含非数字内容
function isMoney(Obj,Astr)
{
	strRef="1234567890.";
	for(i=0;i<Obj.value.length;i++)
	{
		tempChar=Obj.value.substring(i,i+1);
		if (strRef.indexOf(tempChar,0)==-1)
		{
			alert(Astr+"不正确，请检查");
			Obj.focus();
			return false;
		}
		else
		{
			tempLen=Obj.value.indexOf(".");
			if(tempLen!=-1)
			{
				strLen=Obj.value.substring(tempLen+1,Obj.value.length);
				if(arguments[1]!="")
				{
					if(strLen.length>arguments[1])
					{
						alert(Astr+"不正确，请检查");
						Obj.focus();
						return false;
					}
				}
				else
				{
					if(strLen.length>2)
					{
						alert(Astr+"不正确，请检查");
						Obj.focus();
						return false;
					}
				}
			}
		}
	}
	return true;
}

//检查值是否为数字
function isCharsNumberNext (s, bag)
{
	for (var i = 0; i < s.length; i++)
	{
		var c = s.charAt(i);
		if (bag.indexOf(c) == -1) return false;
	}
	return true;
}

function PageBack()
{
	history.back(-1);
}

//创建SWF文件
//2009年10月25日 星期日 23:57:00 lybide
function showSwf(Obj)
{
	var Obj=$.extend(true,{returns:false,width:500,height:300,id:"flashObj1",url:"",param:[{wmode:"transparent"},{quality:"High"},{menu:"false"}]},Obj)
	var str="";
	str+='<object classid="clsid:D27CDB6E-AE6D-11CF-96B8-444553540000" id="'+Obj.id+'" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,0,0,0" border="0" width="'+Obj.width+'" height="'+Obj.height+'">';
		str+='<param name="movie" value="'+Obj.url+'">';
		for (var i = 0; i < Obj.param.length; i++) {
			var bo=Obj.param[i];
			for (os in bo) {//alert(os+"="+bo[os]);
				str+='<param name="'+os+'" value="'+bo[os]+'">';
			}	
		}
		str+='<embed src="'+Obj.url+'" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" name="'+Obj.id+'" width="'+Obj.width+'" height="'+Obj.height+'" quality="High" wmode="transparent">';
		str+='</object>';
	//alert(str)
	if (Obj.returns) {
		return str;
	} else {
		document.write(str);
	}
}


function siteSearchDoSubmit(obj) {
	
}

//2009年05月21日 星期四 15:46:32 lybykw
//变换搜索框
function searchChange(Obj,keys)
{
	if (keys==1) {
		if (Obj.value=="请输入关键字进行全站搜索") {
			Obj.value="";
			$(Obj).addClass("ipt_search2_b");
		}
	}
	else if (keys==2) {
		if (Obj.value=="") {
			Obj.value="请输入关键字进行全站搜索";
			$(Obj).removeClass("ipt_search2_b");
		}
	}
}

//通用网页对话框。
function SystemMsg(Str)
{
	Str = "" + Str;
	alert(Str);
}

/*关闭，返回上一页
function closeBt()
{
	document.write(' <div class="bt_close_d1" style="">[ <a href="#NFSGO_COM" onclick="window.close();">关闭</a> ]　[ <a href="#NFSGO_COM" onclick="window.history.back();">返回上一页</a> ]　[ <a href="/">返回首页</a> ]　[ <a href="#top" >返回顶部</a> ]</div>');
}*/
//关闭，返回上一页  Monday, 2012-10-08 11:03 AM 修改
function closeBt()
{
	document.write(' <div class="bt_close_d1" style="">[ <a href="#NFSGO_COM" onclick="window.close();">关闭</a> ]　[ <a href="/">返回首页</a> ]　[ <a href="#top" >返回顶部</a> ]</div>');
}


//jQuery页面载入事件
//2012年09月12日 星期三 17:19:47 lybide
$(function(){
	$("#mMenu1 > div").hover(
		function () {
			$(this).find(".menu2").show("fast");
			$(this).find("a:first").addClass("current");
		},
		function () {
			$(this).find(".menu2").hide();
			$(this).find("a:first").removeClass("current");
		}
	);
});

function ad1()
{
	//window.prompt("dfsdfsdfsdf", bigTypeKeysId);
	var equlsStr=bigTypeKeysId;
	var f1="";
	if (equlsStr=="onlineRefer" || equlsStr=="job" || equlsStr=="siteSearch" || equlsStr=="site_map" || equlsStr=="friendLink" || equlsStr=="") {
		f1="100";
	}
	else {
		f1=equlsStr;
	}
	var str="";
	str+='<div class="i_ad2">';
		str+='<div id="flashContent2"><!--<object classid="clsid:D27CDB6E-AE6D-11CF-96B8-444553540000" id="obj1" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0" border="0" width="967" height="179">';
		str+='	<param name="movie" value="/public/flash/index_'+f1+'.swf">';
		str+='	<param name="wmode" value="opaque">';
		str+='	<param name="quality" value="High">';
		str+='	<param name="menu" value="false">';
		str+='	<embed src="/public/flash/index_'+f1+'.swf" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" name="obj1" width="967" height="179" quality="High" wmode="transparent">';
		str+='</object>--></div>';
	str+='</div>';
	document.write(str);
	swfobject.embedSWF("/public/flash/index_"+f1+".swf", "flashContent2", "967", "179", "9.0.0",null,{rd:"",b_login:0,width1:$(window).width(),height1:$(window).height()},{wmode:"transparent",menu:"false",allowScriptAccess:"sameDomain"},{id:"dynamicContent5",name:"dynamicContent5"},function(){
		var fhtml=document.getElementById("flashContent2");
		if (fhtml) {
			//alert("提示：您还没有安装flash插件。")
			$(fhtml).html('<img src="/public/flash/img2013/index_'+f1+'.jpg" border="0" alt="" />');
		} else {
			
		}
	});
}

function ad2()
{
	var str="";
	str+="<div>&nbsp;</div>";
	document.write(str);
}
//ad1();

function ShowTabs(tabTitle,tabContent,tabNo,tabCount,overTabClass,outTabClass,moreObj){
    for(i=0;i<=tabCount;i++){
        if(document.getElementById(tabContent+i) == null)
            continue;
        if(i==tabNo){
            document.getElementById(tabContent+i).style.display='block';
            document.getElementById(tabTitle+i).className = overTabClass;
        }
        else{
            document.getElementById(tabContent+i).style.display='none';
            document.getElementById(tabTitle+i).className = outTabClass;
        }
    }
	if (moreObj!=undefined) {//2010年11月08日 星期一 16:27:07 lybide
		var moreId=moreObj.id;
		var moreHref=moreObj.href;
		document.getElementById(moreId).setAttribute("href",moreHref);
	}
}