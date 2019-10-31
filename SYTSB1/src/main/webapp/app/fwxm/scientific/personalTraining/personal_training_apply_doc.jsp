<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String realPath1 = request.getSession().getServletContext().getRealPath("upload");
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String now_date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
	System.out.print(">>>>>>>" + realPath1 + "<<<<<<");
	String[] id = realPath1.split("\\\\");
	String realPath = "";
	for (int i = 0; i < id.length; i++) {
		if (StringUtil.isNotEmpty(realPath)) {
			realPath = realPath + "/" + id[i];
		} else {
			realPath = id[0];
		}
	}
%>
<!--  -->
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

<base
	href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<%@include file="/k/kui-base-form.jsp"%>
<style type="text/css">
.l-icon-exportDoc {background: url('k/kui/skins/icons/word.gif') no-repeat center;}

.l-icon-printPreview {background: url('k/kui/skins/icons/search2.gif') no-repeat center;}

.l-icon-fullScreen {background: url('k/kui/skins/icons/div-drag.gif') no-repeat center;}
</style>
<script src="pub/office/ntkoofficecontrol.js" type="text/javascript"></script>
<script src="pub/office/editor.js" type="text/javascript"></script>
<script src="app/tzsb/inspection/flow/insing/hall/common/common.js" type="text/javascript"></script>
<script type="text/javascript">
	var basepath = "${pageContext.request.contextPath}/";
	var toolBar;//工具条
	var device_sort="";
	var device_sort_code="";
	$(function() {
		$(".layout").ligerLayout({
			bottomHeight : 30,
			space : 0
		});
	});


	//文档标签赋值
	function setBookMarkValue1(bookMarkName, inputValue) {
		var bkmkObj = TANGER_OCX_OBJ.ActiveDocument.BookMarks(bookMarkName);
		if (!bkmkObj) {
			return;
		}
		var saverange = bkmkObj.Range;
		saverange.Text = inputValue;
		TANGER_OCX_OBJ.ActiveDocument.Bookmarks.Add(bookMarkName, saverange);
	}

	function initPage() {
		initToolBar();
		createNtkoEditor("editor_container");
		TANGER_OCX_OBJ.SetReadOnly(true);
		TANGER_OCX_OBJ.Menubar = false;
		TANGER_OCX_OBJ.Statusbar = false;
		TANGER_OCX_OBJ.Toolbars = false;
		var accept_opinion=api.data.accept_opinion;
		TANGER_OCX_OBJ.OpenFromURL($("base").attr("href")+ 
		"app/fwxm/scientific/personalTraining/word/personal_training.doc");		
		var doc = TANGER_OCX_OBJ.ActiveDocument;
		setValues(doc);
		
	}

	function initToolBar() {
		var closeBtn;
		var printBtn;
		var printPreviewBtn;
		var fullScreenBtn;

		closeBtn = {
			id : "close",
			text : "关闭",
			icon : "close",
			click : function() {
				api.close();
				return true;
			}
		};

		printBtn = {
			id : "print",
			text : "打印",
			icon : "print",
			click : function() {
				doPrint();
				//savePrint();
			}
		};
		printPreviewBtn = {
			id : "printPreview",
			text : "打印预览",
			icon : "preview",
			click : function() {
				printPreview();
				return true;
			}
		};
		
		fullScreenBtn = {
			id : "fullScreen",
			text : "全屏",
			icon : "provide",
			click : function() {
				fullScreen();
				return true;
			}
		};
		 
		var itemArr = new Array();
		itemArr.push(printBtn);
		itemArr.push(fullScreenBtn);
		itemArr.push(printPreviewBtn);
		itemArr.push(closeBtn);
		toolBar = $("#toolbar").ligerButton({
			items : itemArr
		});
	}

	
	/**
	 * 特种设备注册登记表格式的设值
	 */
	function setValues(doc) {
		 $.post("personalTrainingAction/detail.do",{id:"${param.id}"},function(res){
			if(res.success){
				var data = res.data;
				//申请人
				if (doc.BookMarks.Exists("apply_op")) {
					var jgdm = data.apply_op;
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("apply_op", "");
					} else {
						setBookMarkValue1("apply_op", jgdm);
					}
				}
			 	//部门
				if (doc.BookMarks.Exists("apply_org")) {
					var jgdm = data.apply_org;
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("apply_org", "");
					} else {
						setBookMarkValue1("apply_org", jgdm);
					}
				}
				//编号
				/* if (doc.BookMarks.Exists("sn")) {
					var jgdm = data.sn;
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("sn", "");
					} else {
						setBookMarkValue1("sn", jgdm);
					}
				} */
				//申请时间
				if (doc.BookMarks.Exists("apply_date")) {
					var jgdm =new Date(data.apply_date.replace(/-/g,"/")).format("yyyy年MM月dd日");//兼容性
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("apply_date", "");
					} else {
						setBookMarkValue1("apply_date", jgdm);
					}
				}
				//申请原因
				if (doc.BookMarks.Exists("apply_reason")) {
					var jgdm = data.apply_reason;
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("apply_reason", "");
					} else {
						setBookMarkValue1("apply_reason", jgdm);
					}
				}
				//培训内容
				if (doc.BookMarks.Exists("apply_content")) {
					var jgdm = data.apply_content;
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("apply_content", "");
					} else {
						setBookMarkValue1("apply_content", jgdm);
					}
				}
				//备注
				if (doc.BookMarks.Exists("remark")) {
					var jgdm = data.remark;
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("remark", "");
					} else {
						setBookMarkValue1("remark", jgdm);
					}
				}
				
				//审核意见内容
				if (doc.BookMarks.Exists("org_audit_opinion")) {
					var jgdm = data.org_audit_opinion;
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("org_audit_opinion", "");
					} else {
						var opinion=getOpinion(jgdm);
						setBookMarkValue1("org_audit_opinion", opinion);
					}
				}
				//审核时间
				if (doc.BookMarks.Exists("org_audit_date")) {
					var jgdm =new Date(data.org_audit_date.replace(/-/g,"/")).format("yyyy年MM月dd日");//兼容性
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("org_audit_date", "");
					} else {
						setBookMarkValue1("org_audit_date", jgdm);
					}
				}
				//签名
				if(doc.BookMarks.Exists("org_audit_op")){					
					var op = data.org_audit_op_id;
					if(op!=null&&op!=undefined&&op!=""){
						$.ajax({
							url : "employee/basic/getEmpSignId.do",
							type : "POST",
							async:false,
							datatype : "json",
							data : {
								id:op,type:"user"
							},
							success : function(res) {
								if(res.pictureID!=null){								
								TANGER_OCX_OBJ.ActiveDocument.Application.Selection.GoTo(-1, 0, 0, "org_audit_op");															
								TANGER_OCX_OBJ.AddPicFromURL($("base").attr("href")+"fileupload2/downloadByObjId2.do?id="+res.pictureID,
											true,1,0,1,100); 
								}
							}
						})
					}
				}
				//审核意见
				if (doc.BookMarks.Exists("audit_opinion")) {
					var jgdm = data.audit_opinion;
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("audit_opinion", "");
					} else {
						var opinion=getOpinion(jgdm);
						setBookMarkValue1("audit_opinion", opinion);
					}
				}
				//审核时间
				if (doc.BookMarks.Exists("audit_date")) {
					var jgdm =new Date(data.audit_date.replace(/-/g,"/")).format("yyyy年MM月dd日");//兼容性
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("audit_date", "");
					} else {
						setBookMarkValue1("audit_date", jgdm);
					}
				}
				//审核人签名
				if(doc.BookMarks.Exists("audit_op")){					
					var op = data.audit_op_id;
					if(op!=null&&op!=undefined&&op!=""){
						$.ajax({
							url : "employee/basic/getEmpSignId.do",
							type : "POST",
							async:false,
							datatype : "json",
							data : {
								id:op,type:"user"
							},
							success : function(res) {
								if(res.pictureID!=null){								
								TANGER_OCX_OBJ.ActiveDocument.Application.Selection.GoTo(-1, 0, 0, "audit_op");															
								TANGER_OCX_OBJ.AddPicFromURL($("base").attr("href")+"fileupload2/downloadByObjId2.do?id="+res.pictureID,
											true,1,0,1,100); 
								}
							}
						})
					}
				}
				
				//审批意见
				/* if (doc.BookMarks.Exists("sign_opinion")) {
					var jgdm = data.sign_opinion;
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("sign_opinion", "");
					} else {
						var opinion=getOpinion(jgdm);
						setBookMarkValue1("sign_opinion", opinion);
					}
				}
				if (doc.BookMarks.Exists("sign_opinion")) {
					var jgdm = data.sign_opinion;
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("sign_opinion", "");
					} else {
						var opinion=getOpinion(jgdm);
						setBookMarkValue1("sign_opinion", opinion);
					}
				}
				//审批时间
				if (doc.BookMarks.Exists("sign_date")) {
					var jgdm =new Date(data.sign_date.replace(/-/g,"/")).format("yyyy年MM月dd日");//兼容性
					if (jgdm == null||jgdm == "null"||jgdm == undefined) {
						setBookMarkValue1("sign_date", "");
					} else {
						setBookMarkValue1("sign_date", jgdm);
					}
				}
				//审批人签名
				if(doc.BookMarks.Exists("sign_op")){					
					var op = data.sign_op_id;
					if(op!=null&&op!=undefined&&op!=""){
						$.ajax({
							url : "inspectionInfoAction/getSignPictureById.do",
							type : "POST",
							async:false,
							datatype : "json",
							data : {
								id:op
							},
							success : function(res) {
								if(res.pictureID!=null){								
								TANGER_OCX_OBJ.ActiveDocument.Application.Selection.GoTo(-1, 0, 0, "sign_op");															
								TANGER_OCX_OBJ.AddPicFromURL($("base").attr("href")+"fileupload/downloadByObjId.do?id="+res.pictureID,
											true,1,0,1,100); 
								}
							}
						})
					}
				} */
				TANGER_OCX_OBJ.SetReadOnly(true,"");
			}
		});
	}
	function getOpinion(opinion){
		if(opinion==1){
			return "同意";
		}else{
			return "不通过";
		}
	}
</script>
</head>
<body onload="initPage();">
	<div class="layout">
		<div position="center" id="editor_container"
			style="width: 100%; height: 100%"></div>
		<div position="bottom"  style="height: 50px;">
			<div class="div1" id="toolbar"
				style="padding: 1px; text-align: right;"></div>
		</div>
	</div>
	<iframe id="export_doc_iframe" style="display: none;"> </iframe>
</body>
</html>