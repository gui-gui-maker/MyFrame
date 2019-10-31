<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%@ include file="/k/kui-base-form.jsp"%>
<title></title>
</head>
<body>

<form id="costForm" name="costForm" method="post" action="tjy2ScientificCostAction/saveCost.do?status=${param.status}">
  	<input type="hidden" name="id" />
  	<input type="hidden" name="fkTjy2ScientificId" id="cost_id"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr>
	       <td class="l-t-td-left">项目名称</td>
	       <td class="l-t-td-right" colspan="3"><input name="project_name" id="project_name" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>	
	    </tr>
	    <tr>
	       <td class="l-t-td-left">审核状态</td>
	       <td class="l-t-td-right" ><input name="process" id="process" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>	
	    </tr>
	    <tr>
	      	<td class="l-t-td-left">审核说明</td>
	       	<td class="l-t-td-right" colspan="3"><textarea name="remark" id="remark" rows="4" cols="25" class="l-textarea" validate="{maxlength:2000}"></textarea></td>
	    </tr>
	</table> 
  	<script type="text/javascript">
    $("#costForm").initFormList({
    	root:'datalist',
        getAction:"tjy2ScientificRemarkAction/getList.do?id=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        delAction:'tjy2ScientificRemarkAction/delete.do',	//删除数据的action
        delActionParam:{ids:"id"},	//默认为选择行的id 
        columns:[
            //此部分配置同grid
            { display:'费用情况主键', name:'id', width:'1%', hide:true},
			{ display:'项目基本信息主键', name:'fk_scientific_id', width:'1%', hide:true},
            { display:'项目名称', name:'project_name', width:'25%'},
            { display:'审核状态', name:'process', width:'10%'},
            { display:'审核说明', name:'remark', width:'60%'}
          
          
        ]
    });
	</script>
	</form>
</body>
</html>