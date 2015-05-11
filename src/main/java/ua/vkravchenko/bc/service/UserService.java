package ua.vkravchenko.bc.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.vkravchenko.bc.entity.Book;
import ua.vkravchenko.bc.entity.Bunch;
import ua.vkravchenko.bc.entity.Role;
import ua.vkravchenko.bc.entity.User;
import ua.vkravchenko.bc.repository.BookRepository;
import ua.vkravchenko.bc.repository.BunchRepository;
import ua.vkravchenko.bc.repository.RoleRepository;
import ua.vkravchenko.bc.repository.UserRepository;

@Transactional
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BunchRepository bunchRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public User findOne(int id) {
		return userRepository.findOne(id);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public List<User> findOnPage(int page) {
		return userRepository.findAll(new PageRequest(page, 20, Direction.ASC, "id")).getContent();
	}
	
	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		userRepository.save(user);
	}

	@Transactional
	public User findOneWithBunches(int id) {
		User user = findOne(id);
		List<Bunch> bunches = bunchRepository.findByUser(user);
		for (Bunch bunch : bunches) {
			List<Book> books = bookRepository.findByBunches(bunches);
			bunch.setBooks(books);
		}
		user.setBunches(bunches);
		return user;
	}

	public User findOne(String email) {
		return userRepository.findByEmail(email);
		
	}
	
}
