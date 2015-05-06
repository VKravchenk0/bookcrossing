package ua.vkravchenko.bc.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="book_id")
	private int id;
	
	private String name;
	
	@ManyToMany(mappedBy = "books")
	private List<Bunch> bunches;

	public List<Bunch> getBunches() {
		return bunches;
	}

	public void setBunches(List<Bunch> bunches) {
		this.bunches = bunches;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void removeBunch(Bunch bunch) {
		bunches.remove(bunch);
	}
	
}
