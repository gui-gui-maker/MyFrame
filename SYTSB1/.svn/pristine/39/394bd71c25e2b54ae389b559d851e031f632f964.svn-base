<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">
    var tbar="";
 	$(function () {
         tbar=[{ text: '作废', id: 'up', icon: 'save', click: directChange},
             { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
         if ("${param.pageStatus}"=="detail")
 	        tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
         $("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    });
        function directChange(){ 
        	var obj=$("#form1").validate().form();
    	 if(obj){
    		 $("#form1").submit();
    	 }else{
    		 return;
    	}} 
        function choosefile(){
            top.$.dialog({
            	width: 900,
                height: 450,
                lock: true,
                parent: api,
                title: "选择文件",
                content: 'url:app/qualitymanage/file_list.jsp',
                cancel: true,
                ok: function(){
                    var p = this.iframe.contentWindow.getSelectedPerson();
                    if(!p){
                 		top.$.dialog.tips('请选择文件！',4,'k/kui/skins/icons/32X32/hits.png',null,0);
                        return false;
                    }
                    $("#fileId").val(p.fileid);
                    $("#fileName").val(p.name);
                    $("#qualityXybzFileId").val(p.id);
                }
            });
        }
    </script>
</head>
<body>
	<form id="form1" action="update/abolish/save.do" getAction="update/abolish/detail.do?id=${param.id}">
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="status" name="status"/>
		<input type="hidden" id="qualityXybzFileId" name="qualityXybzFileId"/>
		
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>
							作废
						</div>
					</legend>
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table" id="">
				
					<tr>
						<td class="l-t-td-left">文件名称</td>
         				<td class="l-t-td-right" ><input  validate="{required:true}" ltype="text"  name="fileName" id="fileName" 
			         		type="text" ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:choosefile}]}" onclick="choosefile();" title="点击选择文件"/></td>
			         	
			         	<td class="l-t-td-left">文件编号</td>
			         	<td class="l-t-td-right"><input ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:choosefile}]}" onclick="choosefile();" title="点击选择文件" ligerUi="{readonly:true}" id="fileId" name="fileId" type="text" ltype="text" validate="{required:true}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">作废原因</td>
						<td class="l-t-td-right" colspan="3"><textarea name="abolishReason" id="abolishReason" rows="5" cols="25" class="l-textarea" validate="{maxlength:2000}" validate="{required:true}"></textarea></td>
					</tr>
					
					</table>
			</fieldset>
	</form>
</body>
</html>