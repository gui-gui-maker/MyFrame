<%--
此页面为公文内容编辑，页面所有的参数如下：
1、${param.status}：标识编辑状态
2、${param.id}: 发文id
3、${param.fid}: 正文id
4、api.data.title: 文件标题，用作上传文件保存时的文件名，这是js接口参数
5、api.data.callback: 回调函数，用作保存文件后将文件id回传给发文表单
6、api.data.tbar: 页面工具按钮，由调用者提供，在不同的状态下动态生成功能按钮
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
	    
	    //加载pdf插件
		//addPdfPlugin();
	    
	    //加载文档内容
	    loadWordDoc();
	    
	    // ie环境下同步执行，非ie下异步执行
	    if(window["_browser_type"]=="IE"){
	    	onDocumentOpened();
	    }
	    //加载工具栏动态按钮
	    initToolbar(api.data.tbar);
	    //检查是否需要执行命令，这是在用户通过基础信息页面直接进入盖章、套红等编辑器命令。
	    if(api.data.dofun){
	    	pageCommondCall[api.data.dofun]();
	    }
	});
	
	//  文件加载完成
	function onDocumentOpened(){
		//设置显示模式
        try{
            initView(api.data.status);
        }catch(e){
            //alert("initView error："+e);
        }
	}
	//加载正文
    function loadWordDoc(){
    	 if(api.data.type=="detail"||api.data.type=="modify"){
    		 try{
    			 if(api.data.doc=="1"){
    				 TANGER_OCX_OBJ.OpenFromURL($("base").attr("href") + "contractAction/getFile.do?id=" + api.data.id);
    			 }else{
    				 TANGER_OCX_OBJ.OpenFromURL($("base").attr("href") + "fileupload/downloadByFilePath.do?path="+api.data.docId);
    			 }
    		
	    	 }catch (e) {
	    		 if(api.data.type=="modify"){
	    			 if (confirm("没有保存对应文档，是否现在编辑？")){
	    				 TANGER_OCX_OBJ.OpenFromURL($("base").attr("href") + "fileupload/downloadByFilePath.do?path="+api.data.docId);
	    			 }
	    		 }else{
		    		 alert("没有保存对应文档,请修改编辑！");
		    		 api.close();
	    		 }
			}
         }else if (api.data.pdf) {
             TANGER_OCX_OBJ.BeginOpenFromURL($("base").attr("href") + "fileupload/download.do?id=" + api.data.pdf);
         }
         else if(api.data.type=="add"){
        	//新文件，从模板加载，使用低版本的office文件格式
            TANGER_OCX_OBJ.OpenFromURL($("base").attr("href") + "fileupload/downloadByFilePath.do?path="+api.data.docId);
         	
        }
    }
	
	function saveCurrentDocument() {
		var response = TANGER_OCX_OBJ.SaveToURL($("base").attr("href") + "contractAction/saveO.do?"
			+ "fileId=" + api.data.fid+"&attType=1&documentName="+api.data.title+"&documentId="+api.data.docId+"&id="+ api.data.id, "docFile", "", api.data.title + ".doc"); 		
	    if(window["_browser_type"]=="IE"){ _onSavedToUrl(response);
	    }
	    api.close();
	}
	
	function _onSavedToUrl(response){
        var respJson = $.parseJSON(response);
        if(respJson.success){
            $(window).data("content");

           //api.data.callback(respJson.id,respJson.orderName);
           top.$.notice("保存成功！");
        }
        else{
            alert("文件保存失败，请稍后再试！");
        }
	}
	
	function _saveAsPdf(){
		//客户端利用控件转PDF
        TANGER_OCX_OBJ.PublishAsPDFToURL($("base").attr("href") + "fileupload/upload.do?businessId=" + api.data.id +"&fileId=" + api.data.pdf, "pdfFile", "fileName=" + api.data.title + ".pdf",
                api.data.title + ".pdf", null, null, true, false, true, "kh,123$%^&*8848", true, false);
        //保存pdf文件结束后执行触发方法 AfterPublishAsPDFToURL 
	}
	
	function _onPublishAdPdfToUrl(ret){
		if(!$.kh.isNull(ret)){
		    var rjson = $.parseJSON(ret);
		    if(rjson.success && rjson.data.id)
                api.data.callback($(window).data("content"),rjson.data.id);
		    else{
		        //pdf保存不成功，只返回word
		        api.data.callback($(window).data("content"),"");
		    }
		}else{
		    //pdf保存不成功，只返回word
		    api.data.callback($(window).data("content"),"");
		}
        api.close();
	}
</script>
<script type="text/javascript" for="TANGER_OCX" event="AfterPublishAsPDFToURL(ret,code)">
_onPublishAdPdfToUrl(ret);
</script>
</head>
<body>
	<div class="layout">
		<div position="center" id="editor"></div>
		<div position="bottom" id="toolbar" class="toolbar-tm-bottom"></div>
	</div>
     <script type="text/javascript" src="app/humanresources/js/ntkoofficecontrol.js"></script> 
	<script type="text/javascript" src="app/pub/office/js/editor.js"></script>
	<script type="text/javascript" src="app/humanresources/js/_doc_editor.js"></script>
</body>
</html>