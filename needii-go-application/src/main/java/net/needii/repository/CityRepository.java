package net.needii.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.City;

/**
 * @author vincent
 *
 */
public interface CityRepository extends JpaRepository<City, Integer>{
	
    
}