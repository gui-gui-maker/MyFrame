package com.lsts.finance.service;

import com.fwxm.outstorage.bean.Tjy2ChCk;
import com.fwxm.outstorage.bean.Tjy2ChCkStatus;
import com.fwxm.outstorage.service.Tjy2ChCkManager;
import com.fwxm.supplies.bean.Warehousing;
import com.fwxm.supplies.service.WarehousingManager;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.core.service.ProcessManager;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.Fybxd;
import com.lsts.finance.dao.FybxdDao;
import com.lsts.finance.web.MoneyConvertAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


/**
 * 实体Manager，继承自泛型类EntityManageImpl，同时声明泛型的运行时bean和dao为Demo,DemoDao。<br/>
 * 通过这样的继承方式，自动获得了对Demo实体的crud操作<br/>
 * 注解@Service声明了该类为一个spring对象
 */
@Service("fybxdService")
public class FybxdService extends EntityManageImpl<Fybxd, FybxdDao> {

    // 必须提供Demo实体的dao对象，使用注解@Autowired标识为自动装配
    @Autowired
    FybxdDao fybxdDao;

    @Autowired
    private ProcessManager processManager;
    @Autowired
    private ActivityManager activityManager;
    @Autowired
    FlowExtManager flowExtManager;
    @Autowired
    MoneyConvertAction moneyConvertAction;

    @Autowired
    WarehousingManager warehousingManager;

    @Autowired
    Tjy2ChCkManager tjy2ChCkManager;

    /*设置状态常量*/
    public final static String CW_FY_REGISTER = "REGISTER";
    public final static String CW_FY_SUBMIT = "SUBMIT";
    public final static String CW_FY_AUDIT = "AUDIT";
    public final static String CW_FY_PASS = "PASS";
    public final static String CW_FY_NO_PASS = "NO_PASS";
    public final static String CW_SUBMIT = "SUBMIT"; // 已提交
    public final static String CW_CSTG = "CSTG"; // 初审通过
    public final static String CW_TH = "NO_PASS"; // 初审通过


    /**
     * 启动流程
     *
     * @param map
     * @throws Exception
     */
    public void doStartProess(Map<String, Object> map) throws Exception {
        flowExtManager.startFlowProcess(map);
    }

    /**
     * 流程提交
     *
     * @param map
     * @throws Exception
     */
    public void doLctj(Map<String, Object> map) throws Exception {
        flowExtManager.submitActivity(map);
    }

    public String getmid(String id) throws ParseException {

        List getmids = fybxdDao.getmid(id);

        Object user1[] = null;
        user1 = (Object[]) getmids.get(0);

        System.out.println(user1[7]);
        String mids = user1[7].toString();
        return mids;
    }


    /**
     * 生成修改单编号
     *
     * @throws Exception
     */
    public synchronized Fybxd saveTask1(Fybxd fybxd, CurrentSessionUser user, Map<String, Object> paramMap) throws Exception {
        //新增保存时，生成新编号，修改功能不需要重新生成编号
        if (null == fybxd.getId() || "".equals(fybxd.getId())) {
            String docNum = "";
            Calendar a = Calendar.getInstance();
            int nowYear = a.get(Calendar.YEAR);
            List<Fybxd> fybxdlist = fybxdDao.getTaskAllot();
            if (fybxdlist == null || fybxdlist.size() == 0) {
                docNum = nowYear + "-" + "0001";
            } else {
                int[] docNumArray = new int[fybxdlist.size()];
                for (int i = 0; i < fybxdlist.size(); i++) {
                    //将编号去掉“-”，转换成int型存入数组
                    if (fybxdlist.get(i).getIdentifier() != null && !"".equals(fybxdlist.get(i).getIdentifier())) {
                        docNumArray[i] = Integer.parseInt(fybxdlist.get(i).getIdentifier().replaceAll("-", ""));
                    }
                }
                int max = docNumArray[0];
                //获取数组中最大的值
                for (int i : docNumArray) {
                    max = max > i ? max : i;
                }
                String docNum1 = String.valueOf(max + 1);

                if (nowYear > Integer.parseInt(docNum1.substring(0, 4))) {
                    docNum = nowYear + "-" + "0001";
                } else {
                    //编号加1后重新组
                    docNum = docNum1.substring(0, 4) + "-" + docNum1.substring(4, 8);
                }
            }
            fybxd.setIdentifier(docNum);
        }
        String userid = fybxd.getUserId();
        if (userid.equals(user.getId())) {

            String mid = getmid(userid);
            fybxd.setUserId(mid);

        }
        /**
         * 使金额初始值为0
         */
        if (fybxd.getStatus() == null || fybxd.getStatus().equals("")) {
            fybxd.setStatus(CW_FY_REGISTER);
        }
        //验证保存时的金额大写是否正确
        BigDecimal total = fybxd.getTotal();
        String wordFigure = fybxd.getWordFigure();
        String sumAmountSup = moneyConvertAction.number2CNMontrayUnit(total);
        if (!wordFigure.equals(sumAmountSup)) {
            fybxd.setWordFigure(sumAmountSup);
        }
			 /* if(fybxd.getMoney1()==null || fybxd.getMoney1().equals("")){
				  fybxd.setMoney1(new BigDecimal(0));
			  }
			  if(fybxd.getMoney2()==null || fybxd.getMoney2().equals("")){
				  fybxd.setMoney2(new BigDecimal(0));
			  }
			  if(fybxd.getMoney3()==null || fybxd.getMoney3().equals("")){
				  fybxd.setMoney3(new BigDecimal(0));
			  }
			  if(fybxd.getMoney4()==null || fybxd.getMoney4().equals("")){
				  fybxd.setMoney4(new BigDecimal(0));
			  }
			  if(fybxd.getTotal()==null || fybxd.getTotal().equals("")){
				  fybxd.setTotal(new BigDecimal(0));
			  }*/
        fybxdDao.save(fybxd);


        //修改入库表
        String fybxId = fybxd.getId();
        List<Warehousing> list = warehousingManager.getWarehousingByFybxId(fybxId);
        for (Warehousing warehousing : list) {//老的设置成null
            warehousing.setFybxd_id(null);
            warehousing.setBz_zt(null);
        }
        //每次保存重新set
        if (paramMap.get("rkdh") != null && StringUtil.isNotEmpty(paramMap.get("rkdh").toString())) {
            List<Warehousing> listWar = warehousingManager.getWarehousingByRkBh(paramMap.get("rkdh").toString().split(","));
            for (Warehousing bean : listWar) {
                bean.setFybxd_id(fybxId);
                bean.setBz_zt(null);
                warehousingManager.save(bean);
            }
        }
        tjy2ChCkManager.updateBxidsToEmpty(fybxId);
        if (paramMap.get("ckdids") != null && StringUtil.isNotEmpty(paramMap.get("ckdids").toString())) {
            List<Tjy2ChCk> tjy2ChCkList = tjy2ChCkManager.getTjy2ChCkByIds(paramMap.get("ckdids").toString());
            for (Tjy2ChCk tjy2ChCk : tjy2ChCkList) {
                tjy2ChCk.setBxId(fybxId);
                tjy2ChCk.setBxStatus(Tjy2ChCkStatus.BX_BXZ);
                tjy2ChCkManager.save(tjy2ChCk);
            }
        }
        return fybxd;
    }


    public void delete(String ids) throws Exception {
        for (String id : ids.split(",")) {
            Fybxd mtMP = fybxdDao.get(id);
            fybxdDao.getHibernateTemplate().delete(mtMP);
        }
    }


}

