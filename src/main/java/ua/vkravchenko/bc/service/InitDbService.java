package ua.vkravchenko.bc.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.vkravchenko.bc.entity.Book;
import ua.vkravchenko.bc.entity.Bunch;
import ua.vkravchenko.bc.entity.City;
import ua.vkravchenko.bc.entity.Country;
import ua.vkravchenko.bc.entity.Role;
import ua.vkravchenko.bc.entity.User;
import ua.vkravchenko.bc.repository.CityRepository;
import ua.vkravchenko.bc.repository.CountryRepository;
import ua.vkravchenko.bc.repository.RoleRepository;
import ua.vkravchenko.bc.repository.UserRepository;
import ua.vkravchenko.bc.util.Util;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private BookService bookService;

	@Autowired
	private BunchService bunchService;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CityService cityService;

	// Method will be executed after context creation
	@PostConstruct
	public void init() {
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			System.out.println("initializing");
			Role roleUser = new Role();
			roleUser.setName("ROLE_USER");
			roleRepository.save(roleUser);

			Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			
			

			User user1 = new User();
			user1.setPassword(encoder.encode("admin"));
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleAdmin);
			roles.add(roleUser);
			user1.setRoles(roles);
			user1.setEnabled(true);
			user1.setEmail("lalka@mail.ru");
			user1.setFirstName("Vyacheslav");
			user1.setLastName("Kravchenko");
			user1.setImageRef("admin.jpg");
			Calendar dob = Calendar.getInstance();
			dob.set(1994, 0, 21);
			user1.setDateOfBirth(new Date(dob.getTimeInMillis()));
			userRepository.save(user1);
			
			User user2 = new User();
			user2.setPassword(encoder.encode("user"));
			user2.setEmail("user@mail.ru");
			List<Role> roles1 = new ArrayList<Role>();
			roles1.add(roleUser);
			user2.setRoles(roles1);
			user2.setEnabled(true);
			user2.setFirstName("Barak");
			user2.setLastName("Obama");
			Calendar dob1 = Calendar.getInstance();
			dob1.set(1988, 6, 14);
			user2.setDateOfBirth(new Date(dob.getTimeInMillis()));
			userRepository.save(user2);

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

			loadCountries();
		}
		

	}
	
	
	
	private void loadCountries() {

		ObjectMapper mapper = new ObjectMapper();
		String rawRequest = "https://api.vk.com/method/database.getCountries?need_all=1&count=1000&lang=";
		String ru = "ru";
		List<Country> countries;
		try {
			String json = Util.getJson(rawRequest + ru);
			VkApiResponse<Country> response = mapper.readValue(json,
					new TypeReference<VkApiResponse<Country>>() {
					});
			countries = response.getResponse();
			for (Country country : countries) {
				country.setCities(new ArrayList<City>());
				country.setUsers(new ArrayList<User>());
				countryRepository.save(country);
			}
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	static class VkApiResponse<T> {

		List<T> response = new ArrayList<T>();

		public List<T> getResponse() {
			return response;
		}

		public void setResponse(List<T> response) {
			this.response = response;
		}
	}

}
