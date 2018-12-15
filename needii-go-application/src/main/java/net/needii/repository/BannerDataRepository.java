/**
 * 
 */
package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.BannerData;

/**
 * @author vincent
 *
 */
public interface BannerDataRepository extends JpaRepository<BannerData, Long>{
	List<BannerData> findByBannerType(int type);
    
}
