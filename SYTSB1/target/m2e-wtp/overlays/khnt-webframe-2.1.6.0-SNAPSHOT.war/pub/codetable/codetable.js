window.Ct = {};
(function ($) {
	
})(jQuery);
var Ext = {};
var manager;
var sta = [{id:'0',text:'禁用'},{id:'1',text:'启用'}];
Ext.onReady = $;
Ct.init = function(){
	if($("#maingrid").length<1){
		var title = '<div class="item-tm" id="titleElement">'+
	    			'<div class="l-page-title has-icon has-note">'+
	    			'<div class="l-page-title-div"></div>'+
	    			'<div class="l-page-title-text"><h1>'+Ct.config.name+'管理</h1></div>'+
	    			'<div class="l-page-title-note">说明：操作码表过后,请点击刷新缓存！</div>'+
	    			'<div class="l-page-title-icon"><img src="k/kui/images/icons/32/setting_tools.png" border="0"></div>'+
	    			'</div>'+
	    			'</div>'+
	    			'<div class="scroll-tm"><div id="maingrid"></div></div>';
		$("body").append(title);
	}
	manager = $("#maingrid").ligerGrid({
        columns: [
        { name: 'id',hide:true},
        { display: '名称', name: 'name', width: '40%', align: 'left'},
        { display: '值', name: 'code',  width: '10%', align: 'center'},
        { display: '排序', name: 'sort',width: '10%', align: 'center' }, 
        { display: '状态', name: 'status', width: '10%', align: 'center',render:function(item){
        	return render(item["status"],sta);
        }},
        { display: "<a class='l-a l-icon-add' href='javascript:void(0);' onclick='addNewValue()' title='增加'><span>增加</span></a><a class='l-a l-icon-refurbish' href='javascript:void(0);' onclick='refreshCache()' title='刷新缓存'><span>刷新缓存</span></a>", isSort: false, width: '10%',render: function (rowdata, rowindex, value) {
        	var h = "";
        	h += "<a class='l-a l-icon-edit' href='javascript:modifyValue()' title='修改'><span>修改</span></a> "
        	h += "<a class='l-a l-icon-del' href='javascript:deleteValue()' title='删除'><span>删除</span></a> ";
        	if(rowdata.status=='0'){
        		h += "<a class='l-a l-icon-userUnlock' href='javascript:change()' title='启用'><span>启用</span></a> ";
        	}else{
        		h += "<a class='l-a l-icon-lock' href='javascript:change(\"1\")' title='禁用'><span>禁用</span></a> ";
        	}
            return h;
        }}], 
        height:$("body").height()-$(".scroll-tm").height()-66,
        frozenRownumbers: false,
        usePager:false,
        isScroll: true,
        url:'pub/codetablevalue/getCodetableValues.do?id='+Ct.config.codeTableId,
        rownumbers:true,
        enabledSort:false,
        allowHideColumn:false,
        tree: {
        	columnName: 'name'
        }
    });
}
function render(value,data){
    for (var i in data) {
    	if (data[i]["id"] == value)
        {
        	return data[i]['text'];
        }
		if(data[i].children)
		{
			for(var j in data[i].children)
			{
				if(data[i].children[j]["id"] ==value)
					return data[i].children[j]['text'];
				if(data[i].children[j].children)
				{
					for(var k in data[i].children[j].children)
						if(data[i].children[j].children[k]["id"]==value)
						{
							return data[i].children[j].children[k]["text"];
						}
				}
			}
		}
    }
    return value;
}

function reloadDataGrid(){
	manager.loadData(true);
}
function addNewValue(){
	var actionNodeID2 = "";
	if(Ct.config.type=="1"){
		var row = manager.getSelectedRow();
		if (row) actionNodeID2 = row.id;
	}
	top.$.dialog({
		width: 450,
		height: 220,
		lock: true,
		title: "新增表值",
		content: 'url:pub/codetable/codetablevalue_detail.jsp?status=add&id='
				+ Ct.config.codeTableId
				+ '&code_table_value_id='
				+ actionNodeID2,
		data: {
			"window": window
		}
	});
}
function deleteValue(){
	var row = manager.getSelected();
	if (row) {
		if (row.id) {
			$.ligerDialog.confirm("删除码值将删除自身值和子码值，确定要删除？",function(yes) {
				if (yes) {
					$.post("pub/codetablevalue/deleteValue.do",{
						ids: row.id,
						codeTabledId: Ct.config.codeTableId
					}, function(resp) {
						if (resp.success) {
							manager.remove(row);//执行删除
						} else {
							$.ligerDialog.error('删除失败');
							return false;
						}
					}, "json");
				}
			});
		}else{
			manager.remove(row);//执行删除
		}
	} else {
		$.ligerDialog.warn('请选择您要删除的码表值！')
	}
}
function modifyValue() {
	var row = manager.getSelectedRow();
	if (row) {
		var actionNodeID2 = row.id;
		top.$.dialog({
			width: 450,
			height: 220,
			lock: true,
			title: "修改表值",
			content: 'url:pub/codetable/codetablevalue_detail.jsp?status=modify&id='
					+ actionNodeID2,
			data: {
				"window": window
			}
		});
	} else {
		$.ligerDialog.warn('请选择需要修改的值!')
	}
}

function change(obj){
	var row = manager.getSelected();
	if (row) {
		var title = "";
		var status = "";
		var t="";
		if(obj=='1'){
			title = "确定要禁用码值'"+row.name+"'吗？";
			t = "禁用成功";
			status = 0;
		}else{
			title = "确定要启用码值'"+row.name+"'吗？";
			t = "启用成功";
			status = 1;
		}
		$.ligerDialog.confirm(title,function(yes) {
			if (yes) {
				$.getJSON("pub/codetablevalue/changeStatus.do",{status:status,ids:row.id,codeTabledId:Ct.config.codeTableId},function(res){
					if(res.success){
						top.$.notice(t,1);
						reloadDataGrid();
					}else{
						$.ligerDialog.error('删除失败');
						return false;
					}
				})
			}
		});
	} else {
		$.ligerDialog.warn('请选择您要操作的码表值！')
	}
}
//刷新码表缓存
function refreshCache(){
	$("body").mask("正在刷新...");
	 $.getJSON("pub/codetable/refreshCache.do",function(resp){
     	if(resp.success){
     		top.$.notice('刷新缓存成功！',1)
     		$.getJSON("pub/codetable/codetableTree.do",function(response){
     			manager.loadData(true);
     		});
     	}
     	else
     		$.ligerDialog.error('刷新缓存失败！请稍后再试。');
		$("body").unmask();
	});
}
