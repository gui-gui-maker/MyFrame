<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>技术相关列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<%
		CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
		String userId = cur_user.getId();
	%>
		<script type="text/javascript" src="pub/bpm/js/util.js"></script>
		<script type="text/javascript" src="app/common/js/render.js"></script>
		<script type="text/javascript">
		var arrayObj;
        var qmUserConfig = {
        	
            sp_fields:[
				{name:"patent_name", compare:"like"},
				{name:"patent_num", compare:"like"},
				{name:"patent_type", compare:"="},
            ],
            tbar:[
			{ text:'详情', id:'detail',icon:'detail', click: detail}

			<sec:authorize access="hasRole('PATENT_ENTER')">
			,"-",
			{ text:'增加', id:'add',icon:'add', click: add},
			"-",
			{ text:'修改', id:'modify',icon:'modify', click:modify},
			"-",
			{ text:'提交', id:'submit',icon:'submit', click:submit},
			"-",
			{ text:'删除', id:'del',icon:'delete', click:del}
			</sec:authorize>
			<sec:authorize access="hasRole('PATENT_CHECK')">
			,"-",
			{ text:'确认', id:'dispose',icon:'dispose', click:dispose}
</sec:authorize>
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        			detail(rowData.id);
        		},
        		rowAttrRender : function(rowData, rowid) {
        			
        			 var fontColor="black";
        			 	if(rowData.war<0){
			            	fontColor="red";
			            }else if (rowData.data_status=="专利确认"){
			                fontColor="green";
			            }else if(rowData.data_status=="专利提交"){
			            	fontColor="blue";
			            }
			            return "style='color:"+fontColor+"'";
			        }
    	        
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); //行选择个数
	   		
			Qm.setTbar({
				detail: count==1,
				modify: count==1,
				del: count>0,
				submit: count>0,
				dispose: count>0
			});
			
		}
        
		function detail(){
			var id = Qm.getValueByName("id").toString();
			top.$.dialog({
				width:800,
				height:500,
				lock:true,
				title:"详情",
				content: 'url:app/patent/patent_manage_detail.jsp?pageStatus=detail&id='+id,
				data:{window:window}
			});
		}
        
        //新增
        function add(){
			top.$.dialog({
				width:800,
				height:500,
				lock:true,
				title:"新增",
				data: {window:window},
				content: 'url:app/patent/patent_manage_detail.jsp?pageStatus=add'
			});
        }
        
        //修改
        function modify(){
        	var id = Qm.getValueByName("id").toString();
        	var status = Qm.getValuesByName("data_status");
        	
        	if(status=="专利确认"){
        		
        		$.ligerDialog.alert("所选数据已经确认！");
				return;
        	}
			top.$.dialog({
				width:800,
				height:500,
				lock:true,
				title:"修改",
				data: {window:window},
				content: 'url:app/patent/patent_manage_detail.jsp?pageStatus=modify&id='+id
			});
        } 
        
        //删除
        function del(){
        	
        	var status = Qm.getValuesByName("data_status");
        	
        	 for(var i=0;i<status.length;i++){
            	 
     			if(status[i] == "专利确认"){
     				$.ligerDialog.alert("所选数据已经确认！");
     				return;
     			}
     		}
            $.del("确定要删除？删除后无法恢复！","patentAction/del.do",{"ids":Qm.getValuesByName("id").toString()});
        }
        //提交
        function submit(){
            var ids = Qm.getValuesByName("id");
            var temp = Qm.getValuesByName("data_status");
           
            for(var i=0;i<temp.length;i++){
            	 
    			if(temp[i] == "专利提交"){
    				$.ligerDialog.alert("所选数据已经提交过！");
    				return;
    			}
    		}
            
            
            $.ligerDialog.confirm('是否提交专利？', function (yes){
                if(!yes){return false;}
                top.$.ajax({
                         url: "patentAction/submit.do?ids="+ids,
                         type: "GET",
                         dataType:'json',
                         async: false,
                         success:function (data) {
                            if(data.success){
                            	top.$.notice("提交成功！");
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
        
        //确认
        function dispose(){
            var ids = Qm.getValuesByName("id");
			var temp = Qm.getValuesByName("data_status");
            
            for(var i=0;i<temp.length;i++){
            	
    			if(temp[i] == "专利确认"){
    				$.ligerDialog.alert("所选专利已经确认过！");
    				return;
    			}
    			if(temp[i] == "专利录入"){
    				$.ligerDialog.alert("请选择提交的专利确认！");
    				return;
    				
    			}
    		}
            $.ligerDialog.confirm('是否确认专利？', function (yes){
                if(!yes){return false;}
                top.$.ajax({
                         url: "patentAction/dispose.do?ids="+ids,
                         type: "GET",
                         dataType:'json',
                         async: false,
                         success:function (data) {
                            if(data.success){
                            	top.$.notice("确认成功！");
                                Qm.refreshGrid();//刷新
                            }else{
                                $.ligerDialog.warn(data.msg);
                            }
                         },
                         error:function () {
                             $.ligerDialog.warn("确认失败！");
                         }
                     });
            });
        } 
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
    <div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表未提交，
                <font color="blue">“蓝色”</font>代表已提交，
                <font color="green">“绿色”</font>代表已确认，
                 <font color="red">“红色”</font>代表专利已超期，
                
            </div>
        </div>
    </div>
	</head>
	<body>
		<qm:qm  pageid="TJY2_PATENT_MANGE" defaultOrder="[{field:\"data_status\",direction:\"ASC\"}]">
		<!--<sec:authorize access="hasRole('PAPER_CHECK')"> 
			<qm:param name="data_status" value="0" compare="<>" /> 
 		</sec:authorize> -->
 		<sec:authorize access="hasRole('PAPER_ENTER')"> 
			<qm:param name="create_man_id" value="<%=userId%>" compare="=" />
 		</sec:authorize>
		</qm:qm>
	</body>
</html>
