package com.inmocontrol.negocio.fachada.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ParametroDTO;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.casouso.parametro.impl.ConsultarParametroPorIdCasoUsoImpl;
import com.inmocontrol.negocio.casouso.parametro.ConsultarParametroPorIdCasoUso;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.negocio.fachada.parametro.ConsultarParametroPorIdFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;

public class ConsultarParametroPorIdFachadaImpl implements ConsultarParametroPorIdFachada {

    private DAOFactory daoFactory;
    private ConsultarParametroPorIdCasoUso casoUso;

    public ConsultarParametroPorIdFachadaImpl() {
        daoFactory = DAOFactory.getFactory();
        casoUso = new ConsultarParametroPorIdCasoUsoImpl(daoFactory);
    }

    @Override
    public ParametroEntidad ejecutar(ParametroDTO datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw new ValidacionExcepcion("Los datos del parametro no pueden ser nulos");
        }

        try {
            ParametroDominio dominio = new ParametroDominio.Builder()
                    .id(datos.getId())
                    .build();
            return casoUso.ejecutar(dominio);

        } catch (Exception excepcion) {
            throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}