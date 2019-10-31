<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<% SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime="";
	nowTime = dateFormat.format(new Date());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>检验设备箱信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="app/finance/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var pageStatus="${param.pageStatus}";
var deviceGrid;
var columns=[];
$(function(){
	defineColumns();
	initGrid();
	
	$('input[name="templateName"]').autocomplete("equipment/box/getBaseReports.do", {
		max: 20,    //列表里的条目数
        minChars: 1,    //自动完成激活之前填入的最小字符
        width: 300,     //提示的宽度，溢出隐藏
        scrollHeight: 300,   //提示的高度，溢出显示滚动条
        scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
        autoFill: false,    //自动填充
        formatItem: function(row, i, max) {
            return row.text;
        },
        formatMatch: function(row, i, max) {
            return row.text;
        },
        formatResult: function(row) {
            return row.text;
        }
    }).result(function(event, row, formatted) {
    	$("#form1").setValues({"fkTemplateId":row.id});
    });
	
	

	$('#boxDepName').autocomplete("employee/basic/searchOrg.do", {
        max: 12,    //列表里的条目数
        minChars: 1,    //自动完成激活之前填入的最小字符
        width: 200,     //提示的宽度，溢出隐藏
        scrollHeight: 300,   //提示的高度，溢出显示滚动条
        scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
        autoFill: false,    //自动填充
        formatItem: function(row, i, max) {
            return row.orgName;
        },
        formatMatch: function(row, i, max) {
            return row.orgName;
        },
        formatResult: function(row) {
            return row.orgName;
        }
    }).result(function(event, row, formatted) {
    	$("#form1").setValues({"boxDepId":row.orgId});
       
    }); 
	
	
	$("#form1").initForm({ //参数设置示例
		toolbar : [ {
			text : '保存',
			id : 'save',
			icon : 'save',
			click : save
		}
		, 
		{
			text : '关闭',
			id : 'close',
			icon : 'cancel',
			click : function close(){	
					 	api.close();
					}
		} ],
		getSuccess : function(res) {
			if(res.success){
				deviceGrid.loadData({
					Rows : res.data["baseEquipment2s"]
				});
				$("#form1").setValues({id:res.id});
				$("#form1").setValues(res.data);
			}
		}
	});
	});
	
	
function chooseOrg(){
    top.$.dialog({
        width: 800,
        height: 450,
        lock: true,
        parent: api,
        title: "选择部门",
        content: 'url:app/common/org_choose.jsp',
        cancel: true,
        ok: function(){
            var p = this.iframe.contentWindow.getSelectedPerson();
            if(!p){
                top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                return false;
            }
            $("#boxDepId").val(p.id);
            $("#boxDepName").val(p.name);
        }
    });
}   
	
	
	function defineColumns(){
		//var columns=[];
		if(pageStatus!="detail"){
			columns.push({ display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='javascript:addDevice()' title='增加'><span>增加</span></a>", isSort: false, width: '30',height:'5%', render: function (rowdata, index, value ) {
				var h = "";
				if (!rowdata._editing) {
					h += "<a class='l-a l-icon-del' href='javascript:delDevice(deviceGrid,"+index+")' title='删除'><span>删除</span></a> ";
				}
				
				return h;
			}
			});
			
		}
		
		var eq_status=<u:dict code="TJY2_EQ_STATUS" />  //获得状态类型码表
		var eq_category=<u:dict code="TJY2_EQ_CATEGORY" />  //获得类别码表
		var eq_use_status=<u:dict code="TJY2_EQ_USE_STATUS" /> 
		columns.push({display: 'id', name: 'id', hide:true},
				{display: '出厂编号',name:'eq_sn',hide:true},
				{display: '供应商ID',name:'eq_supplier_id',hide:true},
				{display: '精度',name:'eq_accurate',hide:true},
				{display: '所在位置',name:'eq_department',hide:true},
				{display: '领用/借用人',name:'eq_user',hide:true},
				{display: '领用/借用时间',name:'eq_draw_date',hide:true},
				{display: '归还时间',name:'eq_return_date',hide:true},
				{display: '设备单价',name:'eq_buy_price',hide:true},
				{display: '购置时间',name:'eq_buy_date',hide:true},
				{display: '设备购买数量',name:'eq_buy_count',hide:true},
				{display: '创建时间',name:'create_date',hide:true},
				{display: '创建时间',name:'create_by',hide:true},
				{display: '最后更新时间',name:'last_modify_date',hide:true},
				{display: '最后更新人',name:'last_modify_by',hide:true},
				{display: '设备使用部门',name:'eq_use_department',hide:true},
				{display: '入库时间',name:'eq_instock_date',hide:true},
				{display: '备注',name:'remark',hide:true},
				{display: '报废时间',name:'eq_scrap_date',hide:true},
				{display: '停用时间',name:'eq_stop_date',hide:true},
				{display: '维修时间',name:'eq_repair_date',hide:true},
				{display: '档案分类号',name:'eq_archive_class_id',hide:true},
				{display: '制造时间',name:'eq_produce_date',hide:true},
				{display: '检定周期',name:'eq_check_cycle',hide:true},
				{display: '设备箱外键ID',name:'FK_UV_ID',hide:true},
				{display: '设备条形码',name:'barcode',hide:true},
				{display: '出入库状态',name:'eq_inout_status',hide:true},
				{display: '借用/领用人外键ID',name:'fk_borrow_draw_id',hide:true},
				{display: '装箱与否',name:'box_status',hide:true},
				{display: '设备所在位置ID',name:'eq_departmentid',hide:true},
				{display: '设备使用部门id',name:'eq_use_departmentid',hide:true},
				{display: '条形码',name:'barcode',type:'text',hide:true},
					 {display: 'equipmentBox',name:'equipmentBox',hide:true},
					 {display: 'equipmentLoan',name:'equipmentLoan',hide:true},
					 {display: '设备名称',width: '20%',name: 'eq_name',type:'text',required:false},
					 {display: '设备编号',width: '12%',name: 'eq_no',type: 'text',required:false},
					 {display: '规格型号',width: '10%',name:'eq_model',type:'text',required:false},
					 {display: '制造厂家',width: '25%',name:'eq_manufacturer',type:'text',required:false},
					 {display: '检定日期',width: '10%',name:'eq_execute_date',type:'date',required:false},
					 {display: '下次检定日期',width: '10%',name:'eq_next_check_date',type:'date',required:false},
					 {display: '设备状态',width: '10%',name: 'eq_status',type:'text',required:false,
						 render:function(rowdata, rowindex, value){
							 for(var i in eq_status){
								 if(eq_status[i]["id"]==rowdata['eq_status']){
									 return eq_status[i]["text"];
								 }
							 }
							 return rowdata['eq_status'];
						 }
					 },
					 {display: '类别',width: '10%',name: 'eq_category',type:'text',required:false,
						 render:function(rowdata, rowindex, value){
							 for(var i in eq_category){
								 if(eq_category[i]["id"]==rowdata['eq_category']){
									 return eq_category[i]["text"];
								 }
							 }
							 return rowdata['eq_category'];
						 }
					 }/* ,
					 {display: '设备使用状态',width: '15%',name:'eq_use_status',type:'text',required:false,
						 render:function(rowdata, rowindex, value){
							 for(var i in eq_use_status){
								 if(eq_use_status[i]["id"]==rowdata['eq_use_status']){
									 return eq_use_status[i]["text"];
								 }
							 }
							 return rowdata['eq_use_status'];
						 }
					 } */
		);
	}
	
	
	function initGrid() {
        deviceGrid = $("#device").ligerGrid({
    	columns: columns,
        enabledEdit: pageStatus!="detail",
        clickToEdit: true,
        rownumbers: true,    
        width:"99%",
        frozenRownumbers: false,
        usePager: false,
        data: {Rows: []}
   	  });
	}
	function f_onBeforeEdit(e){ 
		//for(var i in e){alert(i)}
		if(e.record.is_report_input == "2" ){
				return false;
				}else{
					return true;
				}
			}
		
	
	function save(){
		
		//验证表单数据
		if($('#form1').validate().form()){
			
			var formData = $("#form1").getValues();
	        var data = {};
	        data = formData;
	        //验证grid
	        if(!validateGrid(deviceGrid)){
				return false;
			}
	        var Length = 0;
	        for (var item in deviceGrid.getData()) {
	            Length++;
	        }
	        /* if(Length>6){
	        	$.ligerDialog.warn('一个设备箱最多放6个设备！');
	        	return false;
	        } */
	        if(Length<1){
	        	$.ligerDialog.warn('请至少添加一个设备！');
	        	return false;
	        }
	        
	        data["baseEquipment2s"] = deviceGrid.getData();
	        if($("#boxDepName").val() != "" && $("#boxDepName").val() != undefined){
	           	if($("#boxDepId").val() == "" || $("#boxDepId").val() == undefined){
	                $.ligerDialog.warn("部门id为空，请重新选择部门！");
	                 return;
	             }
	           }
	         
	        $("body").mask("正在保存数据，请稍后！");
	        $.ajax({
	            url: "equipment/box/saveEqui.do",
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	           	data: $.ligerui.toJSON(data),
	            success: function (data, stats) {
	            	$("body").unmask();
	                if (data["success"]) {
	                	if(api.data.window.Qm){
	                		api.data.window.Qm.refreshGrid();
	                	}
	                    top.$.dialog.notice({content:'保存成功'});
	                    api.close();
	                }else{
	                	$.ligerDialog.error('提示：' + data.msg);
	                	$("#save").removeAttr("disabled");
	                }
	            },
	            error: function (data,stats) {
	            	$("body").unmask();
	                $.ligerDialog.error('提示：' + data.msg);
	                $("#save").removeAttr("disabled");
	            }
	        });
		}
	}
	
	
	
	function addDevice() {
		 dia1 = $.ligerDialog.open({ 
		 title: '选择', 
		 width: 700, 
		 height: 500
		 ,parent:api, 
		 url: 'app/equipment/base/equipment_devBox_list.jsp',
		 data: {"window" : window},
		 buttons: [
		    { text: '确定', onclick: f_importOK },
			{ text: '取消', onclick: f_importCancel }
		]
		                                                                                                
		 });
	}
	
	function f_importOK(item, dialog){
		var rows = dialog.frame.f_select();
	    if (!rows) {
	    	top.$.notice("请选择行！");
	        return;
	    } 
	    var devRow = deviceGrid.rows;
	    var isexist=false;
	  
	    for(var i in rows){
	    	for(var j in devRow){
	    		if(rows[i].id == devRow[j].id){
	    			isexist = true;
	    			break;
	    		}else{
	    			isexist = false;
	    		}
	    	}
	    	if(!isexist){
	    		var tt = {id : rows[i].id, 
	    					eq_no: rows[i].eq_no, 
	    					eq_name : rows[i].eq_name, 
	    					eq_model : rows[i].eq_model,
	    					eq_sn : rows[i].eq_sn,
	    					eq_manufacturer :rows[i].eq_manufacturer,
	    					eq_supplier_id : rows[i].eq_supplier_id,
	    					eq_status : rows[i].eq_status,
	    					eq_category : rows[i].eq_category,
	    					eq_use_status : rows[i].eq_use_status,
	    					eq_accurate : rows[i].eq_accurate,
	    					eq_next_check_date : rows[i].eq_next_check_date,
	    					eq_department : rows[i].eq_department,
	    					eq_user : rows[i].eq_user,
	    					eq_draw_date : rows[i].eq_draw_date,
	    					eq_return_date : rows[i].eq_return_date,
	    					eq_buy_price : rows[i].eq_buy_price,
	    					eq_buy_date : rows[i].eq_buy_date,
	    					eq_buy_count : rows[i].eq_buy_count,
	    					create_date : rows[i].create_date,
	    					create_by : rows[i].create_by,
	    					last_modify_date : rows[i].last_modify_date,
	    					last_modify_by : rows[i].last_modify_by,
	    					eq_use_department : rows[i].eq_use_department,
	    					eq_instock_date : rows[i].eq_instock_date,
	    					remark : rows[i].remark,
	    					eq_scrap_date : rows[i].eq_scrap_date,
	    					eq_stop_date : rows[i].eq_stop_date,
	    					eq_repair_date : rows[i].eq_repair_date,
	    					eq_archive_class_id : rows[i].eq_archive_class_id,
	    					eq_produce_date : rows[i].eq_produce_date,
	    					eq_check_cycle : rows[i].eq_check_cycle,
	    					eq_execute_date : rows[i].eq_execute_date,
	    					FK_UV_ID : rows[i].FK_UV_ID,
	    					barcode : rows[i].barcode,
	    					eq_inout_status : rows[i].eq_inout_status,
	    					fk_borrow_draw_id : rows[i].fk_borrow_draw_id,
	    					box_status : rows[i].box_status,
	    					eq_use_departmentid : rows[i].eq_use_departmentid,
	    					eq_departmentid : rows[i].eq_departmentid,

	    					equipmentBox : rows[i].equipmentBox,
	    					equipmentLoan : rows[i].equipmentLoan
	    				};
				deviceGrid.addRow(tt);
	    	
	    	}
	    }
	   		 dialog.close();
	}
	
	function f_importCancel(item, dialog){
		dialog.close();
	}		
		
	/*
	function delDevice(obj, index) {
	alert(obj.id);
	//$.post("equipment/box/XXXX.do?", {id:设备ID}, function(resp) {
	//			if (resp.success) {
	//				
	//			}else{
	//				$.ligerDialog.error("获取设备箱信息失败！");
	//				return;
	//			}
	//		});
			
	//	obj.deleteRow(index);
	}
	*/
	
	function delDevice(obj, index) {
		//var grid=gridConfig[name].manager;
		var data = obj.getSelectedRow();
		var dataId = data.id;
			$.ligerDialog.confirm("确定要移除该设备吗？", function(yes) {
			if (yes) {
				obj.deleteRow(index);
			}
		});

	}
	function queryBox(){
		var boxNumber=$("#boxNumber").val();
		
	}
	//判断设备箱号是否重复
	function queryBox(){
		var boxNumber=$("#boxNumber").val();
		if(boxNumber == ""||boxNumber == "undefined"){
			$.ligerDialog.alert("请输入设备箱号！");
		}else{
			$.ajax({
	        	url: "equipment/box/queryBox.do?boxNumber="+boxNumber,
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            success: function (resp) {
	            	var num = resp.num;
	            	if(num>0){
	            		$.ligerDialog.alert("此设备箱号已存在，请更换设备箱号！");
	            	}
	            },
	            error: function (data) {
	            	$.ligerDialog.alert("设备箱号验证失败！");
	            }
	        });
		}
	}
</script>
</head>
<body>
	<form id="form1" action = "equtpment/box/saveEqui.do"  getAction = "equipment/box/detail.do?id=${param.id}" >
		<input type="hidden" name="id" id="id" value="${param.id}"/>
		<input type="hidden" name="boxDepId" id="boxDepId" value=""/>
			<input type="hidden" name="fkTemplateId" id="fkTemplateId" value=""/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					检验设备箱创建
				</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">设备箱编号</td>
					<td class="l-t-td-right"><input id="boxNumber" name="boxNumber" type="text" ltype="text" validate="{required:true,	maxlength:32}" onblur="queryBox()"/></td>
					<td class="l-t-td-left">部门名称</td>
					<td class="l-t-td-right"><input  validate="{maxlength:50,required:true}"  readonly="readonly" ltype="text"  name="boxDepName" id="boxDepName"  type="text" onclick="chooseOrg();" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"/></td>
					<!-- <td class="l-t-td-right"><input id="boxDepName" name="boxDepName" type="text" ltype="text" validate="{required:true}" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}" /></td> -->
				</tr>
				<tr>
					<td class="l-t-td-left">装箱时间</td>
					<td class="l-t-td-right"><input name="boxTime" type="text" ltype="date" validate="{required:false}" 
        					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="boxTime"  
        					 readonly="readonly" value="<%=nowTime%>" /></td>
					<td class="l-t-td-left">报告类型</td>
					<td class="l-t-td-right"><input name="templateName"  id="templateName" type="text" ltype="text" validate="{maxlength:32}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">备注</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="remark" id="remark" rows="5" cols="25" class="l-textarea" validate="{maxlength:200}"></textarea>
					</td>
					
				</tr>
			</table>
	</fieldset>
	
	<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					检验设备装箱
				</div>
			</legend>
			<div id="device"></div>
	</fieldset>
	</form>
</body>
</html>