package br.com.montreal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.montreal.entity.ImagemProduto;
import br.com.montreal.entity.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

	@Query("SELECT p FROM Produto JOIN FETCH PRODUTO.imagens imgs")
	List<Produto> recuperarProdutosComImagem();

	@Query("SELECT p FROM Produto JOIN FETCH PRODUTO.imagens imgs WHERE p.id = :id")
	Produto recuperarProdutoComImagem(@Param("id") Long id);

	@Query("SELECT pf FROM Produto JOIN FETCH PRODUTO.produtosFilhos pf WHERE p.id = :idPai")
	List<Produto> recuperarProdutosFilhos(@Param("idPai") Long idPai);

	@Query("SELECT imgs FROM Produto JOIN FETCH PRODUTO.imagens imgs WHERE p.id = :id")
	List<ImagemProduto> recuperarImagensProduto(@Param("id") Long id);
	
}
