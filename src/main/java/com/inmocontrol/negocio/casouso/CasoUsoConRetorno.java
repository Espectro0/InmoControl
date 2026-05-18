package com.inmocontrol.negocio.casouso;

public interface CasoUsoConRetorno<D, R> {

	R ejecutar(D datos);

}