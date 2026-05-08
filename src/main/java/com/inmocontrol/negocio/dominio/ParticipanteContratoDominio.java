package com.inmocontrol.negocio.dominio;

import java.util.UUID;

public final class ParticipanteContratoDominio {
    private UUID id;
    private PersonaDominio persona;
    private TipoParticipanteDominio tipoParticipante;
    private ContratoDominio contrato;

    private ParticipanteContratoDominio(final Builder builder) {
        setId(builder.id);
        setPersona(builder.persona);
        setTipoParticipante(builder.tipoParticipante);
        setContrato(builder.contrato);
    }

    public UUID getId() {
        return id;
    }

    public PersonaDominio getPersona() {
        return persona;
    }

    public TipoParticipanteDominio getTipoParticipante() {
        return tipoParticipante;
    }

    public ContratoDominio getContrato() {
        return contrato;
    }

    private void setId(final UUID id) {
        this.id = id;
    }

    private void setPersona(final PersonaDominio persona) {
        this.persona = persona;
    }

    private void setTipoParticipante(final TipoParticipanteDominio tipoParticipante) {
        this.tipoParticipante = tipoParticipante;
    }

    private void setContrato(final ContratoDominio contrato) {
        this.contrato = contrato;
    }

    public static class Builder {
        private UUID id;
        private PersonaDominio persona;
        private TipoParticipanteDominio tipoParticipante;
        private ContratoDominio contrato;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder persona(final PersonaDominio persona) {
            this.persona = persona;
            return this;
        }

        public Builder tipoParticipante(final TipoParticipanteDominio tipoParticipante) {
            this.tipoParticipante = tipoParticipante;
            return this;
        }

        public Builder contrato(final ContratoDominio contrato) {
            this.contrato = contrato;
            return this;
        }

        public ParticipanteContratoDominio build() {
            return new ParticipanteContratoDominio(this);
        }
    }
}
