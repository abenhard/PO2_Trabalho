package br.csi.gg_store.model.venda.produto_venda;

import br.csi.gg_store.model.produto.Produto;
import br.csi.gg_store.model.venda.Venda;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="produto_venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto_Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idproduto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "idvenda")
    private Venda venda;

    @Column(name = "quantidade", nullable = false)
    @NotNull
    @Min(value = 1, message = "Quantidade deve ser maior ou iqual 1")
    private Integer quantidade = 0;
}
