package com.inmocontrol.negocio.fachada;

public interface FachadaConRetorno<D, R> {

	R ejecutar(D datos);

}
