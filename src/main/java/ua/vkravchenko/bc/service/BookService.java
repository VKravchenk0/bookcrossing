package ua.vkravchenko.bc.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.vkravchenko.bc.entity.Book;
import ua.vkravchenko.bc.repository.BookRepository;

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
