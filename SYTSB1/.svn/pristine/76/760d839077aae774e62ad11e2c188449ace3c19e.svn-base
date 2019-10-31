<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}" pageKeys="detail1">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>

    <script type="text/javascript">
	var ABC = "您得到的值是：detail1=33333333333333333333333333333333";//alert(api)
	//var api = frameElement.api, W = api.opener;
	$(function () {

		//$("#title").pageTitle({text:"sdfasdf"});
		//$("#title").pageTitle({text:"sdfasdf"});
		pageTitle({to: "#titleElement", text: "软件开发管理系统——编辑", note: '<a href="javascript:void(0);">点击操作1</a><a href="javascript:void(0);">点击操作2</a><a href="javascript:void(0);">点击操作3</a><a href="javascript:void(0);">点击操作4</a><a href="javascript:void(0);">点击操作5</a><a href="javascript:void(0);">点击操作6</a>这里是副标题，可带操作说明', icon: "k/kui/images/menu-icon/32/places.png"});
		$("#toolbar1").ligerButton({
			items: [
				{text: "保存", id: "save", icon: "save", disabled: true, click: function () {
					top.$.dialog.tips("系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成", 5, "../icons/32X32/i.png", function () {
						alert("tips完成回调函数0000000000000");
					}, 0);
				}},
				{text: "点击这里进行新增按钮操作", id: "gesefwefwef", click: function () {
					$.ligerui.get("gwrhb").addButtonItem({text: "来，新加的按钮", icon: "copy", id: "wefwef", click: function () {
						alert("新增xxxxxxxxxxxxxxx")
					}});
					//alert($.ligerui.get("gwrhb").getValue());
				}},
				{text: "打开窗口1", id: "gwrhb", click: function () {
					var wObj = top.$.dialog({
						id: "detail1",
						width: 600,
						height: 400,
						lock: true, parent: api,
						title: "详细页面2",
						cancel: function () {
							api.focus()
						},
						ok: function () {
						},
						content: 'url:app/demo/detail2.jsp'
					});
				}},

				{text: "删除不用的数据", icon: "del", id: "bwef", click: function () {
					alert("我被点了吗")
				}},
				{text: "删除按钮", icon: "del", id: "del", click: function () {
					var g = $.ligerui.get("bwef").destroy();
					$.ligerui.get("del").destroy()
				}},
				{text: "关闭", id: "close", icon: "cancel", click: function () {
					api.close();
				} }
			]
		});
		$("#buttonBar").ligerButton({
			items: [
				{text: "保存", icon: "save", click: function () {
					top.$.dialog.tips("系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成", 5, "../icons/32X32/i.png", function () {
						alert("tips完成回调函数11111111111111111");
					}, 0);
				}},
				{text: "点击这里进行新增按钮操作", click: function () {
					$.ligerui.get("gwrhb").addButtonItem({text: "来，新加的按钮", icon: "copy", id: "wefwef", click: function () {
						alert("新增xxxxxxxxxxxxxxx")
					}});
					//alert($.ligerui.get("gwrhb").getValue());
				}},
				{text: "打开窗口2", click: function () {
					var wObj = top.$.dialog({
						id: "detail2",
						width: 600,
						height: 400,
						lock: true, parent: api,
						title: "详细页面2",
						//cancel:function(){},
						cancel: true,
						ok: function () {
							var datas = this.iframe.contentWindow.getSelectResult();
							if (datas) {
								alert(datas)
							}
							return true;
						},
						content: 'url:app/demo/detail2.jsp'
					});
				}},
				"-",
				{title: "sxgdfgerg", disabled: true, icon: "save", click: function () {
				}},
				{title: "asdfasdfabfsdfgv", disabled: false, icon: "del", click: function () {
					$.ligerui.get("xbnd9ero8g93w4tg").set("disabled", true);
				}},
				{title: "sdfsdf", id: "xbnd9ero8g93w4tg", img: "/k/kui/images/icons/card.gif", click: function () {
					alert("3333")
				}},
				{text: "删除不用的数据", icon: "del", click: function () {
					alert("我被点了吗")
				}},
				{text: "删除按钮", icon: "del", click: function () {
					var g = $.ligerui.get("bwef").destroy();
					$.ligerui.get("del").destroy()
				}},
				{text: "关闭", icon: "cancel", click: function () {
					api.close();
				} }
			]
		});
		$("#zdyToolbar").ligerToolBar({
			items: [
				"-",
				{icon: "del", id: "bwef", click: function () {
					$("#buttonBar").ligerButton({
						items: [
							{title: "1111111", id: "xbnd9ero8g93w4tg", img: "/k/kui/images/icons/card.gif", click: function () {
								alert("3333")
							}},
							{text: "2222222222", icon: "del", click: function () {
								alert("我被点了吗")
							}},
							"-",
							{text: "3333333333", icon: "del", click: function () {
								var g = $.ligerui.get("bwef").destroy();
								$.ligerui.get("del").destroy()
							}},
							{text: "4444444444", icon: "cancel", click: function () {
								api.close();
							} }
						]
					});
				}},
				{id: "test1", img: "/k/kui/images/icons/car-2.gif"},
				{id: "test2", disabled: true, img: "/k/kui/images/icons/book-9.gif"},
				{id: "test3", img: "/k/kui/images/icons/dialog.gif"},
				{id: "test4", img: "/k/kui/images/icons/calendar.gif"},
				{img: "/k/kui/images/icons/car.gif"},
				"-",
				{img: "/k/kui/images/icons/card.gif"},
				{img: "/k/kui/images/icons/chest.gif"},
				{img: "/k/kui/images/icons/door.gif"},
				{img: "/k/kui/images/icons/file-set.gif"},
				"-",
				{img: "/k/kui/images/icons/forbid.png"},
				{img: "/k/kui/images/icons/next.gif"},
				{img: "/k/kui/images/icons/operate-adjust.gif"},
				{img: "/k/kui/images/icons/msn.gif"},
				{img: "/k/kui/images/icons/mail.gif"},
				"-",
				{text: "删除按钮", icon: "del", id: "del", click: function () {
					var g = $.ligerui.get("bwef").destroy();
					$.ligerui.get("del").destroy()
				}},
				{text: "关闭", id: "close", icon: "cancel", click: function () {
					api.close();
				} }
			]
		});
		$("#zdyToolbar2").ligerToolBar({
			items: [
				"-",
				{text: "上报", icon: "appear"},
				{text: "盖章", icon: "seal"},
				{text: "退回", icon: "return"},
				{text: "流程跟踪", icon: "follow"},
				{text: "处理", icon: "dispose"},
				{text: "退回原因", icon: "return-cause"},
				{text: "启用", icon: "accept"},
				{text: "禁用", icon: "forbid"},
				{text: "公式", icon: "formula"},
				{text: "台账", icon: "standing-book"},
				{text: "记录", icon: "take-notes"},
				{text: "合并", icon: "merge"},
				{text: "下发", icon: "issued"},
				{text: "下拨", icon: "appropriation"},
				{text: "发放", icon: "provide"},
				"->",
				{title: "提交", icon: "submit", click: function () {
					$("#zdyToolbar").ligerToolBar().setEnabled({test1: false, test2: true, test3: false, test4: false});
				}}
			]
		});
		$(".l-page-title-button a").click(function () {
			alert($(this).text())
		})
		$("#opClose").focus();
		var ddd = {
			buttons: [
				{text: "上一步，下一步，自动产生"}
			],
			pages: [
				{title: "sdfsdfsdf", url: "sdfsdf"}
			]
		}
	});
	function init_detail1() {
		//top.$.dialog.alert("自定义初始化页面init函数",null,api);
		//top.$.dialog.confirm("自定义初始化页面init函数2222",function(){alert("yes")},function(){alert("no")},api);
		//top.$.dialog.prompt("自定义初始化页面init函数3333",function(){alert("yes")},"xxxxxxxxxxxxxxxxx",api);
		//top.$.dialog.tips("系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成",3,"k/kui/images/icons/32X32/hits.png",function(){alert("是不");},0);
		//top.$.dialog.notice("系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成",3,"k/kui/images/icons/32X32/hits.png",function(){alert("是不");},0);
		//$.ligerDialog.error('系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成系统开发完');
		$.ligerDialog.open({url: 'app/demo/detail2.jsp', title: "我们是窗口", width: 400, height: 300, modal: true, showMax: true, showMin: true});
	}
	;

	//	(function ($) {
	//		//页面标题
	//		//13-5-2 上午11:09 lybide
	//		$.fn.pageTitle = function (options) {
	//			var defaults = {
	//				text: "页面标题",
	//				note: "",
	//				icon: ""
	//			};
	//			var opts = $.extend({}, defaults, options);
	//			return this.each(function () {
	//				$this = $(this);
	//				// 建造插件特定选项
	//				var o = $.meta ? $.extend({}, opts, $this.data()) : opts;
	//				// 更新插件风格
	//				var lpt = $('<div class="l-page-title"><div class="l-page-title-div"></div></div>');
	//				lpt.append('<div class="l-page-title-text"><h1>222' + o.text + '</h1></div>');
	//				if (o.note) {
	//					lpt.append('<div class="l-page-title-note">1111' + o.note + '</div>');
	//					lpt.addClass("has-note");
	//				}
	//				if (o.icon) {
	//					lpt.append('<div class="l-page-title-icon"><img src="' + o.note + '" border="0"></div>');
	//					lpt.addClass("has-icon");
	//				}
	//				/*$this.css({
	//					backgroundColor: o.background,
	//					color: o.foreground
	//				});*/
	//				//var markup = $this.html();
	//				// 调用格式化函数
	//				//markup = $.fn.hilight.format(markup);
	//				$this.html(lpt);
	//			});
	//		};
	//	})(jQuery);

    </script>
</head>
<body>



<div class="item-tm" id="titleElement">
	<%--<div class="l-page-title has-icon has-note">
		<div class="l-page-title-div"></div>
		<div class="l-page-title-text"><h1>软件开发管理系统——编辑</h1></div>
		<div class="l-page-title-note"><a href="javascript:void(0);">点击操作1</a><a href="javascript:void(0);">点击操作2</a><a href="javascript:void(0);">点击操作3</a><a href="javascript:void(0);">点击操作4</a><a href="javascript:void(0);">点击操作5</a><a href="javascript:void(0);">点击操作6</a>这里是副标题，可带操作说明</div>
		<div class="l-page-title-icon"><img src="k/kui/images/menu-icon/32/places.png" border="0"></div>
	</div>--%>
</div>

<div class="item-tm" isTitle="true">
	<div class="l-page-title">
		<div class="l-page-title-div"></div>
		<div class="l-page-title-button">
			<a href="javascript:void(0);"><span>系统管理</span></a>
			<a href="javascript:void(0);"><span>模板管理</span></a>
			<a href="javascript:void(0);"><span>信息管理</span></a>
			<a href="javascript:void(0);"><span>印章管理</span></a>
			<a href="javascript:void(0);"><span>日志管理</span></a>
			<a href="javascript:void(0);"><span>办公自动化管理</span></a>
			<a href="javascript:void(0);"><span>车辆管理</span></a>
			<a href="javascript:void(0);"><span>公文管理</span></a>
			<a href="javascript:void(0);"><span>码表管理</span></a>
		</div>
	</div>
</div>

<div class="item-tm" isTitle="true">
	<div class="l-page-note"><div class="l-page-note-div">操作说明：近年来，研究所一直致力于RFID和智能卡读写设备的研发和生产，目前已有多款定型产品和解决方案。研究所紧紧抓住RFID技术的发展趋势和潮流，不断加大在RFID产业方面的研发和生产投入，目前研究所在RFID和智能卡读写设备方面的研发技术处于国内领先水平。</div></div>
</div>

<div class="item-tm">
	<div class="div1" id="zdyToolbar" style=""></div>
	<div class="div1" id="zdyToolbar2" style=""></div>
	<div class="div1" id="buttonBar" style="padding:10px;text-align:center;"></div>
</div>

<div class="item-tm">
	<p>自定义区3</p>
</div>

<div class="scroll-tm">
<form id="form1" action="app/demo/test.json" getAction="app/demo/test.json" showToolbar="false">
    <input type="hidden" id="id" name="id">
    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">

        <tr>
            <td class="l-t-td-left"> 变通文本：</td>
            <td class="l-t-td-right"><input name="t1" type="text" value="test" ltype="text" readonly validate="{required:true,maxlength:113}" ligerui="{explain:'请在这里写下你的大名'}"/>
            </td>
            <td class="l-t-td-left"> 后缀：</td>
            <td class="l-t-td-right"><input name="t2" type="text" ltype='text' ligerui="{suffix:'月',onChangeValue:function(e){alert(e)}}"
											validate="{required:true,maxlength:10}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 自定后缀宽度：</td>
            <td class="l-t-td-right"><input name="t3" type="text" ltype='text' ligerui="{suffix:'后缀很长',suffixWidth:'60',nullText:'不能为空哈'}"
											validate="{required:true,maxlength:10}"/>
            </td>
            <td class="l-t-td-left"> 后缀支持HTML：</td>
            <td class="l-t-td-right"><input name="t4" type="text" ltype='text' ligerui="{suffix:'<font color=red>红色</font>'}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 调整器：</td>
            <td class="l-t-td-right"><input name="num" type="text" ltype='spinner'
											validate="{required:true}" value="" ligerui="{type:'int',suffix:'后缀很长',suffixWidth:'60',explain:'一定得填写'}"/></td>
            <td class="l-t-td-left"> </td>
            <td class="l-t-td-right"></td>

        </tr>
        <tr>
            <td class="l-t-td-left"> 日期测试：</td>
            <td class="l-t-td-right"><input name="date" type="text" ltype='date' value=""/></td>
            <td class="l-t-td-left"> 时间测试：</td>
            <td class="l-t-td-right"><input name="time" type="text" ltype='date'
											validate="{required:true}"
											ligerui="{format:'yyyy-MM-dd hh:mm:ss',explain:'时间不能小于当前时间'}" value="2012-12-12 12:12:12"/></td>

        </tr>
        <tr>
            <td class="l-t-td-left"> 日期测试1：</td>
            <td class="l-t-td-right"><input id="date1" name="date1" type="text" ltype='date' value="" validate="{ltTo:'#date2'}"/></td>
            <td class="l-t-td-left"> 日期测试2008：</td>
            <td class="l-t-td-right"><input id="date2" name="date2" type="text" ltype='date' value="" validate="{gtTo:'#date1'}" ligerui="{suffix:'后缀很长',suffixWidth:'60',explain:'在添加自定义说明。支持html，function(){return string;}'}"/></td>

        </tr>
        <tr>
            <td class="l-t-td-left"> 下拉框：</td><!--注意id和valueFieldID的命名规则，在jsp中我们一般用标签生成-->
            <td class="l-t-td-right" id="com1" title="请点周选择"><input type="text" ltype="select"
																	validate="{required:true}"
																	ligerui="{explain:'swef',valueFieldID:'com1',value:'', data: [ { text:'部门1', id:'1' }, { text:'部门2', id:'2' } ],suffix:'后缀很长，很长，不是吗',suffixWidth:'140'}"/>
            </td>
            <td class="l-t-td-left"> 下拉树：</td>
            <td class="l-t-td-right"><input type="text" name="com2" id="com2-txt" ltype="select" value="2"
											validate="{required:true}"
											ligerui="{explain:'说明是什么。',treeLeafOnly:false,valueField:'code',initValue:'1',tree:{async:false,checkbox:false,url: 'app/demo/tree.json' }}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 复选组：</td>
            <td class="l-t-td-right"><input type="checkbox" name="checkbox1" ltype="checkboxGroup"
											validate="{required:true}"
											ligerui="{onSuccess:test,initValue:'1', url:'app/demo/group.json' }"/></td>
            <td class="l-t-td-left"> 单选组：</td>
            <td class="l-t-td-right"><input type="radio" name="radio1" ltype="radioGroup"
											validate="{required:false}"
											ligerui="{value:'1',data: [ { text:'部门1', id:'1' }, { text:'部门2', id:'2' } ] }"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 复选组分行：</td>
            <td class="l-t-td-right"><input type="checkbox" name="checkbox2" ltype="checkboxGroup"
											validate="{required:true}"
											ligerui="{onSuccess:test,lineWrap:false, url:'app/demo/group.json' }"/></td>
            <td class="l-t-td-left"> 单选组分行：</td>
            <td class="l-t-td-right"><input type="radio" name="radio2" ltype="radioGroup"
											validate="{required:true}"
											ligerui="{lineWrap:false,data: [ { text:'部门1', id:'1' }, { text:'部门2', id:'2' } ] }"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 大文本：</td>
            <td class="l-t-td-right" colspan="3"><textarea name="textarea" cols="70" rows="3" class="l-textarea" validate="{required:true}" ligerui="{explain:'字数不能超过3000个字'}"></textarea></td>
        </tr>
    </table>
</form>
</div>

<div class="toolbar-tm">
	<div class="toolbar-tm-bottom" style="text-align:center;">
		<div id="toolbar1"></div>
	</div>
</div>



</body>
</html>
