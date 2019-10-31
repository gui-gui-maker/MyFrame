<%@page import="com.khnt.base.Factory"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head id="main_head">
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-list.jsp" %>
    <title></title>
    <script type="text/javascript">
        var menu;
        var actionNodeID = "";
        var Treenode = "";
        var webroot="11111111111";
        $(function () {
            $("#layout1").ligerLayout({leftWidth:380, allowLeftCollapse:false, allowRightCollapse:false });
            Treenode = $("#tree1").ligerTree({
                checkbox:true,
                url:'infomanage/classtype/ClassViewTree.do',
                attribute:["url"],
                btnClickToToggleOnly:false,
                nodeWidth:200,
                slide:true,
                autoCheckParent:true,
                idFieldName :'id',
                parentIDFieldName :'pid',
                onAfterAppend:function (parentNode, newdata) {
                    //Treenode.collapseAll();
                }

            });
            /*
            $.post("mark/markhtmlHK.do",
    				function(data){
    				//$("table").append(data);						       
    	    	},"text");
            */
        });
        function mark(type) {
            var notes = Treenode.getChecked();
            var classId = "";
            var siteId = "";
            for (var i = 0; i < notes.length; i++) {
                classId += i == 0 ? "" : ",";
                classId += notes[i].data.id;

                if (siteId != notes[i].data.site) {
                    siteId += i == 0 ? "" : ",";
                    siteId += notes[i].data.site;
                }
            }
            if (classId == "") {
                $.ligerDialog.error('请选择站点或栏目!')
                return false;
            }
           // $("body").mask("正在生成静态文件,请稍候……");
            var loadingHtml = "<img src='/k/kui/images/loading-xp.gif'>";
            document.getElementById("loadingGif").innerHTML = loadingHtml;
            //生成首页
            if (type == 1 ) {
            	$.post("mark/markIndex.do", {siteId:siteId}, function (data) {
                    //$("body").unmask();
                    if (data.success) {
                        $.ligerDialog.alert('生成成功！', "success");
                        document.getElementById("loadingGif").innerHTML = "";
                    } else {
                        $.ligerDialog.alert('生成失败！', "error");
                        document.getElementById("loadingGif").innerHTML = "";
                    }
                });
                //alert(siteId)
            }
            else if (type == 2) {//生成栏目

                $.post("mark/markClass.do", {classId:classId}, function (data) {
                    //$("body").unmask();
                    if (data.success) {
                        $.ligerDialog.alert('生成成功！', "success");
                        document.getElementById("loadingGif").innerHTML = "";
                    } else {
                        $.ligerDialog.alert('生成失败！', "error");
                        document.getElementById("loadingGif").innerHTML = "";
                    }
                });
                //alert(siteId + "----" + classId)
            } else if (type == 3) {//生成详情

                $.post("mark/markInfo.do", {classId:classId}, function (data) {
                    //$("body").unmask();
                    if (data.success) {
                        $.ligerDialog.alert('生成成功！', "success");
                        document.getElementById("loadingGif").innerHTML = "";
                    } else {
                        $.ligerDialog.alert('生成失败！', "error");
                        document.getElementById("loadingGif").innerHTML = "";
                    }
                });
            } else if (type == 4) {//生成搜索页
                $.post("mark/markSearch.do", {siteId:siteId}, function (data) {
                    //$("body").unmask();
                    if (data.success) {
                        $.ligerDialog.alert('生成成功！', "success");
                        document.getElementById("loadingGif").innerHTML = "";
                    } else {
                        $.ligerDialog.alert('生成失败！', "error");
                        document.getElementById("loadingGif").innerHTML = "";
                    }
                });
            }
        }
       function refresh() {
            $.post("tempmanage/temp/refreshTpl.do", function (data) {
                if (data.success) {
                    $.ligerDialog.alert(data.msg, "success");
                } else {
                    $.ligerDialog.alert(data.msg, "error");
                }
            });
        }
	
    </script>
    </head>
    <body>
<div id="toptoolbar"></div>
<div id="layout1">
		<div position="left" title="站点" style="overflow:auto;height:92%;">
			<ul id="tree1">
			</ul>
	</div>
		<div position="center" title="生成管理">
		<div class="sp_div_caption">生成站点静态文件</div>
		<table border='0' cellpadding='0' cellspacing='0' width='' height='' align='center' class="generate">
				<tr>
				<td><input type="button" value="生成首页" onclick="mark(1);" class="l-button"></td>
				<td><input type="button" value="生成栏目" onclick="mark(2);" class="l-button"></td>
				<td><input type="button" value="生成详情" onclick="mark(3);" class="l-button"></td>
				<td><input type="button" value="生成搜索页" onclick="mark(4);" class="l-button"></td>
                <td><input type="button" value="刷新模板" onclick="refresh();" class="l-button"></td>
			</tr>
				<tr>
				<td colspan="4" align="center" id="loadingGif"></td>
			</tr>
			</table>
		<table border='0' cellpadding='0' cellspacing='0' width='' height='' align='center' class="gnrt_txt">
				<tr>
				<th>生成首页</th>
				<td>选择左边的站点，点击“生成首页”，<br />
						生成相应站点的首页静态文件</td>
			</tr>
				<tr>
				<th>生成栏目</th>
				<td>选择左边的站点及需要生成的栏目，点击“生成栏目”，<br />
						生成选择的站点下相应栏目的列表静态文件
</td>
			</tr>
				<tr>
				<th>生成详情</th>
				<td>选择左边的站点及需要生成的栏目，点击“生成详情”，<br />
						生成选择的站点下相应栏目的详细信息静态文件  </script></td>
			</tr>
				<!-- <tr>
				<th>生成搜索页</th>
				<td>选择左边的站点，点击“生成搜索页”，<br />
						生成相应站点的搜索页静态文件</td>
			</tr> -->
            </tr>
            <tr>
                <th>刷新模板</th>
                <td>生成静态文件失败时，<br />
                    先点击“刷新模板”然后再点击相应生成按钮</td>
            </tr>
			</table>
	</div>
	</div>
</body>
</html>
