package com.github.fiap.simple_api_java.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fiap.simple_api_java.dto.ProdutoCreateDto;
import com.github.fiap.simple_api_java.dto.ProdutoResponseDto;
import com.github.fiap.simple_api_java.dto.ProdutoUpdateDto;
import com.github.fiap.simple_api_java.model.Produto;
import com.github.fiap.simple_api_java.service.ProdutoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;
    
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> list() {

        List<ProdutoResponseDto> responseDtos = produtoService.list()
            .stream()
            .map(produto -> new ProdutoResponseDto().toDto(produto))
            .toList();
        return ResponseEntity.ok().body(responseDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponseDto> findById(@PathVariable Long id) {
        
        ProdutoResponseDto responseDto = produtoService
                .findById(id)
                .map(produto -> new ProdutoResponseDto().toDto(produto))
                .orElseThrow(() -> new RuntimeException("Erro"));
        
                return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> create(@RequestBody ProdutoCreateDto requestDto) {
        Produto produto = produtoService.saveOrUpdate(requestDto.toModel());

        ProdutoResponseDto responseDto = new ProdutoResponseDto().toDto(produto);
        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoResponseDto> update(@PathVariable Long id, @RequestBody ProdutoUpdateDto requestDto) {
        if (! produtoService.existsById(id)) {
            new RuntimeException("Erro");
        }

        Produto produto = produtoService.saveOrUpdate(requestDto.toModel());

        ProdutoResponseDto responseDto = new ProdutoResponseDto().toDto(produto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id) {
        if (!produtoService.existsById(id)) {
            new RuntimeException("Erro");
        }
        
        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
