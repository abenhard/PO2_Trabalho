package br.csi.gg_store.controller.produto;


import br.csi.gg_store.model.produto.Categoria;
import br.csi.gg_store.model.produto.Marca;
import br.csi.gg_store.service.endereco.EnderecoService;
import br.csi.gg_store.service.produto.CategoriaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private final CategoriaService service;
    public CategoriaController(CategoriaService service){this.service = service;}

    @PostMapping("/print-json")
    public void printJSon(@RequestBody String json){System.out.println(json);}

    @GetMapping
    public ResponseEntity<List<Categoria>> listar(){
        return ResponseEntity.ok(this.service.listar());
    }


    @GetMapping("/{id}")
    public Categoria getCategoria(@PathVariable Long id){ return this.service.findById(id);}


    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid List<Categoria> categorias, UriComponentsBuilder uriBuilder)
    {
        for (Categoria categoria : categorias) {
            this.service.cadastrar(categoria);
        }
        URI uri = uriBuilder.path("/uf/{id}").buildAndExpand(categorias.get(0).getId()).toUri();
        return ResponseEntity.created(uri).body(categorias);
    }
}
