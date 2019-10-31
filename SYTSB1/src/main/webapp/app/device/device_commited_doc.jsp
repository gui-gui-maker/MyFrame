<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
</head>
<script type="text/javascript">
$(function(){
	
    $("#form_tzsbAppDocumentFile").initFormList({
    	root:'list',
        getAction:"device/tzsbapp/listAppDocumentFile.do?appId=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        actionParam:{"fk_tzsb_application_id" : $("#formObj>#appId")},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
        delAction:'device/tzsbapp/delAppDocumentFile.do',	//删除数据的action
        delActionParam:{id:"id"},	//默认为选择行的id 
        columns:[
            { display:'告知书id', name:'fk_tzsb_application_id', width:'1%', hide:true},
            { display:'主键', name:'id', width:'1%', hide:true},
            { display: '文件资料名称', name: 'file_name',width:"25%"},
            { display: '篇幅和页数', width: "35%", name: 'page_num'},
            { display: '备注', name: 'remark', width: "38%"}
            ],
    });
});
	</script>
<body>
<form name="form_tzsbAppDocumentFile" id="form_tzsbAppDocumentFile" method="post"  action="device/tzsbapp/saveAppDocumentFile.do?status=${param.status}" >
         <input type="hidden" name="id" />
        	<input type="hidden" name="fk_tzsb_application_id" value="${param.id}"/>
           <table cellpadding="3" cellspacing="0" class="l-detail-table">
             <tr>
				<td class="l-t-td-left">文件资料名称：</td>
				<td class="l-t-td-right"  ><input name="file_name"
					validate="{required:true,maxlength:128}"  ltype='text' id="tzsb_app_document_file__file_name_1"  /></td>
					<td class="l-t-td-left">篇幅和页数：</td>
				<td class="l-t-td-right"><input name="page_num"
					validate="{required:true,maxlength:64}" ltype='text' id="tzsb_app_document_file__page_num_1" /></td>
			</tr>
			    <tr>
				<td class="l-t-td-left">备注：</td>
				<td class="l-t-td-right"  colspan="3"><input name="remark"
					validate="{required:true,maxlength:128}"  ltype='text' id="tzsb_app_document_file__remark_1" /></td>
			</tr>
	</table>
	</form>
</body>
</html>