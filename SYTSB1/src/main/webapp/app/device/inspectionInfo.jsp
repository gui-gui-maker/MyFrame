<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
</head>
<script type="text/javascript">




$(function(){   
	
	alert(2222);
	$.ajax({   
 url:'xx.action',    
 data:{  },           
 type:'POST',       
 dataType:'json',      
 success:function(data) {        
       //data为返回值                  alert(data)       
       }       
 }); 

}); 




	
	</script>
<body >

				
	<form id="form3"  getAction="device/basic/getInsInfo.do?id=${param.id}">
       
         <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
        	 <input type="hidden" name="id">
        	 
        	
        	
        	
        <tr> 
       	
        <td class="l-t-td-left"> 检验部门:</td>
        <td class="l-t-td-right"><input name="itme_name" validate="{required:false,maxlength:128}"  ltype='text'  /> 
        </td>
        
        <td class="l-t-td-left"> 报告书编号:</td>
        <td class="l-t-td-right"><input name="page_index" validate="{required:false,maxlength:128}"  ltype='text'  /> 
        </td>
       </tr>
       
        <tr> 
       	
        <td class="l-t-td-left"> 检验类别:</td>
        <td class="l-t-td-right"><input type="radio" name="is_inspect_man"  ltype="radioGroup" validate="{required:true}" ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }"/> 
        </td>
        
        <td class="l-t-td-left"> 检验结论:</td>
        <td class="l-t-td-right"><input type="radio" name="is_audit_man"  ltype="radioGroup" validate="{required:true}" ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }"/> 
        </td>
       </tr>
        	
        	
        	
        	
        <tr> 
       	<td class="l-t-td-left"> 下次检验日期:</td>
       	 <td class="l-t-td-right"><input type="radio" name="is_must"  ltype="radioGroup" validate="{required:true}" ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }"/> 
       	 </td>
         <td class="l-t-td-left">项目负责人：</td>
        <td class="l-t-td-right"><input type="radio" name="is_must"  ltype="radioGroup" validate="{required:true}" ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }"/> 
        </td>
       </tr>
		
		</table>
	</form>
	<script type="text/javascript">
        $("#form3").initFormList({
//                opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
//                opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
//                action:"",//保存数据或其它操作的action
			//actionParam: {"fk_reports_id": $("#report_id")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的id为id的值带上去,可以是一个对象或选择器
			//delAction: "report/item/delete.do", //删除数据的action
			root:'datalist',
			//delActionParam: {name: "name"}, //默认为选择行的id
			//success: function (data) {
				//alert("success=" + $.ligerui.toJSON(data))
			//},
			//getSuccess: function (data) {
				//alert("getSuccess=" + $.ligerui.toJSON(data))
			//},
			//delSuccess: function (data) {
				//alert("delSuccess=" + $.ligerui.toJSON(data))
			//},
			//onBeforeSave: function (g) {
			//	alert(g.data("defaultValues"));
			//	return true;
			//},
  	onSelectRow:function (rowdata, rowindex) {
                $("#form3").setValues(rowdata);
            },
           
         
//                getAction:"",//取数据的action
//                getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
			columns: [
				//此部分配置同grid
				{ display: '主键', name: 'id', width: 50, hide: true},
			
				{ display: '检验部门', name: 'itme_name', width: '20%'},
				{ display: '报告书编号', name: 'page_index', width: '15%'},
				{ display: '检验类别', name: 'is_inspect_man', width: '15%'},
				{ display: '检验结论', name: 'is_audit_man', width: '15%'},
				{ display: '项目负责人', name: 'cert_code', width: '10%'},
				{ display: '下次检验日期', name: 'is_must', width: '30%'}
				
				
				
			]
		});
    </script>
</body>
</html>