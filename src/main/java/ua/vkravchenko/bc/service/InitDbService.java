package ua.vkravchenko.bc.service;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.vkravchenko.bc.entity.Book;
import ua.vkravchenko.bc.entity.Bunch;
import ua.vkravchenko.bc.entity.User;

@Transactional
@Service
public class InitDbService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BunchService bunchService;
	
	// Method will be executed after context creation
	@PostConstruct
	public void init() {
		
		User user1 = new User();
		user1.setName("Slava");
		userService.save(user1);
		
		User user2 = new User();
		user2.setName("Petya");
		userService.save(user2);
		
		Book book = new Book();
		book.setName("Animal farm");
		book.setBunches(new ArrayList<Bunch>());
		bookService.save(book);
		
		Book book1 = new Book();
		book1.setName("Brave new world");
		book1.setBunches(new ArrayList<Bunch>());
		bookService.save(book1);
		
		Book book2 = new Book();
		book2.setName("Adventures of Tom Saweyer");
		book2.setBunches(new ArrayList<Bunch>());
		bookService.save(book2);
		
		
				
		User user3 = userService.findOneWithBunches(1);
		User user4 = userService.findOneWithBunches(2);
		
		Bunch bunch = new Bunch();
		bunch.setBooks(new ArrayList<Book>());
		bunch.addBook(book);
		bunch.addBook(book1);
		user3.addBunch(bunch);
		bunchService.save(bunch);
		
		Bunch bunch1 = new Bunch();
		bunch1.setBooks(new ArrayList<Book>());
		bunch1.addBook(book2);
		user4.addBunch(bunch1);
		bunchService.save(bunch1);
		
		
	
		
		
		/*for (int i = 0; i <= 20; i++) {
			User user = new User();
			user.setName(String.valueOf(Character.valueOf((char) ('a' + i))));
			userRepository.save(user);
		}*/
	}
	

}
