package br.csi.gg_store.controller.endereco;

import br.csi.gg_store.model.endereco.Cidade;
import br.csi.gg_store.model.endereco.Endereco;
import br.csi.gg_store.model.endereco.UF;
import br.csi.gg_store.service.endereco.EnderecoService;
import br.csi.gg_store.service.endereco.UFService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Endereco")
public class EnderecoController {
    private final EnderecoService service;
    public EnderecoController(EnderecoService service){this.service = service;}

    @PostMapping("/print-json")
    public void printJSon(@RequestBody String json){System.out.println(json);}

    @GetMapping("/{id}")
    public Endereco endereco(@PathVariable Long id){ return this.service.findById(id);}

    @GetMapping
    public ResponseEntity<List<Endereco>> listar(){
        return ResponseEntity.ok(this.service.listar());
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid List<Endereco> enderecos, UriComponentsBuilder uriBuilder)
    {
        for (Endereco endereco : enderecos) {
            this.service.cadastrar(endereco);
        }
        URI uri = uriBuilder.path("/cidade/{id}").buildAndExpand(enderecos.get(0).getId()).toUri();
        return ResponseEntity.created(uri).body(enderecos);
    }
}
