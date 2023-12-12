package org.java.spring;

import java.time.LocalDate;

import org.java.spring.auth.conf.AuthConf;
import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.serv.RoleService;
import org.java.spring.auth.db.serv.UserService;
import org.java.spring.db.pojo.Discount;
import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.DiscountService;
import org.java.spring.db.serv.IngredientService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DiscountService discountService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	public void run(String... args) throws Exception {
		
		Ingredient ingredient1 = new Ingredient("ingrediente-1");
		Ingredient ingredient2 = new Ingredient("ingrediente-2");
		Ingredient ingredient3 = new Ingredient("ingrediente-3");
		Ingredient ingredient4 = new Ingredient("ingrediente-4");
		Ingredient ingredient5 = new Ingredient("ingrediente-5");
		
		ingredientService.save(ingredient1);
		ingredientService.save(ingredient2);
		ingredientService.save(ingredient3);
		ingredientService.save(ingredient4);
		ingredientService.save(ingredient5);
		
		Ingredient[] ingredientsPizza1 = {ingredient1, ingredient2};
		Pizza pizza1 = new Pizza("Margherita", "Descrizione 1", "https://picsum.photos/500/200", 9.3, ingredientsPizza1);
		
		Ingredient[] ingredientsPizza2 = {ingredient2, ingredient4};
		Pizza pizza2 = new Pizza("Pepperoni", "Descrizione 2", "https://picsum.photos/500/200", 10.5, ingredientsPizza2);
		
		Ingredient[] ingredientsPizza3 = {ingredient1, ingredient3};
		Pizza pizza3 = new Pizza("Siciliana", "Descrizione 3", "https://picsum.photos/500/200", 11.7, ingredientsPizza3);
		
		Ingredient[] ingredientsPizza4 = {ingredient5, ingredient4};
		Pizza pizza4 = new Pizza("Quattro Stagioni", "Descrizione 4", "https://picsum.photos/500/200", 12.9, ingredientsPizza4);
		
		Ingredient[] ingredientsPizza5 = {ingredient1, ingredient4};
		Pizza pizza5 = new Pizza("Capricciosa", "Descrizione 5", "https://picsum.photos/500/200", 13.1, ingredientsPizza5);
		
		Ingredient[] ingredientsPizza6 = {ingredient4, ingredient1};
		Pizza pizza6 = new Pizza("Quattro Formaggi", "Descrizione 6", "https://picsum.photos/500/200", 15.50, ingredientsPizza6);

		pizzaService.save(pizza1);
		pizzaService.save(pizza2);
		pizzaService.save(pizza3);
		pizzaService.save(pizza4);
		pizzaService.save(pizza5);
		pizzaService.save(pizza6);
		
		Discount discount1 = new Discount(LocalDate.parse("2023-01-01"), LocalDate.parse("2024-01-01"), "offerta-speciale-margherita", pizza1);
		discountService.save(discount1);
		
		
		Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		
		String pws1 = AuthConf.passwordEncoder().encode("password-1");
		String pws2 = AuthConf.passwordEncoder().encode("password-2");
		
		userService.save(new User("utente-1", pws1, roleUser));
		userService.save(new User("utente-2", pws2, roleAdmin));
	}
}
