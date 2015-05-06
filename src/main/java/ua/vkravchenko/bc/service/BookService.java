package ua.vkravchenko.bc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ua.vkravchenko.bc.entity.Book;
import ua.vkravchenko.bc.entity.Bunch;
import ua.vkravchenko.bc.entity.User;
import ua.vkravchenko.bc.repository.BookRepository;
import ua.vkravchenko.bc.repository.BunchRepository;
import ua.vkravchenko.bc.repository.UserRepository;

@Transactional
@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public Book findOne(int id) {
		return bookRepository.findOne(id);
	}
	
	public void save(Book book) {
		bookRepository.save(book);
	}
}
