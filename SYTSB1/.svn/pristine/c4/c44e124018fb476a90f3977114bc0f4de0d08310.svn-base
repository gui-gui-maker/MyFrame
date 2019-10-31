<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html;charset=UTF-8"  %>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
String nowTime=""; 
nowTime = dateFormat.format(new Date()); %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus = "${param.pageStatus}">
	<title></title>
	<%@include file="/k/kui-base-form.jsp" %>
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
	<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
	<link rel="Stylesheet" href="app/office/css/jquery.autocomplete.css" />
	<script type="text/javascript">
	var op_type = "${param.op_type}";
	var rest_money = "${param.rest_money}";
	var tbar="";
	var TKJJ=rest_money;
	$(function(){
		if(op_type == "add"){
			$("#rearAmount").val(rest_money);
			$.ajax({
	             url: "cw/bank/detail.do?id=${param.id}",
	             type: "GET",
	             datatype: "json",
	             contentType: "application/json; charset=utf-8",
	             success: function (data) {
	                 if (data.success) {
	                	 $("#remark").val(data.data.remrk);
	                 }
	             }
	         });
			tbar=[{text:'保存备注',id:'saveRemark',icon:'save',click: saveRemark},
				{text:'提交申请',id:'up',icon:'save',click: saveAdd},
				{text:'关闭',id:'close',icon:'cancel',click:function(){api.close();}}];
		}else if(op_type == "check"){
			tbar=[{text: '通过',id: 'pass',icon: 'submit',click: pass},
				{text: '不通过',id: 'no_pass',icon: 'del',click: no_pass},
				{text: '关闭',id: 'close',icon: 'cancel',click: function(){api.close();}}];
		}else if(op_type == "modify"){
			tbar=[{text: '保存',id: 'save',icon: 'save',click: save},
				{text: '关闭',id: 'close',icon: 'cancel',click: function(){api.close();}}];
		}else if(op_type == "detail"){
			tbar=[{text: '关闭',id: 'close',icon: 'cancel',click: function(){api.close();}}];
		}else if(op_type == "confirm" || op_type == "revokeApply" || op_type == "revokeChecked" ){
			tbar=[{text: '确定',id: 'confirm',icon: 'ok',click: confirm},
				{text: '关闭',id: 'close',icon: 'cancel',click: function(){api.close();}}];
		}
	     $("#form1").initForm({
		 		 showToolbar: true,
	             toolbarPosition: "bottom",
	             toolbar: tbar,
	             success: function (response) {
					if(response.success){
						top.$.notice("保存成功！",3);
						api.data.window.Qm.refreshGrid();
						api.close();
					}else{
						$.ligerDialog.error("操作失败！<br/>" + response.msg);
					}
				}
	     });
	 });
	function pass(){
		$("body").mask("正在保存......");
		$.ajax({
			url: "bank/fefund/checkBankFefund.do?id=${param.fefund_id}&result=1",
			type: "POST",
			datatype: "json",
			contentType: "application/json; charset=utf-8",
			success: function (resp) {
				$("body").unmask();
				if (resp.success) {
					top.$.dialog.notice(resp.msg);
					api.data.window.Qm.refreshGrid();
					api.close();
				}else{
					$.ligerDialog.error(resp.msg);
					api.data.window.Qm.refreshGrid();
				}
			},
			error: function (resp) {
				$("body").unmask();
				$.ligerDialog.error(resp.msg);
			}
		});   
	}
	function no_pass(){
		$("body").mask("正在保存......");
		$.ajax({
			url: "bank/fefund/checkBankFefund.do?id=${param.fefund_id}&result=0",
			type: "POST",
			datatype: "json",
			contentType: "application/json; charset=utf-8",
			success: function (resp) {
				$("body").unmask();
				if (resp.success) {
					top.$.dialog.notice(resp.msg);
					api.data.window.Qm.refreshGrid();
					api.close();
				}else{
					$.ligerDialog.error(resp.msg);
					api.data.window.Qm.refreshGrid();
				}
			},
			error: function (resp) {
				$("body").unmask();
				$.ligerDialog.error(resp.msg);
			}
		});    
	}
	function confirm(){
		var url="";
		if("revokeApply"==op_type){
			url = "bank/fefund/revokeApply.do?id=${param.fefund_id}";
		}else if("revokeChecked"==op_type){
			url = "bank/fefund/revokeChecked.do?id=${param.fefund_id}";
		}else if("confirm"==op_type){
			url = "bank/fefund/confirm.do?id=${param.fefund_id}";
		}
		if(url != "" && url != null && url != "undefined"){
			$("body").mask("正在保存......");
			$.ajax({
				url: url,
				type: "POST",
				datatype: "json",
				contentType: "application/json; charset=utf-8",
				success: function (resp) {
					$("body").unmask();
					if (resp.success) {
						top.$.dialog.notice(resp.msg);
						api.data.window.Qm.refreshGrid();
						api.close();
					}else{
						$.ligerDialog.error(resp.msg);
						api.data.window.Qm.refreshGrid();
					}
				},
				error: function (resp) {
					$("body").unmask();
					$.ligerDialog.error(resp.msg);
				}
			});
		}  
	}
	 function choosePerson(){
		    top.$.dialog({
		        width: 800,
		        height: 450,
		        lock: true,
		        parent: api,
		        title: "选择人员",
		        content: 'url:app/common/person_choose.jsp',
		        cancel: true,
		        ok: function(){
		            var p = this.iframe.contentWindow.getSelectedPerson();
		            if(!p){
		                top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
		                return false;
		            }
		            $("#fefundId").val(p.id);
		            $("#fefundName").val(p.name);
		        }
		    });
		}
	function saveAdd(){
		var obj=$("#form1").validate().form();
		if(obj){
			var fefundMoney=$("#fefundMoney").val();
			if(!(Number(fefundMoney) <= Number(TKJJ) ||  Number(TKJJ) == 0 )){
				$("#rearAmount").val(rest_money);
				$("#fefundMoney").val("");
				$.ligerDialog.warn("退款金额不能大于剩余金额");
				return;
			}
			if(op_type="add"){
				var FkId = $("#fkBankDetailId").val();
				$.get("bank/fefund/queryBankFefund.do?FkId="+FkId,function(res){
					if(res.success){
						var listData = res.list;
						if(listData !=null && listData.length>0){
							var fefundNameTemp;
							for(var i=0;i<listData.length;i++){
								if(fefundNameTemp!="" && fefundNameTemp != null){
									if(fefundNameTemp.indexOf(listData[i].fefundName)==-1){
										fefundNameTemp+=","+listData[i].fefundName;
									}
								}else{
									fefundNameTemp=listData[i].fefundName;
								}
							}
							$.ligerDialog.error('提示：此账户下有<font color=red >'+fefundNameTemp+'</font>的退款申请未处理，请处理完毕后再提交申请！');
						}else{
							save();
						}
					}else{
						$.ligerDialog.error('提示：' + res.data);
					}
				})
			}else if(op_type="modify"){
				save();
			}
		}else{
			return;
		}    
	}
	function save(){
		var formData = $("#form1").getValues();
		delete formData["remark"]; //保存时去掉备注
		var jsonObj = $.ligerui.toJSON(formData);
		var FkId = $("#fkBankDetailId").val();
		$("body").mask("正在保存......");
		$.ajax({
			url: "bank/fefund/saveBank.do?FkId="+FkId+"&rest_money="+rest_money+"&pageStatus=${param.pageStatus}",
			type: "POST",
			datatype: "json",
			contentType: "application/json; charset=utf-8",
			data: $.ligerui.toJSON(formData),
			success: function (data, stats) {
				$("body").unmask();
				if (data.success) {
					top.$.dialog.notice({content:'保存成功！'});
					api.data.window.Qm.refreshGrid();
					api.close();
				}else{
					$.ligerDialog.error('提示：' + data.msg);
					api.data.window.Qm.refreshGrid();
				}
			},
			error: function (data,stats) {
				$("body").unmask();
				$.ligerDialog.error('提示：' + data.msg);
			}
		});   
	}
	 function saveRemark(){
	     var remark = $("#remark").val();
	     var FkId = $("#fkBankDetailId").val();
	     if(remark=="" || remark=="null" || remark ==null ||remark == "undefined"){
	    	 $.ligerDialog.confirm("备注内容为空，确定保存吗？", function(yes) {
	 			if (yes) {
	 				saveRemarkOption(FkId,remark);
	 			}
	 		});
	     }else{
	    	 saveRemarkOption(FkId,remark);
	     }
	 }
	 function saveRemarkOption(FkId,remark){
	     $("body").mask("正在保存......");
         $.ajax({
            url: "bank/fefund/saveRemark.do?FkId="+FkId+"&remark="+encodeURI(encodeURI(remark)),
            type: "POST",
            datatype: "json",
            contentType: "application/json; charset=utf-8",
            success: function (data, stats) {
                $("body").unmask();
                if (data.success) {
                    top.$.dialog.notice({content:'保存成功！'});
                    api.data.window.Qm.refreshGrid();
                }else{
                    $.ligerDialog.error('提示：' + data.msg);
                }
            },
            error: function (data,stats) {
                $("body").unmask();
                $.ligerDialog.error('提示：' + data.msg);
            }
        });
	 }
	 
	 function refund(){
	    var fefundMoney=$("#fefundMoney").val();
	    var total = Number(TKJJ) - Number(fefundMoney);
	    if (Number(fefundMoney) <= Number(TKJJ) ||  Number(TKJJ) == 0 ) {
	    	if(Number(TKJJ) >= 0){
		  	 $("#rearAmount").val(total);
		  	 if(fefundMoney=="" ||fefundMoney==null){
		  	   $("#rearAmount").val(rest_money);
		  	 }
	      }
	    }else{
			$("#rearAmount").val(rest_money);
			$("#fefundMoney").val("");
			 $.ligerDialog.warn("退款金额不能大于剩余金额");
		 	 return;
	    }
	      
	 }
	 </script>
</head>
<body>
	<form  id="form1" action="bank/fefund/saveBank.do" getAction="bank/fefund/detail.do?id=${param.fefund_id}">
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="fkBankDetailId" name="fkBankDetailId" value="${param.id}" />
		<input type="hidden" id="fefundId" name="fefundId" value="<%=user.getId()%>"/>
		<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" id="">
			<tr>
				<td class="l-t-td-left">单位名称</td>
				<td class="l-t-td-right"><input id="unitName" name="unitName" type="text" ltype="text" validate="{required:true}" /></td>
				<td class="l-t-td-left">退款人</td>
				<td class="l-t-td-right"><input id="fefundName" name="fefundName" type="text" ltype="text"   readonly="readonly"   validate="{required:true}" onclick="choosePerson();" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}" value="<%=user.getName() %>"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">退款金额</td>
				<td class="l-t-td-right"><input id="fefundMoney" name="fefundMoney" type="text" ltype="text" validate="{required:true}" onchange="refund();"/></td>
				<td class="l-t-td-left">剩余金额</td>
				<td class="l-t-td-right"><input id="rearAmount" name="rearAmount" type="text" ltype="text" readonly="readonly" /></td>
				
			</tr>
			<tr>
				<td class="l-t-td-left">退款日期</td>
				<td class="l-t-td-right"><input id="fefundDate" name="fefundDate" type="text" ltype="date" validate="{required:false}" 
        			ligerui="{initValue:'',format:'yyyy-MM-dd'}"  readonly="readonly" value="<%=nowTime%>"/>
        		</td>
			</tr>
			<tr>
				<td class="l-t-td-left">退款原因</td>
				<td class="l-t-td-right" colspan="3"><textarea name="fefundReason" id="fefundReason" rows="5" cols="25" class="l-textarea" validate="{maxlength:2000}"></textarea></td>
			</tr>
			<c:if test='${param.op_type=="add" }'>
				<tr>
					<td class="l-t-td-left">备注</td>
					<td class="l-t-td-right" colspan="3"><textarea name="remark" id="remark" rows="5" cols="25" class="l-textarea" validate="{maxlength:2000}"></textarea></td>
				</tr>
			</c:if>
		</table>
	</form>
</body>
</html>