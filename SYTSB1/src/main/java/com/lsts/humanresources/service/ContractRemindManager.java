package com.lsts.humanresources.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.humanresources.bean.ContractRemind;
import com.lsts.humanresources.bean.RemindMessage;
import com.lsts.humanresources.dao.ContractRemindDao;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("contractRemind")
public class ContractRemindManager extends EntityManageImpl<ContractRemind, ContractRemindDao> {
    private Logger logger = LoggerFactory.getLogger(ContractRemindManager.class);
    @Autowired
    ContractRemindDao contractRemindDao;
    @Autowired
    private MessageService messageService;

    /**
     * 获取消息提醒设置
     *
     * @return
     */
    public ContractRemind getSetting() {
        return contractRemindDao.getSetting();
    }

    /**
     * 合同到期提醒定时消息发送
     *
     * @return
     * @throws Exception
     */
    public void sendContractRemind() {
        System.out.println("------------系统定时发送合同到期提醒------------");
        HttpServletRequest request = null;
        try {
            SimpleDateFormat formatData = new SimpleDateFormat("yyyy年MM月dd日");
            List<?> list = contractRemindDao.getRemindInfo();//获取到期合同信息
            List<?> phoneList = contractRemindDao.getPhoneBySetting();//获取消息接收人（除当事人之外）
            String signed_man_cache = null;
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] objs = (Object[]) list.get(i);
                    String business_id = (String) objs[0];//业务id
                    String employee_id = (String) objs[1];//当事人id
                    String signed_man = (String) objs[2];//当事人姓名
                    String emp_phone = (String) objs[4];//当事人手机号
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("signed_man", signed_man);
                    map.put("contract_stop_date", formatData.format(objs[3]));
                    messageService.sendMassageByConfig(request, business_id, emp_phone, "", "contractRemind_part",
                            employee_id, map, null,
                            null, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD);//发送消息给当事人
                    this.logger.debug("{}的合同还有60天到期，已向其手机{}发送短信进行提醒。", signed_man, emp_phone);
                    if (signed_man_cache == null) {
                        signed_man_cache = signed_man;
                    } else {
                        signed_man_cache += "," + signed_man;
                    }
                }
            } else {
                this.logger.info("没有距今日60天到期的合同信息");
            }
            if (phoneList != null && phoneList.size() > 0 && signed_man_cache != null) {
                for (int j = 0; j < phoneList.size(); j++) {
                    Object[] objs_cache = (Object[]) phoneList.get(j);
                    String receive_id = (String) objs_cache[0];//接收人id
                    String receive_phone = (String) objs_cache[1];//接收人手机号
                    HashMap<String, Object> map1 = new HashMap<String, Object>();
                    map1.put("signed_man_cache", signed_man_cache);
                    messageService.sendMassageByConfig(request, null, receive_phone, "", "contractRemind_receive",
                            receive_id, map1, null,
                            null, Constant.PEOPLE_CORPID, Constant.PEOPLE_PWD);//发送消息给接收人（除当事人）
                    this.logger.debug("{}的合同还有60天到期，已向接收人手机{}发送短信进行提醒。", signed_man_cache, receive_phone);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
