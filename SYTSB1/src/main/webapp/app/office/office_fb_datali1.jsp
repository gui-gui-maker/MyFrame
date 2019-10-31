<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    CurrentSessionUser user=(CurrentSessionUser)request.getSession().getAttribute("currentSessionUser");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
</head>
<body>
<div class="navtab">
<div  title="重大任务信息" lselected="true">
	<form id="form1" action="weighty/Task/save.do" getAction="weighty/Task/detail.do?id=${param.id}">
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="mainLeadId" name="mainLeadId"/>
		<input type="hidden" id="mainDepId" name="mainDepId"/>
	<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					日期
				</div>
			</legend>
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table" id="zlrw">
				
					<tr>
						<td class="l-t-td-left">完成时限</td>
						<td class="l-t-td-right"> <u:combo name="finishLimit"  code="TJY2_PART_TIME" ></u:combo></td></td>
						<td class="l-t-td-left">开始日期</td>
						<td class="l-t-td-right"><input name="startTime" type="text" ltype="date" validate="{required:false}" 
        					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="startTime"  
        					 readonly="readonly"  /></td>
        				<td class="l-t-td-left">完成日期</td>
						<td class="l-t-td-right"><input name="finishTime" type="text" ltype="date" validate="{required:false}" 
        					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="finishTime"  
        					 readonly="readonly"  /></td>
					</tr>
					</table>
				</fieldset>
					
					<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>
							重大任务
						</div>
					</legend>
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table" id="Weighty">
					
					<tr>
						<td class="l-t-td-left">牵头部门</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="mainDep" id="mainDep" validate="{required:true}" /></td>
						<td class="l-t-td-left">牵头领导</td>
						<td class="l-t-td-right"><input name="mainLeadName" type="text" ltype="text"  id="mainLeadName" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">主责任人</td>
						<td class="l-t-td-right"><input name="mainDutyName" type="text" ltype="text"  id="mainDutyName" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">工作内容</td>
						<td class="l-t-td-right" colspan="3"><textarea name="taskContent" id="taskContent" rows="5" cols="25" class="l-textarea" validate="{maxlength:2000}"></textarea></td>
					</tr>
					</table>
				</fieldset>
				</form>
				</div>
<div title="配合部门" tabId="certTab">
	<form id="certForm" name="certForm" method="post" action="weighty/Dep/saveWei.do"   getAction="weighty/Dep/getdetail.do?id=${param.id}">
  	<input type="hidden" name="id" id="pkDepId"/>
  	<input type="hidden" name="weightyTask.id" value="${param.id}"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr>
	       <td class="l-t-td-left">配合部门</td>
	       <td class="l-t-td-right" ><input name="phDepName" id="phDepName" type="text" ltype="text" /></td>	
	       
	    </tr>
	    
	   
	</table> 
      
      <script type="text/javascript">
	        $("#certForm").initFormList({
				// opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
				// opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
				// action:"",//保存数据或其它操作的action
	            actionParam:{"weightyTask.id" : $("#form1>[name='id']")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
	            delAction:'weighty/Dep/delete.do',//删除数据的action
				 // delActionParam:{"id":$},//默认为选择行的id
				 // getAction:"",//取数据的action
				 // getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
	            onSelectRow:function (rowdata, rowindex) {
	                $("#certForm").setValues(rowdata);
	            },
				// toolbar:[
				//  { text:'保存', click:function(){$("#formJc").submit();}, icon:'save'},
				//  { text:'删除', click:function(){$("#formJc").submit();}, icon:'save'}
				//  ],
	            columns:[
	                 {display:'配合部门主键', name:'id', width:'1%', hide:true},
	                 {display:'重大任务主键', name:'pkDepId', width:'1%', hide:true},
	                 {display:'配合部门', name:'phDepName',width:'15%'},
	                 
	            	]
	        });
    	</script>
	</form>
	</div>
	
	<div title="反馈" id="fankui">
	<form id="formfk" name="formfk" method="post" action="task/Feedback/saveWei.do"   getAction="task/Feedback/fdetail.do?id=${param.id}">
  	<input type="hidden" name="id" id="pkDepId"/>
  	<input type="hidden" name="weightyTask.id" value="${param.id}"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr> 
        <td class="l-t-td-left">完成进度</td>
        <td colspan="3" class="l-t-td-right"> 
        <u:combo name="status" code="TJY2_BG_RWFK" />
        <!-- 	<input id= "feedbackPlan" name="feedbackPlan" type="text" ltype='text' validate="{required:true,maxlength:2000}"/>  -->
        </td>
        <!--  
        <td class="l-t-td-left"> 状态</td>
        <td class="l-t-td-right"> 
       		<u:combo name="status" code="TJY2_BG_RWFK" /></td>
       	-->
       </tr>
       <tr> 
        <td class="l-t-td-left">完成情况</td>
        <td colspan="3" class="l-t-td-right"> 
       		 <textarea name="feedbackRemark" type="text" ltype='text' validate="{required:true,maxlength:2000}"></textarea>
        </td>
       </tr>
      	<tr>
      		<td class="l-t-td-left">未完成理由</td>
      		<td class="l-t-td-right" colspan="3"> 
        		<textarea name="unfinishedTask" id="unfinishedTask" rows="5" cols="25" class="l-textarea" validate="{maxlength:2000}"></textarea>
      	    </td>
      		
      	</tr>
	    
	   
	</table> 
      
      <script type="text/javascript">
	        $("#formfk").initFormList({
				// opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
				// opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
				// action:"",//保存数据或其它操作的action
	            actionParam:{"weightyTask.id" : $("#form1>[name='id']")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
	            delAction:'task/Feedback/delete.do',//删除数据的action
				 // delActionParam:{"id":$},//默认为选择行的id
				 // getAction:"",//取数据的action
				 // getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
	            onSelectRow:function (rowdata, rowindex) {
	                $("#formfk").setValues(rowdata);
	            },
				// toolbar:[
				//  { text:'保存', click:function(){$("#formJc").submit();}, icon:'save'},
				//  { text:'删除', click:function(){$("#formJc").submit();}, icon:'save'}
				//  ],
	            columns:[
	                 {display:'配合部门主键', name:'id', width:'1%', hide:true},
	                 {display:'反馈主键', name:'pkFeedbackId', width:'1%', hide:true},
	                 {display:'完成进度', name:'status',width:'15%'},
	                 {display:'完成情况',name:'feedbackRemark',width:'37%'},
	                 {display:'未完成理由',name:'unfinishedTask',width:'37%'}
	                 
	            	]
	        });
    	</script>
	</form>
	</div>
	
	</div>
</body>
</html>
