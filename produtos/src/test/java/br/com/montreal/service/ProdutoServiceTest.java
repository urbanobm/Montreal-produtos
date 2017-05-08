package br.com.montreal.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import br.com.montreal.entity.ImagemProduto;
import br.com.montreal.entity.ImagemTipo;
import br.com.montreal.entity.Produto;

@TestPropertySource(locations = "classpath:application.properties")
@Configuration
@EnableAutoConfiguration
@EntityScan("br.com.montreal.entity")
@EnableJpaRepositories("br.com.montreal.repository")
@ComponentScan({"br.com.montreal"})
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProdutoServiceTest {

	@Autowired
	private ProdutoService produtoService;

	@Test
	public void salvarProduto(){
		Produto produto = criarProdutoQualquer();
		Assert.assertNotNull(produto);
	}

	@Test
	public void deleteProduto(){
		Produto produto = criarProdutoQualquer();
		List<Produto> produtos = produtoService.recuperarProdutos();
		Assert.assertFalse(CollectionUtils.isEmpty(produtos));
		
		produtoService.deleteProduto(produto.getId());
		produtos = produtoService.recuperarProdutos();
		System.out.println(produtos);
		Assert.assertTrue(CollectionUtils.isEmpty(produtos));
	}
	
	@Test
	public void recuperarProdutos(){
		criarProdutoQualquer();
		List<Produto> produtos = produtoService.recuperarProdutos();
		Assert.assertFalse(CollectionUtils.isEmpty(produtos));
	}
	
	@Test
	public void recuperarProdutosComImagens(){
		Produto produto = criarProdutoQualquer();
		Assert.assertFalse(CollectionUtils.isEmpty(produto.getImagens()));
		
		List<Produto> produtos = produtoService.recuperarProdutosComImagens();
		Assert.assertFalse(CollectionUtils.isEmpty(produtos));
		Assert.assertFalse(CollectionUtils.isEmpty(produtos.get(0).getImagens()));
		Assert.assertNotNull(produtos.get(0).getImagens().get(0).getId());
	}
	
	@Test
	public void recuperarProdutoComImagem(){
		Produto produto = criarProdutoQualquer();
		Assert.assertFalse(CollectionUtils.isEmpty(produto.getImagens()));

		produto = produtoService.recuperarProdutoComImagem(produto.getId());
		Assert.assertFalse(CollectionUtils.isEmpty(produto.getImagens()));
		Assert.assertNotNull(produto.getImagens().get(0).getId());
	}
	
	@Test
	public void recuperarProduto(){
		Produto produto = criarProdutoQualquer();
		Produto produtoSalvo = produtoService.recuperarProduto(produto.getId());
		Assert.assertNotNull(produtoSalvo);
	}

	@Test
	public void recuperarProdutosFilhos(){
		Produto produtoPai = criarProdutoQualquer();
		Produto produtoFilho = criarProdutoFilho(produtoPai);
		List<Produto> produtos = produtoService.recuperarProdutosFilhos(produtoPai.getId());
		produtoService.recuperarProdutos();
		Assert.assertFalse(CollectionUtils.isEmpty(produtos));
		Assert.assertEquals(produtoFilho, produtos.get(0));
	}

	@Test
	public void recuperarImagensProduto(){
		Produto produto = criarProdutoQualquer();
		Assert.assertFalse(CollectionUtils.isEmpty(produto.getImagens()));

		List<ImagemProduto> imagens = produtoService.recuperarImagensProduto(produto.getId());
		Assert.assertFalse(CollectionUtils.isEmpty(imagens));
	}

	private Produto criarProdutoFilho(final Produto produtoPai) {
		String nome = "Panela de pressão";
		String descricao = "Panela de pressão";
		Produto produtoFilho = criarProduto(produtoPai, nome, descricao, ImagemTipo.PNG);
		return produtoService.salvarProduto(produtoFilho);
	}

	private Produto criarProdutoQualquer() {
		String nome = "Panela de pressão 4 litros";
		String descricao = "Panela de pressão de 4 litros Tramontina";
		Produto produto = criarProduto(null, nome, descricao, ImagemTipo.JPG);
		
		return produtoService.salvarProduto(produto);
	}

	private Produto criarProduto(Produto produtoPai, String nome, String descricao, ImagemTipo tipoImagem) {
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setDescricao(descricao);
		ImagemProduto img = new ImagemProduto();
		img.setProduto(produto);
		img.setTipo(tipoImagem);
		List<ImagemProduto> imagens = Arrays.asList(img);
		produto.setImagens(imagens);
		produto.setProdutoPai(produtoPai);
		return produto;
	}
	
}
