<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    CurrentSessionUser user= SecurityUtil.getSecurityUser();
	//user.getSysUser().getEmployee().getOrg().getId()
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        var status='${param.status}';
        var depid='<sec:authentication property="principal.unit.id" />';
        $(function () {
            <sec:authorize access="!hasRole('verify_zp_admin')">
            depid= <%=user.getSysUser().getEmployee().getOrg().getId()%>
            $('input[name="depName"]').val("<%=user.getSysUser().getEmployee().getOrg().getOrgName()%>");
            if (status!='detail') {
                $("#dep_id").ligerGetComboBoxManager().setDisabled();
			}
            </sec:authorize>
            if(status!='detail'){
            	//加载部门信息
                $("#dep_id").ligerComboBox({
               	valueField:'id',
   	            treeLeafOnly: true,
   	            tree: { 
   	            	url: 'rbac/org/orgTree.do?orgid='+depid,
   	            	checkbox:false,
   	            	onClick:function(data){
   	            		getDepName(data)
   	            	}
   	            }
                })
            }
            $("#formXq").initForm({
                success : function(responseText) {//处理成功
                    if (responseText.success) {
                        top.$.dialog.notice({content:'保存成功'});
                        api.data.window.submitAction();
                        $('#xq_id').val(responseText.data.id);
                        $('#sign').val(responseText.data.sign);
                        $('#createdBy').val(responseText.data.createdBy);
                        $('#createdTime').val(responseText.data.createdTime);
                    } else {
                            $.ligerDialog.error('保存失败' + responseText);
                    }
                },
                getSuccess:function(responseText){

                }
            });
        });
        function getDepName(data){
    		if(!data.data.children)
    		{
    			$('input[name="depName"]').val(data.data.text);
    		}
    	}
    </script>
</head>
<body>
<div class="navtab">
<div title="岗位需求信息" lselected="true" style="height: 400px">
    <form id="formXq" action="app/zp/xqxx/save.do" getAction="app/zp/xqxx/detail.do?id=${param.id}">
        <input type="hidden" name="id" id="xq_id">
        <input type="hidden" name="sign" id="sign">
        <input type="hidden" name="createdBy" id="createdBy">
        <input type="hidden" name="createdTime" id="createdTime"> 
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">招聘部门：</td>
                <td class="l-t-td-right">
                    <c:if test="${param.status!='detail'}">
                    	<input type="text"  id="dep_id" name="fkDepId"
											validate="{required:true}" ltype="text"/>
                        <input name="depName" type="hidden" />
                    </c:if>
                    <c:if test="${param.status=='detail'}">
                        <input name="depName" type="text" ltype="text"/>
                    </c:if>
                </td>
                 <td class="l-t-td-left">岗位名称：</td>
                <td class="l-t-td-right">
                        <input name="gwName" type="text" ltype="text" validate="{required:true,maxlength:32}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">学历：</td>
                <td class="l-t-td-right">
                    <u:combo name="academic" code="zpacademic" validate="required:true"></u:combo>
                </td>
                <td class="l-t-td-left">学位：</td>
                <td class="l-t-td-right">
                    <u:combo name="degree" code="zpxuewei" validate="required:true"></u:combo>
                </td>

            </tr>
            <tr>
                <td class="l-t-td-left">年龄限制：</td>
                <td class="l-t-td-right">
                    <input id="nlxz" name="nlxz" title="输入本岗位对年龄的要求" type="text" ltype='text'
                                                validate="{required:true,maxlength:32}" />
                </td>
                <td class="l-t-td-left">拟招聘人数：</td>
                <td class="l-t-td-right">
                	<input name="num" type="text" ltype='spinner' validate="{required:true}" ligerui="{type:'int'}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">用人方式：</td>
                <td class="l-t-td-right">
                    <u:combo name="useM" code="zpyrfs" validate="required:true"></u:combo>
                </td>
                <td class="l-t-td-left">招聘形式：</td>
                <td class="l-t-td-right">
                    <input type="radio" id="way" name="way" ltype="radioGroup" validate="{required:true}"
                               ligerui="{value:'1', data: [ { text:'外部', id:'1' }, { text:'内部', id:'0' }]}"/>
             
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">要求专业：</td>
                <td class="l-t-td-right" colspan="3">
                    <textarea name="yqzy" title="填写详细的专业信息" cols="70" rows="2" class="l-textarea"
                              style="width:100%" validate="{required:true,maxlength:512}"></textarea>
                </td>
            </tr>
             <tr>
                <td class="l-t-td-left">技术职称要求：</td>
                <td class="l-t-td-right" colspan="3">
                    <textarea name="jsyq" title="填写技术职称要求" cols="70" rows="3" class="l-textarea"
                                                            validate="{maxlength:512}"   style="width:100%" ></textarea>
                </td>
            </tr>
             <tr>
                <td class="l-t-td-left">其他要求：</td>
                <td class="l-t-td-right" colspan="3">
                    <textarea name="otheryq" title="" cols="70" rows="2" class="l-textarea"
                              style="width:100%" validate="{maxlength:512}"></textarea>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">备注：</td>
                <td class="l-t-td-right" colspan="3">
                    <textarea name="beizhu" title="备注" cols="70" rows="3" class="l-textarea"
                                                         validate="{maxlength:512}"  style="width:100%" ></textarea>
                </td>
            </tr>
        </table>

    </form>

</div>
<div id="test" title="教材信息">
    <form id="formJc" action="app/zp/jcxx/save.do" method="get" getAction="app/zp/jcxx/detailJc.do?id=${param.id}" >
        <input type="hidden" name="id">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">教材名称：</td>
                <td class="l-t-td-right"><input name="name" title="输入教材名称" type="text" ltype='text'
                                                validate="{required:true,maxlength:255}">
                </td>
                <td class="l-t-td-left">主编：</td>
                <td class="l-t-td-right"><input type="text" name="zb" title="输入主编信息" ltype="text" validate="{required:true,maxlength:32}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">出版社：</td>
                <td class="l-t-td-right"><input name="cbs" title="出版社" type="text" ltype='text'
                                                validate="{required:true,maxlength:255}" /></td>
                <td class="l-t-td-left">出版时间：</td>
                <td class="l-t-td-right"><input name="cbtime" type="text" title="输入出版时间" ltype='text'
                                                validate="{required:true,maxlength:32}" /></td>

            </tr>
            <tr>
                <td class="l-t-td-left">备注：</td>
                <td class="l-t-td-right" colspan="3"><textarea name="beizhu" title="备注" cols="70" rows="3" class="l-textarea"
                                                             validate="{maxlength:512}"   style="width:100%" ></textarea></td>
            </tr>
        </table>
    </form>
    <script type="text/javascript">
        $("#formJc").initFormList({
            actionParam:{"fkHrZpXqxxId.id":$("#formXq>[name=id]")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
                delAction:"app/zp/jcxx/delete.do",//删除数据的action
            onSelectRow:function (rowdata, rowindex) {
                $("#formJc").setValues(rowdata);
            },
            columns:[
                //此部分配置同grid
                { display:'主键', name:'id', width:50, hide:true},
                { display:'教材名称', name:'name', width:200},
                { display:'主编', width:150, name:'zb'},
                { display:'出版社', name:'cbs', width:200},
                { display:'出版时间', name:'cbtime', width:150}
            ]
        });
    </script>
</div>
    <c:if test="${param.status=='detail'}">
        <div id="test" title="操作日志">
            <form id="formRz" action="" method="get" getAction="app/zp/rz/detailRz.do?fkId=${param.id}" >
                <input type="hidden" name="id">
                <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
                    <tr>
                        <td class="l-t-td-left">业务名称：</td>
                        <td class="l-t-td-right"><input name="ywName" title="业务名称" type="text" ltype='text'
                                                        validate="{required:true}">
                        </td>
                        <td class="l-t-td-left">操作人：</td>
                        <td class="l-t-td-right"><input type="text" name="name" title="操作人" ltype="text" validate="{required:true}"/>
                        </td>
                        <td class="l-t-td-left">操作时间：</td>
                        <td class="l-t-td-right"><input name="time" title="操作时间" type="text" ltype='date'
                                                        validate="{required:true}" ligerui="{format:'yyyy-MM-dd hh:mm'}" /></td>
                    </tr>

                </table>
            </form>
            <script type="text/javascript">
                $("#formRz").initFormList({
                    actionParam:{"fkId":$("#formXq>[name=id]")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
                    onSelectRow:function (rowdata, rowindex) {
                        $("#formRz").setValues(rowdata);
                    },
            toolbar:[

            ],
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
    </c:if>
</div>

</body>
</html>
