package com.inmocontrol.dto;

import com.inmocontrol.transversal.UtilBoolean;
import com.inmocontrol.transversal.UtilDate;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilTexto;
import com.inmocontrol.transversal.UtilUUID;

import java.util.Date;
import java.util.UUID;

public final class ContratoDTO {
	private UUID id;
	private String codigoContrato;
	private Date fechaInicio;
	private Date fechaFin;
	private Boolean esActivo;
	private PropiedadDTO propiedad;

	public ContratoDTO() {
		setId(UtilUUID.UUID_CERO);
		setCodigoContrato(UtilTexto.VACIO);
		setFechaInicio(UtilDate.FECHA_DEFECTO);
		setFechaFin(UtilDate.FECHA_DEFECTO);
		setEsActivo(false);
		setPropiedad(new PropiedadDTO());
	}

	private ContratoDTO(final Builder builder) {
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

	public PropiedadDTO getPropiedad() {
		return propiedad;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public void setCodigoContrato(final String codigoContrato) {
		this.codigoContrato = UtilTexto.aplicarTrim(codigoContrato);
	}

	public void setFechaInicio(final Date fechaInicio) {
		this.fechaInicio = UtilDate.obtenerValorDefecto(fechaInicio);
	}

	public void setFechaFin(final Date fechaFin) {
		this.fechaFin = UtilDate.obtenerValorDefecto(fechaFin);
	}

	public void setEsActivo(final Boolean esActivo) {
		this.esActivo = UtilBoolean.obtenerValorDefecto(esActivo);
	}

	public void setPropiedad(final PropiedadDTO propiedad) {
		this.propiedad = UtilObjeto.obtenerValorDefecto(propiedad, new PropiedadDTO());
	}

	public static class Builder {
		private UUID id;
		private String codigoContrato;
		private Date fechaInicio;
		private Date fechaFin;
		private Boolean esActivo;
		private PropiedadDTO propiedad;

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

		public Builder propiedad(final PropiedadDTO propiedad) {
			this.propiedad = propiedad;
			return this;
		}

		public ContratoDTO build() {
			return new ContratoDTO(this);
		}
	}
}
