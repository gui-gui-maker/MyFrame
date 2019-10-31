
/**
* 两个选择框左右移动，与上下调整
* 
*/

//选中项向左移动或向右移动
function moveLeftOrRight(fromObj, toObj) {
	var fromObjOptions = fromObj.options;
	for (var i = 0; i < fromObjOptions.length; i++) {
		if (fromObjOptions[i].selected) {
			toObj.appendChild(fromObjOptions[i]);
			i--;
		}
	}
}
//左边全部右移动，或右边全部左移
function moveLeftOrRightAll(fromObj, toObj) {
	var fromObjOptions = fromObj.options;
	for (var i = 0; i < fromObjOptions.length; i++) {
		fromObjOptions[0].selected = true;
		toObj.appendChild(fromObjOptions[i]);
		i--;
	}
}

//向上移动
function moveUp(selectObj) {
	var theObjOptions = selectObj.options;
	for (var i = 1; i < theObjOptions.length; i++) {
		if (theObjOptions[i].selected && !theObjOptions[i - 1].selected) {
			swapOptionProperties(theObjOptions[i], theObjOptions[i - 1]);
		}
	}
}

//向下移动
function moveDown(selectObj) {
	var theObjOptions = selectObj.options;
	for (var i = theObjOptions.length - 2; i > -1; i--) {
		if (theObjOptions[i].selected && !theObjOptions[i + 1].selected) {
			swapOptionProperties(theObjOptions[i], theObjOptions[i + 1]);
		}
	}
}

//移动至最顶端
function moveToTop(selectObj) {
	var theObjOptions = selectObj.options;
	var oOption = null;
	for (var i = 0; i < theObjOptions.length; i++) {
		if (theObjOptions[i].selected && oOption) {
			selectObj.insertBefore(theObjOptions[i], oOption);
		} else {
			if (!oOption && !theObjOptions[i].selected) {
				oOption = theObjOptions[i];
			}
		}
	}
}

//移动至最低端
function moveToBottom(selectObj) {
	var theObjOptions = selectObj.options;
	var oOption = null;
	for (var i = theObjOptions.length - 1; i > -1; i--) {
		if (theObjOptions[i].selected) {
			if (oOption) {
				oOption = selectObj.insertBefore(theObjOptions[i], oOption);
			} else {
				oOption = selectObj.appendChild(theObjOptions[i]);
			}
		}
	}
}

//全部选中
function selectAllOption(selectObj) {
	var theObjOptions = selectObj.options;
	for (var i = 0; i < theObjOptions.length; i++) {
		theObjOptions[0].selected = true;
	}
}

/* private function */
function swapOptionProperties(option1, option2) {
		    //option1.swapNode(option2);
	var tempStr = option1.value;
	option1.value = option2.value;
	option2.value = tempStr;
	var tempValSource = option1.valSource;//
	option1.valSource = option2.valSource;//
	option2.valSource = tempValSource;//
	tempStr = option1.text;
	option1.text = option2.text;
	option2.text = tempStr;
	tempStr = option1.selected;
	option1.selected = option2.selected;
	option2.selected = tempStr;
}

function resetAutoWidth(obj) {
	var tempWidth = obj.style.getExpression("width");
	if (tempWidth != null) {
		obj.style.width = "auto";
		obj.style.setExpression("width", tempWidth);
		obj.style.width = null;
	}
}

//返回选中的值
function checkSelectedOptionValue() {
	var ObjSelect = document.getElementById('ObjSelect');
	var itemField = "";
	var itemName = "";
	if (ObjSelect && ObjSelect.options && ObjSelect.options.length > 0) {
		var len = ObjSelect.options.length;
		for (var j = 0; j < len; j++) {
			itemField += ObjSelect.options[j].value + "|";
			itemName += ObjSelect.options[j].text + "|";
		}
	}
	
	return itemField;
}
//返回选中的显示名称
function checkSelectedOptionName() {
	var ObjSelect = document.frm1.ObjSelect;
	var itemField = "";
	var itemName = "";
	if (ObjSelect && ObjSelect.options && ObjSelect.options.length > 0) {
		var len = ObjSelect.options.length;
		for (var j = 0; j < len; j++) {
			itemField += ObjSelect.options[j].value + "|";
			itemName += ObjSelect.options[j].text + "|";
		}
	}
	
	return itemName;
}
