/*    */ package com.khnt.security.support;
/*    */ 
/*    */ import com.khnt.rbac.bean.User;
/*    */ import com.khnt.security.CurrentSS3SecurityUser;
/*    */ import com.khnt.security.KHUserDetailsService;
/*    */ import java.util.Collection;
/*    */ import org.springframework.security.core.GrantedAuthority;
/*    */ 
/*    */ public class KHBpmUserDetailsService extends KHUserDetailsService
/*    */ {
/*    */   protected CurrentSS3SecurityUser createCurrentSessionUser(User user, Collection<GrantedAuthority> auths)
/*    */   {
/* 21 */     CurrentSS3SecurityUser userDetails = new CurrentBpmSessionUser(user.getAccount(), user.getPassword(), true, true, true, true, auths);
/*    */ 
/* 23 */     return userDetails;
/*    */   }
/*    */ }

/* Location:           C:\Users\pingZhou\.m2\repository\khnt\khnt-component-bpm\2.1.6.0-SNAPSHOT\khnt-component-bpm-2.1.6.0-SNAPSHOT.jar
 * Qualified Name:     com.khnt.security.support.KHBpmUserDetailsService
 * JD-Core Version:    0.6.0
 */