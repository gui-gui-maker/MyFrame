<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus = "${param.pageStatus}">
<title>预警规则设置</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	var pageStatus = "${param.pageStatus}";
	var tbar="";
	$(function() {
		if(pageStatus=="detail"){
	 		tbar=[{text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}else if(pageStatus=="add" || pageStatus=="modify"){
	 		tbar=[{text: "保存", icon: "save", click: function(){
		      				//表单验证
					    	if ($("#form1").validate().form()) {
					    		save();
					    	}else{
					    		$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');
					    	}
		      			}},
				  {text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}
		
		$("#form1").initForm({
			getSuccess: function (response){
				
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
	})
	function save(){
        	var formData = $("#form1").getValues();
        	top.$.ajax({
                url: "report/yjsz/save1.do",
                type: "POST",
                dataType:'json',
                async: false,
                data: formData,
                success:function (data) {
                	 if(data.success){
                		 api.data.window.Qm.refreshGrid();
 		                 api.close();
                		 top.$.notice('保存成功！',3);												
                     }else{
                    	 $.ligerDialog.error(data.msg,3);	
                     }
                },
                error:function () {
                	$.ligerDialog.error('出错了！请重试！!',3);											
                }
            });
        }
</script>
</head>
<body>
	<div title="预警规则设置" id="formObj">
    <form id="form1" action="report/yjsz/save.do" getAction="report/yjsz/detail.do?id=${param.id}">
     <input type="hidden" name="id" id="id"/>
     <fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					预警规则设置
				</div>
			</legend>
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
	  <tr> 
        <td class="l-t-td-left"> 开始环节</td>
        <td class="l-t-td-right"> 
        <u:combo name="flow"  code="TJY2_BGYJ" validate="required:true"/>
        </td>
        <td class="l-t-td-left"> 结束环节</td>
        <td class="l-t-td-right"> 
        <u:combo name="flows"  code="TJY2_BGYJ" validate="required:true"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 是否启用</td>
        <td class="l-t-td-right" colspan="3"> 
        <u:combo name="state"  code="TJY2_YJ_STATUS" validate="required:true" attribute="initValue:'3'"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 短信推送</td>
        <td class="l-t-td-right"> 
        <u:combo name="duanxinPush"  code="TJY2_PUSH_MESSAGE" validate="required:true" attribute="initValue:'2'"/>
        </td>
        <td class="l-t-td-left"> 微信推送</td>
        <td class="l-t-td-right"> 
        <u:combo name="weixinPush"  code="TJY2_PUSH_MESSAGE" validate="required:true" attribute="initValue:'2'"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left"> 天数</td>
        <td class="l-t-td-right" colspan="3"> 
        <input name="tianshu" type="text" ltype="text" validate="{required:true,maxlength:50}" />
        </td>
       </tr>
      </table>
      </fieldset>
    </form> 
</div>

</body>
</html>
