<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>特种作业人员管理</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/common/js/idCard.js"></script>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	          		//保存基本信息（主表）后，id未自动赋值，故此处手动赋值
	          		$("#basic_id").attr("value",response.data.id);
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
				    		if(checkBasic()){
				    			if(confirm("确定保存？")){
				    				//表单提交
				    				$("#formObj").submit();
					    		}
				    		}				    		
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
	});
	
	function closewindow(){
		api.close();
	}
	
	function checkBasic(){
		var id_card = $("#id_card").val();
		if("" != id_card){
			if(!validateID(id_card)){
	        	alert("您输入的身份证号码无效，请检查！");
	        	$("#id_card").focus();
	        	return false;
	        }
		} 
		var email = $("#email").val();
		if("" != email){
			if(!validateEmail(email)){
				alert("您输入的电子邮箱无效，请检查！");
	        	$("#email").focus();
	        	return false;
			}
		}
		return true;
	}
	
	// 验证身份证号码
	function validateID(value){
		var parser = new ClsIDCard();
		if (parser.IsValid(value)) {
			$("#formObj").setValues({
	       		"birth_date": parser.GetBirthDate()//, "sex": parser.GetSex()
	  		})
	   		return true;
	 	}else{
	    	return false;
	  	}    
	}
	
	//验证邮箱格式是否正确
	function validateEmail(value) { 
		if("" != value){
			//对电子邮件的验证
			var myreg = /(\S)+[@]{1}(\S)+[.]{1}(\w)+/;
			if(!myreg.test(value)){
	       		return false;
			}
		} 
		return true;
	}
	
	// 选择工作单位
	function selectorg(){	
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择企业信息",
			content: 'url:app/enter/enter_open_list.jsp',
			data : {"parentWindow" : window}
		});
	}
	
	function callBack(id,name){
		$('#work_com_id').val(id);	// 工作单位ID
		$('#work_com_name').val(name);	// 工作单位名称
	}
</script>
</head>
<body>
<div class="navtab">
	<div title="基本信息" tabId="basicTab" style="height: 400px">
	<form name="formObj" id="formObj" method="post" action="relevant/basic/saveBasic.do?status=${param.status}"
		getAction="relevant/basic/detail.do?id=${param.id}">
		<input type="hidden" name="id" id="basic_id" value="${param.id}"/>
		<input type="hidden" id="created_by" name="created_by"/>
  		<input type="hidden" id="created_date" name="created_date"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>特种设备相关从业人员</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">姓名：</td>
					<td class="l-t-td-right"><input name="people_name" id="people_name" type="text" ltype='text' validate="{required:true,maxlength:32}" /></td>									
					<td class="l-t-td-left">身份证号码：</td>
					<td class="l-t-td-right"><input name="id_card" id="id_card" type="text" ltype='text' validate="{maxlength:18}" /></td>	
				</tr>
				<tr>
					<td class="l-t-td-left">人员类别：</td>
					<td class="l-t-td-right"><input name="people_type" id="people_type" type="text" ltype='text' validate="{maxlength:16}" /></td>									
					<td class="l-t-td-left">性别：</td>
					<td class="l-t-td-right"><u:combo name="sex" code="EDU_003" /></td>	
				</tr>
				<tr>
					<td class="l-t-td-left">行政区划：</td>
					<td class="l-t-td-right"><input type="text" id="area_code" name="area_code" ltype="select"  validate="{required:true}"  
				ligerui="{
					initValue:'511101',
					readonly:true,
					data: <u:dict sql="select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE"/>,
				}"/></td>									
					<td class="l-t-td-left">出生年月：</td>
					<td class="l-t-td-right"><input name="birth_date" id="birth_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>	
				</tr>
				<tr>
					<td class="l-t-td-left">民族：</td>
					<td class="l-t-td-right"><u:combo name="nationality" code="ba_mz"/></td>									
					<td class="l-t-td-left">工作单位名称：</td>
					<td class="l-t-td-right">
						<input name="work_com_id" id="work_com_id" type="hidden" />
						<input name="work_com_name" id="work_com_name" type="text" ltype='text' onclick="selectorg()"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg()}}]}"/>
					</td>	
				</tr>
				<tr>
					<td class="l-t-td-left">联系电话：</td>
					<td class="l-t-td-right"><input name="tel" type="text" ltype="text" validate="{maxlength:36}"/></td>
					<td class="l-t-td-left">传真：</td>
					<td class="l-t-td-right"><input name="fax" type="text" ltype="text" validate="{maxlength:36}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">电子邮箱：</td>
					<td class="l-t-td-right"><input name="email" id="email" type="text" ltype="text" validate="{maxlength:126}"/></td>
					<td class="l-t-td-left">学历：</td>
					<td class="l-t-td-right"><u:combo name="diploma" code="EDU_013" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">毕业院校：</td>
					<td class="l-t-td-right"><input name="graduate_school" type="text" ltype="text" validate="{maxlength:126}"/></td>
					<td class="l-t-td-left">所学专业：</td>
					<td class="l-t-td-right"><input name="professional" type="text" ltype="text" validate="{maxlength:36}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">技术操作种类：</td>
					<td class="l-t-td-right" colspan="3"><input name="jobs" type="text" ltype="text" validate="{maxlength:126}"/></td>
				</tr>
			</table>
		</fieldset>
	</form>
	</div>	
	<div title="持证情况" tabId="certTab">
	<form id="certForm" name="certForm" method="post" action="relevant/cert/saveCert.do?status=${param.status}">
  	<input type="hidden" name="id" id="cert_id"/>
  	<input type="hidden" name="relevantPeople.id" value="${param.id}"/>
  	<input type="hidden" id="cert_created_by" name="created_by"/>
  	<input type="hidden" id="cert_created_date" name="created_date"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr>
	       <td class="l-t-td-left">证书类型：</td>
	       <td class="l-t-td-right" ><u:combo name="cert_type" code="BASE_RELEVANT_LETTER" validate="required:true"/></td>	
	       <td class="l-t-td-left">证书编号：</td>
	       <td class="l-t-td-right"><input name="cert_no" id="cert_no" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	    </tr>
	    <tr>
	       <td class="l-t-td-left">发证机构：</td>
	       <td class="l-t-td-right" ><input name="cert_authority" id="cert_authority" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	       <td class="l-t-td-left">证书级别：</td>
	       <td class="l-t-td-right"><u:combo name="cert_level" code="BASE_CERT_LEVEL" validate="required:true" /></td>
	    </tr>
	    <tr>
	    	<td class="l-t-td-left">发证日期：</td>
	       <td class="l-t-td-right"><input name="cert_begin_date" id="cert_begin_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" /></td>
	       <td class="l-t-td-left">发证有效截止日期：</td>
	       <td class="l-t-td-right"><input name="cert_end_date" id="cert_end_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" /></td>
	    </tr>
	</table> 
  	<script type="text/javascript">
    $("#certForm").initFormList({
    	root:'datalist',
        getAction:"relevant/cert/getList.do?id=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        actionParam:{"relevantPeople.id" : $("#formObj>[name='id']")},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
        delAction:'relevant/cert/deleteCerts.do',	//删除数据的action
        delActionParam:{ids:"id"},	//默认为选择行的id 
        columns:[
            //此部分配置同grid
            { display:'持证情况主键', name:'id', width:'1%', hide:true},
			{ display:'基本信息主键', name:'fk_people_id', width:'1%', hide:true},
            { display:'证书类型', name:'cert_type', width:'25%'},
            { display:'证书编号', name:'cert_no', width:'20%'},
            { display:'发证机构', name:'cert_authority', width:'35%'},
            { display:'发证日期', name:'cert_begin_date', width:'15%'},
            { display:'发证有效截止日期', name:'cert_end_date', width:'20%'},
            { display:'证书级别', name:'cert_level', width:'25%'}
        ]
    });
	</script>
	</form>
  	</div>
</div>
</body>
</html>
