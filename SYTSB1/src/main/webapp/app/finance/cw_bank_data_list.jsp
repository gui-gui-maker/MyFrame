<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>银行转账数据列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
    var khFileUploader;
    var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields : [
              {group: [{name: "jy_time", compare: ">="},
                       {label: "到", name: "jy_time", compare: "<=", labelAlign: "center", labelWidth:20}
                      ]},
              {name: "money", compare: "="},
              {name:"account_name",compare:"like"},
              {name:"invoice_nos",compare:"like"}
        ],
        tbar:[
              {text: '详情', id: 'detail', icon: 'detail', click: detail}
              ,"-",
              {text: '新增', id: 'add', icon: 'add', click: add}
               ,"-",
              {text: '修改', id: 'edit', icon: 'edit', click: edit}
               ,"-",
              {text: '删除', id: 'del', icon: 'del', click: del}
               ,"-",
              {text: '退款', id: 'fefund', icon: 'return', click: fefund}
              ,'-',
              {text: '退款记录',id:'FecordRecord',icon:'bluebook',click:FecordRecord}
              ,'-', 
              {text: '清空', id: 'empty', icon : 'modify', click : empty}
              ,'-', 
              {text: '转入金额合计', id: 'zhuanru', icon : 'help', click : zhuanru}
           	  ,'-', 
              {text: '剩余金额合计', id: 'shengyu', icon : 'help', click : shengyu}
               ,'-', 
              {text: '已收金额合计', id: 'yishou', icon : 'help', click : yishou}
               ,'-', 
              {text: '转账人信息', id: 'addTransfer', icon : 'add', click : addTransfer}
              ],
        listeners: {
            rowClick: function(rowData, rowIndex) {
            },
            rowDblClick: function(rowData, rowIndex) {
                detail();
            },  
            selectionChange: function(rowData, rowIndex) {
                var count = Qm.getSelectedCount();
                var rest_money= Qm.getValueByName("rest_money");
                var isRest = false;
                if(rest_money > 0){
                    isRest =true ;
                }
                Qm.setTbar({
                    detail: count==1,
                    edit: count==1,
                    del: count>0,
                    fefund : count==1&&isRest,
                    zhuanru: count>0,
                    shengyu: count>0,
                    yishou: count>0,
                    addTransfer : count==1
                });
            }
        }
    };
    function zhuanru(){//转入金额合计
    	var ids = Qm.getValuesByName("money").toString();
		doTotal(ids,"转入总金额");
    }
    function shengyu(){//剩余金额合计
    	var ids = Qm.getValuesByName("rest_money").toString();
		doTotal(ids,"剩余总金额");
    }
    function yishou(){//已收金额合计
    	var ids = Qm.getValuesByName("used_money").toString();
		doTotal(ids,"已收总金额");
    }
    function doTotal(ids,title){
		var str = new Array();
		str = ids.split(",");
		var total = 0;
		if (str != null && str.length > 0) {
			for ( var i = 0; i < str.length; i++) {
				if(str[i]==''||str[i]==null){
						str[i]=0;
					}
				total = total + parseFloat(str[i]);
			}
			$.ligerDialog.alert(title+'合计：' + total + "元。");
		}
	}
    function empty(){
        $("#qm-search-p input").each(function(){
            $(this).val("");
        })
    }
    
    $(function(){
            importData();
        });
function importData() {
    //创建上传实例
    khFileUploader = new KHFileUploader({
        fileSize: "10mb",//文件大小限制
        buttonId: "importData",//上传按钮ID
        container: "filecontainer3",//上传控件容器ID
        title: "银行转账数据",//文件选择框提示
        saveDB : false,
        extName: "xls,xlsx",//文件扩展名限制
        fileNum: 1,//限制上传文件数目
        callback : function(files){
                    //文件无误，执行保存
                    saveData(files);
        }
    });
}

// 上传完成，开始保存汇编数据
function saveData(files){
    $("body").mask("正在保存...");
    $.post("cw/bank/saveBankData.do",{files:$.ligerui.toJSON(files)},function (data) {
            $("body").unmask();
            if (data.success) {
                if(data.repData!=""&&data.repData!=undefined){
                    $.ligerDialog.alert("成功导入&nbsp;"+"<span style='color:green;'>"+data.total+"</span>"+"&nbsp;条转账记录!</br>"+"但有交易日期重复数据：<br/>" + "<span style='color:red;'>"+data.repData+"</span>");
                } else {
                    $.ligerDialog.success("成功导入&nbsp;"+"<span style='color:green;'>"+data.total+"</span>"+"&nbsp;条转账记录!");
                }Qm.refreshGrid();
            } else {
                $.ligerDialog.error("保存失败！<br/>");
            }
       },"json");
}

    function add(){
        top.$.dialog({
            width: 900,
            height: 500,
            lock: true,
            parent: api,
            data: {
                window: window
            },
            title: "新增",
            content: 'url:app/finance/cwBankDetail.jsp?&pageStatus=add'
        });
    }
    function edit() {
    	var id = Qm.getValueByName("id");
    	$.get("bank/fefund/queryBankFefund.do?FkId="+id,function(res){
			if(res.success){
				var listData = res.list;
				if(listData !=null && listData.length>0){
					var fefundNameTemp;
					for(var i=0;i<listData.length;i++){
						if(fefundNameTemp!="" && fefundNameTemp != null){
							if(fefundNameTemp.indexOf(listData[i].fefundName)==-1){
								fefundNameTemp+=","+listData[i].fefundName;
							}
						}else{
							fefundNameTemp=listData[i].fefundName;
						}
					}
					$.ligerDialog.error('提示：此账户下有<font color=red >'+fefundNameTemp+'</font>的退款申请未处理，请处理完毕后再进行修改！');
				}else{
					top.$.dialog({
			            width: 900,
			            height: 500,
			            lock: true,
			            parent: api,
			            data: {
			                window: window
			            },
			            title: "修改",
			            content: 'url:app/finance/cwBankDetail.jsp?id=' + id + '&pageStatus=modify'
			        });
				}
			}else{
				$.ligerDialog.error('提示：' + res.data);
			}
		})
    }
    function detail() {
        var id = Qm.getValueByName("id");
        top.$.dialog({
            width: 900,
            height: 500,
            lock: true,
            parent: api,
            data: {
                window: window
            },
            title: "详情",
            content: 'url:app/finance/cwBankDetail.jsp?id=' + id + '&pageStatus=detail'
        });
    }
    
    //删除
    function del() {
    	var id = Qm.getValueByName("id");
    	$.get("bank/fefund/queryBankFefund.do?FkId="+id,function(res){
			if(res.success){
				var listData = res.list;
				if(listData !=null && listData.length>0){
					var fefundNameTemp;
					for(var i=0;i<listData.length;i++){
						if(fefundNameTemp!="" && fefundNameTemp != null){
							if(fefundNameTemp.indexOf(listData[i].fefundName)==-1){
								fefundNameTemp+=","+listData[i].fefundName;
							}
						}else{
							fefundNameTemp=listData[i].fefundName;
						}
					}
					$.ligerDialog.error('提示：此账户下有<font color=red >'+fefundNameTemp+'</font>的退款申请未处理，请处理完毕后再进行删除！');
				}else{
					 $.del("确定要删除？删除后无法恢复！","cw/bank/delete.do",{"ids":Qm.getValuesByName("id").toString()});
				}
			}else{
				$.ligerDialog.error('提示：' + res.data);
			}
		})
    }
    
    function fefund(){
    	var id = Qm.getValueByName("id");
    	var rest_money = Qm.getValueByName("rest_money");
        top.$.dialog({
            width: 900,
            height: 500,
            lock: true,
            parent: api,
            data: {
                window: window
            },
            title: "退款",
            content: 'url:app/finance/cwBankFefund_detail.jsp?id=' + id + '&pageStatus=add&rest_money='+rest_money+'&op_type=add'
        });
    }
    
    function FecordRecord(){
		top.$.dialog({
	            width: 900,
	            height: 500,
	            lock: true,
	            parent: api,
	            data: {
	                window: window
	            },
	            title: "退款记录",
	            content: 'url:app/finance/cwBankFefund_list.jsp'
		});
    }
    function addTransfer(){
		top.$.dialog({
	            width: 900,
	            height: 500,
	            lock: true,
	            parent: api,
	            data: {
	                window: window,
	                account:Qm.getValueByName("account_name")
	            },
	            title: "转账人信息",
	            content: 'url:app/finance/cw_bank_transfer_detail.jsp?pageStatus=add'
		});
    }
</script>
</head>
<body>
<p id="filecontainer3" style="margin:5px"><a class="l-button-warp l-button" id="importData"><span class="l-button-main l-button-hasicon"><span class="l-button-text">导入银行转账数据</span><span class="l-button-icon iconfont l-icon-excel-import"></span></span></a></p>
    <qm:qm pageid="TJY2_CW_BANK_LIST" script="false" singleSelect="false">
    </qm:qm>
    <script test="text/javascript">
        Qm.config.sortInfo= [{field:'jy_time',direction:'desc'}];
        Qm.config.columnsInfo.jy_time.formater="yyyy-MM-dd";
    </script>
</body>
</html>