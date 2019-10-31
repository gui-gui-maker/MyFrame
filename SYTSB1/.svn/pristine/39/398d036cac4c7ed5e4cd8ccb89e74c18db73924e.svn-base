<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%@ include file="/k/kui-base-form.jsp"%>
<%
	DateFormat ds = new SimpleDateFormat("yyyyMMddHHmmss");
	String nowNum = ds.format(new Date());
%>
<style type="text/css">
	input{
	border:1px solid #000000;
        }
</style>
<script type="text/javascript" src="app/fwxm/scientific/js/doc_order.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/fwxm/scientific/ckeditor/ckeditor.js"></script>
 <!-- <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" /> -->
<script type="text/javascript">
	var type="";
	var wtype="";
	var id="";
	 var sum=0;
	  var sum1=0;
	  var num=0;
	  var num1=0;
	  var num2=0;
	  var num3=0;
	  var num4=0;
	  var num5=1;
	  var num6=0;
	  var num7=0;
	$(function() {
		CKEDITOR.replace('P100001');
		CKEDITOR.replace('P200001');
		CKEDITOR.replace('P300001');
		CKEDITOR.replace('P400001');
		CKEDITOR.replace('P500001');
		CKEDITOR.replace('P600001');
		if("${param.pageStatus}"=="modify"||"${param.pageStatus}"=="detail"){
			$.ajax({
 				url : "com/tjy2/scientificProvince/detailBase.do?id=${param.id}",
 				type : "POST",
 				success : function(res, stats) {
 					$("body").unmask();
 					if (res["success"]) {
 						$("#baseForm").setValues(res.data);
 						id=res.data.id;
 						if("${param.pageStatus}"=="detail"){
 							$("#datail").hide();
 							$("#P100001_text").html(res.P100001);
 							$("#P100001").hide();
 							$("#P200001_text").html(res.P200001);
 							$("#P200001").hide();
 							$("#P300001_text").html(res.P300001);
 							$("#P300001").hide();
 							$("#P400001_text").html(res.P400001);
 							$("#P400001").hide();
 							$("#P500001_text").html(res.P500001);
 							$("#P500001").hide();
 							$("#P600001_text").html(res.P600001);
 							$("#P600001").hide();
 						}
 						
 						//为富文本设置值
 						CKEDITOR.instances.P100001.setData(res.P100001);
 						//$("#P100001").html(res.P100001)
 						CKEDITOR.instances.P200001.setData(res.P200001);
 						CKEDITOR.instances.P300001.setData(res.P300001);
 						CKEDITOR.instances.P400001.setData(res.P400001);
 						CKEDITOR.instances.P500001.setData(res.P500001);
 						CKEDITOR.instances.P600001.setData(res.P600001);
 						
 						//为表格添加列和值
 							
 							if(res.data.p8000031!=null){
 								addNew1Modify(res.data.p8000031,res.data.p8000032,res.data.p8000033,res.data.p8000034,res.data.p8000035,res.data.p8000036,res.data.p8000037);
 							}
 							if(res.data.p8000038!=null){
 								addNew1Modify(res.data.p8000038,res.data.p8000039,res.data.p8000040,res.data.p8000041,res.data.p8000042,res.data.p8000043,res.data.p8000044);
 							}
 							if(res.data.p8000045!=null){
 								addNew1Modify(res.data.p8000045,res.data.p8000046,res.data.p8000047,res.data.p8000048,res.data.p8000049,res.data.p8000050,res.data.p8000051);
 							}
 							if(res.data.p8000052!=null){
 								addNew1Modify(res.data.p8000052,res.data.p8000053,res.data.p8000054,res.data.p8000055,res.data.p8000056,res.data.p8000057,res.data.p8000058);
 							}
 							if(res.data.p8000059!=null){
 								addNew1Modify(res.data.p8000059,res.data.p8000060,res.data.p8000061,res.data.p8000062,res.data.p8000063,res.data.p8000064,res.data.p8000065);
 							}
 							if(res.data.p8000066!=null){
 								addNew1Modify(res.data.p8000066,res.data.p8000067,res.data.p8000068,res.data.p8000069,res.data.p8000070,res.data.p8000071,res.data.p8000072);
 							}
 							if(res.data.p8000073!=null){
 								addNew1Modify(res.data.p8000073,res.data.p8000074,res.data.p8000075,res.data.p8000076,res.data.p8000077,res.data.p8000078,res.data.p8000079);
 							}
 							if(res.data.p8000080!=null){
 								addNew1Modify(res.data.p8000080,res.data.p8000081,res.data.p8000082,res.data.p8000083,res.data.p8000084,res.data.p8000085,res.data.p8000086);
 							}
 							if(res.data.p8000087!=null){
 								addNew1Modify(res.data.p8000087,res.data.p8000088,res.data.p8000089,res.data.p8000090,res.data.p8000091,res.data.p8000092,res.data.p8000093);
 							}
 							if(res.data.p8000094!=null){
 								addNew1Modify(res.data.p8000094,res.data.p8000095,res.data.p8000096,res.data.p8000097,res.data.p8000098,res.data.p8000099,res.data.p8000100);
 							}
 							if(res.data.p8000101!=null){
 								addNew1Modify(res.data.p8000101,res.data.p8000102,res.data.p8000103,res.data.p8000104,res.data.p8000105,res.data.p8000106,res.data.p8000107);
 							}
 							if(res.data.p8000108!=null){
 								addNew1Modify(res.data.p8000108,res.data.p8000109,res.data.p8000110,res.data.p8000111,res.data.p8000112,res.data.p8000113,res.data.p8000114);
 							}
 							if(res.data.p8000115!=null){
 								addNew1Modify(res.data.p8000115,res.data.p8000116,res.data.p8000117,res.data.p8000118,res.data.p8000119,res.data.p8000120,res.data.p8000121);
 							}
 							if(res.data.p8000122!=null){
 								addNew1Modify(res.data.p8000122,res.data.p8000123,res.data.p8000124,res.data.p8000125,res.data.p8000126,res.data.p8000127,res.data.p8000128);
 							}
 							if(res.data.p8000129!=null){
 								addNew1Modify(res.data.p8000129,res.data.p8000130,res.data.p8000131,res.data.p8000132,res.data.p8000133,res.data.p8000134,res.data.p8000135);
 							}
 							
 							$("#p700001").val(res.data.p700001==null?"":res.data.p700001);
 							$("#p700002").val(res.data.p700002==null?"":res.data.p700002);
 							$("#p700003").val(res.data.p700003==null?"":res.data.p700003);
 							$("#p700004").val(res.data.p700004==null?"":res.data.p700004);
 							$("#p700005").val(res.data.p700005==null?"":res.data.p700005);
 							$("#p700006").val(res.data.p700006==null?"":res.data.p700006);
 							$("#p700007").val(res.data.p700007==null?"":res.data.p700007);
 							$("#p700008").val(res.data.p700008==null?"":res.data.p700008);
 							$("#p700009").val(res.data.p700009==null?"":res.data.p700009);
 							$("#p700010").val(res.data.p700010==null?"":res.data.p700010);
 							$("#p700011").val(res.data.p700011==null?"":res.data.p700011);
 							$("#p700012").val(res.data.p700012==null?"":res.data.p700012);
 							$("#p700013").val(res.data.p700013==null?"":res.data.p700013);
 							$("#p700014").val(res.data.p700014==null?"":res.data.p700014);
 							
 							$("#p800001").val(res.data.p800001==null?"":res.data.p800001);
 							$("#p800002").val(res.data.p800002==null?"":res.data.p800002);
 							$("#p800003").val(res.data.p800003==null?"":res.data.p800003);
 							$("#p800004").val(res.data.p800004==null?"":res.data.p800004);
 							$("#p800005").val(res.data.p800005==null?"":res.data.p800005);
 							$("#p800006").val(res.data.p800006==null?"":res.data.p800006);
 							$("#p800007").val(res.data.p800007==null?"":res.data.p800007);
 							$("#p800008").val(res.data.p800008==null?"":res.data.p800008);
 							$("#p800009").val(res.data.p800009==null?"":res.data.p800009);
 							$("#p8000010").val(res.data.p8000010==null?"":res.data.p8000010);
 							$("#p8000011").val(res.data.p8000011==null?"":res.data.p8000011);
 							$("#p8000012").val(res.data.p8000012==null?"":res.data.p8000012);
 							$("#p8000013").val(res.data.p8000013==null?"":res.data.p8000013);
 							$("#p8000014").val(res.data.p8000014==null?"":res.data.p8000014);
 							$("#p8000015").val(res.data.p8000015==null?"":res.data.p8000015);
 							$("#p8000016").val(res.data.p8000016==null?"":res.data.p8000016);
 							$("#p8000017").val(res.data.p8000017==null?"":res.data.p8000017);
 							$("#p8000018").val(res.data.p8000018==null?"":res.data.p8000018);
 							$("#p8000019").val(res.data.p8000019==null?"":res.data.p8000019);
 							$("#p8000020").val(res.data.p8000020==null?"":res.data.p8000020);
 							$("#p8000021").val(res.data.p8000021==null?"":res.data.p8000021);
 							$("#p8000022").val(res.data.p8000022==null?"":res.data.p8000022);
 							$("#p8000023").val(res.data.p8000023==null?"":res.data.p8000023);
 							$("#p8000024").val(res.data.p8000024==null?"":res.data.p8000024);
 							$("#p8000025").val(res.data.p8000025==null?"":res.data.p8000025);
 							$("#p8000026").val(res.data.p8000026==null?"":res.data.p8000026);
 							
 							
 							if("${param.pageStatus}"=="detail"){
 								$("#p700001_text").html(res.data.p700001==null?"":res.data.p700001);
 	 							$("#p700002_text").html(res.data.p700002==null?"":res.data.p700002);
 	 							$("#p700003_text").html(res.data.p700003==null?"":res.data.p700003);
 	 							$("#p700004_text").html(res.data.p700004==null?"":res.data.p700004);
 	 							$("#p700005_text").html(res.data.p700005==null?"":res.data.p700005);
 	 							$("#p700006_text").html(res.data.p700006==null?"":res.data.p700006);
 	 							$("#p700007_text").html(res.data.p700007==null?"":res.data.p700007);
 	 							$("#p700008_text").html(res.data.p700008==null?"":res.data.p700008);
 	 							$("#p700009_text").html(res.data.p700009==null?"":res.data.p700009);
 	 							$("#p700010_text").html(res.data.p700010==null?"":res.data.p700010);
 	 							$("#p700011_text").html(res.data.p700011==null?"":res.data.p700011);
 	 							$("#p700012_text").html(res.data.p700012==null?"":res.data.p700012);
 	 							$("#p700013_text").html(res.data.p700013==null?"":res.data.p700013);
 	 							$("#p700014_text").html(res.data.p700014==null?"":res.data.p700014);
 	 							
 	 							$("#p800001_text").html(res.data.p800001==null?"":res.data.p800001);
 	 							$("#p800002_text").html(res.data.p800002==null?"":res.data.p800002);
 	 							$("#p800003_text").html(res.data.p800003==null?"":res.data.p800003);
 	 							$("#p800004_text").html(res.data.p800004==null?"":res.data.p800004);
 	 							$("#p800005_text").html(res.data.p800005==null?"":res.data.p800005);
 	 							$("#p800006_text").html(res.data.p800006==null?"":res.data.p800006);
 	 							$("#p800007_text").html(res.data.p800007==null?"":res.data.p800007);
 	 							$("#p800008_text").html(res.data.p800008==null?"":res.data.p800008);
 	 							$("#p800009_text").html(res.data.p800009==null?"":res.data.p800009);
 	 							$("#p8000010_text").html(res.data.p8000010==null?"":res.data.p8000010);
 	 							$("#p8000011_text").html(res.data.p8000011==null?"":res.data.p8000011);
 	 							$("#p8000012_text").html(res.data.p8000012==null?"":res.data.p8000012);
 	 							$("#p8000013_text").html(res.data.p8000013==null?"":res.data.p8000013);
 	 							$("#p8000014_text").html(res.data.p8000014==null?"":res.data.p8000014);
 	 							$("#p8000015_text").html(res.data.p8000015==null?"":res.data.p8000015);
 	 							$("#p8000016_text").html(res.data.p8000016==null?"":res.data.p8000016);
 	 							$("#p8000017_text").html(res.data.p8000017==null?"":res.data.p8000017);
 	 							$("#p8000018_text").html(res.data.p8000018==null?"":res.data.p8000018);
 	 							$("#p8000019_text").html(res.data.p8000019==null?"":res.data.p8000019);
 	 							$("#p8000020_text").html(res.data.p8000020==null?"":res.data.p8000020);
 	 							$("#p8000021_text").html(res.data.p8000021==null?"":res.data.p8000021);
 	 							$("#p8000022_text").html(res.data.p8000022==null?"":res.data.p8000022);
 	 							$("#p8000023_text").html(res.data.p8000023==null?"":res.data.p8000023);
 	 							$("#p8000024_text").html(res.data.p8000024==null?"":res.data.p8000024);
 	 							$("#p8000025_text").html(res.data.p8000025==null?"":res.data.p8000025);
 	 							$("#p8000026_text").html(res.data.p8000026==null?"":res.data.p8000026);
 							}
 							
 							
 						
 						
 					} else {
 						$.ligerDialog.error('提示：' + data);
 					}
 				},
 				error : function(data, stats) {
 					$("body").unmask();
 					$.ligerDialog.error('提示：' + JSON.stringify(data));
 				}
 			});
		}
		 $(".navtab").ligerTab({
             //  onAfterSelectTabItem：这个方法是选中tab后进行的操作
             onAfterSelectTabItem : function (tabid)     {
            	 if("${param.pageStatus}"!="detail"){
            	 var P100001=CKEDITOR.instances.P100001.getData();
     			var P200001=CKEDITOR.instances.P200001.getData();
     			var P300001=CKEDITOR.instances.P300001.getData();
     			var P400001=CKEDITOR.instances.P400001.getData();
     			var P500001=CKEDITOR.instances.P500001.getData();
     			var P600001=CKEDITOR.instances.P600001.getData();
     			var data=$("#baseForm").getValues();
     			var entity=$.ligerui.toJSON(data);
     			if($("#id").val()==null||$("#id").val()==""){
     				
     				$.ligerDialog.confirm("请填写信息进行保存！",
								function(yes) {
							    if(yes){
							    	save();	
							    	
							}
						        
						});
     			}else{
     			$.ajax({
     				url : "com/tjy2/scientificProvince/judgeBase.do",
     				type : "POST",
     				data : {
     					"base" : entity,
     					"P100001" : P100001,
     					"P100002" : P200001,
     					"P100003" : P300001,
     					"P100004" : P400001,
     					"P100005" : P500001,
     					"P100006" : P600001
     				},
     				success : function(data, stats) {
     					$("body").unmask();
     					if (data["success"]) {
     						if (api.data.window.Qm) {
     							api.data.window.Qm.refreshGrid();
     						}
     						if(data.judge){
     						 $.ligerDialog.confirm("你有更新的内容为保存,确定保存？",
     								function(yes) {
     							    if(yes){
     							    	save();	
     							}
     						        
     						}); 
     						}
     						
     						
     					} else {
     						$.ligerDialog.error('提示：' + data);
     					}
     				},
     				error : function(data, stats) {
     					$("body").unmask();
     					$.ligerDialog.error('提示：' + JSON.stringify(data));
     				}
     			});
     			}
}}});
		 if("${param.pageStatus}"!="detail"){
		 var tbar=[{
				text : '保存',
				icon : 'save',
				click : save
			}, {
				text : '取消',
				icon : 'cancel',
				click : function() {
					api.close();
				}
			},{
				text : '导出',
				icon : 'print',
				click : input
			}];
		 }else{
			 var tbar=[{text : '导出',
					icon : 'print',
					click : input}]
		 }
		$("#baseForm").initForm({
			showToolbar: true,
			toolbar :tbar,
			toolbarPosition : "bottom",
			success : function(response) {//处理成功
				if (response.success) {
					top.$.notice("保存成功！");
					//保存基本信息（主表）后，id未自动赋值，故此处手动赋值
					
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败！<br/>' + response.msg);
				}
			},
			//取得图片
			getSuccess : function(res) {
			}
		});
	
	})

	function save(){
		//alert($("#type").ligerComboBox().getValue());
		//alert($("#P100001").val());
		//$("#baseForm").submit();
		if ($("#baseForm").validate().form()) {
			var P100001=CKEDITOR.instances.P100001.getData();
			var P200001=CKEDITOR.instances.P200001.getData();
			var P300001=CKEDITOR.instances.P300001.getData();
			var P400001=CKEDITOR.instances.P400001.getData();
			var P500001=CKEDITOR.instances.P500001.getData();
			var P600001=CKEDITOR.instances.P600001.getData();
			var data=$("#baseForm").getValues();
			var entity=$.ligerui.toJSON(data);
			$.ajax({
				url : "com/tjy2/scientificProvince/saveScientific.do",
				type : "POST",
				data : {
					"base" : entity,
					"P100001" : P100001,
					"P100002" : P200001,
					"P100003" : P300001,
					"P100004" : P400001,
					"P100005" : P500001,
					"P100006" : P600001
				},
				success : function(data, stats) {
					$("body").unmask();
					if (data["success"]) {
						if (api.data.window.Qm) {
							api.data.window.Qm.refreshGrid();
						}
						//$("#baseForm").setValues(data.data);
						$("#id").val(data.id);
						$("#status").val(data.data.status);
						$("#createDate").val(data.data.createDate);
						$("#createMan").val(data.data.createMan);
						$("#remark").val(data.data.remark);
						top.$.dialog.notice({
							content : '保存成功'
						});
						
						
					} else {
						$.ligerDialog.error('提示：' + data);
					}
				},
				error : function(data, stats) {
					$("body").unmask();
					$.ligerDialog.error('提示：' + JSON.stringify(data));
				}
			});

		}
	}
    function input(){
    	window.location.href ="/com/tjy2/scientificProvince/input.do?id="+id+"&types=${param.type}";
    	/* $.ajax({
			url : 
			type : "POST",
			success : function(data, stats) {
			},
			error : function(data, stats) {
				$("body").unmask();
				$.ligerDialog.error('提示：' + JSON.stringify(data));
			}
		}); */

    	
    }
    
	function chooseOrg(){
		 var parent_id="100000";
		// alert(dw);
		// alert(parent_id);
	            top.$.dialog({
	                width: 800,
	                height: 450,
	                lock: true,
	                parent: api,
	                title: "选择部门",
	                content: 'url:app/common/org_choose.jsp?par_id='+parent_id,
	                cancel: true,
	                ok: function(){
	                    var p = this.iframe.contentWindow.getSelectedPerson();
	                    if(!p){
	                        top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                        return false;
	                    }
	                    $("#projectDepartmentId").val(p.id);
	                    $("#projectDepartment").val(p.name);
	                }
	            });
	        }
	  function selectUser() {
	        selectUnitOrUser(1, 1, "projectHeadId", "projectHead");
	    }
	  var num1=24;
	  function addNew1(){
    		  num1=num1+7;
		   var s="<tr><td height='50px' width='100px'><a href=\"javascript:void(0)\" onclick=\"deleteParam1("+sum1+")\">删除</a></td>"+
		  "<td height='50px' width='100px' ><input value='' type='text' name='P80000"+num1+"' style='height:50px;border:0px;width:100px'></td>"+
		  "<td height='50px' width='100px'><input value='' type='text' name='P80000"+(num1+1)+"' style='height:50px;border:0px;width:100px'></td>"+
		 " <td height='50px' width='100px'><input value='' type='text' name='P80000"+(num1+2)+"' style='height:50px;border:0px;width:100px'></td>"+
		 " <td height='50px' width='100px'><input value='' type='text' name='P80000"+(num1+3)+"' style='height:50px;border:0px;width:100px'></td>"+
		 " <td height='50px' width='100px'><input value='' type='text' name='P80000"+(num1+4)+"' style='height:50px;border:0px;width:100px'></td>"+
		 " <td height='50px' width='100px'><input value='' type='text' name='P80000"+(num1+5)+"' style='height:50px;border:0px;width:100px'></td>"+
		 " <td ><textarea style='border:0px;height:50px;width:200px' name='P80000"+(num1+6)+"'  rows='1' cols='20' class='l-textarea' ltype='textarea'></textarea></td></tr>";
		  $("#table2").append(s); 
		  alert(num1)
		  }
      function addNew1Modify(value,value1,value2,value3,value4,value5,value6){
    		  num1=num1+7;
    	 
		  var s="<tr><td><a href=\"javascript:void(0)\" onclick=\"deleteParam1("+sum1+")\">删除</a></td>"+
		  "<td height='50px'><input value='"+(value==null?"":value)+"' type='text' name='P80000"+num1+"' style='height:50px;border:0px'></td>"+
		  "<td height='50px'><input value='"+(value1==null?"":value1)+"' type='text' name='P80000"+(num1+1)+"' style='height:50px;border:0px'></td>"+
		 " <td height='50px'><input value='"+(value2==null?"":value2)+"' type='text' name='P80000"+(num1+2)+"' style='height:50px;border:0px'></td>"+
		 " <td height='50px'><input value='"+(value3==null?"":value3)+"' type='text' name='P80000"+(num1+3)+"' style='height:50px;border:0px'></td>"+
		 " <td height='50px'><input value='"+(value4==null?"":value4)+"' type='text' name='P80000"+(num1+4)+"' style='height:50px;border:0px'></td>"+
		 " <td height='50px'><input value='"+(value5==null?"":value5)+"' type='text' name='P80000"+(num1+5)+"' style='height:50px;border:0px'></td>"+
		 " <td><textarea style='border:0px;height:50px' name='P80000"+(num1+6)+"'  rows='1' cols='20' class='l-textarea' ltype='textarea'>"+(value6==null?"":value6)+"</textarea></td></tr>";
		  $("#table2").append(s);
	  }
	  function deleteParam(sum){
		  var table=document.getElementById("table");  
		  var table1=document.getElementById("td1"+sum); 
		  var table2=document.getElementById("td2"+sum); 
		  var table3=document.getElementById("td3"+sum); 
		  $("#td1"+sum).remove();
		  $("#td2"+sum).remove();
		  $("#td3"+sum).remove();
		  
		    var len=table.rows.length;  
		    if(len<=sum){
		    	 table.deleteRow(len-1);//这里删除的是倒数第一行
		    }else{
		    	 table.deleteRow(sum);//这里删除的是指定行
		    }
		   
	  }
	  function deleteParam1(sum1){
		  var table=document.getElementById("table2");  
		    var len=table.rows.length;
		    if(len-5<=sum1){
		    	 table.deleteRow(len-1);//这里删除的是倒数第一行
		    }else{
		    	 table.deleteRow(5+sum1);//这里删除的是指定行
		    }
		   
	  }
	  function countMoeny(){
		  var p900001=parseInt($("#P900001").val());
		 var p900002= $("#P900002").val();
		 var p900003 =$("#P900003").val();
		 if(p900002!=""&&p900003!=""){
			 p900002 =parseInt(p900002);
			 p900003 =parseInt(p900003);
			 if(p900002+p900003!=p900001){
				 alert("对不起！所填金额必须等于总金额，请重新确认金额是否正确！");
				 $("#P900002")[0].focus();
			 }
		 }
		 
		
		 
	  }
	  function countMoeny1(){
		  var p900001=parseInt($("#P900001").val());
		 var p1000001= $("#P1000001").val();
		 var p1000002= $("#P1000002").val();
		 var p1000003= $("#P1000003").val();
		 var p1000004= $("#P1000004").val();
		 var p1000005= $("#P1000005").val();
		 var p1000006= $("#P1000006").val();
		 var p1000007= $("#P1000007").val();
		 var p1000008= $("#P1000008").val();
		 var p1000009= $("#P1000009").val();
		 var p10000010= $("#P10000010").val();
		 
		 if(p1000001!=""&&p1000002!=""&&p1000003!=""&&p1000004!=""&&p1000005!=""&&p1000006!=""&&p1000007!=""&&p1000008!=""&&p1000009!=""&&p10000010!=""){
			 p1000001 =parseInt(p1000001);
			 p1000002 =parseInt(p1000002);
			 p1000003 =parseInt(p1000003);
			 p1000004 =parseInt(p1000004);
			 p1000005 =parseInt(p1000005);
			 p1000006 =parseInt(p1000006);
			 p1000007 =parseInt(p1000007);
			 p1000008 =parseInt(p1000008);
			 p1000009 =parseInt(p1000009);
			 p10000010 =parseInt(p10000010);
			 if(p1000001+p1000002+p1000003+p1000004+p1000005+p1000006+p1000007+p1000008+p1000009+p10000010!=p900001){
				 alert("对不起！所填金额必须等于总金额，请重新确认金额是否正确！");
				 $("#P1000001")[0].focus();
			 }
		 }
		 
		
		 
	  }
</script>
</head>
<body>

<form name="baseForm" id="baseForm" method="post" action="" getAction="" >
<div class="navtab" id="tab">

  <div title="首页" id="form">
		
	
		<input type="hidden" name="id" id="id"/>
		<input type="hidden" name="status" id="status"/>
		<input type="hidden" name="createDate" id="createDate"/> 
		<input type="hidden" name="createId" id="createId"/> 
		<input type="hidden" name="createMan" id="createMan"/>
		
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<tr>
			<td class="l-t-td-left">申报编号：</td>
				<td class="l-t-td-right">
					<input type="text" ltype='text'
					name="projectNo" id="projectNo"
					validate="{required:true}"/></td>
				<td class="l-t-td-left">计划编号：</td>
				<td class="l-t-td-right"><input type="text" ltype='text'
					name="jhNo" id="jhNo" validate="{required:true}"
					 /></td>

			</tr>
			<tr>
			<td class="l-t-td-left">密级：</td>
				<td class="l-t-td-right">
					<input type="text" ltype='text'
					name="classification" id="classification"
					validate="{required:true}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">项目名称：</td>
				<td class="l-t-td-right" colspan="3"><input type="text" ltype='text'
					name="projectName" id="projectName"
					validate="{required:true}"/></td>
				
			</tr>
			<tr>
			<td class="l-t-td-left">研究领域：</td>
				<td class="l-t-td-right">
					<input type="text" ltype='text'
					name="researchField" id="researchField"
					validate="{required:true}"/></td>
				<td class="l-t-td-left">申报单位：</td>
				<td class="l-t-td-right"><input type="text" ltype='text'
					name="declareUnit" id="declareUnit" validate="{required:true}"
					 /></td>

			</tr>
			<tr>
				<td class="l-t-td-left">项目负责人:</td>
				<td class="l-t-td-right"><input type="hidden" name="projectHeadId" id="projectHeadId"></input>
				<input type="text" ltype='text' validate="{required:true}"
					name="projectHead" id="projectHead" onClick="selectUser()" readonly="readonly" /></td> </td>
				<td class="l-t-td-left">联系电话：</td>
				<td class="l-t-td-right">
				<input type="text" ltype='text'
					name="phoneTel" id="phoneTel"   validate="{required:true}"/></td>
			</tr>
			<tr>
			    <td class="l-t-td-left">归口部门:</td>
				<td class="l-t-td-right">
				<input type="text" ltype='text' validate="{required:true}"
					name="unitGk" id="unitGk"/></td> </td>
				<td class="l-t-td-left">报送日期：</td>
				<td class="l-t-td-right">
				<input type="text" ltype='date'
					name="submitDate" id="submitDate"    validate="{required:true}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">开始日期：</td>
				<td class="l-t-td-right">
				<input type="text" ltype='date'
					name="startDate" id="startDate"    validate="{required:true}"/></td>
				<td class="l-t-td-left">结束日期：</td>
				<td class="l-t-td-right"><input type="text" ltype='date'
					 validate="{required:true}"
					name="endDate" id="endDate" /></td>
			</tr>
		</table>
	
	</div> 
	<div title="第二页" id="forma" >
	<table align="center" width="700px">
	<tr>
	   <td align="center"><h1>填  报  说  明 </h1></td>
	 </tr>
	 <tr>
	 <td>1.	申报人填写项目申报书，应实事求是，表述明确。外来语要同时用原文和中文表达，第一次出现的缩略词，须注明全称。 </td>
	 </tr>
	 <tr>
	 <td>2.	申报人不用填写“申报编号”、“计划编号”栏。 </td>
	 </tr>
	  <tr>
	 <td>3.	申报书中涉密内容用“×××××”代替。</td>
	 </tr>
	  <tr>
	 <td>4.	归口部门是指项目申报单位所隶属的省级有关部门或所在市、州科技局。 </td>
	 </tr>
	  <tr>
	 <td>5.	归口部门是指项目申报单位所隶属的省级有关部门或所在市、州科技局。  </td>
	 </tr>
	  <tr>
	 <td>6.	项目申报书文本材料一式1份，用A4纸打印，左侧装订，不得加用塑料等额外装订材料。经申报单位、归口部门审核签署意见并加盖公章后，归口部门报送科技厅发展计划处。 </td>
	 </tr>
	  <tr>
	 <td>7.	网址:四川省科技计划项目管理平台http://xmgl.scst.gov.cn/  </td>
	 </tr>
	 
	 
	</table>
	
	
	</div>
	<div title="第三页" id="form1" >
	<fieldset class="l-fieldset">
					<legend class="l-legend" >一、项目基本信息</legend>
	    <textarea name="P100001" id="P100001" rows="10" cols="100" ></textarea>
	    <div id="P100001_text"></div>
	    </fieldset>
		</div> 
		<div title="第四页" id="form2">
		<fieldset class="l-fieldset">
					<legend class="l-legend">二、立项依据（国内外研究现状，研究意义，立项新颖性、创新点和特色，应用前景，对科技及经济社会发展的促进作用，主要参考文献）</legend>
	    <textarea name="P200001" id="P200001" rows="10" cols="100" class="l-textarea" ltype="textarea"></textarea>
	  <div id="P200001_text"></div>
 </fieldset>
		</div> 
		<div title="第五页" id="form3">
		<fieldset class="l-fieldset">
					<legend class="l-legend">三、相关研究目标、内容、方法、成果及应用方案</legend>
	    <textarea name="P300001" id="P300001" rows="10" cols="100" class="l-textarea" ltype="textarea"></textarea>
	    <div id="P300001_text"></div>
	    </fieldset>
		</div> 
		<div title="第六页" id="form4">
		<fieldset class="l-fieldset">
					<legend class="l-legend">四、计划进度和阶段目标</legend>
	    <textarea name="P400001" id="P400001" rows="10" cols="100" class="l-textarea" ltype="textarea"></textarea>
	    <div id="P400001_text"></div>
	    </fieldset>
		</div>
		<div title="第七页" id="form5">
		<fieldset class="l-fieldset">
					<legend class="l-legend">五、已有研究工作基础及创新平台等条件</legend>
	    <textarea name="P500001" id="P500001" rows="10" cols="100" class="l-textarea" ltype="textarea"></textarea>
	   <div id="P500001_text"></div>
	    </fieldset>
		</div> 
		<div title="第八页" id="form6">
		<fieldset class="l-fieldset">
					<legend class="l-legend">六、近五年主持省部级（含）以上科研项目、获奖及发表论文情况</legend>
	    <textarea name="P600001" id="P600001" rows="10" cols="100" class="l-textarea" ltype="textarea"></textarea>
	    <div id="P600001_text"></div>
	    </fieldset>
		</div> 
		<div title="第九页" id="form10">
		<fieldset class="l-fieldset">
					<legend class="l-legend">七 、项目预算总表（万元）</legend>
					<table border="1" width="800px" id="table" >
					<tr>
					      <td width="100px" height="50px" align="center">申请省级财政科技经费资助</td>
					      <td width="100px" height="50px" align="center">自筹经费</td>
                          <td width="100px" height="50px" align="center">合计</td>
					  </tr>
					  <tr>
					      <td width="100px" height="50px" align="center" id="p700001_text"><input type="text" name="p700001" id="p700001" style="height:50px;"/></td>
					      <td width="100px" height="50px" align="center" id="p700002_text"><input type="text" name="p700002" id="p700002" style="height:50px;"/></td>
                          <td width="100px" height="50px" align="center" id="p700003_text"><input type="text" name="p700003" id="p700003" style="height:50px;"/></td>
					  </tr>
					</table>
					<table border="1" width="800px" id="table" >
					   <tr>
					      <td width="100px" height="50px" align="center">概算科目名称</td>
					      <td width="100px" height="50px" align="center">项目总经费</td>

					   </tr>
					    <tr>
					      <td width="100px" height="50px" align="center">1、设备费</td>
					      <td width="100px" height="50px" id="p700004_text"><input type="text" name="p700004" id="p700004" style="height:50px;"/></td>

					   </tr>
					    <tr>
					    
					      <td width="100px" height="50px" align="center">2、材料费</td>
					      <td width="100px" height="50px" id="p700005_text"><input type="text" name="p700005" id="p700005"   style="height:50px;"/></td>
					   </tr>
					   <tr>
					      <td width="100px" height="50px" align="center">3、测试化验加工费</td>
					      <td width="100px" height="50px" id="p700006_text"><input type="text" name="p700006" id="p700006" style="height:50px;"/></td>
					   </tr>
					   <tr>
					      <td width="100px" height="50px" align="center">4、燃料动力费</td>
					      <td width="100px" height="50px" id="p700007_text"><input type="text" name="p700007" id="p700007" style="height:50px;"/></td>
					   </tr>
					   <tr>
					      <td width="100px" height="50px" align="center">5、差旅费</td>
					      <td width="100px" height="50px" id="p700008_text"><input type="text" name="p700008" id="p700008" style="height:50px;"/></td>
					   </tr>
					   <tr>
					      <td width="100px" height="50px" align="center">6、会议费</td>
					      <td width="100px" height="50px" id="p700009_text"><input type="text" name="p700009" id="p700009" style="height:50px;"/></td>
					   </tr>
					    <tr>
					      <td width="100px" height="50px" align="center">7、国际合作与交流费</td>
					      <td width="100px" height="50px" id="p700010_text"><input type="text" name="p700010" id="p700010" style="height:50px;"/></td>
					   </tr>
					   <tr>
					      <td width="100px" height="50px" align="center">8、出版/文献/信息传播/知识产权事务费</td>
					      <td width="100px" height="50px" id="p700011_text"><input type="text" name="p700011" id="p700011" style="height:50px;"/></td>
					   </tr>
					   <tr>
					      <td width="100px" height="50px" align="center">9、劳务费</td>
					      <td width="100px" height="50px" id="p700012_text"><input type="text" name="p700012" id="p700012" style="height:50px;"/></td>
					   </tr>
					   <tr>
					      <td width="100px" height="50px" align="center">10、专家咨询费</td>
					      <td width="100px" height="50px" id="p700013_text"><input type="text" name="p700013" id="p700013" style="height:50px;"/></td>
					   </tr>
					   <tr>
					      <td width="100px" height="50px" align="center">11、其他费用</td>
					      <td width="100px" height="50px" id="p700014_text"><input type="text" name="p700014" id="p700014" onblur="countMoeny1()"   style="height:50px;"/></td>
					   </tr>
					</table>
	    </fieldset>
		</div> 
      <div title="第十页" id="form11">
		<fieldset class="l-fieldset">
					<legend class="l-legend">八、项目组基本信息（此页为单页，请勿双面打印）</legend>
					<table border="1" width="95%" id="table2">
					   <tr>
					      <td rowspan="7" width="40px">项目负责人</td>
					      <td width="100px" colspan="2" height="30px">姓名</td>
					      <td height="30px" id="p800001_text"><input type="text" name="p800001" id="p800001" style="height:30px;"/></td>
					      <td width="100px" height="30px">性别</td>
					      <td height="30px" id="p800002_text"><input type="text" name="p800002" id="p800002" style="height:30px;"/></td>
					      <td width="100px" height="30px">出生年月</td>
					      <td height="30px" id="p800003_text"><input type="text" name="p800003" id="p800003" style="height:30px;"/></td>
					   </tr>
					   <tr>
					   <td width="100px" colspan="2" height="30px">E-mail</td>
					      <td height="30px" colspan="2" id="p800004_text"><input type="text" name="p800004" id="p800004" style="height:30px;"/></td>
					      <td width="100px" colspan="2" height="30px">电话</td>
					      <td height="30px" id="p800005_text"><input type="text" name="p800005" id="p800005" style="height:30px;"/></td>
					   </tr>
					    <tr>
					   <td width="100px" colspan="2" height="30px">学 历</td>
					      <td height="30px" colspan="2" id="p800006_text"><input type="text" name="p800006" id="p800006" style="height:30px;"/></td>
					      <td width="100px" colspan="2" height="30px">手 机</td>
					      <td height="30px" id="p800007_text"><input type="text" name="p800007" id="p800007" style="height:30px;"/></td>
					   </tr>
					   <tr>
					    <td width="100px" colspan="2" height="30px">学位及获得时间</td>
					      <td height="30px" colspan="7" id="p800008_text"><input type="text" name="p800008" id="p800008" style="height:30px;"/></td>
					   </tr>
					    <tr>
					    <td width="100px" colspan="2" height="30px">从事专业</td>
					      <td height="30px" colspan="7" id="p800009_text"><input type="text" name="p800009" id="p800009" style="height:30px;"/></td>
					   </tr>
					    <tr>
					    <td width="100px" colspan="2" height="30px">所在创新平台名称（重大前沿项目必填）</td>
					      <td height="30px" colspan="7" id="p8000010_text"><input type="text" name="p8000010" id="p8000010" style="height:30px;"/></td>
					   </tr>
					   <tr>
					   <td width="100px" colspan="2" height="30px">其他身份</td>
					      <td height="30px" colspan="2" id="p8000011_text"><input type="text" name="p8000011" id="p8000011" style="height:30px;"/></td>
					      <td width="100px" colspan="2" height="30px">专业技术职称</td>
					      <td height="30px" id="p8000012_text"><input type="text" name="p8000012" id="p8000012" style="height:30px;"/></td>
					   </tr>
					    <tr>
					      <td rowspan="3" width="40px">申报单位</td>
					      <td width="100px" colspan="2" height="30px">单位名称</td>
					      <td height="30px" colspan="7" id="p8000013_text"><input type="text" name="p8000013" id="p8000013" style="height:30px;"/></td>
					   </tr>
					    <tr>
					   <td width="100px" colspan="2" height="30px">单位地址</td>
					      <td height="30px" colspan="2" id="p8000014_text"><input type="text" name="p8000014" id="p8000014" style="height:30px;"/></td>
					      <td width="100px" colspan="2" height="30px">负责人</td>
					      <td height="30px" id="p8000015_text"><input type="text" name="p8000015" id="p8000015" style="height:30px;"/></td>
					   </tr>
					   <tr>
					    <td width="100px" colspan="2" height="30px">科研管理部门联系人</td>
					      <td height="30px" id="p8000016_text"><input type="text" name="p8000016" id="p8000016" style="height:30px;"/></td>
					      <td width="100px" height="30px">联系人手机</td>
					      <td height="30px" id="p8000017_text"><input type="text" name="p8000017" id="p8000017" style="height:30px;"/></td>
					      <td width="100px" height="30px">邮政编码</td>
					      <td height="30px" id="p8000018_text"><input type="text" name="p8000018" id="p8000018" style="height:30px;"/></td>
					   </tr>
					   <tr>
					    <td rowspan="5" width="40px">申报单位</td>
					    <td width="100px" colspan="3" height="30px">单位名称</td>
					    <td width="100px" colspan="4" height="30px">在本项目中分工</td>
					   </tr>
					   <tr>
					    <td width="100px" colspan="3" height="30px" id="p8000019_text"><input type="text" name="p8000019" id="p8000019" style="height:30px;"/></td>
					    <td width="100px" colspan="4" height="30px" id="p8000020_text"><input type="text" name="p8000020" id="p8000020" style="height:30px;"/></td>
					   </tr>
					    <tr>
					    <td width="100px" colspan="3" height="30px" id="p8000021_text"><input type="text" name="p8000021" id="p8000021" style="height:30px;"/></td>
					    <td width="100px" colspan="4" height="30px" id="p8000022_text"><input type="text" name="p8000022" id="p8000022" style="height:30px;"/></td>
					   </tr>
					    <tr>
					    <td width="100px" colspan="3" height="30px" id="p8000023_text"><input type="text" name="p8000023" id="p8000023" style="height:30px;"/></td>
					    <td width="100px" colspan="4" height="30px" id="p8000024_text"><input type="text" name="p8000024" id="p8000024" style="height:30px;"/></td>
					   </tr>
					    <tr>
					    <td width="100px" colspan="3" height="30px" id="p8000025_text"><input type="text" name="p8000025" id="p8000025" style="height:30px;"/></td>
					    <td width="100px" colspan="4" height="30px" id="p8000026_text"><input type="text" name="p8000026" id="p8000026" style="height:30px;"/></td>
					   </tr>
					<tr>
					<td width="100px" height="30px"><a  href="javascript:void(0)" onclick="addNew1();">添加列</a></td>
					<td width="100px" height="30px">姓名</td>
					<td width="100px" height="30px">性别</td>
					<td width="100px" height="30px">年龄</td>
					<td width="100px" height="30px">学历</td>
					<td width="100px" height="30px">专业技术职称</td>
					<td width="100px" height="30px">从事专业</td>
					<td width="300px" height="30px">所在单位</td>
					</tr>
					</table>
	     
	    </fieldset>
		</div> 
</div>
</form>
</body>
</html>