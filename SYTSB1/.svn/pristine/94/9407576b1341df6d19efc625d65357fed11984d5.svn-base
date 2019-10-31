<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>设备信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<!-- 生成条形码JS导入 -->
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<script type="text/javascript">
	var pageStatus = "${param.status}";
	var equip_id = "${param.id}";
	var tbar="";
	$(function() {
		initGrid();
		initGrid1();
		$("#txm").hide();//隐藏条形码
		$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
		var nowDate = new Date().format("yyyy-MM-dd");
		$("#eq_produce_date").val(nowDate);
		$("#eq_buy_date").val(nowDate);
		$("#eq_draw_date").val(nowDate);
		if(pageStatus=="detail"){
			tbar=[{text: "关闭", id: 'close' ,icon: "cancel", click: function(){api.close();}}];
	 	} else if(pageStatus=="add"){
	 		$("#barcode").val(randomNum());
	 		tbar=[{text: "保存", icon: "save", click: function(){
		      				//表单验证
					    	if ($("#formObj").validate().form()) {
					    		$("#formObj").submit();
					    	}
		      			}},
				  {text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	} else if(pageStatus=="modify"){
	 		tbar=[{text: "保存", icon: "save", click: function(){
  				//表单验证
		    	if ($("#formObj").validate().form()) {
		    		$("#formObj").submit();
		    	}
  			}},
	  				{text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}
		
		$("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	         		api.data.window.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
				
				if (response.attachs != null && response.attachs != undefined)
					showAttachFile(response.attachs);
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
		//输入供货商名称弹出供货商名称选项
		$('#eq_supplier_name').autocomplete("equipment2SupplierAction/searchManufacturer.do", {
                    max: 12,    //列表里的条目数
                    minChars: 1,    //自动完成激活之前填入的最小字符
                    width: 200,     //提示的宽度，溢出隐藏
                    scrollHeight: 300,   //提示的高度，溢出显示滚动条
                    scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
                    matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
                    autoFill: false,    //自动填充
                    formatItem: function(row, i, max) {
                        return row.supplier_name + '   ' + row.supplier_tel;
                    },
                    formatMatch: function(row, i, max) {
                        return row.supplier_name + row.supplier_tel;
                    },
                    formatResult: function(row) {
                        return row.supplier_name;
                    }
                }).result(function(event, row, formatted) {
                	document.getElementById("eq_supplier_id").value = row.id
                });
		var receiptUploadConfig = {
    			fileSize : "40mb",	//文件大小限制
    			businessId : "",	//业务ID
    			buttonId : "procufilesBtn",		//上传按钮ID
    			container : "procufilesDIV",	//上传控件容器ID
    			title : "图片",	//文件选择框提示
    			extName : "jpg,gif,png,bmp",	//文件扩展名限制
    			workItem : "",	//页面多点上传文件标识符号
    			fileNum : 2,	//限制上传文件数目
    			callback : function(files){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				addAttachFile(files);
    			}
    	};
		var receiptUploader= new KHFileUploader(receiptUploadConfig);
	});
	//添加附件
	function addAttachFile(files){
		if("${param.status}"=="detail"){
			$("#procufilesBtn").hide();
		}
		if(files){
			var attContainerId="procufiles";
			$.each(files,function(i){
				var file=files[i];
				/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByFilePath.do?path="+file.path+"&fileName="+file.name+"'><image style='width:60px;height:60px' src='upload/"+file.filePath+"'></a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
						"</li>"); */
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.name+"'><image style='width:60px;height:60px' src='upload/"+file.filePath+"'></a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\"\",this,getUploadFile)'>&nbsp;</div>"+
						"</li>");
			});
			getUploadFile();
		}
	}
	
	// 显示附件文件
    function showAttachFile(files){
    	if("${param.status}"=="detail"){
			$("#procufilesBtn").hide();
		}
		if(files){
			//详情
			var attContainerId="procufiles";
			if("${param.status}"=="detail"){
				$.each(files,function(i){
					var file=files[i];
					 //显示附件
					 $("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'><image style='width:60px;height:60px' src='upload/"+file.filePath+"'></a></div>"+
							"</li>");
					/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
											"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
											"</li>"); */
				});
			}
			//修改
			else if("${param.status}"=="modify"){
				$.each(files,function(i){
					var file=files[i];
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'><image style='width:60px;height:60px' src='upload/"+file.filePath+"'></a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,getUploadFile)'>&nbsp;</div>"+
							"</li>");
					/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,getUploadFile)'>&nbsp;</div>"+
							"</li>"); */
				});
				getUploadFile();
			}
		}
    }
	  
	// 将上传的所有文件id写入隐藏框中
	function getUploadFile(){
		var attachId="";
		var i=0;
		$("#procufiles li").each(function(){
			attachId+=(i==0?"":",")+this.id;
			i=i+1;
		});
		if(attachId!=""){
			attachId=attachId.substring(0,attachId.length);
		}
		$("#uploadFiles").val(attachId);
	}
	
	function closewindow(){
		api.close();
	}
	
	//生成13位数的随机数
	function randomNum(){ 
    	var t=''; 
    	for(var i=0;i<13;i++){ 
        	t+=Math.floor(Math.random()*10); 
    	} 
    	return t; 
	} 
	//打印条码
	function printBarCode() {
		var eq_name = $("#eq_name").text();
		var eq_no = $("#eq_no").text();
		var eq_model = $("#eq_model").text();
		var eq_manufacturer = $("#eq_manufacturer").text();
		var BarCodeValue = $.trim($("#barcode").text());
    	var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
    	//条形码
	    LODOP.ADD_PRINT_TEXT(40,255,300,20,"设备名称："+eq_name);
	    LODOP.SET_PRINT_STYLEA(0,"FontSize",9);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
	    LODOP.ADD_PRINT_TEXT(60,255,300,20,"设备编号："+eq_no);
	    LODOP.SET_PRINT_STYLEA(0,"FontSize",9);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
	    LODOP.ADD_PRINT_TEXT(80,255,300,20,"规格型号："+eq_model);
	    LODOP.SET_PRINT_STYLEA(0,"FontSize",9);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
	    LODOP.ADD_PRINT_TEXT(100,255,300,20,"制造厂家："+eq_manufacturer);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",9);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
        LODOP.ADD_PRINT_BARCODE(136,255,300,80,"EAN13",BarCodeValue);
        //二维码
        LODOP.ADD_PRINT_TEXT(420,100,300,20,"设备名称："+eq_name);
	    LODOP.SET_PRINT_STYLEA(0,"FontSize",9);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
	    LODOP.ADD_PRINT_TEXT(450,100,300,20,"设备编号："+eq_no);
	    LODOP.SET_PRINT_STYLEA(0,"FontSize",9);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
	    LODOP.ADD_PRINT_TEXT(480,100,300,20,"规格/型号："+eq_model);
	    LODOP.SET_PRINT_STYLEA(0,"FontSize",9);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
	    LODOP.ADD_PRINT_TEXT(510,100,300,20,"生产厂家："+eq_manufacturer);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",9);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
        LODOP.ADD_PRINT_BARCODE(400,405,150,150,"QRCode",BarCodeValue);
        LODOP.PREVIEW();
	}
	
	
	//金额输入规范只能输入正数
    function onlyNonNegative(obj) {
     var inputChar = event.keyCode;
     //1.判断是否有多于一个小数点
     if(inputChar==190 ) {//输入的是否为.
	     var index1 = obj.value.indexOf(".") + 1;//取第一次出现.的后一个位置
	     var index2 = obj.value.indexOf(".",index1);
	     while(index2!=-1) {
		     obj.value = obj.value.substring(0,index2);
		     index2 = obj.value.indexOf(".",index1);
	     }
     }
     //2.如果输入的不是.或者不是数字，替换 g:全局替换
     obj.value = obj.value.replace(/[^(\d|.)]/g,"");
    }
    //添加供应商
    function addManufacturer(){
			top.$.dialog({
				width: 700,
				height: 360,
				lock:true,
				parent: api,
				title:"新增",
				data: {window:window},
				content: 'url:app/equipment/base/equipment_supplier_detail.jsp?status=addinpage',
			});
        }
	//管理（使用）人员选择
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
	                    /* $("#eq_user").val(p.id); */
	                    $("#eq_user").val(p.name);
	                    /* $("#applyUnitId").val(p.org_id);*/
	                    $("#eq_use_department").val(p.org_name); 
	                }
	            });
	        }
		
    //出入库记录
    function initGrid() {
			var instock_type=<u:dict code="TJY2_EQUIP_INSTOCK_TYPE" />
	        var outstock_type=<u:dict code="TJY2_EQUIP_OUTSTOCK_TYPE" />
	        recoedGrid = $("#form2").ligerGrid({
	    		columns: [{display: '出库/入库',width: '20%',name: 'inoutClass',type:'text',
	    				render:function(rowData){
                    	if(rowData.inoutClass=="0"){
                        	return "入库";
                    	}else if(rowData.inoutClass=="1"){
                        	return "出库";
                    	}
                	}},
                	{display: '类型',width: '20%',name:'inoutType',type:'text',
                    	editor: { type: 'select', data:instock_type},
    					render : function(rowdata, rowindex, value) {
    						if(rowdata.inoutClass=="0"){
    							for ( var i in instock_type) {
        							if (instock_type[i]["id"] == rowdata["inoutType"])
        								return instock_type[i]['text'];
        						}
        						return rowdata["instock_type"];
                        	}else if(rowdata.inoutClass=="1"){
                        		for ( var i in outstock_type) {
        							if (outstock_type[i]["id"] == rowdata["inoutType"])
        								return outstock_type[i]['text'];
        						}
        						return rowdata["outstock_type"];
                        	}	
    					}
 					  },
					  {display: '姓名',width: '28%',name: 'relatedName',type:'text'},
					  {display: '日期',width: '28%',name: 'inoutDate',type:'text'}
	    	          ],
	    		rownumbers : true,
		   		width:"99.9%",
		    	frozenRownumbers: false,
		    	usePager: true,
		 		dataAction:"local",
				url : "equipment2Action/searchRecord.do?equip_id="+equip_id
	    	});
		}
	    //设备使用登记
	    function initGrid1() {
				var use_status=<u:dict code="TJY2_SBDJZT" />
				var use_type=<u:dict code="TJY2_EQUIP_OUTSTOCK_TYPE" />
		        useGrid = $("#form3").ligerGrid({
		    		columns: [{display: '状态',width: '15%',name: 'status',type:'text',
		    			editor: { type: 'select', data:use_status},
    					render : function(rowdata, rowindex, value) {
    							for ( var i in use_status) {
        							if (use_status[i]["id"] == rowdata["status"])
        								return use_status[i]['text'];
        						}
        						return rowdata["use_status"];
    						}
		    			},
		    			  {display: '类型',width: '15%',name:'useType',type:'text',
		    				editor: { type: 'select', data:use_type},
	    					render : function(rowdata, rowindex, value) {
	    							for ( var j in use_type) {
	        							if (use_type[j]["id"] == rowdata["useType"])
	        								return use_type[j]['text'];
	        						}
	        						return rowdata["use_type"];
	    						}
		    			  },
	                	  {display: '姓名',width: '15%',name:'borrowerName',type:'text' },
						  {display: '开始时间',width: '18%',name: 'borrowerTime',type:'text'},
						  {display: '归还时间',width: '18%',name: 'returnTime',type:'text'},
						  {display: '使用部门',width: '15%',name: 'departmentName',type:'text'}
		    	          ],
		    		rownumbers : true,
			   		width:"99.9%",
			    	frozenRownumbers: false,
			    	usePager: true,
			    	dataAction:"local",
					url : "equipmentUseRegisterAction/searchUseRecord.do?equip_id="+equip_id
		    	});
			} 
</script>
</head>
<body>
<div class="navtab">
	<div title="设备档案信息" id="form1">
	<form  id="formObj"  action="equipment2Action/save.do?status=${param.status}"
		getAction="equipment2Action/dle.do?ids=${param.id}">
		<input type="hidden" id="create_by" name="create_by"/>
  		<input type="hidden" id="create_date" name="create_date"/>
  		<input type="hidden" id="is_new" name="is_new" value="0"/>
  		<input type="hidden" id="eq_supplier_id" name="eq_supplier_id"/>
  		<!-- <input type="hidden" id="eq_user" name="eq_user"/> -->
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					设备档案信息
				</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr id = "txm">
					<td class="l-t-td-left">设备ID</td>
					<td class="l-t-td-right"><input type="text" ltype='text' name="id" id="id" value="${param.id}"/>
					<td class="l-t-td-left">条形码</td>
					<td class="l-t-td-right"><input name="barcode" id="barcode" type="text" ltype='text' validate="{required:true,maxlength:32}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">档案分类号</td>
					<td class="l-t-td-right"><input name="eq_archive_class_id" id="eq_archive_class_id" type="text" ltype='text' validate="{maxlength:32}"/></td>
					<td class="l-t-td-left">设备名称</td>
					<td class="l-t-td-right"><input name="eq_name" id="eq_name" type="text" ltype='text' validate="{required:true,maxlength:32}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">设备编号</td>
					<td class="l-t-td-right"><input name="eq_no" id="eq_no" type="text" ltype='text' validate="{maxlength:32}" /></td>	
					<td class="l-t-td-left">规格型号</td>
					<td class="l-t-td-right"><input name="eq_model" id="eq_model" type="text" ltype='text' validate="{maxlength:32}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">出厂编号</td>
					<td class="l-t-td-right"><input name="eq_sn" id="eq_sn" type="text" ltype='text' validate="{maxlength:32}"/></td>
					<td class="l-t-td-left" width="120">精度</td>
					<td class="l-t-td-right"><input name="eq_accurate" id="eq_accurate" type="text" ltype='text' validate="{maxlength:32}"/></td>									
				</tr>
				<tr>
					<!-- <td class="l-t-td-left">制造年月</td>
					<td class="l-t-td-right"><input name="eq_produce_date" id="eq_produce_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td> -->
					<td class="l-t-td-left">供应商</td>
					<td class="l-t-td-right">
					<input name="eq_supplier_name" id="eq_supplier_name" type="text" ltype='text' validate="{maxlength:64,required:true}" ligerui="{iconItems:[{icon:'add',click:addManufacturer}]}"/></td>
					<td class="l-t-td-left">购进日期</td>
					<td class="l-t-td-right"><input name="eq_buy_date" id="eq_buy_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
				</tr>
				<tr>		
					<td class="l-t-td-left">制造厂家</td>
					<td class="l-t-td-right">
					<input  validate="{maxlength:50,required:true}" ltype="text"  name="eq_manufacturer" id="eq_manufacturer" type="text" /></td>
					<td class="l-t-td-left">设备原值（元）</td>
					<td class="l-t-td-right">
					<input name="eq_value" id="eq_value" type="text"  ltype='float' validate="{maxlength:32}" title="只能输入数字" value="0.00" onkeyup="onlyNonNegative(this)"  class="underlineinput" />
				</tr>
				<tr>
					<td class="l-t-td-left" width="120">检定周期（月）</td>
					<td class="l-t-td-right"><u:combo name="eq_check_cycle" code="TJY2_EQ_CHECKCYCLE" validate="required:true"/></td>
					<td class="l-t-td-left">设备状态</td>
					<td class="l-t-td-right"><u:combo name="eq_status" code="TJY2_EQ_STATUS" attribute="initValue:'01'"/></td>
				</tr>
				<%--<tr>
					<td class="l-t-td-left">装箱与否</td>
					<td class="l-t-td-right"><u:combo name="box_status" code="TJY2_EQ_BOXSTATUS" attribute="initValue:'02'"/></td>	
					<td class="l-t-td-left">出入库状态</td>
					<td class="l-t-td-right"><u:combo name="eq_inout_status" code="TJY2_EQ_INOUT_STATUS" attribute="initValue:'03'"/></td>
				</tr> --%>
				<tr>
					<td class="l-t-td-left">管理（使用）人员</td>
					<td class="l-t-td-right">
					<input name="eq_user" id="eq_user" type="text" ltype='text' validate="{maxlength:64,required:true}" readonly="readonly" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/></td>
					<td class="l-t-td-left">管理(使用)部门</td>
					<td class="l-t-td-right">
					<input name="eq_use_department" id="eq_use_department" type="text" ltype='text' validate="{maxlength:64,required:true}" readonly="readonly" ligerui="{iconItems:[{icon:'org'}]}"/></td>
					
				</tr>
				<tr>
					<td class="l-t-td-left">备注</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="remark" rows="2" cols="25" class="l-textarea" validate="{maxlength:1000}"></textarea>
					</td>						
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>检定证书、仪器照片上传</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">照片：</td>
					<td class="l-t-td-right">
						<input name="uploadFiles" type="hidden" id="uploadFiles" name="uploadFiles" validate="{maxlength:1000}" />
						<p id="procufilesDIV">
							<a class="l-button" id="procufilesBtn">
								<span class="l-button-main"><span class="l-button-text">选择文件</span></span>
							</a>
						</p>
				    	<div class="l-upload-ok-list">
							<ul id="procufiles"></ul>
						</div>
					</td>	
				</tr>
			</table>
		</fieldset>
	</form>
	</div>
	<c:if test='${param.status=="detail" }'>
		<div title="出入库记录" id="form2"></div> 
		<div title="使用登记" id="form3"></div>
	</c:if>
</div>
</body>
</html>
