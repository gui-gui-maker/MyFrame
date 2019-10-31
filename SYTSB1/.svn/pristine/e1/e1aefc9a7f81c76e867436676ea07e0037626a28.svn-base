<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}" id="ak">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
     var myText=[];
        $(function () {
		 /* 	$.ajax({
	   			 type:"get",
	   			 dataType:"json",
	   			//async:false, //同步
	   			 data:{},
	   			 url:"demo/sysAddressType/lookAll.do",
	             success:function(data){
	                  for(var i=0;i<data.length;i++){
	                	  var myjson={"text":data[i].name+"","id":data[i].id};
	                	  myText.push(myjson);
	                  }
	   			 $("#myType").ligerTextBox().setData(myText);
	           // $("#myType").ligerTextBox().setData([{ text:'部门1', id:'1' }, { text: '部门2', id:'2' } ]);
	                }
	   		});  */ 
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
						//alert(JSON.stringify(res.data));
				/* 		if(res.data.myType==null){
						res.data.myType="0E15EC9658CFF40DE050007F020002AF"; 
							  var myjson={"text":"内部通讯录","id":"1"};
							  myText.push(myjson);
							  $("#myType").ligerTextBox().setData(myText);   
						} */
					}
			});
		/* 	if(${param.res}==1){
				$("#form1").setValues("rbac/employee/detail.do?id=${param.id}",function(res){	
				}) 
			} */
	/* 		if(${param.res}==2){
		 	 $("#form1").append("<input type='hidden' id='id' name='id' >");
			} */
			if(${param.res}==3){
				$("#form1").setValues("rbac/employee/detail.do?id=${param.id}",function(res){	
				}) 
				$("#fkEmployeeId").val("${param.id}");
			 	$("#form1").attr('action','demo/sysAddress/saves.do'); 
			}
		});
    </script>
</head>
<body>
 <form id="form1" action="rbac/pubAddress/saves.do"  getAction="<c:if test='${param.res==1}'>rbac/employee/detail.do?id=${param.id}</c:if><c:if test='${param.res==2}'>rbac/pubAddress/detail.do?id=${param.id}</c:if>"       > 
 <c:if test='${param.res==2}'><input type="hidden" id="id" name="id" ></c:if>
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">类别：</td>
            <td class="l-t-td-right"  id="myTypes">
            <input type="text" id="myType" name="myType" ltype="select" validate="{required:true}" ligerui="{
           	 value:'${param.myType}',
			 data:<u:dict sql='select id,name from  PUB_ADDRESS_TYPE where type = 1 order by sort '> </u:dict>
			}"/>
          </td>
            <td class="l-t-td-left">名称：</td>
            <td class="l-t-td-right"><input name="name" type="text" ltype="text" validate="{required:true,maxlength:50}"  /></td>
          </tr>
          <tr>
            <td class="l-t-td-left">手机：</td>
            <td class="l-t-td-right"><input name="mobileTel" type="text" ltype="text"  validate="{required:true,maxlength:11,minlength:11}"/></td>
             <td class="l-t-td-left">办公电话：</td>
            <td class="l-t-td-right"><input name="officeTel" type="text" ltype="text" validate="{maxlength:200}"   /></td>
          </tr>
          <tr>
             <td class="l-t-td-left">V网短号：</td>
            <td class="l-t-td-right"><input name="vTel" type="text" ltype="text" validate="{maxlength:8}" /></td>
             <td class="l-t-td-left">家庭电话：</td>
             <td class="l-t-td-right"><input name="homeTel" type="text" ltype="text" validate="{maxlength:20}" /></td>
          </tr>
        <tr>
            <td class="l-t-td-left">传真：</td>
            <td class="l-t-td-right"><input name="faxes" type="text" ltype="text" validate="{maxlength:100}" /></td>
             <td class="l-t-td-left">QQ：</td>
            <td class="l-t-td-right"><input name="qq" type="text" ltype="text" validate="{maxlength:20}" /></td>
        </tr>
  
         <tr>
            <td class="l-t-td-left">紧急联系人：</td>
            <td class="l-t-td-right"><input name="emcPerson" type="text" ltype="text"  validate="{maxlength:20}"/></td>
            <td class="l-t-td-left">紧急联系人电话：</td>
            <td class="l-t-td-right"><input name="emcTel" type="text" ltype="text" validate="{maxlength:20}" /></td>
        </tr>
            <tr>
            <td class="l-t-td-left">与紧急联系人关系：</td>
            <td class="l-t-td-right"><input name="emcRelate" type="text" ltype="text"  validate="{maxlength:20}"/></td>
             <td class="l-t-td-left">博客：</td>
            <td class="l-t-td-right"><input name="blogs" type="text" ltype="text" validate="{maxlength:50}" /></td>       
        </tr>
            <tr>
            <td class="l-t-td-left">邮箱：</td>
            <td class="l-t-td-right"><input name="email" type="text" ltype="text" validate="{maxlength:50,email:true}" /></td>
            <td class="l-t-td-left">MSN：</td>
            <td class="l-t-td-right"><input name="msn" type="text" ltype="text" validate="{maxlength:20}" /></td>
        </tr>
          <tr>
            <td class="l-t-td-left">微信：</td>
            <td class="l-t-td-right"><input name="weixin" type="text" ltype="text" validate="{maxlength:50}" /></td>
            <td class="l-t-td-left">微博：</td>
            <td class="l-t-td-right"><input name="weibo" type="text" ltype="text" validate="{maxlength:50}" /></td>
        </tr>
         <tr>
              <td class="l-t-td-left">邮编：</td>
            <td class="l-t-td-right"><input name="postCode" type="text" ltype="text" validate="{maxlength:10}" /></td>
            <td class="l-t-td-left">通信地址：</td>
            <td class="l-t-td-right" ><input name="currentPlace" type="text" ltype="text" validate="{maxlength:200}"   /></td>
         </tr>
         <tr>
            <td class="l-t-td-left">地址：</td>
            <td class="l-t-td-right" colspan="3"><input  name="homePlace" type="text" ltype="text"  validate="{maxlength:200}"   /></td> 
        </tr>
        <c:if test="${param.pageStatus=='detail'}">
         <tr>
            <td class="l-t-td-left">是否显示手机：</td>
            <td class="l-t-td-right">
                    <input type="radio" id="lookMobileTel" name="lookMobileTel" ltype="radioGroup" validate="{required:true}"
											ligerui="{data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/>
	        </td>
	         <td class="l-t-td-left">是否显示 V网短号：</td>
             <td class="l-t-td-right"><input type="radio" id="lookVTel" name="lookVTel" ltype="radioGroup" validate="{required:true}"
         	ligerui="{data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/></td> 
        </tr>
         <tr>
         <td class="l-t-td-left">是否显示家庭电话：</td>
         <td class="l-t-td-right">
                    <input type="radio" id="lookHomeTel" name="lookHomeTel" ltype="radioGroup" validate="{required:true}"
											ligerui="{data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/>
	    </td>
	     <td class="l-t-td-left">是否显示紧急信息：</td>
         <td class="l-t-td-right"><input type="radio" id="lookEmergency" name="lookEmergency" ltype="radioGroup" validate="{required:true}"	
         ligerui="{data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/></td> 
        </tr>
         <tr> 
          <td class="l-t-td-left">是否显示地址：</td>
         <td class="l-t-td-right">
         <input type="radio" id="lookAddress" name="lookAddress" ltype="radioGroup" validate="{required:true}"
											ligerui="{data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/>
		</td>
		 <td class="l-t-td-left">是否显示通信地址：</td>    
         <td class="l-t-td-right">
           <input type="radio" id="lookMailingAddress" name="lookMailingAddress" ltype="radioGroup" validate="{required:true}"
											ligerui="{data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/>
	    </td>
        </tr> 
        </c:if>
        <c:if test="${param.pageStatus!='detail'}">
             <input type="hidden"  name="lookAddress" value="1">
             <input type="hidden"  name="lookMailingAddress" value="1">
             <input type="hidden"  name="lookHomeTel" value="1">
             <input type="hidden"  name="lookMobileTel" value="1">
             <input type="hidden"  name="lookVTel" value="1">
             <input type="hidden"  name="lookEmergency" value="1"> 
        </c:if>
         <tr>        
            <td class="l-t-td-left">备注：</td>
            <td class="l-t-td-right" colspan="3"><textarea name="remark" cols="80" rows="4" class="l-textarea" validate="{maxlength:200}"  ></textarea></td>
        </tr>   
        
    </table>
             <input type="hidden"  name="fkEmployeeId" id="fkEmployeeId" > 
</form>
</body>
</html>
