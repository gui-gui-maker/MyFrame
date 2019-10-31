<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<head pageStatus="${param.status}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <link type="text/css" rel="stylesheet" href="app/qualitymanage/css/form_detail.css" />
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">
    $(function() {
    	var id="${param.ids}";
    $("#form1").initForm({
		
		  toolbar:[
                 {text:"保存", id:'save',icon:"save", click:save},
                 {text:"关闭", icon:"close", click:function(){
               	  api.close();
                 	 }
              		}]
	});
    if("${param.status}"=="add"&&id.length==32){
    	
    	$.ajax({
            url: "resourceInfo/detail.do?id=${param.ids}",
            type: "POST",
			async:false,
            success: function (data, stats) {
            	$("#event_name").val(data.data.resource_name);
            	$("#event_date").val(data.data.resource_last_update_date);
            	$("#event_man").val(data.data.resource_share_user);
            },
            error: function (data) {
            }
        });
    }
    });
    function save(){
    	if ($("#form1").validate().form()) {
    		var ids =[];
                   ids="${param.ids}".split(",");
    		var event_name=$("#event_name").val();
    		var event_date=$("#event_date").val();
    		var event_man=$("#event_man").val();
    		var event=$("#event").val();
    		var parentPathId="${param.parentPathId}"
    		var event_describe=$("#event_describe").val();
    		for (var i = 0; i < ids.length; i++) {
    			$.ajax({
    	            url: "resourceInfo/addResourceAttribute.do",
    	            type: "POST",
    				async:false,
    	            data: {id:ids[i],event_name:event_name,event_date:event_date,event_man:event_man,event:event,event_describe:event_describe,parentPathId:parentPathId},
    	            success: function (data, stats) {
    	            	top.$.notice("保存成功！");
	               		api.close();
    	            },
    	            error: function (data) {
    	            	$("#save").removeAttr("disabled"); 
    	                $.ligerDialog.error('保存失败！');
    	            }
    	        });
    			
    		}
    		 api.close();
    	}else{
    		$("#save").removeAttr("disabled"); 
    	}
    	
    }

    
    
    
    
    
    
    
    
    
    </script>
    
    

</head>
<body>
 <form id="form1"  getAction="resourceInfo/detail.do?id=${param.ids}">
         <input name="id" type="hidden" />
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		  		          <tr> 
        
        <td class="l-t-td-left"> 名称:</td>
        <td class="l-t-td-right"  colspan="3"> 
        <input  ltype='text'   name="event_name" id="event_name" type="text" validate="{required:true}"  />
        </td>
       
       </tr>
       <tr>
        <td class="l-t-td-left"> 发生时间:</td>
        <td class="l-t-td-right"> 
        <input ltype="date"  name="event_date" id="event_date"   type="text" ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 参与人员:</td>
        <td class="l-t-td-right"  colspan="3"> 
         <input  ltype='text'    name="event_man" id="event_man"  type="text" validate="{required:true}" />
        </td>
      
       </tr>
       <tr>
         <td class="l-t-td-left"> 事件:</td>
        <td class="l-t-td-right"  colspan="3"> 
          <input  ltype='text'   name="event" id="event"  type="text"  />
        </td>
       </tr>
       <tr > 
        <td class="l-t-td-left"> 描述:</td>
        <td class="l-t-td-right1" colspan="3" > 
        <textarea name="event_describe" id="event_describe" rows="4" class="l-textarea"  validate="{maxlength:2000}"></textarea></td>
       </tr>
     
     
      </table>
    </form> 
    </body>
</html>