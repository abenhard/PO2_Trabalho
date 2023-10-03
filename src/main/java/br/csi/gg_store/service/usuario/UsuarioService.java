package br.csi.gg_store.service.usuario;

import br.csi.gg_store.model.usuario.DadosUsuario;
import br.csi.gg_store.model.usuario.Usuario;
import br.csi.gg_store.model.usuario.UsuarioRepository;
import br.csi.gg_store.model.venda.Carrinho;
import br.csi.gg_store.service.venda.CarrinhoService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final CarrinhoService carrinhoService;
    public UsuarioService(UsuarioRepository repository, CarrinhoService carrinhoService){
        this.repository = repository;
        this.carrinhoService = carrinhoService;
    }

    public Usuario cadastrar(Usuario usuario){
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

        Carrinho carrinho = new Carrinho();
        carrinho.setUsuarioCarrinho(usuario);
        carrinho.setPrecoTotal(0.0);
        this.repository.save(usuario);
        carrinhoService.cadastrar(carrinho);
        return usuario;
    }
    public void atualizar(Usuario usuario){
        Usuario usuarioAtualizar = this.repository.getReferenceById(usuario.getId());
        usuarioAtualizar.setNome(usuario.getNome());
        usuarioAtualizar.setTelefone(usuario.getTelefone());
        usuarioAtualizar.setCpf(usuario.getCpf());
        usuarioAtualizar.setDataNascimento(usuario.getDataNascimento());
        usuarioAtualizar.setSenha(usuario.getSenha());
    }
    public DadosUsuario findUsuario(Long id)
    {
        Usuario usuario = this.repository.getReferenceById(id);
        return  new DadosUsuario(usuario);
    }
    public Usuario findByLogin(String login)
    {
        Usuario usuario = this.repository.findByLogin(login);

        if(usuario !=null){ return usuario;}
        else {return null;}
    }
    public List<DadosUsuario> findAllUsuarios(){
        return this.repository.findAll().stream().map(DadosUsuario::new).toList();
    }

    public void excluir(Long id){
        this.repository.deleteById(id);
    }
}
