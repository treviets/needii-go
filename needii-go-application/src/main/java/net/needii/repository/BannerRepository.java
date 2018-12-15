/**
 * 
 */
package net.needii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.Banner;

/**
 * @author vincent
 *
 */
public interface BannerRepository extends JpaRepository<Banner, Long>{
    List<Banner> findBySupplierId(long supplierId);
}
