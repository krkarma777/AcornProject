package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig // 이 서블릿이 파일 업로드를 처리할 것임을 나타냄
public class FileUploadController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 파일 업로드를 처리하는 부분

        // 클라이언트로부터 전송된 파일 파트를 획득
        Part filePart = request.getPart("file");

        // 고유한 파일명을 생성하기 위해 UUID를 사용
        String fileName = UUID.randomUUID().toString() + "_" + getFileName(filePart);

        // 파일을 저장할 디렉토리 경로를 지정
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

        // 파일을 저장할 디렉토리가 없으면 생성
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // 파일의 최종 경로
        String filePath = uploadPath + File.separator + fileName;

        // 파일을 지정된 경로에 저장
        filePart.write(filePath);

        // 클라이언트에게 파일 경로를 응답으로 보내주는 부분

        // 응답의 컨텐츠 타입을 텍스트로 지정
        response.setContentType("text/plain");

        // 응답을 위한 PrintWriter 객체 생성
        PrintWriter out = response.getWriter();

        // 파일 경로를 클라이언트에게 전송
        out.write(filePath);
        out.flush();
    }

    // Part로부터 파일명을 추출하는 메서드
    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                // "filename" 헤더의 값을 추출하고 따옴표를 제거하여 반환
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
