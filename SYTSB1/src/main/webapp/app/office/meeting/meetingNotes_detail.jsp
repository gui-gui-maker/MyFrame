<%@page import="com.khnt.utils.DateUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
String status=request.getParameter("status");
String reqId=request.getParameter("reqId");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload1/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	//jQuery页面载入事件

	var beanData;
	var userGrid;
	var orgGrid;
	var status="<%=status%>";
	$(function() {
		
		//附件执行上传
		
    	var receiptUploadConfig = {
    			fileSize : "10mb",//文件大小限制
    			businessId : "",//业务ID
    			buttonId : "receiptfilesBtn",//上传按钮ID
    			container : "receiptfilesDIV",//上传控件容器ID
    			title : "请选择照片",//文件选择框提示
    			extName : "jpg,gif,jpeg,png,bmp,doc,docx,xls,xlsx,txt",//文件扩展名限制
    			saveDB : true,//是否往数据库写信息
    			attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
    			fileNum : 5,//限制上传文件数目
    			callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				showAttachFile(files);
    			}
    		};
		var receiptUploader= new KHFileUploader(receiptUploadConfig);
		//配置资源选择器
		$("#formObj").initForm({
			
			toolbar : [ 
			            {text : '保存',id : 'save',icon : 'save',click : save}, 
			            {text : '关闭',id : 'close',icon : 'cancel',click : function() {api.close();}}
			          ],
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
				   $("#userGrid").transform("detail");
		                $.post("oa/meetingReq/info/getUser.do?reqId=${param.reqId}", "", function(resp) {
					if (resp.success) {
						userGrid.loadData({Rows : resp.Rows});
					}else{
						$.ligerDialog.error("加载参会人信息失败！");
					}
				});
				$("#orgGrid").transform("detail");
		                $.post("oa/meetingReq/info/getOrg.do?reqId=${param.reqId}", "", function(resp) {
					if (resp.success) {
						orgGrid.loadData({Rows : resp.Rows});
					}else{
						$.ligerDialog.error("加载参会单位信息失败！");
					}
				});
				   showAttachFile(response.files);
				}
				else {
					$.ligerDialog.error("获取数据错误!");
					return;
				}
			} 
		});
		//获取会议信息
		//getMeetingNotes();
		
		initGrid();
		
		try{
			var reqId="<%=reqId%>";
			if(reqId!=null){
				getMeetingReqInfo(reqId);
			}
		}
		catch(e){
			
		}
	});
	
	function getMeetingNotes(){
		if("${param.id}"!=null&&"${param.id}"!=""){
			$.getJSON("oa/meetingNotes/info/detailMeetingNotes.do?id=${param.id}", function(response) {
				 if(response.success){
				   beanData = response.data; 
					//回填表格数据
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
					$.ligerDialog.error("获取会议记录失败!"+response.msg);
					return;
				} 
			});
		}
	}
	
	//初始化参会人员表格
	function initGrid(){
		var userIdNode="id";
		var userNameNode="name";
		var userTelNode="mobileTel";
		//初始化参会单位表格
		var orgIdNode="id";
		var orgNameNode="orgName";
		var orgTelNode="tellphone";
		//定义表格工具条
		if(status!="detail"){
			$("#userToolbar").ligerToolBar({
				items : [
						{id:'useradd',text : "添加",icon : "add",disabled: true,
						 click : function() {
							 selectUnitOrUser(1,1,"","",function(callbackData) {
								    if(callbackData.code){
								   		var temp = callbackData.code.split(",");
								   		for(var i in temp){
								   			$("body").mask("加载中...")
								   			$.getJSON("rbac/user/detail.do?id="+temp[i],function(res){
								   				$("body").unmask();
								   				if(res.success){
								   					var id= res.data.id;
								   					//var name = callbackData.name.split(",")[i];
								   					var array = {};
								   					array["id"]= id;
								   					array["name"] = res.data.name;
								   					if(res.data.employee){
								   						var tel=res.data.employee.officeTel;
									   					array["officeTel"] = tel;
								   					}
								   					userGrid.addRow(array);
								   				}
								   			})
								   			
								   		}
								    }
								});
							}
						},
						{text : "删除",icon : "delete",id:'userdel',disabled:true,click : function() {
							var selectRowObj = userGrid.getCheckedRows();
							for(var i in selectRowObj)
								userGrid.deleteRow(selectRowObj[i]);
							}
						} 
						]
			});
			$("#orgToolbar").ligerToolBar({
								items : [
										{id:'orgadd',disabled:true,text : "添加",icon : "add",click : function() {
											selectUnitOrUser(0,1,"","",function(callbackData) {
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
											   			})
											   			
											   		}
											    }
											});
											}
										},
										{id:'orgdel',disabled:true,text : "删除",icon : "delete",click : function() {
											var selectRowObj = orgGrid.getCheckedRows();
											for(var i in selectRowObj)
												orgGrid.deleteRow(selectRowObj[i]);
											}
										}
										]
							});
		}
		
		userGrid = $("#userGrid").ligerGrid({
			columns : [ 
			            {name : userIdNode,hide : true},
			            {display : '姓名',name : userNameNode,align : 'left'}, 
			            {display : '联系电话',name : userTelNode,align : 'left'} 
			          ],
            width : '99%',
			data:{Rows:[]},
			title : '',
			frozen :false,
			rownumbers:true,
			usePager: true,
			page: 1,
	        pageSize: 10,
	        pageSizeOptions: [10,15],
			checkbox : "${param.status}"=='detail'?false:true
			
		});
		
		orgGrid = $("#orgGrid").ligerGrid({
			columns : [ 
			            {name : orgIdNode,hide : true}, 
			            {display : '单位名称',name : orgNameNode,align : 'left'},
						{display : '联系电话',name : orgTelNode,align : 'left'}
			          ],
	        width : '99%',
			data:{Rows:[]},
			title : '',
			frozen :false,
			rownumbers:true,
			usePager: true,
			page: 1,
	        pageSize: 10,
	        pageSizeOptions: [10,15],
			checkbox : "${param.status}"=='detail'?false:true
		});
	}

	function spiltStr(data,idNode,nameNode,telNode) {
		var reg=",";
		var idArr;
		var telArr;
		var nameArr;
		if (data[idNode]) {
			idArr = data[idNode].split(reg);
		}
		if (data[telNode]) {
			telArr = data[telNode].split(reg);
		}
		if (data[nameNode]) {
			nameArr = data[nameNode].split(reg);
		}
		var len = idArr.length;
		var newArr = new Array();
		for ( var i = 0; i < len; i++) {
			var idVal;
			var nameVal;
			var telVal;
			if (idArr) {
				id = idArr[i];
			}
			if (nameArr) {
				name = nameArr[i];
			}
			if (telArr) {
				tel = telArr[i];
				if(tel=="undefined"){tel="";}
		     }
			var obj= new Object();
			obj[idNode]=id;
			obj[nameNode]=name;
			obj[telNode]=tel
			newArr.push(obj);
		}
		return newArr;
	}
	var notes_id;
	//保存数据
	function save() {
		var id="${param.id}";
	
		if ($("#formObj").validate().form()) {
			    //取参会人员id
			   /*  userGrid.endEdit();
				orgGrid.endEdit();
			    var userId="";
			    var orgId="";
				var userIds=userGrid.getData();
				for(var i=0;i<userIds.length;i++){
					userId+=(i==0?"":",")+userIds[i].id;
				}
				var orgIds=orgGrid.getData();
				for(var i=0;i<orgIds.length;i++){
					orgId+=(i==0?"":",")+orgIds[i].id;
				} */
			    
				//将参会单位和人员id存放到相应的隐藏框中
				/* $("#meetingUser").val(userId);
				$("#meetingOrg").val(orgId);
				 */
				var data = $("#form-table").getValues();
				$.ajax({
					url : "oa/meetingNotes/info/saveMeetingNotes.do?id="+id,
					type : "POST",
					datatype : "json",
					contentType : "application/json; charset=utf-8",
					data : $.ligerui.toJSON(data),
					success : function(data, stats) {
						if (data["success"]) {
							top.$.notice("保存成功！");
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
	
	//选择会议申请信息
	var content;
	function selectReq() {
		var title = "会议选择";
		var url = "url:app/office/meeting/selectReq_list.jsp";
		var width = 700;
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
					var reqId=datas.reqid;
					content=datas.content;
					//取参会人员及单位信息及会议基本信息
					getMeetingReqInfo(reqId);
					return true;
				} else {
					return false;
				}
			}
		});
	}
	
	function getMeetingReqInfo(reqId){
		$.ajax({
			url: "oa/meetingReq/info/detail.do?id="+reqId, 
			type: "POST",
			async: false,
			success: function(callbackData){
				var data=callbackData.data;
					$("#fkreqid").val(data.id);
					$("#meetingName").val(data.name);
					$("#fkroomid").val(data.fkOaMeetingRoom);
					$("#department").val(data.sqDepartment);
					
					var startTimeStr=data.startTime;
					if(startTimeStr!=""){
						startTimeStr=startTimeStr.substring(0,startTimeStr.length-3);
					}
					var endTimeStr=data.endTime;
					if(endTimeStr!=""){
						endTimeStr=endTimeStr.substring(0,endTimeStr.length-3);
					}
					$("#startTime").val(startTimeStr);
					$("#endTime").val(endTimeStr);
					$("#userGrid").transform("detail");
	                $.post("oa/meetingReq/info/getUser.do?reqId="+reqId, "", function(resp) {
				if (resp.success) {
					userGrid.loadData({Rows : resp.Rows});
				}else{
					$.ligerDialog.error("加载参会人信息失败！");
				}
			});
			$("#orgGrid").transform("detail");
	                $.post("oa/meetingReq/info/getOrg.do?reqId="+reqId, "", function(resp) {
				if (resp.success) {
					orgGrid.loadData({Rows : resp.Rows});
				}else{
					$.ligerDialog.error("加载参会单位信息失败！");
				}
			});
				   /* var userData=data.lUser;
				   if(userData.length>0&&userGrid){
					   userGrid.loadData({Rows:userData});
				   }
				   var orgData=data.lOrg;
				   if(orgData.length>0&&orgGrid){
					   orgGrid.loadData({Rows:orgData});
				   } */
		    }
		});
	}
	 //显示附件文件
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
		$("#uploadId").val(attachId);
	}
</script>

</head>
<body>
	<div class="navtab">
		<div title="会议基本信息" lselected="true">
	   <form name="formObj" id="formObj" method="post"
		getAction="oa/meetingNotes/info/detailMeetingNotes.do?id=${param.id}">
		<table border="1" cellpadding="3" cellspacing="0" id="form-table" class="l-detail-table">
			<input name="id" type="hidden" id="notesid"/>
			<input name="fkroomid" type="hidden" id="fkroomid"/>
			<input name="fkreqid" type="hidden" id="fkreqid"/>
			<tr>
				<td class="l-t-td-left">会议标题：</td>
				<td class="l-t-td-right">
				<input id="meetingName" name="name" type="text" ltype='text' readonly="readonly" validate="{required:true,maxlength:200}" onclick="selectReq()" 
				ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){selectReq()}}]}"/>
				<td class="l-t-td-left">申请部门：</td>
				<td class="l-t-td-right"><input  name="department" id="department" type="text" ligerui="{disabled:true}"
					ltype='text' validate="{required:true,maxlength:50}"/></td>
			</tr>
			<tr>
		    	<td class="l-t-td-left">开始时间：</td>
				<td class="l-t-td-right">
				<input type="text" ltype='date' name="startTime" id="startTime" validate="{required:true,maxlength:50}" readonly ligerui="{disabled:true,format:'yyyy-MM-dd hh:mm'}"/>
				</td>
				<td class="l-t-td-left">结束时间：</td>
				<td class="l-t-td-right">
				<input name="endTime" id="endTime" type="text" ltype='date' validate="{required:true,maxlength:50}" ligerui="{disabled:true,format:'yyyy-MM-dd hh:mm'}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">记录人：</td>
				<td class="l-t-td-right">
				<input type="hidden" name="personId" id="personId" value="<sec:authentication property="principal.id" />"/>
				<input id="personName" name="personName" type="text" value="<sec:authentication property="principal.name" />"
					ltype='text' validate="{required:true,maxlength:50}" ligerUi="{disabled:true}" validate="{required:true}"/>
				</td>
				<td class="l-t-td-left">记录时间：</td>
				<td class="l-t-td-right"><input name="notesTime" type="text" ltype='date' validate="{required:false}" ligerui="{format:'yyyy-MM-dd hh:mm'}" value='<%=DateUtil.getCurrentDateTime()%>'/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">会议内容：</td>
				<td colspan="3" class="l-t-td-right">
				   <textarea name="content" type="text"
					ltype='text' validate="{required:true,maxlength:4000}" rows="3"></textarea>
				</td>
			</tr>
			
			<tr>
				<td class="l-t-td-left">备注：</td>
				<td colspan="3" class="l-t-td-right">
				<textArea name="remarks" id="remarks" type="text"
					ltype='text' validate="{maxlength:4000}" rows="2"></textarea>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">附件：</td>
				<td colspan="3" class="l-t-td-right" id="receiptfilesDIV">
						<input name="uploadIds" id="uploadId" type="hidden"/>
						<a class="l-button3" id="receiptfilesBtn">选择文件</a>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left"></td>
				<td colspan="3" class="l-t-td-right" id="receiptfilesDIV">
					<div class="l-upload-ok-list"><ul id="receiptfiles"></ul></div>
				</td>
			</tr>
		   </table>
         </form>
        </div>
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

