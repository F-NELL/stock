package com.example.stock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.stock.controller.ProduitController;
import com.example.stock.pojo.Produit;
import com.example.stock.service.ProduitService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProduitController.class)
class StockApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ProduitService produitService;

	@Test
	public void testGetProduits() throws Exception {
		mockMvc.perform(get("/api/produit"))
				.andExpect(status().isOk());
	}

	@Test
	public void createProduit() throws Exception {
		Produit monProduit = new Produit("stylo", "c'est un beau stylo", 1.2F, 5);
		mockMvc.perform(post("/api/produit")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(monProduit)))
				.andExpect(status().isOk());
	}

}
