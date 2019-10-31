<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head  pageStatus="${param.status}">

    <title>岗位调整页面</title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
            //加载机构信息
            $("#post").initForm({
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
            //根据选择是否缺考
            $('#cancelPost').ligerRadioGroup().bind("change",function(data){
                if(data=="1"){
                    $('#num1').css("display","none");
                    $('#num2').css("display","none");
                    $('#num').val("1");
                }else if(data=="0"){
                    $('#num1').css("display","");
                    $('#num2').css("display","");
                    $('#num').val("");
                }
            });

        });

        function saveResults(){
                $('#formPost').submit();

        }
		function change(data){
			if(data=="1"){
                $('#num1').css("display","none");
                $('#num2').css("display","none");
                $('#num').val("1");
            }else if(data=="0"){
                $('#num1').css("display","");
                $('#num2').css("display","");
                $('#num').val("");
            }
		}

    </script>
</head>
<body>
<form id="formPost" action="app/zp/xqxx/saveAdjust.do"  >
    <input type="hidden" name="id" value="${post.id}"/>
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>岗位基本信息</div>
        </legend>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">岗位编码:</td>
                <td class="l-t-td-right">
                      ${post.gwNum}
                </td>
                <td class="l-t-td-left">岗位名称:</td>
                <td class="l-t-td-right">${post.gwName}
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">岗位有效期:</td>
                <td class="l-t-td-right">
                	${fn:substring(post.yxTime, 0, 10)}
                </td>
                <td class="l-t-td-left">岗位拟招聘人数:</td>
                <td class="l-t-td-right">${post.num}
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">本次投递人数:</td>
                <td class="l-t-td-right">
                    ${param.totalcount}
                </td>
                 <td class="l-t-td-left">未资格审核人数:</td>
               		<td class="l-t-td-right">${param.uncheckcount}
                	</td>
            </tr>
            <tr>
            	<td class="l-t-td-left">已经缴费人数:</td>
                <td class="l-t-td-right" colspan="3">${param.paycount}
                </td>
            </tr>
            
            

        </table>
    </fieldset>

        <fieldset class="l-fieldset">
            <legend class="l-legend">
                <div>调整内容</div>
            </legend>
            <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
                <tr>
                    <td class="l-t-td-left">是否取消招聘:</td>
                    <td class="l-t-td-right">
                        <input type="radio" id="cancelPost" name="cancelPost" ltype="radioGroup" validate="{required:true}"
                               ligerui="{onChange:function(data){change(data)},value:'1', data: [ { text:'是', id:'1' }, { text:'否', id:'0' }]}"/>
                    </td>
                    <td id="num1" style="display: none" class="l-t-td-left">调整后招聘人数:</td>
                    <td id="num2" style="display: none"  class="l-t-td-right">
                        <input name="num" id="num" value="1" type="text"  ltype="text" validate="{required:true,digits:true,min:1}" />
                    </td>
                </tr>


            </table>
        </fieldset>
</form>


</body>
</html>
