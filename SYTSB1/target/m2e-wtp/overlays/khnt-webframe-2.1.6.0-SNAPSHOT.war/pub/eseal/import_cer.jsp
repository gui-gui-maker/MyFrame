<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload-dialog.js"></script>
<script type="text/javascript">

var qmUserConfig = { 
		sp_fields : [
			{label : "证书名称", name : "cer_name", compare : "=", value : ""}
		],
		tbar:[{id:'run_key_import',text:'导入证书',icon:'up',click:importKey}],
	    listeners: {
	        rowDblClick :function(rowData,rowIndex){
	        	//show(rowData.id);
	        },
	        selectionChange :function(rowData,rowIndex){
	        	selectionChange();
	        }
	    }
	};

function selectionChange(){
	var count=Qm.getSelectedCount();//行选择个数
	if(count==0){
		Qm.setTbar({detail:false,run_key_import:true});
	}else if(count==1){
		Qm.setTbar({detail:true,run_key_import:true});
	}else{
		Qm.setTbar({detail:false,run_key_import:true});
	}
}
function importKey() {
	var myUploadConfig = {
		    window : window,//新窗口回调使用的当前window
		    extName : "zip,rar",//过滤文件类型
		    title : "选择打包的证书文件",//过滤文件类型说明，选择文件时用于窗口提示
		    businessId : "keyforytmz", //业务id
		    maxSize : "10mb",//文件大小限制
		    attType : "1",//文件存储类型：1:数据库，0:磁盘，默认为磁盘
		    saveDB : true,//是否存储附件信息到数据库
		    fileNum : 1,//文件数量，-1为不限
		    workItem : "",//多点上传标识
	    	callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
				uploaderBack(files);
			}
		};
	KHDialogFileUploader(myUploadConfig);
}

function uploaderBack(files){
	if(files){
		$("body").mask("正在提交数据...");   
		$.getJSON("pub/certificate/importCer.do",{fileId:files[0].id},function(data){
			if(data.success){
	    		Qm.refreshGrid();
	    		top.$.dialog.notice({content:'提交成功！'});
	    		$("body").unmask();
	    	}else{
	    		$.ligerDialog.error("错误："+data.msg);
	    		$("body").unmask();
	    	}
	    		
	    });
	}
}

function detail(){
	//var id=Qm.getValueByName("id");
	//show(id);
}



</script>

</head>
<body>
	<qm:qm pageid="pub_cer_list">
	</qm:qm>
</body>
</html>