<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
		var pageStatus = "${param.status}";
		var paramInfoGrid;
		var sfs= <u:dict code='sys_sf'/>;
		var paramTypes =  <u:dict code='chartParaType'/>;
		var dataTypes = [
		                  {id: "string", text: "字符串"},
		                  {id: "number", text: "数字"},
		                  {id: "boolean", text: "布尔型"},
		                  {id: "color", text: "颜色"},
		                  {id: "select", text: "下拉"},
		                  {id: "json", text: "json格式"}
		              ];
		$(function() {
			createGrid();
			$("#form1").initForm({ //参数设置示例
				toolbar : [ {
					text : '保存',
					id : 'save',
					icon : 'save',
					click : function(){
						//验证grid
				        if(!validateGrid(paramInfoGrid))
						{
							return false;
						}
				        var gridData =paramInfoGrid.getData();
				        for(var i in gridData){
				        	gridData[i]["bparamId"] = "${param.bparamId}"
				        }
				        $("body").mask("正在保存数据，请稍后！");
				        $.ajax({
				            url: "chart/param/saveParam.do",
				            type: "POST",
				            datatype: "json",
				            contentType: "application/json; charset=utf-8",
				           	data: $.ligerui.toJSON(gridData),
				            success: function (data, stats) {
				            	$("body").unmask();
				                if (data["success"]) {
				                	top.$.dialog.notice({content:'保存成功'});
				                    api.data.window.reloadDataGrid();
				                    api.close();
				                }else
				                {
				                	$.ligerDialog.error('提示：' + data.msg);
				                }
				            },
				            error: function (data,stats) {
				            	$("body").unmask();
				                $.ligerDialog.error('提示：' + data.msg);
				            }
				        });
					}
				}
				, 
				{
					text : '关闭',
					id : 'close',
					icon : 'cancel',
					click : function(){
						api.close();
					}
				} ],
				getSuccess : function(res) {
					if(res.Rows)
					{
						paramInfoGrid.loadData({
							Rows : res.Rows
						});
					}
				}
			});
		})
		function createGrid() {
		    var columns=[
		        { display: 'id', name: 'id', hide:true},
		        { display: 'bparamId', name: 'bparamId', hide:true},
		        { display: '参数名称', width: '10%', name: 'name', type: 'text',required:true,editor: { type: 'text'},maxlength:64,align : 'left'},
		        { display: '参数代码', width: '10%', name: 'code', type: 'text',required:true,editor: { type: 'text'},maxlength:64,align : 'left'},
		        { display: '参数类别', width: '10%', name: 'paramType', type: 'text',required:true,align : 'left',editor: { type: 'select', data: paramTypes ,ext:{emptyOption:false}},
		            render: function (item) {
		                return render(item["paramType"],paramTypes);
		            }
		        },
		        { display: '参数类型', width: '4%', name: 'colType', type: 'text',required:true,align : 'left',editor: { type: 'select', data: dataTypes ,ext:{emptyOption:false}},
		            render: function (item) {
		                return render(item["colType"],dataTypes);
		            }
		        },
		        { display: '参数长度', width: '4%', name: 'colLength', type: 'int',required:true,editor: { type: 'spinner'},align : 'left'},
		        { display: '参数默认值', width: '6%', name: 'colDefvalue', type: 'text',editor: { type: 'text'},maxlength:256,align : 'left'},
		        { display: '参数值范围1', width: '7%', name: 'colSmall', type: 'int',editor: { type: 'spinner'},align : 'left'},
		        { display: '参数值范围2', width: '7%', name: 'colBig', type: 'int',editor: { type: 'spinner'},align : 'left'},
		        { display: '绑定值', width: '10%', name: 'bindData', type: 'textarea',editor: { type: 'textarea',height:'80'},maxlength:256,align : 'left'},
		        { display: '参数描述', name: 'remark', width: '13%', editor: { type: 'textarea',height:'80'},maxlength:512,align : 'left',render:function(data){
					return "<div title='"+data.remark+"'>"+data.remark+"</div>"
				}},
		        { display: '参数排序', name: 'colSort', width: '4%', editor: { type: 'text'},maxlength:20,align : 'left'},
		        { display: '是否禁用', name: 'status', width: '4%',required:true,
		        	editor: { type: 'select', data: sfs ,ext:{emptyOption:false}},
		            render: function (item) {
		                return render(item["status"],sfs);
		            }
		        }
		    ];
		    if(pageStatus!="detail"){
		        columns.push({ display: "<a class='l-a l-icon-add' href='javascript:void(0);' onclick='addNewRow(\"degree\")' title='增加'><span>增加</span></a>", isSort: false, width: '4%', render: function (rowdata, rowindex, value) {
		            var h = "";
		            if (!rowdata._editing) {
		                h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"degree\")' title='删除'><span>删除</span></a> ";
		            }
		            return h;
		        }
		        });
		    }
		    paramInfoGrid = $("#paraminfo").ligerGrid({
		    	columns: columns,
		        enabledEdit: pageStatus!="detail",
		        clickToEdit: true,
		        rownumbers: true,                         //是否显示行序号
		        frozenRownumbers: false,
		        usePager: false,
		        data: {Rows: [
		        ]}
		    });
		}
		function render(value,data){
		    for (var i in data) {
		    	if (data[i]["id"] == value)
		        {
		        	return data[i]['text'];
		        }
		    }
		    return value;
	   }
		function addNewRow(name) {
			paramInfoGrid.addEditRow({colType:'string',colLength:'256',status:'0'}); //添加一行
		}
		function deleteRow(name) {
		    var data = paramInfoGrid.getSelectedRow();
		    if (data.id) {
		        $.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
		            if (yes) {
		                $.getJSON("chart/param/delete.do", {ids: data.id}, function (json) {
		                    if (json.success) {
		                    	paramInfoGrid.deleteSelectedRow();
		                    }
		                });
		            }
		        });
		    } else {
		    	paramInfoGrid.deleteSelectedRow();
		    }
		}
</script>
</head>
<body>
	<form id="form1" action=""
		getAction="chart/param/getParam.do?bparamId=${param.bparamId}">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>详细参数设计</div>
			</legend>
			<div id="paraminfo"></div>
		</fieldset>
	</form>
</body>
</html>
