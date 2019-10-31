<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
	var billGrid=api.data.parentWindow.billGrid;
	var qmUserConfig = {
			sp_fields:[ 
						{label:'发票类型',name:'invoice_type',compare:"like"},
						{group:[
							  {label:'发票编号从',name:'invoice_code',compare:'>='},
							  {label:'至',name:'invoice_code', compare:'<=',labelAlign:"center",labelWidth:20,width:100}
							]}
					],
			
					tbar:[
					      {text: '选择', id: 'lead',	 icon: 'consuming', click:lead}		
					],
					listeners:{
						rowClick:function(rowData,rowIndex){
						},
						rowDblClick:function(rowDate,rowIndex){
							Qm.getQmgrid().selectRange("id", [rowDate.id]);
						},
						
						selectionChange: function(rowData, rowIndex) {
							var count = Qm.getSelectedCount();
							Qm.setTbar({
	        					lead: count>0	
	        				});							
				         }
					}
			};
	
	function lead(){
	    var rows = f_select();
	    if (rows.length==0){
	 	   	$.ligerDialog.warn('请选择需要发放的发票号数据！');
	        return;
	    } 
       var devRow = billGrid.rows;
       var isexist=false;
       var selectRow=[];
       for(var i in rows){
    	   
       		for(var j in devRow){
	       		if(rows[i].id == devRow[j].invoice_id){
	       			isexist = true;
	       			break;
	       		}else{
	       			isexist = false;
	       		}
       		}
       		if(!isexist){
				if(rows[i].done!='1'){
	       		var tt = {invoice_id:rows[i].id,invoice_code:rows[i].invoice_code,invoice_type:rows[i].invoice_type,
	       				registrant_name:api.data.audit_op,registrant_date:api.data.audit_time,lead_name:api.data.apply_op,lead_date:api.data.audit_time,lead_code:rows[i].invoice_code,
	       				lead_dep:api.data.apply_org,lead_num:"1",lead_id:api.data.apply_op_id,registrant_id:api.data.audit_op_id,data_type:"0",
	       				lead_dep_id:api.data.apply_org_id,invoice_type_code:rows[i].invoice_type_code};
	       		selectRow.push(tt);
	       		billGrid.addRow(tt);
				}
       		}
       }
       try{
    	   api.data.parentWindow.onChooseBillBack(selectRow);
       }catch(e){
    	   $.ligerDialog.warn('没有找到回调方法！');
       }
       api.close();
		
	}
	function f_select(){ 
        var rows = Qm.getQmgrid().getSelectedRows();
        return rows; 
	}
</script>
</head>
<body>
	<qm:qm pageid="TJY2_CW_FPGL" script="false" singleSelect="false">
		<qm:param name="status" value="WSY" compare="=" />
	</qm:qm>	
</body>
</html>