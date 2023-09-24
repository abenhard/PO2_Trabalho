package br.csi.gg_store.model.venda;

import br.csi.gg_store.model.produto.Produto;
import br.csi.gg_store.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private double precoTotal;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private Set<Produto> produtos = new HashSet<>();
}
