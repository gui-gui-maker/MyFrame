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
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <link type="text/css" rel="stylesheet" href="app/qualitymanage/css/form_detail.css" />
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">
  
    
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
                $("#nameId").val(p.id);
                $("#name").val(p.name);
                $("#department").val(p.org_name);
                $("#jobs-txt").val(p.POSITION);
                $("#educationMoney").val(p.educationMoney);
                $("#postSalaryZw").val(p.postSalaryZw);
                $("#postSalaryZc").val(p.postSalaryZc);
                $("#total").val(p.total);
                $("#salactName-txt").val(p.salactName);
                $("#education").ligerGetComboBoxManager().setValue( getEducation(p.initial_education,p.initial_degree));
                $("#professional").ligerGetComboBoxManager().setValue(getEMPTITLE(p.emp_title));
            }
        });
    }
    
    function getEMPTITLE(name){
    	if(name==""||name==null){
    		return "";
    	}
    	if(name.indexOf("技术员")!=-1){
    		return "jsy";
  		}else if(name.indexOf("助理工程师")!=-1){
  			return "zlgcs";
  		}else if(name.indexOf("工程师")!=-1){
  			return "gcs";
  		}
    }
    function getEducation(name,degree){
    	if(name==""||name==null){
    		return "";
    	}
    	if(name.indexOf("博士")!=-1){
    		return "bs";
    	}else if(name.indexOf("硕士")!=-1){
    		return "ss";
    	}else if(name.indexOf("研究生")!=-1){
    		return "yjs";
    	}else if(name.indexOf("本科")!=-1||degree!=""){
    		if(degree!=""&&degree!=null){
    			var value = degree.replace(/学士/g,";学士;");
        		var n = value.split("学士").length;
        		if(n>2){
        			return "sxwbg";
        		}else{
        			return "dxbk";
        		}
    		}else{
    			return "dxbk";
    		}
    	}else if(name.indexOf("大专")!=-1||name.indexOf("大学专科")!=-1||name.indexOf("专科")!=-1){
			return "dxzk";
		}else if(name.indexOf("中专")!=-1){
			return "zz";
		}else if(name.indexOf("高中")!=-1){
			return "gz";
		}else if(name.indexOf("初中")!=-1){
			return "cz";
		}
    }
    
    
function chooseOrg(){
        top.$.dialog({
            width: 800,
            height: 450,
            lock: true,
            parent: api,
            title: "选择部门",
            content: 'url:app/common/org_choose.jsp',
            cancel: true,
            ok: function(){
                var p = this.iframe.contentWindow.getSelectedPerson();
                if(!p){
                    top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                    return false;
                }
                $("#departmentId").val(p.id);
                $("#department").val(p.name);
            }
        });
    }
    
    
    
    
    
    
    
    
    
    </script>
    
    

</head>
<body>
 <form id="form1" action="tjy2YwfwbgzqrbAction/save.do" getAction="tjy2YwfwbgzqrbAction/detail.do?id=${param.id}">
         <input name="id" type="hidden" />
         <input name="yesNo" type="hidden" />
        <input name="departmentId" id="departmentId" type="hidden" />
         <input  name="nameId" id="nameId" type="hidden" />
         <input name="data_status" type="hidden" value="1"/>
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		  		          <tr> 
        
        <td class="l-t-td-left"> 姓名:</td>
        <td class="l-t-td-right"> 
        <input  ltype='text' readonly="readonly"  id="name" name="name" type="text" id="Reviewers"  onclick="choosePerson();" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/>
        </td>
        <td class="l-t-td-left"> 部门:</td>
        <td class="l-t-td-right"> 
<input readonly="readonly"    validate="{maxlength:50,required:true}" ltype="text"  name="department" id="department"  type="text" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 学历:</td>
        <td class="l-t-td-right"> 
        <input type="text" ltype="select" name="education" id="education" ligerui="{data:<u:dict code="lpryxl" />}" validate="required:true"/>
       <%--  <u:combo name="education" code="lpryxl"  validate="required:true"/> --%>
        </td>
        <td class="l-t-td-left"> 岗位:</td>
        <td class="l-t-td-right"> 
          <u:combo name="jobs" code="gwgz" />
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 职称:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype="select" name="professional" id="professional" ligerui="{data:<u:dict code="lpryzc" />}" />
         <%-- <u:combo name="professional" code="lpryzc"  /> --%>
        </td>
        <td class="l-t-td-left"> 学历工资:</td>
        <td class="l-t-td-right"> 
        <input name="educationMoney" id="educationMoney"  type="text" ltype='text' validate="{maxlength:22}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 岗位工资职务:</td>
        <td class="l-t-td-right"> 
        <input name="postSalaryZw" id="postSalaryZw" type="text" ltype='text' validate="{maxlength:22}"/>
        </td>
        <td class="l-t-td-left"> 岗位工资职称:</td>
        <td class="l-t-td-right"> 
        <input name="postSalaryZc" id="postSalaryZc" type="text" ltype='text' validate="{maxlength:22}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 月工资小计:</td>
        <td class="l-t-td-right"> 
        <input name="total" id="total" type="text" ltype='text' validate="{maxlength:22}"/>
        </td>
        <td class="l-t-td-left">工资待遇 </td>
        <td class="l-t-td-right"> 
      <u:combo name="salactName"  code="lpry_gzdy" validate="required:true"/>
        </td>
        </tr> 
      </table>
    </form> 
    </body>
</html>