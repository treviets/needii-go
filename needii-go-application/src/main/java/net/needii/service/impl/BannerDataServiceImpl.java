package net.needii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.jpa.entity.BannerData;
import net.needii.jpa.entity.BannerTypeEnum;
import net.needii.repository.BannerDataRepository;
import net.needii.service.BannerDataService;

@Transactional
@Service
public class BannerDataServiceImpl implements BannerDataService {

	@Autowired
	private BannerDataRepository dao;
	
	@Override
	public List<BannerData> findAll() {
		return dao.findAll();
	}

	@Override
	public Long count() {
		return dao.count(); 
	}

	@Override
	public BannerData findOne(long id) {
		return dao.findOne(id);
	}

	@Override
	public void create(BannerData entity) {
		dao.save(entity);
	}

	@Override
	public void update(BannerData entity) {
		dao.save(entity);
	}

	@Override
	public void delete(BannerData entity) {
		dao.save(entity);
	}

	@Override
	public List<BannerData> findAll(BannerTypeEnum typeEnum) {
		return dao.findByBannerType(typeEnum.getValue());
	}

}
