package com.controller.board.util;

import java.util.Map;

import com.dto.board.IPost;

public class AuthUtils {
	
    public static boolean isUserLoggedIn(Map<String, String> paramMap) {
        String isLogin = paramMap.get("isLogin");
        return "yes".equals(isLogin);
    }
    
    public static boolean isUserAuthorized(IPost post, Map<String, String> paramMap) {
        String userId = paramMap.get("userId");
        return post.getUserId().equals(userId);
    }
}
