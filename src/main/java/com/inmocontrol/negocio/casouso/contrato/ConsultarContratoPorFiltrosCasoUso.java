package com.inmocontrol.negocio.casouso.contrato;

import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.entidad.ContratoEntidad;
import java.util.List;

public interface ConsultarContratoPorFiltrosCasoUso extends CasoUsoConRetorno<ContratoDominio, List<ContratoEntidad>> {
}