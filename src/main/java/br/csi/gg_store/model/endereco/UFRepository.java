package br.csi.gg_store.model.endereco;

import br.csi.gg_store.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UFRepository extends JpaRepository<UF, Long> {
    public UF getById(Long id);
    public List<UF> getUFByNome(String nome);
    public Optional<UF> findById(Long id);
}