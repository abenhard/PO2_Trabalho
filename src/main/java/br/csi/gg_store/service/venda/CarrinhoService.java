package br.csi.gg_store.service.venda;


import br.csi.gg_store.model.venda.Carrinho;
import br.csi.gg_store.model.venda.CarrinhoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CarrinhoService {

    private final CarrinhoRepository repository;

    public CarrinhoService(CarrinhoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Carrinho carrinho)
    {
        this.repository.save(carrinho);
    }
    public Optional<Carrinho> findCarrinho(Long id)
    {
        return  this.repository.findById(id);
    }
}