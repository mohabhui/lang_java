package wlu.mmb.beans;

public class UserBean {
private int id;
private String name,email,password, department;

public UserBean() {}

public UserBean(int id, String name, String email, String password, String department) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.department = department;
}

//constructor without id
public UserBean(String name, String email, String password, String department) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
	this.department = department;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}

}
