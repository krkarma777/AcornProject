package com.controller.board.util;

import java.util.Map;

import com.dto.PostDTO;

public class AuthUtils {
	
    public static boolean isUserLoggedIn(Map<String, String> paramMap) {
        String isLogin = paramMap.get("isLogin");
        return "yes".equals(isLogin);
    }
    
    public static boolean isUserAuthorized(PostDTO post, Map<String, String> paramMap) {
        String userId = paramMap.get("userId");
        String nickname = paramMap.get("nickname");
        return post.getUserId().equals(userId) && post.getNickname().equals(nickname);
    }
}
