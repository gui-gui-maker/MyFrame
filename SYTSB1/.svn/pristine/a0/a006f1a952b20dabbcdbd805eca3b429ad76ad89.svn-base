<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>详情页面</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="/pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var g;
	var categories = <u:dict code="lib_category"/>
	var categories2 = <u:dict code="lib_category2"/>
	var books = [];
	var r = new RegExp("^[A-Z]+-[A-Z]-[0-9]{3}-[0-9]{2}$");
	$(function () {
		initGrid();
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
				$("#qrcode").bind("keyup",function(e){
					var htm = $("#qrcode").val();
					if(r.test(htm)){
						$.post("lib/book/getBookByCode.do",{qrcode:this.value,act:"3"},function(res){
							if(res.success){
								var flag = false;
								for(var i in books){
									if(res.data.id == books[i].id){
										flag = true;
									}
								}
								if(!flag){
									books.push(res.data);
									g.addRow(res.data);
								}
								$("#qrcode").val("");
							}else{
								$.ligerDialog.error("操作失败！<br/>" + res.msg);
							}
						});
					} 
				});
			}
		});
	});
	//保存
	function save(){
		
		//验证表单数据
		if($('#formObj').validate().form()){
			
			var formData = $("#formObj").getValues();
	        if(!validateGrid(g)){
	        	return false;
			}
	        if(g.getData().length==0){
	        	top.$.dialog.notice({content:'至少添加一条图书信息！'});
	        	return false;
	        }
	        formData["books"] = g.getData(); 
	        $("body").mask("正在保存数据，请稍后！");
	        $.ajax({
	            url: "lib/borrow/saveBorrow.do",
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	           	data: $.ligerui.toJSON(formData),
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
	}
	
	//初始化表格数据
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
			           {display: "<a class='l-a iconfont l-icon-del' href='javascript:void(0);' title='删除'><span>删除</span></a>", 
							isSort: false, minWidth:30,width: '5%',height:'5%',
							render: function (rowdata, index, value ) {
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
	function selectUser(){
        /* top.$.dialog({
            width: 800,
            height: 450,
            lock: true,
            parent: api,
            title: "选择人员",
            content: 'url:app/common/user_choose.jsp',
            cancel: true,
            ok: function(){
                var p = this.iframe.contentWindow.getSelectedPerson();
                if(!p){
                    top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                    return false;
                }
                $("#borrowedMan").val(p.userid);
                $("#borrowedManName").val(p.name);
            }
        }); */
		selectUnitOrUser("4",0,"","",function(datas){
			if(!datas.code)return;
			$.post("lib/book/getUserByEmpid.do",{"empid":datas.code},function(res){
				if(res.success){
					$("#borrowedMan").val(res.data[0][0]);
		            $("#borrowedManName").val(res.data[0][1]);
				}
			});
		 	
		});
    }
</script>
</head>
<body>
	<form id="formObj" action="">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>借阅信息</div>
			</legend>
			<table cellpadding="3" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">图书二维码：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="qrcode"
							id="qrcode" type="text" ltype="text" value="" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">借阅人：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="borrowedMan" id="borrowedMan" type="hidden"/>
						<input name="borrowedManName" id="borrowedManName" type="text" ltype="text" validate="{required:true}"
							ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectUser()}}]}" 
							onclick="selectUser()" />
					</td>
				</tr>
				<tr>
				 	<td class="l-t-td-left">期限：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="timeLimit"
							id="timeLimit" type="text" ltype="number" value="30"
							validate="{required:true,digits:true,maxlength:3}"
							ligerui="type:'int',isNegative:false,suffix:'天',suffixWidth:'15'" />
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
