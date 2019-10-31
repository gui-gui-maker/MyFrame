<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
			$("#form1").initForm({    //参数设置示例
				success: function (response) {
					if(response.success){
						top.$.notice("保存成功！",3);
						api.data.window.Qm.refreshGrid();
						api.close();
					}
					else{
						$.ligerDialog.error("操作失败！<br/>" + response.msg);
					}
				}
			,getSuccess:function(res){
				//alert(JSON.stringify(res));
				}
			
			});
			if(${param.res}==1){
			/* 	$("#form1").setValues("rbac/employee/detail.do?id=${param.id}",function(res){	
				})  */
				$("#lookAddress").ligerRadioGroup().setValue("1");
				$("#lookMailingAddress").ligerRadioGroup().setValue("1");
				$("#lookHomeTel").ligerRadioGroup().setValue("1");
				$("#lookMobileTel").ligerRadioGroup().setValue("1");
				$("#lookVTel").ligerRadioGroup().setValue("1");
				$("#lookEmergency").ligerRadioGroup().setValue("1");
				$("#fkEmployeeId").val("${param.id}");
			 	$("#form1").attr('action','rbac/pubAddress/saves.do'); 
			}
		/* 	if(${param.res}==2){
		 	 $("#form1").append("<input type='hidden' id='id' name='id'>");
			} */
			
		});
    </script>
</head>
<body>
<form id="form1" action="rbac/pubAddress/saves.do" getAction="<c:if test='${param.res==1}'>rbac/employee/detail.do?id=${param.id}</c:if><c:if test='${param.res==2}'>rbac/pubAddress/detail.do?id=${param.id}</c:if>">
      <c:if test="${param.res==2}"><input type="hidden" id="id" name="id"></c:if>
    <table cellpadding="3" class="l-detail-table">
      <tr>
	      <td class="l-t-td-left">手机：</td>
            <td class="l-t-td-right"><input name="mobileTel" type="text" ltype="text"   ligerui="{disabled:true}"/></td>
         <td class="l-t-td-left">是否显示手机号码：</td>
         <td class="l-t-td-right">
                    <input type="radio" id="lookMobileTel" name="lookMobileTel" ltype="radioGroup" validate="{required:true}"
											ligerui="{data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/>
	    </td>
      </tr>
          <tr>
        <td class="l-t-td-left">V网短号：</td>
            <td class="l-t-td-right"><input name="vTel" type="text" ltype="text"   ligerui="{disabled:true}"/></td>
         <td class="l-t-td-left">是否显示 V网短号：</td>
         <td class="l-t-td-right"><input type="radio" id="lookVTel" name="lookVTel" ltype="radioGroup" validate="{required:true}"
         	ligerui="{data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/></td> 
         	 </tr>
        <tr>
          <td class="l-t-td-left">家庭电话：</td>
            <td class="l-t-td-right"><input name="homeTel" type="text" ltype="text"  ligerui="{disabled:true}"/></td>
         <td class="l-t-td-left">是否显示家庭电话：</td>
         <td class="l-t-td-right">
                    <input type="radio" id="lookHomeTel" name="lookHomeTel" ltype="radioGroup" validate="{required:true}"
											ligerui="{data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/>
	    </td>
	    </tr>
   
        <tr>
       <td class="l-t-td-left">紧急联系人电话：</td>
            <td class="l-t-td-right"><input name="emcTel" type="text" ltype="text" ligerui="{disabled:true}" /></td>
         <td class="l-t-td-left">是否显示紧急信息：</td>
         <td class="l-t-td-right"><input type="radio" id="lookEmergency" name="lookEmergency" ltype="radioGroup" validate="{required:true}"	
         ligerui="{data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/></td> 
      </tr>
      
      <tr>
         <td class="l-t-td-left">地址：</td>
         <td class="l-t-td-right" colspan="3"><input  name="homePlace" type="text" ltype="text"    ligerui="{disabled:true}"  /></td> 
     </tr>
     
     <tr>
         <td class="l-t-td-left">是否显示地址：</td>
         <td class="l-t-td-right">
         <input type="radio" id="lookAddress" name="lookAddress" ltype="radioGroup" validate="{required:true}"
											ligerui="{data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/>
		</td>
      </tr>
      
      <tr>
            <td class="l-t-td-left">通信地址：</td>
            <td class="l-t-td-right" colspan="3"><input name="currentPlace" type="text" ltype="text"   ligerui="{disabled:true}" /></td>
      </tr>
      
      <tr>
         <td class="l-t-td-left">是否显示通信地址：</td>    
         <td class="l-t-td-right">
           <input type="radio" id="lookMailingAddress" name="lookMailingAddress" ltype="radioGroup" validate="{required:true}"
											ligerui="{data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/>
	    </td>
      </tr>
     <input type="hidden"  id="myType" name="myType" value="${param.myType}">
     <input type="hidden"  id="name" name="name" >
    <!--  <input type="hidden"  id="homePlace" name="homePlace" > -->
    <!--  <input type="hidden"  id="currentPlace" name="currentPlace" > -->
     <input type="hidden"  id="postCode" name="postCode" >
     <input type="hidden"  id="officeTel" name="officeTel" >
     <input type="hidden"  id="faxes" name="faxes" >
   <!--   <input type="hidden"  id="homeTel" name="homeTel" > -->
    <!--  <input type="hidden"  id="mobileTel" name="mobileTel" > -->
   <!--   <input type="hidden"  id="vTel" name="vTel" > -->
     <input type="hidden"  id="emcPerson" name="emcPerson" >
    <!--  <input type="hidden"  id="emcTel" name="emcTel" > -->
     <input type="hidden"  id="emcRelate" name="emcRelate" >
     <input type="hidden"  id="qq" name="qq" >
     <input type="hidden"  id="email" name="email" >
     <input type="hidden"  id="msn" name="msn" >
     <input type="hidden"  id="weixin" name="weixin" >
     <input type="hidden"  id="weibo" name="weibo" >
     <input type="hidden"  id="blogs" name="blogs" >
     <input type="hidden"  id="remark" name="remark" >
     <input type="hidden"  id="fkEmployeeId" name="fkEmployeeId" >
    </table>
</form>
</body>
</html>
