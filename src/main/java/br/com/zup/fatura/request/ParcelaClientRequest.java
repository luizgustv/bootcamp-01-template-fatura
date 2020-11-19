package br.com.zup.fatura.request;

import java.math.BigDecimal;
import java.util.UUID;

public class ParcelaClientRequest {


    private UUID identificadorDaFatura;
    private int quantidade;
    private BigDecimal valor;

    public ParcelaClientRequest(){}

    public ParcelaClientRequest(UUID idFatura, ParcelaRequest request) {
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
