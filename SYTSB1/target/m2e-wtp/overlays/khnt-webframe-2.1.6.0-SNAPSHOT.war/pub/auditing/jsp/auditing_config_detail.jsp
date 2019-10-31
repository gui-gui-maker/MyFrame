<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>审计配置管理</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#form1").initForm({
			success: function(resp) {
				if (resp.success) {
					top.$.dialog.notice('保存成功!',3);
					api.data.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败!<br/>' + resp.msg);
				}
			},
			getSuccess: function(resp){
				if("${param.status}"=="detail")
					$("#methodName").html(resp.data.methodFullName);
				else{
					fillMethodList([{id:resp.data.methodShortName,text:resp.data.methodFullName}]);
					$("#methodName").ligerGetComboBoxManager().selectValue(resp.data.methodShortName);
				}
			}
		});
	});
	
	function listMethod(){
		var className = $("#moduleClassName").val();
		if($.kh.isNull(className)) return;
		
		var lastQStr = $("#moduleClassName").data("lastqstring");
		if(className==lastQStr){
			var cacheData = $("#moduleClassName").data("cacheData");
			fillMethodList(cacheData);
		}
		else{
			$.getJSON("khnt/auditing/config/listMethod.do?q="+className,function(resp){
				if(resp.success){
					fillMethodList(resp.data);
					$("#moduleClassName").data("lastqstring",className);
					$("#moduleClassName").data("cacheData",resp.data);
				}
			});
		}
	}
	
	function fillMethodList(data){
		var combo = $("#methodName").ligerGetComboBoxManager();
		combo.options.data = data;
		combo.setData(data);
	}
	
	function methodSelected(val,text){
		$("#methodShortName").val(val);
		$("#methodFullName").val(text);
	}
	
	function classChange(){
		$("#methodShortName").val("");
		$("#methodFullName").val("");
		$("#methodName").ligerGetComboBoxManager().selectValue("");
		fillMethodList([]);
	}
</script>
</head>
<body>
	<form method="post" action="khnt/auditing/config/save.do" id="form1"
		getAction="khnt/auditing/config/detail.do?id=${param.id}" name="form1">
		<input type="hidden" name="id" />
		<input type="hidden" name="methodShortName" id="methodShortName" />
		<input type="hidden" name="methodFullName" id="methodFullName" />
		<table border="0" cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left" style="width: 100px">事件名称：</td>
				<td class="l-t-td-right" colspan='5'><input name="eventName" type="text"
					ligerui="{explain:'描述审计事件的业务名称'}"
					ltype='text' validate="{required:true,maxlength:64}"></td>
			</tr>
			<tr>
				<td class="l-t-td-left">审计类名：</td>
				<td class="l-t-td-right" colspan='5'><input name="moduleClassName" type="text"
					ligerui="{autocomplete:{url:'khnt/auditing/config/listPackageClass.do',
						option:{max:100,trigerChar:'.',displayColumn:[{name:'data',width:'100%'}]}},
						explain:'本事件所监控的实现类的完整类名（包含包路径）如：com.khnt.auditing.service.AuditingManager,系统监听用户的输入动作，当输入符号“.”时自动提示并显示子包和该属于该包的类列表，可用上下方向键加回车键选择并确定。'}"
					id="moduleClassName" ltype='text' validate="{required:true,maxlength:500}" onchange="classChange()" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">方法名称：</td>
				<td class="l-t-td-right" colspan='5'><input type="text" name="methodName" onfocus="listMethod()"
					ligerui="{onSelected:methodSelected,explain:'需要审计的业务事件的业务方法名称'}" id="methodName" ltype='select' validate="{required:true}" /> </td>
			</tr>
			<tr>
				<td class="l-t-td-left">参数位置：</td>
				<td class="l-t-td-right" colspan='5'><input name="paramIndex" type="text" ltype='text'
					ligerui="{explain:'说明此审计项目所需要记录的相关信息存在于方法参数列表和返回值当中的具体位置，用整数表示，如果审计的是方法的传入参数，采用数组下标的方式表示，如0，1，2，3...如果审计的是方法返回值则表示为-1。可以同时提供多个值并用符号“,”隔开，表示此审计需要记录多个参数和返回值信息'}"
					validate="{required:true,maxlength:100}" /></td>
			</tr>
            <tr>
                <td class="l-t-td-left">业务审计器：</td>
                <td class="l-t-td-right" colspan='5'><input name="busAuditor" type="text" id="busAuditor"
                    ltype="text" validate="{required:false,maxlength:1000}" /></td>
            </tr>
			<tr>
				<td class="l-t-td-left">是否启用：</td>
				<td class="l-t-td-right"><input name="lockedStatus" type="radio" id="enable"
					ltype="radioGroup" validate="{required:true}"
					ligerui="{value:'1',data:[{id:'1',text:'是'},{id:'0',text:'否'}]}" /></td>
			<c:if test="${param.status=='detail'}">
			  <td class="l-t-td-left">前置审计：</td>
                <td class="l-t-td-right"><input name="preAudit" type="radio" id="preAudits"
                    ltype="radioGroup" validate="{required:true}"
                    ligerui="{value:'1',data:[{id:'true',text:'是'},{id:'false',text:'否'}]}" /></td>
                <td class="l-t-td-left">后置审计：</td>
                <td class="l-t-td-right"><input name="afterAudit" type="radio" id="afterAudit"
                    ltype="radioGroup" validate="{required:true}"
                    ligerui="{value:'1',data:[{id:'true',text:'是'},{id:'false',text:'否'}]}" /></td>
			</c:if>
            <c:if test="${param.status!='detail'}">
                 <td class="l-t-td-left">前置审计：</td>
                <td class="l-t-td-right"><input name="preAudit" type="radio" id="preAudits"
                    ltype="radioGroup" validate="{required:true}"
                    ligerui="{value:'1',data:[{id:'1',text:'是'},{id:'0',text:'否'}]}" /></td>
                <td class="l-t-td-left">后置审计：</td>
                <td class="l-t-td-right"><input name="afterAudit" type="radio" id="afterAudit"
                    ltype="radioGroup" validate="{required:true}"
                    ligerui="{value:'1',data:[{id:'1',text:'是'},{id:'0',text:'否'}]}" /></td>
            </c:if>  
            </tr>
		</table>
	</form>
</body>
</html>