package com.milena.bookstore.service;

import com.milena.bookstore.domain.Categoria;
import com.milena.bookstore.domain.Livro;
import com.milena.bookstore.repositories.LivroRepository;
import com.milena.bookstore.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    CategoriaService categoriaService;

    public Livro findById(Integer id) {
        Optional<Livro> obj = livroRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado! Id:" + id + "."));
    }

    public List<Livro> findAll(Integer categoriaId) {
        List<Livro> livros = new ArrayList<>();
        Categoria categoria = categoriaService.findById(categoriaId);
        if (Objects.nonNull(categoria)) {
            livros = livroRepository.findAllByCategoria(categoriaId);
        }
        return livros;
    }

    public Livro create(Integer categoriaId, Livro livro) {
        livro.setId(null);
        Categoria categoria = categoriaService.findById(categoriaId);
        livro.setCategoria(categoria);
        return livroRepository.save(livro);
    }

    public Livro update(Integer id, Livro livro) {
        Livro newLivro = findById(id);
        updateData(newLivro, livro);
        return livroRepository.save(newLivro);
    }

    private void updateData(Livro newlivro, Livro livro) {
        newlivro.setTitulo(livro.getTitulo());
        newlivro.setNomeAutor(livro.getNomeAutor());
        newlivro.setTexto(livro.getTexto());
    }

    public void delete(Integer id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            livroRepository.deleteById(id);
        }
    }
}
