package com.controller.board;

import java.util.Map;

import com.controller.board.util.BoardController;
import com.service.PostService;

public class BoardDeleteController implements BoardController {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 유저 로그인 세션 검증 로직 필요 

        // postId 파라미터 파싱
        String postIdParam = paramMap.get("postId"); 
        
        String boardName = paramMap.get("bn"); //게시판 이름 파라미터
        
        String redirectURL = "/Acorn/board/" + boardName;
        
        if (postIdParam != null && !postIdParam.isEmpty()) {
            try {
                Long postId = Long.parseLong(postIdParam);

                // postId를 사용하여 게시글 삭제 수행
                PostService service = new PostService();
                service.delete(postId);

                return "redirect:" + redirectURL; // 삭제 후 리다이렉트

            } catch (NumberFormatException e) {
                // postId가 올바른 형식이 아닌 경우에 대한 처리
                return "redirect:" + redirectURL; // 형식 오류 시 리다이렉트
            }
        } else {
            // postId가 제공되지 않은 경우에 대한 처리
            return "redirect:" + redirectURL; // postId가 없을 때 리다이렉트
        }
    }
}
