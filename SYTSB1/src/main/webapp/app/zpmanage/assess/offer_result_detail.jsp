<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head  pageStatus="${param.status}">
    <title>直接考核-录取结果页面</title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
        	//加载机构信息

            $("#dep_id").ligerComboBox({
                valueField:'id',
                treeLeafOnly:false,
                tree:{
                    url:'app/teacher/orgTree.do',
                    checkbox:false,
                    idFieldName:'id',
                    parentIDFieldName:'pid',
                    nodeWidth:150
                },
                onSelected:function(value,text){
                    //获取该机构下面的部门
                    $("#depName").val(text);
                }
            });
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
        });

        function saveResults(){
                $('#formObj').submit();

        }

        function getDepName(data){
    		if(!data.data.children)
    		{
    			$('input[name="depName"]').val(data.data.text);
    		}
    	}
    </script>
</head>
<body>

<form id="formObj" action="app/zp/jltd/saveAssessOfferResult.do"  >
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
                <td class="l-t-td-left">总成绩:</td>
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
        </table>
    </fieldset>
        <fieldset class="l-fieldset">
            <legend class="l-legend">
                <div>录取结果</div>
            </legend>
            <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
                <tr>
                <td class="l-t-td-left">部门：</td>
                <td class="l-t-td-right" colspan="3">
                      <input name="fkDepId" id="dep_id" type="text" title="选择机构下面的部门" ltype='text' validate="{required:true}"/>
                      <input name="depName" id="depName" type="hidden" />
                </td>
            </tr>
            </table>
        </fieldset>
</form>

</body>
</html>
