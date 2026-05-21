package com.inmocontrol.negocio.fachada.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PropiedadDTO;
import com.inmocontrol.negocio.casouso.propiedad.impl.ModificarPropiedadCasoUsoImpl;
import com.inmocontrol.negocio.casouso.propiedad.ModificarPropiedadCasoUso;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.negocio.dominio.EstratoDominio;
import com.inmocontrol.negocio.dominio.PropiedadDominio;
import com.inmocontrol.negocio.dominio.TipoPropiedadDominio;
import com.inmocontrol.negocio.fachada.propiedad.ModificarPropiedadFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;

public class ModificarPropiedadFachadaImpl implements ModificarPropiedadFachada {

    private DAOFactory daoFactory;
    private ModificarPropiedadCasoUso casoUso;

    public ModificarPropiedadFachadaImpl() {
        daoFactory = DAOFactory.getFactory();
        casoUso = new ModificarPropiedadCasoUsoImpl(daoFactory);
    }

    @Override
    public void ejecutar(PropiedadDTO datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw new ValidacionExcepcion("Los datos de la propiedad no pueden ser nulos");
        }

        try {
            daoFactory.iniciarTransaccion();
            PropiedadDominio dominio = new PropiedadDominio.Builder()
                    .id(datos.getId())
                    .tipoPropiedad(datos.getTipoPropiedad() != null ?
                        new TipoPropiedadDominio.Builder()
                            .id(datos.getTipoPropiedad().getId())
                            .build() : null)
                    .estrato(datos.getEstrato() != null ?
                        new EstratoDominio.Builder()
                            .id(datos.getEstrato().getId())
                            .build() : null)
                    .nombreInmueble(datos.getNombreInmueble())
                    .descripcionInmueble(datos.getDescripcionInmueble())
                    .areaMetros(datos.getAreaMetros())
                    .direccion(datos.getDireccion())
                    .ciudad(datos.getCiudad() != null ?
                        new CiudadDominio.Builder()
                            .id(datos.getCiudad().getId())
                            .build() : null)
                    .build();
            casoUso.ejecutar(dominio);
            daoFactory.confirmarTransaccion();

        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();
            throw new InmocontrolExcepcion("Ocurrio un error modificando la propiedad", excepcion);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}