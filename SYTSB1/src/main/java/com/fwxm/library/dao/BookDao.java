package com.fwxm.library.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.fwxm.library.bean.Book;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName BookDao
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-10-23 10:57:26
 */
@Repository("bookDao")
public class BookDao extends EntityDaoImpl<Book> {
}
