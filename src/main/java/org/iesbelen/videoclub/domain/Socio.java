package org.iesbelen.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Socio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_socio")
    private Long idSocio;
    //nombre, apellidos, tarjeta, dni


    private String dni;

    private String nombre;

    private String apellidos;

    public void setIdSocio(Long idSocio) {
        this.idSocio = idSocio;
    }

    public Long getIdSocio() {
        return idSocio;
    }
}
