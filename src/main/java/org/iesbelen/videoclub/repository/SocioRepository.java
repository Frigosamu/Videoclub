package org.iesbelen.videoclub.repository;

import org.iesbelen.videoclub.domain.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<Socio, Long> {
    boolean existsByDni(String dni);

}
