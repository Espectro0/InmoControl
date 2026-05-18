package com.inmocontrol.negocio.fachada.ciudad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.CiudadDTO;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.negocio.casouso.ciudad.ConsultarCiudadPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.ciudad.impl.ConsultarCiudadPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.negocio.fachada.ciudad.ConsultarCiudadPorFiltrosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarCiudadPorFiltrosFachadaImpl implements ConsultarCiudadPorFiltrosFachada {

    private DAOFactory daoFactory;
    private ConsultarCiudadPorFiltrosCasoUso casoUso;

    public ConsultarCiudadPorFiltrosFachadaImpl() {
        daoFactory = DAOFactory.getFactory();
        casoUso = new ConsultarCiudadPorFiltrosCasoUsoImpl(daoFactory);
    }

    @Override
    public List<CiudadEntidad> ejecutar(CiudadDTO datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw new ValidacionExcepcion("Los datos de la ciudad no pueden ser nulos");
        }

        try {
            CiudadDominio dominio = new CiudadDominio.Builder()
                    .nombre(datos.getNombre())
                    .build();
            return casoUso.ejecutar(dominio);

        } catch (Exception excepcion) {
            throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}