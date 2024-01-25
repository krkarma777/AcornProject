package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.board.BoardContentController;
import com.controller.board.BoardDeleteController;
import com.controller.board.BoardEditController;
import com.controller.board.BoardViewController;
import com.controller.board.BoardWriteController;
import com.controller.board.util.AuthUtils;
import com.controller.board.util.BoardController;
import com.controller.board.util.BoardView;

@WebServlet("/board/*")
public class BoardFrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// URI에 따른 컨트롤러를 매핑하는 Map
	private Map<String, BoardController> controllerMap = new HashMap<>();

	public BoardFrontControllerServlet() {
		
		// 다양한 URI에 대해 컨트롤러를 매핑
		controllerMap.put("/Acorn/board/content", new BoardContentController());
		controllerMap.put("/Acorn/board/write", new BoardWriteController());
		controllerMap.put("/Acorn/board/edit", new BoardEditController());
		controllerMap.put("/Acorn/board/delete", new BoardDeleteController());

		// 게시판 추가 가능
		controllerMap.put("/Acorn/board/movie", new BoardViewController("movie"));
		controllerMap.put("/Acorn/board/music", new BoardViewController("music"));
		controllerMap.put("/Acorn/board/book", new BoardViewController("book"));
		
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 현재 요청의 URI를 가져옴
		String requestURI = request.getRequestURI();

		// URI에 해당하는 컨트롤러를 가져옴
		BoardController controller = controllerMap.get(requestURI);
		if (controller == null) {
			// URI에 매핑된 컨트롤러가 없으면 404 에러 반환
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 요청 파라미터를 paramMap으로 변환
		Map<String, String> paramMap = createParamMap(request);
		
		// 유저 로그인 검증
		AuthUtils.addUserLoginInfo(paramMap, request);

		// 뷰에서 사용할 모델 객체 생성
		Map<String, Object> model = new HashMap<>(); // 추가

		// 컨트롤러 실행하고 뷰 이름 반환
		String viewName = controller.process(paramMap, model);

		// 컨트롤러에서 반환한 문자열이 "redirect:"로 시작하면 리다이렉션 수행
		if (viewName.startsWith("redirect:")) {
			String redirectURL = viewName.substring("redirect:".length());
			response.sendRedirect(redirectURL);
		} else {
			// 뷰 이름을 해석해서 렌더링하고 응답
			BoardView view = viewResolver(viewName);
			view.render(model, request, response);
		}
	}

	// 요청 파라미터를 paramMap으로 변환하는 메서드
	private Map<String, String> createParamMap(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String, String> paramMap = new HashMap<>();
		request.setCharacterEncoding("utf-8");
		request.getParameterNames().asIterator()
				.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		
		return paramMap;
	}

	// 뷰 이름을 해석해서 View 객체로 변환하는 메서드
	private BoardView viewResolver(String viewName) {
		return new BoardView("/WEB-INF/" + viewName + ".jsp");
	}
}
