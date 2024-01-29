package com.controller.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.controller.board.util.AuthUtils;
import com.controller.board.util.BoardController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.PostService;

public class BoardLikeController implements BoardController {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        if (!AuthUtils.isUserLoggedIn(paramMap)) {
            model.put("success", false);
            model.put("message", "로그인이 필요합니다.");
            return objectMapper.writeValueAsString(model);
        }

        String postId = paramMap.get("postId");
        System.out.println(postId);
        Long postIdLong = Long.parseLong(postId);
        String userId = paramMap.get("userId");

        // HashMap에 담아서 전달
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("postId", postId);

        PostService service = new PostService();
        int n = service.postLike(map);

        Long likeCount = service.selectPagePost(postIdLong).getLikeNum();;
        
        if (n == 1) {
            model.put("success", true);
            model.put("message", "좋아요 처리 성공");
            model.put("likeCount", likeCount);
        } else {
            model.put("success", false);
            model.put("message", "이미 좋아요 누른 게시글입니다.");
        }

        // JSON 문자열로 변환
        return objectMapper.writeValueAsString(model);
    }
}
