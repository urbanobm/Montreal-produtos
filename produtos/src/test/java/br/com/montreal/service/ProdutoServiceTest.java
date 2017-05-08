package br.com.montreal.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import br.com.montreal.entity.ImagemProduto;
import br.com.montreal.entity.ImagemTipo;
import br.com.montreal.entity.Produto;


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
		Produto produtoFilho = criarProdutoQualquer();
		Produto produtoPai = criarProdutoPai(produtoFilho);
		List<Produto> produtos = produtoService.recuperarProdutosFilhos(produtoPai.getId());
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

	private Produto criarProdutoPai(Produto produtoFilho) {
		String nome = "Panela de pressão";
		String descricao = "Panela de pressão";
		Produto produtoPai = criarProduto(nome, descricao, ImagemTipo.PNG);
		
		produtoPai = produtoService.salvarProduto(produtoPai);
		produtoFilho.setProdutoPai(produtoPai);
		produtoFilho = produtoService.salvarProduto(produtoFilho);
		
		return produtoService.recuperarProduto(produtoPai.getId());
	}

	private Produto criarProdutoQualquer() {
		String nome = "Panela de pressão 4 litros";
		String descricao = "Panela de pressão de 4 litros Tramontina";
		Produto produto = criarProduto(nome, descricao, ImagemTipo.JPG);
		
		return produtoService.salvarProduto(produto);
	}

	private Produto criarProduto(String nome, String descricao, ImagemTipo tipoImagem) {
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setDescricao(descricao);
		ImagemProduto img = new ImagemProduto();
		img.setProduto(produto);
		img.setTipo(tipoImagem);
		List<ImagemProduto> imagens = Arrays.asList(img);
		produto.setImagens(imagens);
		return produto;
	}
	
}
