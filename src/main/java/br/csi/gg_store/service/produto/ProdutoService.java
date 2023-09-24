package br.csi.gg_store.service.produto;

import br.csi.gg_store.model.produto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository){ this.repository = repository;}

    public void  cadastrar(Produto produto)
    {
        this.repository.save(produto);
    }

    public List<Produto> listar(){
        return this.repository.findAll();
    }
    public List<Produto> getProdutosByCategorias(Set<Categoria> categorias) {
        return this.repository.findByCategorias(categorias);
    }


    public Produto findById(Long id){
        return this.repository.findById(id).get();
    }
    public List<Produto> getProdutoPorNome(String nome)
    {
        return this.repository.getProdutoByNome(nome);
    }
    public void atualizar(Produto produto){
        Produto produtoSalvar = this.repository.getReferenceById(produto.getId());
        produtoSalvar.setNome(produto.getNome());
        produtoSalvar.setDescricao(produto.getDescricao());
        produtoSalvar.setPrecoBase(produto.getPrecoBase());
    }
    public void excluir(Long id){
        this.repository.deleteById(id);
    }
}
