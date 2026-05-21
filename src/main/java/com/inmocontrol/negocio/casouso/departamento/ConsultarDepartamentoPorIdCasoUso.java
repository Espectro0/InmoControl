package com.inmocontrol.negocio.casouso.departamento;

import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.DepartamentoDominio;

public interface ConsultarDepartamentoPorIdCasoUso
    extends CasoUsoConRetorno<DepartamentoDominio, DepartamentoEntidad> {}
