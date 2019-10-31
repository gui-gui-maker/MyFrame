<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="add">
<title>通用数据导入导出业务模版配置</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		var configItem = api.data.settings.configItem.split(",");//可配置项目
		var defaultValue = api.data.settings.defaultValue;//默认配置数据
		var busConfigInfo = api.data.settings.busConfigInfo;//业务个性配置
		
		var splitCfg = {isCfg:false,value:""};
		//根据可配置项目显示对应的输入区域
		if(configItem){
			for(var i in configItem){
				$("#" + configItem[i] + "_idx").show();
				if(configItem[i]=="split"){
					splitCfg.isCfg=true;
					if(defaultValue[configItem[i]])
						splitCfg.value = defaultValue[configItem[i]];
					else
						splitCfg.value = busConfigInfo[configItem[i]];
				}
				else
					$("input[name='" + configItem[i] + "']").val(defaultValue[configItem[i]]);
			}
		}
		$("#formObj").initForm({
			afterParse: function(formObj){
				if(splitCfg.isCfg){
					$("input[name='split']").ligerGetRadioGroupManager().setValue(splitCfg.value);
				}
			},
			toolbar : [ {
				text : "确定",
				icon : "save",
				click : function() {
					var ucg = {};
					for(var i in configItem){
						if(configItem[i]=="split"){
							ucg["split"]=$("input[name='split']").ligerGetRadioGroupManager().getValue();
						}else
							ucg[configItem[i]] = $("input[name='" + configItem[i] + "']").ligerGetTextBoxManager().getValue();
					}
					if(api.data.callback) api.data.callback(ucg);
					api.close();
				}
			}, {
				text : "取消",
				icon : "close",
				click : function() {
					api.close();
				}
			} ]
		});
	});
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="">
		<table cellpadding="3" class="l-detail-table">
			<tr id="sheetAt_idx" style="display: none">
				<td class="l-t-td-left">Excel工作表序号：</td>
				<td class="l-t-td-right"><input name="sheetAt" type="text"
					ligerui="{initValue:1,suffix:'EXCEL工作表顺序',suffixWidth:110}" value="0" ltype='number'
					validate="{maxlength:2}" /></td>
			</tr>
			<tr id="split_idx" style="display: none">
				<td class="l-t-td-left">文本分隔符号：</td>
				<td class="l-t-td-right"><input name="split" type="radio"
					ligerui="{data:[{id:',',text:'逗号“,”'},{id:'t',text:'制表符'}]}" ltype="radioGroup" /></td>
			</tr>
			<tr id="rowBegin_idx" style="display: none">
				<td class="l-t-td-left">数据起始行号：</td>
				<td class="l-t-td-right"><input name="rowBegin" type="text"
					ltype='number' validate="{required:true,maxlength:5}" ligerui="{suffix:'第一行数据行号',suffixWidth:110}"/></td>
			</tr>
			<tr id="rowEnd_idx" style="display: none">
				<td class="l-t-td-left">数据结束行号：</td>
				<td class="l-t-td-right"><input name="rowEnd" type="text" ligerui="{suffix:'-1表示不限制',suffixWidth:110}"
					ltype='number' validate="{required:true,maxlength:5}" /></td>
			</tr>
			<tr id="colBegin_idx" style="display: none">
				<td class="l-t-td-left">数据起始列号：</td>
				<td class="l-t-td-right"><input name="colBegin" type="text" 
					ltype='number' validate="{required:true,maxlength:3}" ligerui="{suffix:'从哪一列开始',suffixWidth:110}"/></td>
			</tr>
			<tr id="colEnd_idx" style="display: none">
				<td class="l-t-td-left">数据结束列号：</td>
				<td class="l-t-td-right"><input name="colEnd" type="text" ligerui="{suffix:'-1表示不限制',suffixWidth:110}" value="0"
					ltype='number' validate="{required:true,maxlength:3}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>