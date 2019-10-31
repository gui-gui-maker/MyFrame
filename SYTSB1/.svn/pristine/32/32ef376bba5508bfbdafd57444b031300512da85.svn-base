<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head pageStatus="add">
	<%@ include file="/k/kui-base-form.jsp"%>
	<script type="text/javascript" src="app/tzsb/inspection/flow/insing/support/ChipCombobox.js"></script>
	<script type="text/javascript">
	var w = window.screen.availWidth;
	var h = window.screen.availHeight;
	
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'确定', id:'save',icon:'save', click:saveInfo},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(resp){
	  
	        },
	        success: function (response) {
	        	
			}
		});
		
	});	
	
	
		function chooseTemp() {
			top.$.dialog({
				parent : api,
				width : 800,
				height : 550,
				lock : true,
				title : "选择模版",
				content : 'url:app/relation/choose_report_list.jsp',
				data : {
					"window" : window
				}
			});
		}

		function callBack(report_id, report_rtbox_code, report_name, ysjl_rtbox_code) {
			$("#report_id").val(report_id);
			$("#report_rtbox_code").val(report_rtbox_code);
			$("#report_name").val(report_name);
			$("#ysjl_rtbox_code").val(ysjl_rtbox_code);
		}

		function saveInfo() {
			//验证表单数据
			var report_id = $("#report_id").val();
			var ysjl_rtbox_code = $("#ysjl_rtbox_code").val();
			var report_rtbox_code = $("#report_rtbox_code").val();
			var report_name = $("#report_name").val();
			if ("" == ysjl_rtbox_code) {
				top.$.dialog.notice({
					content : '亲，您选择的报告暂无对应的原始记录模板！'
				});
			} else if ("" == report_rtbox_code) {
				top.$.dialog.notice({
					content : '亲，您选择的报告暂无对应的报告模板！'
				});
			} else {
				top.$
						.dialog({
							width : w,
							height : h,
							lock : true,
							title : "新增对应关系",
							data : {
								"pwindow" : window,
								"ysjl_rtCode":ysjl_rtbox_code,
								"report_rtCode":report_rtbox_code,
								"report_name":report_name
								
							},
							content : 'url:app/relation/relation_index.jsp?status=add&report_id='
									+ report_id
									+ '&ysjl_rtbox_code='
									+ ysjl_rtbox_code
									+ '&report_rtbox_code='
									+ report_rtbox_code
									//+ '&report_name=' + report_name
						});
			}
		}

		function close() {
			api.data.window.refreshGrid();
			api.close();
		}
	</script>
</head>
	<body>
		<form name="formObj" id="formObj" method="post"
			action=""
			getAction="">
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>
						对应关系
					</div>
				</legend>
				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					<tr>
						<td class="l-t-td-left">模板：</td>
						<td class="l-t-td-right">
							<input id="report_id" name="report_id"  type="hidden"  />
							<input id="report_rtbox_code" name="report_rtbox_code"  type="hidden"  />
							<input id="ysjl_rtbox_code" name="ysjl_rtbox_code"  type="hidden"  />
							<input name="report_name" id="report_name" type="text" ltype='text' validate="{required:true,maxlength:200}" ligerui="{value:'',iconItems:[{icon:'add',click:function(){chooseTemp()}}]}" onclick="chooseTemp('0')"/>
						</td>							
					</tr>
				</table>
			</fieldset>	
		</form>
	</body>
</html>