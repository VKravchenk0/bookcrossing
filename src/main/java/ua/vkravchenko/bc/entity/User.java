package ua.vkravchenko.bc.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "app_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(unique = true, nullable = false)
	private Integer id;
	
	@Column(unique = true)
	private String name;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Bunch> bunches;
	
	@ManyToMany
	@JoinTable
	private List<Role> roles;
	
	private String password;
	
	private boolean enabled;
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Bunch> getBunches() {
		return bunches;
	}

	public void setBunches(List<Bunch> bunches) {
		this.bunches = bunches;
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
	
	public void addBunch(Bunch bunch) {
		bunches.add(bunch);
		bunch.setUser(this);
	}

	

}
