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

            $("#org_id").ligerComboBox({
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
                    $('input[name="orgName"]').val(text);
                    //alert($('input[name="orgName"]').val());
                    $.ajax({
                        type:'post',
                        dataType:'html',
                        url:'app/teacher/deptTree.do?unitId='+value,
                        success:function(msg)
                        {
                            var data = eval(msg);
                            $('input[name="depName"]').val("");
                            $('input[name="depId"]').val("");
                            $('input[name="gwId"]').val("");
                            $('input[name="gwName"]').val("");
                            $("#dep_id").ligerGetComboBoxManager().clearContent();
                            $("#dep_id").ligerGetComboBoxManager().setData(data);
                        }
                    })
                }
            });
            
            //加载部门信息
            $("#dep_id").ligerComboBox({
                data:null,
                isMultiSelect:false,
                valueField:'id',
                onSelected:function(val,text){
                    //获取部门下面的岗位信息
                    $('input[name="depName"]').val(text);
                    $.ajax({
                        type:'post',
                        dataType:'html',
                        url:'app/teacher/postion.do?depId='+val,
                        success:function(msg)
                        {
                            // alert(msg);

                            // alert(msg);
                            var data = eval(msg);
                           // data.push({"id":"999999","text":"其他"});
                            //alert(data)
                            $('input[name="gwId"]').val("");
                            $('input[name="gwName"]').val("");
                            $("#gw_id").ligerGetComboBoxManager().clearContent();
                            $("#gw_id").ligerGetComboBoxManager().setData(data);
                        }
                    })
                }
            });
            //加载岗位信息
            $("#gw_id").ligerComboBox({
                data: null,
                isMultiSelect: false,
                valueField:'id',
                onSelected:function(val,text){
                    $('input[name="gwName"]').val(text);
                }});
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


    </script>
</head>
<body>

<form id="formObj" action="app/zp/jltd/saveOtherOfferResult.do"  >
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
                <td class="l-t-td-left">机构：</td>
                <td class="l-t-td-right">
                        <input name="fkOrgId" id="org_id" type="text" ltype='text' title="选择机构信息" validate="{required:true}"/>
                        <input name="orgName" type="hidden" />
                </td>
                <td class="l-t-td-left">部门：</td>
                <td class="l-t-td-right">
                        <input name="fkDepId" id="dep_id" type="text" title="选择机构下面的部门" ltype='text' validate="{required:true}"/>
                        <input name="depName" type="hidden" />
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">岗位：</td>
                <td class="l-t-td-right">
                        <input name="fkGwId" id="gw_id" type="text" title="选择岗位信息" ltype='text'
                               validate="{required:true}"/>
                        <input name="gwName" type="hidden" />
                </td>
                <td class="l-t-td-left">&nbsp;</td>
                <td class="l-t-td-right">&nbsp;</td>

            </tr>

            </table>
        </fieldset>
</form>

</body>
</html>
