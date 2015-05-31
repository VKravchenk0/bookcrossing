package ua.vkravchenko.bc.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
public class Region {
	
	@Id
	@JsonProperty("region_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="country_id", nullable = false)
	private Country country;
	
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	@OneToMany(mappedBy = "region")
	private List<City> cities;

	public void assignCountry(Country country) {
		this.country = country;
		country.getRegions().add(this);
		
	}

}
