package com.scts.push.service;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.scts.push.bean.PushRecord;
import com.scts.push.constants.PushContants;
import com.scts.push.dao.PushRecordDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class PushManager {
    @Autowired
    private PushRecordDao pushRecordDao;


    /**
     * 按appId，appKey，masterSecret,url 推送给指定的（targets）客户端
     *
     * @param appId
     * @param appKey
     * @param masterSecret
     * @param url
     * @param targetCids   指定的cid集合
     * @param obj
     * @param title
     * @param content
     * @param type         业务类型
     * @return
     * @throws IOException
     */
    public IPushResult pusMsgToTargets(String appId, String appKey, String masterSecret, String url, JSONObject obj, String title, String content, String type, List<String> targetCids) throws Exception {
        return _pushMsgToTargets(appId, appKey, masterSecret, url, targetCids, obj, title, content, type);
    }

    /**
     * 按默认配置 推送给指定的（targets）客户端
     *
     * @param targetCids 指定的cid集合
     * @param obj
     * @param title
     * @param content
     * @param type       业务类型
     * @return
     * @throws IOException
     */
    public IPushResult pusMsgToTargets(JSONObject obj, String title, String content, String type, List<String> targetCids) throws Exception {
        return _pushMsgToTargets(PushContants.APP_ID, PushContants.APP_KEY, PushContants.MASTER_SECRET, PushContants.URL, targetCids, obj, title, content, type);
    }

    private IPushResult _pushMsgToTargets(String appId, String appKey, String masterSecret, String url, List<String> targetCids, JSONObject obj, String title, String content, String type) throws Exception{
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        List<Target> targetList = new ArrayList<Target>();
        for (String cid : targetCids) {
            targetList.add(getTarget(cid, appId));
        }
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        try{
            push.connect();
        }catch (Exception e){
            throw new KhntException("获取推送连接失败，请稍后重试");
        }
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        ListMessage message = new ListMessage();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        obj.put("_RANDOM_UUID", uuid);
        try {
            message.setData(notificationTemplate(appId, appKey, obj, title, content));
        }catch (Exception e){
            e.printStackTrace();
            throw new KhntException("构建消息模板失败，请与管理员联系");
        }
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);
        String contentId="";
        try{
            contentId = push.getContentId(message);
        }catch (Exception e) {
            throw new KhntException("获取推送消息ID失败，请稍后重试或与管理员联系");
        }
        IPushResult ret = null;
        try {
            ret = push.pushMessageToList(contentId, targetList);
        }catch (Exception e){
            throw new KhntException("消息推送失败，请稍后重试或与管理员联系");
        }
        Date sendDate = new Date();
        for (String cid : targetCids) {
            PushRecord record = getPushRecord(appId, appKey, masterSecret, content, type, user, contentId, ret, sendDate, cid, PushContants.SEND_TO_TARGETS);
            record.setCidUUid(record.getCid() + "_" + uuid);
            pushRecordDao.save(record);
        }
        return ret;
    }

    private PushRecord getPushRecord(String appId, String appKey, String masterSecret, String content, String type, CurrentSessionUser user, String contentId, IPushResult ret, Date sendDate, String cid, String sendType) {
        PushRecord record = new PushRecord();
        record.setCid(cid);
        record.setAppId(appId);
        record.setAppKey(appKey);
        record.setMasterSecret(masterSecret);
        if (user != null) {
            record.setCreateId(user.getId());
            record.setCreateName(user.getName());
            record.setCreateOrgId(user.getDepartment().getId());
            record.setCreateOrgName(user.getDepartment().getOrgName());
            record.setCreateUnitId(user.getUnit().getId());
            record.setCreateUnitName(user.getUnit().getOrgName());
        }
        record.setSendType(sendType);
        record.setType(type);
        record.setContent(content);
        record.setCreateTime(sendDate);
        record.setContentId(contentId);
        if(ret.getResponse()!=null&&ret.getResponse().get("result")!=null){
            record.setResult(ret.getResponse().get("result").toString());
        }else{
            record.setResult("failed");
        }
        record.setResultContent(ret.getResponse().toString());
        return record;
    }


    private TransmissionTemplate notificationTemplate(String appId, String appkey, JSONObject obj, String title, String content) {
        TransmissionTemplate template = new TransmissionTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appkey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent(obj.toString().replaceAll("\"", "\'"));
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

        APNPayload payload = new APNPayload();
        APNPayload.DictionaryAlertMsg news = new APNPayload.DictionaryAlertMsg();
        news.setTitle(title);
        news.setBody(content);
        payload.setAlertMsg(news);
        payload.setBadge(0);
        payload.setContentAvailable(-1);
        payload.setSound("default");
        payload.addCustomMsg("payload", obj.toString().replaceAll("\"", "\'"));
        template.setAPNInfo(payload);
        return template;
    }

    private Target getTarget(String cid, String appId) {
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(cid);
        return target;
    }

    /**
     * 按appId，appKey，masterSecret,url 推送给所有的客户端
     *
     * @param appId
     * @param appKey
     * @param masterSecret
     * @param url
     * @param obj
     * @param title
     * @param content
     * @return
     * @throws IOException
     */
    public IPushResult pusMsgToAll(String appId, String appKey, String masterSecret, String url, JSONObject obj, String title, String content, String type) throws Exception {
        return _pusMsgToAll(appId, appKey, masterSecret, url, obj, title, content, type);
    }

    /**
     * 按默认配置 推送给所有的客户端
     *
     * @param obj
     * @param title
     * @param content
     * @return
     * @throws IOException
     */
    public IPushResult pusMsgToAll(JSONObject obj, String title, String content, String type) throws Exception {
        return _pusMsgToAll(PushContants.APP_ID, PushContants.APP_KEY, PushContants.MASTER_SECRET, PushContants.URL, obj, title, content, type);
    }

    private IPushResult _pusMsgToAll(String appId, String appKey, String masterSecret, String url, JSONObject obj, String title, String content, String type) throws Exception {
        CurrentSessionUser user = SecurityUtil.getSecurityUser();
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        try{
            push.connect();
        }catch (Exception e){
            throw new KhntException("获取推送连接失败，请稍后重试");
        }
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        try {
            message.setData(notificationTemplate(appId, appKey, obj, title, content));
        }catch (Exception e){
            throw new KhntException("构建消息模板失败，请与管理员联系");
        }
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);
        //给所有cid推送
        IPushResult ret = null;
        try {
            ret = push.pushMessageToApp(message);
        }catch (Exception e){
            throw new KhntException("消息推送失败，请稍后重试或与管理员联系");
        }
        PushRecord record = getPushRecord(appId, appKey, masterSecret, content, type, user, null, ret, new Date(), null, PushContants.SEND_TO_ALL);
        pushRecordDao.save(record);
        return ret;
    }

}
