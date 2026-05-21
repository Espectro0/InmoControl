package com.inmocontrol.negocio.casouso.parametroclausulacontrato;

import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import java.util.List;

public interface ConsultarParametroClausulaContratoPorFiltrosCasoUso extends CasoUsoConRetorno<ParametroClausulaContratoDominio, List<ParametroClausulaContratoEntidad>> {
}