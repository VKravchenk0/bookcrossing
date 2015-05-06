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
import javax.persistence.ManyToOne;

@Entity
public class Bunch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="bunch_id")
	private int id;
	
	@ManyToMany
	@JoinTable(name="book_bunch", joinColumns=@JoinColumn(name="bunch_id", nullable=true), 
	inverseJoinColumns=@JoinColumn(name="book_id"))
	private List<Book> books;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void addBook(Book book) {
		books.add(book);
		book.getBunches().add(this);
	}
	
	public void removeBook(Book book) {
		book.removeBunch(this);
		books.remove(book);
	}
	
	

}
