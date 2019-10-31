<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus = "${param.pageStatus}">
<title>固定资产信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<!-- 生成条形码JS导入 -->
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<script type="text/javascript">
	var pageStatus = "${param.pageStatus}";
	var tbar="";
	$(function() {
		$("#txm").hide();//隐藏条形码
		if(pageStatus=="detail"){
			if(("${param.fromList}")!="back"){
				tbar=[
		 			<sec:authorize ifAnyGranted="TJY2_EQ_MANAGER,sys_administrate">
		 			{text: "打印标签", id: "print", icon: "print", click: function(){printTag();}},
		 			</sec:authorize>
		 			{text: "关闭", icon: "cancel", click: function(){api.close();}}];
 			}else{
 				tbar=[{text: "关闭", icon: "cancel", click: function(){api.close();}}];
 			}
	 	}else if(pageStatus=="add"){
	 		tbar=[{text: "保存", icon: "save", click: function(){
		      				//表单验证
					    	if ($("#form1").validate().form()) {
					    		$("#form1").submit();
					    	}else{
					    		$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');
					    	}
		      			}},
				  {text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}else if(pageStatus=="modify"){
	 		tbar=[{text: "保存", icon: "save", click: function(){
  				//表单验证
		    	if ($("#form1").validate().form()) {
		    		$("#form1").submit();
		    	}else{
		    		$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');
		    	}
  			}},
	  			{text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}
		
		$("#form1").initForm({
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
	})	
	//使用部门选择
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
                $("#userDepartmentId").val(p.id);
                $("#userDepartment").val(p.name);
            }
        });
    }
	//使用人员选择
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
	                    $("#userId").val(p.id);
	                    $("#userName").val(p.name);
	                    $("#userDepartmentId").val(p.org_id);
	                    $("#userDepartment").val(p.org_name); 
	                }
	            });
	        } 
	//判断卡片编号是否重复
	function checkCardNo(){
		//alert("i`m coming~~~~");
		var cardNo = $("#cardNo").val();
		if(cardNo == ""||cardNo == "undefined"){
			$.ligerDialog.alert("请输入卡片编号！");
		}else{
			$.ajax({
	        	url: "equipPpeAction/checkCardNo.do?cardNo="+cardNo,
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            success: function (resp) {
	            	var data = resp.list;
	            	if(data.length!=0){
	            		$.ligerDialog.alert("此卡片编号已存在，请更换卡片编号！");
	            	}else{
	            	}
	            	
	            },
	            error: function (data) {
	            	$.ligerDialog.alert("卡片编号验证失败！");
	            }
	        });
		}
	}
	//判断自编号是否重复
	function checkSelfNo(){
		//alert("i`m coming~~~~");
		var selfNo = $("#selfNo").val();
		if(selfNo == ""||selfNo == "undefined"){
			$.ligerDialog.alert("请输入自编号！");
		}else{
			$.ajax({
	        	url: "equipPpeAction/checkSelfNo.do?selfNo="+selfNo,
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            success: function (resp) {
	            	var data = resp.list;
	            	if(data.length){
	            		$.ligerDialog.alert("此自编号已存在，请更换自编号！");
	            	}else{
	            	}
	            	
	            },
	            error: function (data) {
	            	$.ligerDialog.alert("自编号验证失败！");
	            }
	        });
		}
	}
	//打印标签
	function printTag() {
		var assetName = $("#assetName").text();//名称
		var owner = ("${param.owner}");
		var selfNo = $("#selfNo").text();//自编号/编号
		var spaciModel = $("#spaciModel").text();//规格型号
		var sn = $("#sn").text();//序列号/出厂编号
		var productDate = $("#productDate").text();//出厂日期
		var userDepartment = $("#userDepartment").text();//使用部门
		var BarCodeValue = $.trim($("#barcode").text());
		//var BarCodeValue = $("#barcode").text().trim();
    	var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
    	//条形码
    	if(owner == "100047"){
    		LODOP.ADD_PRINT_TEXT(0,10,300,10,"鼎盛公司办公设备标签");
    	}else if(owner == "100044"){
      		LODOP.ADD_PRINT_TEXT(0,10,300,10,"司法鉴定中心办公设备标签");
      	}else if(owner == "100042"){
      		LODOP.ADD_PRINT_TEXT(0,10,300,10,"检验检测协会办公设备标签");
      	}else if(owner == "100000"){
    		LODOP.ADD_PRINT_TEXT(0,10,300,10,"川特院办公设备标签");
    	}else{
    		$.ligerDialog.alert("请确认自编号为<font color='red'>"+selfNo+"</font>,名称为<font color='red'>"+assetName+"</font>的归属！");
    		return
    	}
	    LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
		LODOP.SET_PRINT_STYLEA(0,"Bold",50);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
		
	    LODOP.ADD_PRINT_TEXT(22,10,80,10,"资产名称：");
	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
		LODOP.ADD_PRINT_TEXT(22,70,125,10,assetName);
	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);

		LODOP.ADD_PRINT_TEXT(35,10,80,10,"资产编号：");
	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
		LODOP.ADD_PRINT_TEXT(35,70,125,10,selfNo);
	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
		
	    LODOP.ADD_PRINT_TEXT(48,10,80,10,"规格型号：");
	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
		LODOP.ADD_PRINT_TEXT(48,70,125,10,spaciModel);
	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
		
	    LODOP.ADD_PRINT_TEXT(62.5,10,80,10,"出厂编号：");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
		if(sn.length>16){
			LODOP.ADD_PRINT_TEXT(61,70,125,15,sn);
    		LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-4);
    		LODOP.SET_PRINT_STYLEA(0,"FontSize",5);
		}else{
			LODOP.ADD_PRINT_TEXT(62.5,70,125,15,sn);
    		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		}
		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
		
		LODOP.ADD_PRINT_TEXT(77,10,80,10,"出厂日期：");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true); 
		LODOP.ADD_PRINT_TEXT(77,70,125,10,productDate);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true); 
		
		LODOP.ADD_PRINT_TEXT(90,10,80,10,"使用部门：");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true); 
		LODOP.ADD_PRINT_TEXT(90,70,125,10,userDepartment);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true); 
		
		
		/* ADD_PRINT_BARCODE(Top,Left,Width,Height,BarCodeType,BarCodeValue); */
        /* LODOP.ADD_PRINT_BARCODE(54,50,300,45,"Code93",BarCodeValue); //打印包含英文字母的条码*/
        /* LODOP.ADD_PRINT_BARCODE(0,165,45,170,"EAN13",BarCodeValue);//打印13位数的条码 
        LODOP.SET_PRINT_STYLEA(0,"Angle",90);  */
        LODOP.ADD_PRINT_BARCODE(13,181,114,114,"QRCode",BarCodeValue);
        //LODOP.PREVIEW();
        LODOP.PRINT();
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
</script>
</head>
<body>
	<div title="固定资产信息" id="formObj">
    <form id="form1" action="equipPpeAction/save.do?status=${param.pageStatus}" getAction="equipPpeAction/detail.do?id=${param.id}">
     <input type="hidden" name="userDepartmentId" id="userDepartmentId"/>
     <input type="hidden" name="inventoryDate" id="inventoryDate"/>
     <input type="hidden" name="inventoryId" id="inventoryId"/>
     <input type="hidden" name="inventoryName" id="inventoryName"/>
     <input type="hidden" name="loanStatus" id="loanStatus" value="0"/>
     <input type="hidden" name="userId" id="userId"/>
     <input type="hidden" name="createDate" id="createDate"/>
     <input type="hidden" name="createId" id="createId"/>
     <input type="hidden" name="createBy" id="createBy"/>
     <input type="hidden" name="lastModifyDate" id="lastModifyDate"/>
     <input type="hidden" name="lastModifyId" id="lastModifyId"/>
     <input type="hidden" name="lastModifyBy" id="lastModifyBy"/>
     <fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					固定资产信息
				</div>
			</legend>
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
	  <tr> 
        <td class="l-t-td-left"> 卡片编号</td>
        <td class="l-t-td-right"> 
        <input name="cardNo" id="cardNo" type="text" ltype='text' validate="{maxlength:200}" onblur="checkCardNo()"/>
        </td>
        <td class="l-t-td-left"> 自编号</td>
        <td class="l-t-td-right"> 
        <input name="selfNo" id="selfNo" type="text" ltype='text' validate="{required:true,maxlength:200}" onblur="checkSelfNo()"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 资产名称</td>
        <td class="l-t-td-right"> 
        <input name="assetName" id="assetName" type="text" ltype='text' validate="{required:true,maxlength:200}"/>
        </td>
        <td class="l-t-td-left"> 规格型号</td>
        <td class="l-t-td-right"> 
        <input name="spaciModel" id="spaciModel" type="text" ltype='text' validate="{maxlength:200}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 序列号</td>
        <td class="l-t-td-right"> 
        <input name="sn" id="sn" type="text" ltype='text' validate="{maxlength:200}"/>
        </td>
        <td class="l-t-td-left"> 计量单位</td>
        <td class="l-t-td-right"> 
        <input name="unit" type="text" ltype='text' validate="{required:true,maxlength:200}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 价值（元）</td>
        <td class="l-t-td-right"> 
        <input name="assetValue" id="assetValue" type="text" ltype='text' title="只能输入数字" validate="{maxlength:200}" onkeyup="onlyNonNegative(this)"/>
        </td>
        <td class="l-t-td-left"> 原值（元）</td>
        <td class="l-t-td-right"> 
        <input name="originalValue" id="originalValue" type="text" ltype='text' title="只能输入数字" validate="{maxlength:200}" onkeyup="onlyNonNegative(this)"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left"> 使用部门</td>
        <td class="l-t-td-right"> 
        <input name="userDepartment" id="userDepartment" type="text" ltype='text' validate="{maxlength:100}"  readonly="readonly" onclick="chooseOrg();" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"/>
        </td>
        <td class="l-t-td-left"> 使用人</td>
        <td class="l-t-td-right"> 
        <input name="userName" id="userName" type="text" ltype='text' validate="{maxlength:100}" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 出厂日期</td>
        <td class="l-t-td-right"> 
        <input name="productDate" id="productDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"/>
        </td>
        <td class="l-t-td-left"> 入库日期</td>
        <td class="l-t-td-right"> 
        <input name="instockDate" id="instockDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left"> 发放日期</td>
        <td class="l-t-td-right"> 
        <input name="releaseDate" id="releaseDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"/>
        </td>
        <td class="l-t-td-left"> 资产状态</td>
        <td class="l-t-td-right"> 
        <!-- <input name="assetStatus" id="assetStatus" type="text" ltype='text' validate="{maxlength:32}"/> -->
        <u:combo name="assetStatus" code="TJY2_PPE_ASSET_STATUS" validate="{required:true}" attribute="initValue:'ZY'"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left"> 放置地点</td>
        <td class="l-t-td-right"> 
        <input name="placeLocation" id="placeLocation" type="text" ltype='text' validate="{maxlength:200}"/>
        </td>
       	<td class="l-t-td-left"> 是否盘点</td>
        <td class="l-t-td-right"> 
        <u:combo name="inventory" code="TJY2_EQUIP_INVENTORY" validate="{required:true}" attribute="initValue:'WPD'"/>
        </td>
	  </tr>
	  <tr>
	   <td class="l-t-td-left"> 最低使用年限</td>
        <td class="l-t-td-right"> 
        <input name="serviceLife" id="serviceLife" type="text" ltype='text' validate="{maxlength:200}"/>
        </td>
	   <td class="l-t-td-left">归属</td>
		<td class="l-t-td-right">
			<u:combo name="owner" code="TJY2_PPE_OWNER" attribute="initValue:'100000'"/>
		</td>
	  </tr>
       <tr>
		<td class="l-t-td-left">备注</td>
		<td class="l-t-td-right" colspan="3">
			<textarea name="remark" rows="2" cols="25" class="l-textarea" validate="{maxlength:500}"></textarea>
		</td>						
	  </tr>
       <tr id = "txm">
        <!-- <input type="hidden" name="id" id="id"/> -->
        <td class="l-t-td-left">ID</td>
	    <td class="l-t-td-right"><input name="id" id="id" type="text" ltype='text'/></td>
        <td class="l-t-td-left">条形码</td>
	    <td class="l-t-td-right"><input name="barcode" id="barcode" type="text" ltype='text' initvalue='0' validate="{maxlength:13}"/></td>
       </tr>
      </table>
      </fieldset>
    </form> 
</div>

</body>
</html>
