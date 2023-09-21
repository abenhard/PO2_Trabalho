package br.csi.gg_store.model.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public Produto getById(Long id);
    public List<Produto> getProdutoByNome(String nome);
    public Optional<Produto> findById(Long id);
}
