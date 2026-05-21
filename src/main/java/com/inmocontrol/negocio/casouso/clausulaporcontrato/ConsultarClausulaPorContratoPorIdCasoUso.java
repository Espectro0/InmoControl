package com.inmocontrol.negocio.casouso.clausulaporcontrato;

import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;

public interface ConsultarClausulaPorContratoPorIdCasoUso
    extends CasoUsoConRetorno<ClausulaPorContratoDominio, ClausulaPorContratoEntidad> {}
