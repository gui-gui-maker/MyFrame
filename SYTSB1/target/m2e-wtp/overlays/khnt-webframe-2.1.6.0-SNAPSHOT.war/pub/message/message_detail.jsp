<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>发送消息</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
    $(function() {
        $("#form1").initForm({
            success : function(response) {
                if (response.success) {
                    top.$.notice('发送成功！', 3);
                    api.data.Qm.refreshGrid();
                    api.close();
                } else {
                    $.ligerDialog.error("发送失败!<br/>" + response.msg);
                }
            },
            toolbar : [ {
                text : "发送",
                icon : "save",
                click : saveForm
            }, {
                text : "取消",
                icon : "cancel",
                click : function() {
                    api.close();
                }
            } ]
        });

    });

    function saveForm() {
        var msgType = $("#type-txt").ligerGetCheckBoxManager().getValue();
        if(msgType==""){
            $.ligerDialog.alert("您没有选择任何消息类型！");
            return;
        }
        var personIds = $("#receiversId").val();
        if (msgType.indexOf("system") != -1 && personIds == "") {
            $.ligerDialog.alert("请选择消息接收人！");
            return;
        }

        var receivers = [];
        if (msgType.indexOf("sms") != -1){
            if(personIds == "" && phones == "") {
                $.ligerDialog.alert("请选择消息接收人或者输入电话号码！");
                return;
            }
            if ($("#content").val().length > 140) {
                $.ligerDialog.alert("短信内容过长，不能超过140字！");
                return;
            }
            var phones = $("#phone_account").val().replace(/^\s+|\s+$/, "").replace(/\s+/g, ",").replace(/，/g, ",")
                            .replace(/,+/g, ",").replace(/^,|,$/, "").split(",");
            for(var i in phones){
            	receivers.push({account: phones[i],type: "sms"});
            }
        }
       
        if (msgType.indexOf("email") != -1){
            if(personIds == "" && emails == "") {
                $.ligerDialog.alert("请选择消息接收人或者输入邮箱地址！");
                return;
            }
            var emails = $("#email_account").val().replace(/^\s+|\s+$/, "").replace(/\s+/g, ",").replace(/，/g, ",").replace(/,+/g, ",").replace(/^,|,$/, "").split(",");
            for(var i in emails){
            	receivers.push({account: emails[i],type: "email"});
            }
        }

        if(personIds){
            var personNames = $("#receiversName").val().split(",");
            var idsArr = personIds.split(",");
            for ( var idx in idsArr) {
                if (idsArr[idx]){
                    receivers.push({
                        personId : idsArr[idx],
                        personName : personNames[idx]
                    });
                }
            }
        }
        $("#receivers").val($.ligerui.toJSON(receivers));
        $("#form1").submit();
    }

    function selectUser() {
        selectUnitOrUser(1, 1, "receiversId", "receiversName");
    }

    function changeMsgType(val) {
        if (val.indexOf("email") > -1) {
            $("#email_tr").show();
        } else {
            $("#email_tr").hide();
            $("#email_account").val("");
        }
        if (val.indexOf("sms") > -1) {
            $("#phone_tr").show();
        } else {
            $("#phone_tr").hide();
            $("#phone_account").val("");
        }
    }
</script>
</head>
<body>
    <form id="form1" action="pub/message/send.do" style="padding-top: 1em;">
        <input type="hidden" id="id" name="id" /> <input type="hidden" id="receivers" name="receivers" /> <input
            type="hidden" id="receiversId" />
        <table class="l-detail-table">
            <tr>
                <td class="l-t-td-left">消息类型：</td>
                <td class="l-t-td-right"><u:combo ltype="checkboxGroup" name="type"
                        code="sys_message_types" attribute="onChange:changeMsgType"
                        validate="required:true" /></td>
            </tr>
            <tr id="receivers_tr">
                <td class="l-t-td-left">消息接收人：</td>
                <td class="l-t-td-right"><textarea id="receiversName" name="receiversName" rows="3"
                        readonly="readonly" ligerui="{iconItems:[{icon:'user',click:selectUser}]}"></textarea></td>
                </td>
            </tr>
            <tr id="phone_tr" style="display:none;">
                <td class="l-t-td-left">电话号码：</td>
                <td class="l-t-td-right"><textarea id="phone_account" name="phones" rows="3"></textarea></td>
            </tr>
            <tr id="email_tr" style="display:none;">
                <td class="l-t-td-left">邮箱地址：</td>
                <td class="l-t-td-right"><textarea id="email_account" name="emails" rows="3"></textarea></td>
            </tr>
            <tr>
                <td class="l-t-td-left">发送时间：</td>
                <td class="l-t-td-right"><input name="sendTime" type="text" ltype='date'
                    validate="{maxlength:30}" ligerui="{format:'yyyy-MM-dd hh:mm'}" /></td>
            </tr>
            <tr>
                <td class="l-t-td-left">消息内容：</td>
                <td class="l-t-td-right"><textarea name="content" ltype='textarea' rows="7" id="content"
                        validate="{required:true}"></textarea></td>
            </tr>
        </table>
    </form>
</body>
</html>
