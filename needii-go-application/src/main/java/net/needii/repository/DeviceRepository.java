package net.needii.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.Device;


/**
 * @author vincent
 *
 */
public interface DeviceRepository extends JpaRepository<Device, Long>{
	
    Device findByDeviceUID(String deviceUID);
}
