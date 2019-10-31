<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
			$("#form1").initForm({    //参数设置示例
				success: function (response) {
					if(response.success){
						top.$.notice("保存成功！",3);
						api.data.window.Qm.refreshGrid();
						api.close();
					}
					else{
						$.ligerDialog.error("操作失败！<br/>" + response.msg);
					}
				},//采用标签形式实现 按钮资源的控制，并且实现资源权限控制 ，自标签可以实现额外按钮添加，木有权限控制 type必填项目值为tbar(list页面按钮) 或toolbar（detail页面按钮）
				//如果不写code 那么url必须唯一
				// id表示id按钮id，text：按钮显示名称  icon：按钮显示图标 click:按钮对应放方法 position 按钮放置在权限按钮前后 ，splitLine:是否显示分割线
				<tbar:toolBar type="toolbar">
					<tbar:tbar id="note1" text="示例说明1" icon="help" click="note" position="before" />
					<tbar:tbar id="note2" text="示例说明2" icon="help" click="note" position="before"/>
					<tbar:tbar id="note3" text="示例说明3" icon="help" click="note" />
					<tbar:tbar id="note4" text="示例说明4" icon="help" click="function test(){alert(\"aaaaa\")}"/>
				</tbar:toolBar>
				
			});
		});
        
        function note(){
        	alert(1)
        }
    </script>
</head>
<body>
<form id="form1" action="demo/crud/save.do" getAction="demo/crud/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">20个字符：</td>
            <td class="l-t-td-right"><input name="strVal" type="text" ltype="text" validate="{required:true,maxlength:50}" /></td>
        </tr>
        <tr>
            <td class="l-t-td-left">数字：</td>
            <td class="l-t-td-right"><input name="intVal" type="text" ltype="number" validate="{required:true,maxlength:10}" /></td>
        </tr>
        <tr>
            <td class="l-t-td-left">日期：</td>
            <td class="l-t-td-right"><input name="dateVal" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
        </tr>
        <tr>
            <td class="l-t-td-left">文本：</td>
            <td class="l-t-td-right"><textarea name="text" rows="5" validate="{required:true,maxlength:500}"></textarea></td>
        </tr>
    </table>
</form>
</body>
</html>
