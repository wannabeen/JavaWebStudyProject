package member;

import java.time.LocalDateTime;

public class MemberDto {
	private int idx;
	private String id;
	private String pw;
	private String nickname;
	private String tel;
	private LocalDateTime joinDateTime;

	public MemberDto(int idx, String id, String pw, String nickname, String tel, LocalDateTime joinDateTime) {
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.nickname = nickname;
		this.tel = tel;
		this.joinDateTime = joinDateTime;
	}

	public MemberDto(String id, String pw, String nickname, String tel) {
		this.id = id;
		this.pw = pw;
		this.nickname = nickname;
		this.tel = tel;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public LocalDateTime getJoinDateTime() {
		return joinDateTime;
	}

	public void setJoinDateTime(LocalDateTime joinDateTime) {
		this.joinDateTime = joinDateTime;
	}
}