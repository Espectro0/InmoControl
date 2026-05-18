package com.inmocontrol.negocio.fachada.pais.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PaisDTO;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.negocio.casouso.pais.ConsultarPaisPorIdCasoUso;
import com.inmocontrol.negocio.casouso.pais.impl.ConsultarPaisPorIdCasoUsoImpl;
import com.inmocontrol.negocio.dominio.PaisDominio;
import com.inmocontrol.negocio.fachada.pais.ConsultarPaisPorIdFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;

public class ConsultarPaisPorIdFachadaImpl implements ConsultarPaisPorIdFachada {

    private DAOFactory daoFactory;
    private ConsultarPaisPorIdCasoUso casoUso;

    public ConsultarPaisPorIdFachadaImpl() {
        daoFactory = DAOFactory.getFactory();
        casoUso = new ConsultarPaisPorIdCasoUsoImpl(daoFactory);
    }

    @Override
    public PaisEntidad ejecutar(PaisDTO datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw new ValidacionExcepcion("Los datos del pais no pueden ser nulos");
        }

        try {
            PaisDominio dominio = new PaisDominio.Builder()
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