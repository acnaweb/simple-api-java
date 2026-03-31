package com.github.fiap.simple_api_java.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.github.fiap.simple_api_java.dto.ProdutoCreateDto;
import com.github.fiap.simple_api_java.model.Produto;
import com.github.fiap.simple_api_java.service.ProdutoService;

@WebMvcTest
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService service;

    private ProdutoCreateDto produtoCreateDto;

    
    @BeforeEach
    public void setup() {
        produtoCreateDto = new ProdutoCreateDto();
        produtoCreateDto.setNome("Produto novo");

        }
    
    @DisplayName("test create produto")
    @Test
    void testGivenNewProduto_whenCreate_thenSavedProduto() throws Exception{
        
        given(service.saveOrUpdate(any(Produto.class)))
            .willAnswer((invocation) -> invocation.getArguments()[0]);

        String body = "{\"nome\":\"Maçã\"}";
        
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .content(body));

        response.andExpect(status().isOk());
    }

    @DisplayName("test fail")
    @Test
    void testGivenNewProduto_whenCreate_thenFail() throws Exception{
        // fail("Um erro acontecerá");
    }
    
}
