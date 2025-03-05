package org.iesbelen.videoclub.exception;

public class SocioNotFoundException extends RuntimeException {
    public SocioNotFoundException(Long idSocio) {
        super("Not found Socio with id: " + idSocio);
    }
}
