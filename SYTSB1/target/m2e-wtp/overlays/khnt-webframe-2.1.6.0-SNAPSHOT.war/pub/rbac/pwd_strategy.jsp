<%@page import="com.khnt.base.Factory"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>密码策略 </title>
    <%@ include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
		var pwdExpiry = "<%=Factory.getSysPara().getProperty("SYS_PWD_EXPIRY_USE")%>";
		var maxExpiryDay = "<%=Factory.getSysPara().getProperty("SYS_PWD_MAX_DAYS")%>";
		var mustModifyPwd = "<%=Factory.getSysPara().getProperty("SYS_PWD_EXPIRY_MUST_MODPWD")%>";
		var pwdRuleUse = "<%=Factory.getSysPara().getProperty("SYS_PWD_RULE_USE")%>";
		var pwdRule = <%=Factory.getSysPara().getProperty("SYS_PWD_RULE")%>;
		var initPwd = "<%=Factory.getSysPara().getProperty("SYS_PWD_DEFAULT")%>";
		var mmdInitPwd = "<%=Factory.getSysPara().getProperty("SYS_PWD_MMD_INIT")%>";
	</script>
</head>
<body>
<form id="formObj">
	<fieldset class="l-fieldset">
		<legend class="l-legend">密码组合规则</legend>
		<table class="l-detail-table" style="width:350px;">
	        <tr>
	            <td class="l-t-td-left" style="width:120px;">启用：</td>
	            <td class="l-t-td-right">
	            	<input id="pwdRule" name="pwdRule" type="checkbox"  onchange="setRulesUsed(this)" ltype="checkBoxGroup" />
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left">最短位数：</td>
	            <td class="l-t-td-right">
	            	<input id="length" name="length" type="text" ltype='spinner' class="rule-item" 
	            		ligerui="{type:'int',suffixWidth:120,suffix:'位，0表示不限制',minValue:0,maxValue:16}" />
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left">包含字母：</td>
	            <td class="l-t-td-right">
	            	<input id="letter" name="letter" class="rule-item" type="checkbox" ltype="checkBoxGroup" />
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left">包含数字：</td>
	            <td class="l-t-td-right">
	            	<input id="numbers" name="numbers" class="rule-item" type="checkbox" ltype="checkBoxGroup" />
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left">其他字符：</td>
	            <td class="l-t-td-right">
	            	<input id="other" name="other" class="rule-item" type="checkbox" ltype="checkBoxGroup" />
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left"></td>
	            <td class="l-t-td-right">
	    			<button type="button" id="saveRule"></button>
	            </td>
            </tr>
	    </table>
	</fieldset>
	<fieldset class="l-fieldset">
		<legend class="l-legend">定期修改密码</legend>
		<table class="l-detail-table" style="width:300px;">
	        <tr>
	            <td class="l-t-td-left" style="width:120px;">启用：</td>
	            <td class="l-t-td-right">
	            	<input id="pwdExpiry" name="pwdExpiry" type="checkbox" onchange="setExpiryUsed(this)" ltype="checkBoxGroup" />
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left">密码有效期：</td>
	            <td class="l-t-td-right">
	            	<input id="maxDays" name="maxDays" type="text" ltype='spinner' 
	            		ligerui="{type:'int',suffix:'天',minValue:1,maxValue:365}" />
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left">过期必须修改：</td>
	            <td class="l-t-td-right">
	            	<input id="isMustModify" name="isMustModify" type="checkbox" ltype='checkBoxGroup' />
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left"></td>
	            <td class="l-t-td-right">
	    			<button type="button" id="saveExpiry"></button>
	            </td>
            </tr>
	    </table>
	</fieldset>
	<fieldset class="l-fieldset">
		<legend class="l-legend">初始密码</legend>
		<table class="l-detail-table" style="width:300px;">
	        <tr>
	            <td class="l-t-td-left" style="width:120px;">初始密码：</td>
	            <td class="l-t-td-right">
	            	<input id="initPwd" name="initPwd" type="password" ltype='text' />
	            </td>
	        </tr> 
	        <tr>
	            <td class="l-t-td-left">必须修改初始密码：</td>
	            <td class="l-t-td-right">
	            	<input id="mmpd" name="mmpd" type="checkbox" ltype='checkBoxGroup' />
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left"></td>
	            <td class="l-t-td-right">
	    			<button type="button" id="saveInitPwd"></button>
	            </td>
            </tr>
	    </table>
	</fieldset>
</form>
<script type="text/javascript" src="pub/rbac/js/_pwd_strategy.js"></script>
</body>
</html>
