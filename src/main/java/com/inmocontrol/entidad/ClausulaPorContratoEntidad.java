package com.inmocontrol.entidad;

import com.inmocontrol.transversal.UtilNumero;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.UUID;

public final class ClausulaPorContratoEntidad {
	private UUID id;
	private Integer numeroClausula;
	private ContratoEntidad contrato;
	private ClausulaContratoEntidad clausula;

	private ClausulaPorContratoEntidad(final Builder builder) {
		setId(builder.id);
		setNumeroClausula(builder.numeroClausula);
		setContrato(builder.contrato);
		setClausula(builder.clausula);
	}

	public UUID getId() {
		return id;
	}

	public Integer getNumeroClausula() {
		return numeroClausula;
	}

	public ContratoEntidad getContrato() {
		return contrato;
	}

	public ClausulaContratoEntidad getClausula() {
		return clausula;
	}

	private void setId(final UUID id) {
		this.id = id;
	}

	private void setNumeroClausula(final Integer numeroClausula) {
		this.numeroClausula = UtilNumero.obtenerValorDefecto(numeroClausula);
	}

	private void setContrato(final ContratoEntidad contrato) {
		this.contrato = UtilObjeto.obtenerValorDefecto(contrato, new ContratoEntidad.Builder().build());
	}

	private void setClausula(final ClausulaContratoEntidad clausula) {
		this.clausula = UtilObjeto.obtenerValorDefecto(clausula, new ClausulaContratoEntidad.Builder().build());
	}

	public static class Builder {
		private UUID id;
		private Integer numeroClausula;
		private ContratoEntidad contrato;
		private ClausulaContratoEntidad clausula;

		public Builder id(final UUID id) {
			this.id = id;
			return this;
		}

		public Builder numeroClausula(final Integer numeroClausula) {
			this.numeroClausula = numeroClausula;
			return this;
		}

		public Builder contrato(final ContratoEntidad contrato) {
			this.contrato = contrato;
			return this;
		}

		public Builder clausula(final ClausulaContratoEntidad clausula) {
			this.clausula = clausula;
			return this;
		}

		public ClausulaPorContratoEntidad build() {
			return new ClausulaPorContratoEntidad(this);
		}
	}
}
