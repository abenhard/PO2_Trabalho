package br.csi.gg_store.service.venda;

import br.csi.gg_store.infra.exceptions.CarrinhoNotFoundException;
import br.csi.gg_store.infra.exceptions.ProdutoNotFoundException;
import br.csi.gg_store.infra.exceptions.QuantidadeInvalidaException;
import br.csi.gg_store.model.produto.*;
import br.csi.gg_store.model.venda.*;
import jakarta.persistence.OptimisticLockException;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarrinhoService {

    private final CarrinhoRepository repository;
    private final Produto_CarrinhoRepository produtoCarrinhoRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(CarrinhoRepository repository, Produto_CarrinhoRepository produtoCarrinhoRepository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.produtoCarrinhoRepository = produtoCarrinhoRepository;
        this.produtoRepository = produtoRepository;
    }

    public Set<CarrinhoDTO> findAll() {
        List<Carrinho> carrinhos = this.repository.findAll();
        return carrinhos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toSet());
    }

    private CarrinhoDTO convertToDto(Carrinho carrinho) {
        Set<ProdutoDTO> produtosDto = carrinho.getProdutosCarrinho().stream()
                .map(produtoCarrinho -> {
                    Produto produto = produtoCarrinho.getProduto();

                    Set<String> categorias = new HashSet<>();
                    Set<Categoria> categoriaSet = produto.getCategorias();
                    for(Categoria categoria: categoriaSet)
                    {
                        categorias.add(categoria.getNome());
                    }

                    Marca marca = produto.getMarca();
                    String nomeMarca = marca.getNome();

                    return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPrecoBase(),categorias,nomeMarca);
                })
                .collect(Collectors.toSet());

        return new CarrinhoDTO(carrinho.getUsuarioCarrinho().getNome(), carrinho.getId(), carrinho.getPrecoTotal(), produtosDto);
    }
    public void cadastrar(Carrinho carrinho) {
        this.repository.save(carrinho);
    }

    public CarrinhoDTO findCarrinho(Long id) {

       Carrinho carrinho = this.repository.getReferenceById(id);

       CarrinhoDTO carrinhoDTO  = convertToDto(carrinho);


        return carrinhoDTO;
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void adicionarProdutoAoCarrinho(Produto_CarrinhoDTO produtoCarrinhoDTO) {
        Carrinho carrinho = this.repository.findById(produtoCarrinhoDTO.getIdCarrinho())
                .orElseThrow(() -> new CarrinhoNotFoundException("Carrinho não encontrado"));

        Produto produto = this.produtoRepository.findById(produtoCarrinhoDTO.getIdProduto())
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));

        Produto_Carrinho produtoCarrinho = this.produtoCarrinhoRepository
                .findProduto_CarrinhoByCarrinhoIdAndProdutoId(produtoCarrinhoDTO.getIdCarrinho(), produtoCarrinhoDTO.getIdProduto())
                .orElse(null);
        if (produtoCarrinho == null) {
            produtoCarrinho = new Produto_Carrinho();
            produtoCarrinho.setCarrinho(carrinho);
            produtoCarrinho.setProduto(produto);
            produtoCarrinho.setQuantidade(produtoCarrinhoDTO.getQuantidade());
        } else {
            produtoCarrinho.setQuantidade(produtoCarrinho.getQuantidade() + produtoCarrinhoDTO.getQuantidade());
        }

        this.produtoCarrinhoRepository.save(produtoCarrinho);

        // Atualizar o preço total do carrinho após adicionar um produto.
        BigDecimal precoTotal = carrinho.getProdutosCarrinho().stream()
                .map(pc -> pc.getProduto().getPrecoBase().multiply(BigDecimal.valueOf(pc.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        carrinho.setPrecoTotal(precoTotal);

        this.repository.save(carrinho);
    }
    @Transactional
    public void removerProdutoDoCarrinho(Produto_CarrinhoDTO produtoCarrinhoDTO) {
        try {
            Carrinho carrinho = this.repository.findById(produtoCarrinhoDTO.getIdCarrinho())
                    .orElseThrow(() -> new CarrinhoNotFoundException("Carrinho não encontrado"));
            Produto produto = this.produtoRepository.findById(produtoCarrinhoDTO.getIdProduto())
                    .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));
            Produto_Carrinho produtoCarrinho = produtoCarrinhoRepository
                    .findProduto_CarrinhoByCarrinhoIdAndProdutoId(produtoCarrinhoDTO.getIdCarrinho(), produtoCarrinhoDTO.getIdProduto())
                    .orElseThrow(() -> new ProdutoNotFoundException("Produto no carrinho não encontrado"));

            int quantidadeRemover = produtoCarrinhoDTO.getQuantidade();

            int novaQuantidade = Math.max(produtoCarrinho.getQuantidade() - quantidadeRemover, 0);


            if (novaQuantidade>0) {
                produtoCarrinho.setQuantidade(novaQuantidade);
                this.produtoCarrinhoRepository.save(produtoCarrinho);

            } else {
                carrinho.getProdutosCarrinho().remove(produtoCarrinho);
                produto.getProdutosCarrinho().remove(produtoCarrinho);
                this.repository.save(carrinho);  // Update the carrinho to remove the produtoCarrinho reference
                this.produtoRepository.save(produto);
                this.produtoCarrinhoRepository.delete(produtoCarrinho);
            }

            // Update total price
            carrinho.setPrecoTotal(calculateTotalPrice(carrinho));


        } catch (OptimisticLockException e) {
            e.printStackTrace();
            // Handle the exception, log it, or perform necessary actions
        }
    }

    private BigDecimal calculateTotalPrice(Carrinho carrinho) {
        return carrinho.getProdutosCarrinho().stream()
                .map(pc -> pc.getProduto().getPrecoBase().multiply(BigDecimal.valueOf(pc.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

