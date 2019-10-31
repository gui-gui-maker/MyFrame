var buOrderListGrid;
function createBuOrderListGrid() {
	var columns = [
			{
				display : 'id',
				name : 'id',
				hide : true
			},{
				display : 'goodsId',
				name : 'goodsId',
				hide : true
			},
			{
				display : '商品条码',
				name : 'goodsSn',
				width : '10%',
				editor : {
					type : 'text'
				},
				align: 'left',
				maxlength : 64,
				required:true
			},
			{
				display : '商品编号',
				name : 'goodsCode',
				width : '5%',
				editor : {
					type : 'select',
					ext :
	                    function (rowdata)
	                    {
	                        return {
	                            onBeforeOpen: f_selectGoods
	                        };
	                    }
				},
				align: 'left',
				maxlength : 32,
				required:true
			},
			{
				display : '商品名称',
				name : 'goodsName',
				width : '10%',
				type:'text',
				editor : {
					type : 'select',
					ext :
	                    function (rowdata)
	                    {
	                        return {
	                            onBeforeOpen: f_selectGoods
	                        };
	                    }
				},
				align: 'left',
				maxlength : 128,
				required:true
			},{
				display : '商品拼音码',
				name : 'goodsPinyin',
				hide : true
			},
			{
				display : '商品单位',
				name : 'goodsUnit',
				align: 'left',
				width : '5%'
			},
			{
				display : '商品型号',
				name : 'goodsModel',
				align: 'left',
				width : '5%'
			},
			{
				display : '商品规格',
				name : 'goodsSpec',
				align: 'left',
				width : '5%'
			},
			{
				display : '商品颜色',
				name : 'goodsColor',
				type:'text',
				align: 'left',
				width : '5%'
			},
			{
				display : '数量',
				name : 'goodsNum',
				width : '5%',
				type:'int',
				align: 'right',
				editor : {
					type : 'int',
					minValue:'1',
				}
			},
			{
				display : '单价',
				name : 'goodsPrice',
				width : '6%',
				type:'float',
				align: 'right',
				editor : {
					type : 'float',
					minValue:'0.00'
				}
			},
			{
				display : '金额',
				name : 'goodsMoney',
				width : '6%',
				type:'float',
				align: 'right'
			},
			{
				display : '税率(%)',
				name : 'goodsTax',
				width : '6%',
				type:'float',
				align: 'right',
				editor : {
					type : 'float',
					minValue:'0.00'
				}
			},
			{
				display : '含税单价',
				name : 'goodsTaxPrice',
				width : '6%',
				type:'float',
				align: 'right'
			},
			{
				display : '含税金额',
				name : 'goodsAllMoney',
				width : '6%',
				type:'float',
				align: 'right'
			},
			{
				display : '税额',
				name : 'goodsTaxMoney',
				width : '6%',
				align: 'right',
				type:'float'
			},
			{
				display : '备注',
				name : 'remark',
				width : '7%',
				type:'text',
				align: 'left',
				editor : {
					type : 'textarea',
					height:80
				},
				maxlength : 512
			}];
	if (pageStatus != "detail") {
		columns
				.unshift({
					display : "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='addNewRow(\"buOrderList\")' title='增加'><span>增加</span></a>",
					isSort : false,
					width : '2%',
					render : function(rowdata, rowindex, value) {
						var h = "";
						if (!rowdata._editing) {
							h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"buOrderList\")' title='删除'><span>删除</span></a> ";
						}
						return h;
					}
				});
	}
	buOrderListGrid = $("#buOrderList").ligerGrid({
		columns : columns,
		enabledEdit : pageStatus != "detail",
		clickToEdit : true,
		rownumbers : true, // 是否显示行序号
		frozenRownumbers : false,
		usePager : false,
		data : {
			Rows : []
		},
		height:$("body").height()-$("#purchasemain").height()-130,
		onAfterEdit: f_onAfterEdit
	});
	gridConfig["buOrderList"].manager = buOrderListGrid;
}

function f_onAfterEdit(e){
	if(e.column.name=="goodsSn"){
		$.getJSON("data/goods/getGoodsBySn.do",{goods_sn:e.record.goodsSn},function(data){
			if(data.success){
				buOrderListGrid.updateCell('goodsId', data.data.id, e.record);
		    	buOrderListGrid.updateCell('goodsSn', data.data.goods_sn, e.record);
		    	buOrderListGrid.updateCell('goodsCode', data.data.goods_code, e.record);
		    	buOrderListGrid.updateCell('goodsName', data.data.goods_name, e.record);
		    	buOrderListGrid.updateCell('goodsPinyin', data.data.goods_pinyin, e.record);
		    	buOrderListGrid.updateCell('goodsUnit', data.data.goods_unit, e.record);
		    	buOrderListGrid.updateCell('goodsModel', data.data.goods_model, e.record);
		    	buOrderListGrid.updateCell('goodsSpec', data.data.goods_spec, e.record);
		    	buOrderListGrid.updateCell('goodsColor', data.data.goods_color, e.record);
		    	buOrderListGrid.updateCell('goodsPrice', data.data.goods_purchase_price, e.record);
		    	updatePrice(e);
			}
			else
			{
				top.$.dialog.notice({
             		content: "未找到对应条码的产品！"
            	});
				clearData(e)
			}
		})
	}
	if(e.column.name == 'goodsNum' || e.column.name == 'goodsPrice' ||e.column.name == 'goodsTax'
			||e.column.name=='goodsCode'||e.column.name=='goodsName'){
		updatePrice(e);
	}
}

function addNewRow(name) {
	var grid = gridConfig[name].manager;
	grid.addEditRow({
		goodsSn:'',
		goodsCode:'',
		goodsName:'',
		goodsNum : '1',
		goodsTax : '0.00'
	})
	//grid.addEditRow({}); // 添加一行
}


function deleteRow(name) {
	var grid = gridConfig[name].manager; // 删除一行
	var data = grid.getSelectedRow();
	if (data.id) {
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$.getJSON(gridConfig[name]["delUrl"], {
					ids : data.id
				}, function(json) {
					if (json.success) {
						grid.deleteSelectedRow();
					}
				});
			}
		});
	} else {
		grid.deleteSelectedRow();
	}
}

var gridConfig = {
		buOrderList : {manager : buOrderListGrid,delUrl : "purchase/order/list/delete.do"}
};
function render(value, data) {
	for ( var i in data) {
		if (data[i]["id"] == value) {
			return data[i]['text'];
		}
		if (data[i].children) {
			for ( var j in data[i].children) {
				if (data[i].children[j]["id"] == value)
					return data[i].children[j]['text'];
				if (data[i].children[j].children) {
					for ( var k in data[i].children[j].children)
						if (data[i].children[j].children[k]["id"] == value) {
							return data[i].children[j].children[k]["text"];
						}
				}
			}
		}
	}
	return value;
}

//价格处理
function updatePrice(e){
	var goodsMoney = parseFloat(e.record.goodsPrice)*parseFloat(e.record.goodsNum)
	if(!isNaN(goodsMoney)){
		buOrderListGrid.updateCell('goodsMoney',Math.round(goodsMoney *100)/100 , e.record);
	}
	var goodsTaxPrice = parseFloat(e.record.goodsPrice)*parseFloat(e.record.goodsTax)*0.01+parseFloat(e.record.goodsPrice)
	if(!isNaN(goodsTaxPrice)){
		buOrderListGrid.updateCell('goodsTaxPrice', Math.round(goodsTaxPrice *100)/100, e.record);
	}
	
	var goodsAllMoney = (parseFloat(e.record.goodsPrice)*parseFloat(e.record.goodsTax)*0.01+parseFloat(e.record.goodsPrice))*parseFloat(e.record.goodsNum)
	if(!isNaN(goodsAllMoney)){
		buOrderListGrid.updateCell('goodsAllMoney', Math.round(goodsAllMoney *100)/100, e.record);
	}
	
	var	goodsTaxMoney = parseFloat(e.record.goodsPrice)*parseFloat(e.record.goodsNum)*parseFloat(e.record.goodsTax)*0.01
	if(!isNaN(goodsTaxMoney)){
		buOrderListGrid.updateCell('goodsTaxMoney', Math.round(goodsTaxMoney *100)/100, e.record);
	}
}


function updatePrice1(e,price,i){
	buOrderListGrid.updateRow(i,{goodsPrice:price});
	
	var goodsMoney = parseFloat(price)*parseFloat(e.goodsNum)
	if(!isNaN(goodsMoney)){
		buOrderListGrid.updateRow(i,{goodsMoney:Math.round(goodsMoney *100)/100});
	}
	var goodsTaxPrice = parseFloat(price)*parseFloat(e.goodsTax)*0.01+parseFloat(price)
	if(!isNaN(goodsTaxPrice)){
		buOrderListGrid.updateRow(i,{goodsTaxPrice:Math.round(goodsTaxPrice *100)/100});
	}
	
	var goodsAllMoney = (parseFloat(price)*parseFloat(e.goodsTax)*0.01+parseFloat(price))*parseFloat(e.goodsNum)
	if(!isNaN(goodsAllMoney)){
		buOrderListGrid.updateRow(i,{goodsAllMoney:Math.round(goodsAllMoney *100)/100});
	}
	
	var	goodsTaxMoney = parseFloat(price)*parseFloat(e.goodsNum)*parseFloat(e.goodsTax)*0.01
	
	if(!isNaN(goodsTaxMoney)){
		buOrderListGrid.updateRow(i,{goodsTaxMoney:Math.round(goodsTaxMoney *100)/100});
	}
}


function clearData(e){
	buOrderListGrid.updateCell('goodsId', '', e.record);
	buOrderListGrid.updateCell('goodsSn', '', e.record);
	buOrderListGrid.updateCell('goodsPinyin', '', e.record);
	buOrderListGrid.updateCell('goodsCode', '', e.record);
	buOrderListGrid.updateCell('goodsName', '', e.record);
	buOrderListGrid.updateCell('goodsUnit', '', e.record);
	buOrderListGrid.updateCell('goodsModel', '', e.record);
	buOrderListGrid.updateCell('goodsSpec', '', e.record);
	buOrderListGrid.updateCell('goodsColor', '', e.record);
	buOrderListGrid.updateCell('goodsPrice', '', e.record);
	buOrderListGrid.updateCell('goodsMoney', '', e.record);
	buOrderListGrid.updateCell('goodsTaxPrice', '', e.record);
	buOrderListGrid.updateCell('goodsAllMoney', '', e.record);
	buOrderListGrid.updateCell('goodsTaxMoney', '', e.record);
	buOrderListGrid.updateCell('remark', '', e.record);
}





function f_selectGoods() { 
    $.ligerDialog.open({ title: '选择商品', width: 1024, height: 600, url: 'app/purchase/select_goods_list_with_warn.jsp', buttons: [
        { text: '确定',onclick: f_selectGoodsOK },
        { text: '取消',onclick: f_selectGoodsCancel }
    ]
    });
    return false;
}
function f_selectGoodsOK(item, dialog)
{
    var data = dialog.frame.f_select();
    if(data[0].id){
    	buOrderListGrid.updateCell('goodsId', data[0].id, buOrderListGrid.getSelected());
    	buOrderListGrid.updateCell('goodsSn', data[0].goods_sn, buOrderListGrid.getSelected());
    	buOrderListGrid.updateCell('goodsPinyin', data[0].goodsPinyin, buOrderListGrid.getSelected());
    	buOrderListGrid.updateCell('goodsCode', data[0].goods_code, buOrderListGrid.getSelected());
    	buOrderListGrid.updateCell('goodsName', data[0].goods_name, buOrderListGrid.getSelected());
    	buOrderListGrid.updateCell('goodsUnit', data[0].goods_unit, buOrderListGrid.getSelected());
    	buOrderListGrid.updateCell('goodsModel', data[0].goods_model, buOrderListGrid.getSelected());
    	buOrderListGrid.updateCell('goodsSpec', data[0].goods_spec, buOrderListGrid.getSelected());
    	buOrderListGrid.updateCell('goodsColor', data[0].goods_color, buOrderListGrid.getSelected());
    	buOrderListGrid.updateCell('goodsPrice', data[0].goods_purchase_price, buOrderListGrid.getSelected());
    	
    	for(var i = 1 ; i < data.length ; i++){
	    	var grid = gridConfig["buOrderList"].manager;
	    	grid.addEditRow({
	    		goodsId:data[i].id,
	    		goodsSn:data[i].goods_sn,
	    		goodsPinyin:data[i].goodsPinyin,
	    		goodsCode:data[i].goods_code,
	    		goodsName:data[i].goods_name,
	    		goodsUnit:data[i].goods_unit,
	    		goodsModel:data[i].goods_model,
	    		goodsSpec:data[i].goods_spec,
	    		goodsColor:data[i].goods_color,
	    		goodsPrice:data[i].goods_purchase_price,
	    		goodsNum : '1',
	    		goodsTax : '0.00'
	    	}) 
	    	
    	}
    	buOrderListGrid.endEdit();
    	var data = buOrderListGrid.getData();
    	for(var i=0;i<data.length;i++){
    		updatePrice1(data[i],data[i].goodsPrice,i);
		}
    	
        dialog.close();
    }
}
function f_selectGoodsCancel(item, dialog)
{
    dialog.close();
}


