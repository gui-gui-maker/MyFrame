<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
</head>
<script type="text/javascript">
$(function(){
	
    $("#form_tzsbAppConsUnit").initFormList({
    	root:'list',
        getAction:"device/tzsbapp/listAppConsUnit.do?appId=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        actionParam:{"fk_tzsb_application_id" : $("#formObj>#appId")},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
        delAction:'device/tzsbapp/delAppConsUnit.do',	//删除数据的action
        delActionParam:{id:"id"},	//默认为选择行的id 
        columns:[
            { display:'告知书id', name:'fk_tzsb_application_id', width:'1%', hide:true},
            { display:'主键', name:'id', width:'1%', hide:true},
            { display:'项目名称', name:'item_name', width:'25%'},
            { display:'单位名称', name:'construction_unit_name', width:'35%'},
            { display:'组织机构代码', name:'construction_unit_code', width:'38%'},
            { display: '单位名称', hide:true,name: 'fk_construction_unit_id'}
            ],
    });
});

	</script>
<body>
<form name="form_tzsbAppConsUnit" id="form_tzsbAppConsUnit" method="post"  action="device/tzsbapp/saveAppConsUnit.do?status=${param.status}" >
         <input type="hidden" name="id" />
      	<input type="hidden" name="fk_tzsb_application_id" value="${param.id}"/>
           <table cellpadding="3" cellspacing="0" class="l-detail-table">
           			<tr>
				<td class="l-t-td-left">项目名称：</td>
				<td class="l-t-td-right"><input name="item_name"
					validate="{required:true,maxlength:64}" ltype='text' id="tzsb_app_const_unit__item_name_1"  /></td>
				<td class="l-t-td-left">组织机构代码：</td>
				<td class="l-t-td-right" ><input name="construction_unit_code" readonly="readonly"
					validate="{required:true,maxlength:128}"  ltype='text' id="tzsb_app_const_unit__construction_unit_code_1" /></td>
			</tr>
			<tr>
					<td class="l-t-td-left">单位名称：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="fk_construction_unit_id" id="tzsb_app_const_unit__fk_construction_unit_id_1" type="hidden" />
					  <input type="text" id="tzsb_app_const_unit__construction_unit_name_1" name="construction_unit_name"   ltype="text"  validate="{required:true}" onclick="selectorg('7')"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('7')}}]}"/>
					</td>
			</tr>
	</table>
	</form>
</body>
</html>