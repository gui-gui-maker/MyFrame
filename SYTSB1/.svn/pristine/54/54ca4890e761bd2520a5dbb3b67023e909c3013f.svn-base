<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults : {
			columnWidth : 0.3,
			labelAlign : 'right',
			labelSeparator : '',
			labelWidth : 120
		},
		sp_fields :  [ 
		              {name : "gysmc",compare : "like"}, 
		              {name : "warehousing_num",compare : "like"}, 
		              {name : "lxrbm",compare : "like"}, 
		              {name : "lxrmc",compare : "like"}, 
		              {name : "dh",compare : "like"}, 
		              {name : "create_user_name",compare : "like"}],
		tbar : [ 
		         {text : '详情',id : 'detail',icon : 'detail',click : detail},"-",
			     { text:'选择', id:'select',icon:'check',click:selectValue}
		],
		listeners : {
			rowClick : function(rowData, rowIndex) {

			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail : count == 1,
					select:count>=1
				});
				
				
			},rowAttrRender:function(rowData, rowid) {
			}
		}
	}
	 
	function selectValue(){
		api.data.window.returnRkBh(Qm.getValuesByName("warehousing_num").toString());
		api.close();
	}
	function f_isChecked(rowdata)
    {
		var rkbh=api.data.rkbh.split(",");
		for(var i=0;i<rkbh.length;i++){
			if(rowdata.warehousing_num==rkbh[i]){
				return true;
			}
		}
		return false;
    }
	
	$(function(){
		//Qm设置默认选中
		Qm.config.isChecked=f_isChecked
	});
	
	function detail() {
		top.$.dialog({
			width : $(top).width()-300,
			height :  $(top).height()-300,
					lock : true,
					parent : api,
					data : {
						window : window
					},
					title : "详情",
					content : 'url:app/supplies/warehousing/warehousing_detail.jsp?pageStatus=detail&id='
							+ Qm.getValueByName("id")
				});
	}
	
</script>

</head>
<body>
	<div class="lb_gys_list" id="titleElement">
		<qm:qm pageid="ch_bx_rk_list">
			<qm:param name="state" value="2" compare="=" />
			<c:if test="${param.dep!='100025' }">
			<qm:param name="create_org_id" value="${param.dep }" compare="=" />
			</c:if>
		</qm:qm>
	</div>
</body>
</html>