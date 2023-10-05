package br.csi.gg_store.model.venda;

import br.csi.gg_store.model.produto.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name ="produto_carrinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto_Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idproduto")
    private Produto produto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcarrinho")
    private Carrinho carrinho;

    @Column(name = "quantidade", nullable = false)
    @NotNull
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantidade = 0;

    @Version
    private Long version;
}
