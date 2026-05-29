package com.inmocontrol.entidad;

import com.inmocontrol.transversal.UtilDate;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilTexto;
import java.util.Date;
import java.util.UUID;

public final class ContratoEntidad {
	private UUID id;
	private String codigoContrato;
	private Date fechaInicio;
	private Date fechaFin;
	private Boolean esActivo;
	private PropiedadEntidad propiedad;

	private ContratoEntidad(final Builder builder) {
		setId(builder.id);
		setCodigoContrato(builder.codigoContrato);
		setFechaInicio(builder.fechaInicio);
		setFechaFin(builder.fechaFin);
		setEsActivo(builder.esActivo);
		setPropiedad(builder.propiedad);
	}

	public UUID getId() {
		return id;
	}

	public String getCodigoContrato() {
		return codigoContrato;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public Boolean getEsActivo() {
		return esActivo;
	}

	public PropiedadEntidad getPropiedad() {
		return propiedad;
	}

	private void setId(final UUID id) {
		this.id = id;
	}

	private void setCodigoContrato(final String codigoContrato) {
		this.codigoContrato = UtilTexto.aplicarTrim(codigoContrato);
	}

	private void setFechaInicio(final Date fechaInicio) {
		this.fechaInicio = UtilDate.obtenerValorDefecto(fechaInicio);
	}

	private void setFechaFin(final Date fechaFin) {
		this.fechaFin = UtilDate.obtenerValorDefecto(fechaFin);
	}

	private void setEsActivo(final Boolean esActivo) {
		this.esActivo = esActivo;
	}

	private void setPropiedad(final PropiedadEntidad propiedad) {
		this.propiedad = UtilObjeto.obtenerValorDefecto(propiedad, new PropiedadEntidad.Builder().build());
	}

	public static class Builder {
		private UUID id;
		private String codigoContrato;
		private Date fechaInicio;
		private Date fechaFin;
		private Boolean esActivo;
		private PropiedadEntidad propiedad;

		public Builder id(final UUID id) {
			this.id = id;
			return this;
		}

		public Builder codigoContrato(final String codigoContrato) {
			this.codigoContrato = codigoContrato;
			return this;
		}

		public Builder fechaInicio(final Date fechaInicio) {
			this.fechaInicio = fechaInicio;
			return this;
		}

		public Builder fechaFin(final Date fechaFin) {
			this.fechaFin = fechaFin;
			return this;
		}

		public Builder esActivo(final Boolean esActivo) {
			this.esActivo = esActivo;
			return this;
		}

		public Builder propiedad(final PropiedadEntidad propiedad) {
			this.propiedad = propiedad;
			return this;
		}

		public ContratoEntidad build() {
			return new ContratoEntidad(this);
		}
	}
}
