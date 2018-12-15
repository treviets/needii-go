package net.needii.service;

import java.util.List;

import net.needii.dto.PaymentMethodDto;
import net.needii.jpa.entity.PaymentMethodTypeEnum;

public interface PaymentMethodService {
	
	List<PaymentMethodDto> findAll();
	
	PaymentMethodDto findOne(int id);
	
	PaymentMethodDto findByType(PaymentMethodTypeEnum typeEnum);
    
    void create(PaymentMethodDto entity);

    void update(PaymentMethodDto entity);

    void delete(PaymentMethodDto entity);
}
