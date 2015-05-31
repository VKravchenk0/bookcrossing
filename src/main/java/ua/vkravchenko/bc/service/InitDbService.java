package ua.vkravchenko.bc.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.vkravchenko.bc.entity.Book;
import ua.vkravchenko.bc.entity.Bunch;
import ua.vkravchenko.bc.entity.City;
import ua.vkravchenko.bc.entity.Country;
import ua.vkravchenko.bc.entity.Region;
import ua.vkravchenko.bc.entity.Role;
import ua.vkravchenko.bc.entity.User;
import ua.vkravchenko.bc.repository.CityRepository;
import ua.vkravchenko.bc.repository.CountryRepository;
import ua.vkravchenko.bc.repository.RegionRepository;
import ua.vkravchenko.bc.repository.RoleRepository;
import ua.vkravchenko.bc.repository.UserRepository;

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
	private RegionRepository regionRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BunchService bunchService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	// Method will be executed after context creation
	@PostConstruct
	public void init() {
		
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
		user1.setCountry("Ukraine");
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
		
		
		loadLocations();
		
		
	}
	
	private void loadLocations() {
		
	}
	
	private void loadLocationsOld() {
		Country country = new Country();
		country.setId(1);
		country.setName("Ukraine");
		country.setRegions(new ArrayList<Region>());
		countryRepository.save(country);
		
		Region region1 = new Region();
		region1.setId(1);
		region1.setTitle("Kyiv region");
		region1.assignCountry(country);
		region1.setCities(new ArrayList<City>());
		regionRepository.save(region1);
		
		Region region2 = new Region();
		region2.setId(2);
		region2.setTitle("Lviv region");
		region2.assignCountry(country);
		region2.setCities(new ArrayList<City>());
		regionRepository.save(region2);
		
		City city1 = new City();
		city1.setId(1);
		city1.setTitle("Kyiv");
		city1.setImportant(1);
		city1.assignRegion(region1);
		cityRepository.save(city1);
		
		City city2 = new City();
		city2.setId(2);
		city2.setTitle("Lviv");
		city2.setImportant(0);
		city2.assignRegion(region2);
		cityRepository.save(city2);
		
		City city3 = new City();
		city3.setId(3);
		city3.setTitle("Boryspil");
		city3.setImportant(0);
		city3.assignRegion(region1);
		cityRepository.save(city3);
	}
	
	
	
	/*https://api.vk.com/method/database.getRegions?country_id=2
	https://api.vk.com/method/database.getCountriesById?country_ids=1
*/	

}
