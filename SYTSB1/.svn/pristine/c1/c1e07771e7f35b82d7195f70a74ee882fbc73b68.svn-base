<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title>短信详情</title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>

    <script type="text/javascript">
        $(function () {
            $("#form1").initForm({
            	getSuccess:function(res){
            		$("#form1").setValues(res.data.message);
            		$("#form1").setValues(res.data);
            	}
            });
        });
    </script>
</head>
<body>
<div class="title-tm">
	<div class="l-page-title has-icon has-note">
		<div class="l-page-title-div"></div>
		<div class="l-page-title-text"><h1>短信发送详情</h1></div>
		<div class="l-page-title-icon"><img src="k/kui/images/menu-icon/32/places.png" border="0"></div>
	</div>
</div>
<div class="scroll-tm">
<form id="form1" getAction="pub/message/mobile/getMobileMessage.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
    	<tr>
        	<td class="l-t-td-left"> &nbsp;</td>
            <td class="l-t-td-right">&nbsp;
            </td>
            <td class="l-t-td-left"> &nbsp;</td>
            <td class="l-t-td-right">&nbsp;
            </td>
            <td class="l-t-td-left"> &nbsp;</td>
            <td class="l-t-td-right">&nbsp;
            </td>
        </tr>
        <tr>
        	<td class="l-t-td-left"> 发送人：</td>
            <td class="l-t-td-right"><input name="senderName" type="text" ltype='text' validate="{required:true,maxlength:50}" />
            </td>
            <td class="l-t-td-left"> 接受人：</td>
            <td class="l-t-td-right"><input name="personName" type="text" ltype='text' validate="{required:true,maxlength:50}" />
            </td>
            <td class="l-t-td-left"> 手机号码：</td>
            <td class="l-t-td-right"><input name="account" type="text" ltype='text' validate="{required:true,maxlength:50}" />
            </td>
        </tr>
        <tr>
        	<td class="l-t-td-left"> 发送时间：</td>
            <td class="l-t-td-right"><input name="cretime" type="text" ltype='date'
                                            validate="{required:true}"
                                            ligerui="{format:'yyyy-MM-dd HH:mm:ss'}" value=""/>
            </td>
            <td class="l-t-td-left"> 创建时间：</td>
            <td class="l-t-td-right"><input name="createTime" type="text" ltype='date'
                                            validate="{required:true}"
                                            ligerui="{format:'yyyy-MM-dd HH:mm:ss'}" value=""/>
            </td>
            <td class="l-t-td-left"> 发送状态：</td>
            <td class="l-t-td-right"><u:combo name="status" code="pub_sms" validate="required:true" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 短信内容：</td>
            <td class="l-t-td-right" colspan="5">
            	<textarea name="content" cols="70" rows="3" class="l-textarea" validate="{required:true,maxlength:1000}"></textarea>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 发送结果：</td>
            <td class="l-t-td-right" colspan="5">
            	<textarea name="reason" cols="70" rows="3" class="l-textarea" validate="{required:true,maxlength:1000}"></textarea>
            </td>
        </tr>
        
    </table>
</form>
</div>
<div class="toolbar-tm">
	<div class="toolbar-tm-bottom"></div>
</div>
</body>
</html>
