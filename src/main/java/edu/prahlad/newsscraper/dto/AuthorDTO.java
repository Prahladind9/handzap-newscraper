package edu.prahlad.newsscraper.dto;

public class AuthorDTO {
	
	private String name;
	private String userName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "AuthorDTO [name=" + name + ", userName=" + userName + "]";
	}
	
}
