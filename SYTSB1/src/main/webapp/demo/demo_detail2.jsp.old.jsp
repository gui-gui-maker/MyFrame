<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">

		$(function () {
			//页面标题
			pageTitle({to: "#title", text: "软件开发管理系统——编辑", note: '<a href="javascript:void(0);">点击操作1</a><a href="javascript:void(0);">点击操作2</a><a href="javascript:void(0);">点击操作3</a><a href="javascript:void(0);">点击操作4</a><a href="javascript:void(0);">点击操作5</a><a href="javascript:void(0);">点击操作6</a>这里是副标题，可带操作说明', icon: "k/kui/images/icons/32/places.png"});
			//如果不设置额外参数，不用调用此方法，初始化时会自动调用
			$("#form1").initForm({
				getSuccess: function (res) {
					//alert(res.data.id)
				}
			});
			$("#dfgsde1").click(
				function () {
					$("#xfhertg3w4t").rules("add", {required: true});//必填
					var form = $("#form1").validate();
					form.element("#xfhertg3w4t");//单输入框验证
				}
			);
			$("#dfgsde2").click(
				function () {
					var d = $("#xfhertg3w4t").rules("remove", "required");

					var validator = $("#form1").validate();
					validator.successList.push($("#xfhertg3w4t"));
					validator.showErrors();

				}
			);
		});
		function test(data, v) {
			//alert(data)
			//alert(v)
		}
		function e(obj) {
			alert(obj.value + " 被天天点")
		}
		function ddddd(arg) {
			$("#form2").data("formOptions").getAction = "xxxx.jsp?xxx=11";
			//$("#form2").data("formOptions",{getAction:"xxxx.jsp?xxx=11",xxxxxxxxxxxxxxxxxxxxxxxx:11111111111111111});

		}
		;
		function bd1(arg) {
			winOpen({content: "url:demo/bd1.jsp", title: "数据比对", parent: api, lock: true, width: 500, height: 400, max: false, min: false});
		}
		;

		/*top.$.dialog.prompt("你娃想爪子",
		 function(val){
		 alert(val)
		 },
		 "这里是什么初始值",api
		 );*/

    </script>
</head>
<body>

<div class="title-tm">
	<div id="title"></div>
	<%--<div class="l-page-title has-icon has-note">--%>
		<%--<div class="l-page-title-div"></div>--%>
		<%--<div class="l-page-title-text"><h1>软件开发管理系统——编辑</h1></div>--%>
		<%--<div class="l-page-title-note"><a href="javascript:void(0);">点击操作1</a><a href="javascript:void(0);">点击操作2</a><a href="javascript:void(0);">点击操作3</a><a href="javascript:void(0);">点击操作4</a><a href="javascript:void(0);">点击操作5</a><a href="javascript:void(0);">点击操作6</a>这里是副标题，可带操作说明</div>--%>
		<%--<div class="l-page-title-icon"><img src="k/kui/images/icons/32/places.png" border="0"></div>--%>
	<%--</div>--%>
</div>

<div class="item-tm">
	<div class="l-page-note"><div class="l-page-note-div">操作说明：近年来，研究所一直致力于RFID和智能卡读写设备的研发和生产，目前已有多款定型产品和解决方案。研究所紧紧抓住RFID技术的发展趋势和潮流，不断加大在RFID产业方面的研发和生产投入，目前研究所在RFID和智能卡读写设备方面的研发技术处于国内领先水平。<input type="button" value="重新给一对多赋值Action" id="submitElement" class="k-form-input" onclick="ddddd();"/><input type="button" value="数据比对" id="sdfasdf" class="k-form-input" onclick="bd1();"/><input type="button" value="全为必填" id="dfgsde1" class="k-form-input"/><input type="button" value="删除必填" id="dfgsde2" class="k-form-input"/></div></div>
</div>

<div class="navtab">
<div title="<font color=red>主表</font>" lselected="true" style="height: 400px">
    <form id="form1" action="demo/test.json" getAction="demo/test.json">
        <input type="hidden" name="id" id="form1_id">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
           	<tr>
                <td class="l-t-td-left"></td>
                <td class="l-t-td-right"></td>
                <td class="l-t-td-left"></td>
                <td class="l-t-td-right"></td>
            </tr>
			<script type="text/javascript">
			function gets(val, e, srcObj) {//val：现有input框的值，e：为input对像，srcObj：为图标对象
				alert("现有input框的值:" + val);
				e.setValue("图标点击后，自动赋值。");
			}
			;
			</script>
            <tr>
                <td class="l-t-td-left" ext_alt="aefhiwoeufhwiuefhoiu">开发部：</td>
                <td class="l-t-td-right" colspan="3"><input type="text" id="ssef" ltype="text" validate="{required:true,maxlength:20}" ligerui="{iconItems:[{icon:'search',click:function(val,e,srcObj){gets(val,e,srcObj)}},{img:'k/kui/images/icons/16/help.png',click:function(val,e,srcObj){gets(val,e,srcObj)}}],valueFieldID:'test',suffix:'月',value:'', data: [ { text:'部门1', id:'1' }, { text:'部门2', id:'2' } ] }"/>
                </td>
            </tr>
			<tr>
                <td class="l-t-td-left">变通文本：</td>
                <td class="l-t-td-right"><input name="name1" type="text" ltype='text' ligerui="{suffix:'月'}"
												validate="{required:true,maxlength:10}"/>
                </td>
                <td class="l-t-td-left">多图标：</td>
                <td class="l-t-td-right"><input name="sex" type="text" ltype='text' validate="{required:true,maxlength:10}" ligerui="{iconItems:[{icon:'count',click:function(){}},{icon:'follow'},{icon:'photo'},{icon:'picture'}]}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">自定后缀宽度：</td>
                <td class="l-t-td-right"><input name="name19er8guaoseifjwe4j0909" id="xfhertg3w4t" type="text" ltype='text' validate="{required:true,maxlength:10}"></td>
                <td class="l-t-td-left">后缀支持HTML：</td>
                <td class="l-t-td-right"><input name="sexgew" type="text" ltype='text' validate="{required:true}" ligerui="{suffix:'<input type=button value=test onclick=e(this)>'}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">调整器：</td>
                <td class="l-t-td-right"><input name="tzq1" type="text" ltype='spinner' validate="{required:true,maxlength:10}" value="" ligerui="{type:'int',width:180}"/></td>
                <td class="l-t-td-left">时间测试：</td>
                <td class="l-t-td-right"><input name="incomeDay" type="text" ltype='date' validate="{required:true,maxlength:32}" ligerui="{format:'yyyy-MM-dd hh:mm:ss',width:180}" value=""/></td>
            </tr>
            <tr>
                <td class="l-t-td-left">部门：</td>
                <td class="l-t-td-right" colspan="3"><input name="bm999999" type="text" id="test-txt" ltype="select" validate="{required:true}" ligerui="{iconItems:[{icon:'add',click:function(val,e,srcobj){sys_modify_codetable('402880c03dab1fea013dab50a3490002','dw_001','test');}}],valueFieldID:'test',suffix:'月',value:'', data: [ { text:'部门1', id:'1' }, { text:'部门2', id:'2' } ] }"/></td>
            </tr>
            <tr>
                <td class="l-t-td-left">复选组：</td>
                <td class="l-t-td-right"><input type="checkbox" name="checkbox1" ltype="checkboxGroup" validate="{required:true}" ligerui="{onSuccess:test,initValue:'1', url:'demo/group.json' }"/></td>
                <td class="l-t-td-left">单选组：</td>
                <td class="l-t-td-right"><input type="radio" name="radio1" ltype="radioGroup" validate="{required:true}" ligerui="{data: [ { text:'部门1', id:'1' }, { text:'部门2', id:'2' } ] }"/></td>
            </tr>
            <tr>
                <td class="l-t-td-left">大文本：</td>
                <td class="l-t-td-right" colspan="3"><textarea name="operationUnit" cols="70" rows="4" class="l-textarea" ligerui="{iconItems:[{icon:'edit',click:gets},{icon:'del',click:gets},{icon:'copy',click:gets}]}" validate="{required:true}"></textarea></td>
            </tr>
        </table>
    </form>

</div>
<div id="test" title="子表1">
	<form id="form2" action="demo/test.json" getAction="demo/getAction.json?s=44" delAction="demo/test.json">
        <input type="hidden" name="id">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">名字：</td>
                <td class="l-t-td-right"><input name="name" type="text" ltype='text'
												validate="{required:true,maxlength:10}">
                </td>
				<script type="text/javascript">
				function methodChange(arg) {
					//alert("radio点击选择事件："+arg);
				}
				;
				</script>
                <td class="l-t-td-left">性别：</td>
                <td class="l-t-td-right"><input type="radio" name="sex" ltype="radioGroup" validate="{required:true}"
												ligerui="{value:'2',onChange:methodChange, data: [ { text:'男', id:'1' }, { text:'女', id:'2' } ]}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">年龄：</td>
                <td class="l-t-td-right"><input name="age" type="text" ltype='spinner'
												validate="{required:true}" value=""
												ligerui="{type:'int',suffix:'月'}"/></td>
                <td class="l-t-td-left">入职日期：</td>
                <td class="l-t-td-right"><input name="incomeDay" type="text" ltype='date'
												validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}"/></td>

            </tr>
            <tr>
                <td class="l-t-td-left">部门：</td>
                <td class="l-t-td-right"><input type="text" name="departmentId" id="departmentId-txt" ltype="select" value="2"
												validate="{required:true}"
												ligerui="{treeLeafOnly:false,valueField:'code',initValue:'1',tree:{checkbox:false,data:[{'id':'10','code':'10', 'text': '节点1', 'children': [{'id':'1','code':'1', 'text': '节点1.1' },{'id':'2','code':'2x', 'text': '节点1.2' },{'id':'3','code':'3', 'text': '节点1.3', 'children': [   {'id':'4','code':'4', 'text': '节点1.3.1' ,'children': [ {'id':'5','code':'5', 'text': '节点1.3.1.1' }, {'id':'6','code':'6', 'text': '节点1.3.1.2' }] },   {'id':'13','code':'13', 'text': '节点1.3.2' }]},{'id':'14','code':'14', 'text': '节点1.4' }]}]}}"/>
                </td>
                <td class="l-t-td-left">爱好：</td>
                <td class="l-t-td-right"><input type="checkbox" name="fav" ltype="checkboxGroup"
												validate="{required:true}"
												ligerui="{onSuccess:test,initValue:'1', url:'demo/group.json' }"/>

                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3">
					<%--<input name="address" type="text" ltype='text' validate="{required:true}" value=""/>--%>
					<textarea name="address" cols="70" rows="4" class="l-textarea"></textarea>
				</td>
            </tr>
        </table>

    <script type="text/javascript">
        $("#form2").initFormList({
//                opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
//                opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
//                action:"",//保存数据或其它操作的action
			actionParam: {"fkId": $("#form1>[name=id]")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
//                delAction:"",//删除数据的action
//                delActionParam:{},//默认为选择行的id
//                getAction:"",//取数据的action
//                getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
			onSelectRow: function (rowdata, rowindex) {
				$("#form2").setValues(rowdata);
			},
			toolbar: [
				{ text: '保存', click: function () {
					$("#form2").submit();
				}, icon: 'save'}
			],
			columns: [
				//此部分配置同grid
				{ display: '主键', name: 'id', width: 50, hide: true},
				{ display: '名字', name: 'name', width: '10%'},
				{ display: '性别', width: '5%', name: 'sex'},
				{ display: '年龄', name: 'age', width: '5%', render: function (data) {
					return "<font color='" + (data["age"] > 30 ? "blue" : "red") + "'>" + data["age"] + "</font>";
				}},
				{ display: '入职日期', name: 'incomeDay', width: '15%', format: "yyyy-MM-dd"},
				{ display: '部门', name: 'departmentId', width: '15%'},
				{ display: '爱好', name: 'fav', width: '20%'},
				{ display: '地址', name: 'address', align: 'left', width: '20%'}
			]
		});
    </script>
	</form>
</div>
<div title="事件接口示例">
    <form id="form3" action="demo/test.json" getAction="demo/getAction.json" delAction="demo/test.json">
        <input type="hidden" name="id">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">名字：</td>
                <td class="l-t-td-right"><input name="name" type="text" ltype='text'
												validate="{required:true,maxlength:10}">
                </td>
                <td class="l-t-td-left">性别：</td>
                <td class="l-t-td-right"><input name="sex" type="text" ltype='text' validate="{required:true}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">年龄：</td>
                <td class="l-t-td-right"><input name="age" type="text" ltype='spinner'
												validate="{required:true}" value=""
												ligerui="{type:'int',suffix:'月'}"/></td>
                <td class="l-t-td-left">入职日期：</td>
                <td class="l-t-td-right"><input name="incomeDay" type="text" ltype='date'
												validate="{required:true}" ligerui=""/></td>

            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"><input name="address" type="text" ltype='text'
															validate="{required:true}" value=""/></td>
            </tr>
        </table>
    </form>
    <script type="text/javascript">
        $("#form3").initFormList({
//                opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
//                opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
//                action:"",//保存数据或其它操作的action
			actionParam: {"fkId": $("#form1>[name=id]")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的id为id的值带上去,可以是一个对象或选择器
			delAction: "demo/test.json", //删除数据的action
			delActionParam: {name: "name"}, //默认为选择行的id
			success: function (data) {
				//alert("success=" + $.ligerui.toJSON(data))
			},
			getSuccess: function (data) {
				//alert("getSuccess=" + $.ligerui.toJSON(data))
			},
			delSuccess: function (data) {
				//alert("delSuccess=" + $.ligerui.toJSON(data))
			},

//                getAction:"",//取数据的action
//                getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
			columns: [
				//此部分配置同grid
				{ display: '主键', name: 'id', width: 50, hide: true},
				{ display: '名字', name: 'name', width: '20%'},
				{ display: '性别', width: '10%', name: 'sex'},
				{ display: '年龄', name: 'age', width: '10%'},
				{ display: '入职日期', name: 'incomeDay', width: '20%'},
				{ display: '地址', name: 'address', align: 'left', width: '30%'}
			]
		});
    </script>
</div>
<div title="test">
    <form id="form4" action="demo/test.json" getAction="demo/getAction2.json" delAction="demo/test.json">
        <input type="hidden" name="workers.id">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">名字：</td>
                <td class="l-t-td-right"><input name="workers.name" type="text" ltype='text'
												validate="{required:true,maxlength:10}">
                </td>
                <td class="l-t-td-left">性别：</td>
                <td class="l-t-td-right"><input name="sex" type="text" ltype='text' validate="{required:true}"/>
                </td>
            </tr>
        </table>
    </form>
    <script type="text/javascript">
        $("#form4").initFormList({
//                opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
//                opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
//                action:"",//保存数据或其它操作的action
//                actionParam:{"fkId":$("#form1>[name=id]")},//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的id为id的值带上去,可以是一个对象或选择器
//                delAction:"demo/test.json",//删除数据的action
//                delActionParam:{name:"name"},//默认为选择行的id
//                success:function(data){alert("success="+$.ligerui.toJSON(data))},
//                getSuccess:function(data){alert("getSuccess="+$.ligerui.toJSON(data))},
//                delSuccess:function(data){alert("delSuccess="+$.ligerui.toJSON(data))},

//                getAction:"",//取数据的action
//                getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
			columns: [
				//此部分配置同grid
				{ display: '主键', name: 'workers.id', width: 50 },
				{ display: '名字', name: 'workers.name', width: '20%'}
			]
		});
    </script>
</div>
<div title="子表21">
    <form getAction="demo/test.json">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">名字：</td>
                <td class="l-t-td-right"><input name="name" type="text" ltype='text'
												validate="{required:true,maxlength:10}">
                </td>
                <td class="l-t-td-left">性别：</td>
                <td class="l-t-td-right"><input name="sex" type="text" ltype='text' validate="{required:true}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">年龄：</td>
                <td class="l-t-td-right"><input name="age" type="text" ltype='spinner'
												validate="{required:true}" value=""
												ligerui="{type:'int',suffix:'月'}"/></td>
                <td class="l-t-td-left">入职日期：</td>
                <td class="l-t-td-right"><input name="incomeDay" type="text" ltype='date'
												validate="{required:true}" ligerui=""/></td>

            </tr>
            <tr>
                <td class="l-t-td-left">部门：</td>
                <td class="l-t-td-right" colspan="3"><input type="text" id="test1-txt" ltype="select"
															validate="{required:true}"
															ligerui="{valueFieldID:'test1',value:'on', data: [ { text:'部门1', id:'1' }, { text:'部门2', id:'2' } ] }"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"><input name="address" type="text" ltype='text'
															validate="{required:true}" value=""/></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址1：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址2：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址3：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址4：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
            <tr>
                <td class="l-t-td-left">地址5：</td>
                <td class="l-t-td-right" colspan="3"></td>
            </tr>
        </table>
    </form>
</div>
</div>

</body>
</html>
