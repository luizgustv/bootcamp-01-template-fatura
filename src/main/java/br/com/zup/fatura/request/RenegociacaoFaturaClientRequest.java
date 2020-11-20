package br.com.zup.fatura.request;

import java.math.BigDecimal;
import java.util.UUID;

public class RenegociacaoFaturaClientRequest {

    private UUID identificadorDaFatura;
    private int quantidade;
    private BigDecimal valor;

    public RenegociacaoFaturaClientRequest(UUID identificadorDaFatura,
                                           int quantidade, BigDecimal valor) {
        this.identificadorDaFatura = identificadorDaFatura;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public RenegociacaoFaturaClientRequest(UUID idFatura, RenegociacaoFaturaRequest request) {
        this.identificadorDaFatura = idFatura;
        this.quantidade = request.getQuantidade();
        this.valor = request.getValor();
    }

    public UUID getIdentificadorDaFatura() {
        return identificadorDaFatura;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

}
