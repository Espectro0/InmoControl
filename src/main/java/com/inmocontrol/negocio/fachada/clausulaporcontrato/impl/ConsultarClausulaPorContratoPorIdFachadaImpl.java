package com.inmocontrol.negocio.fachada.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ClausulaPorContratoDTO;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.impl.ConsultarClausulaPorContratoPorIdCasoUsoImpl;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.ConsultarClausulaPorContratoPorIdCasoUso;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.negocio.fachada.clausulaporcontrato.ConsultarClausulaPorContratoPorIdFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;

public class ConsultarClausulaPorContratoPorIdFachadaImpl implements ConsultarClausulaPorContratoPorIdFachada {

    private DAOFactory daoFactory;
    private ConsultarClausulaPorContratoPorIdCasoUso casoUso;

    public ConsultarClausulaPorContratoPorIdFachadaImpl() {
        daoFactory = DAOFactory.getFactory();
        casoUso = new ConsultarClausulaPorContratoPorIdCasoUsoImpl(daoFactory);
    }

    @Override
    public ClausulaPorContratoEntidad ejecutar(ClausulaPorContratoDTO datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw new ValidacionExcepcion("Los datos de la clausula por contrato no pueden ser nulos");
        }

        try {
            ClausulaPorContratoDominio dominio = new ClausulaPorContratoDominio.Builder()
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