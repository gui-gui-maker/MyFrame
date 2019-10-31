package com.khnt.bpm.ext.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.khnt.bpm.ext.bean.Opinion;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.DateUtil;

/**
 * <p>
 * 工作流意见管理dao
 * </p>
 * 
 * @ClassName OpinionDao
 * @JDK 1.5
 * @author
 * @date 2011-5-4 下午03:25:06
 */
@Repository("opinionDao")
public class OpinionDao extends EntityDaoImpl<Opinion> {
	@SuppressWarnings("unchecked")
	public JSONArray queryServiceOpinion(String serviceId) {
		String sql = "select t.signer_name,t.sign_date,t.opinion,t.signer_id,t.workitem,b.id file_id,b.file_name,"
				+ "t.result,t.signature,t.seal,t.name,t.id,a.name aname, t.opinion_signature from flow_opinion t left join pub_attachment b "
				+ "on t.id=b.business_id left join flow_activity a on a.id=t.activity_id where t.service_id=? order by t.sign_date desc";
		List<Object[]> rlist = createSQLQuery(sql, serviceId).list();
		JSONArray jsonArray = new JSONArray();
		String preId = null;
		JSONObject jr = null;
		for (Object[] r : rlist) {
			if (!r[11].equals(preId)) {
				preId = String.valueOf(r[11]);
				jr = new JSONObject();
				jr.put("signerName", r[0]);
				Timestamp ts = (Timestamp) r[1];
				Date d = new Date(ts.getTime());
				jr.put("signDate", DateUtil.getDateTime(d));
				jr.put("opinion", r[2]);
				jr.put("signerId", r[3]);
				jr.put("workitem", r[4]);
				jr.put("result", r[7]);
				jr.put("signature", r[8]);
				jr.put("seal", r[9]);
				jr.put("name", r[10]);
				jr.put("id", r[11]);
				jr.put("activityName", r[12]);
				jr.put("opinionSignature", r[13]);
				JSONArray atts = new JSONArray();
				jr.put("atts", atts);
				jsonArray.put(jr);
			}
			JSONArray atts = jr.getJSONArray("atts");
			if (r[5] != null) {
				JSONObject att = new JSONObject();
				att.put("attId", r[5]);
				att.put("attName", r[6]);
				atts.put(att);
			}
		}
		return jsonArray;
	}
}