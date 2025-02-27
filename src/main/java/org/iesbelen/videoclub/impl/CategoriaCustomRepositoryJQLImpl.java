package org.iesbelen.videoclub.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesbelen.videoclub.domain.Categoria;
import org.iesbelen.videoclub.repository.CategoriaCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaCustomRepositoryJQLImpl implements CategoriaCustomRepository {

    @Autowired
    private EntityManager em;

    @Override
    public List<Categoria> queryCustomCategoria(Optional<String> buscarOptional, Optional<String> ordenarOptional) {
        StringBuilder queryBuilder = new StringBuilder("SELECT C FROM Categoria C");

        if (buscarOptional.isPresent()) {
            queryBuilder.append(" ").append("WHERE C.nombre like :nombre");
        }

        if (ordenarOptional.isPresent()) {
            if ( "asc".equalsIgnoreCase(ordenarOptional.get())) {
                queryBuilder.append(" ").append("ORDER BY C.nombre ASC");
            } else if ("desc".equalsIgnoreCase(ordenarOptional.get())) {
                queryBuilder.append(" ").append("ORDER BY C.nombre DESC");
            }
        }


        Query query = em.createQuery(queryBuilder.toString());

        if (buscarOptional.isPresent()) {
            query.setParameter("nombre", "%" + buscarOptional.get() + "%");
        }

        return query.getResultList();
    }
}
