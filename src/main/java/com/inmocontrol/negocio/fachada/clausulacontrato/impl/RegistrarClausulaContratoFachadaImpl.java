package com.inmocontrol.negocio.fachada.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ClausulaContratoDTO;
import com.inmocontrol.negocio.casouso.clausulacontrato.impl.RegistrarClausulaContratoCasoUsoImpl;
import com.inmocontrol.negocio.casouso.clausulacontrato.RegistrarClausulaContratoCasoUso;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.negocio.dominio.TipoAplicacionDominio;
import com.inmocontrol.negocio.fachada.clausulacontrato.RegistrarClausulaContratoFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;

public class RegistrarClausulaContratoFachadaImpl implements RegistrarClausulaContratoFachada {

    private DAOFactory daoFactory;
    private RegistrarClausulaContratoCasoUso casoUso;

    public RegistrarClausulaContratoFachadaImpl() {
        daoFactory = DAOFactory.getFactory();
        casoUso = new RegistrarClausulaContratoCasoUsoImpl(daoFactory);
    }

    @Override
    public void ejecutar(ClausulaContratoDTO datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw new ValidacionExcepcion("Los datos de la clausula contrato no pueden ser nulos");
        }

        try {
            daoFactory.iniciarTransaccion();
            ClausulaContratoDominio dominio = new ClausulaContratoDominio.Builder()
                    .areaReferencia(datos.getAreaReferencia() != null ?
                        new AreaReferenciaDominio.Builder()
                            .id(datos.getAreaReferencia().getId())
                            .build() : null)
                    .tipoAplicacion(datos.getTipoAplicacion() != null ?
                        new TipoAplicacionDominio.Builder()
                            .id(datos.getTipoAplicacion().getId())
                            .build() : null)
                    .titulo(datos.getTitulo())
                    .contenidoLegal(datos.getContenidoLegal())
                    .build();
            casoUso.ejecutar(dominio);
            daoFactory.confirmarTransaccion();

        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();
            throw new InmocontrolExcepcion("Ocurrio un error registrando la clausula contrato", excepcion);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}