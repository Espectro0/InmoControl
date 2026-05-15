package com.inmocontrol.transversal.excepcion;

public class InmocontrolExcepcion extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InmocontrolExcepcion(String mensaje) {
        super(mensaje);
    }

    public InmocontrolExcepcion(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
