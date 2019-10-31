
//2016年11月17日 14:59:05 星期四 lybide
function getLigerUi() {
	var str1="";
	//str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/ligerui1.1.9/all/ligerui.all.js.khnt.retail.js"%3E%3C/script%3E');document.write(str1);return;
	var jsObj=[];
	jsObj.push("base.js");
	jsObj.push("inject.js");
	jsObj.push("ligerAccordion.js");
	jsObj.push("ligerButton.js");
	jsObj.push("ligerCheckBox.js");
	jsObj.push("ligerCheckBoxGroup.js");
	jsObj.push("ligerCheckBoxList.js");//1.3.3 new
	jsObj.push("ligerComboBox.js");
	jsObj.push("ligerDateEditor.js");
	jsObj.push("ligerDialog.js");
	jsObj.push("ligerDrag.js");
	jsObj.push("ligerEasyTab.js");
	jsObj.push("ligerFilter.js");
	jsObj.push("ligerForm.js");
	jsObj.push("ligerGrid.js");
	jsObj.push("ligerLayout.js");
	jsObj.push("ligerListBox.js");//1.3.3 new
	jsObj.push("ligerMenu.js");
	jsObj.push("ligerMenuBar.js");
	jsObj.push("ligerMessageBox.js");
	jsObj.push("ligerPanel.js");//1.3.3 new
	jsObj.push("ligerPopupEdit.js");//1.3.3 new
	jsObj.push("ligerPortal.js");//1.3.3 new
	jsObj.push("ligerRadio.js");
	jsObj.push("ligerRadioGroup.js");
	jsObj.push("ligerRadioList.js");//1.3.3 new
	jsObj.push("ligerResizable.js");
	jsObj.push("ligerSpinner.js");
	jsObj.push("ligerTab.js");
	jsObj.push("ligerTextBox.js");
	jsObj.push("ligerTip.js");
	jsObj.push("ligerToolBar.js");
	jsObj.push("ligerTree.js");
	jsObj.push("ligerWindow.js");
	jsObj.push("ligerZKHExt.js");//kh add
	jsObj.push("ligerZKHSimLct.js");//kh add
	for (var i in jsObj) {
		str1+=unescape('%3Cscript type="text/javascript" src="'+kui["kuiBase"]+'kui/frame/ligerui/plugins-kh/'+jsObj[i]+'"%3E%3C/script%3E');
	}
	document.write(str1);
}
getLigerUi();