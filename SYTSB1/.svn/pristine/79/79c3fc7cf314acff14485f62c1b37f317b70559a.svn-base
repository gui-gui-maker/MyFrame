<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp" %>

<script type="text/javascript">


	//jQuery页面载入事件
	$(function () {


		$("#toolbar1").ligerButton({
			items: [
				{text: "保存", icon: "save", click: function () {
					top.$.dialog.tips("系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成", 5, "../icons/dialog/32X32/i.png", function () {
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
						content: 'url:demo/detail2.jsp'
					});
				}},
				"-",
				{title: "sxgdfgerg", disabled: true, icon: "save", click: function () {
				}},
				{title: "asdfasdfabfsdfgv", disabled: false, icon: "del", click: function () {
					$.ligerui.get("xbnd9ero8g93w4tg").set("disabled", true);
				}},
				{title: "sdfsdf", id: "xbnd9ero8g93w4tg", img: "/k/kui/images/icons/16/card.png", click: function () {
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


		$("#newData").attr("src", "yfaz/special/sixty/sixty_archives_detail.jsp?model=${param.model}&status=${param.status}&id=${param.id}");

	});

</script>
</head>
<body>
<div id="layout">
	<div class="item-tm">wefwefwefwef</div>
	<div class="item-tm">wefwefwefwef</div>
	<div class="item-tm">wefwefwefwef</div>
	<div class="item-tm">wefwefwefwef</div>
	<div class="navtab">
		<div title="申请单" lselected="true">
			<div style="line-height:32px;">
				<p>　　成都川大科鸿新技术研究所以学校强大的教学、科研实力为后盾，是专业从事计算机系统集成、软件研发、电子标签及智能卡读写设备研制的科研机构。研究所固定资产1500万，自有办公楼1800平方米。研究所通过了CMMI3级国际认证、ISO9001:2000质量管理体系认证、计算机系统集成三级资质认证、ISO14000环保体系认证，取得了国家工业产品生产许可证办公室颁发的IC卡读写机生产许可证书，是四川省高新技术企业、四川省软件企业、中国电子标签标准工作组成员单位、中国防伪技术协会常务理事单位、中国防伪行业协会理事单位、中国自动识别技术协会会员、2008年北京奥运会和残奥会门票设计及查验入围企业。连续多年被评为成都市高新区纳税大户。</p>
<p>　　研究所管理制度健全，组织机构完善，先后在北京、郑州、重庆、厦门等地设立了分公司或分支机构。先后承担了国家质检总局、公安部、卫生部、教育部、铁道部及省市级重要网络系统的建设和软件开发，已获得多项技术专利和计算机软件著作权。</p>
<p>　　近年来，研究所一直致力于RFID和智能卡读写设备的研发和生产，目前已有多款定型产品和解决方案。研究所紧紧抓住RFID技术的发展趋势和潮流，不断加大在RFID产业方面的研发和生产投入，目前研究所在RFID和智能卡读写设备方面的研发技术处于国内领先水平，在手机支付（NFC）方面的研发能力处于世界前沿水平。研究所成立了RFID专家委员会、RFID研发中心，同时还建成了RFID和智能卡读写设备生产车间。研究所利用RFID技术为教育部、铁道部、社会劳动保障部研发实施的&ldquo;全国大中专学生购票优惠卡防伪系统&rdquo;是2003年度亚洲最大的电子标签应用项目，在证件证书防伪领域目前是我国除第二代身份证之外最大的电子标签应用项目。同年，研究所又利用RFID技术成功实施了成都市组织机构代码证电子副本项目，对整个成都市组织机构代码证起到了数字化和防伪作用。另外，研究所还研制了RFID学生证、RFID校园一卡通、RFID智能巡检系统、RFID固定资产管理系统、RFID电子门票等产品。</p>
<p>　　Chengdu university of new technology, the research so strong school of teaching, scientific research strength as the backing, is specialized is engaged in the computer system integration, software development, electronic tag and smart card reading and writing equipment research and scientific research institutions. Research institute fixed assets 15 million, 1800 square meters has its own office building. Institute through the CMMI3 class of international authentication, the ISO9001:2000 quality management system certification, computer system integration level 3 qualification certification, ISO14000 environmental protection system authentication, has obtained the national industrial production license issued by the office of the IC card reading and writing machine production certificate, sichuan province, sichuan province high and new technology enterprise software enterprise, China electronic tag standard members of the working party unit, China anti-counterfeiting technology of standing director unit, China security industry association, China's automatic identification technology association, member of the 2008 Beijing Olympic Games and the paralympics games tickets design and check on enterprise. For many years been rated as chengdu high-tech zone large taxpayer.</p>
<p>  　　Research institute management system is perfect, integrated organization, successively in Beijing, zhengzhou, chongqing, xiamen, set up a branch or branches. Has undertaken the state quality inspection administration and the ministry of public security, the ministry of public health, the ministry of education, the ministry of railways and important network system of ﹑ construction and software development, and has access to a number of patents and copyright in computer software.</p>
<p>  　　In recent years, the institute has been committed to the RFID and smart CARDS reading and writing equipment research and development and the production, at present had much money to finalize the design products and solutions. Research institute hold RFID technology development trend and trends, and constantly increase in RFID industry research and development and production input, the present study RFID and smart CARDS in reading and writing the equipment research and development technology a leading domestic level, in the mobile payment (NFC) research and development ability in the world frontier level. The establishment of the institute of the expert committee, RFID RFID r&amp;d center, also built a RFID and smart CARDS reading and writing equipment production workshop. Using RFID technology institute for the ministry of education, the ministry of railways, social labor security research and development of the implementation of the &quot;national college students to block tickets anti-counterfeiting system&quot; is 2003 annual Asia's largest electronic tag application project, in the certificate in our country at present is security certificate field in the second generation id card besides the biggest electronic tag application project. In the same year, the use of RFID technology institute and successful implementation of chengdu city organization code certificate electronic copy project, to the chengdu organization code certificate have digital and security role. In addition, the institute also developed RFID student id card, RFID campus card, RFID intelligent checking system, RFID fixed assets management system, RFID tickets and other products.</p>
			</div>
		</div>
		<div title="退役军人档案信息">
			<div style="line-height:32px;">
				<p>　　经过全党各级党组织和广大党员的反复酝酿、逐级遴选，经过各选举单位的共同努力，2270名党的十八大代表已经全部选举产生了。他们将肩负全党8000多万名党员、400多万个基层党组织的信任和重托，带着全国各族人民的愿望和期盼，光荣地出席党的第十八次全国代表大会。在此，我们对当选为党的十八大代表的同志们表示热烈的祝贺！</p>
<p>　　党的十八大是我们党在全面建设小康社会关键时期和深化改革开放、加快转变经济发展方式攻坚时期召开的一次十分重要的会议，是全党全国各族人民政治生活中的一件大事，对于我们党高举中国特色社会主义伟大旗帜，团结带领全国各族人民继续全面建设小康社会、加快推进社会主义现代化、开创中国特色社会主义事业新局面，具有重大而深远的意义。选出符合中央要求、广大党员拥护的党代表，对于开好十八大至关重要。去年十月以来，各选举单位党组织认真按照中央的部署要求，严格履行党章、充分发扬民主、切实加强领导、坚持选好选优，圆满完成了十八大代表选举工作。选举产生的十八大代表，政治素质好、议事能力强，结构合理、分布广泛，具有先进性和广泛代表性，为十八大的胜利召开奠定了坚实的思想基础和组织基础。</p>
<p>　　一个代表一面旗。党的全国代表大会是党的最高领导机关，党的重大问题要由党的全国代表大会讨论决定。党的全国代表大会的代表是从全体党员中逐级遴选出的优秀分子，一言一行体现党的形象，备受社会关注。党的十八大代表要带头遵守和贯彻党章，按优秀党员的标准严格要求自己，从思想上、行动上保持先进性和纯洁性；带头执行党的路线方针政策，讲政治、顾大局、守纪律，始终同党中央保持高度一致；带头践行党的根本宗旨和社会主义核心价值体系，不断改进思想作风、工作作风和生活作风，赢得党员群众公认；带头创先争优，服务大局、服务群众，在生产、工作、学习和社会生活各个方面作好表率。</p>
<p>　　使命高于一切，责任重于泰山。党的十八大代表将听取和审查中央委员会和中央纪律检查委员会的报告，讨论和决定党的重大问题，选举新一届中央领导集体。十八大是实行党代表任期制后召开的第一次党的全国代表大会，与过去相比，这一届代表肩负的责任更重了，素质要求也更高了。每一位十八大代表都要进一步增强政治责任感和历史使命感，积极参加党组织举办的学习培训，加强应知应会知识学习，提高履职尽责能力。要坚持群众路线，深入基层调查研究，摸实情、听民声、问民计，把广大党员、群众的意见和要求反映到十八大上去。要以饱满的政治热情和昂扬的精神状态参与十八大的各项议程，正确行使民主权利，忠实履行代表职责，积极建言献策，为确保大会圆满成功做出应有的贡献。闭会期间，代表们要时刻牢记使命，认真学习宣传贯彻十八大精神，密切联系党员群众，立足本职干实事、拼搏奉献创一流，在推动科学发展、促进社会和谐中发挥更大的作用。</p>
<p>　　盛世迎盛会，党心聚民心。我们坚信，在以胡锦涛同志为总书记的党中央坚强领导下，经过全体代表的共同努力，党的十八大一定能开成一次团结的大会、胜利的大会、奋进的大会，为党和国家发展描绘更加美好的蓝图，在中华民族伟大复兴的征程上谱写更加灿烂辉煌的新篇章。</p>
			</div>
		</div>
	</div>
	<div class="toolbar-tm" position="bottom">
		<div class="toolbar-tm-bottom">
			<div id="toolbar1"></div>
		</div>
	</div>
<div>
</body>
</html>
