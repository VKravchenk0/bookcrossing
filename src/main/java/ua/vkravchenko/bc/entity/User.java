package ua.vkravchenko.bc.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "app_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(unique = true)
	private String name;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Bunch> bunches;
	
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
