package br.csi.gg_store.model.venda;

import br.csi.gg_store.model.produto.Produto;
import br.csi.gg_store.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="carrinhos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="precototal")
    private BigDecimal precoTotal;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "idusuario")
    private Usuario usuarioCarrinho;

    @OneToMany(mappedBy = "carrinho")
    @JsonIgnore
    private Set<Produto_Carrinho> produtosCarrinho = new HashSet<>();
}
