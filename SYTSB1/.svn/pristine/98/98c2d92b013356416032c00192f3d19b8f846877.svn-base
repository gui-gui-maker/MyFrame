<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>详情页面</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript">
	var g;
	var categories = <u:dict code="lib_category"/>;
	var categories2 = <u:dict code="lib_category2"/>;
	var r = new RegExp("^[A-Z]+-[A-Z]-[0-9]{3}-[0-9]{2}$");
	$(function () {
		initGrid(api.data.books);
		initGridData(api.data.bookIds);
		$("#formObj").initForm({    //参数设置示例
			toolbar : [ {
				text : '保存',
				id : 'save',
				icon : 'save',
				click : save
			},
			{
				text : '关闭',
				id : 'close',
				icon : 'cancel',
				click : function close(){	
						 	api.close();
						}
			}],
			success: function (response) {
				if(response.success){
					top.$.notice("保存成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}
				else{
					$.ligerDialog.error("操作失败！<br/>" + response.msg);
				}
			},
			afterParse : function(){
				$("#bookQrcode").bind("keyup",function(e){
					if(r.test(this.value)||e.keyCode==13){
						$.post("lib/book/getBookByCode.do",{qrcode:this.value,act:'2'},function(res){
							if(res.success){
								if(res.data){
									if(res.data.status!='1'){
										top.$.dialog.notice({content:'未上架图书！'});
										return;
									}
									g.addRow(res.data);
								}
								//清除当前二维码
								$("#bookQrcode").val("");
							}else{
								$.ligerDialog.error("操作失败！<br/>" + res.msg);
							}
						});
					}
				});
			}
		});
		
	});
	function save(){
		
		//验证
        if(!validateGrid(g)){
        	return false;
		}
        if(g.getData().length==0){
        	top.$.dialog.notice({content:'至少添加一条图书信息！'});
        	return false;
        }
        var ids = [];
        var rows = g.getData();
        for(var i in rows){
        	ids.push(rows[i].id);
        }
        $("body").mask("正在保存数据，请稍后！");
        $.ajax({
            url: "lib/book/unstack.do",
            type: "POST",
            datatype: "json",
           	data: {'bookIds':ids.join(",")},
            success: function (data, stats) {
            	$("body").unmask();
                if (data["success"]) {
                	if(api.data.window.Qm){
                		api.data.window.Qm.refreshGrid();
                	}
                    top.$.dialog.notice({content:'保存成功'});
                    api.close();
                }else{
                	$.ligerDialog.error('提示：' + data.msg);
                	$("#save").removeAttr("disabled");
                }
            },
            error: function (data,stats) {
            	$("body").unmask();
                $.ligerDialog.error('提示：' + data.msg);
                $("#save").removeAttr("disabled");
            }
        });
	}
	function del(obj, index) {
		var data = obj.getSelectedRow();
		var dataId = data.id;
		$.ligerDialog.confirm("确定要移除吗？", function(yes) {
			if (yes) {
				obj.deleteRow(index);
			}
		});
	}
	function initGridData(ids){
		if(ids&&ids.length>0){
			$.post("lib/book/getBooks.do",{"ids":ids.join(",")},function(res){
				if(res.success){
					g.loadData({Rows:res.data}); 
				}else{
					top.$.dialog.notice({content:'初始化数据错误：'+res.msg});
				}
			});
		}
	}
	
	//初始化表格
	function initGrid() {
		
		g = $("#grid").ligerGrid({
			columns : [
			            {display: "<a class='l-a l-icon-del' href='javascript:void(0);' title='删除'><span>删除</span></a>", 
						isSort: false, minWidth:30,width: '5%',height:'5%',render: function (rowdata, index, value ) {
									var h = "";
									if (!rowdata._editing) {
										h += "<a class='l-a l-icon-del' href='javascript:del(g,"+index+")' title='删除'><span>删除</span></a> ";
									}
									return h;
								}
			           }, 
			           {display : 'id', name : 'id', width : '1', hide : true}, 
			           {display : '二维码', name : 'qrcode', width : '15%'}, 
			           {display : '书名', name : 'name', width : '30%'},
			           {display : '类别', name : 'category', width : '15%',
			        	   render : function(rowdata) {
			        		   for (var i = 0; i < categories.length; i++) {
									if (rowdata["category"] == categories[i].id) {
										return categories[i].text;
									}
							   }
						   }
			           }, 
			           {display : '类型', name : 'content', width : '10%',
			        	   render : function(rowdata) {
			        		   for (var i = 0; i < categories2.length; i++) {
									if (rowdata["content"] == categories2[i].id) {
										return categories2[i].text;
									}
							   }
						   }
			           }, 
					   {display : '书架',name : 'currentPosition',width : '15%'}
			           ],
			rownumbers : true,
			height : "320",
			width : "98%",
			frozenRownumbers : false,
			usePager : false,
			data : {Rows : []},
			onSelectRow : function(rowdata, rowid, rowobj) {

			}
		});
	}
</script>
</head>
<body>
	<form id="formObj" action="">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>下架操作</div>
			</legend>
			<table cellpadding="3" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">扫描图书二维码：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="bookQrcode"
							id="bookQrcode" type="text" ltype="text" value="" />
					</td>
				</tr>
			</table>
		</fieldset>

		<fieldset class="l-fieldset" style="height: 500px">
			<legend class="l-legend">图书列表 </legend>
			<div id="grid" style="height: 400px;"></div>
		</fieldset>
	</form>
</body>
</html>
