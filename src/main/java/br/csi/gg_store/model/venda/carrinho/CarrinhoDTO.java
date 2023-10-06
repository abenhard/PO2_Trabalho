package br.csi.gg_store.model.venda.carrinho;

import br.csi.gg_store.model.produto.Produto;
import br.csi.gg_store.model.produto.ProdutoDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class CarrinhoDTO {
    private String nomeUsuario;
    private Long idCarrinho;
    private BigDecimal precoTotal;
    private Set<ProdutoDTO> produtos;

    public CarrinhoDTO(String nomeUsuario, Long idCarrinho, BigDecimal precoTotal, Set<ProdutoDTO> produtos) {
        this.nomeUsuario = nomeUsuario;
        this.idCarrinho = idCarrinho;
        this.precoTotal = precoTotal;
        this.produtos = produtos;
    }
}
