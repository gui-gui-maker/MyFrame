<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
</head>

<body>
  
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       
       	<input name="id" type="hidden" />
		  <tr>
			  		<td class="l-t-td-left">车牌号：</td>
							<td class="l-t-td-right"><input name="ladle_car_number"
								validate="{required:false,maxlength:100}" ltype='text' id="ladle_car_number" />
					</td>
						<td class="l-t-td-left">车辆型号：</td>
							<td class="l-t-td-right"><input name="ladle_car_structure"
								validate="{required:false,maxlength:100}" ltype='text' id="ladle_car_structure" />
					</td>
			  
			  </tr>		
			  <tr>
			  		<td class="l-t-td-left">发动机号：</td>
							<td class="l-t-td-right"><input name="ladle_car_domain_num"
								validate="{required:false,maxlength:100}" ltype='text' id="ladle_car_domain_num" />
					</td>
						<td class="l-t-td-left">车架号：</td>
							<td class="l-t-td-right"><input name="p20003002"
								validate="{required:false,maxlength:100}" ltype='text' id="p20003002" />
					</td>
			  
			  </tr>	
				
			<tr>
					<td class="l-t-td-left">设计单位：</td>
					<td class="l-t-td-right" >
							<input name="fk_design_units_id" id="fk_design_units_id" type="hidden" />
							<input type="text" id="cbz" name="cbz"   ltype="text"  validate="{required:false}" onclick="selectorg('1')"
											ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('3')}}]}"/>
							
					</td>			
					<td class="l-t-td-left">设计日期：</td>
							<td class="l-t-td-right"><input name="design_date"
								validate="{required:false,maxlength:100}" ltype='text' id="design_date" />
					</td>			
			  </tr>		
			
			   <tr>
			   <td class="l-t-td-left">设计使用年限：</td>
							<td class="l-t-td-right"><input name="cp_by7"
								validate="{required:false,maxlength:100}" ltype='text' id="cp_by7" />
					</td>
			  		
						<td class="l-t-td-left">产品标准：</td>
							<td class="l-t-td-right"><input name="cp_by3"
								validate="{required:false,maxlength:100}" ltype='text' id="cp_by3" />
					</td>
			  
			  </tr>	
			  
			    <tr>
			    <td class="l-t-td-left">罐体型号：</td>
							<td class="l-t-td-right"><input name="p_gtmp"
								validate="{required:false,maxlength:100}" ltype='text' id="p_gtmp" />
					</td>
			  		<td class="l-t-td-left">罐车图号：</td>
							<td class="l-t-td-right"><input name="cp_by6"
								validate="{required:false,maxlength:100}" ltype='text' id="cp_by6" />
					</td>
						
			  
			  </tr>
			   
			   <tr>
			  		<td class="l-t-td-left">监检证书编号：</td>
							<td class="l-t-td-right"><input name="cp_by4"
								validate="{required:false,maxlength:100}" ltype='text' id="cp_by4" />
					</td>
						<td class="l-t-td-left">主体结构形式：</td>
							<td class="l-t-td-right"><input name="p20001002"
								validate="{required:false,maxlength:100}" ltype='text' id="p20001002" />
					</td>
			  
			  </tr>	
			     <tr>
			  		<td class="l-t-td-left">支座型式：</td>
							<td class="l-t-td-right"><input name="p20001043"
								validate="{required:false,maxlength:100}" ltype='text' id="p20001043" />
					</td>
						<td class="l-t-td-left">保温隔热方式：</td>
							<td class="l-t-td-right"><input name="p_bwfs"
								validate="{required:false,maxlength:100}" ltype='text' id="p_bwfs" />
					</td>
			  
			  </tr>	
			  
			  
			  <tr> 
		        <td class="l-t-td-left"> 容积：</td>
		        <td class="l-t-td-right"><input name="container_capacity" validate="{required:false,maxlength:128}"  ltype='text' id="container_capacity" /> 
		        </td>
		        <td class="l-t-td-left"> 介质：</td>
		        <td class="l-t-td-right">
		        
		        <u:combo name="p_czjz" code="BASE_PARA_CZJZ" />
		       
		        </td>
       		</tr>
       		<tr> 
		        <td class="l-t-td-left"> 有效容积：</td>
		        <td class="l-t-td-right"><input name="cp_by5" validate="{required:false,maxlength:128}"  ltype='text' id="cp_by5" /> 
		        </td>
		        <td class="l-t-td-left"> 内径：</td>
		        <td class="l-t-td-right"><input name="container_minor_diameter" validate="{required:false,maxlength:128}"  ltype='text'  id="container_minor_diameter"/> 
		        </td>
       		</tr>
       		
       		<tr> 
		        <td class="l-t-td-left"> 罐体外形尺寸：</td>
		        <td class="l-t-td-right"><input name="p_gtwj" validate="{required:false,maxlength:128}"  ltype='text' id="p_gtwj" /> 
		        </td>
		        <td class="l-t-td-left"> 最大充装量：</td>
		        <td class="l-t-td-right"><input name="p_mzzl" validate="{required:false,maxlength:128}"  ltype='text'  id="p_mzzl"/> 
		        </td>
       		</tr>
       		
       			<tr> 
		        <td class="l-t-td-left"> 使用压力：</td>
		        <td class="l-t-td-right"><input name="p_sjyl" validate="{required:false,maxlength:128}"  ltype='text' id="p_sjyl" /> 
		        </td>
		        <td class="l-t-td-left"> 使用温度：</td>
		        <td class="l-t-td-right"><input name="p_sjwd" validate="{required:false,maxlength:128}"  ltype='text'  id="p_sjwd"/> 
		        </td>
       		</tr>
       		
       		<tr> 
		        <td class="l-t-td-left"> 设计压力：</td>
		        <td class="l-t-td-right"><input name="shell_design_press" validate="{required:false,maxlength:128}"  ltype='text' id="shell_design_press" /> 
		        </td>
		        <td class="l-t-td-left"> 设计温度：</td>
		        <td class="l-t-td-right"><input name="shell_design_temper" validate="{required:false,maxlength:128}"  ltype='text'  id="shell_design_temper"/> 
		        </td>
       		</tr>
       		
       		<tr> 
		        <td class="l-t-td-left"> 筒体材料：</td>
		        <td class="l-t-td-right"><input name="shell_material" validate="{required:false,maxlength:128}"  ltype='text' id="shell_material" /> 
		        </td>
		        <td class="l-t-td-left"> 封头材料：</td>
		        <td class="l-t-td-right"><input name="head_material" validate="{required:false,maxlength:128}"  ltype='text'  id="head_material"/> 
		        </td>
       		</tr>
       		<tr> 
		        <td class="l-t-td-left"> 筒体厚度：</td>
		        <td class="l-t-td-right"><input name="shell_thk" validate="{required:false,maxlength:128}"  ltype='text' id="shell_thk" /> 
		        </td>
		        <td class="l-t-td-left"> 封头厚度：</td>
		        <td class="l-t-td-right"><input name="head_thk" validate="{required:false,maxlength:128}"  ltype='text'  id="head_thk"/> 
		        </td>
       		</tr>
	
     	
      </table>
   






</body>
</html>
