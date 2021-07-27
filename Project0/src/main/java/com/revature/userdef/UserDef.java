package com.revature.userdef;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class UserDef implements Serializable {
	// literally only created the UID to get rid of the warning, and it doesn't matter at all.
	private static final long serialVersionUID = -6426075925303078798L;
	private Integer id;
	private String username;
	private String email;
	private LocalDate birthday;
	private UserType userType;
	private String accntType;
	private Double balance;
	
	public UserDef() {
		super();
	}
	public UserDef(Integer id, String username, String email, LocalDate birthday, UserType userType, String AccntType, Double balance) {
		this();
		this.id       = id;
		this.email    = email;
		this.username = username;
		this.birthday = birthday;
		this.userType = userType;
		this.accntType= AccntType;
		this.balance  = balance; 
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
		return userType;
	}
	public void setType(UserType userType) {
		this.userType = userType;
	}
	public String getAccntType() {
		return accntType;
	}
	public void setAccntType(String AccntType) {
		this.accntType = AccntType;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	@Override
	public int hashCode() {
		return Objects.hash(accntType, balance, birthday, email, id, userType, username);
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
		return Objects.equals(accntType, other.accntType) && Objects.equals(balance, other.balance)
				&& Objects.equals(birthday, other.birthday) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && userType == other.userType
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "UserDef [id=" + id + ", username=" + username + ", email=" + email + ", birthday=" + birthday
				+ ", userType=" + userType + ", accntType=" + accntType + ", balance=" + balance + "]";
	}

	
	
}
