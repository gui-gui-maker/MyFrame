<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageKeys="demo_list_pageKeys">
<%@include file="/k/kui-base-list.jsp" %>
<title>通用查询</title>
<script type="text/javascript">
var aaa = 000;
var qmUserConfig = {
	//titleElement:{text:"列表Demo演示",icon:"k/kui/images/icons/32/thumb_up.png",note:"带时间测试,初始值测试"},
	//titleElement:{text:"列表演示，这里是标题",icon:"k/kui/images/icons/32/shopping.png",note:"sfwefwefwef"},
	//自定义查询面板样式参数
	//title:"自定义了表格的标题",
	//sp_defaults: {columnWidth: 0.33, labelAlign: 'right', labelSeparator: '', labelWidth: 80, layout: "column"},//可以自己定义 layout:column,float
	searchButton: {text: "自来查询", id: "button1111", icon: "search-list", click: function () {
		alert("查询前，还想干点什么。");
		Qm.searchData();
	}},
	// 自定义查询面板查询字段
	//id userid 登录人ID user_name 用户 login_time 登录时间 login_ip IP logout_time 退出时间 logout_time 状态
	sp_fields: [
		//{label: "重命名测试", id: "ttttt", name: "id", compare: ">", value: "2007-8-9", format: "yyyy"},
		{group: [
			{label: "登录时间", name: "login_time", compare: ">=", value: "", width: "100"},
			{label: "到", name: "login_time", compare: "<=", value: "", labelAlign: "center", labelWidth: 20, width: "100"}
		]},
		//{newRow:false,label:"带时间测试",name:"logout_time",compare:"like",value:""},
		{group: [
			{label: "退出时间测试", name: "logout_time", compare: ">=", value: "", width: 150},
			{label: "至", name: "logout_time", compare: "<=", value: "", labelAlign: "center", labelWidth: 20, width: 100}
		]},
		{label: "登录人ID", name: "userid", compare: "like", value: ""},
		{label: "用户", name: "user_name", compare: "=", value: "", width: ""},
		{label: "下拉树测试，状态", name: "state", compare: "=", value: "", treeLeafOnly: false, selectBoxWidth: 300}
	],
	//定义按钮，可以直接是extjs的按钮
	tbar: [
		{ text: "上传1", id: "fupload", icon: "add", click: fuploadSingle },
		"-",
		{ text: "上传2", id: "fupload", icon: "add", click: fuploadMore },
		"-",
		{ text: "单表", id: "add", icon: "add", click: add},
		"-",
		{ text: "多Tab", id: "add1", icon: "add", click: add1 },
		"-",
		{ text: "一对多表", id: "add2", icon: "add", click: add2 },
		"-",
		{ text: "详情", id: "detail", icon: "detail", click: detail, title: "点击查看详情"},
		{ title: "打开窗口111", id: "openwin1", img: "k/kui/images/icons/16/thumb_up.png", click: function () {
			openwin1();
		}, disabled: false},
		{ title: "打开窗口222", id: "openwin2", icon: "modify", click: function () {
			openwin2();
		}},
		{ title: "印章", id: "openwin3", icon: "seal", click: function () {
			//winOpen({content:"url:demo/demo_yinzhang1.jsp",width:800,height:600}).max();
			winOpen({content: "url:demo/demo_yinzhang1.jsp", width: 800, height: 600}).max();
			//$.ligerDialog.open({width:500,height:400,url:"demo/demo_yinzhang1.jsp" });
		}},
		{ text: "详情4", id: "yljy1", img: "k/kui/images/icons/16/home_page.png", click: function () {
			yljy1();
		}, disabled: true},
		"-",
		{ text: "修改", id: "edit", icon: "modify", click: edit, disabled: true},
		"-",
		{ text: "删除", id: "del", icon: "delete", click: test, disabled: true},
		"-",
		{ text: "设置查询条件", id: "test", click: function () {
			Qm.setCondition({name: "id", compare: "=", value: "2"});
			Qm.searchData();
		}},
		"-",
		{ text: "清除条件", id: "test", click: function () {
			Qm.setCondition([]);
			Qm.searchData();
		}},
		"-",
		{ text: "上传及拍照", id: "test", click: function () {
			doPicture();
		}}
	],
	//提供以下4个事件
	listeners: {
		null1: "",
		rowClick: function (rowData, rowIndex) {
			//tips("点击第 "+(rowIndex+1)+" 行-"+rowData.id);
		},
		/*rowDblClick: function (rowData, rowIndex) {
			detail(rowData.id);
		},*/
		onDblClickRow :function(rowdata,rowindex,rowDomElement){detail();},
		selectionChange: function (rowData, rowIndex) {
			var count = Qm.getSelectedCount();
			var status = {};
			if (count == 0) {
				status = {yljy1: false, edit: false, del: false};
			} else if (count == 1) {
				status = {edit: true, del: true, yljy1: true};
			} else {
				status = {edit: false, del: true, yljy1: true};
			}
			Qm.setTbar(status);
			//tips("选中 "+count+" 行");
		},
		beforeQmReady: function () {
			//tips("beforeQmReady事件发生在所有参数解析之前");
		},
		//可以邦定ligerui grid原生的事件和属性
		onAfterShowData: function () {
			Qm.getQmgrid().selectRange("id", [30, 2]);
		},
		rowAttrRender: function (item, rowid) {
			if (item.user_name == "运维管理员")
				return 'style="color:#e6640d;"';
			else
				return "";
		},
		null1: ""
	}
};

//top.$.dialog.notice({content:'操作成功！',time:9999});

var tip;
function tips(content) {
	if (!tip) {
		tip = $.ligerDialog.tip({ title: "提示信息", content: content });
	}
	tip.show();
	tip.set("content", content);
}
function test(item) {
	tips(item.id + "=" + item.text);
}

function openwin1() {
	var wObj = winOpen({
		id: "detail1",
		width: 800,
		height: 600,
		lock: true,
		title: "",
		content: "url:demo/demo_detail7.jsp"
	});
	//wObj.max();
}

var xxxxxxx = 123123213;

function openwin1is2() {
	//winOpen.list["detail1"].close();
	winClose("detail1");
	setTimeout(function () {
		var wObj = winOpen({
			id: "aerty34t34534t",
			width: 800,
			height: 600,
			lock: true,
			title: "",
			content: "url:demo/dialog2.jsp"
		});
		//wObj.min();
	}, 40);
	//wObj.max();
}

function openwin2() {
	var wObj = winOpen({
		id: "detail1",
		width: 800,
		height: 600,
		lock: true,
		title: "",
		data: {"window": window},
		okVal: "确定确定打开打开。。。。。。", ok: function () {
		},
		cancelVal: "必须关闭。。。。。。", cancel: function () {
		},
		content: "url:demo/dialog1.jsp"
	});
	//wObj.max();
}
;

function yljy1(arg) {
	//$.ligerDialog.open({width:800,height:600,url:"demo/demo_detail4.jsp" });return;
	var wObj = winOpen({
		width: 800,
		height: 600,
		lock: true,
		title: "详细页面4——带多项按钮操作演示",
		content: "url:demo/demo_detail4.jsp"
	});
}
;

function fuploadSingle() {top.$.dialog.loading("<div>sssssss</div>");
	winOpen({
		width: 700,
		height: 300,
		lock: true,
		title: "页面直接上传文件示例",
		content: "url:demo/demo_fileupload.jsp",
		cancel: true
	});
}
function fuploadMore() {
	winOpen({
		width: 700,
		height: 400,
		lock: true,
		title: "弹出窗口上传文件示例",
		content: "url:demo/demo_fileupload_dialog.jsp",
		cancel: true
	});
}

function add() {
	var width = 700;
	var height = 450;
	var windows = winOpen({
		width: width,
		height: height,
		lock: true,
		data: {window: window},
		title: "单表示例",
		content: "url:demo/demo_detail.jsp"
	});
}
function add1() {
	var width = 700;
	var height = 500;
	var windows = winOpen({
		width: width,
		height: height,
		lock: true,
		title: "多Tab",
		content: "url:demo/demo_detail3.jsp"
	});
}
function add2() {
	var width = 800;
	var height = 600;
	var windows = winOpen({
		width: width,
		height: height,
		lock: true,
		title: "一对多表",
		data: {window: window},
		content: "url:demo/demo_detail2.jsp"
	});
}
function edit() {
	var width = 800;
	var height = 600;
	var windows = winOpen({
		width: width,
		height: height,
		lock: true,
		title: "一对多表",
		content: "url:demo/demo_detail2.jsp?pageStatus=modify"
	});
}
function detail(id) {
	if (!id) {
		id = Qm.getValueByName("id");
	}//alert(Qm.getValueByName("id")+"////");
	var width = 800;
	var height = 600;
	var windows = winOpen({
		width: width,
		height: height,
		lock: true,
		title: "一对多表",
		content: "url:demo/demo_detail2.jsp?pageStatus=detail&id=" + id
	});
}

//调用摄像头拍照
function doPicture() {
	var windows = winOpen({
		width: 1000,
		height: 800,
		lock: true,
		data: {window: window},
		content: "url:demo/yj_grtest_list.jsp"
	});
}
</script>
</head>
<body>

<%--<div class="item-tm" id="titleElement">--%>
<%--<div class="l-page-title">--%>
<%--<div class="l-page-title-div"></div>--%>
<%--<div class="l-page-title-text"><h1>列表演示，为了更好的系统</h1></div>--%>
<%--<div class="l-page-title-note">友情说明：列表数据项<font color="#FF0000">“红色”</font>代表正在开发中。<font color="#0000ff">“蓝色”</font>代表正在制作中。<font color="#00ff00">“绿色”</font>代表已经完成了。<div class="l-page-note"><div class="l-page-note-div">黄色的友情说明：列表数据项<font color="#FF0000">“红色”</font>代表正在开发中。<font color="#0000ff">“蓝色”</font>代表正在制作中。<font color="#00ff00">“绿色”</font>代表已经完成了。更多的说明<a href="javascript:void(0);" onclick="$('#plClose1').click();">标题隐藏</a>，一切为了说明</div></div></div>--%>
<%--<div class="l-page-title-icon"><img src="k/kui/images/icons/32/places.png" border="0"></div>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="item-tm" isTitle="true">--%>
<%--<div class="l-page-note"><div class="l-page-note-div">其它说明，这也是标题的一部分，它具有isTitle="true"，在<a href="javascript:void(0);">标题隐藏</a>时，此栏也一起隐藏。</div></div>--%>
<%--</div>--%>

<div class="item-tm">
	<div id="titleElement" class="l-page-title">
		<div class="l-page-title-div"></div>
		<div class="l-page-title-text"><h1>列表演示，为了更好的系统</h1></div>
		<div class="l-page-title-note">友情说明：列表数据项<font color="#FF0000">“红色”</font>代表正在开发中。<font
				color="#0000ff">“蓝色”</font>代表正在制作中。<font color="#00ff00">“绿色”</font>代表已经完成了。
			<div class="l-page-note">
				<div class="l-page-note-div">黄色的友情说明：列表数据项<font color="#FF0000">“红色”</font>代表正在开发中。<font
						color="#0000ff">“蓝色”</font>代表正在制作中。<font color="#00ff00">“绿色”</font>代表已经完成了。更多的说明<a
						href="javascript:void(0);" onclick="$('#plClose1').click();">标题隐藏</a>，一切为了说明
				</div>
			</div>
		</div>
		<div class="l-page-title-icon"><img src="k/kui/images/icons/32/places.png" border="0"></div>
	</div>
</div>

<div class="item-tm" isTitle="true">
	<div class="l-page-note">
		<div class="l-page-note-div">其它说明，这也是标题的一部分，它具有isTitle="true"，在<a href="javascript:void(0);">标题隐藏</a>时，此栏也一起隐藏。
		</div>
	</div>
</div>

<q:qm pageid="sys_03" singleSelect="false">
	<%--<qm:param name="str1" compare="like" value="A"/>--%>
</q:qm>
<script type="text/javascript">
	//    根据 sql或码表名称获得Json格式数据
	<%--var aa=<u:dict code="community_type"></u:dict>;--%>
	<%--Qm.config.columnsInfo.str3.binddata=<u:dict sql="select t.id,t.name from pub_code_table_values t where t.code_table_id='4028807036572ae1013657b7a4a0000b'"></u:dict>;--%>
	<%--Qm.config.columnsInfo.num1.binddata=<u:dict sql="select id,pid,code,value from test"></u:dict>;--%>
	//Qm.config.columnsInfo.num1.binddata = [
	//	{id: '00000000', text: '优抚类别', children: [
	//		{id: '402880b33d158ac2013d15952f080008', text: '烈士遗属'},
	//		{id: '402880b33d158ac2013d159463ac0006', text: '病故军人遗属'},
	//		{id: '402880b33d158ac2013d159632b60009', text: '60周岁以上农村籍退役人员'},
	//		{id: '402880b33d158ac2013d1594309a0005', text: '在乡复原军人'},
	//		{id: '402880b33d158ac2013d15967c4e000a', text: '60周岁以上错杀被平反人员子女'},
	//		{id: '402880b33d158ac2013d159495200007', text: '因公牺牲军人遗属'},
	//		{id: '402880b33d158ac2013d15973c98000b', text: '60周岁以上烈士子女'},
	//		{id: '402880b33d158ac2013d15977d8b000c', text: '铀矿开采军队退役人员'},
	//		{id: '402880b33d158ac2013d1597bb33000d', text: '其他参加核试验退役人员'},
	//		{id: '402880b33d158ac2013d15981914000e', text: '原8023部队退役人员'},
	//		{id: '402880b33d158ac2013d159875bb000f', text: '红军失散人员'},
	//		{id: '402880b33d158ac2013d1598b1a10010', text: '在乡西路军红军老战士'},
	//		{id: '402880b33d158ac2013d159909b60011', text: '在乡退伍红军老战士'},
	//		{id: '402880b33d158ac2013d1599540c0012', text: '伤残民兵民工'},
	//		{id: '402880b33d158ac2013d1599aed60013', text: '伤残人民警察'},
	//		{id: '402880b33d1a79c4013d1a7e9ae10000', text: '伤残国家机关工作人员'},
	//		{id: '5', text: '残疾军人'},
	//		{id: '6', text: '参战涉核人员'},
	//		{id: '7', text: '参战退役人员'},
	//		{id: '8', text: '带病回乡退伍军人'}
	//	]}
	//];
</script>
</body>
</html>