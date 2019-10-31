<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/k/kui-base-list.jsp" %>
    <title>通用查询示例</title>
    <script type="text/javascript">
        var qmUserConfig = {
            //sp_defaults: {columnWidth: 0.33, labelAlign: 'right', labelSeparator: '', labelWidth: 80, layout: "column"},//可以自己定义 layout:column,float
            sp_fields: [
                {label: "<font color=blue>select变radio</font>",name: "str3", compare: "=", value: "",xtype:"radioGroup"},
                {label: "input变select",id:"str2",name: "str2", compare: "like", value: ""},
                {label:"input变弹出框",id:"fid",name:"id",compare:"=",value:"",xtype:"combo",onBeforeOpen:f_selectContact}
            ],
            //定义按钮，可以直接是extjs的按钮
            tbar: [
                { text: "取查询条件", click: function () {
                    alert($.ligerui.toJSON(Qm.getQmgrid().get("parms")))
                }}
            ]
        };
        function f_selectContact()
        {
            $.ligerDialog.open({ title: '选择流水号', name:'winselector',width: 700, height: 300, url: 'demo/qm/qm_1_list.jsp', buttons: [
                { text: '确定', onclick: f_selectContactOK },
                { text: '取消', onclick: f_selectContactCancel }
            ]
            });
            return false;
        }
        function f_selectContactOK(item, dialog)
        {
            var fn = dialog.frame.Qm || dialog.frame.window.Qm;
            var data = fn.getValueByName("id");
            if (!data)
            {
                alert('请选择行!');
                return;
            }
            $.ligerui.get("fid")._changeValue(data,data);
            dialog.close();
        }
        function f_selectContactCancel(item, dialog)
        {
            dialog.close();
        }

        var tip;
        function tips(content) {
            if (!tip) {
                tip = top.$.dialog.notice(content,5);
            }
            tip.show();
            tip.content(content);
        }
    </script>
</head>
<body>
<div class="item-tm" isTitle="true">
    <div class="l-page-note">
        <div class="l-page-note-div">
            该页面展示一些js的高级应用：改变查询条件的默认类型，替换后台配置的格式化方法（对导出数据无效），取查询条件等
        </div>
    </div>
</div>
<qm:qm pageid="qm_02"/>
<script type="text/javascript">
    Qm.config.columnsInfo["str2"]["binddata"]=[["A","字符串2包含A"],["AB","字符串2包含AB"]];
    Qm.config.columnsInfo["str2"]["formater"]=function(a,b,c,d,e){
        var value=$.ligerui.get("str2").getValue();
        var row=Qm.getRow(a,b,c,d,e),thisValue=Qm.getThisValue(a,b,c,d,e);
        return thisValue.replace(value,'<font style="background-color: #ffff00">'+value+'</font>');
    }
</script>
</body>
</html>