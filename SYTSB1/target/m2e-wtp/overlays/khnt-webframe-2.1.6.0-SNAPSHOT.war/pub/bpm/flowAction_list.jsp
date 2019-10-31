<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>流程功能查询</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
        var qmUserConfig = {
//            //自定义查询面板样式参数
//            title:"重命名",
//            sp_defaults:{columnWidth:0.2,labelAlign:'top',labelSeparator:'',labelWidth:100},// 可以自己定义
//            自定义查询面板查询字段
//            sp_fields:[
//                {name:"date2",compare:">",value:"2007-01-01"},
//                {label:"自定义名称",name:"str2",compare:"like",value:"A"},
//                {name:"str1",compare:"like",value:"B"},
//                {label:"自定义名称",name:"str2",compare:"like",value:"C"},
//                {label:"自定义名称",name:"str2",compare:"like",value:""},
//                {name:"str3",compare:"=",value:"1"}
//            ],
//            //定义按钮，可以直接是extjs的按钮
            tbar:[
                { text:'增加', id:'add', click: add},
                "-",
                { text:'修改', id:'modify', click:modify},
                "-",
                { text:'查看', id:'detail', click:detail },
                "-",
                { text:'删除', id:'del', click:del }
            ],
////            //提供以下4个事件
            listeners: {
                rowClick :function(rowData,rowIndex){}
                ,rowDblClick :function(rowData,rowIndex){detail();}
                ,selectionChange :function(rowData,rowIndex){selectionChange()}
            }
        };
       
       
        //行选择改变事件
        function selectionChange(){
          var count=Qm.getSelectedCount();//行选择个数
          if(count==0){
           Qm.setTbar({add:true,modify:false,detail:false,del:false});
          }else if(count==1){
           Qm.setTbar({add:true,modify:true,detail:true,del:true});
          }else{
           Qm.setTbar({add:true,modify:false,detail:false,del:true});
          }
        }
        
        
       //新增流程任务节点事件
       function add(){
			var width=600;
			var height=300;
			var windows=top.$.dialog({
				width:width,
				height:height,
				lock:true,
				title:"新增流程事件",
				content: 'url:pub/bpm/flowAction_detail.jsp?status=add&flowtype=${param.flowtype}'
				,ok:function(w){
					var iframe=this.iframe.contentWindow;
					iframe.$("#formObj").submit();
					return false;
				}
				,cancel:function(w){//取消按钮函数
				}
			});
       }  
       
		function submitAction(o) {
		  Qm.refreshGrid();
		}
       
        //修改流程任务节点事件
       function modify(){
            var width=600;
			var height=300;
			var windows=top.$.dialog({
				width:width,
				height:height,
				lock:true,
				title:"修改流程事件",
				content: 'url:pub/bpm/flowAction_detail.jsp?status=modify&flowtype=${param.flowtype}&id='+Qm.getValueByName("id")
				,ok:function(w){
					var iframe=this.iframe.contentWindow;
					iframe.$("#formObj").submit();
					return false;
				}
				,cancel:function(w){//取消按钮函数
				}
			});
       } 
       //查看流程任务节点事件
       function detail(){
            var width=600;
			var height=300;
				var windows=top.$.dialog({
				width:width,
				height:height,
				lock:true,
				title:"查看流程事件",
				content: 'url:pub/bpm/flowAction_detail.jsp?status=detail&flowtype=${param.flowtype}&id='+Qm.getValueByName("id")
				,ok:function(w){
					var iframe=this.iframe.contentWindow;
					iframe.$("#formObj").submit();
					return false;
				}
				,cancel:function(w){//取消按钮函数
				}
			});
       } 
       //删除任务节点事件
       function del(){
         $.del("你是否要删除任务节点事件","bpm/flowAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
       }    
    </script>
</head>
<body>
	<qm:qm pageid="bpm_2" script="false" singleSelect="false">
		<qm:param name="flowtype" compare="=" value="${param.flowtype}" />
	</qm:qm>
</body>
</html>
