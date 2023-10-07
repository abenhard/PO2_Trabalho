package br.csi.gg_store.model.venda.produto_carrinho;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Produto_CarrinhoDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idCarrinho;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idProduto;
    private String nomeProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;

    public Produto_CarrinhoDTO(){}
    public Produto_CarrinhoDTO(Long idProduto, Integer quantidade){
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

}
