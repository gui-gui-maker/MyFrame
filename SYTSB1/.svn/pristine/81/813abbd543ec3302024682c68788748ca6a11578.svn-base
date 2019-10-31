<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>消息订阅设置</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/message/js/subscription.js"></script>
<script type="text/javascript">
	var _s_msg_module ;
	var _p_work_codes = <u:dict code="pub_workflow_service_code"/>;
    $(function() {
    	$("#wform").initForm({
    		toolbar: [{
    			text: "保存",
    			icon: "save",
    			click: saveData
    		}],
    		afterParse: function(){
    			getMsgModules(function(data){
    				_s_msg_module = data;
    				createWorktypesView();
    			})
    		}
    	});
    });
    
    function createWorktypesView(){
    	var hstr = createWorktypesHtml(_p_work_codes,0);
    	$("#worktypes_view").append(hstr);
    	$(".worktype").hover(function(){
    		$(this).addClass("hover");
    		
    	},function(){
    		$(this).removeClass("hover");
    	});
    	
    	$("body").mask("正在加载...")
    	$.getJSON("pub/worktask/message/get.do",function(response){
    		$("body").unmask();
    		if(response.success && response.data){
    			$.each(response.data,function(){
    				$("#worktype-" + this.id + " select").val(this.module);
    			});
    		}
    	});
    }
    
    function createMsgModuleOption(modules,level){
    	var str = "";
    	var m = modules || _s_msg_module;
    	var i = level || 0;
    	$.each(m,function(){
    		str += "<option value='" + this.code + "' style='padding-left:" + (level*2) + "em'>" + this.text + "</option>";
    		if(this.children && this.children.length > 0){
    			str += createMsgModuleOption(this.children,i+1);
    		}
    	});
    	return str;
    }
    
    function createWorktypesHtml(types,level){
    	var hstr = "";
    	var moduleStr = "<select>" + createMsgModuleOption() + "</select>";
    	$.each(types, function(j) {
    		hstr += "<li><div class='worktype' id='worktype-" + this.id + "' " +
    				"worktype='" + this.id + "'><span class='worktype-name' style='margin-left:" + (1+2*level) + "em;'>" + this.text + "</span><span class='module'>" + moduleStr + "</span></div>";
    		
    		if(this.children && this.children.length > 0){
    			hstr += "<ul>" + createWorktypesHtml(this.children,level+1) + "</ul>";
    		}
    		hstr += "</li>";
    	});
    	return hstr;
    }
    
    function saveData(){
    	var datas = [];
    	$(".worktype").each(function(){
    		var $this = $(this);
			datas.push({
				id: $this.attr("worktype"),
				typeName: $this.find(".worktype-name").text(),
				module: $this.find("select option:selected").attr("value"),
				moduleName: $this.find("select option:selected").text()
			});
    	});
    	console.log(JSON.stringify(datas));
    	$("body").mask("正在保存，请稍后...");
    	$.ajax({
            type: "POST",
            dataType: "json",
            url: "pub/worktask/message/save.do",
            contentType: "application/json;charset=utf-8",
            data: $.ligerui.toJSON(datas),
            success: function (resp, stats) {
        		$("body").unmask();
            	if(resp.success){
    				top.$.notice("操作成功！",3);
    			}else{
    				$.ligerDialog.error("保存失败！<br/>" + resp.msg);
    			}
            },
            error: function (data) {
        		$("body").unmask();
                $.ligerDialog.error('保存失败！<br/>' + data.msg);
            }
    	});
    }
</script>
<style type="text/css">
html,body{overflow:auto;}
*{font-size:14px!important;}
.header{border-bottom:2px solid #dedede;font-weight:bold;margin:1em 1em 0 1em;}
.header .work-type{display:inline-block;width:48%;line-height:8mm;padding-left:1em;}
.header .msg-module{display:inline:48%;line-height:8mm;}
ul{padding:0;margin:0;}
#worktypes_view{margin:1em;}
.worktype{height:8mm;border-bottom:1px solid #dedede;padding: 1mm 1em 1mm 1mm;}
.worktype.hover{background:#efefef;}
.worktype span{display:inline-block;}
.worktype .worktype-name{line-height:8mm;}
.worktype .module{float:right;width:50%;padding-top:0.5mm;}
.worktype .module select{height:7mm;width:15em;}
</style>
</head>
<body>
	<form id="wform">
		<div class="header">
			<span class="work-type">工作任务类型</span>
			<span class="msg-module">消息模块</span>
		</div>
    	<ul id="worktypes_view"></ul>
	</form>
</body>
</html>