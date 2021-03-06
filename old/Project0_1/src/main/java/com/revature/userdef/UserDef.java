package com.revature.userdef;

import java.io.Serializable;
import java.time.LocalDate;

public class UserDef implements Serializable {
	// literally only created the UID to get rid of the warning, and it doesn't matter at all.
	private static final long serialVersionUID = -6426075925303078798L;
	private Integer id;
	private String username;
	private String email;
	private LocalDate birthday;
	private UserType type;
	private Long currency;
	
	public UserDef() {
		super();
		this.type = UserType.CUSTOMER;
	}
	
	public UserDef(Integer id, String username, String email, LocalDate birthday, Long currency) {
		this();
		this.id = id;
		this.username = username;
		this.email = email;
		this.birthday = birthday;
		this.currency = currency;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public Long getCurrency() {
		return currency;
	}
	public void setCurrency(Long currency) {
		this.currency = currency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDef other = (UserDef) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type != other.type)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", birthday=" + birthday + ", type="
				+ type + ", currency=" + currency + "]";
	}
}
