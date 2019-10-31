<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.lsts.sinspection.bean.SupervisionInspection" %>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<%@page import="util.MoneyUtil"%>
<%@page import="util.DateToChinese"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<%@include file="/k/kui-base-form.jsp"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<script src="app/pub/office/js/editor.js" type="text/javascript"></script>
<script type="text/javascript">
	var basepath = "${pageContext.request.contextPath}/";
	var toolBar;//工具条
	var beanData = api.data.bean;	// 维保基本信息
	var fleet_signPicture_url = api.data.fleet_signPicture_url;	    // 车队负责人签字
	var office_signPicture_url = api.data.office_signPicture_url;	// 办公室负责人签字
	var seal_signPicture_url = api.data.seal_signPicture_url;	    // 印章
	$(function(){
		var height = $(window).height()-$('.toolbar-tm').height();
		$("#scroll-tm").css({height:height});
	
		$(".layout").ligerLayout({
			bottomHeight:30,
			space : 0
		});
	});
	
	function initPage(){
		initToolBar();
		createNtkoEditor("editor_container");
		//远程加载文档
		loadBumfDoc();
		initDocView();
	}
	
	
	function initDocView(){
		if("${param.pageStatus}"=="detail"){
			initView("view");
		}
	}
	function initView(view){
		if(view=="view"){
			TANGER_OCX_OBJ.SetReadOnly(true);
			TANGER_OCX_OBJ.Statusbar = false;
			TANGER_OCX_OBJ.Toolbars = false;
		}
	}
	function initToolBar(){
		var printBtn;
		var closeBtn;
		printBtn={
			id: "print",
			text: "打印",
			icon:"print",
			click: function(){						
				doPrint();
				api.close();
				return true;
			}
		};
		closeBtn={
			id: "close",
			text: "关闭",
			icon:"close",
			click: function(){
				api.close();
				return true;
			}
		};
		var itemArr=new Array();
		itemArr.push(printBtn);
		itemArr.push(closeBtn);
		toolBar=$("#toolbar").ligerButton({
			items:itemArr
		});
	}
	//插入图片到指定书签位置
	function addPictureToDocument(position,url){
	  var adoc = TANGER_OCX_OBJ.ActiveDocument;
	  if (!adoc.BookMarks.Exists(position))
	      return;
	  adoc.BookMarks(position).Select();
	  if(position=="sealSignPicture"){
		  TANGER_OCX_OBJ.AddPicFromURL(
			      url,//URL 注意；URL必须返回Word支持的图片类型。
			      true,//是否浮动图片
			      0,//如果是浮动图片，相对于左边的Left 单位磅
			      0,//如果是浮动图片，相对于当前段落Top
			      1, //当前光标处
			      100,//无缩放
			      0 //文字上方
			  );
	  }else{
		  TANGER_OCX_OBJ.AddPicFromURL(
			      url,//URL 注意；URL必须返回Word支持的图片类型。
			      false,//是否浮动图片
			      0,//如果是浮动图片，相对于左边的Left 单位磅
			      0,//如果是浮动图片，相对于当前段落Top
			      1, //当前光标处
			      60,//缩放60%
			      1 //文字上方
			  );
	  }
	}
	//加载正文
	function loadBumfDoc(){
		TANGER_OCX_OBJ.ToolBars=true;
		if(beanData != null){	
			TANGER_OCX_OBJ.OpenFromURL("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/car/car_wb/pc/carWb20181212.doc"); // 维保申请单
			if(beanData.carUnit!=null){
				setBookMarkValue1("carUnit", beanData.carUnit);		// 车属单位
			}				
			if(beanData.carNum!=null){
				setBookMarkValue1("carNum", beanData.carNum);		// 车 牌 号
			}
			if(beanData.carBrand!=null){
				setBookMarkValue1("carBrand", beanData.carBrand);	// 品牌型号
			}
			if(beanData.engineNo!=null){
				setBookMarkValue1("engineNo", beanData.engineNo);	// 发动机号
			}				
			if(beanData.frameNo!=null){
				setBookMarkValue1("frameNo", beanData.frameNo);		// 车辆识别代号
			}
			if(beanData.applyUserName!=null){
				setBookMarkValue1("applyUserName", beanData.applyUserName);		// 送修人姓名
			}
			if(beanData.applyDate!=null){
				setBookMarkValue1("applyDate", beanData.applyDate.substr(0,4)+"年"+beanData.applyDate.substr(5,2)+"月"+beanData.applyDate.substr(8,2)+"日");				// 送修时间
				setBookMarkValue1("sealDateYear", beanData.applyDate.substr(0,4));				// 送修时间年
				setBookMarkValue1("sealDateMonth", beanData.applyDate.substr(5,2));				// 送修时间月
				setBookMarkValue1("sealDateDay", beanData.applyDate.substr(8,2));				// 送修时间日
			}
			if(beanData.contents!=null){
				setBookMarkValue1("contents", beanData.contents);				// 维修项目
			}
			if(typeof(fleet_signPicture_url)!="undefined"){
				addPictureToDocument("fleetSignPicture", fleet_signPicture_url);				// 车队负责人签字
			}
			if(typeof(office_signPicture_url)!="undefined"){
				addPictureToDocument("officeSignPicture", office_signPicture_url);			// 办公室负责人签字
			}
			if(typeof(seal_signPicture_url)!="undefined"){
				addPictureToDocument("sealSignPicture", seal_signPicture_url);				// 印章
			}
		}else{
			TANGER_OCX_OBJ.OpenFromURL("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/payment/templete.doc");
		}
		//saved属性用来判断文档是否被修改过,文档打开的时候设置成ture,当文档被修改,自动被设置为false,该属性由office提供.
		//TANGER_OCX_OBJ.activeDocument.saved=true;
	}

	function showBB(){
		$("#sssss").show();
	}
</script>
</head>
<body onload="initPage();">
<div class="scroll-tm" style="overflow: hidden" id="sssss"> 
<div class="layout">
    <div id="seal_container"></div>
	<div position="center" id="editor_container" style="width:100%;height:100%"></div>
	<div position="bottom" style="height: 50px;">
		<div class="div1" id="toolbar" style="padding:1px;text-align:right;"></div>
	</div>
</div>
<iframe id="export_doc_iframe" style="display:none;">
</iframe>
</div>
</body>
</html>