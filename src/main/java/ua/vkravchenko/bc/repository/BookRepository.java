package ua.vkravchenko.bc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.vkravchenko.bc.entity.Book;
import ua.vkravchenko.bc.entity.Bunch;

public interface BookRepository extends JpaRepository<Book, Integer>{

	List<Book> findByBunches(List<Bunch> bunches);

}
