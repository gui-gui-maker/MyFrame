<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script test="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
	$(function() {
		//发票附件执行上传
    	var receiptUploadConfig = {
    			fileSize : "2mb",//文件大小限制
    			businessId : "",//业务ID
    			buttonId : "receiptfilesBtn",//上传按钮ID
    			container : "receiptfilesDIV",//上传控件容器ID
    			title : "请选择车辆照片",//文件选择框提示
    			extName : "jpg,gif,jpeg,png,bmp",//文件扩展名限制
    			saveDB : true,//是否往数据库写信息
    			attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
    			fileNum : 1,//限制上传文件数目
    			callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				$("#receiptfilesBtn").hide();
    				addAttachFile(files);
    			}
    		};
		var receiptUploader= new KHFileUploader(receiptUploadConfig);
		
		$("#formObj").initForm({
			success : function(responseText) {//处理成功
				if (responseText.success) {
					top.$.notice("保存成功！");
					if(api.data.window.Qm){
						api.data.window.Qm.refreshGrid();
					}else{
						api.data.window.getData();
					}
					api.close();
				} else {
					$.ligerDialog.error(responseText.msg)
				}
			},
			getSuccess : function(response) {
				//显示采购单附件
				var files=response.files;
				showAttachFile(files);
				if (response.success){
					processChanage(response.data.state);
				}
				else {
					$.ligerDialog.alert("获取数据错误!");
					return;
				}
			}
		});
	});
	function addAttachFile(files){
		if("${param.status}"=="detail"){
			$("#receiptfilesBtn").hide();
		}
		/**
		
		if(files){
			$.each(files,function(i){
				var file=files[i];
				$("#receiptfiles").append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByObjId.do?id="+file.id+"'>"+file.name+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,deleteFileUp)'>&nbsp;</div>"+
						"</li>");
			});
			getUploadFile();
		}
		*/
		if(files){
			$.each(files,function(i){
				var data=files[i];
				createFileView(data.id,data.name,$("head").attr("pageStatus")=="detail"?false:true,"receiptfiles",true,function(fid){
					deleteFileUp();
				})
				getUploadFile();
			})
		}
	}
	 //显示附件文件
    function showAttachFile(files){
    	if("${param.status}"=="detail"){
			$("#receiptfilesBtn").hide();
		}
    	
		if(files!=null&&files!=""){
			$.each(files,function(i){
				var data=files[i];
				$("#receiptfilesBtn").hide();
				createFileView(data.id,data.fileName,$("head").attr("pageStatus")=="detail"?false:true,"receiptfiles",true,function(fid){
					deleteFileUp();
				})
			})
			//详情
			/**
			if("${param.status}"=="detail"){
				$.each(files,function(i){
					var file=files[i];
					$("#receiptfilesBtn").hide();
					$("#receiptfiles").append("<li id='"+file.id+"'>"+
											"<div><a href='fileupload/downloadByObjId.do?id="+file.id+"'>"+file.fileName+"</a></div>"+
											"</li>");
				});
			}
			//修改
			else if("${param.status}"=="modify"){
				$.each(files,function(i){
					var file=files[i];
					$("#receiptfilesBtn").hide();
					$("#receiptfiles").append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/downloadByObjId.do?id="+file.id+"'>"+file.fileName+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,deleteFileUp)'>&nbsp;</div>"+
							"</li>");
				});
			}
			**/
		}
    }
  //将上传的所有文件id写入隐藏框中
	function getUploadFile(){
		var attachId="";
		var i=0;
		$("#receiptfiles li").each(function(){
			attachId+=(i==0?"":",")+this.id;
			i=i+1;
		});
		$("#carpicId").val(attachId);
	}
	function deleteFileUp(){
		$("#receiptfilesBtn").show();
		$("#procufilesBtn").show();
		getUploadFile();
	}
	function processChanage(va) {
		if (va == "1") {
			//租用
			$("#room-div-tr1").hide();
			$("#rental-div-tr1").show();
			$("#rental-div-tr2").show();
			$("#rental-div-tr3").show();
			if("${param.status}"!="detail"){
				$("#managerRoomName").rules("remove", "required");
				$("#buyDate").rules("remove", "required");
				$("#dayRentalPrice").rules("add",{required:true});
				$("#basicMileage").rules("add",{required:true});
				$("#basicTime").rules("add",{required:true});
				$("#exceedMileagePrice").rules("add",{required:true});
				$("#exceedTimePrice").rules("add",{required:true});
			}
			$("#managerRoomCode").val('');
			$("#managerRoomName").val('');
			$("#buyDate").val('');
		} else {
			$("#room-div-tr1").show();
			$("#rental-div-tr1").hide();
			$("#rental-div-tr2").hide();
			$("#rental-div-tr3").hide();
			if("${param.status}"!="detail"){
				$("#managerRoomName").rules("add", {required:true});
				$("#buyDate").rules("add", {required:true});
				$("#dayRentalPrice").rules("remove", "required");
				$("#basicMileage").rules("remove", "required");
				$("#basicTime").rules("remove", "required");
				$("#exceedMileagePrice").rules("remove", "required");
				$("#exceedTimePrice").rules("remove", "required");
			}
			$("#dayRentalPrice").val('0');
			$("#basicMileage").val('0');
			$("#basicTime").val('0');
			$("#exceedMileagePrice").val('0');
			$("#exceedTimePrice").val('0');
		}
	}
	function getDriver(){
		var title = "驾驶员选择";
		var url = "url:app/oa/car/select_driver_list.jsp";
		var width = 750	;
		top.$.dialog({
			width : width,
			height : 400,
			lock : true,
			parent : api,
			id : "win98",
			title : title,
			content : url,
			cancel: true,
			ok : function() {
				var data = this.iframe.contentWindow.driverSelect();
				$("#driverCode").val(data.id);
		        $("#driver").val(data.name);
				return true;
			}
		});
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="oa/car/info/save.do"
		getAction="oa/car/info/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id" />
		<input name="carState" type="hidden" id="id"/>
		<input name="carStateDate" type="hidden" id="id"/>
		<input name="orgCode" id="orgCode" type="hidden" value="<sec:authentication property="principal.unit.id"/>"/>
		<input name="orgName" type="hidden"  validate="{required:true,maxlength:50}" value="<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>"/>
		<table border="1" cellpadding="3" cellspacing="0" id="form-table"
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">车牌号：</td>
				<td class="l-t-td-right"><input name="carNum" type="text"
					ltype='text' validate="{required:true,maxlength:10}" /></td>
				<td class="l-t-td-left">车辆品牌：</td>
				<td class="l-t-td-right"><input name="carBrand" type="text"
					ltype='text' validate="{required:true,maxlength:20}" /></td>
					<!-- 车架号 -->
			</tr>
			<tr>
			    <td class="l-t-td-left">排气量：</td>
				<td class="l-t-td-right">
				<input name="carDisplacement" type="text" ltype='spinner' validate="{required:true,maxlength:10}" ligerui="{type:'float',isNegative:false,suffix:'L',suffixWidth:'45'}" />
				</td>
				<td class="l-t-td-left">车型：</td>
				<td class="l-t-td-right"><input name="carType" type="text"
					ltype='text' validate="{required:false,maxlength:15}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">颜色：</td>
				<td class="l-t-td-right"><input name="color" type="text"
					ltype='text' validate="{maxlength:10}" /></td>
					<td class="l-t-td-left">产地：</td>
				<td class="l-t-td-right"><input name="address" type="text"
					ltype='text' validate="{required:false,maxlength:50}" /></td>
			</tr>
			<tr>
			    <td class="l-t-td-left">百公里油耗：</td>
				<td class="l-t-td-right"><input type="text" ltype='spinner' name="oilConsumption100" validate="{required:false,maxlength:20}" ligerui="{type:'float',isNegative:false,suffix:'L',suffixWidth:'45'}" /></td>
				<td class="l-t-td-left">行驶里程：</td>
				<td class="l-t-td-right"><input name="carMileage" type="text"
					ltype='spinner' validate="{maxlength:10}" ligerui="{type:'float',isNegative:false,suffix:'KM',suffixWidth:'45'}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">核定载人数：</td>
				<td class="l-t-td-right">
				<input name="loadNumber" type="text" ltype='spinner' validate="{required:false,maxlength:3,digits:true}" ligerui="{type:'int',isNegative:false,suffix:'人',suffixWidth:'45'}"/>
				</td>
				<td class="l-t-td-left">车辆价格：</td>
				 <td class="l-t-td-right">
				 <input name="carMoney" type="text" ltype='spinner' validate="{required:false,maxlength:10}" ligerui="{type:'float',isNegative:false,suffix:'元',suffixWidth:'45'}" />
				 </td>
			</tr>
			<tr>
				<td class="l-t-td-left">发动机号：</td>
				<td class="l-t-td-right">
				<input name="engineNo" type="text" ltype='text' validate="{required:false,maxlength:100}" />
				</td>
				<td class="l-t-td-left">车架号码：</td>
				 <td class="l-t-td-right">
				 <input name="frameNo" type="text" ltype='text' validate="{required:false,maxlength:100}" />
				 </td>
			</tr>
		    <tr>
		        <td class="l-t-td-left">购置日期：</td>
				<td class="l-t-td-right"><input name="buyDate" id="buyDate" type="text" 
					ltype='date' validate="" ligerUi="{format:'yyyy-MM-dd'}" /></td>
				<td class="l-t-td-left">批复文号：</td>
				<td class="l-t-td-right"><input name="approvalNumber" type="text"
					ltype='text' validate="{maxlength:20}" /></td>
		    </tr>
			<tr>
				<td class="l-t-td-left">驾驶员：</td>
				<td class="l-t-td-right">
					<input type="hidden" name="driverCode" id="driverCode"/>
					<input id="driver" name="driver" type="text" ltype='text' readonly="readonly" validate="{maxlength:20}" onclick="getDriver()" 
					ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){getDriver()}}]}"/>
				</td>
				<td class="l-t-td-left">技术状态：</td>
				<td class="l-t-td-right"><input name="technicalCondition" type="text"
					ltype='text' validate="{maxlength:20}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">变更号：</td>
				<td class="l-t-td-right"><input name="changeNum" type="text"
					ltype='text' validate="{maxlength:10}" /></td>
				<td class="l-t-td-left"> 车辆类型：</td>
                <td class="l-t-td-right">
               	 <input name="state" type="radio" ltype="radioGroup" validate="{required:true}" ligerui="{onChange:processChanage,value:'0',data:[{text:'公有',id:'0'},{text:'租用',id:'1'}] }"/>
                </td> 
			</tr>
			<tr id="room-div-tr1">
				    <td class="l-t-td-left">所属部门：</td>
					<td class="l-t-td-right" >
					<input type="hidden" name="managerRoomCode" id="managerRoomCode"/>
					<input id="managerRoomName" name="managerRoomName" type="text" ltype='text' readonly="readonly" validate="{maxlength:50}" onclick="selectUnitOrUser(0,0,'managerRoomCode','managerRoomName','')" 
					ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){selectUnitOrUser(0,0,'managerRoomCode','managerRoomName','')}}]}"/>
					</td>
				<td class="l-t-td-left">类别：</td>
				<td class="l-t-td-right"><input name="type" id="type" type="text" 
				 ltype='select'  ligerui="{initValue:'',data:[{id:'1',text:'一类车辆'},{id:'2',text:'二类车辆'},
				 {id:'3',text:'三类车辆'},{id:'4',text:'其他类车辆'}]}"  />
				</td>
			</tr>
			<tr style="display:none;" id="rental-div-tr1">
			    <td class="l-t-td-left">日租价：</td>
				<td class="l-t-td-right" colspan="3">
				<input name="dayRentalPrice" id="dayRentalPrice" type="text"
					ltype='spinner' validate="{maxlength:10}" ligerui="{type:'float',isNegative:false,suffix:'元/天',suffixWidth:'45'}" />
				</td>
			</tr>
			<tr style="display:none;" id="rental-div-tr2">
			    <td class="l-t-td-left">基本里程：</td>
				<td class="l-t-td-right"><input name="basicMileage" id="basicMileage" type="text"
					ltype='spinner' validate="{maxlength:10}" ligerui="{type:'float',isNegative:false,suffix:'公里',suffixWidth:'45'}" /></td>
				<td class="l-t-td-left">超基本里程单价：</td>
				<td class="l-t-td-right"><input name="exceedMileagePrice" id="exceedMileagePrice" type="text"
					ltype='spinner' validate="{maxlength:10}" ligerui="{type:'float',isNegative:false,suffix:'元/公里',suffixWidth:'45'}" /></td>
			</tr>
			<tr style="display:none;" id="rental-div-tr3">
			    <td class="l-t-td-left">基本时间：</td>
				<td class="l-t-td-right"><input name="basicTime" id="basicTime" type="text"
					ltype='spinner' validate="{maxlength:10}" ligerui="{type:'float',isNegative:false,suffix:'小时',suffixWidth:'45'}"/>
				</td>
				<td class="l-t-td-left">超基本时间单价：</td>
				<td class="l-t-td-right"><input name="exceedTimePrice" id="exceedTimePrice" type="text"
				 ltype='spinner' validate="{maxlength:10}" ligerui="{type:'float',isNegative:false,suffix:'元/小时',suffixWidth:'45'}"  />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
				<td class="l-t-td-right" colspan="3">
				<textarea name="remark" id="remark" rows="7" cols="7" validate="{maxlength:2000}" ></textarea>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">车辆照片：</td>
				<td class="l-t-td-right" colspan="3" id="receiptfilesDIV">
					<input name="carpicId" id="carpicId" type="hidden"/>
					<a class="l-button3" id="receiptfilesBtn" title="上传文件">+</a>
					<div class="l-upload-ok-list"><ul id="receiptfiles"></ul></div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>

