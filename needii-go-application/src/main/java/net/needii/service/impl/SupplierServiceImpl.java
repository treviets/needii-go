/**
 * 
 */
package net.needii.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.needii.dto.SupplierDto;
import net.needii.jpa.entity.Supplier;
import net.needii.jpa.entity.SupplierBankInfo;
import net.needii.jpa.entity.SupplierCompanyProfile;
import net.needii.repository.SupplierRepository;
import net.needii.service.SupplierService;
import util.Option;

/**
 * @author kelvin
 *
 */
@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierRepository supplierRepository;

	ModelMapper mapper;
	@Override
	public List<SupplierDto> findAll(Option option) {
		return this.map(supplierRepository.findAll());
	}

	@Override
	public Long count(Option option) {
		return Long.valueOf(supplierRepository.findAll().size());
	}

	@Override
	public SupplierDto findOne(long id) {
		Supplier entity = supplierRepository.findOne(id);
		if (entity == null) {
			return null;
		}
		SupplierDto dto = new SupplierDto();
		mapper.map(entity, dto);
		return dto;
	}

	@Override
	public SupplierDto findEmail(String email) {
		Supplier entity = supplierRepository.findByEmail(email);
		if (entity == null) {
			return null;
		}
		SupplierDto dto = new SupplierDto();
		mapper.map(entity, dto);
		return dto;
	}

	@Override
	public SupplierDto findByPhone(String phone) {

		Supplier entity = supplierRepository.findByPhone(phone);
		if (entity == null) {
			return null;
		}
		SupplierDto dto = new SupplierDto();
		mapper.map(entity, dto);
		return dto;
	}

	@Override
	public SupplierDto create(SupplierDto dto) {
		Supplier supplierEntity = new Supplier();
		mapper.map(dto, supplierEntity);
		SupplierBankInfo bankInfo = supplierEntity.getBankInfo();
		SupplierCompanyProfile companyProfile = supplierEntity.getCompanyProfile();
		
		supplierEntity.setBankInfo(null);
		supplierEntity.setCompanyProfile(null);
		
		supplierEntity = supplierRepository.save(supplierEntity);
		bankInfo.setSupplier(supplierEntity);
		supplierEntity.setBankInfo(bankInfo);

		supplierEntity.setCompanyProfile(null);
		if(companyProfile != null) {
			companyProfile.setSupplier(supplierEntity);
		}
		
		supplierEntity.setSupplierAddress(null);
		supplierRepository.save(supplierEntity);
		dto = new SupplierDto();
		mapper.map(supplierEntity, dto);
		return dto;
	}

	@Override
	public SupplierDto update(SupplierDto dto) {
		Supplier supplierEntity = new Supplier();
		mapper.map(dto, supplierEntity);
		mapper.map(supplierRepository.save(supplierEntity), dto);
		return dto;
	}

	@Override
	public void delete(SupplierDto dto) {
		Supplier supplierEntity = new Supplier();
		mapper.map(dto, supplierEntity);
		supplierRepository.delete(supplierEntity);

	}

	@Override
	public List<SupplierDto> findByLocation(double lat, double lng, double distance) {
		return this.map(supplierRepository.findByLatAndLng(lat, lng));
	}
	
	public List<SupplierDto> map(List<Supplier> source) {
		ModelMapper mapper = new ModelMapper();
		List<SupplierDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			SupplierDto dto = new SupplierDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
	
	@PostConstruct
	public void postInit() {
		mapper = new ModelMapper();
		mapper.addMappings(supplierPropertiesSkip);
		mapper.getConfiguration().setAmbiguityIgnored(true);
	}
	
	PropertyMap<Supplier, SupplierDto> supplierPropertiesSkip = new PropertyMap<Supplier, SupplierDto>() {
		
		@Override
		protected void configure() {
			skip().getBankInfo().setSupplier(null);
			skip().getCompanyProfile().setSupplier(null);
			skip().setOrderDetails(null);
			skip().setSupplierAddress(null);
			skip().setSupplierRole(null);
		}
	};
}
