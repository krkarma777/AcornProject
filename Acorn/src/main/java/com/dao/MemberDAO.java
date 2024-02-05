package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dto.MemberDTO;

public class MemberDAO {

	public List<MemberDTO> selectAll(SqlSession session) {
		List<MemberDTO> list = session.selectList("selectAll");
		System.out.println("dao "+ list);
		return list;
	}
	
	public MemberDTO findUserId(SqlSession session, Map<String, String> dataForFindUserId) {
		MemberDTO dto = session.selectOne("com.config.MemberMapper.findUserId", dataForFindUserId);
		return dto;
	}

	public MemberDTO findUserPW(SqlSession session, Map<String, String> dataForFindUserPW) {
		MemberDTO dto = session.selectOne("com.config.MemberMapper.findUserPW", dataForFindUserPW);
		return dto;
	}

	public static boolean isUserIdDuplicate(SqlSession session, String userId) {
		try {
			int num = session.selectOne("com.config.MemberMapper.isUserIdDuplicate", userId);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isUserNicknameDuplicate(SqlSession session, String nickname) {
		try {
			int num = session.selectOne("com.config.MemberMapper.isUserNicknameDuplicate", nickname);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int insertNewMember(SqlSession session, MemberDTO dto) {
		int num = session.insert("com.config.MemberMapper.insertNewMember", dto);
		return num;
	}

	public static boolean isUserPNDuplicate(SqlSession session, Map<String, String> dataForFindExistPN) {
		try {
			int num = session.selectOne("com.config.MemberMapper.isUserPNDuplicate", dataForFindExistPN);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isUserEmailDuplicate(SqlSession session, Map<String, String> dataForFindExistEmail) {
		try {
			int num = session.selectOne("com.config.MemberMapper.isUserEmailDuplicate", dataForFindExistEmail);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean loginPossible(SqlSession session, Map<String, String> dataForLogin) {
		try {
			int num = session.selectOne("com.config.MemberMapper.loginPossible", dataForLogin);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public MemberDTO findMemberInfo(SqlSession session, Map<String, String> idPW) {
		MemberDTO dto = session.selectOne("com.config.MemberMapper.findMemberInfo", idPW);
		return dto;
	}

	public static boolean findPWbyNickname(SqlSession session, HashMap<String, String> nicknameMap) {
		try {
			int num = session.selectOne("com.config.MemberMapper.findPWbyNickname", nicknameMap);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean findPWbyPhoneNum(SqlSession session, Map<String, String> phoneNumMap) {
		try {
			int num = session.selectOne("com.config.MemberMapper.findPWbyPhoneNum", phoneNumMap);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean findPWbyEmail(SqlSession session, HashMap<String, String> emailMap) {
		try {
			int num = session.selectOne("com.config.MemberMapper.findPWbyEmail", emailMap);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public MemberDTO selectMemberData(SqlSession session, String userId) {
		MemberDTO dto = session.selectOne("com.config.MemberMapper.selectMemberData", userId);
		return dto;
	}

	public MemberDTO selectOne(SqlSession session, String userId) {
		MemberDTO dto = session.selectOne("com.config.MemberMapper.selectOne", userId);
		return dto;
	}
	
	
	
	
	
}