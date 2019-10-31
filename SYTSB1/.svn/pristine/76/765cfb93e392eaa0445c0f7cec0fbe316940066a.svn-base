<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>报告审核提交</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	var device_sort_code = api.data.device_sort_code;
	var check_type = "${param.check_type}";
	$(function() {

		$("#form1").initForm({ //参数设置示例
			toolbar : [ {
				text : '保存',
				//id : 'save',
				icon : 'save',
				click : save
			}, {
				text : '关闭',
				//id : 'close',
				icon : 'cancel',
				click : close
			} ],
			toolbarPosition : "top",
			getSuccess : function(res) {
				
			},
			success : function(res) {
				if ("${param.type}" == "sub") {
					api.data.window.subCheck2();
				}
			}
		});

		
		
	})
	
	function close(url) {
		api.close();

	}
	function save() {
		var bhg_name = "";
		var bhg_value = "";
		$("[name='bhg_name']").each(
				function() {
					if ($(this).attr("checked")) {
						if (bhg_name == "") {
							bhg_name = $(this).val();
						} else {
							bhg_name = bhg_name + "," + $(this).val();
						}

						if (bhg_value == "") {
							bhg_value = $(this).parent().next().text();
						} else {
							bhg_value = bhg_value + ","
									+ $(this).parent().next().text();
						}

					}
				})

		if (bhg_name == '') {
			top.$.notice("请选择问题来源！");
			return;
		}
		url = "reportBHGRecordAction/addErrorResource.do?id=${param.ids}";
		//验证表单数据
		if ($('#form1').validate().form()) {
			$("body").mask("正在保存数据，请稍后！");
			$.ajax({
				url : url,
				type : "POST",
				datatype : "json",
				data : {
					"bhg_name" : bhg_name,
					"bhg_value" : bhg_value
				},
				success : function(data, stats) {
					$("body").unmask();
					if (data["success"]) {
						top.$.dialog.notice({
							content : '保存成功'
						});
						if ("${param.type}" == "sub") {
							api.data.window.subCheck2();
						}
						//api.data.window.submitAction();
						api.close();

					} else {
						$.ligerDialog.error('提示：' + data.msg);
					}
				},
				error : function(data, stats) {
					$("body").unmask();
					$.ligerDialog.error('提示：' + data.msg);
				}
			});
		}
	}
	function setValue(valuex, name) {
		if (valuex == "") {
			return;
		}
		if (name == 'next_op_name') {
			$('#next_op_name').val(valuex)
		}
	}

	function init() {
		if (device_sort_code == "" || check_type == "") {
			api.data.window.showmsg1();
			api.close();
			return;
		}
		sort = device_sort_code.substring(0, 1);
		//alert("设备类型："+sort+"------------------检验类型："+check_type)
		if (check_type == "1") {
			//制造监督检验
			if (sort == "1" || sort == "2"
					|| (sort == "7" && device_sort_code != "7310")
					|| sort == "B") {
				//锅炉	压力容器	气瓶	压力管道元件	材料	零部件
				$("#zzjd").show();
				$("#jdldqjy").hide();
				$("#azgzwxjd").hide();
				$("#jkjd").hide();
				$("#cyldqjy").hide();
				$("#glsjwjjd").hide();
			}
		} else if (check_type == "3") {
			//定期检验
			if (sort == "3" || sort == "4" || sort == "5" || sort == "6"
					|| sort == "9") {
				//机电类特种设备
				//电梯	起重机械	 场（厂）内专用机动车辆	客运索道	大型游乐设施
				$("#zzjd").hide();
				$("#jdldqjy").show();
				$("#azgzwxjd").hide();
				$("#jkjd").hide();
				$("#cyldqjy").hide();
				$("#glsjwjjd").hide();
			} else if (sort == "1" || sort == "2" || sort == "8") {
				//承压类特种设备
				//锅炉	压力容器	压力管道	气瓶
				$("#zzjd").hide();
				$("#jdldqjy").hide();
				$("#azgzwxjd").hide();
				$("#jkjd").hide();
				$("#cyldqjy").show();
				$("#glsjwjjd").hide();
			} else {
				if ("${param.type}" == "sub") {
					api.data.window.subCheck2();
				} else {
					api.data.window.showmsg2();
				}
				api.close();
				return;
			}
		} else if (check_type == "2") {
			//安装改造维修监督检验
			if (sort == "3" || sort == "1" || sort == "2"
					|| (sort == "7" && device_sort_code != "7310")
					|| sort == "4" || sort == "9" || sort == "6" || sort == "8") {
				//锅炉 压力容器 气瓶   压力管道   电梯   起重机械     客运索道 大型游乐设施
				$("#zzjd").hide();
				$("#jdldqjy").hide();
				$("#azgzwxjd").show();
				$("#jkjd").hide();
				$("#cyldqjy").hide();
				$("#glsjwjjd").hide();
			} else {
				if ("${param.type}" == "sub") {
					api.data.window.subCheck2();
				} else {
					api.data.window.showmsg2();
				}
				api.close();
				return;
			}
		} else if (check_type == "7") {
			//进口设备检验
			if (sort == "1" || sort == "2"
					|| (sort == "7" && device_sort_code != "7310")) {
				//锅炉	压力容器	气瓶	压力管道元件
				$("#zzjd").hide();
				$("#jdldqjy").hide();
				$("#azgzwxjd").hide();
				$("#jkjd").show();
				$("#cyldqjy").hide();
				$("#glsjwjjd").hide();
			} else {
				if ("${param.type}" == "sub") {
					api.data.window.subCheck2();
				} else {
					api.data.window.showmsg2();
				}
				api.close();
				return;
			}
		} else if (check_type == "9") {
			//设计文件鉴定(资料审查)
			if (sort == "1") {
				//锅炉
				$("#zzjd").hide();
				$("#jdldqjy").hide();
				$("#azgzwxjd").hide();
				$("#jkjd").hide();
				$("#cyldqjy").hide();
				$("#glsjwjjd").show();
			} else {
				if ("${param.type}" == "sub") {
					api.data.window.subCheck2();
				} else {
					api.data.window.showmsg2();
				}
				api.close();
				return;
			}
		} else {
			if ("${param.type}" == "sub") {
				api.data.window.subCheck2();
			} else {
				api.data.window.showmsg2();
			}
			api.close();
			return;
		}
	}
</script>
</head>
<body onload="init()">
	<form id="form1">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>选择问题来源：</div>
			</legend>
			<!-- 锅炉	压力容器	气瓶	压力管道元件	材料	零部件     制造监督检验-->
			<table cellpadding="3" cellspacing="0"
				style="border-color: blue; margin-left: 100px; width: 80%;"
				id="zzjd">
				<!-- <tr>
					<td>锅炉	压力容器	气瓶	压力管道元件	材料	零部件     制造监督检验
					</td>
				</tr> -->
				<tr>
					<td><input type="checkbox" name="bhg_name" value="SJ"><span>设计</span>
						<hr /></td>
						
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="GY"><span>工艺</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="CL"><span>材料</span>
						<hr /></td>
						
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="JXZZYJG"><span>机械制作与加工</span>
						<hr /></td>
				</tr>
				
				<tr>
					<td><input type="checkbox" name="bhg_name" value="HJ"><span>焊接</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="RCL"><span>热处理</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="WSJC"><span>无损检测</span>
						<hr /></td>
					
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="LH"><span>理化</span>
						<hr /></td>
				</tr>
				
				<tr>
					<td><input type="checkbox" name="bhg_name" value="KZXT"><span>控制系统</span>
						<hr /></td>
					
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="JYYSY"><span>检验与试验</span>
						<hr /></td>
				</tr>
				
				<tr>
					<td><input type="checkbox" name="bhg_name" value="SBHJYSYZZ"><span>设备和检验试验装置</span>
						<hr /></td>
						
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="CCZL"><span>出厂资料</span>
						<hr /></td>
				</tr>
				
				<tr>
					<td><input type="checkbox" name="bhg_name" value="ZLGL"><span>质量管理</span>
						<hr /></td>
					
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="QT"><span>其它</span>
						<hr /></td>
				</tr>
				
			</table>
			<!-- 锅炉压力容器气瓶压力管道电梯起重机械客运索道     安装改造维修监督检验 -->
			<table cellpadding="3" cellspacing="0"
				style="border-color: blue; margin-left: 100px; width: 80%;"
				id="azgzwxjd">
				<!-- <tr>
					<td>锅炉压力容器气瓶压力管道电梯起重机械客运索道     安装改造维修监督检验
					</td>
				</tr> -->
				<tr>
					<td><input type="checkbox" name="bhg_name" value="SJ"><span>设计</span>
						<hr /></td>
					
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="GY"><span>工艺</span>
						<hr /></td>
				</tr>
				
				<tr>
					<td><input type="checkbox" name="bhg_name" value="CL"><span>材料</span>
						<hr /></td>
					
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="JXZZYJG"><span>机械制作与加工</span>
						<hr /></td>
				</tr>
				
				<tr>
					<td><input type="checkbox" name="bhg_name" value="HJ"><span>焊接</span>
						<hr /></td>
					
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="RCL"><span>热处理</span>
						<hr /></td>
				</tr>
				
				<tr>
					<td><input type="checkbox" name="bhg_name" value="WSJC"><span>无损检测</span>
						<hr /></td>
					
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="LH"><span>理化</span>
						<hr /></td>
				</tr>
				
				<tr>
					<td><input type="checkbox" name="bhg_name" value="KZXT"><span>控制系统</span>
						<hr /></td>
					
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="JYYSY"><span>检验与试验</span>
						<hr /></td>
				</tr>
			
				<tr>
					<td><input type="checkbox" name="bhg_name" value="SBHJYSYZZ"><span>设备和检验试验装置</span>
						<hr /></td>
					
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="TS"><span>调试</span>
						<hr /></td>
				</tr>
				
				<tr>
					<td><input type="checkbox" name="bhg_name" value="JGCL"><span>竣工资料</span>
						<hr /></td>
					
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="ZLGL"><span>质量管理</span>
						<hr /></td>
				</tr>
				
				<tr>
					<td><input type="checkbox" name="bhg_name" value="QT"><span>其它</span>
						<hr /></td>
				</tr>
			</table>
			<!-- 电梯起重机械场（厂）内专用机动车辆客运索道大型游乐设施     机电类特种设备定期检验 -->
			<table cellpadding="3" cellspacing="0"
				style="border-color: blue; margin-left: 100px; width: 80%;"
				id="jdldqjy">
				<!-- 	<tr>
					<td>电梯起重机械场（厂）内专用机动车辆客运索道大型游乐设施     机电类特种设备定期检验
					</td>
				</tr> -->
				<tr>
					<td><input type="checkbox" name="bhg_name" value="JSZL"><span>技术资料</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="JXCD"><span>机械传动</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="YQYCD"><span>液（气）压传动</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="DQXT"><span>电气系统</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="JSJG"><span>金属结构</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="AQBHZZ"><span>安全保护装置</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="QT"><span>其它</span>
						<hr /></td>
				</tr>
			</table>
			<!-- 锅炉压力容器气瓶压力管道元件    进口监督检验 -->
			<table cellpadding="3" cellspacing="0"
				style="border-color: blue; margin-left: 100px; width: 80%;"
				id="jkjd">
				<!-- 	<tr>
					<td>锅炉压力容器气瓶压力管道元件    进口监督检验
					</td>
				</tr> -->
				<tr>
					<td><input type="checkbox" name="bhg_name" value="XKZ"><span>许可证</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="WJZL"><span>文件资料</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="CL"><span>材料</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="HJ"><span>焊接</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="JG"><span>结构</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="WSJC"><span>无损检测</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="WGJJHCC"><span>外观及几何尺寸</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="YLXSSY"><span>压力（型式）试验</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="CCZL"><span>出厂资料</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="QT"><span>其他</span>
						<hr /></td>
				</tr>
			</table>
			<!--锅炉压力容器压力管道气瓶    承压类特种设备定期检验 -->
			<table cellpadding="3" cellspacing="0"
				style="border-color: blue; margin-left: 100px; width: 80%;"
				id="cyldqjy">
				<!-- 	<tr>
					<td>锅炉压力容器压力管道气瓶    承压类特种设备定期检验
					</td>
				</tr> -->
				<tr>
					<td><input type="checkbox" name="bhg_name" value="JSZL"><span>技术资料</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="FS"><span>腐蚀</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="LW"><span>裂纹</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="XL"><span>泄露</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="MS"><span>磨损</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="CZLH"><span>材质劣化</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="AQFJ"><span>安全附件</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="ZZYLQX"><span>制造遗留缺陷</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="QT"><span>其他</span>
						<hr /></td>
				</tr>
			</table>
			<!--锅炉    设计文件鉴定 -->
			<table cellpadding="3" cellspacing="0"
				style="border-color: blue; margin-left: 100px; width: 80%;"
				id="glsjwjjd">
				<!-- <tr>
					<td>锅炉    设计文件鉴定
					</td>
				</tr> -->
				<tr>
					<td><input type="checkbox" name="bhg_name" value="QZSX"><span>签字手续</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="AQ"><span>安全</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="JN"><span>节能</span>
						<hr /></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="bhg_name" value="QT"><span>其他</span>
						<hr /></td>
				</tr>
			</table>
		</fieldset>
	<!-- <fieldset class="l-fieldset">
		<legend class="l-legend">
			<div></div>
		</legend>
		<div style="height: 280px;" id="resource"></div>
	</fieldset> -->
	</form>
	
</body>
</html>
