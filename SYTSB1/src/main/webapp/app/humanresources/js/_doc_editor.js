
var editorToolbars = {
	saveBtn : {
		id : "save",
		text : "保存",
		icon : "save",
		click : saveCurrentDocument
	},
	closeBtn : {
		id : "close",
		text : "关闭",
		icon : "close",
		click : function() {
			api.close();
		}
	},
	printBtn : {
		id : "print",
		text : "打印",
		icon : "print",
		click : doPrint
	},
	printPreviewBtn : {
		id : "printPreview",
		text : "打印预览",
		icon : "preview",
		click : printPreview
	},
	setLayoutBtn : {
		id : "printSet",
		text : "页面设置",
		icon : "pager",
		click : setLayout
	},
	fullScreenBtn : {
		id : "fullScreen",
		text : "全屏",
		icon : "distribution",
		click : fullScreen
	},
	reviseBtn : {
		id : "revise",
		text : "清稿",
		icon : "check",
		click : acceptRevisions
	},
	annotationBtn : {
		id : "annotation",
		text : "批注",
		icon : "modify",
		click : doHandSign
	},
	readonyBtn : {
		id : "readony",
		text : "设为只读",
        img : 'app/public/images/oa/dacy-16.png',
		click : function(){setReadOnly(true);}
	},
	redBtn : {
		id : "red",
		text : "套红",
        img : 'app/public/images/oa/thmb-16.png',
		click : useTempleteToDocument
	},
	stampBtn : {
		id : "stamp",
		text : "盖章",
        img : 'app/public/images/oa/yyrz-16.png',
		click : chooseSealAddToDocument
	},
	revStampBtn : {
		id : "revStamp",
		text : "撤销盖章",
		icon : "seal",
		click : function() {
			$("#revStamp").ligerGetTextBoxManager().setDisabled();
			var wordObj = document.getElementById("TANGER_OCX_OBJ");
			delWordSeal(wordObj.ActiveDocument);
			api.data.pwindow.setSealSuccess(false);
			$("#stamp").ligerGetTextBoxManager().setEnabled();
		}
	},
	exportDoc : {
		id : "exportDoc",
		text : "导出为WORD",
		icon : "word",
		click : function() {
			if ($.kh.isNull(api.data.word)) {
				alert("该文件不存在，无法导出！");
			} else {
				window.location = $("base").attr("href") + "fileupload/download.do?id=" + api.data.word;
			}
		}
	},
	exportPdf : {
		id : "exportPdf",
		text : "导出为PDF",
		img : "k/kui/images/file-type/16/pdf.png",
		click : function() {
			if ($.kh.isNull(api.data.pdf)) {
				alert("该文件不存在，无法导出为PDF！");
			} else {
				window.location = $("base").attr("href") + "fileupload/download.do?id=" + api.data.pdf;
			}
		}
	}
};

var pageCommondCall = {
	addSeal: chooseSealAddToDocument,
	useRed: useTempleteToDocument
};

function initToolbar(options) {
	var tbars = [];
	tbars.push(editorToolbars.fullScreenBtn);
	if (options.revise)
		tbars.push(editorToolbars.reviseBtn);
	if (options.eword)
		tbars.push(editorToolbars.exportDoc);
	if (options.epdf)
		tbars.push(editorToolbars.exportPdf);
	if (options.red)
		tbars.push(editorToolbars.redBtn);
	if (options.seal){
		//如果是盖章环节，要检查文档是否已盖章
		tbars.push(editorToolbars.readonyBtn);
		tbars.push(editorToolbars.stampBtn);
	}
	if (options.print) {
		tbars.push(editorToolbars.printBtn);
		tbars.push(editorToolbars.printPreviewBtn);
	}
	if (options.approve)
		tbars.push(editorToolbars.annotationBtn);
	if (options.layout)
		tbars.push(editorToolbars.setLayoutBtn);
	if (options.edit)
		tbars.push(editorToolbars.saveBtn);
	tbars.push(editorToolbars.closeBtn);
	$("#toolbar").ligerButton({
		items : tbars
	});
}

/**
 * 编辑器视图初始化,根据参数status指定状态,无论何种状态菜单栏功能禁用
 */
function initView(status) {
	// 编辑器功能选项设置
	TANGER_OCX_OBJ.FileNew = false;
	TANGER_OCX_OBJ.FileOpen = false;
	TANGER_OCX_OBJ.FileSave = false;
	TANGER_OCX_OBJ.FileSaveAs = false;
	TANGER_OCX_OBJ.FileClose = false;
	TANGER_OCX_OBJ.Titlebar = false;
	TANGER_OCX_OBJ.Menubar = false;
	// 审批状态
	if (status == "approve") {
		setReadOnly(false);
		TANGER_OCX_OBJ.Statusbar = true;
		TANGER_OCX_OBJ.Toolbars = true;
		TANGER_OCX_OBJ.Menubar = true;
		// 保留痕迹
		showRevisions(true);
		setMarkModify(true);
		//setEditor(userName);
	}
	// 起草
	else if (status == 'draft') {
		TANGER_OCX_OBJ.SetReadOnly(false);
		TANGER_OCX_OBJ.Toolbars = true;
		TANGER_OCX_OBJ.Statusbar = true;
		TANGER_OCX_OBJ.FileOpen = true;
		TANGER_OCX_OBJ.Menubar = true;
	} 
	// 非审批处理
	else if(status == "handle") {
		TANGER_OCX_OBJ.SetReadOnly(false);
		TANGER_OCX_OBJ.Statusbar = false;
		TANGER_OCX_OBJ.Toolbars = true;
		TANGER_OCX_OBJ.Menubar = true;
		showRevisions(false);
		setMarkModify(false);
	}
	// 其他状态只读
	else{
		TANGER_OCX_OBJ.SetReadOnly(true);
		TANGER_OCX_OBJ.Statusbar = false;
		TANGER_OCX_OBJ.Toolbars = false;
	}
	// 事件重载，全屏模式下的替代方案
	setNtkoAlert();
}

/**
 * 盖章前选择印章
 */
function chooseSealAddToDocument(){
	$.getJSON("oa/seal/userSeal.do", function(response){
		if(response.success){
			if(!response.data || response.data.length == 0){
				alert("找不到可用的印章！\n请联系系统管理人员或者技术支持人员获得帮助。");
				return;
			}
			else if(response.data.length == 1){
				_addDocSeal(response.data[0].sealFile,response.data[0].id);
			}else{
				top.winOpen({
					width: 450,
					height: 300,
					lock: true,
					parent: api,
					title: "选择印章",
					content: "url:zjpt/oa/official_docs/issue/func_select_seal.jsp",
					data: {
						seals: response.data,
						callback: function(sealFile,sealId){
							_addDocSeal(sealFile,sealId);
						}
					}
				});
			}
		}else{
			alert("获取用户印章失败！\n请联系系统管理人员或者技术支持人员获得帮助。");
		}
	});
}

/**
 * 执行盖章
 * @param sfid 印章文件id
 */
function _addDocSeal(sfid,sealId){
	// 接受所有修订
	acceptRevisions();
	// 将印章盖上
	doSignSeal(api.data.user.name,$("base").attr("href") + "fileupload/download.do?id=" + sfid);
	//doSignSealPic(api.data.user.name,$("base").attr("href") + "fileupload/download.do?id=" + sfid, api.data.issuedUnit);
    //禁用盖章按钮
	
	// 成功后回调
	if(api.data.addSealCallback)
		api.data.addSealCallback(sealId);
}

/**
 * 执行套红
 * 
 * 套红需要的模板文件id和书签值从api.data.useTemplete中获取。
 */
function useTempleteToDocument(){
	if(api.data.getTempletes){
		api.data.getTempletes(function(datas){
			showRevisions(false);
			useTemplete($("base").attr("href") + "fileupload/download.do?id=" + datas.fid, datas.bodyMark, datas.docInfo);
			//成功回调
			if(api.data.useTempleteCallback)
				api.data.useTempleteCallback();
		});
	}else{
		alert("没有套红数据，无法执行操作！");
	}
}