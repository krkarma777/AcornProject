package com.controller.board;

import java.util.Map;

import com.controller.board.util.AuthUtils;
import com.controller.board.util.BoardController;
import com.controller.board.util.ErrorMessage;
import com.dto.board.PostDTO;
import com.service.PostService;

// 게시글 삭제를 담당하는 컨트롤러
public class BoardDeleteController implements BoardController {

    // 에러 메시지에 사용될 상수
    String INVALID_REQUEST = ErrorMessage.INVALID_REQUEST.getMessage();
    String POST_NOT_FOUND = ErrorMessage.POST_NOT_FOUND.getMessage();
    String ERROR = ErrorMessage.ERROR.getMessage();
    String ERROR_PAGE = ErrorMessage.ERROR_PAGE.getMessage();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 로그인 여부 확인
        if (!AuthUtils.isUserLoggedIn(paramMap)) {
            return "redirect:/Acorn/LoginServlet";
        }

        // 파라미터에서 게시글 ID와 보드 이름 추출
        String postIdParam = paramMap.get("postId");
        String boardName = paramMap.get("bn");
        String redirectURL = "/Acorn/board/" + boardName;
        
        PostService service = new PostService();

        try {
            // postId를 파싱하고 게시글 조회
            Long postId = parsePostId(postIdParam);
            PostDTO post = getPostOrHandleError(service, postId, model, redirectURL);

            // 게시글이 존재하지 않는 경우 리다이렉트
            if (post == null) return "redirect:" + redirectURL;

            // 사용자 권한 확인
            if (!AuthUtils.isUserAuthorized(post, paramMap)) return "redirect:" + redirectURL;

            // 게시글 삭제 실행
            service.delete(postId);
        } catch (NumberFormatException e) {
            // postId 파싱 중 에러 발생 시 모델에 에러 메시지 추가
            model.put(ERROR, INVALID_REQUEST);
        }

        // 삭제 후 리다이렉트
        return "redirect:" + redirectURL;
    }

    // postId 파싱 메서드
    private Long parsePostId(String postIdParam) throws NumberFormatException {
        return Long.parseLong(postIdParam);
    }

    // postId로 게시글 조회 및 에러 처리 메서드
    private PostDTO getPostOrHandleError(PostService service, Long postId, Map<String, Object> model, String redirectURL) {
        PostDTO post = service.select(postId);
        if (post == null) {
            // 게시글이 존재하지 않을 경우 모델에 에러 메시지 추가
            model.put(ERROR, POST_NOT_FOUND);
        }
        return post;
    }
}
