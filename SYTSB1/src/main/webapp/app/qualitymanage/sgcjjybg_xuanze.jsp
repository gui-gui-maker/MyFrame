<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报告列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
		var a="${param.abc}";
		var fid="${param.fid}";
			var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
 	        sp_fields:[
            {name:"test_coding",compare:"like"},
            {name:"type",compare:"like"},
            {name:"variety",compare:"like"},
            {name:"category",compare:"like"}
        ],
	            tbar:[
			        { text:'选择', id:'select',icon:'check',click:getSelectedPersons},
			        "-",
// 			        { text:'新增', id:'xz',icon:'add',click:add},
// 			        "-",
			        { text:'关闭', id:'close',icon:'close',click:close},
			        "-",
			        { text:'历史记录', id:'jl',icon:'detail',click:details}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({});
	                }
	            }
	        };
        	function details(){
        		var id = Qm.getValueByName("id");
        		var test_coding = Qm.getValueByName("test_coding");
        		if(!test_coding){
        			top.$.notice("请选择一条进行查看！！！",4);
                    return;
                }
        		top.$.dialog({
        			width: 300,
        			height: 450,
        			lock: true,
        			parent: api,
        			data: {
        				window: window
        			},
        			title: "历史记录",
        			content: 'url:app/qualitymanage/sg_history_record.jsp?ids=' + test_coding + '&pageStatus=detail'
        		});
        	}
			function getSelectedPersons(){
				var id = Qm.getValueByName("id");
		        if(!id){
		            $.ligerDialog.alert("请先选择所要使用的检验项目代码！");
		            return;
		        }
		        $.ligerDialog.confirm('您确定要生成吗，此次生成的编号不可再次使用？', function (yes){
		        	if(!yes){return false;}
					if(a=='1'){
						api.data.parentWindow.callBackReport(
								Qm.getValuesByName("id").toString(), 
								Qm.getValuesByName("test_coding").toString());
					}else if(a=='2'){
						api.data.parentWindow.callBackReport2(
								Qm.getValuesByName("id").toString(), 
								Qm.getValuesByName("test_coding").toString());
					}else if(a=='3'){
						api.data.parentWindow.callBackReport3(
								Qm.getValuesByName("id").toString(), 
								Qm.getValuesByName("test_coding").toString());
					}else if(a=='4'){
						api.data.parentWindow.callBackReport4(
								Qm.getValuesByName("id").toString(), 
								Qm.getValuesByName("test_coding").toString());
					}else if(a=='5'){
						api.data.parentWindow.callBackReport5(
								Qm.getValuesByName("id").toString(), 
								Qm.getValuesByName("test_coding").toString());
					}else if(a=='6'){
						api.data.parentWindow.callBackReport6(
								Qm.getValuesByName("id").toString(), 
								Qm.getValuesByName("test_coding").toString());
					}else if(a=='7'){
						api.data.parentWindow.callBackReport7(
								Qm.getValuesByName("id").toString(), 
								Qm.getValuesByName("test_coding").toString());
					}
				api.close();
		        });
			}
			function add(){
				top.$.dialog({
					width: 400,
					height: 250,
					lock: true,
					parent: api,
					data: {
						window: window
					},
					title: "新增",
					content: 'url:app/qualitymanage/sgcjjybg_xuanze_datail.jsp?pageStatus=add'+'&fid='+fid+'&xzbh='+a
				});
			}
			function close(){
				api.close();
			}
			
			//列表刷新
			function refreshGrid() {
			    Qm.refreshGrid();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="TJY2_ZL_SGCJJYBG_NUM"  pagesize="100"  singleSelect="true">
		</qm:qm>
	</body>
</html>