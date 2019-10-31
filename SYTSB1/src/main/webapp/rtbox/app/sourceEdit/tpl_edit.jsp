<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%--单页面，引导方式--%>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<meta name="renderer" content="webkit"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<script src="k/kui/frame/jquery.min.js"></script>

<!--begin code mirror -->
<!--下面两个是使用Code Mirror必须引入的-->
<link rel="stylesheet" href="codemirror-5.31.0/lib/codemirror.css"/>
<script src="codemirror-5.31.0/lib/codemirror.js"></script>

<!--Java代码高亮必须引入-->
<script src="codemirror-5.31.0/clike.js"></script>

<!--groovy代码高亮-->
<script src="codemirror-5.31.0/mode/groovy/groovy.js"></script>

<!--引入css文件，用以支持主题-->
<link rel="stylesheet" href="codemirror-5.31.0/theme/dracula.css"/>
<link rel="stylesheet" href="codemirror-5.31.0/theme/eclipse.css"/>

<!--支持代码折叠-->
<link rel="stylesheet" href="codemirror-5.31.0/addon/fold/foldgutter.css"/>
<script src="codemirror-5.31.0/addon/fold/foldcode.js"></script>
<script src="codemirror-5.31.0/addon/fold/foldgutter.js"></script>
<script src="codemirror-5.31.0/addon/fold/brace-fold.js"></script>
<script src="codemirror-5.31.0/addon/fold/comment-fold.js"></script>

<!--括号匹配-->
<script src="codemirror-5.31.0/addon/edit/matchbrackets.js"></script>

<!--全屏模式-->
<link rel="stylesheet" href="codemirror-5.31.0/addon/display/fullscreen.css">
<script src="codemirror-5.31.0/addon/display/fullscreen.js"></script>

<!--自动补全-->
<link rel="stylesheet" href="codemirror-5.31.0/addon/hint/show-hint.css">
<script src="codemirror-5.31.0/addon/hint/show-hint.js"></script>
<script src="codemirror-5.31.0/addon/hint/anyword-hint.js"></script>

<!-- Search/Replace 功能 -->
<script src="codemirror-5.31.0/addon/search/search.js"></script>
<script src="codemirror-5.31.0/addon/search/searchcursor.js"></script>
<script src="codemirror-5.31.0/addon/search/jump-to-line.js"></script>
<script src="codemirror-5.31.0/addon/dialog/dialog.js"></script>
<link rel="stylesheet" href="codemirror-5.31.0/addon/dialog/dialog.css">
<link rel="stylesheet" href="codemirror-5.31.0/addon/search/matchesonscrollbar.css">

<!-- <script src="codemirror-5.31.0/lib/searchbox.js"></script> -->

<!--end Code Mirror -->

<head>
    <meta charset="utf-8"/>
    <title>代码框</title>
</head>
<body>
    <!-- begin code -->
    <textarea class="form-control" id="code" name="code"></textarea>
    <!-- end code-->
</div>

<script>
    //根据DOM元素的id构造出一个编辑器
    var editor = CodeMirror.fromTextArea(document.getElementById("code"), {
        mode: "text/groovy",    //实现groovy代码高亮
        mode: "text/x-java", //实现Java代码高亮
        lineNumbers: true,	//显示行号
        theme: "dracula",	//设置主题
        //theme:"eclipse",
        lineWrapping: true,	//代码折叠
        foldGutter: true,
        gutters: ["CodeMirror-linenumbers", "CodeMirror-foldgutter"],
        matchBrackets: true,	//括号匹配
        //readOnly: true,        //只读
        extraKeys:{
	       	"Ctrl-Space":"autocomplete",
	       	"Ctrl-S":function () {
				//parent.save();
				parent.saveFormContent();
            },
            "Shift-Alt-Enter": function (cm) {
             	cm.setOption("fullScreen", !cm.getOption("fullScreen"));
            }
        }
    });
    
    editor.setSize('100%', document.body.clientWidth);     //设置代码框的长宽

    editor.setValue("");    //先代码框的值清空
    //editor.setValue("<html></html>");    //给代码框赋值

    // editor.setOption("readOnly", true);
    
	$(function(){
		/* $.ajax({
			 type:"get",
			 dataType:"json",
			 data:{"filePath":"${param.filepath}"},
			 url:"rtbox/sourceEdit/ajaxRquest.do",
            success:function(data){
	           	 if(data.success){
	           		 editor.setValue(data["data"]);
	           	 }else{
	           		 alert("文件加载失败,请核对后再试！")
	           	 }
            }
		}); */
		$.ajax({
			 type:"get",
			 dataType:"json",
			 data:{"rtPageId":"${param.rtPageId}","pageCode":"${param.pageCode}"},
			 url:"com/rt/page/getPageContent.do",
            success:function(data){
	           	 if(data.success){
	           		 editor.setValue(data["data"]);
	           	 }else{
	           		 alert("文件加载失败,请核对后再试！")
	           	 }
            }
		});
    	
    });

	function getContent(){
		return editor.getValue();
	}
	
	function findInputLine(key,prev){
		CodeMirror.commands.search(editor,key,prev);
	}
	
	function findNext(prev){
		CodeMirror.commands.searchNext(editor,prev);
	}
	
</script>
</body>
</html>