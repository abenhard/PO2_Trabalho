package br.csi.gg_store.controller.endereco;

import br.csi.gg_store.model.endereco.Cidade;
import br.csi.gg_store.model.endereco.UF;
import br.csi.gg_store.service.endereco.CidadeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    private final CidadeService service;
    public CidadeController(CidadeService service){this.service = service;}

    @PostMapping("/print-json")
    public void printJSon(@RequestBody String json){System.out.println(json);}

    @GetMapping
    public ResponseEntity<List<Cidade>> listar(){
        return ResponseEntity.ok(this.service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity cidade(@PathVariable Long id){ return ResponseEntity.ok(this.service.findById(id));}

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid List<Cidade> cidades, UriComponentsBuilder uriBuilder)
    {
        for (Cidade cidade : cidades) {
            this.service.salvar(cidade);
        }
        URI uri = uriBuilder.path("/cidade/{id}").buildAndExpand(cidades.get(0).getId()).toUri();
        return ResponseEntity.created(uri).body(cidades);
    }

}
