package com.github.fiap.simple_api_java.dto;

import org.modelmapper.ModelMapper;

import com.github.fiap.simple_api_java.model.Produto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoCreateDto {    
	
    private String nome;  
    private static final ModelMapper mapper = new ModelMapper();
    
    public Produto toModel() {
        return  mapper.map(this, Produto.class);
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static ModelMapper getMapper() {
		return mapper;
	}
    
    
}
