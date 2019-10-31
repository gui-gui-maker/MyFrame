<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    CurrentSessionUser user=(CurrentSessionUser)request.getSession().getAttribute("currentSessionUser");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--获取当前登录人  -->
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String userId=e.getId();
	String uId = SecurityUtil.getSecurityUser().getId();
	String users=curUser.getName();
	String userid= curUser.getId();
	%>
<head pageStatus="${param.status}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<link type="text/css" rel="stylesheet" href="app/office/css/form_detail.css" />
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<script type="text/javascript">
	//jQuery页面载入事件
    var isChecked="${param.isChecked}";
    var serviceId = "${requestScope.serviceId}";//提交数据的id
	var activityId = "${requestScope.activityId}";//流程id
	var processId = "${requestScope.processId}";//过程
	var areaFlag;//改变状态
	/* <bpm:ifPer function="TJY2_OFFICE_BMZR_SH" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>//部门审核 */
	<bpm:ifPer function="TJY2_OFFICE_ZR_SH" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>//办公室主任审核
	/* <bpm:ifPer function="TJY2_OFFICE_LD_SH" activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>//分管领导审核  */ 
	var beanData;
	var userGrid;
	var orgGrid;
	var status="${param.status}";
	var  notes_id="${param.notes_id}";
	var pnumber;
	$(function() {
		/* $('#endTime').blur(function() {
	     
              }); */ 
		 
		 $("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
		 $('#department').autocomplete("employee/basic/searchOrg.do", {
            max: 12,    //列表里的条目数
            minChars: 1,    //自动完成激活之前填入的最小字符
            width: 200,     //提示的宽度，溢出隐藏
            scrollHeight: 300,   //提示的高度，溢出显示滚动条
            scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
            matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
            autoFill: false,    //自动填充
            formatItem: function(row, i, max) {
                return row.orgName;
            },
            formatMatch: function(row, i, max) {
                return row.orgName;
            },
            formatResult: function(row) {
                return row.orgName;
            }
        }).result(function(event, row, formatted) {
        }); 
		
	   
	 	 if(isChecked!="" && typeof(isChecked)!="undefined"){
		      $("#formObj").setValues("oa/meetingReq/info/detail.do?id=${requestScope.serviceId}");
		      $("#formObj").initForm({
		    	  toolbar : [ 
				            {text : '审核',id : 'submit',icon : 'submit',click : sh}, 
				            {text : '关闭',id : 'close',icon : 'cancel',click : function() {api.close();}}
				          ],
				          toolbarPosition : "bottom",
				          showToolbar: true
				
			 
		});
		      $("#userGrid").transform("detail");
              $.post("oa/meetingReq/info/getUser.do?reqId=${requestScope.serviceId}", "", function(resp) {
			if (resp.success) {
				userGrid.loadData({Rows : resp.Rows});
			}else{
				$.ligerDialog.error("加载参会人信息失败！");
			}
		});
		$("#orgGrid").transform("detail");
              $.post("oa/meetingReq/info/getOrg.do?reqId=${requestScope.serviceId}", "", function(resp) {
			if (resp.success) {
				orgGrid.loadData({Rows : resp.Rows});
			}else{
				$.ligerDialog.error("加载参会单位信息失败！");
			}
		});
		      
			initGrid(); 
			$("#form-tab").attr("disabled",true); 
	 	}else if("${param.a}"=="a"){
	 		/* $("#formObj").setValues("oa/meetingReq/info/detail.do?id=${requestScope.serviceId}"); */
	      $("#formObj").initForm({
	    	  toolbar : [{ text: '打印', id: 'print', icon: 'print', click: print},
		    		      { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}],
			          toolbarPosition : "bottom",
			          showToolbar: true
			});
	        $("#formObj").transform("detail");
	        $("#formObj").setValues("oa/meetingReq/info/detailMeeting.do?id=${param.id}");
	        
	        $("#form1").transform("detail");
	        $("#form1").setValues("oa/meetingNotes/info/detail.do?id=${param.notes_id}");
		    $("#userGrid").transform("detail");
	        $.post("oa/meetingReq/info/getUser.do?reqId=${param.id}", "", function(resp) {
				if (resp.success) {
					userGrid.loadData({Rows : resp.Rows});
				}else{
					$.ligerDialog.error("加载参会人信息失败！");
				}
			});
	        
			$("#orgGrid").transform("detail");
	        $.post("oa/meetingReq/info/getOrg.do?reqId=${param.id}", "", function(resp) {
				if (resp.success) {
					orgGrid.loadData({Rows : resp.Rows});
				}else{
					$.ligerDialog.error("加载参会单位信息失败！");
				}
			});
			      
			initGrid(); 
			//$("#form-tab").attr("disabled",true);
		}else{
			if("${param.status}"=="modify"){
				$("#formObj").setValues("oa/meetingReq/info/detailMeeting.do?id=${param.id}");
			}
		  $("#formObj").initForm({
			toolbar : [ 
			            {text : '保存',id : 'save',icon : 'save',click : save}, 
			            {text : '关闭',id : 'close',icon : 'cancel',click : function() {api.close();}}
			          ],
			          
			toolbarPosition : "bottom",
			success : function(responseText) {//处理成功
				if (responseText.success) {
					top.$.notice("保存成功！");
					if(api.data.window.Qm){ 
						api.data.window.Qm.refreshGrid();
					}
					api.close();
				} else {
					$.ligerDialog.error('保存失败' + responseText)
				}
			},
			getSuccess : function(response) {
				          	$("#notesDiv").hide();
				if (response.success){
					beanData = response.data;
					var meetingNotes=beanData.meetingNotes;
					if(meetingNotes!=null&&status=="detail"){
						$("#form-Notes-tab").setValues(meetingNotes);
					}
					  $("#userGrid").transform("detail");
		                $.post("oa/meetingReq/info/getUser.do?reqId=${param.id}", "", function(resp) {
					if (resp.success) {
						userGrid.loadData({Rows : resp.Rows});
					}else{
						$.ligerDialog.error("加载参会人信息失败！");
					}
				});
				$("#orgGrid").transform("detail");
		                $.post("oa/meetingReq/info/getOrg.do?reqId=${param.id}", "", function(resp) {
					if (resp.success) {
						orgGrid.loadData({Rows : resp.Rows});
					}else{
						$.ligerDialog.error("加载参会单位信息失败！");
					}
				});
				}
				else {
					$.ligerDialog.alert("获取数据错误!");
					return;
				}

			},
			afterParse: function(){
				$("#roomCode").val(api.data.roomName);
				$("#hyAdress").val(api.data.hyadress);
				
			}
		});
		
		
		initGrid();
	 	}
	});
	
	
	
	function sh() {
		top.$.dialog({
			width: 600,
			height: 200,  
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审核",
			content: 'url:app/office/meeting/update_apply_yijian.jsp?pageStatus=add&serviceId='+serviceId+'&activityId='+activityId+'&processId='+processId
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
                    $("#personId").val(p.id);
                    $("#department").val(p.name);
                }
            });
        }
	
	function initGrid(){
		//初始化参会人员表格
		var userIdNode="id";
		var userNameNode="name";
		var userTelNode="mobileTel";
		//初始化参会单位表格
		var orgIdNode="id";
		var orgNameNode="orgName";
		var orgTelNode="tellphone";
		//定义表格工具条
		if(status!="detail" && (isChecked=="" || typeof(isChecked)=="undefined")){
		var arrays1={};
			$("#userToolbar").ligerToolBar({
				items : [
						{text : "添加",icon : "add",
						 click : function() {
							 //selectUnitOrUser("4",1,"","",function(datas){
					     selectUnitOrUser("4",1,"","",function(callbackData) {
							 if(arrays1.length!='' && arrays1.length!=null){
                                   if(callbackData.code){
										   var temp = callbackData.code.split(","); 
                                           for(var i=0;i<temp.length;i++){ 
                                              for(var j=0;j<arrays1.length;j++){			
                                                    if(arrays1[j]==temp[i]){
			                                            temp.splice(i,1);//从下标为i的元素开始，连续删除1个元素
                                                         i--;
                                                         $("body").mask("加载中...")
                                                        } 
	                                                  }
		                                         $.getJSON("employee/basic/detail.do?id="+temp[i],function(res){
										   		 $("body").unmask();
										   				if(res.success){  
										   					var id= res.data.id;
										   					var array = {};
										   					array["id"]= id;
										   					array["name"] = res.data.name;
										   					if(res.data.mobileTel){
										   						var tel=res.data.mobileTel;
											   					array["mobileTel"] = tel;
										   					}
										   					userGrid.addRow(array);
										   				}
										   			})

                                          }
                               }
                               arrays1.push.apply(arrays1, temp);
                      }else{
                             if(callbackData.code){
										  var temp = callbackData.code.split(",");
                                          for(var i in temp){ 
                                                    $("body").mask("加载中...")
										   			$.getJSON("employee/basic/detail.do?id="+temp[i],function(res){
										   				$("body").unmask();
										   					
										   				if(res.success){
										   					var id= res.data.id;
										   					var array = {};
										   					array["id"]= id;
										   					array["name"] = res.data.name;
										   					if(res.data.mobileTel){
										   						var tel=res.data.mobileTel;
											   					array["mobileTel"] = tel;
										   					}
										   					userGrid.addRow(array);
										   				}
										   			})
            }
      }arrays1=temp;
 }  
										});
							}
						},
						{text : "删除",icon : "delete",click : function() {
								var selectRowObj = userGrid.getCheckedRows();
								for(var i in selectRowObj)
									userGrid.deleteRow(selectRowObj[i]);
							}
						} 
						]
			});
			var arrays={};
			$("#orgToolbar").ligerToolBar({
								items : [
										{text : "添加",icon : "add",click : function() {
												selectUnitOrUser("0",1,"","",function(callbackData) {
												 if(arrays!='' && arrays!=null && arrays!="undefined"){
													if(callbackData.code){
												   		var temp = callbackData.code.split(",");
												   		 for(var i=0;i<temp.length;i++){ 
                                                          for(var j=0;j<arrays.length;j++){			
                                                              if(arrays[j]==temp[i]){
			                                                  temp.splice(i,1);//从下标为i的元素开始，连续删除1个元素
                                                                 i--;
                                                               $("body").mask("加载中...")
                                                        } 
	                                                  }
												   			$.getJSON("rbac/org/detail.do?id="+temp[i],function(res){
												   				$("body").unmask();
												   				if(res.success){
												   					var id= res.data.id;
												   					//var name = callbackData.name.split(",")[i];
												   					var array = {};
												   					array["id"]= id;
												   					array["orgName"] = res.data.orgName;
													   				array["tellphone"] = res.data.tellphone;
												   					orgGrid.addRow(array);
												   				}
												   			})
												   	   }
												    }arrays.push.apply(arrays, temp);
												 }else{
                                                   if(callbackData.code){
                                                    var temp = callbackData.code.split(",");
                                                    for(var i in temp){ 
                                                    $("body").mask("加载中...")
												     $.getJSON("rbac/org/detail.do?id="+temp[i],function(res){
												   				$("body").unmask();
												   				if(res.success){
												   					var id= res.data.id;
												   					//var name = callbackData.name.split(",")[i];
												   					var array = {};
												   					array["id"]= id;
												   					array["orgName"] = res.data.orgName;
													   				array["tellphone"] = res.data.tellphone;
												   					orgGrid.addRow(array);
												   				   }
												   				});
												   			}
												     }arrays=temp;
												  }
										      });
											}
										},
										{text : "删除",icon : "delete",click : function() {
											var selectRowObj = orgGrid.getCheckedRows();
											for(var i in selectRowObj)
												orgGrid.deleteRow(selectRowObj[i]);
											}
										}
										]
							});
		}
		
		//定义表格
		userGrid = $("#userGrid").ligerGrid({
			columns : [ 
			            {name : userIdNode,hide : true},
			            {display : '姓名',name : userNameNode,align : 'left'}, 
			            {display : '联系电话',name : userTelNode,align : 'left'} 
			          ],
			width : '99%',
			data : {Rows:[]},
			title : '',
			frozen :false,
			rownumbers:true,
			usePager: true,
			page: 1,
	        pageSize: 15,
	        pageSizeOptions: [10,15],
			checkbox : "${param.status}"=="detail"?false:true
		});
		orgGrid = $("#orgGrid").ligerGrid({
			columns : [ 
			            {name : orgIdNode,hide : true}, 
			            {display : '单位名称',name : orgNameNode,align : 'left'},
						{display : '联系电话',name : orgTelNode,align : 'left'}
			          ],
			width : '99%',
			data:{Rows:[]},
			frozen :false,
			rownumbers:true,
			usePager: true,
			title : '',
			page: 1,
	        pageSize: 15,
		    pageSizeOptions: [10,15],
			checkbox : "${param.status}"=="detail"?false:true
		});
		
	}


	//选择会议室
	function selectRoom() {
	 	var startTimeVal=$("#startTime").val();
		var endTimeVal=$("#endTime").val();
		/* if(startTimeVal==null||startTimeVal==""||endTimeVal==null||endTimeVal==""){
			$.ligerDialog.alert("请填写会议开始和结束时间！");
			return;
		}  */
		var title = "会议室选择";
		var url = "url:app/office/meeting/selectRoom_list1.jsp?startTime="+startTimeVal+"&endTime="+endTimeVal;
		var width = 800;
		top.$.dialog({
			width : width,
			height : 400,
			lock : true,
			parent : api,
			id : "",
			title : title,
			content : url,
			cancel : true,
			ok : function() {
				var datas = this.iframe.contentWindow.getSelectResult();
				if (datas) {
					$("#fkOaMeetingRoom").val(datas.roomid);
					$("#roomCode").val(datas.roomcode);
					$("#hyAdress").val(datas.place);
					pnumber=datas.pnumber;
					var pnumbers=$("#pnumber").val();
					if(parseInt(pnumbers)>parseInt(pnumber)){
						 $("#pnumber").val("");
					 }
					var startTime=$("#startTime").val();
				     var endTime=$("#endTime").val();
				     var roomId = $("#fkOaMeetingRoom").val();
				     var id = $("#meeting_id").val();
					if(startTime!="" && endTime!=""&&roomId!=""){
				           if(endTime<startTime){
				             $.ligerDialog.warn("结束时间不能小于开始时间！")
				           }else{
				        	  /*  $.ligerDialog.warn("时间符合规范！统计正在开发中！") */
				        	   $.ajax({
				   	        	url: "oa/meetingReq/info/countReq.do?startTime="+startTime+"&endTime="+endTime+"&id="+id+"&roomId="+$("#fkOaMeetingRoom").val(),
				   	            type: "POST",
				   	            datatype: "json",
				   	            contentType: "application/json; charset=utf-8",
				   	            success: function (resp) {
				   	            	if(resp.success){
				   	            		
				   	            	}else{
				   	            		$.ligerDialog.open(
					   	            			{
					   	            			width: 300,
					   	            			type: 'warn',
					   	            			content: resp.msg,
					   	            			buttons: [{ text: '确定', onclick: function() {
					   	            				$.ligerDialog.close(); 
					   	            				$(".l-dialog,.l-window-mask").css("display","none"); 
					   	            			} }, 
					   	            		    			{ text: '取消', onclick: function() {api.close();}}]
					   	            			});
				   	            	}
				   	            	
				   	            },
				   	            error: function (data) {
				   	            	$.ligerDialog.alert("后台查询失败！");
				   	            }
				   	        });
				           }
				        }
					return true;
				} else {
					return false;
				}
			}
		});
	}

	//保存数据
	function save() {
		 var id=$("#meeting_id").val();
		   var startTime=$("#startTime").val();
		     var endTime=$("#endTime").val();
		     var content=$("#content").val();
		     var remark=encodeURI(encodeURI($('#remark').val()));
		     var compere=encodeURI(encodeURI($("#compere").val()));
		if ($("#formObj").validate().form()) {
				var data = $("#form-tab").getValues();
				userGrid.endEdit();
				orgGrid.endEdit();
			    var userId="";
			    var orgId="";
				//将表格数据打包成json格式的数据存放在data数组中
				var userData=[];
				var orgData=[];
				var userIds=userGrid.getData();
				for(var i=0;i<userIds.length;i++){
					userData.push({id:userIds[i].id});
					userId+=(i==0?"":",")+userIds[i].id;
				}
			
				var orgIds=orgGrid.getData();
				for(var i=0;i<orgIds.length;i++){
					orgData.push({id:orgIds[i].id});
					orgId+=(i==0?"":",")+orgIds[i].id;
				}
				/* data["lUser"] = userData;
				data["lOrg"] =orgData;
				$("#meetingUser").val(userId);
				$("#meetingOrg").val(orgId); */
				/* if(userData==""||orgData==""){
					$.ligerDialog.warn("参会人员和参会单位数据不能为空！")
					return;
				} */
				//data["meetingNotes"] = $("#form-Notes-tab").getValues();
				 $.ajax({
					url : "oa/meetingReq/info/saveMeetingReq.do?&userId="+userId+"&orgId="+orgId+"&Id="+id+"&startTime="+startTime+"&endTime="+endTime+"&content="+content+"&remark="+remark+"&compere="+compere,
					type : "POST",
					datatype : "json",
					contentType : "application/json; charset=utf-8",
					data : $.ligerui.toJSON(data),
					success : function(data, stats) {
						if (data["success"]) {
							top.$.dialog.notice(data.msg);
							api.data.window.Qm.refreshGrid();
							api.close();
						} else {
							$.ligerDialog.error('保存失败！' + data.msg);
						}
					},
					error : function(data) {
						$.ligerDialog.error('保存失败！' + data.msg);
					}
				}); 
			}
	}
function yzpnumber(){
	var pnumbers=$("#pnumber").val();
    if(parseInt(pnumbers)>parseInt(pnumber)){
	   $.ligerDialog.warn("参会人数过多，请重新选择会议室！");
	}
}
//会议时间验证
function checkTime(date){
	var startTime=$("#startTime").val();
    var endTime=$("#endTime").val();
    var roomId = $("#fkOaMeetingRoom").val();
    if(startTime!="" && endTime!=""){
          if(endTime<startTime){
            $.ligerDialog.warn("结束时间不能小于开始时间！")
          }else{
       	  /*  $.ligerDialog.warn("时间符合规范！统计正在开发中！") */
       	  if(roomId!=""){
       		$.ajax({
  	        	url: "oa/meetingReq/info/countReq.do?startTime="+startTime+"&endTime="+endTime+"&id="+$("#meeting_id").val()+"&roomId="+$("#fkOaMeetingRoom").val(),
  	            type: "POST",
  	            datatype: "json",
  	            contentType: "application/json; charset=utf-8",
  	            success: function (resp) {
  	            	if(resp.success){
  	            		
  	            	}else{
  	            		$.ligerDialog.open(
	   	            			{
	   	            			width: 280,
	   	            			type: 'warn',
	   	            			content: resp.msg,
	   	            			buttons: [{ text: '确定', onclick: function() {
	   	            				$.ligerDialog.close(); 
	   	            				$(".l-dialog,.l-window-mask").css("display","none"); 
	   	            			} }, 
	   	            		    			{ text: '取消', onclick: function() {api.close();}}]
	   	            			});
  	            	}
  	            	
  	            },
  	            error: function (data) {
  	            	$.ligerDialog.alert("后台查询失败！");
  	            }
  	        });
       	  }
          }
       }
}
//----------------------------------------------------------------------------
//打印
function print(){
    var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
    var strBodyStyle = "<style>" + $("#pstyle").html() + "</style>";
    LODOP.PRINT_INIT("打印会议室请假单");
    LODOP.ADD_PRINT_HTM('12px', '10px', "750px", "100%", strBodyStyle+ $("#formObj").html());
    LODOP.SET_PRINT_PAGESIZE (1, 0, 0,"A4");
    //LODOP.PREVIEW();
    LODOP.PRINT();
 }
</script>
<style type="text/css" media="print" id="pstyle">
* {font-family:"宋体";font-size:15px;letter-spacing:normal;}
h1{font-family:宋体;font-size:6mm; text-align:center;margin:10px 0 0 0;}
table{ margin:-2 auto;width: 650px;}
table td{ height:40px}
.l-detail-table td, .l-detail-table {
    border-collapse: collapse;
    border: 1px solid black;
}
.l-detail-table {
    padding:0px;
    border:0px solid #CFE3F8;
    border-top:0px;
    border-left:0px;
    word-break:break-all;
    table-layout:fixed;
}
.check {
    width:720px;
}
.l-t-td-left{ text-align:center;}
.l-t-td-right{ padding-left:5px;}
#form-tab，#form-tab1{
 padding:5px;
    border:0px solid #CFE3F8;
    border-top:0px;
    border-left:0px;
    word-break:break-all;
    table-layout:fixed;
}

</style>
<style>
.l-t-td-right1 {
    padding: 0;
    margin: 0;
}

.l-t-td-right1 .l-text {
    background-image: none;
}

.l-t-td-right1 .l-text-wrapper {
    width: 100%;
    height: 90px;
}

.l-textarea .l-text-wrapper {
    width: 100%;
    height: 100%;
}

.l-textarea-onerow {
    height: 30px;
}

.l-textarea-onerow div {
    padding: 0;
}

.l-t-td-right1 .l-text {
    border: none;
}

.l-t-td-right1 .l-text.change, .l-t-td-right1 .l-radio-group-wrapper.change
    {
    background: url("../images/x-input.png") repeat-x;
}

.l-t-td-right1 .l-text input {
    height: 90px;
    padding-top: 0;
    line-height: 24px;
}

.l-t-td-right1 .l-radio-group-wrapper {
    height: 90px;
    padding-left: 5px;
}

.l-t-td-right1 textarea {
    height: 100%;
}

.l-textarea-onerow textarea {
    height: 12px;
    padding: 6px 5px;
    width: 98%
}

.l-t-td-right1 label {
    height: 90px;
    line-height: 24px;
    display: inline-block;
}

.l-t-td-right1 div.input, .l-td-right div.input {
    border: none;
    padding-left: 5px;
}

.l-t-td-right1 .input-warp div {
    height: 100%;
    line-height: 28px;
}
</style>
</head>
<body>
		<div class="navtab">
			<div title="申请表" lselected="true" >
			<form name="formObj" id="formObj" method="post"
		      getAction="oa/meetingReq/info/detailMeeting.do?id=${param.id}">
			  <h1 align="center" class="l-label" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">会&nbsp;议&nbsp;室&nbsp;申&nbsp;请&nbsp;表&nbsp; </h1><div style="height:2px">&nbsp;</div>
			  <input name="id" type="hidden" id="meeting_id"/> 
			  <table border="1" class="l-detail-table" id="form-tab" >
					<tr>
						<td rowspan="2" class="l-t-td-left">申请部门</td>
						<td rowspan="2" colspan="3" class="l-t-td-right">
						   <input type="text" ltype="text" name="sqDepartment" id="department" validate="required:true" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}" value="<%=curUser.getDepartment().getOrgName() %>" readonly="readonly" /></td>
						<td class="l-t-td-left">会议开始时间</td>						
						<td class="l-t-td-right" colspan="2"><input id="startTime" name="startTime" type="text" ltype='date'  validate="required:true" ligerui="{format:'yyyy-MM-dd hh:mm:ss'}" onchange="checkTime()"/></td>
						
					</tr>
					
					<tr>					
					    <td class="l-t-td-left">会议结束时间</td>
						<td class="l-t-td-right" colspan="2" colspan="4"><input id="endTime" name="endTime" type="text" ltype='date'  validate="required:true" ligerui="{format:'yyyy-MM-dd hh:mm:ss'}" onchange="checkTime()"/></td>
					</tr>
					<tr>
					  <td class="l-t-td-left" >会议名称</td>
						<td colspan="6"  class="l-t-td-right" ><input type="text" ltype="text"  name="name"  validate="{required:true,maxlength:200}"/></td>
					</tr>
					<tr>
						<td rowspan="2" class="l-t-td-left">会议室名称</td>
						<td rowspan="2" colspan="3" class="l-t-td-right"><input name="fkOaMeetingRoom" type="hidden" id="fkOaMeetingRoom" value="${param.roomId}"/> 
						<input id="roomCode" name="roomCode" type="text" ltype='text' readonly="readonly" value="" validate="{required:true,maxlength:50}" onclick="selectRoom()" 
						ligerui="{iconItems:[{icon:'trigger',click:function(val,e,srcObj){selectRoom()}}]}"/></td>
						<td class="l-t-td-left">是否制作会标</td>
						<td class="l-t-td-right" colspan="2"> <input type="radio" id="ifZzhb" name="ifZzhb" ltype="radioGroup" 
							ligerui="{value:'0',data: [ 
								{ text:'是', id:'1' },
								{ text:'否', id:'0' }
							]}"/></td>
					</tr>
					<tr>
					   <td class="l-t-td-left">是否制作欢迎标语</td>
						<td class="l-t-td-right" colspan="2"><input type="radio" id="ifHyby" name="ifHyby" ltype="radioGroup" 
							ligerui="{value:'0',data: [ 
								{ text:'是', id:'1' },
								{ text:'否', id:'0' }
							]}"/></td>
					</tr>
					<tr>
						<td class="l-t-td-left">参会人数</td>
						<td class="l-t-td-right" colspan="3"><input type="text" ltype="spinner" ligerui="{readonly:false,type:'int'}"  id="pnumber" name="pnumber" validate="{required:true}" onblur='yzpnumber()'/></td>
						<td class="l-t-td-left">是否需要制作座牌</td>
						<td class="l-t-td-right" colspan="2">
						
						<input type="radio" id="ifZzhyzp" name="ifZzhyzp" ltype="radioGroup" 
							ligerui="{value:'1',data: [ 
								{ text:'是', id:'1' },
								{ text:'否', id:'0' }
							]}"/></td>
					</tr>
					<tr>
						<td class="l-t-td-left">会议地点</td>
						<td class="l-t-td-right" colspan="3"><input type="text" ltype="text" id="hyAdress" name="hyAdress" validate="required:true" /></td>
						<td class="l-t-td-left">是否制作就餐座牌</td>
						<td class="l-t-td-right" colspan="2">
						<input type="radio" id="ifZzjczp" name="ifZzjczp" ltype="radioGroup" 
							ligerui="{value:'0',data: [ 
								{ text:'是', id:'1' },
								{ text:'否', id:'0' }
							]}"/></td>
					</tr>
					<tr>
						<td rowspan="2" class="l-t-td-left">会议室布置形式</td>
						<td rowspan="2" colspan="3" class="l-t-td-right"><u:combo ltype="radioGroup" name="hysbzxs" code="meeting_layout_type" attribute="initValue:'1'" validate="required:true"></u:combo> </td>
						<td class="l-t-td-left">是否摆鲜花</td>
						<td class="l-t-td-right" colspan="2">
						<input type="radio" id="ifFlowers" name="ifFlowers" ltype="radioGroup" 
							ligerui="{value:'0',data: [ 
								{ text:'是', id:'1' },
								{ text:'否', id:'0' }
							]}"/>
						</td>
					</tr>
					<tr>
					   
					    <td class="l-t-td-left">是否要水果</td>
						<td class="l-t-td-right" colspan="2">
 						
 						<input type="radio" id="ifFruits" name="ifFruits" ltype="radioGroup" 
							ligerui="{value:'0',data: [ 
								{ text:'是', id:'1' },
								{ text:'否', id:'0' }
							]}"/>
						</td>
					</tr>
					<tr>
					 <td class="l-t-td-left">需要办公室配合</br>的其它事项</td>
					 <td class="l-t-td-right"  colspan="6"><input type="text" ltype="text" name="officeOtherPz" value="提供茶水" validate="{required:true,maxlength:100}"/></td>
					</tr>
					<tr>
						<td class="l-t-td-left" >室主任意见</td>
						<td class="l-t-td-right"  colspan="3"><input type="text" ltype="text" name="szrYj"  disabled="disabled"  style="background:#DCDCDC"//></td>
						<td class="l-t-td-left" >办公室意见</td>
						<td class="l-t-td-right"  colspan="2"><input type="text" ltype="text"  name="officeYj" disabled="disabled"  style="background:#DCDCDC"//></td>
					</tr>
					<tr>
					  <td class="l-t-td-left" >分管院长意见</td>
						<td colspan="6"  class="l-t-td-right" ><input type="text" ltype="text"  name="fgyzYj" disabled="disabled"  style="background:#DCDCDC"//></td>
					</tr>
					<tr>
					   <td class="l-t-td-left" >院长意见</td>
						<td  colspan="6"   class="l-t-td-right"><input type="text" ltype="text"  name="yzYj" disabled="disabled"  style="background:#DCDCDC"//></td>
					</tr>
					<tr>
					   <td class="l-t-td-left"  >备注</td>
						<td class="l-t-td-right" colspan="6"><input type="text" ltype="text"  name="remarks" id="remark" validate="{maxlength:400}" /></td>
					</tr>
					 <tr>
					   <td class="l-t-td-left"  rowspan="2">注:</td>
					   <td colspan="6" rowspan="2" class="l-t-td-right" style="color: red;text-align:left"><h3>1.请附参会人员详细名单(姓名、职务)；如需做座牌请填写清楚要做座牌人员名单。</br>2.如需会后用餐请填接待申请单。</h3></td>
					 </tr>
			</table>
			<table  class="check" border="0" id = "form-tab1">
			 <tr>
                    <td width="560px">&nbsp;</td>
                    <td width="70px">承办人：</td>
                    <td width="140px" class="l-t-td-right"><input ltype='text' id="compere"  name="compere" type="text" value="<%=curUser.getDepartment().getOrgName() %>" readonly="readonly"/></td>
            </tr>
	    </table>
	    </form>
	  </div>	
	  <c:if test="${param.status=='detail' && param.notes_id!=''}">
			<div title="会议记录" id="notesDiv">
			  	<form name="form1" id="form1" method="post"
		      getAction="oa/meetingNotes/info/detail.do?id=${param.notes_id}">
				<table   border="1" cellpadding="3" cellspacing="0" class="l-detail-table" id="form-Notes-tab">
					<input name="id" type="hidden" id="notesid"/>
					<input name="fkroomid" type="hidden" id="fkroomid"/>
					<input name="fkreqid" type="hidden" id="fkreqid"/>
					<!-- <input type="hidden" name="meetingUser" id="meetingUser" />
					<input type="hidden" name="meetingOrg" id="meetingOrg" /> -->
					<tr>
						<td class="l-t-td-left">会议名称：</td>
						<td class="l-t-td-right">
						<input name="name" type="text" ltype='text' id="meetingName" validate="{required:true,maxlength:200}" onclick="selectReq()"/>
						</td>
						<td class="l-t-td-left">承办单位：</td>
						<td class="l-t-td-right">
						<input  name="department" id="department" type="text" ltype='text' validate="{required:true,maxlength:50}"/>
						</td>
					</tr>
					<tr>
				    	<td class="l-t-td-left">开始时间：</td>
						<td class="l-t-td-right"><input type="text" ltype='date' id="startTime"  name="startTime" validate="{required:true,maxlength:50,ltTo:'#endTime'}" ligerui="{format:'yyyy-MM-dd hh:mm'}" />
						</td>
						<td class="l-t-td-left">结束时间：</td>
						<td class="l-t-td-right"><input id="endTime" name="endTime" type="text" ltype='date'  validate="{required:true,maxlength:50,gtTo:'#startTime'}" ligerui="{format:'yyyy-MM-dd hh:mm'}" />
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">记录人：</td>
						<td class="l-t-td-right">
						<input type="hidden" name="personId" id="personId""/>
						<input name="personName" id="personName" type="text" 
							ltype='text' validate="{required:true,maxlength:50}" ligerUi="{disabled:true}" /></td>
						<td class="l-t-td-left">记录时间：</td>
						<td class="l-t-td-right">
						<input name="notesTime" type="text" ltype='date' validate="{required:true}" ligerUi="{format:'yyyy-MM-dd hh:mm'}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">会议内容：</td>
						<td colspan="3" class="l-t-td-right">
						   <textarea name="content" id="content" type="text" ltype='text' validate="{required:true,maxlength:1000}" rows="10"></textarea>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">备注：</td>
						<td colspan="3" class="l-t-td-right">
							<textArea name="remarks" type="text" ltype='text' validate="{maxlength:1000}"></textarea>
						</td>
					</tr>
				</table>
			  </form>
			 </div>
			</c:if>
			
			<div title="参会人员">
				<div id="userToolbar"></div>
				<div id="userGrid"></div>
			</div>
			
			<div title="参会单位">
				<div id="orgToolbar"></div>
				<div id="orgGrid"></div>
			</div>
		</div>
	
</body>
</html>

