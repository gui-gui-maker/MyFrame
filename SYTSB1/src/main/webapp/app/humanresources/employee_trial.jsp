<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},		
		sp_fields:[
				     {name: "emp_name", compare: "like"},
                     {name: "emp_sex", compare: "like"},
                     {name: "emp_status", compare: "="},
                     {name: "emp_id_card", compare: "like"},
                     {name: "emp_phone", compare: "like"},
                     {name: "emp_title_num", compare: "like"}
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '转正', id: 'positive', icon: 'modify', click: positive
		}],
		
        listeners: {
	        rowClick: function(rowData, rowIndex) {
	        	
	        },
	        rowDblClick: function(rowData, rowIndex) {
		      detail();
	        },	
	        selectionChange: function(rowData, rowIndex) {
		         var count = Qm.getSelectedCount();
		         Qm.setTbar({
		          	detail: count==1,
		         	positive:count==1
			
		       });
	        },
	        rowAttrRender : function(rowData, rowid) {
	        	var fontColor="black";
            	var fireDate=rowData.fire_date;
            	//获取试用日期后3个月的年 ，月，日
            	var date1=new Date(fireDate);
            	var year1=date1.getFullYear();
            	var month1=date1.getMonth()+4;
            	
            	if(month1-12>0){
            		month1=month1-12;
            		year1=year1+1;
            	}
            	var day1=date1.getDay();
            	//获取当前时间的年 ，月，日
            	var date2=new Date();
            	var year2=date2.getFullYear();
            	var month2=date2.getMonth()+1;
            	var day2=date2.getDay();
            	if(year2==year1){

            		if(month1-month2==1){
            			if(day1+30-day2<=7){
            				fontColor="red";
            			}
            			
            		}else if(month2-month1==0){
            			if(day1-day2<=7){
            				fontColor="red";
            			}
            		}
            	}	
 	       			return "style='color:"+fontColor+"'";
				}
}
}
		
		
function detail(){
    top.$.dialog({
        width: 900,
        height: 500,
        lock: true,
        parent: api,
        data: {
      	 window: window
        },
        title: "详情",
        content: 'url:app/humanresources/registration_datail.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
     });
}
function positive(){
 	var id=Qm.getValueByName("id");
   $.ligerDialog.confirm('是否转正？', function (yes){
    if(!yes){return false;}
    top.$.ajax({
                 url: "employeeBaseAction/addPositive.do?id="+id,
                 type: "GET",
                 dataType:'json',
                 async: false,
                 success:function (data) {
                    if(data.success){
                        $.ligerDialog.success("提交成功！");
                        Qm.refreshGrid();//刷新
                    }else{
                        $.ligerDialog.warn(data.msg);
                    }
                 },
                 error:function () {
                     $.ligerDialog.warn("提交失败！");
                 }
             });
});
     }
        function loadGridData(treeId) {
    		if (treeId != null&&treeId !="100000") {
    			Qm.setCondition([ {
    				name : "work_department",
    				compare : "like",
    				value : treeId
    			} ]);
    		} else {
    			Qm.setCondition([]);
    		}
    		Qm.searchData();
    	}
        
</script>
 
</head>
<body>
  <div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="red">“红色”</font>代表转正日期距今小于一周。
			</div> 
		</div>
	</div>
		  <qm:qm pageid="TJY2_RL_EMPLOYEE">
       <qm:param name="emp_status" value="3" compare="in" />
       </qm:qm>
</body>
</html>