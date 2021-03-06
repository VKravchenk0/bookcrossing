package ua.vkravchenko.bc.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@JsonIgnoreProperties({"region"})
public class City {

	@Id
	@JsonProperty("cid")
	private int id;

	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;
	
	@OneToMany(mappedBy = "city")
	private List<User> users;

	@JsonProperty("title")
	private String name;
	
	private String region;
	
	private String area;
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void assignCountry(Country country) {
		this.country = country;
		country.getCities().add(this);
		
	}

}
