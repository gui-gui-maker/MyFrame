<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.khnt.qm.def.DefinedSystem" %>
<%@ page import="com.khnt.qm.QmController" %>
<%@ page import="com.khnt.qm.QmCache" %>
<%@ page import="com.khnt.qm.Const" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="qm" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>通用查询</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="k/qm/extjs/css/ext-all.css"/>
    <link rel="stylesheet" type="text/css" href="k/qm/extjs/css/paging.css"/>
    <script type="text/javascript" src="k/qm/extjs/ext-base.js"></script>
    <script type="text/javascript" src="k/qm/extjs/ext-all.js"></script>
    <script type="text/javascript" src="k/qm/extjs/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="k/qm/extjs/paging.js"></script>
    <script type="text/javascript" src="k/qm/pagecol.js"></script>
    <script type="text/javascript" src="k/qm/page.js"></script>
    <script type="text/javascript">
        var qmUserConfig = {
//            //自定义查询面板样式参数
            sp_defaults:{columnWidth:0.2,labelAlign:'right',labelSeparator:'',labelWidth:100},// 可以自己定义
//            //自定义查询面板查询字段
            sp_fields:[
                {name:"pageid",compare:"like",value:""},
                {name:"pagename",compare:"like",value:""},
                {name:"typesname",compare:"=",value:""}
            ],
//            //定义按钮，可以直接是extjs的按钮
            tbar:[
                pageadd,
                '-',
                pageaddMobil,
                '-',
                pageaddMobil_mui,
                '-',
                pageedit,
                '-',
                pagecopy,
                '-',
                pagedatadel,
                '-',
                pagefieldset,
                '-',
                pagepreview,
                '-',
                cacheTool,
                '-',
                tool
            ],
//            //提供以下4个事件
            listeners: {
//                rowclick :function(grid,rowIndex){alert("点击第 "+(rowIndex+1)+" 行");}
                rowdblclick :rowDblClickFun
                ,selectionchange :selectionChangeFun
                ,customfun :function(grid){
                    var task = {
                        run: function(){
                            var ids=[];
                            grid.getStore().each(function(r){
                                ids.push(r.get("pageid"));
                            });

                            Ext.Ajax.request({
                                url: Qm.pagingUrlPre +'getCacheDes&pageid='+ ids,
                                callback: function (options, success, response) {
                                    var obj = Ext.util.JSON.decode(response.responseText);
                                    if (obj.des) {
                                        document.getElementById("__page_title").innerHTML=("通用查询管理（"+obj.des+"）");
                                    }
                                    if (obj.data) {
                                        grid.getStore().commitChanges();
                                        grid.getStore().each(function(r){
                                            var d=obj.data[r.get("pageid")];
                                            if(d){
                                                r.set("lastaccesstime",d["lastAccessTime"]);
                                                r.set("hitcount",d["hitCount"]);
                                            }else{
                                                r.set("lastaccesstime","");
                                                r.set("hitcount","");
                                            }
                                        });
                                    }
                                }
                            });
                        },
                        interval: 5000
                    };
                    var runner = new Ext.util.TaskRunner();
                    grid.getStore().on("load",function(){
                        runner.start(task);
                    });
                }
            }
        };
    </script>
    <style type="text/css">
        .ext-gecko .x-window-body .x-form-item {
            outline: medium none;
            overflow: hidden;
        }
    </style>
</head>
<body>
<%
    if (DefinedSystem.isSysCreate()) {
        String title="<span id='__page_title'>通用查询管理（Version："+Const.version+"）</span>";
%>
<qm:qm pageid="<%=Const.SYSTEMPAGEID%>" pagesize="200" clientModel="extjs" title="<%=title%>"/>

<div id="pageWin"></div>
<div id="pagecolWin"></div>
<div id="previewWin"></div>
<%} else {%>
<script type="text/javascript">
    Qm.pagingUrlPre = "<%=basePath+QmController.servletName%>?__method=";
    Ext.onReady(function() 
    {
        createSystem();
    });
</script>
<%}%>
</body>
</html>
