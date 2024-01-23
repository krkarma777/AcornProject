package com.controller.board;

import java.util.Map;

import com.controller.board.util.BoardController;
import com.dto.PostDTO;
import com.service.PostService;

public class BoardEditController implements BoardController {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String postIdParam = paramMap.get("postId");
        String postBoard = paramMap.get("bn"); //게시판 이름 파라미터
        PostService service = new PostService();
        
        // postIdParam이 제공되고, 유효한 경우
        if (postIdParam != null && !postIdParam.isEmpty()) {
            Long postId;
            try {
                postId = Long.parseLong(postIdParam);
            } catch (NumberFormatException e) {
                return "error"; // postId가 올바른 형식이 아닌 경우의 처리
            }

            // 수정 페이지 로드를 위한 로직
            if (!paramMap.containsKey("postTitle") && !paramMap.containsKey("postText")) {
                PostDTO post = service.select(postId);
                if (post != null) {
                    model.put("post", post);
                    return "board/edit"; // 수정 페이지로 이동
                } else {
                    return "error"; // 글이 없는 경우
                }
            }

            // 게시글 업데이트를 위한 로직
            String postTitle = paramMap.get("postTitle");
            String postText = paramMap.get("postText");
            if (postTitle != null && postText != null) {
                service.update(postId, postTitle, postText);

                // 수정된 글로 리다이렉트
                String redirectURL = String.format("/Acorn/board/content?postId=%d&bn=%s", postId, postBoard);
                return "redirect:" + redirectURL;
            }
        }

        // 필수 파라미터가 제공되지 않은 경우의 처리
        return "error"; // 오류 페이지로 리다이렉트
    }
}
