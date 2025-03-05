package org.iesbelen.videoclub.service;

import org.iesbelen.videoclub.domain.Socio;
import org.iesbelen.videoclub.exception.SocioNotFoundException;
import org.iesbelen.videoclub.repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocioService {
    private final SocioRepository socioRepository;

    @Autowired
    private TarjetaService tarjetaService;

    public SocioService(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }

    public List<Socio> all() {
        return this.socioRepository.findAll();
    }

    public Socio save(Socio socio) {
        if (socioRepository.existsByDni(socio.getDni())) {
            throw new RuntimeException("El DNI ya existe");
        }
        return socioRepository.save(socio);
    }


    public Socio one(Long id) {
        return this.socioRepository.findById(id)
                .orElseThrow(() -> new SocioNotFoundException(id));
    }

    public Socio replace(Long id, Socio socio) {
        return this.socioRepository.findById(id).map(s -> (id.equals(socio.getIdSocio()) ?
                this.socioRepository.save(socio) : null))
                .orElseThrow(() -> new SocioNotFoundException(id));
    }

    public void delete(Long id) {
        this.socioRepository.findById(id).map(s -> {
            this.socioRepository.delete(s);
            return s;
        }).orElseThrow(() -> new SocioNotFoundException(id));
    }

    public void findByIdSocio(Long id) {
        this.socioRepository.findById(id);
    }

    public Socio addTarjeta(Long id, Long idTarjeta) {
        Socio socio = this.socioRepository.findById(id).orElseThrow(() -> new SocioNotFoundException(id));
        socio.setTarjeta(this.tarjetaService.one(idTarjeta));
        return this.socioRepository.save(socio);
    }
}
