<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String realPath1 = request.getSession().getServletContext().getRealPath("upload");
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String now_date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
	System.out.print(">>>>>>>" + realPath1 + "<<<<<<");
	String[] id = realPath1.split("\\\\");
	String realPath = "";
	for (int i = 0; i < id.length; i++) {
		if (StringUtil.isNotEmpty(realPath)) {
			realPath = realPath + "/" + id[i];
		} else {
			realPath = id[0];
		}
	}
%>
<!--  -->
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

<base
	href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<%@include file="/k/kui-base-form.jsp"%>
<style type="text/css">
.l-icon-exportDoc {background: url('k/kui/skins/icons/word.gif') no-repeat center;}

.l-icon-printPreview {background: url('k/kui/skins/icons/search2.gif') no-repeat center;}

.l-icon-fullScreen {background: url('k/kui/skins/icons/div-drag.gif') no-repeat center;}
</style>
<script src="/pub/office/ntkoofficecontrol.js" type="text/javascript"></script>
<script src="pub/office/editor.js" type="text/javascript"></script>
<script type="text/javascript">
	var basepath = "${pageContext.request.contextPath}/";
	var toolBar;//工具条
	var device_sort="";
	var device_sort_code="";
	$(function() {
		$(".layout").ligerLayout({
			bottomHeight : 30,
			space : 0
		});
	});


	//文档标签赋值
	function setBookMarkValue1(bookMarkName, inputValue) {
		var bkmkObj = TANGER_OCX_OBJ.ActiveDocument.BookMarks(bookMarkName);
		if (!bkmkObj) {
			return;
		}
		var saverange = bkmkObj.Range;
		saverange.Text = inputValue;
		TANGER_OCX_OBJ.ActiveDocument.Bookmarks.Add(bookMarkName, saverange);
	}

	function initPage() {
		initToolBar();
		createNtkoEditor("editor_container");
		TANGER_OCX_OBJ.SetReadOnly(true);
		TANGER_OCX_OBJ.Menubar = false;
		TANGER_OCX_OBJ.Statusbar = false;
		TANGER_OCX_OBJ.Toolbars = false;
		var id="${param.id}";
		//TANGER_OCX_OBJ.OpenFromURL($("base").attr("href")+"fileupload/download.do?id="+id);
		TANGER_OCX_OBJ.OpenFromURL($("base").attr("href") + "fileupload/download.do?id=" + id);
		//TANGER_OCX_OBJ.OpenFromURL($("base").attr("href")+"fileupload/downloadByObjId.do?id="+id);		
		var doc = TANGER_OCX_OBJ.ActiveDocument;
		
	}

	function initToolBar() {
		var closeBtn;
		var printBtn;
		var printPreviewBtn;
		var fullScreenBtn;
		var downLoad;

		
		downLoad = {
				id : "down",
				text : "下载",
				icon : "detail",
				click : function() {
					var id="${param.id}";
					window.open($("base").attr("href")+"fileupload/download.do?id="+id).attr("src");
					return true;
				}
		};
		
		closeBtn = {
			id : "close",
			text : "关闭",
			icon : "close",
			click : function() {
				api.close();
				return true;
			}
		};

		printBtn = {
			id : "print",
			text : "打印",
			icon : "print",
			click : function() {
				doPrint();
				//savePrint();
			}
		};
		printPreviewBtn = {
			id : "printPreview",
			text : "打印预览",
			icon : "preview",
			click : function() {
				printPreview();
				return true;
			}
		};
		
		fullScreenBtn = {
			id : "fullScreen",
			text : "全屏",
			icon : "provide",
			click : function() {
				fullScreen();
				return true;
			}
		};
		 
		var itemArr = new Array();
		itemArr.push(printBtn);
		itemArr.push(fullScreenBtn);
		itemArr.push(printPreviewBtn);
		itemArr.push(closeBtn);
		itemArr.push(downLoad);
		toolBar = $("#toolbar").ligerButton({
			items : itemArr
		});
	}

</script>
</head>
<body onload="initPage();">
	<div class="layout">
		<div position="center" id="editor_container"
			style="width: 100%; height: 100%"></div>
		<div position="bottom" style="height: 50px;">
			<div class="div1" id="toolbar"
				style="padding: 1px; text-align: right;"></div>
		</div>
	</div>
	<iframe id="export_doc_iframe" style="display: none;"> </iframe>
</body>
</html>