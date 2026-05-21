package com.inmocontrol.negocio.casouso.clausulaporcontrato;

import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import java.util.List;

public interface ConsultarClausulaPorContratoTodosCasoUso
		extends CasoUsoConRetorno<Void, List<ClausulaPorContratoEntidad>> {
}