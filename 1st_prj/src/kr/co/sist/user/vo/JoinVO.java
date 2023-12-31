package kr.co.sist.user.vo;

public class JoinVO {
	private String id;
	private String pass;
	private String name;
	private String tel;
	private String email;

	public JoinVO(String id, String pass, String name, String tel, String email) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.tel = tel;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "JoinVO [id=" + id + ", pass=" + pass + ", name=" + name + ", tel=" + tel + ", email=" + email + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
