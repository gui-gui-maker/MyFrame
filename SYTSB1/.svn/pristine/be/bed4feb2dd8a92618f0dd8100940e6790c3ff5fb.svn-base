

/**
 * 打开正文内容编辑器
 * @param cid 正文文件id
 * @param oid 发文id
 */
function openContentDoc(options){
	top.winOpen({
		width: 1000,
		height: $(top).height()>=650?650:$(top).height(),
		lock: true,
		parent: api,
		title: options.title||"正文",
		content: "url:app/archives/doc_editor.jsp",
		data: {
			savePdf: false,
			window:window,
			docId: options.docId,
			doc: options.doc,
			id: options.id || "",
			type:options.type,
			pdf: options.pdf || "",
			aid: options.aid || "",
			dofun: options.dofun,
            word: options.word,
			user: options.user,
			issuedUnit: "发文单位",
			funParam: options.funParam,
			status: options.status,
			tbar: options.tbar||{},
			callback: options.callback,
			title: options.title||"odcontent",
			addSealCallback: options.addSealCallback,
			useTempleteCallback: options.useTempleteCallback,
			getTempletes: options.getTempletes
		}
	}).max();
}