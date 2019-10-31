<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
</head>
<script type="text/javascript">
$(function(){
	
    $("#form_tzsbAppWorks").initFormList({
    	root:'list',
        getAction:"device/tzsbapp/listAppWorks.do?appId=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        actionParam:{"fk_tzsb_application_id" : $("#formObj>#appId")},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
        delAction:'device/tzsbapp/delAppWorks.do',	//删除数据的action
        delActionParam:{id:"id"},	//默认为选择行的id 
        columns:[
            { display:'告知书id', name:'fk_tzsb_application_id', width:'1%', hide:true},
            { display:'主键', name:'id', width:'1%', hide:true},
            { display:'作业项目', name:'work_item', width:'25%'},
            { display: '姓名', width: "10%", name: 'name'},
            { display: '身份证', name: 'id_card', width: "15%" },
            { display: '作业类别(方法)', name: 'work_type', width: "25%"},
            { display: '作业级别(项目)', name: 'work_item_dis', width: "23%"}
            ],
    });
});
	</script>
<body>
<form name="form_tzsbAppWorks" id="form_tzsbAppWorks" method="post"  action="device/tzsbapp/saveAppWorks.do?status=${param.status}" >
         <input type="hidden" name="id" />
        	<input type="hidden" name="fk_tzsb_application_id" value="${param.id}"/>
           <table cellpadding="3" cellspacing="0" class="l-detail-table">
           			<tr>
				<td class="l-t-td-left">作业项目：</td>
				<td class="l-t-td-right" colspan="3"><u:combo validate="{required:true,maxlength:64}"  name="work_item"  code="WORK_ITEM" ></u:combo></td>
			</tr>
			           			<tr>
				<td class="l-t-td-left">姓名：</td>
				<td class="l-t-td-right"><input name="name"
					validate="{required:true,maxlength:64}" ltype='text' id="tzsb_app_workers__name_1"  /></td>
				<td class="l-t-td-left">身份证：</td>
				<td class="l-t-td-right" ><input name="id_card"
					validate="{required:false,maxlength:128}"  ltype='text' id="tzsb_app_workers__id_card_1" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">作业类别(方法)：</td>
				<td class="l-t-td-right" ><u:combo name="work_type"   code="WORK_TYPE"></u:combo></td>
				<td class="l-t-td-left">作业级别(项目)：</td>
				<td class="l-t-td-right" ><u:combo name="work_item_dis"  code="WORK_ITEM_DIS"></u:combo></td>
			</tr>
	</table>
	</form>
</body>
</html>