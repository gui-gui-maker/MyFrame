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
					Rows : res.data["foods"]
				});
				$("#form1").setValues({id:res.id});
				$("#form1").setValues(res.data);
			} 
		}
	});
});
	
	
	function defineColumns(){
		if(pageStatus!="detail"){
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
			
		}
		

		columns.push(
				{ display:'ID', name:'id', width:'1%', hide:true},
	            { display:'菜名', name:'name', width:'20%'},
	            { display:'类型', name:'ftype', width:'20%'},
	            { display:'描述', name:'fdesc', width:'50%'}
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
	function save(){
		
		//验证表单数据
		if($('#form1').validate().form()){
			
			var formData = $("#form1").getValues();
	        if(!validateGrid(deviceGrid)){
				return false;
			}
	        var foods = deviceGrid.getData();
			if(!foods){
				$.ligerDialog.alert('提示：请至少发布一个菜品' );  
				return false;  
			}
	        formData["foods"] = foods;
	        var  jsonString = $.ligerui.toJSON(formData);
	        console.log(formData);
	        console.log(jsonString);
	        $("body").mask("正在保存数据，请稍后！");
	        $.ajax({
	            url: "dining/pubo/saveUseTime.do",
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	           	data: $.ligerui.toJSON(formData),
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
		 height: 500,
		 parent:api, 
		 url: 'app/fwxm/dining/food_checkbox.jsp',
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
	    		var tt = {
	    					id : rows[i].id, 
		    				name: rows[i].name, 
		    				ftype : rows[i].ftype, 
		    				fdesc : rows[i].fdesc,
	    				};
				deviceGrid.addRow(tt);
	    	
	    	}
	    }
	   		 dialog.close();
	}
	
	function f_importCancel(item, dialog){
		dialog.close();
	}		
		
	
	
	function delDevice(obj, index) {
		var data = obj.getSelectedRow();
		var dataId = data.id;
			$.ligerDialog.confirm("确定要移除该设备吗？", function(yes) {
			if (yes) {
				obj.deleteRow(index);
			}
		});

	}

</script>
</head>
<body>
	<form id="form1" method="post" action="dining/pubo/saveUseTime.do"
		getAction="dining/pubo/puboDetail.do?id=${param.id}">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					就餐时间信息
				</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<input type="hidden" name="id" id="id" value="${param.id}"/>
			<input type="hidden" name="pub_man" id="pub_man" value=""/>
			<input type="hidden" name="pub_time" id="pub_time" value=""/>
			
				<tr>
					<td class="l-t-td-left">就餐日期：</td>
					<td class="l-t-td-right" colspan="3">
					<input name="use_time" 
						id="use_time" 
						type="text" 
						ltype="date"
						validate="{required:true}" 
						ligerUi="{initValue:'',format:'yyyy-MM-dd'}"
						/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">类型：</td>
					<td class="l-t-td-right" colspan="3">
						<u:combo name="meal_name" code="food_meal_type" 
						ltype="radioGroup" validate="required:true"
						/>
					</td>
				</tr>
				<c:if test="${param.pageStatus=='detail'}">
					<tr>
						<td class="l-t-td-left">状态：</td>
						<td class="l-t-td-right">
							<u:combo name="pub_status" code="food_pubo_status"/>
						</td>
					</tr>
				</c:if>
				<c:if test="${param.pageStatus!='detail'}">
					<input type="hidden" name="pub_status" id="pub_status" value="0"/>
				</c:if>
			</table>
	</fieldset>
	
	<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					菜单详情
				</div>
			</legend>
			<div id="device"></div>
	</fieldset>
	</form>
</body>
</html>