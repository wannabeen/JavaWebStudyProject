package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.ParameterUtil;

@WebServlet("/member/login")
public class MemberLogin extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/member/login.html");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 검증
		String id = ParameterUtil.getString(request, "id");
		String pw = ParameterUtil.getString(request, "pw");
		
		if(!ParameterUtil.isId(id)) {
			response.setStatus(400);
			return;
		} else if(!ParameterUtil.isPw(pw)){
			return;
		}
		
		MemberDto member = new MemberDto(id, pw, null, null);
		
		MemberService service = new MemberService();
		boolean result = service.correctIdPw(member);
		
		if(!result) {
			response.setStatus(400);
		}
	}
}
