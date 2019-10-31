<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/k/jqm/mobile-base.jsp" %>
    <base href="<%=basePath%>">
    <title>iscrollview Demo</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>

    <link href="k/jqm/themes/default/jquery.mobile.min.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="k/jqm/themes/default/jquery.mobile.iscrollview.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="k/jqm/themes/default/jquery.mobile.iscrollview-pull.css" media="screen" rel="stylesheet"
          type="text/css"/>

    <script src="k/jqm/jquery2.js" type="text/javascript"></script>
    <script src="k/jqm/jquery.mobile.js" type="text/javascript"></script>
    <script src="k/jqm/iscroll.js" type="text/javascript"></script>
    <script src="k/jqm/jquery.mobile.iscrollview.js" type="text/javascript"></script>
    <script type="text/javascript">
    	$(function(){
    		$(document).on("pageshow","#page5",function(){
    			pageinit();
        	});
    	})
        var qmUserConfig = {
            sp_fields: [
                {name: "columm", compare: "like", value: "", width: ""}
            ],
            //定义按钮，可以直接是extjs的按钮
            tbar: [
                { text: "选中记录数", icon: "plus", click: function () {
                	$("head").attr("pageStatus","modify");
                	$.mobile.changePage("${pageContext.request.contextPath}/demo/qm/mobile/detail4.jsp", {
						transition: "slide",
						reloadPage: false,
						data:{orgId:'100001'}
                	});
	               return false;
             	} },
                { text: "删除", icon: "delete", click: function () {
                    
                	/**
                	openDialog({
                    	href:'${pageContext.request.contextPath}/dialog.html',
                    	transition:'slide',
                    	dialog:'MessageDialog',
                    	callback:function(){
                    		$("#btnOk").unbind("click").bind("click", function () {
                                alert("test");
                                $("#MessageDialog").dialog("close");
                            });
                    	}
                    })
                    */
                    $.del("确定要删除？","rbac/org/delete.do",{ids:'100019'});
                }}
            ]
        };
            var qmUserConfig = {
                sp_fields: [
                    {name: "str1", compare: "like", value: "", width: ""}
                ],
                //定义按钮，可以直接是extjs的按钮
                tbar: [
                    { text: "增加", icon: "plus", click: test},
                    { text: "修改", icon: "edit", click: test},
                    { text: "删除", icon: "delete", click: test}
                ]
            };
            function test() {
                alert(this.text+""+Qm.getValueByName("id"));
            }
    </script>
</head>

<body>
<div class="qm-page" data-role="page">
    <qm:qm pageid="qm_mb_02" script="false"/>
</div>
</body>
</html>