package com.inmocontrol.entidad;

import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class ParametroClausulaContratoEntidad {
    private UUID id;
    private ParametroEntidad parametro;
    private ClausulaPorContratoEntidad clausulaPorContrato;
    private String valor;

    private ParametroClausulaContratoEntidad(final Builder builder) {
        setId(builder.id);
        setParametro(builder.parametro);
        setClausulaPorContrato(builder.clausulaPorContrato);
        setValor(builder.valor);
    }

    public UUID getId() {
        return id;
    }

    public ParametroEntidad getParametro() {
        return parametro;
    }

    public ClausulaPorContratoEntidad getClausulaPorContrato() {
        return clausulaPorContrato;
    }

    public String getValor() {
        return valor;
    }

    private void setId(final UUID id) {
        this.id = id;
    }

    private void setParametro(final ParametroEntidad parametro) {
        this.parametro = parametro;
    }

    private void setClausulaPorContrato(final ClausulaPorContratoEntidad clausulaPorContrato) {
        this.clausulaPorContrato = clausulaPorContrato;
    }

    private void setValor(final String valor) {
        this.valor = UtilTexto.aplicarTrim(valor);
    }

    public static class Builder {
        private UUID id;
        private ParametroEntidad parametro;
        private ClausulaPorContratoEntidad clausulaPorContrato;
        private String valor;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder parametro(final ParametroEntidad parametro) {
            this.parametro = parametro;
            return this;
        }

        public Builder clausulaPorContrato(final ClausulaPorContratoEntidad clausulaPorContrato) {
            this.clausulaPorContrato = clausulaPorContrato;
            return this;
        }

        public Builder valor(final String valor) {
            this.valor = valor;
            return this;
        }

        public ParametroClausulaContratoEntidad build() {
            return new ParametroClausulaContratoEntidad(this);
        }
    }
}
