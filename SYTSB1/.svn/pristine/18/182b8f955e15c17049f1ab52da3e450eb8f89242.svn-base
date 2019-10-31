package com.fwxm.outstorage.inf;

import com.fwxm.outstorage.bean.Tjy2ChCkType;
import com.khnt.core.crud.bean.BaseEntity;
import com.khnt.core.crud.dao.EntityDaoInf;
import com.khnt.core.crud.manager.impl.EntityManageImpl;

import java.lang.reflect.ParameterizedType;

public abstract class OutStorageExtends<T extends BaseEntity, D extends EntityDaoInf<T>> extends EntityManageImpl<T, D> implements OutStorageInf<T> {

    Class<T> clazz;

    public String getType() {
        clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return Tjy2ChCkType.getKey(clazz);
    }
}
