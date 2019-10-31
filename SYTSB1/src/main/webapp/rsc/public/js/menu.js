// 2009年05月22日 星期五 16:25:26 lybykw

var timeout	= 500;
var closetimer	= 0;
var ddmenuitem	= 0;
var ddmenubig = 0;

// open hidden layer
function mopenMain(Obj,id)
{
	// cancel close timer
	mcancelclosetime();
	// close old layer
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
	if (id!="") {
		ddmenuitem = document.getElementById(id);
		//ddmenuitem = id.parentNode.childNodes(1);
		ddmenuitem.style.visibility = 'visible';
	}
}

//2009年06月16日 星期二 16:07:32 lybykw
function mopen(Obj,id)
{
	mopenMain(Obj,id);
	if (ddmenubig) {
		ddmenubig.className="inv1";
	}
	Obj.className="inv1b";
	ddmenubig=Obj;
}

function mopen2(Obj,id)
{
	mopenMain(Obj,id);
	if (ddmenubig) {
		ddmenubig.className="inv1";
	}
}

function mclose()
{
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
	if (ddmenubig) {
		ddmenubig.className="inv1";
	}
}

function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}

function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}
document.onclick = mclose;