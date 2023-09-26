package br.csi.gg_store.service.endereco;

import br.csi.gg_store.model.endereco.*;
import br.csi.gg_store.model.usuario.DadosUsuario;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void salvar(String nome, Long iduf)
    {
        UF uf = ufRepository.getById(iduf);
        Cidade cidade = new Cidade();
        cidade.setNome(nome);
        cidade.setUf(uf);
        this.repository.save(cidade);
    }

    public List<CidadeDTO> listar(){
        return this.repository.findAll().stream().map(CidadeDTO::new).toList();
    }
    public Cidade findById(Long id){
        return this.repository.findById(id).get();
    }
    public Cidade getCidadePorNomeOuUf(String nome, UF uf)
    {
        return this.repository.getCidadeByNomeOrUf(nome, uf).get(0);
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
        Optional<Cidade> existingCidade = Optional.ofNullable(this.repository.getCidadeByNomeOrUf(nome, uf).get(0));
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
