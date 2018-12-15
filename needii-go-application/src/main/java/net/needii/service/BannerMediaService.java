package net.needii.service;

import java.util.List;

import net.needii.jpa.entity.BannerMedia;

public interface BannerMediaService {
	List<BannerMedia> findAll();	
	
	Long count();
	
	BannerMedia findOne(long id);
    
    void create(BannerMedia entity);

    void update(BannerMedia entity);

    void delete(BannerMedia entity);
}
