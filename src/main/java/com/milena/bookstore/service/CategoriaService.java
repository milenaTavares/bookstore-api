package com.milena.bookstore.service;

import com.milena.bookstore.domain.Categoria;
import com.milena.bookstore.dtos.CategoriaDTO;
import com.milena.bookstore.repositories.CategoriaRepository;
import com.milena.bookstore.service.exceptions.DataIntegrityViolationException;
import com.milena.bookstore.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    public Categoria create(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Integer id, CategoriaDTO categoriaDTO) {
        Categoria categoria = findById(id);
        if(Objects.nonNull(categoria)) {
            if(Objects.nonNull(categoriaDTO.nome)) {
                categoria.setNome(categoriaDTO.nome);
            }
            if(Objects.nonNull(categoriaDTO.descricao)) {
                categoria.setDescricao(categoriaDTO.descricao);
            }
        }
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()) {
            try {
                categoriaRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException("Categoria não pode ser excluida, pois possui livros associados");
            }
        }
    }
}
