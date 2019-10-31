<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/rtbox/public/base.jsp"%>
<link href="rtbox/app/templates/default/tpl.css" rel="stylesheet" />
<script type="text/javascript"> 
	location.href="rtbox/app/demo/tpl.jsp";
 </script> 

<script type="text/javascript">
	$(function() {
		$("#toptoolbar").ligerToolBar({
			items : [ {
				text : '保存',
				click : itemclick,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改',
				click : itemclick
			}, {
				line : true
			}, {
				text : '删除',
				click : itemclick
			} ]
		});
		
		$("form").ligerForm();

	});

	function itemclick() {
		notice("test1");
		notice("test3");
	}
	
	function notice(id){
		var d = dialog({
			content:'填一些字把'
		});
		d.show($("#"+id)[0]);
		setTimeout(function () {
		    d.close().remove();
		}, 2000);
	}
</script>
<meta http-equiv=Content-Type content="text/html; charset=x-cp20936">
<meta name=ProgId content=Word.Document>
<meta name=Generator content="Microsoft Word 12">
<meta name=Originator content="Microsoft Word 12">
<link rel=File-List href="6.files/filelist.xml">
<link rel=Edit-Time-Data href="6.files/editdata.mso">
<!--[if !mso]><style>v\:* {behavior:url(#default#VML);}o\:* {behavior:url(#default#VML);}w\:* {behavior:url(#default#VML);}.shape {behavior:url(#default#VML);}</style><![endif]-->
<title>电站锅炉内部检验原始记录</title>
<link rel=themeData href="6.files/themedata.thmx">
<link rel=colorSchemeMapping href="6.files/colorschememapping.xml">
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
 
<style type="text/css">
table {width:90%;}
td {height:32px;}
</style>

</head>
<body style="text-align: center">
	<div id="toptoolbar" style="position: fixed;"></div>
	<form id="formObj" name="formObj" action="" getAction="">
		<p class=MsoNormal align=center style='text-align: center; line-height: 16.0pt; mso-line-height-rule: exactly'>
			<b><span style='font-size: 16.0pt; mso-bidi-font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>测</span></b><b><span
				lang=EN-US style='font-size: 16.0pt; mso-bidi-font-size: 12.0pt'><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;</span></span></b><b><span
				style='font-size: 16.0pt; mso-bidi-font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>厚</span></b><b><span
				lang=EN-US style='font-size: 16.0pt; mso-bidi-font-size: 12.0pt'><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;</span></span></b><b><span
				style='font-size: 16.0pt; mso-bidi-font-size: 12.0pt; font-family: 宋体'>报<span lang=EN-US><span style='mso-spacerun: yes'>&nbsp;&nbsp;</span></span>告
			</span></b><b><span lang=EN-US style='font-size: 16.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span></b>
		</p>
		<p class=MsoNormal align=center style='text-align: center; line-height: 16.0pt; mso-line-height-rule: exactly'>
			<span lang=EN-US><spanstyle='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span>
		</p>
		<p class=MsoNormal align=left style='text-align: left; line-height: 16.0pt; mso-line-height-rule: exactly; tab-stops: 410.0pt'>
			<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>部件检验编号：</span>
			<spanlang =EN-US>
			<spanstyle='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</span>
			<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>单项报告编号：</span>
		</p>
		<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0
			style='border-collapse: collapse; mso-table-layout-alt: fixed; border: none; mso-border-alt: solid windowtext .5pt; mso-padding-alt: 0cm 5.4pt 0cm 5.4pt; mso-border-insideh: .5pt solid windowtext; mso-border-insidev: .5pt solid windowtext'>
			<tr style='mso-yfti-irow: 0; mso-yfti-firstrow: yes; page-break-inside: avoid'>
				<td width=106 style='width: 79.85pt; border: solid windowtext 1.0pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>产品名称</span>
					</p>
				</td>
				<td width=210 colspan=6
					style='width: 157.65pt; border: solid windowtext 1.0pt; border-left: none; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' id="test1" ltype="date" ligerui="{width:120,height:22}"></span>
					</p>
				</td>
				<td width=98 colspan=3
					style='width: 73.55pt; border: solid windowtext 1.0pt; border-left: none; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>产品编号</span>
					</p>
				</td>
				<td width=280 colspan=6
					style='width: 209.95pt; border: solid windowtext 1.0pt; border-left: none; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype="text" ligerui="{width:120,height:22}"></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 1; page-break-inside: avoid'>
				<td width=106
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>部件名称</span>
					</p>
				</td>
				<td width=210 colspan=6
					style='width: 157.65pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=98 colspan=3
					style='width: 73.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>仪器型号</span>
					</p>
				</td>
				<td width=280 colspan=6
					style='width: 209.95pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 2; page-break-inside: avoid'>
				<td width=106
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>仪器精度</span>
					</p>
				</td>
				<td width=210 colspan=6
					style='width: 157.65pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=98 colspan=3
					style='width: 73.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>资产编号</span>
					</p>
				</td>
				<td width=280 colspan=6
					style='width: 209.95pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 3; page-break-inside: avoid'>
				<td width=106
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>检定有效期</span>
					</p>
				</td>
				<td width=210 colspan=6
					style='width: 157.65pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=98 colspan=3
					style='width: 73.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>耦</span> <span
							style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>合</span> <span
							style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>剂</span>
					</p>
				</td>
				<td width=280 colspan=6
					style='width: 209.95pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='select' ligerui="{width:120,data:[{id:'1',text:'1'},{id:'2',text:'2'}]}"></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 4; page-break-inside: avoid'>
				<td width=106
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>元件名称</span>
					</p>
				</td>
				<td width=91 colspan=3
					style='width: 68.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>表面状态</span>
					</p>
				</td>
				<td width=119 colspan=3
					style='width: 89.6pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>名义厚度</span>
					</p>
				</td>
				<td width=98 colspan=3
					style='width: 73.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>实测点数</span>
					</p>
				</td>
				<td width=124 colspan=4
					style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>实测最小厚度</span>
					</p>
				</td>
				<td width=156 colspan=2
					style='width: 116.9pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal style='text-indent: 10.5pt; mso-char-indent-count: 1.0'>
						<span style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>试</span> <span
							style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>块</span> <span
							style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>厚</span> <span
							style='font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>度</span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 5; page-break-inside: avoid'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=91 colspan=3 valign=top
					style='width: 68.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=80 colspan=3 valign=top
					style='width: 89.6pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<table border='0' class='l-text-suffix-wrap'><tr><td class='l-text-left'><input type='text' ltype='spinner' ligerui="{width:60,type:'int'}"></td><td class='l-text-suffix'>mm</td></tr></table>
					</p>
				</td>
				<td width=80 colspan=3 valign=top
					style='width: 73.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=124 colspan=4 valign=top
					style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal style="width: 200;">
<!-- 						<input type='text' ltype='text' ligerui="{width:50}"/> -->
						<table border='0' class='l-text-suffix-wrap'><tr><td class='l-text-left'><input type='text' ltype='text' ligerui="{width:50,suffix:'mm',suffixWidth:'60'}"/></td><td class='l-text-suffix'>mm</td></tr></table>
					</p>
				</td>
				<td width=156 colspan=2 valign=top
					style='width: 116.9pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<table border='0' class='l-text-suffix-wrap'><tr><td class='l-text-left'><input type='text' ltype='text' ligerui="{width:50,suffix:'mm',suffixWidth:'60'}"/></td><td class='l-text-suffix'>mm</td></tr></table>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 6; page-break-inside: avoid'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=91 colspan=3 valign=top
					style='width: 68.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=119 colspan=3 valign=top
					style='width: 89.6pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp; </span><span style='mso-spacerun: yes'>&nbsp;</span><span
							style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>mm</span>
					</p>
				</td>
				<td width=98 colspan=3 valign=top
					style='width: 73.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=124 colspan=4 valign=top
					style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>mm</span>
					</p>
				</td>
				<td width=156 colspan=2 valign=top
					style='width: 116.9pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>mm</span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 7; page-break-inside: avoid'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=91 colspan=3 valign=top
					style='width: 68.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=119 colspan=3 valign=top
					style='width: 89.6pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>mm</span>
					</p>
				</td>
				<td width=98 colspan=3 valign=top
					style='width: 73.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=124 colspan=4 valign=top
					style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>mm</span>
					</p>
				</td>
				<td width=156 colspan=2 valign=top
					style='width: 116.9pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>mm</span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 8; page-break-inside: avoid'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=91 colspan=3 valign=top
					style='width: 68.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=119 colspan=3 valign=top
					style='width: 89.6pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp; </span><span style='mso-spacerun: yes'>&nbsp;</span><span
							style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>mm</span>
					</p>
				</td>
				<td width=98 colspan=3 valign=top
					style='width: 73.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=124 colspan=4 valign=top
					style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>mm</span>
					</p>
				</td>
				<td width=156 colspan=2 valign=top
					style='width: 116.9pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>mm</span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 9; page-break-inside: avoid'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=91 colspan=3 valign=top
					style='width: 68.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=119 colspan=3 valign=top
					style='width: 89.6pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp; </span><span style='mso-spacerun: yes'>&nbsp;</span><span
							style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>mm</span>
					</p>
				</td>
				<td width=98 colspan=3 valign=top
					style='width: 73.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=124 colspan=4 valign=top
					style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>mm</span>
					</p>
				</td>
				<td width=156 colspan=2 valign=top
					style='width: 116.9pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>mm</span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 10; page-break-inside: avoid'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=91 colspan=3 valign=top
					style='width: 68.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=119 colspan=3 valign=top
					style='width: 89.6pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp; </span><span style='mso-spacerun: yes'>&nbsp;</span><span
							style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>mm</span>
					</p>
				</td>
				<td width=98 colspan=3 valign=top
					style='width: 73.55pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span>
					</p>
				</td>
				<td width=124 colspan=4 valign=top
					style='width: 93.05pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>mm</span>
					</p>
				</td>
				<td width=156 colspan=2 valign=top
					style='width: 116.9pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>mm</span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 11; page-break-inside: avoid'>
				<td width=695 colspan=16 valign=top
					style='width: 521.0pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>实</span><span style='font-size: 12.0pt'>
						</span><span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>测</span><span style='font-size: 12.0pt'>
						</span><span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>厚</span><span style='font-size: 12.0pt'>
						</span><span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>度（</span><span lang=EN-US
							style='font-size: 12.0pt'>mm</span><span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>）</span><span
							lang=EN-US style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 12'>
				<td width=106
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>元件名称</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>测点</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>编号</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>测点</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>厚度</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>测点</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>编号</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>测点</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>厚度</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>测点</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>编号</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>测点</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>厚度</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>测点</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>编号</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=101
					style='width: 75.75pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>测点</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
					<p class=MsoNormal align=center style='text-align: center; line-height: 15.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>厚度</span><span lang=EN-US
							style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 13'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=101 valign=top
					style='width: 75.75pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 14'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=101 valign=top
					style='width: 75.75pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 15'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=101 valign=top
					style='width: 75.75pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 16'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=101 valign=top
					style='width: 75.75pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 17'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=101 valign=top
					style='width: 75.75pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 18'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=101 valign=top
					style='width: 75.75pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 19'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=101 valign=top
					style='width: 75.75pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 20'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=101 valign=top
					style='width: 75.75pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 21'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=101 valign=top
					style='width: 75.75pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}" id="test3"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 22'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=101 valign=top
					style='width: 75.75pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 23'>
				<td width=106 valign=top
					style='width: 79.85pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=3 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=70 colspan=2 valign=top
					style='width: 52.2pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=101 valign=top
					style='width: 75.75pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 24; height: 101.25pt'>
				<td width=695 colspan=16 valign=top
					style='width: 521.0pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 101.25pt'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体'>测点部位示意图<span lang=EN-US>:(</span>可另加附页<span lang=EN-US>)<input type='text' ltype='text' ligerui="{width:50}"></span></span><span
							lang=EN-US style='font-size: 9.0pt; mso-bidi-font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 25; page-break-inside: avoid; height: 35.6pt; mso-height-rule: exactly'>
				<td width=695 colspan=16 valign=top
					style='width: 521.0pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 35.6pt; mso-height-rule: exactly'>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span style='font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: "Times New Roman"; mso-hansi-font-family: "Times New Roman"'>结论：</span><span lang=EN-US
							style='font-size: 12.0pt'><input type='text' ltype='text' ligerui="{width:50}">
						<o:p></o:p></span>
					</p>
					<p class=MsoNormal align=left style='text-align: left; line-height: 20.0pt; mso-line-height-rule: exactly'>
						<span lang=EN-US style='font-size: 12.0pt'><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
						<o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 26; page-break-inside: avoid; height: 35.75pt'>
				<td width=111 colspan=2
					style='width: 83.15pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 35.75pt'>
					<p class=MsoNormal align=center style='text-align: center; line-height: 28.0pt; mso-line-height-rule: exactly; tab-stops: 180.75pt'>
						<span style='font-family: 宋体'>检验人员</span><span lang=EN-US style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=230 colspan=6 valign=top
					style='width: 172.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 35.75pt'>
					<p class=MsoNormal style='line-height: 28.0pt; mso-line-height-rule: exactly; tab-stops: 180.75pt'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=110 colspan=3
					style='width: 82.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 35.75pt'>
					<p class=MsoNormal align=center
						style='text-align: center; text-indent: 10.5pt; mso-char-indent-count: 1.0; line-height: 28.0pt; mso-line-height-rule: exactly; tab-stops: 180.75pt'>
						<span style='font-family: 宋体'>日<span lang=EN-US><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp; </span></span>期
						</span><span lang=EN-US style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=244 colspan=5 valign=top
					style='width: 182.85pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 35.75pt'>
					<p class=MsoNormal
						style='margin-left: 34.55pt; mso-para-margin-left: 3.29gd; text-indent: 52.5pt; mso-char-indent-count: 5.0; line-height: 28.0pt; mso-line-height-rule: exactly; tab-stops: 180.75pt'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow: 27; mso-yfti-lastrow: yes; page-break-inside: avoid; height: 35.75pt'>
				<td width=111 colspan=2
					style='width: 83.15pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 35.75pt'>
					<p class=MsoNormal align=center style='text-align: center; line-height: 28.0pt; mso-line-height-rule: exactly; tab-stops: 180.75pt'>
						<span style='font-family: 宋体'>审<span lang=EN-US><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp; </span></span>核
						</span><span lang=EN-US style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=230 colspan=6 valign=top
					style='width: 172.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 35.75pt'>
					<p class=MsoNormal style='line-height: 28.0pt; mso-line-height-rule: exactly; tab-stops: 180.75pt'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=110 colspan=3
					style='width: 82.5pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 35.75pt'>
					<p class=MsoNormal align=center
						style='text-align: center; text-indent: 10.5pt; mso-char-indent-count: 1.0; line-height: 28.0pt; mso-line-height-rule: exactly; tab-stops: 180.75pt'>
						<span style='font-family: 宋体'>日<span lang=EN-US><span style='mso-spacerun: yes'>&nbsp;&nbsp;&nbsp; </span></span>期
						</span><span lang=EN-US style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
				<td width=244 colspan=5 valign=top
					style='width: 182.85pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0cm 5.4pt 0cm 5.4pt; height: 35.75pt'>
					<p class=MsoNormal
						style='margin-left: 34.55pt; mso-para-margin-left: 3.29gd; text-indent: 52.5pt; mso-char-indent-count: 5.0; line-height: 28.0pt; mso-line-height-rule: exactly; tab-stops: 180.75pt'>
						<span lang=EN-US><input type='text' ltype='text' ligerui="{width:50}"></span><span lang=EN-US style='font-size: 12.0pt'><o:p></o:p></span>
					</p>
				</td>
			</tr>
			<![if !supportMisalignedColumns]>
			<tr height=0>
				<td width=106 style='border: none'></td>
				<td width=4 style='border: none'></td>
				<td width=65 style='border: none'></td>
				<td width=21 style='border: none'></td>
				<td width=48 style='border: none'></td>
				<td width=70 style='border: none'></td>
				<td width=1 style='border: none'></td>
				<td width=24 style='border: none'></td>
				<td width=44 style='border: none'></td>
				<td width=30 style='border: none'></td>
				<td width=36 style='border: none'></td>
				<td width=4 style='border: none'></td>
				<td width=70 style='border: none'></td>
				<td width=15 style='border: none'></td>
				<td width=55 style='border: none'></td>
				<td width=101 style='border: none'></td>
			</tr>
			<![endif]>
		</table>
	</form>
</body>
</html>
