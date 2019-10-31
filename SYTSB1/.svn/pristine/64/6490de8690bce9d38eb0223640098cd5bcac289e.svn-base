<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
</head>

<body>
  
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       
       	<input name="id" type="hidden" />
		
	
       <tr>
        <td class="l-t-td-left" style="width: 15%">使用登记证编号：</td>
        <td class="l-t-td-right"><input name="registration_num" type="text" ltype='text' validate="{required:false,maxlength:30}"/> 
        </td>
        <td class="l-t-td-left" style="width: 15%">锅炉型号：</td>
        <td class="l-t-td-right"><input name="boiler_model" type="text" ltype='text' validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">锅炉房类型：</td>
        <td class="l-t-td-right"><input name="boiler_room_type" type="text" ltype='text' validate="{required:false,maxlength:30}"/>
        </td>
        <td class="l-t-td-left">制造国：</td>
        <td class="l-t-td-right"><input name="manufacturer" type="text" ltype='text' validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr>
       <td class="l-t-td-left">产品监检单位名称：</td>
      	 <input   name="fk_inspection_unit_id" type='hidden'/>
        <td class="l-t-td-right">
        <input type="text" id="cp_by1" name="cp_by1"   ltype="text"  validate="{required:false}" onclick="selectorg('5')"
										ligerui="{suffixWidth:'30',readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('5')}}]}"/>
       
        </td>
        <td class="l-t-td-left">锅炉结构形式：</td>
        <td class="l-t-td-right">
        	<input name="boiler_structure" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="boiler_structure" code="BASE_PARA_P10001001" />
        	 -->
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">设计工作压力：</td>
        <td class="l-t-td-right">
        	<input name="design_pressure" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'MPa'}" validate="{required:false,maxlength:30}"/>
        </td>
        <td class="l-t-td-left">许可使用压力：</td>
        <td class="l-t-td-right">
        	<input name="permit_pressure" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'MPa'}" validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">额定出力：</td>
        <td class="l-t-td-right">
        	<input name="rated_output" type="text" ltype='text' validate="{required:false,maxlength:30}"/>
        </td>
        <td class="l-t-td-left">介质出口温度：</td>
        <td class="l-t-td-right">
        	<input name="outlet_temperature" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'℃'}" validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">加热方式：</td>
        <td class="l-t-td-right">
        	<input name="heating_method" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
	        <!-- 
	        <u:combo name="heating_method" code="BASE_PARA_P10001002" />
	         -->
        </td>
        <td class="l-t-td-left">燃料种类：</td>
        <td class="l-t-td-right">
        	<input name="fuel_type" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="fuel_type" code="BASE_PARA_P10001003" />
        	 -->
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">锅炉用途：</td>
        <td class="l-t-td-right">
        	<input name="boiler_use" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="boiler_use" code="BASE_PARA_P10001004" />
        	 -->
        </td>
        <td class="l-t-td-left">使用状态：</td>
        <td class="l-t-td-right">
        	<input name="use_state" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="use_state" code="BASE_PARA_P10001005" />
        	 -->
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">燃烧方式：</td>
        <td class="l-t-td-right">
        	<input name="combustion_type" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="combustion_type" code="BASE_PARA_P10001006" />
        	 -->
        </td>
        <td class="l-t-td-left">水处理方式：</td>
        <td class="l-t-td-right">
        	<input name="water_treatment" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="water_treatment" code="BASE_PARA_P10001007" />
        	 -->
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">除氧方式：</td>
        <td class="l-t-td-right">
        	<input name="deoxidization" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="deoxidization" code="BASE_PARA_P10001008" />
        	 -->
        </td>
        <td class="l-t-td-left">除渣方式：</td>
        <td class="l-t-td-right">
        	<input name="deslagging" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="deslagging" code="BASE_PARA_P10001009" />
        	 -->
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">消烟除尘方式：</td>
        <td class="l-t-td-right">
        	<input name="smoke_dust" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="smoke_dust" code="BASE_PARA_P10001010" />
        	 -->
        </td>
        <td class="l-t-td-left">单位司炉数量：</td>
        <td class="l-t-td-right">
        	<input name="stoker_num" type="text" ltype='text' validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">水质人员数量：</td>
        <td class="l-t-td-right">
        	<input name="water_quality_num" type="text" ltype='text' validate="{required:false,maxlength:30}"/>
        </td>
        <td class="l-t-td-left">水循环方式：</td>
        <td class="l-t-td-right">
        	<input name="p10001011" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="p10001011" code="BASE_PARA_P10001011" />
        	 -->
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">燃烧器布置方式：</td>
        <td class="l-t-td-right">
        	<input name="p10001012" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="p10001012" code="BASE_PARA_P10001012" />
        	 -->
        </td>
        <td class="l-t-td-left">设计燃料可&nbsp;&nbsp;&nbsp;<br/>燃基挥发份：</td>
        <td class="l-t-td-right">
        	<input name="p10001013" type="text" ltype='text' validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">锅炉再热蒸&nbsp;&nbsp;&nbsp;<br/>汽调温方式：</td>
        <td class="l-t-td-right">
        	<input name="p10001015" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="p10001015" code="BASE_PARA_P10001015" /> 
        	 -->
        </td>
        <td class="l-t-td-left">锅炉设计规范：</td>
        <td class="l-t-td-right">
        	<input name="p10001016" type="text" ltype='text' validate="{required:false,maxlength:200}"/>
        	<!-- 
        	<u:combo name="p10001016" code="BASE_PARA_P10001016" />
        	 -->
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">锅炉制造规范：</td>
        <td class="l-t-td-right">
        	<input name="p10001017" type="text" ltype='text' validate="{required:false,maxlength:200}"/>
        </td>
        <td class="l-t-td-left">设备新旧状况：</td>
        <td class="l-t-td-right">
        	<input name="p10001018" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="p10001018" code="BASE_PARA_P10001018" />
        	 -->
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">锅炉监检形式：</td>
        <td class="l-t-td-right">
        	<input name="p10001019" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="p10001019" code="BASE_PARA_P10001019" /> 
        	 -->
        </td>
        <td class="l-t-td-left">锅炉过热蒸&nbsp;&nbsp;&nbsp;<br/>汽调温方式：</td>
        <td class="l-t-td-right">
        	<input name="p10001020" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="p10001020" code="BASE_PARA_P10001020" /> 
        	 -->
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">锅炉补给&nbsp;&nbsp;&nbsp;<br/>水处理方式：</td>
        <td class="l-t-td-right">
        	<input name="p10001021" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="p10001021" code="BASE_PARA_P10001021" />
        	 -->
        </td>
        <td class="l-t-td-left">锅炉汽水分离&nbsp;&nbsp;&nbsp;<br/>装置(方式)：</td>
        <td class="l-t-td-right">
        	<input name="p10001022" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="p10001022" code="BASE_PARA_P10001022" />
        	 -->
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">锅炉水质报&nbsp;&nbsp;&nbsp;<br/>告水处理方法：</td>
        <td class="l-t-td-right">
        	<input name="p10001023" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="p10001023" code="BASE_PARA_P10001023" /> 
        	 -->
        </td>
        <td class="l-t-td-left">锅炉水压报&nbsp;&nbsp;&nbsp;<br/>告试验理由：</td>
        <td class="l-t-td-right">
        	<input name="p10001024" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="p10001024" code="BASE_PARA_P10001024" />
        	 -->
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">油压报告试验理由：</td>
        <td class="l-t-td-right">
        	<input name="p10001025" type="text" ltype='text' validate="{required:false,maxlength:20}"/>
        	<!-- 
        	<u:combo name="p10001025" code="BASE_PARA_P10001025" />
        	 -->
        </td>
        <td class="l-t-td-left">锅炉最大连续蒸发量：</td>
        <td class="l-t-td-right">
        	<input name="p10002005" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'t/h'}" validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">锅炉再热蒸汽流量：</td>
        <td class="l-t-td-right">
        	<input name="p10002006" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'t/h'}" validate="{required:false,maxlength:30}"/>
        </td>
        <td class="l-t-td-left">锅筒工作压力：</td>
        <td class="l-t-td-right">
        	<input name="p10002007" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'MPa'}" validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">锅炉过热器出口压力：</td>
        <td class="l-t-td-right">
        	<input name="p10002008" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'MPa'}" validate="{required:false,maxlength:30}"/>
        </td>
        <td class="l-t-td-left">锅炉再热器入口压力：</td>
        <td class="l-t-td-right">
        	<input name="p10002009" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'MPa'}" validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">锅炉再热器出口压力：</td>
        <td class="l-t-td-right">
        	<input name="p10002010" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'MPa'}" validate="{required:false,maxlength:30}"/>
        </td>
        <td class="l-t-td-left">锅炉给水温度：</td>
        <td class="l-t-td-right">
        	<input name="p10002011" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'℃'}" validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">锅炉过热器出口温度：</td>
        <td class="l-t-td-right">
        	<input name="p10002012" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'℃'}" validate="{required:false,maxlength:30}"/>
        </td>
        <td class="l-t-td-left">锅炉再热器入口温度：</td>
        <td class="l-t-td-right">
        	<input name="p10002013" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'℃'}" validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">锅炉再热器出口温度：</td>
        <td class="l-t-td-right">
        	<input name="p10002014" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'℃'}" validate="{required:false,maxlength:30}"/>
        </td>
        <td class="l-t-td-left">直流锅炉启动压力：</td>
        <td class="l-t-td-right">
        	<input name="p10002015" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'MPa'}" validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">直流锅炉启动流量：</td>
        <td class="l-t-td-right">
        	<input name="p10002016" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'t/h'}" validate="{required:false,maxlength:30}"/>
        </td>
        <td class="l-t-td-left">锅炉设计燃料应用&nbsp;&nbsp;&nbsp;<br/>基低位发热值：</td>
        <td class="l-t-td-right">
        	<input name="p10002017" type="text" ltype='text' ligerui="{suffixWidth:'30',suffix:'大卡'}" validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left">受热面布置方式：</td>
        <td class="l-t-td-right">
        	<input name="p_slmbzfs" type="text" ltype='text' validate="{required:false,maxlength:30}"/>
        </td>
        <td class="l-t-td-left">主体材料：</td>
        <td class="l-t-td-right">
        	<input name="p_ztcl" type="text" ltype='text' validate="{required:false,maxlength:30}"/>
        </td>
       </tr>
      </table>
</body>
</html>
