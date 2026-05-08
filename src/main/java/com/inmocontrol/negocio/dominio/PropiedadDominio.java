package com.inmocontrol.negocio.dominio;

import com.inmocontrol.transversal.UtilNumero;
import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class PropiedadDominio {
    private UUID id;
    private TipoPropiedadDominio tipoPropiedad;
    private EstratoDominio estrato;
    private String nombreInmueble;
    private String descripcionInmueble;
    private Integer areaMetros;
    private String direccion;
    private CiudadDominio ciudad;

    private PropiedadDominio(final Builder builder) {
        setId(builder.id);
        setTipoPropiedad(builder.tipoPropiedad);
        setEstrato(builder.estrato);
        setNombreInmueble(builder.nombreInmueble);
        setDescripcionInmueble(builder.descripcionInmueble);
        setAreaMetros(builder.areaMetros);
        setDireccion(builder.direccion);
        setCiudad(builder.ciudad);
    }

    public UUID getId() {
        return id;
    }

    public TipoPropiedadDominio getTipoPropiedad() {
        return tipoPropiedad;
    }

    public EstratoDominio getEstrato() {
        return estrato;
    }

    public String getNombreInmueble() {
        return nombreInmueble;
    }

    public String getDescripcionInmueble() {
        return descripcionInmueble;
    }

    public Integer getAreaMetros() {
        return areaMetros;
    }

    public String getDireccion() {
        return direccion;
    }

    public CiudadDominio getCiudad() {
        return ciudad;
    }

    private void setId(final UUID id) {
        this.id = id;
    }

    private void setTipoPropiedad(final TipoPropiedadDominio tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    private void setEstrato(final EstratoDominio estrato) {
        this.estrato = estrato;
    }

    private void setNombreInmueble(final String nombreInmueble) {
        this.nombreInmueble = UtilTexto.aplicarTrim(nombreInmueble);
    }

    private void setDescripcionInmueble(final String descripcionInmueble) {
        this.descripcionInmueble = UtilTexto.aplicarTrim(descripcionInmueble);
    }

    private void setAreaMetros(final Integer areaMetros) {
        this.areaMetros = UtilNumero.obtenerValorDefecto(areaMetros);
    }

    private void setDireccion(final String direccion) {
        this.direccion = UtilTexto.aplicarTrim(direccion);
    }

    private void setCiudad(final CiudadDominio ciudad) {
        this.ciudad = ciudad;
    }

    public static class Builder {
        private UUID id;
        private TipoPropiedadDominio tipoPropiedad;
        private EstratoDominio estrato;
        private String nombreInmueble;
        private String descripcionInmueble;
        private Integer areaMetros;
        private String direccion;
        private CiudadDominio ciudad;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder tipoPropiedad(final TipoPropiedadDominio tipoPropiedad) {
            this.tipoPropiedad = tipoPropiedad;
            return this;
        }

        public Builder estrato(final EstratoDominio estrato) {
            this.estrato = estrato;
            return this;
        }

        public Builder nombreInmueble(final String nombreInmueble) {
            this.nombreInmueble = nombreInmueble;
            return this;
        }

        public Builder descripcionInmueble(final String descripcionInmueble) {
            this.descripcionInmueble = descripcionInmueble;
            return this;
        }

        public Builder areaMetros(final Integer areaMetros) {
            this.areaMetros = areaMetros;
            return this;
        }

        public Builder direccion(final String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder ciudad(final CiudadDominio ciudad) {
            this.ciudad = ciudad;
            return this;
        }

        public PropiedadDominio build() {
            return new PropiedadDominio(this);
        }
    }
}
