package org.iesbelen.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.videoclub.domain.Socio;
import org.iesbelen.videoclub.service.SocioService;
import org.iesbelen.videoclub.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/socios")
public class SocioController {
    private final SocioService socioService;

    @Autowired
    private TarjetaService tarjetaService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar", "!paginado"})
    public List<Socio> all() {
        log.info("Accediendo a todos los socios");
        return this.socioService.all();
    }

    @PostMapping({"","/"})
    public Socio newSocio(@RequestBody Socio socio) {
        return this.socioService.save(socio);
    }

    @GetMapping("/{id}")
    public Socio one(@PathVariable("id") Long id) {
        return this.socioService.one(id);
    }

    @PutMapping("/{id}")
    public Socio replaceSocio(@PathVariable("id") Long id, @RequestBody Socio socio) {
        return this.socioService.replace(id, socio);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteSocio(@PathVariable("id") Long id) {
        this.socioService.delete(id);
    }

    @PostMapping("/{id}/tarjeta")
    public Socio addTarjeta(@PathVariable("id") Long id, @RequestBody Long idTarjeta) {
        return this.socioService.addTarjeta(id, idTarjeta);
    }

}
