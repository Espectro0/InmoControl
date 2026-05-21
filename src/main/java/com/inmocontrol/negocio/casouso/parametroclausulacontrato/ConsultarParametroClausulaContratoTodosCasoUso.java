package com.inmocontrol.negocio.casouso.parametroclausulacontrato;

import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import java.util.List;

public interface ConsultarParametroClausulaContratoTodosCasoUso
		extends CasoUsoConRetorno<Void, List<ParametroClausulaContratoEntidad>> {
}