package br.com.montreal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.montreal.entity.ImagemProduto;
import br.com.montreal.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT p FROM Produto p JOIN FETCH p.imagens imgs")
	List<Produto> recuperarProdutosComImagem();

	@Query("SELECT p FROM Produto p JOIN FETCH p.imagens imgs WHERE p.id = :id")
	Produto recuperarProdutoComImagem(@Param("id") Long id);

	@Query("SELECT p FROM Produto p LEFT JOIN p.produtoPai pp WHERE pp.id = :idPai")
	List<Produto> recuperarProdutosFilhos(@Param("idPai") Long idPai);

	@Query("SELECT img FROM ImagemProduto img WHERE img.produto.id = :id")
	List<ImagemProduto> recuperarImagensProduto(@Param("id") Long id);
	
}
