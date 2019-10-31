<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<style>
.l-text-wrapper,l-text{
border:none;
}
 .l-show{
border:none;border-bottom:solid 1px #9DB9E2;
}
</style>
<script type="text/javascript">
String.prototype.LTrim = function()    
{    
return this.replace(/(^\s*)/g, "");    
};
	$(function () {
		 $("input[readOnly]").keydown(function(e) {
             e.preventDefault();
        });
	    
	   //获取告知书编号
	   url = "device/tzsbapp/getApplicationCode.do";
		$.post(url,function(code){
			$("#application_code").val(code);
			
		});
		
		 if("${param.status}"=='modify'){
			    $("#formObj").initForm({    //参数设置示例
				       toolbar:[
				            { text:'保存', id:'save',icon:'save', click:saveAdd },
				            { text:'关闭', id:'close',icon:'cancel', click:close }
				        ],
				        toolbarPosition: "bottom",
				        getSuccess:function(res){
				    
				        },afterParse:function(formObj){//form表单完成渲染后，回调函数
				        	 
						}
				    });
			   url = "device/tzsbapp/detial.do";
				$.post(url,{"appId":"${param.id}"},function(code){
					 fillFormByJSON(code);
				});
			
		 }else if("${param.status}"=='detail'){
			 url = "device/tzsbapp/detial.do";
				$.post(url,{"appId":"${param.id}"},function(code){
					 fillFormByJSON(code);
				});
		 }else{
			    $("#formObj").initForm({    //参数设置示例
				       toolbar:[
				            { text:'保存', id:'save',icon:'save', click:saveAdd },
				            { text:'关闭', id:'close',icon:'cancel', click:close }
				        ],
				        toolbarPosition: "bottom",
				        getSuccess:function(res){
				        }
				    });
		 }
		 
		   
	});
    function saveAdd(){
    	var csort=$("#tzsb_application_device_sort_code").ligerComboBox();
    	var csort_value=csort.getValue();
    	$("#tzsb_application_device_sort").val($("#tzsb_application_device_sort_code").val().LTrim());
    	//设备通知书
    	 var d1 = {};
    	 d1 = $(".TzsbApplication").getValues();
    	var  tzsbApplication=$.ligerui.toJSON(d1);
    	
    	
    	//告知书工程情况
    	 var d2 = {};
    	 d2 = $("#TzsbAppEngineerSituations").getValues();
    	var  tzsbAppEngineerSituations=$.ligerui.toJSON(d2);
    	
    	//现场施工组织
    	 var d3 = {};
    	 d3 = $("#TzsbAppConstrucationOrg").getValues();
    	var  tzsbAppConstrucationOrg=$.ligerui.toJSON(d3);
    	
      //表单验证
    	if ($("#formObj").validate().form()) {
	       $("body").mask("正在保存数据，请稍后！");
	    	url = "device/tzsbapp/saveApp.do";
	        $.ajax({
	            url: url,
	            type: "POST",
	           	data:{"tzsbApplication":tzsbApplication,"tzsbAppEngineerSituations":tzsbAppEngineerSituations,"tzsbAppConstrucationOrg":tzsbAppConstrucationOrg},
	            success: function (data, stats) {
	            	$("body").unmask();
	                if (data["success"]) {
	                	if(api.data.window.Qm)
	                	{
	                		api.data.window.Qm.refreshGrid();
	                	}
	                    top.$.dialog.notice({content:'保存成功'});
	                    $("#appId").val(data['id']);
	                   // api.close();
	                }else
	                {
	                	$.ligerDialog.error('提示：' + data);
	                }
	            },
	            error: function (data,stats) {
	            	$("body").unmask();
	                $.ligerDialog.error('提示：' + JSON.stringify(data));
	            }
	        });    		
	        
	        var construct_sort=$("#construct_sort").val();
 		    var fk_construct_units_id=$("#tzsb_app_engineersituations_fk_construct_units_id").val();
 		    var fk_construction_units_id=$("#fk_construction_units_id-2").val();
 		    var construction_units_name=$("#construction_units_name-2").val();
 		    $("#form_tzsbAppDevice").attr("action","device/tzsbapp/saveAppDevice.do?status=${param.status}&construct_sort="+construct_sort+"&fk_construct_units_id="+fk_construct_units_id+"&fk_construction_units_id="+fk_construction_units_id+"&construction_units_name="+construction_units_name);
    	}

   }
   function close(){
        api.close();
    }
	function selectorg(type){
		com_type=type;
		var url = 'url:app/enter/enter_open_list.jsp';
		if (type != "") {
			url += '?com_type='+com_type;
		}
		
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择企业信息",
			content: url,
			data : {"parentWindow" : window}
		});
	}
	function callBack(id,name,com_code,address,com_legal_rep){
		//判断选择单位类型关联相应字段 0 :使用单位 1：建设单位 2： 安装代为 3：现场施工机构 4：产品监检单位 5:锅炉产品监检单位 6:土建工程监理或验收单位 7:土建工程施工单位
		if(com_type=='0'){
			$('#fk_construction_units_id-1').val(id);
			$('#construction_units_name-1').val(name);
			$('#com_name').val(name);
			$('#com_address').val(address);
			$('#com_code').val(com_code);
			$('#com_legal_rep').val(com_legal_rep);
		}else if(com_type=='1'){
			$('#tzsb_app_engineersituations_fk_construct_units_id').val(id);
			$('#tzsb_app_engineersituations_company_name').val(name);
		}else if(com_type=='2'){
			$('#engineering_devise_units').val(id);
			$('#engineering_devise_units_name').val(name);
		}else if(com_type=='3'){
			$('#fk_construction_units_id-2').val(id);
			$('#construction_units_name-2').val(name);
		}else if(com_type=='4'){
			$('#monitor_unit_id').val(id);
			$('#cp_by2').val(name);
		}else if(com_type=='5'){
			$('#fk_inspection_unit_id').val(id);
			$('#cp_by1').val(name);
		}
		else if(com_type=='6'){
			$('#tzsb_app_supervision_unit__fk_supervision_unit_id_1').val(id);
			$('#tzsb_app_supervision_unit__supervision_unit_name_1').val(name);
			$('#tzsb_app_supervision_unit__supervision_unit_code_1').val(com_code);
			
		}else if(com_type=='7'){
			$('#tzsb_app_const_unit__fk_construction_unit_id_1').val(id);
			$('#tzsb_app_const_unit__construction_unit_name_1').val(name);
			$('#tzsb_app_const_unit__construction_unit_code_1').val(com_code);
		}else if(com_type=='8'){
			$('#tzsb_app_device__fk_company_info_make_id_1').val(id);
			$('#tzsb_app_device__make_units_name_1').val(name);
			$('#tzsb_app_device__make_units_code_1').val(com_code);
		}else if(com_type=='9'){
			$('#tsjc_app_outsour__contractor_unit_1').val(id);
			$('#tsjc_app_outsour__contractor_unit_1_name').val(name);
			$('#tsjc_app_outsour__contractor_unit_code_1').val(com_code);
		}
	}
	function fillFormByJSON(jsonObj){
		jsonObj = JSON.parse(jsonObj.content); 
		var tzsbApplication=jsonObj.tzsbApplication;
		$("#appId").val(tzsbApplication["id"]);
		$("#created_by").val(tzsbApplication["created_by"]);
		$("#created_date").val(tzsbApplication["created_date"]);
		var node1=$(".TzsbApplication  *");
		var len=node1.length;
		 for ( var  i = 0 ; i < len; i ++ ){
             var  obj  =  node1[i];
             switch(obj.tagName){
             case 'INPUT':
            	 if(obj.className.indexOf("date_input")==0){
            		 var dates=tzsbApplication[obj.name];
            		 if(!isNaN(dates)){
            			 try{
            				 var atestr=new Date(dates).format("yyyy-MM-dd");
	                		 $(obj).val(atestr);
            			 }catch(e){
            			 }
            		 }
            	 }else if(obj.id=='tzsb_application_device_sort_code'){
            		 $("#tzsb_application_device_sort_code").ligerComboBox().setValue(tzsbApplication[obj.name]);
            		 //$("#tzsb_application_device_sort_code").ligerComboBox().selectValue(tzsbApplication[obj.name]);
            	 }else if(obj.name=='construct_sort'){
            		  $("#construct_sort-txt").ligerComboBox().setValue(tzsbApplication[obj.name]);
            	 }else{
            		 if(obj.id=='tzsb_application_device_sort_code_val'){
            		 }else{
            	       $(obj).val(tzsbApplication[obj.name]);
            		 }
            	 }
            	 //alert(obj.tagName+'--'+jsonObj.tzsbApplication[obj.name]);
            	 break;
             case "TEXTAREA":
            	 $(obj).text(tzsbApplication[obj.name]);
            	 break;
             case "DIV":
            	 var name=$(obj).attr("name");
            	 var type=$(obj).attr("xtype");
            	 if(name!=null&&name!='undefined'){
            		 if(type!=null&&type.toLowerCase()=='date'){
	            		 var dates=tzsbApplication[name];
	            		 if(!isNaN(dates)){
	            			 try{
	            				 var atestr=new Date(dates).format("yyyy-MM-dd");
		                		 $(obj).html(atestr);
	            			 }catch(e){
	            			 }
	            		
	            		 }
            		 }else{
            			 if(name!=null&&name=='device_sort_code'){
            				 $(obj).html(tzsbApplication['device_sort']);
            			 }else  if(name!=null&&name=='construct_sort'){
            				 var cs=tzsbApplication['construct_sort'];
            				 if(cs=='1')
            				 $(obj).html("安装");
            				 else if(cs=='2')
                				 $(obj).html("改造");
            				 else if(cs=='3')
                				 $(obj).html("维修");
            				 else{
            					 $(obj).html("");
            				 }
            			 }else{
            				 $(obj).html(tzsbApplication[name]);
            			 }
            			
            		 }
            	 }
            	 break;
            	 
             }
             
        }
		 
			var tzsbAppConstrucationOrg=jsonObj.tzsbAppConstrucationOrg;
			var node2=$("#TzsbAppConstrucationOrg  *");
			var len2=node2.length;
			 for ( var  i = 0 ; i < len2; i ++ ){
	             var  obj  =  node2[i];
	             switch(obj.tagName){
	             case 'INPUT':
	            	 if(obj.className.indexOf("date_input")==0){
	            		 var dates=tzsbAppConstrucationOrg[obj.name];
	            		 if(!isNaN(dates)){
	            			 try{
	            				 var atestr=new Date(dates).format("yyyy-MM-dd");
		                		 $(obj).val(atestr);
	            			 }catch(e){
	            			 }
	            		 }
	            	 }else{
	            	 $(obj).val(tzsbAppConstrucationOrg[obj.name]);
	            	 }
	            	 //alert(obj.tagName+'--'+jsonObj.tzsbApplication[obj.name]);
	            	 break;
	             case "DIV":
	            	 var name=$(obj).attr("name");
	            	 var type=$(obj).attr("xtype");
	            	 if(name!=null&&name!='undefined'){
	            		 if(type!=null&&type.toLowerCase()=='date'){
		            		 var dates=tzsbAppConstrucationOrg[name];
		            		 if(!isNaN(dates)){
		            			 try{
		            				 var atestr=new Date(dates).format("yyyy-MM-dd");
			                		 $(obj).html(atestr);
		            			 }catch(e){
		            			 }
		            		
		            		 }
	            		 }else{
	            			 $(obj).html(tzsbAppConstrucationOrg[name]);
	            		 }
	            	 }
	            	 break;
	             }
	             
	        }
			 
				var tzsbAppEngineerSituations=jsonObj.tzsbAppEngineerSituations;
				var node3=$("#TzsbAppEngineerSituations  *");
				var len3=node3.length;
				 for ( var  i = 0 ; i < len3; i ++ ){
		             var  obj  =  node3[i];
		             switch(obj.tagName){
		             case 'INPUT':
		            	 if(obj.className.indexOf("date_input")==0){
		            		 var dates=tzsbAppEngineerSituations[obj.name];
		            		 if(!isNaN(dates)){
		            			 try{
		            				 var atestr=new Date(dates).format("yyyy-MM-dd");
			                		 $(obj).val(atestr);
		            			 }catch(e){
		            			 }
		            		
		            		 }
		            	 }else if(obj.name=='engineering_district'){
		            		// $("#engineering_district_val").val(tzsbAppEngineerSituations[obj.name]);
		            		 $("#engineering_district").ligerComboBox().setValue(tzsbAppEngineerSituations[obj.name]);
		            	 }else{
		            		 if(obj.id!='engineering_district_val'){
         		            	 $(obj).val(tzsbAppEngineerSituations[obj.name]);
		            		 }
		            	 }
		            	 break;
		             case "DIV":
		            	 var name=$(obj).attr("name");
		            	 var type=$(obj).attr("xtype");
		            	 if(name!=null&&name!='undefined'){
		            		 if(type!=null&&type.toLowerCase()=='date'){
			            		 var dates=tzsbAppEngineerSituations[name];
			            		 if(!isNaN(dates)){
			            			 try{
			            				 var atestr=new Date(dates).format("yyyy-MM-dd");
				                		 $(obj).html(atestr);
			            			 }catch(e){
			            			 }
			            		
			            		 }
		            		 }else{
		            			 if(name!=null&&name=='engineering_district'){
		         		         	//建设单位
		         					url = "device/tzsbapp/getOrgByCode.do?orgCode="+tzsbAppEngineerSituations[name];
		         					$.post(url,function(result){
		         						 if(result.success){
		         							 try{
		         								$("#engineering_district").html(result.org.orgName);
		         							 }catch(e){}
		         							 
		         						 }
		         					});
		            				
		            			 }else{
		            			 $(obj).html(tzsbAppEngineerSituations[name]);
		            			 }
		            		 }
		            	 }
		            	 break;
		             }
		        }
					//施工单位基本情况
					url = "enter/basic/getEnterById.do?id="+tzsbApplication['fk_construction_units_id'];
					$.post(url,function(result){
						 if(result.success){
							 if("${param.status}"=='detail'){
								 $('#com_legal_rep').html(result.content['com_legal_rep']);
								 $('#com_name').html(result.content['com_name']);
								 $('#com_address').html(result.content['com_address']);
								 $('#com_code').html(result.content['com_code']);
							 }else{
								 $('#com_legal_rep').val(result.content['com_legal_rep']);
								 $('#com_name').val(result.content['com_name']);
								 $('#com_address').val(result.content['com_address']);
								 $('#com_code').val(result.content['com_code']);
							 }
							 
						 }
					});
		         	//建设单位
					url = "enter/basic/getEnterById.do?id="+tzsbAppEngineerSituations['fk_construct_units_id'];
					$.post(url,function(result){
						 if(result.success){
							 if("${param.status}"=='detail'){
								 $('#tzsb_app_engineersituations_company_name').html(result.content['com_name']);
							 }else{
								 $('#tzsb_app_engineersituations_company_name').val(result.content['com_name']);
							 }
						 }
					});
					//工程设计单位
					var tzsbdu=tzsbAppEngineerSituations['engineering_devise_units'];
					if(tzsbdu!=null&&tzsbdu!=''&&tzsbdu!='undefined'){
						url = "enter/basic/getEnterById.do?id="+tzsbdu;
						$.post(url,function(result){
							 if(result.success){
								 try{
									 if("${param.status}"=='detail'){
										 $('#engineering_devise_units_name').html(result.content['com_name']);
									 }else{
										 $('#engineering_devise_units_name').val(result.content['com_name']);
									 }
								 }catch(e){}
								
							 }
						});
					}
					    var construct_sort=tzsbApplication['construct_sort'];
			 		    var fk_construct_units_id=tzsbAppEngineerSituations['fk_construct_units_id'];
			 		    var fk_construction_units_id=tzsbAppConstrucationOrg['fk_construction_units_id'];
			 		    var construction_units_name=tzsbAppConstrucationOrg['construction_units_name'];
			 		    $("#form_tzsbAppDevice").attr("action","device/tzsbapp/saveAppDevice.do?status=${param.status}&construct_sort="+construct_sort+"&fk_construct_units_id="+fk_construct_units_id+"&fk_construction_units_id="+fk_construction_units_id+"&construction_units_name="+construction_units_name);
					
	}
</script>
</head>
<body>
	<div class="navtab">
		<div title="设备基本信息" id="form1" >
			<%@ include file="device_notify_baseinfo.jsp"%>
		</div>
		
			<div title="施工分包" id="form2" >
				<%@ include file="device_build_divide.jsp"%>
			</div>
			
			<div title="设备情况" id="form3" >
				<%@ include file="device_notify_situ.jsp"%>
			</div>
	
		
		<div title="土建工程施工单位" id="form4" >
				<%@ include file="device_tjbuild_unit.jsp"%>
		</div>
		
		<div title="土建工程监理或验收单位" id="form5" >
				<%@ include file="device_buildys_divide.jsp"%>
		</div>
		
		<div title="提交的文件资料" id="form6" >
				<%@ include file="device_commited_doc.jsp"%>
		</div>
		
		<div title="现场管理、专业、作业人员情况" id="form7" >
				<%@ include file="device_work_proj.jsp"%>
		</div>
	</div>
</body>
</html>
