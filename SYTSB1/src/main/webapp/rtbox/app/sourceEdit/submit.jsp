<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
<title>结果页</title>  
    <!--SyntaxHighlighter的基本脚本-->  
    <script type="text/javascript" src="syntaxhighlighter/scripts/shCore.js"></script>  
    <!--SyntaxHighlighter的对各个编程语言解析的脚本-->  
    <script type="text/javascript" src="syntaxhighlighter/scripts/shBrushJScript.js"></script>  
    <script type="text/javascript" src="syntaxhighlighter/scripts/shBrushPhp.js"></script>  
    <script type="text/javascript" src="syntaxhighlighter/scripts/shBrushJava.js"></script>  
    <script type="text/javascript" src="syntaxhighlighter/scripts/shBrushCSharp.js"></script>  
    <script type="text/javascript" src="syntaxhighlighter/scripts/shBrushCpp.js"></script>  
    <script type="text/javascript" src="syntaxhighlighter/scripts/shBrushAS3.js"></script>  
    <script type="text/javascript" src="syntaxhighlighter/scripts/shBrushPython.js"></script>  
    <script type="text/javascript" src="syntaxhighlighter/scripts/shBrushVb.js"></script>  
    <script type="text/javascript" src="syntaxhighlighter/scripts/shBrushSql.js"></script>  
    <script type="text/javascript" src="syntaxhighlighter/scripts/shBrushXml.js"></script>  
    <script type="text/javascript" src="syntaxhighlighter/scripts/shBrushPlain.js"></script>  
    <!--所使用的SyntaxHighlighter样式-->  
    <link type="text/css" rel="stylesheet" href="syntaxhighlighter/styles/shCoreEclipse.css"/>  
    <!--初始化SyntaxHighlighter的必须代码，必须放在head中，引入文件之后-->  
    <script type="text/javascript">SyntaxHighlighter.all();</script>  
    <!--用于消除右上角的广告-->  
    <script type="text/javascript">SyntaxHighlighter.defaults['toolbar'] = false;</script>  
</head>  
  
<body>  
    <p><b>标题：</b>${param.title}</p>  
    <p><b>内容：</b>${param.content}</p>  
    <%out.print(request.getParameter("content")); %>
    <p><a href="demo.html">返回</a></p>  
</body>  
</html>