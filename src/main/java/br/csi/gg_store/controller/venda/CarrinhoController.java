package br.csi.gg_store.controller.venda;

import br.csi.gg_store.infra.security.TokenServiceJWT;
import br.csi.gg_store.model.usuario.DadosUsuario;
import br.csi.gg_store.model.usuario.Usuario;
import br.csi.gg_store.model.venda.carrinho.CarrinhoDTO;
import br.csi.gg_store.model.venda.produto_carrinho.Produto_CarrinhoDTO;
import br.csi.gg_store.service.usuario.UsuarioService;
import br.csi.gg_store.service.venda.CarrinhoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService service;
    private UsuarioService usuarioService;
    private TokenServiceJWT tokenService;
    public CarrinhoController(CarrinhoService service, TokenServiceJWT tokenService, UsuarioService usuarioService){
        this.service =service;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public CarrinhoDTO findByUsuario(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", ""); // Extract token from the request header
        String login = tokenService.getSubject(token);

        return this.service.findByUsuario(login);
    }

    @PostMapping("/adicionarProduto")
    public ResponseEntity<String> adicionarProdutoAoCarrinho(HttpServletRequest request, @RequestBody @Valid Produto_CarrinhoDTO produtoCarrinhoDTO) {
        if(produtoCarrinhoDTO.getQuantidade()<=0)
        {
            return ResponseEntity.ok("Quantidade do Produto deve ser maior que 0.");
        }
        else {
            String token = request.getHeader("Authorization").replace("Bearer ", ""); // Extract token from the request header
            String login = tokenService.getSubject(token);

            this.service.adicionarProdutoAoCarrinho(produtoCarrinhoDTO, login);
            return ResponseEntity.ok("Produto adicionado ao carrinho com sucesso.");
        }
    }

    @DeleteMapping("/removerProduto")
    @Transactional
    public ResponseEntity<String> removerProdutoDoCarrinho(HttpServletRequest request, @RequestBody @Valid Produto_CarrinhoDTO produtoCarrinhoDTO) {
        if(produtoCarrinhoDTO.getQuantidade()<=0)
        {
            return ResponseEntity.ok("Quantidade do Produto deve ser maior que 0.");
        }
        else {
            String token = request.getHeader("Authorization").replace("Bearer ", ""); // Extract token from the request header
            String login = tokenService.getSubject(token);

            this.service.removerProdutoDoCarrinho(produtoCarrinhoDTO, login);
            return ResponseEntity.ok("Produto removido do carrinho com sucesso.");
        }
    }
    @DeleteMapping("/removerProduto/{id}")
    @Transactional
    public ResponseEntity<String> removerProdutoDoCarrinho(HttpServletRequest request, @RequestBody @Valid Produto_CarrinhoDTO produtoCarrinhoDTO, @PathVariable Long id) {
        if(produtoCarrinhoDTO.getQuantidade()<=0)
        {
            return ResponseEntity.ok("Quantidade do Produto deve ser maior que 0.");
        }
        else {
            DadosUsuario usuario = this.usuarioService.findUsuario(id);
            this.service.removerProdutoDoCarrinho(produtoCarrinhoDTO, usuario.login());
            return ResponseEntity.ok("Produto removido do carrinho com sucesso.");
        }
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


