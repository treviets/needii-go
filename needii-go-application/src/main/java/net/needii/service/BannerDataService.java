package net.needii.service;

import java.util.List;

import net.needii.jpa.entity.BannerData;
import net.needii.jpa.entity.BannerTypeEnum;

public interface BannerDataService {
	List<BannerData> findAll();	
	
	Long count();
	
	BannerData findOne(long id);
    
    void create(BannerData entity);

    void update(BannerData entity);

    void delete(BannerData entity);
    

	List<BannerData> findAll(BannerTypeEnum typeEnum);
}
