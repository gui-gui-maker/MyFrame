<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
    var qy = [{ state: 1, text: '启用' }, { state: 2, text: '停用'}];
    var code_qy = <u:dict code='TJY2_YJ_STATUS'/>;//是否启用
    var code_sz = <u:dict code="TJY2_BGYJ"/>;//选择环节
    //alert(JSON.stringify(code_sz))
    var code_ts = <u:dict code='TJY2_PUSH_MESSAGE'/>;//是否推送
    var manager, g;
    $(function () {
    	manager = $("#maingrid").ligerGrid({
			columns: [//{ display: '主键', name: 'ids', width: 50, type: 'int'},
	            { display: '开始环节', name: 'flow',width: 120,textModel : true,
					editor: { type: 'select', data: code_sz},
					render : function(rowdata, rowindex, value) {
						for ( var i in code_sz) {
// 							alert("code_sz@@"+code_sz[i]["id"]);
// 							alert("rowdata@@"+rowdata["flow"]);
							if (code_sz[i]["id"] == rowdata["flow"])
								return code_sz[i]['text'];
						}
						return rowdata["flow"];
					}
				},/*{ display: '是否启用', name: 'state',width: 80,type: 'text',
                    editor: { type: 'select', data: qy, valueField: 'state' },
    	            render: function (item){
    					if (parseInt(item.state) == 1) return '启用';
    						return '停用';
    	                }
    	            },*/
	            { display: '结束环节', name: 'flows',width: 120,
					editor: { type: 'select', data: code_sz},
					render : function(rowdata, rowindex, value) {
						for ( var i in code_sz) {
							if (code_sz[i]["id"] == rowdata["flows"])
								return code_sz[i]['text'];
						}
						return rowdata["flows"];
					}
				},
	            { display: '是否启用', name: 'state',width: 80,textModel : true,
					editor: { type: 'select', data: code_qy},
					render : function(rowdata, rowindex, value) {
						for ( var i in code_qy) {
							if (code_qy[i]["id"] == rowdata["state"])
								return code_qy[i]['text'];
						}
						return rowdata["state"];
					}
				},
		        { display: '短信', name: 'duanxinPush',width: 80,textModel : true,
					editor: { type: 'select', data: code_ts},
						render : function(rowdata, rowindex, value) {
							for ( var i in code_ts) {
								if (code_ts[i]["id"] == rowdata["duanxinPush"])
									return code_ts[i]['text'];
							}
							return rowdata["duanxinPush"];
						}
					},
	            { display: '微信', name: 'weixinPush',width: 80,textModel : true,
					editor: { type: 'select', data: code_ts},
						render : function(rowdata, rowindex, value) {
							for ( var i in code_ts) {
								if (code_ts[i]["id"] == rowdata["weixinPush"])
									return code_ts[i]['text'];
							}
							return rowdata["weixinPush"];
						}
					},
	            { display: '天数', name: 'tianshu', width: 80, type: 'int', editor: { type: 'int'} },
	            { display: '操作', isSort: false, width: 150, render: function (rowdata, rowindex, value)
	            {var h = "";
// 	            	if (!rowdata._editing){
// 						h += "<a href='javascript:beginEdit(" + rowindex + ")'>修改</a> ";
// 	                    h += "<a href='javascript:deleteRow(" + rowindex + ")'>删除</a> "; 
// 	                }else{
// 	                    h += "<a href='javascript:endEdit(" + rowindex + ")'>提交</a> ";
// 	                    h += "<a href='javascript:cancelEdit(" + rowindex + ")'>取消</a> "; 
// 	                }
	            	 h += "<a href='javascript:endEdit(" + rowindex + ")'>提交</a> ";
	            	 h += "<a href='javascript:deleteRow(" + rowindex + ")'>删除</a> "; 
	            return h;}
            }],onSelectRow: function (rowdata, rowindex){
				//$("#txtrowindex").val(rowindex);
            },enabledEdit: true,
            rownumbers : true,
            frozenRownumbers: false,
		    	//usePager: false,
              clickToEdit: true, //是否编辑
              isScroll: false,
			  url : 'report/yjsz/detailWork.do',
              width: '100%'
		});   
	});
    function beginEdit(rowid) { //修改
        var ids = manager.getRow(rowid).id;alert(rowid);
//     	if(!ids){
//              $.ligerDialog.error("请先选择要修改的数据！");
//              return;undefined
//          }
// var pattern = /^([0-9.]+)$/;
// var ss=1;
// if (pattern.test(ss)) {
// 	alert(rowid);
// 	rowid="1";
// 	manager.beginEdit(rowid);
// }else{
// 	alert(22);
// 	rowid="1";
// 	manager.beginEdit(rowid);
// }
        manager.beginEdit(rowid);
//         top.$.ajax({
//             url: "report/yjsz/delete1.do?id="+ids,
//             type: "POST",
//             dataType:'json',
//             async: false
//         });
    }
    function cancelEdit(rowid) { //取消
        manager.cancelEdit(rowid);
//         var data = manager.getRow(rowid);
//         top.$.ajax({
//             url: "report/yjsz/save1.do",
//             type: "POST",
//             dataType:'json',
//             async: false,
//             data: data
//         });
    }
    function endEdit(rowid){//提交
    	manager.endEdit(rowid);
    var data = manager.getRow(rowid);
        top.$.ajax({
            url: "report/yjsz/save1.do",
            type: "POST",
            dataType:'json',
            async: false,
            data: data,
            success:function (data) {
            	 if(data.success){
            		 top.$.notice('提交成功！',3);												
                 }else{
                	 $.ligerDialog.error(data.msg,3);	
                 }
            },
            error:function () {
            	$.ligerDialog.error('出错了！请重试！!',3);											
            }
        });
        
    }

    function deleteRow(rowid){
    	var ids = manager.getRow(rowid).id;
        if (confirm('确定删除?')){
            top.$.ajax({
                url: "report/yjsz/delete1.do?id="+ids,
                type: "POST",
                dataType:'json',
                async: false,
                success:function (data) {
                	 if(data.success){
                		 top.$.notice('删除成功！!',3);											
                     }
                },
                error:function () {
                	$.ligerDialog.error('出错了！请重试！!',3);											
                }
            });
            manager.deleteRow(rowid);
        }
    }
    var newrowid = 1;
    function add(){
        manager.add();
    } 
      
    function getSelected(){ 
        var row = manager.getSelectedRow();
        if (!row) { 
        	alert('请选择行'); return; 
        }
        alert(JSON.stringify(row));
    }
    function getData(){ 
        var data = manager.getData();
        alert(JSON.stringify(data));
    } 
    function addNewRow(){
        var row = manager.getSelectedRow();
        //参数1:rowdata(非必填)
        //参数2:插入的位置 Row Data 
        //参数3:之前或者之后(非必填)
        manager.addRow({ 
            state: "2",
            ids: newrowid++,
            flow : 1,
            flows : 7,
            tianshu: 1,
            duanxinPush:"2",
            weixinPush:"2"
        }, row, document.getElementById("chkbefore").checked);
    } 
    </script>
</head>
<style>
.l-panel-bwarp{height:490px}
</style>
<body>
<a class="l-button-warp l-button" onclick="add()"><span class="l-button-main l-button-hasicon"><span class="l-button-text">添加一行</span><span class="l-button-icon l-icon-add"></span></span></a>
<!-- <a class="l-button" style="width:100px;float:left; margin-left:10px;" onclick="addNewRow()">添加行</a>  -->
<input type="checkbox" id="chkbefore" style="visibility:hidden" />
<div id="maingrid" style="margin-top:20px"></div>
  
</body>
</html>