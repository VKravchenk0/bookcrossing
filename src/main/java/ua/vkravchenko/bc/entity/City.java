package ua.vkravchenko.bc.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
public class City {

	@Id
	@JsonProperty("region_id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "region_id", nullable = false)
	private Region region;

	private String title;
	
	private int important;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImportant() {
		return important;
	}

	public void setImportant(int important) {
		this.important = important;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void assignRegion(Region region) {
		this.region = region;
		region.getCities().add(this);
		
	}

}
