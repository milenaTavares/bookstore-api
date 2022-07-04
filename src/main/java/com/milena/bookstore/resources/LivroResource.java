package com.milena.bookstore.resources;

import com.milena.bookstore.domain.Livro;
import com.milena.bookstore.dtos.LivroDTO;
import com.milena.bookstore.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

    @Autowired
    LivroService livroService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id) {
        Livro livro = livroService.findById(id);
        return ResponseEntity.ok().body(livro);
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer categoriaId) {
        List<Livro> livros = livroService.findAll(categoriaId);
        List<LivroDTO> livrosDTO = livros.stream().map(livro -> montarLivroDTO(livro)).collect(Collectors.toList());
        return ResponseEntity.ok().body(livrosDTO);
    }

    @PostMapping
    public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer categoriaId,
                                        @RequestBody Livro livro) {
        livro = livroService.create(categoriaId, livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro livro) {
        Livro updateLivro = livroService.update(id, livro);
        return ResponseEntity.ok().body(updateLivro);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @RequestBody Livro livro) {
        Livro updateLivro = livroService.update(id, livro);
        return ResponseEntity.ok().body(updateLivro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private LivroDTO montarLivroDTO(Livro livro) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.id = livro.getId();
        livroDTO.titulo = livro.getTitulo();
        return livroDTO;
    }
}
