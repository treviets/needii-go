/**
 * 
 */
package net.needii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.jpa.entity.Device;
import net.needii.repository.DeviceRepository;
import net.needii.service.DeviceService;

/**
 * @author kelvin
 *
 */
@Transactional
@Service
public class DeviceServiceImpl implements DeviceService{
	@Autowired
	private DeviceRepository dao;
	
	@Override
	public List<Device> findAll() {
		return dao.findAll();
	}
	
	@Override
	public Device findOne(long id) {
		return dao.findOne(id);
	}
	
	@Override
	public Device findOne(String deviceUID) {
		return dao.findByDeviceUID(deviceUID);
	}

	@Override
	public void create(Device entity) {
		dao.save(entity);
	}

	@Override
	public void update(Device entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Device entity) {
		dao.delete(entity);
	}

	@Override
	public void createOrUpdate(Device entity) {
		dao.save(entity);
	}

}
