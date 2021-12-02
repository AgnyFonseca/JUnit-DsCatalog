package com.devsuperior.dscatalog.resources;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.services.ProductService;
import com.devsuperior.dscatalog.tests.Factory;

@WebMvcTest(ProductResource.class)
public class ProductResourceTests {
	
	//Requisição, chamando o Endpoint
	@Autowired
	private MockMvc mockMvc;
	
	private ProductDTO productDTO;
	private PageImpl<ProductDTO> page;
	
	@MockBean
	private ProductService service;
	
	//Simular comportamento
	@BeforeEach
	void setUp() throws Exception {
		
		productDTO = Factory.createProductDTO();
		page = new PageImpl<>(List.of(productDTO));
		
		//Static import de when sem o Mockito.
		when(service.findAllPaged(ArgumentMatchers.any())).thenReturn(page);
	}
	
	@Test
	public void findAllShouldReturnPage() throws Exception {
		//perform faz uma requisição
		mockMvc.perform(get("/products")).andExpect(status().isOk());
	}
}