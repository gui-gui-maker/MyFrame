/**
 * 导出报告pdf，并盖章
 * certPwd,certPath,sealXmlPath 盖章的配置的相关参数，在properties文件取值,没值则默认
 * pdfPath: pdf文件路径
 * Report_sn:报告书编号
 * 保存的文件路劲，没值则默认upload/日期/报告书编号/报告书编号.pdf
 */
var pdfPath=""; var report_sn="";var signPage="";
var id="";
var up = false;
var day ="";
	function doPrintreport(day1,id1,pdfPath1,report_sn1)
	{

		id = id1;
		day = day1;
		if(pdfPath1==undefined||pdfPath1==null||pdfPath1==""){

			alert("请输入pdf文件路劲！");
			return;
		}else{
			pdfPath =pdfPath1;
		}
		if(report_sn1==undefined||report_sn1==null||report_sn1==""){
			alert("请输入报告书编号！");
			return;
		}else{
			report_sn = report_sn1;
		}
	
//		var fso = new ActiveXObject("Scripting.FileSystemObject");
//		if(!fso.FolderExists("D:\\TEMP\\")) {
//			fso.CreateFolder("D:\\TEMP\\");
//		}
		MRViewer.ExportFile("D:\\TEMP\\"+report_sn+".pdf",false,false,false,false,"SingleFile=true");

		upload();

		
	}
	
    function upload(){
    	
    		//var id = Qm.getValueByName("id");
    		top.$.dialog({
    			width : 1, 
    			height : 1, 
    			lock : true, 
    			title:"查看修改报告",
    			content: 'url:app/flow/export/report_doc.jsp?status=detail&report_sn='+report_sn,
    			data : {"window" : window,
    				pdfPath:pdfPath,
    				report_sn:report_sn,
    				id:id,
    				day:day}
    		})
    
    }