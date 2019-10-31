<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/rtbox/public/base.jsp"%>

<script type="text/javascript">
	var kFrameConfig=[];
	 kFrameConfig.base="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/";
</script>
<!-- <script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script> -->
<script type="text/javascript" src="rtbox/public/js/uploadAsst.js"></script>

<link href="rtbox/app/templates/default/tpl.css" rel="stylesheet" />
<script type="text/javascript">
	var treeManager = null;
	var menu;
	var actionNodeID;
	$(function() {
		$("#toptoolbar").ligerToolBar({
			items : [ {
				text : '保存',
				click : save,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '导出',
				click : itemExport
			}, {
				line : true
			}, {
				text : '删除',
				click : itemclick
			} ]
		});

		$("form").ligerForm();

		menu = $.ligerMenu({
			top : 100,
			left : 100,
			width : 120,
			items : [ {
				text : '增加',
				click : itemclick,
				icon : 'add'
			}, {
				text : '修改',
				click : itemclick
			}, {
				line : true
			}, {
				text : '查看',
				click : itemclick
			} ]
		});

		//页面布局
		$("#layout1").ligerLayout({
			leftWidth : 200,
			// 			topHeight: 30,
			space : 3,
			allowTopResize : false
		});

		createTree();
		
		initUpload("1");
	});	 
	
	

	function createTree() {
		treeManager = $("#tree1").ligerTree({
			checkbox : true,
			selectCancel : false,
			url : "rtbox/app/templates/default/dir.json",
			onSelect : function(node) {
			},
			onCheck : function(node, checked) {
			},
			onContextmenu : function(node, e) {
				actionNodeID = node.data.text;
				menu.show({
					top : e.pageY,
					left : e.pageX
				});
				return false;
			}
		});
	}

	function itemclick() {
		notice("TBL00005");
		// 		notice("test3");
		// 		alert("保存");
	}

	function itemExport() {
		// 		$("body").mask("正在导出...");
		$.post("com/rt/page/getMap.do", {}, function(data) {
			if (data.success) {
				// 				top.$.notice('导出成功！',4)
				alert("成功");
			} else {
				// 				$.ligerDialog.alert('导出失败！', "error");
				alert("失败");
			}
			// 			$("body").unmask();
		});
	}
	function save() {
		var data = $("#formObj").getValues();
		$.ajax({
			url : "com/rt/page/saveMap.do",
			type : "POST",
			datatype : "json",
			contentType : "application/json; charset=utf-8",
			data : $.ligerui.toJSON(transFormDataToJSON(data)),
			success : function(data, stats) {
				if (data.success) {
					alert("成功");
				} else {
					alert("失败");
				}
			},
			error : function(data) {
				alert("失败");
			}
		});
	}

	 
</script>
<!-- [$RtPageHead] -->

<meta http-equiv=Content-Type content="text/html; charset=x-cp20936">
<meta name=ProgId content=Word.Document>
<meta name=Generator content="Microsoft Word 12">
<meta name=Originator content="Microsoft Word 12">
<link rel=File-List href="6.files/filelist.xml">
<link rel=Edit-Time-Data href="6.files/editdata.mso">
<!--[if !mso]><style>v\:* {behavior:url(#default#VML);}o\:* {behavior:url(#default#VML);}w\:* {behavior:url(#default#VML);}.shape {behavior:url(#default#VML);}</style><![endif]-->
<title>电站锅炉内部检验原始记录</title>
<!--[if gte mso 9]><xml> <o:DocumentProperties>  <o:Author>zrg</o:Author>  <o:LastAuthor>系统管理员</o:LastAuthor>  <o:Revision>2</o:Revision>  <o:TotalTime>0</o:TotalTime>  <o:LastPrinted>2016-02-23T02:08:00Z</o:LastPrinted>  <o:Created>2016-03-18T01:16:00Z</o:Created>  <o:LastSaved>2016-03-18T01:16:00Z</o:LastSaved>  <o:Pages>3</o:Pages>  <o:Words>13483</o:Words>  <o:Characters>76854</o:Characters>  <o:Company>Microsoft</o:Company>  <o:Lines>640</o:Lines>  <o:Paragraphs>180</o:Paragraphs>  <o:CharactersWithSpaces>90157</o:CharactersWithSpaces>  <o:Version>12.00</o:Version> </o:DocumentProperties> <o:CustomDocumentProperties>  <o:KSOProductBuildVer dt:dt="string">2052-10.1.0.5457</o:KSOProductBuildVer> </o:CustomDocumentProperties> <o:OfficeDocumentSettings>  <o:RelyOnVML/>  <o:AllowPNG/> </o:OfficeDocumentSettings></xml><![endif]-->
<link rel=themeData href="6.files/themedata.thmx">
<link rel=colorSchemeMapping href="6.files/colorschememapping.xml">
<!--[if gte mso 9]><xml> <w:WordDocument>  <w:TrackMoves/>  <w:TrackFormatting/>  <w:PunctuationKerning/>  <w:DrawingGridHorizontalSpacing>5.25 磅</w:DrawingGridHorizontalSpacing>  <w:DrawingGridVerticalSpacing>7.8 磅</w:DrawingGridVerticalSpacing>  <w:DisplayHorizontalDrawingGridEvery>0</w:DisplayHorizontalDrawingGridEvery>  <w:DisplayVerticalDrawingGridEvery>2</w:DisplayVerticalDrawingGridEvery>  <w:ValidateAgainstSchemas>false</w:ValidateAgainstSchemas>  <w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid>  <w:IgnoreMixedContent>false</w:IgnoreMixedContent>  <w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText>  <w:DoNotUnderlineInvalidXML/>  <w:DoNotPromoteQF/>  <w:LidThemeOther>EN-US</w:LidThemeOther>  <w:LidThemeAsian>ZH-CN</w:LidThemeAsian>  <w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript>  <w:Compatibility>   <w:SpaceForUL/>   <w:BalanceSingleByteDoubleByteWidth/>   <w:DoNotLeaveBackslashAlone/>   <w:ULTrailSpace/>   <w:DoNotExpandShiftReturn/>   <w:AdjustLineHeightInTable/>   <w:BreakWrappedTables/>   <w:SnapToGridInCell/>   <w:WrapTextWithPunct/>   <w:UseAsianBreakRules/>   <w:DontGrowAutofit/>   <w:SplitPgBreakAndParaMark/>   <w:DontVertAlignCellWithSp/>   <w:DontBreakConstrainedForcedTables/>   <w:DontVertAlignInTxbx/>   <w:Word11KerningPairs/>   <w:CachedColBalance/>   <w:UseFELayout/>  </w:Compatibility>  <m:mathPr>   <m:mathFont m:val="Cambria Math"/>   <m:brkBin m:val="before"/>   <m:brkBinSub m:val="--"/>   <m:smallFrac m:val="off"/>   <m:dispDef/>   <m:lMargin m:val="0"/>   <m:rMargin m:val="0"/>   <m:defJc m:val="centerGroup"/>   <m:wrapIndent m:val="1440"/>   <m:intLim m:val="subSup"/>   <m:naryLim m:val="undOvr"/>  </m:mathPr></w:WordDocument></xml><![endif]-->
<!--[if gte mso 9]><xml> <w:LatentStyles DefLockedState="false" DefUnhideWhenUsed="true"  DefSemiHidden="true" DefQFormat="false" DefPriority="99"  LatentStyleCount="267">  <w:LsdException Locked="false" Priority="0" SemiHidden="false"   UnhideWhenUsed="false" QFormat="true" Name="Normal"/>  <w:LsdException Locked="false" Priority="9" SemiHidden="false"   UnhideWhenUsed="false" QFormat="true" Name="heading 1"/>  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 2"/>  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 3"/>  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 4"/>  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 5"/>  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 6"/>  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 7"/>  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 8"/>  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 9"/>  <w:LsdException Locked="false" Priority="39" Name="toc 1"/>  <w:LsdException Locked="false" Priority="39" Name="toc 2"/>  <w:LsdException Locked="false" Priority="39" Name="toc 3"/>  <w:LsdException Locked="false" Priority="39" Name="toc 4"/>  <w:LsdException Locked="false" Priority="39" Name="toc 5"/>  <w:LsdException Locked="false" Priority="39" Name="toc 6"/>  <w:LsdException Locked="false" Priority="39" Name="toc 7"/>  <w:LsdException Locked="false" Priority="39" Name="toc 8"/>  <w:LsdException Locked="false" Priority="39" Name="toc 9"/>  <w:LsdException Locked="false" Priority="0" UnhideWhenUsed="false"   Name="header"/>  <w:LsdException Locked="false" Priority="0" UnhideWhenUsed="false"   Name="footer"/>  <w:LsdException Locked="false" Priority="35" QFormat="true" Name="caption"/>  <w:LsdException Locked="false" Priority="0" UnhideWhenUsed="false"   Name="page number"/>  <w:LsdException Locked="false" Priority="10" SemiHidden="false"   UnhideWhenUsed="false" QFormat="true" Name="Title"/>  <w:LsdException Locked="false" Priority="1" UnhideWhenUsed="false"   Name="Default Paragraph Font"/>  <w:LsdException Locked="false" Priority="0" UnhideWhenUsed="false"   Name="Body Text"/>  <w:LsdException Locked="false" Priority="11" SemiHidden="false"   UnhideWhenUsed="false" QFormat="true" Name="Subtitle"/>  <w:LsdException Locked="false" Priority="22" SemiHidden="false"   UnhideWhenUsed="false" QFormat="true" Name="Strong"/>  <w:LsdException Locked="false" Priority="20" SemiHidden="false"   UnhideWhenUsed="false" QFormat="true" Name="Emphasis"/>  <w:LsdException Locked="false" SemiHidden="false" Name="Normal Table"/>  <w:LsdException Locked="false" Priority="59" SemiHidden="false"   UnhideWhenUsed="false" Name="Table Grid"/>  <w:LsdException Locked="false" SemiHidden="false" UnhideWhenUsed="false"   QFormat="true" Name="No Spacing"/>  <w:LsdException Locked="false" Priority="60" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Shading"/>  <w:LsdException Locked="false" Priority="61" SemiHidden="false"   UnhideWhenUsed="false" Name="Light List"/>  <w:LsdException Locked="false" Priority="62" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Grid"/>  <w:LsdException Locked="false" Priority="63" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 1"/>  <w:LsdException Locked="false" Priority="64" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 2"/>  <w:LsdException Locked="false" Priority="65" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 1"/>  <w:LsdException Locked="false" Priority="66" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 2"/>  <w:LsdException Locked="false" Priority="67" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 1"/>  <w:LsdException Locked="false" Priority="68" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 2"/>  <w:LsdException Locked="false" Priority="69" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 3"/>  <w:LsdException Locked="false" Priority="70" SemiHidden="false"   UnhideWhenUsed="false" Name="Dark List"/>  <w:LsdException Locked="false" Priority="71" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Shading"/>  <w:LsdException Locked="false" Priority="72" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful List"/>  <w:LsdException Locked="false" Priority="73" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Grid"/>  <w:LsdException Locked="false" Priority="60" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Shading Accent 1"/>  <w:LsdException Locked="false" Priority="61" SemiHidden="false"   UnhideWhenUsed="false" Name="Light List Accent 1"/>  <w:LsdException Locked="false" Priority="62" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Grid Accent 1"/>  <w:LsdException Locked="false" Priority="63" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 1"/>  <w:LsdException Locked="false" Priority="64" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 1"/>  <w:LsdException Locked="false" Priority="65" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 1 Accent 1"/>  <w:LsdException Locked="false" SemiHidden="false" UnhideWhenUsed="false"   QFormat="true" Name="List Paragraph"/>  <w:LsdException Locked="false" SemiHidden="false" UnhideWhenUsed="false"   QFormat="true" Name="Quote"/>  <w:LsdException Locked="false" SemiHidden="false" UnhideWhenUsed="false"   QFormat="true" Name="Intense Quote"/>  <w:LsdException Locked="false" Priority="66" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 2 Accent 1"/>  <w:LsdException Locked="false" Priority="67" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 1"/>  <w:LsdException Locked="false" Priority="68" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 1"/>  <w:LsdException Locked="false" Priority="69" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 1"/>  <w:LsdException Locked="false" Priority="70" SemiHidden="false"   UnhideWhenUsed="false" Name="Dark List Accent 1"/>  <w:LsdException Locked="false" Priority="71" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Shading Accent 1"/>  <w:LsdException Locked="false" Priority="72" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful List Accent 1"/>  <w:LsdException Locked="false" Priority="73" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Grid Accent 1"/>  <w:LsdException Locked="false" Priority="60" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Shading Accent 2"/>  <w:LsdException Locked="false" Priority="61" SemiHidden="false"   UnhideWhenUsed="false" Name="Light List Accent 2"/>  <w:LsdException Locked="false" Priority="62" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Grid Accent 2"/>  <w:LsdException Locked="false" Priority="63" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 2"/>  <w:LsdException Locked="false" Priority="64" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 2"/>  <w:LsdException Locked="false" Priority="65" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 1 Accent 2"/>  <w:LsdException Locked="false" Priority="66" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 2 Accent 2"/>  <w:LsdException Locked="false" Priority="67" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 2"/>  <w:LsdException Locked="false" Priority="68" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 2"/>  <w:LsdException Locked="false" Priority="69" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 2"/>  <w:LsdException Locked="false" Priority="70" SemiHidden="false"   UnhideWhenUsed="false" Name="Dark List Accent 2"/>  <w:LsdException Locked="false" Priority="71" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Shading Accent 2"/>  <w:LsdException Locked="false" Priority="72" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful List Accent 2"/>  <w:LsdException Locked="false" Priority="73" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Grid Accent 2"/>  <w:LsdException Locked="false" Priority="60" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Shading Accent 3"/>  <w:LsdException Locked="false" Priority="61" SemiHidden="false"   UnhideWhenUsed="false" Name="Light List Accent 3"/>  <w:LsdException Locked="false" Priority="62" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Grid Accent 3"/>  <w:LsdException Locked="false" Priority="63" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 3"/>  <w:LsdException Locked="false" Priority="64" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 3"/>  <w:LsdException Locked="false" Priority="65" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 1 Accent 3"/>  <w:LsdException Locked="false" Priority="66" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 2 Accent 3"/>  <w:LsdException Locked="false" Priority="67" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 3"/>  <w:LsdException Locked="false" Priority="68" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 3"/>  <w:LsdException Locked="false" Priority="69" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 3"/>  <w:LsdException Locked="false" Priority="70" SemiHidden="false"   UnhideWhenUsed="false" Name="Dark List Accent 3"/>  <w:LsdException Locked="false" Priority="71" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Shading Accent 3"/>  <w:LsdException Locked="false" Priority="72" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful List Accent 3"/>  <w:LsdException Locked="false" Priority="73" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Grid Accent 3"/>  <w:LsdException Locked="false" Priority="60" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Shading Accent 4"/>  <w:LsdException Locked="false" Priority="61" SemiHidden="false"   UnhideWhenUsed="false" Name="Light List Accent 4"/>  <w:LsdException Locked="false" Priority="62" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Grid Accent 4"/>  <w:LsdException Locked="false" Priority="63" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 4"/>  <w:LsdException Locked="false" Priority="64" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 4"/>  <w:LsdException Locked="false" Priority="65" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 1 Accent 4"/>  <w:LsdException Locked="false" Priority="66" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 2 Accent 4"/>  <w:LsdException Locked="false" Priority="67" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 4"/>  <w:LsdException Locked="false" Priority="68" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 4"/>  <w:LsdException Locked="false" Priority="69" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 4"/>  <w:LsdException Locked="false" Priority="70" SemiHidden="false"   UnhideWhenUsed="false" Name="Dark List Accent 4"/>  <w:LsdException Locked="false" Priority="71" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Shading Accent 4"/>  <w:LsdException Locked="false" Priority="72" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful List Accent 4"/>  <w:LsdException Locked="false" Priority="73" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Grid Accent 4"/>  <w:LsdException Locked="false" Priority="60" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Shading Accent 5"/>  <w:LsdException Locked="false" Priority="61" SemiHidden="false"   UnhideWhenUsed="false" Name="Light List Accent 5"/>  <w:LsdException Locked="false" Priority="62" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Grid Accent 5"/>  <w:LsdException Locked="false" Priority="63" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 5"/>  <w:LsdException Locked="false" Priority="64" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 5"/>  <w:LsdException Locked="false" Priority="65" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 1 Accent 5"/>  <w:LsdException Locked="false" Priority="66" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 2 Accent 5"/>  <w:LsdException Locked="false" Priority="67" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 5"/>  <w:LsdException Locked="false" Priority="68" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 5"/>  <w:LsdException Locked="false" Priority="69" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 5"/>  <w:LsdException Locked="false" Priority="70" SemiHidden="false"   UnhideWhenUsed="false" Name="Dark List Accent 5"/>  <w:LsdException Locked="false" Priority="71" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Shading Accent 5"/>  <w:LsdException Locked="false" Priority="72" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful List Accent 5"/>  <w:LsdException Locked="false" Priority="73" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Grid Accent 5"/>  <w:LsdException Locked="false" Priority="60" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Shading Accent 6"/>  <w:LsdException Locked="false" Priority="61" SemiHidden="false"   UnhideWhenUsed="false" Name="Light List Accent 6"/>  <w:LsdException Locked="false" Priority="62" SemiHidden="false"   UnhideWhenUsed="false" Name="Light Grid Accent 6"/>  <w:LsdException Locked="false" Priority="63" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 6"/>  <w:LsdException Locked="false" Priority="64" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 6"/>  <w:LsdException Locked="false" Priority="65" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 1 Accent 6"/>  <w:LsdException Locked="false" Priority="66" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium List 2 Accent 6"/>  <w:LsdException Locked="false" Priority="67" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 6"/>  <w:LsdException Locked="false" Priority="68" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 6"/>  <w:LsdException Locked="false" Priority="69" SemiHidden="false"   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 6"/>  <w:LsdException Locked="false" Priority="70" SemiHidden="false"   UnhideWhenUsed="false" Name="Dark List Accent 6"/>  <w:LsdException Locked="false" Priority="71" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Shading Accent 6"/>  <w:LsdException Locked="false" Priority="72" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful List Accent 6"/>  <w:LsdException Locked="false" Priority="73" SemiHidden="false"   UnhideWhenUsed="false" Name="Colorful Grid Accent 6"/>  <w:LsdException Locked="false" Priority="19" SemiHidden="false"   UnhideWhenUsed="false" QFormat="true" Name="Subtle Emphasis"/>  <w:LsdException Locked="false" Priority="21" SemiHidden="false"   UnhideWhenUsed="false" QFormat="true" Name="Intense Emphasis"/>  <w:LsdException Locked="false" Priority="31" SemiHidden="false"   UnhideWhenUsed="false" QFormat="true" Name="Subtle Reference"/>  <w:LsdException Locked="false" Priority="32" SemiHidden="false"   UnhideWhenUsed="false" QFormat="true" Name="Intense Reference"/>  <w:LsdException Locked="false" Priority="33" SemiHidden="false"   UnhideWhenUsed="false" QFormat="true" Name="Book Title"/>  <w:LsdException Locked="false" Priority="37" Name="Bibliography"/>  <w:LsdException Locked="false" Priority="39" QFormat="true" Name="TOC Heading"/> </w:LatentStyles></xml><![endif]-->
<style>
<!-- /* Font Definitions */
@font-face {
	font-family: 宋体;
	panose-1: 2 1 6 0 3 1 1 1 1 1;
	mso-font-alt: SimSun;
	mso-font-charset: 134;
	mso-generic-font-family: auto;
	mso-font-pitch: variable;
	mso-font-signature: 3 680460288 22 0 262145 0;
}

@font-face {
	font-family: 黑体;
	panose-1: 2 1 6 9 6 1 1 1 1 1;
	mso-font-alt: SimHei;
	mso-font-charset: 134;
	mso-generic-font-family: modern;
	mso-font-pitch: fixed;
	mso-font-signature: -2147482945 953122042 22 0 262145 0;
}

@font-face {
	font-family: "Cambria Math";
	panose-1: 2 4 5 3 5 4 6 3 2 4;
	mso-font-charset: 1;
	mso-generic-font-family: roman;
	mso-font-format: other;
	mso-font-pitch: variable;
	mso-font-signature: 0 0 0 0 0 0;
}

@font-face {
	font-family: 楷体_GB2312;
	mso-font-alt: 楷体;
	mso-font-charset: 134;
	mso-generic-font-family: modern;
	mso-font-pitch: auto;
	mso-font-signature: 1 135135232 16 0 262144 0;
}

@font-face {
	font-family: 仿宋_GB2312;
	mso-font-alt: 仿宋;
	mso-font-charset: 134;
	mso-generic-font-family: modern;
	mso-font-pitch: auto;
	mso-font-signature: 1 135135232 16 0 262144 0;
}

@font-face {
	font-family: "\@宋体";
	panose-1: 2 1 6 0 3 1 1 1 1 1;
	mso-font-charset: 134;
	mso-generic-font-family: auto;
	mso-font-pitch: variable;
	mso-font-signature: 3 680460288 22 0 262145 0;
}

@font-face {
	font-family: "\@黑体";
	panose-1: 2 1 6 9 6 1 1 1 1 1;
	mso-font-charset: 134;
	mso-generic-font-family: modern;
	mso-font-pitch: fixed;
	mso-font-signature: -2147482945 953122042 22 0 262145 0;
}

@font-face {
	font-family: "\@楷体_GB2312";
	mso-font-charset: 134;
	mso-generic-font-family: modern;
	mso-font-pitch: auto;
	mso-font-signature: 1 135135232 16 0 262144 0;
}

@font-face {
	font-family: "\@仿宋_GB2312";
	mso-font-charset: 134;
	mso-generic-font-family: modern;
	mso-font-pitch: auto;
	mso-font-signature: 1 135135232 16 0 262144 0;
} /* Style Definitions */
p.MsoNormal,li.MsoNormal,div.MsoNormal {
	mso-style-unhide: no;
	mso-style-qformat: yes;
	mso-style-parent: "";
	margin: 0cm;
	margin-bottom: .0001pt;
	text-align: justify;
	text-justify: inter-ideograph;
	mso-pagination: none;
	font-size: 10.5pt;
	mso-bidi-font-size: 12.0pt;
	font-family: "Times New Roman", "serif";
	mso-fareast-font-family: 宋体;
	mso-font-kerning: 1.0pt;
}

p.MsoHeader,li.MsoHeader,div.MsoHeader {
	mso-style-noshow: yes;
	mso-style-unhide: no;
	margin: 0cm;
	margin-bottom: .0001pt;
	text-align: center;
	mso-pagination: none;
	tab-stops: center 207.65pt right 415.3pt;
	layout-grid-mode: char;
	border: none;
	mso-border-bottom-alt: solid windowtext .75pt;
	padding: 0cm;
	mso-padding-alt: 0cm 0cm 1.0pt 0cm;
	font-size: 9.0pt;
	font-family: "Times New Roman", "serif";
	mso-fareast-font-family: 宋体;
	mso-font-kerning: 1.0pt;
}

p.MsoFooter,li.MsoFooter,div.MsoFooter {
	mso-style-noshow: yes;
	mso-style-unhide: no;
	margin: 0cm;
	margin-bottom: .0001pt;
	mso-pagination: none;
	tab-stops: center 207.65pt right 415.3pt;
	layout-grid-mode: char;
	font-size: 9.0pt;
	font-family: "Times New Roman", "serif";
	mso-fareast-font-family: 宋体;
	mso-font-kerning: 1.0pt;
}

p.MsoBodyText,li.MsoBodyText,div.MsoBodyText {
	mso-style-noshow: yes;
	mso-style-unhide: no;
	margin: 0cm;
	margin-bottom: .0001pt;
	text-align: justify;
	text-justify: inter-ideograph;
	mso-pagination: none;
	font-size: 14.0pt;
	mso-bidi-font-size: 12.0pt;
	font-family: "Times New Roman", "serif";
	mso-fareast-font-family: 宋体;
	mso-font-kerning: 1.0pt;
}

.MsoChpDefault {
	mso-style-type: export-only;
	mso-default-props: yes;
	font-size: 10.0pt;
	mso-ansi-font-size: 10.0pt;
	mso-bidi-font-size: 10.0pt;
	mso-ascii-font-family: "Times New Roman";
	mso-fareast-font-family: 宋体;
	mso-hansi-font-family: "Times New Roman";
	mso-font-kerning: 0pt;
} /* Page Definitions */
@page {
	mso-page-border-surround-header: no;
	mso-page-border-surround-footer: no;
	mso-footnote-separator: url("6.files/header.html") fs;
	mso-footnote-continuation-separator: url("6.files/header.html") fcs;
	mso-endnote-separator: url("6.files/header.html") es;
	mso-endnote-continuation-separator: url("6.files/header.html") ecs;
}

@page Section1 {
	size: 595.3pt 841.9pt;
	margin: 42.5pt 54.0pt 42.5pt 54.0pt;
	mso-header-margin: 42.55pt;
	mso-footer-margin: 49.6pt;
	mso-page-numbers: 1;
	mso-footer: url("6.files/header.html") f1;
	mso-first-footer: url("6.files/header.html") ff1;
	mso-paper-source: 0;
	layout-grid: 15.6pt;
}

div.Section1 {
	page: Section1;
}

@page Section2 {
	size: 595.3pt 841.9pt;
	margin: 2.0cm 42.55pt 2.0cm 42.55pt;
	mso-header-margin: 42.55pt;
	mso-footer-margin: 49.6pt;
	mso-page-numbers: 1;
	mso-title-page: yes;
	mso-footer: url("6.files/header.html") f2;
	mso-first-footer: url("6.files/header.html") ff2;
	mso-paper-source: 0;
	layout-grid: 15.6pt;
}

div.Section2 {
	page: Section2;
}
-->
</style>
<!--[if gte mso 10]><style> /* Style Definitions */ table.MsoNormalTable	{mso-style-name:普通表格;	mso-tstyle-rowband-size:0;	mso-tstyle-colband-size:0;	mso-style-noshow:yes;	mso-style-priority:99;	mso-style-qformat:yes;	mso-style-parent:"";	mso-padding-alt:0cm 5.4pt 0cm 5.4pt;	mso-para-margin:0cm;	mso-para-margin-bottom:.0001pt;	mso-pagination:widow-orphan;	font-size:10.0pt;	font-family:"Times New Roman","serif";}table.MsoTableGrid	{mso-style-name:网格型;	mso-tstyle-rowband-size:0;	mso-tstyle-colband-size:0;	mso-style-priority:59;	mso-style-unhide:no;	border:solid windowtext 1.0pt;	mso-border-alt:solid windowtext .5pt;	mso-padding-alt:0cm 5.4pt 0cm 5.4pt;	mso-border-insideh:.5pt solid windowtext;	mso-border-insidev:.5pt solid windowtext;	mso-para-margin:0cm;	mso-para-margin-bottom:.0001pt;	text-align:justify;	text-justify:inter-ideograph;	mso-pagination:none;	font-size:10.0pt;	font-family:"Times New Roman","serif";}</style><![endif]-->
<!--[if gte mso 9]><xml> <o:shapedefaults v:ext="edit" spidmax="12289" strokecolor="#739cc3">  <v:fill angle="90" type="gradient">   <o:fill v:ext="view" type="gradientUnscaled"/>  </v:fill>  <v:stroke color="#739cc3" weight="1.25pt"/> </o:shapedefaults></xml><![endif]-->
<!--[if gte mso 9]><xml> <o:shapelayout v:ext="edit">  <o:idmap v:ext="edit" data="1"/> </o:shapelayout></xml><![endif]-->

</head>
<body>
	<div id="layout1" style="width: 99.8%">
		<!-- 		<div position="top" id="toptoolbar"></div> -->
		<div position="left" title="文档结构" class="overflow-auto">
			<ul id="tree1"></ul>
		</div>
		<div position="center" style="overflow: auto;">
			<div>
				<form id="formObj" action="" getAction="">
					<!-- 	 <input type="hidden" id="nextPage" name="nextPage" value="[$RtNextPage]">  -->
					<p class=MsoNormal align=center style='text-align: center'>
						<b><spanstyle ='font-size:22.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times NewRoman"'>锅炉内部检验结论报告</span></b><b><spanlang
								=EN-US style='font-size:22.0pt'>
							<o:p></o:p>
							</span></b>
					</p>
					<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US><spanstyle='mso-spacerun:yes'>               </span></span><span
							style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>报告编号：</span><span lang=EN-US><spanstyle='mso-spacerun:yes'>                            </span></span><span
							style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>检验日期：</span>
					</p>
					<div align=center>
						<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0
							style='border-collapse: collapse; mso-table-layout-alt: fixed; border: none; mso-border-alt: solid windowtext .5pt; mso-padding-alt: 0cm 5.4pt 0cm 5.4pt'>
							<tr style='mso-yfti-irow: 0; mso-yfti-firstrow: yes; height: 22.7pt; mso-height-rule: exactly'>
								<td width=166 colspan=2
									style='width: 124.5pt; border: solid windowtext 1.0pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>锅炉型号</span>
									</p>
								</td>
								<td width=166 colspan=3
									style='width: 124.5pt; border: solid windowtext 1.0pt; border-left: none; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										213
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border: solid windowtext 1.0pt; border-left: none; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>设备代码</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border: solid windowtext 1.0pt; border-left: none; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US>12</span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 1; height: 22.7pt; mso-height-rule: exactly'>
								<td width=166 colspan=2
									style='width: 124.5pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>产品编号</span>
									</p>
								</td>
								<td width=166 colspan=3
									style='width: 124.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US>1</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>单位内编号</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US>12</span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 2; height: 22.7pt; mso-height-rule: exactly'>
								<td width=166 colspan=2
									style='width: 124.5pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>制造单位</span>
									</p>
								</td>
								<td width=498 colspan=7
									style='width: 373.6pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US>2</span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 3; height: 22.7pt; mso-height-rule: exactly'>
								<td width=166 colspan=2
									style='width: 124.5pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>安装单位</span>
									</p>
								</td>
								<td width=498 colspan=7
									style='width: 373.6pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US>12</span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 4; height: 22.7pt; mso-height-rule: exactly'>
								<td width=166 colspan=2
									style='width: 124.5pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>产权单位</span>
									</p>
								</td>
								<td width=498 colspan=7
									style='width: 373.6pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US>1</span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 5; height: 22.7pt; mso-height-rule: exactly'>
								<td width=166 colspan=2
									style='width: 124.5pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>使用单位</span>
									</p>
								</td>
								<td width=498 colspan=7
									style='width: 373.6pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US>1</span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 6; height: 22.7pt; mso-height-rule: exactly'>
								<td width=166 colspan=2
									style='width: 124.5pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>使用单位地点</span>
									</p>
								</td>
								<td width=498 colspan=7
									style='width: 373.6pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US>121</span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 7; height: 22.7pt; mso-height-rule: exactly'>
								<td width=166 colspan=2
									style='width: 124.5pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>使用单位机构代码</span>
									</p>
								</td>
								<td width=166 colspan=3
									style='width: 124.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>已替换</span><span lang=EN-US>DB</span><span
											style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>数据</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>邮政编码</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US><input id="TBL00000" name="TBL00000" type="text" ltype="text" value="" required /></span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 8; height: 22.7pt; mso-height-rule: exactly'>
								<td width=166 colspan=2
									style='width: 124.5pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>使用登记证编号</span>
									</p>
								</td>
								<td width=166 colspan=3
									style='width: 124.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US><input id="TBL00001" name="TBL00001" type="text" ltype="text" value="" required /></span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>投入使用日期</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>年</span><span lang=EN-US><span
											style='mso-spacerun: yes'>     </span></span><span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>月</span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 9; height: 22.7pt; mso-height-rule: exactly'>
								<td width=166 colspan=2
									style='width: 124.5pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>累计运行小时</span>
									</p>
								</td>
								<td width=166 colspan=3
									style='width: 124.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span lang=EN-US>h</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>上次检验日期</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>年</span><span lang=EN-US><span
											style='mso-spacerun: yes'>     </span></span><span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>月</span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 10; height: 22.7pt; mso-height-rule: exactly'>
								<td width=44 rowspan=6
									style='width: 32.7pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>性能参数</span>
									</p>
								</td>
								<td width=122
									style='width: 91.8pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>额定蒸发量</span>
									</p>
								</td>
								<td width=166 colspan=3
									style='width: 124.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00002" name="TBL00002" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>t/h</td>
												</tr>
											</table></span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>再热蒸汽流量</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00003" name="TBL00003" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>t/h</td>
												</tr>
											</table></span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 11; height: 22.7pt; mso-height-rule: exactly'>
								<td width=122
									style='width: 91.8pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>锅筒工作压力</span>
									</p>
								</td>
								<td width=166 colspan=3
									style='width: 124.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00004" name="TBL00004" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>MPa</td>
												</tr>
											</table></span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>锅筒工作温度</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00005" name="TBL00005" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>℃</td>
												</tr>
											</table></span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 12; height: 22.7pt; mso-height-rule: exactly'>
								<td width=122
									style='width: 91.8pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>过热器出口压力</span>
									</p>
								</td>
								<td width=166 colspan=3
									style='width: 124.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00006" name="TBL00006" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>MPa</td>
												</tr>
											</table></span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>过热器出口温度</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00007" name="TBL00007" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>℃</td>
												</tr>
											</table></span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 13; height: 22.7pt; mso-height-rule: exactly'>
								<td width=122
									style='width: 91.8pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>再热器出口压力</span>
									</p>
								</td>
								<td width=166 colspan=3
									style='width: 124.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00008" name="TBL00008" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>MPa</td>
												</tr>
											</table></span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>再热器出口温度</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00009" name="TBL00009" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>℃</td>
												</tr>
											</table></span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 14; height: 22.7pt; mso-height-rule: exactly'>
								<td width=122
									style='width: 91.8pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>再热器进口压力</span>
									</p>
								</td>
								<td width=166 colspan=3
									style='width: 124.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00010" name="TBL00010" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>MPa</td>
												</tr>
											</table></span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>再热器进口温度</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00011" name="TBL00011" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>℃</td>
												</tr>
											</table></span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 15; height: 22.7pt; mso-height-rule: exactly'>
								<td width=122
									style='width: 91.8pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>给水压力</span>
									</p>
								</td>
								<td width=166 colspan=3
									style='width: 124.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00012" name="TBL00012" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>MPa</td>
												</tr>
											</table></span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>给水温度</span>
									</p>
								</td>
								<td width=166 colspan=2
									style='width: 124.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=right style='text-align: right'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00013" name="TBL00013" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>℃</td>
												</tr>
											</table></span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 16; height: 38.2pt; mso-height-rule: exactly'>
								<td width=44
									style='width: 32.7pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 38.2pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>检验</span>
									</p>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>依据</span>
									</p>
								</td>
								<td width=621 colspan=8
									style='width: 465.4pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 38.2pt; mso-height-rule: exactly'>
									<p class=MsoNormal>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>《锅炉安全技术监察规程》《锅炉定期检验规则》</span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 17; height: 51.95pt; mso-height-rule: exactly'>
								<td width=44
									style='width: 32.7pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 51.95pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>缺陷</span>
									</p>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>及其</span>
									</p>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>处理</span>
									</p>
								</td>
								<td width=621 colspan=8 valign=top
									style='width: 465.4pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 51.95pt; mso-height-rule: exactly'>
									<p class=MsoNormal>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>检验发现的缺陷位置、性质、程度及处理意见（必要时附图或者附页）</span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 18; height: 22.7pt; mso-height-rule: exactly'>
								<td width=44 rowspan=4
									style='width: 32.7pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>检验结论</span>
									</p>
								</td>
								<td width=124 colspan=2 rowspan=3
									style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>符合要求</span>
									</p>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>基本符合要求</span>
									</p>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>不符合要求</span>
									</p>
								</td>
								<td width=496 colspan=6
									style='width: 372.35pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>允许（监控）使用参数</span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 19; height: 22.7pt; mso-height-rule: exactly'>
								<td width=124
									style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>压力</span>
									</p>
								</td>
								<td width=124 colspan=2
									style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00014" name="TBL00014" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>MPa</td>
												</tr>
											</table></span>
									</p>
								</td>
								<td width=124 colspan=2
									style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>温度</span>
									</p>
								</td>
								<td width=124
									style='width: 93.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US><table border='0' class='l-text-suffix-wrap'>
												<tr>
													<td class='l-text-left'><input id="TBL00015" name="TBL00015" type="text" ltype="text" value="" required /></td>
													<td class='l-text-suffix'>℃</td>
												</tr>
											</table></span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 20; height: 22.7pt; mso-height-rule: exactly'>
								<td width=124
									style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>介质</span>
									</p>
								</td>
								<td width=124 colspan=2
									style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US><input id="TBL00016" name="TBL00016" type="text" ltype="text" value="" required /></span>
									</p>
								</td>
								<td width=124 colspan=2
									style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>其他</span>
									</p>
								</td>
								<td width=124
									style='width: 93.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US><input id="TBL00017" name="TBL00017" type="text" ltype="text" value="" required /></span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 21; height: 22.7pt; mso-height-rule: exactly'>
								<td width=621 colspan=8
									style='width: 465.4pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>下次检验日期：</span><span lang=EN-US><span
											style='mso-spacerun: yes'>                 </span></span><span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>年</span><span
											lang=EN-US><span style='mso-spacerun: yes'>    </span></span><span
											style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>月</span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 22; height: 22.7pt; mso-height-rule: exactly'>
								<td width=44
									style='width: 32.7pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>说明</span>
									</p>
								</td>
								<td width=621 colspan=8
									style='width: 465.4pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal align=center style='text-align: center'>
										<span lang=EN-US><input id="TBL00018" name="TBL00018" type="text" ltype="text" value="" required /></span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 23; height: 22.7pt; mso-height-rule: exactly'>
								<td width=664 colspan=9
									style='width: 498.1pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal>
										<span lang=EN-US><input id="TBL00019" name="TBL00019" type="text" ltype="text" value="" required /></span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 24; height: 22.7pt; mso-height-rule: exactly'>
								<td width=332 colspan=5
									style='width: 249.0pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>编制：</span><span lang=EN-US><input id="TBL00020"
											name="TBL00020" type="text" ltype="text" value="" required /><span style='mso-spacerun: yes'>                    </span></span><span
											style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>日期：</span><span lang=EN-US><input id="TBL00021"
											name="TBL00021" type="text" ltype="text" value="" required /></span>
									</p>
								</td>
								<td width=332 colspan=4 rowspan=3
									style='width: 249.1pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									
									<div class="l-upload-ok-list" id='uploadctr1'><input type="button" class="l-button3" value="+" id="uploadfilebtn1" style="width:40px;height:40px;font-size:20px;" /><ul id="attList1"></ul></div>
									
<!-- 									<div id="uploadctr"> -->
<!--                     <div class="uploading" id="pickfiles">附件上传</div> -->
<!--                 </div> -->
<!-- 									<p class=MsoNormal align=center style='text-align: center'> -->
<!-- 										<span lang=EN-US> -->
										
										
<!-- 										<input id="TBL00022" name="TBL00022" type="text" ltype="text" value="" required /></span> -->
<!-- 									</p> -->
<!-- 									<p class=MsoNormal align=center style='text-align: center'> -->
<!-- 										<span lang=EN-US><o:p> </o:p></span> -->
<!-- 									</p> -->
<!-- 									<p class=MsoNormal align=center style='text-align: center'> -->
<!-- 										<span lang=EN-US><o:p> </o:p></span> -->
<!-- 									</p> -->
								</td>
							</tr>
							<tr style='mso-yfti-irow: 25; height: 22.7pt; mso-height-rule: exactly'>
								<td width=332 colspan=5
									style='width: 249.0pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>审核：</span><span lang=EN-US><input id="TBL00023"
											name="TBL00023" type="text" ltype="text" value="" required /><span style='mso-spacerun: yes'>                    </span></span><span
											style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>日期：</span><span lang=EN-US><input id="TBL00024"
											name="TBL00024" type="text" ltype="text" value="" required /></span>
									</p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 26; mso-yfti-lastrow: yes; height: 22.7pt; mso-height-rule: exactly'>
								<td width=332 colspan=5
									style='width: 249.0pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 22.7pt; mso-height-rule: exactly'>
									<p class=MsoNormal>
										<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>批准：</span><span lang=EN-US><input id="TBL00025"
											name="TBL00025" type="text" ltype="text" value="" required /><span style='mso-spacerun: yes'>                    </span></span><span
											style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>日期：</span><span lang=EN-US><input id="TBL00026"
											name="TBL00026" type="text" ltype="text" value="" required /></span>
									</p>
								</td>
							</tr>
							<![if !supportMisalignedColumns]>
							<tr height=0>
								<td width=28 style='border: none'></td>
								<td width=28 style='border: none'></td>
								<td width=5 style='border: none'></td>
								<td width=367 style='border: none'></td>
								<td width=307 style='border: none'></td>
								<td width=162 style='border: none'></td>
								<td width=11 style='border: none'></td>
								<td width=21 style='border: none'></td>
								<td width=436 style='border: none'></td>
							</tr>
							<![endif]>
						</table>
					</div>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>注：本报告适用于电站锅炉以及电站锅炉以外有过热器的</span>
						<spanlang =EN-US>A</span>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>级蒸汽锅炉。</span>
					</p>

					<!-- 				 [$RtPageBody] -->
				</form>

			</div>
		</div>

		<div position="right" title="功能区">
			<input type="button" value="上一页" onclick="location.href='rtbox/app/templates/default/index[$RtNextPage].jsp'"> <input type="button" value="下一页"
				onclick="location.href='rtbox/app/templates/default/index[$RtNextPage].jsp'">
		</div>
	</div>



</body>
</html>