package br.csi.gg_store.controller.endereco;

import br.csi.gg_store.model.endereco.Cidade;
import br.csi.gg_store.model.endereco.Endereco;
import br.csi.gg_store.model.endereco.EnderecoDTO;
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
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoService service;
    public EnderecoController(EnderecoService service){this.service = service;}

    @PostMapping("/print-json")
    public void printJSon(@RequestBody String json){System.out.println(json);}

    @GetMapping("/{id}")
    public Endereco endereco(@PathVariable Long id){ return this.service.findById(id);}

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listar(){
        return ResponseEntity.ok(this.service.listar());
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid List<EnderecoDTO> enderecosDto, UriComponentsBuilder uriBuilder)
    {
         this.service.cadastrar(enderecosDto);

        URI uri = uriBuilder.path("/cidade/{id}").buildAndExpand(enderecosDto.get(0).getId()).toUri();
        return ResponseEntity.created(uri).body(enderecosDto);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid Endereco endereco){
        try {
            this.service.atualizar(endereco);
            return ResponseEntity.ok().body(endereco);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Falha ao atualizar Endereco");
        }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        try {
            this.service.excluir(id);
            return ResponseEntity.ok().body("Endereco Deletada com Sucesso");
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
}
