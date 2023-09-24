package br.csi.gg_store.controller.produto;


import br.csi.gg_store.model.produto.Categoria;
import br.csi.gg_store.model.produto.Produto;
import br.csi.gg_store.service.endereco.EnderecoService;
import br.csi.gg_store.service.produto.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService service;
    public ProdutoController(ProdutoService service){this.service = service;}

}
