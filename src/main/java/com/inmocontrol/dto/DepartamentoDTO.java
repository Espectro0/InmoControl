package com.inmocontrol.dto;

import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilTexto;
import com.inmocontrol.transversal.UtilUUID;

import java.util.UUID;

public final class DepartamentoDTO {
	private UUID id;
	private String nombre;
	private PaisDTO pais;
	private UUID paisId;

	public DepartamentoDTO() {
		setId(UtilUUID.UUID_CERO);
		setNombre(UtilTexto.VACIO);
		setPais(new PaisDTO());
		setPaisId(UtilUUID.UUID_CERO);
	}

	private DepartamentoDTO(final Builder builder) {
		setId(builder.id);
		setNombre(builder.nombre);
		setPais(builder.pais);
		setPaisId(builder.paisId);
	}

	public UUID getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public PaisDTO getPais() {
		return pais;
	}

	public UUID getPaisId() {
		return paisId;
	}

	private void setId(final UUID id) {
		this.id = id;
	}

	private void setNombre(final String nombre) {
		this.nombre = UtilTexto.aplicarTrim(nombre);
	}

	private void setPais(final PaisDTO pais) {
		this.pais = UtilObjeto.obtenerValorDefecto(pais, new PaisDTO());
	}

	private void setPaisId(final UUID paisId) {
		this.paisId = paisId;
	}

	public static class Builder {
		private UUID id;
		private String nombre;
		private PaisDTO pais;
		private UUID paisId;

		public Builder id(final UUID id) {
			this.id = id;
			return this;
		}

		public Builder nombre(final String nombre) {
			this.nombre = nombre;
			return this;
		}

		public Builder pais(final PaisDTO pais) {
			this.pais = pais;
			return this;
		}

		public Builder paisId(final UUID paisId) {
			this.paisId = paisId;
			return this;
		}

		public DepartamentoDTO build() {
			return new DepartamentoDTO(this);
		}
	}
}
