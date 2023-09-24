package br.csi.gg_store.model.endereco;

import br.csi.gg_store.model.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;


public record CidadeDTO(String nome, Long iduf){
    public Long getIduf(){return iduf;}

}
