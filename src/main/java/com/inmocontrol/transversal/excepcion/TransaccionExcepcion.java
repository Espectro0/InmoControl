package com.inmocontrol.transversal.excepcion;

public class TransaccionExcepcion extends RuntimeException {

    private static final long serialVersionUID = 6715703643636888883L;

    public TransaccionExcepcion(String mensaje) {
        super(mensaje);
    }

    public TransaccionExcepcion(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
