/**
 * 
 */
package net.needii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.needii.dto.CartItemDto;
import net.needii.jpa.entity.CartItem;
import net.needii.repository.CartItemRepository;
import net.needii.service.CartItemService;

/**
 * @author kelvin
 *
 */
@Transactional
@Service
public class CartItemServiceImpl implements CartItemService{
	@Autowired
	private CartItemRepository dao;
	
	@Override
	public CartItemDto findOne(long id) {
		ModelMapper mapper = new ModelMapper();
		CartItemDto cartItemDto = new CartItemDto();
		mapper.map(dao.findOne(id), cartItemDto);
		return cartItemDto;
	}
	
	@Override
	public List<CartItemDto> findByCartId(long cartId) {
		return this.map(dao.findByCartId(cartId));
	}
	
	@Override
	public void create(CartItemDto cartItemDto) {
		ModelMapper mapper = new ModelMapper();
		CartItem entity = new CartItem();
		mapper.map(cartItemDto, entity);
		dao.save(entity);
	}

	@Override
	public void update(CartItemDto cartItemDto) {
		ModelMapper mapper = new ModelMapper();
		CartItem entity = new CartItem();
		mapper.map(cartItemDto, entity);
		dao.save(entity);
	}

	@Override
	public void delete(CartItemDto cartItemDto) {
		ModelMapper mapper = new ModelMapper();
		CartItem entity = new CartItem();
		mapper.map(cartItemDto, entity);
		dao.delete(entity);
	}

	@Override
	public void createOrUpdate(CartItemDto cartItemDto) {
		ModelMapper mapper = new ModelMapper();
		CartItem entity = new CartItem();
		mapper.map(cartItemDto, entity);
		dao.save(entity);
	}

	private List<CartItemDto> map(List<CartItem> source) {
		ModelMapper mapper = new ModelMapper();
		List<CartItemDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			CartItemDto dto = new CartItemDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}
}
