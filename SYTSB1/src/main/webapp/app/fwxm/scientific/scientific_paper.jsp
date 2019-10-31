<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title></title>
</head>
<body>

<form id="paperForm" name="paperForm" method="post" action="tjy2ScientificPaperAction/savePaper.do?status=${param.status}">
  	<input type="hidden" name="id" id="cert_id"/>
  	<input type="hidden" name="fkTjy2ScientificId" id="paper_id"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr>
	       <td class="l-t-td-left">论文题目</td>
	       <td class="l-t-td-right" colspan="3"><input name="paperName" id="paperName" type="text" ltype='text' validate="{required:true,maxlength:100}" /></td>	
	     
	    </tr>
	    <tr>
	      <td class="l-t-td-left">发表时间</td>
	       <td class="l-t-td-right"><input name="paperDate" id="paperDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" /></td>
	       <td class="l-t-td-left">发表类型</td>
	       <td class="l-t-td-right" ><input name="paperType" id="paperType" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	      
	    </tr>
	    <tr>
	     <td class="l-t-td-left">论文作者</td>
	       <td class="l-t-td-right"><input name="paperAuthor" id="paperType" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	    	<td class="l-t-td-left">出版社</td>
	       	<td class="l-t-td-right"><input name="paperPress" id="paperPress" type="text" ltype='text' validate="{required:true,maxlength:50}"  /></td>
	     
	    </tr>
	</table> 
  	<script type="text/javascript">
    $("#paperForm").initFormList({
    	root:'datalist',
        getAction:"tjy2ScientificPaperAction/getList.do?id=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        delAction:'tjy2ScientificPaperAction/delete.do',	//删除数据的action
        delActionParam:{ids:"id"},	//默认为选择行的id 
        columns:[
            //此部分配置同grid
            { display:'项目论文主键', name:'id', width:'1%', hide:true},
			{ display:'科研项目主键', name:'fkTjy2ScientificId', width:'1%', hide:true},
            { display:'论文题目', name:'paperName', width:'25%'},
            { display:'发表时间', name:'paperDate', width:'15%'},
            { display:'发表类型', name:'paperType', width:'15%'},
            { display:'论文作者', name:'paperAuthor', width:'15%'},
            { display:'出版社', name:'paperPress', width:'25%'},

        ]
    });
	</script>
	</form>
</body>
</html>