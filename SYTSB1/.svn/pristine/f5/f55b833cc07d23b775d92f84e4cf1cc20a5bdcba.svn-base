<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.lsts.sinspection.bean.SupervisionInspection" %>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<% 
	String status = request.getParameter("status");
	SupervisionInspection supervisionInspection = (SupervisionInspection)request.getSession().getAttribute("supervisionInspection");
%>
<head>
<%@include file="/k/kui-base-form.jsp"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<script src="app/pub/office/js/editor.js" type="text/javascript"></script>
<script src="app/pub/seal/js/seal.js" type="text/javascript"></script>
<script type="text/javascript">
	var basepath = "${pageContext.request.contextPath}/";
	var toolBar;//工具条
	var beanData = api.data.bean;//父窗口的数据
	var bumfId=beanData == null?"": beanData.id;

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
		if("${param.status}"=="detail"){
			initView("view");
		}
	}
	
	function initToolBar(){
	    	var saveBtn;
	    	var closeBtn;
	 		saveBtn = {
	 					id : "save",
	 					text : "保存",
	 					icon:"save",
	 					click : function() {
	 						saveBumfDraft();
	 						return false;
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
			 //有保存
			if("${param.status}"!="detail"){	
				itemArr.push(saveBtn);	
			}
			itemArr.push(closeBtn);
			toolBar=$("#toolbar").ligerButton({
				items:itemArr
			});
	    }
	
	//加载正文
	function loadBumfDoc(){
		TANGER_OCX_OBJ.ToolBars=true;
		if (${empty param.docId}) {
			if(${param.type}==1){
				TANGER_OCX_OBJ.OpenFromURL("/app/sinspection/003锅壳式锅炉产品监督检验项目表.doc");
			}else if(${param.type}==2){
				TANGER_OCX_OBJ.OpenFromURL("/app/sinspection/004小型锅炉产品质量监督检验项目表.doc");
			}else if(${param.type}==3){
				TANGER_OCX_OBJ.OpenFromURL("/app/sinspection/006铸铁烘缸安全质量监督检验项目表.doc");
			}else if(${param.type}==4){
				TANGER_OCX_OBJ.OpenFromURL("/app/sinspection/007电站锅炉产品监督检验项目表.doc");
			}else if(${param.type}==5){
				TANGER_OCX_OBJ.OpenFromURL("/app/sinspection/009阀门产品安全性能监督检验项目表.doc");
			}else if(${param.type}==6){
				TANGER_OCX_OBJ.OpenFromURL("/app/sinspection/010安全阀产品安全性能监督检验项目表.doc");				
			}else if(${param.type}==7){
				TANGER_OCX_OBJ.OpenFromURL("/app/sinspection/011燃气调压装置（或部件）产品安全性能监督检验项目表.doc");				
			}else{
				TANGER_OCX_OBJ.OpenFromURL("/app/sinspection/templete.doc");
			}
		}else {
			TANGER_OCX_OBJ.OpenFromURL("/fileupload/downloadByObjId.do?id=${param.docId}");
			if("add" != "<%=status%>"){
				if(${param.type}==1){
					setBookMarkValue1("product_made_date","<%=supervisionInspection!=null?DateUtil.getDateTime("yyyy年MM月dd日", supervisionInspection.getProduct_made_date()):"" %>");
				}else if(${param.type}==2){
	
				}else if(${param.type}==3){
					setBookMarkValue1("product_type","<%=supervisionInspection!=null?supervisionInspection.getProduct_type():"" %>");
					setBookMarkValue1("product_pressure","<%=supervisionInspection!=null?supervisionInspection.getProduct_pressure():"" %>");
					setBookMarkValue1("product_temperature","<%=supervisionInspection!=null?supervisionInspection.getProduct_temperature():"" %>");
					setBookMarkValue1("product_medium","<%=supervisionInspection!=null?supervisionInspection.getProduct_medium():"" %>");
					setBookMarkValue1("product_main_material","<%=supervisionInspection!=null?supervisionInspection.getProduct_main_material():"" %>");
					setBookMarkValue1("product_wall_thickness","<%=supervisionInspection!=null?supervisionInspection.getProduct_wall_thickness():"" %>");
				}else if(${param.type}==4){
					setBookMarkValue1("product_made_date","<%=supervisionInspection!=null?DateUtil.getDateTime("yyyy年MM月dd日", supervisionInspection.getProduct_made_date()):"" %>");
				}else if(${param.type}==5){
					setBookMarkValue1("product_made_date","<%=supervisionInspection!=null?DateUtil.getDateTime("yyyy年MM月dd日", supervisionInspection.getProduct_made_date()):"" %>");
					setBookMarkValue1("product_pressure","<%=supervisionInspection!=null?supervisionInspection.getProduct_pressure():"" %>");
					setBookMarkValue1("product_diameter","<%=supervisionInspection!=null?supervisionInspection.getProduct_diameter():"" %>");
					setBookMarkValue1("product_temperature","<%=supervisionInspection!=null?supervisionInspection.getProduct_temperature():"" %>");
					setBookMarkValue1("product_medium","<%=supervisionInspection!=null?supervisionInspection.getProduct_medium():"" %>");
					setBookMarkValue1("product_valuebody_material","<%=supervisionInspection!=null?supervisionInspection.getProduct_valuebody_material():"" %>");
					setBookMarkValue1("product_welding_material","<%=supervisionInspection!=null?supervisionInspection.getProduct_welding_material():"" %>");
				}else if(${param.type}==6){
					setBookMarkValue1("product_made_date","<%=supervisionInspection!=null?DateUtil.getDateTime("yyyy年MM月dd日", supervisionInspection.getProduct_made_date()):"" %>");
					setBookMarkValue1("product_pressure","<%=supervisionInspection!=null?supervisionInspection.getProduct_pressure():"" %>");
					setBookMarkValue1("product_diameter","<%=supervisionInspection!=null?supervisionInspection.getProduct_diameter():"" %>");
					setBookMarkValue1("product_temperature","<%=supervisionInspection!=null?supervisionInspection.getProduct_temperature():"" %>");
					setBookMarkValue1("product_medium","<%=supervisionInspection!=null?supervisionInspection.getProduct_medium():"" %>");
					setBookMarkValue1("product_valuebody_material","<%=supervisionInspection!=null?supervisionInspection.getProduct_valuebody_material():"" %>");
					setBookMarkValue1("product_welding_material","<%=supervisionInspection!=null?supervisionInspection.getProduct_welding_material():"" %>");
				}else if(${param.type}==7){
					setBookMarkValue1("product_made_date","<%=supervisionInspection!=null?DateUtil.getDateTime("yyyy年MM月dd日", supervisionInspection.getProduct_made_date()):"" %>");
					setBookMarkValue1("product_pressure","<%=supervisionInspection!=null?supervisionInspection.getProduct_pressure():"" %>");
					setBookMarkValue1("product_temperature","<%=supervisionInspection!=null?supervisionInspection.getProduct_temperature():"" %>");
					setBookMarkValue1("product_medium","<%=supervisionInspection!=null?supervisionInspection.getProduct_medium():"" %>");
					setBookMarkValue1("product_main_material","<%=supervisionInspection!=null?supervisionInspection.getProduct_main_material():"" %>");
					setBookMarkValue1("product_welding_material","<%=supervisionInspection!=null?supervisionInspection.getProduct_welding_material():"" %>");
				}
				//String.fromCharCode(10)把换行符ascii码转换成字符串 13表示回车符
				setBookMarkValue1("made_unit_name","<%=supervisionInspection!=null?supervisionInspection.getMade_unit_name():"" %>");
				setBookMarkValue1("inspection_code","<%=supervisionInspection!=null?supervisionInspection.getInspection_code():"" %>");		
				setBookMarkValue1("product_name","<%=supervisionInspection!=null?supervisionInspection.getProduct_name():"" %>");
				setBookMarkValue1("product_model","<%=supervisionInspection!=null?supervisionInspection.getProduct_model():"" %>");
				setBookMarkValue1("product_code","<%=supervisionInspection!=null?supervisionInspection.getProduct_code():"" %>");
			}
		}
		//saved属性用来判断文档是否被修改过,文档打开的时候设置成ture,当文档被修改,自动被设置为false,该属性由office提供.
		//TANGER_OCX_OBJ.activeDocument.saved=true;
	}

	//保存正文isClose:是否关闭窗口并提示保存成功
	function saveBumfDraft(isClose) {
		//var isSave = TANGER_OCX_OBJ.activeDocument.saved;
		//if(isSave){
		//	alert("未填写任何内容，不能保存！");
		//	return;
		//}
		//正文已经存在时，先删除正文表，然后在上传
		var title = encodeURI(beanData.title);
		var response = TANGER_OCX_OBJ.SaveToURL("${pageContext.request.contextPath}/fileupload/fileUp.do?businessId="+bumfId+"&fileId=${param.docId}","docFile", "", title+".doc");
		var data = $.parseJSON(response);
		if(data.success){
			//将附件id返回到文档基本信息页面
			if ("${param.id}" == ""){
				api.data.pwindow.editorCallback(data.data.id);
			}
			top.$.notice("文件保存成功！");
			api.close();
		}else{
			$.ligerDialog.alert("文件保存失败！");
		}
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