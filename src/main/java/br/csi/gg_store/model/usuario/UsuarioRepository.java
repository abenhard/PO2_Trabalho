package br.csi.gg_store.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario getById(Long id);

    public Usuario findByLogin(String login);
    public Usuario findByCpf(String login);
    public Usuario findByLoginOrCpf(String login, String cpf);

}
