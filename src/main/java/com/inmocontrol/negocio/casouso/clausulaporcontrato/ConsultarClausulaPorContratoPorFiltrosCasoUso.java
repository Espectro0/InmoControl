package com.inmocontrol.negocio.casouso.clausulaporcontrato;

import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import java.util.List;

public interface ConsultarClausulaPorContratoPorFiltrosCasoUso
    extends CasoUsoConRetorno<ClausulaPorContratoDominio, List<ClausulaPorContratoEntidad>> {}
