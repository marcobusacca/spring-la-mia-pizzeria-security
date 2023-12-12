package org.java.spring;

import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.serv.RoleService;
import org.java.spring.auth.db.serv.UserService;
import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.IngredientService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	public void run(String... args) throws Exception {
		
		pizzaService.save(new Pizza("Margherita", "Descrizione 1", "https://picsum.photos/500/200", 9.3));
		pizzaService.save(new Pizza("Pepperoni", "Descrizione 2", "https://picsum.photos/500/200", 10.5));
		pizzaService.save(new Pizza("Siciliana", "Descrizione 3", "https://picsum.photos/500/200", 11.7));
		pizzaService.save(new Pizza("Quattro Stagioni", "Descrizione 4", "https://picsum.photos/500/200", 12.9));
		pizzaService.save(new Pizza("Capricciosa", "Descrizione 5", "https://picsum.photos/500/200", 13.1));
		pizzaService.save(new Pizza("Quattro Formaggi", "Descrizione 6", "https://picsum.photos/500/200", 15.50));
		
		ingredientService.save(new Ingredient("ingrediente-1"));
		ingredientService.save(new Ingredient("ingrediente-2"));
		ingredientService.save(new Ingredient("ingrediente-3"));
		ingredientService.save(new Ingredient("ingrediente-4"));
		ingredientService.save(new Ingredient("ingrediente-5"));
		ingredientService.save(new Ingredient("ingrediente-6"));
		ingredientService.save(new Ingredient("ingrediente-7"));
		ingredientService.save(new Ingredient("ingrediente-8"));
		ingredientService.save(new Ingredient("ingrediente-9"));
		ingredientService.save(new Ingredient("ingrediente-10"));
		
		Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		
		userService.save(new User("utente-1", "password-1", roleUser));
		userService.save(new User("utente-2", "password-2", roleAdmin));
	}
}
