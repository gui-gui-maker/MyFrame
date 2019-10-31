<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head pageStatus="${param.pageStatus}">
		<title></title>
		<!-- 每个页面引入，页面编码、引入js，页面标题 -->
		<%@include file="/k/kui-base-form.jsp"%>
		<script type="text/javascript">
        var pageStatus = "${param.pageStatus}";
        $(function () {  
            $("#form1").initForm({//参数设置示例
                toolbar:[   
                    {text:'保存', id:'saveSysFormTable',icon:'save', click:save },
                    {text:'关闭', id:'close',icon:'cancel', click:close } 
                ], 
                getSuccess:function(res){    
                       manager.loadData({Rows:res.list});            
                }       
            });   
            function save() { 
                 manager.endEdit();
         
            	    var data= manager.getData();
                    if(data.length>0)
                    {
                      var itemData=data[data.length-1];
                      if((itemData.columm==undefined)||(itemData.columnDisplay==undefined)||(itemData.dataType==undefined))
                      {
                         $.ligerDialog.error("列信息未填写完整，不能创建表！");
                         return false;
                      }else{
                              $("#columnInfo").val( $.ligerui.toJSON(data));
                              $('#form1').submit(); 
                          }
                    }
       
               } 
         
            function close(){
                api.close();
            }
            bearProp();
        });
        var propTypes =[{'id':'string','text':'字符串'},{'id':'number','text':'数字'},{'id':'date','text':'日期'},{'id':'org','text':'选择机构'}]; 
        var manager; 
        function bearProp() {
            var columns=[
                 { display: '字段名', name: 'columm', width: '10%', editor: { type: 'text'}},
                 { display: '显示名', name: 'columnDisplay', width: '10%', editor: { type: 'text' }},
              
                 { display: '字段类型', width: '10%', name: 'dataType', type: 'text',
                     editor: { type: 'select', data: propTypes },
                     render: function (item) {
                         for (var i in propTypes) {
                         
                             if (propTypes[i]["id"] == item["dataType"])
                                 return propTypes[i]['text'];
                         }
                         return propTypes["text"];
                     }
                 },
                 { display: '是否为空', name: 'allownull', width: '', align :'center',type: 'int', editor: { type: 'checkbox'},
                     render: function (item) {
                         return '<a class="l-checkbox'+(item["allownull"]=="1"?" l-checkbox-checked":"")+'" style="margin-top:3px;margin-left:5px;"></a>';
                     }
                 }, { display: '长度', name: 'length', width: '10%', editor: { type: 'int' }}
            ]
            if(pageStatus!="detail"){
                columns.push({ display: "<a class='l-a l-icon-add' href='javascript:void(0);' onclick='addNewRow()'><span>增加</span></a>", isSort: false, width: '10%', render: function (rowdata, rowindex, value) {
                    var h = "";
                    if (!rowdata._editing) {
                        /* h += "<a class='l-a l-icon-add' href='javascript:void(0);' onclick='addNewRow()'><span>增加</span></a> "; */
                        h += "<a class='l-a l-icon-del' href='javascript:void(0);' onclick='deleteRow(" + rowindex + ")'><span>删除</span></a> ";
                    }
                    else {
                        h += "<a class='l-a l-icon-save' href='javascript:void(0);' onclick='endEdit(" + rowindex + ")'><span>保存</span></a> ";
                        h += "<a class='l-a l-icon-cancel' href='javascript:void(0);' onclick='cancelEdit(" + rowindex + ")'><span>取消</span></a> ";
                    }
                    return h; 
                }
                });
            }
            manager = $("#prop").ligerGrid({
                columns: columns,
                onSelectRow: function (rowdata, rowindex) {
                    $("#txtrowindex").val(rowindex);
                },
                title: '表字段设置',
                enabledEdit: pageStatus!="detail",
                clickToEdit: true,
                rownumbers: true,
                frozenRownumbers: false,
                isScroll: true, 
                usePager: false,
                data: {Rows: []}
            });

        }

        function beginEdit(g) {
            var row = manager.getSelectedRow();  
            if (!row) {
                alert('请选择行');
                return;
            }
            //manager.beginEdit(row);
            var windows=top.$.dialog({
                width:600,
                height:500,
                lock:true,
                title:"修改字段信息",
                content: 'url:create/updatezd_detail.jsp?pageStatus=modify&id=', 
                data: {"window": window}   
            });
        }
        function cancelEdit() {
            var row = manager.getSelectedRow();
            if (!row) {
                alert('请选择行');
                return;
            }
            manager.cancelEdit(row);
        }
        function cancelAllEdit() {
            manager.cancelEdit();
        }
        function endEdit() {
            var row = manager.getSelectedRow();
            if (!row) {
                alert('请选择行');
                return;
            }
            manager.endEdit(row);
        }
        function endAllEdit() {
            manager.endEdit();
        }
        function deleteRow() {
            manager.deleteSelectedRow();
        }
        var newrowid = 100;
        function addNewRow() {
                    var data= manager.getData();
                    if(data.length>0)
                    {
                      var itemData=data[data.length-1];
                      if((itemData.columm==undefined)||(itemData.columnDisplay==undefined)||(itemData.dataType==undefined))
                      {
                         $.ligerDialog.error("列信息未填写完整，不能添加下一列！");
                         return false;
                      }
                    }
		            manager.addEditRow();
        }

 
     
    </script>

	</head>
	<body>
		<form id="form1" action="pub/form/table/saveSysFormTable.do"
			getAction="pub/form/table/detail.do?id=${param.id}" method="post"
			name="form1">
			<table border="0" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
				<tr>
					<input type="hidden" name="id" id="id" />
					<td class="l-t-td-left">
						表名：
					</td>
					<td class="l-t-td-right">
						<input name="tableName" id="tableName"
							<c:if test="${param.pageStatus=='modify'}">readonly="readonly"</c:if>
							ltype='text' validate="{maxlength:50,required:true}" />
					</td>
					<td class="l-t-td-left">
						注释：
					</td>
					<td class="l-t-td-right">
						<input name="tableMome" id="tableMome"
							<c:if test="${param.pageStatus=='modify'}">readonly="readonly"</c:if>
							ltype='text' validate="{maxlength:50,required:true}" />
					</td>
				</tr>
			</table>
			<input type="hidden" name="columnInfo" id="columnInfo"></input>
			<div id="prop"></div>
		</form>
	</body>
</html>
