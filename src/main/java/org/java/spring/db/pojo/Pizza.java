package org.java.spring.db.pojo;


import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 20)
	@NotBlank(message = "Il nome non può essere vuoto")
	@Length(min = 3, max = 20, message = "Il nome della pizza deve essere compreso tra 3 e 20 caratteri")
	private String nome;
	
	@Column(columnDefinition = "TEXT")
	@NotBlank(message = "La descrizione non può essere vuota")
	@Length(min = 5, max = 100, message = "La descrizione della pizza deve essere compresa tra 5 e 100 caratteri")
	private String descrizione;
	
	@NotBlank(message = "L url della foto non può essere vuoto")
	@URL(protocol = "https", message = "Devi inserire un URL valido (Protocollo HTTPS)")
	private String url_foto;
	
	@Positive(message = "Il prezzo deve essere maggiore di 0")
	private double prezzo;
	
	@OneToMany(mappedBy = "pizza")
	private List<Discount> discounts;
	
	@ManyToMany
	private List<Ingredient> ingredients;
	
	public Pizza() {}
	public Pizza(String nome, String descrizione, String urlFoto, double prezzo, Ingredient...ingredients) {
		setNome(nome);
		setDescrizione(descrizione);
		setUrl_foto(urlFoto);
		setPrezzo(prezzo);
		setIngredients(ingredients);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getUrl_foto() {
		return url_foto;
	}
	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	public List<Discount> getDiscounts() {
		return discounts;
	}
	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public void setIngredients(Ingredient...ingredients) {
		setIngredients(Arrays.asList(ingredients));
	}
	
	
	public String getPrezzoFormattato() {
		return String.format("%.2f", getPrezzo()) + "€";
	}
	
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getNome() + " - " 
				+ getPrezzo();
	}
}
