package br.csi.gg_store.controller.venda;

import br.csi.gg_store.model.venda.Carrinho;
import br.csi.gg_store.service.venda.CarrinhoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService service;

    public CarrinhoController(CarrinhoService service){this.service = service;}

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Carrinho carrinho, UriComponentsBuilder uriBuilder)
    {

        this.service.cadastrar(carrinho);

        URI uri = uriBuilder.path("/carrinho/{id}").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(carrinho.getId());
    }
    @GetMapping
    public List<Carrinho> findAll(){return  this.service.findAll();}
    @GetMapping("/{id}")
    public Optional<Carrinho> findCarrinho(@PathVariable Long id){ return this.service.findCarrinho(id);}
}
