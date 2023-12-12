package org.java.spring.controller;

import org.java.spring.db.pojo.Discount;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.DiscountService;
import org.java.spring.db.serv.PizzaService;
import org.java.spring.dto.DiscountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DiscountController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DiscountService discountService;
	
	@GetMapping("/pizzas/{id}/discount/create")
	public String createDiscount(Model model, @PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		
		Discount discount = new Discount();
		
		discount.setPizza(pizza);
		
		model.addAttribute("discount", discount);
		
		return "discount-form";
	}
	@PostMapping("/pizzas/{id}/discount/create")
	public String storeDiscount(@ModelAttribute DiscountDTO discountDTO, @PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		
		Discount discount = new Discount(discountDTO.getData_inizio(), discountDTO.getData_fine(), discountDTO.getTitolo(), pizza);
		
		discountService.save(discount);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizzas/{pizzaId}/discount/edit/{discountId}")
	public String editDiscount(Model model, @PathVariable int pizzaId, @PathVariable int discountId) {
		
		Discount discount = discountService.findById(discountId);
		
		model.addAttribute("discount", discount);
		
		return "discount-form";
	}
	@PostMapping("/pizzas/{pizzaId}/discount/edit/{discountId}")
	public String updateDiscount(@ModelAttribute DiscountDTO discountDTO, @PathVariable int pizzaId, @PathVariable int discountId) {
		
		Discount discount = discountService.findById(discountId);
		
		discount.setData_inizio(discountDTO.getData_inizio());
		discount.setData_fine(discountDTO.getData_fine());
		discount.setTitolo(discountDTO.getTitolo());
		
		discountService.save(discount);
		
		return "redirect:/";
	}
}
