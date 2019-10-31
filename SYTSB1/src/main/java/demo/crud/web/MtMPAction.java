package demo.crud.web;

import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.utils.StringUtil;

import demo.crud.bean.MtMC;
import demo.crud.bean.MtMP;
import demo.crud.service.MtMCManager;
import demo.crud.service.MtMPManager;

@Controller
@RequestMapping("demo/mtmp")
public class MtMPAction extends SpringSupportAction<MtMP, MtMPManager> {

	@Autowired
	private MtMPManager mtMPManager;

	@Autowired
	private MtMCManager mtMCManager;

	@Override
	public HashMap<String, Object> save(HttpServletRequest request, MtMP entity)
			throws Exception {
		String mtmcids = entity.getMtmcIds();
		mtMPManager.save(entity);
		if (!StringUtil.isEmpty(mtmcids)) {
			for (String mtmcid : mtmcids.split(",")) {
				MtMC mtMC = mtMCManager.get(mtmcid);
				if (mtMC != null) {
					if (mtMC.getMtMPs() == null) {
						mtMC.setMtMPs(new HashSet<MtMP>());
					}
					mtMC.getMtMPs().add(entity);
					mtMCManager.save(mtMC);
				}
			}
		}
		return JsonWrapper.successWrapper(entity);
	}

	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		mtMPManager.delete(ids);
		return JsonWrapper.successWrapper();
	}
	
	
}