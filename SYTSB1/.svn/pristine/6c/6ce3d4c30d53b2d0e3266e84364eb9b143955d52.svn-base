<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>银行转账数据列表</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
	var tbar = "";
	$(function() {
		tbar = [ {
			text : '下载模板',
			id : 'downloadExcel',
			icon : 'excel-export',
			click : downloadExcel
		}, {
			text : '导入',
			id : 'save',
			icon : 'save',
			click : save
		}, {
			text : '关闭',
			id : 'close',
			icon : 'cancel',
			click : function() {
				api.data.window.Qm.refreshGrid();
				api.close();
			}
		} ];
		$("#form1").initForm({
			showToolbar : false,
			toolbarPosition : "bottom",
			toolbar : tbar,
			success : function(response) {
				if (response.success) {
					top.$.notice("保存成功！", 3);
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error("操作失败！<br/>" + response.msg);
				}
			},
			afterParse : function() {
				//创建上传实例
				new KHFileUploader({
					fileSize : "10mb",//文件大小限制
					buttonId : "save",//上传按钮ID 
					container : "filecontainer3",//上传控件容器ID
					title : "工资导入",//文件选择框提示
					saveDB : false,
					extName : "xls,xlsx",//文件扩展名限制
					fileNum : 1,//限制上传文件数目
					callback : function(files) {
						saveData(files);//文件无误，执行保存
					}
				});
			}
		});
		/*向下拉框中填入数据*/
		var currentDate = new Date();
		var currentYear = currentDate.getFullYear();
		var currentMonth = currentDate.getMonth() + 1;
		var size = currentYear - 2015 + 1;
		for (var i = 0; i < size; i++) {
			var yearOld = currentYear - i;
			if (yearOld == currentYear) {
				$("#salaryYear").append(
						$("<option value="+yearOld+" selected>" + yearOld
								+ "</option>"));
			} else {
				$("#salaryYear")
						.append(
								$("<option value="+yearOld+">" + yearOld
										+ "</option>"));
			}
		}
		if ($("#salaryYear").find("option:selected").val() == currentYear) {
			for (var i = 0; i < currentMonth; i++) {
				var mathOld = currentMonth - i;
				if (mathOld == currentMonth) {
					if (mathOld < 10) {
						$("#salaryTmonth").append(
								$("<option value=0"+mathOld+" selected>0"
										+ mathOld + "</option>"));
					} else {
						$("#salaryTmonth").append(
								$("<option value="+mathOld+" selected>"
										+ mathOld + "</option>"));
					}
				} else {
					if (mathOld < 10) {
						$("#salaryTmonth").append(
								$("<option value=0"+mathOld+">0" + mathOld
										+ "</option>"));
					} else {
						$("#salaryTmonth").append(
								$("<option value="+mathOld+">" + mathOld
										+ "</option>"));
					}
				}
			}
		} else {
			for (var i = 0; i < 12; i++) {
				var mathOld = 12 - i;
				if (mathOld == currentMonth) {
					if (mathOld < 10) {
						$("#salaryTmonth").append(
								$("<option value=0"+mathOld+" selected>0"
										+ mathOld + "</option>"));
					} else {
						$("#salaryTmonth").append(
								$("<option value="+mathOld+" selected>"
										+ mathOld + "</option>"));
					}
				} else {
					if (mathOld < 10) {
						$("#salaryTmonth").append(
								$("<option value=0"+mathOld+">0" + mathOld
										+ "</option>"));
					} else {
						$("#salaryTmonth").append(
								$("<option value="+mathOld+">" + mathOld
										+ "</option>"));
					}
				}
			}
		}
	});
	function directChange() {
		var obj = $("#form1").validate().form();
		if (obj) {
			$("#form1").submit();
		} else {
			return;
		}
	}
	function yearSelected() {
		var currentDate = new Date();
		var currentYear = currentDate.getFullYear();
		var currentMonth = currentDate.getMonth() + 1;
		$("#salaryTmonth").empty();
		if ($("#salaryYear").find("option:selected").val() == currentYear) {
			for (var i = 0; i < currentMonth; i++) {
				var mathOld = currentMonth - i;
				if (mathOld == currentMonth) {
					if (mathOld < 10) {
						$("#salaryTmonth").append(
								$("<option value=0"+mathOld+" selected>0"
										+ mathOld + "</option>"));
					} else {
						$("#salaryTmonth").append(
								$("<option value="+mathOld+" selected>"
										+ mathOld + "</option>"));
					}
				} else {
					if (mathOld < 10) {
						$("#salaryTmonth").append(
								$("<option value=0"+mathOld+">0" + mathOld
										+ "</option>"));
					} else {
						$("#salaryTmonth").append(
								$("<option value="+mathOld+">" + mathOld
										+ "</option>"));
					}
				}
			}
		} else {
			for (var i = 0; i < 12; i++) {
				var mathOld = 12 - i;
				if (mathOld == currentMonth) {
					if (mathOld < 10) {
						$("#salaryTmonth").append(
								$("<option value=0"+mathOld+" selected>0"
										+ mathOld + "</option>"));
					} else {
						$("#salaryTmonth").append(
								$("<option value="+mathOld+" selected>"
										+ mathOld + "</option>"));
					}
				} else {
					if (mathOld < 10) {
						$("#salaryTmonth").append(
								$("<option value=0"+mathOld+">0" + mathOld
										+ "</option>"));
					} else {
						$("#salaryTmonth").append(
								$("<option value="+mathOld+">" + mathOld
										+ "</option>"));
					}
				}
			}
		}
	}
	function save() {

	}
	// 上传完成，开始保存汇编数据
	var importSalary = $("#form1").getValues();
	function saveData(files) {
		var month = $("#salaryYear").find("option:selected").val();
		var etime = $("#salaryTmonth").find("option:selected").val();
		if (etime == null || etime == "" || etime == "null") {
			var manager = $.ligerDialog.waitting('请选择导入工资的月份...');
			setTimeout(function() {
				manager.close();
			}, 1500);
		} else {
			$("body").mask("正在保存...");
			$
					.post(
							"finance/salaryAction/savegz.do",
							{
								month : $.ligerui.toJSON(month),
								etime : $.ligerui.toJSON(etime),
								files : $.ligerui.toJSON(files),
								importSalary : $.ligerui.toJSON(importSalary)
							},
							function(data) {
								$("body").unmask();
								if (data.success) {
									if (data.repData != ""
											&& data.repData != 'undefined') {
										api.data.window.Qm.refreshGrid();
										top.$.notice("保存成功！");
										api.close();
									}
								} else {
									$.ligerDialog
											.error("保存失败！请确认&nbsp;<span style='color:red;'>"
													+ data.result
													+ "</span>&nbsp;在系统中的信息或在表格中的数据是否正确！<br/><span style='color:red;'>确认后，请删除刚导入的数据并重新导入！</span>");
								}
							}, "json");
		}
	}
	//下载模板，通过文件id下载
	function downloadExcel() {
	/* 	window.location.href = "/fileupload/downloadByFilePath.do?path=2407121277918181888.xlsx&fileName="
				+ encodeURI(encodeURI("四川省特种设备检验研究院在编人员工资表模板.xlsx")); */
				window.location.href = "/fileupload/download.do?id=402883a05398c1bd01539c34605323d2&fileName="
					+ encodeURI(encodeURI("四川省特种设备检验研究院在编人员工资表模板.xlsx"));			
		
	}
</script>
<style type="">
.l-icon-downloadExcel {
	background: url('k/kui/images/icons/16/excel-export.png') no-repeat center;
}

select {
	width: 130px;
	height: 26px;
}
</style>
</head>
<body>
	<form id="form1" action="finance/importSalaryAction/saveim.do">
		<h1
			style="padding: 5mm 0 2mm 0; font-family: 宋体; font-size: 7mm; text-align: center;">工资导入</h1>
		<table style="margin-top: 7%;">
			<tr style="display: none">
				<td class="l-t-td-left">月份/工资</td>
				<td colspan="3"><input id="salartTime" name="salartTime"
					type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM'}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">年份</td>
				<td class="l-t-td-right"><select id="salaryYear"
					onchange="yearSelected()">
				</select></td>
				<td class="l-t-td-left">月份</td>
				<td class="l-t-td-right"><select id="salaryTmonth">
				</select></td>
			</tr>
		</table>
	</form>
	<div id="filecontainer3"></div>
</body>
</html>
