package ua.vkravchenko.bc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ua.vkravchenko.bc.entity.User;
import ua.vkravchenko.bc.repository.UserRepository;

@Transactional
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public List<User> findOnPage(int page) {
		int lastIndex = page * 20;
		int startingIndex = lastIndex - 20;
		return userRepository.findAll(new PageRequest(startingIndex, lastIndex, Direction.ASC, "id")).getContent();
	}
	
}
