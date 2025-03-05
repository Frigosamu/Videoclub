package org.iesbelen.videoclub.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesbelen.videoclub.domain.Pelicula;
import org.iesbelen.videoclub.repository.PeliculaCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PeliculaCustomRepositoryJPQLImpl implements PeliculaCustomRepository {

    @Autowired
    private EntityManager em;

    @Override
    public List<Pelicula> queryCustomPelicula(Optional<String> ordenarOptional) {
        StringBuilder queryBuilder = new StringBuilder("SELECT p FROM Pelicula p");

        if (ordenarOptional.isPresent()) {
            if ("asc".equalsIgnoreCase(ordenarOptional.get())) {
                queryBuilder.append(" ").append("ORDER BY p.titulo ASC");
            } else if ("desc".equalsIgnoreCase(ordenarOptional.get())) {
                queryBuilder.append(" ").append("ORDER BY p.titulo DESC");
            }
        }

        Query query = em.createQuery(queryBuilder.toString());
        return query.getResultList();
    }

    @Override
    public List<Pelicula> queryCustomPelicula(Optional<String> buscarOptional, Optional<String> ordenarOptional) {
        StringBuilder queryBuilder = new StringBuilder("SELECT p FROM Pelicula p");

        if (buscarOptional.isPresent()) {
            queryBuilder.append(" ").append("WHERE p.titulo like :titulo");
        }

        if (ordenarOptional.isPresent()) {
            if ("asc".equalsIgnoreCase(ordenarOptional.get())) {
                queryBuilder.append(" ").append("ORDER BY p.titulo ASC");
            } else if ("desc".equalsIgnoreCase(ordenarOptional.get())) {
                queryBuilder.append(" ").append("ORDER BY p.titulo DESC");
            }
        }

        Query query = em.createQuery(queryBuilder.toString());
        if (buscarOptional.isPresent()) {
            query.setParameter("titulo", "%" + buscarOptional.get() + "%");
        }

        return query.getResultList();
    }
}
