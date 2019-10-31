<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>

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
<head pageStatus="${param.pageStatus}">
<title>设备借用申请</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
	var pageStatus="${param.pageStatus}";
	var deviceGrid;
	var columns=[];
	var boxIds = "";
	var tbar="";
	var isChecked="${param.isChecked}";
	var serviceId = "${requestScope.serviceId}";//提交数据的id
	var activityId = "${requestScope.activityId}";//流程id
	var processId = "${requestScope.processId}";//过程
	$(function () {
		defineColumns();
		initGrid();
		//$("#areaFlag").val(areaFlag);
    	if(isChecked=="undefined"){
    		initGridFalse();//不可编辑列表
     		$("#lcsq").transform("detail");
    	    $("#lcsq").setValues("equipment/Loan/detail.do?id=${requestScope.serviceId}");
    	  	//查询设备列表及设备箱
			queryEqyipmentlistandBox("${requestScope.serviceId}");
    	    tbar=[{ text: '不通过', id: 'del', icon: 'forbid', click: nosubmitSh},
                  { text: '通过', id: 'submit', icon: 'accept', click: submitSh},
                  { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	}else {
    		tbar=[{text : '保存',id : 'save',icon : 'save',click : save}, 
				  {text : '关闭',id : 'close',icon : 'cancel',click : function(){api.close();}
			}];
    	}
		$("#form1").initForm({ //参数设置示例
 			toolbar : tbar,
			getSuccess : function(res) {
				if(res.success){
					/* deviceGrid.loadData({
						Rows : res.data["baseEquipment2s"]
					}); */
					$("#form1").setValues({id:res.id});
					$("#form1").setValues(res.data);
					
				}
			}
		});
		
		$('#loanName').autocomplete("employee/basic/searchEmployee.do", {
		 max: 12,    //列表里的条目数
         minChars: 1,    //自动完成激活之前填入的最小字符
         width: 200,     //提示的宽度，溢出隐藏
         scrollHeight: 300,   //提示的高度，溢出显示滚动条
         scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
         matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
         autoFill: false,    //自动填充
         formatItem: function(row, i, max) {
             return row.name + '   ' + row.mobileTel;
         },
         formatMatch: function(row, i, max) {
             return row.name + row.mobileTel;
         },
         formatResult: function(row) {
             return row.name;
         }
     }).result(function(event, row, formatted) {
    	 $("#loanId").val(row.id);
     });
		
		$('#depName').autocomplete("employee/basic/searchOrg.do", {
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
	        $("#depId").val(row.orgId);
	    });
	});
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
	            $("#loanId").val(p.id);
	            $("#loanName").val(p.name);
	            $("#depId").val(p.org_id);
	            $("#depName").val(p.org_name);
	        }
	    });
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
	        var radio = $("input[name='loanType']:checked").val();
		    if(radio ==null||radio==""){
		        $.ligerDialog.warn("借用/领用类型为空，请重新选择类型！");
		        return false;
	        }
		    /* if(radio =="loan"){
		    	if($("#requiredDiv2").val()==null || $("#requiredDiv2").val()==""){
		    		$.ligerDialog.warn("归还时间为空，请填写归还时间！");
		        	  return false;
		    	}
		    } */
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
	        if(data["baseEquipment2s"]==null || data["baseEquipment2s"].length ==0){
	        	$.ligerDialog.error('提示：' + '设备信息不能为空！');
	        	return false;
	        }
	        $("body").mask("正在保存数据，请稍后！");
	        $.ajax({
	            url:"equipment/Loan/saveEqui.do?&boxIds="+boxIds+"&pageStatus=${param.pageStatus}",
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
	
	function equipmentBox() {
		 dia1 = $.ligerDialog.open({ 
		 title: '选择设备箱', 
		 width: 700, 
		 height: 500
		 ,parent:api, 
		 url: 'app/equipment/base/equipment_equipbox_list.jsp',
		 data: {"window" : window},
		 buttons: [
		    { text: '确定', onclick: f_importBoxOK },
			{ text: '关闭', onclick: f_importCancel }
		]
		                                                                                                
		 });
	}
   
	
	
	function defineColumns(){
		if(pageStatus=="add"){
			columns.push({ display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='javascript:addDevice()' title='增加'><span>增加</span></a>", 
				isSort: false, 
				width: '30',
				height:'5%', 
				render: function (rowdata, index, value ) {
					var h = "";
					if (!rowdata._editing) {
						h += "<a class='l-a l-icon-del' href='javascript:delDevice(deviceGrid,"+index+")' title='删除'><span>删除</span></a> ";
					}
					return h;
				}
			});
			
		}else if(pageStatus=="detail"){
			//查询设备列表及设备箱
			queryEqyipmentlistandBox("${param.id}");
		}else if(pageStatus=="modify"){
			columns.push({ display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='javascript:addDevice()' title='增加'><span>增加</span></a>", 
				isSort: false, 
				width: '30',
				height:'5%', 
				render: function (rowdata, index, value ) {
					var h = "";
					if (!rowdata._editing) {
						h += "<a class='l-a l-icon-del' href='javascript:delDevice(deviceGrid,"+index+")' title='删除'><span>删除</span></a> ";
					}
					return h;
				}
			});
			//查询设备列表及设备箱
			queryEqyipmentlistandBox("${param.id}");
		}
		
		var eq_status=<u:dict code="TJY2_EQ_STATUS" />  //获得状态类型码表
		var use_status=<u:dict code="TJY2_EQ_USE_STATUS" />
		columns.push({display: 'id', name: 'id', hide:true },
					 {display: 'box_status', name: 'box_status', hide:true },
					 {display: '设备名称',width: '20%',name: 'eq_name',type:'text',required:false},
					 {display: '设备编号',width: '15%',name: 'eq_no',type: 'text',required:false},
					 {display: '规格型号',width: '15%',name: 'eq_model',type:'text',required:false},
					 {display: '制造厂家',width: '28%',name: 'eq_manufacturer',type:'text',required:false},
					 {display: '设备状态',width: '10%',name: 'eq_status',type:'text',required:false,
						 editor: { type: 'select',data:eq_status,ext:{emptyOption:false,selectBoxHeight:240}},
						 render:function(rowdata, rowindex, value){
							 for(var i in eq_status){
								 if(eq_status[i]["id"]==rowdata['eq_status']){
									 return eq_status[i]["text"];
								 }
							 }
							 return rowdata['eq_status'];
						 }
					 }
					 /* {display: '使用状态',width: '10%',name:'eq_use_status',type:'text',required:false,
						 editor: { type: 'select',data:use_status,ext:{emptyOption:false,selectBoxHeight:240}},
						 render:function(rowdata, rowindex, value){
							 for(var i in use_status){
								 if(use_status[i]["id"]==rowdata['eq_use_status']){
									 return use_status[i]["text"];
								 }
							 }
							 return rowdata['eq_use_status'];
						 }
					 } */
		);

	}

	//可编辑列表
	function initGrid(){
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
	//不可编辑列表
	function initGridFalse(){
		deviceGrid = $("#device").ligerGrid({
			columns: columns,
			enabledEdit: false,
			rownumbers : true,
			width:"99.6%",
			frozenRownumbers: false,
			usePager: false,
			data: {Rows: []} 
		});
	} 
	

	
	function addDevice() {
		 dia1 = $.ligerDialog.open({ 
		 title: '选择', 
		 width: 850, 
		 height: 500,
		 parent:api, 
		 url: 'app/equipment/apply/equipment_loan_select.jsp',
		 data: {"window" : window},
		 buttons: [
		    { text: '确定', onclick: f_importOK },
			{ text: '关闭', onclick: f_importCancel }
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
	   		var tt = {  id : rows[i].id, 
	   					eq_no: rows[i].eq_no, 
	   					box_status : rows[i].box_status,
	   					eq_name : rows[i].eq_name,
						eq_model : rows[i].eq_model,
						eq_manufacturer: rows[i].eq_manufacturer,
	   					eq_status : rows[i].eq_status,
	   					eq_use_status : rows[i].eq_use_status};
				deviceGrid.addRow(tt);
	   	 }
	   } 
	  		 /* dialog.close(); */
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
		   						box_status : boxRows[i].box_status,
								eq_no: boxRows[i].eq_no, 
			   					eq_name : boxRows[i].eq_name,
								eq_model : boxRows[i].eq_model,
								eq_manufacturer : boxRows[i].eq_manufacturer,
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
							baseEquipment2s : boxs1[k].baseEquipment2s 
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
			var boxs = $("#form1").data("equipmentBox");
			$.each(newBoxs,function(){
				$("#equipmentBox").prepend('<span class="label label-read" id="' + (isNew?this.id:this.id) + '"><span class="text">' + this.box_number + 
						'<span class="label label-read" baseEquipment2s="' + this.baseEquipment2s + '">' +
						'</span><span class="del btn btn-lm" onclick="deleteBox(\'' + (isNew?this.id:this.id) + '\',' + isNew + ')"><img src="k/kui/images/icons/16/delete1.png">&nbsp;</span></span>');
			});
			if(boxs)
				boxs = boxs.concat(newBoxs);
			else
				boxs = newBoxs;
			$("#form1").data("equipmentBox",boxs);
		}
		//编辑修改申请的设备箱
		function editBox(newBoxs,isNew){
			var boxs = $("#form1").data("equipmentBox");
			$.each(newBoxs,function(){
				/* alert(this.baseEquipment2s); */
				$("#equipmentBox").prepend('<span class="label label-read" id="' + (isNew?this.id:this.id) + '"><span class="text">' + this.boxNumber + 
						'<span class="label label-read" baseEquipment2s="' + this.baseEquipment2s + '">' +
						'</span><span class="del btn btn-lm" onclick="deleteBox(\'' + (isNew?this.id:this.id) + '\',' + isNew + ')"><img src="k/kui/images/icons/16/delete1.png">&nbsp;</span></span>');
			});
			if(boxs)
				boxs = boxs.concat(newBoxs);
			else
				boxs = newBoxs;
			$("#form1").data("equipmentBox",boxs);
		}
		//显示设备箱编号
		function showBoxNumber(newBoxs,isNew){
			var boxs = $("#form1").data("equipmentBox");
			$.each(newBoxs,function(){
				$("#equipmentBox").prepend('<span class="label label-read" id="' + (isNew?this.id:this.id) + '"><span class="text">' + this.boxNumber + '&nbsp;&nbsp;</span></span>');
			});
			if(boxs)
				boxs = boxs.concat(newBoxs);
			else
				boxs = newBoxs;
			$("#form1").data("equipmentBox",boxs);
		}
		//删除设备箱
		function deleteBox(delId,isNew){
			data1 = deviceGrid.getData();
			var boxs = $("#form1").data("equipmentBox");//所有箱子
			//查看箱子里面的信息
			for(var h in boxs){
				var id = boxs[h].id;
				if(delId!=id){
					continue;
				}
				var baseEquipment2s = boxs[h].baseEquipment2s;//箱子中的设备
				//移除箱子中列在表里的设备
				for (var t in baseEquipment2s ){
					var data = deviceGrid.getData();//所有设备
					for(var m in data){
						var equipid = baseEquipment2s[t].id;
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
		
		
		$("#form1").data("equipmentBox",newBoxs);
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

	//查询设备列表及设备箱
	function queryEqyipmentlistandBox(id){
		$.ajax({
        	url: "equipment/Loan/detail1.do?id="+id,
            type: "POST",
            success: function (resp) {
                if (resp.success) {
                	var baseEquipment2s = resp.baseEquipment2s;
                	var equipmentBoxs = resp.equipmentBoxs;
                	if(baseEquipment2s.length>0){
                		//将设备显示在申请列表里面
	                	for(var e in baseEquipment2s){
	                		var bb = {id : baseEquipment2s[e].id,
	                				box_status : baseEquipment2s[e].box_status,
	                				eq_no : baseEquipment2s[e].eq_no,
	                				eq_name: baseEquipment2s[e].eq_name,
	                				eq_model : baseEquipment2s[e].eq_model,
	                				eq_manufacturer: baseEquipment2s[e].eq_manufacturer,
	                				eq_status: baseEquipment2s[e].eq_status,
	                				eq_use_status: baseEquipment2s[e].eq_use_status};
							deviceGrid.addRow(bb);
	                	}
                	}
                	if(equipmentBoxs.length>0){
                		if(pageStatus=="modify"){
                			//取出箱子里面的设备，显示设备箱编号，并列出箱子中的设备
                    		editBox(equipmentBoxs,true);
                    		for(var m in equipmentBoxs){
                    			var baseEquipment2sInbox = equipmentBoxs[m].baseEquipment2s;
                    			for(var e in baseEquipment2sInbox){
                    				var baseEquipment2Inbox = baseEquipment2sInbox[e];
                    				var bb = {id : baseEquipment2Inbox.id,
                    						box_status : baseEquipment2Inbox.box_status,
    		                				eq_no : baseEquipment2Inbox.eq_no,
    		                				eq_name: baseEquipment2Inbox.eq_name,
    		                				eq_model : baseEquipment2Inbox.eq_model,
    		                				eq_manufacturer: baseEquipment2Inbox.eq_manufacturer,
    		                				eq_status: baseEquipment2Inbox.eq_status,
    		                				eq_use_status: baseEquipment2Inbox.eq_use_status};
    								deviceGrid.addRow(bb);
                    			}
                    		}
                		}else if(pageStatus=="detail" || isChecked=="undefined"){
                			//取出箱子里面的设备，显示设备箱编号，并列出箱子中的设备
                    		showBoxNumber(equipmentBoxs,true);
                    		for(var m in equipmentBoxs){
                    			var baseEquipment2sInbox = equipmentBoxs[m].baseEquipment2s;
                    			for(var e in baseEquipment2sInbox){
                    				var baseEquipment2Inbox = baseEquipment2sInbox[e];
                    				var bb = {id : baseEquipment2Inbox.id,
                    						box_status : baseEquipment2Inbox.box_status,
    		                				eq_no : baseEquipment2Inbox.eq_no,
    		                				eq_name: baseEquipment2Inbox.eq_name,
    		                				eq_model : baseEquipment2Inbox.eq_model,
    		                				eq_manufacturer: baseEquipment2Inbox.eq_manufacturer,
    		                				eq_status: baseEquipment2Inbox.eq_status,
    		                				eq_use_status: baseEquipment2Inbox.eq_use_status};
    								deviceGrid.addRow(bb);
                    			}
                    		}
                		}
                		
                		/* for(var m in equipmentBoxs){
                			var baseEquipment2sInBox = equipmentBoxs[m].baseEquipment2s,
                			alert(baseEquipment2sInBox);
                			//将设备显示在申请列表里面
		                	for(var e in baseEquipment2sInBox){
		                		var bb = {id : baseEquipment2sInBox[e].id,
		                				eq_no : baseEquipment2sInBox[e].eq_no,
		                				eq_name: baseEquipment2sInBox[e].eq_name,
		                				eq_model : baseEquipment2sInBox[e].eq_model,
		                				eq_status: baseEquipment2sInBox[e].eq_status,
		                				eq_use_status: baseEquipment2sInBox[e].eq_use_status};
								deviceGrid.addRow(bb);
		                	}
                		} */
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
	function submitSh(){
    	var serviceId = "${requestScope.serviceId}";//提交数据的id
    	var activityId = "${requestScope.activityId}";//流程id
        $.ligerDialog.confirm('确定通过审核？', function (yes){
        if(!yes){return false;}
         $("body").mask("正在提交，请稍后！");
         getServiceFlowConfig("TJY2_EQ_APPLY","",function(result,data){
                if(result){
                     top.$.ajax({
                         url: "equipment/Loan/equiLc.do?id="+serviceId+
                        		 "&typeCode=TJY2_EQ_APPLY&status="+"&activityId="+activityId,
                         type: "GET",
                         dataType:'json',
                         async: false,
                         success:function (data) {
                             if (data) {
                                $.ligerDialog.alert("审核成功！！！");
                                api.data.window.Qm.refreshGrid();
				                api.close();
                                /* $("body").unmask(); */
                             }
                         }
                     });
                }else{
                 $.ligerDialog.alert("出错了！请重试！");
                 $("body").unmask();
                }
             });
        });
    	
    }
	
	
	function nosubmitSh(){
    	$.ligerDialog.confirm('确定要不通过审核？', function (yes){
           if(!yes){return false;}
    	 $("body").mask("正在处理，请稍后！");
    	 
    	 getServiceFlowConfig("TJY2_EQ_APPLY","",function(result,data){
             if(result){
                  top.$.ajax({
                      url: "equipment/Loan/equibtg.do?id="+serviceId+
                     		 "&typeCode=TJY2_EQ_APPLY&status="+"&activityId="+activityId+"&processId"+processId,
                      type: "GET",
                      dataType:'json',
                      async: false,
                      success:function (data) {
                          if (data) {
                             $.ligerDialog.alert("操作成功！！！");
				             api.close();
				             api.data.window.Qm.refreshGrid();
                             /* $("body").unmask(); */
                          }
                      },
                      error:function () {
                          $.ligerDialog.alert("出错了!！请重试！");
                          $("body").unmask();
                      }
                  });
             }else{
              $.ligerDialog.alert("出错了！请重试！");
              $("body").unmask();
             }
          });
     });
    }
	
	function hidden(){
		 var radio= $("input[name='loanType']:checked").val();
		 if(radio == "lean"){
			 $("#requiredDiv").hide();
			 $("#requiredDiv2").hide();
		 }else{
			 $("#repayTime").attr("required",true);
			 $("#requiredDiv").show();
			 $("#requiredDiv2").show();
		 }
	}
	
</script>

</head>
<body>
	<form name="form1" id="form1" method="post" action="equipment/Loan/saveEqui.do?pageStatus=${param.pageStatus}"
		getAction="equipment/Loan/detail.do?id=${param.id}">
		<input name="id" id="id" type="hidden" value="${param.id }"/>	
		<input name="loanNo" id="loanNo" type="hidden"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>设备申请</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table" id="lcsq">
				<tr>
					<td class="l-t-td-left">借用/领用</td>
    			    <td class="l-t-td-right">
        				<u:combo name="loanType" ltype="radioGroup" code="TJY2_EQUIP_LOANTYPE" validate="{required:true}" attribute="onChange:function(){hidden();}"/>
        			</td>
				</tr>
				<tr>
					<td class="l-t-td-left">申请事由</td>
					<td class="l-t-td-right" colspan="3"><textarea name="reason" rows="2" cols="25" class="l-textarea" validate="{required:true,maxlength:1024}"></textarea></td>				
				</tr>
				<tr>
					<td class="l-t-td-left">借用/领用时间</td>
					<td class="l-t-td-right"><input name="loanTime" id="loanTime" type="text" ltype='date' validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}" value="<%=nowTime%>" /></td>	
					<td class="l-t-td-left" id="requiredDiv" >预计归还时间</td>
					<td class="l-t-td-right" id="requiredDiv2" ><input name="repayTime" id="repayTime" type="text" ltype='date'  ligerui="{format:'yyyy-MM-dd'}"  /></td>	
				</tr>
				<tr>	
					<td class="l-t-td-left">申请人</td>
					<td class="l-t-td-right">
						<input name="loanId" id="loanId" type="hidden" value="<%=userid %>"/>
						<input  ltype='text' readonly="readonly" value="<%=users %>" id="loanName" name="loanName" type="text" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}" onclick="choosePerson();"/>
						<!-- <input name="loanName" id="loanName" type="text" ltype='text' validate="{required:true}" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/> -->
					</td>
					<td class="l-t-td-left">申请部门</td>
					<td class="l-t-td-right">
						<input name="depId" id="depId" type="hidden" value="<%=useres.getDepartment().getId() %>"/>
						<input  validate="{maxlength:50,required:true}" value="<%=useres.getDepartment().getOrgName() %>" readonly="readonly" ltype="text"  name="depName" id="depName"  type="text" ligerui="{iconItems:[{icon:'org'}]}"/>
						<!-- <input id="depName" name="depName" type="text" ltype="text" validate="{required:true}" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}" /> -->
					</td>	
				</tr>
				
			
			</table>
		</fieldset>
		
		
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					选择设备
				</div>
			</legend>
			<table cellpadding="1" cellspacing="0" class="l-detail-table">
				 <tr>
		              <td class="l-t-td-left" style="width: 30px;">设备箱</td>
			          <td class="l-t-td-right" id="equipmentBox">
				          <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}"><span class="l-button label" title="添加设备箱">
					            <span  class="l-a l-icon-add"  onclick="equipmentBox();">&nbsp;</span>
				                </span></c:if>
			        </td>
			    </tr>
			</table>
			<div id="device"></div>
	</fieldset>
	</form>
</body>
</html>