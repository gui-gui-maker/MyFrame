/** *******************************************************
 * 公式服务接口定义
 ** ******************************************************/

/**
 * 用ID获取公式
 * 如果指定ID的公式存在，返回该公式的JSON对象，否则返回NULL,返回的方式是通过回调函数实现
 * 
 * @param id 公式ID
 * @param callback 回调函数，获取公式成功后，通过回调函数返回数据
 */
function getFormula(id,callback){
	if(id){
		$.getJSON("pub/formula/detail.do",{id:id},function(resp){
			if(resp.success)
				callback(resp.data);
			else
				callback(null);
		});
	}
	else{
		callback(null);
	}
}

/**
 * 设计公式。此接口接受用户传入一个已存在的公式ID，这将会修改该公式，如果未传入ID，则表示添加公式。
 * @param id 公式ID 
 * @param callback 回调函数，获取公式成功后，通过回调函数返回数据
 */
function designFormula(id,type,name,callback){
	var status = "add";
	if(id) status = "modify";
	top.$.dialog({
		width : 900,
		height : 500,
		lock : true,
		parent : api,
		content : "url:pub/formula/formula_editor.jsp?id=" + id + 
				"&status=" + status + "&type=" + type + "&name=" + name,
		data : {callback : callback}	
	});
}

/**
 * 根据类别选择公式
 * @param type 公式类别代码
 * @param chooseCallback 回调函数
 */
function chooseFormula(type,chooseCallback){
	top.$.dialog({
		width : 800,
		height : 450,
		lock : true,
		parent : api,
		title : "选择公式",
		content : "url:pub/formula/formula_select_index.jsp?type=" + type,
		data : {
			callback : chooseCallback
		}
	});
}

/**
 * 执行公式计算，给定相关的上下文参数
 * @param uniqueName 公式唯一标识名称
 * @param items 给定变量列表，json字符串
 * @param codeTables 指定变量的码表，可接受多个码表，用逗号','分隔
 * @callback 回调函数
 */
function executeFormula(uniqueName,items,codeTables,callback,fixed){
	$.getJSON("pub/formula/execute.do", {
		uniqueName : uniqueName,
		codeTables : codeTables,
		items : items
	}, function(response){
		if(response.success){
			if(callback){
				var rst = response.data;
				if(fixed)rst = rst.toFixed(fixed);
				else rst = rst.toFixed(4);
				callback(new Number(rst));
			}
		}else{
			$.ligerDialog.error("计算失败！<br />" + response.msg);
		}
	});
}