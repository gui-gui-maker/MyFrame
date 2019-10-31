<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
 	<%@include file="/k/jqm/mobile-base.jsp" %>
 	<script type="text/javascript">
 		$(function(){
 			$("#formObj").transform("detail")
 			$("#formObj").setValues({idn:'11111',sex:'2',sex1:'1,2',sex2:'1'})
 		})
 	</script>
  </head>
  <body>
  	<form id="formObj">
  		<div data-role="fieldcontain" data-controltype="textinput">
		<label for="idn"> 身份证号 </label> <input name="idn" id="idn"
			placeholder="" value="" type="text">
		</div>
		<div data-role="fieldcontain" data-controltype="textinput">
		<label for="sex"> 性别 </label>
			<u:wbSelect id="sex" name="sex" code="pub_xb" attribute="code=\"pub_xb\";data-native-menu=\"false\";multiple=\"multiple\"" />
		</div>
		<div data-role="fieldcontain">
			<fieldset data-role="controlgroup">
			<legend>性别1</legend>
				<u:wbSelect id="sex1" name="sex1" code="pub_xb" type="checkbox" attribute="code=pub_xb" />
			</fieldset>
		</div>
		<div data-role="fieldcontain">
			<fieldset data-role="controlgroup">
			<legend>性别2</legend>
				<u:wbSelect id="sex2" name="sex2" code="pub_xb" type="radio"  attribute="code=pub_xb" />
			</fieldset>
		</div>
  	</form>
  </body>
</html>
