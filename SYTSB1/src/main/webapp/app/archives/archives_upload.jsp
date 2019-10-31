<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%
	DateFormat ds = new SimpleDateFormat("yyyyMMdd");
	String date = ds.format(new Date());
%>
<title></title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript"
	src="pub/fileupload1/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function() {
		initGrid();
		$("#form1").initForm({
			toolbar : [{
				text : '删除',
				id : 'delete',
				icon : 'delete',
				click : dels
			}, {
				text : '关闭',
				id : 'close',
				icon : 'close',
				click : function() {
					api.close();
				}
			} ],
			getSuccess : function(response) {
				if (response.success) {
					//初始化列表
					g.loadData({
						Rows : response.list
					});
				}
			}
		});

		var receiptUploadConfig = {
			fileSize : "500mb", //文件大小限制
			businessId : "", //业务ID
			folder :'${param.report_sn}',
			attType : "0",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
			buttonId : "procufilesBtn", //上传按钮ID
			container : "procufilesDIV", //上传控件容器ID
			title : "图片（png,gif,jpg,jpeg）、word、pdf", //文件选择框提示
			extName : "doc,pdf,png,gif,jpg,jpeg,txt,rm,rmvb,wmv,avi,mp4,3gp,mkv", //文件扩展名限制
			workItem : "", //页面多点上传文件标识符号
			fileNum : 100, //限制上传文件数目
			callback : function(file) { //上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
				addAttachFile(file);
				console.log(file);
				
				//验证表单数据
				if ($('#form1').validate().form()) {
					var formData = $("#form1").getValues();
					formData.files = $.ligerui.toJSON(file);
					$("body").mask("正在保存数据，请稍后！");
					$.post("uploadsAction/a/saveUpload.do", formData, function(res) {
						if (res["success"]) {
							$("body").unmask();
							top.$.dialog.notice({
								content : '保存成功'
							});
							$("#procufiles").empty();
							refreshGrid();
						} else {
							$("body").unmask();
							$.ligerDialog.error('提示：' + res["msg"]);
						}
					});
				} 
			}
		};
		var receiptUploader = new KHFileUploader(receiptUploadConfig);
	});
	//初始化选择框
	var g;
	function initGrid() {
		g = $("#g").ligerGrid({
			columns : [ {
				display : 'empId',
				name : 'fileId',
				width : '1%',
				hide : true
			}, {
				display : 'id',
				name : 'id',
				width : '10%',
				hide : true
			}, {
				display : 'uploadId',
				name : 'uploadId',
				width : '10%',
				hide : true
			}, {
				display : '上传类型',
				name : 'uploadType',
				width : '20%',
				render : function(item) {
					var clsses = [ {
						text : '检验报告',
						id : '0'
					}, {
						text : '原始记录',
						id : '1'
					}, {
						text : '自检报告',
						id : '2'
					}, {
						text : '其他',
						id : '3'
					} ];
					for (var i = 0; i < clsses.length; i++) {
						if (item["uploadType"] == clsses[i].id) {
							return clsses[i].text;
						}
					}
				}
			}, {
				display : '文件名称',
				name : 'uploadName',
				width : '20%'
			} ],
			checkbox : true,
			rownumbers : true,
			height : "280",
			width : "98%",
			frozenRownumbers : false,
			usePager : false,
			data : {
				Rows : []
			},
			onSelectRow : function(rowdata, rowid, rowobj) {

			}
		});
	}

	// 将上传的所有文件id写入隐藏框中
	function getUploadFile() {
		var attachId = "";
		var i = 0;
		$("#procufiles li").each(function() {
			attachId += (i == 0 ? "" : ",") + this.id;
			i = i + 1;
		});
		if (attachId != "") {
			attachId = attachId.substring(0, attachId.length);
		}
		$("#uploadFiles").val(attachId);
	}

	//添加附件
	function addAttachFile(files) {

		if ("${param.pageStatus}" == "detail") {
			$("#procufilesBtn").hide();
		}
		if (files) {
			var attContainerId = "procufiles";
			$
					.each(
							files,
							function(i) {
								var file = files[i];

								$("#procufiles")
										.append(
												"<li id='"+file.id+"'>"
														+ "<div><a href='#' onclick='editor(\""
														+ file.path
														+ "\",\""
														+ file.name
														+ "\",\""
														+ status
														+ "\");return false'>"
														+ file.name
														+ "</a></div>"
														+ "<div class='l-icon-close progress' onclick='deleteUploadFile(\""
														+ file.id
														+ "\",\""
														+ file.path
														+ "\",this,getUploadFile)'>&nbsp;</div>"
														+ "</li>");
							});
			//getUploadFile();
		}
	}
	//编辑word文档
	function editor(docId, docName, status) {
		var type = "";
		var id = $("#uploadId").val()
		var documentDoc = $("#uploadDoc").val()
		//		alert(documentDoc)
		var doc = "0";
		if (documentDoc != "" && documentDoc != null) {
			doc = "1";
		}
		if (status == "") {
			type = "modify";
		} else {
			type = "add";
		}
		//打开生成报告页面
		openContentDoc({
			docId : docId,
			doc : doc,
			status : "draft",
			id : id,
			type : type,
			window : window,
			title : docName,
			pdf : true,
			tbar : {
				edit : false,
				print : false,
				layout : true
			}
		});

	}

	// 显示附件文件
	function showAttachFile(files) {

		if ("${param.pageStatus}" == "detail") {
			$("#procufilesBtn").hide();
		}
		if (files) {
			//详情
			var attContainerId = "procufiles";

			if ("${param.pageStatus}" == "detail") {
				$.each(files, function(i) {
					var file = files[i];
					//显示附件
					$("#" + attContainerId).append(
							"<li id='"+file.id+"'>"
									+ "<div><a href='#' onclick='editor(\""
									+ file.uploadPath + "\",\""
									+ file.uploadName + "\",\"" + status
									+ "\");return false'>" + file.uploadName
									+ "</a></div>" + "</li>");
				});
			}
			//修改
			else if ("${param.pageStatus}" == "modify") {
				$
						.each(
								files,
								function(i) {
									var file = files[i];
									$("#" + attContainerId)
											.append(
													"<li id='"+file.id+"'>"
															+ "<div><a href='#' onclick='editor(\""
															+ file.uploadPath
															+ "\",\""
															+ file.uploadName
															+ "\",\""
															+ status
															+ "\");return false'>"
															+ file.uploadName
															+ "</a></div>"
															+ "<div class='l-icon-close progress' onclick='deleteUploadFile(\""
															+ file.id
															+ "\",\""
															+ file.uploadPath
															+ "\",this,getUploadFile)'>&nbsp;</div>"
															+ "</li>");
									getUploadFile();
								});
			}
		}
	}
	function dels() {
		var selected = g.getSelectedRows();
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$("body").mask("正在删除，请稍后！");
				$.post("uploadsAction/a/deleteUploads.do", {
					rows : $.ligerui.toJSON(selected)
				}, function(res) {
					if (res["success"]) {
						$("body").unmask();
						top.$.dialog.notice({
							content : '删除成功'
						});
						refreshGrid();
					} else {
						$("body").unmask();
						$.ligerDialog.error('提示：' + res["msg"]);
					}
				});
			}
		});

	}
	function refreshGrid() {
		$.post("uploadsAction/a/detailUpload.do?fileId=${param.id}", {},
			function(res) {
				if (res["success"]) {
					g.loadData({
						Rows : res["list"]
					});
				} else {
					$.ligerDialog.error('提示：刷新列表出错！');
				}
			}
		);
	}
</script>
</head>

<body>
	<form name="form1" id="form1" method="post"
		action="uploadsAction/a/save1.do"
		getAction="uploadsAction/a/detailUpload.do?fileId=${param.id}">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>文件上传</div>
			</legend>
			<input type="hidden" name="id" id="uploadId" /> 
			<!-- 报告id -->
			<input type="hidden" name="fileId" id="fileId" value="${param.id}" />
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">选择上传的类型</td>
					<td class="l-t-td-right"><input type="radio" name="uploadType"
						id="uploadType" ltype="radioGroup"
						ligerui="{value:'附件',data: [ { text:'检验报告', id:'0' }, { text:'原始记录', id:'1' }, { text:'自检报告', id:'2' }, { text:'其他', id:'3' }] }" />
					</td>
					<td class="l-t-td-left">文件：</td>
					<td class="l-t-td-right"><input name="uploadFiles"
						type="hidden" id="uploadFiles" validate="{maxlength:1000}" />
						<p id="procufilesDIV">
							<a class="l-button" id="procufilesBtn"> <span
								class="l-button-main"><span class="l-button-text">选择文件</span></span>
							</a>
						</p>
						<div class="l-upload-ok-list" id="div">
							<ul id="procufiles"></ul>
						</div></td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend"> 文件列表 </legend>
			<div id="g" style="height: 300px;"></div>
		</fieldset>
	</form>
</body>
</html>