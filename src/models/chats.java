package models;

import java.util.Date;

public class chats {

	private int id;
	private int user_id1;
	private int user_id2;
	private int type;
	private String name;
	private String content;
	private Date time;
	
	
	public chats(int id, int u1, int u2, int type, String name, String content, Date time) {
		this.id = id;
		this.user_id1 = u1;
		this.user_id2 = u2;
		this.type = type;
		this.name = name;
		this.content = content;
		this.time = time;
	}
	
	public chats(int u1, int u2, int type, String name, String content, Date time) {
		this.user_id1 = u1;
		this.user_id2 = u2;
		this.type = type;
		this.name = name;
		this.content = content;
		this.time = time;
	}
	
	public int getId() {
		return this.id;
	}
	public int getU1() {
		return this.user_id1;
	}
	public int getU2() {
		return this.user_id2;
	}
	public int getType() {
		return this.type;
	}
	public String getName() {
		return this.name;
	}
	public String getContent() {
		return this.content;
	}
	public Date getTime() {
		return this.time;
	}
}
