<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="pub/rbac/js/area.js"></script>
	<style type="text/css">
	//.l-grid-row {height:50px;}
	</style>
    <script type="text/javascript">
        var pageStatus = "";
		$(function () {
			$("#form1").initForm({    //参数设置示例
				getAction: "base/citizen/detail.do?id=",
				toolbar: [
					{text: '保存，请点这里', id: 'save', icon: 'save', click: save },
					{text: "点击这里进行新增按钮操作", id: "gesefwefwef", click: function () {
						$.ligerui.get("gwrhb").addButtonItem({text: "来，新加的按钮", icon: "copy", id: "wefwef", click: function () {
							alert("新增xxxxxxxxxxxxxxx")
						}});
						//alert($.ligerui.get("gwrhb").getValue());
					}},
					{text: "打开软件详情", id: "gwrhb"},
					{text: "删除不用的数据", icon: "del", id: "bwef", click: function () {
						alert("我被点了吗")
					}},
					{text: "删除按钮", icon: "del", id: "del", click: function () {
						var g = $.ligerui.get('bwef').destroy();
						$.ligerui.get('del').destroy()
					}},
					{text: '关闭', id: 'close', icon: 'cancel', click: close }
				],
				getSuccess: function (res) {
					manager.loadData({Rows: res.data.baChildbears});
				}
			});
			function save() {
				if ($("#form1").validate().form()) {
					var data = $("#form1").getValues();
					data["baChildbears"] = manager.getData();
					$.ajax({
						url: "base/citizen/changeCitizen.do",
						type: "POST",
						datatype: "json",
						contentType: "application/json; charset=utf-8",
						data: $.ligerui.toJSON(data),
						success: function (data, stats) {
							if (data["success"]) {
								api.data.window.saveCitizenRow($("head").attr("pageStatus"), data.data);
								api.close();
							}
						},
						error: function (data) {
							$.ligerDialog.error('保存失败！' + data.msg);
						}
					});
				}
			}

			function close() {
				api.close();
			}

			bearProp();
			$("#idn").focusout(function () {
				var value = $(this).val();
				var parser = new ClsIDCard();

				if (parser.IsValid(value)) {
					alert(parser.GetBirthDay())
					var json = {birthday: parser.GetBirthDay(), sex: parser.GetSex()};
					alert(json.birthday)
					$("#form1").setValues({birthday: parser.GetBirthDay(), sex: parser.GetSex()})
				}
			});
			manager111 = $("#btn1").ligerButton({
				click: function () {
					var g = $("#save").ligerGetTextBoxManager();
					g.setValue("上一步");
					var g = $.ligerui.get('save');
					g.setValue("真的要进行保存吗？");
					var g = $.ligerui.get('bwef');
					g.setDisabled();
				}, text: "点击我吧"
			});
			manager112 = $("#btn2").ligerButton({
				click: function () {
					var g = $("#save").ligerGetTextBoxManager();
					g.setValue("下一步");
					var g = $.ligerui.get('save');
					g.setValue("真的要进行保存吗？");
					var g = $.ligerui.get('bwef');
					g.setDisabled();
				}, text: "点击我吧"
			});
			manager111.setValue("我的值变换了");
			manager111.setDisabled();
			manager111.setEnabled();
			var g = $("#btn1").ligerGetTextBoxManager();
			g.setValue("动态直接改变值");
			$("#btn").toggle(function () {
				var g = $.ligerui.get('bwef');
				g.setDisabled();
			}, function () {
				var g = $.ligerui.get('bwef');
				g.setEnabled();
			});
		});
		var propTypes = [
			{'id': '1', 'text': '中国'},
			{'id': '2', 'text': '美国'},
			{'id': '3', 'text': '英国'},
			{'id': '5', 'text': '俄国'},
			{'id': '4', 'text': '法国'}
		];
		var propTypes2 = [
			//{'id': '1', 'text': '一孩次111'},
			//{'id': '2', 'text': '二孩次222'},
			//{'id': '3', 'text': '三孩次333'},
			//{'id': '4', 'text': '四孩次444'},
			{id:'5e5658e645dec6670145dedcf0940034',text:'起重机械定期(首检)检验报告(升降机适用)'},{id:'5e5658e645da7ad30145dead5bbf0223',text:'起重机械定期(首检)检验报告(电动葫芦起重设备适用)'},{id:'5e5658e645da7ad30145deb1982b0232',text:'起重机械定期(首检)检验报告(门座起重机适用)'},{id:'5e5658e645dec6670145def5dc4c006c',text:'起重机械定期(首检)检验报告 (桅杆起重机适用)'},{id:'5e5658e645d12b2b0145d13f58de0019',text:'起重流动式检验报告'},{id:'5e5658e645da7ad30145deabc60f021b',text:'起重机械定期(首检)检验报告(流动式起重机适用)'},{id:'5e5658e645da7ad30145dea0805801eb',text:'起重机械定期(首检)检验报告 (塔式起重机适用)'},{id:'5e5658e645dec6670145def027c0005c',text:'起重机械定期(首检)检验报告(机械式停车设备适用)'},{id:'5e5658e645da7ad30145deb02dff022a',text:'起重机械定期(首检)检验报告(轻小型起重设备适用)'},{id:'5e5658e645da7ad30145deaa02830212',text:'起重机械定期(首检)检验报告(桥、门式起重机适用)'},{id:'5e5658e645dec6670145dee8c82b004f',text:'起重机械定期(首检)检验报告(旋臂式起重设备适用)'}
		];
		//for (var i = 5; i < 30; i++) {
		//	propTypes2.push({'id': i, 'text': '四孩次-for-'+i});
		//}
		var manager;
		function bearProp() {
			var columns = [
				{ display: '孩次（下拉多选）', width: '200', name: 'orders1', type: 'text',resizable:false,
					editor: {
						type: 'select',
						data: propTypes,
						ext: {
							//data:null,
							//tree:propTypes,
							isMultiSelect: true,
							emptyOption:true,
							selectBoxWidth:500,
							//valueFieldID:"text3",
							temp: 12112
						}
						/* ext:function(rowdata) {
						 return {
						 onBeforeOpen:function(){

						 },
						 render:function()
						 {
						 for (var i = 0; i < propTypes.length; i++)
						 {
						 if (propTypes[i]['id'] == rowdata.id)
						 return "dddddd";
						 }
						 }
						 };
						 }*/

					},
					render: function (item) {
						var arr1=[];
						for (var i in propTypes) {
							//2013-12-26 下午1:42 lybide 多选值判断
							var bds=(","+item["orders1"]+",").indexOf(","+propTypes[i]["id"]+",")>=0;
							if (bds) {
								arr1.push(propTypes[i]['text']);
							}
						}
						return arr1.toString();
					}
				},
				{ display: '孩次2', width: '100', name: 'orders2', type: 'text',resizable:false,
					editor: { type: 'select', data: propTypes2},
					render: function (item) {
						for (var i in propTypes2) {
							if (propTypes2[i]["id"] == item["orders2"])
								return propTypes2[i]['text'];
						}
						return item["orders2"];
					}
				},
				//添加自定义图标是不行的。
				//,ligerui:{readonly:false,iconItems:[{icon:'search',click:function(val,e,srcObj){alert(123123123);return;gets(val,e,srcObj)}},{img:'k/kui/images/icons/16/help.png',click:function(val,e,srcObj){gets(val,e,srcObj)}}],valueFieldID:'test',suffix:'月',value:'', data: [ { text:'部门1', id:'1' }, { text:'部门2', id:'2' } ] }
				{ display: '生育服务证号', name: 'certNo1', width: '', editor: { type: 'text' }},
				{ display: '备注1', name: 'textarea1', width: '300', editor: { type: 'textarea',height:80,width:300 }},
				{ display: '点选文本', name: 'inputclick1', width: '100', editor: { type: 'text' }},
				{ display: '名称，自动下拉', name: 'name1', width: '100', editor: { type: 'text', ligerui: {autocomplete: {data: ["111", "222", "333"]}}}},
				{ display: '独生子女生日', name: 'honourNo1', width: '100', type: 'int', editor: { type: 'date',ext:{format:"yyyy-MM"}} },
				{ display: '光荣证号', name: 'honourNo2', width: '100', type: 'int', editor: { type: 'spinner'} },
				{ display: 'ddd单选', name: 'honourNo3', width: '100', type: 'int', editor: { type: 'radio'} },
				{ display: '政策外生育', name: 'pol1', width: '100', align: 'center', type: 'int', editor: { type: 'checkbox'},
					render: function (item) {
						return '<a class="l-checkbox' + (item["pol"] == "1" ? " l-checkbox-checked" : "") + '" style="margin-top:3px;"></a>';
					}
				}
			]
			if (pageStatus != "detail") {
				columns.push({ display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='addNewRow()'><span>增加</span></a><a class='l-a l-icon-save' onclick='abse1()'></a>", isSort: false, width: '100', render: function (rowdata, rowindex, value) {
					var h = "";
					if (!rowdata._editing) {
						h += "<a class='l-a iconfont l-icon-add' href='javascript:addNewRow()'><span>增加</span></a> ";
						h += "<a class='l-a l-icon-edit' href='javascript:beginEdit(" + rowindex + ")'><span>修改</span></a> ";
						h += "<a class='l-a l-icon-del' href='javascript:deleteRow(" + rowindex + ")'><span>删除</span></a> ";
					}
					else {
						h += "<a class='l-a l-icon-save' href='javascript:endEdit(" + rowindex + ")'><span>保存</span></a> ";
						h += "<a class='l-a l-icon-cancel' href='javascript:cancelEdit(" + rowindex + ")'><span>取消</span></a> ";
					}
					return h;
				}
				});
			}
			manager = $("#prop").ligerGrid({
				columns: columns,
				onSelectRow: function (rowdata, rowindex) {
					$("#txtrowindex").val(rowindex);
				},
				title: '生育状况',
				enabledEdit: pageStatus != "detail",
				clickToEdit: true,
				rownumbers: true,
				frozenRownumbers: false,
				isScroll: true,
				usePager: false,
				allowAdjustColWidth:false,
				data: {Rows:[
					{orders1:'1,2,3,5',orders2:"1",certNo1:"EverEdit",textarea1:"EverEdit是一个快速、轻量级\n和易于扩展的集文本、源代码、二进制编辑\n于一身的高性能纯文本编辑器",name1:"编辑器",inputclick1:"超强",honourNo1:"2013-8-8",honourNo2:"8541652321",honourNo3:"1",pol1:"0"},
					{orders1:'3,4,1',orders2:"3",certNo1:"EmEditor",textarea1:"EmEditor是一个快速、轻量级\n和易于扩展的集文本、源代码、二进制编辑\n于一身的高性能纯文本编辑器",name1:"编辑器",inputclick1:"一般",honourNo1:"2013-8-8",honourNo2:"78678934563",honourNo3:"0",pol1:"1"},
					{orders1:'4,2',orders2:"4",certNo1:"JetBrains IntelliJ IDEA 12.1.1",textarea1:"IntelliJ IDEA是一个更快速、轻量级\n和易于扩展的集文本、源代码、二进制编辑\n于一身的高性能纯文本编辑器",name1:"编辑器",inputclick1:"超超强",honourNo1:"2013-8-8",honourNo2:"34523452345",honourNo3:"1",pol1:"1"}
                ]}
			});

		}


		function gets(val, e, srcObj) {//val：现有input框的值，e：为input对像，srcObj：为图标对象
			alert("现有input框的值:" + val);
			e.setValue("图标点击后，自动赋值。");
		}

		function abse1(){
			alert(123)
		}

		function beginEdit(g) {
			alert(g);
			var row = manager.getSelectedRow();
			if (!row) {
				alert('请选择行');
				return;
			}
			manager.beginEdit(row);
		}
		function cancelEdit() {
			var row = manager.getSelectedRow();
			if (!row) {
				alert('请选择行');
				return;
			}
			manager.cancelEdit(row);
		}
		function cancelAllEdit() {
			manager.cancelEdit();
		}
		function endEdit() {
			var row = manager.getSelectedRow();
			if (!row) {
				alert('请选择行');
				return;
			}
			manager.endEdit(row);
		}
		function endAllEdit() {
			manager.endEdit();
		}
		function deleteRow() {
			manager.deleteSelectedRow();
		}
		var newrowid = 100;
		function addNewRow() {
			manager.addEditRow();
		}

		function getSelected() {
			var row = manager.getSelectedRow();
			if (!row) {
				alert('请选择行');
				return;
			}
			alert(JSON.stringify(row));
		}
		function getData() {
			var data = manager.getData();
			alert(JSON.stringify(data));
		}
    </script>
    <style type="text/css">
        .l-panel-header {
			background:url("");
			border-bottom:1px solid #99BBE8;
			color:#15428B;
			font-size:12px;
			font-weight:bold;
			height:24px;
			position:relative;
		}
		.l-grid-row-cell-inner {height:auto !important;}
    </style>
</head>
<body class="p5">

<div class="scroll-tm">

			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="" align="center" class="l-table">
				<tr>
					<td class="l-table-head" width="120">dd</td>
					<td class="l-table-head" width="120">33</td>
					<td class="l-table-head">44</td>
				</tr>
				<tr>
					<td class="l-table-td">33</td>
					<td class="l-table-td">11</td>
					<td class="l-table-td">33</td>
				</tr>
			</table>

			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="" align="center" class="l-table-come">
				<tr>
					<td class="l-table-td">33</td>
					<td class="l-table-td">11</td>
					<td class="l-table-td">33</td>
				</tr>
			</table>

			<table cellspacing="4" cellpadding="4" class="l-grid-header-table">
				<tbody>
					<tr class="l-grid-hd-row l-grid-header">
						<td class="l-grid-hd-cell l-grid-hd-cell-rownumbers" id="grid1001|hcell|c101" style="height: 23px; width: 26px;" columnindex="0"><div class="l-grid-hd-cell-inner"></div></td>
						<td class="l-grid-hd-cell" id="grid1001|hcell|c103" style="height: 23px; width: 150px; display: none;" columnindex="2" columnname="id"><div class="l-grid-hd-cell-inner"><span class="l-grid-hd-cell-text">id</span></div></td>
						<td class="l-grid-hd-cell" id="grid1001|hcell|c104" style="height: 23px; width: 150px; display: none;" columnindex="3" columnname="userid"><div class="l-grid-hd-cell-inner"><span class="l-grid-hd-cell-text">登录人ID</span></div></td>
						<td class="l-grid-hd-cell" id="grid1001|hcell|c105" style="height: 23px; width: 150px;" columnindex="4" columnname="user_name"><div class="l-grid-hd-cell-inner"><span class="l-grid-hd-cell-text">用 户</span></div></td>
						<td class="l-grid-hd-cell" id="grid1001|hcell|c106" style="height: 23px; width: 150px;" columnindex="5" columnname="login_time"><div class="l-grid-hd-cell-inner"><span class="l-grid-hd-cell-text">登录时间</span></div></td>
						<td class="l-grid-hd-cell" id="grid1001|hcell|c107" style="height: 23px; width: 150px;" columnindex="6" columnname="login_ip"><div class="l-grid-hd-cell-inner"><span class="l-grid-hd-cell-text">IP</span></div></td>
						<td class="l-grid-hd-cell" id="grid1001|hcell|c108" style="height: 23px; width: 150px;" columnindex="7" columnname="logout_time"><div class="l-grid-hd-cell-inner"><span class="l-grid-hd-cell-text">退出时间</span></div></td>
						<td class="l-grid-hd-cell l-grid-hd-cell-last" id="grid1001|hcell|c109" style="height: 23px; width: 150px;" columnindex="8" columnname="state"><div class="l-grid-hd-cell-inner"><span class="l-grid-hd-cell-text">状态</span></div></td>
					</tr>
					<tr>
						<td class="l-grid-row-cell">1</td>
						<td class="l-grid-row-cell">3</td>
						<td class="l-grid-row-cell">sdafasdf</td>
						<td class="l-grid-row-cell">sdafasdf</td>
						<td class="l-grid-row-cell">sdafasdf</td>
						<td class="l-grid-row-cell">sdafasdf</td>
					</tr>
				</tbody>
			</table>

<form id="form1" action="" getAction="">
	<table border="0" cellpadding="0" cellspacing="0" width="" height="" align="center" class="">
		<tr>
			<td><a id="btn1"></a>  <a id="btn2"></a></td>
		</tr>
	</table>
    <input type="hidden" id="id" name="id">
    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">姓名：</td>
            <td class="l-t-td-right"><input name="name" type="text" value="" ltype='text' ligerui="{width:180}" validate="{maxlength:50,required:true}"/></td>
            <td class="l-t-td-left">身份证号：</td>
            <td class="l-t-td-right"><input id="idn" name="idn" type="text" value="" ltype='text' validate="{maxlength:18,idno:true,required:true}"/></td>
            <td class="l-t-td-left">性别：</td>
            <td class="l-t-td-right"><input type="radio" id="sex-txt" name="sex" ltype="radioGroup" ligerui="{ valueFieldID:'sex',lineWrap:false,data: [{'id':'1','text':'男'},{'id':'2','text':'女'}]}"/></td>
            <td class="l-t-td-left">民xx族：</td>
            <td class="l-t-td-right"><input type="text" id="nation-txt" name="_nation-txt" ltype="select" ligerui="{ disabled:true,value:'36',valueFieldID:'nation',data: [{'id':'01','text':'汉族'},{'id':'02','text':'蒙古族'},{'id':'03','text':'回族'},{'id':'04','text':'藏族'},{'id':'05','text':'维吾尔族'},{'id':'06','text':'苗族'},{'id':'07','text':'彝族'},{'id':'08','text':'壮族'},{'id':'09','text':'布依族'},{'id':'10','text':'朝鲜族'},{'id':'11','text':'满族'},{'id':'12','text':'侗族'},{'id':'13','text':'瑶族'},{'id':'14','text':'白族'},{'id':'15','text':'土家族'},{'id':'16','text':'哈尼族'},{'id':'17','text':'哈萨克族'},{'id':'18','text':'傣族'},{'id':'19','text':'黎族'},{'id':'20','text':'傈僳族'},{'id':'21','text':'佤族'},{'id':'22','text':'畲族'},{'id':'23','text':'高山族'},{'id':'24','text':'拉祜族'},{'id':'25','text':'水族'},{'id':'26','text':'东乡族'},{'id':'27','text':'纳西族'}
            ,{'id':'28','text':'景颇族'},{'id':'29','text':'柯尔克孜族'},{'id':'30','text':'土族'}, {'id':'31','text':'达斡尔族'},{'id':'32','text':'仫佬族'},{'id':'33','text':'羌族'},{'id':'34','text':'布朗族'},{'id':'35','text':'撒拉族'},{'id':'36','text':'毛难族'},{'id':'37','text':'仡佬族'},{'id':'38','text':'锡伯族'},{'id':'39','text':'阿昌族'},{'id':'40','text':'普米族'},{'id':'41','text':'塔吉克族'},{'id':'42','text':'怒族'},{'id':'43','text':'乌孜别克族'},{'id':'44','text':'俄罗斯族'},{'id':'45','text':'鄂温克族'},{'id':'46','text':'崩龙族'},{'id':'47','text':'保安族'},{'id':'48','text':'裕固族'},{'id':'49','text':'京族'},{'id':'50','text':'塔塔尔族'},{'id':'51','text':'独龙族'},{'id':'52','text':'鄂伦春族'},{'id':'53','text':'赫哲族'},{'id':'54','text':'门巴族'},{'id':'55','text':'珞巴族'},{'id':'56','text':'基诺族'},{'id':'97','text':'其他'},{'id':'98','text':'外国血统'}]}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">曾用名：</td>
            <td class="l-t-td-right"><input name="befname" type="text" value="" ltype='text' validate="{maxlength:50}" ligerui="{suffix:'元',width:180}"/></td>
            <td class="l-t-td-left">出生日期：</td>
            <td class="l-t-td-right"><input name="birthday" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" value=""/></td>
            <td class="l-t-td-left">身高（cm）：</td>
            <td class="l-t-td-right"><input name="stature" type="text" value="" ltype='spinner' ligerui="{type:'int'}"/></td>
            <td class="l-t-td-left">宗教信仰：</td>
            <td class="l-t-td-right"><input type="text" id="rel-txt" name="_rel-txt" ltype="select" ligerui="{ valueFieldID:'rel',data: []}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">政治面貌：</td><!-- {readonly:true,value:'asdfasdf',iconItems:[{icon:'l-trigger-icon',click:function(){}}]} ,data: [{'id':'1','text':'中共党员'},{'id':'2','text':'农工党党员'},{'id':'3','text':'致公党党员'},{'id':'4','text':'九三学社社员'},{'id':'5','text':'台盟盟员'},{'id':'6','text':'共青团员'},{'id':'7','text':'群众'},{'id':'8','text':'无党派民主人士'},{'id':'9','text':'民盟盟员'},{'id':'10','text':'中共预备党员'},{'id':'11','text':'民建会员'},{'id':'12','text':'民革会员'},{'id':'13','text':'民进会员'},{'id':'14','text':'其他'}]-->
            <td class="l-t-td-right"><input type="text" id="pol-txt" name="_pol-txt" ltype="text" ligerui="{readonly:true,value:'我是初始值',iconItems:[{icon:'selectbox',click:function(){$('#pol-txt').ligerTextBox().set('value','值被改变了。');}}]}" disabled/></td>
            <td class="l-t-td-left">性别，可编辑码表：</td>
            <td class="l-t-td-right">
				<u:combo name="sdfsdf" modify="true" code="sys_sf"></u:combo>
            <%--<input type="text" id="edu-txt" name="_edu-txt" ltype="select" ligerui="{ valueFieldID:'edu',data: [{'id':'1','text':'小学教育'},{'id':'2','text':'初级中学教育'},{'id':'3','text':'普通高级中学教育'},{'id':'4','text':'中等职业教育'},{'id':'5','text':'专科教育'},{'id':'6','text':'大学本科教育'},{'id':'7','text':'研究生教育'},{'id':'0','text':'其他'}]}"/>--%></td>
            <td class="l-t-td-left">与户主关系：</td>
            <td class="l-t-td-right"><input type="text" id="familyrel-txt" name="_familyrel-txt" ltype="select" ligerui="{ valueFieldID:'familyrel',data: [{'id':'1','text':'本人'},{'id':'2','text':'配偶'},{'id':'3','text':'子'},{'id':'4','text':'女'},{'id':'5','text':'孙子、孙女或外孙子、外孙女'},{'id':'6','text':'父母'},{'id':'7','text':'祖父母或外祖父母'},{'id':'8','text':'兄弟姐妹'},{'id':'9','text':'其他'}]}" validate="{required:true}"/></td>
            <td class="l-t-td-left"></td>
            <td class="l-t-td-right"></td>

        </tr>
        <tr>
            <td class="l-t-td-left">户口性质：</td>
            <td class="l-t-td-right"><input type="radio" id="regType-txt" name="regType" ltype="radioGroup" ligerui="{ valueFieldID:'regType',data: [{'id':'0','text':'非农业'},{'id':'1','text':'农业'}]}"/></td>
            <td class="l-t-td-left">居住类别：</td>
            <td class="l-t-td-right"><input type="radio" id="liveType-txt" name="liveType" ltype="radioGroup" ligerui="{ disabled:true,valueFieldID:'liveType',data: [{'id':'1','text':'常住'},{'id':'2','text':'暂住'}]}"/></td>
            <td class="l-t-td-left">籍贯：</td>
            <td class="l-t-td-right"><input type="checkbox" id="helpTypes-txt" name="helpTypes" ltype="checkboxGroup" ligerui="{ disabled:true,lineWrap:true,valueFieldID:'helpTypes',data: [{'id':'101','text':'低保救助'},{'id':'102','text':'就业支持'},{'id':'103','text':'技能培训'},{'id':'104','text':'教育救助'},{'id':'105','text':'医疗救助'},{'id':'106','text':'其他救助'}]}" validate="{required:true}"/></td>
            <td class="l-t-td-left">联系电话：</td>
            <td class="l-t-td-right"><input name="tel" type="text" value="" ltype='text' validate="{maxlength:50}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">户籍所在地：</td>
            <td class="l-t-td-right" colspan="2"><input id="regAreaId" name="regAreaId" type="hidden"><input name="regAreaName" type="text" value="" ltype='select' validate="{maxlength:100}" ligerui="{disabled:true,textModel:true,valueFieldID:'regAreaId',onBeforeOpen:showAreaList}"/></td>
            <td class="l-t-td-right"><input name="regAddr" type="text" value="" ltype='text' validate="{maxlength:50}"/></td>
            <td class="l-t-td-left">现居住地址：</td>
            <td class="l-t-td-right" colspan="2"><input id="addrAreaId" name="addrAreaId" type="hidden"><input name="addrAreaName" type="text" value="" ltype='select' validate="{maxlength:100}" ligerui="{textModel:true,valueFieldID:'addrAreaId',onBeforeOpen:showAreaList}"/></td>
            <td class="l-t-td-right"><input name="addr" type="text" value="" ltype='text' validate="{maxlength:50}"/></td>
        </tr>
        <tr>

            <td class="l-t-td-left">父亲身份证号：</td>
            <td class="l-t-td-right"><input name="fIdn" type="text" value="" ltype='date' validate="{maxlength:18}" ligerui="{width:180}"/></td>
            <td class="l-t-td-left">母亲身份证号：</td>
            <td class="l-t-td-right"><input name="mIdn" type="text" value="" ltype='text' validate="{maxlength:18,idno:true}"/></td>
            <td class="l-t-td-left">婚姻状况：</td>
            <td class="l-t-td-right"><input type="text" id="marital-txt" name="_marital-txt" ltype="select" ligerui="{ valueFieldID:'marital',data: [{'id':'1','text':'未婚'},{'id':'2','text':'已婚'},{'id':'3','text':'丧偶'},{'id':'4','text':'离婚'},{'id':'5','text':'未说明的婚姻状况'}]}"/></td>
            <td class="l-t-td-left">是否有结婚证：</td>
            <td class="l-t-td-right"><input type="radio" id="isMCert-txt" name="isMCert" ltype="radioGroup" ligerui="{ valueFieldID:'isMCert',data: [{'id':'1','text':'是'},{'id':'0','text':'否'}]}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left" colspan="2"><table width="100%" cellspacing="0" cellpadding="0" border="0" class="l-text-suffix-wrap">
                <tr>
                    <td align="left" width="120">是否受过劳动或技能培训：</td>
                    <td align="left"><input type="radio" id="isTech-txt" name="isTech" ltype="radioGroup" ligerui="{ valueFieldID:'isTech',data: [{'id':'1','text':'是'},{'id':'0','text':'否'}]}"/></td>
                </tr>
            </table></td>

            <td class="l-t-td-left">工作单位：</td>
            <td class="l-t-td-right"><input name="workunit" type="text" value="" ltype='text' validate="{maxlength:50}"/></td>
            <td class="l-t-td-left">单位性质：</td>
            <td class="l-t-td-right"><input type="text" id="unitType-txt" name="_unitType-txt" ltype="select" ligerui="{ valueFieldID:'unitType',data: [{'id':'9','text':'国有企业'},{'id':'15','text':'社会团体'},{'id':'0','text':'机关'},{'id':'6','text':'差额拨款事业'},{'id':'10','text':'国有控股企业'},{'id':'16','text':'民办非企业'},{'id':'1','text':'事业'},{'id':'7','text':'自收自支事业'},{'id':'11','text':'集体企业'},{'id':'2','text':'企业'},{'id':'8','text':'全额拨款事业'},{'id':'12','text':'外资企业'},{'id':'3','text':'民间组织'},{'id':'13','text':'合资企业'},{'id':'4','text':'军队'},{'id':'14','text':'私营企业(又称民营企业)'},{'id':'5','text':'其他'}]}"/></td>
            <td class="l-t-td-left">职业：</td>
            <td class="l-t-td-right"><input name="vocation" type="text" value="" ltype='text' validate="{maxlength:20}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">职务：</td>
            <td class="l-t-td-right"><input name="post" type="text" value="" ltype='spinner' validate="{maxlength:20}" ligerui="{width:180}"/></td>
            <td class="l-t-td-left">专长（特长）：</td>
            <td class="l-t-td-right"><input name="spec" type="text" value="" ltype='text' validate="{maxlength:20}"/></td>
            <td class="l-t-td-left">月收入：</td>
            <td class="l-t-td-right"><input name="salary" type="text" value="" ltype='spinner' validate="{}"/></td>
            <td class="l-t-td-left">人员类别：</td>
            <td class="l-t-td-right"><input type="text" id="citizenType-txt" name="_citizenType-txt" ltype="select" ligerui="{ valueFieldID:'citizenType',data: [{'id':'1','text':'城市人员类别'},{'id':'1_1','text':'学龄前'},{'id':'1_2','text':'未就业人员'},{'id':'1_3','text':'失业人员'},{'id':'1_4','text':'退休人员'},{'id':'1_5','text':'学生'},{'id':'1_6','text':'灵活就业人员'},{'id':'1_7','text':'三无人员'},{'id':'1_8','text':'在职职工'},{'id':'1_9','text':'其他'},{'id':'2','text':'城市特殊人员'},{'id':'2_1','text':'征地无业'},{'id':'2_2','text':'水库移民'},{'id':'2_3','text':'国有森工'},{'id':'2_4','text':'高校毕业生'},{'id':'2_5','text':'释放人员'},{'id':'2_6','text':'退役军人'},{'id':'2_7','text':'农垦企业人员'},{'id':'2_8','text':'优抚对象'},{'id':'3','text':'农村特殊人员'},{'id':'3_1','text':'征地农民'},{'id':'3_2','text':'水库移民'},{'id':'3_3','text':'在校学生'},{'id':'3_4','text':'失学儿童'},{'id':'3_5','text':'退伍军人'},{'id':'3_6','text':'优抚对象'},{'id':'3_7','text':'两劳释放人员'}]}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">劳动能力：</td>
            <td class="l-t-td-right"><input type="text" id="work-txt" name="_work-txt" ltype="select" ligerui="{ valueFieldID:'work',data: []}"/></td>
            <td class="l-t-td-left">参保情况：</td>
            <td class="l-t-td-right"><input type="text" id="insurance-txt" name="_insurance-txt" ltype="select" ligerui="{ valueFieldID:'insurance',data: [{'id':'1','text':'大病补充医疗保险'},{'id':'2','text':'城镇职工养老保险'},{'id':'3','text':'城镇职工医疗保险'},{'id':'4','text':'城乡居民医疗保险'},{'id':'5','text':'城乡居民养老保险'},{'id':'6','text':'农村社会养老保险'},{'id':'7','text':'农村合作医疗保险'},{'id':'0','text':'其它商业保险'}]}"/></td>
            <td class="l-t-td-left">是否服兵役：</td>
            <td class="l-t-td-right"><input type="radio" id="isSeverArmy-txt" name="isSeverArmy" ltype="radioGroup" ligerui="{ valueFieldID:'isSeverArmy',data: [{'id':'1','text':'是'},{'id':'0','text':'否'}]}"/></td>
            <td class="l-t-td-left">健康状况：</td>
            <td class="l-t-td-right"><input type="text" id="health-txt" name="_health-txt" ltype="select" ligerui="{ valueFieldID:'health',data: [{'id':'01','text':'健康或良好'},{'id':'02','text':'一般或较弱'},{'id':'03','text':'重病'}]}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">残疾证号：</td>
            <td class="l-t-td-right"><input name="disaNo" type="text" value="" ltype='text' validate="{maxlength:20}"/></td>
            <td class="l-t-td-left">重病：</td>
            <td class="l-t-td-right"><input type="text" id="disease-txt" name="_disease-txt" ltype="select" ligerui="{ valueFieldID:'disease',data: [{'id':'1','text':'精神病'},{'id':'2','text':'麻风病'}]}"/></td>
            <td class="l-t-td-left">慢性病：</td>
            <td class="l-t-td-right"><input type="text" id="chrDisease-txt" name="_chrDisease-txt" ltype="select" ligerui="{ valueFieldID:'chrDisease',data: [{'id':'1','text':'恶性肿瘤'},{'id':'2','text':'白血病'},{'id':'3','text':'再生障碍性贫血'},{'id':'4','text':'系统性红斑狼疮晚期'},{'id':'5','text':'心脑血管疾病造成的瘫痪'},{'id':'6','text':'类风湿病'},{'id':'7','text':'糖尿病'},{'id':'0','text':'其他'}]}"/></td>
            <td class="l-t-td-left">民政对象：</td>
            <td class="l-t-td-right"><input type="text" id="civilType-txt" name="_civilType-txt" ltype="select" ligerui="{ valueFieldID:'civilType',data: [{'id':'1','text':'城市低保对象'},{'id':'2','text':'农村低保对象'},{'id':'3','text':'农村五保对象'},{'id':'0','text':'其他对象'}]}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">重点关注人员：</td>
            <td class="l-t-td-right"><input type="text" id="mainCitizen-txt" name="_mainCitizen-txt" ltype="select" ligerui="{ valueFieldID:'mainCitizen',isMultiSelect:true,data: [{'id':'1','text':'病'},{'id':'2','text':'空巢'},{'id':'3','text':'孤寡老人'},{'id':'4','text':'困难人员'},{'id':'5','text':'留守儿童'},{'id':'6','text':'高龄老人'},{'id':'7','text':'孤儿'},{'id':'8','text':'困难军转干部'},{'id':'9','text':'重度残疾'},{'id':'0','text':'其他'}]}"/></td>
            <td class="l-t-td-left">重点管理人员：</td>
            <td class="l-t-td-right"><input type="text" id="mainManaged-txt" name="_mainManaged-txt" ltype="select" ligerui="{ valueFieldID:'mainManaged',data: [{'id':'1','text':'社区矫正'},{'id':'2','text':'刑解释教人员'},{'id':'3','text':'法轮功邪教人员'},{'id':'0','text':'其他'}]}"/></td>
            <td class="l-t-td-left">生育状况：</td>
            <td class="l-t-td-right"><input type="text" id="bear-txt" name="_bear-txt" ltype="select" ligerui="{ valueFieldID:'bear',data: [{'id':'1','text':'未育'},{'id':'2','text':'现孕'},{'id':'3','text':'已育'}]}"/></td>
            <td class="l-t-td-left">避孕节育措施：</td>
            <td class="l-t-td-right"><input type="text" id="contraception-txt" name="_contraception-txt" ltype="select" ligerui="{ valueFieldID:'contraception',data: [{'id':'1','text':'结扎'},{'id':'2','text':'安环'},{'id':'3','text':'皮埋'},{'id':'4','text':'口服避孕药'},{'id':'5','text':'避孕套'},{'id':'0','text':'其他'}]}"/></td>
        </tr>
        <tr>

            <td class="l-t-td-left">备注：</td>
            <td class="l-t-td-right" colspan="7"><input name="remarks" type="text" ltype="text" validate="{maxlength:100}"/></td>
        </tr>

    </table>
    <div id="prop"></div>
	<script type="text/javascript">
		function getSelected()
        {
            var manager = $("#prop").ligerGetGridManager();
            var row = manager.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
            alert(JSON.stringify(row));
        }
        function getData()
        {
            var manager = $("#prop").ligerGetGridManager();
            var data = manager.getData();
            alert(JSON.stringify(data));
        }
	</script>
	<p><a class="l-button" style="width:120px" onclick="getSelected()">获取选中的值(选择行)</a></p>
	<p><a class="l-button" style="width:120px" onclick="getData()">获取当前的值</a></p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
	<p>sdfasdfasdf</p>
</form>

	</div>

<div class="toolbar-tm"><div class="toolbar-tm-bottom"></div></div>

</body>
</html>
