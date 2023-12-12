package org.java.spring.db.pojo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate data_inizio;
	
	private LocalDate data_fine;

	private String titolo;
	
	@ManyToOne
	private Pizza pizza;
	
	public Discount() { }
	public Discount(LocalDate data_inizio, LocalDate data_fine, String titolo, Pizza pizza) {
		setData_inizio(data_inizio);
		setData_fine(data_fine);
		setTitolo(titolo);
		setPizza(pizza);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getData_inizio() {
		return data_inizio;
	}
	public void setData_inizio(LocalDate data_inizio) {
		this.data_inizio = data_inizio;
	}
	public LocalDate getData_fine() {
		return data_fine;
	}
	public void setData_fine(LocalDate data_fine) {
		this.data_fine = data_fine;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getTitolo();
	}
}
