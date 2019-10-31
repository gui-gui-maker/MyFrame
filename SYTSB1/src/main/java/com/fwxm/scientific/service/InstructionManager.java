package com.fwxm.scientific.service;


import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.fwxm.scientific.bean.Instruction;
import com.fwxm.scientific.bean.InstructionInfo;
import com.fwxm.scientific.dao.InstructionDao;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName InstructionManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-01-10 13:42:45
 */
@Service("instructionManager")
public class InstructionManager extends EntityManageImpl<Instruction, InstructionDao> {
	@Autowired
	InstructionDao instructionDao;

	/**
	 * 
	 * @param id
	 * @param hall_id
	 * @return
	 * @throws Exception
	 */
	public Instruction getDetail(String id) throws Exception {

		Instruction hall = instructionDao.get(id);

		String ids = "";
		// 判断是否多个id
		StringBuffer json = new StringBuffer();
		if (id.indexOf(",") != -1) {
			String temp[] = id.split(",");

			for (int i = 0; i < temp.length; i++) {

				json.append("'").append(temp[i]).append("'");
				if (i != temp.length - 1) {
					json.append(",");
				}

			}
			ids = json.toString();
		} else {
			ids = json.append("'").append(id).append("'").toString();
		}

		String sql = "select t.* from TJY2_INSTRUCTION_INFO t where  t.TJY2_INSTRUCTION_ID ='"+id+"'";

		SQLQuery query = instructionDao.getquery(sql);

		query.addEntity(InstructionInfo.class);

		List list = query.list();

		hall.setInstructionInfo(list);

		return hall;

	}
	public String getEmpSignId(String userId) {

		String signId = "";
		String sql = "select t.id from PUB_ATTACHMENT  t where t.business_id = ?";
		List<Object> list = instructionDao.createSQLQuery(sql, userId).list();
		if (list.size() > 0) {
			if (list.get(0) != null) {
				signId = list.get(0).toString();
			}
		}

		return signId;
	}
}
