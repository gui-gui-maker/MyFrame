<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>订餐订单信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="app/common/js/idCard.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var foodList = api.data.foods;
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	          		//保存基本信息（主表）后，id未自动赋值，故此处手动赋值
	          		$("#id").attr("value",response.data.id);
	         		api.data.window.refreshGrid();
	            	//api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		//if(checkBasic()){
				    			if(confirm("确定保存？")){
				    				//表单提交
				    				$("#formObj").submit();
					    		}
				    		//}				    		
				    	}
	      			}
	      		},
				{
					text: "关闭", icon: "cancel", click: function(){
						api.close();
					}
				}
			], toolbarPosition: "bottom"
		});	
	    
	    $("#pubForm").initFormList({
	    	root:'datalist',
	        getAction:"dining/pubm/getList.do?id=${param.id}",
	        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
	        actionParam:{"fpo_id" : $("#formObj>[name='id']")},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
	        delAction:'dining/pubm/delete.do',	//删除数据的action
	        delActionParam:{ids:"id"},	//默认为选择行的id 
	        columns:[
	            //此部分配置同grid
	            { display:'ID', name:'id', width:'1%', hide:true},
	            { display:'就餐时间ID', name:'fpo_id', width:'1%', hide:true},
	            { display:'菜品名称', name:'food_name', width:'20%'},
	            { display:'发布时间', name:'pub_time', width:'25%'},
	            { display:'发布人', name:'pub_man', width:'20%'},
	            { display:'状态', name:'status', width:'5%',hide:true}
	        ]
	    });
	});	
	
	function closewindow(){
		api.close();
	}
</script>

</head>
<body>
<div class="navtab">
	<div title="就餐时间信息" tabId="futTab" style="height: 400px">
	<form name="formObj" id="formObj" method="post" action="dining/pubo/saveUseTime.do"
		getAction="dining/pubo/puboDetail.do?id=${param.id}">
		<input type="hidden" name="id" id="id" value="${param.id}"/>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>								
					<td class="l-t-td-left">就餐日期：</td>
					<td class="l-t-td-right">
					<input name="use_time" 
						id="use_time" 
						type="text" 
						ltype="date"
						validate="{required:true}" 
						ligerUi="{format:'yyyy-MM-dd'}"
						/>
					</td>
					<td class="l-t-td-left">类型：</td>
					<td class="l-t-td-right"><u:combo name="meal_name" code="food_meal_type"/></td>
				</tr>
				<tr>								
					<td class="l-t-td-left">状态：</td>
					<td class="l-t-td-right"><u:combo name="pub_status" code="food_pubo_status"/></td>
				</tr>
			</table>
	</form>
	</div>	
	<div title="菜单详情" tabId="pubTab">
	<form id="pubForm" name="pubForm" method="post" action="dining/pubm/savePubm.do">
  	<input type="hidden" name="id" id="pub_id"/>
  	<input type="hidden" name="fpo_id" value="${param.id}"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<%-- <tr>
            <td class="l-t-td-left">菜品类型：</td>
            <td class="l-t-td-right"><u:combo name="food_name" code="food_tree" validate="{required:true}"></u:combo></td>
        </tr> --%>
        <tr>
            <td class="l-t-td-left">菜品：</td>
            <td class="l-t-td-right">
					<input name="food_name" 
					id="food_name" 
					type="radio" 
					ltype="radioGroup"
					validate="{required:true}"
					ligerui="{
						readonly:true,
						data:foodList
						}"
					/></td>
        </tr>
        
	</table> 
  	
	</form>
  	</div>
</div>
</body>
</html>
