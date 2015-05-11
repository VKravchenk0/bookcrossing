package ua.vkravchenko.bc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.vkravchenko.bc.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
