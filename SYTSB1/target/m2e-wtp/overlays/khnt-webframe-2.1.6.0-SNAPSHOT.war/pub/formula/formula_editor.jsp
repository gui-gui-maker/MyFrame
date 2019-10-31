<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>业务规则公式设计器</title>
<%@include file="/k/kui-base-form.jsp"%>
<link type="text/css" rel="stylesheet" href="pub/formula/css/formula.css" />
<script type="text/javascript" src="pub/formula/js/formula_editor.js"></script>
<script type="text/javascript">
	$(function() {
		$("#formulaForm").initForm({
			toolbar:[ {
				text : "模拟测试",
				icon : "check",
				id : 'validate',
				click : testFormula
			}, {
				text : "保存公式",
				icon : "save",
				id : 'save',
				click : saveFormula
			}, {
				text : "清除公式",
				icon : "cut",
				id : 'cut',
				click : function() {
					$("#formula").val("");
					$("#formulaDesc").html("");
				}
			}, {
				text : "关闭",
				icon : "cancel",
				click : function() {
					api.close();
				}
			} ],
			success : function(response){
				if(response.success){
					top.$.notice("公式保存成功！",3);
					if(api.data.window)
						api.data.window.submitAction();
					if(api.data.callback)
						api.data.callback(response.data);
					api.close();
				}
				else{
					top.$.dialog.tips('公式保存失败！错误信息：<br />' + response.msg,5,"k/kui/images/icons/dialog/32X32/hits.png",null,0)
				}
			},
			getSuccess: function(response){
				if(response.success){
					$("#formulaDesc").html(response.data.desc);
					if("${param.name}"){
						$("#f_name").val("${param.name}");
					}
				}
			}
		});
		
		//公式要素列表
		$.getJSON("pub/formula/item/getItemsByTypes.do?types=${param.type}",function(resp) {
			if (resp.success) {
				formulaItemArr = resp.data;
				$.each(resp.data, function(i) {
					$("#itemVariableTree").append("<li>" + resp.data[i].name + "［" + resp.data[i].variable + 
							"］<a class='add-item' href='javascript:itemclick(\"" + resp.data[i].name + "\",\"" + resp.data[i].variable + "\");'></a></li>");
				});
				$("#itemVariableTree li").hover(function(){
					$(this).css("backgroundColor","#e6effb");
					$(this).find("a").show();
				},function(){
					$(this).css("backgroundColor","white");
					$(this).find("a").hide();
				});
			}
		});
		$(".oper-table .operator").click(function() {
			formulaOprAdd(this.id);
		});
		$(".oper-table .number-btn").click(function() {
			addNumberToFormula(this.value);
		});
		$("#formula").height($(".tbody td").height()/2-20);
	});

	var actionTreeNode = null;
	function itemclick(name, variable) {
		formulaItemAdd({name:name,variable:variable});
	}

	//保存公式
	function saveFormula() {
		var formulaDesc = $("#formulaDesc").html();
		$("#formulaDescField").val(formulaDesc);
		$("#formulaForm").submit();
	}

	//公式测试 
	function testFormula() {
		top.$.dialog({
			width : 500,
			height : 250,
			lock : true,
			parent : api,
			title : "公式测试",
			content : "url:pub/formula/formula_test.jsp",
			data : {
				typeCode : "${param.type}",
				formula : $("#formula").val(),
				formulaDesc : $("#formulaDesc").html()
			},
			cancel : true
		});
	}
</script>
</head>
<body>
	<form id="formulaForm" action="pub/formula/save.do" 
		getAction="pub/formula/detail.do?id=${param.id}">
		<input type="hidden" name="id" id="formulaId" value="${param.id}" />
		<input type="hidden" name="type" value="${param.type}" />
		<input type="hidden" name="desc" id="formulaDescField" />
		<table class="l-detail-table outer_tbl" border="1" cellspacing="0">
			<tr class="blank_row">
				<td style="width: 240px;"></td>
				<td></td>
				<td style="width: 300px;"></td>
			</tr>
			<tr style="height:30px;">
				<td class="l-t-td-right" colspan="3" style="padding:0;">
					<table class="inner-tbl">
						<tr>
							<td class="l-t-td-left" style="width:15%">公式名称：</td>
							<td class="l-t-td-right" style="width:40%">
								<input type="text" ltype="text" name="name" validate="{required:true,maxlength:200}" />
							</td>
							<td class="l-t-td-left" style="width:15%">唯一标识：</td>
							<td class="l-t-td-right" style="width:30%">
								<input type="text" ltype="text" name="uniqueName" validate="{required:true,maxlength:32}" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr class="theader">
				<td>
					<div class="l-layout-header l-layout-header-over">
						<div class="l-layout-header-toggle"></div>
						<div class="l-layout-header-inner">公式要素（双击添加）</div>
					</div>
				</td>
				<td>
					<div class="l-layout-header l-layout-header-over">
						<div class="l-layout-header-toggle"></div>
						<div class="l-layout-header-inner">公式</div>
					</div>
				</td>
				<td>
					<div class="l-layout-header l-layout-header-over">
						<div class="l-layout-header-toggle"></div>
						<div class="l-layout-header-inner">操作</div>
					</div>
				</td>
			</tr>
			<tr class="tbody">
				<td><ul id="itemVariableTree"></ul></td>
				<td style="overflow: hidden;" id="formula-exp">
					<div class="formulaDesc" id="formulaDesc"></div>
					<textarea id="formula" class="formula" name="code"
							validate="{required:true,maxlength:2000}" 
							onchange="parseFormulaToDesc()"></textarea>
				</td>
				<td style="padding-top: 1em">
					<table class="l-detail-table oper-table">
						<tr>
							<td><input type="button" value="+" title="加"
								class="operator l-button" id="add" /></td>
							<td><input type="button" value="-" title="减"
								class="operator l-button" id="subtract" /></td>
							<td><input type="button" value="×" title="乘"
								class="operator l-button" id="multiply" /></td>
							<td><input type="button" value="÷" title="除"
								class="operator l-button" id="divide" /></td>
						</tr>
						<tr>
							<td><input type="button" value="(" title="小括号（开始）"
								class="operator l-button" id="left_bracket" /></td>
							<td><input type="button" value=")" title="小括号（结束）"
								class="operator l-button" id="right_bracket" /></td>
							<td><input type="button" value="{" title="大括号（开始）"
								class="operator l-button" id="left_big_bracket" /></td>
							<td><input type="button" value="}" title="大括号（结束）"
								class="operator l-button" id="right_big_bracket" /></td>
						</tr>
						<tr>
							<td><input type="button" value="如果"
								class="operator l-button" id="if" /></td>
							<td><input type="button" value="那么"
								class="operator l-button" id="then" /></td>
							<td><input type="button" value="否则"
								class="operator l-button" id="else" /></td>
							<td><input type="button" value="大于"
								class="operator l-button" id="gt" /></td>
						</tr>
						<tr>
							<td><input type="button" value="大于等于"
								class="operator l-button" id="gte" /></td>
							<td><input type="button" value="等于"
								class="operator l-button" id="equal" /></td>
							<td><input type="button" value="不等于"
								class="operator l-button" id="no_equal" /></td>
							<td><input type="button" value="小于"
								class="operator l-button" id="lt" /></td>
						</tr>
						<tr>
							<td><input type="button" value="小于等于"
								class="operator l-button" id="lte" /></td>
							<td><input type="button" value="并且"
								class="operator l-button" id="and" /></td>
							<td><input type="button" value="或者"
								class="operator l-button" id="or" /></td>
							<td><input type="button" value="四舍五入"
								class="operator l-button" id="round" /></td>
						</tr>
						<tr>
							<td><input type="button" value="&crarr; 换行"
								class="operator l-button" id="enter" /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td><input type="button" value="&larr; 退格"
								class="backSpace l-button" onclick="doBackSpace()" /></td>
						</tr>
					</table>
					<table class="l-detail-table oper-table">
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><input type="button" value="1"
								class="number-btn l-button" /></td>
							<td><input type="button" value="2"
								class="number-btn l-button" /></td>
							<td><input type="button" value="3"
								class="number-btn l-button" /></td>
							<td><input type="button" value="4"
								class="number-btn l-button" /></td>
						</tr>
						<tr>
							<td><input type="button" value="5"
								class="number-btn l-button" /></td>
							<td><input type="button" value="6"
								class="number-btn l-button" /></td>
							<td><input type="button" value="7"
								class="number-btn l-button" /></td>
							<td><input type="button" value="8"
								class="number-btn l-button" /></td>
						</tr>
						<tr>
							<td><input type="button" value="9"
								class="number-btn l-button" /></td>
							<td><input type="button" value="0"
								class="number-btn l-button" /></td>
							<td><input type="button" value="."
								class="number-btn l-button" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
