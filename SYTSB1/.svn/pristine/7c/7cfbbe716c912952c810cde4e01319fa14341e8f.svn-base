<%@page import="java.util.Date"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus = "${param.pageStatus}">
<title>固定资产信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<!-- 生成条形码JS导入 -->
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>

<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String userId=e.getId();
	String userid = SecurityUtil.getSecurityUser().getId();
	String users=curUser.getName();
	
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    String rkTime = sf.format(new Date());
    String sql="SELECT ID,LX_NAME FROM TJY2_CH_GOODS_TYPE WHERE STATE='1' and CREATE_ORG_ID='"+uu.getOrg().getId()+"'";
%>
<script type="text/javascript">
	var pageStatus = "${param.pageStatus}";
	var tbar="";
	$(function() {
		if(pageStatus=='detail'){
			tbar=[
	  				{text: "关闭", icon: "cancel", click: function(){api.close();}}];
		}else{
			tbar=[
                   		{text: "保存", icon: "save", click: function(){
		  				//表单验证
				    	if ($("#form1").validate().form()) {
				    		$("#form1").submit();
				    	}else{$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');}
		  				}},
	  				{text: "关闭", icon: "cancel", click: function(){api.close();}}];
		}
		
		$("#form1").initForm({
			success: function (response) {//处理成功
				console.log(response);
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
					api.data.window.Qm.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
				if (response.attachs != null && response.attachs != undefined)
					showAttachFile(response.attachs);
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
	});
	
	//金额输入规范只能输入正数
	function onlyNonNegative(obj) {
		var inputChar = event.keyCode;
		//1.判断是否有多于一个小数点
		if(inputChar==190 ) {//输入的是否为.
			var index1 = obj.value.indexOf(".") + 1;//取第一次出现.的后一个位置
			var index2 = obj.value.indexOf(".",index1);
			while(index2!=-1) {
				obj.value = obj.value.substring(0,index2);
				index2 = obj.value.indexOf(".",index1);
			}
		}
		//2.如果输入的不是.或者不是数字，替换 g:全局替换
		obj.value = obj.value.replace(/[^(\d|.)]/g,"");
	}
</script>
</head>
<body>
	<div title="供应商信息" id="formObj">
    <form id="form1" action="com/tjy2/supplier/save.do" getAction="com/tjy2/supplier/detail.do?id=${param.id}">
     <input type="hidden" name="id"/>
     <input type="hidden" name="createUserId"/>
     <input type="hidden" name="createUserName"/>
     <input type="hidden" name="createDate"/>
     <input type="hidden" name="createOrgId"/>
     <input type="hidden" name="createOrgName"/>
     <input type="hidden" name="createUnitId"/>
     <input type="hidden" name="createUnitName"/>
     
     <fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					供应商信息
				</div>
			</legend>
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       <tr>
       		<td class="l-t-td-left"></td>
       		<td class="l-t-td-right"></td>
       		<td class="l-t-td-left"></td>
       		<td class="l-t-td-right"></td>
       </tr>
       <tr>
       		<td class="l-t-td-left">供应商名称：</td>
       		<td class="l-t-td-right" colspan="3"><input name="gysmc" id="gysmc" type="text" ltype='text' validate="{required:true,maxlength:200}" /></td>
       		
       </tr>
       <tr>
       <td class="l-t-td-left">主要产品及服务</td>
        <td class="l-t-td-right"> 
        <input ltype="select" name="zycpjfw" validate="{required:true}" ligerui="{data:<u:dict code='' sql='<%=sql %>'/>}"/>
           
        </td>
       		<td class="l-t-td-left">供应商地址：</td>
       		<td class="l-t-td-right"><input name="gysdz" id="gysdz" type="text" ltype='text' validate="{maxlength:200}" /></td>
       		
       </tr>
       <tr>
       		<td class="l-t-td-left">法人代表：</td>
       		<td class="l-t-td-right"><input name="frdb" id="frdb" type="text" ltype='text'  /></td>
       		<td class="l-t-td-left">手机/固定电话：</td>
       		<td class="l-t-td-right"><input name="tel" id="tel" type="text" ltype='text'  /></td>
       </tr>
       
	  <tr> 
        <td class="l-t-td-left">邮编：</td>
        <td class="l-t-td-right"> 
        <input name="yb" id="yb" type="text" ltype='text' validate="{maxlength:200}" />
        </td>
        <td class="l-t-td-left">传真号码：</td>
        <td class="l-t-td-right"> 
        <input name="cz" id="cz" type="text" ltype='text' validate="{maxlength:200}"/>
        </td>
       </tr>
       <tr>
       		<td class="l-t-td-left">开户银行：</td>
       		<td class="l-t-td-right" colspan="3"><input name="khyh" id="khyh" type="text" ltype='text' validate="{maxlength:200}" /></td>
       </tr>
       <tr> 
        <td class="l-t-td-left">注资机构代码：</td>
        <td class="l-t-td-right"> 
        <input name="zzjgdm" id="zzjgdm" type="text" ltype='text' validate="{maxlength:200}"/>
        </td>
        <td class="l-t-td-left">税务登记证编码：</td>
        <td class="l-t-td-right"> 
        <input name="swdjzbm" id="swdjzbm" type="text" ltype='text' validate="{maxlength:200}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">注册日期：</td>
        <td class="l-t-td-right"> 
        <input name="zcrq" id="zcrq" type="text" ltype='date' />
        </td>
        <td class="l-t-td-left">营业执照有效日期：</td>
        <td class="l-t-td-right"> 
        <input name="yyzzyxrq" type="text" ltype='date'/>
        </td>
       </tr>
       <tr>
       		<td class="l-t-td-left">供应商网址：</td>
       		<td class="l-t-td-right" colspan="3"><input name="wz" id="wz" type="text" ltype='text' validate="{maxlength:200}" /></td>
       </tr>
       
       <tr> 
        <td class="l-t-td-left">业务联系人：</td>
        <td class="l-t-td-right"> 
        <input name="lxr" id="lxr" type="text" ltype='text' validate="{maxlength:200}"/>
        </td>
        <td class="l-t-td-left">手机号码：</td>
        <td class="l-t-td-right"> 
        <input name="lxrdh" id="lxrdh" type="text" ltype='text' validate=""/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left"> 固定电话：</td>
        <td class="l-t-td-right"> 
        <input name="lxrgddh" id="lxrgddh" type="text" ltype='text'/>
        </td>
        <td class="l-t-td-left"> 邮箱</td>
        <td class="l-t-td-right"> 
        <input name="yx" id="yx" type="text" ltype='text' ligerui="{format:'yyyy-MM-dd'}"/>
        </td>
       </tr>
       
       <tr>
		<td class="l-t-td-left">备注</td>
		<td class="l-t-td-right" colspan="3">
			<textarea name="bz" rows="2" cols="25" class="l-textarea" validate="{maxlength:300}"></textarea>
		</td>						
	  </tr>
      </table>
      </fieldset>
    </form> 
</div>

</body>
</html>
