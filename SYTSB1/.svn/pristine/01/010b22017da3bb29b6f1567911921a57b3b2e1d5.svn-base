var LC_HISTORY = new Array();

/**
 * 与后台方法通讯，获取图表XML
 * @param chartid 在配置中定义的图表ID
 * @param target 用于展现图表的DIV的id
 * @param params 参数（JSON格式）
 * @return
 * @throws Exception
 */
function RenderChart(chartid,target,params)
{
	var p = (params=="")?"{}":params;
	$.post("lchart/view/getChartXml.do",{chartid:chartid,param:p,rnd:Math.random()}, function(res) {
		if (res.success) {
			pushHisXml(chartid,res.data.xml);
			/*var chart = new FusionCharts("pub/chart/FusionCharts/Charts/"+res.data.type+".swf", chartid, "100%", "100%", "0", "1");
			chart.setDataXML(res.data.xml);
			chart.render(target);*/
			var chart = new FusionCharts({
				  "type": res.data.type,
				  "id": chartid,
				  "renderAt": target,
				  "width": "100%",
				  "height": "100%",
				  "dataFormat": "xml",
				  "dataSource": res.data.xml
				});
			  chart.render();
		}
		else
		{
			alert("数据处理错误");
		}
	});
}

/**
无下层图表数据后对外的接口，需要时覆盖此方法即可
*/
function LC_NoSubChart(chartid,params)
{
	var wObj = top.$.dialog({
		id: "chart_sub",
		icon: "k/kui/images/icons/dialog/32X32/i.png",
		width: 120,
		height: 80,
		parent: api,
		title: "系统提示",
		//cancel: true,
		//ok: false,
		content: '已经是最下层！'
	});
}

/**
无上层图表数据后对外的接口，需要时覆盖此方法即可
*/
function LC_NoTopChart(chartid)
{
	var wObj = top.$.dialog({
		id: "chart_top",
		icon: "k/kui/images/icons/dialog/32X32/i.png",
		width: 120,
		height: 80,
		parent: api,
		title: "系统提示",
		//cancel: true,
		//ok: false,
		content: '已经是最顶层！'
	});
}

/**
使用自定义按钮实现上钻时调用此方法
参数名：chartId(调用名称)
*/
function LC_GotoBack(cid)
{
	gotoBack(cid);
}

/**
点击图表后对外的接口，需要时覆盖此方法即可
参数名：chartId(调用名称)
*/
function LC_ClickChart(params)
{
	
}


function RefreshChart(chartid,params)
{
	$.post("lchart/view/getChartXml.do",{chartid:chartid,param:params}, function(res) {
		if (res.success) {
			if (res.data.xml!="0")
			{
				pushHisXml(chartid,res.data.xml);
				var chartRef = FusionCharts(chartid);
				chartRef.setDataXML(res.data.xml);
			}
			else
			{
				LC_NoSubChart(chartid,params);
				return;
			}
			//chartRef.render(target);
		}
		else
		{
			alert("数据处理错误");
			return;
		}
	});
}

function RefreshChartByXml(chartid,xml)
{
	var chartRef = FusionCharts(chartid);
	chartRef.setDataXML(xml);
}

function testAlert(p){
	//var ps = $.parseJSON(p);
	alert(p);
}

function gotoURL(p)
{
	var ps = p.split(",,");
	var url = ps[0];
	var linkType = ps[1];
	var param = ps[2];
	if (linkType=="_self")
	{
		window.location=url;
	}
	else if (linkType=="_top")
	{
		var pr = $.parseJSON(param);
		var w = (pr.width==undefined)? 600:pr.width;
		var h = (pr.height==undefined)? 400:pr.height;
		var t = (pr.title==undefined)?"":pr.title;
		
		var wObj = top.$.dialog({
			id: "chart_open",
			width: parseInt(w),
			height: parseInt(h),
			parent: api,
			title: t,
			//cancel: true,
			//ok: false,
			content: 'url:'+url
		});
	}
	else  //_blank
	{
		window.open(url,"blank");
	}
	LC_ClickChart(param);
}

function pushHisXml(cid,xml)
{
	try
	{
		var hisNum = -1;
		$.each(LC_HISTORY,function(i,n){
			if (n.cid==cid)
			{
				if (hisNum<parseInt(n.his))
				{
					hisNum = n.his;
				}
			}
		});	
		var hisObj = new LCHistory(cid,hisNum+1,xml);
		LC_HISTORY.push(hisObj);
	}
	catch(e)
	{
		alert(e);	
	}
}

function gotoCharts(p)
{
	var ps = p.split(",,");
	var chartid = ps[0];
	var cids = ps[1];
	var linkType = ps[2];
	var param = ps[3];
	
	if (linkType=="_top")
	{
		var pr = $.parseJSON(param);
		var charts = cids.split(",");
		var w = (pr.width==undefined)? 600:pr.width;
		var h = (pr.height==undefined)? 400:pr.height;
		var t = (pr.title==undefined)?"":pr.title;
		
		var wObj = top.$.dialog({
			//id: "chart_open",
			width: parseInt(w),
			height: parseInt(h),
			parent: api,
			title: t,
			max: false,
    		min: false,
			//cancel: true,
			//ok: false,
			content: "url:pub/chart/lchart_win.jsp?c="+charts[0]+"&p="+param
		});
	}
	else  //_selft _blank
	{
		var charts = cids.split(",");
		for (var i=0;i<charts.length;i++)
		{
			RefreshChart(charts[i],param);
		}
	}
	LC_ClickChart(param);
}

function gotoBack(cid)
{
	try
	{
		var xml = "";	
		var hisNum = -1;
		var hisIndex = 0;
		$.each(LC_HISTORY,function(i,n){
			if (n.cid==cid)
			{
				if (hisNum<parseInt(n.his)) 
				{
					hisNum = n.his;
					hisIndex = i;
				}
			}
		});
		if (hisNum>0)
		{
			LC_HISTORY.splice(hisIndex,1);
			hisNum = -1;
			$.each(LC_HISTORY,function(i,n){
				if (n.cid==cid)
				{
					if (hisNum<parseInt(n.his))
					{
						hisNum = n.his;
						xml = n.xml;
					}
				}
			});
			RefreshChartByXml(cid,xml);
		}
		else
		{
			LC_NoTopChart(cid);
		}
	}
	catch(e)
	{
		alert(e);	
	}
}

function LCHistory(cid,his,xml)
{
	this.cid = cid;
	this.his = his;
	this.xml = xml;
}
