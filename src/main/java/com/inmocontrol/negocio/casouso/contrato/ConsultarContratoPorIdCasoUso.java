package com.inmocontrol.negocio.casouso.contrato;

import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.ContratoDominio;

public interface ConsultarContratoPorIdCasoUso
    extends CasoUsoConRetorno<ContratoDominio, ContratoEntidad> {}
