<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>通用查询</title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script test="text/javascript">
		var aaa = 000;
		var qmUserConfig = {
//            //自定义查询面板样式参数
//            title:"重命名",
//            sp_defaults:{columnWidth:0.2,labelAlign:'top',labelSeparator:'',labelWidth:100},// 可以自己定义
//            自定义查询面板查询字段
			sp_fields: [

			],
//            //定义按钮，可以直接是extjs的按钮
			tbar: [
				{ text: '详情', id: 'seal', icon: 'add', click: detail },
				{ text: '印章测试', id: 'seal', icon: 'add', click: testSeal },
				"-",
				{ text: '网站WEBSERVICE测试', id: 'seal', icon: 'add', click: testService }
			],
////            //提供以下4个事件
			listeners: {
				ddd: ""
//                rowClick :function(rowData,rowIndex){tips("点击第 "+(rowIndex+1)+" 行-"+rowData.id);}
				, rowDblClick: function (rowData, rowIndex) {
					detail(rowData.id);
				}
				//,onDblClickRow :function(rowdata,rowindex,rowDomElement){detail();}
				, selectionChange: function (rowData, rowIndex) {
					var count = Qm.getSelectedCount();
					var status = {};
					if (count == 0) {
						status = {yljy1: false, edit: false, del: false};
						//status={ffffffffffff:false,del:false};
					} else if (count == 1) {
						status = {edit: true, del: true, yljy1: true};
						//status={ffffffffffff:false,del:true};
					} else {
						status = {edit: false, del: true, yljy1: true};
						//status={ffffffffffff:false,del:true};
					}
					Qm.setTbar(status);
//                    tips("选中 "+count+" 行");
				}
//                ,beforeQmReady:function(){
//                    tips("beforeQmReady事件发生在所有参数解析之前");
//                }
				//可以邦定ligerui grid原生的事件和属性
//                ,onAfterShowData:function () {
//                    Qm.getQmgrid().selectRange("id",[30,2]);
//                }
//                ,rowAttrRender:function(item, rowid) {
//                    if(item.id<100)
//                        return "style='background-color:red'";
//                    else
//                        return "";
//                }
			}
		};
		function detail(id) {
			if (!id) {
				id = Qm.getValueByName("id");
			}//alert(Qm.getValueByName("id")+"////");
			var width = 800;
			var height = 600;
			var windows = winOpen({
				width: width,
				height: height,
				lock: true,
				title: "一对多表",
				content: "url:demo/demo_detail2.jsp?pageStatus=detail&id=" + id
			});
		};
		var tip;
		function tips(content) {
			if (!tip) {
				tip = $.ligerDialog.tip({ title: '提示信息', content: content });
			}
			tip.show();
			tip.set('content', content);
		}
		function test(item) {
			tips(item.id + "=" + item.text);
		}

		function testSeal() {
			var wObj = top.$.dialog({
				id: "detail1",
				width: 800,
				height: 600,
				lock: true,
				title: "",
				content: 'url:demo/signseal/page_seal.jsp'
			});
			//wObj.max();
		}
		;

		function testService() {
			$.getJSON("khnt/test/test_web_service.do", function (resp) {
				$.ligerDialog.alert(resp.success);
			});
		}
    </script>
</head>
<body>
<q:qm pageid="sys_03" singleSelect="false">
    <%--<qm:param name="str1" compare="like" value="A"/>--%>
</q:qm>

</body>
</html>