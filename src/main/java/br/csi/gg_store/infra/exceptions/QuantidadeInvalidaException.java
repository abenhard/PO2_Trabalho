package br.csi.gg_store.infra.exceptions;

public class QuantidadeInvalidaException extends RuntimeException {
    public QuantidadeInvalidaException(String mensagem)
    {
        super(mensagem);
    }
}
