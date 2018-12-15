/**
 * 
 */
package net.needii.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.Language;

/**
 * @author kelvin
 *
 */
public interface LanguageRepository extends JpaRepository<Language, Integer>{
	Language findByCode(String code);
}
