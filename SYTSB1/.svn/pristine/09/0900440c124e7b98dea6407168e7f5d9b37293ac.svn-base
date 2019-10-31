<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
</head>
<script type="text/javascript">



$(function () {
	
$("#form1").initForm({
	
     toolbarPosition :"bottom",
    success : function(responseText) {//处理成功
    	
        if (responseText.success) {
        	
        	
        	
            top.$.dialog.notice({content:'保存成功'});
            api.data.window.submitAction();
            
            $('#fk_reports_id').val(responseText.data.id);
            $('#report_id').val(responseText.data.id);
            //$(window.parent.document).find('#teacher_id').val(responseText.data.id);
        } else {

                $.ligerDialog.error('保存失败' + responseText);
        }
    },
    getSuccess:function(responseText){
        //alert(res.data.id);
        //alert("111");


    }
});

});
	</script>
<body>

<div class="navtab">
	<div title="报告配置"   lselected="true">			
	<form id="form1" action="report/basic/save.do" getAction="report/basic/detail.do?id=${param.id}">
          <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
          
	<tr> 
       					<input id="report_id" name="id" type="hidden" />
        
        <td class="l-t-td-left"> 报告名称:</td>
        <td class="l-t-td-right"><input name="report_name" validate="{required:true,maxlength:128}"  ltype='text'  /> 
        </td>
        
        <td class="l-t-td-left"> 报告超期天数:</td>
        <td class="l-t-td-right"><input name="und_date" validate="{required:false,maxlength:128}"  ltype='text'  /> 
        </td>
       </tr>
       <tr> 
         <td class="l-t-td-left"> 报表模版文件:</td>
        <td class="l-t-td-right"><input name="report_file" validate="{required:true,maxlength:128}"  ltype='text'  /> 
        </td>
        <td class="l-t-td-left"> 报表路径:</td>
        <td class="l-t-td-right"><input name="rootpath"  value="app/file" validate="{required:false,maxlength:128}"  ltype='text'  /> 
        </td>
        
       </tr>
       <tr> 
        <td class="l-t-td-left"> 数据集名称:</td>
        <td class="l-t-td-right"><input name="mrdataset" value="BGDS" validate="{required:false,maxlength:128}"  ltype='text'  /> 
        </td>
        <td class="l-t-td-left"> 报告书编号格式:</td>
        <td class="l-t-td-right"><input name="report_sn"  value="XX,YYYY,SN5" validate="{required:false,maxlength:128}"  ltype='text'  /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 是否打印合格标签:</td>
        <td class="l-t-td-right"><input type="radio" name="is_print_tags"  ltype="radioGroup" validate="{required:true}" ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }"/> 
        </td>
        <td class="l-t-td-left"> 标签模板文件:</td>
        <td class="l-t-td-right"><input name="report_file_tags" validate="{required:false,maxlength:128}"  ltype='text'  /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 标签数据集名称:</td>
        <td class="l-t-td-right"><input name="mrdataset_tags"  value="BGDS" validate="{required:false,maxlength:128}"  ltype='text'  /> 
        </td>
        <td class="l-t-td-left"> 打印份数:</td>
        <td class="l-t-td-right"> <input name="printcopies" value=1 ligerui="{type:'int'}" ltype='spinner'  validate="{required:true,maxlength:128}"  ltype='text'  />
        </td>
       </tr>
       <tr> 
       <td class="l-t-td-left"> 下次检验间隔时间：</td>
        <td class="l-t-td-right"><input name="interval" ligerui="{type:'int'}" ltype='spinner'  validate="{required:false,maxlength:128}"  ltype='text'  /> 
        </td>
         
       </tr>
      
       
      
      
				
		</table>
       
	</form>
	</div>
	<div id="test" title="报告关联证书">
	<form id="form2" action="report/cert/save.do" getAction="report/cert/getCert.do?id=${param.id}">
       
         <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
        	 <input type="hidden" name="id">
        	<input type="hidden" name="fk_reports_id" id="fk_reports_id" value="${param.id}">
        	 <tr> 
     
         <td class="l-t-td-left">证书类型：</td>
        <td class="l-t-td-right"><u:combo name="cert_code" code="BASE_LETTER" />
        </td>
       </tr>
		
		</table>
	</form>
	<script type="text/javascript">
        $("#form2").initFormList({
//                opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
//                opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
//                action:"",//保存数据或其它操作的action
			actionParam: {"fk_reports_id": $("#report_id")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的id为id的值带上去,可以是一个对象或选择器
			delAction: "report/cert/delete.do", //删除数据的action
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
                $("#form2").setValues(rowdata);
            },
           
         
//                getAction:"",//取数据的action
//                getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
			columns: [
				//此部分配置同grid
				{ display: '主键', name: 'id', width: 50, hide: true},
			
				{ display: '证书名称', name: 'cert_code', width: '100%'},
			
			]
		});
    </script>
	</div>
	<div id="test" title="报告项目配置">
	
	<form id="form3" action="report/item/save.do" getAction="report/item/getItem.do?id=${param.id}">
       
         <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
        	 <input type="hidden" name="id">
        	 
        	<input type="hidden" name="fk_reports_id"  value="${param.id}">
        	
        	
        <tr> 
       	
        <td class="l-t-td-left"> 项目名称:</td>
        <td class="l-t-td-right"><input name="itme_name" validate="{required:false,maxlength:128}"  ltype='text'  /> 
        </td>
        
        <td class="l-t-td-left"> 对应报表页索引:</td>
        <td class="l-t-td-right"><input name="page_index" validate="{required:false,maxlength:128}"  ltype='text'  /> 
        </td>
       </tr>
       
        <tr> 
       	
        <td class="l-t-td-left"> 是否单独检验员:</td>
        <td class="l-t-td-right"><input type="radio" name="is_inspect_man"  ltype="radioGroup" validate="{required:true}" ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }"/> 
        </td>
        
        <td class="l-t-td-left"> 是否单独审核人:</td>
        <td class="l-t-td-right"><input type="radio" name="is_audit_man"  ltype="radioGroup" validate="{required:true}" ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }"/> 
        </td>
       </tr>
        	
        	
        	
        	
        <tr> 
       	<td class="l-t-td-left"> 是否为必选项:</td>
       	 <td class="l-t-td-right"><input type="radio" name="is_must"  ltype="radioGroup" validate="{required:true}" ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }"/> 
       	 </td>
         <td class="l-t-td-left">证书类型：</td>
        <td class="l-t-td-right"><u:combo name="cert_code" code="BASE_LETTER" />
        </td>
       </tr>
		
		</table>
	</form>
	<script type="text/javascript">
        $("#form3").initFormList({
//                opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
//                opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
//                action:"",//保存数据或其它操作的action
			actionParam: {"fk_reports_id": $("#report_id")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的id为id的值带上去,可以是一个对象或选择器
			delAction: "report/item/delete.do", //删除数据的action
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
			
				{ display: '项目名称', name: 'itme_name', width: '20%'},
				{ display: '对应报表页索引', name: 'page_index', width: '15%'},
				{ display: '是否单独检验员', name: 'is_inspect_man', width: '15%'},
				{ display: '是否单独审核人', name: 'is_audit_man', width: '15%'},
				{ display: '是否为必选项', name: 'is_must', width: '10%'},
				{ display: '证书类型', name: 'cert_code', width: '30%'}
				
				
				
			]
		});
    </script>
	</div>
</div>
</body>
</body>
</html>