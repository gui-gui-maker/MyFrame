<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-list.jsp" %>
    <title></title>
    <script type="text/javascript">
        var returnValue = "";
        var uppath="";
        $(function () {
        	var oFrm = document.getElementById('rightFrame');
    		oFrm.onload = oFrm.onreadystatechange = function() {
    		     if (this.readyState&&this.readyState != 'complete')
    		    	 $('body').mask("正在加载文件...");
    		     else 
    		     {
    		    	 $('body').unmask();
    		     }
    		}
            $("#layout1").ligerLayout({leftWidth:0, allowLeftCollapse:false, allowRightCollapse:false, bottomHeight:38 });
            $("#rightFrame").attr("src", "pub/fileviewer/FileListView.jsp?url=${param.obj}");
            $("#toolbar1").ligerButton({
    			items: [
    				{text: "选择", icon: "save", click: function () {
    					api.data.window.setPath(returnValue);
    					api.close();
    				}},
    				{text: "关闭", icon: "cancel", click: function () {
    					api.close();
    				}}
    			]
    		});
            $("#toolbar2").ligerToolBar({
    			items: [
    				{text: "返回上一层", icon: "return",id:'up',disabled: true, click: function () {
    					var url;
    					var obj = $("#rightFrame").contents();
    					var dir  = obj.find("body").find("img").eq(0).attr("updir");
    					var isup = false;
    					if(dir==""||dir==undefined||dir==null){
    						isup = true;
    					}else{
	    					dir = dir.substring(0,dir.lastIndexOf("/"));
	    					var basepath = obj.find("body").find("#basepath").val();
    						var temp = basepath.split(",");
        					for(var i in temp){
        						if(temp[i]==dir){
        							isup =true;
        							dir = basepath;
        						}
        					}
    					}
    					if(isup){
    						url = "${param.obj}";
    						$("#toolbar2").ligerToolBar().setEnabled({up:false});
    					}else{
    						url = "${param.obj}"+"?relatePath="+dir;
    					}
    					$("#uppath").val("");
    					$("#path").val("");
    					$("#rightFrame").attr("src", "pub/fileviewer/FileListView.jsp?url="+url);
    				}}
    			]
    		});
        });
    </script>
</head>
<body>

	<div class="item-tm">
		<div id="toolbar2"></div>
	</div>
    <div class="scroll-tm">
        <iframe marginwidth="0" id="rightFrame" marginheight="0" frameborder="0" valign=top src=""
                name="rtree"
                width=100% height=100%  allowTransparency></iframe>
    </div>
    <div class="item-tm">
		<input type="text" ltype="text" style="width:100%" readonly="readonly" id="path"/>
	</div>
    <div class="item-tm">
		<div class="toolbar-tm-bottom">
			<div id="toolbar1"></div>
		</div>
	</div>
</body>
</html>