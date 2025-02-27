package org.iesbelen.videoclub.repository;

import org.iesbelen.videoclub.domain.Pelicula;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaCustomRepository {
    public List<Pelicula> queryCustomPelicula(Optional<String> buscarOptional, Optional<String> ordenarOptional);
}
