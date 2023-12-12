package org.java.spring.db.serv;

import java.util.List;

import org.java.spring.db.pojo.Discount;
import org.java.spring.db.repo.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

	@Autowired
	private DiscountRepository discountRepository;
	
	public List<Discount> findAll() {
		
		return discountRepository.findAll();
	}
	public Discount findById(int id) {
		
		return discountRepository.findById(id).get();
	}
	public void save(Discount discount) {
		
		discountRepository.save(discount);
	}
	public void delete(Discount discount) {
		
		discountRepository.delete(discount);
	}
}
