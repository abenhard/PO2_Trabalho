package br.csi.gg_store.model.endereco;

import br.csi.gg_store.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    public Endereco getById(Long id);
    public Optional<Endereco> findById(Long id);
    public Optional<Endereco> findByCep(String cep);
    public void deleteByUsuario(Usuario usuario);
}
