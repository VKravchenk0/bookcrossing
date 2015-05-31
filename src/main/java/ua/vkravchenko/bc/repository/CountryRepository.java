package ua.vkravchenko.bc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.vkravchenko.bc.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{


}
