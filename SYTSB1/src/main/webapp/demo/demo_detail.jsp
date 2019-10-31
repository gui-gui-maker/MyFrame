<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="pub/rbac/js/area.js"></script>
    <script type="text/javascript">
        $(function () {
////                        如果不设置额外参数，不用调用此方法，初始化时会自动调用
			$("#form1").initForm({    //参数设置示例
//                toolbar:null,
				success: function (aaa) {
					$("#tree").ligerTree().treeManager.bind("success", function (data) {
						alert(data)
					});
					$("#tree").ligerTree().loadData();
				}
				//,toolbar:[{ text: '什么是什么，我是信sdfsdfsdfsdfsdfsdf w4t 1234rawefawef', click: function(){alert("ok thanks")} }]
			});
			$("#com1").ligerTip();
//            $("#test").transform("detail");
			$("#areaName").ligerComboBox({initValue: "1111", initText: "1111", valueFieldID: "regAreaId", textModel: true, onBeforeOpen: showAreaList});

			//弹出窗口选择数据
			$("#dialogCombo").ligerComboBox({initValue: "2222", iconCls: "l-icon-search", initText: "2222", valueFieldID: "regAreaId", textModel: true, onBeforeOpen: showDialogCombo});
		});

		function showDialogCombo() {
			var data = <u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from pub_code_table_values t,pub_code_table c where t.code_table_id=c.id and c.code='ba_dwxz'" type="tree" />;
			showTreeDialog(data, false, function (nodeData) {
				if (nodeData) {
					$("#dialogCombo").val(nodeData.text)
				}
			});
		}

		function test(value) {
			//alert(this.parent().html())
			//alert("test="+value)
			alert("test=" + this.input.parent().html());
		}
		function tt() {
			$("#form1").setValues({radio1: "1"})
		}
		function setComTree() {
			$("#com2-txt").ligerComboBox().setValue("")
		}
    </script>
</head>
<body>
<form id="form1" action="demo/test.json" getAction="demo/test.json">
    <input type="hidden" id="id" name="id">
    <input type="button" value="test" onclick="setComTree()">
    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">

        <tr>
            <td class="l-t-td-left">区划选择：</td>
            <td class="l-t-td-right"><input name="tt1" id="areaName" type="text" value="test"/></td>
            <td class="l-t-td-left">弹出窗口树选择：</td>
            <td class="l-t-td-right"><input name="tt2" id="dialogCombo" type="text" value=""/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">普通文本：</td>
            <td class="l-t-td-right"><input name="t1" type="text" value="test" ltype='text' validate="{required:true,maxlength:30}" ligerui="{explain:'请在这里写下你的大名',readonly:true,disabled:true}"/></td>
            <td class="l-t-td-left">后缀：</td>
            <td class="l-t-td-right"><input name="t2" type="text" ltype='text' value="直接就是值 value" ligerui="{suffix:'月'}" validate="{required:true,maxlength:10}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">自定后缀宽度：</td>
            <td class="l-t-td-right"><input name="t3" type="text" ltype='text' ligerui="{suffix:'后缀很长',suffixWidth:'60',nullText:'不能为空哈'}"
											validate="{required:true,maxlength:10}"/>
            </td>
            <td class="l-t-td-left">后缀支持HTML：</td>
            <td class="l-t-td-right"><input name="t4" type="text" ltype='text' ligerui="{suffix:'<font color=red>红色</font>'}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">调整器：</td>
            <td class="l-t-td-right"><input name="num" type="text" ltype='spinner'
											validate="{required:true}" value="" ligerui="{type:'float',suffix:'后缀很长',suffixWidth:'60',explain:'一定得填写',readonly:false}"/></td>
            <td class="l-t-td-left"></td>
            <td class="l-t-td-right"></td>

        </tr>
        <tr>
            <td class="l-t-td-left">日期测试：</td>
            <td class="l-t-td-right"><input name="date" type="text" ltype='date' value="" ligerui="{disabled:false,showTime:true}"/></td>
            <td class="l-t-td-left">时间测试2：</td>
            <td class="l-t-td-right"><input name="time" type="text" ltype='date'
											validate="{required:true}"
											ligerui="{format:'yyyy',explain:'时间不能小于当前时间',initValue:'2013-01-17',readonly:false}" value="2012-12-12 14:21:12.0"/></td>

        </tr>
        <tr>
            <td class="l-t-td-left">日期测试1：</td>
            <td class="l-t-td-right"><input id="date1" name="date1" type="text" ltype='date' value="" validate="{ltTo:'#date2'}"/></td>
            <td class="l-t-td-left">日期测试2：</td>
            <td class="l-t-td-right"><input id="date2" name="date2" type="text" ltype='date' value="" validate="{gtTo:'#date1'}" ligerui="{suffix:'后缀很长',suffixWidth:'60',explain:'在添加自定义说明。支持html，function(){return string;}'}"/></td>

        </tr>
		<tr>
			<td class="l-t-td-left">下拉框：</td>
			<!--注意id和valueFieldID的命名规则，在jsp中我们一般用标签生成-->
			<td class="l-t-td-right" title="请点周选择"><input type="text" name="com1" ltype="select" validate="{required:true}" ligerui="{
				explain:'sdasdfasdfsadf',
				//valueFieldID:'com1',
				value:'2',
				readonly:true,
				//disabled:true,
				//data: [ { text:'部门1', id:'1' }, { text:'部门2', id:'2' } ],
				url:'demo/zz_select_date.txt',
				//suffix:'后缀很长，很长，不是吗',
				suffixWidth:'140'
			}"/>
			</td>
			<td class="l-t-td-left">下拉树：</td>
			<td class="l-t-td-right"><input type="text" name="com2" id="com2-txt" ltype="select" validate="{required:true}" ligerui="{explain:'说明是什么。',treeLeafOnly:false,valueField:'com2',value:'2',tree:{async:false,checkbox:false,url: 'demo/tree.json' }}"/>
			</td>
		</tr>
        <tr>
            <td class="l-t-td-left">复选组：</td>
            <td class="l-t-td-right"><input type="checkbox" name="checkbox1" ltype="checkboxGroup"
											validate="{required:true}"
											ligerui="{initValue:'1', url:'demo/group.json' }"/></td>
            <td class="l-t-td-left">单选组：</td>
            <td class="l-t-td-right"><input type="radio" name="radio1" ltype="radioGroup"
											validate="{required:false}"
											ligerui="{onChange:test,value:'1',data: [ { text:'部门1', id:'1' }, { text:'部门2', id:'2' } ] }"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">复选组分行：</td>
            <td class="l-t-td-right"><input type="checkbox" name="checkbox2" ltype="checkboxGroup"
											validate="{required:true}"
											ligerui="{lineWrap:false,value:'1,3', url:'demo/group.json' }"/></td>
            <td class="l-t-td-left">单选组分行：</td>
            <td class="l-t-td-right"><input type="radio" name="radio2" ltype="radioGroup"
											validate="{required:true}"
											ligerui="{lineWrap:false,data: [ { text:'部门1', id:'1' }, { text:'部门2', id:'2' } ] }"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">大文本：</td>
            <td class="l-t-td-right" colspan="3"><textarea name="textarea" cols="70" rows="30" class="l-textarea" validate="{required:true}" ligerui="{explain:'字数不能超过3000个字'}"></textarea></td>
        </tr>
    </table>
</form>

</body>
</html>
