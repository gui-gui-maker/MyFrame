<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>选择机构=人员=角色</title>
<%@include file="/k/kui-base-list.jsp"%>
<script test="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script test="text/javascript">
	function selectUU(t, m) {
		selectUnitOrUser(t, m, "code", "name", function(data) {
			$.ligerDialog.alert("code:"+data.code + "<br/>name:" + data.name);
		});
	}
</script>
<style type="text/css">
p {
	margin: 1em 0;
	text-indent: 1em
}body{padding:10px;}
</style>
</head>
<body>
	code：<br/>
	<textarea id="code" style="width:500px;height:4em;"></textarea><br/>
	name：<br/>
	<textarea id="name" style="width:500px;height:4em;"></textarea>
	<p>
		<input type="button" class="l-button3" onclick="selectUU(0,0)" value="机构单选" /> 
		<input type="button" class="l-button3" onclick="selectUU(0,1)" value="机构多选" />
	</p>
	<p>
		<input type="button" class="l-button3" onclick="selectUU(1,0)" value="人员单选" /> 
		<input type="button" class="l-button3" onclick="selectUU(1,1)" value="人员多选" />
	</p>
	<p>
		<input type="button" class="l-button3" onclick="selectUU(2,0)" value="角色单选" />
		<input type="button" class="l-button3" onclick="selectUU(2,1)" value="角色多选" />
	</p>
	
<pre>
/**
 * 参数说明：
 * 参数3、4、5都是可选，但是【3、4】和【5】必须存在一个，否则页面获取不到选择结果。
 *	 
 * 参数1: 0.只选部门；1.只选人员；2.角色
 * 参数2：0.单选；1.多选;
 * 参数3: 要填充的code字段控件id，可为空;
 * 参数4：要填充的name字段控件id，可为空；
 * 参数5：选择完成回调方法，可为空
 */
selectUnitOrUser(2, 0, "code", "name", function(data) {
	$.ligerDialog.alert("code:"+data.code + "$lt;br/$gt;name:" + data.name);
});
	</pre>
</body>
</html>