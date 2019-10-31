package com.fwxm.ret.service;

import com.fwxm.material.bean.Goods;
import com.fwxm.material.bean.GoodsAndOrder;
import com.fwxm.material.dao.GoodsAndOrderDao;
import com.fwxm.material.dao.GoodsDao;
import com.fwxm.outstorage.bean.Tjy2ChCk;
import com.fwxm.outstorage.bean.Tjy2ChCkStatus;
import com.fwxm.outstorage.dao.Tjy2ChCkDao;
import com.fwxm.recipients.bean.Tjy2ChLq;
import com.fwxm.recipients.bean.Tjy2ChLqStatus;
import com.fwxm.recipients.dao.Tjy2ChLqDao;
import com.fwxm.ret.bean.Tjy2ChReturn;
import com.fwxm.ret.bean.Tjy2ChReturnStatus;
import com.fwxm.ret.dao.Tjy2ChReturnDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Tjy2ChReturnManager extends EntityManageImpl<Tjy2ChReturn, Tjy2ChReturnDao> {

    @Autowired
    Tjy2ChReturnDao tjy2ChReturnDao;

    @Autowired
    Tjy2ChLqDao tjy2ChLqDao;

    @Autowired
    Tjy2ChCkDao tjy2ChCkDao;

    @Autowired
    GoodsAndOrderDao goodsAndOrderDao;

    @Autowired
    GoodsDao goodsDao;

    public HashMap<String, Object> saveChTrByLq(HttpServletRequest request, Tjy2ChReturn entity) throws Exception {
        String lqId = request.getParameter("lqId");
        Tjy2ChLq tjy2ChLq = tjy2ChLqDao.get(lqId);
        if (!Tjy2ChLqStatus.YCK.equals(tjy2ChLq.getStatus())) {
            throw new KhntException("该领取单不是已出库的领取单，无法进行退还");
        }
        List<GoodsAndOrder> goodsAndOrderDaoList = entity.getGoods();
        if (StringUtil.isNotEmpty(entity.getId())) {
            //先删除原来的，再新增。
            Tjy2ChReturn source = tjy2ChReturnDao.get(entity.getId());
            if (Tjy2ChReturnStatus.YRK.equals(source.getStatus())) {
                throw new KhntException("不能修改已退还入库的退还单");
            }
            for (GoodsAndOrder gad : source.getGoods()) {
                goodsAndOrderDao.remove(gad);
            }
            tjy2ChReturnDao.remove(source);
            entity.setId(null);
            entity.setTrBh(source.getTrBh());
        } else {
            entity.setTrBh(getTRBH());
        }
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        entity.setGoods(null);
        entity.setCreateId(user.getId());
        entity.setCreateName(user.getName());
        entity.setCreateOrgId(user.getDepartment().getId());
        entity.setCreateOrgName(user.getDepartment().getOrgName());
        entity.setCreateUnitId(user.getUnit().getId());
        entity.setCreateUnitName(user.getUnit().getOrgName());
        Date now = new Date();
        entity.setCreateTime(now);
        entity.setStatus(Tjy2ChReturnStatus.WTJ); // 未提交
        tjy2ChReturnDao.save(entity);
        for (GoodsAndOrder gad : goodsAndOrderDaoList) {
            gad.setType("TR");
            gad.setCreate_time(now);
            gad.setStatus("1");
            gad.setFk_order_id(entity.getId());
            goodsAndOrderDao.save(gad);
        }
        return JsonWrapper.successWrapper(entity);
    }

    public HashMap<String, Object> saveChTrByCk(HttpServletRequest request, Tjy2ChReturn entity) throws Exception {
        Tjy2ChCk tjy2ChCk = tjy2ChCkDao.get(entity.getCkId());
        if (!Tjy2ChCkStatus.YCK.equals(tjy2ChCk.getStatus())) {
            throw new KhntException("该出库单无法进行退还");
        }
        List<GoodsAndOrder> goodsAndOrderList = entity.getGoods();
        canReturn(goodsAndOrderList, tjy2ChCk);
        if (StringUtil.isNotEmpty(entity.getId())) {
            //先删除原来的，再新增。
            Tjy2ChReturn source = tjy2ChReturnDao.get(entity.getId());
            if (Tjy2ChReturnStatus.YRK.equals(source.getStatus())) {
                throw new KhntException("不能修改已退还入库的退还单");
            }
            for (GoodsAndOrder gad : source.getGoods()) {
                goodsAndOrderDao.remove(gad);
            }
            tjy2ChReturnDao.remove(source);
            entity.setId(null);
            entity.setTrBh(source.getTrBh());
        } else {
            entity.setTrBh(getTRBH());
        }
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        entity.setGoods(null);
        entity.setCreateId(user.getId());
        entity.setCreateName(user.getName());
        entity.setCreateOrgId(user.getDepartment().getId());
        entity.setCreateOrgName(user.getDepartment().getOrgName());
        entity.setCreateUnitId(user.getUnit().getId());
        entity.setCreateUnitName(user.getUnit().getOrgName());
        Date now = new Date();
        entity.setCreateTime(now);
        entity.setStatus(Tjy2ChReturnStatus.WTJ); // 未提交
        tjy2ChReturnDao.save(entity);
        for (GoodsAndOrder gad : goodsAndOrderList) {
            gad.setType("TR");
            gad.setCreate_time(now);
            gad.setStatus("1");
            gad.setFk_order_id(entity.getId());
            goodsAndOrderDao.save(gad);
        }
        return JsonWrapper.successWrapper(entity);
    }


    public HashMap<String, Object> takeEffect(HttpServletRequest request, Tjy2ChReturn entity) throws Exception {
        Tjy2ChCk tjy2ChCk = tjy2ChCkDao.get(entity.getCkId());
        canReturn(entity.getGoods(), tjy2ChCk);
        List<GoodsAndOrder> trGoods = entity.getGoods();
        List<GoodsAndOrder> ckGoods = tjy2ChCk.getGoods();
        Map<String, GoodsAndOrder> ckGoodsMap = new HashMap<String, GoodsAndOrder>();
        for (GoodsAndOrder ckGood : ckGoods) {
            ckGoodsMap.put(ckGood.getGoods().getId(), ckGood);
        }
        for (GoodsAndOrder trGood : trGoods) {
            Goods goods = trGood.getGoods();
            GoodsAndOrder goodsAndOrder = ckGoodsMap.get(goods.getId());
            Float sjlqsl = goodsAndOrder.getSjlqsl() - trGood.getSl();
            Float kcsl = goods.getSl() + trGood.getSl();
            if (sjlqsl < 0) {
                throw new KhntException("【" + goods.getWpmc() + "】退还数量不能超过实际领取数量【" + goodsAndOrder.getSjlqsl() + "】");
            } else if (kcsl > goods.getCssl()) {
                throw new KhntException("【" + goods.getWpmc() + "】退还数量不能超过入库数量【" + goods.getCssl() + "】");
            } else {
                //1.将出库单上的实际出库数量扣除
                goodsAndOrder.setSjlqsl(goodsAndOrder.getSjlqsl() - trGood.getSl());
                //2.对库存数量进行增加
                goods.setSl(kcsl);
                goodsDao.save(goods);
                goodsAndOrderDao.save(goodsAndOrder);
            }
        }
        entity.setTrTime(new Date());
        entity.setStatus(Tjy2ChReturnStatus.YRK);
        return JsonWrapper.successWrapper(entity);
    }

    private void canReturn(List<GoodsAndOrder> trGoods, Tjy2ChCk tjy2ChCk) throws KhntException {
        List<GoodsAndOrder> ckGoods = tjy2ChCk.getGoods();
        Map<String, Float> ckGoodsMap = new HashMap<String, Float>();
        for (GoodsAndOrder ckGood : ckGoods) {
            ckGoodsMap.put(ckGood.getGoods().getId(), ckGood.getSl());
        }
        StringBuffer errorMsg = new StringBuffer();
        for (GoodsAndOrder trGood : trGoods) {
            Goods goods = trGood.getGoods();
            Float sl = ckGoodsMap.get(goods.getId());
            if (trGood.getSl() > sl) {
                errorMsg.append("名称【" + goods.getWpmc() + "】、规格及型号【" + goods.getGgjxh() + "】的存货退还数量超过领取数量【" + sl + "】无法进行退还<br>");
            }
        }
        if (errorMsg.length() > 0) {
            throw new KhntException(errorMsg.toString());
        }
    }

    public void deleteChTr(String[] ids) {
        for (String id : ids) {
            Tjy2ChReturn entity = this.get(id);
            if (!Tjy2ChReturnStatus.WTJ.equals(entity.getStatus())) {
                throw new KhntException("只能删除未提交的退还单");
            }
            entity.setStatus(Tjy2ChReturnStatus.REMOVE);
            for (GoodsAndOrder gad : entity.getGoods()) {
                gad.setStatus(Tjy2ChReturnStatus.REMOVE);
                goodsAndOrderDao.save(gad);
            }
            tjy2ChReturnDao.save(entity);
        }
    }

    private String getTRBH() {
    	 String newbh="";
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	String bh= "TR"+sdf.format(new Date());
        Map<String,Object> mapBean=tjy2ChReturnDao.getBeanByYear(bh);
        if(mapBean!=null){
        	Object object = mapBean.get("TR_BH");
	        int no= Integer.parseInt(String.valueOf(object ))+1;
		    newbh=bh+String.format("%03d", no);
	     }else{
	    	 newbh=bh+"001";
	     }
        return newbh;
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
//        Date date = calendar.getTime();
//        String hql = "from Tjy2ChReturn where createTime >= ? ";
//        List<Tjy2ChReturn> list = tjy2ChReturnDao.createQuery(hql, date).list();
//        return "TR" + sf.format(date) + "-" + String.format("%03d", list.size() + 1);
    }
}
