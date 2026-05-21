package com.inmocontrol.negocio.fachada.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PersonaDTO;
import com.inmocontrol.negocio.casouso.persona.impl.ModificarPersonaCasoUsoImpl;
import com.inmocontrol.negocio.casouso.persona.ModificarPersonaCasoUso;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.negocio.dominio.TipoDocumentoDominio;
import com.inmocontrol.negocio.fachada.persona.ModificarPersonaFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;
import com.inmocontrol.transversal.UtilObjeto;

public class ModificarPersonaFachadaImpl implements ModificarPersonaFachada {

    private DAOFactory daoFactory;
    private ModificarPersonaCasoUso casoUso;

    public ModificarPersonaFachadaImpl() {
        daoFactory = DAOFactory.getFactory();
        casoUso = new ModificarPersonaCasoUsoImpl(daoFactory);
    }

    @Override
    public void ejecutar(PersonaDTO datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw new ValidacionExcepcion("Los datos de la persona no pueden ser nulos");
        }

        try {
            daoFactory.iniciarTransaccion();
            PersonaDominio dominio = new PersonaDominio.Builder()
                    .id(datos.getId())
                    .tipoDocumento(datos.getTipoDocumento() != null ?
                        new TipoDocumentoDominio.Builder()
                            .id(datos.getTipoDocumento().getId())
                            .build() : null)
                    .numeroIdentificacion(datos.getNumeroIdentificacion())
                    .primerNombre(datos.getPrimerNombre())
                    .segundoNombre(datos.getSegundoNombre())
                    .primerApellido(datos.getPrimerApellido())
                    .segundoApellido(datos.getSegundoApellido())
                    .numeroTelefonico(datos.getNumeroTelefonico())
                    .correoElectronico(datos.getCorreoElectronico())
                    .direccionResidencia(datos.getDireccionResidencia())
                    .ciudadResidencia(datos.getCiudadResidencia() != null ?
                        new CiudadDominio.Builder()
                            .id(datos.getCiudadResidencia().getId())
                            .build() : null)
                    .fechaNacimiento(datos.getFechaNacimiento())
                    .edad(datos.getEdad())
                    .build();
            casoUso.ejecutar(dominio);
            daoFactory.confirmarTransaccion();

        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();
            throw new InmocontrolExcepcion("Ocurrio un error modificando la persona", excepcion);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}