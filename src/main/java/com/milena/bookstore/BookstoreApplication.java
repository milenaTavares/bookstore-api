package com.milena.bookstore;

import com.milena.bookstore.domain.Categoria;
import com.milena.bookstore.domain.Livro;
import com.milena.bookstore.repositories.CategoriaRepository;
import com.milena.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private LivroRepository livroRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática", "Livro de TI");
		Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Lorem Ipsum", c1);
		c1.getLivros().addAll(Arrays.asList(l1));

		this.categoriaRepository.saveAll(Arrays.asList(c1));
		this.livroRepository.saveAll(Arrays.asList(l1));
	}
}