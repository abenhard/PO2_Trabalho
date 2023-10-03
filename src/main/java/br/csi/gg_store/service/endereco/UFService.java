package br.csi.gg_store.service.endereco;

import br.csi.gg_store.model.endereco.UF;
import br.csi.gg_store.model.endereco.UFRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UFService {
    private final UFRepository repository;

    public UFService(UFRepository repository){ this.repository = repository;}

    public void  cadastrar(UF uf)
    {
        this.repository.save(uf);
    }

    public List<UF> listar(){
        return this.repository.findAll();
    }

    public UF findById(Long id){
        return this.repository.findById(id).get();
    }
    public UF getUfPorNome(String nome)
    {
        return this.repository.getUFByNome(nome);
    }
    public void atualizar(UF uf){
        UF ufSalvar = this.repository.getReferenceById(uf.getId());
        ufSalvar.setNome(uf.getNome());
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }

    @Transactional
    public UF getOrCreateUf(String nome) {
        UF uf = this.repository.getUFByNome(nome);
        if (uf!=null) {
            return uf;
        } else {
            UF novaUf = new UF();
            novaUf.setNome(nome);
            return this.repository.save(novaUf);
        }
    }
}
