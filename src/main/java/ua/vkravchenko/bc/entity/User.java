package ua.vkravchenko.bc.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import ua.vkravchenko.bc.annotation.UniqueEmail;

@Entity
@Table(name = "app_user")
public class User {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(unique = true, nullable = false)
	private Integer id;
	
	@Column(unique = true)
	@Email(message = "Invalid email address")
	@Size(min = 1, message = "Invalid email address")
	@UniqueEmail(message = "Username with such email already exists")
	private String email;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Bunch> bunches;
	
	@ManyToOne
	private City city;
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	@ManyToOne
	private Country country;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@ManyToMany
	@JoinTable
	private List<Role> roles;
	
	@Size(min = 5, message = "Password must be at least 5 characters")
	private String password;
	
	
	//@Column(nullable = false)
	private boolean enabled;
	
	//@Column(nullable = false)
	@Size(min = 3, message = "Name must be at least 3 characters long")
	private String firstName;
	
	//@Column(nullable = false)
	@Size(min = 3, message = "Last Name must be at least 3 characters long")
	private String lastName;
	
	private String imageRef;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getImageRef() {
		return imageRef;
	}

	public void setImageRef(String imageRef) {
		this.imageRef = imageRef;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addBunch(Bunch bunch) {
		bunches.add(bunch);
		bunch.setUser(this);
	}
	
	public void assignCountry(Country country) {
		this.setCountry(country);
		country.getUsers().add(this);
	}
	
	public void assignCity(City city) {
		this.setCity(city);
		city.getUsers().add(this);
	}

	

}
