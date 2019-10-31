package com.lsts.humanresources.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.lsts.humanresources.bean.PositionTitle;
import com.lsts.humanresources.dao.PositionTitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("positionTitleManager")
public class PositionTitleManager extends EntityManageImpl<PositionTitle, PositionTitleDao> {
    @Autowired
    PositionTitleDao positionTitleDao;

    public List<PositionTitle> getByEmpId(String id) {
        List<PositionTitle> list = new ArrayList<PositionTitle>();
        String hql = "from PositionTitle where FK_EMPLOYEE_ID=? and termination='0'";
        list = positionTitleDao.createQuery(hql, id).list();
        return list;
    }

    /**
     * 取出合同文档
     *
     * @param id
     * @return
     * @throws KhntException
     */
    public PositionTitle getFile(String id) throws KhntException {
        //取得文档
        byte[] order = positionTitleDao.getFile(id);
        //取得表记录
        PositionTitle PositionTitle = positionTitleDao.get(id);
        PositionTitle.setDocumentDoc(order);
        return PositionTitle;
    }

    /**
     * 保存合同文档
     *
     * @param inputStream
     * @param order
     */
    public String saveO(InputStream inputStream, PositionTitle order) {
        return positionTitleDao.saveO(inputStream, order);
    }
}
