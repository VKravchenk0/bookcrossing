package ua.vkravchenko.bc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.vkravchenko.bc.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}
