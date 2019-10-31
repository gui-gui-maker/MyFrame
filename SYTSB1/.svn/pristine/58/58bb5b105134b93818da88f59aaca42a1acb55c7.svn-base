<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head pageStatus="${param.status}">
</head>
<script type="text/javascript">
$(function(){
    $("#from_trzsbAppOutsour").initFormList({
    	root:'list',
        getAction:"device/tzsbapp/listAppOutsour.do?appId=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        actionParam:{"fk_tzsb_application_id" : $("#formObj>#appId")},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
        delAction:'device/tzsbapp/delAppOutsour.do',	//删除数据的action
        delActionParam:{id:"id"},	//默认为选择行的id 
        columns:[
            //此部分配置同grid
            { display:'告知书id', name:'fk_tzsb_application_id', width:'1%', hide:true},
            { display:'主键', name:'id', width:'1%', hide:true},
            { display:'施工项目', name:'engineering_item', width:'30%'},
            { display:'分包单位名称', name:'contractor_unit', width:'30%'},
            { display:'组织机构代码', name:'contractor_unit_code', width:'38%'}
            ],
    });
});
	</script>
<body>
<form name="from_trzsbAppOutsour" id="from_trzsbAppOutsour" method="post"  action="device/tzsbapp/saveAppOutsour.do?status=${param.status}" >
         <input type="hidden" name="id" id="outsour_id"/>
      	<input type="hidden" name="fk_tzsb_application_id" value="${param.id}"/>
           <table cellpadding="3" cellspacing="0" class="l-detail-table">
           			<tr>
				<td class="l-t-td-left">施工项目：</td>
				<td class="l-t-td-right"><input name="engineering_item"
					validate="{required:true,maxlength:64}" ltype='text' id="tsjc_app_outsour__engineering_item_1"  /></td>
				<td class="l-t-td-left">组织机构代码：</td>
				<td class="l-t-td-right" ><input name="contractor_unit_code" readonly="readonly"
					validate="{required:true,maxlength:128}"  ltype='text' id="tsjc_app_outsour__contractor_unit_code_1" /></td>
			</tr>
			<tr>
					<td class="l-t-td-left">分包单位名称：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="tsjc_app_outsour__contractor_unit_1" id="tsjc_app_outsour__contractor_unit_1" type="hidden" />
					  <input type="text" id="tsjc_app_outsour__contractor_unit_1_name" name="contractor_unit"   ltype="text"  validate="{required:true}" onclick="selectorg('9')"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('9')}}]}"/>
					</td>
			</tr>
	</table>
	    </form>
</body>
</html>