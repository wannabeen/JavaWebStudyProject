package member;

public class MemberService {
	public String join(MemberDto member) {
		MemberDao dao = new MemberDao();

		// 아이디 중복 여부 확인
		if (dao.selectOneById(member.getId()) != null) {
			return "duplicate_id";
		}

		// 연락처 중복 여부 확인
		if (dao.selectOneByTel(member.getTel()) != null) {
			return "duplicate_tel";
		}

		// 회원가입
		if (dao.insertByMemberDto(member) == 1) {
			return "OK";
		} else {
			return "unknown error";
		}
	}

	public boolean correctIdPw(MemberDto member) {
		MemberDao dao = new MemberDao();

		MemberDto selectedMember = dao.selectOneById(member.getId());
		if (selectedMember == null) {
			return false;
		}

		// 사용자가 입력한 비밀번호
		String userInputPw = member.getPw();

		// 사용자가 입력한 아이디로 조회한 회원 정보의 빌밀번호
		String storedUserPw = selectedMember.getPw();

		if (!userInputPw.equals(storedUserPw)) {
			return false;
		} else {
			return true;
		}
	}
}