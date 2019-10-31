/**
 * 编辑器视图初始化,根据参数status指定状态,无论何种状态菜单栏功能禁用
 */
function initNtkoEditorView() {
	// 编辑器功能选项设置
	TANGER_OCX_OBJ.FileNew = false;
	TANGER_OCX_OBJ.FileOpen = false;
	TANGER_OCX_OBJ.FileSave = false;
	TANGER_OCX_OBJ.FileSaveAs = false;
	TANGER_OCX_OBJ.FileClose = false;
	TANGER_OCX_OBJ.Titlebar = false;
	TANGER_OCX_OBJ.Menubar = false;
	if (api.data.status == "edit") {
		setReadOnly(false);
		TANGER_OCX_OBJ.Statusbar = true;
		TANGER_OCX_OBJ.Toolbars = true;
		// 保留痕迹
		try{
    		showRevisions(true);
    		setMarkModify(true);
	    }catch(e){}	}
	// 其他状态只读
	else{
		TANGER_OCX_OBJ.SetReadOnly(true);
		TANGER_OCX_OBJ.Statusbar = false;
		TANGER_OCX_OBJ.Toolbars = false;
	}
	// 事件重载，全屏模式下的替代方案
	setNtkoAlert();
}
var editorToolbars = {
	saveBtn : {
		id : "save",
		text : "保存",
		icon : "save",
		click : saveDocument
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
	closeBtn : {
		id : "close",
		text : "关闭",
		icon : "close",
		click : function() {
			api.close();
		}
	}
};
function initToolbar() {
	var tbars = [];
    tbars.push(editorToolbars.fullScreenBtn);
    tbars.push(editorToolbars.printBtn);
	tbars.push(editorToolbars.printPreviewBtn);
    if(api.data.status=="edit"){
        tbars.push(editorToolbars.setLayoutBtn);
        tbars.push(editorToolbars.saveBtn);
    }
	tbars.push(editorToolbars.closeBtn);
	$("#toolbar").ligerButton({
		items : tbars
	});
}
