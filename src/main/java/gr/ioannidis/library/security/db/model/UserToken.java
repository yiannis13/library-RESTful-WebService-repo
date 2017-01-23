package gr.ioannidis.library.security.db.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String tokenValue;
	private Date validUntil;
	
	@OneToOne
	private User user;
	
	
	public UserToken() {
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTokenValue() {
		return tokenValue;
	}


	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}


	public Date getValidUntil() {
		return validUntil;
	}


	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}
