package br.csi.gg_store.controller.usuario;

import br.csi.gg_store.model.usuario.DadosUsuario;
import br.csi.gg_store.model.usuario.Usuario;
import br.csi.gg_store.service.usuario.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service){this.service =service;}

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid List<Usuario> usuarios, UriComponentsBuilder uriBuilder)
    {
        ArrayList<Usuario> u = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if(this.service.findByLoginOrCpf(usuario.getLogin(), usuario.getCpf())){
            }
            else {
                u.add(this.service.cadastrar(usuario));
            }
        }
        URI uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(u.get(0).getId()).toUri();
        return ResponseEntity.created(uri).body(u);
    }
    @GetMapping("/{id}")
    public DadosUsuario findById(@PathVariable Long id){return this.service.findUsuario(id);}

    @GetMapping
    public List<DadosUsuario> findAll(){
        return this.service.findAllUsuarios();
    }
}
