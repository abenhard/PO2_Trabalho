package br.csi.gg_store.controller.produto;



import br.csi.gg_store.model.produto.Produto;

import br.csi.gg_store.service.produto.ProdutoService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService service;
    public ProdutoController(ProdutoService service){this.service = service;}

    @GetMapping
    public List<Produto> listar(){ return this.service.listar();}

    @GetMapping("/{id}")
    public Produto findById(@PathVariable Long id){return this.service.findById(id);}


}
