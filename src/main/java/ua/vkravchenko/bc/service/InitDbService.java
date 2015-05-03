package ua.vkravchenko.bc.service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.vkravchenko.bc.entity.User;
import ua.vkravchenko.bc.repository.UserRepository;

@Transactional
@Service
public class InitDbService {
	
	@Autowired
	private UserRepository userRepository;
	
	// Method will be executed after context creation
	@PostConstruct
	public void init() {
		User user1 = new User();
		user1.setName("Slava");
		userRepository.save(user1);
		
		User user2 = new User();
		user2.setName("Petya");
		userRepository.save(user2);
	}
	

}
