<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
	 <script type="text/javascript" src="pub/bpm/js/util.js"></script>
	<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
	 <link type="text/css" rel="stylesheet" href="app/office/css/form_detail.css" />
	 <script type="text/javascript" src="pub/fileupload1/fileupload/js/fileupload.js"></script>
	 <script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
     <script type="text/javascript">
    var tbar="";
    var isChecked="${param.isChecked}";
    var serviceId = "${requestScope.serviceId}";//提交数据的id
	var activityId = "${requestScope.activityId}";//流程id
	var processId = "${requestScope.processId}";//退回id

	var areaFlag;//改变状态
     <bpm:ifPer function="TJY2_ZL_GZDJ_SJBMFZR" activityId = "${requestScope.activityId}">areaFlag="1";</bpm:ifPer>
  	 <bpm:ifPer function="TJY2_ZL_GZDJ_FWBFZR" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>
  	 <bpm:ifPer function="TJY2_ZL_GZDJ_YWBMFGLD" activityId = "${requestScope.activityId}">areaFlag="3";</bpm:ifPer>
  	 <bpm:ifPer function="TJY2_ZL_GZDJ_GZR" activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>
  	 
    $(function () {
    	var receiptUploadConfig = {
    			fileSize : "10mb",//文件大小限制
    			businessId : "",//业务ID
    			buttonId : "receiptfilesBtn",//上传按钮ID
    			container : "receiptfilesDIV",//上传控件容器ID
    			title : "请选择照片",//文件选择框提示
    			extName : "jpg,gif,jpeg,png,bmp,doc,docx,xls,xlsx",//文件扩展名限制
    			saveDB : true,//是否往数据库写信息
    			attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
    			fileNum : 5,//限制上传文件数目
    			callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				showAttachFile(files);
    			}
    		};
    	var receiptUploader= new KHFileUploader(receiptUploadConfig);
    	if(isChecked!="" && typeof(isChecked)!="undefined"){
   	    	 $("#sg1").setValues("seal/regist/details.do?id=${requestScope.serviceId}"); 
        	$("#dy").transform("detail",function(){});
        	 fx();
   	    	$("#dy").setValues("seal/regist/details.do?id=${requestScope.serviceId}");
    		$("#form1").initForm({
    			
   	    	toolbar : [{ text: '通过', id: 'submit', icon: 'accept', click: submitSh},
   	    	      { text: '不通过', id: 'nosubmit', icon: 'forbid', click: nosubmitSh},
   	    	 	//使用bpm:ifPer标签检查当前处理环节是否可以退回
   	    	    <bpm:ifPer function="TJY2_ZL_GZDJ_SJBMFZR,TJY2_ZL_GZDJ_FWBFZR,TJY2_ZL_GZDJ_YWBMFGLD,TJY2_ZL_GZDJ_GZR" activityId="${requestScope.activityId}">{
   	    	        text: '退回', icon: 'submit', click: function(){
   	    	            $.getJSON("seal/regist/turnback.do?activityId="+activityId+"&dataBus=&id="+serviceId+"&areaFlag="+areaFlag,
   	    	                    function(resp){
   	    	                        if(resp.success){
   	    	                            top.$.notice("操作成功！",3);
   	    	                         	api.data.window.Qm.refreshGrid();
   	    	                            api.close();
   	    	                        }
   	    	                    }
   	    	            );
   	    	        }
   	    	    },
   	    	    </bpm:ifPer>
   	    	      { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}],
   	           toolbarPosition : "bottom",
               showToolbar: true,
               getSuccess : function(response) {
   				if (response.success){
   					beanData = response.data;
   					//回填表格数据
   				   showAttachFile(response.files);
   				}else {
   					$.ligerDialog.error("获取数据错误!");
   					return;
   				}
   			}
    		});
    		} else {
    			$("#form1").initForm({
            toolbar : [{ text: '保存', id: 'up', icon: 'save', click: save},
                { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}],
        	toolbarPosition : "bottom",
			success : function(responseText) {//处理成功
				if (responseText.success) {
					top.$.notice("保存成功！");
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败' + responseText)
				}
			},
		 	getSuccess : function(response) {
				if (response.success){
					beanData = response.data;
					//回填表格数据
				   showAttachFile(response.files);
				}else {
					$.ligerDialog.error("获取数据错误!");
					return;
				}
			}
    			});
            
        } 
    	/*  if ("${param.pageStatus}"=="detail")
    	        tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	}); */
    	//$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
    	$('#chapterName').autocomplete("employee/basic/searchEmployee.do", {
            max: 12,    //列表里的条目数
            minChars: 1,    //自动完成激活之前填入的最小字符
            width: 200,     //提示的宽度，溢出隐藏
            scrollHeight: 300,   //提示的高度，溢出显示滚动条
            scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
            matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
            autoFill: false,    //自动填充
            formatItem: function(row, i, max) {
                return row.name + '   ' + row.mobileTel;
            },
            formatMatch: function(row, i, max) {
                return row.name + row.mobileTel;
            },
            formatResult: function(row) {
                return row.name;
            }
        }).result(function(event, row, formatted) {
//            alert(row.mobileTel);
			$("#proposerId").val(row.id);
        }); 
    	$('#orgName').autocomplete("employee/basic/searchOrg.do", {
            max: 12,    //列表里的条目数
            minChars: 1,    //自动完成激活之前填入的最小字符
            width: 200,     //提示的宽度，溢出隐藏
            scrollHeight: 300,   //提示的高度，溢出显示滚动条
            scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
            matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
            autoFill: false,    //自动填充
            formatItem: function(row, i, max) {
            //alert(row);
                return row.orgName;
            },
            formatMatch: function(row, i, max) {
                return row.orgName;
            },
            formatResult: function(row) {
                return row.orgName;
            }
        }).result(function(event, row, formatted) {
//            alert(row.orgId);
        	  $("#orgid").val(p.id);
        });
    });
    function submitSh(){
     	var obj=$("#form1").validate().form();
         $.ligerDialog.confirm('是否提交审核？', function (yes){
         if(!yes){return false;}
          $("body").mask("提交中...");
          getServiceFlowConfig("TJY2_ZL_GZDJ","",function(result,data){
                 if(result){
                     top.$.ajax({
                          url: "seal/regist/zltj.do?id="+serviceId+
                         		 "&typeCode=TJY2_ZL_GZDJ&activityId="+activityId+"&areaFlag="+areaFlag,
                          type: "POST",
                          dataType:'json',
                          async: false,
                          success:function (data) {
                        	  api.close();
                              api.data.window.Qm.refreshGrid();
                        	  top.$.notice('审核完成！',3);
                              if (data) {
                                	$("body").unmask();
                              }else{
                            	  return;
                            	  api.colse();
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
    function nosubmitSh(){
      if(areaFlag=="4"){
    	
      }else{
    	$.ligerDialog.confirm('是否要不通过审核？', function (yes){
           if(!yes){return false;}
    	 $("body").mask("正在处理，请稍后！");
    	 getServiceFlowConfig("TJY2_ZL_GZDJ","",function(result,data){
    		 if(result){
                  top.$.ajax({
                      url: "seal/regist/ret.do?id="+serviceId+
                     		 "&typeCode=TJY2_ZL_GZDJ&activityId="+activityId+"&areaFlag="+areaFlag+"&processId="+processId,
                      type: "POST",
                      dataType:'json',
                     
                      async: false,
                      success:function (data) { 
                          if (data) {
                             $("body").unmask();
                             api.close();
                             api.data.window.Qm.refreshGrid();
                       	     top.$.notice('审核完成！',3);
                           }else{
                       		 	return;
                       		 api.colse();
                          }
                      },
                      error:function () {
                    	  $.ligerDialog.alert("出错了!！请重试！");
                          $("body").unmask();
                      }
                  });
             }else{
              $.ligerDialog.alert("出错了！请重试！");
              $("body").unmask();
             }
    		
          });
     });
    		}
    }
    function save(){ 
        var matters=$("#matters").val();
    	var orgName=$("#orgName").val();
    	var  receiptfilesDIV=$("#receiptfilesDIV").val();
    	var sealScore=$("#sealScore").val();
    	if(matters=="" || orgName=="" || sealScore==""){
    		 $.ligerDialog.alert("请填写必填项！");
    		return;
    	}
    	var obj=$("#form1").validate().form();
    		var formData = $("#form1").getValues();
            $("body").mask("正在保存......");
           $.ajax({
               url: "seal/regist/saves.do",
               type: "POST",
               datatype: "json",
               contentType: "application/json; charset=utf-8",
               data: $.ligerui.toJSON(formData),
               success : function(data, stats) {
					if (data.success) {
					$("body").unmask();
						api.data.window.Qm.refreshGrid();
						api.close();
					} else {
					$("body").unmask();
						$.ligerDialog.error('保存失败！' + data.msg);
					}
				},
				error : function(data) {
				$("body").unmask();
					$.ligerDialog.error('保存失败！' + data.msg);
				}
           });
    	}
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
                $("#orgid").val(p.id);
                $("#orgName").val(p.name);
            }
        });
    }
    
    
   /*  function choosefile(){
    	top.$.dialog({
			parent: api,
			width : 600, 
			height : 500, 
			lock : false, 
			title:"选择报告编号",
			content: 'url:app/archives/archives_file_xuanze.jsp',
			data : {"parentWindow" : window}
		});

    } */
    function callBackReport(id, report_sn){
    	var a=0;
    	var temp = report_sn.split(",");
    	for (var i = 0; i < temp.length; i++) {
    		a++;
    	}
    	$("#documentId").val(report_sn);		
    	$("#documentIdid").val(id);
    	$("#documentName").val(a);

	}	
   function close(){
   	if(api.data.window.submitAction)
   		api.data.window.submitAction();
   		api.close();
   }
   function showAttachFile(files){
   	if($("head").attr("pageStatus")=="detail"){
   		$("#receiptfilesBtn").hide();
   	}
		if(files!=null&&files!=""){
			$.each(files,function(i){
				var data=files[i];
				createFileView(data.id,$.kh.isNull(data.name)?data.fileName:data.name,$("head").attr("pageStatus")=="detail"?false:true,"receiptfiles",true,function(fid){
					getUploadFile();
				})
				getUploadFile();
			})
		}
   }
	function getUploadFile(){
		var attachId="";
		var i=0;
		$("#receiptfiles li").each(function(){
			attachId+=(i==0?"":",")+this.id;
			i=i+1;
		});
		if(i>=5){
			$("#receiptfilesBtn").hide(); 
		}
		$("#uploadId").val(attachId);
	}
	function fx(){
    	var files=null;
    	top.$.ajax({
            url: "seal/regist/details.do?id=${requestScope.serviceId}",
            type: "GET",
            dataType:'json',
            async: false,
            success:function (response) {
            	//alert(response.attachs);
            	files=response.files;
       			$("#receiptfilesBtn").hide();
       			showAttachFile(response.files);
            }
        });
    }
    </script>
   
</head>
<body>
<form name='form1' id="form1" action="seal/regist/save.do" method="post" getAction="seal/regist/details.do?id=${param.id}">
    <input type="hidden" id="id" name="id"/>
    <input type="hidden" id="createrid" name="createrid"  value="<%=uId%>"/>
    <input type="hidden" id="orgid" name="orgid" />
    <%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
    <h1 id="dy2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">非用章范围需盖章申请表</h1></br>
     <table id="sg1" class="check">
			 <tr>
                    <td width="650px">&nbsp;</td>
                    <td width="50px" align="center">编号：</td>
                    <td style="width: 200px;" class="l-t-td-right"><input ltype='text' readOnly="true" name="identifier" type="text"/></td>
           			<td width="10px">&nbsp;</td>
                    <td width="10px" align="center"></td>
                    <td width="20" class="l-t-td-right"></td>
            </tr>
	</table>
    <table id="dy" border="1" cellpadding="3" class="l-detail-table">
        <tr>
           <%--  <td class="l-t-td-left">部门</td>
        	<td class="l-t-td-right"><input value="<%=user.getDepartment().getOrgName() %>" validate="{maxlength:50}" ltype="text"  name="applyUnit" id="applyUnit" type="text" ligerui="{disabled:true,iconItems:[{icon:'org',click:chooseOrg}]}"/></td> --%>
             <td class="l-t-td-left">办理事项</td>
        	<td class="l-t-td-right" colspan="5" ><input validate="{required:true,maxlength:200}" ltype="text"  name="matters" id="matters" type="text" /></td>
		</tr>
		<tr>
				<td class="l-t-td-left">附件：</td>
				<td  class="l-t-td-right" id="receiptfilesDIV">
						<input name="uploadIds" id="uploadId" type="hidden"/>
						<a class="l-button3" id="receiptfilesBtn" title="上传文件">+</a>
				</td>
				<td  class="l-t-td-right" id="receiptfilesDIV" colspan="4">
					<div class="l-upload-ok-list"><ul id="receiptfiles"></ul></div>
				</td>
			</tr>
		<tr>
            <td class="l-t-td-left" >使用单位名称</td>
            <td class="l-t-td-right" colspan="5" ><input  validate="{required:true,maxlength:50}" ltype="text" readonly="readonly" name="orgName" id="orgName" type="text" onclick="chooseOrg()"/></td>
		
		</tr>
		<tr></tr>
        <tr>
         	<td class="l-t-td-left" >用章名称</td>
            <td class="l-t-td-right" colspan="2"><u:combo  name="chapterName"  code="quality_seal_type"  validate="required:true"></u:combo>
            <td class="l-t-td-left"  >盖章份数/份</td>
			<td class="l-t-td-right"  colspan="2" ><input name="sealScore" id="sealScore"  class="l-textarea" type="text" ltype="text"  validate="{required:true}"/></td>
        </tr>
        
        <tr>
			<td class="l-t-td-left" >送件人</td>
			<td class="l-t-td-right" colspan="2"><input readOnly="true" name="givePerson" id="givePerson"  class="l-textarea" type="text" ltype='text' /></td>
            <td class="l-t-td-left">日期</td>
			<td class="l-t-td-right"  colspan="2"> <input ligerui="{disabled:true}" ligerui="{format:'YYYY-MM-dd'}" readonly="readonly" name="givePersonDate" id="deliveryHeadDate"  class="l-textarea"  type="text" ltype="date"  /></td>
        
        </tr>
        <tr>	
            <td class="l-t-td-left" ></br></br></br>送件部门负责人</br>签字</br></br></br></td>
        	<td class="l-t-td-right"  colspan="2"><input readOnly="true" id="deliveryHead" name="deliveryHead" type="text" ltype='text' /></td>
        	<td class="l-t-td-left">签字日期</td>
			<td class="l-t-td-right" colspan="2" > <input ligerui="{disabled:true}" ligerui="{initValue:'',format:'YYYY-MM-dd'}" readonly="readonly" name="deliveryHeadDate" id="deliveryHeadDate"   type="text" ltype="date" /></td>
        </tr>   
         <tr>
			<td class="l-t-td-left" ></br></br></br>服务部负责人</br>签字</br></br></br></td>
			<td class="l-t-td-right" colspan="2"><input readOnly="true" name="serviceHead" id="serviceHead"  class="l-textarea" type="text" ltype='text'/></td>
             <td class="l-t-td-left">签字日期</td>
			<td class="l-t-td-right" colspan="2"><input ligerui="{disabled:true}" ligerui="{initValue:'',format:'YYYY-MM-dd'}" readonly="readonly" name="serviceHeadDate" id="serviceHeadDate"  class="l-textarea"  type="text" ltype="date"  /></td>
          
        </tr>
        <tr>
			<td class="l-t-td-left"></br></br></br>业务部门分管院</br>领导签字</br></br></br></td>
			<td class="l-t-td-right" colspan="2"><input readOnly="true" name="opertionalManagement" id="opertionalManagement"  class="l-textarea" type="text" ltype='text' /></td>
             <td class="l-t-td-left">签字日期</td>
			<td class="l-t-td-right"  colspan="2"><input ligerui="{disabled:true}" ligerui="{initValue:'',format:'YYYY-MM-dd'}" readonly="readonly" name="opertionalManagementDate" id="opertionalManagementDate"  class="l-textarea"  type="text" ltype="date"  /></td>
        </tr>
        <tr>	
			<td class="l-t-td-left" ></br></br></br>盖章人签字</br></br></br></td>
			<td class="l-t-td-right"  colspan="2"><input readOnly="true" name="sealer" id="sealer"  class="l-textarea" type="text" ltype='text' validate="{maxlength:40}"/></td>
			<td class="l-t-td-left">盖章日期</td>
			<td class="l-t-td-right"  colspan="2"><input ligerui="{disabled:true}" ligerui="{initValue:'',format:'YYYY-MM-dd'}" readonly="readonly" name="sealDate" id="sealDate"  class="l-textarea"  type="text" ltype="date" /></td>
                                       
        </tr>   
    </table>
   
   
</form>
</body>
</html>
