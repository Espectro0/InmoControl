package com.inmocontrol.dto;

import java.util.UUID;

public final class ParticipanteContratoDTO {
  private UUID id;
  private PersonaDTO persona;
  private TipoParticipanteDTO tipoParticipante;
  private ContratoDTO contrato;
  private UUID personaId;
  private UUID tipoParticipanteId;
  private UUID contratoId;

  private ParticipanteContratoDTO(final Builder builder) {
    setId(builder.id);
    setPersona(builder.persona);
    setTipoParticipante(builder.tipoParticipante);
    setContrato(builder.contrato);
    setPersonaId(builder.personaId);
    setTipoParticipanteId(builder.tipoParticipanteId);
    setContratoId(builder.contratoId);
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

  public UUID getPersonaId() {
    return personaId;
  }

  public UUID getTipoParticipanteId() {
    return tipoParticipanteId;
  }

  public UUID getContratoId() {
    return contratoId;
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

  private void setPersonaId(final UUID personaId) {
    this.personaId = personaId;
  }

  private void setTipoParticipanteId(final UUID tipoParticipanteId) {
    this.tipoParticipanteId = tipoParticipanteId;
  }

  private void setContratoId(final UUID contratoId) {
    this.contratoId = contratoId;
  }

  public static class Builder {
    private UUID id;
    private PersonaDTO persona;
    private TipoParticipanteDTO tipoParticipante;
    private ContratoDTO contrato;
    private UUID personaId;
    private UUID tipoParticipanteId;
    private UUID contratoId;

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

    public Builder personaId(final UUID personaId) {
      this.personaId = personaId;
      return this;
    }

    public Builder tipoParticipanteId(final UUID tipoParticipanteId) {
      this.tipoParticipanteId = tipoParticipanteId;
      return this;
    }

    public Builder contratoId(final UUID contratoId) {
      this.contratoId = contratoId;
      return this;
    }

    public ParticipanteContratoDTO build() {
      return new ParticipanteContratoDTO(this);
    }
  }
}
