package com.inmocontrol.negocio.casouso.persona;

import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.negocio.casouso.CasoUsoConRetorno;
import com.inmocontrol.negocio.dominio.PersonaDominio;

public interface ConsultarPersonaPorIdCasoUso
    extends CasoUsoConRetorno<PersonaDominio, PersonaEntidad> {}
