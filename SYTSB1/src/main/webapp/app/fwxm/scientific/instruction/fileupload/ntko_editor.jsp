<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function(){
	    // 页面布局
		$(".layout").ligerLayout({
			space:0,
			bottomHeight:40,
			allowBottomResize: false
		});
	    //加载文档编辑器
		createNtkoEditor("editor","<sec:authentication property="principal.name" htmlEscape="false" />");
	    //加载文档内容
		if(api.data.fid){
            TANGER_OCX_OBJ.OpenFromURL($("base").attr("href") + "fileupload/download.do?id=" + api.data.fid);
        }
	    // ie环境下同步执行，非ie下异步执行
	    if(window["_browser_type"]=="IE"){
	    	onDocumentOpened();
	    }
	    initToolbar();
	});
	
    //  文件加载完成
	function onDocumentOpened(){
	    initNtkoEditorView();
	}
	
    function saveDocument() {
		var response = TANGER_OCX_OBJ.SaveToURL($("base").attr("href") + "fileupload/upload.do?businessId=" + api.data.busId 
				+ "&fileId=" + api.data.fid, "docFile", "",api.data.fname );
		if(window["_browser_type"]=="IE") _onSavedToUrl(response);
	}
    
    function _onSavedToUrl(response){
        var respJson = $.parseJSON(response);
        if(respJson.success){
            top.$.notice("文件已保存！",3);
        }
        else{
            alert("文件保存失败，请稍后再试！");
        }
	}
</script>
</head>
<body>
	<div class="layout">
		<div position="center" id="editor"></div>
		<div position="bottom" id="toolbar" class="toolbar-tm-bottom"></div>
	</div>
    <script type="text/javascript" src="pub/office/ntkoofficecontrol.js"></script>
	<script type="text/javascript" src="pub/office/editor.js"></script>
    <script type="text/javascript" src="pub/fileupload/js/ntko_editor.js"></script>
</body>
</html>