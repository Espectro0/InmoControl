package com.inmocontrol.dto;

import java.util.UUID;

public final class ParticipanteContratoDTO {
    private UUID id;
    private PersonaDTO persona;
    private TipoParticipanteDTO tipoParticipante;
    private ContratoDTO contrato;

    private ParticipanteContratoDTO(final Builder builder) {
        setId(builder.id);
        setPersona(builder.persona);
        setTipoParticipante(builder.tipoParticipante);
        setContrato(builder.contrato);
    }

    public UUID getId() {
        return id;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public TipoParticipanteDTO getTipoParticipante() {
        return tipoParticipante;
    }

    public ContratoDTO getContrato() {
        return contrato;
    }

    private void setId(final UUID id) {
        this.id = id;
    }

    private void setPersona(final PersonaDTO persona) {
        this.persona = persona;
    }

    private void setTipoParticipante(final TipoParticipanteDTO tipoParticipante) {
        this.tipoParticipante = tipoParticipante;
    }

    private void setContrato(final ContratoDTO contrato) {
        this.contrato = contrato;
    }

    public static class Builder {
        private UUID id;
        private PersonaDTO persona;
        private TipoParticipanteDTO tipoParticipante;
        private ContratoDTO contrato;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder persona(final PersonaDTO persona) {
            this.persona = persona;
            return this;
        }

        public Builder tipoParticipante(final TipoParticipanteDTO tipoParticipante) {
            this.tipoParticipante = tipoParticipante;
            return this;
        }

        public Builder contrato(final ContratoDTO contrato) {
            this.contrato = contrato;
            return this;
        }

        public ParticipanteContratoDTO build() {
            return new ParticipanteContratoDTO(this);
        }
    }
}
