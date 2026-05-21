package com.inmocontrol.negocio.casouso.persona;

import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import java.util.List;

public interface ConsultarPersonaPorFiltrosCasoUso
    extends CasoUsoConRetorno<PersonaDominio, List<PersonaEntidad>> {}
