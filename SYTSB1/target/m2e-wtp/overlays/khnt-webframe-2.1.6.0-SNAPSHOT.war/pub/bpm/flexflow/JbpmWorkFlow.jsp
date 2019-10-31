<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>流程设计</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="pub/bpm/flexflow/swfobject.js"></script>
<script type="text/javascript">
    $(function() {
        $("body").height($(window).height());
        createSwf();
    });

    function createSwf() {
        var swfVersionStr = "10.0.0";
        var xiSwfUrlStr = "playerProductInstall.swf";
        var flashvars = {
       		actionType: "${param.status}",
       		xml: encodeURI('<?xml version="1.0" encoding="utf-8"?><c:out value="${fn:replace(flowDefinition.flowxml,'\\\'','\\\"')}" escapeXml="false"/>'.replace(/&&/gi,';and;and')),
       		flowType: "${param.flowtype}"
        };
        var params = {
        	quality: "high",
        	bgcolor: "#ffffff",
        	allowscriptaccess: "sameDomain",
        	allowfullscreen: "true",
        	wmode: "transparent"
        };
        var attributes = {
        	id: "JbpmWorkFlow",
        	name: "JbpmWorkFlow",
        	align: "middle"
        };
        swfobject.embedSWF("pub/bpm/flexflow/JbpmWorkFlow.swf", "flashContent", "100%", "100%", swfVersionStr, xiSwfUrlStr, flashvars, params, attributes);
        swfobject.createCSS("#flashContent", "display:block;text-align:left;");
    }

    //保存流程
    function saveWorkFlow(flowxml) {
        $("body").mask("数据保存中...");
        $.post("bpm/definition/saveFlow.do", {
            flowxml : flowxml.replace(/;and;and/gi,'&&'),
            id : "${param.id}",
            flowTypeID : "${param.flowType}"
        }, function(data) {
            if (data.success) {
                top.$.notice('保存成功', 2);
                api.data.window.Qm.refreshGrid();//执行列表页面函数
                api.close();
            } else {
                $("body").unmask();
                $.ligerDialog.error("保存失败");
            }
        },"json");
    }

    //选择环节参与者
    function selectParticipators(type, mutiple) {
    	var stype = type=="role"?"2":type=="position"?"4":"1";
    	var checkbox = type=="position"?"1":mutiple;
   		selectOrgUser({
   			type: stype,
   			checkbox: checkbox,
   			callback: function(data){
   				var str = data.code + ";" + data.name;
                   JbpmWorkFlow.setParticipators(type, str);
   			}
   		});
    }

    //得到功能当前流程可使用的功能
    function flexSelectFunction() {
        return "${functions}";
    }

    // 选择子流程
    function selectSubFlow() {
        top.winOpen({
            width : 750,
            height : 400,
            lock : true,
            parent : api,
            title : "选择子流程",
            content : "url:pub/bpm/_fun_select_subflow.jsp",
            ok : function() {
                var rs = this.content.getSelected();
                if (rs) JbpmWorkFlow.setSubFlow(rs.id, rs.name);
            },
            cancel : true
        });
    }
    
    // 退出设计器
    function exitWindow(){
    	if(window.api)window.api.close();
    	else if(window==top)window.close();
    }
</script>
</head>
<body>
    <div id="flashContent">
        <a href='http://www.adobe.com/go/getflashplayer' style="display:block;padding:1em;font-size:4mm;">您的电脑没有安装 flash player，请点击这里下载安装。</a>
    </div>
</body>
</html>
