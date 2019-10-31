<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*" %> 
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User user = (User)curUser.getSysUser();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = dateFormat.format(new Date());
%>
<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="pub/bpm/js/util.js"></script>
	<link type="text/css" rel="stylesheet" href="app/car/car_wb/pc/css/form_detail.css" />
	<script type="text/javascript" src="app/car/js/choose_car.js"></script>
    <script type="text/javascript">
    var pageStatus = "${param.pageStatus}";
    var isChecked = "${param.isChecked}";
	var serviceId = "${requestScope.serviceId}";
	var activityId = "${requestScope.activityId}";
	var processId = "${requestScope.processId}";
	var areaFlag;
	<bpm:ifPer function="CAR_WB_CDFZR_SH" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>//车队负责人审核
	<bpm:ifPer function="CAR_WB_BGSZR_SH" activityId = "${requestScope.activityId}">areaFlag="3";</bpm:ifPer>//办公室主任审核申请
	//<bpm:ifPer function="CAR_WB_CDFZR_GZ" activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>//车队负责人盖章
	var tbar="";
	$(function() {
		if(isChecked!="" && typeof(isChecked)!="undefined"){
			$("#form1").transform("detail");
			$("#form1").attr("getAction","carWbAction/detail.do?id="+serviceId);
			//if("3"==areaFlag){
				//tbar=[
					//{ text: '盖章', id: 'seal', icon: 'submit', click: seal}];
			//}else{
				tbar=[
					{ text: '通过', id: 'checkPass', icon: 'submit', click: checkPass},
					{ text: '不通过', id: 'checkNoPass', icon: 'del', click: checkNoPass},
	                { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
			//}
		}else{
			if("add"==pageStatus || "modify"==pageStatus){
				tbar=[
					{text: "保存", id: 'save', icon: "save", click: function(){
				    	if ($("#form1").validate().form()) {
				    		$("#form1").submit();
				    	}else{
				    		$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');
				    	}
		  			}},
					{text: "关闭", id: 'close', icon: "cancel", click: function(){api.close();}}
				];
			}else{
				tbar=[
					{text: "关闭", id: 'close', icon: "cancel", click: function(){api.close();}}
				];
			}
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
				var fleetDealId = resp.data.fleetDealId;
				var officeDealId = resp.data.officeDealId;
				var sealId = resp.data.sealId;
				var dataStatus = resp.data.dataStatus;
				if(((fleetDealId != null && fleetDealId != "")||(officeDealId !=null && officeDealId != "")||(sealId != null && sealId != "")) && (dataStatus != null && dataStatus != "" && dataStatus != "9") ){
					$.ajax({
			        	url: "carWbAction/getSignPictures.do?fleetDealId="+fleetDealId+"&officeDealId="+officeDealId+"&sealId="+sealId,
			            type: "POST",
			            success: function (resp) {
							if(resp.success){
								//获取当前网址
				            	var curWwwPath=window.document.location.href;      
				        		//获取主机地址之后的目录    
				        		var pathName=window.document.location.pathname;     
				        		var pos=curWwwPath.indexOf(pathName);     
				        		//获取主机地址
				        		var localhostPaht=curWwwPath.substring(0,pos);
				        		var realPath=localhostPaht;
				        		if(typeof(resp.fleet_signPicture)!="undefined"){
				        			var image_url = realPath+"/upload/"+resp.fleet_signPicture;
				        			createPictureDiv("fleet_signPicture",image_url);
				        		}
				        		if(typeof(resp.office_signPicture)!="undefined"){
				        			var image_url = realPath+"/upload/"+resp.office_signPicture;
				        			createPictureDiv("office_signPicture",image_url);
				        			$("#office_signPicture").attr("src",realPath+"/upload/"+resp.office_signPicture);
				        		}
				        		if(typeof(resp.seal_signPicture)!="undefined"){
				        			var image_url = realPath+"/upload/"+resp.seal_signPicture;
				        			createPictureDiv("seal_signPicture",image_url);
				        		}
			            	}
			            }
			        });
				}
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
	})
	//审核通过
	function checkPass(){
     	$.ligerDialog.confirm('是否审核通过？', function (yes){
	        if(!yes){return false;}
	        $("body").mask("正在处理，请稍后！");
	        getServiceFlowConfig("TJY2_CAR_WB","",function(result,data){
	        	if(result){
	            	top.$.ajax({
	                	url: "carWbAction/checkPass.do?id="+serviceId+
	                    	"&typeCode=TJY2_CAR_WB&status="+"&activityId="+activityId+"&areaFlag="+areaFlag,
	                    type: "POST",
	                    dataType:'json',
	                    async: false,
	                    success:function (data) {
	                    	if (data) {
	                       	api.data.window.Qm.refreshGrid();
	                    	api.close();
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
	//审核不通过
	function checkNoPass(){
       	$.ligerDialog.confirm('是否要不通过？', function (yes){
  	         if(!yes){return false;}
  	    	 $("body").mask("正在处理，请稍后！");
  	    	 getServiceFlowConfig("TJY2_CAR_WB","",function(result,data){
  	    		 if(result){
  	                 top.$.ajax({
  	                     url: "carWbAction/checkNoPass.do?id="+serviceId+
  	                    		 "&typeCode=TJY2_CAR_WB&status="+"&activityId="+activityId+"&areaFlag="+areaFlag+"&processId="+processId,
  	                     type: "POST",
  	                     dataType:'json',
  	                     async: false,
  	                     success:function (data) {
  	                         if (data) {
  	                           	api.data.window.Qm.refreshGrid();
  				                api.close();
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
	//盖章
	function seal(){
     	$.ligerDialog.confirm('是否盖章？', function (yes){
	        if(!yes){return false;}
	        $("body").mask("正在处理，请稍后！");
	        getServiceFlowConfig("TJY2_CAR_WB","",function(result,data){
	        	if(result){
	            	top.$.ajax({
	                	url: "carWbAction/checkPass.do?id="+serviceId+
	                    	"&typeCode=TJY2_CAR_WB&status="+"&activityId="+activityId+"&areaFlag="+areaFlag,
	                    type: "POST",
	                    dataType:'json',
	                    async: false,
	                    success:function (data) {
	                    	if (data) {
	                       	api.data.window.Qm.refreshGrid();
	                    	api.close();
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
	//添加image
	function createPictureDiv(imageId, imageUrl){
		var myParent = document.getElementById("signPictureContainer"); 
		var myImage = document.createElement("img");
		myImage.style.width = "80px";
		myImage.style.height = "34px";
		myImage.id = imageId;
		myImage.src = imageUrl;
		if(imageId=="seal_signPicture"){
			myParent = document.getElementById("sealContainer"); 
			myImage.style.width = "120px";
			myImage.style.height = "120px";
		}
		myParent.appendChild(myImage);
	}
	//选择车辆
	function selectCarInfo(){
		selectCar('1','1','','','1',function(datas){
			var carInfo = datas.jsonObj.carInfo[0];
			$("#fkCarId").val(carInfo.id);
        	$("#carNum").val(carInfo.car_num);
			//$("#carUnit").val(carInfo.manager_room_name);
        	$("#carBrand").val(carInfo.car_brand);
        	$("#engineNo").val(carInfo.engine_no);
        	$("#frameNo").val(carInfo.frame_no);
        	$("#carMileage").val(carInfo.car_mileage);
		});
	}
    </script>
</head>
<body>
    <form id="form1" action="carWbAction/saveInfo.do?pageStatus=${param.pageStatus}" getAction="carWbAction/detail.do?id=${param.id}">
    <h1 style="padding:5mm 0 2mm 0;font-family:宋体;font-size:7mm;text-align:center;">在蓉省级单位公务用车送修单</h1></br>
    	<input type="hidden" id="id" name="id"/>
    	<input type="hidden" id="fkCarId" name="fkCarId"/>
        <input type="hidden" id="applyUserId" name="applyUserId" value="<%=user.getId() %>"/>
        <input type="hidden" id="carMileage" name="carMileage"/>
        <input type="hidden" id="fleetDealId" name="fleetDealId"/>
        <input type="hidden" id="fleetDealName" name="fleetDealName"/>
        <input type="hidden" id="fleetDealResult" name="fleetDealResult"/>
        <input type="hidden" id="fleetDealRemark" name="fleetDealRemark"/>
        <input type="hidden" id="fleetDealDate" name="fleetDealDate"/>
        <input type="hidden" id="officeDealId" name="officeDealId"/>
        <input type="hidden" id="officeDealName" name="officeDealName"/>
        <input type="hidden" id="officeDealResult" name="officeDealResult"/>
        <input type="hidden" id="officeDealRemark" name="officeDealRemark"/>
        <input type="hidden" id="officeDealDate" name="officeDealDate"/>
        <input type="hidden" id="sealUserId" name="sealUserId"/>
        <input type="hidden" id="sealUserName" name="sealUserName"/>
        <input type="hidden" id="sealId" name="sealId"/>
        <input type="hidden" id="createUserId" name="createUserId"/>
        <input type="hidden" id="createUserName" name="createUserName"/>
        <input type="hidden" id="createDate" name="createDate"/>
        <input type="hidden" id="lastModifyUserId" name="lastModifyUserId"/>
        <input type="hidden" id="lastModifyUserName" name="lastModifyUserName"/>
        <input type="hidden" id="lastModifyDate" name="lastModifyDate"/>
        <input type="hidden" id="dataStatus" name="dataStatus" value="0"/>
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       <tr> 
        <td class="l-t-td-left"> 车属单位</td>
        <td class="l-t-td-right" colspan="5"> 
        <input name="carUnit" id="carUnit" type="text" ltype='text' validate="{required:true,maxlength:100}" value="四川省特种设备检验研究院"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 车牌号</td>
		<td class="l-t-td-right" colspan="2"><input name="carNum" id="carNum" type="text"
						readonly="readonly" title="点击此处选择车辆"
						ltype='text' validate="{required:true}"
						onclick="selectCarInfo()"
						ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectCarInfo()}}]}" />
					</td>
		<td class="l-t-td-left"> 品牌型号</td>
        <td class="l-t-td-right"colspan="2"> 
        <input name="carBrand" id="carBrand" type="text" ltype='text' validate="{required:true,maxlength:100}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 发动机号</td>
        <td class="l-t-td-right" colspan="2"> 
        <input name="engineNo" id="engineNo" type="text" ltype='text' validate="{required:true,maxlength:100}"/>
        </td>
        <td class="l-t-td-left"> 车辆识别代码</td>
        <td class="l-t-td-right" colspan="2"> 
        <input name="frameNo" id="frameNo" type="text" ltype='text' validate="{required:true,maxlength:100}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 送修人姓名</td>
        <td class="l-t-td-right" colspan="2"> 
        <input name="applyUserName" id="applyUserName" type="text" ltype='text' validate="{required:true,maxlength:100}" value="<%=user.getName() %>"/>
        </td>
        <td class="l-t-td-left"> 送修时间</td>
        <td class="l-t-td-right" colspan="2"> 
        <input name="applyDate" id="applyDate" type="text" ltype='date' ligerui="{format:'yyyy年MM月dd日'}" validate="{required:true}" value="<%=nowTime %>"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left" rowspan="3"> 维保项目</td>
        <td colspan="5" style='height: 220px;'>
		<textarea name="contents" id="contents" rows="14" cols="50" class="l-t-td-textarea" validate="{maxlength:4000}" style="border-bottom:0px"></textarea>
	    </td>
       </tr>
       <tr> 
        <td style='text-align:left;padding-left: 5px;border-right: 0px;height: 60px;border-bottom: 0px;'> 批准人（签名）：</td>
        <td colspan="3" class="l-t-td-right" style='border-right: 0px;border-left: 0px;border-bottom: 0px;'>
       	<div id = "signPictureContainer">
	    </div>
		</td>
         <td rowspan ="2" class="l-t-td-right" style='text-align:center;border-left: 0px;border-top: 0px;'>
        	<div id = "sealContainer" style="position:relative;">
	        <span style="position:absolute; left:0; top:35%; text-align: center; padding-left: 20%; height: 28px;">
	        	单&nbsp;位&nbsp;（&nbsp;盖&nbsp;章&nbsp;）
	        	<input name="sealData" id="sealData" type="text" ltype='date' ligerui="{format:'yyyy年MM月dd日'}"  validate="{maxlength:100}"/>
	        </span>
			</div>
        </td>
       </tr>
       <tr>
       	<td colspan="4" style='text-align:center;border-right: 0px;border-top: 0px;height: 60px;'> </td>
       </tr>
      </table>
    </form> 
</body>
</html>
