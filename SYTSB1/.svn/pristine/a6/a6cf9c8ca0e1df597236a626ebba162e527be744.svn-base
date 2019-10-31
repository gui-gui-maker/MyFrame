<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head  pageStatus="${param.status}">
    <title>面试成绩录入页面</title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
            //加载机构信息
            $("#formResults").initForm({
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
            var status='${param.status}';
            if (status!='detail') {
            //根据选择是否缺考
            $('#examResults').ligerRadioGroup().bind("change",function(data){
                if(data=="1"){
                	$('#interviewResults').ligerGetTextBoxManager().setEnabled();
                    $('#interviewResults').val("");
                }else if(data=="0"){
                    $('#interviewResults').val("0");
                    $('#interviewResults').ligerGetTextBoxManager().setDisabled();
                }
            });

            }
        });

        function saveResults(){
                $('#formResults').submit();

        }
		function test1(data){
			if(data=="1"){
            	//$('#interviewResults').ligerGetTextBoxManager().setEnabled();
            	if($('#interviewResults').val()==''||$('#interviewResults').val()=='0')
            	{
            		$('#interviewResults').val("");
            	}
            }else if(data=="0"){
                $('#interviewResults').val("0");
               // $('#interviewResults').ligerGetTextBoxManager().setDisabled();
            }
		}

    </script>
</head>
<body>
<c:if test="${param.status=='detail'}">
<div class="navtab">
    <div title="面试成绩录入" lselected="true" style="height: 400px">
</c:if>
<form id="formResults" action="app/zp/jltd/saveInterviewResult.do?status=1"  >
    <input type="hidden" name="id" value="${zpJltd.id}"/>
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>报考基本信息</div>
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
                <td class="l-t-td-left">考场编号:</td>
                <td class="l-t-td-right">
                    ${examRoom.num}
                </td>
                <td class="l-t-td-left">考场名称:</td>
                <td class="l-t-td-right">${examRoom.name}
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">投递岗位:</td>
                <td class="l-t-td-right" colspan="3">
                    ${zpXqxx.gwNum}-${zpXqxx.gwName}-${zpXqxx.depName}-${zpXqxx.orgName}
                </td>

            </tr>


        </table>
    </fieldset>

        <fieldset class="l-fieldset">
            <legend class="l-legend">
                <div>成绩信息</div>
            </legend>
            <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
                <tr>
                    <td class="l-t-td-left">考试情况:</td>
                    <td class="l-t-td-right" colspan="3">
                        <c:if test="${param.status!='detail'}">
                            <input type="radio" id="examResults" name="examResults" ltype="radioGroup" validate="{required:true}"
                                   ligerui="{value:'1', data: [ { text:'正常', id:'1' }, { text:'缺考', id:'0' }],onChange:function(data){test1(data)}}"/>
                        </c:if>
                        <c:if test="${param.status=='detail'}">
                            <c:if test="${zpJltd.sign=='24'}">缺考</c:if>
                            <c:if test="${zpJltd.sign=='10'}">正常</c:if>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">面试成绩:</td>
                    <td class="l-t-td-right">
                        <c:if test="${param.status!='detail'}">
                            <input name="interviewResults" id="interviewResults"  value="${zpJltd.interviewResults}" type="text"  ltype="text" validate="{required:true}" />
                        </c:if>
                        <c:if test="${param.status=='detail'}">
                            ${zpJltd.interviewResults}
                        </c:if>

                    </td>
                    <td class="l-t-td-left">笔试成绩:</td>
                    <td class="l-t-td-right">
                            ${zpJltd.writeResults}
                    </td>

                </tr>
                <tr>
                    <td class="l-t-td-left">加分成绩:</td>
                    <td class="l-t-td-right">
                        ${zpJltd.bonusResults}
                    </td>
                    <td class="l-t-td-left"></td>
                    <td class="l-t-td-right">
                    </td>
                </tr>
 				<tr>
                    <td class="l-t-td-left">加分原因:</td>
                    <td class="l-t-td-right" colspan="3">
                            ${zpJltd.bonusReason}
                    </td>

                </tr>
            </table>
        </fieldset>
</form>

<c:if test="${param.status=='detail'}">
    </div>
<div id="rz" title="操作日志">
    <form id="formRz" action="" method="get" getAction="app/zp/rz/detailRz.do?fkId=${zpJltd.id}" >
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
//            actionParam:{"fkId":$("#formXq>[name=id]")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
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
    </div>
</c:if>

</body>
</html>
