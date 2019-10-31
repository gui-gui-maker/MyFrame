<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%
DateFormat ds = new SimpleDateFormat("yyyyMMdd");
String date = ds.format(new Date());
%>
    <title></title>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<link href="app/approve/jl133/Source/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"> 
<!-- <link rel="stylesheet" href="app/approve/jQueryTimer/css/style.css" /> -->
<script src="app/approve/jl133/Source/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="app/approve/jl133/Source/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="app/approve/jl133/Source/lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="app/approve/jl133/Source/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
<script src="app/approve/jl133/Source/lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
<script src="app/approve/jl133/Source/lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
<style>
	.l-page-title2{
		padding:10px 0 0 0;
	}
	.l-grid-detailpanel-inner{
		height:200px;
	}
	.calendarWarp {
		width:15%;
		margin-left:15px;
		float:left;
	}
	.calendarWarp label{
		display:block;
		float:left;
		height:22px;
		line-height:22px;
	}
	.calendarWarp .l-text-wrapper{
		float:left;
	}
</style>
<script type="text/javascript">

        var g;
        var rows_data;
		/* $(function(){
			 f_showCustomers();
			 $.getJSON("sysRecordAction/getRecords.do",{},function(data){
	         	 if(data.success){
	         		rows_data = data.data;
	                g.loadData({
						Rows : rows_data
					});
	         	 }
	         });      
		});
         */

$(function(){
    $("#pageloading").hide();
	f_showCustomers();
})
        function f_showCustomers()
        {
            g = $("#maingrid").ligerGrid({
                columns: [
                { display: 'id', name: 'id', align: 'left',frozen:true },
                { display: '操作人', name: 'account' ,minWidth:180},
                { display: '地址', name: 'ip_address' ,minWidth:200},
                { display: '时间', name: 'op_time',minWidth:200,isSort:true},
                { display: '操作类型', name: 'action',minWidth:200,
                	render : function (record, rowindex, value, column) {
		            		var text = '';
		            		if(value=='add'){
		            			text = '添加';
		            		}else if(value=='update'){
		            			text='修改';
		            		}else if(value=='delete'){
		            			text='删除';
		            		}
		        	        return text;
        	     		}},
                { display: '对象', name: 'bean_name',minWidth:260,
        	      render : function (record, rowindex, value, column) {
            		var text = '';
            		if(value=='com.lsts.approve.bean.CertificateRule'){
            			text = '检验报告签字分配规则';
            		}else if(value=='com.lsts.approve.bean.CertificateBy'){
            			text='检验报告授权签字人';
            		}
        	        return text;  //返回此单元格显示的HTML内容(一般根据value和row的内容进行组织)
        	     }},
                { display: '表名称', name: 'table_name',minWidth:250,frozen:true},
                { display: '表记录', name: 'business_id',minWidth:250,frozen:true},
                { display: '旧表记录', name: 'old_record',frozen:true },
                { display: '新表记录', name: 'new_record' ,frozen:true}
                ],   
				isScroll: false, 
				frozen:false,
				/* toolbar: { 
							items: [{ text: '自定义查询', click: itemclick, icon: 'search2'}]
				            }, */
				pageSizeOptions: [3,10, 20, 30, 40, 50, 100], 
				usePager:true,
				page:"1",
		        pageSize:10,  
				/* data:{Rows:[]}, */
                showTitle: false,
                width:'98%',
                columnWidth:100,
                detail: { onShowDetail: f_show},
                detailHeight:200,
                onError: function (a, b)
                { 
                },
                dataAction:"server",
                url:"sysRecordAction/getRecords.do",
            });
            
        }
        function itemclick()
            {
                g.options.data = $.extend(true,{}, rows_data);
                g.showFilter();
            }
        function f_getData(row)
        {
            var data = { Rows: [] };
            var old_record = row.old_record;
            var new_record = row.new_record;
            if(old_record){
            	var row = JSON.parse(old_record);
            	
	            data.Rows.push(row);
            }
            if(new_record){
            	data.Rows.push(JSON.parse(new_record));
            }
            return data;
        }
        //显示顾客订单
        function f_show(row, detailPanel,callback)
        {
            var grid = document.createElement('div'); 
            $(detailPanel).append(grid);
            if(row.bean_name=='com.lsts.approve.bean.CertificateBy'){
            	$(grid).css('margin',10).ligerGrid({
                    columns:
                                [
                                { display: 'id', name: 'id',type:'text',width:1,hide:true },
                                { display: '用户id', name: 'user_id', width: 1,hide:true },
                                { display: '用户账号', name: 'user_account',width:1,hide:true },
                                { display: '姓名', name: 'user_name',
                                	render : function (record, rowindex, value, column) {
	                            		var previous = this.getRow(rowindex-1);
	                            		if(previous){
	                            			if(previous.user_name != value){
	                            				return "<span style='color:red'>"+value+"</span>";
	                            			}
	                            		}
	                        	        return value;  //返回此单元格显示的HTML内容(一般根据value和row的内容进行组织)
                        	     }},
                                { display: '部门id', name: 'deptId' ,width:1,hide:true },
                                { display: '部门', name: 'dept',width:1,hide:true  },
                                { display: '设备类型id', name: 'deviceTypeId',width:1,hide:true  },
                                { display: '设备类型', name: 'deviceType',width:180, },
                                { display: '是否是主签人员', name: 'is_substitute_person',
                                	render : function(record, rowindex, value, column)
		                                	{
			                            		 var text = '';
			                            		 if(value=='1'){
			                            			 text = '是';
			                            		 }else if(value=='0'){
			                            			 text='否';
			                            		 }
			                            		 var previous = this.getRow(rowindex-1);
			                            		 if(previous){
			                            			 if(previous.is_substitute_person != value){
			                            				 return "<span style='color:red'>"+text+"</span>";
			                            			 }
			                            		 }
			                        	         return text;  
				                    	    } 
                                },
                                { display: '可签部门id', name: 'mayCertDeptId' ,width:1,hide:true },
                                { display: '可签部门', name: 'mayCertDept',width:1,hide:true  },
                                { display: '百分比', name: 'percentage' ,width:1,hide:true },
                                { display: '报告id', name: 'report_id' ,width:1,hide:true },
                                { display: '报告名称', name: 'report_name',width:200 },
                                { display: '状态', name: 'status' ,
                                	render : function (record, rowindex, value, column) {
	                                		var text = '';
	                                		if(value=='0'){
	                                			text = '停用';
	                                		}else if(value=='1'){
	                                			text='启用';
	                                		}
	                                		var previous = this.getRow(rowindex-1);
	                                		//console.log(previous);
	                                		if(previous){
	                                			if(previous.status != value){
	                                				return "<span style='color:red'>"+text+"</span>";
	                                			}
	                                		}
                                	        return text;  //返回此单元格显示的HTML内容(一般根据value和row的内容进行组织)
                                	     }},
                                { display: '是否在岗', name: 'is_vacation',
                                  render : function(record, rowindex, value, column){
	                            		var text = '';
	                            		if(value=='1'){
	                            			text = '请假';
	                            		}else if(value=='0'){
	                            			text='在岗';
	                            		}
	                            		var previous = this.getRow(rowindex-1);
	                            		if(previous){
	                            			if(previous.is_vacation != value){
	                            				return "<span style='color:red'>"+text+"</span>";
	                            			}
	                            		}
	                        	        return text;  
                        	     }},
                                { display: 'fk', name: 'fk_rule',width:1,hide:true }
                                ],
                    isScroll: false,
                    showToggleColBtn: false, 
                    width: '98%',
                    data: f_getData(row), 
                    showTitle: false,
                    columnWidth: 150, 
                    onAfterShowData: callback,
                    frozen:false
                });  
            }else if(row.bean_name=='com.lsts.approve.bean.CertificateRule'){
            	$(grid).css('margin',10).ligerGrid({
                    columns:
                                [
                                { display: 'id', name: 'id',width:1,hide:true },
                                { display: '设备大类id', name: 'device_classify_id', width:1,hide:true  },
                                { display: '设备类型', name: 'device_classify_name',width:150},
                                { display: '部门id', name: 'dept_id',width:1,hide:true  },
                                { display: '部门', name: 'dept',width:150},
                                { display: '报告id', name: 'report_id' ,width:1,hide:true },
                                { display: '报告名称', name: 'report_name',width:240},
                                { display: '分配规则', name: 'certificate_rule',
                                  render : function(record, rowindex, value, column){
	                            		var text = '';
	                            		if(value=='1'){
	                            			text = '量少优先分配';
	                            		}else if(value=='2'){
	                            			text='随机分配 ';
	                            		}else if(value=='3'){
	                            			text='指定分配人 ';
	                            		}
	                            		var previous = this.getRow(rowindex-1);
	                            		if(previous){
	                            			if(previous.certificate_rule != value){
	                            				return "<span style='color:red'>"+text+"</span>";
	                            			}
	                            		}
	                        	        return text;  
                        	     }},
                                { display: '是否可签本部门', name: 'is_allow_self',
                        	    	 render : function(record, rowindex, value, column){
	                            		var text = '';
	                            		if(value=='0'){
	                            			text = '否';
	                            		}else if(value=='1'){
	                            			text='是 ';
	                            		}
	                            		var previous = this.getRow(rowindex-1);
	                            		if(previous){
	                            			if(previous.is_allow_self != value){
	                            				return "<span style='color:red'>"+text+"</span>";
	                            			}
	                            		}
	                        	        return text;  
	                    	     }},
                                { display: '是否匹配相同单位', name: 'is_same_unit',
	                    	    	 render : function(record, rowindex, value, column){
	                            		var text = '';
	                            		if(value=='0'){
	                            			text = '否';
	                            		}else if(value=='1'){
	                            			text='是 ';
	                            		}
	                            		var previous = this.getRow(rowindex-1);
	                            		if(previous){
	                            			if(previous.is_same_unit != value){
	                            				return "<span style='color:red'>"+text+"</span>";
	                            			}
	                            		}
	                        	        return text;  
	                    	     }}
                                ], isScroll: false, showToggleColBtn: false, width: '98%',
                    data: f_getData(row) , showTitle: false, columnWidth: 100
                     , onAfterShowData: callback,frozen:false
                });  
            }
           
        }
        
  
</script>
 
 
<body>
	<div class="l-page-title2 has-icon has-note" style="height:30px">
		<div class="calendarWarp"  style="width:16%;">
		    操作人：<input type="text" name="account" class='ECalendar' id="account"  value=""/>
		</div>
		<div class="calendarWarp"  style="width:10%;">
		    操作类型：<select name="action" class='ECalendar' id="action"  value="">
		    	<option value=""></option>
		    	<option value="add">添加</option>
		    	<option value="update">修改</option>
		    	<option value="delete">删除</option>
		    </select>
		</div>
		<div class="calendarWarp"  style="width:16%;">
		    对象：<select name="bean_name" class='ECalendar' id="bean_name"  value="">
		    	<option value=""></option>
		    	<option value="com.lsts.approve.bean.CertificateRule">检验报告签字分配规则</option>
		    	<option value="com.lsts.approve.bean.CertificateBy">检验报告授权签字人</option>
		    </select>
		</div>
		<div class="calendarWarp" style="width:30%;">
		   <label>时间：从  </label><input type="text" name="op_time" id="op_time1"  value=""/>
		    <label>到  </label><input type="text" name="op_time" id="op_time2"  value=""/>
		</div>
		<div class="calendarWarp" style="width:12%;">
		    <input id="search" type="button" style="width:100px;" class='ECalendar' click="search();"  value="查询"/>
		</div>
	</div>        
	<div style="display: none;"></div>
	<div class="l-loading" style="display: block" id="pageloading">
	</div>
	<div id="maingrid"></div>
	<br> 
	<div style="display:none;"></div>
	
 
<script type="text/javascript">
    $(function(){
        $("#op_time1").ligerDateEditor({
        	 format: "yyyy-MM-dd hh:mm:ss",
        	 showTime: true
        });
        $("#op_time2").ligerDateEditor({
        	 format: "yyyy-MM-dd hh:mm:ss",
        	 showTime: true
        });
    })
    $('#search').bind('click',function(){
    	search();
    });
    function search(){
    	var account = $('#account').val();
    	var action = $('#action').val();
    	var bean_name = $('#bean_name').val();
    	var op_time1 = $("#op_time1").val();
    	var op_time2 = $("#op_time2").val();
    	var searchObject = {
	    		account:account,
	    		action:action,
	    		bean_name:bean_name,
	    		op_time1:op_time1,
	    		op_time2:op_time2
    		};
    
    	 $.ajax({
             type : 'post',
             url : 'sysRecordAction/searchRecords.do',
             dataType : 'json',
             contentType : 'application/json',
             data : JSON.stringify(searchObject),
             beforeSend : function () {
                 // ....
             },
             success : function (data) { // 返回的RequestResult的json对象
            	 if(data.success){
                     g.loadData({
     					Rows : data.data
     				});
             	 }else{
             		 alert(data.msg);
             	 }
             },
         });
    	
    }
</script> 
</body>
</html>