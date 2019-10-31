function loadingDom(){
	$.ajax({
		url: "/app/approve/listApproveItem.do",
		type: "GET",
		dataType:'json',
		success:function (data) {
			if (data.success) {
				var dataList = data.dataList;
				if(dataList.length > 0){
					unEsCape(dataList);
				}
			}
		},
		error:function () {
			alert("加载审批事项失败！");
		}
	});
}
function unEsCape(dataList){//解析数据
	//var loopI = 0;
	for(var i = 0; i<dataList.length;i++){
		var isInJS = appJsonData[dataList[i].code] ? true : false;//JS文件配置中能找到此项
		if(!isInJS) continue;
		var itemTitle = dataList[i].item;
		var divC = document.createElement("div");
		var jQueryObj = $(divC);
		jQueryObj.attr("id", dataList[i].id);
		jQueryObj.attr("class", "spbtndiv");
		//jQueryObj.attr("title",dataList[i].note);
		jQueryObj.attr("codename",dataList[i].code);
		jQueryObj.attr("itemtitle",itemTitle);
		jQueryObj.html("<div id='caption'><a href='javascript:void(0);' onclick='redirectReceivePage(\""+dataList[i].id+"\",\""+dataList[i].code+"\",\""+itemTitle+"\")'>"+itemTitle+"</a></div>"+"<div class='jjtext' title='"+dataList[i].note+"'>"+dataList[i].note+"</div>");
		$("#caption").mouseover(function(){
			this.style.color = "#00f";
		//this.style.background = "url(/app/approve/img/bj_3.jpg) repeat-x left top";
		});
		$("#caption").mouseout(function(){
			this.style.color = "#000";
		//this.style.background = "url(/app/approve/img/bj.jpg) repeat-x left top";
		});
		/*jQueryObj.click(function(){
	    redirectReceivePage(this.id,this.getAttribute("codename"),this.getAttribute("itemtitle"));
	});*/
		$("#contentDiv").append(jQueryObj);
	}
}
function redirectReceivePage(id,itemCode,itemTitle){
	itemTitle = escape(itemTitle).replace(/%/g,"\\");
	var $window = $(window);
	var width = $window.width(), height=$window.height();
	var dialogOption = "width="+870+",height="+height+",help=no";
	window.open("/app/approve/operation/operationContent.jsp?itemId="+id+"&itemCode="+itemCode+"&itemTitle="+itemTitle+"&pageStatus=add", "", dialogOption);
}

function queryOperation(){
	var $window = $(window);
	var height=$window.height();
	var dialogOption = "width="+830+",height="+height+",help=no";
	window.open("/app/approve/operation/queryOperation.jsp", "", dialogOption);
}

function nameCheck(type){//名称核准，type:1.社团;2.民办非
	var $window = $(window);
	var height=$window.height();
	var dialogOption = "width="+830+",height="+height+",help=no";
	window.open("/app/ngo/approval/itemDatumList.jsp?modelTypel="+type, "", dialogOption);
}

function queryApproval(){
	var $window = $(window);
	var height=$window.height();
	var dialogOption = "width="+830+",height="+height+",help=no";
	window.open("/app/ngo/approval/queryApproval.jsp", "", dialogOption);
}


