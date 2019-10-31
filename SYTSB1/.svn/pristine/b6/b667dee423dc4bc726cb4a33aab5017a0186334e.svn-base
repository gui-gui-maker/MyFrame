<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title></title>
</head>
<body>

<form id="patentForm" name="patentForm" method="post" action="tjy2ScientificPatentAction/savePatent.do?status=${param.status}">
  	<input type="hidden" name="id" id="cert_id"/>
  	<input type="hidden" name="fkTjy2ScientificId" id="patent_id"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr>
	       <td class="l-t-td-left">专利名称</td>
	       <td class="l-t-td-right" colspan="3"><input name="patentName" id="patentName" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>	
	     
	    </tr>
	    <tr>
	      <td class="l-t-td-left">专利申请时间</td>
	       <td class="l-t-td-right"><input name="patentDate" id="patentDate" type="text" ltype='date'  ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" /></td>
	       <td class="l-t-td-left">专利申请人</td>
	       <td class="l-t-td-right" ><input name="patentMan" id="patentMan" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	      
	    </tr>
	    <tr>
	    	<td class="l-t-td-left">专利申请人地址</td>
	       	<td class="l-t-td-right" colspan="3"><input name="patentManAddress" id="patentManAddress" type="text" ltype='text'  validate="{required:true}" /></td>
	    </tr>
	    <tr>
	     <td class="l-t-td-left">专利编号</td>
	       <td class="l-t-td-right"><input name="patentNumber" id="patentNumber" type="text" ltype='text' validate="{required:true}" /></td>
	    	<td class="l-t-td-left">专利类型</td>
	       	<td class="l-t-td-right"><input name="patentType" id="patentType" type="text" ltype='text' validate="{required:true}" /></td>
	     
	    </tr>
	    <tr>
	      	<td class="l-t-td-left">专利发明人</td>
	       	<td class="l-t-td-right" colspan="3"><input name="patentInvent" id="patentInvent" type="text" ltype='text'  validate="{required:true}" /></td>
	       
	    </tr>
	</table> 
  	<script type="text/javascript">
    $("#patentForm").initFormList({
    	root:'datalist',
        getAction:"tjy2ScientificPatentAction/getList.do?id=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        delAction:'tjy2ScientificPatentAction/delete.do',	//删除数据的action
        delActionParam:{ids:"id"},	//默认为选择行的id 
        columns:[
            //此部分配置同grid
            { display:'专利情况主键', name:'id', width:'1%', hide:true},
			{ display:'科研信息主键', name:'fkTjy2ScientificId', width:'1%', hide:true},
            { display:'专利名称', name:'patentName', width:'15%'},
            { display:'专利申请时间', name:'patentDate', width:'15%'},
            { display:'专利申请人', name:'patentMan', width:'15%'},
            { display:'专利编号', name:'patentNumber', width:'15%'},
            { display:'专利类型', name:'patentType', width:'25%'},
            { display:'专利发明人', name:'patentInvent', width:'15%'},
            { display:'专利申请人地址', name:'patentManAddress', width:'12%'}

        ]
    });
	</script>
	</form>
</body>
</html>