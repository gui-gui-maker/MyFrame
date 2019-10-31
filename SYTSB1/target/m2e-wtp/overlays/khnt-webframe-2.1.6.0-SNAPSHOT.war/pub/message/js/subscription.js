/**
 * 创建模块列表
 * @param modules
 */
function createModuleViewHtml(modules,msgTypes,level,isdefault) {
	var hstr = "";
	$.each(modules, function(j) {
		hstr += "<li><div style='padding-left:" + (1+2*level) + "em;' class='module' id='module-" + this.code + "' " +
				"module='" + this.code + "'><span class='module-name'>" + this.text + "</span>";
		if(isdefault!=0){
			hstr += "&nbsp;&nbsp;<input type='checkbox' class='close' id='close_"+level+j+"'/><label for='close_"+level+j+"'>禁止发送消息</label>";
		}
		hstr +="<span class='cfg'>";
		for(var i in msgTypes){
			hstr += '<span class="cfg-item"><span><input type="checkbox" name="' + msgTypes[i].id + '" id="' + msgTypes[i].id  + level + j + 
				'"/>'
			if(isdefault!=0){
				hstr += '<input type="hidden" name="' + msgTypes[i].id + '_temp" id="' + msgTypes[i].id  + level + j + '_temp"/>'
			}
			hstr += '</span><label for="' + msgTypes[i].id + level + j + '">' + msgTypes[i].text + '</label>'
			if(isdefault!=0){
				hstr += '&nbsp;&nbsp;<img title="模板设置" src="k/kui/images/icons/16/to_do_list.png" style="cursor:pointer" onClick="setTempContent(this)">'
			}
			hstr +='</span>';
		}
		hstr += "</span></div>";
		if(this.children && this.children.length > 0){
			hstr += "<ul>" + createModuleViewHtml(this.children,msgTypes,level+1,isdefault) + "</ul>";
		}
		hstr += "</li>";
	});
	return hstr;
}

function createModuleView(modules,msgTypes,isdefault){
	$("body").mask("正在加载，请稍后...");
	$("#module_view").html(createModuleViewHtml(modules,msgTypes,0,isdefault));
	$(".module").hover(function(){$(this).addClass("hover");},function(){$(this).removeClass("hover");});
	initUserSubscription();
}

/**
 * 初始化数据
 */
function initUserSubscription(){
	$.getJSON($("#mform").attr("getAction"),function(response){
		if(response.success && response.data && response.data.length > 0){
			$.each(response.data,function(){
				if(!$.kh.isNull(this.config)){
					var configs = this.config.split(",");
					for(var i in configs){
						if(!configs[i])continue;
						$("#module-" + this.module + " :checkbox[name='" + configs[i] + "']").attr("checked",true);
					}
				}
				if(this.send=='1'){
					$("#module-" + this.module + " .close").attr("checked",true);
				}
				if(!$.kh.isNull(response.mts)&&response.mts.length>0){
					for(var j in response.mts){
						if(response.mts[j].module==this.module){
							$("#module-" + this.module + " input[name='"+response.mts[j].type+"_temp']").val(response.mts[j].template);
						}
					}
				}
				
			});
		}
		$("body").unmask();
	});
}

/**
 * 保存模块订阅配置
 */
function saveSubsData(){
	var saveData={};
	var datas = [];
	var moduleTemp =[];
	$(".module").each(function(){
		var config = "";
		$(this).find(".cfg").find(":checkbox:checked").each(function(){
			config += "," + $(this).attr("name");
		});
		var send = "0";
		if($(this).find(".close").attr("checked")=="checked"){
			send = "1";
		}
		datas.push({"module":$(this).attr("module"),"config":config==""?"":config.substring(1),"send":send});
	});
	$(".module").each(function(){
		var module = $(this).attr("module");
		$(this).find(".cfg-item").each(function(){
			moduleTemp.push({"module":module,"type":$(this).find("input[type='checkbox']").attr("name"),"template":$(this).find("input[type='hidden']").val()})
		});
	});
	saveData["moduleTemps"] = moduleTemp;
	saveData["moduleTypes"] = datas;
	if(datas.length==0){
		$("body").unmask();
		$.ligerDialog.confirm("您没有订阅任何内容，确定要保存？",function(isy){
			if(isy) doSaveData(saveData);
		});
	}else{
		doSaveData(saveData);
	}
}

/**
 * 保存订阅信息，同时保存订阅账号数据
 */
function saveDataWithAccount(){
	var userMsgCfg = {};
	var isEmptyCfg = true;
	$("#userAccount input").each(function(){
		isEmptyCfg = false;
		userMsgCfg[$(this).attr("name")] = $(this).val();
	});
	if(isEmptyCfg){
		saveSubsData();
	}else{
		$("body").mask("正在保存，请稍后...");
		$.post("rbac/user/setExtConfig.do",{ec:$.ligerui.toJSON({msg_cfg:userMsgCfg})},function(resp){
			if(resp.success){
				saveSubsData();
			}else{
	    		$("body").unmask();
				$.ligerDialog.error(resp.msg || "保存账号信息失败，请联系技术支持人员！");
			}
		},"json");
	}
}

function doSaveData(datas){
	$("body").mask("正在保存，请稍后...");
	$.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: $.ligerui.toJSON(datas),
		url: $("#mform").attr("action"),
        success: function (resp, stats) {
        	if(resp.success){
				top.$.notice("操作成功！",3);
				if(api) api.close();
			}else{
				$.ligerDialog.error("保存失败！<br/>" + resp.msg);
			}
    		$("body").unmask();
        },
        error: function (data) {
            $.ligerDialog.error('保存失败！<br/>' + data.msg);
    		$("body").unmask();
        }
	});
}
function getMsgModules(callback){
	var modules = [];
	$.getJSON("pub/message/subscription/getUserMessageModules.do",function(res){
		var moduls = res[0].children;
		if( moduls.length>0&& moduls[0].children!="null" && moduls[0].children!=null && moduls[0].children!=undefined &&moduls[0].children!="")
			modules = moduls[0].children;
		if(callback){
			callback(modules);
		}
	})
}
/**
 * 设置模板内容
 * @param obj
 */
function setTempContent(obj){
	var msg = $(obj).parent().find("input[type='hidden']").val();
	var title = $(obj).parent().find("label").text();
	dw = top.$.dialog({
		width : 600,
		height : 400,
		title : "设置"+title+"模板内容",
		data:{window:window,msg:msg,callback:function(data){
			setTemp(data,obj)
		}},
		content : "url:pub/message/subscription_template.jsp"
	});
}
function setTemp(data,obj){
	$(obj).parent().find("input[type='hidden']").val(data);
}