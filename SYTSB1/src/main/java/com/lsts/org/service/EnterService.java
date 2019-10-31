package com.lsts.org.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.impl.SysParaPropertiesImpl;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.StringUtil;
import com.lsts.org.bean.EnterInfo;
import com.lsts.org.dao.EnterDao;

/**
 * 单位信息信息管理   servier
 * 
 * @author 肖慈边 2014-1-21
 */
@Service("enterService")
public class EnterService extends EntityManageImpl<EnterInfo, EnterDao> {
	@Autowired
	private EnterDao enterDao;


	
	@Override
	public void save(EnterInfo entity) throws Exception {
		// TODO Auto-generated method stub
		
		entity.setSend_status("0");//记录数据被修改
		
		
		super.save(entity);
	}
	

					
	public void del(String ids) throws Exception {
		// TODO Auto-generated method stub
		//判断是否多个ID
		if(ids.indexOf(",")!=-1){
			
			String id[] =ids.split(",");
			
			for(int i=0;i<id.length;i++){
				
				enterDao.createSQLQuery("update  BASE_COMPANY_INFO  set com_status='05' where id =?",id[i] ).executeUpdate();
				
			}
			
		}else{
		
			enterDao.createSQLQuery("update  BASE_COMPANY_INFO  set com_status='05' where id = ?", ids).executeUpdate();
			
		}
		
	}
	
	public void saveRelation(String com_id,String com_type){
		
		//判断是否多个ID
				if(com_type.indexOf(",")!=-1){
					
					String type[] =com_type.split(",");
					
					for(int i=0;i<type.length;i++){
						
						//先查找是否之前有这天数据
						
						List list =enterDao.createSQLQuery("select * from BASE_COM_TYPE_RELATION t where t.com_id='"+com_id+"' and t.com_type='"+type[i]+"'").list();
						
						System.out.println(list.size());
						
						if(list.size()==0){
						
						enterDao.createSQLQuery("insert into  BASE_COM_TYPE_RELATION(ID,COM_ID,COM_TYPE)  values('"+StringUtil.getUUID()
+"','"+com_id+"','"+type[i]+"')").executeUpdate();
						
						}
					
					}
					
				}else{
					
					List list =enterDao.createSQLQuery("select * from BASE_COM_TYPE_RELATION t where t.com_id='"+com_id+"' and t.com_type='"+com_type+"'").list();
					
					System.out.println(list.size());
					
					if(list.size()==0){
				
					enterDao.createSQLQuery("insert into  BASE_COM_TYPE_RELATION(ID,COM_ID,COM_TYPE)  values('"+StringUtil.getUUID()
+"','"+com_id+"','"+com_type+"')").executeUpdate();
					
					}
				}
		
		
	}
	
	public List<EnterInfo> queryEnterInfos() {
		return enterDao.queryEnterInfos();
	}
	
	/**
	 * 根据组织结构代码查询单位信息
	 * @param code
	 * @return
	 */
	public EnterInfo queryEnterByCode(String code){
		return enterDao.queryEnterByCode(code);
	}
	
	/**
	 * 根据主键查询单位信息
	 * @param id -- 主键
	 * @return
	 */
	public EnterInfo queryEnterById(String id){
		return enterDao.get(id);
	}
	
	/**
	 * 根据单位名称查询单位信息
	 * @param com_name -- 单位名称
	 * @return
	 */
	public List<EnterInfo> queryInfos(String com_name) {
		return enterDao.queryInfos(com_name);
	}
	
	/**
	 * 根据组织机构代码查询信息
	 * @param code
	 * @return
	 */
	public HashMap<String, Object>  getInfoByOrgID(String orgid){
		//List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql="select t.com_code from  base_company_info t where t.com_status !='05' and t.com_code='"+orgid+"'";
		int count=enterDao.createSQLQuery(sql).executeUpdate();
		if(count>0){
			map.put("message", "此组织机构代码已存在，无需再增加！");
			return map;
		}else{
	//		SysParaPropertiesImpl d=new SysParaPropertiesImpl();
//			if(d.getProperty("orgInfo").equals("yes")){
			
			System.out.println("11111111111111111111111111111111111111111111111");
			
			try {
				if(orgid.indexOf("-")>0){
					orgid = orgid.substring(0,8)+orgid.substring(9,orgid.length());
				}
				String driverName = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@192.168.92.21:1521:orcl";
				sql="select jgdm,jgmc,jgdz,fddbr,xzqh,yzbm,dhhm,fzr,email,url,zcrq,zczj,pzjgmc,zch from xt_jgxx@zljs where jgdm='"+orgid+"'";
				Class.forName(driverName);
				Connection con = null;
				con = DriverManager.getConnection(url,"tzsbjy","tzsbjy"); 
				
				System.out.println("22222222222222222"+con);
				
				Statement stmt=con.createStatement();
				ResultSet rest=stmt.executeQuery(sql);
				
			System.out.println(sql+"33333"+rest.getRow());
				//int rowCount = 0; 
				
				if(rest.getRow()>0){
				System.out.println("44444444444");
					
					while(rest.next()){
						 //rowCount=rest.getInt("totalCount "); 
						// rest.getString("jgdm");
							map.put("com_code",  rest.getString("jgdm"));
							map.put("com_name",  rest.getString("jgmc"));
							map.put("com_address",  rest.getString("jgdz"));
							map.put("com_legal_rep",  rest.getString("fddbr"));
							map.put("com_area_code",  rest.getString("xzqh"));
							map.put("com_zip_code",  rest.getString("yzbm"));
							map.put("com_tel",  rest.getString("dhhm"));
							map.put("com_contact_man", rest.getString("fzr"));
							map.put("com_email",  rest.getString("email"));
							map.put("com_web_site",  rest.getString("url"));
							map.put("com_fund_date",  rest.getString("zcrq"));
							map.put("registered_funds",  rest.getString("zczj"));
							map.put("approve_depart",  rest.getString("pzjgmc"));
							map.put("licence_reg_no",  rest.getString("zch"));
							//rowCount++;
					}
				}else{
					map.put("message", "没有此组织机构代码相关信息存在！");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				map.put("message", "没有此组织机构代码相关信息存在！");
				e.printStackTrace();
				System.out.println("222222222222222222111111111111111111");
			}
			//sql="select '0','1','2','3','4','5','6','7','8','9','10','11','12','13' from  base_company_info t where t.com_status !='05'  and rownum=1";
			/*List list =enterDao.createSQLQuery(sql).list();
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Object[] objArr = list.toArray();
					Object[] obj = (Object[]) objArr[i];
					
					map.put("com_code", obj[0]);
					map.put("com_name", obj[1]);
					map.put("com_address", obj[2]);
					map.put("com_legal_rep", obj[3]);
					map.put("com_area_code", obj[4]);
					map.put("com_zip_code", obj[5]);
					map.put("com_tel", obj[6]);
					map.put("com_contact_man", obj[7]);
					map.put("com_email", obj[8]);
					map.put("com_web_site", obj[9]);
					map.put("com_fund_date", obj[10]);
					map.put("registered_funds", obj[11]);
					map.put("approve_depart", obj[12]);
					map.put("licence_reg_no", obj[13]);
					//mapList.add(map);
					
				}
			}*/
//			}else{
//				map.put("message", "没有此组织机构代码相关信息存在！");
//			}
		}
		return map;
	}
}
