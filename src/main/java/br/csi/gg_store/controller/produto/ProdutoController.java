package br.csi.gg_store.controller.produto;




import br.csi.gg_store.model.produto.Produto;

import br.csi.gg_store.model.produto.ProdutoDTO;
import br.csi.gg_store.service.produto.ProdutoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService service;
    public ProdutoController(ProdutoService service){this.service = service;}

    @GetMapping
    public List<Produto> getProdutos(){ return this.service.listar();}

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable Long id){return this.service.findById(id);}


    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid List<ProdutoDTO> produtos, UriComponentsBuilder uriBuilder)
    {
        this.service.cadastrar(produtos);
        URI uri = uriBuilder.path("/produtos").buildAndExpand(produtos.get(0).getId()).toUri();
        return ResponseEntity.created(uri).body(produtos);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid ProdutoDTO produtoDTO){
        try {
            this.service.atualizar(produtoDTO);
            return ResponseEntity.ok().body(produtoDTO);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Falha ao atualizar Produto");
        }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        try {
            this.service.excluir(id);
            return ResponseEntity.ok().body("Produto Deletado com Sucesso");
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
}
