<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>资源编辑</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	var paramInfoGrid;
	var pageStatus = "${param.status}";
	var sf = [{id:'0',text:'否'},{id:'1',text:'是'}]
	$(function(){
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
			        api.data.window.setMparamValue($.ligerui.toJSON(gridData));
			        api.close();
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
	    var columns=[];
	    $.getJSON("chart/bparam/detail.do?id=${param.bparamId}",function(res){
		    if(res.success){
		    	columns.push({ display: 'paramType', name: 'paramType',hide:true});
			    for(var i in res.data.chartParams){
				    var param = res.data.chartParams[i];
				    var column;
				    if(param.colType=='string'||param.colType=='json'){
					   column ={width:'10%', display: param.name, name: param.code,editor: { type: 'text'},maxlength:param.colLength,align : 'center'}
					   columns.push(column);
				    }	
				    if(param.colType=='boolean'){
					    column ={width:'10%', display: param.name, name: param.code,editor: { type: 'select', data: sf ,ext:{emptyOption:false}},maxlength:param.colLength,align : 'center',render:function(rowdata,rowindex,value){
							return render(value,sf);
					    }}
					    columns.push(column);
				    }	
				    if(param.colType=='number'){
					   column ={width:'10%', display: param.name, name: param.code,editor: { type: 'spinner',minValue:param.colSmall,maxValue:param.colBig},maxlength:param.colLength,align : 'center'}
					   columns.push(column);
				    }
				    if(param.colType=='color'){
					   column ={width:'10%', display: param.name, name: param.code,editor: { type: 'text'},maxlength:param.colLength,align : 'center'}
					   columns.push(column);
				    }	
				    if(param.colType=='select'){
				    	column ={width:'10%', display: param.name, name: param.code,editor: { type: 'select',data:eval(param.bindData),ext:{emptyOption:false}},maxlength:param.colLength,align : 'center'}
						columns.push(column);
					}
				    if(i%2==0){
				    	$("#title").append(column.name+":"+param.remark+"</br>")
				    }else{
				    	$("#title").append(column.name+":"+param.remark+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				    }
			    }
			    if(pageStatus!="detail"){
			        columns.unshift({ display: "<a class='l-a l-icon-add' href='javascript:void(0);' onclick='addNewRow(\""+param.paramType+"\")' title='增加'><span>增加</span></a>", isSort: false, width: '4%', render: function (rowdata, rowindex, value) {
			            var h = "";
			            if (!rowdata._editing) {
			                h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"degree\")' title='删除'><span>删除</span></a> ";
			            }
			            return h;
			        }
			        });
			    }
			    paramInfoGrid = $("#gird").ligerGrid({
			    	columns: columns,
			        enabledEdit: pageStatus!="detail",
			        clickToEdit: true,
			        rownumbers: true,  //是否显示行序号
			        frozenRownumbers: false,
			        height:'92%',
			        usePager: false,
			        data: {Rows: [
			        ]}
				   });
		   		}
		   		if(api.data.data!=''&&api.data.data!=undefined&&api.data.data!=null){
			   		paramInfoGrid.loadData({
						Rows : $.parseJSON(api.data.data)
					});
		   		}
		   		$.cSize();
	    })
		    
	}
	function addNewRow(name) {
		paramInfoGrid.addEditRow({paramType:name}); //添加一行
	}
	function deleteRow(name) {
	    paramInfoGrid.deleteSelectedRow();
	  
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
</script>
</head>
<body>
	
<div class="item-tm">
	<div class="l-page-note">
		<div class="l-page-note-div" id="title">
		</div>
	</div>
</div>
<form id="form1">
	<div id="gird"></div>
</form>
</body>
</html>
