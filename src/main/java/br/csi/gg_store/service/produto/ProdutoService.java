package br.csi.gg_store.service.produto;

import br.csi.gg_store.model.produto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final MarcaRepository marcaRepository;
    private final CategoriaService categoriaService;

    public ProdutoService(ProdutoRepository repository, MarcaRepository marcaRepository, CategoriaService categoriaService){
        this.repository = repository;
        this.marcaRepository = marcaRepository;
        this.categoriaService = categoriaService;
    }

    public void cadastrar(List<ProdutoDTO> produtoDTOS)
    {
        for(ProdutoDTO produtoDTO: produtoDTOS) {
            Produto produto = new Produto();

            produto.setNome(produtoDTO.getNome());
            produto.setDescricao(produtoDTO.getDescricao());
            produto.setPrecoBase(produtoDTO.getPrecoBase());

            Marca marca = marcaRepository.getMarcaByNome(produtoDTO.getMarca()).get(0);

            produto.setMarca(marca);

            Set<Categoria> categorias = new HashSet<>();

            for (String categoria : produtoDTO.getCategorias()) {
                categorias.add(categoriaService.getCategoriaPorNome(categoria).get(0));
            }
            produto.setCategorias(categorias);

            Set<Produto> produtos = new HashSet<>();
            produtos.add(produto);

            for (Categoria categoria : categorias) {
                categoria.setProdutos(produtos);
            }


            this.repository.save(produto);
        }
    }

    public List<Produto> listar(){
        return this.repository.findAll();
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
