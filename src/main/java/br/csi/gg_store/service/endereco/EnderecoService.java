package br.csi.gg_store.service.endereco;

import br.csi.gg_store.model.endereco.Endereco;
import br.csi.gg_store.model.endereco.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository){ this.repository = repository;}

    public void  cadastrar(Endereco endereco)
    {
        this.repository.save(endereco);
    }

    public List<Endereco> listar(){
        return this.repository.findAll();
    }

    public Endereco findById(Long id){
        return this.repository.findById(id).get();
    }
    public Endereco findByCep(String cep){ return this.repository.findByCep(cep).get();}
    public void atualizar(Endereco endereco){
       Endereco enderecoSalvar = this.repository.getReferenceById(endereco.getId());
        enderecoSalvar.setRua(endereco.getRua());
        enderecoSalvar.setBairro(endereco.getBairro());
        enderecoSalvar.setCep(endereco.getCep());
        enderecoSalvar.setComplemento(endereco.getComplemento());
        enderecoSalvar.setCidade(endereco.getCidade());
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }
}
