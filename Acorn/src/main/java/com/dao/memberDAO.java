package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dto.memberDTO;

public class memberDAO {

	public List<memberDTO> selectAll(SqlSession session) {
		List<memberDTO> list = session.selectList("selectAll");
		System.out.println("dao "+ list);
		return list;
	}
	
	public memberDTO findUserId(SqlSession session, Map<String, Object> dateForFindUserId) {
		memberDTO dto = session.selectOne("findUserId", dateForFindUserId);
		return dto;
	}

	public memberDTO findUserPW(SqlSession session, Map<String, Object> dataForFindUserPW) {
		memberDTO dto = session.selectOne("findUserPW", dataForFindUserPW);
		return dto;
	}

	public static boolean isUserIdDuplicate(SqlSession session, String userId) {
		try {
			int num = session.selectOne("isUserIdDuplicate", userId);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isUserNicknameDuplicate(SqlSession session, String nickname) {
		try {
			int num = session.selectOne("isUserNicknameDuplicate", nickname);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int insertNewMember(SqlSession session, memberDTO dto) {
		int num = session.insert("insertNewMember", dto);
		return num;
	}

	public static boolean isUserPNDuplicate(SqlSession session, Map<String, String> dataForFindExistPN) {
		try {
			int num = session.selectOne("isUserPNDuplicate", dataForFindExistPN);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isUserEmailDuplicate(SqlSession session, Map<String, String> dataForFindExistEmail) {
		try {
			int num = session.selectOne("isUserEmailDuplicate", dataForFindExistEmail);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean loginPossible(SqlSession session, Map<String, String> dataForLogin) {
		try {
			int num = session.selectOne("loginPossible", dataForLogin);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public memberDTO findMemberInfo(SqlSession session, Map<String, String> idPW) {
		memberDTO dto = session.selectOne("findMemberInfo", idPW);
		return dto;
	}

	public static boolean findPWbyNickname(SqlSession session, HashMap<String, String> nicknameMap) {
		try {
			int num = session.selectOne("findPWbyNickname", nicknameMap);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean findPWbyPhoneNum(SqlSession session, Map<String, String> phoneNumMap) {
		try {
			int num = session.selectOne("findPWbyPhoneNum", phoneNumMap);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean findPWbyEmail(SqlSession session, HashMap<String, String> emailMap) {
		try {
			int num = session.selectOne("findPWbyEmail", emailMap);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public memberDTO selectMemberData(SqlSession session, String userId) {
		memberDTO dto = session.selectOne("selectMemberData", userId);
		return dto;
	}

	public memberDTO selectOne(SqlSession session, String userId) {
		memberDTO dto = session.selectOne("selectOne", userId);
		return dto;
	}
	
	
	
	
	
}