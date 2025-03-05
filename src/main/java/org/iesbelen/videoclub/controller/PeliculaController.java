package org.iesbelen.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.videoclub.domain.Pelicula;
import org.iesbelen.videoclub.repository.PeliculaRepository;
import org.iesbelen.videoclub.service.CategoriaService;
import org.iesbelen.videoclub.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private PeliculaRepository peliculaRepository;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping(value = {"", "/"}, params = {"!buscar", "!ordenar", "!pagina", "!tamanio"})
    public List<Pelicula> all() {
        log.info("Accediendo a todas las películas");
        return this.peliculaService.all();
    }

    @PostMapping({"","/"})
    public Pelicula newPelicula(@RequestBody Pelicula pelicula) {
        return this.peliculaService.save(pelicula);
    }

    @PostMapping("/{id}/add/{id_categoria}")
    public void addPelicula(@PathVariable long id, @PathVariable long id_categoria) {
        this.peliculaService.addCategoria(id, id_categoria);
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

    @GetMapping(value = {"/order"})
    public List<Pelicula> allOrdanado() {
        log.info("Accediendo a todas las películas ordenadas");
        return this.peliculaService.findAllByOrderByTituloAsc();
    }

    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamanio"})
    public List<Pelicula> all(@RequestParam("buscar") Optional<String> buscarOptional, @RequestParam("ordenar") Optional<String> ordenarOptional) {
        log.info("Accediendo a todas las películas con filtro buscar: %s y ordenadr: %s",
                buscarOptional.orElse(""),
                ordenarOptional.orElse(""));

        return this.peliculaService.findAllByQueryFilter(buscarOptional, ordenarOptional);
    }

    @GetMapping(value = {"" , "/"}, params = {"!buscar", "!ordenar"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value = "pagina", defaultValue = "0") int pagina,
                                                   @RequestParam(value = "tamanio", defaultValue = "3") int tamanio) {
        log.info("Accediendo a todas las películas con paginación");

        Map<String, Object> responseAll = this.peliculaService.all(pagina, tamanio);

        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = {"", "/"}, params = {"orden"})
    public ResponseEntity<List<Pelicula>> obtenerPeliculas(
            @RequestParam(required = false) String[] orden) {

        // Si no se pasa ningún parámetro 'orden', el valor será null
        List<Pelicula> peliculas = peliculaService.obtenerPeliculaConOrdenYPaginado(orden);
        return ResponseEntity.ok(peliculas);
    }
}