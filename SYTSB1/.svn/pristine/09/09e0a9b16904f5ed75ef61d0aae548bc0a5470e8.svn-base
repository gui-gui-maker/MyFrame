<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>消息发送</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="k/kui/frame/ligerTree.js"></script>
<script type="text/javascript">
var status="${param.pageStatus}";
var tbar="";
$(function(){
	if(status=="add" ||status=="modify"){
		tbar=[ {text : '保存',id : 'save',icon : 'save',click : function(){
	    	if ($("#formObj").validate().form()) {
	    		$("#formObj").submit();
	    	}else{
	    		$.ligerDialog.error('提示：' + '请填写完整后保存！');
	    	}}},
		          {text : '关闭',id : 'close',icon : 'cancel',click : function(){api.close();}} ];
	}else if(status=="detail"){
		tbar=[ {text : '关闭',id : 'close',icon : 'cancel',click : function(){api.close();}} ];
	}
	$("#formObj").initForm({
		showToolbar: true,
        toolbarPosition: "bottom",
        toolbar: tbar,
		success: function(resp) {
			if (resp.success) {
            	top.$.dialog.notice({
             		content: "保存成功！"
            	});
            	api.data.window.Qm.refreshGrid();
				api.close();
    		} else {
           		$.ligerDialog.error('保存失败！<br/>' + resp.msg);
      		}
		},
		getSuccess: function(resp){
			
		}
	});
});
</script>
</head>
<body>
		   <form name="formObj" id="formObj"  action="officeOwnnoteAction/saveOwnnote.do?pageStatus=${param.pageStatus}" getAction="officeOwnnoteAction/detail.do?id=${param.id}">
		   <input type="hidden" id="id" name="id" />
		   <input type="hidden" id="createDate" name="createDate"/>
		   <input type="hidden" id="createId" name="createId"/>
		   <input type="hidden" id="createBy" name="createBy"/>
		   <input type="hidden" id="lastModifyDate" name="lastModifyDate"/>
		   <input type="hidden" id="lastModifyId" name="lastModifyId"/>
		   <input type="hidden" id="lastModifyBy" name="lastModifyBy"/>
		   <fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					个人记事本
				</div>
			</legend>
				<table class="l-detail-table">
					<tr>
						<td class="l-t-td-left">工作内容</td>
						<td class="l-t-td-right">
							<textarea id="workContent" name="workContent" required="true" rows="7" cols="50" class="l-textarea" validate="{require:true,maxlength:2000}"></textarea>
					    </td>
					</tr>
					<tr>
						<td class="l-t-td-left">工作记录</td>
						<td class="l-t-td-right">
							<textarea id="workRecord" name="workRecord" rows="16" cols="50" class="l-textarea" validate="{maxlength:2000}"></textarea>
					    </td>
					</tr>
					<tr>
						<td class="l-t-td-left">工作状态</td>
						<td class="l-t-td-right"><input type="radio" name="workStatus" id="workStatus" ltype="radioGroup" validate="{required:false}"
							ligerui="{value:'0',data: [ { text:'未开始', id:'0' }, { text:'进行中', id:'1' }, { text:'已完成', id:'2' }] }" />
						</td>
				  </tr>
	        	</table>
	        	</fieldset>
	       </form>
</body>
</html>
