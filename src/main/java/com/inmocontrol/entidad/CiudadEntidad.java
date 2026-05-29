package com.inmocontrol.entidad;

import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class CiudadEntidad {
	private UUID id;
	private String nombre;
	private DepartamentoEntidad departamento;

	private CiudadEntidad(final Builder builder) {
		setId(builder.id);
		setNombre(builder.nombre);
		setDepartamento(builder.departamento);
	}

	public UUID getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public DepartamentoEntidad getDepartamento() {
		return departamento;
	}

	private void setId(final UUID id) {
		this.id = id;
	}

	private void setNombre(final String nombre) {
		this.nombre = UtilTexto.aplicarTrim(nombre);
	}

	private void setDepartamento(final DepartamentoEntidad departamento) {
		this.departamento = UtilObjeto.obtenerValorDefecto(departamento, new DepartamentoEntidad.Builder().build());
	}

	public static class Builder {
		private UUID id;
		private String nombre;
		private DepartamentoEntidad departamento;

		public Builder id(final UUID id) {
			this.id = id;
			return this;
		}

		public Builder nombre(final String nombre) {
			this.nombre = nombre;
			return this;
		}

		public Builder departamento(final DepartamentoEntidad departamento) {
			this.departamento = departamento;
			return this;
		}

		public CiudadEntidad build() {
			return new CiudadEntidad(this);
		}
	}
}
