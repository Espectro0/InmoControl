package com.inmocontrol.negocio.casouso.clausulacontrato;

import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import java.util.List;

public interface ConsultarClausulaContratoPorFiltrosCasoUso
    extends CasoUsoConRetorno<ClausulaContratoDominio, List<ClausulaContratoEntidad>> {}
