package com.inmocontrol.negocio.casouso.departamento;

import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.DepartamentoDominio;
import java.util.List;

public interface ConsultarDepartamentoPorFiltrosCasoUso
    extends CasoUsoConRetorno<DepartamentoDominio, List<DepartamentoEntidad>> {}
