/*    */ package com.khnt.security.support;
/*    */ 
/*    */ import com.khnt.bpm.communal.BpmOrgImpl;
/*    */ import com.khnt.bpm.communal.BpmUser;
/*    */ import com.khnt.bpm.communal.BpmUserImpl;
/*    */ import com.khnt.rbac.bean.User;
/*    */ import com.khnt.security.CurrentSS3SecurityUser;
/*    */ import java.util.Collection;
/*    */ import org.springframework.security.core.GrantedAuthority;
/*    */ 
/*    */ public class CurrentBpmSessionUser extends CurrentSS3SecurityUser
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CurrentBpmSessionUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
/*    */   {
/* 22 */     super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
/*    */   }
/*    */ 
/*    */   public BpmUser getBpmUser()
/*    */   {
/* 29 */     BpmUser bpmUser = new BpmUserImpl(getSysUser().getId(), getName(), new BpmOrgImpl(getUnit()), 
/* 29 */       new BpmOrgImpl(getDepartment()), getRoles());
/*    */ 
/* 31 */     return bpmUser;
/*    */   }
/*    */ }

/* Location:           C:\Users\pingZhou\.m2\repository\khnt\khnt-component-bpm\2.1.6.0-SNAPSHOT\khnt-component-bpm-2.1.6.0-SNAPSHOT.jar
 * Qualified Name:     com.khnt.security.support.CurrentBpmSessionUser
 * JD-Core Version:    0.6.0
 */