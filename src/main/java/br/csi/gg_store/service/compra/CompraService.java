package br.csi.gg_store.service.compra;

import br.csi.gg_store.model.compra.Compra;
import br.csi.gg_store.model.compra.CompraDTO;
import br.csi.gg_store.model.compra.CompraRepository;
import br.csi.gg_store.model.compra.StatusCompra;
import br.csi.gg_store.model.compra.carrinho.Carrinho;
import br.csi.gg_store.model.compra.carrinho.CarrinhoRepository;
import br.csi.gg_store.model.compra.produto_Compra.Produto_Compra;
import br.csi.gg_store.model.compra.produto_Compra.Produto_CompraDTO;
import br.csi.gg_store.model.compra.produto_Compra.Produto_CompraRepository;
import br.csi.gg_store.model.compra.produto_carrinho.Produto_Carrinho;
import br.csi.gg_store.model.usuario.Usuario;
import br.csi.gg_store.model.usuario.endereco.Endereco;
import br.csi.gg_store.service.usuario.endereco.EnderecoService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CompraService {

    private final CompraRepository repository;
    private final CarrinhoRepository carrinhoRepository;
    private final Produto_CompraRepository produtoCompraRepository;
    private final EnderecoService enderecoService;

    public CompraService(CompraRepository repository, EnderecoService enderecoService, Produto_CompraRepository produtoCompraRepository, CarrinhoRepository carrinhoRepository)
    {
        this.repository = repository;
        this.enderecoService = enderecoService;
        this.produtoCompraRepository = produtoCompraRepository;
        this.carrinhoRepository = carrinhoRepository;
    }
    public Set<CompraDTO> getCompraByUsuario(Usuario usuario)
    {
       Set<Compra> compras = this.repository.findCompraByUsuarioVenda(usuario);
       Set<CompraDTO> compraDTOS = new HashSet<>();

       for(Compra compra: compras)
       {
           CompraDTO compraDTO = new CompraDTO();

           compraDTO.setStatusCompra(compra.getStatus());
           compraDTO.setEnderecoDTO(this.enderecoService.convertToEnderecoDTO(compra.getEnderecoCompra()));
           compraDTO.setNomeUsuario(compra.getUsuarioVenda().getNome());
           compraDTO.setProdutoCompraDTOSet(convertToProduto_CompraDTO(compra.getProdutosCompra()));

           compraDTOS.add(compraDTO);
       }
       return compraDTOS;
    }
    public void salvarCompra(Usuario usuario, Long idEndereco){
        Endereco endereco = this.enderecoService.findById(idEndereco);
        Carrinho carrinho = this.carrinhoRepository.findCarrinhoByUsuarioCarrinho(usuario);
        Set<Produto_Compra> produtoCompraSet = new HashSet<>();

        Compra compra = new Compra();

        compra.setEnderecoCompra(endereco);
        compra.setStatus(StatusCompra.aberta);
        compra.setUsuarioVenda(usuario);
        compra.setPrecoTotal(carrinho.getPrecoTotal());
        this.repository.save(compra);

        for(Produto_Carrinho produtoCarrinho: carrinho.getProdutosCarrinho())
        {
            Produto_Compra produtoCompra= new Produto_Compra();
            produtoCompra.setCompra(compra);
            produtoCompra.setProduto(produtoCarrinho.getProduto());
            produtoCompra.setQuantidade(produtoCarrinho.getQuantidade());

            produtoCompraSet.add(produtoCompra);
            this.produtoCompraRepository.save(produtoCompra);
        }

        compra.setProdutosCompra(produtoCompraSet);
    }

    public Set<Produto_CompraDTO> convertToProduto_CompraDTO(Set<Produto_Compra> produtoCompras)
    {
        Set<Produto_CompraDTO> produtoCompraDTOS = new HashSet<>();

        for(Produto_Compra produtoCompra: produtoCompras)
        {
            Produto_CompraDTO produtoCompraDTO = new Produto_CompraDTO();

            produtoCompraDTO.setProduto(produtoCompra.getProduto().getNome());
            produtoCompraDTO.setQuantidade(produtoCompra.getQuantidade());

            produtoCompraDTOS.add(produtoCompraDTO);
        }
        return produtoCompraDTOS;
    }
}
