package org.iesbelen.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.videoclub.domain.Pelicula;
import org.iesbelen.videoclub.service.CategoriaService;
import org.iesbelen.videoclub.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;

    @Autowired
    private CategoriaService categoriaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar", "!paginado", "!orden"})
    public List<Pelicula> all() {
        log.info("Accediendo a todas las películas");
        return this.peliculaService.all();
    }

    @GetMapping(value = {"/order"})
    public List<Pelicula> allOrdered() {
        log.info("Accediendo a todas las películas ordenadas");
        return this.peliculaService.findAllByOrderByTituloAsc();
    }

    @GetMapping(value = {"","/"}, params = {"!paginado", "!orden"})
    public List<Pelicula> all(@RequestParam("buscar") Optional<String> buscarOptional,
                              @RequestParam("ordenar") Optional<String> ordenarOptional) {

        log.info("Accediendo a todas las películas con filtro buscar: %s y ordenar: %s",
                buscarOptional.orElse(""),
                ordenarOptional.orElse(""));
        return this.peliculaService.findAllByQueryFilter(buscarOptional, ordenarOptional);
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar", "!paginado"})
    public ResponseEntity<List<Pelicula>> allbyColumn(@RequestParam(value = "orden", required = false) String[] orden) {
        log.info("Accediendo a todas las películas con ordenación por columna: " + orden[0]);

        List<Pelicula> peliculas = this.peliculaService.findAllOrderByCol(orden);

        return ResponseEntity.ok(peliculas);
    }

    // Para ordenar por dos columnas
    /*@GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar", "!paginado"})
    public ResponseEntity<List<Pelicula>> allbyColumn(@RequestParam(value = "orden", required = false) String[] orden) {
        log.info("Accediendo a todas las películas con ordenación por columnas: " + orden[0]);

        List<Pelicula> peliculas = this.peliculaService.findAllOrderByCols(orden);

        return ResponseEntity.ok(peliculas);
    }*/

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar", "!orden"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value = "paginado", defaultValue = "0") String[] paginacion) {
        log.info("Accediendo a todas las películas con paginación");

        Map<String, Object> responseAll = this.peliculaService.all(paginacion);

        return ResponseEntity.ok(responseAll);
    }

    @PostMapping({"","/"})
    public Pelicula newPelicula(@RequestBody Pelicula pelicula) {
        return this.peliculaService.save(pelicula);
    }

    @GetMapping("/{id}")
    public Pelicula one(@PathVariable("id") Long id) {
        return this.peliculaService.one(id);
    }

    @PutMapping("/{id}")
    public Pelicula replacePelicula(@PathVariable("id") Long id, @RequestBody Pelicula pelicula) {
        return this.peliculaService.replace(id, pelicula);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable("id") Long id) {
        this.peliculaService.delete(id);
    }

    @PostMapping("/{id}/addCategoria/{id_categoria}")
    public Pelicula addCategoria(@PathVariable("id") Long id, @PathVariable("id_categoria") Long idCategoria) {
        return this.peliculaService.addCategoria(id, idCategoria);
    }
}