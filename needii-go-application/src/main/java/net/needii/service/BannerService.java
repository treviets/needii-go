package net.needii.service;

import java.util.List;

import net.needii.jpa.entity.Banner;

public interface BannerService {
	
	List<Banner> findAll();
	
	Banner findOne(long id);
    
    void create(Banner entity);

    void update(Banner entity);

    void delete(Banner entity);
}
