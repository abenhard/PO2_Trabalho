package br.csi.gg_store.model.endereco;

import br.csi.gg_store.model.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="enderecos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String complemento;

    @NotBlank
    private String bairro;

    @Size(min = 8, max =9, message = "CEP inválido")
    private String cep;

    @NotBlank
    @Column(nullable = false)
    private String numero;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "idcidade")
    private Cidade cidade;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;
}
