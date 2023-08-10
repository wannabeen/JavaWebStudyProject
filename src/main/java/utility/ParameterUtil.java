package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class ParameterUtil {
	/**
	 * name 파라미터 값을 꺼내는 메서드
	 * 파라미터가 없거나 파라미터 값을 trim() 한 후 비어있다면 null을 반환
	 * 
	 * @param request 파라미터를 담고 있는 객체
	 * @param name 값을 꺼낼 파라미터의 이름
	 * @return String 또는 null
	 */
	public static String getString(HttpServletRequest request, String name) {
		String str = null;
		
		str = request.getParameter(name);
		
		if(str != null) {
			str = str.trim(); // 문자열 공백 없애기
			
			if(str.length() == 0) { // " " != null 이기 때문에 trim() 메서드를 통해 공백을 없애고 검사
				str = null;
			}
		}
		
		return str;
	}
	
	public static boolean isId(String id) {
		String pattern = "[a-zA-Z0-9]{3,20}";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(id);
		
		return m.matches();
	}
	
	public static boolean isPw(String pw) {
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(pw);
		
		return m.matches();
	}
	
	public static boolean isNickname(String nickname) {
		String pattern = "[가-힣a-zA-Z0-9]{2,10}";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(nickname);
		
		return m.matches();
	}
	
	public static boolean isTel(String tel) {
		String pattern = "010-([0-9]{4})-([0-9]{4})";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(tel);
		
		return m.matches();
	}
}