<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
        <%
SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
String nowTime=""; 
nowTime = dateFormat.format(new Date());
%>
    <script type="text/javascript">
        $(function () {
			$("#form1").initForm({    //参数设置示例
				success: function (response) {
					if(response.success){
						top.$.notice("保存成功！",3);
						api.data.window.Qm.refreshGrid();
						api.close();
					}
					else{
						$.ligerDialog.error("操作失败！<br/>" + response.msg);
					}
				}
			});
		});
        function choosePerson(){
            top.$.dialog({
                width: 800,
                height: 450,
                lock: true,
                parent: api,
                title: "选择人员",
                content: 'url:app/common/person_choose.jsp',
                cancel: true,
                ok: function(){
                    var p = this.iframe.contentWindow.getSelectedPerson();
                    if(!p){
                        top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                        return false;
                    }
                    $("#employeeId").val(p.id);
                    $("#name").val(p.name);
                    $("#idno").val(p.id_no);
                    $("#telphone").val(p.mobile_tel);
                }
            });
        }
    </script>
</head>
<body>
<form id="form1" action="oa/car/driver/save.do" getAction="oa/car/driver/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <input name="unitId" type="hidden" value="<sec:authentication property="principal.unit.id"/>"/>
    <input type="hidden" name="employeeId" id="employeeId" />
    <input type="hidden" name="fileNumber" id="fileNumber" />
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">姓名：</td>
            <td class="l-t-td-right">
            <input id="name" name="name" type="text" ltype="text" validate="{required:true,maxlength:32}" onclick="choosePerson();" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/>
            </td>
            <td class="l-t-td-left">身份证号：</td>
            <td class="l-t-td-right"><input id="idno" name="idno" type="text" ltype="text" validate="{required:true,idno:true}" /></td>
        </tr>
        <tr>
            <td class="l-t-td-left">初次领证</br>日期：</td>
            <td class="l-t-td-right"><input name="cardDate" type="text" ltype='date'/></td>
            <td class="l-t-td-left">准驾车型：</td>
            <td class="l-t-td-right"><input name="quasiCarType" type="text" ltype="text" validate="{required:true}" /></td>
        </tr>
        <tr>
            <td class="l-t-td-left">有效起始</br>日期：</td>
            <td class="l-t-td-right"><input name="validDateStart" type="text" ltype='date'/></td>
            <td class="l-t-td-left">有效期限：</td>
            <td class="l-t-td-right"><input id="validYear" name="validYear" type="text" ltype='select' 
            		ligerui="{
								value:'',
								readonly:true,
								data: [ { text:'6年', id:'6年' },{ text:'10年', id:'10年' },{ text:'长期', id:'长期' }]
							}"/></td>
        </tr>
         <tr>
            <td class="l-t-td-left">实际驾龄：</td>
            <td class="l-t-td-right"><input name="driverAge" type="text" ltype='spinner' ligerui="{suffix:'年',type:'int',minValue:0}" /></td>
        	<td class="l-t-td-left">驾驶证状态：</td>
            <td class="l-t-td-right"><input name="status" type="text" ltype="text" validate="{required:true}" /></td>
        </tr>
        <tr>
        	<td class="l-t-td-left">扣分情况：</td>
            <td class="l-t-td-right"><input name="points" type="text" ltype='spinner' ligerui="{suffix:'分',type:'int',maxValue:0,minValue:-12}" /></td>
            <td class="l-t-td-left">联系电话：</td>
            <td class="l-t-td-right"><input id="telphone" name="telphone" type="text" ltype='text' validate="{required:true,maxlength:32}" /></td>
        </tr>
    </table>
</form>
</body>
</html>
