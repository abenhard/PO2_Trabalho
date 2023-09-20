package br.csi.gg_store.controller.endereco;


import br.csi.gg_store.model.endereco.UF;

import br.csi.gg_store.service.endereco.UFService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/uf")
public class UFController {
    private final UFService service;
    public UFController(UFService service){this.service = service;}

    @PostMapping("/print-json")
    public void printJSon(@RequestBody String json){System.out.println(json);}

    @GetMapping
    public ResponseEntity<List<UF>> listar(){
        return ResponseEntity.ok(this.service.listar());
    }


    @GetMapping("/{id}")
    public UF uf(@PathVariable Long id){ return this.service.findById(id);}


    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid List<UF> ufs, UriComponentsBuilder uriBuilder)
    {
        for (UF uf : ufs) {
            this.service.cadastrar(uf);
        }
        URI uri = uriBuilder.path("/uf/{id}").buildAndExpand(ufs.get(0).getId()).toUri();
        return ResponseEntity.created(uri).body(ufs);
    }

}
