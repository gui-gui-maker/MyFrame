<%@page import="com.khnt.bpm.core.engine.FlowEngine"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="modify">
<title>工作流演示-起草</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function () {
		api.size(750,$(top).height()-100);
		api.position($(top).width()/2-375,25);
		$("#app_form").transform("detail");
		$("#form2").initForm({
			toolbar: [{
				text: '查看流程', icon: 'follow', click: function () {
					trackProcess("${processId}");
				}
			},{
				text: '提交', icon: 'submit', click: readySubmit
			},<bpm:ifPer function="turnback" activityId="${activityId}">{
				text: '退回', icon: 'submit', click: goback
			},</bpm:ifPer>{
				text: '取消', icon: 'cancel', click: function () {
					api.close();
				}
			}],
			getSuccess:function(resp){
				$("#app_form").setValues(resp.data);
			}
		});
		$.getJSON("bpm/opinion/serviceOpinion.do?serviceId=${serviceId}",function(resp){
			$("#app_records").ligerGrid({
				columns:[
					{display:'审批人',name:'signerName',align:'center',width:100},
					{display:'审批时间',name:'signDate',align:'center',width:130},
					{display:'审批意见',name:'opinion',align:'left',width:350}
				],
				usePager: false,
				data:{Rows:resp.data},
			});
		});
	});
	
	function goback(){
		$("body").mask("正在处理，请稍后！");
		$.getJSON("bpm/flowExt/nextActivities.do?activityId=${activityId}&forward=false",function(resp1){
			if(resp1.success){
				if(resp1.data.length==0){
					$.lgierDialog.error("不能退回，没有可以退回的目标环节！");
					return;
				}
				var htmls="<form style='padding-top:3em;' id='actidc'>"
				$.each(resp1.data,function(){
					htmls += "<p style='padding-left:5em;'><input type='radio' name='actid' value='" + this.id + "' id='"+this.id + "'/><label for='"+this.id+"'>"+this.name+"</label></p>";
				});
				htmls += "<p style='padding-left:5em;'><label>备注</label><textarea rows='5' cols='20' id='turnbackNote'></textarea></p>";
				window['atcwindow'] = $.ligerDialog.open({
					title: '选择下一环节',
					width: 550,
					height: 400,
					content: htmls+"<p style='text-align:center;padding:2em 0;'><input type='button' class='l-button-warp l-button' value='确定' onclick='readyTurnback();'/><input type='button' class='l-button' value='取消' onclick='window[\"atcwindow\"].close();'/></p></form>",
				});
			}else{
				$.ligerDialog.error("操作失败<br/>"+resp1.msg);
			}
			$("body").unmask();
		});
	}
	
	function readyTurnback(){
		var remark = $("#turnbackNote").val();
		var aid = $("#actidc :radio:checked").val();
		if(aid==null || aid=="" || remark==""){
			$.ligerDialog.error("请填写备注并选择下一环节！");
			return;
		}
		$.post("bpm/opinion/save.do",{
			serviceId : "${serviceId}",
			activityId : "${activityId}",
			opinion : remark,
			result : "退回"
		},function(resp){
			if(resp.success){
				window['atcwindow'].close();
				doturnback(aid);
			}
			else $.ligerDialog.error("保存意见失败，请稍后再试！");
		},"json");
	}
	
	function doturnback(aid){
		$("body").mask("正在处理，请稍后！");
		$.post('demo/bpm/bussiness/turnback.do',{
				activityId:"${activityId}",
				dataBus:'{"<%=FlowEngine.DATA_BUS_NEXT_ACTIVITY_ID%>":"' + aid + '"}'
			},function(resp){
				if(resp.success){
					top.$.notice("操作成功！",3);
					if(api.data.Qm)api.data.Qm.refreshGrid();
					if(api.data.window.Qm)api.data.window.Qm.refreshGrid();
					api.close();
				}else{
					$.ligerDialog.error("操作失败<br/>"+resp.msg);
				}
				$("body").unmask();
			},"json");
	}
	
	function readySubmit(){
		var signResult = $("#sign_result").ligerGetRadioGroupManager().getValue();
		if(!signResult){
			$.ligerDialog.alert("请选择您的审批意见！");
			return;
		}
		// 保存意见
		$.post("bpm/opinion/save.do",{
			serviceId : "${serviceId}",
			activityId : "${activityId}",
			opinion : signResult==1?"同意":"不同意",
			result : signResult==1?"同意":"不同意"
		},function(resp){
			if(resp.success)submitApp(signResult);
			else $.ligerDialog.error("保存意见失败，请稍后再试！");
		},"json");
	}
	
	function submitApp(signResult){
		$("body").mask("正在处理，请稍后！");
		$.getJSON("demo/bpm/bussiness/submit.do",{
			activityId : "${activityId}",
			dataBus : "",
			signature : signResult
		},function(resp){
			if(resp.success){
				top.$.notice("操作成功！",3);
				if(api.data.Qm) api.data.Qm.refreshGrid();
				if(api.data.window.Qm) api.data.window.Qm.refreshGrid();
				api.close();
			}else{
				$.ligerDialog.error(resp.msg);
				$("body").unmask();
			}
		});
	}
</script>
</head>
<body>
	<form id="form2" action="" getAction="demo/bpm/bussiness/detail.do?id=${serviceId}">
		<fieldset class="l-fieldset">
			<legend class="l-legend"><span>申请信息</span></legend>
			<jsp:include page="leave_detail_form.jsp" />
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend"><span>审批记录</span></legend>
			<div id="app_records"></div>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend"><span>审批</span></legend>
			<table class="l-detail-table" cellpadding="3">
				<td class="l-t-td-left">审批意见：</td>
				<td class="l-t-td-right">
					<input type="radio" name="sign" id="sign_result" ltype="radioGroup" ligerui="{data:[{id:'1',text:'同意'},{id:'0',text:'不同意'}]}" />
				</td>
			</table>
		</fieldset>
	</form>
</body>
</html>
