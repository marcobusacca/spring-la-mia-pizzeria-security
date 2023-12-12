package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Discount;
import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.IngredientService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping
	public String getPizzas(Model model, @RequestParam(required = false) String nome) {
		
		List<Pizza> pizzas = nome == null
							? pizzaService.findAll()
							: pizzaService.findByNome(nome);
		
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("nome", nome == null ? "" : nome);
		
		return "pizzas";
	}
	
	@GetMapping("/pizzas/{id}")
	public String getPizza(Model model, @PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		
		List<Discount> discounts = pizza.getDiscounts();
		
		model.addAttribute("pizza", pizza);
		
		model.addAttribute("discounts", discounts);
		
		return "pizza";
	}
	
	@GetMapping("/pizzas/create")
	public String createPizza(Model model) {
		
		Pizza pizza = new Pizza();
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredients);
		
		return "pizza-form";
	}
	@PostMapping("/pizzas/create")
	public String storePizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
		
		return savePizza(model, pizza, bindingResult);
	}
	
	@GetMapping("pizzas/edit/{id}")
	public String editPizza(Model model, @PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredients);
		
		return "pizza-form";
	}
	@PostMapping("pizzas/edit/{id}")
	public String updatePizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
		
		return savePizza(model, pizza, bindingResult);
	}
	
	@PostMapping("/pizzas/delete/{id}")
	public String deletePizza(@PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		pizzaService.delete(pizza);
		
		System.out.println(pizza);
		
		return "redirect:/";
	}
	
	private String savePizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
		
		System.out.println("Pizza:\n" + pizza);
		System.out.println("\n---------------\n");
		System.out.println("Error:\n" + bindingResult);
		
		if (bindingResult.hasErrors()) {
			
			System.out.println(bindingResult);
			
			model.addAttribute("pizza", pizza);
			return "pizza-form";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
}
