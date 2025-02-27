package org.iesbelen.videoclub.repository;

import org.iesbelen.videoclub.domain.Categoria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaCustomRepository {

    public List<Categoria> queryCustomCategoria(Optional<String> buscarOptional, Optional<String> ordenarOptional);
}
