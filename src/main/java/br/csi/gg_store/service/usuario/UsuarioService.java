package br.csi.gg_store.service.usuario;

import br.csi.gg_store.model.usuario.DadosUsuario;
import br.csi.gg_store.model.usuario.Usuario;
import br.csi.gg_store.model.usuario.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public Usuario cadastrar(Usuario usuario){
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        this.repository.save(usuario);
        return usuario;
    }
    public DadosUsuario findUsuario(Long id)
    {
        Usuario usuario = this.repository.getReferenceById(id);
        return  new DadosUsuario(usuario);
    }
    public DadosUsuario findByLoginOrCpf(String login, String cpf)
    {
        Usuario usuario = this.repository.findByLoginOrCpf(login, cpf);

        return new DadosUsuario(usuario);
    }
    public List<DadosUsuario> findAllUsuarios(){
        return this.repository.findAll().stream().map(DadosUsuario::new).toList();
    }
}