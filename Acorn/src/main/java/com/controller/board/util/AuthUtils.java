package com.controller.board.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
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
    
	public static void addUserLoginInfo(Map<String, String> paramMap , HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginUser");
		if (dto == null) {
			paramMap.put("isLogin", "no");
		} else {
			paramMap.put("isLogin", "yes");
			paramMap.put("nickname", dto.getNickname());
			paramMap.put("userId", dto.getUserId());
		}
	}
}
