package br.csi.gg_store.model.compra;

import br.csi.gg_store.model.usuario.endereco.Endereco;
import br.csi.gg_store.model.usuario.Usuario;
import br.csi.gg_store.model.compra.produto_venda.Produto_Venda;
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
@Table(name ="vendas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="precototal")
    private BigDecimal precoTotal;

    @Column(name= "status")
    private String status;

    @OneToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name = "idusuario")
    private Usuario usuarioVenda;

    @OneToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name = "idendereco")
    private Endereco enderecoVenda;

    @OneToMany(mappedBy = "venda")
    @JsonIgnore
    private Set<Produto_Venda> produtosVenda = new HashSet<>();
}
