<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String status=request.getParameter("status");
%>
<head pageStatus="${param.status}">
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var beanData;
	$(function() {
		$("#form1").initForm({
			success : function(responseText) {//处理成功
				if (responseText.success) {
					top.$.notice("保存成功！");
					api.data.window.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败' + responseText);
				}
			},
			getSuccess : function(responseText) {
				if(responseText.success){
					beanData=responseText.data;
					$("#advicenote_content").val(beanData.advicenote_content);
				}
			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#form1").validate().form()) {
				    		var content = $("#advicenote_content").val();
				    		if("" != content){
				    			if(confirm("确定保存？")){
				    				//表单提交
				    				$("#form1").submit();
					    		}
				    		}else{
				    			$.ligerDialog.alert("请先进行“在线编制”再保存！");
				    		}				    		
				    	}
	      			}
	      		},
				{
					text: "关闭", icon: "cancel", click: function(){
						api.close();
					}
				}
			], toolbarPosition: "bottom"
		});
	});

	// 打开正文，编辑正文
	function openDocEditor(){
		//将表单上的数据打包传过去
		var formData=$("#form1").getValues();
		var docId=$("#advicenote_content").val();
		var type=formData.advicenote_type;
		//$.ligerDialog.alert();
		if((formData.advicenote_type =='' || formData.advicenote_type==null)&&('${param.status}'=='add')){
			$.ligerDialog.warn('在线编制时，请先选择通知书类型！');
			return;
		}
		top.$.dialog({
			width : $(top).width(),
			height :  $(top).height()-40,
			lock : true,
			title : "正文内容",
			parent: api,
			content : 'url:app/advicenote/docEditor.jsp?status=${param.status}&docId='+docId+'&type='+type,
			data: {pwindow: window,bean: formData}
		}).max();
	}
	
	//编辑完正文后填写正文id
	function editorCallback(docId){
		$("#advicenote_content").val(docId);
	}
	
	
</script>
</head>
<body>
	<form name="form1" id="form1" action="advicenote/saveAdviceNote.do"
		getAction="advicenote/detail.do?id=${param.id}">
		<input id="id" name="id" type="hidden" />
		<table border="0" cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">通知书名称：</td>
				<td class="l-t-td-right" colspan="3">
					<input id="advicenote_name" name="advicenote_name" type="text" ltype='text' validate="{required:true,maxlength:200}" />
			    </td>
			</tr>
			<tr>
				<td class="l-t-td-left">通知书类型：</td>
				<td class="l-t-td-right" colspan="3">
					<u:combo name="advicenote_type" code="ADVICENOTE_TYPE" modify="false" validate="required:true"/>
				</td>
			</tr>
			<tr id="editdocs" >
				<td class="l-t-td-left">在线编制：</td>
				<td class="l-t-td-right" colspan="3">
					<input name="advicenote_content" type="hidden" id="advicenote_content" /> 
				<%
					if("detail".equals(status)){
				%>
				   <span style="cursor:pointer"><img src="k\kui\skins\icons\word.gif" onclick="openDocEditor();"></span>
				<% 
					}else{
				%>
				   <span class="l-button" onclick="openDocEditor();"><span class="l-button-main"><span class="l-button-text">编辑正文</span></span></span>
				<%
					}
				%>
				</td>
			</tr>
			<%
				if("detail".equals(status)){
			%>
			<tr>
				<td class="l-t-td-left">录入人：</td>
				<td class="l-t-td-right">
					<input id="enter_user_name" name="enter_user_name" type="text" ltype='text'  />
			    </td>
			    <td class="l-t-td-left">录入时间：</td>
				<td class="l-t-td-right">
					<input name="enter_date" id="enter_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" />
			    </td>
			</tr>	
			<tr>
				<td class="l-t-td-left">审核人：</td>
				<td class="l-t-td-right">
					<input id="check_user_name" name="check_user_name" type="text" ltype='text'  />
			    </td>
			    <td class="l-t-td-left">审核时间：</td>
				<td class="l-t-td-right">
					<input name="check_date" id="check_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" />
			    </td>
			</tr>	
			<tr>
				<td class="l-t-td-left">审核结果：</td>
				<td class="l-t-td-right">
					<input type="radio" name="check_result" id="check_result" ltype="radioGroup"
							ligerui="{data: [ { text:'通过', id:'1' }, { text:'不通过', id:'2' } ] }"/>
			    </td>
			    <td class="l-t-td-left">审核意见：</td>
				<td class="l-t-td-right">
					<textarea name="check_content" id="check_content" rows="2" cols="25" class="l-textarea" ></textarea>
			    </td>
			</tr>	
			<%
				}
			%>
		</table>
	</form>
</body>
</html>
