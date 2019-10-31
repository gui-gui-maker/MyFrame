<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>设备出库</title>
<%@ include file="/k/kui-base-form.jsp"%>
<%
	String status = request.getParameter("status");
%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/common/js/render.js"></script>
<script type="text/javascript" src="app/equipment/js/order.js"></script>
<script type="text/javascript">
	var pageStatus="${param.status}";
	var type="${param.type}"
	var deviceGrid;
	var columns=[];
	var boxIds = "";
	$(function(){
		defineColumns();
		initGrid();
		if(type=="loan"){
			tbar=[{text : '打印',id : 'print',icon : 'print',click : print},
					{text : '保存',id : 'save',icon : 'save',click : save}, 
					{text : '关闭',id : 'close',icon : 'cancel',click : close}];
		}else{
			tbar=[{text : '保存',id : 'save',icon : 'save',click : save}, 
					{text : '关闭',id : 'close',icon : 'cancel',click : close}];
		}
		var nowDate = new Date().format("yyyy-MM-dd");
		$("#borrow_draw_date").val(nowDate);
		$("#outstock_date").val(nowDate);
		$("#formObj").initForm({ //参数设置示例
			toolbar : tbar,
			toolbarPosition : "bottom",
			getSuccess : function(res) {
				if(res.success){
					deviceGrid.loadData({
						Rows : res.inspectionDatas
					});
					$("#formObj").setValues({id:res.id});
				}
			}
		}); 
		/* //回车事件绑定
	    $('#barcode').bind('keyup', function(event) {
	        if (event.keyCode == "13") {
	            //回车执行查询
	            searchByBarcode();
	        }
	    }); */
		$("#barcode").bind("input propertychange", function () { 
        	var bar_code = $("#barcode").val();
        	if(bar_code.length == 13){
        		searchByBarcode();
        	}
        });
		});
		//定义列表
		function defineColumns(){
			if(pageStatus=="outStock"){
				$.ajax({
		        	url: "equipment/Loan/detail1.do?id=${param.id}",
		            type: "POST",
		            success: function (resp) {
		                if (resp.success) {
		                	var baseEquipment2s = resp.baseEquipment2s;
		                	var equipmentBoxs = resp.equipmentBoxs;
		                	var equipmentLoan = resp.equipmentLoan;
		                	var jieyong = equipmentLoan.loanType;
		                	//获取到借用申请里面的归还时间并格式化
		                	var rTime  = equipmentLoan.repayTime;
		                	if(rTime!=null){
		                		var date1 = new Date((rTime.toString()).replace(/-/g, "/"));
		                		rTime = date1.format("yyyy-MM-dd");
		                	}else{
		                		$("#return_date").val("");
		                	}
		                	//依据类型给页面设值
		                	if(jieyong == "loan"){
		                		$("#outstock_type").val("01");
		                		$("#outstock_type1").val("借用");
		                		/* $("#return_date").val(equipmentLoan.repayTime); */
		                		/* $("#return_date").val(parseISO8601(equipmentLoan.repayTime)); */
		                		$("#return_date").val(rTime);
		                	}else if(jieyong == "lean"){
		                		$("#outstock_type").val("02");
		                		$("#outstock_type1").val("领用");
		                		$("#return_date").val(rTime);
		                	}else{
		                		$("#outstock_type").val("03");
		                		$("#outstock_type1").val("配置");
		                		$("#return_date").val(""); 
		        				$("#return_date").ligerGetTextBoxManager().setDisabled();
		                	}
		                	$("#apply_id").val(equipmentLoan.id);
		                	$("#borrow_draw_id").val(equipmentLoan.loanId);
		                	$("#borrow_draw_by").val(equipmentLoan.loanName);
		                	$("#outstock_position_id").val(equipmentLoan.depId);
		                	$("#outstock_position").val(equipmentLoan.depName);
		                	if(baseEquipment2s.length>0){
		                		//将设备显示在申请列表里面
			                	for(var e in baseEquipment2s){
			                		var bb = {id : baseEquipment2s[e].id,
			                				isBox: baseEquipment2s[e].box_status,
			                				eq_no : baseEquipment2s[e].eq_no,
			                				eq_name: baseEquipment2s[e].eq_name,
			                				eq_model : baseEquipment2s[e].eq_model,
			                				eq_manufacturer: baseEquipment2s[e].eq_manufacturer,
			                				eq_status: baseEquipment2s[e].eq_status};
			                				/* eq_use_status: baseEquipment2s[e].eq_use_status}; */
									deviceGrid.addRow(bb);
			                	}
		                	}
		                	if(equipmentBoxs.length>0){
		                		//取出箱子里面的设备，显示设备箱编号，并列出箱子中的设备
	                    		showBoxNumber(equipmentBoxs,true);
		                		for(var m in equipmentBoxs){
		                			var equipmentBoxId = equipmentBoxs[m].id;
		                			var baseEquipment2sInbox = equipmentBoxs[m].baseEquipment2s;
		                			for(var e in baseEquipment2sInbox){
		                				var baseEquipment2Inbox = baseEquipment2sInbox[e];
		                				var bb = {id : baseEquipment2Inbox.id,
		                						isBox: baseEquipment2Inbox.box_status,
				                				eq_no : baseEquipment2Inbox.eq_no,
				                				eq_name: baseEquipment2Inbox.eq_name,
				                				eq_model : baseEquipment2Inbox.eq_model,
				                				eq_manufacturer: baseEquipment2Inbox.eq_manufacturer,
				                				eq_status: baseEquipment2Inbox.eq_status};
				                				/* eq_use_status: baseEquipment2Inbox.eq_use_status}; */
										deviceGrid.addRow(bb);
		                			}
		                		}
		                	}
		                	
		                }else{
		                	$.ligerDialog.error('提示：' + data.msg);
		                }
		            },
		            error: function (data0,stats) {
		                $.ligerDialog.error('提示：' + data.msg);
		            }
		        });
			}
			var eq_status=<u:dict code="TJY2_EQ_STATUS" />  //获得状态类型码表
			/* var use_status=<u:dict code="TJY2_EQ_USE_STATUS" /> */
			columns.push({display: 'id', name: 'id', hide:true },
					{display: 'isBox', name: 'isBox', hide:true },
					{display: '设备名称',width: '20%',name: 'eq_name',type:'text',required:true},
					{display: '设备编号',width: '15%',name: 'eq_no',type: 'text',required:true},
					{display: '规格型号',width: '15%',name:'eq_model',type:'text',required:true},
					{display: '制造厂家',width: '20%',name: 'eq_manufacturer',type:'text',required:true},
					{display: '设备状态',width: '10%',name: 'eq_status',type:'text',required:true,
						 editor: { type: 'select',data:eq_status,ext:{emptyOption:false,selectBoxHeight:240}},
						 render:function(rowdata, rowindex, value){
							 for(var i in eq_status){
								 if(eq_status[i]["id"]==rowdata['eq_status']){
									 return eq_status[i]["text"];
								 }
							 }
							 return rowdata['eq_status'];
						 }
					 },
					/*  {display: '设备使用状态',width: '12%',name:'eq_use_status',type:'text',required:true,
						 editor: { type: 'select',data:use_status,ext:{emptyOption:false,selectBoxHeight:240}},
						 render:function(rowdata, rowindex, value){
							 for(var i in use_status){
								 if(use_status[i]["id"]==rowdata['eq_use_status']){
									 return use_status[i]["text"];
								 }
							 }
							 return rowdata['eq_use_status'];
						 }
					 }, */
					 {display: '确认',width: '5%',name: 'check',type:'text',required:true,
						 render:function(rowData){
							 if(rowData.check==true){
								 return "<img class='l-icon-ok' style='height:18px;width:18px;padding:1.5px;'/>";
							 }
						 }}
			);
		}
		function initGrid() {
	        deviceGrid = $("#device").ligerGrid({
	    	columns: columns,
	    	enabledEdit: pageStatus=="outStock",
	    	clickToEdit: true,
	    	rownumbers : true,
		    width:"99.6%",
		    frozenRownumbers: false,
		    usePager: false,
		    data: {Rows: []}
	    });
	}
		function f_onBeforeEdit(e)
		{ 
			if(e.record.is_report_input == "2" ){
					return false;
					}else{
						return true;
					}
				}
		function save(){
			var apply_id = $("#apply_id").val();
			url = "equipOutstockRecordAction/outstock.do?apply_id="+apply_id; 
			//验证表单数据
			if($('#formObj').validate().form()){
				var formData = $("#formObj").getValues();
		        var data = {};
		        data = formData;
		        //验证grid
		        if(!validateGrid(deviceGrid)){
					return false;
				}
		        //验证部门ID是否存在
		        if($("#outstock_position").val() != "" && $("#outstock_position").val() != undefined){
		            if($("#outstock_position_id").val() == "" || $("#outstock_position_id").val() == undefined){
		                $.ligerDialog.warn("部门id为空，请重新选择部门！");
		                return false;
		            }
		          }
		        //验证借用/领用人ID
		        if($("#borrow_draw_by").val() != "" && $("#borrow_draw_by").val() != undefined){
		            if($("#borrow_draw_id").val() == "" || $("#borrow_draw_id").val() == undefined){
		                $.ligerDialog.warn("领用/借用人id为空，请重新选择领用/借用人！");
		                return false;
		            }
		          }
		        
		        data["baseEquipment2s"] = deviceGrid.getData();
		        var equipmentBoxs = $("#formObj").data("box_td");
		        data["equipmentBoxs"] = equipmentBoxs;
		        $("body").mask("正在保存数据，请稍后！");
		        $.ajax({
		        	url: url,
		            type: "POST",
		            datatype: "json",
		            contentType: "application/json; charset=utf-8",
		           	data:$.ligerui.toJSON(data),
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
		function close(){	
			 api.close();
		}
		function addDevice() {
			 dia1 = $.ligerDialog.open({ 
			 title: '选择', 
			 width: 700, 
			 height: 500
			 ,parent:api, 
			 url: 'app/equipment/base/equipment_outstock_list1.jsp',
			 data: {"window" : window},
			 buttons: [
			    { text: '确定', onclick: f_importOK },
				{ text: '取消', onclick: f_importCancel }
				]
			 });
		}
	function f_importOK(item, dialog){
		var rows = dialog.frame.f_select();
	    if (!rows){
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
	    		/* var tt = {id : rows[i].id, eq_name : rows[i].eq_name, eq_no: rows[i].eq_no, eq_model : rows[i].eq_model, eq_manufacturer: rows[i].eq_manufacturer, eq_status: rows[i].eq_status, eq_use_status: rows[i].eq_use_status}; */
				var tt = {id : rows[i].id, eq_name : rows[i].eq_name, eq_no: rows[i].eq_no, eq_model : rows[i].eq_model, eq_manufacturer: rows[i].eq_manufacturer, eq_status: rows[i].eq_status};
	    		deviceGrid.addRow(tt);
	    	
	    	}
	    }
	    dialog.close();
		}
	//显示设备箱编号
	function showBoxNumber(newBoxs,isNew){
		var boxs = $("#form1").data("box_td");
		$.each(newBoxs,function(){
			$("#box_td").prepend('<span class="label label-read" id="' + (isNew?this.id:this.id) + '"><span class="text">' + this.boxNumber + '&nbsp;&nbsp;</span></span>');
		});
		if(boxs)
			boxs = boxs.concat(newBoxs);
		else
			boxs = newBoxs;
		$("#form1").data("box_td",boxs);
	}
	//删除全局变量中的ID
	function dealBoxIds(delId){
		var boxId = boxIds.split(",");
		var newboxIds = "";
		for(n in boxId){
 			if(delId != boxId[0]){
 				newboxIds+=boxId[0]+",";
 			}
		}
		boxIds = newboxIds.substring(0, newboxIds.length-1);
	} 
		function f_importCancel(item, dialog){
			dialog.close();
		}		
				
		function delDevice(obj, index) {
			var row = obj.getData();
			if(row[index].box_status == "01"){
				$.ligerDialog.warn("此设备已装箱，不能单独删除！");
			} else {
				obj.deleteRow(index);
			}
		}
		//归还日期控制
		function valueChange(val,text){
			var backtime = new Date().format("yyyy-MM-dd");
			if(text=="配置"){
				$("#return_date").val(""); 
				$("#return_date").ligerGetTextBoxManager().setDisabled();
			}else{
				$("#return_date").ligerGetTextBoxManager().setEnabled();
				$("#return_date").val(backtime); 
			}
		}
		//日期格式转换
		function parseISO8601(dateStringInRange) {
	   		var isoExp = /^\s*(\d{4})-(\d\d)-(\d\d)\s*$/,
	       	date = new Date(),
	       	parts = isoExp.exec(dateStringInRange);

	   		if(parts) {
		     	month = +parts[2];
		     	date.setFullYear(parts[1], month - 1, parts[3]);
		     	if(month != date.getMonth() + 1) {
		       		date.setTime(NaN);
		     	}
	   		}
	   		return date;
 		}

		//借用/领用人选择
		function choosePerson(){
            top.$.dialog({
                width: 800,
                height: 450,
                lock: true,
                parent: api,
                title: "选择人员",
                content: 'url:app/common/person_choose.jsp',
                cancel: true,
                ok: function(){
                    var p = this.iframe.contentWindow.getSelectedPerson();
                    if(!p){
                        top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                        return false;
                    }
                    $("#borrow_draw_id").val(p.id);
                    $("#borrow_draw_by").val(p.name);
                    $("#outstock_position_id").val(p.org_id);
                    $("#outstock_position").val(p.org_name);
                }
            });
        }
		//通过条形码查找设备信息
		function searchByBarcode(){
			var barcode = $("#barcode").val();
			//获取列表中的设备
			var devRow = deviceGrid.rows;
			if(barcode.length == 13){
		        $.ajax({
		        	url: "equipment2Action/searchByBarcode.do",
		            type: "POST",
		           	data: "barcode="+barcode+"&type=outstock",
		            success: function (resp) {
		                if (resp.success) {
		                	var baseEquipment1 = resp.baseEquipment;
		                	try{
				  				//验证列表中是否已存在此设备
					        	var isexist=false;
					        	var lsid = baseEquipment1.id;
						    	for(var i in devRow){
						    		if(devRow[i].id == lsid){
						    			isexist = true;
						    			devRow[i].check = true;
						    			deviceGrid.reRender();
						    			break;
						    		}else{
						    			isexist = false;
						    		}
						    	}
						    	if(!isexist){
						    		 $.ligerDialog.error("该设备不在申请表中！");
						    	}
				 			 } catch(e){}
		                }else{
		                	/* $.ligerDialog.error('提示：' + data.msg); */
		                	$.ligerDialog.error('提示：' + resp.message); 
		                }
		            },
		            error: function (data0,stats) {
		                $.ligerDialog.error('提示：' + data.msg);
		            }
		        });
		       
		        
			}else if(barcode == "" || barcode.length == 0){
				
				$.ligerDialog.warn("码值不能为空！");
			}else{
				$.ligerDialog.error("此条码不合法！");
			}
			$("#barcode").val("").focus();
		}
		function print(){
			var id = "${param.id}";
			top.$.dialog({
				width:900,
				height:630,
				lock:true,
				title:"打印设备借用登记表",
				content: 'url:app/equipment/apply/equipment_borrow_print.jsp?status=detail&id=' + id,
				data:{window:window}
			});
		}
</script>
</head>
<body>
	<div title="出库信息" tabId="basicTab" style="height: 400px">
	<form name="formObj" id="formObj" method="post" action="equipOutstockRecordAction/outstock.do"
		getAction="equipment2Action/getList.do?id=${param.ids}">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>出库基本信息</div>
			</legend>
			<table id="baseinfo" cellpadding="3" cellspacing="0" class="l-detail-table">
			<input  type="hidden" id="borrow_draw_id" name="borrow_draw_id"/>
			<input  type="hidden" id="apply_id" name="apply_id"/>
			<input  type="hidden" id="outstock_position_id" name="outstock_position_id"/>
			<input  type="hidden" id="create_by" name="create_by"/>
	  		<input  type="hidden" id="create_date" name="create_date"/>
				<tr>
					<td class="l-t-td-left">出库类型</td>
					<td class="l-t-td-right">
					<%-- <u:combo name="outstock_type" code="TJY2_EQUIP_OUTSTOCK_TYPE" validate="required:true"/></td> --%>
					<input type="hidden" ltype="text"  name="outstock_type" id="outstock_type"  type="text"/>
					<input readonly="readonly" name="outstock_type1" id="outstock_type1"  type="text" ltype="text" />
					<%-- <td class="l-t-td-left">出库登记人</td>
					<td class="l-t-td-right">
						<input name="outstock_by" id="outstock_by" type="text" ltype='text' validate="{disabled:true,maxlength:32}" 
						validate="{required:false,maxlength:64}" value="<sec:authentication property="principal.name"/>"/>
					</td> --%>
					<td class="l-t-td-left">借用/领用人</td>
					<td class="l-t-td-right">
						<input readonly="readonly" id="borrow_draw_by" name="borrow_draw_by" type="text" ltype='text'/>
						<!-- <input  ltype='text' id="borrow_draw_by" name="borrow_draw_by" type="text" id="Reviewers" validate="required:true" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/></td> -->
					</td> 
				</tr>
				<tr>
					<td class="l-t-td-left">出库部门</td>
					<td class="l-t-td-right">
						<input readonly="readonly" ltype="text"  name="outstock_position" id="outstock_position"  type="text"/>
						<!-- <input  validate="{maxlength:50,required:true}" ltype="text"  name="outstock_position" id="outstock_position" type="text" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"/></td> -->
					</td> 
					<td class="l-t-td-left">预计归还日期</td>
					<td class="l-t-td-right">
						<input readonly="readonly" name="return_date" id="return_date" type="text"/></td>
				</tr>
				<tr>	
					<td class="l-t-td-left" id = "constrolDate" name = "constrolDate">借用/领用/配置时间</td>
					<td class="l-t-td-right">
						<input name="borrow_draw_date" id="borrow_draw_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>出库设备信息</div>
			</legend>
			<table id="eqinfo" cellpadding="1" cellspacing="0" class="l-detail-table">
				 <tr>
		              <td class="l-t-td-left" style="width: 60px;">设备条形码</td>
				      <td class="l-t-td-right"><input name="barcode" id="barcode" type="text" ltype='text' validate="{maxlength:64}"/></td>
				      <td class="l-t-td-left"></td>
					  <td class="l-t-td-right"></td>  
			    </tr>
				 <tr>
		              <td class="l-t-td-left">设备箱</td>
			          <td class="l-t-td-right" id="box_td">
			        </td>
			    </tr>
			</table>
			<div id="device"></div>
		</fieldset>
	</form>
	</div>
	<!-- 页面加载完，条形码文本输入框自动获取焦点 -->
	<script type="text/javascript">
		setTimeout( function(){
  			try{
   				 document.getElementById('barcode').focus();
 			 } catch(e){}
				}, 1000);
		</script>
</body>
</html>
