<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>详情页面</title>
<%@include file="/k/kui-base-form.jsp" %>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="k/config.js"></script>
<script type="text/javascript" src="k/kui/frame/core.js"></script>
<script type="text/javascript" src="k/kui/frame/main.js"></script>
<%
	String pageStatus = request.getParameter("status");
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userid=user.getId();
%>
<script type="text/javascript">
	var pageStatus = "${param.status}";	
	var fkInspectionInfoId=api.data.id;
	var acc_id=api.data.acc_id;
	var flow_num=api.data.flow_num;
	var report_com_name=api.data.report_com_name;
	var area_name=api.data.area_name;
	var report_sn=api.data.report_sn;
	//定时请求 ，这里设置3秒循环
	var timer1="";
	var userid="<%=userid%>";
	$(function () {
		$("#report_sn").val(report_sn);
		$("#formObj").initForm({    //参数设置示例
			toolbar : [
				{
					text : '签名推送',
					icon : 'save',
					click : save
				},{
					text : '关闭',
					icon : 'cancel',
					click : close
				}   
			],
			afterParse:function(){
				//查询登录用户绑定设备
				$.ajax({
					type:'POST',
					url:'payment/order/lockUserCid/queryLockUser.do?userId='+userid,
					data:{},
		    		dataType:'JSON',
					success:function(ress){
						if(ress.data && ress.data.cid && ress.data.cid!=null && ress.data.cid!="")
						{
							$("#cid").ligerGetComboBoxManager().setValue(ress.data.cid);
						}
					}
				});
				//报告书编号
				$.post("payment/payInfo/selectReportSn.do",{"reportSn":api.data.report_sn},function(resp){
					$("#report_sn1").val(resp.data);
				});
			}
		});
		//输入领取人姓名弹出联系人手机号选项
		/* $('#pulldown_op').autocomplete("report/draw/getContactsInfo.do?com_name="+encodeURI(api.data.report_com_name), {
            max: 12,    //列表里的条目数
            minChars: 1,    //自动完成激活之前填入的最小字符
            width: 200,     //提示的宽度，溢出隐藏
            scrollHeight: 300,   //提示的高度，溢出显示滚动条
            scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
            matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
            autoFill: false,    //自动填充
            formatItem: function(row, i, max) {
                return row.pulldown_ops + '   ' + row.linkmodes;
            },
            formatMatch: function(row, i, max) {
                return row.pulldown_ops + row.linkmodes;
            },
            formatResult: function(row) {
                return row.pulldown_ops;
            }
        }).result(function(event, row, formatted) {
        	document.getElementById("linkmode").value = row.linkmodes
        }); */
		
		
	});
	
	function save(){
		$.post(
				"reportDrawSignAction/pushSignature.do",
				{
					"fkInspectionInfoId":fkInspectionInfoId,
					"report_sn":api.data.report_sn,
					"format_report_sn":$("#report_sn1").val(),
					"invoice_no":api.data.invoice_no,
					"linkmode":$("#linkmode").val(),
					"cid":$("#cid").ligerComboBox().getValue()
				},
				function(res){
					if(res.success){
						top.$.notice("推送成功！");
						$("#imgVersion").val(res.data.imgVersion);
						timer1 = window.setInterval("signatureListener('"+res.data.id+"')", 3000); 
					}	
				}
		);
	}
	 //签名后跳转页面
	function signatureListener(id){
		$.ajax({
			type:'POST',
			url:'reportDrawSignAction/detailAndGetImg.do',
			data:{"id":id},
			dataType:'JSON',
			success:function(res){
				if(res.data.imgVersion != $("#imgVersion").val()){
					//停止轮询
	  				window.clearInterval(timer1);
					top.$.dialog({
						width : 700, 
						height : 500, 
						lock : true, 
						title : "报告领取", 
						content : 'url:app/flow/report_draw_detailNew.jsp?status=add',
						parent: api,
						data : {
							"window" : window,
							"id":fkInspectionInfoId,
							"acc_id":acc_id,
							"flow_num":flow_num,
							"report_com_name":report_com_name,
							"area_name":area_name,
							"report_sn":report_sn,
							"report_sn1":$("#report_sn1").val(),
							"reportDrawSign":res.data,
							"image":res.image,
							"cid":$("#cid").ligerGetComboBoxManager().getValue(),
							"qm":api.data.window.Qm
						}
					});
					api.close();
				}
			}
		})
	} 
	
	function close(){
		api.data.window.Qm.refreshGrid();
		api.close();
	}
	
	function initInfo(){
		$.getJSON("report/draw/getComInfo.do?id="+api.data.id, function(resp){
			if(resp.success){
				$('#job_unit').val(resp.data.report_com_name);	// 工作单位
			}
		})
		$('#formObj').attr('action','department/basic/flow_saveDrow.do?status=${param.status}&acc_id='+api.data.acc_id+'&flow_num='+api.data.flow_num);
		$("#inspection_info_id").val(api.data.id);
		$("#area_name").val(api.data.area_name);
		$("#job_unit").val(api.data.report_com_name);
	}
	function selectCourse(){
		var status = "add";
		top.$.dialog({
			width: 600,
			height: 400,
			lock:true,
			title:"添加设备",
			content: 'url:app/flow/addPanelComputer_detail.jsp',
		});
   	}
</script>
</head>
<body <%if("add".equals(pageStatus)){%>onload="initInfo();"<%}%>>
<form name="formObj" id="formObj" method="post" action="">
		<input type="hidden" name="inspectionInfo.id" id="inspection_info_id" value=""/>
		<input type="hidden" name="area_name" id="area_name" value=""/>
		<input type="hidden" name="job_unit" id="job_unit" value=""/>
		<input type="hidden" name="beans" id="beans"/>
		<input type="hidden" name="op_user_name" id="op_user_name" />
		
		<input id="reportDrawSignId" name="reportDrawSignId" type="hidden" value=""/>
		<input id="imgVersion" name="imgVersion" type="hidden" value=""/>
		
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>报告领取</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<!-- <tr>
					<td class="l-t-td-left">领取人：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="pulldown_op" id="pulldown_op" type="text" ltype='text' validate="{required:true,maxlength:200}" />
					</td>
				</tr> -->
				<tr>
					<td class="l-t-td-left">联系电话：</td>
					<td class="l-t-td-right" colspan="3"><input name="linkmode" id="linkmode" type="text" ltype="text" validate="{required:true,maxlength:200}"/></td>
				</tr>
				
				<tr>
					<td class="l-t-td-left">报告书编号：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="report_sn" id="report_sn" type="hidden"  />
						<textarea name="report_sn1" id="report_sn1" rows="10" cols="25" class="l-textarea" validate="{required:true}" ></textarea>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">设备选择：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="cid" id="cid" ltype="select" validate="{required:true}"  ligerui="{value:'',data:<u:dict code="SIGN_DEVICE"/>,iconItems:[{icon:'add',click:function(){selectCourse()}}]}"/>
					</td>
				</tr>
				<!-- <tr>
					<td class="l-t-td-left">签名：</td>
					<td class="l-t-td-right" colspan="3">
              			<img style="width:220px;vertical-align: text-bottom;" src="" />
	              	</td>
				</tr> -->
			</table>
		</fieldset>
	</form>
</body>
</html>
