<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head  pageStatus="${status}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
            //加载机构信息
            $("#formJl").initForm({
                toolbar:[
                    { text:'保存', click:function(){saveAssign();}, icon:'save'},
                    { text:'关闭', click:function(){api.close();}, icon:'delete'}
                ],
                toolbarPosition:'bottom'
            });


            //加载岗位信息
            $("#examRoomIdtext").ligerComboBox({
                data: ${examRoomStr},
                isMultiSelect: false,
                valueField:'id',
                onSelected:function(val,text){
                    $('#examRoomId').val(val);
                }

            });
        });

        function saveAssign(){
//            alert($('#examRoomId').val());
            if($('#examRoomId').val()==""){
               alert("请选择考场！");
               return;
            }else{
                //alert("1");
                $('#formAssign').submit();
                setTimeout(function ()
                {
                    top.$.dialog.notice({content:'保存成功'});
                    api.close();
                    api.data.window.submitAction();
                }, 1000);
            }

        }


    </script>
</head>
<body>

<form id="formAssign" action="app/zp/jltd/saveAssign.do"  >
    <input type="hidden" name="ids" value="${ids}"/>
    <input name="examRoomId" id="examRoomId" type="hidden" value=""/>
<fieldset class="l-fieldset">
    <legend class="l-legend">
        <div>选择笔试考场</div>
    </legend>
    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">笔试考场选择：</td>
            <td class="l-t-td-right">
                <input name="examRoomIdtext" id="examRoomIdtext" type="text" ltype="text" value=""  validate="{required:true}" />
            </td>
            <td class="l-t-td-left">&nbsp;</td>
            <td class="l-t-td-right">&nbsp;
            </td>
        </tr>


    </table>
</fieldset>

        <fieldset class="l-fieldset">
            <legend class="l-legend">
                <div>考场人员信息</div>
            </legend>
            <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
                <tr>
                    <td class="l-t-td-left">考场人员信息：</td>
                    <td class="l-t-td-right" colspan="3">
                        <textarea name="kscn" title="考场人员信息" cols="70" rows="3" class="l-textarea"
                                  style="width:100%" readonly="true" ligerui="{disabled:true}" readonly="readonly">${nameStr}</textarea>
                    </td>

                </tr>

            </table>
        </fieldset>
</form>



</body>
</html>
