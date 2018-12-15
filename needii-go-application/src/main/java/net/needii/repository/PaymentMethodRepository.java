package net.needii.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.needii.jpa.entity.PaymentMethod;

/**
 * @author vincent
 *
 */
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer>{
	

	PaymentMethod findByType(int typeEnum);
}
