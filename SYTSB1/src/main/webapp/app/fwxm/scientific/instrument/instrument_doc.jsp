<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<%
String realPath1 = request.getSession().getServletContext().getRealPath("upload");

System.out.print(">>>>>>>"+realPath1+"<<<<<<");
String[] id = realPath1.split("\\\\");
String realPath="";
for(int i=0;i<id.length;i++){
	if(StringUtil.isNotEmpty(realPath)){
	realPath = realPath +"/"+id[i];
	}else{
		realPath = id[0];
	}
}
%>
<!--  -->
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<%@include file="/k/kui-base-form.jsp"%>
<style type="text/css">
    .l-icon-exportDoc{background:url('k/kui/skins/icons/word.gif') no-repeat center;}
    .l-icon-printPreview{background:url('k/kui/skins/icons/search2.gif') no-repeat center;}
    .l-icon-fullScreen{background:url('k/kui/skins/icons/div-drag.gif') no-repeat center;}
</style>
<script src="pub/office/ntkoofficecontrol.js" type="text/javascript"></script>
<script src="pub/office/editor.js" type="text/javascript"></script>
<script type="text/javascript">
	var basepath = "${pageContext.request.contextPath}/";
	var toolBar;//工具条
	$(function(){
		$(".layout").ligerLayout({
			bottomHeight:30,
			space : 0
		});
	});
	
	//文档标签赋值
	function setBookMarkValue1(bookMarkName,inputValue){
		var bkmkObj = TANGER_OCX_OBJ.ActiveDocument.BookMarks(bookMarkName);
		if(!bkmkObj){
			return;
		}
		var saverange = bkmkObj.Range;
		saverange.Text = inputValue;
		TANGER_OCX_OBJ.ActiveDocument.Bookmarks.Add(bookMarkName,saverange);
	}
	
	function initPage(){
		initToolBar();
		createNtkoEditor("editor_container");
		TANGER_OCX_OBJ.SetReadOnly(true);
		TANGER_OCX_OBJ.Menubar = false;
		TANGER_OCX_OBJ.Statusbar = false;
		TANGER_OCX_OBJ.Toolbars = false;
		
			TANGER_OCX_OBJ.OpenFromURL($("base").attr("href") + "app/fwxm/scientific/instrument/word/yqxq.doc");		
		
		TANGER_OCX_OBJ.SetReadOnly(false,"");
		
			$.post("com/tjy2/instrumentDevice/getDetail.do",{id:"${param.id}"},function(res){
				if(res.success){
					TANGER_OCX_OBJ.SetReadOnly(false,"");
					var doc = TANGER_OCX_OBJ.ActiveDocument;
				/* if(doc.BookMarks.Exists("enter_date")){
				
					if(res.data.enterDate!=null){
						var enter_date = res.data.enterDate.substring(0,4)+"年"+res.data.enterDate.substring(5,7)+"月"+res.data.enterDate.substring(8,10)+"日";
						setBookMarkValue1("enter_date",enter_date);
					}
					
				}  */
				
				var pts = res.data.instrumentDeviceInfo;
				if(pts!=null){
					for (var j = 0; j < pts.length; j++) {
						var pt = res.data.instrumentDeviceInfo[j];
						if(j>0){
							 TANGER_OCX_OBJ.ActiveDocument.Tables(1).Cell(j+1,5).Select(); 
								//插入一行
								TANGER_OCX_OBJ.ActiveDocument.Application.Selection.InsertRowsBelow(1);
								//序号
								 if(pt.deviceName!=null){
								TANGER_OCX_OBJ.ActiveDocument.Tables(1).Cell(2+j,1).Range.InsertAfter(pt.deviceName);
								 }
								 if(pt.sum!=null){
									TANGER_OCX_OBJ.ActiveDocument.Tables(1).Cell(2+j,2).Range.InsertAfter(pt.sum);
								} 
								 if(pt.deviceModel!=null){
										TANGER_OCX_OBJ.ActiveDocument.Tables(1).Cell(2+j,3).Range.InsertAfter(pt.deviceModel);
									} 
								 if(pt.deviceManufacturer!=null){
										TANGER_OCX_OBJ.ActiveDocument.Tables(1).Cell(2+j,4).Range.InsertAfter(pt.deviceManufacturer);
									}
								 if(pt.money!=null){
										TANGER_OCX_OBJ.ActiveDocument.Tables(1).Cell(2+j,5).Range.InsertAfter(pt.money);
									}
								 if(pt.reason!=null){
										TANGER_OCX_OBJ.ActiveDocument.Tables(1).Cell(2+j,6).Range.InsertAfter(pt.reason);
									}
								 if(pt.channel!=null){
										TANGER_OCX_OBJ.ActiveDocument.Tables(1).Cell(2+j,7).Range.InsertAfter(pt.channel);
									}
								 if(pt.parameter!=null){
										TANGER_OCX_OBJ.ActiveDocument.Tables(1).Cell(2+j,8).Range.InsertAfter(pt.parameter);
									}
								 if(pt.remark!=null){
										TANGER_OCX_OBJ.ActiveDocument.Tables(1).Cell(2+j,9).Range.InsertAfter(pt.remark);
									}
							}
						
					}
				}
				/* if(doc.BookMarks.Exists("audit_date")){
					if(res.data.auditDate!=null){
						var audit_date = res.data.auditDate.substring(0,4)+"年"+res.data.auditDate.substring(5,7)+"月"+res.data.auditDate.substring(8,10)+"日";
						setBookMarkValue1("audit_date",audit_date);
					}
					
				} 
				if(doc.BookMarks.Exists("sign_date")){
					if(res.data.signDate!=null){
						var sign_date = res.data.signDate.substring(0,4)+"年"+res.data.sigDnate.substring(5,7)+"月"+res.data.signDate.substring(8,10)+"日";
						setBookMarkValue1("sign_date",sign_date);
					}
					
				}  */
					if(doc.BookMarks.Exists("deviceName")){
						if(res.data.instrumentDeviceInfo[0].deviceName!=null){
							setBookMarkValue1("deviceName",res.data.instrumentDeviceInfo[0].deviceName);
						}	
					} 
					if(doc.BookMarks.Exists("sum")){
						if(res.data.instrumentDeviceInfo[0].sum!=null){
							setBookMarkValue1("sum",res.data.instrumentDeviceInfo[0].sum);
						}	
					} 
					if(doc.BookMarks.Exists("deviceModel")){
						if(res.data.instrumentDeviceInfo[0].deviceModel!=null){
							setBookMarkValue1("deviceModel",res.data.instrumentDeviceInfo[0].deviceModel);
						}	
					} 
					if(doc.BookMarks.Exists("deviceManufacturer")){
						if(res.data.instrumentDeviceInfo[0].deviceManufacturer!=null){
							setBookMarkValue1("deviceManufacturer",res.data.instrumentDeviceInfo[0].deviceManufacturer);
						}	
					} 
					if(doc.BookMarks.Exists("money")){
						if(res.data.instrumentDeviceInfo[0].money!=null){
							setBookMarkValue1("money",res.data.instrumentDeviceInfo[0].money);
						}	
					} 
					if(doc.BookMarks.Exists("reason")){
						if(res.data.instrumentDeviceInfo[0].reason!=null){
							setBookMarkValue1("reason",res.data.instrumentDeviceInfo[0].reason);
						}	
					} 
					if(doc.BookMarks.Exists("channel")){
						if(res.data.instrumentDeviceInfo[0].channel!=null){
							setBookMarkValue1("channel",res.data.instrumentDeviceInfo[0].channel);
						}
					} 
					if(doc.BookMarks.Exists("parameter")){
						if(res.data.instrumentDeviceInfo[0].parameter!=null){
							setBookMarkValue1("parameter",res.data.instrumentDeviceInfo[0].parameter);
						}
					} 
					if(doc.BookMarks.Exists("remark")){
						if(res.data.instrumentDeviceInfo[0].remark!=null){
							setBookMarkValue1("remark",res.data.instrumentDeviceInfo[0].remark);
						}
					} 
					if(doc.BookMarks.Exists("departmentName")){
						if(res.data.departmentName!=null){
							setBookMarkValue1("departmentName",res.data.departmentName);
						}
					} 
					if(doc.BookMarks.Exists("headMan")){
						if(res.data.headMan!=null){
							setBookMarkValue1("headMan",res.data.headMan);
						}
					} 
					if(doc.BookMarks.Exists("createDate")){
						if(res.data.createDate!=null){
							var sign_date = res.data.createDate.substring(0,4)+"年"+res.data.createDate.substring(5,7)+"月"+res.data.createDate.substring(8,10)+"日";
							setBookMarkValue1("createDate",sign_date);
						}
						
					}
					 
				}
			}) 
			
		
		 
		
	}
	//下方按钮
  	function initToolBar(){
    	var saveBtn;
    	var closeBtn;
    	var printBtn;
    	var printPreviewBtn;
    	var setLayoutBtn;
    	var fullScreenBtn;
    	var subBtn;
    	var backBtn;
    
		closeBtn={
				id: "close",
				text: "关闭",
				icon:"close",
				click: function(){
				    api.close();
					return true;
				}
		};
		subBtn={
				
				id: "sub",
				text: "审核通过提交批准",
				icon:"submit",
				click: function(){
					$("body").hide();
					top.$.dialog({
						width : 500, 
						height : 200, 
						lock : true, 
						title:"审核通过提交批准",
						parent:api,
						content: 'url:app/tzsb/quality/modify/modify_audit.jsp?status=add&id=${param.id}&step=1&nextStep=2',
						data : {"window" : window},
						close:function(){
							api.close();
						}
						
					});
				}
		};
	sub2Btn={
				
				id: "sub",
				text: "批准通过",
				icon:"submit",
				click: function(){
					$("body").hide();
					top.$.dialog({
						width : 500, 
						height : 200, 
						lock : true, 
						title:"批准通过",
						parent:api,
						content: 'url:app/tzsb/quality/modify/modify_audit.jsp?status=add&id=${param.id}&step=2&nextStep=3',
						data : {"window" : window},
						close:function(){
							api.close();
						}
						
					});
				}
		};
	sub3Btn={
			
			id: "sub",
			text: "最终确认",
			icon:"submit",
			click: function(){
				$("body").hide();
				top.$.dialog({
					width : 500, 
					height : 200, 
					lock : true, 
					title:"批准通过",
					parent:api,
					content: 'url:app/tzsb/quality/modify/finalmod.jsp?status=add&id=${param.id}&step=2&nextStep=3',
					data : {"window" : window},
					close:function(){
						api.close();
					}
					
				});
			}
	};
	backBtn={
				
				id: "back",
				text: "退回",
				icon:"back",
				click: function(){
					var check_type = "${param.check_type}";
					var step="1";
					if("02"==check_type){
						step="6";
					}
				
						 if (confirm( '确定退回报告录入？')) {
								$.get("inspectionInfoAction/back.do?infoIds=${param.id}&step=0"+step,function(res){
								if(res.success){
									top.$.notice("退回成功！");
									api.close();
								}
							})
						 }
					
				}
		};

		printBtn={
				id: "print",
				text: "打印",
				icon:"print",
				click: function(){
					doPrint();
					savePrint();
				}
		 };
		 printPreviewBtn={
				id: "printPreview",
				text: "打印预览",
				icon:"preview",
				click: function(){
					printPreview();
					return true;
				}
		 };

		 fullScreenBtn={
				id: "fullScreen",
				text: "全屏",
				icon:"provide",
				click: function(){
					fullScreen();
					return true;
				}
		 };
		 saveBtn = {
					id : "save",
					text : "保存",
					icon:"save",
					click : function() {
						saveCurrentDocument();
						return false;
					}
		};
		var itemArr=new Array();
		
		//itemArr.push(setLayoutBtn);
		/* if("${param.status}"!="detail"){
		 itemArr.push(saveBtn);
		 } */
		var step = "${param.step}";
		
		
		itemArr.push(printBtn);
		itemArr.push(printPreviewBtn);

	   	//itemArr.push(printBtn);
	   	//itemArr.push(backBtn);
	   	//
	   	itemArr.push(fullScreenBtn);
	   	//itemArr.push(printPreviewBtn);
	   	itemArr.push(closeBtn);
	    toolBar=$("#toolbar").ligerButton({
			items:itemArr
		});
    }
	
  	function saveCurrentDocument() {
  		var folder = "quality/management-review";
		var response = TANGER_OCX_OBJ.SaveToURL("${pageContext.request.contextPath}/fileupload/fileUp.do?attType=0&id=${param.id}&folder="+folder+"&fname=${param.id}","docFile", "","${param.id}.doc");
		var data = $.parseJSON(response);
		if(data.success){
			 
			top.$.notice("文件保存成功！");
			api.close(); 
			 
		}
		else{
			alert("文件保存失败！");
		}
		
	}
  	
  	function savePrint(){
  		$.ajax({
			url : "tzsbInspectionFlowAction/saveIsPrintNotice.do",
			type : "POST",
			async:false,
			datatype : "json",
			data : {
				id:"${param.id}"
			},
			success : function(res) {
		    	if(res.success){
		    		api.close();
		    	}else{
		    		$.ligerDialog.alert("保存打印结果失败！", "提示");
		    	}
		    }
    	})
  	}
	
</script>
</head>
<body onload="initPage();">
<div class="layout">
	<div position="center" id="editor_container" style="width:100%;height:100%"></div>
	<div position="bottom" style="height: 50px;">
		<div class="div1" id="toolbar" style="padding:1px;text-align:right;"></div>
	</div>
</div>
<iframe id="export_doc_iframe" style="display:none;">
</iframe>
</body>
</html>