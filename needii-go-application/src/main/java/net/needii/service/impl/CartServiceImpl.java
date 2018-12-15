/**
 * 
 */
package net.needii.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.CartDto;
import net.needii.jpa.entity.Cart;
import net.needii.repository.CartRepository;
import net.needii.service.CartService;

/**
 * @author kelvin
 *
 */
@Transactional
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository dao;

	@Override
	public CartDto findOne(long id) {
		ModelMapper mapper = new ModelMapper();
		CartDto cardDto = new CartDto();
		Cart cartEntity= dao.findOne(id);
		if(cartEntity == null) {
			return null;
		}
		mapper.map(cartEntity, cardDto);
		return cardDto;
	}

	@Override
	public CartDto findByCustomerId(long customerId) {
		ModelMapper mapper = new ModelMapper();
		CartDto cardDto = new CartDto();
		Cart cartEntity= dao.findByCustomerId(customerId);
		if(cartEntity == null) {
			return null;
		}
		mapper.map(cartEntity, cardDto);
		return cardDto;
	}

	@Override
	public CartDto findByDeviceUID(String deviceUID) {
		ModelMapper mapper = new ModelMapper();
		CartDto cardDto = new CartDto();
		Cart cartEntity= dao.findByDeviceUID(deviceUID);
		if(cartEntity == null) {
			return null;
		}
		mapper.map(cartEntity, cardDto);
		return cardDto;
	}

	@Override
	public void create(CartDto cartDto) {
		ModelMapper mapper = new ModelMapper();
		Cart entity = new Cart();
		mapper.map(cartDto, entity);

		dao.save(entity);
	}

	@Override
	public void update(CartDto cartDto) {
		ModelMapper mapper = new ModelMapper();
		Cart entity = new Cart();
		mapper.map(cartDto, entity);

		dao.save(entity);
	}

	@Override
	public void delete(CartDto cartDto) {
		ModelMapper mapper = new ModelMapper();
		Cart entity = new Cart();
		mapper.map(cartDto, entity);
		dao.delete(entity);
	}

	@Override
	public void createOrUpdate(CartDto cartDto) {
		ModelMapper mapper = new ModelMapper();
		Cart entity = new Cart();
		mapper.map(cartDto, entity);
		dao.save(entity);
	}

}
