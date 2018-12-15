package net.needii.service;

import java.util.List;

import net.needii.jpa.entity.Language;

public interface LanguageService {
	List<Language> findAll();	
	
	Language findOne(String code);
}
