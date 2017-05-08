package br.com.montreal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.montreal.entity.ImagemProduto;
import br.com.montreal.entity.Produto;
import br.com.montreal.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto salvarProduto(Produto produto){
		return produtoRepository.save(produto);
	}
	
	public void deleteProduto(Long id){
		produtoRepository.delete(id);
	}
	
	//1. Recuperar todos os Produtos excluindo os relacionamentos;
	public List<Produto> recuperarProdutos(){
		return (List<Produto>) produtoRepository.findAll();
	}
	
	//2. Recuperar todos os Produtos incluindo um relacionamento específico (Produto ou Imagem);
	public List<Produto> recuperarProdutosComImagens(){
		return (List<Produto>) produtoRepository.recuperarProdutosComImagem();
	}
	
	//3. Igual ao nº 1 utilizando um id de produto específico;
	public Produto recuperarProdutoComImagem(Long id){
		return produtoRepository.recuperarProdutoComImagem(id);
	}
	
	//4. Igual ao nº 2 utilizando um id de produto específico;
	public Produto recuperarProduto(Long id){
		return produtoRepository.findOne(id);
	}

	//5. Recupera a coleção de produtos filhos por um id de produto específico;
	public List<Produto> recuperarProdutosFilhos(Long idPai){
		return (List<Produto>) produtoRepository.recuperarProdutosFilhos(idPai);
	}

	//6. Recupera a coleção de Imagens para um id de produto específico;
	public List<ImagemProduto> recuperarImagensProduto(Long id){
		return (List<ImagemProduto>) produtoRepository.recuperarImagensProduto(id);
	}


}
