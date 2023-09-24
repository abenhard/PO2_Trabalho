package br.csi.gg_store.controller.produto;


import br.csi.gg_store.model.produto.Categoria;
import br.csi.gg_store.service.endereco.EnderecoService;
import br.csi.gg_store.service.produto.CategoriaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private final CategoriaService service;
    public CategoriaController(CategoriaService service){this.service = service;}
}
