package com.fwxm.recipients.dao;

import com.fwxm.recipients.bean.Tjy2ChLq;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.humanresources.bean.BgLeave;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class Tjy2ChLqDao extends EntityDaoImpl<Tjy2ChLq> {

    public String getAuditor(Tjy2ChLq tjy2ChLq, String sign) throws Exception {
        String telphone = null;
        String sql = "";
        if (sign.equals("0")) {
            //未到党政领导环节审核时获取信息的sql
            sql = "select r.emp_phone " +
                    "from sys_user s, tjy2_rl_employee_base r " +
                    "where s.ID in (select distinct (t.handler_id) " +
                    "from TJY2_CH_RECIPIENTS b, v_pub_worktask t " +
                    "where b.id = t.SERVICE_ID " +
                    // "and t.WORK_TYPE like '%TJY2_RL_%' "+
                    "and t.STATUS = '0' " +
                    "and b.id = '" + tjy2ChLq.getId() + "') " +
                    "and r.id = s.employee_id and s.status='1'";
            if (tjy2ChLq.getCreateOrgId().equals("100025") || tjy2ChLq.getCreateOrgId().equals("100030")) {
                sql = "select r.emp_phone " +
                        "from sys_user s, tjy2_rl_employee_base r " +
                        "where s.ID in (select distinct (t.handler_id) " +
                        "from TJY2_CH_RECIPIENTS b, v_pub_worktask_add_position t " +
                        "where b.id = t.SERVICE_ID " +
                        //"and t.WORK_TYPE like '%TJY2_RL_%' "+
                        "and t.STATUS = '0' " +
                        "and b.id = '" + tjy2ChLq.getId() + "') " +
                        "and r.id = s.employee_id and s.status='1'";
            }
        } else if (sign.equals("1")) {
            //到党政领导环节审核时获取信息的sql
            sql = "select r.emp_phone " +
                    "from sys_user s, tjy2_rl_employee_base r " +
                    "where s.ID in (select distinct (t.handler_id) " +
                    "from TJY2_RL_LEAVE b, v_pub_worktask t " +
                    "where b.id = t.SERVICE_ID " +
                    //"and t.WORK_TYPE like '%TJY2_RL_%' "+
                    "and t.STATUS = '0' " +
                    "and b.id = '" + tjy2ChLq.getId() + "') " +
                    "and r.id = s.employee_id " +
                    "and s.status='1' " +
                    "and r.id in (select t.fk_rl_emplpyee_id " +
                    "from TJY2_RL_ORGID_LEADERID t " +
                    "where instr(t.fk_sys_org_id, '" + tjy2ChLq.getCreateOrgId() + "') > 0)";
        }
        List<?> list = this.createSQLQuery(sql).list();
        telphone = list.get(0).toString();
        return telphone;
    }
    

	public Map<String, Object>  getBeanByYear( String year){
		String sql="SELECT * FROM ( SELECT to_number(substr(LQ_BH,11)) as LQ_BH FROM TJY2_CH_RECIPIENTS WHERE LQ_BH LIKE  '"+year+"%'    ) order by LQ_BH desc";
		List<Map<String, Object>> list=this.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		if(list!=null && list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}
}
