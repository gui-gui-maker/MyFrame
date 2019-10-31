<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="edit">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
//                        如果不设置额外参数，不用调用此方法，初始化时会自动调用
            $("#formObj").initForm({    //参数设置示例
                toolbarPosition:"top",
                success : function(responseText) {//处理成功
                    if (responseText.success) {
                        top.$.notice("保存成功！");
                    } else {

                            $.ligerDialog.error('保存失败' + responseText);
                    }
                }
            });


        });


    </script>
</head>
<body>
<form id="formObj" action="app/zp/system/save.do" getAction="app/zp/system/detail.do?id=sys001">
    <input type="hidden" id="id" name="id">
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>公开招聘设置</div>
        </legend>
    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
    	<tr>
            <td class="l-t-td-left"> 岗位有效期：</td>
            <td class="l-t-td-right"><input name="regEndTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}" validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left"> 岗位调整时间：</td>
            <td class="l-t-td-right"><input name="payEndTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"
                                            validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left"> 筛选面试人员时间：</td>
            <td class="l-t-td-right"><input name="writeResultsTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}" validate="{required:true,maxlength:32}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 筛选体检人员时间：</td>
            <td class="l-t-td-right"><input name="testStartTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"
                                            validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left"> 笔试缴费金额：</td>
            <td class="l-t-td-right"><input name="writeMoney" type="text" ltype='text' validate="{required:true,digits:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left"> 面试缴费金额：</td>
            <td class="l-t-td-right"><input name="interviewMoney" type="text" ltype='text' validate="{required:true,digits:true,maxlength:32}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">招聘结束时间：</td>
            <td class="l-t-td-right"><input name="checkEndTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}" validate="{required:true,maxlength:32}"/>
            </td>
              <td class="l-t-td-left"> 笔试时间：</td>
            <td class="l-t-td-right"><input name="writeTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}" validate="{required:true,maxlength:32}"/>
            <td class="l-t-td-left">面试时间：</td>
            <td class="l-t-td-right"><input name="interviewTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}" validate="{required:true,maxlength:32}"/>
            </td>
        </tr>
        <tr>
        	 <td class="l-t-td-left">缴费时间:</td>
            <td class="l-t-td-right" >
            	<input name="startPayTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}" validate="{required:true,maxlength:32}"/>至
            	<input name="endPayTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}" validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left">打印准考证时间：</td>
            <td class="l-t-td-right">
            	<input name="startPrintTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>至
            	<input name="endPrintTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left">退费时间：</td>
            <td class="l-t-td-right">
            	<input name="startTfTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>至
            	<input name="endTfTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>
            </td>
           
        </tr>
         <tr>
         	 <td class="l-t-td-left">面试审核时间：</td>
            <td class="l-t-td-right">
            	<input name="startIntCTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>至
            	<input name="endIntCTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left">体检时间：</td>
            <td class="l-t-td-right">
            	<input name="startTestTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>至
            	<input name="endTestTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left">体检地点：</td>
            <td class="l-t-td-right">
            	<input name="testWhere" type="text" ltype='text' validate="{required:true,maxlength:128}"/>
            </td>
        </tr>
         <tr>
         	 <td class="l-t-td-left">笔试成绩是否显示：</td>
            <td class="l-t-td-right">
            	<u:combo name="wrisopen" code="isOrNot" attribute="initValue:'0'" validate="required:true"></u:combo>
            </td>
            <td class="l-t-td-left">面试成绩是否显示：</td>
            <td class="l-t-td-right">
            	<u:combo name="inrisopen" code="isOrNot" attribute="initValue:'0'" validate="required:true"></u:combo>
            </td>
            <td class="l-t-td-left"></td>
            <td class="l-t-td-right">
            </td>
        </tr>
    </table>
    </fieldset>
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>直接考核设置</div>
        </legend>
    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
    	<tr>
            <td class="l-t-td-left"> 筛选体检人员时间：</td>
            <td class="l-t-td-right"><input name="testEndTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}" validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left"> 招聘结束时间：</td>
            <td class="l-t-td-right"><input name="resultsTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"
                                            validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left"> 缴费金额：</td>
            <td class="l-t-td-right"><input name="assessMoney" type="text" ltype='text' validate="{required:true,digits:true,maxlength:32}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 面试时间：</td>
            <td class="l-t-td-right"><input name="ainttime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}" validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left"> 面试审核时间：</td>
            <td class="l-t-td-right">
            	<input name="aintstime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>至
            	<input name="aintetime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>
            </td>
           	<td class="l-t-td-left"> 体检时间：</td>
            <td class="l-t-td-right">
            	<input name="ateststime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>至
            	<input name="atestetime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 体检地点：</td>
            <td class="l-t-td-right"><input name="atestwhere" type="text" ltype='text'  validate="{required:true,maxlength:128}"/>
            </td>
            <td class="l-t-td-left">面试成绩是否显示：</td>
            <td class="l-t-td-right">
            	<u:combo name="a_inrisopen" code="isOrNot" attribute="initValue:'0'" validate="required:true"></u:combo>
            </td>
           	<td class="l-t-td-left"> </td>
            <td class="l-t-td-right">
            </td>
        </tr>
    </table>
    </fieldset>
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>其他招聘设置</div>
        </legend>
    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
    	<tr>
            <td class="l-t-td-left"> 筛选体检人员时间：</td>
            <td class="l-t-td-right"><input name="qttestEndTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}" validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left"> 招聘结束时间：</td>
            <td class="l-t-td-right"><input name="qtresultsTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"
                                            validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left"> 缴费金额：</td>
            <td class="l-t-td-right"><input name="qtMoney" type="text" ltype='text' validate="{required:true,digits:true,maxlength:32}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 面试时间：</td>
            <td class="l-t-td-right"><input name="qtinttime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}" validate="{required:true,maxlength:32}"/>
            </td>
            <td class="l-t-td-left"> 面试审核时间：</td>
            <td class="l-t-td-right">
            	<input name="qtintstime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>至
            	<input name="qtintetime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>
            </td>
           	<td class="l-t-td-left"> 体检时间：</td>
            <td class="l-t-td-right">
            	<input name="qtteststime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>至
            	<input name="qttestetime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true,maxlength:32}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 体检地点：</td>
            <td class="l-t-td-right"><input name="qttestwhere" type="text" ltype='text'  validate="{required:true,maxlength:128}"/>
            </td>
            <td class="l-t-td-left">面试成绩是否显示：</td>
            <td class="l-t-td-right">
            	<u:combo name="qt_inrisopen" code="isOrNot" attribute="initValue:'0'" validate="required:true"></u:combo>
            </td>
           	<td class="l-t-td-left"> </td>
            <td class="l-t-td-right">
            </td>
        </tr>
    </table>
    </fieldset>
</form>
</body>
</html>
