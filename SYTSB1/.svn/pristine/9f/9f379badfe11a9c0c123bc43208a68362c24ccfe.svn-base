<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.util.Arrays"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>报告领取</title>
<%@ include file="/k/kui-base-form.jsp"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="app/common/js/idCard.js"></script>
<script type="text/javascript" src="app/common/js/windowprint.js"></script>
<%
	String pageStatus = request.getParameter("status");
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userid=user.getId();
%>
<script type="text/javascript">
	var g_item_num = 0;
	var g_index = 0;
	var pageStatus = "${param.status}";
	var userid="<%=userid%>";
	var timer="";
	var reportSn=api.data.report_sn;
	var report_sn="";
	var str="123";//以W开头的报告书编号字符串组合
	var strW="";
	for(var i=0;i<reportSn.split(",").length;i++){
        var reportSnNum=reportSn.split(",")[i];
		var reSn=reportSnNum.substring(0,5)+reportSnNum.substring(7, reportSnNum.length);
        if(i==(reportSn.split(",").length-1)){
			report_sn=report_sn+reSn;
		}else{
			report_sn=report_sn+reSn+",";
		}
    }
	$(function() {
		$("#report_sn").val(api.data.report_sn);
		$("#linkmode").val(api.data.reportDrawSign.pulldown_tel);
		$("#report_sn1").val(api.data.report_sn1);
		document.getElementById("signImg").src = "data:image/png;base64,"+api.data.image;
		$("#reportDrawSignId").val(api.data.reportDrawSign.id);
		$("#imgVersion").val(api.data.reportDrawSign.imgVersion);
		
		$("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	    			document.getElementById("linkmode").readOnly=true;
	            	api.data.qm.refreshGrid();
	    			top.$.notice("保存成功！",3);
					api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, 
			getSuccess: function (response){
				
			}, 
			afterParse:function(){
				$("#cid").ligerGetComboBoxManager().setValue(api.data.cid);
			},
			toolbar: [
	      		{
	      			text: "保存", icon: "save", click: save
	      		},
	      		{	id: 'againSign',
					text : '重新推送',
					icon : 'issued',
					click : againSign
				},
				{
					text: "关闭", icon: "cancel", click: function(){
						api.data.qm.refreshGrid();
						api.close();
					}
				}
			], 
			toolbarPosition: "bottom"
		});	
	   /*  $('#formObj').attr('action','department/basic/flow_saveDrow.do?status=${param.status}&acc_id='+api.data.acc_id+'&flow_num='+api.data.flow_num);
		$("#inspection_info_id").val(api.data.id);
		$("#area_name").val(api.data.area_name);
		$("#job_unit").val(api.data.report_com_name);
		//输入领取人姓名弹出联系人手机号选项
		$('#pulldown_op').autocomplete("report/draw/getContactsInfo.do?com_name="+encodeURI(api.data.report_com_name), {
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
			//表单验证
	    	if ($("#formObj").validate().form()) {
    			$.ligerDialog.confirm('是否保存，保存后将不能修改签名！', function (yes) {
					if(yes){
    						//表单提交
		    				var formdata = $("#formObj").getValues();
		    				var sign = api.data.reportDrawSign;
    						//var ids = api.data.id.split(",");
    						//var acci = api.data.acc_id.split(",");
    						//g_item_num = ids.length;
    						//g_index = 0;
    						var draw_sn = new Date().getTime();
    						var fpath = sign.signFile;
    						var evaluate = sign.evaluate;
    						var linkmode = formdata.linkmode;
    						//$.each(ids,function(i){
    							//var id = ids[i];
    							$("body").mask("正在保存数据，请稍后！");
    							$.post(
	    								"department/basic/saveAddNew.do",
	    								{
	    									"acc_id" : api.data.acc_id,
	    									"flow_num" : api.data.flow_num,
	    									"info_id" : api.data.id,
	    									"draw_sn" : draw_sn,
	    									"base64ImagePath":fpath,
	    									"evaluate":evaluate,
	    									"linkmode":linkmode
	    								},
	    								function(res){
	    									 $("body").unmask();
	    									/* if(res.success){
	    										g_index ++;
	    									}else{
	    										g_index ++;
	    										$.ligerDialog.error(res.msg);
	    									}
    										if(g_index == g_item_num){
    											$.ligerDialog.alert("保存完成!");
    										} */
	    									//if(res.success){
	    										top.$.notice("保存完成!");
	    										api.data.qm.refreshGrid();
	    										api.close();
	    									/* }else {
	    						           		$.ligerDialog.error('保存失败！<br/>' + res.msg);
	    						      		} */
	    								}
   								);
    						//});
					}
				});
	    	}
	}
	
	function closewindow(){
		api.close();
	}
	
	function checkBasic(){
		var idNo = $("#idcard").val();
		if("" != idNo){
			if(!validateID(idNo)){
	        	$.ligerDialog.alert("您输入的身份证号码无效，请检查！");
	        	$("#idcard").focus();
	        	return false;
	        }
		} 
		return true;
	}
	
	function checkID(el) {
	        if(!validateID(el.value)){
	        	$.ligerDialog.alert("您输入的身份证号码无效，请检查！");
	        	$("#idcard").focus();
	        }
	}
	
	//验证身份证号码是否正确
	function validateID(value){
			var checkFlag = new clsIDCard(value);
	        if(checkFlag.IsValid()){
	        	//checkFlag.GetBirthDate()	此方法返回的年月日中月份不包含'0'，例如：1988-7-21
	        	//showBirthdayAndSex(value);	//根据身份证号码自动提取出生年月、性别
	        	return true;
	        }else{
	        	return false;
	        }	
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
	
	function againSign(){
		$.ajax({
			type:'POST',
			url:'department/basic/isNotSaveReportDrawSign.do?fkInspectionInfoId='+api.data.id,//验证报告是否领取
			data:{
				
			},
    		dataType:'JSON',
			success:function(ress){
				if(ress.success==true){
				//重新推送不再显示财务相关签字信息
					$.ajax({
						type:'POST',
						url:'reportDrawSignAction/pushSignature.do',
						data:{
							//"pulldown_op" : $("#pulldown_op").val(),
							"linkmode" : $("#linkmode").val(),
							"cid" : $("#cid").ligerComboBox().getValue(),
							"reportDrawSignId" : $("#reportDrawSignId").val()
						},
			    		dataType:'JSON',
						success:function(ress){
							if(ress){
								top.$.notice("推送成功！");
								timer = window.setInterval('signatureListener("'+$("#reportDrawSignId").val()+'")', 3000); 
							}
						}
					});
				}else{
					$.ligerDialog.error(ress.msg);
				}
			}
		});
		
	}
	function signatureListener(id){
		$.post(
				"reportDrawSignAction/detail.do",
				{id:id},
				function(res){
	  				if(res.data && res.data.imgVersion && $("#imgVersion").val()!= res.data.imgVersion ){
						//停止轮询
		  				window.clearInterval(timer);
			  			//签名
			  			$("table tr td img").attr("src",res.data.signRelativeFile);
			  			$("#linkmode").val(res.data.pulldown_tel);
			  			$("#imgVersion").val(res.data.imgVersion);
	  				}
	  			}
		);
	}
	
	function reportPrint(){
		var beans=$("#beans").val();
		var op_user_name=$("#op_user_name").val();
		//var pulldown_op=$("#pulldown_op").val();
		var linkmode=$("#linkmode").val();
		//var report_sn=$("#report_sn").val();
		var report_sn=api.data.report_sn;
		var cid=$("#cid").ligerComboBox().getValue();
		$.ajax({
			type:'POST',
			url:'department/basic/queryReportDrawCert.do?fkInspectionInfoId='+api.data.id,//验证报告是否领取
			data:{},
    		dataType:'JSON',
			success:function(ress){
				if(ress.success==true){
					var type=ress.data;
					if(type==''||type==null){
						//$("body").mask("正在打印领取记录！");
						$.post("department/basic/queryReportDoc.do",{fkInspectionInfoId:api.data.id},function(res){
							if(res.success){
					  			//签名
					  			//$("table tr td image").attr("src",res.image);
					  			$.getJSON("department/basic/reportDrawCert.do?fkInspectionInfoId="+api.data.id, function(resp){
					  				if(resp.success){
					  					//停止轮询
						  				//window.clearInterval(timer1);
										top.$.dialog({
											width : $(top).width(),
											height :  $(top).height()-40,
											lock : true,
											title : "打印领取记录",
											parent: api,
											content : 'url:app/flow/newDocEditor.jsp?status=modify&isPrint=2',	
											data: {window: window, bean: resp.printContent, op_user_name: resp.op_user_name,image:res.image}
										}).max();
										api.data.window.submitAction();
				            			api.close();
									}
								})
					  		}
					  	});
					}
				}else{
					$.ligerDialog.error(ress.msg);
				}
			}
		});
	}
	
	
	function printReport(){
		var beans=$("#beans").val();
		var op_user_name=$("#op_user_name").val();
		//var pulldown_op=$("#pulldown_op").val();
		var linkmode=$("#linkmode").val();
		var report_sn=api.data.report_sn;
		var cid=$("#cid").ligerComboBox().getValue();
		var report_sn1=api.data.report_sn1;
		$.ajax({
			type:'POST',
			url:'department/basic/queryReportDrawCert.do?fkInspectionInfoId='+api.data.id,//验证报告是否领取
			data:{},
    		dataType:'JSON',
			success:function(ress){
				if(ress.success==true){
					var type=ress.data;
					if(type==''||type==null){
						//$("body").mask("正在打印领取记录！");
						$.post("department/basic/queryReportDoc.do",{fkInspectionInfoId:api.data.id},function(res){
							if(res.success){
					  			//签名
					  			//$("table tr td image").attr("src",res.image);
					  			$.getJSON("department/basic/reportDrawCert.do?fkInspectionInfoId="+api.data.id, function(resp){
					  				if(resp.success){
										top.$.dialog({
											width : $(top).width(),
											height :  $(top).height()-40,
											lock : true,
											title : "打印领取记录",
											parent: api,
											content : 'url:app/flow/newDocEditor.jsp?status=modify&isPrint=2',	
											data: {window: window, bean: resp.printContent, op_user_name: resp.op_user_name,image:res.image,report_sn1:report_sn1}
										}).max();
									}else{
										$.ligerDialog.error(resp.message);
									}
								})
					  		}
					  	});
					}
				}else{
					$.ligerDialog.error(ress.msg);
				}
			}
		});
	}
	
	function selectCourse(){
		var status = "add";
		top.$.dialog({
			width: 600,
			height: 400,
			lock:true,
			title:"添加设备",
			content: 'url:app/flow/addPanelComputer_detail.jsp',
			/* ok:function(w){
				var datas = this.iframe.contentWindow.getSelectResult();
					return true;
				},
			cancel:true */
		});
   	}
	
	//刷新码表缓存
	function refreshCache(){
		$("body").mask("正在刷新...");
		 $.getJSON("pub/codetable/refreshCache.do",function(resp){
         	if(resp.success){
         		top.$.notice('刷新缓存成功！',4)
         		treeManager.clear();
         		$.getJSON("pub/codetable/codetableTree.do",function(response){
         			reCreateTree(response);
         		});
         	}
         	else
         		$.ligerDialog.error('刷新缓存失败！请稍后再试。');
			$("body").unmask();
    	});
	}

	
	
</script>
</head>
<body <%if("add".equals(pageStatus)){%>onload="initInfo();"<%}%>>
	<form name="formObj" id="formObj" method="post" action="">
		<input id="reportDrawSignId" name="reportDrawSignId" type="hidden" value=""/>
		<input id="imgVersion" name="imgVersion" type="hidden" value=""/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>报告领取</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">联系电话：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="linkmode" id="linkmode" type="text" ltype="text"  validate="{required:true,maxlength:200}"/>
					</td>		
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
				<tr>
					<td class="l-t-td-left">签名：</td>
					<td class="l-t-td-right" colspan="3">
              			<img id="signImg" style="width:220px;vertical-align: text-bottom;" src="" />
	              	</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>