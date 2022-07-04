package com.milena.bookstore.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public Integer id;

    @NotEmpty(message = "Campo nome é obrigatório.")
    @Length(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres.")
    public String nome;

    @NotEmpty(message = "Campo descrição é obrigatório.")
    @Length(min = 3, max = 200, message = "O campo descrição deve ter entre 3 e 200 caracteres.")
    public String descricao;
}
