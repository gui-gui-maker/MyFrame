<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<%@include file="/k/kui-base-form.jsp" %>

<script src="oa/js/editor.js" type="text/javascript"></script>
<style type="text/css">
body {
	overflow:hidden;
	padding:0;
	margin:0;
}

.i_button {
	border:solid 1px #0e6490;
	padding:5px 2px 2px 2px;
	background:url(k/kui/skins/Aqua/images/controls/nomarbj.png);
	text-align:center;
	color:#fff;
	font-weight:bold;
	text-decoration:none;
	cursor:pointer;
}

.toolbar {
	background:url('k/kui/skins/Aqua/images/panel/panel-toolbar.gif') #CEDFEF repeat-x;
	border:1px #9CBAE7 solid;
	overflow:hidden;
	padding:5px;
	border-bottom:0px;
	height:40px;
}
</style>
<script type="text/javascript">
var api = frameElement.api;
var beanData = api.data.bean;
//初始化
function init() {

	//远程加载文档
	loadRemoteDoc();
	load();
	var id = "";
	if (id == null || id == "") {
		TANGER_OCX_OBJ.Toolbars = false;
		TANGER_OCX_OBJ.FileSave = false;
	}
}

//远程加载文档
function loadRemoteDoc() {
	try {
		TANGER_OCX_OBJ.OpenFromURL("/retire/document/retire_obituary.doc");
	}
	catch (err) {
		alert("打开文件错误，请检查！");
	}
}

function load() {
	var id = "";
	$.post(
		"/retire/formwork/obituary.do",
		{"id": id},
		function (data) {
			setBookMarkValue(data.data.retirePerson.name, "name");
			setBookMarkValue(data.data.retirePerson.name, "name1");
			setBookMarkValue(data.data.retirePerson.name, "name2");
			setBookMarkValue(data.data.retirePerson.name, "name3");
			setBookMarkValue(data.data.deathTime, "death_time");
			setBookMarkValue(data.data.age, "age");
			setBookMarkValue(data.data.bornTime, "born_date");
			setBookMarkValue(data.data.joinTime, "join_time");
			setBookMarkValue(data.data.retirePerson.nativePlace, "native_place");
			setBookMarkValue(data.data.retirePerson.retire, "retire");
			setBookMarkValue(data.data.retirePerson.retire, "retire1");
			setBookMarkValue(data.data.retireTime, "retire_time");
			setBookMarkValue(data.data.retirePerson.retireChildUnit.name, "u_name");
			setBookMarkValue(data.data.workList, "work_list");
			setBookMarkValue(data.data.funeralTime, "funeral_time");
			setBookMarkValue(data.data.sysTime, "sys_time")
		})

}

// 设置文字到书签位置
function setBookMarkValue(inputValue, BookMarkName) {
	var bkmkObj = TANGER_OCX_OBJ.ActiveDocument.BookMarks(BookMarkName);
	if (!bkmkObj)
		return;
	var saverange = bkmkObj.Range;
	saverange.Text = inputValue;
	TANGER_OCX_OBJ.ActiveDocument.Bookmarks.Add(BookMarkName, saverange);
}
</script>
</head>
<body onload="init()">
	<object id="TANGER_OCX_OBJ" classid="clsid:01DFB4B4-0E07-4e3f-8B7A-98FD6BFF153F"
			codebase="oa/plugin/OfficeControl.cab#version=5,0,1,0" width="100%" height="95%">
		<param name="BorderStyle" value="1"> 
		<param name="Titlebar" value="0">
		<param name="StatusBar" value="-1">
		<param name="BorderStyle" value="0"> 
		<param name="TitlebarColor" value="42768"> 
		<param name="TitlebarTextColor" value="0">
		<param name="MenubarColor" value="14402205"> 
		<param name="BorderColor" value="14402205">
		<PARAM NAME="MenuButtonColor" VALUE="16180947"> 
		<param name="MenuBarStyle" value="3"> 
		<param name="MenuButtonStyle" value="7"> 
		<param name="FilePrint" value="-1">
		<param name="FileSave" value="-1"> 
		<param name="FileSaveAs" value="-1">
		<param name="MakerCaption" value="成都川大科鸿新技术研究所">
		<param name="MakerKey" value="5CF5FD7E2FCEE83E94BC681C4162AD366621F605"> 
		<param name="ProductCaption" value="川大科鸿OA"> 
		<param name="ProductKey" value="01DB7CC2E552800DCDD79527D8FD0BD3D07F3028">
		<span style="color:red">不能装载文档控件。请在检查浏览器的选项中检查浏览器的安全设置。</span> 
    </object>
	<div class="toolbar">
	
		<input type="button" class="i_button" name="printset" value="打印" onclick="doPrint()">
		<input type="button" class="i_button" name="print" value="打印预览" onclick="printPreview()">
		<input type="button" class="i_button" name="print" value="页面设置" onclick="setLayout()">
		
		
		<input type="button" class="i_button" name="close" value="关 闭" onclick="api.close()"/>
		
	</div>
</body>
</html>