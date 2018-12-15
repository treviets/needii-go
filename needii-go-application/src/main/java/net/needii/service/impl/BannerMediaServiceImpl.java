package net.needii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.jpa.entity.BannerMedia;
import net.needii.repository.BannerMediaRepository;
import net.needii.service.BannerMediaService;

@Transactional
@Service
public class BannerMediaServiceImpl implements BannerMediaService{

	@Autowired
	private BannerMediaRepository dao;
	
	@Override
	public List<BannerMedia> findAll() {
		return dao.findAll();
	}

	@Override
	public Long count() {
		return dao.count(); 
	}

	@Override
	public BannerMedia findOne(long id) {
		return dao.findOne(id);
	}

	@Override
	public void create(BannerMedia entity) {
		dao.save(entity);
	}

	@Override
	public void update(BannerMedia entity) {
		dao.save(entity);
	}

	@Override
	public void delete(BannerMedia entity) {
		dao.delete(entity);
	}

}
