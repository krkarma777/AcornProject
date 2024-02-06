package com.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.controller.board.util.BoardController;
import com.dto.board.PageDTO;
import com.dto.board.PostPageDTO;
import com.service.PostService;

// 게시판 뷰 컨트롤러 구현
public class BoardViewController implements BoardController {
    
    private String postBoard; // 게시판 이름

    // 생성자: 게시판 이름을 초기화
    public BoardViewController(String postBoard) {
        this.postBoard = postBoard;
    }

    // 요청을 처리하는 메소드
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // 현재 페이지 번호 설정
        String curPageStr = paramMap.get("curPage");
        int curPage = 1; // 기본값
        if (curPageStr != null && !curPageStr.isEmpty()) {
            curPage = Integer.parseInt(curPageStr);
        }

        String postCategoryId = paramMap.get("pc");
        
        // 페이지당 게시글 수 설정
        int perPage = 20;
        int offset = (curPage - 1) * perPage;

        // 서비스 객체 생성
        PostService service = new PostService();
        
        // 요청에 따른 매개변수 맵 설정
        HashMap<String, Object> map = new HashMap<>();
        map.put("postBoard", postBoard);
        map.put("offset", offset);
        map.put("perPage", perPage);
        map.put("curPage", curPage);
        map.put("postCategoryId", postCategoryId);

        // 검색 조건 추가
        searchBoard(paramMap, map);
        
        // 정렬 추가
        sortIndex(paramMap, map);
        
        // 페이지 정보 가져오기
        PageDTO<PostPageDTO> pageDTO = service.getPostsByPage(map);
        
		List<PostPageDTO> hotList = service.selectAll(new HashMap<String, String>() {{
		    put("board", postBoard);
		    put("postCount", "5");
		}});
		
		List<PostPageDTO> popularListAll = service.popularPostTwoDays(new HashMap<String, String>() {{
		    put("postCount", "10");
		}});
		
		final String category = postBoard.contains("movie") ? "movie" : 
            postBoard.contains("tv") ? "tv" : 
            postBoard.contains("book") ? "book" : "";

		
		List<PostPageDTO> popularListCategory = service.popularPostTwoDays(new HashMap<String, String>() {{
			put("board", category);
			put("board2", category+"Info");
			put("board3", category+"Meet");
			put("postCount", "10");
		}});

        // 모델에 페이지 정보와 게시판 이름 추가
        model.put("pDTO", pageDTO);
        model.put("postBoard", postBoard);
        model.put("hotList", hotList);
        model.put("popularListCategory", popularListCategory);
        model.put("popularListAll", popularListAll);
        
        // 뷰 페이지 이름 반환
        return "board/boardView";
    }

    // 검색 기능을 처리하는 메소드
    private void searchBoard(Map<String, String> paramMap, HashMap<String, Object> map) {
        // 검색 위치와 검색어 가져오기
        String searchPosition = paramMap.get("selectSearchPositionText");
        String searchText = paramMap.get("inputSearchFreeText");

        // 검색 위치와 검색어가 있을 경우에만 검색 조건 추가
        if (searchPosition != null && searchText != null) {
            switch (searchPosition) {
                case "titleText": {
                    map.put("postTitle", searchText.trim());
                    map.put("postText", searchText.trim());
                    break;
                }
                case "postTitle": {
                    map.put("postTitle", searchText.trim());
                    break;
                }
                case "postText": {
                    map.put("postText", searchText.trim());
                    break;
                }
                case "userId": {
                    map.put("userId", searchText.trim());
                    break;
                }
            }
        }
    }
    private void sortIndex(Map<String, String> paramMap, HashMap<String, Object> map) {
    	String orderType = paramMap.get("sortIndex");
    	if (orderType != null) {
    		switch (orderType) {
    		case "likeNum": {
    			map.put("sortIndex", "likeNum");
    			break;
    		}
    		case "viewNum": {
    			map.put("sortIndex", "viewNum");
    			break;
    		}
    		}
    	}
    }
}
