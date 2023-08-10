package member;

public class MemberService {
	public String join(MemberDto member) {
		MemberDao dao = new MemberDao();
		
		// 아이디 중복 여부 확인
		if(dao.selectOneById(member.getId()) != null) {
			return "duplicate_id";
		}
		
		// 연락처 중복 여부 확인
		if(dao.selectOneByTel(member.getTel()) != null) {
			return "duplicate_tel";
		}
		
		// 회원가입
		if(dao.insertByMemberDto(member) == 1) {
			return "OK";
		} else {
			return "unknown error";
		}
	}
}