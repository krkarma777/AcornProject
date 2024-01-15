package com.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactory {
	static SqlSessionFactory sqlSessionFactory= null;
	static {//초기화
		String resource = "com/config/Configuration.xml";  //수정이 필요한 유일한 부분 
		//Configuration.xml의 경로가 바뀔시에 수정이 필요 
		InputStream inputStream= null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			System.out.println("configuration.xml 로딩 성공 ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//파일의 경로가 틀린경우 ioException발생 
			e.printStackTrace();
		}
		 sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		 //DriverManager와 비슷한 객체 
	}//end static
	
	//SqlSession 반환
	public static SqlSession getSqlSession() {//
		//    SqlSession session=    MySqlSessionFactory.getSqlSession();
		SqlSession session = sqlSessionFactory.openSession();
		//getConnection의 Connection과 비슷한 기능의 SqlSession객체 생성 리턴 
		return session;
	}
	
}