<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
						{name:"device_name",compare:"like",value:""},
						{name:"device_registration_code",compare:"like",value:""}
			            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({modify:count==1,detail:count==1,del:count>0});
	                },
	                rowAttrRender : function(rowData, rowid) {
	            		var fontColor="black";	            		
	            		// 该设备正在报检中显示红色
	            		if(rowData.is_cur_check == "2") {
	            			fontColor="red";
	            		}
	            		return "style='color:"+fontColor+"'";
	            	}
					
	            ,
                afterQmReady:function(){
                	
                	Qm.setCondition([{name : "fk_company_info_use_id", compare : "=", value : "${param.org_id}"}]);
                	Qm.searchData();
                }
					
					
	            }
			
	};
	
	function submitAction() {
		Qm.refreshGrid();
	}

	function close()
	{	
		 api.close();
	}
	   function f_select()
       { 
		  var rows = Qm.getQmgrid().getSelectedRows();
          return rows; 
       }


	
	
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表设备当前未报检，
				<font color="red">“红色”</font>代表设备当前报检中。<font color="red"><b>请选择当前未报检的设备！！！</b></font>
				
			</div>
		</div>
	</div>
	<qm:qm pageid="dev_info" script="false" singleSelect="false" seachOnload="false"  >
	</qm:qm>
</body>
</html>
