<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
	<script type="text/javascript" src="app/car/js/wb_record_item_info.js"></script>
	<script type="text/javascript" src="app/car/js/choose_car.js"></script>
    <script type="text/javascript">
    var pageStatus="${param.pageStatus}";
	$(function(){
		createWbRecordItemGrid();
		if("add"==pageStatus || "modify"==pageStatus){
			tbar=[
				{text: "保存", id: 'save', icon: "save", click: saveInfo},
				{text: "关闭", id: 'close', icon: "cancel", click: function(){api.close();}}
			];
		}else{
			tbar=[
				{text: "关闭", id: 'close', icon: "cancel", click: function(){api.close();}}
			];
		}
		$("#form1").initForm({
			success: function (resp) {
	    		if (resp.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	         		api.data.window.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！');
	      		}
			}, 
			getSuccess: function (resp){
				if(resp.success){
					wbRecordItemGrid.loadData({
						Rows : resp.carWbRecordItems
					});
					$("#form1").setValues(resp.data);
				}
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
	})
	//保存
	function saveInfo(){
    	if ($("#form1").validate().form()) {
    		$("#save").attr("disabled","disabled");
    		var formData = $("#form1").getValues();
    		/* delete formData["item_type"]; */
			var data = {};
			data = formData;
		    data["carWbRecordItems"] = wbRecordItemGrid.getData();
			$("body").mask("正在保存数据，请稍后！");
			$.ajax({
				url: "carWbRecordAction/saveInfo.do?pageStatus=${param.pageStatus}",
				type: "POST",
			 	datatype: "json",
			 	contentType: "application/json; charset=utf-8",
			 	data: $.ligerui.toJSON(data),
			  	success: function (resp) {
			   		$("body").unmask();
			      	if (resp["success"]) {
			       		if(api.data.window.Qm){
			                api.data.window.Qm.refreshGrid();
			   			}
			         	top.$.dialog.notice({content:'保存成功！'});
			     		api.close();
			     	}else{
			      		$.ligerDialog.error(resp.msg);
			      		$("#save").removeAttr("disabled");
			    	}
			  	},
				error: function (resp) {
			   		$("body").unmask();
					$.ligerDialog.error(resp.msg);
					$("#save").removeAttr("disabled");
				}
			});
    	}else{
    		$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');
    	}
			}
	//选择车辆
	function selectWbInfo(){
		selectCar('1','1','','','00',function(datas){
			var carInfo = datas.jsonObj.carInfo[0];
			$("#fkCarId").val(carInfo.id);
        	$("#carNum").val(carInfo.car_num);
		});
	}
	//选择维保申请单
	function selectWbInfo() {
		top.$.dialog({
	        width: 800,
	        height: 450,
	        lock: true,
	        parent: api,
	        title: "选择维保编号",
	        content: 'url:app/car/car_wb/pc/carWb_apply_choose_list.jsp?chooseType=wbRecord',
	        cancel: true,
	        ok: function(){
	            var p = this.iframe.contentWindow.getSelectedCarWb();
	            if(!p){
	                top.$.notice("请选择数据！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                return false;
	            }
	            $("#fkWbId").val(p.id);
	        	$("#wbSn").val(p.sn);
	            $("#fkCarId").val(p.fk_car_id);
	        	$("#carNum").val(p.car_num);
	            
	        }
	    });
	}
	//选择车辆
	function selectCarInfo(){
		selectCar('1','1','','','00',function(datas){
			var carInfo = datas.jsonObj.carInfo[0];
			$("#fkCarId").val(carInfo.id);
        	$("#carNum").val(carInfo.car_num);
		});
	}
	function setValues(valuex,name){
		if(valuex==""){
			return;
		}
		var selected = wbRecordItemGrid.rows;
        if (!selected) { alert('请选择行'); return; }
        var item_type;
        var is_renew;
        for(var i in selected){
        	if(name=='item_type'){
		    	if(valuex==''|| valuex==null || valuex ==undefined){
		    		item_type = selected[i].itemType;
		      	}else{
		        	var text= $("input[name='item_type']").ligerGetComboBoxManager().getValue();
		        	item_type = text;
		      	}
		  	}
			if(name=='is_renew'){
		    	if(valuex==''|| valuex==null || valuex ==undefined){
		    		is_renew = selected[i].isRenew;
		      	}else{
		        	var text= $("input[name='is_renew']").ligerGetComboBoxManager().getValue();
		        	is_renew = text;
		      	}
		  	}
        	wbRecordItemGrid.updateRow(selected[i],{
        		itemType: item_type,
        		isRenew: is_renew
        	});
     	}
	}
    </script>
</head>
<body>
	<form id="form1" action="carWbRecordAction/saveInfo.do?pageStatus=${param.pageStatus}" getAction="carWbRecordAction/getDetail.do?id=${param.id}">
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="fkCarId" name="fkCarId" />
		<input type="hidden" id="fkWbId" name="fkWbId" />
        <input type="hidden" id="param1" name="param1"/>
        <input type="hidden" id="param2" name="param2"/>
        <input type="hidden" id="param3" name="param3"/>
        <input type="hidden" id="param4" name="param4"/>
        <input type="hidden" id="param5" name="param5"/>
        <input type="hidden" id="createUserId" name="createUserId"/>
        <input type="hidden" id="createUserName" name="createUserName"/>
        <input type="hidden" id="createDate" name="createDate"/>
        <input type="hidden" id="lastModifyUserId" name="lastModifyUserId"/>
        <input type="hidden" id="lastModifyUserName" name="lastModifyUserName"/>
        <input type="hidden" id="lastModifyDate" name="lastModifyDate"/>
        <input type="hidden" id="dataStatus" name="dataStatus" value="0"/>
        <fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					基本信息
				</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		       	<tr> 
		       		<td class="l-t-td-left"> 维保申请编号</td>
					<td class="l-t-td-right"><input name="wbSn" id="wbSn" type="text"
						readonly="readonly" title="点击此处选择维保申请编号"
						ltype='text' validate="{required:true}"
						onclick="selectWbInfo()"
						ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectWbInfo()}}]}" />
					</td>
			       	<td class="l-t-td-left"> 车牌号</td>
					<td class="l-t-td-right"><input name="carNum" id="carNum" type="text"
						readonly="readonly" title="点击此处选择车辆"
						ltype='text' validate="{required:true}"
						onclick="selectCarInfo()"
						ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectCarInfo()}}]}" />
					</td>
		      	</tr>
		      	<tr>
		      		<td class="l-t-td-left"> 维保单位</td>
			        <td class="l-t-td-right" colspan="3"> 
			        	<input type="text" id="wbComName" name="wbComName" ltype='text' validate="{required:true}" />
			        </td>
		      	</tr>
		       	<tr> 
			        <td class="l-t-td-left"> 维保联系人</td>
			        <td class="l-t-td-right"> 
			        	<input type="text" id="wbContacts" name="wbContacts" ltype='text' validate="{required:true}" />
			        </td>
			        <td class="l-t-td-left"> 维保联系电话</td>
			        <td class="l-t-td-right"> 
			        	<input type="text" id="wbContactsTel" name="wbContactsTel" ltype='text' validate="{required:true}" />
			        </td>
		       	</tr>
	       	</table>
		</fieldset>
		<fieldset class="l-fieldset" id='item1'>
            <legend class="l-legend">
                <div>维保项目</div>
            </legend>
            <div style="height:100%;" id="wbRecordItem"></div>
         </fieldset>
        <c:if test="${param.pageStatus!='detail'}">
        <fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					便捷填写
				</div>
			</legend>
		 <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">项目类别</td>
				<td class="l-t-td-right">
					<input type="text" id="item_type" name="item_type" ltype="select" validate="{required:false}" ligerui="{
						value:'',
						readonly:true,
						data: [ { text:'维修', id:'0' }, { text:'保养', id:'1' }, { text:'维修加保养', id:'2' }],
						suffixWidth:'140'
					}" onchange="setValues (this.value,'item_type')"/>
				</td>
				<td class="l-t-td-left">是否更换零部件</td>
				<td class="l-t-td-right">
					<input type="text" id="is_renew" name="is_renew" ltype="select" validate="{required:false}" ligerui="{
						value:'',
						readonly:true,
						data: [ { text:'否', id:'0' }, { text:'是', id:'1' }],
						suffixWidth:'140'
					}" onchange="setValues (this.value,'is_renew')"/>
				</td>
			</tr>
			
		</table>
		</fieldset>
		</c:if>
    </form> 

</body>
</html>
