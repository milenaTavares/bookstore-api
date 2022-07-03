package com.milena.bookstore.resources;

import com.milena.bookstore.domain.Categoria;
import com.milena.bookstore.dtos.CategoriaDTO;
import com.milena.bookstore.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok().body(categoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> categorias = categoriaService.findAll();
        List<CategoriaDTO> categoriasDTO = categorias.stream().map(categoria -> montarCategoriaDTO(categoria)).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoriasDTO);
    }

    public CategoriaDTO montarCategoriaDTO(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.id = categoria.getId();
        categoriaDTO.nome = categoria.getNome();
        categoriaDTO.descricao = categoria.getDescricao();
        return categoriaDTO;
    }
}
