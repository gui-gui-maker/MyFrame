<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title></title>
</head>
<body>

<form id="certForm" name="certForm" method="post" action="employee/cert/saveCert.do?status=${param.status}">
  	<input type="hidden" name="id" id="cert_id"/>
  	<input type="hidden" name="employee.id" id="employeeId"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr>
	       <td class="l-t-td-left">证书类型</td>
	       <td class="l-t-td-right" colspan="3"><u:combo name="cert_type" code="BASE_LETTER" validate="required:true"/></td>	
	     
	    </tr>
	    <!-- 
	    <tr>
	       <td class="l-t-td-left">证书类别：</td>
	       <td class="l-t-td-right" ><u:combo name="cert_category" code="BASE_CERT_TYPE" validate="required:true"/></td>	
	       <td class="l-t-td-left">证书代码：</td>
	       <td class="l-t-td-right"><input name="cert_code" id="cert_code" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	    </tr>
	     -->
	    <tr>
	      <td class="l-t-td-left">证书编号</td>
	       <td class="l-t-td-right"><input name="cert_no" id="cert_no" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	       <td class="l-t-td-left">发证机构</td>
	       <td class="l-t-td-right" ><input name="cert_issue_org" id="cert_issue_org" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	      
	    </tr>
	    <tr>
	     <td class="l-t-td-left">证书级别</td>
	       <td class="l-t-td-right"><u:combo name="cert_level" code="BASE_CERT_LEVEL" validate="required:true" /></td>
	    	<td class="l-t-td-left">初次取证日期</td>
	       	<td class="l-t-td-right"><input name="first_get_date" id="first_get_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" /></td>
	     
	    </tr>
	    <tr>
	      	<td class="l-t-td-left">发证日期</td>
	       	<td class="l-t-td-right"><input name="cert_begin_date" id="cert_begin_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" /></td>
	       	<td class="l-t-td-left">有效截止日期</td>
	       	<td class="l-t-td-right"><input name="cert_end_date" id="cert_end_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" /></td>
	    </tr>
	</table> 
  	<script type="text/javascript">
    $("#certForm").initFormList({
    	root:'datalist',
        getAction:"employee/cert/getList.do?id=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        delAction:'employee/cert/deleteCerts.do',	//删除数据的action
        delActionParam:{ids:"id"},	//默认为选择行的id 
        columns:[
            //此部分配置同grid
            { display:'持证情况主键', name:'id', width:'1%', hide:true},
			{ display:'人员基本信息主键', name:'employee_id', width:'1%', hide:true},
            { display:'证书类型', name:'cert_type', width:'15%'},
            { display:'证书编号', name:'cert_no', width:'15%'},
            { display:'证书类别', name:'cert_category', width:'15%'},
            { display:'证书代码', name:'cert_code', width:'15%'},
            { display:'发证机构', name:'cert_issue_org', width:'25%'},
            { display:'证书级别', name:'cert_level', width:'15%'},
            { display:'发证日期', name:'cert_begin_date', width:'12%'},
            { display:'有效截止日期', name:'cert_end_date', width:'18%'},
            { display:'授权状态', name:'cert_status', width:'10%', render: function (item) {
                	if("0" == item["cert_status"]){
                		return "未授权";
                	}else if("1" == item["cert_status"]){
                		return "授权";
                	}
            	}
            }
        ]
    });
	</script>
	</form>
</body>
</html>