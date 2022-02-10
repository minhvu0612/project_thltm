package models;

public class users {
	private int id;
	private String username;
	private String avatar;
	private String password;
	private String email;
	private String phone;
	private String gender;
	
	public users(int id, String u, String a, String pw, String e, String pn, String g) {
		this.id = id;
		this.username = u;
		this.avatar = a;
		this.password = pw;
		this.email = e;
		this.phone = pn;
		this.gender = g;
	}
	
	public users(String u, String pw, String e, String pn, String g) {
		this.username = u;
		if (g.compareTo("male") == 0) {
			this.avatar = "https://res.cloudinary.com/diw0u2vl1/image/upload/v1641639419/default_avatar/mnuue0txf2qv1zhpsbno.jpg";
		}
		else {
			this.avatar = "https://res.cloudinary.com/diw0u2vl1/image/upload/v1641639373/default_avatar/c2xkuolzbms9xhsxh7cr.png";
		}
		this.password = pw;
		this.email = e;
		this.phone = pn;
		this.gender = g;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getAvatar() {
		return this.avatar;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getGender() {
		return this.gender;
	}
}
