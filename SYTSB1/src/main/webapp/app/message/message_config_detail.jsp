<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head pageStatus="${param.status}">
	<%@ include file="/k/kui-base-form.jsp"%>
	<% CurrentSessionUser user = SecurityUtil.getSecurityUser();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String now = df.format(new Date());
	%>
	<script type="text/javascript">
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	        getSuccess:function(resp){
	        	if(resp.success){
					$("#formObj").setValues(resp.data);
				}
	        },
	        success: function (response) {//处理成功
	    		if (response.success) {
		      		top.$.dialog.notice({
			             content: "保存成功！"
					});
	            	api.data.window.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error(response.msg);
	      		}
			}
		});
		
	    if("${param.status}"=='modify'){
	    	if(api.data.params!=undefined&&api.data.params!=null&&api.data.params!=""){
	    		initParamButton(api.data.params)
	    	}
	    }
	});	
	
	function close(){
		api.close();
	}
	
	function selectModule(){
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择功能模块信息",
			content: 'url:app/message/module_list.jsp',
			data : {"parentWindow" : window}
		});
	}
	//选择报检单位回调
	function callBack(id,module_code,module_name,param_01){
			$('#fk_module_id').val(id);
			$('#module_code').val(module_code);
			$('#module_name').val(module_name);
			initParamButton(param_01)
		
	}
	
	function initParamButton(params){
		
		if(params!=null&&params!=""){
			
			var tt = '<tr>'
				paramss = params.split(",");
			for (var i = 0; i < paramss.length; i++) {
				try {
					var name = paramss[i].split(":")[0];
					var value = paramss[i].split(":")[1];
					
					tt = tt + '<td><input type="button" class="param"  value="'+name+'('+value+')'+'" onclick="addParamTag(\''+value+'\')"/></td>'
				} catch (e) {
					//tt = tt + '<td></td>'
				}
				
			}
			
			tt = tt + '</tr>'
			//alert(tt)
			$("#params_div").html("提示：点击添加可用参数到内容：");
			$("#params_table").html("");
			$("#params_table").append(tt);
		}else{
			$("#params_div").html("提示：无可用参数！");
			$("#params_table").html("");
			$("#params_table").append('<tr><td></td></tr>')
		}
	}
	
	function addParamTag(param){
		var rangeData = getCursorPosition(document.getElementById("content"));
		//alert(rangeData.start+"-----------"+rangeData.end+"---"+rangeData.text)
		var preVal = $("#content").val();
		var val = preVal.substring(0,rangeData.start)+"\${"+param+"}"+preVal.substring(rangeData.end,preVal.length)
		$("#content").val(val);
		rangeData.end = rangeData.start + ("\${"+param+"}").length;
		
		setCursorPosition(document.getElementById("content"),rangeData);
		//$("#content").focus();
	}
	
	function getCursorPosition(textarea) {  
	    var rangeData = {text: "", start: 0, end: 0 };  
	        textarea.focus();  
	    if (textarea.setSelectionRange) { // W3C  
	        rangeData.start= textarea.selectionStart;  
	        rangeData.end = textarea.selectionEnd;  
	        rangeData.text = (rangeData.start != rangeData.end) ? textarea.value.substring(rangeData.start, rangeData.end): "";  
	    } else if (document.selection) { // IE  
	        var i,  
	            oS = document.selection.createRange(),  
	            // Don't: oR = textarea.createTextRange()  
	            oR = document.body.createTextRange();  
	        oR.moveToElementText(textarea);  
	        rangeData.text = oS.text;  
	        rangeData.bookmark = oS.getBookmark();  
	        // object.moveStart(sUnit [, iCount])  
	        // Return Value: Integer that returns the number of units moved.  
	        for (i = 0; oR.compareEndPoints('StartToStart', oS) < 0 && oS.moveStart("character", -1) !== 0; i ++) {  
	            // Why? You can alert(textarea.value.length)  
	            if (textarea.value.charAt(i) == '/n') {  
	                i ++;  
	            }  
	        }  
	        rangeData.start = i;  
	        rangeData.end = rangeData.text.length + rangeData.start;  
	    }  
	    return rangeData;  
	}  
	
	function setCursorPosition(textarea, rangeData) {  
	    if(!rangeData) {  
	        alert("You must get cursor position first.")  
	    }  
	    if (textarea.setSelectionRange) { // W3C  
	        textarea.focus();  
	        textarea.setSelectionRange(rangeData.start, rangeData.end);  
	    } else if (textarea.createTextRange) { // IE  
	        var oR = textarea.createTextRange();  
	        // Fixbug :  
	        // In IE, if cursor position at the end of textarea, the setCursorPosition function don't work  
	        if(textarea.value.length === rangeData.start) {  
	            oR.collapse(false)  
	            oR.select();  
	        } else {  
	            oR.moveToBookmark(rangeData.bookmark);  
	            oR.select();  
	        }  
	    }  
	}
	
</script>
<style type="text/css">
.param{
 width: 100px;
 height: 30px;
 background-color: buttonface;
}

</style>
</head>
	<body>
	
		<form name="formObj" id="formObj" method="post" action="messageContentConAction/save.do"
			getAction="messageContentConAction/detail.do?id=${param.id}">
			<input id="id" name="id"  type="hidden"  />
			<input id="fk_module_id" name="fk_module_id"  type="hidden"  />
			<input id="create_op_id" name="create_op_id"  type="hidden" value="<%=user.getId() %>" />
			<input id="create_op" name="create_op"  type="hidden"  value="<%=user.getName()%>"/>
			<input id="create_time" name="create_time"  type="hidden" value="<%=now %>" />
			
			<input id="last_update_op_id" name="last_update_op_id"  type="hidden" />
			<input id="last_update_op" name="last_update_op"  type="hidden"  />
			<input id="last_update_time" name="last_update_time"  type="hidden" />
			<input id="data_status" name="data_status"  type="hidden"  value="0"/>
		<c:if test="${param.status!='detail'}">
		<div id="params">
			<div id="params_div">提示：无可用参数！</div>
			<table id = "params_table" align="left">
				<tr><td></td></tr>
			</table>
			</div>
		</c:if>
				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					<tr>
						<td class="l-t-td-left">模块编号：</td>
						<td class="l-t-td-right">
							<input type="text" id="module_code" name="module_code" ltype="text" validate="{required:true}" readonly="readonly"
							 onclick="selectModule()"
								ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectModule()}}]}"/>
						</td>
						<td class="l-t-td-left">模块名称：</td>
						<td class="l-t-td-right">
							<input type="text" id="module_name" name="module_name" ltype="text" validate="{required:true}" readonly="readonly"/>
						</td>	
					</tr>
					<tr>
						<td class="l-t-td-left">发送方式：</td>
						<td class="l-t-td-right">
							<input type="text" name="send_type" id="send_type" ltype="select" validate="{required:true}" 
							ligerui="{
							initValue:'1',
							data:<u:dict code="MSG_TYPE"></u:dict>,isMultiSelect:true
							}"/> 
						</td>
						<td class="l-t-td-left">发送时限：</td>
						<td class="l-t-td-right">
							<input type="text" id="send_time" name="send_time" ltype="select" validate="{required:true}" 
							ligerui="{
							initValue:'1',
							data:<u:dict code="tjy2_msg_timelimit"></u:dict>
							}"/>
						</td> 
					</tr>
					
					<tr>
					    <td class="l-t-td-left">定时类型：</td>
						<td class="l-t-td-right">
							<input type="text" id="preview_type" name="preview_type" ltype="select" 
							ligerui="{
							initValue:'1',
							data:<u:dict code="message_types"></u:dict>
							}"/>
						</td>
						<td class="l-t-td-left">定时时间：</td>
						<td class="l-t-td-right">
							<input type="text" name="preview_time" id="preview_time" ltype="date" validate="{required:false}"
							ligerui="{initValue:'',format:'yyyy-MM-dd hh:mm:ss'}" /> 
						</td>
						
					</tr>
					<tr>
					     <td class="l-t-td-left">延迟时间：</td>
						 <td class="l-t-td-right">
							<input type="text" id="late_time" name="late_time" ltype="date" validate="{required:false}"
							ligerui="{initValue:'',format:'yyyy-MM-dd hh:mm:ss'}"/>
						 </td> 
					</tr>
					<tr>
						<td class="l-t-td-left">发送内容：</td>
						<td class="l-t-td-right" colspan="3">
							<textarea name="content" id="content" rows="2" type="text" ltype="text"  validate="{required:false,maxlength:1000}"></textarea>
						</td> 								
					</tr>
				</table>
			
		</form>
	</body>
</html>