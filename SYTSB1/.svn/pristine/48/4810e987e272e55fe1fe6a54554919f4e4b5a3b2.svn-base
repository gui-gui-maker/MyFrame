package com.fwxm.material.service;

import com.fwxm.material.bean.Goods;
import com.fwxm.material.dao.GoodsDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodsManager extends EntityManageImpl<Goods, GoodsDao> {
    @Autowired
    GoodsDao goodsDao;

    public HashMap<String, Object> getGoodsBySearch(HttpServletRequest request) throws Exception {
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        String wpmc = request.getParameter("wpmc");
        String gysmc = request.getParameter("gysmc");
        String warehousing_num = request.getParameter("warehousing_num");
        String blqbm = request.getParameter("blqbmId");
        String chlx = request.getParameter("chlx");
        String hql = "from Goods where 1=1 and sl>0 ";

        List<Object[]> list = new ArrayList<Object[]>();
/*        if (StringUtil.isNotEmpty(user.getDepartment().getId())) {
            hql += " and sybm_id = :sybm_id ";
            list.add(new Object[]{"sybm_id", user.getDepartment().getId()});
        }*/
        if (StringUtil.isNotEmpty(wpmc)) {
            hql += " and wpmc like :wpmc ";
            list.add(new Object[]{"wpmc", "%" + wpmc + "%"});
        }
        if (StringUtil.isNotEmpty(gysmc)) {
            hql += " and gysmc like :gysmc ";
            list.add(new Object[]{"gysmc", "%" + gysmc + "%"});
        }
        if (StringUtil.isNotEmpty(warehousing_num)) {
            hql += " and warehousing_num like :warehousing_num ";
            list.add(new Object[]{"warehousing_num", "%" + warehousing_num + "%"});
        }
        if(StringUtil.isNotEmpty(blqbm)){
            hql += " and create_org_id=:blqbm";
            list.add(new Object[]{"blqbm", blqbm});
        }
        if(StringUtils.isNotEmpty(chlx)){
            hql += " and goodstype.lx_name like :chlx";
            list.add(new Object[]{"chlx", "%" + chlx + "%"});
        }
        hql += " and state = '2' order by rk_time asc";
        Query query = goodsDao.createQuery(hql);
        for (Object[] arr : list) {
            query.setParameter(arr[0].toString(), arr[1]);
        }
        return JsonWrapper.successWrapper(query.list());
    }
}
