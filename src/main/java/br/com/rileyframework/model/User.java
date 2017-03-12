package br.com.rileyframework.model;

public class User {

	public String id;
	public String mail;
	public String password;
	
	public User(){
	}
	
	public User(String id, String mail, String password) {
		super();
		this.id = id;
		this.mail = mail;
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
