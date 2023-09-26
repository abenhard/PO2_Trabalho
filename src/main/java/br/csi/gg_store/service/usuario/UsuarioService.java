package br.csi.gg_store.service.usuario;

import br.csi.gg_store.model.endereco.Cidade;
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
    public boolean findByLoginOrCpf(String login, String cpf)
    {
        Usuario usuario = this.repository.findByLoginOrCpf(login, cpf);

        if(usuario !=null){ return true;}
        else {return false;}
    }
    public List<DadosUsuario> findAllUsuarios(){
        return this.repository.findAll().stream().map(DadosUsuario::new).toList();
    }
}
