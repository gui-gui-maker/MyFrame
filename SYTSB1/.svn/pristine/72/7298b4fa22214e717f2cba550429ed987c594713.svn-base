<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
</head>

<body>
  
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       
       	<input name="id" type="hidden" />
		
	
      <tr> 
      
        <td class="l-t-td-left"> 容器名称：</td>
        <td class="l-t-td-right"><input name="container_name" validate="{required:false,maxlength:128}"  ltype='text'  /> 
        </td>
        <td class="l-t-td-left"> 压力容器结构形式：</td>
        <td class="l-t-td-right"><u:combo name="p20001046" code="BASE_PARA_P20001046" />  
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 容器分类：</td>
        <td class="l-t-td-right"><u:combo name="container_claasify" code="BASE_CONTAINER_CLASSIFY" />  
        </td>
        	
      
        <td class="l-t-td-left"> 设计单位名称：</td>
        <td class="l-t-td-right">
        <input name="fk_design_units_id"   type='hidden' /> 
         <input type="text" id="cbz" name="cbz"   ltype="text"  validate="{required:false}" onclick="selectorg('3')"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('3')}}]}"/>
       
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 制造国：</td>
        <td class="l-t-td-right"><input name="manufacturer" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 产品监检单位名称：</td>
        <td class="l-t-td-right">
        <input name="monitor_unit_id"   type='hidden' /> 
        
        <input type="text" id="cp_by2" name="cp_by2"   ltype="text"  validate="{required:false}" onclick="selectorg('4')"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('4')}}]}"/>
        
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 所在车间分厂：</td>
        <td class="l-t-td-right"><input name="workshop" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 容器内径：</td>
        <td class="l-t-td-right"><input name="container_minor_diameter" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 筒体材料：</td>
        <td class="l-t-td-right"><input name="shell_material" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 封头材料：</td>
        <td class="l-t-td-right"><input name="head_material" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 内衬材料：</td>
        <td class="l-t-td-right"><input name="lining_material" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 夹套材料：</td>
        <td class="l-t-td-right"><input name="clamp_material" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 筒体厚度：</td>
        <td class="l-t-td-right"><input name="shell_thk" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 封头厚度：</td>
        <td class="l-t-td-right"><input name="head_thk" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 内衬壁厚：</td>
        <td class="l-t-td-right"><input name="lining_thk" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 夹套厚度：</td>
        <td class="l-t-td-right"><input name="clamp_thk" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 容器容积：</td>
        <td class="l-t-td-right"><input name="container_capacity" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 容器高(长)：</td>
        <td class="l-t-td-right"><input name="container_length" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 壳体重量：</td>
        <td class="l-t-td-right"><input name="shell_weight" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 内件重量：</td>
        <td class="l-t-td-right"><input name="lining_weight" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 充装重量：</td>
        <td class="l-t-td-right"><input name="capacity_weight" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 有无保温绝热：</td>
        <td class="l-t-td-right"><input name="is_insulate" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 壳程设计压力：</td>
        <td class="l-t-td-right"><input name="shell_design_press" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 壳程设计温度：</td>
        <td class="l-t-td-right"><input name="shell_design_temper" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 壳程工作压力：</td>
        <td class="l-t-td-right"><input name="shell_max_press" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 管程设计压力：</td>
        <td class="l-t-td-right"><input name="lining_design_press" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 管程设计温度：</td>
        <td class="l-t-td-right"><input name="lining_design_temper" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 管程工作压力：</td>
        <td class="l-t-td-right"><input name="lining_max_press" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 夹套设计压力：</td>
        <td class="l-t-td-right"><input name="clamp_design_press" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 夹套设计温度：</td>
        <td class="l-t-td-right"><input name="clamp_design_temper" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 夹套工作压力：</td>
        <td class="l-t-td-right"><input name="clamp_max_press" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 壳程介质：</td>
        <td class="l-t-td-right"><input name="shell_medium" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 管程介质：</td>
        <td class="l-t-td-right"><input name="lining_medium" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 夹套介质：</td>
        <td class="l-t-td-right"><input name="clamp_medium" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 氧舱照明：</td>
        <td class="l-t-td-right"><input name="chamber_light" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 氧舱空调电机：</td>
        <td class="l-t-td-right"><input name="chamber_motor" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 氧舱测氧方式：</td>
        <td class="l-t-td-right"><input name="chamber_oxygen_test" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 罐车牌号：</td>
        <td class="l-t-td-right"><input name="ladle_car_number" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 罐车结构型式：</td>
        <td class="l-t-td-right"><input name="ladle_car_structure" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 罐车地盘号码：</td>
        <td class="l-t-td-right"><input name="ladle_car_domain_num" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 产权单位：</td>
        <td class="l-t-td-right"><input name="property_right_unit" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 产权单位代码：</td>
        <td class="l-t-td-right"><input name="property_right_unit_code" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 压力容器型式：</td>
        <td class="l-t-td-right"><u:combo name="p20001002" code="BASE_PARA_P20001002" /> 
        </td>
        <td class="l-t-td-left"> 压力容器类别：</td>
        <td class="l-t-td-right"><u:combo name="p20001003" code="BASE_PARA_P20001003" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 压力容器压力等级：</td>
        <td class="l-t-td-right"><u:combo name="p20001004" code="BASE_PARA_P20001004" /> 
        </td>
        <td class="l-t-td-left"> 压力容器检验类别：</td>
        <td class="l-t-td-right"><u:combo name="p20001006" code="BASE_PARA_P20001006" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 设备新旧状况：</td>
        <td class="l-t-td-right"><u:combo name="p20001007" code="BASE_PARA_P20001007" /> 
        </td>
        <td class="l-t-td-left"> 压力容器监检形式：</td>
        <td class="l-t-td-right"><u:combo name="p20001008" code="BASE_PARA_P20001008" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 安全阀型号：</td>
        <td class="l-t-td-right"><u:combo name="p20001009" code="BASE_PARA_P20001009" /> 
        </td>
        <td class="l-t-td-left"> 安全阀规格：</td>
        <td class="l-t-td-right"><u:combo name="p20001010" code="BASE_PARA_P20001010" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 压力表量程：</td>
        <td class="l-t-td-right"><u:combo name="p20001011" code="BASE_PARA_P20001011" /> 
        </td>
        <td class="l-t-td-left"> 压力表精度：</td>
        <td class="l-t-td-right"><u:combo name="p20001012" code="BASE_PARA_P20001012" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 液面计型式：</td>
        <td class="l-t-td-right"><u:combo name="p20001013" code="BASE_PARA_P20001013" /> 
        </td>
        <td class="l-t-td-left"> 爆破片型号：</td>
        <td class="l-t-td-right"><u:combo name="p20001014" code="BASE_PARA_P20001014" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 紧急切断阀形式：</td>
        <td class="l-t-td-right"><u:combo name="p20001015" code="BASE_PARA_P20001015" /> 
        </td>
        <td class="l-t-td-left"> 温度计形式：</td>
        <td class="l-t-td-right"><u:combo name="p20001016" code="BASE_PARA_P20001016" />  
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 压力容器使用状态：</td>
        <td class="l-t-td-right"><u:combo name="p20001030" code="BASE_PARA_P20001030" /> 
        </td>
        <td class="l-t-td-left"> 封头型式：</td>
        <td class="l-t-td-right"><u:combo name="p20001042" code="BASE_PARA_P20001042" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 支座型式：</td>
        <td class="l-t-td-right"><u:combo name="p20001043" code="BASE_PARA_P20001043" /> 
        </td>
        <td class="l-t-td-left"> 是否快开门：</td>
        <td class="l-t-td-right"><u:combo name="p20001045" code="BASE_PARA_P20001045" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 充装介质：</td>
        <td class="l-t-td-right"><u:combo name="p_czjz" code="BASE_PARA_CZJZ" /> 
        </td>
        <td class="l-t-td-left"> 罐式集装箱联合&nbsp;&nbsp;&nbsp;<br>国危险品编号：</td>
        <td class="l-t-td-right"><input name="p_lhgwxph" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 罐式集装箱长：</td>
        <td class="l-t-td-right"><input name="p_gsjzxcd" validate="{required:false,maxlength:128}" ligerui="{suffix:'mm'}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 罐式集装箱宽：</td>
        <td class="l-t-td-right"><input name="p_gsjzxkd" validate="{required:false,maxlength:128}" ligerui="{suffix:'mm'}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 罐式集装箱高：</td>
        <td class="l-t-td-right"><input name="p_gsjzxgd" validate="{required:false,maxlength:128}" ligerui="{suffix:'mm'}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 保温材料：</td>
        <td class="l-t-td-right"><input name="p_bwcl" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 医疗机构登记号：</td>
        <td class="l-t-td-right"><input name="p_yljgdjh" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 氧舱容限：</td>
        <td class="l-t-td-right"><input name="p_ycrx" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 装卸方式：</td>
        <td class="l-t-td-right"><u:combo name="p_xzfs" code="BASE_PARA_XZFS" /> 
        </td>
        <td class="l-t-td-left"> 保温方式：</td>
        <td class="l-t-td-right"><u:combo name="p_bwfs" code="BASE_PARA_BWFS" /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 空载重量：</td>
        <td class="l-t-td-right"><input name="p_kzzl" validate="{required:false,maxlength:128}" ligerui="{suffix:'Kg'}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 满载总重量：</td>
        <td class="l-t-td-right"><input name="p_mzzl" validate="{required:false,maxlength:128}" ligerui="{suffix:'Kg'}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 罐体(铭牌)编号：</td>
        <td class="l-t-td-right"><input name="p_gtmp" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 罐体外形-外径：</td>
        <td class="l-t-td-right"><input name="p_gtwj" validate="{required:false,maxlength:128}" ligerui="{suffix:'mm'}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 罐体外形-总长：</td>
        <td class="l-t-td-right"><input name="p_gtzc" validate="{required:false,maxlength:128}" ligerui="{suffix:'mm'}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 罐体材料-内筒：</td>
        <td class="l-t-td-right"><input name="p_gtntcl" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 罐体材料-外筒：</td>
        <td class="l-t-td-right"><input name="p_gtwtcl" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 试验压力：</td>
        <td class="l-t-td-right"><input name="p_syyl" validate="{required:false,maxlength:128}" ligerui="{suffixWidth:'30',suffix:'MPa'}" ltype='text' /> 
        </td>
       </tr>
       <tr> 
       
        <td class="l-t-td-left"> 最高工作压力：</td>
        <td class="l-t-td-right"><input name="p_zggzyl" validate="{required:false,maxlength:128}" ligerui="{suffixWidth:'30',suffix:'MPa'}" ltype='text' /> 
        </td>
      
        <td class="l-t-td-left"> 设计温度：</td>
        <td class="l-t-td-right"><input name="p_sjwd" validate="{required:false,maxlength:128}" ligerui="{suffix:'℃'}" ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 允许最大充装量：</td>
        <td class="l-t-td-right"><input name="p_yxzdczl" validate="{required:false,maxlength:128}" ligerui="{suffix:'Kg'}" ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 充装系数：</td>
        <td class="l-t-td-right"><input name="p_czxs" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 入孔位置：</td>
        <td class="l-t-td-right"><input name="p_rkwz" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 铭牌位置：</td>
        <td class="l-t-td-right"><input name="p_mpwz" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 罐体颜色：</td>
        <td class="l-t-td-right"><u:combo name="p_gtys" code="BASE_PARA_GTYS" />  
        </td>
        <td class="l-t-td-left"> 罐体壁厚-筒：</td>
        <td class="l-t-td-right"><input name="p_gtttbh" validate="{required:false,maxlength:128}" ligerui="{suffix:'mm'}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 罐体壁厚-封头：</td>
        <td class="l-t-td-right"><input name="p_gtftbh" validate="{required:false,maxlength:128}" ligerui="{suffix:'mm'}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 罐体壁厚-外筒：</td>
        <td class="l-t-td-right"><input name="p_gtwtbh" validate="{required:false,maxlength:128}" ligerui="{suffix:'mm'}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 安全阀数量：</td>
        <td class="l-t-td-right"><input name="p_aqfsl" validate="{required:false,maxlength:128}" ligerui="{suffix:'个'}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 爆破片数量：</td>
        <td class="l-t-td-right"><input name="p_bppsl" validate="{required:false,maxlength:128}" ligerui="{suffix:'个'}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 紧急切断阀数量：</td>
        <td class="l-t-td-right"><input name="p_jjqdhsl" validate="{required:false,maxlength:128}" ligerui="{suffix:'个'}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 防撞设施数量：</td>
        <td class="l-t-td-right"><input name="p_fzsssl" validate="{required:false,maxlength:128}" ligerui="{suffix:'处'}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 液面计数量：</td>
        <td class="l-t-td-right"><input name="p_yyjsl" validate="{required:false,maxlength:128}" ligerui="{suffix:'个'}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 气瓶组编号：</td>
        <td class="l-t-td-right"><input name="cp_by1" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
    
     
       <tr> 
        <td class="l-t-td-left"> 车牌号码(挂牌号码、&nbsp;&nbsp;&nbsp;<br>罐式集装箱编号)：</td>
        <td class="l-t-td-right"><input name="p20003001" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
        <td class="l-t-td-left"> 车盘（车架）号码：</td>
        <td class="l-t-td-right"><input name="p20003002" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 设计单位代码：</td>
        <td class="l-t-td-right"><input name="design_units_code" validate="{required:false,maxlength:128}"  ltype='text' /> 
        </td>
     	
      </table>
   






</body>
</html>
