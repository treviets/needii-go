package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.Ward;

/**
 * @author Vincent
 *
 */
public interface WardRepository extends JpaRepository<Ward, Integer>{
	
	List<Ward> findByDistrictId(int districtId);
}
