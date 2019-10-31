<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%--单页面，引导方式--%>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<meta name="renderer" content="webkit"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<script src="k/kui/frame/jquery.min.js"></script>
<!-- <script src="app/tools/lhgdialog/lhgcore.lhgdialog.min.js"></script> -->

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	var searchKey;
	function preview(){
		var content = document.getElementById("code").contentWindow.getContent();
		/* $.post("rtbox/sourceEdit/preview.do",{"filepath":"${param.filepath}","fileContent":content},function(data){
			if(data.success){
				document.getElementById("content").src="rtbox/sourceEdit/preview.do?filepath=${param.filepath}";
			}else{
				alert("预览失败！请检查服务器代码！");
			}
		}); */
		$("#fileContent").val(content);
		$("#preview").submit();
	}
	
	// 原保存到文件
	function save(){
		var content = document.getElementById("code").contentWindow.getContent();
		$.post("rtbox/sourceEdit/save.do",{"filePath":"${param.filepath}","fileContent":content},function(data){
			var timer;
			var msg;
			if(data.success){
				document.getElementById("content").src="${param.filepath}";
				msg='保存成功！';
			}else{
				msg='保存失败！';
			}
			
			$.dialog({
			    content: content,
			    init: function () {
			        var that = this, i = 2;
			        var fn = function () {
			            that.title(i + '秒后关闭');
			            !i && that.close();
			            i --;
			        };
			        timer = setInterval(fn, 1000);
			        fn();
			        
			        // 保存成功后重新预览
			        $("#fileContent").val(content);
					$("#preview").submit();
			    },
			    close: function () {
			        clearInterval(timer);
			    }
			});
		});
	}
	
	// 现保存到数据库
    function saveFormContent(){
        var content = document.getElementById("code").contentWindow.getContent();
        console.log(content)
        $.post("/com/rt/page/saveIndexChangeReport.do",
        		{'path':"",'content':content,"rtboxId":parent.rtboxId,'pageCode':'${param.pageCode}','pageName':'${param.pageName}'},function(res){
            if(res.success){
            	top.$.notice("保存成功！",2);
            }else{
                alert("保存失败");
            }
        })
    }
	
	function findInputLine(key,prev){
		document.getElementById("code").contentWindow.findInputLine(key,prev);
	}
	
	function findNext(prev){
		//var key = document.getElementById("code").contentWindow.getlastQuerykey();
		document.getElementById("code").contentWindow.findNext(prev);
	}
	
	function downloadFile(){
		document.getElementById("download").src="rtbox/sourceEdit/download.do?filePath=${param.filepath}";
	}
	
	function onDocKeydown(e){
	    e = e||window.event;
	    if( e.ctrlKey && e.keyCode==83 ){
	    	save();
	    	return false;
		}
	}
	document.onkeydown = onDocKeydown;
	
	function hideLeftIfr(){
		var left = document.getElementById('left').style.display;
		if(left==null || left=='none'){
            document.getElementById('left').style.display='block';
	    }else{
	         document.getElementById('left').style.display='none';
	    }
	}
</script>

<!-- 模版页面预览时注入
<script type="text/javascript">
	$(function(){
		$("input").focus(function(){
			if(this.id) parent.find(this.id,false);
			if(this.name) parent.find(this.name,false);
		});
	});
	function onDocKeydown(e){
	    e = e||window.event;
	    if( e.ctrlKey && e.keyCode==83 ){
	    	parent.save();
	    	return false;
		}
	}
	document.onkeydown = onDocKeydown;
</script>
 -->
<body>
<table class="result" width="100%" height="100%" cellpadding="0" cellspacing="0">
	<!-- <tr>
		<td colspan="3">
			<div id="toolbar">
				<button type="button" onclick="preview();">预览</button>
				<button type="button" onclick="save();">保存</button>
				<button type="button" onclick="downloadFile();">下载</button>
				<button type="button" onclick="findNext(false);">向下查找</button>
				<button type="button" onclick="findNext(true);">向上查找</button>
			</div>
		</td>
	</tr> -->
	<tr>
    	<td width="49%" id="left">
	        <iframe name="code" id="code" frameborder="0" scrolling="no" width="100%" height="100%"
	        	src="rtbox/app/sourceEdit/tpl_edit.jsp?rtPageId=${param.rtPageId}&pageCode=${param.pageCode}"
	        	onload="this.height=this.contentWindow.document.documentElement.scrollHeight" >
	            <script type="text/javascript">   
	            //动态获取被引用的页面的高度
	            function reinitIframe1(){       
	                var iframe = document.getElementById("code");
	                try{       
	                    var bHeight = iframe.contentWindow.document.body.scrollHeight;       
	                    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
	                    var height = Math.max(bHeight, dHeight);       
	                    iframe.height =  height;       
	                }catch (ex){}       
	            }       
	            window.setInterval("reinitIframe1()", 200);   
	            </script>    
            </iframe>   
        </td>
        <td width="2%" align="center">
        	<div>
        		<img width="10px" height="20px" src="https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2576990071,3082160916&fm=27&gp=0.jpg">
        	</div>
        </td>   
        <td width="49%">   
                <iframe name="content" id="content" frameborder="0" scrolling="no" width="100%" height="100%" 
                	src="rtbox/sourceEdit/preview.do?rtPageId=${param.rtPageId}&pageCode=${param.pageCode}"
                    onload="this.height=this.contentWindow.document.documentElement.scrollHeight" >   
                    <script type="text/javascript">   
                    //动态获取被引用的页面的高度   
                    function reinitIframe2(){       
                        var iframe = document.getElementById("content");      
                        try{       
                            var bHeight = iframe.contentWindow.document.body.scrollHeight; 
                            var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;     
                            var height = Math.max(bHeight, dHeight);       
                            iframe.height =  height;       
                        }catch (ex){}       
                    }       
                    window.setInterval("reinitIframe2()", 200);   
                    </script>    
            </iframe>   
        </td>   
    </tr>   
</table>
<form id="preview" target="content" method="post" action="rtbox/sourceEdit/preview.do">
    <input type="hidden" id="rtPageId" name="rtPageId" value="${param.rtPageId}"/>
    <input type="hidden" id="pageCode" name="pageCode" value="${param.pageCode}"/>
    <input type="hidden" id="fileContent" name="fileContent" />
</form>
 <iframe id="download" height="0" width="0"></iframe>
</body>
</html>