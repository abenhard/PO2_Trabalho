package br.csi.gg_store.model.endereco;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO{
    public EnderecoDTO(String rua, String bairro, String complemento, String cep, String numero, String cidade, String login, String uf){
        this.rua =rua;
        this.bairro = bairro;
        this.complemento =complemento;
        this.cep = cep;
        this.numero= numero;
        this.cidade=cidade;
        this.login=login;
        this.uf=uf;
    }

    String id;
    String login;
    String rua;
    String bairro;
    String complemento;
    String cep;
    String numero;
    String cidade;
    String uf;
}
