<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="detail">
    <title>低保新申请详细页面</title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->




<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<base href="http://192.168.1.58:8082/"/>

<script type="text/javascript" src="k/kui/frame/core.js"></script>
<script type="text/javascript" src="k/kui/frame/main.js"></script>

<script type="text/javascript">
loadCoreLibrary("form");
</script>
    <script type="text/javascript" src="pub/seal/js/seal.js"></script>
    <script type="text/javascript">
    	var interviewDetailGrid;
		var ba_yhzgx = [
			{'id': '1', 'text': '本人'},
			{'id': '2', 'text': '配偶'},
			{'id': '3', 'text': '子'},
			{'id': '4', 'text': '女'},
			{'id': '5', 'text': '孙子、孙女或外孙子、外孙女'},
			{'id': '6', 'text': '父母'},
			{'id': '7', 'text': '祖父母或外祖父母'},
			{'id': '8', 'text': '兄弟姐妹'},
			{'id': '99', 'text': '其他'}
		];
		var ba_jkzk = [
			{'id': '01', 'text': '健康或良好'},
			{'id': '02', 'text': '一般或较弱'},
			{'id': '03', 'text': '重病'}
		];
		var jobStatus = [
			{'id': '2', 'text': '未就业'},
			{'id': '1', 'text': '已就业'}
		];
		var userStatus = [
			{'id': '3', 'text': '死亡'},
			{'id': '2', 'text': '变更'},
			{'id': '1', 'text': '新增'},
			{'id': '99', 'text': '正常'}
		];
		var ba_hkxz = [
			{'id': '0', 'text': '非农业'},
			{'id': '1', 'text': '农业'}
		];
		var pageStatus = "detail";

		$(function () {
			$("#form1").initForm({
				toolbar: [],
				getAction: "dibao/home/inter/getInterByHomeId.do?homeId=402881ef3e7f071e013e7f0f55100868",
				getSuccess: function (res) {
					$("#table1").transform("detail");
					$("#table1").setValues(res.data);
					//$("#id").val(res.data.id);
					if (res.data != null) {
						//调查人员签名的数据
						$.post("pub/signseal/getListByIds.do", {Ids: res.data.person}, function (sign) {
							if (sign.success) {
								//显示印章数据
								var left = 10;
								var top = -40;
								var num = 0;
								var signData = sign.data;
								//获取总的调查人员个数
								var totalNum = signData.length;
								//计算行数
								var rowNum = parseInt(totalNum / 4);
								top = top * (rowNum + 1);
								for (var i in signData) {
									num++;
									showSingleSeal("person1", signData[i], left, top, 0);
									left += 160;
									if (num % 4 == 0) {
										top += 100;
										left = 10;
									}
								}
							}
						}, "json");
						//村（社区）印章
						if (res.data.villageSeal != null) {
							showSeal(res.data.villageSeal, "villageSeal1");
						}
						//乡镇人民政府印章
						if (res.data.townSeal != null) {
							showSeal(res.data.townSeal, "townSeal1");
						}
						//村社区签名
						if (res.data.village != null) {
							showSeal(res.data.village, "village1");
						}
						//民政办主任签名
						if (res.data.town != null) {
							showSeal(res.data.town, "town1");
						}
					}
				}
			});

			homeProp();
		});
		//显示印章或签名
		function showSeal(Ids, sealStationId) {
			$.post("pub/signseal/getListByIds.do", {Ids: Ids}, function (sign) {
				if (sign.success) {
					//显示印章数据
					var signData = sign.data;
					for (var i in signData) {
						showSingleSeal(sealStationId, signData[i], "", "", 0);
					}
				}
			}, "json");
		}

		//家庭财产列表处理
		function homeProp() {
			var columns = [
				{display: '人员ID', width: '10%', name: 'userId', hide: true, type: 'text'},
				{display: '姓名', width: '10%', name: 'name', type: 'text'},
				{display: '与户主关系', width: '10%', name: 'familyrel', type: 'text',
					render: function (item) {
						for (var i in ba_yhzgx) {
							if (ba_yhzgx[i]["id"] == item["familyrel"])
								return ba_yhzgx[i]['text'];
						}
						return item["familyrel"];
					}
				},
				{display: '户口性质', width: '10%', name: 'category', type: 'text',
					render: function (item) {
						for (var i in ba_hkxz) {
							if (ba_hkxz[i]["id"] == item["category"])
								return ba_hkxz[i]['text'];
						}
						return item["category"];
					}
				},
				{display: '出生日期', width: '10%', name: 'birthday', type: 'date'},
				{display: '月收入（元）', width: '10%', name: 'income', type: 'int', editor: {type: 'int'}},
				{display: '健康状况', width: '10%', name: 'healthStatus',
					editor: { type: 'select', data: ba_jkzk },
					render: function (item) {
						for (var i in ba_jkzk) {
							if (ba_jkzk[i]["id"] == item["healthStatus"])
								return ba_jkzk[i]['text'];
						}
						return item["healthStatus"];
					}
				},
				{display: '就业状况', width: '10%', name: 'jobStatus',
					editor: { type: 'select', data: jobStatus },
					render: function (item) {
						for (var i in jobStatus) {
							if (jobStatus[i]["id"] == item["jobStatus"])
								return jobStatus[i]['text'];
						}
						return item["jobStatus"];
					}
				},
				{display: '人员状态', width: '10%', name: 'status',
					editor: { type: 'select', data: userStatus },
					render: function (item) {
						for (var i in userStatus) {
							if (userStatus[i]["id"] == item["status"])
								return userStatus[i]['text'];
						}
						return item["status"];
					}
				}
			];

			interviewDetailGrid = $("#interviewDetail").ligerGrid({
				columns: columns,
				title: null,
				rownumbers: true,                         //是否显示行序号
				frozenRownumbers: false,
				isScroll: true,
				usePager: false,
				url: 'dibao/user/info/getUserList.do?homeID=402881ef3e7f071e013e7f0f55100868',
				root: 'data'
			});
		}


    </script>
     <style type="text/css">
        .l-panel-header {
			background:url("");
			border-bottom:1px solid #99BBE8;
			color:#15428B;
			font-size:12px;
			font-weight:bold;
			height:24px;
			position:relative;
		}

		.l-text-suffix {
			padding:0 0 0 5px !important;
			vertical-align:middle;
			width:36px;
		}
    </style>
</head>
<body>
<div class="scroll-tm">

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="detail">
    <title>带引入家庭详细资料页面</title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <script type="text/javascript">
    	var pageStatus = "";
		$(function () {
			//设置家庭基本表格为只读页面
			$("#homeInfo").transform("detail");
			$("#homeInfo").setValues("dibao/home/detail.do?id=402881ef3e7f071e013e7f0f55100868");

		});
		function openDetail() {
			var windows = top.$.dialog({
				width: $(top).width(),
				height: $(top).height(),
				lock: true,
				data: {window: window},
				title: "家庭详情",
				content: 'url:dibao/home_manage.jsp?pageStatus=detail&first=false&id='
			});
		}
		function showlist() {
			$(this).data('areacode', '510723');
			showAreaList.call(this);
		}
    </script>
</head>
<body>
	<fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>家庭基本资料</div>
        </legend>
	    <table id="homeInfo" border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" pageStatus="detail">
	        <tr>
	        	<td class="l-t-td-left"> </td>
	            <td class="l-t-td-right" align="right" colspan="5">
	            	<a href="javascript:openDetail()">[家庭详细资料]</a>
	            </td>
	        </tr>
	        <tr>
        		<td class="l-t-td-left"> 户主姓名：</td>
	            <td class="l-t-td-right">
	            	<input name="name" type="text" ltype='text' validate="{required:true,maxlength:50}"/>
	            </td>
	            <td class="l-t-td-left"> 户主身份证号：</td>
	            <td class="l-t-td-right">
	            	<input name="idn" type="text" ltype='text' validate="{required:true,idno:true,maxlength:18}"/>
	            </td>
	            <td class="l-t-td-left"> 联系电话：</td>
	            <td class="l-t-td-right">
	            	<input name="tel" type="text" ltype='text' validate="{required:true,maxlength:11}"/>
	            </td>
        	</tr>
        	<tr>
	                <td class="l-t-td-left">户籍所在地：</td>
	                <td class="l-t-td-right" colspan="2">
	                	<input id="nativeAreaCode" name="nativeAreaCode" type="hidden" value="">
	                	<input id="nativeAreaName" name="nativeAreaName" type="text" value="" ltype='select' validate="{maxlength:100,required:true}" ligerui="{textModel:true,valueFieldID:'nativeAreaCode',onBeforeOpen:showlist}"/>
	                </td>
	                <td class="l-t-td-right">
	                	<input name="natives" type="text" ltype='text' validate="{required:true,maxlength:200}"/>
	                </td>
	            	<td class="l-t-td-left"> 户籍派出所：</td>
		            <td class="l-t-td-right">
		            	<input type="text" id="nativeStation-txt" name="_nativeStation-txt" ltype="select" ligerui="{ valueFieldID:'nativeStation',data: [{'id':'1','text':'云溪镇派出所'},{'id':'2','text':'富驿镇派出所'}],'iconItems':[{icon:'add',click:function(){sys_modify_codetable('402881ef3e87b92d013e87c0ffaa0001','db_0019','nativeStation');}}]}" validate="{required:true}"/>
		            </td>
	            </tr>
	            <tr>
	                <td class="l-t-td-left">居住地址：</td>
	                <td class="l-t-td-right" colspan="2">
	                	<input id="addrAreaCode" name="addrAreaCode" type="hidden" value="">
	                	<input id="addrAreaName" name="addrAreaName" type="text" value="" ltype='select' validate="{maxlength:100,required:true}" ligerui="{textModel:true,valueFieldID:'addrAreaCode',onBeforeOpen:showlist}"/>
	                </td>
	                <td class="l-t-td-right">
	                	<input name="addr" type="text" ltype='text' validate="{required:true,maxlength:200}"/>
		            </td>
		            <td class="l-t-td-left"> 邮政编码：</td>
		            <td class="l-t-td-right">
		            	<input name="zip" type="text" ltype='text' validate="{digits:true,rangelength:[6,6],maxlength:6}"/>
		            </td>
	            </tr>
            <tr>
	            <td class="l-t-td-left"> 救助类别：</td>
	            <td class="l-t-td-right" colspan="5">
	            	<input type="checkbox" id="helpTypes-txt" name="helpTypes" ltype="checkboxGroup" ligerui="{ valueFieldID:'helpTypes',data: [{'id':'101','text':'低保救助'},{'id':'102','text':'就业支持'},{'id':'103','text':'技能培训'},{'id':'104','text':'教育救助'},{'id':'105','text':'医疗救助'},{'id':'106','text':'其他救助'}]}" validate="{required:true}"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left"> 致贫原因：</td>
	            <td class="l-t-td-right" colspan="5">
	            	<input type="checkbox" id="poorReason-txt" name="poorReason" ltype="checkboxGroup" ligerui="{ valueFieldID:'poorReason',data: [{'id':'10201','text':'收入匮乏'},{'id':'10202','text':'教育支出高'},{'id':'10203','text':'医疗费支出高'},{'id':'10204','text':'无工作'},{'id':'10205','text':'临时灾难'},{'id':'10206','text':'无劳动能力'},{'id':'10207','text':'生存条件恶劣'},{'id':'10208','text':'年老体弱'},{'id':'10209','text':'其他'},{'id':'10210','text':'因残'}]}" validate="{required:true}"/>

	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left"> 开户行：</td>
	            <td class="l-t-td-right">
	            	<input type="text" id="bank-txt" name="_bank-txt" ltype="select" ligerui="{ valueFieldID:'bank',data: [{id:'1',text:'中国农业银行'},{id:'2',text:'中国建设银行'},{id:'3',text:'中国工商银行'},{id:'4',text:'中国银行'},{id:'5',text:'农村信用社',children:[{id:'ger',text:'re'}]},{id:'6',text:'中国邮政储蓄银行'},{id:'7',text:'中国招商银行'},{id:'8',text:'成都银行'}]}" validate="{required:true}"/>
	            	<!-- <input name="bank" type="text" ltype='text' value="四川省农村信用社" validate="{required:true,maxlength:200}"  /> -->
	            </td>
	            <td class="l-t-td-left"> 开户账号：</td>
	            <td class="l-t-td-right">
	            	<input name="bankAccount" type="text" ltype='text' validate="{required:true,creditcard:true,rangelength:[16,19]}"/>
	            </td>
	            <td class="l-t-td-left"> 租房费用：</td>
	            <td class="l-t-td-right">
	            	<input name="rentalCost" type="text" ltype='spinner' ligerui="{suffix:'元',type:'int'}"/>
	            </td>
	        </tr>
	        <tr>
	        	<td class="l-t-td-left"> 申请原因 ：</td>
	            <td class="l-t-td-right" colspan="5">
	            	<textarea name="applyReason" cols="70" rows="4" class="l-textarea" validate="{required:true}"></textarea>
	            </td>
	        </tr>
	        <tr>
	        	<td class="l-t-td-left"> 申请时间：</td>
	            <td class="l-t-td-right">
	            	<input name="applyTime" type="text" ltype='date' validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}"/>
	            </td>
	            <td class="l-t-td-left"> 家庭属性：</td>
	            <td class="l-t-td-right" colspan="3">
	            	<input type="checkbox" id="homeType-txt" name="homeType" ltype="checkboxGroup" ligerui="{ valueFieldID:'homeType',data: [{'id':'1','text':'优抚对象'},{'id':'2','text':'残疾人家庭'},{'id':'3','text':'离异家庭'},{'id':'4','text':'孤老'},{'id':'5','text':'孤儿'},{'id':'6','text':'重病家庭'},{'id':'99','text':'其它'}],initValue:'0'}"/>
	            </td>
	        </tr>

	        <tr>
	        	<td class="l-t-td-left">银行贷款情况：</td>
                <td class="l-t-td-right"><input name="bankLoan" type="text" value="" ltype='spinner' ligerui="{suffix:'元',type:'int'}" validate="{maxlength:8}"/></td>
                <td class="l-t-td-left">私有贷款情况：</td>
                <td class="l-t-td-right"><input name="privLoan" type="text" value="" ltype='spinner' ligerui="{suffix:'元',type:'int'}" validate="{maxlength:8}"/></td>
	        	<td class="l-t-td-left"> 上年度建房费：</td>
	            <td class="l-t-td-right"><input name="lastBuildFee" type="text" ltype='spinner' ligerui="{suffix:'元',type:'int'}"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left"> 上年度医疗费：</td>
	            <td class="l-t-td-right"><input name="lastMedicalFee" type="text" ltype='spinner' ligerui="{suffix:'元',type:'int'}"/>
	            </td>
	            <td class="l-t-td-left"> 上年度教育费：</td>
	            <td class="l-t-td-right"><input name="lastEduFee" type="text" ltype='spinner' ligerui="{suffix:'元',type:'int'}"/>
	            </td>
	            <td class="l-t-td-left"> 上年度其他费：</td>
	            <td class="l-t-td-right"><input name="lastOtherFee" type="text" ltype='spinner' ligerui="{suffix:'元',type:'int'}"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left"> 备注：</td>
	            <td class="l-t-td-right" colspan="5">
	            	<textarea name="remark" cols="70" rows="4" class="l-textarea"></textarea>
	            </td>
	        </tr>
	    </table>
    </fieldset>
</body>
</html>

    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>家庭成员情况</div>
        </legend>
        <div id="interviewDetail"></div>
    </fieldset>
    <div id="table1" pageStatus="detail">
        <fieldset class="l-fieldset">
            <legend class="l-legend">
                <div>家庭财产情况</div>
            </legend>
            <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
                <tr>
                    <td class="l-t-td-left"> 住房情况：</td>
                    <td class="l-t-td-right">
                        <input type="checkbox" id="house-txt" name="house" ltype="checkboxGroup" ligerui="{ valueFieldID:'house',data: [{'id':'1','text':'楼房'},{'id':'2','text':'平房'},{'id':'3','text':'小青瓦'},{'id':'4','text':'借住'},{'id':'5','text':'无房'}]}" validate="{required:true}"/>
                    </td>
                    <td class="l-t-td-left"> 面积：</td>
                    <td class="l-t-td-right">
                        <input name="houseArea" type="text" ltype='spinner' ligerui="{suffix:'㎡',type:'int'}"/>
                    </td>
                    <td class="l-t-td-left"> 装饰情况：</td>
                    <td class="l-t-td-right">
                        <input type="checkbox" id="houseDec-txt" name="houseDec" ltype="checkboxGroup" ligerui="{ valueFieldID:'houseDec',data: [{'id':'0','text':'未装饰'},{'id':'1','text':'中等'},{'id':'2','text':'豪华'}]}" validate="{required:true}"/>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 交通工具：</td>
                    <td class="l-t-td-right">
                        <input type="checkbox" id="vehicle-txt" name="vehicle" ltype="checkboxGroup" ligerui="{ valueFieldID:'vehicle',data: [{'id':'1','text':'汽车'},{'id':'2','text':'摩托车'}]}"/>
                    </td>
                    <td class="l-t-td-left"> 家用电器：</td>
                    <td class="l-t-td-right">
                        <input type="checkbox" id="appliance-txt" name="appliance" ltype="checkboxGroup" ligerui="{ valueFieldID:'appliance',data: [{'id':'1','text':'空调'},{'id':'3','text':'冰箱'},{'id':'2','text':'电脑'}]}"/>
                    </td>
                    <td class="l-t-td-left"> 通讯工具：</td>
                    <td class="l-t-td-right">
                        <input type="checkbox" id="communicationTool-txt" name="communicationTool" ltype="checkboxGroup" ligerui="{ valueFieldID:'communicationTool',data: [{'id':'1','text':'手机'},{'id':'2','text':'座机'}]}"/>
                    </td>

                </tr>
                <tr>
                    <td class="l-t-td-left"> 其他财产情况：</td>
                    <td class="l-t-td-right" colspan="5">
                        <textarea name="other" cols="70" rows="3" class="l-textarea" validate="{maxlength:1000}"></textarea>
                    </td>
                </tr>
            </table>
        </fieldset>
        <fieldset class="l-fieldset">
            <legend class="l-legend">
                <div>调查意见</div>
            </legend>
            <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
                <tr>
                    <td class="l-t-td-left"> 调查意见：</td>
                    <td class="l-t-td-right" colspan="5">
                        <textarea name="sug" cols="70" rows="3" class="l-textarea" validate="{required:true,maxlength:1000}"></textarea>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left"> 调查成员签字：</td>
                    <td id="person1" colspan="3" style="width:300px;height:170px;border: 0px;text-align: center;vertical-align: middle;">
                        签&nbsp;&nbsp;字
                    </td>
                    <td class="l-t-td-left"> 调查时间：</td>
                    <td class="l-t-td-right">
                        <input name="time" type="text" ltype='date' value="" validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}"/>
                    </td>
                </tr>

                    <!-- 城市入户调查 -->
                    <tr>
                        <td class="l-t-td-left" colspan="6">
                            <table border="0" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
                                <tr>
                                    <td class="l-t-td-left"> 社区居委会（盖章）：</td>
                                    <td id="villageSeal1" style="width:300px;height:170px;border: 0px;text-align: center;vertical-align: middle;"> 印&nbsp;&nbsp;&nbsp;章</td>

                                    <td class="l-t-td-left"> 乡镇人民政府（盖章）：</td>
                                    <td id="townSeal1" style="width:300px;height:170px;border: 0px;text-align: center;vertical-align: middle;"> 印&nbsp;&nbsp;&nbsp;章</td>
                                </tr>
                            </table>
                        </td>
                    </tr>


            </table>
        </fieldset>
    </div>
<form id="form1">
    <input type="hidden" id="id" name="id" value=""/>
    <input type="hidden" id="homeId" name="homeId" value=""/>
</form>
</div>
<div class="toolbar-tm">
	<div class="toolbar-tm-bottom"></div>
</div>
</body>
</html>
