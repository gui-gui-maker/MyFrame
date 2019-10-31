
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head pageStatus="${param.pageStatus}">
		<title></title>
		<!-- 每个页面引入，页面编码、引入js，页面标题 -->
		<%@include file="/k/kui-base-form.jsp"%>
		<script type="text/javascript">

                $(function () {
                	ps = $("head").attr("pageStatus");
//                    如果不设置额外参数，不用调用此方法，初始化时会自动调用
                    $("#form1").initForm({
                    	success: function(res) {
    						if (res.success) {
    						
    							top.$.notice("保存成功！");
    							api.data.window.Qm.refreshGrid();
    						}
    					}, getSuccess: function(res) {
    					
    					}
                    });
             
                })
                

    </script>
	</head>
	<body>
		<div class="navtab">
			<div title="<font>表信息</font>" lselected="true" style="height: 400px">
				<form id="form1" action="pub/form/table/save.do"
					getAction="pub/form/table/detail.do?id=${param.id}"
					method="post" name="form1">


					<table border="0" cellspacing="0" cellpadding="3"
						style="width: 100%; height: 100%" class="l-detail-table">
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
								<input name="tableMome" id="tableMome" ltype='text'
									validate="{maxlength:50,required:true}" />
							</td>
						</tr>

					</table>
				</form>
			</div>

			<div title="列信息">
				<form id="form3" action="pub/form/table/saveColumn.do"
					getAction="pub/form/table/getTableColumnInfo.do?id=${param.id}"
					delAction="pub/form/table/deleteColumn.do" name="form3">
		
							<table border="0" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
			<tr>

					<input type="hidden" name="tableId" id="tableId" value="${param.id}"/>
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

				</form>
				<script type="text/javascript">
          $("#form3").initFormList({id:'columngrid',
             toolbar:[   
               {text:'保存', id:'saveSysFormTable',icon:'save', click:save },
                 {text:'删除', id:'delSysFormTable',icon:'del', click:del }
                ],
            actionParam:{"id":$("#form1>[name=id]")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的id为id的值带上去,可以是一个对象或选择器
       
           onSelectRow:function(rowdata, rowindex) {
            
           $("#form3").setValues(rowdata);
			
			},	
            columns:[
                   { display: '编号', name: 'id', width: '5%',hide:true},
                  { display: 'tableId', name: 'tableId', width: '5%',hide:true},
                 { display: '字段名', name: 'columm', width: '25%'},
                 { display: '显示名', name: 'columnDisplay', width: '25%'},
              
                 { display: '字段类型', width: '10%', name: 'dataType', type: 'text'
                    
                 },
                 { display: '是否为空', name: 'allownull', width: '5%', align :'center',type: 'int', editor: { type: 'checkbox'},
                     render: function (item) {
                         return '<a class="l-checkbox'+(item["allownull"]=="1"?" l-checkbox-checked":"")+'" style="margin-top:3px;margin-left:5px;"></a>';
                     }
                 }, { display: '长度', name: 'length', width: '20%'}
                 ],   data: {Rows: []}
        });
         function save() { 
             $.ligerDialog.confirm('保存成功后，会重新设计表单，是否保存数据？', function (yes)
              {
                if(yes){   
                  var pageData = $("#form3").getValues();                                      
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
                          var manager = $("#columngrid").ligerGetGridManager(); 
                           manager.loadData();
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
			                  data: {tableId: "${param.id}", ids: $("#id").val()},
			                  success: function (data, stats) {
			                  if (data["success"]) {
		                        top.$.dialog.notice({content:'删除成功！'});
		                        $.ligerDialog.success('请在表单设计中对表单进行重新设计！');
		                
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
			</div>
		</div>

	</body>
</html>
