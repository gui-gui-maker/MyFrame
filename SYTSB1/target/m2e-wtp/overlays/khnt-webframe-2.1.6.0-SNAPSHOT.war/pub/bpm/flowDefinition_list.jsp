<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>流程定义管理</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
        var qmUserConfig = {
        	<sec:authorize ifNotGranted="super_administrate">
				<tbar:toolBar type="tbar" code="liucdy">
				</tbar:toolBar>,
			</sec:authorize>
			<sec:authorize access="hasRole('super_administrate')">
			tbar:[
                { text:'详情 ', id:'detail',icon:'detail',  click:detail },
                "-",
                { text:'增加 ', id:'add',icon:'add', click: add},
                "-",
                { text:'修改 ', id:'modify',icon:'modify', click:modify},
                "-",
                { text:'删除 ', id:'del',icon:'delete', click:del },
                "-",
                { text:'复制 ', id:'copy',icon:'copy', click:copy },
                "-",
                { text:'导出 ', id:'export',icon:'export', click:doExport},
                "-",
                { text:'导入 ', id:'import',icon:'basket-put', click:doImport},
			    "-",
                { text:'发布 ', id:'flowIssue', click:flowIssue ,icon:'right'},
                "-",
                { text:'取消发布', id:'flowUnIssue', click:flowUnIssue ,icon:'communication' }
                /* ,"-",
                { text:'数据配置', id:'monitor', click:monitor ,icon:'modify' } */
            ],
            </sec:authorize>
            listeners: {
                rowDblClick: function(rowData,rowIndex){detail();},
                selectionChange: function(rowData,rowIndex){
                    var count = Qm.getSelectedCount();//行选择数
                	Qm.setTbar({
						copy: count == 1,
						"export": count >= 1,
						modify: count == 1,
						detail: count == 1,
						del: count >= 1,
						flowIssue: count >= 1 && rowData.state=="未发布",
						flowUnIssue: count >= 1 && rowData.state=="已发布",
						monitor: count == 1 
					});
				},
				rowAttrRender : function(rowData) {
					if(rowData.state=="已发布")return "style='color:blue'";
				}
			}
		};
        
        function doExport(){
        	window.location = $("base").attr("href") + "/bpm/definition/export.do?ids=" + Qm.getValuesByName("id").toString();
        }
        
        function doImport(){
			var snd = parent.treeMgr.getSelected();
			if(snd==null){
				parent.$.ligerDialog.error("请选择一个流程分类！");
				return;
			}
        	winOpen({
        		title: "工作流定义导入",
        		width: 400,
        		height: 140,
        		lock: true,
        		data: {callback: function(){Qm.refreshGrid();}},
        		content: "url:pub/bpm/flow_definition_import.jsp?typeId=" + snd.data.id
        	});
        }

		//新增流程
		function add() {
			var snd = parent.treeMgr.getSelected();
			if(snd==null){
				parent.$.ligerDialog.error("请选择一个流程分类！");
				return;
			}
			top.$.dialog({
				width: top.$("body").width(),
				height: top.$("body").height(),
				lock: true,
				title: "新增流程",
				data: {"window" : window},
				content: 'url:bpm/definition/create.do?flowType=' + snd.data.id
			});
		}
		function monitor() {
			top.$.dialog({
				width: 800,
				height: 400,
				lock: true,
				title: "监察监督数据项配置",
				data: {"window" : window},
				content: 'url:pub/monitor/cfg_detail.jsp?flowId=' + Qm.getValueByName("id")
			});
		}
		function submitAction(o) {
			Qm.refreshGrid();
		}

		//修改流程
		function modify() {
			var width = top.$("body").width();
			var height = top.$("body").height();
			top.$.dialog({
				width: width,
				height: height,
				lock: true,
				title: "修改流程",
				data: {"window" : window},
				content: 'url:bpm/definition/modifyFlow.do?status=MODIFY&id=' + Qm.getValueByName("id")
			});
		}
		//查看流程
		function detail() {
			var width = top.$("body").width();
			var height = top.$("body").height();
			top.$.dialog({
				width: width,
				height: height,
				lock: true,
				title: "查看流程",
				content: 'url:bpm/definition/detailFlow.do?status=detail&id=' + Qm.getValueByName("id")
			});
		}
		//删除
		function del() {
			$.del(kFrameConfig.DEL_MSG, "bpm/definition/delete.do", {"ids" : Qm.getValuesByName("id").toString()});
		}

		//复制
		function copy(){
			top.$.dialog({
				width: 400,
				height: 260,
				lock: true,
				title: "复制流程",
				data: {window:window},
				content: "url:pub/bpm/flowDefinition_copy.jsp?id=" + Qm.getValuesByName("id").toString()
			});
		}
		
		//发布流程
		function flowIssue() {
			$.post("bpm/definition/flowIssue.do", {
				ids : Qm.getValuesByName("id").toString()
			}, function(data) {
				if (data.success) {
					top.$.notice('发布成功！',3);
					Qm.refreshGrid();
				} else {
					$.ligerDialog.error('发布失败！');
				}
			});
		}
		//取消发布流程
		function flowUnIssue() {
			$.post("bpm/definition/flowUnIssue.do", {
				ids : Qm.getValuesByName("id").toString()
			}, function(data) {
				if (data.success) {
					top.$.notice('取消发布成功！',3);
					Qm.refreshGrid();
				} else {
					$.ligerDialog.error('取消发布失败！');
				}
			});
		}
        
        //指定条件加载数据
        function loadGridData(flowtype){
    		Qm.config.defaultSearchCondition[0].value=flowtype;
    		Qm.searchData();
        }
	</script>
	</head>
	<body class="p0">
		<qm:qm pageid="bpm_3" singleSelect="false">
           <qm:param name="flowtype" compare="=" value=""/>
		</qm:qm>
	</body>
</html>
