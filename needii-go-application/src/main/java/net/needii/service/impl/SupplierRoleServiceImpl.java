/**
 * 
 */
package net.needii.service.impl;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.SupplierRoleDto;
import net.needii.jpa.entity.SupplierRole;
import net.needii.repository.SupplierRoleRepository;
import net.needii.service.SupplierRoleService;

/**
 * @author Vincent
 *
 */
@Transactional
@Service
public class SupplierRoleServiceImpl implements SupplierRoleService {

	@Autowired
	SupplierRoleRepository supplierRoleRepository;

	ModelMapper mapper;
	
	@Override
	public SupplierRoleDto findOne(int id) {
		SupplierRoleDto dto = new SupplierRoleDto();
		mapper.map(supplierRoleRepository.getOne(id), dto);
		return dto;
	}

	@PostConstruct
	public void postInit() {
		mapper = new ModelMapper();
		mapper.addMappings(supplierRolePropertiesSkip);
	}
	PropertyMap<SupplierRole, SupplierRoleDto> supplierRolePropertiesSkip = new PropertyMap<SupplierRole, SupplierRoleDto>() {

		@Override
		protected void configure() {
			skip().setSuppliers(null);
		}
	};

}
