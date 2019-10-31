package com.scts.push.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.scts.push.bean.PushRecord;
import com.scts.push.dao.PushRecordDao;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class PushRecordManager extends EntityManageImpl<PushRecord, PushRecordDao> {
    @Autowired
    private PushRecordDao recordDao;

    public HashMap<String, Object> updateToReceived(String cid, String randomUUID) throws Exception {
        Query query = recordDao.createQuery("from PushRecord where cidUUid = :cidUuid");
        query.setParameter("cidUuid", cid + "_" + randomUUID);
        List<PushRecord> records = query.list();
        Date receiveTime = new Date();
        for (PushRecord record : records) {
            record.setReceiveTime(receiveTime);
            recordDao.save(record);
        }
        return JsonWrapper.successWrapper("");
    }
}
