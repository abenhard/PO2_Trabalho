package br.csi.gg_store.model.venda;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto_CarrinhoDTO {
    private Long idCarrinho;
    private Long idProduto;
    private Integer quantidade;
}
