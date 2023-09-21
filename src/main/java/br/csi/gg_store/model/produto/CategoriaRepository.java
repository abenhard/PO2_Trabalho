package br.csi.gg_store.model.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public Categoria getById(Long id);
    public List<Categoria> getCategoriaByNome(String nome);
    public Optional<Categoria> findById(Long id);
}
