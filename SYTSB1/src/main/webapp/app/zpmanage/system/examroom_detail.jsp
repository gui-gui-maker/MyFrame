<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
//                        如果不设置额外参数，不用调用此方法，初始化时会自动调用
            $("#formObj").initForm({    //参数设置示例
                success : function(responseText) {//处理成功
                    if (responseText.success) {
                        top.$.notice("保存成功！");
                        api.data.window.submitAction();//执行列表页面函数
                        api.close();
                    } else {

                            $.ligerDialog.error('保存失败' + responseText);
                    }
                }
            });
            $('#type-txt').change(function(){
                //根据考场类型设置是否选择考官
            var type=$('#type-txt').val();
            //alert(type);
            if(type=="笔试考场"){
                $('#examiner').val("");
//                $('#examiner').readonly=true;
            }else{
//                $('#examiner').readonly=false;
            }
            })


        });


    </script>
</head>
<body>
<form id="formObj" action="app/zp/examroom/save.do" getAction="app/zp/examroom/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
        <tr>
            <td class="l-t-td-left"> 考场名称：</td>
            <td class="l-t-td-right"><input name="name" type="text" ltype='text'
                                            validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left"> 考场编号：</td>
            <td class="l-t-td-right"><input name="num" type="text" ltype='text' validate="{required:true,maxlength:32}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 考场类型：</td>
            <td class="l-t-td-right"><u:combo name="type" code="zp0001" validate="required:true"></u:combo></td>
            <td class="l-t-td-left"> 是否启用：</td>
            <td class="l-t-td-right"><u:combo name="sign" code="isOrNot" validate="required:true"></u:combo></td>

        </tr>
        <tr>
            <td class="l-t-td-left"> 考官信息：</td>
            <td class="l-t-td-right" colspan="3"><textarea name="examiner" id="examiner" title="考官信息，在面试考场时候填写，填写考官姓名，用/号分隔" cols="70" rows="3" class="l-textarea"
                                                           style="width:100%"></textarea>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 考场地点：</td>
            <td class="l-t-td-right" colspan="3"><textarea name="remark" title="考场地点" cols="70" rows="3" class="l-textarea"
                                                           style="width:100%" ></textarea></td>
        </tr>
    </table>
</form>
</body>
</html>
