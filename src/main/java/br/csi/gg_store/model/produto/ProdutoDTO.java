package br.csi.gg_store.model.produto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class ProdutoDTO{
    private String id;
    private String nome;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal precoBase;
    private Set<String> categorias;
    private String marca;
}
