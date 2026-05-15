package com.inmocontrol.transversal.excepcion;

public class TransaccionExcepcion extends InmocontrolExcepcion {

    private static final long serialVersionUID = 1L;

    public TransaccionExcepcion(String mensaje) {
        super(mensaje);
    }

    public TransaccionExcepcion(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
