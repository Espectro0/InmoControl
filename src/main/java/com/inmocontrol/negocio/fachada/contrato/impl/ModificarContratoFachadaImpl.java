package com.inmocontrol.negocio.fachada.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ContratoDTO;
import com.inmocontrol.negocio.casouso.contrato.impl.ModificarContratoCasoUsoImpl;
import com.inmocontrol.negocio.casouso.contrato.ModificarContratoCasoUso;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.negocio.fachada.contrato.ModificarContratoFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;

public class ModificarContratoFachadaImpl implements ModificarContratoFachada {

    private DAOFactory daoFactory;
    private ModificarContratoCasoUso casoUso;

    public ModificarContratoFachadaImpl() {
        daoFactory = DAOFactory.getFactory();
        casoUso = new ModificarContratoCasoUsoImpl(daoFactory);
    }

    @Override
    public void ejecutar(ContratoDTO datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw new ValidacionExcepcion("Los datos del contrato no pueden ser nulos");
        }

        try {
            daoFactory.iniciarTransaccion();
            ContratoDominio dominio = new ContratoDominio.Builder()
                    .id(datos.getId())
                    .codigoContrato(datos.getCodigoContrato())
                    .fechaInicio(datos.getFechaInicio())
                    .fechaFin(datos.getFechaFin())
                    .esActivo(datos.getEsActivo())
                    .propiedad(datos.getPropiedad() != null ?
                        new PropiedadDominio.Builder()
                            .id(datos.getPropiedad().getId())
                            .build() : null)
                    .build();
            casoUso.ejecutar(dominio);
            daoFactory.confirmarTransaccion();

        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();
            throw new InmocontrolExcepcion("Ocurrio un error modificando el contrato", excepcion);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}