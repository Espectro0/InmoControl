package com.inmocontrol.negocio.dominio;

import com.inmocontrol.transversal.UtilNumero;
import java.util.UUID;

public final class ClausulaPorContratoDominio {
    private UUID id;
    private Integer numeroClausula;
    private ContratoDominio contrato;
    private ClausulaContratoDominio clausula;

    private ClausulaPorContratoDominio(final Builder builder) {
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

    public ContratoDominio getContrato() {
        return contrato;
    }

    public ClausulaContratoDominio getClausula() {
        return clausula;
    }

    private void setId(final UUID id) {
        this.id = id;
    }

    private void setNumeroClausula(final Integer numeroClausula) {
        this.numeroClausula = UtilNumero.obtenerValorDefecto(numeroClausula);
    }

    private void setContrato(final ContratoDominio contrato) {
        this.contrato = contrato;
    }

    private void setClausula(final ClausulaContratoDominio clausula) {
        this.clausula = clausula;
    }

    public static class Builder {
        private UUID id;
        private Integer numeroClausula;
        private ContratoDominio contrato;
        private ClausulaContratoDominio clausula;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder numeroClausula(final Integer numeroClausula) {
            this.numeroClausula = numeroClausula;
            return this;
        }

        public Builder contrato(final ContratoDominio contrato) {
            this.contrato = contrato;
            return this;
        }

        public Builder clausula(final ClausulaContratoDominio clausula) {
            this.clausula = clausula;
            return this;
        }

        public ClausulaPorContratoDominio build() {
            return new ClausulaPorContratoDominio(this);
        }
    }
}
