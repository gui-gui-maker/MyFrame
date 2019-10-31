<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
   	var g = null;
    $(function() {
		$("#form1").initForm({
			        success: function (response) {
						if(response.success){
							top.$.notice("保存成功！",3);
							api.data.window.Qm.refreshGrid();
							api.close();
						}else{
							$.ligerDialog.error("操作失败！<br/>" + response.msg);
						}
			        },
					getSuccess:function(res){
						console.log(res);
						if(res.success){
							var row = res.data;
							if(row.bean_name=='com.lsts.approve.bean.CertificateBy'){
								 show1(f_getData(row));
				            }else if(row.bean_name=='com.lsts.approve.bean.CertificateRule'){
				            	 show2(f_getData(row));
				            }
						}
					}
		});
    })
    
    function show1(data){
    	g = $("#g").ligerGrid({
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
            data: data, 
            width: '100%',
            title:'签字人员变更详细',
            showTitle: true, 
            columnWidth: 150, 
            frozen:false,
            usePager:false
        });
    }
    function show2(data){
    	g = $("#g").ligerGrid({
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
                        ], 
                        isScroll: false,
                        showToggleColBtn: false, 
			            data: data , 
                        width: '100%',
                        title:'签字规则变更详细',
			            showTitle: true, 
			            columnWidth: 100, 
			            frozen : false,
			            usePager : false
        });  
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
    </script>
</head>
<style type="text/css">
    .l-t-td-right1 .l-text {
		border: none;
	}
	.l-t-td-right1 .l-text {
		background-image: none;
	}
</style>
<body>
	<div title="变更详细" id="fankui">
		<form id="form1" name="form1" method="post" action=""   
			getAction="sysRecordAction/detail2.do?id=${param.id }">
	  		<input type="hidden" name="id" id="id" />
	  		<input type="hidden" name="table_name" id="table_name"/>
	  		<input type="hidden" name="business_id" id="business_id" />
	  		<input type="hidden" name="old_record" id="old_record"/>
	  		<input type="hidden" name="new_record" id="new_record"/>
			<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
				<tr> 
			        <td class="l-t-td-left">用户:</td>
			        <td class="l-t-td-right"> 
			        	<input type="text" ltype="text" id="account" name="account" />
			        </td>
			        <td class="l-t-td-left">地址:</td>
			        <td class="l-t-td-right"> 
			        	<input type="text" ltype="text" id="ip_address" name="ip_address" />
			        </td>
		        </tr>
				<tr> 
			        <td class="l-t-td-left">时间:</td>
			        <td class="l-t-td-right"> 
			        	<input type="text"  ltype="date" id="op_time" name="op_time" ligerui="{format:'yyyy-MM-dd hh:mm:ss'}"/>
			        </td>
			        <td class="l-t-td-left">变更类型:</td>
			        <td class="l-t-td-right"> 
			        	<input type="text" ltype="select" id="action" name="action" 
			        	ligerui="{data:[{id:'add',text:'新增'},{id:'delete',text:'删除'},{id:'update',text:'更新'},{id:'edit',text:'修改'}]}"/>
			        </td>
		        </tr>
				<tr> 
			        <td class="l-t-td-left">操作对象:</td>
			        <td class="l-t-td-right" colspan="3"> 
			        	<input type="text" ltype="select" id="bean_name" name="bean_name" 
			        	ligerui="{data:[{id:'com.lsts.approve.bean.CertificateRule',text:'检验报告签字分配规则'},
			        	{id:'com.lsts.approve.bean.CertificateBy',text:'检验报告授权签字人'}]}"/>
			        </td>
		        </tr>
			</table> 
	       <div id="g" style="margin-top:20px;"></div>
		</form>
	</div>
</body>
</html>
