package com.inmocontrol.negocio.casouso.clausulacontrato;

import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;

public interface ConsultarClausulaContratoPorIdCasoUso
    extends CasoUsoConRetorno<ClausulaContratoDominio, ClausulaContratoEntidad> {}
