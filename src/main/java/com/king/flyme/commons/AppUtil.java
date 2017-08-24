package com.king.flyme.commons;

import com.king.flyme.bean.CustUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class AppUtil {

    private static final Logger log = LoggerFactory.getLogger(AppUtil.class);

    public static CustUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && (principal instanceof User)) {
            return (CustUser) principal;
        }
        return null;
    }

    public static Integer getCurrentUserId() {
        CustUser currentUser = getCurrentUser();
        if (currentUser == null) {
            return null;
        }
        return currentUser.getId();
    }
}
