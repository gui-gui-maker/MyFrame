<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head  pageStatus="${param.status}">
    <title>直接考核-复检结果结果录入页面</title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
            //加载机构信息
            $("#formObj").initForm({
                toolbar:[
                    { text:'保存', click:function(){saveResults();}, icon:'save'},
                    { text:'关闭', click:function(){api.close();}, icon:'delete'}
                ],
                toolbarPosition:'bottom',
                success : function(responseText) {//处理成功
                    if (responseText.success) {
                        top.$.dialog.notice({content:'操作成功！'});
                        api.data.window.submitAction();
                        api.close();
                    } else {
                        $.ligerDialog.error(responseText.msg)
                    }
                }
            });

            //根据体检情况动态改变页面上的参数
            $('#recheckResults').ligerRadioGroup().bind("change",function(data){
                if(data=="1"){
                    $('#recheck').css("display","none");
                    $('#reason').val("/");
                }else if(data=="0"){
                    $('#recheck').css("display","");
                    $('#reason').val("");
                }else if(data=="2"){
                    $('#recheck').css("display","none");
                    $('#reason').val("/");
                }
            });


        });

        function saveResults(){
                $('#formObj').submit();

        }
        function test1(data){
        	if(data=="1"){
                $('#recheck').css("display","none");
                $('#reason').val("/");
            }else if(data=="0"){
                $('#recheck').css("display","");
                $('#reason').val("");
            }else if(data=="2"){
                $('#recheck').css("display","none");
                $('#reason').val("/");
            }
		}

    </script>
</head>
<body>

<form id="formObj" action="app/zp/jltd/saveOtherRecheckResult.do"  >
    <input type="hidden" name="id" value="${zpJltd.id}"/>
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>基础信息</div>
        </legend>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">姓名:</td>
                <td class="l-t-td-right">
                      ${zpJlxx.name}
                </td>
                <td class="l-t-td-left">身份证号码:</td>
                <td class="l-t-td-right">${zpJlxx.certificatesNum}
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">面试成绩:</td>
                <td class="l-t-td-right">
                    ${zpJltd.interviewResults}
                </td>
                <td class="l-t-td-left">总成绩成绩:</td>
                <td class="l-t-td-right">
                    ${total}
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">投递岗位:</td>
                <td class="l-t-td-right" colspan="3">
                    ${zpXqxx.gwNum}-${zpXqxx.gwName}-${zpXqxx.depName}-${zpXqxx.orgName}
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">体检不合格原因:</td>
                <td class="l-t-td-right" colspan="3">
                    ${reason}
                </td>
            </tr>
        </table>
    </fieldset>

        <fieldset class="l-fieldset">
            <legend class="l-legend">
                <div>复检结果</div>
            </legend>
            <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
                <tr>
                    <td class="l-t-td-left">复检结果:</td>
                    <td class="l-t-td-right" colspan="3">
                        <input type="radio" id="recheckResults" name="recheckResults" ltype="radioGroup" validate="{required:true}"
                               ligerui="{onChange:function(data){test1(data)},value:'1', data: [ { text:'合格', id:'1' }, { text:'不合格', id:'0' } , { text:'未复检', id:'2' } ]}"/>
                    </td>
                </tr>
                <tr id="recheck" style="display: none">
                    <td class="l-t-td-left">不合格原因:</td>
                    <td class="l-t-td-right" colspan="3">
                        <textarea name="reason" id="reason" title="不合格原因" cols="70" rows="3" class="l-textarea"
                                  style="width:100%" validate="{required:true}"  >/</textarea>
                    </td>
                </tr>

            </table>
        </fieldset>
</form>

</body>
</html>
