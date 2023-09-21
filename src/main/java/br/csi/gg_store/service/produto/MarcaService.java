package br.csi.gg_store.service.produto;


import br.csi.gg_store.model.produto.MarcaRepository;
import br.csi.gg_store.model.produto.Marca;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {
    private final MarcaRepository repository;

    public MarcaService(MarcaRepository repository){ this.repository = repository;}

    public void  cadastrar(Marca marca)
    {
        this.repository.save(marca);
    }

    public List<Marca> listar(){
        return this.repository.findAll();
    }

    public Marca findById(Long id){
        return this.repository.findById(id).get();
    }
    public List<Marca> getMarcaPorNome(String nome)
    {
        return this.repository.getMarcaByNome(nome);
    }
    public void atualizar(Marca marca){
       Marca marcaSalvar = this.repository.getReferenceById(marca.getId());
        marcaSalvar.setNome(marca.getNome());
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }

}
