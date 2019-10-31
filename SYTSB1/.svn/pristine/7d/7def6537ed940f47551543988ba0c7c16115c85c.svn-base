<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    CurrentSessionUser user=(CurrentSessionUser)request.getSession().getAttribute("currentSessionUser");

%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--获取当前登录人  -->
<%CurrentSessionUser useres = SecurityUtil.getSecurityUser();
String users=useres.getName();
String userid= useres.getId();%>
<head pageStatus="${param.status}">
<title>设备入库</title>
<%@ include file="/k/kui-base-form.jsp"%>
<%
	String status = request.getParameter("status");
%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/common/js/render.js"></script>
<script type="text/javascript" src="app/equipment/js/order.js"></script>
<script type="text/javascript">
	var pageStatus="${param.status}";
	var deviceGrid;
	var columns=[];
	var boxIds = "";
	$(function(){
		defineColumns();
		initGrid();
		$("#tr_manager").hide();
		var nowDate = new Date().format("yyyy-MM-dd");
		$("#instock_date").val(nowDate);
		$("#back_time").val(nowDate); 
		$("#formObj").initForm({ //参数设置示例
			toolbar : [
				{text : '保存',id : 'save',icon : 'save',click : save}, 
				{text : '关闭',id : 'close',icon : 'cancel',click : close}
				],
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
			if(pageStatus=="inStock"){
				columns.push({
					display: "<a class='l-a iconfont l-icon-add' iconfont href='javascript:void(0);' onclick='javascript:addDevice()' title='增加'><span>增加</span></a>",
					isSort: false, 
					width: '30',
					height:'5%', 
					render: function (rowdata, index, value) {
					var h = "";
					if (!rowdata._editing) {
						h += "<a class='l-a l-icon-del' href='javascript:delDevice(deviceGrid,"+index+")' title='删除'><span>删除</span></a> ";
					}
					return h;
				}
				});
			}
			var eq_status=<u:dict code="TJY2_EQ_STATUS" />  //获得状态类型码表
			var use_status=<u:dict code="TJY2_EQ_USE_STATUS" />
			columns.push({display: 'id', name: 'id', hide:true},
					{display: 'isBox', name: 'isBox', hide:true},
					{display: '设备名称',width: '100',name: 'eq_name',type:'text',required:false},
					{display: '设备编号',width: '100',name: 'eq_no',type: 'text',required:false},
					{display: '规格型号',width: '100',name:'eq_model',type:'text',required:false},
					{display: '制造厂家',width: '190',name: 'eq_manufacturer',type:'text',required:false},
					{display: '设备状态',width: '100',name: 'eq_status',type:'text',required:false,
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
					 {display: '设备使用状态',width: '100',name:'eq_use_status',type:'text',required:false,
						 editor: { type: 'select',data:use_status,ext:{emptyOption:false,selectBoxHeight:240}},
						 render:function(rowdata, rowindex, value){
							 for(var i in use_status){
								 if(use_status[i]["id"]==rowdata['eq_use_status']){
									 return use_status[i]["text"];
								 }
							 }
							 return rowdata['eq_use_status'];
						 }
					 }
			);
		}
		function initGrid() {
	        deviceGrid = $("#device").ligerGrid({
	    	columns: columns,
	        enabledEdit: pageStatus=="inStock",
	    	clickToEdit: true,
	    	rownumbers : true,
	    	height:"99%",
		    width:"99%",
		    frozenRownumbers: false,
		    usePager: false,
	        dataAction:"local",
				url : 'equipment2Action/getList.do?id=${param.id}'
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
			url = "equipInstockRecordAction/instock.do"; 
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
		        if($("#instock_position").val() != "" && $("#instock_position").val() != undefined){
		            if($("#instock_position_id").val() == "" || $("#instock_position_id").val() == undefined){
		                $.ligerDialog.warn("部门id为空，请重新选择部门！");
		                return;
		            }
		          }
		        //验证归还人ID
		        if($("#back_user_name").val() != "" && $("#back_user_name").val() != undefined){
		            if($("#back_user_id").val() == "" || $("#back_user_id").val() == undefined){
		                $.ligerDialog.warn("归还人id为空，请重新选择归还人！");
		                return;
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
		function close(){	
			 api.close();
		}
		function addDevice() {
			 dia1 = $.ligerDialog.open({ 
			 title: '选择', 
			 width: 850, 
			 height: 500,
			 parent:api, 
			 url: 'app/equipment/base/equipment_instock_list1.jsp',
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
	    		var tt = {id : rows[i].id, eq_name : rows[i].eq_name, eq_no: rows[i].eq_no, eq_model : rows[i].eq_model, eq_manufacturer: rows[i].eq_manufacturer, eq_status: rows[i].eq_status, eq_use_status: rows[i].eq_use_status};
				deviceGrid.addRow(tt);
	    	
	    	}
	    }
	    dialog.close();
		}
	function f_importBoxOK(item, dialog){
		var rows = dialog.frame.f_select();
	   if (!rows){
	   	top.$.notice("请选择行！");
	       return;
	   } 
	   
	   var boxId="";
	   var boxs=[];
	   var eqNames="";
	   
	   for(var i in rows){
			if(boxIds.indexOf(rows[i].id)==-1){
				boxId = boxId+rows[i].id+",";
			} else {
				eqNames = eqNames+rows[i].box_number+",";
			}
	   }
	   
	   if(eqNames != ""){
			$.ligerDialog.warn("设备箱："+"<span style='color:red;'>"+eqNames.substring(0,eqNames.length-1)+"</span>"+" 已存在！");
  		}
	   boxIds = boxIds+boxId;
	   var devRow = deviceGrid.rows;
	   var isexist=false;
	   
	   if(boxId!=""){
		   //获取设备箱里的设备信息
		   $.post("equipment/box/getBoxByid.do?", {id:boxId.substring(0,boxId.length-1)}, function(resp) {
				if (resp.success) {
					var boxRows = resp.data;//用于装载设备箱的设备信息
					var boxs1 = resp.data1;//用于装载设备箱的基本信息
					if(boxRows=="" || boxRows==undefined){
						$.ligerDialog.error("该设备箱没有设备！");
						return;
					}
					for(var i in boxRows){
		   				for(var j in devRow){
		   					if(boxRows[i].id == devRow[j].id){
		   						isexist = true;
		   						break;
		   					}else{
		   						isexist = false;
		   					}
		  		 		}
		  			 	if(!isexist){
		   					var tt = {id : boxRows[i].id,
		   						box_status : "01",
								eq_no: boxRows[i].eq_no, 
			   					eq_name : boxRows[i].eq_name,
								eq_model : boxRows[i].eq_model,
			   					eq_status : boxRows[i].eq_status,
			   					eq_use_status : boxRows[i].eq_use_status};
								deviceGrid.addRow(tt);
							
		   				}
		   			}
					//将设备箱ID、编号和 设备装入boxs
					for(var k in boxs1){
						boxs.push({
							types : "box",
							id: boxs1[k].id,
							box_number: boxs1[k].boxNumber,
							equips : boxs1[k].baseEquipment2s 
						});
					}
					addBox(boxs,true);
		 			dialog.close();
				}else{
					$.ligerDialog.error("获取设备箱信息失败！");
					return;
				}
			});
			}
	}
	//添加设备箱
	function addBox(newBoxs,isNew){
		var boxs = $("#formObj").data("box_td");
		$.each(newBoxs,function(){
			$("#box_td").prepend(
				'<span class="label label-read" id="' + (isNew?this.id:this.id) + '"><span class="text">' + this.box_number + 
				'<span class="label label-read" equips="' + this.equips + '">' +
				'</span><span class="del btn btn-lm" onclick="deleteBox(\'' + (isNew?this.id:this.id) + '\',' + isNew + ')"><img src="k/kui/images/icons/16/delete1.png">&nbsp;</span></span>');
		});
		if(boxs)
			boxs = boxs.concat(newBoxs);
		else
			boxs = newBoxs;
		$("#formObj").data("box_td",boxs);
	}
	
	//删除设备箱
	function deleteBox(delId,isNew){
		data1 = deviceGrid.getData();
		var boxs = $("#formObj").data("box_td");
		//查看箱子里面的信息
		for(var h in boxs){
			var id = boxs[h].id;
			if(delId!=id){
				continue;
			}
			var equips = boxs[h].equips;//箱子中的设备
			//移除箱子中列在表里的设备
			for (var t in equips ){
				var data = deviceGrid.getData();//所有设备
				for(var m in data){
					var equipid = equips[t].id;
						if(equipid == data[m].id){
						deviceGrid.deleteRow(m);
					}
				}
			}
		} 
		var newBoxs = [];
		$.each(boxs,function(){
				if(this.id != delId){
					newBoxs.push(this);
				}
		});
		
		
		$("#formObj").data("box_td",newBoxs);
		$("#"+delId).remove();
		dealBoxIds(delId);
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
		/* var gridConfig={
	    device:{manager:deviceGrid,delUrl:"equipment2Action/delete.do"}
		}; */
		//归还日期控制
		/* function valueChange(val,text){
			if(text=="采购"){
				$("#back_user_name").val(""); 
				$("#back_user_name").ligerGetTextBoxManager().setDisabled();
			}else{
				$("#back_user_name").ligerGetTextBoxManager().setEnabled();
			}
		} */
		//归还/采购人选择
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
                    $("#back_user_id").val(p.id);
                    $("#back_user_name").val(p.name);
                }
            });
        }
		//设备管理人选择
		function choosePerson2(){
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
                    $("#instock_manager_id").val(p.id);
                    $("#instock_manager").val(p.name);
                    $("#instock_position_id").val(p.org_id);
                    if(p.id=="402883a0515e5d76015160d23a5d3ccb"&&p.org_id=="100046"){
                    	$("#instock_position").val("院管");
                    }else{
                    	$("#instock_position").val(p.org_name);
                    }
                }
            });
        }
		//部门选择选择
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
                    $("#instock_position_id").val(p.id);
                    $("#instock_position").val(p.name);
                }
            });
        }
		//选择设备箱
		function equipmentBox() {
		 dia1 = $.ligerDialog.open({ 
		 title: '选择设备箱', 
		 width: 700, 
		 height: 500
		 ,parent:api, 
		 url: 'app/equipment/base/equipment_equipbox_list2.jsp',
		 data: {"window" : window},
		 buttons: [
		    { text: '确定', onclick: f_importBoxOK },
			{ text: '取消', onclick: f_importCancel }
		]
		                                                                                                
		 });
	}
		var baseEquipment1;
		//通过条形码查找设备信息
		function searchByBarcode(){
			var barcode = $("#barcode").val();
			//获取列表中的设备
			var devRow = deviceGrid.rows;
			if(barcode.length == 13){
		        $.ajax({
		        	url: "equipment2Action/searchByBarcode.do",
		            type: "POST",
		           	data: "barcode="+barcode+"&type=instock",
		            success: function (resp) {
		                if (resp.success) {
		                	baseEquipment1 = resp.baseEquipment;
		                	//验证列表中是否已存在此设备
				        	var isexist=false;
					    	for(var i in devRow){
					    		if(devRow[i].id== baseEquipment1.id){
					    			isexist = true;
					    			break;
					    		}else{
					    			isexist = false;
					    		}
					    	}
					    	if(!isexist){
					    		var bb = {id : baseEquipment1.id,
					    				eq_name : baseEquipment1.eq_name,
					    				eq_no: baseEquipment1.eq_no,
					    				eq_model : baseEquipment1.eq_model,
					    				eq_manufacturer: baseEquipment1.eq_manufacturer,
					    				eq_status: baseEquipment1.eq_status,
					    				eq_use_status: baseEquipment1.eq_use_status};
								deviceGrid.addRow(bb);
					    	}
				        $("#barcode").val("").focus(); 
		                }else{
		                	$.ligerDialog.error('提示：' + resp.message); 
		                	$("#barcode").val("").focus(); 
		                }
		            },
		            error: function (data0,stats) {
		                $.ligerDialog.error('提示：' + data.msg);
		            }
		        });
		        
			}else if(barcode == "" || barcode.length == 0){
				
				$.ligerDialog.warn("码值不能为空！");
				$("#barcode").val("").focus(); 
			}else{
				$.ligerDialog.error("此条码不合法！");
				$("#barcode").val("").focus(); 
			}
			
		}
		function valueChange(val,text){
			if(text=="领用归还"){
				$("#tr_manager").show();
			}else{
				$("#tr_manager").hide();
			}
		}
</script>
</head>
<body>
	<div title="入库信息" tabId="basicTab" style="height: 400px">
	<form name="formObj" id="formObj" method="post" action="equipInstockRecordAction/instock.do"
		getAction="equipment2Action/getList.do?id=${param.ids}">
		<input type="hidden" id="create_by" name="create_by"/>
  		<input type="hidden" id="create_date" name="create_date"/>
  		<input type="hidden" id="back_user_id" name="back_user_id"  value="<%=userid %>"/>
  		<input type="hidden" id="instock_manager_id" name="instock_manager_id" value="402883a0515e5d76015160d23a5d3ccb"/>
  		<input type="hidden" id="instock_position_id" name="instock_position_id" value="100046"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>入库基本信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">入库类型</td>
					<td class="l-t-td-right"><u:combo name="instock_type" code="TJY2_EQUIP_INSTOCK_TYPE" 
					validate="required:true" attribute="onSelected:valueChange"/></td>
					<td class="l-t-td-left">入库登记人</td>
					<td class="l-t-td-right">
						<input name="instock_name" id="instock_name" type="text" ltype='text' readonly="readonly" 
						validate="{required:false,maxlength:64}" value="<sec:authentication property="principal.name"/>"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">归还/采购人</td>
					<td class="l-t-td-right">
						<input  ltype='text' validate="{maxlength:50,required:true}" readonly="readonly" id="back_user_name" name="back_user_name" type="text" onclick = "choosePerson()" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/>
						<!-- <input  ltype='text' id="back_user_name" name="back_user_name" type="text" id="Reviewers" validate="required:true" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/> -->
						</td>
					</td> 	
					<td class="l-t-td-left">归还/采购时间</td>
					<td class="l-t-td-right">
						<input name="back_time" id="back_time" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
				</tr>
				<tr id="tr_manager">
					<td class="l-t-td-left">管理（使用）人员</td>
					<td class="l-t-td-right">
					<input  ltype='text' value="张恒" validate="{maxlength:50,required:true}" readonly="readonly" id="instock_manager" name="instock_manager" type="text" onclick = "choosePerson2()" ligerui="{iconItems:[{icon:'user',click:choosePerson2}]}"/></td>
					<td class="l-t-td-left">管理（使用）部门</td>
					<td class="l-t-td-right">
					<input  ltype="text" value="院管" validate="{maxlength:50,required:true}" readonly="readonly" id="instock_position" name="instock_position" type="text" onclick = "chooseOrg()" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"/></td>
				</tr>
				<tr>	
					<td class="l-t-td-left">入库日期</td>
					<td class="l-t-td-right" colspan="3">
						<input name="instock_date" id="instock_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
				</tr>
				<!-- <tr>
					<td class="l-t-td-left">入库部门</td>
					<td class="l-t-td-right">
						<input  validate="{maxlength:50,required:true}" readonly="readonly" ltype="text"  name="instock_position" id="instock_position"  type="text" onclick = "chooseOrg()" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"/>
						<input  validate="{maxlength:50,required:true}" ltype="text"  name="instock_position" id="instock_position" type="text" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"/></td>
					</td>
				</tr> -->	
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>入库设备信息</div>
			</legend>
			<table cellpadding="1" cellspacing="0" class="l-detail-table">
				<tr>
		              <td class="l-t-td-left">设备条形码</td>
				      <td class="l-t-td-right"><input name="barcode" id="barcode" type="text" ltype='text' validate="{maxlength:64}"/></td>
				      <td class="l-t-td-left"></td>
					  <td class="l-t-td-right"></td>  
			    </tr>
				<tr>
		              <td class="l-t-td-left">设备箱选择</td>
			          <td class="l-t-td-right" id="box_td">
				          <c:if test="${param.status=='inStock'}"><span class="l-button label" title="添加设备箱">
					            <!-- <span  class="add btn"  onclick="selectReaders();">&nbsp;</span> -->
					            <span  class="l-a l-icon-add"  onclick="equipmentBox();">&nbsp;</span>
				                </span></c:if>
			        </td>
			    </tr>
			</table>
			<div style="height:150px;" id="device"></div>
		</fieldset>
	</form>
	</div>
	
	<!-- 页面加载完，条形码文本输入框框自动获取焦点 -->
	<script type="text/javascript">
		setTimeout( function(){
  			try{
   				 document.getElementById('barcode').focus();
 			 } catch(e){}
				}, 500);
</script>
</body>
</html>
