package br.csi.gg_store.controller.compra;


import br.csi.gg_store.infra.security.TokenServiceJWT;
import br.csi.gg_store.model.compra.Compra;
import br.csi.gg_store.model.compra.CompraDTO;
import br.csi.gg_store.model.usuario.Usuario;
import br.csi.gg_store.service.compra.CarrinhoService;
import br.csi.gg_store.service.compra.CompraService;
import br.csi.gg_store.service.usuario.UsuarioService;
import br.csi.gg_store.service.usuario.endereco.EnderecoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/compra")
public class CompraController {

    private final CompraService service;
    private final CarrinhoService carrinhoService;
    private final EnderecoService enderecoService;
    private UsuarioService usuarioService;
    private TokenServiceJWT tokenService;


    public CompraController(CompraService service, CarrinhoService carrinhoService, EnderecoService enderecoService,UsuarioService usuarioService, TokenServiceJWT tokenService)
    {
        this.service = service;
        this.carrinhoService = carrinhoService;
        this.enderecoService = enderecoService;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }
    @GetMapping
    public ResponseEntity<Set<CompraDTO>> adicionarProdutoAoCarrinho(HttpServletRequest request)
    {
        String token = request.getHeader("Authorization").replace("Bearer ", ""); // Extract token from the request header
        String login = tokenService.getSubject(token);

        Usuario usuario = this.usuarioService.findByLogin(login);

        Set<CompraDTO> compras = this.service.getCompraByUsuario(usuario);

        return ResponseEntity.ok(compras);

    }
    @PostMapping("/{idEndereco}")
    public ResponseEntity<String> adicionarProdutoAoCarrinho(HttpServletRequest request, @PathVariable Long idEndereco) {
        String token = request.getHeader("Authorization").replace("Bearer ", ""); // Extract token from the request header
        String login = tokenService.getSubject(token);

        Usuario usuario = this.usuarioService.findByLogin(login);

        if(this.enderecoService.findByUsuarioAndId(usuario, idEndereco))
        {
            this.service.salvarCompra(usuario, idEndereco);

            return ResponseEntity.ok("Compra cadastrada com sucesso!");
        }
        else{
            return ResponseEntity.badRequest().body("Endereço Não encontrado!");
        }

    }
}
