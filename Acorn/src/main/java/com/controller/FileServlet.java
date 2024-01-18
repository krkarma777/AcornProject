package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/uploads/*")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filename = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");

		// Properties 객체 생성 및 로드
		Properties props = new Properties();
		String uploadPath = null;

		try (InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream("com/config/config.properties")) {
			if (inputStream != null) {
				props.load(inputStream);
				uploadPath = props.getProperty("uploadPath");
			} else {
				throw new IOException("config.properties 파일을 찾을 수 없습니다.");
			}
		}
		File file = new File(uploadPath, filename);

		response.setHeader("Content-Type", getServletContext().getMimeType(filename));
		response.setHeader("Content-Length", String.valueOf(file.length()));
		response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

		Files.copy(file.toPath(), response.getOutputStream());
	}
}
