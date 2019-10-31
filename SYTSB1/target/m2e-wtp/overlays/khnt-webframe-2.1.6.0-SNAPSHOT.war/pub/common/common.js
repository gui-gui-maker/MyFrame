/**
 * 选择系统机构。
 * 
 */
function chooseSystemUnit(option){
	__chooseSystemResource("unit",option);
}

/**
 * 选择系统人员。
 * 
 */
function chooseSystemUser(option){
	__chooseSystemResource("user",option);
}

/**
 * 选择系统资源（机构、人员）
 * 
 * 根据业务场景需求，支持多种选取的机构范围策略：
 * 1、全部范围内选择
 * 2、本单位以下范围
 * 3、本单位以上范围
 * 5、本单位直接下级
 * 6、本单位直接上级
 * 
 * @param type 资源类别
 * @param option 选项参数
 * {
 *     multiple: true/false,	//是否多选，默认false
 *     props: "unit/dep/all",	//机构性质，默认all，unit为单位，dep为部门
 *     type: "",				//机构类型，业务系统定义类型如敬老院、福利院、孤儿院等
 *     range: "all/pa/sa/s/sl",	//可选范围，默认all，pa为本单位以上，sa为本单位以下,s为本单位直接下级,sl为同级兄弟机构
 *     self: true/false,		//是否可选本单位/部门
 *     selfType: unit/dep,		//当前机构类型，默认为unit，该参数主要用于选择同级机构时，这个同级的出发点到底是用户所在部门还是单位
 *     sync: true/false,		//是否同步加载，注意异步加载模式下，页面动态过滤不可用
 *     idField: "",				//选择结果要填充id值的目标对象，
 *     nameField: "",			//选择结果要填充name值的目标对象
 *     initIds，					//初始化选中的id，多个用逗号","分隔，用于多次选择时，方便操作
 *     initNames，				//初始化选中的name，多个用逗号","分隔，用于多次选择时，方便操作
 *     callback: function 		//选择结果回调方法
 * }
 * 
 */
function __chooseSystemResource(type,option){
	var ops = $.extend({
		multiple: false,
	    props: "all",
	    type: "all",
	    range: "all",
	    expand: false,
	    self: true,
	    selfType: "unit",
	    sync: false,
	    groupToOrg: false,
	    initIds: "",
	    initNames: ""
	},option);
	if(ops.initIds == "" && ops["idField"] && ops["nameField"]){
		ops.initIds = $("#" + ops.idField).val();
		ops.initNames = $("#" + ops.nameField).val();
	}
	ops.initIds = ops.initIds.replace(/，/g,",");
	ops.initNames = ops.initNames.replace(/，/g,",");
	var isUnit = type == "unit";
	var pageUrl = isUnit?"choose_unit.jsp":"choose_user.jsp";
	
	winOpen({
		title: isUnit?"系统机构选择":"系统人员选择",
		lock: true,
		parent: api,
		width: isUnit?900:1024,
		height: 550,
		content: "url:pub/common/" + pageUrl + "?multiple=" + ops.multiple + "&type=" + ops.type + "&groupToOrg=" + ops.groupToOrg + 
				"&range=" + ops.range + "&self=" + ops.self + "&sync=" + ops.sync + "&props=" + ops.props + "&expand=" + ops.expand,
		cancel: true,
		data: ops,
		ok: function(){
			var rst = this.iframe.contentWindow.chooseResult();
			if(ops.idField) $("#" + ops.idField).val(rst==null?"":rst.code);
			if(ops.nameField) $("#" + ops.nameField).val(rst==null?"":rst.name);
			if(ops.callback) ops.callback(rst);
		}
	});
}