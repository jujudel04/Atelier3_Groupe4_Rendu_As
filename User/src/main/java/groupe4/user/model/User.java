package groupe4.user.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String login;
	private String passwd;
	
	public User() {
		super();
	}

	public User(Integer id, String name, String login, String passwd) {
		this();
		this.id = id;
		this.name = name;
		this.login = login;
		this.passwd = passwd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "User [" + this.id + "]: name:" + this.name + ", login:" + this.login + ", passwd:" + this.passwd;
	}

}
