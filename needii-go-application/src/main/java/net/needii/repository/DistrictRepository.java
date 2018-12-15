package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.District;

/**
 * @author Vincent
 *
 */
public interface DistrictRepository extends JpaRepository<District, Integer>{
	
	List<District> findByCityId(int cityId);
}
