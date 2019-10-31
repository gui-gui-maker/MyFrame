<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
</head>
<script type="text/javascript">
$(function(){
	
    $("#form_tzsbAppDevice").initFormList({
    	root:'list',
        getAction:"device/tzsbapp/listAppDevice.do?appId=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        actionParam:{"fk_tzsb_application_id" : $("#formObj>#appId"),"construct_sort" : $("#formObj>#construct_sort"),"fk_construct_units_id" : $("#formObj>#tzsb_app_engineersituations_fk_construct_units_id"),"fk_construction_units_id" : $("#formObj>#fk_construction_units_id-2"),"construction_units_name" : $("#formObj>#construction_units_name-2")},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
        delAction:'device/tzsbapp/delAppDevice.do',	//删除数据的action
        delActionParam:{id:"id"},	//默认为选择行的id 
        columns:[
            { display:'告知书id', name:'fk_tzsb_application_id', width:'1%', hide:true},
            { display:'主键', name:'id', width:'1%', hide:true},
            { display:'设备类别', name:'device_sort', width:'10%'},
            { display:'设备级别', name:'device_dis', width:'10%'},
            { display:'设备品种(型式)', name:'device_variety', width:'10%'},
            { display: '设备名称', name: 'device_name', width: "10%"},
            { display: '设备型号(参数)', name: 'device_type', width: "10%" },
            { display: '设备代码', name: 'device_code', width: "10%"},
            { display: '单位内编码', name: 'internal_num', width: "10%"},
            { display: '设备地点', name: 'device_use_address', width: "10%"},
            { display: '制造编号', name: 'factory_code', width: "10%"},
            { display: '管道级别', name: 'pipe_level', hide:true},
            { display: '管道材质', name: 'pipe_material', hide:true},
            { display: '管道长度', name: 'pipe_length', hide:true},
            { display: '管道公标尺寸', name: 'pipe_size', hide:true},
            { display: '制造单位机构代码', name: 'make_units_code', hide:true},
            { display: '制造单位', name: 'fk_company_info_make_id', hide:true},
            { display: '制造单位', name: 'make_units_name',width: "10%"},
            { display: '设备类别', name: 'device_sort_code', hide:true},
            { display: '制造日期', name: 'make_date', width: "10%"},
            ]
    });
    
    $(".tr_pipe").css({"display":"none"});
    
});
//改变管道相应的几个输入项目是否可见 by liaozw
function changePipeDisplay() {
	var cdevice_sort=$("#tzsb_app_device__device_sort_1").ligerComboBox();
	var device_sort=cdevice_sort.getValue();
	$("#form_tzsbAppDevice_device_sort_name").val($("#tzsb_app_device__device_sort_1").val());
	if(device_sort.indexOf("8")==0) {
		$(".tr_pipe").css({"display":""});
	} else {
		$(".tr_pipe").css({"display":"none"});
		$("#tzsb_app_device__pipe_level_1").val('');
		$("#tzsb_app_device__pipe_material_1").val('');
		$("#tzsb_app_device__pipe_length_1").val('');
		$("#tzsb_app_device__pipe_size_1").val('');
	}
}
	</script>
<body>
<form name="form_tzsbAppDevice" id="form_tzsbAppDevice" method="post"  action="" >
         <input type="hidden" name="id" />
      	<input type="hidden" name="fk_tzsb_application_id" value="${param.id}"/>
           <table cellpadding="3" cellspacing="0" class="l-detail-table">
           			<tr>
				<td class="l-t-td-left">设备类别：</td>
				<td class="l-t-td-right" colspan="3">
				 <input type="hidden" name="device_sort"  id="form_tzsbAppDevice_device_sort_name"/>
				<input type="text" onchange="changePipeDisplay(this);"
					id="tzsb_app_device__device_sort_1" name="device_sort_code" ltype="select"
					validate="{required:true}"
					ligerui="{
				selectBoxHeight:400,
				initValue:'3000',
				readonly:true,
				tree:{checkbox:false,data: <u:dict sql="select distinct  id,parent_id pid,device_code code,device_name text from BASE_DEVICE_CATEGORIES t connect by prior t.id=t.parent_id"/>}
				}" />
				</td>
				
			</tr>
           			<tr>
				<td class="l-t-td-left">设备级别：</td>
				<td class="l-t-td-right"><input name="device_dis"
					validate="{required:false,maxlength:64}" ltype='text' id="tzsb_app_device__device_dis_1"  /></td>
				<td class="l-t-td-left">设备品种(型式)：</td>
				<td class="l-t-td-right" ><input name="device_variety"
					validate="{required:false,maxlength:128}"  ltype='text' id="tzsb_app_device__device_variety_1" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">设备名称：</td>
				<td class="l-t-td-right"><input name="device_name"
					validate="{required:true,maxlength:64}" ltype='text' id="tzsb_app_device__device_name_1"  /></td>
				<td class="l-t-td-left">设备型号(参数)：</td>
				<td class="l-t-td-right" ><input name="device_type"
					validate="{required:false,maxlength:128}"  ltype='text' id="tzsb_app_device__device_type_1" /></td>
			</tr>
				<tr>
				<td class="l-t-td-left">设备代码：</td>
				<td class="l-t-td-right"><input name="device_code"
					validate="{required:true,maxlength:64}" ltype='text' id="tzsb_app_device__device_code_1"  /></td>
				<td class="l-t-td-left">单位内编码：</td>
				<td class="l-t-td-right" ><input name="internal_num"
					validate="{required:false,maxlength:128}"  ltype='text' id="tzsb_app_device__internal_num_1" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">设备地点：</td>
				<td class="l-t-td-right"><input name="device_use_address"
					validate="{required:false,maxlength:64}" ltype='text' id="tzsb_app_device__device_use_address_1"  /></td>
				<td class="l-t-td-left">制造编号：</td>
				<td class="l-t-td-right" ><input name="factory_code"
					validate="{required:true,maxlength:128}"  ltype='text' id="tzsb_app_device__factory_code_1" /></td>
			</tr>
			<tr>
					<td class="l-t-td-left">制造单位：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="fk_company_info_make_id" id="tzsb_app_device__fk_company_info_make_id_1" type="hidden" />
					  <input type="text" id="tzsb_app_device__make_units_name_1" name="make_units_name"   ltype="text"  validate="{required:true}" onclick="selectorg('8')"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('8')}}]}"/>
					</td>
			</tr>
			<tr>
				<td class="l-t-td-left">制造单位机构代码：</td>
				<td class="l-t-td-right"><input name="make_units_code"
					validate="{required:true,maxlength:64}" ltype='text'
					id="tzsb_app_device__make_units_code_1" readonly="readonly"/></td>
				<td class="l-t-td-left">制造日期：</td>
				<td class="l-t-td-right"><input name="make_date"
					id="tzsb_app_device__makeDate_1"  ltype="date"
					validate="{required:true}"
					ligerui="{format:'yyyy-MM-dd'}"  /></td>
			</tr>
			<tr class="tr_pipe">
				<td class="l-t-td-left">管道级别：</td>
				<td class="l-t-td-right"><input name="device__pipe_level"
					validate="{required:false,maxlength:64}" ltype='text' id="tzsb_app_device__pipe_level_1"  /></td>
				<td class="l-t-td-left">管道材质：</td>
				<td class="l-t-td-right" ><input name="pipe_material"
					validate="{required:false,maxlength:128}"  ltype='text' id="tzsb_app_device__pipe_material_1" /></td>
			</tr>
			<tr class="tr_pipe">
				<td class="l-t-td-left">管道长度：</td>
				<td class="l-t-td-right"><input name="pipe_length"
					validate="{required:false,maxlength:64}" ltype='text' id="tzsb_app_device__pipe_length_1"  /></td>
				<td class="l-t-td-left">管道公标尺寸：</td>
				<td class="l-t-td-right" ><input name="pipe_size"
					validate="{required:false,maxlength:128}"  ltype='text' id="tzsb_app_device__pipe_size_1" /></td>
			</tr>
	</table>
	</form>
</body>
</html>