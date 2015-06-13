package ua.vkravchenko.bc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.vkravchenko.bc.entity.Country;
import ua.vkravchenko.bc.entity.User;

public interface CountryRepository extends JpaRepository<Country, Integer>{

	@Query("SELECT u FROM User u WHERE u.city.id IN (SELECT city.id FROM City city WHERE city.country.id = ?1)")
	public List<User> getInhabitants(int countryId);
	

}
