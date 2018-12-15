package net.needii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.jpa.entity.Banner;
import net.needii.repository.BannerRepository;
import net.needii.service.BannerService;

@Transactional
@Service("bannerService")
public class BannerServiceImpl implements BannerService{

	@Autowired
	private BannerRepository dao;
	
	@Override
	public List<Banner> findAll() {
		return dao.findAll();
	}
	
	
	@Override
	public Banner findOne(long id) {
		return dao.findOne(id);
	}


	@Override
	public void create(Banner entity) {
		dao.save(entity);
	}

	@Override
	public void update(Banner entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Banner entity) {
		dao.delete(entity);
	}

}
