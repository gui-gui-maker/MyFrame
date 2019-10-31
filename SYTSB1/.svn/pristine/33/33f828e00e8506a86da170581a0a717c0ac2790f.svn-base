function getInputVal(name) {
	var type = $('#' + name).attr("type");
	var ltype = $('#' + name).attr("ltype");
	var val;
	if (type == "hidden") {
		val = $('#' + name).val();
	} else if (type == "text") {
		if (ltype == "date") {
			val = $('#' + name).val();
		} else if (ltype == "select") {
			val = $('#' + name).val();
		} else {
			val = $('#' + name).val();
		}

	} else if (type == "radio") {
		try {
			val = $('#' + name).ligerGetRadioManager('getValue');
		} catch (e) {
			try {
				val = liger.get(name).getValue();
			} catch (e) {
				val = $('#' + name).val();
			}
		}
	} else if (type == "checkbox") {
		try {
			val = $('#' + name).ligerGetCheckBoxManager('getValue');
		} catch (e) {
			try {
				val = liger.get(name).getValue();
			} catch (e) {
				val = $('#' + name).val();
			}
		}
	} else {
		val = document.getElementById(name).value;
	}
	return val;
}

function setInputVal(name, val) {
	if (!$('#' + name)) {
		return;
	}
	try {
		val = liger.get(name).setValue(val);
	} catch (e) {
		try {
			$('#' + name).val(val);
		} catch (e) {
			try {
				$('#' + name).ligerGetComboBoxManager.setValue(val);
			} catch (e) {
				try {
					$('#' + name).ligerGetDateEditorManager.setValue(val);
				} catch (e) {
					try {
						$('#' + name).ligerGetRadioGroupManager().setValue(val);
					} catch (e) {
						document.getElementById(name).value = val;
					}
				}
			}
		}
	}
}

String.prototype.Trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.LTrim = function() {
	return this.replace(/(^\s*)/g, "");
};
String.prototype.RTrim = function() {
	return this.replace(/(\s*$)/g, "");
};

var comm = {
	dialog : null
};
comm.Loading = function(icon, content, title) {
	if (icon == "loading") {
		content = "<div><img src='rtbox/public/images/loading/loading.gif' style='padding-right:5px;'></img>" + content + "</div>"
	}
	comm.dialog = dialog({
		id : 'comm',
		title : title,
		content : content
	});
	comm.dialog.show();
}
comm.LoadEnd = function(time) {
	if (parseInt(time) > 0) {
		setTimeout(function() {
			comm.dialog.remove();
		}, parseInt(time) * 1000);
	} else {
		comm.dialog.remove();
	}
};

comm.mask = function(content) {
	content = "<div><img src='rtbox/public/images/loading/loading.gif' style='padding-right:5px;'></img>" + content + "</div>"
	comm.dialog = dialog({
		id : 'comm',
		content : content
	});
	comm.dialog.showModal();
}
comm.unmask = function() {
	comm.dialog.remove();
};

comm.Content = function(content) {
	comm.dialog.content(content);
};

comm.info = function(content, time) {
	comm.dialog = dialog({
		id : 'comm',
		content : content
	});
	comm.dialog.show();

	if (!time) {
		time = 2;
	}
	comm.LoadEnd(time);
};

function info(msg) {
	dialog({
		id : 'system',
		title : '系统提示',
		content : msg,
		ok : function() {
		}
	}).show();
}

function error(title, msg) {
	dialog({
		id : 'system',
		title : title,
		content : msg,
		ok : function() {
		}
	}).show();
}

function notice(title, msg, time) {
	var hasTime = true;
	if (typeof (time) == "undefined" || time == null || time.length <= 0) {
		time = 10;
	}

	var d = dialog({
		id : 'system',
		width : 220,
		title : title,
		content : msg
	});
	d.show();
	setTimeout(function() {
		d.close().remove();
	}, parseInt(time) * 1000);

}

function confirm(msg, fun) {
	var d = dialog({
		id : 'system',
		title : '系统提示',
		content : msg,
		okValue : '确定',
		ok : function() {
			eval(fun);
		},
		cancelValue : '取消',
		cancel : function() {
		}
	});
	d.show();
}
