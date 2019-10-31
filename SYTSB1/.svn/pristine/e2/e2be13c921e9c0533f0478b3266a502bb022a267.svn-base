<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title></title>
</head>
<body>

<form id="costForm" name="costForm" method="post" action="tjy2ScientificCostAction/saveCost.do?status=${param.status}">
  	<input type="hidden" name="id" />
  	<input type="hidden" name="fkTjy2ScientificId" id="cost_id"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr>
	       <td class="l-t-td-left">费用类型</td>
	       <td class="l-t-td-right" ><input name="costType" id="costType" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>	
	         <td class="l-t-td-left">金额</td>
	       <td class="l-t-td-right"><input name="money" id="money" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	     
	    </tr>
	  
	    <tr>
	     <td class="l-t-td-left">说明</td>
	       <td class="l-t-td-right1" colspan="3"><textarea name="costInstructions" id="costInstructions" rows="4" cols="25" class="l-textarea" validate="{maxlength:2000}"></textarea></td>
	    </tr>
	    <tr>
	      	<td class="l-t-td-left">理由</td>
	       	<td class="l-t-td-right1" colspan="3"><textarea name="costReason" id="costReason" rows="4" cols="25" class="l-textarea" validate="{maxlength:2000}"></textarea></td>
	    </tr>
	</table> 
  	<script type="text/javascript">
    $("#costForm").initFormList({
    	root:'datalist',
        getAction:"tjy2ScientificCostAction/getList.do?id=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        delAction:'tjy2ScientificCostAction/delete.do',	//删除数据的action
        delActionParam:{ids:"id"},	//默认为选择行的id 
        columns:[
            //此部分配置同grid
            { display:'费用情况主键', name:'id', width:'1%', hide:true},
			{ display:'项目基本信息主键', name:'fkTjy2ScientificId', width:'1%', hide:true},
            { display:'费用类型', name:'costType', width:'15%'},
            { display:'金额', name:'money', width:'15%'},
            { display:'说明', name:'costInstructions', width:'35%'},
            { display:'理由', name:'costReason', width:'35%'}
          
          
        ]
    });
	</script>
	</form>
</body>
</html>