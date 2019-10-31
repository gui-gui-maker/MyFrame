<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
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
String sql=" select id,GYSMC FROM TJY2_CH_SUPPLIER WHERE STATUS=1 and CREATE_ORG_ID='"+uu.getOrg().getId()+"'";

String typeSql="SELECT ID,LX_NAME FROM TJY2_CH_GOODS_TYPE WHERE STATE='1' and CREATE_ORG_ID='"+uu.getOrg().getId()+"'";
%>
<script type="text/javascript">
	var pageStatus = "${param.pageStatus}";
	var tbar="";
	$(function() {

		if(pageStatus=="detail"){
			tbar=[{text: "关闭", icon: "cancel", click: function(){api.close();}}];
 		}else{
 			tbar=[
         		{text: "保存", icon: "save", click: function () {

         			var rksl=$("#cssl").val();//入库数量
         			var kcsl=$("#sl").val();//库存数量
         			var cksl='${param.cksl}';//出库数量
         			rksl=parseInt(rksl) ;
         			kcsl=parseInt(kcsl);
         			cksl=parseInt(cksl);
        			var tj=false;
         			if(rksl <kcsl){
         				$.ligerDialog.error('提示：' + '库存数量不能大于入库数量！');
         				return false;
         			}
         			if(cksl>rksl){
         				 $.ligerDialog.confirm('入库数量小于出库数量，确定要修改？', function (yes){
         					 if(yes){
         						save();
         					 }else{
         						return;
         					 }
         				 });
         			}else if((cksl+kcsl)!=rksl){
         				 $.ligerDialog.confirm('库存数量'+kcsl+'加出库数量'+cksl+'不等于入库数量，是否确定要修改？', function (yes){
         					 if(yes){
         						save();
         					 }else{
         						return;
         					 }
         				 });
         				 
         			}else{save();}
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
				console.log(response);
				if(pageStatus=="modify"){
					$("#lx_id").ligerGetTextBoxManager().setValue(response.data.goodstype.id);
					$("#gys_id").ligerGetTextBoxManager().setValue(response.data.supplier.id);
				}
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar:tbar
    	});
	});
	
	function save(){
		//表单验证
    	if ($("#form1").validate().form()) {
	    		$("#form1").submit();
    	}else{$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');}
	}
	
</script>
</head>
<body>
	<div title="物品信息" id="formObj">
    <form id="form1" action="/goods/saveGoodsBean.do" getAction="/goods/detail.do?id=${param.id}">
     <input type="hidden" name="id"/>
     <input type="hidden" name="create_unit_name"/>
     <input type="hidden" name="create_unit_id"/>
     <input type="hidden" name="create_org_name"/>
     <input type="hidden" name="create_org_id"/>
     <input type="hidden" name="create_user_name"/>
     <input type="hidden" name="create_user_id"/>
     <input type="hidden" name="create_time"/>
     <input type="hidden" name="state"/>
     <input type="hidden" name="fk_warehousing_id"/>
     <input type="hidden" name="warehousing_num"/>
     <input type="hidden" name="rk_time"/>
     <fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					物品信息
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
       		<td class="l-t-td-left">物品名称：</td>
       		<td class="l-t-td-right" ><input name="wpmc" id="wpmc" type="text" ltype='text' validate="{maxlength:200}" /></td>
   
       		<td class="l-t-td-left">规格及型号：</td>
       		<td class="l-t-td-right" ><input name="ggjxh" id="ggjxh" type="text" ltype='text' validate="{required:true,maxlength:200}" /></td>
       </tr>
       <tr>
       		<td class="l-t-td-left">单位：</td>
       		<td class="l-t-td-right" ><input name="dw" id="dw" type="text" ltype='text' validate="{maxlength:200}" /></td>
       		<td class="l-t-td-left">单价：</td>
       		<td class="l-t-td-right" ><input name="je" id="je" type="text" ltype='text' validate="{maxlength:200}" /></td>
       </tr>
       <tr>
       		<td class="l-t-td-left">入库数量：</td>
       		<td class="l-t-td-right" ><input name="cssl" id="cssl" type="text" ltype='text' validate="{maxlength:200}" /></td>
       		<td class="l-t-td-left">库存数量：</td>
       		<td class="l-t-td-right" ><input name="sl" id="sl" type="text" ltype='text' validate="{maxlength:200}" /></td>
       </tr>
       <tr>
       		<td class="l-t-td-left">供应商：</td>
       		<td class="l-t-td-right" >
       		<input name="gysmc" id="gysmc"  type="hidden"/>
<%--        		<c:if test="${param.pageStatus=='modify' }"> --%>
<!--        		<input name="gysmc" id="gysmc"  type="text" ltype="text"  ligerUi="{disabled:true}" /> -->
<%--        		</c:if> --%>
       		<input name="supplier.id" id="gys_id" type="text" ltype="select" validate="{required:true}" ligerui="{
					initValue:'',
					readonly:true,
					onchange:function(value,text){
						$('#gysmc').val(text);
					},
					data: <u:dict sql='<%=sql%>'/>
					}"/>
       		
       		</td>
       		<td class="l-t-td-left">物品类型：</td>
       		<td class="l-t-td-right" >
       		<input  name="wplx" id="wplx" type="hidden"/>
       		<input name="goodstype.id" id="lx_id" type="text" ltype="select" validate="{required:true}" ligerui="{
					initValue:'',
					readonly:true,
					onchange:function(value,text){
						$('#wplx').val(text);
					},
					data: <u:dict sql='<%=typeSql %>'/>
					}"/>
					
					
       		</td>
      </tr>
       <tr>
		<td class="l-t-td-left">备注：</td>
		<td class="l-t-td-right" colspan="3">
			<textarea name="bz"  class="l-textarea" validate="{maxlength:100}"></textarea>
		</td>						
	  </tr>
      </table>
      </fieldset>
    </form> 
</div>

</body>
</html>