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
        var tabId="${param.tableId}";
        if(tabId=="")
        {

            	parent.setTab();
        }else{
	            bearProp();
	            $("#form2").initForm({//参数设置示例
	                toolbar:[   
	                    {text:'保存', id:'saveSysFormTable',icon:'save', click:save },
	                       {text:'清空', id:'add',icon:'add', click:add },
	                 {text:'删除', id:'delSysFormTable',icon:'del', click:del }
	                ], 
	                getSuccess:function(res){    
	                     $("#id").val(null);
	                    
	                          manager.loadData({Rows:res.list});   
	             
	                }     
	            });
            }
         }); 
           function add()
           {
                 document.location.reload();
                 
           }
          function save() { 
             $.ligerDialog.confirm('保存成功后，会重新设计表单，是否保存数据？', function (yes)
              {
                if(yes){   
                  var pageData = $("#form2").getValues();                                      
 		          $.ajax({
                  url: "pub/form/table/saveColumn.do",
                  type: "POST",
                  datatype: "json",
                  async: false,
                  contentType: "application/json; charset=utf-8",
                  data: $.ligerui.toJSON(pageData),
                  success: function (data, stats) {
                  if (data["success"]) {
                        top.$.dialog.notice({content:'操作成功！'});
                         document.location.reload();
                        }else{
                        $.ligerDialog.error('保存失败！' + data.msg);
                         }
                         },
                      error: function (data) {
                             $.ligerDialog.error('保存失败！' + data.msg);
                         }
                      });
                    }});                                
          }   
	        var propTypes =[{'id':'string','text':'字符串'},{'id':'number','text':'数字'},{'id':'date','text':'日期'}]; 
	        var manager; 
           function bearProp() {
            var columns=[
                  { display: '编号', name: 'id', width: '5%',hide:true},
                  { display: 'tableId', name: 'tableId', width: '5%',hide:true},
                 { display: '字段名', name: 'columm', width: '25%'},
                 { display: '注释', name: 'columnDisplay', width: '25%'},
              
                 { display: '字段类型', width: '10%', name: 'dataType', type: 'text'
                    
                 },
                 { display: '是否为空', name: 'allownull', width: '5%', align :'center',type: 'int', editor: { type: 'checkbox'},
                     render: function (item) {
                         return '<a class="l-checkbox'+(item["allownull"]=="1"?" l-checkbox-checked":"")+'" style="margin-top:3px;margin-left:5px;"></a>';
                     }
                 }, { display: '长度', name: 'length', width: '20%'}
            ]
       
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
                colDraggable:false ,
                rowDraggable:false, 
                isScroll: true, 
        
                usePager: false,
                data: {Rows: []},
                 onSelectRow : function(rowdata, rowindex) {
            
            	$("#form2").setValues(rowdata);
			
			}
            });

        }
	     function del()
	     {
	     if( $("#id").val()!="")
	     {
	              $.ligerDialog.confirm('会删除相关的数据,若刪除成功，是否删除所选择的行？', function (yes)
                     {
                     if(yes){
                          $.ajax({
		                  url: "pub/form/table/deleteColumn.do",
		                  type: "POST",
		                  data: {tableId: "${param.tableId}", ids: $("#id").val()},
		                  success: function (data, stats) {
		                  if (data["success"]) {
	                        top.$.dialog.notice({content:'删除成功！'});
	                        $.ligerDialog.success('请在表单设计中对表单进行重新设计！');
	                         document.location.reload();
	                        }else{
	                        $.ligerDialog.error('保存失败！' + data.msg);
	                         }
	                         },
	                      error: function (data) {
	                             $.ligerDialog.error('保存失败！' + data.msg);
	                         }
                        }); 
                     }
                    })
	    
	     }else{
	       $.ligerDialog.error('删除列不能为空，请在列表中选择删除的行！' ); 
	     }
     		
     }
    </script>

	</head>
	<body>
		
		<form id="form2" action="pub/form/table/saveColumn.do"
			getAction="pub/form/table/detail.do?id=${param.tableId}" method="post"
			name="form2">
			<table border="0" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
			<tr>

					<input type="hidden" name="tableId" id="tableId" value="${param.tableId}"/>
					<input type="hidden" name="id" id="id"/>
					<td class="l-t-td-left">
						字段名：
					</td>
					<td class="l-t-td-right">
						<input name="columm" id="columm" ltype='text'
							validate="{maxlength:50,required:true}" />
					</td>
					<td class="l-t-td-left">
						注释：
					</td>
					<td class="l-t-td-right">
						<input name="columnDisplay" id="columnDisplay" ltype='text'
							validate="{maxlength:50,required:true}" />
					</td>
				

				</tr><tr>
					<td class="l-t-td-left">
						数据类型：
					</td>
					<td class="l-t-td-right">
			        
				
				<u:combo name="dataType"
						code="data_type" />
					</td>
					</tr>
				<tr>

					<td class="l-t-td-left">
						长度：
					</td>
					<td class="l-t-td-right">
						<input name="length" id="length" ltype='text'
							validate="{maxlength:50,required:true}" />
					</td>
					<td class="l-t-td-left">
						是否为空：
					</td>
					<td class="l-t-td-right">

						<u:combo name="allownull" code="sys_sf" ltype="radioGroup"
							validate="required:false" attribute="initValue:'1' " />
					</td>
				</tr>
			</table>
<div id="prop"></div>
		
		</form>
			
	</body>
</html>
