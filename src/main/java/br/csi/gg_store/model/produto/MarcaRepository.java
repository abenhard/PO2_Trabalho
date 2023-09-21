package br.csi.gg_store.model.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    public Marca getById(Long id);
    public List<Marca> getMarcaByNome(String nome);
    public Optional<Marca> findById(Long id);
}
