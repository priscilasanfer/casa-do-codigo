package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProdutoDAO produtoDao;

    @RequestMapping("/")
    @Cacheable(value="produtosHome")
    public ModelAndView index() {

        List<Produto> produtos = produtoDao.listar();

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", produtos);

        return modelAndView;
    }
}