package br.csi.gg_store.controller.venda;

import br.csi.gg_store.model.venda.Carrinho;
import br.csi.gg_store.model.venda.CarrinhoDTO;
import br.csi.gg_store.model.venda.Produto_CarrinhoDTO;
import br.csi.gg_store.model.venda.Produto_Carrinho;
import br.csi.gg_store.service.venda.CarrinhoService;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService service;

    public CarrinhoController(CarrinhoService service) {
        this.service = service;
    }

    @GetMapping
    public Set<CarrinhoDTO> findAll() {
        return this.service.findAll();
    }

    @PostMapping("/adicionarProduto")
    public ResponseEntity<String> adicionarProdutoAoCarrinho(@RequestBody @Valid Produto_CarrinhoDTO produtoCarrinhoDTO) {
        if(produtoCarrinhoDTO.getQuantidade()<=0)
        {
            return ResponseEntity.ok("Quantidade do Produto deve ser maior que 0.");
        }
        else {
            this.service.adicionarProdutoAoCarrinho(produtoCarrinhoDTO);
            return ResponseEntity.ok("Produto adicionado ao carrinho com sucesso.");
        }
    }

    @DeleteMapping("/removerProduto")
    @Transactional
    public ResponseEntity<String> removerProdutoDoCarrinho(@RequestBody @Valid Produto_CarrinhoDTO produtoCarrinhoDTO) {
        this.service.removerProdutoDoCarrinho(produtoCarrinhoDTO);
        return ResponseEntity.ok("Produto removido do carrinho com sucesso.");
    }

    @GetMapping("/{carrinhoId}")
    public ResponseEntity<CarrinhoDTO> listarProdutosDoCarrinho(@PathVariable Long carrinhoId) {

        CarrinhoDTO carrinhoDTO = this.service.findCarrinho(carrinhoId);

        if (carrinhoDTO != null) {
            return ResponseEntity.ok(carrinhoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


