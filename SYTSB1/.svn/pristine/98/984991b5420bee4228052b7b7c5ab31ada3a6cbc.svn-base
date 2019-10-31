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
                    { text:'保存', click:function(){savepay();}, icon:'save'},
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
        });

        function savepay(){
//            alert($('#jfje').val());
            if($('#jfje1').val()==""){
               alert("缴费金额为填写！");
               return;
            }else{
               // alert($('#jfje1').val());
                $('#formJl').submit();
               // setTimeout(function ()
              //  {
              //      top.$.dialog.notice({content:'保存成功'});
              //      api.close();
              //      api.data.window.submitAction();
              //  }, 1000);
            }

        }


    </script>
</head>
<body>
<div class="navtab">
<div title="现场缴费信息" lselected="true" style="height: 400px">
<form id="formJl" action="app/zp/jfjl/save.do"  >
    <input type="hidden" name="fkHrZpJltd" value="${zpJltd.id}"/>
<fieldset class="l-fieldset">
    <legend class="l-legend">
        <div>投递信息</div>
    </legend>
    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">姓名：</td>
            <td class="l-t-td-right">
                <input name="bkUser" type="text" ltype="text" value="${zpJlxx.name}"  ligerui="{disabled:true}" />
            </td>
            <td class="l-t-td-left">身份证号码：</td>
            <td class="l-t-td-right">
                <input name="certificatesNum" value="${zpJlxx.certificatesNum}" type="text" ltype="text" ligerui="{disabled:true}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">投递岗位：</td>
            <td class="l-t-td-right">
                <input name="bkGw" type="text" ltype="text" value="${zpXqxx.gwName}"  ligerui="{disabled:true}" />
            </td>
            <td class="l-t-td-left">岗位编码：</td>
            <td class="l-t-td-right">
                <input name="gwNum"  value="${zpXqxx.gwNum}" type="text" ltype="text" ligerui="{disabled:true}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">投递部门：</td>
            <td class="l-t-td-right">
                <input name="bkBm"  value="${zpXqxx.depName}" type="text" ltype="text" ligerui="{disabled:true}"/>
            </td>
            <td class="l-t-td-left">投递机构：</td>
            <td class="l-t-td-right">
                <input name="orgName" type="text" ltype="text" value="${zpXqxx.orgName}"  ligerui="{disabled:true}" />
            </td>
        </tr>
        <tr>

            <td class="l-t-td-left">投递时间：</td>
            <td class="l-t-td-right">
                <input name="bkTime"  value="${zpJltd.tdTime}" type="text" ltype="date" ligerui="{disabled:true}"/>
            </td>
            <td class="l-t-td-left">&nbsp;</td>
            <td class="l-t-td-right">
                &nbsp;
            </td>
        </tr>
    </table>
</fieldset>
    <c:if test="${param.status!='detail'}">
        <fieldset class="l-fieldset">
            <legend class="l-legend">
                <div>缴费信息</div>
            </legend>
            <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
                <tr>
                    <td class="l-t-td-left">缴费金额：</td>
                    <td class="l-t-td-right">
                        <input name="jfje1" id="jfje1"  value="${system.writeMoney }" type="text"  ligerui="{disabled:true}" ltype="text" />
                    </td>
                    <td class="l-t-td-left">&nbsp;</td>
                    <td class="l-t-td-right">
                        &nbsp;
                    </td>
                </tr>

            </table>
        </fieldset>
    </c:if>
    <c:if test="${param.status=='detail'}">
        <c:if test="${zpJfjl!=null}">
        <fieldset class="l-fieldset">
            <legend class="l-legend">
                <div>缴费信息</div>
            </legend>
            <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
                <tr>
                    <td class="l-t-td-left">缴费金额：</td>
                    <td class="l-t-td-right">
                        <input name="jfje"  value="${zpJfjl.jfje}" type="text"  ltype="text" />
                    </td>
                    <td class="l-t-td-left">缴费方式：</td>
                    <td class="l-t-td-right">
                        <input name="jffs"  value="${zpJfjl.jffs}" type="text"  ltype="text" />
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">收费人：</td>
                    <td class="l-t-td-right">
                        <input name="czUser"  value="${zpJfjl.czUser}" type="text"  ltype="text" />
                    </td>
                    <td class="l-t-td-left">收费时间：</td>
                    <td class="l-t-td-right">
                        <input name="jfTime"  value="${zpJfjl.jfTime}" type="text"  ltype="date" />
                    </td>
                </tr>

            </table>
        </fieldset>
            </c:if>
    </c:if>

</form>

</div>

<%--<c:if test="${status=='detail'}">--%>
    <div id="rz" title="操作日志">
        <form id="formRz" action="" method="get" getAction="app/zp/rz/detailRz.do?fkId=${param.id}" >
            <input type="hidden" name="id">
            <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
                <tr>
                    <td class="l-t-td-left">业务名称：</td>
                    <td class="l-t-td-right"><input name="ywName" title="业务名称" type="text" ltype='text'
                                                    validate="{required:true}" ligerui="{disabled:true}">
                    </td>
                    <td class="l-t-td-left">操作人：</td>
                    <td class="l-t-td-right"><input type="text" name="name" title="操作人" ltype="text" validate="{required:true}" ligerui="{disabled:true}"/>
                    </td>
                    <td class="l-t-td-left">操作时间：</td>
                    <td class="l-t-td-right"><input name="time" title="操作时间" type="text" ltype='date'
                                                    validate="{required:true}" ligerui="{format:'yyyy-MM-dd hh:mm',disabled:true}" /></td>
                </tr>

            </table>
        </form>
        <script type="text/javascript">
            $("#formRz").initFormList({
//                opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
//                opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
//                action:"",//保存数据或其它操作的action
                actionParam:{"fkId":$("#formXq>[name=id]")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
//                    delAction:"app/zp/jcxx/delete.do",//删除数据的action
//                delActionParam:{"id":$},//默认为选择行的id
//                getAction:"",//取数据的action
//                getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
                onSelectRow:function (rowdata, rowindex) {
                    $("#formRz").setValues(rowdata);
                },
                toolbar:null,
                columns:[
                    //此部分配置同grid
                    { display:'主键', name:'id', width:50, hide:true},
                    { display:'业务名称', name:'ywName', width:400},
                    { display:'操作人', width:150, name:'name'},
                    { display:'操作时间', type:"date",format:'yyyy-MM-dd hh:mm', name:'time', width:150}
                ]
            });
        </script>
    </div>
<%--</c:if>--%>
</div>

</body>
</html>
