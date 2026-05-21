package com.inmocontrol.negocio.casouso.parametroclausulacontrato;

import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;

public interface ConsultarParametroClausulaContratoPorIdCasoUso
    extends CasoUsoConRetorno<ParametroClausulaContratoDominio, ParametroClausulaContratoEntidad> {}
