package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.ParameterUtil;

@WebServlet("/member/join")
public class MemberJoin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 파라미터 검증
		String id = ParameterUtil.getString(request, "id");
		String pw = ParameterUtil.getString(request, "pw");
		String nickname = ParameterUtil.getString(request, "nickname");
		String tel = ParameterUtil.getString(request, "tel");
		System.out.println("complete");

		if (!ParameterUtil.isId(id)) {
			System.out.println("ID FAIL");
			response.setStatus(400);
			return;
		} else if (!ParameterUtil.isPw(pw)) {
			System.out.println("PW FAIL");
			response.setStatus(400);
			return;
		} else if (!ParameterUtil.isNickname(nickname)) {
			System.out.println("NICKNAME FAIL");
			response.setStatus(400);
			return;
		} else if (!ParameterUtil.isTel(tel)) {
			System.out.println("TEL FAIL");
			response.setStatus(400);
			return;
		}

		// 회원 가입
		MemberDto member = new MemberDto(id, pw, nickname, tel);

		MemberService service = new MemberService();
		String result = service.join(member);

		if (result.equals("OK")) {
			// 상태 코드를 설정하지 않으면 자동으로 200으로 설정되므로
			// 설정 생략
//			response.setStatus(200);
		} else if (result.startsWith("duplicate")) {
			response.setStatus(409);
		} else {
			response.setStatus(500);
		}
	}
}