package model;

import java.io.Serializable;

public class Mutter implements Serializable {
	private String number;//つぶやきNo
	private String userName;//ユーザ名
	private String text;//つぶやき内容
	public Mutter() {}
	public Mutter(String number,String userName,String text) {
		this.number = number;
		this.userName = userName;
		this.text = text;
	}
	public String getNumber() {return number;}
	public String getUserName() {return userName;}
	public String getText() {return text;}

}
