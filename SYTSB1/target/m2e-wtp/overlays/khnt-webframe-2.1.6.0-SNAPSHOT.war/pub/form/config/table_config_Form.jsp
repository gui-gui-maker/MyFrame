<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head pageStatus="${param.status}">
		<title></title>
		<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%@ taglib prefix="sec"
			uri="http://www.springframework.org/security/tags"%>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<base
			href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
		<link href="k/kui/skins/Aqua/css/ligerui-all.css" rel="stylesheet"
			type="text/css" />
		<!-- kui框架css -->
		<link href="k/kui/skins/ligerui-icons.css" rel="stylesheet"
			type="text/css" />

		<script src="k/kui/frame/jquery.min.js" type="text/javascript"></script>
		<script src="k/kui/frame/ligerui.all.js" type="text/javascript"></script>
		<script src="k/kui/frame/jquery.validate.min.js"
			type="text/javascript"></script>
		<script src="k/kui/frame/jquery.metadata.js" type="text/javascript"></script>
		<script src="k/kui/frame/messages_cn.js" type="text/javascript"></script>
		<script src="k/kui/frame/main.js" type="text/javascript"></script>
		<link href="pub/form/config/css/lab.css" rel="stylesheet"
			type="text/css" />
		<script src="pub/form/config/js/lab.js" type="text/javascript"></script>

		<script type="text/javascript">

	$(function() {
			     $.getJSON("pub/form/config/detail.do?id=${param.id}",
			      		 "",
			      		 function(data){
			      		 	var list=data.list;
	                         initkh(list);
			      		 })
                   $("#form1").ligerForm();	 
	});
	function initkh(columns){
	var columns=columns;
	
	init(columns);

	var heraderInfo="";
	
	}
$.extend($.ligerDefaults.Grid, {
    rowHeight: 24,
    fixedCellHeight: false,
    frozen: false,
    async: true,
    headerRowHeight: 30,
    allowUnSelectRow: true
});
//去掉  大于小于包括,并改变顺序
$.ligerDefaults.Filter.operators['string'] =
    $.ligerDefaults.Filter.operators['text'] =
    ["like", "equal", "notequal", "startwith", "endwith"];

//扩展一个 数字输入 的编辑器
$.ligerDefaults.Grid.editors['numberbox'] = {
    create: function (container, editParm)
    {
        var column = editParm.column;
        var precision = column.editor.precision;
        var input = $("<input type='text' style='text-align:right' class='l-text' />");
        input.bind('keypress', function (e)
        {
            var keyCode = window.event ? e.keyCode : e.which;
            return keyCode >= 48 && keyCode <= 57 || keyCode == 46 || keyCode == 8;
        });
        input.bind('blur', function ()
        {
            var value = input.val();
            input.val(parseFloat(value).toFixed(precision));
        });
        container.append(input);
        return input;
    },
    getValue: function (input, editParm)
    {
        return parseFloat(input.val());
    },
    setValue: function (input, value, editParm)
    {
        var column = editParm.column;
        var precision = column.editor.precision;
        input.val(value.toFixed(precision));
    },
    resize: function (input, width, height, editParm)
    {
        input.width(width).height(21);
    }
}; 
$.ligerDefaults.Grid.editors['string'] =
$.ligerDefaults.Grid.editors['text'] = {
    create: function (container, editParm)
    {
        var input = $("<input type='text' style='border:1px solid #d3d3d3;'/>");
        container.append(input);
        return input;
    },
    getValue: function (input, editParm)
    {
        return input.val();
    },
    setValue: function (input, value, editParm)
    {
        input.val(value);
    },
    resize: function (input, width, height, editParm)
    {
        input.width(width).height(21);
    }
};
$.ligerDefaults.Grid.editors['select'] =
{
    create: function (container, editParm)
    {
        var column = editParm.column;
        var input = $("<select></select");
        container.append(input);
        var data = column.editor.data;

        if (!data) return input;
        $(data).each(function ()
        {
            input.append('<option value="' + this.value + '">' + (this.text || this.name) + '</option>');
        });
        return input;
    },
    getValue: function (input, editParm)
    {
        return input.val();
    },
    setValue: function (input, value, editParm)
    {
        if (value)
            input.val(value);
    },
    resize: function (input, width, height, editParm)
    {
        input.css({ width: width, height: 22 });
    }
};


var root = "../../";
var fieldTypeData = [{ value: 'text', text: '文本框' }, { value: 'textarea', text: '多行文本框' }, { value: 'date', text: '日期控件' },{ value: 'time', text: '日期控件(带时分秒)' }, { value: 'select', text: '下拉框' }, { value: 'digits', text: '整数输入框' }, { value: 'number', text: '浮点数输入框' }, { value: 'hidden', text: '隐藏控件'},{ value: 'org', text: '选择机构' }];
  

function init(columns)
{
    bulidMainGrid(columns);
}


function bulidMainGrid(columns)
{
    var rows = [];
    $(columns).each(function ()
    {
        var row = {
            allownull:this.allownull ,
            name: this.name,
            display: this.display,
            listwidth: this.listwidth,
            type: this.type,
            width:  this.width,
            labelwidth: this.labelwidth,
            space: this.space,
            newline: this.newline,
         
            inlist: this.inlist,
            insearch: this.insearch,
            inform: this.inform,
            isSend: false,
            isConfig: this.isConfig,
            type:this.type,
        
            dataBind:this.dataBind,
            length:this.length,
            SourceTableName: this.sourceTableName,
            SourceTableIDField: this.sourceTableIDField,
            SourceTableTextField: this.sourceTableTextField
        }; 
        row.type = this.type;
        if (this.isAutoKey || this.isInForeignKey)
        {
            row.inlist = false;
            row.type = "hidden";
        }
        if (row.SourceTableName)
        {
            row.type = "select";
        }
        if (this.isAutoKey)
        {
            row.insearch = false;
        }
        rows.push(row);
    });

    var gridPanle = $('<div style="margin:7px;"></div>').appendTo('body');
    window.grid =  gridPanle.ligerGrid({
        columns: [
            { display: '字段名', name: 'name', align: 'left', width: 110, minWidth: 30 },
            { display: '显示名', name: 'display', align: 'left', width: 110, minWidth: 30, editor: { type: 'text'} },
            { display: '是否为空', name: 'allownull', width: 55, render: checkboxRender,isSort :false,}
            ,
             {display: '列表显示', name: 'inlist', width: 55, render: checkboxRender,isSort :false },
            { display: '列表宽度', name: 'listwidth', align: 'right', width: 50, minWidth: 30, editor: { type: 'text'} },
            { display: '搜索显示', name: 'insearch', width: 55, render: checkboxRender,isSort :false },
            { display: '是否可配', name: 'isConfig', width: 0, render: checkboxRender,  columnWidth:0,isSort :false }
           ,
            { display: '表单显示', name: 'inform', width: 55, render: checkboxRender ,isSort :false},
            { display: '控件类型', name: 'type', align: 'left', width: 80, minWidth: 30, editor: { type: 'select', data: fieldTypeData }, render: fieldTypeRender },
            { display: '标签宽度', name: 'labelwidth', align: 'right', width: 55, editor: { type: 'text'},isSort :false  },
            { display: '控件宽度', name: 'width', align: 'right', width: 55, editor: { type: 'text'},isSort :false },
            { display: '间隔宽度', name: 'space', align: 'right', width: 55, editor: { type: 'text'},isSort :false },
            { display: '是否新行', name: 'newline', width: 55, render: checkboxRender ,isSort :false},
            { display: '綁定值', name: 'dataBind', width: 55,  editor: { type: 'text'}  },
            { display: '长度', name: 'length', width: 55  }
            
       ],data: { Rows: rows }, usePager: false, toolbar: createGridToolbar(),enabledSort:false,
        enabledEdit: true, clickToEdit: true, fixedCellHeight: false, inWindow: false, rownumbers: true,
        width: '98%', height: '100%',heightDiff:-14, rowHeight: 24
    });
    window.grid.toggleCol('isConfig', false);
     window.grid.toggleCol('labelwidth', false);
      window.grid.toggleCol('space', false);
      
      
}

function createGridToolbar(tName)
{
    var items = [];
   
    items.push({ text: '保存', click: outjson, img: "pub/form/config/icons/printer_48.png" });
    items.push({ text: '上移', click: moveup, img: "pub/form/config/icons/sign_up.gif" });
    items.push({ text: '下移', click: movedown, img: "pub/form/config/icons/arrow_down.gif" }); 
    //items.push({ text: '自动翻译字段', click: translate, img: "../icons/world.gif" });
    return { items: items };

    function clear()
    {
        var managers = $.ligerui.find($.ligerui.controls.Input);
        for (var i = 0, l = managers.length; i < l; i++)
        {
            if (exits(managers[i].id))
            { 
                managers[i].destroy();
            }
        }
    }
    function exits(id)
    {
        for (var i = 0, l = window.grid.rows.length; i < l; i++)
        {
            var name = window.grid.rows[i].name;
            if (name == id) return true;
        }
        return false;
    }




    function outjson()
    {
        grid.endEdit();//结束编辑
        var d = bulidData();
        $.post("pub/form/config/autoGenerateFiles.do",
	    { 
		   "data":$.ligerui.toJSON(d),
		   "id":'${param.id}'    
		 }, function(data){
			      		 if (data.success) {
				            $.ligerDialog.alert('生成成功！');
				            	api.close();
					     }else{
					        $.ligerDialog.alert('生成失败！');
					     }
		 }) 
   
    }
 
    function moveup()
    { 
        var selected = grid.getSelected();
        if (!selected) return;
        grid.up(selected);
    }
    function movedown()
    { 
        var selected = grid.getSelected();
        if (!selected) return;
        grid.down(selected);
    } 
}




//获取 表单和表格 结构 所需要的数据
function bulidData()
{ 
    var griddata = [], searchdata= [], formdata= [];   
    for (var i = 0, l = grid.rows.length; i < l; i++)
    {
        var o = grid.rows[i];
       
            griddata.push({inlist:o.inlist, display: o.display, name: o.name, width: o.listwidth,isSend:o.isSend,isConfig:o.isConfig});
        if (o.insearch)
            searchdata.push(getFieldData(o, true));
  
            formdata.push(getFieldData(o));
    }
    return { grid: griddata, search: searchdata, form: formdata };

    function getFieldData(o, search)
    {

        var field = {
            display: o.display,
            name: o.name,
            newline: o.newline,
            allownull:o.allownull,
            inlist:o.inlist,
            inform:o.inform,
            listwidth:o.listwidth,
            insearch:o.insearch,
            labelwidth: o.labelwidth || o.labelwidth,
            width: o.width,
            space: o.space,
            type: o.type,
            isSend: o.isSend,
            isConfig: o.isConfig,
            dataBind:o.dataBind,
     
           length:o.length
        }; 
        if (!search)
        {
            field.validate = getValidate(o);
        }
        else
        {
            field.cssClass = "field";
            field.newline = o.newline ? true : false;
        }
        return field;
    }
    function getValidate(o)
    {
        if (o.validate) return o.validate;
        if (!o.allownull) return { required: true };
        return null;
    }
}

//字段类型渲染器
function fieldTypeRender(r, i, value)
{
    for (var i = 0, l = fieldTypeData.length; i < l; i++)
    {
        var o = fieldTypeData[i];
    
        if (o.value == value) return o.text || o.name;
    }
    return "文本框";
}
//是否类型的模拟复选框的渲染函数
function checkboxRender(rowdata, rowindex, value, column)
{
    var iconHtml = '<div class="chk-icon';

    if (value==true)
    {
     iconHtml += " chk-icon-selected";
    }
    iconHtml += '"';
    iconHtml += ' rowid = "' + rowdata['__id'] + '"';
    iconHtml += ' gridid = "' + this.id + '"';
    iconHtml += ' columnname = "' + column.name + '"';
    iconHtml += '></div>';

    return iconHtml;
}
//是否类型的模拟复选框的点击事件
$("div.chk-icon").live('click', function ()
{
    var grid = $.ligerui.get($(this).attr("gridid"));
    var rowdata = grid.getRow($(this).attr("rowid"));
    var columnname = $(this).attr("columnname");
    var checked = rowdata[columnname];
     
    grid.updateCell(columnname, !checked, rowdata);
});




	</script>
	</head>
	<body>


		<form name="form1" id="form1">

			<table class="l-detail-table">
				<tr aligin="center" width="110%">



					<td class="l-t-td-left">

					</td>

				</tr>
			</table>

		</form>
	</body>
</html>
