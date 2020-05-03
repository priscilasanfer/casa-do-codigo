package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.models.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProdutosController {

    @RequestMapping("/produtos/form")
    public String form() {
        return "produtos/form";
    }

    @RequestMapping("/produtos")
    public String gravar(Produto produto) {
        System.out.println(produto);
        return "produtos/ok";
    }
}