/**
 * 
 */
package net.needii.service;

import java.util.List;

import net.needii.jpa.entity.Device;

/**
 * @author kelvin
 *
 */
public interface DeviceService {

	List<Device> findAll();
	
	Device findOne(long id);
	
	Device findOne(String deviceUID);
	
	void create(Device entity);

    void update(Device entity);

    void delete(Device entity);
    
    void createOrUpdate(Device entity);
	
}
