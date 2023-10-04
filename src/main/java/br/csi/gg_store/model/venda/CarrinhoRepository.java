package br.csi.gg_store.model.venda;

import br.csi.gg_store.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
