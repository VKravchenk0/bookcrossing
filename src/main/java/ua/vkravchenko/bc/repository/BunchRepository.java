package ua.vkravchenko.bc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.vkravchenko.bc.entity.Bunch;
import ua.vkravchenko.bc.entity.User;

public interface BunchRepository extends JpaRepository<Bunch, Integer>{

	List<Bunch> findByUser(User user);

}
