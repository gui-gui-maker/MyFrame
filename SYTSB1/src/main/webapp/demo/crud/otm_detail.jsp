<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    CurrentSessionUser user=(CurrentSessionUser)request.getSession().getAttribute("currentSessionUser");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
//          如果不设置额外参数，不用调用此方法，初始化时会自动调用
            $("#form").initForm({
                success : function(responseText) {//处理成功
                    if (responseText.success) {
                        top.$.dialog.notice({content:'保存成功'});
                        api.data.window.Qm.refreshGrid();
                        $("#id").val(responseText.data.id);
                    } else {
                        $.ligerDialog.error('保存失败' + responseText);
                    }
                },
                getSuccess:function(responseText){

                }
            });

        });

    </script>
</head>
<body>
<div class="navtab">
<div title="父亲信息" lselected="true">
    <form id="form" action="demo/onetomp/save.do" getAction="demo/onetomp/detail.do?id=${param.id}">
        <input type="hidden" name="id" id="id">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">

            <tr>
                <td class="l-t-td-left">父亲姓名：</td>
	                <td class="l-t-td-right">
	                	<input name="name" type="text" ltype='text' validate="{required:true,maxlength:32}"/>
	                </td>
            </tr>
        </table>

    </form>

</div>
<div  title="儿子信息">
    <form id="form1" action="demo/onetomf/save.do" method="get" getAction="demo/onetomf/fdetail.do?id=${param.id}" >
        <input type="hidden" name="id">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">儿子姓名：</td>
                <td class="l-t-td-right"><input name="name" type="text" ltype='text' validate="{required:true,maxlength:32}"/>
                </td>
            </tr>
        </table>
    </form>
    <script type="text/javascript">
	        $("#form1").initFormList({
				// opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
				// opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
				// action:"",//保存数据或其它操作的action
	            actionParam:{"oneToMP.id":$("#form>[name=id]")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
	            delAction:"demo/onetomf/delete.do",//删除数据的action
				 // delActionParam:{"id":$},//默认为选择行的id
				 // getAction:"",//取数据的action
				 // getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
	            onSelectRow:function (rowdata, rowindex) {
	                $("#form1").setValues(rowdata);
	            },
				// toolbar:[
				//  { text:'保存', click:function(){$("#formJc").submit();}, icon:'save'},
				//  { text:'删除', click:function(){$("#formJc").submit();}, icon:'save'}
				//  ],
	            columns:[
	                //此部分配置同grid
	                { display:'主键', name:'id', width:50, hide:true},
	                { display:'儿子姓名', name:'name', width:200}
	            ]
	        });
    	</script>
</div>
</div>
</body>
</html>
