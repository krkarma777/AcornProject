package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload")
@MultipartConfig // 이 서블릿이 파일 업로드를 처리할 것임을 나타냄
public class FileUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// 요청이 멀티파트인지 확인
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 멀티파트 형식이 아니면 중지
			PrintWriter writer = response.getWriter();
			writer.println("오류: 폼은 enctype이 multipart/form-data이어야 합니다.");
			writer.flush();
			return;
		}
		
        // Properties 객체 생성 및 로드
        Properties props = new Properties();
        String uploadPath = null;
        int MEMORY_THRESHOLD = 0;
        int MAX_FILE_SIZE = 0;
        int MAX_REQUEST_SIZE = 0;
        String UPLOAD_DIRECTORY = null;

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("com/config/config.properties")) {
            if (inputStream != null) {
                props.load(inputStream);
                uploadPath = props.getProperty("uploadPath");
                UPLOAD_DIRECTORY = props.getProperty("UPLOAD_DIRECTORY");
                MEMORY_THRESHOLD = Integer.parseInt(props.getProperty("MEMORY_THRESHOLD_MB")) * 1024 * 1024;
                MAX_FILE_SIZE = Integer.parseInt(props.getProperty("MAX_FILE_SIZE_MB")) * 1024 * 1024;
                MAX_REQUEST_SIZE = Integer.parseInt(props.getProperty("MAX_REQUEST_SIZE_MB")) * 1024 * 1024;
            } else {
                throw new IOException("config.properties 파일을 찾을 수 없습니다.");
            }
        }

		// 구성 설정
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 메모리 임계값 설정 - 기본 파일 크기를 넘어서면 임시 디렉토리에 저장
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 임시 저장 디렉토리 설정
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 파일 최대 크기 설정
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 요청 최대 크기 설정
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 사용자 이름을 기반으로 업로드 경로 설정
		String userName = System.getProperty("user.name");


        // 업로드 파일이 저장될 서버 경로 설정
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
		try {
			// 요청 파싱
			List<FileItem> formItems = upload.parseRequest(request);

			String uploadedFileUrl = ""; // 업로드된 파일의 URL
			if (formItems != null && formItems.size() > 0) {
				// 폼 필드 처리
				for (FileItem item : formItems) {
					// 파일 필드인 경우 처리
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();

						// 파일 확장자를 유지하기 위해 파일 이름에서 확장자 추출
						String ext = fileName.substring(fileName.lastIndexOf('.'));

						// UUID를 사용하여 고유한 파일 이름 생성
						String newFileName = UUID.randomUUID().toString() + ext;

						// 파일 경로 조합
						String filePath = uploadPath + File.separator + newFileName;
						File storeFile = new File(filePath);
						

		                // 디스크에 파일 저장
		                item.write(storeFile);

		                // 업로드된 파일의 URL 설정
		                uploadedFileUrl = request.getContextPath() + "/" + UPLOAD_DIRECTORY + "/" + newFileName;
					}
				}
			}

            // 클라이언트에게 응답 전송
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print("{\"fileUrl\":\"" + uploadedFileUrl + "\"}");
			out.flush();
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + ex.getMessage());
		}
	}
}
