package com.edu.yati.auth.security.custom;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection="eduaccuser")
public class AccountUserDetail implements UserDetails {
	
	private static final long serialVersionUID = -6550024132440358956L;
	
	@Id
    @JsonProperty
    private String id;

	@Transient
	Logger logger = LoggerFactory.getLogger(AccountUserDetail.class);
	
	@NotNull
	private String standard;
	
	@NotNull
    private String username;
	
	@NotNull
    private String password;
	
	@NotNull
    private String firstName;
	
	@NotNull
    private String lastName;
	
	@NotNull
    private String email;
	
	@NotNull
	private String mobileNumber;
	
    private List<Role> roles;
    private boolean accountNonExpired, accountNonLocked, credentialsNonExpired, enabled;
    
    public AccountUserDetail(){}

    public AccountUserDetail(String username, String password) {
    	this.username = username;
    	this.password = password;
       this.accountNonExpired = true;
       this.accountNonLocked = true;
       this.credentialsNonExpired = true;
       this.enabled = true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }
    public void grantAuthority(Role authority) {
        if ( roles == null ) roles = new ArrayList<>();
        roles.add(authority);
    }
    @Override
    public List<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        //roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
        for(Role role: roles) {
        	authorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return authorities;
    }

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AccountUserDetail [logger=" + logger + ", username=" + username
				+ ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", roles=" + roles + ", accountNonExpired=" + accountNonExpired + ", accountNonLocked="
				+ accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled + "]";
	}
}
