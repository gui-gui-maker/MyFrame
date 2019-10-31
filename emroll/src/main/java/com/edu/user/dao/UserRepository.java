package com.edu.user.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edu.business.bean.Enroll;
import com.edu.user.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaSpecificationExecutor<User>,JpaRepository<User,String> {
    User findByUserNameOrEmail(String username,String email);

    User findByUserName(String userName);

    Long countByUserName(String userName);

    List<User> findByEmailLike(String email);

    User findByUserNameIgnoreCase(String userName);

    List<User> findByUserNameOrderByEmailDesc(String email);

    @Transactional(timeout = 10)
    @Modifying
    @Query("update User set userName = ?1 where id = ?2")
    int modifyById(String  userName, Long id);

    @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    void deleteById(String id);

    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    Page<User> findByNickName(String nickName, Pageable pageable);

    Slice<User> findByNickNameAndEmail(String nickName, String email, Pageable pageable);

}