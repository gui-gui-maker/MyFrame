package com.fwxm.material.dao;

import com.fwxm.material.bean.GoodsType;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("goodsTypeDao")
public class GoodsTypeDao extends EntityDaoImpl<GoodsType> {

    /**
     * 按部门，单位过滤，如果都为空，则查所有
     *
     * @param orgId
     * @param unitId
     * @return
     * @throws Exception
     */
    public List<GoodsType> getAllTypes(String orgId, String unitId) throws Exception {
        String hql = " from GoodsType where 1=1 ";
        Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(orgId)) {
            hql += " and createOrgId =:orgId ";
            params.put("orgId", orgId);
        }
        if (StringUtil.isNotEmpty(unitId)) {
            hql += " and createUnitId =:unitId ";
            params.put("unitId", unitId);
        }
        hql += " and state != 99";
        Query query = this.createQuery(hql);
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        return query.list();
    }
}
