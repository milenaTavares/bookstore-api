package com.milena.bookstore.service;

import com.milena.bookstore.domain.Categoria;
import com.milena.bookstore.repositories.CategoriaRepository;
import com.milena.bookstore.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! Id:" + id + "."));
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
}
