package com.inmocontrol.negocio.dominio;

import com.inmocontrol.transversal.UtilTexto;
import java.util.UUID;

public final class ParametroClausulaContratoDominio {
  private UUID id;
  private ParametroDominio parametro;
  private ClausulaPorContratoDominio clausulaPorContrato;
  private String valor;

  private ParametroClausulaContratoDominio(final Builder builder) {
    setId(builder.id);
    setParametro(builder.parametro);
    setClausulaPorContrato(builder.clausulaPorContrato);
    setValor(builder.valor);
  }

  public UUID getId() {
    return id;
  }

  public ParametroDominio getParametro() {
    return parametro;
  }

  public ClausulaPorContratoDominio getClausulaPorContrato() {
    return clausulaPorContrato;
  }

  public String getValor() {
    return valor;
  }

  private void setId(final UUID id) {
    this.id = id;
  }

  private void setParametro(final ParametroDominio parametro) {
    this.parametro = parametro;
  }

  private void setClausulaPorContrato(final ClausulaPorContratoDominio clausulaPorContrato) {
    this.clausulaPorContrato = clausulaPorContrato;
  }

  private void setValor(final String valor) {
    this.valor = UtilTexto.aplicarTrim(valor);
  }

  public static class Builder {
    private UUID id;
    private ParametroDominio parametro;
    private ClausulaPorContratoDominio clausulaPorContrato;
    private String valor;

    public Builder id(final UUID id) {
      this.id = id;
      return this;
    }

    public Builder parametro(final ParametroDominio parametro) {
      this.parametro = parametro;
      return this;
    }

    public Builder clausulaPorContrato(final ClausulaPorContratoDominio clausulaPorContrato) {
      this.clausulaPorContrato = clausulaPorContrato;
      return this;
    }

    public Builder valor(final String valor) {
      this.valor = valor;
      return this;
    }

    public ParametroClausulaContratoDominio build() {
      return new ParametroClausulaContratoDominio(this);
    }
  }
}
