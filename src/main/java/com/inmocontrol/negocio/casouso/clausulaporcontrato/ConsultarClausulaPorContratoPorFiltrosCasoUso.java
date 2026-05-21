package com.inmocontrol.negocio.casouso.clausulaporcontrato;

import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import java.util.List;

public interface ConsultarClausulaPorContratoPorFiltrosCasoUso extends CasoUsoConRetorno<ClausulaPorContratoDominio, List<ClausulaPorContratoEntidad>> {
}