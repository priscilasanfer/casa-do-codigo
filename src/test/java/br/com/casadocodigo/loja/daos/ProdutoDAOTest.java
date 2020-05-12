package br.com.casadocodigo.loja.daos;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.config.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProdutoDAO.class,
        JPAConfiguration.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProdutoDAOTest {

    @Autowired
    private ProdutoDAO produtoDao;

    @Test
    @Transactional
    public void deveSomarTodosPrecosPorTipoLivro() {

        List<Produto> livrosImpressos = ProdutoBuilder
                .newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN)
                .mais(3).buildAll();
        livrosImpressos.stream().forEach(produtoDao::gravar);

        List<Produto> livrosEbook = ProdutoBuilder
                .newProduto(TipoPreco.EBOOK, BigDecimal.TEN)
                .mais(3).buildAll();
        livrosEbook.stream().forEach(produtoDao::gravar);

        BigDecimal valor = produtoDao.somaPrecosPorTipo(TipoPreco.EBOOK);
        Assert.assertEquals(new BigDecimal(40).setScale(2), valor);

    }
}