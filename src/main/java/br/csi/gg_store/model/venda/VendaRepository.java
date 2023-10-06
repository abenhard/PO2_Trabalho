package br.csi.gg_store.model.venda;

import br.csi.gg_store.model.venda.produto_carrinho.Produto_Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
