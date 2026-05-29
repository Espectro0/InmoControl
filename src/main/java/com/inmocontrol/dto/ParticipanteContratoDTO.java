package com.inmocontrol.dto;

import java.util.UUID;

import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilUUID;

public final class ParticipanteContratoDTO {
  private UUID id;
  private PersonaDTO persona;
  private TipoParticipanteDTO tipoParticipante;
  private ContratoDTO contrato;
  private UUID personaId;
  private UUID tipoParticipanteId;
  private UUID contratoId;

  public ParticipanteContratoDTO() {
	  setId(UtilUUID.UUID_CERO);
	  setPersona(new PersonaDTO());
	  setTipoParticipante(new TipoParticipanteDTO());
	  setContrato(new ContratoDTO());
	  setPersonaId(UtilUUID.UUID_CERO);
	  setTipoParticipanteId(UtilUUID.UUID_CERO);
	  setContratoId(UtilUUID.UUID_CERO);
  }

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

  public void setId(final UUID id) {
    this.id = id;
  }

  public void setPersona(final PersonaDTO persona) {
    this.persona = UtilObjeto.obtenerValorDefecto(persona, new PersonaDTO());
  }

  public void setTipoParticipante(final TipoParticipanteDTO tipoParticipante) {
    this.tipoParticipante = UtilObjeto.obtenerValorDefecto(tipoParticipante, new TipoParticipanteDTO());
  }

  public void setContrato(final ContratoDTO contrato) {
    this.contrato = UtilObjeto.obtenerValorDefecto(contrato, new ContratoDTO());
  }

  public void setPersonaId(final UUID personaId) {
    this.personaId = personaId;
  }

  public void setTipoParticipanteId(final UUID tipoParticipanteId) {
    this.tipoParticipanteId = tipoParticipanteId;
  }

  public void setContratoId(final UUID contratoId) {
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
