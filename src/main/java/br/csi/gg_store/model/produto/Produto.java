package br.csi.gg_store.model.produto;

import br.csi.gg_store.model.venda.Carrinho;
import br.csi.gg_store.model.venda.Produto_Carrinho;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Column(name = "descricao")
    private String descricao;

    @NotBlank
    @Column(name="precobase")
    private BigDecimal precoBase;

    @ManyToOne(cascade = CascadeType.PERSIST) // Ajuste o cascade type conforme sua necessidade
    @JoinColumn(name = "idmarca")
    private Marca marca;

    @OneToMany(mappedBy = "produto") // Correção: mapeamento corrigido para 'produtosCarrinho'
    @JsonIgnore
    private Set<Produto_Carrinho> produtosCarrinho = new HashSet<>();

    @ManyToMany(mappedBy = "produtos")
    private Set<Categoria> categorias = new HashSet<>();
}
