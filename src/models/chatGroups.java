package models;

import java.util.Date;

public class chatGroups {

	private int id;
	private int user_id1;
	private int type;
	private String name;
	private String content;
	private Date time;
	
	
	public chatGroups(int id, int u1, int type, String name, String content, Date time) {
		this.id = id;
		this.user_id1 = u1;
		this.type = type;
		this.name = name;
		this.content = content;
		this.time = time;
	}
	
	public chatGroups(int u1, int type, String name, String content, Date time) {
		this.user_id1 = u1;
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