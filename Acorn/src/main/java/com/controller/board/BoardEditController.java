package com.controller.board;

import java.util.Map;

import com.controller.board.util.AuthUtils;
import com.controller.board.util.BoardController;
import com.controller.board.util.ErrorMessage;
import com.dto.PostDTO;
import com.service.PostService;

public class BoardEditController implements BoardController {

	String INVALID_REQUEST = ErrorMessage.INVALID_REQUEST.getMessage();
	String ERROR_PAGE = ErrorMessage.ERROR_PAGE.getMessage();
	String ERROR = ErrorMessage.ERROR.getMessage();
    
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 로그인 여부 확인
        if (!AuthUtils.isUserLoggedIn(paramMap)) {
            return "redirect:/Acorn/LoginServlet";
        }

        try {
            // postId 파싱 및 검증
            Long postId = parseAndValidatePostId(paramMap.get("postId"));
            // 게시글 정보 조회
            PostDTO post = fetchPost(postId, model);
            if (post == null) return ERROR_PAGE;

            // 사용자 권한 검증
            if (!isUserAuthorized(post, paramMap)) return ERROR_PAGE;

            // 게시글 수정 또는 수정 페이지 로드 처리
            return processEditOrRedirect(postId, paramMap, model, post);
        } catch (NumberFormatException e) {
            return ERROR_PAGE;
        }
    }

    private Long parseAndValidatePostId(String postIdParam) throws NumberFormatException {
        return Long.parseLong(postIdParam);
    }

    private PostDTO fetchPost(Long postId, Map<String, Object> model) {
        PostService service = new PostService();
        PostDTO post = service.select(postId);
        if (post == null) {
            model.put(ERROR, INVALID_REQUEST);
        }
        return post;
    }

    private boolean isUserAuthorized(PostDTO post, Map<String, String> paramMap) {
        String userId = paramMap.get("userId");
        String nickname = paramMap.get("nickname");
        return post.getUserId().equals(userId) && post.getNickname().equals(nickname);
    }

    private String processEditOrRedirect(Long postId, Map<String, String> paramMap, Map<String, Object> model, PostDTO post) {
        String postTitle = paramMap.get("postTitle");
        String postText = paramMap.get("postText");
        String postBoard = paramMap.get("bn");

        if (postTitle == null || postText == null) {
            model.put("post", post);
            return "board/edit";
        } else {
            new PostService().update(postId, postTitle, postText);
            return String.format("redirect:/Acorn/board/content?postId=%d&bn=%s", postId, postBoard);
        }
    }
}
