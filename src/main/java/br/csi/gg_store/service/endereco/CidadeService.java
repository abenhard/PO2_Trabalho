package br.csi.gg_store.service.endereco;

import br.csi.gg_store.model.endereco.Cidade;
import br.csi.gg_store.model.endereco.CidadeRepository;
import br.csi.gg_store.model.endereco.UF;
import br.csi.gg_store.model.endereco.UFRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    private final CidadeRepository repository;
    private final UFRepository ufRepository;

    public CidadeService(CidadeRepository repository, UFRepository ufRepository){
        this.repository = repository;
        this.ufRepository=ufRepository;
    }

    public void salvar(Cidade cidade)
    {
        this.repository.save(cidade);
    }

    public List<Cidade> listar(){
        return this.repository.findAll();
    }
    public Cidade findById(Long id){
        return this.repository.findById(id).get();
    }
    public Cidade getCidadePorNomeOuUf(String nome, UF uf)
    {
        return this.repository.getCidadeByNomeOrUf(nome, uf);
    }
    public void atualizar(Cidade cidade){
        Cidade cidadeAtualizar = this.repository.getReferenceById(cidade.getId());
        cidadeAtualizar.setNome(cidade.getNome());
        cidadeAtualizar.setUf(cidade.getUf());
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }
    @Transactional
    public Cidade getOrCreateCidade(String nome, UF uf) {
        Optional<Cidade> existingCidade = Optional.ofNullable(this.repository.getCidadeByNomeOrUf(nome, uf));
        if (existingCidade.isPresent()) {
            return existingCidade.get(); // Return the existing Cidade if found
        } else {
            Cidade newCidade = new Cidade();
            newCidade.setNome(nome);
            newCidade.setUf(uf);
            return this.repository.save(newCidade); // Create and return a new Cidade
        }
    }
}
