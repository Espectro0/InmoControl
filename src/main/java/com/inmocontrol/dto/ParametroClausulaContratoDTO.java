package com.inmocontrol.dto;

import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class ParametroClausulaContratoDTO {
  private UUID id;
  private ParametroDTO parametro;
  private ClausulaPorContratoDTO clausulaPorContrato;
  private String valor;

  private ParametroClausulaContratoDTO(final Builder builder) {
    setId(builder.id);
    setParametro(builder.parametro);
    setClausulaPorContrato(builder.clausulaPorContrato);
    setValor(builder.valor);
  }

  public UUID getId() {
    return id;
  }

  public ParametroDTO getParametro() {
    return parametro;
  }

  public ClausulaPorContratoDTO getClausulaPorContrato() {
    return clausulaPorContrato;
  }

  public String getValor() {
    return valor;
  }

  private void setId(final UUID id) {
    this.id = id;
  }

  private void setParametro(final ParametroDTO parametro) {
    this.parametro = parametro;
  }

  private void setClausulaPorContrato(final ClausulaPorContratoDTO clausulaPorContrato) {
    this.clausulaPorContrato = clausulaPorContrato;
  }

  private void setValor(final String valor) {
    this.valor = UtilTexto.aplicarTrim(valor);
  }

  public static class Builder {
    private UUID id;
    private ParametroDTO parametro;
    private ClausulaPorContratoDTO clausulaPorContrato;
    private String valor;

    public Builder id(final UUID id) {
      this.id = id;
      return this;
    }

    public Builder parametro(final ParametroDTO parametro) {
      this.parametro = parametro;
      return this;
    }

    public Builder clausulaPorContrato(final ClausulaPorContratoDTO clausulaPorContrato) {
      this.clausulaPorContrato = clausulaPorContrato;
      return this;
    }

    public Builder valor(final String valor) {
      this.valor = valor;
      return this;
    }

    public ParametroClausulaContratoDTO build() {
      return new ParametroClausulaContratoDTO(this);
    }
  }
}
