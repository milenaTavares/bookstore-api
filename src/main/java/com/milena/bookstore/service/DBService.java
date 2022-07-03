package com.milena.bookstore.service;

import com.milena.bookstore.domain.Categoria;
import com.milena.bookstore.domain.Livro;
import com.milena.bookstore.repositories.CategoriaRepository;
import com.milena.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;

    public void instanciaBaseDeDados() {

        Categoria c1 = new Categoria(null, "Informática", "Livro de TI");
        Categoria c2 = new Categoria(null, "Romance", "Livro de romance");
        Categoria c3 = new Categoria(null, "Suspense", "Livro de suspense");

        Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Lorem Ipsum", c1);
        Livro l2 = new Livro(null, "A culpa é das estrelas", "Jonh Grey", "Lorem Ipsum", c2);
        Livro l3 = new Livro(null, "Livro de romance", "Teste", "Lorem Ipsum", c2);
        Livro l4 = new Livro(null, "Maria, maria", "João da Silva", "Lorem Ipsum", c3);
        Livro l5 = new Livro(null, "João", "Roberto", "Lorem Ipsum", c3);

        c1.getLivros().addAll(Arrays.asList(l1));
        c2.getLivros().addAll(Arrays.asList(l2, l3));
        c3.getLivros().addAll(Arrays.asList(l4, l5));

        this.categoriaRepository.saveAll(Arrays.asList(c1, c2, c3));
        this.livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
    }
}
