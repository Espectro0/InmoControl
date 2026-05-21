package com.inmocontrol.entidad;

import java.util.UUID;

public final class ParticipanteContratoEntidad {
  private UUID id;
  private PersonaEntidad persona;
  private TipoParticipanteEntidad tipoParticipante;
  private ContratoEntidad contrato;

  private ParticipanteContratoEntidad(final Builder builder) {
    setId(builder.id);
    setPersona(builder.persona);
    setTipoParticipante(builder.tipoParticipante);
    setContrato(builder.contrato);
  }

  public UUID getId() {
    return id;
  }

  public PersonaEntidad getPersona() {
    return persona;
  }

  public TipoParticipanteEntidad getTipoParticipante() {
    return tipoParticipante;
  }

  public ContratoEntidad getContrato() {
    return contrato;
  }

  private void setId(final UUID id) {
    this.id = id;
  }

  private void setPersona(final PersonaEntidad persona) {
    this.persona = persona;
  }

  private void setTipoParticipante(final TipoParticipanteEntidad tipoParticipante) {
    this.tipoParticipante = tipoParticipante;
  }

  private void setContrato(final ContratoEntidad contrato) {
    this.contrato = contrato;
  }

  public static class Builder {
    private UUID id;
    private PersonaEntidad persona;
    private TipoParticipanteEntidad tipoParticipante;
    private ContratoEntidad contrato;

    public Builder id(final UUID id) {
      this.id = id;
      return this;
    }

    public Builder persona(final PersonaEntidad persona) {
      this.persona = persona;
      return this;
    }

    public Builder tipoParticipante(final TipoParticipanteEntidad tipoParticipante) {
      this.tipoParticipante = tipoParticipante;
      return this;
    }

    public Builder contrato(final ContratoEntidad contrato) {
      this.contrato = contrato;
      return this;
    }

    public ParticipanteContratoEntidad build() {
      return new ParticipanteContratoEntidad(this);
    }
  }
}
