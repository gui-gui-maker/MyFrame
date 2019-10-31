<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>

    <script type="text/javascript">

                $(function () {
					return;
					$("#vali").ligerRadioGroup().bind("change", setRules);
					$("#form1").validate({
						showErrors: function (errorMap, errorList) {
							alert($.ligerui.toJSON(errorMap));
							alert($.ligerui.toJSON(errorList));
							this.defaultShowErrors();
						}
					});

				});
				function setRules(value) {
					var ele = $("#name");
					if (value == "1") {
						ele.rules("add", { required: true});
						//$("#test-txt").rules("add", { required:true});
						//$("#incomeDay").rules("add", { required:true});
					}
					else {
						ele.rules("remove", "required");
						//$("#test-txt").rules("remove","required");
						//$("#incomeDay").rules("remove","required");
						var validator = $("#form1").validate();
						validator.successList.push(ele);
						validator.showErrors();
					}
				}

    </script>

	<script type="text/javascript">
				var emails = [
					{"value": "lybide@163.com", data: ["Tachybaptus", "ruficollis", "Podiceps"]},
					{"value": "lybide@sina.com", data: ["Tachybaptus1", "ruficollis1", "Podiceps1"]},
					{"value": "lybide@sohu.com", data: ["Dr. <strong>Tech</strong> de Log", "ruficollis3", "Master <em>Sync</em>"]}
				];
				var cities = ["美国", "中国", "英国", "June", "July", "法国", "September", "October", "November", "December"];
				$(function () {//jQuery页面载入事件
					//setTimeout(function(){$("#suggest1").autocomplete(cities);},0)
					//$("#suggest1").autocomplete(cities);
				});
				</script>

</head>
<body>

<div class="title-tm">
	<div class="l-page-title has-icon has-note">
		<div class="l-page-title-div"></div>
		<div class="l-page-title-text"><h1>软件开发管理系统——编辑</h1></div>
		<div class="l-page-title-note"><a href="javascript:void(0);">点击操作1</a><a href="javascript:void(0);">点击操作2</a><a href="javascript:void(0);">点击操作3</a><a href="javascript:void(0);">点击操作4</a><a href="javascript:void(0);">点击操作5</a><a href="javascript:void(0);">点击操作6</a>这里是副标题，可带操作说明</div>
		<div class="l-page-title-icon"><img src="k/kui/images/icons/32/places.png" border="0"></div>
	</div>
</div>

<div class="item-tm">
	<div class="l-page-note"><div class="l-page-note-div">操作说明：近年来，研究所一直致力于RFID和智能卡读写设备的研发和生产，目前已有多款定型产品和解决方案。研究所紧紧抓住RFID技术的发展趋势和潮流，不断加大在RFID产业方面的研发和生产投入，目前研究所在RFID和智能卡读写设备方面的研发技术处于国内领先水平。</div></div>
</div>

<form id="form1" action="demo/test.json" getAction="demo/test.json">
    <div class="navtab">
        <div title="表1" lselected="true">
            <input type="hidden" name="id" id="form1_id">
            <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">下拉输入本地数据</td>
					<td class="l-t-td-right">

                        <input name="idn" type="text" ltype='text' validate="{idno:true,maxlength:18}" ligerui="{autocomplete:{url:'base/citizen/q.do',option:{extraParams:{name:'idn',compare:'llike',hasHouse:true,hasFamily:true,conditions:[['regType','=','1'],['sex','=','1'],]},valueKey:'idn',setValueColumn:{'name':'name'},displayColumn:[{name:'name',width:130},{name:'idn',width:130}]}}}"/>

                        <input name="name1" type="text" ligerui="{autocomplete:{data:cities,option:{matchContains:true,delay:400,width:150},result:function(event, data, formatted){alert(data+'=='+formatted)}}}">
					</td>
					<td class="l-t-td-left">下拉输入2：</td>
					<td class="l-t-td-right"><input name="name2" type="text" ligerui="{autocomplete:{url:'demo/zz_autocomplete_date.jsp'}}"/></td>
				</tr>
				<tr>
					<script type="text/javascript">
					var op1 = {
						//minChars: 0,
						//width: 310,
						matchContains: "word",
						autoFill: false,
						formatItem: function (row, i, max) {//自定义第行数据
							var str1 = "";
							str1 += '<div style="width:40px;" class="text-center">id-' + i + '</div>';
							str1 += '<div style="width:40px;" class="text-center">' + max + '</div>';
							str1 += '<div style="width:100px;" class="text-center">' + row.data[1] + '</div>';
							str1 += '<div style="width:auto;" class="text-center">' + row.value + '</div>';
							return str1;
						}
					};
					</script>
					<td class="l-t-td-left">选择电子邮箱：</td>
					<td class="l-t-td-right"><input name="name3" type="text" ligerui="{autocomplete:{data:emails,option:op1,result:function(event, data, formatted){alert('选择结果：'+data.to+'\n'+formatted);}}}">
					</td>
					<td class="l-t-td-left">下拉远程4：</td>
					<td class="l-t-td-right"><input name="name4" type="text" ligerui="{autocomplete:{url:'demo/zz_autocomplete_date.jsp',option:{setValueColumn:{'name1':'data1','name2':'data2','name3':'data3'},formatResult:function(row){return row.data3;},displayColumn:[{name:'data1',width:130},{name:'data2',width:80},{name:'data3'}]}}}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">下拉简单数据：</td>
					<td class="l-t-td-right"><input name="name5" type="text" ligerui="{autocomplete:{data:['a中国','a美国','a英国','b成都','b房产'],option:{matchContains:true}}}"></td>
					<td class="l-t-td-left">下拉远程6，可多选：：</td>
					<td class="l-t-td-right"><input name="name6" type="text" ligerui="{autocomplete:{url:'demo/zz_autocomplete_date.jsp',option:{multiple:true}}}"/></td>
				</tr>
				<tr>
                    <td class="l-t-td-left"> 名字：</td>
                    <td class="l-t-td-right"><input id="name" name="name" type="text" ltype='text'
													validate=""/>
                        <input type="button" value="设置为必填" onclick="setRules(1)">
                        <input type="button" value="设置为选填" onclick="setRules(2)">
                    </td>
                    <td class="l-t-td-left"> 更改验证规则：</td>
                    <td class="l-t-td-right"><input id="vali" name="vali" type="radio" ltype='radioGroup'
													validate="{required:true}"
													ligerui="{data:[{id:'1',text:'设置为必填'},{id:'2',text:'设置为选填'}]}"/>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 年龄：</td>
                    <td class="l-t-td-right"><input name="age" type="text" ltype='spinner'
													validate="{required:true}" value="" ligerui="{type:'int'}"/></td>
                    <td class="l-t-td-left"> 入职日期：</td>
                    <td class="l-t-td-right"><input name="incomeDay" id="incomeDay" type="text" ltype='date'
													validate="{required:true}"
													ligerui="{showTime: true,format:'yyyy-MM-dd hh:mm'}" value=""/></td>

                </tr>
                <tr>
                    <td class="l-t-td-left"> 部门：</td>
                    <td class="l-t-td-right" colspan="3"><input type="text" id="test-txt" ltype="select"
																validate="{required:true}"
																ligerui="{valueFieldID:'test',value:'', data: [ { text:'部门1', id:'1' }, { text:'部门2', id:'2' } ] }"/>
                    </td>
                </tr>
            </table>
        </div>
        <div title="表2" style="">
            <table border="1" cellpadding="3" cellspacing="0" class="l-detail-table">
                <tr>
                    <td class="l-t-td-left"> 名字：</td>
                    <td class="l-t-td-right"><input name="name" type="text" ltype='text' validate="{required:true,maxlength:10}"></td>
                    <td class="l-t-td-left"> 性别：</td>
                    <td class="l-t-td-right"><input name="sex" type="text" ltype='text' validate="{required:true}"/></td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 年龄：</td>
                    <td class="l-t-td-right"><input name="age" type="text" ltype='spinner'
													validate="{required:true}" value=""
													ligerui="{type:'int',suffix:'月'}"/></td>
                    <td class="l-t-td-left"> 入职日期：</td>
                    <td class="l-t-td-right"><input name="incomeDay" type="text" ltype='date'
													validate="{required:true}" ligerui=""/></td>

                </tr>
                <tr>
                    <td class="l-t-td-left"> 部门：</td>
                    <td class="l-t-td-right" colspan="3"><input type="text" id="test1-txt" ltype="select"
																validate="{required:true}"
																ligerui="{valueFieldID:'test1',value:'on', data: [ { text:'部门1', id:'1' }, { text:'部门2', id:'2' } ] }"/>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 地址：</td>
                    <td class="l-t-td-right" colspan="3"><input name="address" type="text" ltype='text'
																validate="{required:true}" value=""/></td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 地址1：</td>
                    <td class="l-t-td-right" colspan="3"></td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 地址2：</td>
                    <td class="l-t-td-right" colspan="3"></td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 地址3：</td>
                    <td class="l-t-td-right" colspan="3"></td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 地址4：</td>
                    <td class="l-t-td-right" colspan="3"></td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 地址5：</td>
                    <td class="l-t-td-right" colspan="3"></td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 地址：</td>
                    <td class="l-t-td-right" colspan="3"></td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 地址：</td>
                    <td class="l-t-td-right" colspan="3"></td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 地址：</td>
                    <td class="l-t-td-right" colspan="3"></td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>
