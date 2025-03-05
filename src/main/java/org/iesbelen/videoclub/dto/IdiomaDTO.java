package org.iesbelen.videoclub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdiomaDTO {

    private String nombre;
    private List<String> titulosPeliculas;
}
