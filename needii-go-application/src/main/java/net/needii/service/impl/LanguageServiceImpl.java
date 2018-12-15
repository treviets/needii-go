package net.needii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.jpa.entity.Language;
import net.needii.repository.LanguageRepository;
import net.needii.service.LanguageService;


@Transactional
@Service
public class LanguageServiceImpl implements LanguageService{

	@Autowired
	private LanguageRepository dao;
	
	@Override
	public List<Language> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Language findOne(String code) {
		return dao.findByCode(code);
	}

}
