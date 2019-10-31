var deviceGrid;

function createDeviceInfoGrid() {
    var columns=[
        { display: 'id', name: 'id', hide:true},
        { display: 'sn', name: 'sn', hide:true},
        { display: 'fk_inspection_info_id', name: 'fk_inspection_info_id', hide:true},
        { display: '制造单位', width: '15%', name: 'made_unit_name', type: 'text', editor: { type: 'text'},required:false,maxlength:200},
        { display: '制造单位地址', name: 'made_unit_addr', hide:true},
        { display: '制造许可证级别', width: '10%', name: 'made_license_level', type: 'text', editor: { type: 'text'},required:false,maxlength:200},
        { display: '制造许可证编号', width: '10%', name: 'made_license_code', type: 'text', editor: { type: 'text'},required:false,maxlength:200},
        { display: '制造日期', width: '10%', name: 'made_date', type: 'text', editor: { type: 'text'}, required:false},
        { display: '安装单位', width: '15%', name: 'install_unit_name', type: 'text', editor: { type: 'text'},required:false,maxlength:200},
        { display: '安装许可证编号', width: '10%', name: 'install_license_code', type: 'text', editor: { type: 'text'},required:false,maxlength:32},
        { display: '安装日期', width: '10%', name: 'install_date', type: 'text', editor: { type: 'text'}, required:false,maxlength:32},
        { display: '施工/建设单位', width: '15%', name: 'construction_unit_name', type: 'text', editor: { type: 'text'},required:false,maxlength:200},
        { display: '组织机构代码', width: '10%', name: 'construction_unit_code', type: 'text', editor: { type: 'text'},required:false,maxlength:32},
        { display: '改造修理许可级别', width: '15%', name: 'construction_license_level', type: 'text', editor: { type: 'text'},required:false,maxlength:32},
        { display: '改造修理许可证编号', width: '15%', name: 'construction_license_code', type: 'text', editor: { type: 'text'},required:false,maxlength:32},
        { display: '施工类别', width: '10%', name: 'construction_type', type: 'text', editor: { type: 'text'},required:false,maxlength:32},
        { display: '竣工日期', width: '10%', name: 'repair_finish_date', type: 'text', editor: { type: 'text'},required:false,maxlength:32},
        { display: '使用单位', width: '20%', name: 'com_name', type: 'text', editor: { type: 'text'},required:false,maxlength:200},
        { display: '组织机构代码', width: '10%', name: 'com_code', type: 'text', editor: { type: 'text'},required:false,maxlength:32},
        { display: '使用登记证编号', width: '10%', name: 'com_registration_num', type: 'text', editor: { type: 'text'},required:false,maxlength:32},
        { display: '设备使用地点', width: '10%', name: 'device_use_place', type: 'text', editor: { type: 'text'},required:false,maxlength:200},
        { display: '设备类别', width: '15%', name: 'device_type_code', type: 'text',required:true,        	
            editor: { type: 'select', data: deviceType ,ext:{emptyOption:false,selectBoxHeight:200}},
            render: function (item) {
                return render(item["device_type_code"],deviceType);
            }
        },
        { display: '设备/产品名称', width: '15%', name: 'device_name', type: 'text',editor: { type: 'text'}, required:false,maxlength:200},
        { display: '产品编号', width: '10%', name: 'device_no', type: 'text',editor: { type: 'text'}, required:false,maxlength:1000},
        { display: '产品批号', width: '10%', name: 'device_batch_num', type: 'text',editor: { type: 'text'}, required:false,maxlength:100},
        { display: '部件编号及管号', width: '15%', name: 'device_no_2', type: 'text',editor: { type: 'text'}, required:false,maxlength:1000},
        { display: '设备代码', width: '10%', name: 'device_code', type: 'text', editor: { type: 'text'}, required:false,maxlength:1000},
        { display: '产品型号', width: '10%', name: 'device_model', type: 'text',editor: { type: 'text'}, required:false,maxlength:100},
        { display: '等级/型号', width: '10%', name: 'device_model_2', type: 'text',editor: { type: 'text'}, required:false,maxlength:100},
        { display: '产品/制造标准', width: '15%', name: 'device_standard', type: 'text',editor: { type: 'text'}, required:false,maxlength:100},
        { display: '产品数量', width: '10%', name: 'device_count', type: 'text', editor: { type: 'text'}, required:false,maxlength:100},
        { display: '材料牌号', width: '10%', name: 'material_num', type: 'text',editor: { type: 'text'}, required:false,maxlength:32},
        { display: '设计单位', width: '15%', name: 'design_unit_name',type: 'text',  editor: { type: 'text'}, required:false,maxlength:200},
        { display: '设计许可证编号', width: '10%', name: 'design_license_code',type: 'text',  editor: { type: 'text'}, required:false,maxlength:32},
        { display: '产品图号', width: '10%', name: 'device_pic_no', type: 'text', editor: { type: 'text'}, required:false,maxlength:100},
        { display: '设计日期', width: '10%', name: 'design_date', type: 'text', editor: { type: 'text'}, required:false},
        { display: '压力', width: '10%', name: 'device_pressure', type: 'text',editor: { type: 'text'}, required:false,maxlength:200},
        { display: '温度', width: '10%', name: 'use_temp', type: 'text',editor: { type: 'text'}, required:false,maxlength:100},
        { display: '介质', width: '10%', name: 'device_medium', type: 'text',editor: { type: 'text'}, required:false,maxlength:200},
        { display: '容积', width: '10%', name: 'device_volume', type: 'text',editor: { type: 'text'}, required:false,maxlength:200},
        { display: '材质', width: '10%', name: 'device_meterial', type: 'text', editor: { type: 'text'}, required:false,maxlength:100},
        { display: '长度', width: '10%', name: 'device_length', type: 'text', editor: { type: 'text'}, required:false,maxlength:100},
        { display: '出厂编号1', width: '10%', name: 'factory_code_1', type: 'text',editor: { type: 'text'}, required:false,maxlength:100},
        { display: '出厂编号2', width: '10%', name: 'factory_code_2', type: 'text',editor: { type: 'text'}, required:false,maxlength:100},
        { display: '车辆牌号/车辆识别代码', width: '15%', name: 'device_car_num', type: 'text', editor: { type: 'text'}, required:false,maxlength:100},
        { display: '工程名称', width: '10%', name: 'project_name', type: 'text', editor: { type: 'text'}, required:false,maxlength:100},
        { display: '检验结论', width: '10%', name: 'inspection_conclusion', type: 'text', editor: { type: 'text'}, required:false,maxlength:20},
        { display: '文件、材料、标记移植', width: '15%', name: 'confirm_date1', hide:true},
        { display: '爆破试验及标定爆破压力', width: '15%', name: 'confirm_date2', hide:true},
        { display: '产品质量证明书及外观、标志', width: '15%', name: 'confirm_date3', hide:true},    
        { display: '监检所抽的产品编号', name: 'check_device_no', hide:true},
        { display: '本证书适用的产品编号', name: 'trial_device_no', hide:true},
        { display: '来料加工（是）', name: 'device_processing', hide:true},
        { display: '来料加工（否）', name: 'device_processing2', hide:true},
        { display: '气瓶文件审查', name: 'device_file_check', hide:true},
        { display: '文件审查日期', name: 'file_check_date', hide:true},
        { display: '安装资料审核', name: 'install_data_check', hide:true},
        { display: '资料审查日期', name: 'data_check_date', hide:true},
        { display: '气瓶外观检查', name: 'device_surface_check', hide:true},
        { display: '外观检查日期', name: 'surface_check_date', hide:true},
        { display: '安装质量检查', name: 'install_quality_check', hide:true},
        { display: '安装质量检查日期', name: 'quality_check_date', hide:true},
        { display: '泄漏试验确认', name: 'leak_test_check', hide:true},
        { display: '泄漏试验日期', name: 'leak_check_date', hide:true},
        { display: '安装竣工资料审查', name: 'finish_data_check', hide:true},
        { display: '安装竣工资料审查日期', name: 'finish_check_date', hide:true},       
        { display: '鉴定项目编号', name: 'check_project_code', hide:true},
        { display: '设计属性', name: 'design_property', hide:true},
        { display: '审查属性', name: 'exam_property', hide:true},
        { display: '鉴定属性', name: 'check_property', hide:true},
        { display: '额定出力', name: 'rated_output', hide:true},
        { display: '设计热效率', name: 'design_heat', hide:true},
        { display: '稳定工况范围', name: 'work_range', hide:true},
        { display: '锅炉给水（回水）温度（℃）', name: 'use_temp2', hide:true},
        { display: '结构型式', name: 'struct_type', hide:true},
        { display: '设计燃料类型', name: 'design_fuel_type', hide:true},
        { display: '燃烧方式', name: 'burn_mode', hide:true},
        { display: '燃料低位发热量不低于（MJ/Kg） ', name: 'calorific_value', hide:true},
        { display: '燃烧机型号', name: 'burner_model', hide:true},       
        { display: '总图编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_ztbh', hide:true},
        { display: '水冷（壁）系统图或本体图编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_bttbh', hide:true},
        { display: '对流管束编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_dlgsbh', hide:true},
        { display: '锅筒、汽水分离器、储水罐编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_gtbh', hide:true},
        { display: '过热器编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_grqbh', hide:true},
        { display: '减温器编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_jwqbh', hide:true},
        { display: '省煤器编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_smqbh', hide:true},
        { display: '再热器编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_zrqbh', hide:true},
        { display: '有机热载体锅炉系统图编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_rylxttbh', hide:true},
        { display: '强度计算汇总表编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_qdjshzbbh', hide:true}, 
        { display: '主给水管道编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_zjsgdbh', hide:true},
        { display: '主蒸汽管道编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_zzqgdbh', hide:true},
        { display: '再热蒸汽冷段管道编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_zrzqldgdbh', hide:true},
        { display: '再热蒸汽热段管道编号/设计/审核/批准人员/批准日期和备注', name: 'glsjwj_jdqd_zrzqrdgdbh', hide:true},
        { display: '编号类型（单位内编号/产品编号/车牌号）', name: 'code_type', hide:true},
        { display: '编号值', name: 'code_value', hide:true},
        { display: '联系人', name: 'check_op', hide:true},
        { display: '联系电话', name: 'check_tel', hide:true},
        { display: '要求整定压力', name: 'yqzdyl', hide:true},
        { display: '校验方式', name: 'check_method', hide:true},
        { display: '校验介质', name: 'check_medium', hide:true},
        { display: '整定压力 ', name: 'zdyl', hide:true},
        { display: '密封试验压力', name: 'mfsyyl', hide:true}, 
        { display: '下次校验日期', name: 'last_inspection_date', hide:true},    
        { display: '报告编号', name: 'report_sn', hide:true},
        { display: '有关安全技术监察规程', width: '15%', name: 'safely_tech_standard', type: 'text', editor: { type: 'text'},required:false,maxlength:200},
        { display: '改造与重大修理项目', width: '15%', name: 'repair_project', type: 'text', editor: { type: 'text'},required:false,maxlength:1000},
        { display: '对安装单位质量体系运转情况的评价', width: '22%', name: 'install_evaluate', type: 'text', editor: { type: 'text'},required:false,maxlength:1000},
        { display: '记事', width: '10%', name: 'inspection_events', type: 'text', hide:true},
        { display: '备注、说明', width: '10%', name: 'remark', type: 'text', editor: { type: 'text'},required:false,maxlength:1000},
        { display: '本体耗钢量（吨）', name: 'weight_steels', hide:true},
        { display: '是否自编号', name: 'is_self_sn', hide:true},       
        { display: '检验员1', width: '8%', name: 'inspection_user_name1', type: 'text', editor: { type: 'text'},required:true,maxlength:20},
        { display: '检验员2', width: '8%', name: 'inspection_user_name2', type: 'text', editor: { type: 'text'},required:false,maxlength:20},
		{ display: '检验日期', width: '10%', name: 'inspection_date', editor: { type: 'date'},format:'yyyy-MM-dd',type:'date',required:true},		
		{ display: '应收金额', width: '10%', name: 'advance_fees',type: 'float',  editor: { type: 'float'}, required:false,maxlength:32}
    ];
	
    if(pageStatus!="detail"){
        columns.unshift({ display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='addNewRow(\"deviceInfos\")' title='增加'><span>增加</span></a>", isSort: false, width: '4%',height:'5%', render: function (rowdata, rowindex, value) {
            var h = "";
            if (!rowdata._editing) {
                h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"deviceInfos\")' title='删除'><span>删除</span></a> ";
            }
            return h;
        }
        });
    }

    deviceGrid = $("#deviceInfos").ligerGrid({
    	columns: columns,
    	enabledEdit: pageStatus!="detail" ,
        rownumbers: true,    
        height:"70%",
        width:"100%",
        //是否显示行序号
        frozenRownumbers: false,
        usePager: false,
        //onAfterEdit: f_onAfterEdit,
        data: {Rows: [
        ]}
    });
    gridConfig["deviceInfos"].manager=deviceGrid;
    
}

//编辑后事件 
function f_onAfterEdit(e){
	var grid=gridConfig["deviceInfos"].manager; 
	var data = grid.getSelectedRow();   
	alert(data.device_type_code);
	alert(e.column.name);
	grid.updateCell('device_name', data.device_type_code, e.record);
}

function addNewRow(name) {
	var grid=gridConfig[name].manager; 
	grid.addEditRow({}); //添加一行
}

function deleteRow(name) {
    var grid=gridConfig[name].manager;   //删除一行   
    var data = grid.getSelectedRow();   
    if(data.id==null||data.id==undefined||data.id==""){
    	grid.deleteSelectedRow();
    }else{
    	 $.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
             if (yes) {
                 $.getJSON(gridConfig[name]["delUrl"], {ids: data.id}, function (json) {
                     if (json.success) {
                         grid.deleteSelectedRow();
                     }
                 });
             }
         });
    }
}

var gridConfig={	
    deviceInfos:{manager:deviceGrid,delUrl:"inspection/zzjdinfo/del.do"}
};

function render(value,data){
    for (var i in data) {
    	if (data[i]["id"] == value)
        {
    		
        	return data[i]['text'];
        }
		if(data[i].children)
		{
			for(var j in data[i].children)
			{
				if(data[i].children[j]["id"] ==value)
					return data[i].children[j]['text'];
				if(data[i].children[j].children)
				{
					for(var k in data[i].children[j].children)
						if(data[i].children[j].children[k]["id"]==value)
						{
							return data[i].children[j].children[k]["text"];
						}
				}
			}
		}
    }
    return value;
}
