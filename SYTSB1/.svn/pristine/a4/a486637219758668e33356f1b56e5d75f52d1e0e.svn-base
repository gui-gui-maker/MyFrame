<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head pageStatus="${param.pageStatus}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<title>设置文件重要等级</title>
<script type="text/javascript">
//jQuery页面载入事件

var beanData;
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];           
     if(e && e.keyCode==13){ // enter 键
    	 save();
    }
};
$(function() {

	//配置资源选择器

	$("#formObj").initForm({
		toolbar : [ {
			text : '保存',
			id : 'save',
			icon : 'save',
			click : save
		}, {
			text : '关闭',
			id : 'close',
			icon : 'cancel',
			click : function() {
				api.close();
			}
		} ],
		toolbarPosition : "bottom",
		success : function(responseText) {//处理成功
			if (responseText.success) {
				top.$.notice("处理成功 ");
				api.data.window.Qm.refreshGrid();
				api.close();
			} else {
				$.ligerDialog.error('处理失败' + responseText.msg);
			}
		}
	});
	
	/* var fileName = api.data.fileName;
	$("#fileName").val(fileName); */
});
function save(){
	if ($("#formObj").validate().form()) {
		var grade = $("input[name=resource_important_flag]").ligerGetRadioGroupManager().getValue();
		var ids = api.data.ids;
		var beans = api.data.window.beans;
		for (var i = 0; i < ids.length; i++) {
			var type = beans[ids[i]].infoType;
			if(type=="1"){
				//文件夹
				$.ajax({
					url : "resourcePath/updatePathImportantFlag.do",
					type : "POST",
					datatype : "json",
					async:false,
					data : {
						id:ids[i],
						pathImportantFlag:grade
					},
					success : function(data, stats) {
						$("body").unmask();
						if(!data.success){
							$.ligerDialog.error(res.msg);
						}
						api.close();
					},
					error : function(data, stats) {
						$.ligerDialog.error(res.msg);
					}
				})
			
			}else{
				//文夹
				$.ajax({
					url : "resourceInfo/updateResourceImportantFlag.do",
					type : "POST",
					datatype : "json",
					async:false,
					data : {
						id:ids[i],
						resourceImportantFlag:grade
					},
					success : function(data, stats) {
						$("body").unmask();
						if(!data.success){
							$.ligerDialog.error(res.msg);
						}
						
					},
					error : function(data, stats) {
						$.ligerDialog.error(res.msg);
					}
				})
			
			}
		}
		top.$.notice("修改完成！");
		api.data.window.refreshData(api.data.window.pid);
		api.close();
	}
	
}


</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" >
		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">文件星级：</td>
				<td colspan="3" class="l-t-td-right">
					<input type="radio" name="resource_important_flag" ltype="radioGroup" validate="{required:true}"
						ligerui="{
						readonly:true,
						data:[{id:'1',text:'☆'},{id:'2',text:'☆☆'},{id:'3',text:'☆☆☆'},{id:'4',text:'☆☆☆☆'},{id:'5',text:'☆☆☆☆☆'}]
						}"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>