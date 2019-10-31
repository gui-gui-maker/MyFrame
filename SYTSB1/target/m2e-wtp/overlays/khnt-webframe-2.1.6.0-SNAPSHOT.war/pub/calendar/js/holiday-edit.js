/**
 * 根据起止时间转换假日日期数组。
 * 
 * @param start
 * @param end
 */
function parseHolidayDate(start,end){
	//节日日期明细
	var holidayDates = [];
	var hstart = moment(start),hend = moment(end);
	var length = hend.diff(hstart,"d");
	for(var i = 0; i < length; i++){
		holidayDates.push({
			date: hstart.format("YYYY-MM-DD")
		});
		hstart.add(1,"d");
	}
	
	return holidayDates;
}

/**
 * 将假日信息更新至服务器
 * 
 * @param holiday
 * @param callback
 */
function saveHoliday(holiday,start,end,callback){
	holiday.holidayDates = parseHolidayDate(start,end);
	$.ajax({
		url: "pub/calendar/holiday/save.do",
		type: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(holiday),
        success: callback
	});
}

if(typeof window["JSON"] === 'undefind'){
	window.JSON = {
		stringify: function (o) {
			alert(o);
			var f = function (n) {
				return n < 10 ? '0' + n : n;
			},
			escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
			quote = function (value) {
				escapable.lastIndex = 0;
				return escapable.test(value) ? '"' + value.replace(escapable,
					function (a) {
						var c = meta[a];
						return typeof c === 'string' ? c : '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
					}) + '"' : '"' + value + '"';
			};
			if (o === null) return 'null';
			var type = typeof o;
			if (type === 'undefined') return undefined;
			if (type === 'string') return quote(o);
			if (type === 'number' || type === 'boolean') return '' + o;
			if (type === 'object') {
				if (typeof o.toJSON === 'function') {
					return JSON.stringify(o.toJSON());
				}
				if (o.constructor === Date) {
					return isFinite(this.valueOf()) ? this.getUTCFullYear() + '-' + f(this.getUTCMonth() + 1) + '-' + f(this.getUTCDate()) + 'T' + f(this.getUTCHours()) + ':' + f(this.getUTCMinutes()) + ':' + f(this.getUTCSeconds()) + 'Z' : null;
				}
				var pairs = [];
				if (o.constructor === Array) {
					for (var i = 0,
							 l = o.length; i < l; i++) {
						pairs.push(JSON.stringify(o[i]) || 'null');
					}
					return '[' + pairs.join(',') + ']';
				}
				var name, val;
				for (var k in o) {
					type = typeof k;
					if (type === 'number') {
						name = '"' + k + '"';
					} else if (type === 'string') {
						name = quote(k);
					} else {
						continue;
					}
					type = typeof o[k];
					if (type === 'function' || type === 'undefined') {
						continue;
					}
					val = JSON.stringify(o[k]);
					pairs.push(name + ':' + val);
				}
				return '{' + pairs.join(',') + '}';
			}
		}
	}
}

