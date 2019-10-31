<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>2016特检院大数据</title>
<%@include file="/k/kui-base-list.jsp"%>
<meta http-equiv="cache-control" content="no-cache" />
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="description" content="luofanting.com.cn">  
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0"> 
  <script src="app/statistics/js/echarts.js"></script>
  <script src="app/statistics/weixin/js/china.js"></script>
<link rel="stylesheet" href="app/statistics/weixin/css/style.css">
<link rel="stylesheet" href="app/statistics/weixin/css/animate.css">
</head>  
<style>  
.swipe {  
    overflow: hidden;  
    visibility: hidden;  
    position: relative;  
}  
.swipe-wrap {  
    overflow: hidden;  
    position: relative;  
    height: 100%;
}  
.swipe-wrap > figure {  
    float: left;  
    width: 100%; 
    height: 98%;  
    position: relative;  
}  
#slider {  
    max-width: 650px;/* 设置最大的宽度 */  
    margin: 0px auto;  /* 居中对齐 */  
}  
  
figure {  
    margin: 0;/* 对齐，四周宽度都为0，在轮播的时候，以整张图片进行移动 */  
}  
 div.swipe {  
   /*  border: 1px solid gray;   */
     height: 100%;  
} 
.page-swipe nav #position {  
    text-align: center;  
    list-style: none;  
    margin: 0;  
    padding: 0  
}  
.page-swipe nav #position li {  
    display: inline-block;  
    width: 10px;  
    height: 10px;  
    border-radius: 10px;  
    background: #ffffff;  
    box-shadow: inset 0 1px 3px black,0 0 1px 1px #gray;  
    margin: 0 2px;  
    cursor: pointer  
}  
.page-swipe nav #position li.on {  
    box-shadow: inset 0 1px 3px -1px #28b4ea,0 1px 2px rgba(0,0,0,.5);  
    background-color: #1293dc;  
    background-image: -webkit-gradient(linear,left top,left bottom,color-stop(0%,#1293dc),color-stop(100%,#0f6297));  
    background-image: -webkit-linear-gradient(top,#1293dc,#0f6297);  
    background-image: -moz-linear-gradient(top,#1293dc,#0f6297);  
    background-image: -ms-linear-gradient(top,#1293dc,#0f6297);  
    background-image: -o-linear-gradient(top,#1293dc,#0f6297);  
    background-image: linear-gradient(top,#1293dc,#0f6297)  
} 
.face{
 height: 100%;  
} 
</style>  
<body class="page-swipe">  
<div id="slider" class="swipe" style="visibility:visible;">  
    <div class="swipe-wrap">  
    	<figure>  
            <div class="face faceInner">  
             	 <div class="layout">
					 <div class="container bg01" >
							<div class="in_logo"> <img src="app/statistics/weixin/images/lo_t.png"  class="animated  slideInLeft "> </div>
							<div class="in_2016"> <img src="app/statistics/weixin/images/2016ds.png" class="animated zoomIn delay_2" > </div>	
					 		<div class="in_ren"> <img src="app/statistics/weixin/images/ren.png" class="animated fadeInUp delay_5" > </div>
					 </div>
				</div>
                
            </div>  
        </figure>  
        <figure>  
            <div class="face faceInner">  
             	   <div class="layout">
	 
				 <div class="container bg02" >
				 	
					<div class="box01">
						<div class="max_title">
							<img src="app/statistics/weixin/images/a_t1.png"  class=" animated flipInX ">
						</div>
						<div class="num01 animated lightSpeedIn delay_2">
							<span class="dsj"> ${data.enterYear==null?'':data.enterYear}</span > 年
							<span class="dsj"> ${data.enterMonth==null?'':data.enterMonth}</span> 月
							 <span class="dsj"> ${data.enterDay==null?'':data.enterDay}</span> 天 
							
						</div>
					</div>
					
					<div class="box02">
					     <div class="max_title2 animated flipInX delay_5 ">
						</div>
						 <span class="zhenshu dsj animated  bounceInDown delay_5"> ${data.cartListC}</span>
					
					</div>
				<c:if test="${data.cartListC>0}">
					<div class="inzs_list animated bounceInUp delay_8" >
						   
						   <table class="biaot" align="center" cellpadding="0" cellspacing="0" >
							<tbody>
							  <tr height="30">
								<th align="left"  width="25%" >证书项目</th>
								<th align="left"  width="25%" >证书性质</th>
								<th align="left"  width="50%" >证书编号</th>
				
							  </tr>
							</tbody>
						  </table>
						  
						  
						  <table id="showTable"  align="center" cellpadding="0" cellspacing="0" class="biao_body">
							<tbody id="certtb">  
							  </tbody>
						  </table>
						   </div>
						 </div>	
					</div>
                </c:if>
               <div class="box01">
               <c:if test="${data.education!=null}"><span class="dsj">学历：</span>${data.education}</c:if>
               <c:if test="${data.empIdCard!=null}"><span class="dsj">身份证：</span>${data.empIdCard}</c:if>
               </div>
            </div>  
        </figure>  
        <figure>  
            <div class="face faceInner"> 
              	<div class="layout">
	 
					 <div class="container bg03" >
					 	
						<div class="box03">
							<div class="max_title">
								<img src="app/statistics/weixin/images/b_t1.png"  class=" animated flipInY ">
							</div>
				
						</div>
						
					    <div class="work_list">
							
							<div class="w_bt w_t1 animated fadeInRight delay_5">
								
								
								<div> <span class="work_tit">出具检验报告</span> <span class="dsj red"> ${data.report. lr_count} </span>份</div>
								
								<div class="bar">
									<div  class="remainingSpaceUi">
									<span class="remainingSpaceUi_span" style="width: ${fn:substring((data.report. lr_count)/(data.reportAll. lr_count)*100, 0, 5)}%; background-color:#ff7700; background-position: initial initial; background-repeat: initial initial;"></span> 
									</div>
									
									
									<span class="bar_tt">占年度全院的${fn:substring((data.report. lr_count)/(data.reportAll. lr_count)*100, 0, 5)}% </span>
								</div>
							</div>
							
							
							
							
							<div class="w_bt w_t2 animated fadeInRight delay_8">
							
								<div> <span class="work_tit">审核报告书</span> <span class="dsj red">${data.report. sh_count}</span>份</div>
								
								<div class="bar">
									<div  class="remainingSpaceUi">
									<span class="remainingSpaceUi_span" style="width: 29.396490455690413%; background-color:#ff7700; background-position: initial initial; background-repeat: initial initial;"></span> 
									</div>
									
									
									<span class="bar_tt">超过12%人 </span>
								</div>
							
							
							</div>
							<div class="w_bt w_t3 animated fadeInRight delay_10">
								
								
								<div> <span class="work_tit">签发报告</span> <span class="dsj red">${data.report. qf_count}</span>份</div>
								
								<div class="bar">
									<div  class="remainingSpaceUi">
									<span class="remainingSpaceUi_span" style="width: 22.396490455690413%; background-color:#ff7700; background-position: initial initial; background-repeat: initial initial;"></span> 
									</div>
									
									
									<span class="bar_tt">超过10%人</span>
								</div>
							</div>
							
							<div class="w_bt"></div>
						
						    <div class="work_bg animated fadeInLeft delay_2 time_o_5" ></div>
						
						</div>
					 </div>	
				</div>
            </div>  
        </figure>  
        
         <figure>  
            <div class="face faceInner">
                <div class="layout">
	 
					 <div class="container bg04" >
					 	
						<div class="box03">
							<div class="max_title">
								<img src="app/statistics/weixin/images/b_t1.png"  class=" animated flipInY ">
							</div>
				
						</div>
						
					    <div class="work_list">
							
							<div class="w_bt w_t1 animated fadeInRight delay_5">
								<div> <span class="work_tit">打印报告</span> <span class="dsj red">12365</span>份</div>
								
								<div class="bar">
									<div  class="remainingSpaceUi">
									<span class="remainingSpaceUi_span" style="width: 19.396490455690413%; background-color:#ff7700; background-position: initial initial; background-repeat: initial initial;"></span> 
									</div>
									<span class="bar_tt">占全院15% </span>
								</div>
							</div>
							
							
							
							
							<div class="w_bt w_t2 animated fadeInRight delay_8">
							
								<div> <span class="work_tit">打印合格证</span> <span class="dsj red">5669</span>份</div>
								
								<div class="bar">
									<div  class="remainingSpaceUi">
									<span class="remainingSpaceUi_span" style="width: 29.396490455690413%; background-color:#ff7700; background-position: initial initial; background-repeat: initial initial;"></span> 
									</div>
									
									
									<span class="bar_tt">占全院12% </span>
								</div>
							
							
							</div>
							<div class="w_bt w_t3 animated fadeInRight delay_10">
								
								
								<div> <span class="work_tit">领取</span> <span class="dsj red">2212</span>份</div>
								
								<div class="bar">
									<div  class="remainingSpaceUi">
									<span class="remainingSpaceUi_span" style="width: 22.396490455690413%; background-color:#ff7700; background-position: initial initial; background-repeat: initial initial;"></span> 
									</div>
									
									
									<span class="bar_tt">占全院10%</span>
								</div>
							</div>
							
							<div class="w_bt w_t4 animated fadeInRight delay_12">
							
								<div> <span class="work_tit">归档报告</span> <span class="dsj red">5230</span>份</div>
								
								<div class="bar">
									<div  class="remainingSpaceUi">
									<span class="remainingSpaceUi_span" style="width: 42.396490455690413%; background-color:#ff7700; background-position: initial initial; background-repeat: initial initial;"></span> 
									</div>
									
									
									<span class="bar_tt">占全院40%</span>
								</div>			
							
							
							</div>
						
						    <div class="work_bg animated fadeInLeft delay_2 time_o_5" ></div>
						
						</div>
					 </div>
				</div>
            </div>  
        </figure> 
        
        <figure>  
            <div class="face faceInner">
                <div class="layout">
	 
					 <div class="container bg05" >
					 	
						<div class="box03">
							<div class="max_title">
								<img src="app/statistics/weixin/images/c_t1.png"  class=" animated flipInY ">
							</div>
				
						</div>
						
					    <div class="px_bd animated bounceIn delay_2">
						
							<div class="px_nub"> ${data.pxSum} </div>
						
						</div>
						
						<div class="px_ad animated fadeInUp delay_5">
							<div><span class="px_time">${data.pxts} <i style="font-size:.5rem;">天</i></span> <span style=" margin-right:.25rem"> &nbsp;</span> <span class="px_tt">${data.pxdd }</span>   </div>
						</div>
					 </div>
				</div>
            </div>  
        </figure>  
        <figure> 
         	<div class="face faceInner">  
         	
         	<div class="layout">
				 <div class="container bg02" >
					<div class="box01">
						<div class="max_title">
							<img src="app/statistics/weixin/images/ld_tt.png"  class=" animated  bounceInDown ">
						</div>
					
					</div>
			<div id="main" style="width: 99%;height:90%;"></div>
    		<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        var geoCoordMap = {
        	    '上海': [121.4648,31.2891],
        	    '东莞': [113.8953,22.901],
        	    '东营': [118.7073,37.5513],
        	    '中山': [113.4229,22.478],
        	    '临汾': [111.4783,36.1615],
        	    '临沂': [118.3118,35.2936],
        	    '丹东': [124.541,40.4242],
        	    '丽水': [119.5642,28.1854],
        	    '乌鲁木齐': [87.9236,43.5883],
        	    '佛山': [112.8955,23.1097],
        	    '保定': [115.0488,39.0948],
        	    '兰州': [103.5901,36.3043],
        	    '包头': [110.3467,41.4899],
        	    '北京': [116.4551,40.2539],
        	    '北海': [109.314,21.6211],
        	    '南京': [118.8062,31.9208],
        	    '南宁': [108.479,23.1152],
        	    '南昌': [116.0046,28.6633],
        	    '南通': [121.1023,32.1625],
        	    '厦门': [118.1689,24.6478],
        	    '台州': [121.1353,28.6688],
        	    '合肥': [117.29,32.0581],
        	    '呼和浩特': [111.4124,40.4901],
        	    '咸阳': [108.4131,34.8706],
        	    '哈尔滨': [127.9688,45.368],
        	    '唐山': [118.4766,39.6826],
        	    '嘉兴': [120.9155,30.6354],
        	    '大同': [113.7854,39.8035],
        	    '大连': [122.2229,39.4409],
        	    '天津': [117.4219,39.4189],
        	    '太原': [112.3352,37.9413],
        	    '威海': [121.9482,37.1393],
        	    '宁波': [121.5967,29.6466],
        	    '宝鸡': [107.1826,34.3433],
        	    '宿迁': [118.5535,33.7775],
        	    '常州': [119.4543,31.5582],
        	    '广州': [113.5107,23.2196],
        	    '廊坊': [116.521,39.0509],
        	    '延安': [109.1052,36.4252],
        	    '张家口': [115.1477,40.8527],
        	    '徐州': [117.5208,34.3268],
        	    '德州': [116.6858,37.2107],
        	    '惠州': [114.6204,23.1647],
        	    '成都': [103.9526,30.7617],
        	    '扬州': [119.4653,32.8162],
        	    '承德': [117.5757,41.4075],
        	    '拉萨': [91.1865,30.1465],
        	    '无锡': [120.3442,31.5527],
        	    '日照': [119.2786,35.5023],
        	    '昆明': [102.9199,25.4663],
        	    '杭州': [119.5313,29.8773],
        	    '枣庄': [117.323,34.8926],
        	    '柳州': [109.3799,24.9774],
        	    '株洲': [113.5327,27.0319],
        	    '武汉': [114.3896,30.6628],
        	    '汕头': [117.1692,23.3405],
        	    '江门': [112.6318,22.1484],
        	    '沈阳': [123.1238,42.1216],
        	    '沧州': [116.8286,38.2104],
        	    '河源': [114.917,23.9722],
        	    '泉州': [118.3228,25.1147],
        	    '泰安': [117.0264,36.0516],
        	    '泰州': [120.0586,32.5525],
        	    '济南': [117.1582,36.8701],
        	    '济宁': [116.8286,35.3375],
        	    '海口': [110.3893,19.8516],
        	    '淄博': [118.0371,36.6064],
        	    '淮安': [118.927,33.4039],
        	    '深圳': [114.5435,22.5439],
        	    '清远': [112.9175,24.3292],
        	    '温州': [120.498,27.8119],
        	    '渭南': [109.7864,35.0299],
        	    '湖州': [119.8608,30.7782],
        	    '湘潭': [112.5439,27.7075],
        	    '滨州': [117.8174,37.4963],
        	    '潍坊': [119.0918,36.524],
        	    '烟台': [120.7397,37.5128],
        	    '玉溪': [101.9312,23.8898],
        	    '珠海': [113.7305,22.1155],
        	    '盐城': [120.2234,33.5577],
        	    '盘锦': [121.9482,41.0449],
        	    '石家庄': [114.4995,38.1006],
        	    '福州': [119.4543,25.9222],
        	    '秦皇岛': [119.2126,40.0232],
        	    '绍兴': [120.564,29.7565],
        	    '聊城': [115.9167,36.4032],
        	    '肇庆': [112.1265,23.5822],
        	    '舟山': [122.2559,30.2234],
        	    '苏州': [120.6519,31.3989],
        	    '莱芜': [117.6526,36.2714],
        	    '菏泽': [115.6201,35.2057],
        	    '营口': [122.4316,40.4297],
        	    '葫芦岛': [120.1575,40.578],
        	    '衡水': [115.8838,37.7161],
        	    '衢州': [118.6853,28.8666],
        	    '西宁': [101.4038,36.8207],
        	    '西安': [109.1162,34.2004],
        	    '贵阳': [106.6992,26.7682],
        	    '连云港': [119.1248,34.552],
        	    '邢台': [114.8071,37.2821],
        	    '邯郸': [114.4775,36.535],
        	    '郑州': [113.4668,34.6234],
        	    '鄂尔多斯': [108.9734,39.2487],
        	    '重庆': [107.7539,30.1904],
        	    '金华': [120.0037,29.1028],
        	    '铜川': [109.0393,35.1947],
        	    '银川': [106.3586,38.1775],
        	    '镇江': [119.4763,31.9702],
        	    '长春': [125.8154,44.2584],
        	    '长沙': [113.0823,28.2568],
        	    '长治': [112.8625,36.4746],
        	    '阳泉': [113.4778,38.0951],
        	    '青岛': [120.4651,36.3373],
        	    '韶关': [113.7964,24.7028]
        	};

        	var BJData = [
        	    [{name:'成都'}, {name:'上海',value:95}],
        	    [{name:'成都'}, {name:'广州',value:90}],
        	    [{name:'成都'}, {name:'大连',value:80}],
        	    [{name:'成都'}, {name:'南宁',value:70}],
        	    [{name:'成都'}, {name:'南昌',value:60}],
        	    [{name:'成都'}, {name:'拉萨',value:50}],
        	    [{name:'成都'}, {name:'长春',value:40}],
        	    [{name:'成都'}, {name:'包头',value:30}],
        	    [{name:'成都'}, {name:'重庆',value:20}],
        	    [{name:'成都'}, {name:'常州',value:10}]
        	];
        	var planePath = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';

        	var convertData = function (data) {
        	    var res = [];
        	    for (var i = 0; i < data.length; i++) {
        	        var dataItem = data[i];
        	        var fromCoord = geoCoordMap[dataItem[0].name];
        	        var toCoord = geoCoordMap[dataItem[1].name];
        	        if (fromCoord && toCoord) {
        	            res.push({
        	                fromName: dataItem[0].name,
        	                toName: dataItem[1].name,
        	                coords: [fromCoord, toCoord]
        	            });
        	        }
        	    }
        	    return res;
        	};

        	var color = ['#ff4500'];
        	var series = [];
        	var item = ['成都', BJData];
        	
        	    series.push({
        	        name: item[0] + ' Top10',
        	        type: 'lines',
        	        zlevel: 1,
        	        effect: {
        	            show: true,
        	            period: 6,
        	            trailLength: 0.7,
        	            color: '#fff',
        	            symbolSize: 3
        	        },
        	        lineStyle: {
        	            normal: {
        	                color: color[0],
        	                width: 0,
        	                curveness: 0.2
        	            }
        	        },
        	        data: convertData(item[1])
        	    },
        	    {
        	        name: item[0] + ' Top10',
        	        type: 'lines',
        	        zlevel: 2,
        	        symbol: ['none', 'arrow'],
        	        symbolSize: 10,
        	        effect: {
        	            show: true,
        	            period: 6,
        	            trailLength: 0,
        	            symbol: planePath,
        	            symbolSize: 15
        	        },
        	        lineStyle: {
        	            normal: {
        	                color: color[0],
        	                width: 1,
        	                opacity: 0.6,
        	                curveness: 0.2
        	            }
        	        },
        	        data: convertData(item[1])
        	    },
        	    {
        	        name: item[0] + ' Top10',
        	        type: 'effectScatter',
        	        coordinateSystem: 'geo',
        	        zlevel: 2,
        	        rippleEffect: {
        	            brushType: 'stroke'
        	        },
        	        label: {
        	            normal: {
        	                show: true,
        	                position: 'right',
        	                formatter: '{b}'
        	            }
        	        },
        	        symbolSize: function (val) {
        	            return val[2] / 8;
        	        },
        	        itemStyle: {
        	            normal: {
        	                color: color[0]
        	            }
        	        },
        	        data: item[1].map(function (dataItem) {
        	            return {
        	                name: dataItem[1].name,
        	                value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
        	            };
        	        })
        	    });

        	option = {
        	    backgroundColor: '#ffffff',
        	    title : {
        	        text: '',
        	        subtext: '',
        	        left: 'center',
        	        textStyle : {
        	            color: '#fff'
        	        }
        	    },
        	    tooltip : {
        	        trigger: 'item'
        	    },
        	   /*  legend: {
        	        orient: 'vertical',
        	        top: 'bottom',
        	        left: 'right',
        	        data:['北京 Top10'],
        	        textStyle: {
        	            color: '#fff'
        	        },
        	        selectedMode: 'single'
        	    }, */
        	    geo: {
        	        map: 'china',
        	        label: {
        	            emphasis: {
        	                show: false
        	            }
        	        },
        	        roam: false,
        	        itemStyle: {
        	            normal: {
        	                areaColor: '#8fd1e8',
        	                borderColor: '#fff'
        	            },
        	            emphasis: {
        	                areaColor: '#fbdd08'
        	            }
        	        }
        	    },
        	    series: series
        	};
        	// 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            </script>
				 </div>
			</div>
    		 </div>  
     	</figure>
     	<figure>  
            <div class="face faceInner">  
            	<div class="layout">
	 
					 <div class="container bg06" >
					 	
						<div class="box03">
							<div class="max_title">
								<img src="app/statistics/weixin/images/d_t1.png"  class=" animated flipInY ">
							</div>
				
						</div>
						
						<div class="gzzj_bt animated fadeInUp">
							<div> 特检云内有你<span>144</span>条工作足迹，这里是最近<span>20</span>条</div>
							<div> （可在检验软件云检索内输入姓名查看详细）</div>
							
						</div>
						
						<div class="gzzj_list animated fadeInUp delay_2 ">
						
						     	<ul>
				                	<li>
				                        <div class="w_break">· 人力资源部对信息宣传中心两位同志进行民主测评</div> <span>2016-05-23</span>
				                    </li>
				                	<li>
				                        <div class="w_break">· 我院高手如何妙手回春让淘汰电脑再立新功</div> <span>2016-05-23</span>
				                    </li>
				                	<li>
				                        <div class="w_break">· 【简讯】黄坚率队赴贵州省特检院调研</div> <span>2016-05-23</span>
				                    </li>
				                	<li>
				                        <div class="w_break">· 市局召开质监信息系统建设项目情况汇报会</div> <span>2016-05-23</span>
				                    </li>
				                    <li >
				                        <div class="w_break">· 省特检院举行第二届感动特检人物评选活动暨2016新春团拜会</div> <span>2016-05-23</span>
				                    </li>
				                	<li>
				                        <div class="w_break">· 人力资源部对信息宣传中心两位同志进行民主测评</div> <span>2016-05-23</span>
				                    </li>
				                	<li>
				                        <div class="w_break">· 我院高手如何妙手回春让淘汰电脑再立新功</div> <span>2016-05-23</span>
				                    </li>
				                	<li>
				                        <div class="w_break">· 【简讯】黄坚率队赴贵州省特检院调研</div> <span>2016-05-23</span>
				                    </li>
				                	<li>
				                        <div class="w_break">· 市局召开质监信息系统建设项目情况汇报会</div> <span>2016-05-23</span>
				                    </li>
				                    <li >
				                        <div class="w_break">· 省特检院举行第二届感动特检人物评选活动暨2016新春团拜会</div> <span>2016-05-23</span>
				                    </li>		
				                </ul>
						</div>
					 </div>
				</div>
            </div>  
        </figure>
     	<figure>  
            <div class="face faceInner">
            	<div class="layout">
	 
					 <div class="container bg07" >
						<div class="gzzj_bt msg_bt animated fadeInUp">
							<div> 2016年你为我院检验平台提出过<span>${data.advanceSum}</span>条建议，其中 <span>20%</span>       被采纳。</div>			
						</div>
						
						<div class="gzzj_list msg_list animated fadeInUp delay_2 ">
						
						     	<ul>
				                	<li>
				                        <div class="w_break">· 人力资源部对信息宣传中心两位同志进行民主测评</div> <span>2016-05-23</span>
				                    </li>
				                	<li>
				                        <div class="w_break">· 我院高手如何妙手回春让淘汰电脑再立新功</div> <span>2016-05-23</span>
				                    </li>
				                	<li>
				                        <div class="w_break">· 【简讯】黄坚率队赴贵州省特检院调研</div> <span>2016-05-23</span>
				                    </li>
				                	<li>
				                        <div class="w_break">· 市局召开质监信息系统建设项目情况汇报会</div> <span>2016-05-23</span>
				                    </li>
				                    <li >
				                        <div class="w_break">· 省特检院举行第二届感动特检人物评选活动暨2016新春团拜会</div> <span>2016-05-23</span>
				                    </li>
				                </ul>
						</div>
						<div class="app_down animated fadeInUp delay_5">
						
							<div class="down_tt">
							
								<div><a href="#" class="but01">安卓版下载</a></div>
								<div><a href="#" class="but02">苹果版下载</a></div>
								<div>
								<div class="bar kjdx">
								<span class="bar_tt">你的特检云空间已使用</span>
									<div  class="remainingSpaceUi">
									<span class="remainingSpaceUi_span" style="width: 22.396490455690413%; background-color:#ff7700; background-position: initial initial; background-repeat: initial initial;"></span>                    
									</div>
									<span> 225M/1G</span>
								</div>
								</div>
							</div>
						</div>
					 </div>
				</div>
            </div>  
        </figure>
        <figure>  
            <div class="face faceInner">  
            	<div class="layout">
					 <div class="container bg08" >
						<div class="end_img">
							<div class="max_title">
								<img src="app/statistics/weixin/images/end_lo.png"  class=" animated fadeInDown ">
							</div>
				
						</div>
						
						<div class="end_img xcy delay_2"> <img src="app/statistics/weixin/images/end_tt.png"  class=" animated  fadeInDown "> </div>
						
						<div class="copyright animated fadeInDown delay_5"> 技术支持：信息宣传中心 </div>
					 </div>
				</div>
            </div>  
        </figure>
    </div>  
</div>  
<div style="position: absolute;top: 94%;left: 0rem;width:100%;">
<nav>  
<ul id="position">  
  <li class="on"></li>  
  <li class=""></li>  
   <li class=""></li>  
  <li class=""></li>  
   <li class=""></li>  
  <li class=""></li> 
    <li class=""></li> 
     <li class=""></li> 
    <li class=""></li> 
</ul>  
</nav>  
</div>
<script src="app/statistics/weixin/js/swipe.js"></script>
<script type="text/javascript" src="app/statistics/weixin/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="app/statistics/weixin/js/script.js"></script>
<script>
var slider =  
  Swipe(document.getElementById('slider'), {  
    auto: 6000,  
    continuous: true,  
    callback: function(pos) {  
        var i = bullets.length;  
        while(i--){  
            bullets[i].className = ' ';  
        }  
        bullets[pos].className = 'on';  
    }  
  });  
var bullets = document.getElementById('position').getElementsByTagName('li');  

var cartList = <%=request.getAttribute("cartList")%>;
alert(cartList.length)
for (var i = 0; i < cartList.length; i++) {
	$("#certtb").append('<tr>'
			+'<td  width="25%">'+cartList[i].cert_type+'</td>'
			+'<td  width="25%">'+cartList[i].cert_level+'</td>'
			+'<td  width="50%">'+cartList[i].cert_no+'</td>' +'</tr>')
}
</script>  
</body>  
</html>