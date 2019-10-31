/**
 * 创建文档对象
 * 
 * @param  containerId 对象容器Id
 */
function createNtkoEditor(containerId,cuser) {
	initNtkoEditorContainer(containerId,cuser);
	window.TANGER_OCX_OBJ = document.getElementById("TANGER_OCX");
}

// 全屏编辑
function fullScreen() {
	TANGER_OCX_OBJ.FullScreenMode = !TANGER_OCX_OBJ.FullScreenMode;
}

// 设置编辑用户
function setEditor(cuser) {
	if(TANGER_OCX_OBJ.doctype==1){
		if(15==TANGER_OCX_OBJ.getOfficeVer()){
			//如果是OFFICE2013需要启用此属性值
			TANGER_OCX_OBJ.ActiveDocument.Application.Options.UseLocalUserInfo=true;
		}
		TANGER_OCX_OBJ.ActiveDocument.Application.UserName = cuser;
	}
}

// 设置是否只读
function setReadOnly(boolVal) {
	TANGER_OCX_OBJ.SetReadOnly(boolVal);
}

// 允许或禁止显示修订工具栏和工具菜单
function enableReviewBar(boolvalue) {
	TANGER_OCX_OBJ.ActiveDocument.CommandBars.Item("Reviewing").Enabled = boolvalue;
	TANGER_OCX_OBJ.ActiveDocument.CommandBars.Item("Track Changes").Enabled = boolvalue;
	TANGER_OCX_OBJ.IsShowToolMenu = boolvalue;
}

// 打开或者关闭修订模式
function setReviewMode(boolvalue) {
	TANGER_OCX_OBJ.ActiveDocument.TrackRevisions = boolvalue;
}

// 设置痕迹保留状态
function setMarkModify(boolvalue) {
	setReviewMode(boolvalue);
	enableReviewBar(!boolvalue);
}

// 显示/隐藏修订
function showRevisions(boolvalue) {
	TANGER_OCX_OBJ.ActiveDocument.ShowRevisions = boolvalue;
	if (boolvalue) {
		TANGER_OCX_OBJ.ActiveDocument.Application.ActiveWindow.View.ShowRevisionsAndComments = true;
		TANGER_OCX_OBJ.ActiveDocument.Application.ActiveWindow.View.RevisionsView = 1;
	}
}

// 接受修订记录
function acceptRevisions() {
	try {
		TANGER_OCX_OBJ.ActiveDocument.AcceptAllRevisions();
		TANGER_OCX_OBJ.ActiveDocument.Application.ActiveWindow.View.ShowRevisionsAndComments = false;
		TANGER_OCX_OBJ.ActiveDocument.Application.ActiveWindow.View.RevisionsView = 0;
	} catch (e) {
		alert("不能进行该操作：" + e.number + ":" + e.description);
	}
}

// 拒绝接受修订
function rejectRevisions() {
	TANGER_OCX_OBJ.ActiveDocument.RejectAllRevisions();
}

// 是否可以拷贝
function enableContentCopy(boolVal) {
	TANGER_OCX_OBJ.IsStrictNoCopy = boolVal;
}

// 批注功能
function doHandSign(name) {
	try {
		TANGER_OCX_OBJ.DoHandSign2(name, "", 0, 0, 0);
	} catch (e) {
		alert("不能进行该操作：" + e.number + ":" + e.description);
	}
}

// 设置文字到书签位置
function setBookMarkValue(inputValue, BookMarkName) {
	var bkmkObj = TANGER_OCX_OBJ.ActiveDocument.BookMarks(BookMarkName);
	if (!bkmkObj)
		return;
	var saverange = bkmkObj.Range;
	saverange.Text = inputValue;
	TANGER_OCX_OBJ.ActiveDocument.Bookmarks.Add(BookMarkName, saverange);
}

/**
 * 套用文件模版，将原稿内容套用模板生成目标排版及格式的文件，同时用指定的数据内容替换模版中的书签
 * 
 * @param {Object}
 *            url 模版地址
 * @param {Object}
 *            bodyBookMark 正文主体书签名
 * @param {Object}
 *            data 其他数据(JSON键值对)
 */
function useTemplete(url, docBodyBookMark, data) {
	try {
		// 选择当前文档的所有内容
		var curSel = TANGER_OCX_OBJ.ActiveDocument.Application.Selection;
		setReviewMode(false);
		// 取消修订模式
		curSel.WholeStory();
		// 全选
		curSel.Cut();
		// 将当前文档剪贴到剪贴板

		// 插入模板到剪贴后的空白文档中，从远程加载模版
		// TANGER_OCX_OBJ.AddTemplateFromURL(url);
		TANGER_OCX_OBJ.ActiveDocument.Application.Selection.HomeKey(6);
		TANGER_OCX_OBJ.AddTemplateFromURL(url);
		// 文档主体书签
		if (!TANGER_OCX_OBJ.ActiveDocument.BookMarks.Exists(docBodyBookMark)) {
			alert("模板文件中未设置正确的书签，请检查！");
			curSel.Paste();
			// 将剪贴的内容粘贴回原处
			return;
		}

		// 粘贴先前剪贴的正文内容到模板中正文书签位置
		var bkmkObj = TANGER_OCX_OBJ.ActiveDocument.BookMarks(docBodyBookMark);
		bkmkObj.Range.Paste();
		var saverange = bkmkObj.Range;
		TANGER_OCX_OBJ.ActiveDocument.Bookmarks.Add(docBodyBookMark, saverange);

	/*	$.each(data, function(key, val) {
			if (TANGER_OCX_OBJ.ActiveDocument.BookMarks.Exists(key)) {
				if(key=="二维码")
				addPictureToDocument(key,$("base").attr("href") + "fileupload/download.do?id="+val);	
					else					
				setBookMarkValue(val, key);
			
			}
		
		});*/
		setTimeout(function(){log(data);},2000);
		
		
	} catch (err) {
		alert("执行模板套用时出错：" + err.number + ":" + err.description);
	}
}

function log(data){	
	$.each(data, function(key, val) {
		if (TANGER_OCX_OBJ.ActiveDocument.BookMarks.Exists(key)) {
			if(key=="二维码")
			addPictureToDocument(key,$("base").attr("href") + "fileupload/download.do?id="+val);	
				else					
			setBookMarkValue(val, key);
		
		}
	});
	
}


// 打印页面设置
function setLayout() {
	try {
		TANGER_OCX_OBJ.showdialog(5);
		// 设置页面布局
	} catch (err) {
	} finally {
	}
}

// 打印文档
function doPrint() {
	TANGER_OCX_OBJ.printout(true);
}

// 打印预览
function printPreview() {
	TANGER_OCX_OBJ.PrintPreview();
}

//盖章
function doSignSeal(signer, sealUrl,position) {
	/*if (!TANGER_OCX_OBJ.IsNTKOSecSignInstalled()) {
		alert("您的电脑中还没有安装安全电子印章软件，请先安装后再操作！");
		return;
	}*/
	try {
		var adoc = TANGER_OCX_OBJ.ActiveDocument;
		if (adoc.BookMarks.Exists(position)){
			adoc.BookMarks(position).Select();
			adoc.Application.Selection.MoveRight(1,1);
		}
		TANGER_OCX_OBJ.AddSignFromURL(signer, sealUrl, 0, 0, 1, 2, // 打印模式
			false, // IsUseCertificate
			false, // IsLocked,
			true, // IsCheckDocChange,
			false, // IsShowUI,
			null, // signpass,
			false, // IsAddComment
			true //IsBelowText
		);
	} catch (e) {
		alert("不能进行该操作!\n错误码：" + e.number + "\n描述信息：" + e.description);
	}
}

// 盖章
function doSignSealPic(signer, sealUrl,position) {
	/*if (!TANGER_OCX_OBJ.IsNTKOSecSignInstalled()) {
		alert("您的电脑中还没有安装安全电子印章软件，请先安装后再操作！");
		return;
	}*/
	try {
		var adoc = TANGER_OCX_OBJ.ActiveDocument;
		if (adoc.BookMarks.Exists(position)){
			adoc.BookMarks(position).Select();
			adoc.Application.Selection.MoveRight(1,1);
		}
		TANGER_OCX_OBJ.AddSignFromURL(signer, sealUrl, 0, 0, "", 1,100,1);
	} catch (e) {
		alert("不能进行该操作!\n错误码：" + e.number + "\n描述信息：" + e.description);
	}
}

var oldalert;

// 设置自定义的alert事件响应，用于全屏模式下。
function setNtkoAlert() {
	try {
		oldalert = window.alert;
		window.alert = showNtkoAlert; // 重载alert方法为showNtkoAlert
	} catch (err) {
		alert("错误：" + err.number + ":" + err.description);
	}
}

// 自定义的alert事件响应
function showNtkoAlert(mes) {
	var isInFullScreenMode = false;
	var ntkoocx = TANGER_OCX_OBJ;
	try {
		// 如果不支持全屏属性，以下语句会产生异常
		isInFullScreenMode = ntkoocx.FullScreenMode;
	} catch (err) {
		// 执行这儿说明控件不支持FullScreenMode属性
		isInFullScreenMode = false;
	}
	if (isInFullScreenMode) {
		ntkoocx.ShowTipMessage("提示信息", mes);
	} else {
		oldalert(mes);
		// 调用未重载的alert提示信息
	}
}

/**
 * 根据json给书签赋值 json中含有arrayList用这个方法赋值。
 * 
 * @author bait
 * @param json
 *            (json键值对)
 */
function setBookMarkWithJson(json) {
	if (json == "")
		return;
	var doc = TANGER_OCX_OBJ.ActiveDocument;
	for ( var BookMarkName in json) {
		if (doc.BookMarks.Exists(BookMarkName)) {
			try {
				var data = json[BookMarkName];
				if ((typeof data) == "string") {
					if (data != "") {
						doc.BookMarks(BookMarkName).Range.Text = data;
					}
				} else {
					// 循环处理arrayList中的值显示到页面
					if (data.length > 0) {
						doc.BookMarks(BookMarkName).Select();
						var curSel = doc.Application.Selection;
						for (var i = 0; i < data.length; i++) {
							for (var j = 0; j < data[i].length; j++) {
								curSel.TypeText(data[i][j]);
								if (i == data.length - 1 && j == data[i].length - 1) {

								} else {
									curSel.MoveRight(12);
								}
							}
						}
					}
				}
			} catch (e) {
				alert("数据填充错误：" + e)
			}
		}
	}
	doc.Application.Selection.HomeKey(6);
}

/**
 * 根据模版地址直接打开模版并且替换书签的值
 * 
 * @author bait
 * @param {Object}
 *            url 模版地址
 * @param {Object}
 *            data 书签具体数据(JSON键值对)
 */
function loadTemplate(url, data) {
	try {
		TANGER_OCX_OBJ.OpenFromURL(url);
		setBookMarkWithJson(data);
	} catch (e) {
		if (confirm("文件加载错误，是否自动重新加载？"))
			loadTemplate(url, data);
	}
}

//插入图片到指定书签位置
function addPictureToDocument(position,url){
     var adoc = TANGER_OCX_OBJ.ActiveDocument;
     if (!adoc.BookMarks.Exists(position))
      return;
  adoc.BookMarks(position).Select();
  TANGER_OCX_OBJ.AddPicFromURL(
      url,//URL 注意；URL必须返回Word支持的图片类型。
      true,//是否浮动图片
      0,//如果是浮动图片，相对于左边的Left 单位磅
      0,//如果是浮动图片，相对于当前段落Top
      1, //当前光标处
      100,//无缩放
      1 //文字上方
  );
}
//插入图片到指定位置
function AddPictureFromURL(URL)
{	
    TANGER_OCX_OBJ.AddPicFromURL(
		URL,//URL 注意；URL必须返回Word支持的图片类型。
		true,//是否浮动图片
		0, 
		0,
        1, //当前光标处
		100,//无缩放
		1 //文字上方
);	
}




