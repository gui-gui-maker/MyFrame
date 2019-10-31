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
		$(".onlyNumber").ligerSpinner({isNegative: true }); 
		CKEDITOR.replace('P100001');
		CKEDITOR.replace('P200001');
		CKEDITOR.replace('P300001');
		CKEDITOR.replace('P400001');
		CKEDITOR.replace('P500001');
		CKEDITOR.replace('P600001');
		CKEDITOR.replace('P700001');
		if("${param.pageStatus}"=="modify"||"${param.pageStatus}"=="detail"){
			$.ajax({
 				url : "tjy2ScientificResearchAction/detailBase.do?id=${param.id}",
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
 							$("#P700001_text").html(res.P700001);
 							$("#P700001").hide();
 						}
 						
 						//为富文本设置值
 						CKEDITOR.instances.P100001.setData(res.P100001);
 						//$("#P100001").html(res.P100001)
 						CKEDITOR.instances.P200001.setData(res.P200001);
 						CKEDITOR.instances.P300001.setData(res.P300001);
 						CKEDITOR.instances.P400001.setData(res.P400001);
 						CKEDITOR.instances.P500001.setData(res.P500001);
 						CKEDITOR.instances.P600001.setData(res.P600001);
 						CKEDITOR.instances.P700001.setData(res.P700001);
 						
 						//为表格添加列和值
 							if(res.data.p800001!=null){
 								addNewModify(res.data.p800001,res.data.p800006,res.data.p900004,res.data.p900005,res.data.p900006);
 							}
 							if(res.data.p800002!=null){
 								addNewModify(res.data.p800002,res.data.p800007,res.data.p900007,res.data.p900008,res.data.p900009);
 							}
 							if(res.data.p800003!=null){
 								addNewModify(res.data.p800003,res.data.p800008,res.data.p9000010,res.data.p9000011,res.data.p9000012);
 							}
 							if(res.data.p800004!=null){
 								addNewModify(res.data.p800004,res.data.p800009,res.data.p9000013,res.data.p9000014,res.data.p9000015);
 							}
 							if(res.data.p800005!=null){
 								addNewModify(res.data.p800005,res.data.p8000010,res.data.p9000016,res.data.p9000017,res.data.p9000018);
 							}
 							if(res.data.p1100006!=null){
 								addNew1Modify(res.data.p1100006,res.data.p11000016,res.data.p11000026,res.data.p11000036);
 							}
 							if(res.data.p1100007!=null){
 								addNew1Modify(res.data.p1100007,res.data.p11000017,res.data.p11000027,res.data.p11000037);
 							}
 							if(res.data.p1100008!=null){
 								addNew1Modify(res.data.p1100008,res.data.p11000018,res.data.p11000028,res.data.p11000038);
 							}
 							if(res.data.p1100009!=null){
 								addNew1Modify(res.data.p1100009,res.data.p11000019,res.data.p11000029,res.data.p11000039);
 							}
 							if(res.data.p11000010!=null){
 								addNew1Modify(res.data.p11000010,res.data.p11000020,res.data.p11000030,res.data.p11000040);
 							}
 							if(res.data.p11000011!=null){
 								addNew1Modify(res.data.p11000011,res.data.p11000021,res.data.p11000031,res.data.p11000041);
 							}
 							if(res.data.p11000012!=null){
 								addNew1Modify(res.data.p11000012 ,res.data.p11000022,res.data.p11000032,res.data.p11000042);
 							}
 							if(res.data.p11000013!=null){
 								addNew1Modify(res.data.p11000013,res.data.p11000023,res.data.p11000033,res.data.p11000043);
 							}
 							if(res.data.p11000014!=null){
 								addNew1Modify(res.data.p11000014,res.data.p11000024,res.data.p11000034,res.data.p11000044);
 							}
 							if(res.data.p11000015!=null){
 								addNew1Modify(res.data.p11000015,res.data.p11000025,res.data.p11000035,res.data.p11000045);
 							}
 							if(res.data.p11000046!=null){
 								addNew1Modify(res.data.p11000046,res.data.p11000051,res.data.p11000056,res.data.p11000061);
 							}
 							if(res.data.p11000047!=null){
 								addNew1Modify(res.data.p11000047,res.data.p11000052,res.data.p11000057,res.data.p11000062);
 							}
 							if(res.data.p11000048!=null){
 								addNew1Modify(res.data.p11000048,res.data.p11000053,res.data.p11000058,res.data.p11000063);
 							}
 							if(res.data.p11000049!=null){
 								addNew1Modify(res.data.p11000049,res.data.p11000054,res.data.p11000059,res.data.p11000064);
 							}
 							if(res.data.p11000050!=null){
 								addNew1Modify(res.data.p11000050,res.data.p11000055,res.data.p11000060,res.data.p11000065);
 							}
 							
 							$("#P900001").val(res.data.p900001==null?"":res.data.p900001);
 							$("#P900002").val(res.data.p900002==null?"":res.data.p900002);
 							$("#P900003").val(res.data.p900003==null?"":res.data.p900003);
 							$("#P1000001").val(res.data.p1000001==null?"":res.data.p1000001);
 							$("#P1000002").val(res.data.p1000002==null?"":res.data.p1000002);
 							$("#P1000003").val(res.data.p1000003==null?"":res.data.p1000003);
 							$("#P1000004").val(res.data.p1000004==null?"":res.data.p1000004);
 							$("#P1000005").val(res.data.p1000005==null?"":res.data.p1000005);
 							$("#P1000006").val(res.data.p1000006==null?"":res.data.p1000006);
 							$("#P1000007").val(res.data.p1000007==null?"":res.data.p1000007);
 							$("#P1000008").val(res.data.p1000008==null?"":res.data.p1000008);
 							$("#P1000009").val(res.data.p1000009==null?"":res.data.p1000009);
 							$("#P10000010").val(res.data.p10000010==null?"":res.data.p10000010);
 							$("#P10000011").val(res.data.p10000011==null?"":res.data.p10000011);
 							$("#P10000012").val(res.data.p10000012==null?"":res.data.p10000012);
 							$("#P10000013").val(res.data.p10000013==null?"":res.data.p10000013);
 							$("#P10000014").val(res.data.p10000014==null?"":res.data.p10000014);
 							$("#P10000015").val(res.data.p10000015==null?"":res.data.p10000015);
 							$("#P10000016").val(res.data.p10000016==null?"":res.data.p10000016);
 							$("#P10000017").val(res.data.p10000017==null?"":res.data.p10000017);
 							$("#P10000018").val(res.data.p10000018==null?"":res.data.p10000018);
 							$("#P10000019").val(res.data.p10000019==null?"":res.data.p10000019);
 							$("#P10000020").val(res.data.p10000020==null?"":res.data.p10000020);
 							$("#P1100001").val(res.data.p1100001==null?"":res.data.p1100001);
 							$("#P1100002").val(res.data.p1100002==null?"":res.data.p1100002);
 							$("#P1100003").val(res.data.p1100003==null?"":res.data.p1100003);
 							$("#P1100004").val(res.data.p1100004==null?"":res.data.p1100004);
 							$("#P1100005").val(res.data.p1100005==null?"":res.data.p1100005);
 							if("${param.pageStatus}"=="detail"){
 							$("#P900001_text").html(res.data.p900001==null?"":res.data.p900001);
 							$("#P900002_text").html(res.data.p900002==null?"":res.data.p900002);
 							$("#P900003_text").html(res.data.p900003==null?"":res.data.p900003);
 							$("#P1000001_text").html(res.data.p1000001==null?"":res.data.p1000001);
 							$("#P1000002_text").html(res.data.p1000002==null?"":res.data.p1000002);
 							$("#P1000003_text").html(res.data.p1000003==null?"":res.data.p1000003);
 							$("#P1000004_text").html(res.data.p1000004==null?"":res.data.p1000004);
 							$("#P1000005_text").html(res.data.p1000005==null?"":res.data.p1000005);
 							$("#P1000006_text").html(res.data.p1000006==null?"":res.data.p1000006);
 							$("#P1000007_text").html(res.data.p1000007==null?"":res.data.p1000007);
 							$("#P1000008_text").html(res.data.p1000008==null?"":res.data.p1000008);
 							$("#P1000009_text").html(res.data.p1000009==null?"":res.data.p1000009);
 							$("#P10000010_text").html(res.data.p10000010==null?"":res.data.p10000010);
 							$("#P10000011_text").html(res.data.p10000011==null?"":res.data.p10000011);
 							$("#P10000012_text").html(res.data.p10000012==null?"":res.data.p10000012);
 							$("#P10000013_text").html(res.data.p10000013==null?"":res.data.p10000013);
 							$("#P10000014_text").html(res.data.p10000014==null?"":res.data.p10000014);
 							$("#P10000015_text").html(res.data.p10000015==null?"":res.data.p10000015);
 							$("#P10000016_text").html(res.data.p10000016==null?"":res.data.p10000016);
 							$("#P10000017_text").html(res.data.p10000017==null?"":res.data.p10000017);
 							$("#P10000018_text").html(res.data.p10000018==null?"":res.data.p10000018);
 							$("#P10000019_text").html(res.data.p10000019==null?"":res.data.p10000019);
 							$("#P10000020_text").html(res.data.p10000020==null?"":res.data.p10000020);
 							$("#P1100001_text").html(res.data.p1100001==null?"":res.data.p1100001);
 							$("#P1100002_text").html(res.data.p1100002==null?"":res.data.p1100002);
 							$("#P1100003_text").html(res.data.p1100003==null?"":res.data.p1100003);
 							$("#P1100004_text").html(res.data.p1100004==null?"":res.data.p1100004);
 							$("#P1100005_text").html(res.data.p1100005==null?"":res.data.p1100005);
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
     			var P700001=CKEDITOR.instances.P700001.getData();
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
     				url : "tjy2ScientificResearchAction/judgeBase.do",
     				type : "POST",
     				data : {
     					"base" : entity,
     					"P100001" : P100001,
     					"P100002" : P200001,
     					"P100003" : P300001,
     					"P100004" : P400001,
     					"P100005" : P500001,
     					"P100006" : P600001,
     					"P100007" : P700001
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
			var P700001=CKEDITOR.instances.P700001.getData();
			var data=$("#baseForm").getValues();
			var entity=$.ligerui.toJSON(data);
			$.ajax({
				url : "tjy2ScientificResearchAction/saveScientific.do",
				type : "POST",
				data : {
					"base" : entity,
					"P100001" : P100001,
					"P100002" : P200001,
					"P100003" : P300001,
					"P100004" : P400001,
					"P100005" : P500001,
					"P100006" : P600001,
					"P100007" : P700001
				},
				success : function(data, stats) {
					$("body").unmask();
					if (data["success"]) {
						if (api.data.window.Qm) {
							api.data.window.Qm.refreshGrid();
						}
						//$("#baseForm").setValues(data.data);
						id=data.id;
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
    	if(id=="" || id=="null" || id=="undefined"){
    		if("${param.pageStatus}"=="add"){
    			$.ligerDialog.error('提示：请保存数据后再执行导出！');
    		}else{
    			$.ligerDialog.error('提示：未获取到要导出的数据！请重试或联系管理员！');
    		}
    	}else{
    		window.location.href ="/tjy2ScientificResearchAction/myInput.do?id="+id+"&types=${param.type}";
    	}
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
	 
	  function addNew(){
		  if(sum==5){
			  alert("对不起！最多增加5行！");
			  sum=0;
			  return;
		  }
		  sum++;
		  num=sum+5;
		  num5=num5+3;
		  num6=num5+1;
		  num7=num6+1;
		  $("#table").append("<tr><td height='50px'><input type='text' name='P80000"+sum+"' style='height:158px;border:0px'></td><td><textarea style='border:0px' name='P80000"+num+"'  rows='10' cols='100' class='l-textarea' ltype='textarea'>  </textarea></td><td><a href=\"javascript:void(0)\" onclick=\"deleteParam("+sum+")\">删除</a></tr>");
		  $("#table1_tr").append(" <td id='td1"+sum+"' width='100px' height='50px' ><input value=''  name='P90000"+num5+"' style='border:0px;height:50px;' type='text' ></td>");
		  $("#table1_tr1").append(" <td id='td2"+sum+"'  width='100px' height='50px' ><input value='' type='text' name='P90000"+num6+"' style='border:0px;height:50px;' ></td>");
		  $("#table1_tr2").append(" <td id='td3"+sum+"' width='100px' height='50px' ><input value='' type='text' name='P90000"+num7+"' style='border:0px;height:50px;' ></td>");
		  
	  }
	  function addNewModify(value,value1,value2,value3,value4){
		  if(sum==5){
			  alert("对不起！最多增加5行！");
			  sum=0;
			  return;
		  }
		  sum++;
		  num=sum+5;
		  num5=num5+3;
		  num6=num5+1;
		  num7=num6+1;
		  if("${param.pageStatus}"!="detail"){
			  $("#table").append("<tr><td height='50px'><input type='text' name='P80000"+sum+"' value='"+(value==null?"":value)+"' style='height:158px;border:0px'></td><td><textarea style='border:0px'  name='P80000"+num+"'  rows='10' cols='100' class='l-textarea' ltype='textarea'>"+(value1==null?"":value1)+"</textarea></td><td><a href=\"javascript:void(0)\" onclick=\"deleteParam("+sum+")\">删除</a></tr>");
		  }else{
			  $("#table").append("<tr><td height='50px'><input type='text' name='P80000"+sum+"' value='"+(value==null?"":value)+"' style='height:158px;border:0px'></td><td><textarea style='border:0px'  name='P80000"+num+"'  rows='10' cols='100' class='l-textarea' ltype='textarea'>"+(value1==null?"":value1)+"</textarea></td></tr>");
		  }
		  
		  $("#table1_tr").append(" <td width='100px' height='50px' ><input  name='P90000"+num5+"' value='"+(value2==null?"":value2)+"'  type='text' style='border:0px;height:50px;' ></td>");
		  $("#table1_tr1").append(" <td width='100px' height='50px' ><input type='text' name='P90000"+num6+"' value='"+(value3==null?"":value3)+"' style='border:0px;height:50px;'></td>");
		  $("#table1_tr2").append(" <td width='100px' height='50px' ><input type='text' name='P90000"+num7+"' value='"+(value4==null?"":value4)+"' style='border:0px;height:50px;'></td>");
		  
	  }
	  function addNew1(){
		  var name="课题负责人";
		  if(sum1>0){
			  name="课题组成员";
		  }
		  sum1++;
		  if(sum1<11){
		  num1=sum1+5;
		  num2=num1+10;
		  num3=num1+20;
		  num4=num1+30;
		  }else{
			  num1=sum1+35;
			  num2=sum1+40;
			  num3=sum1+45;
			  num4=sum1+50;
		  }
		  $("#table2").append("<tr><td><a href=\"javascript:void(0)\" onclick=\"deleteParam1("+sum1+")\">删除</a></td><td >"+name+"</td><td height='50px' width='70px'><input value='' type='text' name='P110000"+num1+"' style='height:50px;border:0px'></td><td height='50px'><input value='' type='text' name='P110000"+num2+"' style='height:50px;border:0px'></td><td height='50px'><input value='' type='text' name='P110000"+num3+"' style='height:50px;border:0px'></td><td><textarea style='border:0px;height:50px' name='P110000"+num4+"'  rows='1' cols='20' class='l-textarea' ltype='textarea'></textarea></td></tr>");
		  }
      function addNew1Modify(value,value1,value2,value3){
    	  var name="课题负责人";
		  if(sum1>0){
			  name="课题组成员";
		  }
		  sum1++;
		  if(sum1<11){
			  num1=sum1+5;
			  num2=num1+10;
			  num3=num1+20;
			  num4=num1+30;
			  }else{
				  num1=sum1+35;
				  num2=sum1+40;
				  num3=sum1+45;
				  num4=sum1+50;
			  }
		  $("#table2").append("<tr><td><a href=\"javascript:void(0)\" onclick=\"deleteParam1("+sum1+")\">删除</a></td><td >"+name+"</td><td height='50px' width='70px'><input type='text' value='"+(value==null?"":value)+"' name='P110000"+num1+"' style='height:50px;border:0px'></td><td height='50px'><input type='text' value='"+(value1==null?"":value1)+"' name='P110000"+num2+"' style='height:50px;border:0px'></td><td height='50px'><input type='text' value='"+(value2==null?"":value2)+"' name='P110000"+num3+"' style='height:50px;border:0px'></td><td><textarea style='border:0px;height:50px' name='P110000"+num4+"'  rows='1' cols='20' class='l-textarea' ltype='textarea'>"+(value3==null?"":value3)+"</textarea></td></tr>");
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

<form name="baseForm" id="baseForm" method="post" action="tjy2ScientificResearchAction/saveScientific.do" getAction="" >
<div class="navtab" id="tab">

  <div title="首页" id="form">
		
	
		<input type="hidden" name="id" id="id"/>
		<input type="hidden" name="status" id="status"/>
		<input type="hidden" name="createDate" id="createDate"/> 
		<input type="hidden" name="createMan" id="createMan"/>
		<input type="hidden" name="remark" id="remark"/>
		<input type="hidden" name="projectDepartment" id="projectDepartment"/>
		
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">项目名称：</td>
				<td class="l-t-td-right" colspan="3"><input type="text" ltype='text'
					name="projectName" id="projectName"
					validate="{required:true}"/></td>
				
			</tr>
			<tr>
			<td class="l-t-td-left">项目类别：</td>
				<td class="l-t-td-right">
					<u:combo name="projectType" code="project_type" attribute="required:true"></u:combo></td>
				<td class="l-t-td-left">专业类别：</td>
				<td class="l-t-td-right"><input type="text" ltype='text'
					name="professionalType" id="professionalType" validate="{required:true}"
					 /></td>

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
			<tr>
				<td class="l-t-td-left">项目负责人:</td>
				<td class="l-t-td-right"><input type="hidden" name="projectHeadId" id="projectHeadId"></input>
				<input type="text" ltype='text' validate="{required:true}"
					name="projectHead" id="projectHead" onClick="selectUser()" readonly="readonly" /></td> </td>
				<td class="l-t-td-left">填表日期：</td>
				<td class="l-t-td-right">
				<input type="text" ltype='date'
					name="fillDate" id="fillDate"   validate="{required:true}"/></td>
			</tr>
			<c:if test='${param.type=="rw" }'>
			<tr>
			<td class="l-t-td-left">项目编号：</td>
				<td class="l-t-td-right"><input type="text" ltype='text'
					name="projectNo" id="projectNo" validate="{required:true}"
					 /></td>
			</tr>
			</c:if>
			
		</table>
	
	</div> 
	<div title="第二页" id="forma" >
	<table align="center" width="700px">
	<tr>
	   <td align="center"><h1>填  报  说  明 </h1></td>
	 </tr>
	 <tr>
	 <td>一、各项内容要求实事求是，逐条认真填写，表达明确、严谨。 </td>
	 </tr>
	 <tr>
	 <td>二、项目申报书由项目负责人填写并经承担部门负责人签署意见，报院科技委。 </td>
	 </tr>
	 <tr>
	 <td>三、“申请项目经费预算表” 经费支出明细填写说明：<br/>
 &nbsp; &nbsp; &nbsp;&nbsp;1.设备费：是在指项目研究过程中购置或试制专用仪器设备，对现有仪器设备进行升级改造，以及租赁外单位仪器设备而发生的费用。要严格控制设备购置费用支出。<br/>
&nbsp; &nbsp; &nbsp;&nbsp;2.材料费：是指在项目研究过程中消耗的各种原材料、辅助材料等低值易耗品的采购及运输、装卸、整理等费用。<br/>
&nbsp; &nbsp; &nbsp;&nbsp;3.测试化验加工费：是指在项目研究过程中支付给外单位的检验、测试、化验及加工等费用。<br/>
&nbsp; &nbsp; &nbsp;&nbsp;4.差旅费：开展科学实验（试验）、科学考察、业务调研、学术交流等所发生的外埠差旅费。差旅费的开支标准应当按照国家有关规定执行。<br/>
&nbsp; &nbsp; &nbsp;&nbsp;5.会议费：是指在项目研究过程中为组织开展学术研讨、咨询以及协调项目或课题等活动而发生的会议费用。<br/>
&nbsp; &nbsp; &nbsp;&nbsp;6.出版/文献/信息传播/知识产权事务费：是指在项目研究过程中需要支付的论文版面费、著作出版费、资料费、专用软件购买费、文献检索费、专利申请及其他知识产权事务等费用。<br/>
&nbsp; &nbsp; &nbsp;&nbsp;7.劳务费：是指在项目研究过程中支付给项目组成员中没有工资性收入的相关人员的劳务性费用。劳务费的支出不得超过总经费的20%。<br/>
&nbsp; &nbsp; &nbsp;&nbsp;8.专家咨询费：是指在项目研究过程中支付给临时聘请的咨询专家的咨询费、鉴定费、评审费等费用。<br/>
&nbsp; &nbsp; &nbsp;&nbsp;9.协作费：是指由外单位承担部分协作等发生的费用。<br/>
&nbsp; &nbsp; &nbsp;&nbsp;10.其它费用：是指项目研制过程中发生的支出费用，其它费用总额不得超过总经费的4％。<br/>
 </td>
	 </tr>
	 
	</table>
	
	
	</div>
	<div title="第三页" id="form1" >
	<fieldset class="l-fieldset">
					<legend class="l-legend" >一、项目研究的目的、意义</legend>
	    <textarea name="P100001" id="P100001" rows="10" cols="100" ></textarea>
	    <div id="P100001_text"></div>
	    </fieldset>
		</div> 
		<div title="第四页" id="form2">
		<fieldset class="l-fieldset">
					<legend class="l-legend">二、国内外同类研究现状分析及存在的问题（含查新结果）</legend>
	    <textarea name="P200001" id="P200001" rows="10" cols="100" class="l-textarea" ltype="textarea"></textarea>
	  <div id="P200001_text"></div>
 </fieldset>
		</div> 
		<div title="第五页" id="form3">
		<fieldset class="l-fieldset">
					<legend class="l-legend">三、项目研究内容</legend>
	    <textarea name="P300001" id="P300001" rows="10" cols="100" class="l-textarea" ltype="textarea"></textarea>
	    <div id="P300001_text"></div>
	    </fieldset>
		</div> 
		<div title="第六页" id="form4">
		<fieldset class="l-fieldset">
					<legend class="l-legend">四、项目研究预期达到的最终目标（包含拟解决的关键问题、研究结果、技术指标、经济及社会效益）</legend>
	    <textarea name="P400001" id="P400001" rows="10" cols="100" class="l-textarea" ltype="textarea"></textarea>
	    <div id="P400001_text"></div>
	    </fieldset>
		</div>
		<div title="第七页" id="form5">
		<fieldset class="l-fieldset">
					<legend class="l-legend">五、项目研究采用的研究方法、技术路线、实验方案、创新之处及可行性分析</legend>
	    <textarea name="P500001" id="P500001" rows="10" cols="100" class="l-textarea" ltype="textarea"></textarea>
	   <div id="P500001_text"></div>
	    </fieldset>
		</div> 
		<div title="第八页" id="form6">
		<fieldset class="l-fieldset">
					<legend class="l-legend">六、项目研究基础（课题组人员情况、已有的工作基础、仪器设备、研究条件、组织保障措施）</legend>
	    <textarea name="P600001" id="P600001" rows="10" cols="100" class="l-textarea" ltype="textarea"></textarea>
	    <div id="P600001_text"></div>
	    </fieldset>
		</div> 
		<div title="第九页" id="form7">
		<fieldset class="l-fieldset">
					<legend class="l-legend">七、项目合作研究必要性说明（有合作单位时说明）</legend>
	    <textarea name="P700001" id="P700001" rows="10" cols="100" class="l-textarea" ltype="textarea"></textarea>
	   <div id="P700001_text"></div>
	    </fieldset>
		</div> 
		<div title="第十页" id="form8">
		<fieldset class="l-fieldset">
					<legend class="l-legend">八、项目年度计划内容及考核目标内容</legend>
					<table border="1" width="800px" id="table">
					   <tr>
					      <td width="100px" height="50px" align="center">年度</td>
					      <td width="600px" height="50px" align="center">项目年度计划内容及考核目标内容</td>
					      <td id="datail" height="50px"><a href="javascript:void(0)" onclick="addNew();">添加列</a></td>
					   </tr>
					
					</table>
	     
	    </fieldset>
		</div> 
		<div title="第十一页" id="form9">
		<fieldset class="l-fieldset">
					<legend class="l-legend">九、经费预算（万元）</legend>
					<table border="1" width="800px" id="table1" status="modify">
					   <tr id="table1_tr">
					      <td width="100px" height="50px" align="center">合计总金额</td>
					      <td width="100px" height="50px" align="center">其中</td>
					      <td width="100px" height="50px" align="center">小计</td>
					     
					   </tr>
					   <tr id="table1_tr1">
					   <td  width="100px" height="50px" align="center" rowspan="2" id="P900001_text"><input class="onlyNumber" type="text" name="P900001" id="P900001"  style="height:107px;border:0px"/></td>
					   <td height="50px" align="center">申请院拨经费</td>
					   	   <td width="100px" height="50px" id="P900002_text"><input class="onlyNumber" type="text" name="P900002" id="P900002"  style="height:50px;"></td>
					  
					   </tr>
					   <tr id="table1_tr2">
					   <td height="50px" align="center">其他经费来源</td>
					     <td width="100px" height="50px" id="P900003_text"><input class="onlyNumber" type="text" name="P900003" id="P900003" onblur="countMoeny(this)"   style="height:50px;"></td>
					 
					   </tr>
					</table>
	    </fieldset>
		</div> 
		<div title="第十二页" id="form10">
		<fieldset class="l-fieldset">
					<legend class="l-legend">九、经费预算（万元）</legend>
					<table border="1" width="800px" id="table" >
					   <tr>
					      <td width="50px" align="center">序号</td>
					      <td width="100px" height="50px" align="center">经费支出明细</td>
					      <td width="100px" height="50px" align="center">金额</td>
					      <td width="100px" height="50px" align="center">计算依据及理由</td>
					   </tr>
					    <tr>
					      <td width="50px" align="center">1</td>
					      <td width="100px" height="50px" align="center">设备费</td>
					      <td width="100px" height="50px" id="P1000001_text"><input class="onlyNumber" type="text" name="P1000001" id="P1000001" style="height:50px;"/></td>
					      <td width="300px" height="50px" id="P10000011_text"><textarea style='border:0px;height:50px;' name="P10000011" id="P10000011" rows='2.3' cols='30' class='l-textarea' ltype='textarea'></textarea></td>
					   </tr>
					    <tr>
					      <td width="50px" align="center">2</td>
					      <td width="100px" height="50px" align="center">材料费</td>
					      <td width="100px" height="50px" id="P1000002_text"><input class="onlyNumber" type="text" name="P1000002" id="P1000002"   style="height:50px;"/></td>
					      <td width="300px" height="50px" id="P10000012_text"><textarea style='border:0px;height:50px;' name="P10000012" id="P10000012"  rows='2.3' cols='30' class='l-textarea' ltype='textarea'></textarea></td>
					   </tr>
					   <tr>
					      <td width="50px" align="center">3</td>
					      <td width="100px" height="50px" align="center">测试化验加工费</td>
					      <td width="100px" height="50px" id="P1000003_text"><input class="onlyNumber" type="text" name="P1000003" id="P1000003" style="height:50px;"/></td>
					      <td width="300px" height="50px" id="P10000013_text"><textarea style='border:0px;height:50px;' name="P10000013" id="P10000013"  rows='2.3' cols='30' class='l-textarea' ltype='textarea'></textarea></td>
					   </tr>
					   <tr>
					      <td width="50px" align="center">4</td>
					      <td width="100px" height="50px" align="center">差旅费</td>
					      <td width="100px" height="50px" id="P1000004_text"><input class="onlyNumber" type="text" name="P1000004" id="P1000004" style="height:50px;"/></td>
					      <td width="300px" height="50px" id="P10000014_text"><textarea style='border:0px;height:50px;' name="P10000014" id="P10000014"  rows='2.3' cols='30' class='l-textarea' ltype='textarea'></textarea></td>
					   </tr>
					   <tr>
					      <td width="50px" align="center">5</td>
					      <td width="100px" height="50px" align="center">会议费</td>
					      <td width="100px" height="50px" id="P1000005_text"><input class="onlyNumber" type="text" name="P1000005" id="P1000005" style="height:50px;"/></td>
					      <td width="300px" height="50px" id="P10000015_text"><textarea style='border:0px;height:50px;' name="P10000015" id="P10000015"  rows='2.3' cols='30' class='l-textarea' ltype='textarea'></textarea></td>
					   </tr>
					   <tr>
					      <td width="50px" align="center">6</td>
					      <td width="100px" height="50px" align="center">出版/文献/信息传播/知识产权事务费</td>
					      <td width="100px" height="50px" id="P1000006_text"><input class="onlyNumber" type="text" name="P1000006" id="P1000006" style="height:50px;"/></td>
					      <td width="300px" height="50px" id="P10000016_text"><textarea style='border:0px;height:50px;' name="P10000016" id="P10000016"  rows='2.3' cols='30' class='l-textarea' ltype='textarea'></textarea></td>
					   </tr>
					   <tr>
					      <td width="50px" align="center">7</td>
					      <td width="100px" height="50px" align="center">劳务费</td>
					      <td width="100px" height="50px" id="P1000007_text"><input class="onlyNumber" type="text" name="P1000007" id="P1000007" style="height:50px;"/></td>
					      <td width="300px" height="50px" id="P10000017_text"><textarea style='border:0px;height:50px;' name="P10000017" id="P10000017"  rows='2.3' cols='30' class='l-textarea' ltype='textarea'></textarea></td>
					   </tr>
					   <tr>
					      <td width="50px" align="center">8</td>
					      <td width="100px" height="50px" align="center">专家咨询费</td>
					      <td width="100px" height="50px" id="P1000008_text"><input class="onlyNumber" type="text" name="P1000008" id="P1000008" style="height:50px;"/></td>
					      <td width="300px" height="50px" id="P10000018_text"><textarea style='border:0px;height:50px;' name="P10000018" id="P10000018"  rows='2.3' cols='30' class='l-textarea' ltype='textarea'></textarea></td>
					   </tr>
					   <tr>
					      <td width="50px" align="center">9</td>
					      <td width="100px" height="50px" align="center">协作费</td>
					      <td width="100px" height="50px" id="P1000009_text"><input class="onlyNumber" type="text" name="P1000009" id="P1000009" style="height:50px;"/></td>
					      <td width="300px" height="50px" id="P10000019_text"><textarea style='border:0px;height:50px;' name="P10000019" id="P10000019"  rows='2.3' cols='30' class='l-textarea' ltype='textarea'></textarea></td>
					   </tr>
					   <tr>
					      <td width="50px" align="center">10</td>
					      <td width="100px" height="50px" align="center">其他费用</td>
					      <td width="100px" height="50px" id="P10000010_text"><input class="onlyNumber" type="text" name="P10000010" id="P10000010" onblur="countMoeny1()"   style="height:50px;"/></td>
					      <td width="300px" height="50px" id="P10000020_text"><textarea style='border:0px;height:50px;' name="P10000020" id="P10000020"  rows='2.3' cols='30' class='l-textarea' ltype='textarea'></textarea></td>
					   </tr>
					</table>
	    </fieldset>
		</div> 
      <div title="第十三页" id="form11">
		<fieldset class="l-fieldset">
					<legend class="l-legend">十、课题组成员概况及任务分工</legend>
					<table border="1" width="800px" id="table2">
					   <tr>
					      <td rowspan="5" width="40px">协作单位</td>
					      <td width="100px" height="30px">协作单位名称</td>
					      <td height="30px"colspan="4" id="P1100001_text"><input type="text" name="P1100001" id="P1100001" style="height:30px;"/></td>
					   </tr>
					   <tr>
					      <td width="100px" height="30px">单位地址</td>
					      <td height="30px"colspan="4" id="P1100002_text"><input type="text" name="P1100002" id="P1100002" style="height:30px;"/></td>
					   </tr>
					    <tr>
					      <td width="100px" height="30px">电    话</td>
					      <td height="30px"colspan="4" id="P1100003_text"><input type="text" name="P1100003" id="P1100003" style="height:30px;"/></td>
					   </tr>
					   <tr>
					      <td width="100px" height="30px">邮    编</td>
					      <td height="30px"colspan="4" id="P1100004_text"><input type="text" name="P1100004" id="P1100004" style="height:30px;"/></td>
					   </tr>
					   <tr>
					      <td width="100px" height="30px">法人代表</td>
					      <td height="30px"colspan="4" id="P1100005_text"><input type="text" name="P1100005" id="P1100005" style="height:30px;"/></td>
					   </tr>
					<tr>
					<td  width="30px"></td>
					<td width="100px" height="30px"><a  href="javascript:void(0)" onclick="addNew1();">添加列</a></td>
					<td width="100px" height="30px">姓名</td>
					<td width="100px" height="30px">专  业</td>
					<td width="100px" height="30px">职  称</td>
					<td width="400px" height="30px">任务分工</td>
					</tr>
					</table>
	     
	    </fieldset>
		</div> 
</div>
</form>
</body>
</html>