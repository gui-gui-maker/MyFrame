<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<% SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime="";
	nowTime = dateFormat.format(new Date());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>检验设备箱信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var pageStatus="${param.pageStatus}";
$(function(){
	$("#form1").initForm({ //参数设置示例
		toolbar : [ {
			text : '保存',
			id : 'save',
			icon : 'save',
			click : save
		}, 
		{
			text : '关闭',
			id : 'close',
			icon : 'cancel',
			click : function close(){	
					 	api.close();
					}
		} ],
		getSuccess : function(res) {
			if(res.success){
				$("#form1").setValues(res.data);
			}
		}
	});
});
	
	
	function chooseOrg(){
	    top.$.dialog({
	        width: 800,
	        height: 450,
	        lock: true,
	        parent: api,
	        title: "选择部门",
	        content: 'url:app/common/org_choose.jsp',
	        cancel: true,
	        ok: function(){
	            var p = this.iframe.contentWindow.getSelectedPerson();
	            if(!p){
	                top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                return false;
	            }
	            $("#boxDepId").val(p.id);
	            $("#boxDepName").val(p.name);
	        }
	    });
	}   
	
	function save(){
		//验证表单数据
		if($('#form1').validate().form()){
			var formData = $("#form1").getValues();
	        var data = {};
	        data = formData;
	        if($("#boxDepName").val() != "" && $("#boxDepName").val() != undefined){
	           	if($("#boxDepId").val() == "" || $("#boxDepId").val() == undefined){
	                $.ligerDialog.warn("部门id为空，请重新选择部门！");
	                 return;
	             }
	           }
	         
	        $("body").mask("正在保存数据，请稍后！");
	        $.ajax({
	            url: "equipment/box/saveEqui.do",
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	           	data: $.ligerui.toJSON(data),
	            success: function (data, stats) {
	            	$("body").unmask();
	                if (data["success"]) {
	                	if(api.data.window.Qm){
	                		api.data.window.Qm.refreshGrid();
	                	}
	                    top.$.dialog.notice({content:'保存成功'});
	                    api.close();
	                }else{
	                	$.ligerDialog.error('提示：' + data.msg);
	                	$("#save").removeAttr("disabled");
	                }
	            },
	            error: function (data,stats) {
	            	$("body").unmask();
	                $.ligerDialog.error('提示：' + data.msg);
	                $("#save").removeAttr("disabled");
	            }
	        });
		}
	}	
	
	//判断设备箱号是否重复
	function queryBox(){
		var boxNumber=$("#boxNumber").val();
		if(boxNumber == ""||boxNumber == "undefined"){
			$.ligerDialog.alert("请输入设备箱号！");
		}else{
			$.ajax({
	        	url: "equipment/box/queryBox.do?boxNumber="+boxNumber,
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            success: function (resp) {
	            	var num = resp.num;
	            	if(num>0){
	            		$.ligerDialog.alert("此设备箱号已存在，请更换设备箱号！");
	            	}
	            },
	            error: function (data) {
	            	$.ligerDialog.alert("设备箱号验证失败！");
	            }
	        });
		}
	}
</script>
</head>
<body>
	<form id="form1" action = "equtpment/box/saveEqui.do"  getAction = "equipment/box/detail.do?id=${param.id}" >
		<input type="hidden" name="id" id="id" value="${param.id}"/>
		<input type="hidden" name="boxDepId" id="boxDepId" value=""/>
		<input type="hidden" name="registerId" id="registerId" value=""/>
		<input type="hidden" name="registerName" id="registerName" value=""/>
		<input type="hidden" name="registerTime" id="registerTime" value=""/>
			<input type="hidden" name="fkTemplateId" id="fkTemplateId" value=""/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					检验设备箱创建
				</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">设备箱编号</td>
					<td class="l-t-td-right"><input id="boxNumber" name="boxNumber" type="text" ltype="text" validate="{required:true,	maxlength:32}" onblur="queryBox()"/></td>
					<td class="l-t-td-left">部门名称</td>
					<td class="l-t-td-right"><input  validate="{maxlength:50,required:true}"  readonly="readonly" ltype="text"  name="boxDepName" id="boxDepName"  type="text" onclick="chooseOrg();" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">装箱时间</td>
					<td class="l-t-td-right"><input name="boxTime" type="text" ltype="date" validate="{required:false}" 
        					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="boxTime"  
        					 readonly="readonly" value="<%=nowTime%>" /></td>
					<td class="l-t-td-left">报告类型</td>
					<td class="l-t-td-right"><input name="templateName"  id="templateName" type="text" ltype="text" validate="{maxlength:32}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">备注</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="remark" id="remark" rows="5" cols="25" class="l-textarea" validate="{maxlength:200}"></textarea>
					</td>
					
				</tr>
			</table>
	</fieldset>
	</form>
</body>
</html>