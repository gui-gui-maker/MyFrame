<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>业务规则公式测试</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
    $(function () {
    	$("#formula").html(api.data.formula);
    	$("#formulaDesc").html(api.data.formulaDesc);
    	$("body").mask("正在测试中，请稍后...");
		$.post("pub/formula/testFormula.do",{"formula":api.data.formula,"typeCode":api.data.typeCode},function(response){
			if(response.success){
				displayTestResult(response);
			}
			else{
				$.ligerDialog.alert("测试公式失败！<br/>" + response.msg);
			}
			$("body").unmask();
		},"json");
    });
    
	//测试结果展示
	function displayTestResult(response){
		$("#result").html("<span class='variable test-val'>" + response.data + "</span>");
		if(response.context){
			var htmlStr = "";
			$.each(response.context,function(i,item){
				htmlStr += (item.name + "：<span class='variable'>" + item.variable + " = <span class='test-val'>" + item.testValues + "</span></span><br />");
			});
			$("#testValue").html(htmlStr);
		}
	}
</script>
<style type="text/css">
	table {height:100%;width:100%}
	table,td{
		border:1px lightblue solid;
		border-collapse: collapse;
		vertical-align:top;
	}
	.td_r{text-align:left;padding:5px;}
	.td_l{text-align:right;padding:5px;}
	.variable{color:blue;font-size:1.2em}
	.test-val{color:red;font-family:Consolas}
</style>
</head>
<body>
<div class="scroll-tm">
	<table>
        <tr>
            <td class="td_l" height="23" width="100">测试公式：</td>
            <td class="td_r" id="formula"></td>
        </tr><tr>
            <td class="td_l" height="23">公式描述：</td>
            <td class="td_r" id="formulaDesc"></td>
        </tr>
        <tr>
            <td class="td_l" height="23">测试结果：</td>
            <td class="td_r" id="result"></td>
        </tr> 
        <tr>
            <td class="td_l">测试数据：</td>
            <td class="td_r" id="testValue"></td>
        </tr>
    </table>
</div>
</body>
</html>