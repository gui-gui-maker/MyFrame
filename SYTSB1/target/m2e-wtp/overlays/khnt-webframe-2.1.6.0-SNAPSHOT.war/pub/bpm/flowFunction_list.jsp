<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>流程功能管理</title>
        <%@include file="/k/kui-base-list.jsp"%>
        <script type="text/javascript">
        var qmUserConfig = {
        		<sec:authorize ifNotGranted="super_administrate">
				<tbar:toolBar type="tbar" code="liucfun">
				</tbar:toolBar>,
				</sec:authorize>
			<sec:authorize access="hasRole('super_administrate')">
				tbar:[
                { text:'详情', id:'detail',icon:'detail',  click:detail },
                "-",
                { text:'增加 ', id:'add',icon:'add', click: add},
                "-",
                { text:'修改 ', id:'modify',icon:'modify', click:modify},
                "-",
                { text:'删除 ', id:'del',icon:'delete', click:del }
            ],
    		</sec:authorize>
    
            listeners: {
                rowDblClick :function(rowData,rowIndex){detail(rowData.id);},
                selectionChange :function(rowData,rowIndex){
                     var count = Qm.getSelectedCount();
                     Qm.setTbar({modify:count==1,detail:count==1,del:count>=1});
                }
            }
        };
        
        //新增流程任务节点功能
        function add(){
            var snd = parent.treeMgr.getSelected();
            if(snd==null){
                parent.$.ligerDialog.error("请选择一个流程分类！");
                return;
            }
            top.$.dialog({
                width: 420,
                height: 280,
                lock:true,
                data: {window:window},
                title:"新增流程功能",
                content: 'url:pub/bpm/flowFunction_detail.jsp?status=add&flowtype=' + snd.data.id
            });
        }  
        
        function submitAction(o) {
            Qm.refreshGrid();
        }
       
        //修改流程任务节点功能
        function modify(){
            top.$.dialog({
                width: 420,
                height: 280,
                lock:true,
                data: {window:window},
                title:"修改流程功能",
                content: 'url:pub/bpm/flowFunction_detail.jsp?status=modify&id='+Qm.getValueByName("id")
            });
        } 
        
        //查看流程任务节点功能
        function detail(id){
            if(Qm.getSelectedCount() == 1)
                id = Qm.getValueByName("id");
            top.$.dialog({
                width: 420,
                height: 280,
                lock: true,
                cancel: true,
                title: "查看流程功能",
                content: 'url:pub/bpm/flowFunction_detail.jsp?status=detail&id=' + id
            });
        }
        
        //删除任务节点功能
        function del(){
            $.del(kFrameConfig.DEL_MSG,"bpm/flowFunction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
        }
        
        //指定条件加载数据
        function loadGridData(flowtype){
            Qm.config.defaultSearchCondition[0].value=flowtype;
            Qm.searchData();
        }
    </script>
    </head>
    <body class="p0">
        <qm:qm pageid="bpm_1" script="false" singleSelect="false">
            <qm:param name="flowtype" compare="=" value="${param.flowtype}" />
        </qm:qm>
    </body>
</html>
