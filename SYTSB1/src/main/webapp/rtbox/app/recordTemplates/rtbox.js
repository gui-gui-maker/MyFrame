/**
 * 操作报表
 * 
 * @param code
 *            报表代码
 * @param param
 *            name和value属性的json
 * @param param reportPath 报告jsp文件路径
 */
function opRt(code, param,reportPath) {
	if(reportPath==null||reportPath==""){
		reportPath = "rtbox/app/recordTemplates/";
	}
	var _param = "";
	if (param) {
		for (var i = 0; i < param.length; i++) {
			_param += "&" + param[i].name + "=" + param[i].value;
		}
	}
	/*content : 'url:'+reportPath + code
	+ '/index.jsp?code='+code + _param,*/
	top.$.dialog({
		width : '80%',
		height : '98%',
		lock : true,
		parent : api,
		data : {
			window : window
		},
		title : "填写报表",
		content : 'url:'+reportPath +'/index.jsp?code='+code + _param,
		close:function(){
			if(Qm!=undefined&&Qm!=null){
				Qm.refreshGrid();
			}
		}
	}).max();
}

/**
 * SQL语句必须为 NAME，VALUE
 * @param id
 * @param code
 * @param param
 * @param sql
 */
function outRt(id, code,param, sql) {
	var _param = "";
	if (param != null) {
		for (var i = 0; i < param.length; i++) {
			_param += " and " + param[i].name + "='" + param[i].value + "' ";
		}
	}
	if (!sql) {
		sql = " select item_name name,item_value value from tzsb_report_item_value where 1=1 ";
	}

	$("body").mask("正在生成数据，请稍后...");
	$
			.ajax({
				url : 'com/rt/page/rtRevert.do',
				type : 'post',
				dataType : "json",
				data : {
					id : id,
					code:code,
					sql : sql + _param
				},
				success : function(response) {
					$("body").unmask();
					if (response.success) {
						var data = response.data;
						var outPutDocDirPath = data.outPutDocDirPath;
//						outPutDocDirPath =  outPutDocDirPath.replace("D:/rtbox", "")
//								+ id + ".docx";//转入java
						var content = '导出成功,点击下载:<br> <a href="' + outPutDocDirPath
						+ '" target="_blank">' + data.rtName + '('
						+ id + ')' + '</a>';
//						alert("导出成功,点击下载:"+ content);
						top.$.dialog({
							width: 350,
							height: 150,
							lock: true,
							parent: api,
							data: {
								window: window
							},
							title: "系统消息",
							content: content
						});
					} else {
						alert("导出失败！" + response.msg);
					}

				}
			});
}
