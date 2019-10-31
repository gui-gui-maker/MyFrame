function print(){
	var id=Qm.getValueByName("id");
	var zd=Qm.getValueByName("zd");
 	var LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));
	LODOP.PRINT_INIT("");
	LODOP.PRINT_INITA(0,0,1100,1730,"");					//、、
	LODOP.SET_PRINT_PAGESIZE(1,0,0,"A4");
	if(zd=='主动介入'){
		LODOP.ADD_PRINT_IMAGE(0,0,1100,1114,"<img src='/app/discipline/images/gzd.jpg'/>");

		$.ajax({								
			cache:false,
			type:"post",
			async: false,
			data:{"id":id},
			dataTyp:"json",
			url:"disciplineZdjrAction/detail.do",			
			success:function(data) {
			 	LODOP.ADD_PRINT_TEXT(175,195,432,27,data.data.szbm);
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	var jdlb="";
			 	if(data.data.jdlb=='zdjc'){jdlb="重大决策";}
			 	if(data.data.jdlb=='zygbrm'){jdlb="重要干部任免";}
			 	if(data.data.jdlb=='zdxmap'){jdlb="重大项目安排";}
			 	if(data.data.jdlb=='dedzjsy'){jdlb="大额度资金使用";}
			 	
			 	LODOP.ADD_PRINT_TEXT(229,196,157,19,jdlb);
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	var jdfs="";
			 	if(data.data.jdfs=='sqyf'){jdfs="事情预防";}
			 	if(data.data.jdfs=='szjr'){jdfs="事中介入";}
			 	if(data.data.jdfs=='shjr'){jdfs="事后介入";}
			 	if(data.data.jdfs=='qccy'){jdfs="全程参与";}
			 	if(data.data.jdfs=='qt'){jdfs="其它";}
			 	LODOP.ADD_PRINT_TEXT(229,564,131,21,jdfs);//监督方式
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(270,195,520,89,data.data.jdgzsy);//事由
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(395,194,150,20,data.data.jdsj_start.substring(0,10));
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(397,563,153,20,data.data.jdsj_end.substring(0,10));
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(437,190,531,93,data.data.bmyj);
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(543,577,125,21,data.data.bmyj_fzr);
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(566,518,51,21,data.data.bmyj_time.substring(0,4));
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(565,579,31,21,data.data.bmyj_time.substring(5,7));
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(565,621,31,21,data.data.bmyj_time.substring(8,10));
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);

			 	LODOP.ADD_PRINT_TEXT(590,194,526,42,data.data.jjgzyj==null? "":"负责人审核意见："+data.data.jjgzyj);
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
				LODOP.ADD_PRINT_TEXT(631,195,527,28,data.data.yld==null? "" :"院领导审核意见："+data.data.yld);
				LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
				LODOP.ADD_PRINT_TEXT(663,194,527,43,data.data.fzrxf==null ? "":"负责人工作安排意见："+data.data.fzrxf);
				LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
				LODOP.ADD_PRINT_TEXT(707,191,529,40,data.data.cz_user_names==null? "":"已安排的工作人员："+data.data.cz_user_names);
				LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(749,582,125,21,data.data.jjgzyj_fzr);
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(770,521,45,21,data.data.jjgzyj_time.substring(0,4));
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(771,581,30,21,data.data.jjgzyj_time.substring(5,7));
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(771,622,30,21,data.data.jjgzyj_time.substring(8,10));
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	
			 	LODOP.ADD_PRINT_TEXT(803,200,516,90,data.data.bljg);
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(905,511,51,21,data.data.bljg_time.substring(0,4));
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(905,563,30,23,data.data.bljg_time.substring(5,7));
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	LODOP.ADD_PRINT_TEXT(905,601,30,23,data.data.bljg_time.substring(8,10));
			 	LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
			 	 }
			 })
	}
	if(zd=='申请介入'){

		$.ajax({								
			cache:false,
			type:"post",
			async: false,
			data:{"id":id},
			dataTyp:"json",
			url:"com/zdsx/sqjr/detail.do",			
			success:function(data) {
			console.log(data.data);
		
		LODOP.ADD_PRINT_IMAGE(0,0,1100,1114,"<img src='/app/discipline/images/spb.jpg'/>");
		LODOP.ADD_PRINT_TEXT(169,185,171,23,data.data.sqr);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(167,507,188,23,data.data.szbm);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		var jdlb="";
		if(data.data.jdlb=='1'){jdlb='决策事项';}
		if(data.data.jdlb=='2'){jdlb='干部任免';}
		if(data.data.jdlb=='3'){jdlb='项目安排';}
		if(data.data.jdlb=='4'){jdlb='资金使用';}
		if(data.data.jdlb=='5'){jdlb='其它';}
		LODOP.ADD_PRINT_TEXT(199,186,167,23,jdlb);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		var jdzl="";
		if(data.data.jdzl=='1'){jdzl="部门要求介入";}
		if(data.data.jdzl=='2'){jdzl="事物工作需要全程监督";}
		if(data.data.jdzl=='3'){jdzl="针对工作一环节的监督";}
		if(data.data.jdzl=='4'){jdzl="其它";}
		LODOP.ADD_PRINT_TEXT(200,506,184,23,jdzl);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(234,184,546,88,data.data.jdgzsy);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(351,184,173,27,data.data.jdsj_start.substring(0,10));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(357,510,100,20,data.data.jdsj_end.substring(0,10));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(402,297,431,66,data.data.bmfzryj);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(475,525,37,20,data.data.bmfzr_time.substring(0,4));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(475,575,24,20,data.data.bmfzr_time.substring(5,7));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(475,613,23,20,data.data.bmfzr_time.substring(8,10));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(498,296,434,78,data.data.bmfgyyj);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(595,532,37,20,data.data.bmfgy_time.substring(0,4));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(595,581,25,21,data.data.bmfgy_time.substring(5,7));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(595,621,24,22,data.data.bmfgy_time.substring(8,10));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(618,298,426,65,data.data.jjfgyyj);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(694,529,41,20,data.data.jjfgy_time.substring(0,4));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(693,577,26,20,data.data.jjfgy_time.substring(5,7));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(694,616,24,20,data.data.jjfgy_time.substring(8,10));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(829,188,531,66,data.data.bljg);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(906,511,37,20,data.data.bljg_time.substring(0,4));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(905,566,27,20,data.data.bljg_time.substring(5,7));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(905,607,24,20,data.data.bljg_time.substring(8,10));
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(740,295,426,35,data.data.fzrxf==null ?"":data.data.fzrxf);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
		LODOP.ADD_PRINT_TEXT(787,294,409,25,data.data.cz_user_names==null? "":"已安排的工作人员："+data.data.cz_user_names);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
			}
		});

	}
//		LODOP.PRINT_DESIGN();//打印设计
		 LODOP.PRINT();//直接打印
	}