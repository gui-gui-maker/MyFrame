<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>

    <script type="text/javascript">
    var pageStatus="${param.pageStatus}";
    $(function () {
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
    	$("#form1").initForm({    //参数设置示例
    		success: function (res) {
    			if(res.success){
    				top.$.dialog.notice({
                 		content: "保存成功！"
                	});		
    				api.data.window.Qm.refreshGrid();
    				api.close();
    			}else{
    				$.ligerDialog.error('保存失败！<br/>' + res.msg);
    			}
    		},
    		afterParse:function(formObj){//form表单完成渲染后，回调函数
    			$.ajax({
		        	url: "oa/car/info/detail.do?id="+api.data.fk_car_id,
		            type: "POST",
		            success: function (resp) {
						if(resp.success){
							if(pageStatus!="detail"){
								$("#fkCarId").val(resp.data.id);
			           	 		$("#carNum").val(resp.data.carNum);
				           	 	$("#carBrand").val(resp.data.carBrand);
				       	 		$("#loadNumber").val(resp.data.loadNumber);
					       	 	$("#carMileage").val(resp.data.carMileage);
					   	 		$("#engineNo").val(resp.data.engineNo);
					   	 		$("#frameNo").val(resp.data.frameNo);
			            	}else{
			            		//$("#fkCarId").html(api.data.fk_car_id);
			           	 		$("#carNum").html(resp.data.carNum);
				           	 	$("#carBrand").html(resp.data.carBrand);
				       	 		$("#loadNumber").html(resp.data.loadNumber);
					       	 	$("#carMileage").html(resp.data.carMileage);
					   	 		$("#engineNo").html(resp.data.engineNo);
					   	 		$("#frameNo").html(resp.data.frameNo);
			            	}
							
		            	}
		            }
		        });
    		},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    });
  	//保存
	function saveInfo(){
  		var url = "carCmDetailAction/saveInfo.do?pageStatus=${param.pageStatus}";;
  		if("${param.opType}" == "history"){
  			url = "carCmDetailAction/saveHistoryInfo.do?pageStatus=${param.pageStatus}";
  		}
    	if ($("#form1").validate().form()) {
    		$("#save").attr("disabled","disabled");
    		var formData = $("#form1").getValues();
    		var data = {};
			data = formData;
			$("body").mask("正在保存数据，请稍后！");
			$.ajax({
				url: url,
				type: "POST",
			 	datatype: "json",
			 	contentType: "application/json; charset=utf-8",
			 	data: $.ligerui.toJSON(formData),
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
    </script>
</head>
<body>
    <form id="form1" action="carCmDetailAction/saveInfo.do?pageStatus=${param.pageStatus}" getAction="carCmDetailAction/detail.do?id=${param.id}">
    	<input type="hidden" id="id" name="id"/>
    	<input type="hidden" id="fkCarId" name="fkCarId"/>
    	<input type="hidden" id="dataStatus" name="dataStatus" value="0"/>
       <fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					车辆信息
				</div>
			</legend>
			<table id="carInfo" border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		   <tr> 
	        <td class="l-t-td-left"> 车牌号</td>
			<td class="l-t-td-right">
			<input name="carNum" id="carNum" type="text" ltype='text' readonly="readonly" unselectable='on'/>
			</td>
			<td class="l-t-td-left"> 品牌型号</td>
	        <td class="l-t-td-right"> 
	        <input name="carBrand" id="carBrand" type="text" ltype='text' readonly="readonly" unselectable='on'/>
	        </td>
	       </tr>
	       <tr> 
	        <td class="l-t-td-left"> 核定载人数</td>
			<td class="l-t-td-right">
			<input name="loadNumber" id="loadNumber" type="text" ltype='text' readonly="readonly" unselectable='on'/>
			</td>
			<td class="l-t-td-left"> 行驶里程</td>
	        <td class="l-t-td-right"> 
	        <input name="carMileage" id="carMileage" type="text" ltype='text' readonly="readonly" unselectable='on'/>
	        </td>
	       </tr>
	       <tr> 
	        <td class="l-t-td-left"> 发动机号</td>
	        <td class="l-t-td-right"> 
	        <input name="engineNo" id="engineNo" type="text" ltype='text' readonly="readonly" unselectable='on'/>
	        </td>
	        <td class="l-t-td-left"> 车辆识别代码</td>
	        <td class="l-t-td-right"> 
	        <input name="frameNo" id="frameNo" type="text" ltype='text' readonly="readonly" unselectable='on'/>
	        </td>
	       </tr>
	      </table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					保养信息
				</div>
			</legend>
			<table id="cmInfo" border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
	       <tr> 
	        <td class="l-t-td-left"> 保养公里数</td>
	        <td class="l-t-td-right"> 
	        	<input id="cmKm" name="cmKm" type="text" ltype='text' validate="{required:true,maxlength:32}"/>
	        </td>
	        <td class="l-t-td-left"> 保养日期</td>
	        <td class="l-t-td-right"> 
	        	<input id="cmDate" name="cmDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"  validate="{required:true}}"/>
	        </td>
	       </tr>
	       <tr> 
	        <td class="l-t-td-left"> 下次保养公里数</td>
	        <td class="l-t-td-right"> 
	        	<input id="nextCmKm" name="nextCmKm" type="text" ltype='text' validate="{required:true,maxlength:32}"/>
	        </td>
	        <td class="l-t-td-left"> 下次保养日期</td>
	        <td class="l-t-td-right"> 
	        	<input id="nextCmDate" name="nextCmDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"  validate="{required:true}}"/>
	        </td>
	       </tr>
	       <tr> 
	        <td class="l-t-td-left"> 备注</td>
	        <td class="l-t-td-right" colspan="3"> 
	        	<textarea name="remark" id="remark" rows="14" cols="50" class="l-t-td-textarea" validate="{maxlength:4000}" style="border-bottom:0px"></textarea>
	        </td>
	        </tr>
	      </table>
		</fieldset>
    </form> 
</body>
</html>
