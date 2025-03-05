package org.iesbelen.videoclub.exception;

public class TarjetaNotFoundException extends RuntimeException {
    public TarjetaNotFoundException(Long idTarjeta) {
        super("Not found Tarjeta with id: " + idTarjeta);
    }
}
